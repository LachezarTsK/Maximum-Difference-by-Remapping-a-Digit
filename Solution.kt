
import kotlin.math.pow

class Solution {

    private companion object {
        const val NOT_FOUND = -1
        const val DIGIT_TO_INSERT_FOR_MIN_VALUE = 0
        const val DIGIT_TO_INSERT_FOR_MAX_VALUE = 9
    }

    fun minMaxDifference(input: Int): Int {
        val numberOfDigits = findNumberOfDigits(input)
        val minValue = createExtremeValue(input, DIGIT_TO_INSERT_FOR_MIN_VALUE, numberOfDigits)
        val maxValue = createExtremeValue(input, DIGIT_TO_INSERT_FOR_MAX_VALUE, numberOfDigits)

        return maxValue - minValue
    }

    private fun findNumberOfDigits(input: Int): Int {
        var input = input
        var numberOfDigits = 0
        while (input > 0) {
            input /= 10
            ++numberOfDigits
        }
        return if (numberOfDigits != 0) numberOfDigits else 1
    }

    private fun createExtremeValue(input: Int, digitToInsertForExtremeValue: Int, dumberOfDigits: Int): Int {
        var input = input
        var extremeValue = 0
        var digitToReplace = NOT_FOUND
        var extractorForLeftmostValue = (10.0).pow(dumberOfDigits - 1).toInt()

        while (extractorForLeftmostValue > 0) {
            var leftmostDigit = input / extractorForLeftmostValue

            if (digitToReplace == NOT_FOUND && leftmostDigit != digitToInsertForExtremeValue) {
                digitToReplace = leftmostDigit
            }
            if (leftmostDigit == digitToReplace) {
                leftmostDigit = digitToInsertForExtremeValue
            }

            extremeValue = 10 * extremeValue + leftmostDigit
            input %= extractorForLeftmostValue
            extractorForLeftmostValue /= 10
        }
        return extremeValue
    }
}
