package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.EnumSource;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

@DisplayName("Workout Tests")
public class WorkoutTest {

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create valid workout")
        void testValidWorkout() {
            String title = "Morning Cardio";
            int duration = 45;
            Intensity intensity = Intensity.MEDIUM;
            List<Exercise> exercises = new ArrayList<>();
            exercises.add(new Exercise("Push-ups", 10, 3));
            
            Workout workout = new Workout(title, duration, intensity, exercises);
            
            assertEquals(title, workout.title());
            assertEquals(duration, workout.durationMinutes());
            assertEquals(intensity, workout.intensity());
            assertEquals(exercises, workout.exercises());
        }

        @Test
        @DisplayName("Should throw exception for invalid title")
        void testWorkoutWithInvalidTitle() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Workout("", 30, Intensity.LOW, new ArrayList<>()); 
            });
        }

        @Test
        @DisplayName("Should throw exception for null title")
        void testWorkoutWithNullTitle() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Workout(null, 30, Intensity.LOW, new ArrayList<>());
            });
        }

        @Test
        @DisplayName("Should throw exception for invalid duration")
        void testWorkoutWithInvalidDuration() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Workout("Cardio", 0, Intensity.LOW, new ArrayList<>());
            });
        }

        @Test
        @DisplayName("Should throw exception for null intensity")
        void testWorkoutWithNullIntensity() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Workout("Cardio", 30, null, new ArrayList<>()); 
            });
        }

        @Test
        @DisplayName("Should handle null exercises by converting to empty list")
        void testWorkoutWithNullExercises() {
            Workout workout = new Workout("Cardio", 30, Intensity.LOW, null);
            
            assertNotNull(workout);
            assertNotNull(workout.exercises());
            assertTrue(workout.exercises().isEmpty());
        }

        @ParameterizedTest
        @CsvSource({
            "ABC, 5, LOW",
            "Very Long Workout Title That Should Still Be Valid, 120, HIGH",
            "Strength Training, 60, MEDIUM",
            "Cardio Blast, 30, HIGH"
        })
        @DisplayName("Should create workout with valid boundary values")
        void testWorkoutWithBoundaryValues(String title, int duration, Intensity intensity) {
            List<Exercise> exercises = new ArrayList<>();
            exercises.add(new Exercise("Test Exercise", 10, 3));
            
            Workout workout = new Workout(title, duration, intensity, exercises);
            
            assertEquals(title, workout.title());
            assertEquals(duration, workout.durationMinutes());
            assertEquals(intensity, workout.intensity());
            assertEquals(exercises, workout.exercises());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   ", "VeryLongWorkoutTitleThatExceedsTheMaximumAllowedLengthForWorkoutTitles"})
        @DisplayName("Should throw exception for invalid titles")
        void testWorkoutWithInvalidTitles(String invalidTitle) {
            assertThrows(IllegalArgumentException.class, () -> {
                new Workout(invalidTitle, 30, Intensity.LOW, new ArrayList<>());
            });
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, 181, 1000})
        @DisplayName("Should throw exception for invalid durations")
        void testWorkoutWithInvalidDurations(int invalidDuration) {
            assertThrows(IllegalArgumentException.class, () -> {
                new Workout("Cardio", invalidDuration, Intensity.LOW, new ArrayList<>());
            });
        }

        @ParameterizedTest
        @EnumSource(Intensity.class)
        @DisplayName("Should create workout with different intensities")
        void testWorkoutWithDifferentIntensities(Intensity intensity) {
            List<Exercise> exercises = new ArrayList<>();
            exercises.add(new Exercise("Test Exercise", 10, 3));
            
            Workout workout = new Workout("Test Workout", 30, intensity, exercises);
            
            assertEquals(intensity, workout.intensity());
        }
    }

    @Nested
    @DisplayName("Factory Method Tests")
    class FactoryMethodTests {

        @Test
        @DisplayName("Should create workout with valid parameters")
        void testCreateWorkoutValid() {
            String title = "Strength Training";
            int duration = 60;
            Intensity intensity = Intensity.HIGH;
            List<Exercise> exercises = new ArrayList<>();
            exercises.add(new Exercise("Squats", 15, 4));
            
            Workout workout = Workout.createWorkout(title, duration, intensity, exercises);
            
            assertNotNull(workout);
            assertEquals(title, workout.title());
            assertEquals(duration, workout.durationMinutes());
            assertEquals(intensity, workout.intensity());
            assertEquals(exercises, workout.exercises());
        }

        @Test
        @DisplayName("Should return null for invalid title")
        void testCreateWorkoutInvalidTitle() {
            Workout workout = Workout.createWorkout("", 60, Intensity.HIGH, new ArrayList<>());
            
            assertNull(workout);
        }

        @Test
        @DisplayName("Should return null for invalid duration")
        void testCreateWorkoutInvalidDuration() {
            Workout workout = Workout.createWorkout("Strength Training", 0, Intensity.HIGH, new ArrayList<>());
            
            assertNull(workout);
        }

        @Test
        @DisplayName("Should return null for null intensity")
        void testCreateWorkoutNullIntensity() {
            Workout workout = Workout.createWorkout("Strength Training", 60, (Intensity) null, new ArrayList<>());
            
            assertNull(workout);
        }

        @Test
        @DisplayName("Should handle null exercises by converting to empty list")
        void testCreateWorkoutNullExercises() {
            Workout workout = Workout.createWorkout("Strength Training", 60, Intensity.HIGH, null);
            
            assertNotNull(workout);
            assertNotNull(workout.exercises());
            assertTrue(workout.exercises().isEmpty());
        }

        @Test
        @DisplayName("Should return null for null title")
        void testCreateWorkoutNullTitle() {
            Workout workout = Workout.createWorkout(null, 60, Intensity.HIGH, new ArrayList<>());
            
            assertNull(workout);
        }

        @ParameterizedTest
        @CsvSource({
            "Cardio, 30, LOW",
            "Strength, 60, MEDIUM",
            "HIIT, 45, HIGH",
            "Yoga, 90, LOW"
        })
        @DisplayName("Should create workout with various valid parameters")
        void testCreateWorkoutWithValidParameters(String title, int duration, Intensity intensity) {
            List<Exercise> exercises = new ArrayList<>();
            exercises.add(new Exercise("Test Exercise", 10, 3));
            
            Workout workout = Workout.createWorkout(title, duration, intensity, exercises);
            
            assertNotNull(workout);
            assertEquals(title, workout.title());
            assertEquals(duration, workout.durationMinutes());
            assertEquals(intensity, workout.intensity());
            assertEquals(exercises, workout.exercises());
        }
    }

    @Nested
    @DisplayName("Equality Tests")
    class EqualityTests {

        @Test
        @DisplayName("Should be equal to identical workout")
        void testWorkoutEquality() {
            List<Exercise> exercises1 = new ArrayList<>();
            exercises1.add(new Exercise("Push-ups", 10, 3));
            List<Exercise> exercises2 = new ArrayList<>();
            exercises2.add(new Exercise("Push-ups", 10, 3));
            
            Workout workout1 = new Workout("Cardio", 30, Intensity.MEDIUM, exercises1);
            Workout workout2 = new Workout("Cardio", 30, Intensity.MEDIUM, exercises2);
            Workout workout3 = new Workout("Strength", 30, Intensity.MEDIUM, exercises1);
            
            assertEquals(workout1, workout2);
            assertNotEquals(workout1, workout3);
            assertNotEquals(workout1, null);
            assertEquals(workout1, workout1);
        }

        @Test
        @DisplayName("Should have same hash code for equal workouts")
        void testWorkoutHashCode() {
            List<Exercise> exercises1 = new ArrayList<>();
            exercises1.add(new Exercise("Push-ups", 10, 3));
            List<Exercise> exercises2 = new ArrayList<>();
            exercises2.add(new Exercise("Push-ups", 10, 3));
            
            Workout workout1 = new Workout("Cardio", 30, Intensity.MEDIUM, exercises1);
            Workout workout2 = new Workout("Cardio", 30, Intensity.MEDIUM, exercises2);
            
            assertEquals(workout1.hashCode(), workout2.hashCode());
        }

        @Test
        @DisplayName("Should not be equal to different type")
        void testEqualsWithDifferentType() {
            Workout workout = new Workout("Cardio", 30, Intensity.MEDIUM, new ArrayList<>());
            String notWorkout = "not a workout";
            
            assertNotEquals(workout, notWorkout);
        }

        @Test
        @DisplayName("Should not be equal to workout with different title")
        void testEqualsWithDifferentTitle() {
            List<Exercise> exercises = new ArrayList<>();
            exercises.add(new Exercise("Push-ups", 10, 3));
            
            Workout workout1 = new Workout("Cardio", 30, Intensity.MEDIUM, exercises);
            Workout workout2 = new Workout("Strength", 30, Intensity.MEDIUM, exercises);
            
            assertNotEquals(workout1, workout2);
        }

        @Test
        @DisplayName("Should not be equal to workout with different duration")
        void testEqualsWithDifferentDuration() {
            List<Exercise> exercises = new ArrayList<>();
            exercises.add(new Exercise("Push-ups", 10, 3));
            
            Workout workout1 = new Workout("Cardio", 30, Intensity.MEDIUM, exercises);
            Workout workout2 = new Workout("Cardio", 45, Intensity.MEDIUM, exercises);
            
            assertNotEquals(workout1, workout2);
        }

        @Test
        @DisplayName("Should not be equal to workout with different intensity")
        void testEqualsWithDifferentIntensity() {
            List<Exercise> exercises = new ArrayList<>();
            exercises.add(new Exercise("Push-ups", 10, 3));
            
            Workout workout1 = new Workout("Cardio", 30, Intensity.LOW, exercises);
            Workout workout2 = new Workout("Cardio", 30, Intensity.HIGH, exercises);
            
            assertNotEquals(workout1, workout2);
        }

        @Test
        @DisplayName("Should not be equal to workout with different exercises")
        void testEqualsWithDifferentExercises() {
            List<Exercise> exercises1 = new ArrayList<>();
            exercises1.add(new Exercise("Push-ups", 10, 3));
            List<Exercise> exercises2 = new ArrayList<>();
            exercises2.add(new Exercise("Squats", 15, 4));
            
            Workout workout1 = new Workout("Cardio", 30, Intensity.MEDIUM, exercises1);
            Workout workout2 = new Workout("Cardio", 30, Intensity.MEDIUM, exercises2);
            
            assertNotEquals(workout1, workout2);
        }
    }

    @Nested
    @DisplayName("String Representation Tests")
    class StringRepresentationTests {

        @Test
        @DisplayName("Should return correct toString format")
        void testWorkoutToString() {
            List<Exercise> exercises = new ArrayList<>();
            exercises.add(new Exercise("Push-ups", 10, 3));
            exercises.add(new Exercise("Squats", 15, 4));
            
            Workout workout = new Workout("Morning Workout", 45, Intensity.MEDIUM, exercises);
            String result = workout.toString();
            
            assertTrue(result.contains("Morning Workout"));
            assertTrue(result.contains("45"));
            assertTrue(result.contains("MEDIUM"));
            assertTrue(result.contains("Push-ups"));
            assertTrue(result.contains("Squats"));
        }

        @Test
        @DisplayName("Should include all fields in toString")
        void testToStringIncludesAllFields() {
            List<Exercise> exercises = new ArrayList<>();
            exercises.add(new Exercise("Test Exercise", 10, 3));
            
            Workout workout = new Workout("Test Workout", 60, Intensity.HIGH, exercises);
            String result = workout.toString();
            
            assertTrue(result.contains("Test Workout"));
            assertTrue(result.contains("60"));
            assertTrue(result.contains("HIGH"));
            assertTrue(result.contains("Test Exercise"));
        }
    }
}