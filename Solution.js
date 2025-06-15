
/**
 * @param {number} input
 * @return {number}
 */
var minMaxDifference = function (input) {
    const numberOfDigits = findNumberOfDigits(input);
    const minValue = createExtremeValue(input, Util.DIGIT_TO_INSERT_FOR_MIN_VALUE, numberOfDigits);
    const maxValue = createExtremeValue(input, Util.DIGIT_TO_INSERT_FOR_MAX_VALUE, numberOfDigits);

    return maxValue - minValue;
};

class Util {
    static NOT_FOUND = -1;
    static DIGIT_TO_INSERT_FOR_MIN_VALUE = 0;
    static DIGIT_TO_INSERT_FOR_MAX_VALUE = 9;
}

/**
 * @param {number} input
 * @return {number}
 */
function findNumberOfDigits(input) {
    let numberOfDigits = 0;
    while (input > 0) {
        input = Math.floor(input / 10);
        ++numberOfDigits;
    }
    return (numberOfDigits !== 0) ? numberOfDigits : 1;
}

/**
 * @param {number} input
 * @param {number} digitToInsertForExtremeValue
 * @param {number} dumberOfDigits
 * @return {number}
 */
function createExtremeValue(input, digitToInsertForExtremeValue, numberOfDigits) {
    let extremeValue = 0;
    let digitToReplace = Util.NOT_FOUND;
    let extractorForLeftmostValue = Math.pow(10, numberOfDigits - 1);

    while (extractorForLeftmostValue > 0) {
        let leftmostDigit = Math.floor(input / extractorForLeftmostValue);

        if (digitToReplace === Util.NOT_FOUND && leftmostDigit !== digitToInsertForExtremeValue) {
            digitToReplace = leftmostDigit;
        }
        if (leftmostDigit === digitToReplace) {
            leftmostDigit = digitToInsertForExtremeValue;
        }

        extremeValue = 10 * extremeValue + leftmostDigit;
        input %= extractorForLeftmostValue;
        extractorForLeftmostValue = Math.floor(extractorForLeftmostValue / 10);
    }
    return extremeValue;
}
