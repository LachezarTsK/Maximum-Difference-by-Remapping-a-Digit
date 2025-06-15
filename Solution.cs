
using System;

public class Solution
{
    private static readonly int NOT_FOUND = -1;
    private static readonly int DIGIT_TO_INSERT_FOR_MIN_VALUE = 0;
    private static readonly int DIGIT_TO_INSERT_FOR_MAX_VALUE = 9;

    public int MinMaxDifference(int input)
    {
        int numberOfDigits = FindNumberOfDigits(input);
        int minValue = CreateExtremeValue(input, DIGIT_TO_INSERT_FOR_MIN_VALUE, numberOfDigits);
        int maxValue = CreateExtremeValue(input, DIGIT_TO_INSERT_FOR_MAX_VALUE, numberOfDigits);

        return maxValue - minValue;
    }

    private int FindNumberOfDigits(int input)
    {
        int numberOfDigits = 0;
        while (input > 0)
        {
            input /= 10;
            ++numberOfDigits;
        }
        return (numberOfDigits != 0) ? numberOfDigits : 1;
    }

    private int CreateExtremeValue(int input, int digitToInsertForExtremeValue, int numberOfDigits)
    {
        int extremeValue = 0;
        int digitToReplace = NOT_FOUND;
        int extractorForLeftmostValue = (int)Math.Pow(10, numberOfDigits - 1);

        while (extractorForLeftmostValue > 0)
        {
            int leftmostDigit = input / extractorForLeftmostValue;

            if (digitToReplace == NOT_FOUND && leftmostDigit != digitToInsertForExtremeValue)
            {
                digitToReplace = leftmostDigit;
            }
            if (leftmostDigit == digitToReplace)
            {
                leftmostDigit = digitToInsertForExtremeValue;
            }

            extremeValue = 10 * extremeValue + leftmostDigit;
            input %= extractorForLeftmostValue;
            extractorForLeftmostValue /= 10;
        }
        return extremeValue;
    }
}
