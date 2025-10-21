package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Exercise Tests")
public class ExerciseTest {

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create valid exercise")
        void testValidExercise() {
            Exercise exercise = new Exercise("Push-ups", 10, 3);
            
            assertEquals("Push-ups", exercise.name());
            assertEquals(10, exercise.reps());
            assertEquals(3, exercise.sets());
        }

        @Test
        @DisplayName("Should throw exception for invalid name")
        void testExerciseWithInvalidName() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Exercise("", 10, 3);
            });
        }

        @Test
        @DisplayName("Should throw exception for null name")
        void testExerciseWithNullName() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Exercise(null, 10, 3); 
            });
        }

        @Test
        @DisplayName("Should throw exception for invalid reps")
        void testExerciseWithInvalidReps() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Exercise("Push-ups", 0, 3); 
            });
        }

        @Test
        @DisplayName("Should throw exception for invalid sets")
        void testExerciseWithInvalidSets() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Exercise("Push-ups", 10, 0); 
            });
        }

        @ParameterizedTest
        @CsvSource({
            "A, 1, 1",
            "Very Long Exercise Name That Should Still Be Valid, 100, 10",
            "Squats, 15, 5",
            "Push-ups, 20, 3"
        })
        @DisplayName("Should create exercise with valid boundary values")
        void testExerciseWithBoundaryValues(String name, int reps, int sets) {
            Exercise exercise = new Exercise(name, reps, sets);
            
            assertEquals(name, exercise.name());
            assertEquals(reps, exercise.reps());
            assertEquals(sets, exercise.sets());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   ", "VeryLongExerciseNameThatExceedsTheMaximumAllowedLengthForExerciseNamesAndStillContinuesToBeVeryLongIndeed"})
        @DisplayName("Should throw exception for invalid names")
        void testExerciseWithInvalidNames(String invalidName) {
            assertThrows(IllegalArgumentException.class, () -> {
                new Exercise(invalidName, 10, 3);
            });
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, 101, 1000})
        @DisplayName("Should throw exception for invalid reps")
        void testExerciseWithInvalidReps(int invalidReps) {
            assertThrows(IllegalArgumentException.class, () -> {
                new Exercise("Push-ups", invalidReps, 3);
            });
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, 11, 100})
        @DisplayName("Should throw exception for invalid sets")
        void testExerciseWithInvalidSets(int invalidSets) {
            assertThrows(IllegalArgumentException.class, () -> {
                new Exercise("Push-ups", 10, invalidSets);
            });
        }
    }

    @Nested
    @DisplayName("Factory Method Tests")
    class FactoryMethodTests {

        @Test
        @DisplayName("Should create exercise with valid parameters")
        void testCreateExerciseValid() {
            Exercise exercise = Exercise.createExercise("Squats", 15, 4);
            
            assertNotNull(exercise);
            assertEquals("Squats", exercise.name());
            assertEquals(15, exercise.reps());
            assertEquals(4, exercise.sets());
        }

        @Test
        @DisplayName("Should return null for invalid name")
        void testCreateExerciseInvalidName() {
            Exercise exercise = Exercise.createExercise("", 15, 4);
            
            assertNull(exercise);
        }

        @Test
        @DisplayName("Should return null for invalid reps")
        void testCreateExerciseInvalidReps() {
            Exercise exercise = Exercise.createExercise("Squats", 0, 4);
            
            assertNull(exercise);
        }

        @Test
        @DisplayName("Should return null for invalid sets")
        void testCreateExerciseInvalidSets() {
            Exercise exercise = Exercise.createExercise("Squats", 15, 0);
            
            assertNull(exercise);
        }

        @Test
        @DisplayName("Should return null for null name")
        void testCreateExerciseWithNullName() {
            Exercise exercise = Exercise.createExercise(null, 15, 4);
            
            assertNull(exercise);
        }

        @ParameterizedTest
        @CsvSource({
            "Push-ups, 10, 3",
            "Squats, 20, 5",
            "Pull-ups, 8, 4",
            "Planks, 1, 3"
        })
        @DisplayName("Should create exercise with various valid parameters")
        void testCreateExerciseWithValidParameters(String name, int reps, int sets) {
            Exercise exercise = Exercise.createExercise(name, reps, sets);
            
            assertNotNull(exercise);
            assertEquals(name, exercise.name());
            assertEquals(reps, exercise.reps());
            assertEquals(sets, exercise.sets());
        }
    }

    @Nested
    @DisplayName("Equality Tests")
    class EqualityTests {

        @Test
        @DisplayName("Should be equal to identical exercise")
        void testExerciseEquality() {
            Exercise exercise1 = new Exercise("Push-ups", 10, 3);
            Exercise exercise2 = new Exercise("Push-ups", 10, 3);
            Exercise exercise3 = new Exercise("Squats", 10, 3);
            
            assertEquals(exercise1, exercise2);
            assertNotEquals(exercise1, exercise3);
            assertNotEquals(exercise1, null);
            assertEquals(exercise1, exercise1);
        }

        @Test
        @DisplayName("Should have same hash code for equal exercises")
        void testExerciseHashCode() {
            Exercise exercise1 = new Exercise("Push-ups", 10, 3);
            Exercise exercise2 = new Exercise("Push-ups", 10, 3);
            
            assertEquals(exercise1.hashCode(), exercise2.hashCode());
        }

        @Test
        @DisplayName("Should not be equal to different type")
        void testEqualsWithDifferentType() {
            Exercise exercise = new Exercise("Push-ups", 10, 3);
            String notExercise = "not an exercise";
            
            assertNotEquals(exercise, notExercise);
        }

        @Test
        @DisplayName("Should not be equal to exercise with different name")
        void testEqualsWithDifferentName() {
            Exercise exercise1 = new Exercise("Push-ups", 10, 3);
            Exercise exercise2 = new Exercise("Squats", 10, 3);
            
            assertNotEquals(exercise1, exercise2);
        }

        @Test
        @DisplayName("Should not be equal to exercise with different reps")
        void testEqualsWithDifferentReps() {
            Exercise exercise1 = new Exercise("Push-ups", 10, 3);
            Exercise exercise2 = new Exercise("Push-ups", 15, 3);
            
            assertNotEquals(exercise1, exercise2);
        }

        @Test
        @DisplayName("Should not be equal to exercise with different sets")
        void testEqualsWithDifferentSets() {
            Exercise exercise1 = new Exercise("Push-ups", 10, 3);
            Exercise exercise2 = new Exercise("Push-ups", 10, 5);
            
            assertNotEquals(exercise1, exercise2);
        }
    }

    @Nested
    @DisplayName("String Representation Tests")
    class StringRepresentationTests {

        @Test
        @DisplayName("Should return correct toString format")
        void testExerciseToString() {
            Exercise exercise = new Exercise("Push-ups", 10, 3);
            String result = exercise.toString();
            
            assertTrue(result.contains("Push-ups"));
            assertTrue(result.contains("10"));
            assertTrue(result.contains("3"));
        }

        @Test
        @DisplayName("Should include all fields in toString")
        void testToStringIncludesAllFields() {
            Exercise exercise = new Exercise("Squats", 20, 5);
            String result = exercise.toString();
            
            assertTrue(result.contains("Squats"));
            assertTrue(result.contains("20"));
            assertTrue(result.contains("5"));
        }
    }
}
