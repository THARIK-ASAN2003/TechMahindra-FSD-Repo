import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;

public class WarehouseManagementSystem {
    // Product class
    static class Product implements Serializable, Comparable<Product> {
        private String productID;
        private String name;
        private int quantity;
        private Location location;
        private double price;

        public Product(String productID, String name, int quantity, Location location, double price) {
            this.productID = productID;
            this.name = name;
            this.quantity = quantity;
            this.location = location;
            this.price = price;
        }

        public String getProductID() { return productID; }
        public String getName() { return name; }
        public int getQuantity() { return quantity; }
        public Location getLocation() { return location; }
        public double getPrice() { return price; }

        public synchronized void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public synchronized boolean decrementQuantity(int amount) {
            if (quantity >= amount) {
                quantity -= amount;
                return true;
            }
            return false;
        }

        @Override
        public int compareTo(Product other) {
            return this.productID.compareTo(other.productID);
        }

        @Override
        public String toString() {
            return String.format("Product{ID='%s', name='%s', quantity=%d, location=%s, price=%.2f}",
                productID, name, quantity, location, price);
        }
    }

    // Location class
    static class Location implements Serializable {
        private int aisle;
        private int shelf;
        private int bin;

        public Location(int aisle, int shelf, int bin) {
            this.aisle = aisle;
            this.shelf = shelf;
            this.bin = bin;
        }

        public int getAisle() { return aisle; }
        public int getShelf() { return shelf; }
        public int getBin() { return bin; }

        @Override
        public String toString() {
            return String.format("Location{aisle=%d, shelf=%d, bin=%d}", aisle, shelf, bin);
        }
    }

    // Order class
    static class Order implements Comparable<Order> {
        private String orderID;
        private Map<String, Integer> productQuantities;
        private Priority priority;
        private LocalDateTime orderTime;
        private OrderStatus status;

        public enum Priority {
            STANDARD(0), EXPEDITED(1);
            private final int value;
            Priority(int value) { this.value = value; }
            public int getValue() { return value; }
        }

        public enum OrderStatus {
            PENDING, PROCESSING, COMPLETED, FAILED
        }

        public Order(String orderID, Priority priority) {
            this.orderID = orderID;
            this.priority = priority;
            this.productQuantities = new HashMap<>();
            this.orderTime = LocalDateTime.now();
            this.status = OrderStatus.PENDING;
        }

        public void addProduct(String productID, int quantity) {
            productQuantities.put(productID, quantity);
        }

        public String getOrderID() { return orderID; }
        public Map<String, Integer> getProductQuantities() { return productQuantities; }
        public Priority getPriority() { return priority; }
        public OrderStatus getStatus() { return status; }
        
        public void setStatus(OrderStatus status) {
            this.status = status;
        }

        @Override
        public int compareTo(Order other) {
            int priorityCompare = Integer.compare(
                other.priority.getValue(),
                this.priority.getValue()
            );
            if (priorityCompare != 0) return priorityCompare;
            return this.orderTime.compareTo(other.orderTime);
        }

        @Override
        public String toString() {
            return String.format("Order{ID='%s', priority=%s, status=%s}", 
                orderID, priority, status);
        }
    }

    // Custom Exceptions
    static class OutOfStockException extends Exception {
        public OutOfStockException(String message) { super(message); }
    }

    static class InvalidLocationException extends Exception {
        public InvalidLocationException(String message) { super(message); }
    }

    // Inventory Manager class
    static class InventoryManager {
        private final ConcurrentHashMap<String, Product> products;
        private final PriorityBlockingQueue<Order> orderQueue;
        private final ExecutorService orderProcessors;
        private final Logger logger;
        private static final String DATA_FILE = "inventory_data.ser";

        public InventoryManager(int numThreads) {
            this.products = new ConcurrentHashMap<>();
            this.orderQueue = new PriorityBlockingQueue<>();
            this.orderProcessors = Executors.newFixedThreadPool(numThreads);
            this.logger = Logger.getLogger(InventoryManager.class.getName());
            setupLogger();
            loadInventory();
        }

