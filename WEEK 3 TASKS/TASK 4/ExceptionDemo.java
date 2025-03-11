public class ExceptionDemo {
    public static void main(String[] args) {
        try {
            throw new Exception("This is a custom exception.");
        } catch (Exception e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }
    }
}