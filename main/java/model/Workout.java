package model;


import util.WorkoutUtils;

import java.util.List;

public record Workout(String title, int durationMinutes, Intensity intensity, List<Exercise> exercises) {
    public Workout {
        if (!WorkoutUtils.isValidTitle(title))
            throw new IllegalArgumentException("");
        if (!WorkoutUtils.isValidDuration(durationMinutes))
            throw new IllegalArgumentException("");
        if (intensity == null)
            throw new IllegalArgumentException("");

        exercises = exercises == null ? List.of() : List.copyOf(exercises);
    }

    public static Workout createWorkout(String title, int durationMinutes, Intensity intensity, List<Exercise> exercises) {
        if (WorkoutUtils.isValidTitle(title) && WorkoutUtils.isValidDuration(durationMinutes) && intensity != null) {
            return new Workout(title, durationMinutes, intensity, exercises);
        }
        return null;
    }

    public static Workout createWorkout(String title, int durationMinutes, String intensityString, List<Exercise> exercises) {
        Intensity parsed = WorkoutUtils.parseIntensity(intensityString);
        if (parsed != null && WorkoutUtils.isValidTitle(title) && WorkoutUtils.isValidDuration(durationMinutes)) {
            return new Workout(title, durationMinutes, parsed, exercises);
        }
        return null;
    }
}