        private void setupLogger() {
            try {
                FileHandler fileHandler = new FileHandler("warehouse_inventory.log", true);
                fileHandler.setFormatter(new SimpleFormatter());
                logger.addHandler(fileHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void addProduct(Product product) throws InvalidLocationException {
            validateLocation(product.getLocation());
            products.put(product.getProductID(), product);
            logger.info("Added product: " + product);
            saveInventory();
        }

        public void submitOrder(Order order) {
            orderQueue.offer(order);
            logger.info("Submitted order: " + order.getOrderID());
        }

        public void startOrderProcessing() {
            for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
                orderProcessors.submit(this::processOrders);
            }
        }

        private void processOrders() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Order order = orderQueue.take();
                    processOrder(order);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }

        private void processOrder(Order order) {
            logger.info("Processing order: " + order.getOrderID());
            order.setStatus(Order.OrderStatus.PROCESSING);

            try {
                Map<String, Integer> quantities = order.getProductQuantities();
                for (Map.Entry<String, Integer> entry : quantities.entrySet()) {
                    Product product = products.get(entry.getKey());
                    if (product == null || product.getQuantity() < entry.getValue()) {
                        throw new OutOfStockException("Insufficient stock for product: " + entry.getKey());
                    }
                }

                for (Map.Entry<String, Integer> entry : quantities.entrySet()) {
                    Product product = products.get(entry.getKey());
                    product.decrementQuantity(entry.getValue());
                }

                order.setStatus(Order.OrderStatus.COMPLETED);
                logger.info("Completed order: " + order.getOrderID());
                saveInventory();
            } catch (Exception e) {
                order.setStatus(Order.OrderStatus.FAILED);
                logger.severe("Failed to process order " + order.getOrderID() + ": " + e.getMessage());
            }
        }

        private void validateLocation(Location location) throws InvalidLocationException {
            if (location.getAisle() < 0 || location.getShelf() < 0 || location.getBin() < 0) {
                throw new InvalidLocationException("Invalid location values");
            }
        }

        private void saveInventory() {
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(DATA_FILE))) {
                oos.writeObject(new ArrayList<>(products.values()));
                logger.info("Saved inventory state");
            } catch (IOException e) {
                logger.severe("Failed to save inventory: " + e.getMessage());
            }
        }

        @SuppressWarnings("unchecked")
        private void loadInventory() {
            File file = new File(DATA_FILE);
            if (!file.exists()) return;

            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(DATA_FILE))) {
                List<Product> productList = (List<Product>) ois.readObject();
                productList.forEach(product -> products.put(product.getProductID(), product));
                logger.info("Loaded inventory state");
            } catch (IOException | ClassNotFoundException e) {
                logger.severe("Failed to load inventory: " + e.getMessage());
            }
        }

        public void shutdown() {
            orderProcessors.shutdown();
            try {
                if (!orderProcessors.awaitTermination(60, TimeUnit.SECONDS)) {
                    orderProcessors.shutdownNow();
                }
            } catch (InterruptedException e) {
                orderProcessors.shutdownNow();
            }
            saveInventory();
        }

        public void displayInventory() {
            System.out.println("\nCurrent Inventory:");
            products.values().stream()
                .sorted()
                .forEach(product -> System.out.println(product));
        }
    }

    // Main method
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager(4);

        // Initialize sample inventory
        try {
            manager.addProduct(new Product("P001", "Laptop", 50,
                new Location(1, 1, 1), 999.99));
            manager.addProduct(new Product("P002", "Mouse", 100,
                new Location(1, 1, 2), 29.99));
            manager.addProduct(new Product("P003", "Keyboard", 75,
                new Location(1, 1, 3), 59.99));
            
            System.out.println("Initial inventory loaded");
            manager.displayInventory();

            // Start order processing
            manager.startOrderProcessing();

            // Create and submit sample orders
            Order order1 = new Order("O001", Order.Priority.EXPEDITED);
            order1.addProduct("P001", 2);
            order1.addProduct("P002", 3);
            System.out.println("\nSubmitting expedited order: " + order1);

            Order order2 = new Order("O002", Order.Priority.STANDARD);
            order2.addProduct("P003", 1);
            System.out.println("Submitting standard order: " + order2);

            manager.submitOrder(order1);
            manager.submitOrder(order2);

            // Wait for orders to process
            Thread.sleep(2000);
            
            System.out.println("\nInventory after order processing:");
            manager.displayInventory();

        } catch (InvalidLocationException | InterruptedException e) {
            e.printStackTrace();
        }

        // Shutdown gracefully
        manager.shutdown();
    }
}