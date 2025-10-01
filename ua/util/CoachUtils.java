package ua.util;

public class CoachUtils {
    public static boolean isValidExperienceYears(int years) {
        return ValidationHelper.isNumberInRange(years, 0, 50);
    }
}
