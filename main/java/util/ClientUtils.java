package util;


import model.Level;

public class ClientUtils {
    public static boolean isValidLevel(String level) {
        return parseLevel(level) != null;
    }

    public static Level parseLevel(String value) {
        if (value == null) return null;
        return switch (value.trim().toLowerCase()) {
            case "beginner", "beg" -> Level.BEGINNER;
            case "intermediate", "inter" -> Level.INTERMEDIATE;
            case "advanced", "adv" -> Level.ADVANCED;
            default -> null;
        };
    }
}
