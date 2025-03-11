public class OddNumberChecker {
    public static void checkIfOdd(int number) throws IllegalArgumentException {
        if (number % 2 != 0) {
            throw new IllegalArgumentException("The number " + number + " is odd.");
        }
        System.out.println("The number " + number + " is even.");
    }

    public static void main(String[] args) {
        try {
            checkIfOdd(3); // Change the number to test
        } catch (IllegalArgumentException e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }
    }
}