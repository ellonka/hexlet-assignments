package exercise;

// BEGIN
public class ReversedSequence implements CharSequence{
    String sequence;

    public ReversedSequence(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public int length() {
        return sequence.length();
    }

    @Override
    public char charAt(int index) {
        return sequence.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return sequence.subSequence(start, end);
    }

    @Override
    public String toString() {
        return reverse(sequence);
    }

    private String reverse(String sequence) {
        char[] charArray = sequence.toCharArray();
        String result = "";
        for (int i = sequence.length() - 1; i >= 0; i--) {
            result += charArray[i];
        }
        return result;
    }
}
// END
