
public class Solution {

    private static final int NOT_FOUND = -1;
    private static final int DIGIT_TO_INSERT_FOR_MIN_VALUE = 0;
    private static final int DIGIT_TO_INSERT_FOR_MAX_VALUE = 9;

    public int minMaxDifference(int input) {
        int numberOfDigits = findNumberOfDigits(input);
        int minValue = createExtremeValue(input, DIGIT_TO_INSERT_FOR_MIN_VALUE, numberOfDigits);
        int maxValue = createExtremeValue(input, DIGIT_TO_INSERT_FOR_MAX_VALUE, numberOfDigits);

        return maxValue - minValue;
    }

    private int findNumberOfDigits(int input) {
        int numberOfDigits = 0;
        while (input > 0) {
            input /= 10;
            ++numberOfDigits;
        }
        return (numberOfDigits != 0) ? numberOfDigits : 1;
    }

    private int createExtremeValue(int input, int digitToInsertForExtremeValue, int numberOfDigits) {
        int extremeValue = 0;
        int digitToReplace = NOT_FOUND;
        int extractorForLeftmostValue = (int) Math.pow(10, numberOfDigits - 1);

        while (extractorForLeftmostValue > 0) {
            int leftmostDigit = input / extractorForLeftmostValue;

            if (digitToReplace == NOT_FOUND && leftmostDigit != digitToInsertForExtremeValue) {
                digitToReplace = leftmostDigit;
            }
            if (leftmostDigit == digitToReplace) {
                leftmostDigit = digitToInsertForExtremeValue;
            }

            extremeValue = 10 * extremeValue + leftmostDigit;
            input %= extractorForLeftmostValue;
            extractorForLeftmostValue /= 10;
        }
        return extremeValue;
    }
}
