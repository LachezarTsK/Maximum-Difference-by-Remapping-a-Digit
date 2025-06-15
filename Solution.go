
package main
import "math"

const NOT_FOUND = -1
const DIGIT_TO_INSERT_FOR_MIN_VALUE = 0
const DIGIT_TO_INSERT_FOR_MAX_VALUE = 9

func minMaxDifference(input int) int {
    numberOfDigits := findNumberOfDigits(input)
    minValue := createExtremeValue(input, DIGIT_TO_INSERT_FOR_MIN_VALUE, numberOfDigits)
    maxValue := createExtremeValue(input, DIGIT_TO_INSERT_FOR_MAX_VALUE, numberOfDigits)

    return maxValue - minValue
}

func findNumberOfDigits(input int) int {
    if input == 0 {
        return 1
    }

    numberOfDigits := 0
    for input > 0 {
        input /= 10
        numberOfDigits++
    }
    return numberOfDigits
}

func createExtremeValue(input int, digitToInsertForExtremeValue int, numberOfDigits int) int {
    extremeValue := 0
    digitToReplace := NOT_FOUND
    extractorForLeftmostValue := int(math.Pow(10.0, float64(numberOfDigits - 1)))

    for extractorForLeftmostValue > 0 {
        leftmostDigit := input / extractorForLeftmostValue

        if digitToReplace == NOT_FOUND && leftmostDigit != digitToInsertForExtremeValue {
            digitToReplace = leftmostDigit
        }
        if leftmostDigit == digitToReplace {
            leftmostDigit = digitToInsertForExtremeValue
        }

        extremeValue = 10 * extremeValue + leftmostDigit
        input %= extractorForLeftmostValue
        extractorForLeftmostValue /= 10
    }
    return extremeValue
}
