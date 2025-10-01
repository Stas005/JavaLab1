package ua.util;

public class ClientUtils {
    public static boolean isValidLevel(String level) {
        return ValidationHelper.isStringLengthBetween(level, 1, 20);
    }
}
