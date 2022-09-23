package exercise;

class Sentence {
    public static void printSentence(String sentence) {
        // BEGIN
        if ('!' == sentence.charAt(sentence.length() - 1)) {
            System.out.println(sentence.toUpperCase());
        } else {
            System.out.println(sentence.toLowerCase());
        }
        // END
    }
}
