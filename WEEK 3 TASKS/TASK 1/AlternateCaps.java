public class AlternateCaps {
    public static void main(String[] args) {
        String input = "anurag";
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            result.append(i % 2 == 0 ? Character.toUpperCase(ch) : Character.toLowerCase(ch));
        }

        System.out.println("Input: " + input);
        System.out.println("Output: " + result);
    }
}