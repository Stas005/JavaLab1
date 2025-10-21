package util;

import java.util.regex.Pattern;

public class ValidationHelper {
    private ValidationHelper() {
    }

    static boolean isStringMatchPattern(String text, String pattern) {
        if (text == null || pattern == null) {
            return false;
        }
        return Pattern.matches(pattern, text);
    }

    static boolean isStringLengthBetween(String text, int min, int max) {
        if (text == null) {
            return false;
        }
        int length = text.trim().length();
        return length >= min && length <= max;
    }
    
    static boolean isNumberInRange(int number, int min, int max) {
        return number >= min && number <= max;
    }
    
    static boolean isNumberInRange(double number, double min, double max) {
        return number >= min && number <= max;
    }
}
