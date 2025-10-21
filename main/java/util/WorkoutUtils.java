package util;

import model.Intensity;

public class WorkoutUtils {
    public static boolean isValidTitle(String title) {
        return ValidationHelper.isStringLengthBetween(title, 3, 50);
    }

    public static boolean isValidDuration(int duration) {
        return ValidationHelper.isNumberInRange(duration, 5, 180);
    }

    public static Intensity parseIntensity(String value) {
        if (value == null) return null;
        return switch (value.trim().toLowerCase()) {
            case "low" -> Intensity.LOW;
            case "medium", "med" -> Intensity.MEDIUM;
            case "high" -> Intensity.HIGH;
            default -> null;
        };
    }

    public static boolean isValidIntensity(String value) {
        return parseIntensity(value) != null;
    }
}
