package ua.util;

public class ProgressUtils {
    public static boolean isValidWeight(double weight) {
        return ValidationHelper.isNumberInRange(weight, 30.0, 300.0);
    }

    public static boolean isValidBmi(double bmi) {
        return ValidationHelper.isNumberInRange(bmi, 10.0, 50.0);
    }
    
    public static boolean isValidDate(java.util.Date date) {
        return date != null;
    }
}
