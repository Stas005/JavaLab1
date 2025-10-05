package ua.model;

import ua.util.ExerciseUtils;

public record Exercise(String name, int reps, int sets) {
    public Exercise {
        if (!ExerciseUtils.isValidName(name))
            throw new IllegalArgumentException("");
        if (!ExerciseUtils.isValidReps(reps))
            throw new IllegalArgumentException("");
        if (!ExerciseUtils.isValidSets(sets))
            throw new IllegalArgumentException("");
    }

    public static Exercise createExercise(String name, int reps, int sets) {
        if (ExerciseUtils.isValidName(name) && ExerciseUtils.isValidReps(reps) && ExerciseUtils.isValidSets(sets)) {
            return new Exercise(name, reps, sets);
        }
        return null;
    }
}
