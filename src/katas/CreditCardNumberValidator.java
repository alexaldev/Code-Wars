package katas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CreditCardNumberValidator {

    public boolean isValid(final String value) {
        return isValid(Long.valueOf(value));
    }

    private boolean isValid(final long value) {

        if (value < 0) {
            throw new IllegalArgumentException("Value must be a positive integer.");
        }

        final List<Long> valueDigits = toDigits(value);
        final int digitsCount = valueDigits.size();

        if ( digitsCount > 16) {
            throw new IllegalArgumentException("Value digits count must be < 16");
        }

        doubleEveryOtherDigit(valueDigits, isOdd(digitsCount) ? 1 : 0);

        final long digitsSum = sum(valueDigits);

        return digitsSum % 10 == 0;
    }

    private void doubleEveryOtherDigit(final List<Long> digits, final int startingIndex) {

        for (int i = startingIndex; i < digits.size(); i+= 2) {
            final long digit = digits.get(i);
            long replaceWith = digit * 2;
            if (replaceWith >= 10) {
                replaceWith -= 9;
            }
            digits.set(i, replaceWith);
        }
    }

    private long sum(List<Long> digits) {

        long result = 0;
        for (long digit : digits) {
            result += digit;
        }
        return result;
    }

    private boolean isOdd(final long value) {
        return (value % 2 != 0);
    }

    private List<Long> toDigits(long value) {

        final List<Long> result = new ArrayList<>();
        LinkedList<Long> stack = new LinkedList<>();

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
        final boolean[] flag = {(0 & 1) == 1};
        System.out.println(new CreditCardNumberValidator().isValid("8675309"));
    }
}
