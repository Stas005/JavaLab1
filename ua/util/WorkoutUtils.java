package ua.util;

public class WorkoutUtils {
    public static boolean isValidTitle(String title) {
        return ValidationHelper.isStringLengthBetween(title, 3, 50);
    }

    public static boolean isValidDuration(int duration) {
        return ValidationHelper.isNumberInRange(duration, 5, 180);
    }

    public static boolean isValidIntensity(String intensity) {
        return intensity != null;
    }
}
