package util;

public class ExerciseUtils {
    public static boolean isValidName(String name) {
        return ValidationHelper.isStringLengthBetween(name, 1, 100);
    }
    
    public static boolean isValidReps(int reps) {
        return ValidationHelper.isNumberInRange(reps, 1, 100);
    }
    
    public static boolean isValidSets(int sets) {
        return ValidationHelper.isNumberInRange(sets, 1, 10);
    }
}
