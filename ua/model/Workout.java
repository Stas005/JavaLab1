package ua.model;

import ua.util.WorkoutUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Workout {
    private String title;
    private int durationMinutes;
    private String intensity;
    private List<Exercise> exercises;
    
    public Workout() {
        this.exercises = new ArrayList<Exercise>();
    }
    
    public Workout(String title, int durationMinutes, String intensity, List<Exercise> exercises) {
        setTitle(title);
        setDurationMinutes(durationMinutes);
        setIntensity(intensity);
        this.exercises = exercises;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        if (WorkoutUtils.isValidTitle(title)) 
            this.title = title;
    }
    
    public int getDurationMinutes() {
        return durationMinutes;
    }
    
    public void setDurationMinutes(int durationMinutes) {
        if (WorkoutUtils.isValidDuration(durationMinutes)) 
            this.durationMinutes = durationMinutes;
    }
    
    public String getIntensity() {
        return intensity;
    }
    
    public void setIntensity(String intensity) {
        if (WorkoutUtils.isValidIntensity(intensity)) 
            this.intensity = intensity;
    }
    
    public List<Exercise> getExercises() {
        return exercises;
    }
    
    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
    
    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }
    
    public void removeExercise(Exercise exercise) {
        this.exercises.remove(exercise);
    }
    
    public static Workout createWorkout(String title, int durationMinutes, String intensity, List<Exercise> exercises) {
        if (WorkoutUtils.isValidTitle(title) && WorkoutUtils.isValidDuration(durationMinutes) && WorkoutUtils.isValidIntensity(intensity)) {
            return new Workout(title, durationMinutes, intensity, exercises);
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "Workout{" +
                "title='" + title + '\'' +
                ", durationMinutes=" + durationMinutes +
                ", intensity='" + intensity + '\'' +
                ", exercises=" + (exercises != null ? exercises.size() : 0) +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Workout workout = (Workout) o;

        if (durationMinutes != workout.durationMinutes) return false;
        if (title != null ? !title.equals(workout.title) : workout.title != null) return false;
        if (intensity != null ? !intensity.equals(workout.intensity) : workout.intensity != null) return false;
        return exercises != null ? exercises.equals(workout.exercises) : workout.exercises == null;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(title, durationMinutes, intensity, exercises);
    }
}
