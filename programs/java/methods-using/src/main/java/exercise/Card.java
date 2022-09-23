package exercise;

class Card {
    public static void printHiddenCard(String cardNumber, int starsCount) {
        // BEGIN
        String result = "*".repeat(starsCount);

        result += cardNumber.substring(cardNumber.length() - 4);
        System.out.println(result);
        // END
    }
}
