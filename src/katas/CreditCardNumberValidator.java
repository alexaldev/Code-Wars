package katas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CreditCardNumberValidator {

    public boolean isValid(final String value) {
        return isValid(Integer.valueOf(value));
    }
    
    public boolean isValid(final int value) {

        if (value < 0) {
            throw new IllegalArgumentException("Value must be a positive integer.");
        }

        final List<Integer> valueDigits = toDigits(value);
        final int digitsCount = valueDigits.size();

        if ( digitsCount > 16) {
            throw new IllegalArgumentException("Value digits count must be < 16");
        }

        doubleEveryOtherDigit(valueDigits, isOdd(digitsCount) ? 1 : 0);

        final int digitsSum = sum(valueDigits);

        return digitsSum % 10 == 0;
    }

    private void doubleEveryOtherDigit(final List<Integer> digits, final int startingIndex) {

        for (int i = startingIndex; i < digits.size(); i+= 2) {
            final int digit = digits.get(i);
            int replaceWith = digit * 2;
            if (replaceWith > 10) {
                replaceWith -= 9;
            }
            digits.set(i, replaceWith);
        }
    }

    private int sum(final List<Integer> digits) {

        int result = 0;
        for (Integer i : digits) {
            result += i;
        }
        return result;
    }

    private boolean isOdd(final int value) {
        return (value % 2 != 0);
    }

    private List<Integer> toDigits(int value) {

        final List<Integer> result = new ArrayList<>();
        LinkedList<Integer> stack = new LinkedList<>();

        while (value > 0) {
            stack.push(value % 10);
            value = value / 10;
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new CreditCardNumberValidator().isValid("2626262626262626"));
    }
}
