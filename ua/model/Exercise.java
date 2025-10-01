package ua.model;

import ua.util.ExerciseUtils;

import java.util.Objects;

public class Exercise {
    private String name;
    private int reps;
    private int sets;

    public Exercise() {
    }
    
    public Exercise(String name, int reps, int sets) {
        setName(name);
        setReps(reps);
        setSets(sets);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        if (ExerciseUtils.isValidName(name)) {
            this.name = name;
        }
    }
    
    public int getReps() {
        return reps;
    }
    
    public void setReps(int reps) {
        if (ExerciseUtils.isValidReps(reps)) {
            this.reps = reps;
        }
    }
    
    public int getSets() {
        return sets;
    }
    
    public void setSets(int sets) {
        if (ExerciseUtils.isValidSets(sets)) {
            this.sets = sets;
        }
    }
    
    public static Exercise createExercise(String name, int reps, int sets) {
        if (ExerciseUtils.isValidName(name) && ExerciseUtils.isValidReps(reps) && ExerciseUtils.isValidSets(sets)) {
            return new Exercise(name, reps, sets);
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", reps=" + reps +
                ", sets=" + sets +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return reps == exercise.reps &&
                sets == exercise.sets &&
                name.equals(exercise.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, reps, sets);
    }
}
