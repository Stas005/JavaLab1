package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ExerciseUtils Tests")
public class ExerciseUtilsTest {

    @Nested
    @DisplayName("Name Validation Tests")
    class NameValidationTests {

        @Test
        @DisplayName("Should validate correct exercise name")
        void testIsValidExerciseNameValid() {
            assertTrue(ExerciseUtils.isValidName("Push-ups"));
        }

        @Test
        @DisplayName("Should reject name too short")
        void testIsValidExerciseNameTooShort() {
            assertFalse(ExerciseUtils.isValidName(""));
        }

        @Test
        @DisplayName("Should reject name too long")
        void testIsValidExerciseNameTooLong() {
            String longName = "VeryLongExerciseNameThatExceedsTheMaximumAllowedLengthForExerciseNamesAndStillContinuesToBeVeryLongIndeed";
            assertFalse(ExerciseUtils.isValidName(longName));
        }

        @Test
        @DisplayName("Should return false for null name")
        void testIsValidExerciseNameNull() {
            assertFalse(ExerciseUtils.isValidName(null));
        }

        @Test
        @DisplayName("Should return false for empty name")
        void testIsValidExerciseNameEmpty() {
            assertFalse(ExerciseUtils.isValidName(""));
        }

        @Test
        @DisplayName("Should return false for whitespace only")
        void testIsValidExerciseNameWhitespace() {
            assertFalse(ExerciseUtils.isValidName("   "));
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "Push-ups", "Squats", "Pull-ups", "Planks", "Burpees",
            "Lunges", "Crunches", "Mountain Climbers", "Jumping Jacks", "Deadlifts"
        })
        @DisplayName("Should validate various valid exercise names")
        void testIsValidExerciseNameWithValidNames(String name) {
            assertTrue(ExerciseUtils.isValidName(name));
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "", "VeryLongExerciseNameThatExceedsTheMaximumAllowedLengthForExerciseNamesAndStillContinuesToBeVeryLongIndeed"
        })
        @DisplayName("Should reject various invalid exercise names")
        void testIsValidExerciseNameWithInvalidNames(String name) {
            assertFalse(ExerciseUtils.isValidName(name));
        }
    }

    @Nested
    @DisplayName("Reps Validation Tests")
    class RepsValidationTests {

        @Test
        @DisplayName("Should validate minimum reps")
        void testIsValidRepsMinimum() {
            assertTrue(ExerciseUtils.isValidReps(1));
        }

        @Test
        @DisplayName("Should validate maximum reps")
        void testIsValidRepsMaximum() {
            assertTrue(ExerciseUtils.isValidReps(100));
        }

        @Test
        @DisplayName("Should validate middle range reps")
        void testIsValidRepsMiddle() {
            assertTrue(ExerciseUtils.isValidReps(50));
        }

        @Test
        @DisplayName("Should reject zero reps")
        void testIsValidRepsZero() {
            assertFalse(ExerciseUtils.isValidReps(0));
        }

        @Test
        @DisplayName("Should reject negative reps")
        void testIsValidRepsNegative() {
            assertFalse(ExerciseUtils.isValidReps(-1));
        }

        @Test
        @DisplayName("Should reject reps above maximum")
        void testIsValidRepsAboveMaximum() {
            assertFalse(ExerciseUtils.isValidReps(101));
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 5, 10, 15, 20, 25, 50, 75, 100})
        @DisplayName("Should validate various valid reps")
        void testIsValidRepsWithValidValues(int reps) {
            assertTrue(ExerciseUtils.isValidReps(reps));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -5, 101, 150, 200, 1000})
        @DisplayName("Should reject various invalid reps")
        void testIsValidRepsWithInvalidValues(int reps) {
            assertFalse(ExerciseUtils.isValidReps(reps));
        }
    }

    @Nested
    @DisplayName("Sets Validation Tests")
    class SetsValidationTests {

        @Test
        @DisplayName("Should validate minimum sets")
        void testIsValidSetsMinimum() {
            assertTrue(ExerciseUtils.isValidSets(1));
        }

        @Test
        @DisplayName("Should validate maximum sets")
        void testIsValidSetsMaximum() {
            assertTrue(ExerciseUtils.isValidSets(10));
        }

        @Test
        @DisplayName("Should validate middle range sets")
        void testIsValidSetsMiddle() {
            assertTrue(ExerciseUtils.isValidSets(5));
        }

        @Test
        @DisplayName("Should reject zero sets")
        void testIsValidSetsZero() {
            assertFalse(ExerciseUtils.isValidSets(0));
        }

        @Test
        @DisplayName("Should reject negative sets")
        void testIsValidSetsNegative() {
            assertFalse(ExerciseUtils.isValidSets(-1));
        }

        @Test
        @DisplayName("Should reject sets above maximum")
        void testIsValidSetsAboveMaximum() {
            assertFalse(ExerciseUtils.isValidSets(11));
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
        @DisplayName("Should validate various valid sets")
        void testIsValidSetsWithValidValues(int sets) {
            assertTrue(ExerciseUtils.isValidSets(sets));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -5, 11, 15, 20, 100})
        @DisplayName("Should reject various invalid sets")
        void testIsValidSetsWithInvalidValues(int sets) {
            assertFalse(ExerciseUtils.isValidSets(sets));
        }
    }

    @Nested
    @DisplayName("Boundary Value Tests")
    class BoundaryValueTests {

        @Test
        @DisplayName("Should handle exact boundary values for reps")
        void testRepsBoundaryValues() {
            assertTrue(ExerciseUtils.isValidReps(1));
            
            assertTrue(ExerciseUtils.isValidReps(100));
            
            assertFalse(ExerciseUtils.isValidReps(0));
            
            assertFalse(ExerciseUtils.isValidReps(101));
        }

        @Test
        @DisplayName("Should handle exact boundary values for sets")
        void testSetsBoundaryValues() {
            assertTrue(ExerciseUtils.isValidSets(1));
            
            assertTrue(ExerciseUtils.isValidSets(10));
            
            assertFalse(ExerciseUtils.isValidSets(0));
            
            assertFalse(ExerciseUtils.isValidSets(11));
        }

        @Test
        @DisplayName("Should handle edge cases around boundaries")
        void testEdgeCasesAroundBoundaries() {
            assertTrue(ExerciseUtils.isValidReps(2));
            assertTrue(ExerciseUtils.isValidReps(99));
            
            assertFalse(ExerciseUtils.isValidReps(-1));
            assertFalse(ExerciseUtils.isValidReps(102));
            
            assertTrue(ExerciseUtils.isValidSets(2));
            assertTrue(ExerciseUtils.isValidSets(9));
            
            assertFalse(ExerciseUtils.isValidSets(-1));
            assertFalse(ExerciseUtils.isValidSets(12));
        }
    }

    @Nested
    @DisplayName("Range Validation Tests")
    class RangeValidationTests {

        @Test
        @DisplayName("Should validate entire valid range for reps")
        void testValidRepsRange() {
            for (int i = 1; i <= 100; i++) {
                assertTrue(ExerciseUtils.isValidReps(i), 
                    "Reps " + i + " should be valid");
            }
        }

        @Test
        @DisplayName("Should validate entire valid range for sets")
        void testValidSetsRange() {
            for (int i = 1; i <= 10; i++) {
                assertTrue(ExerciseUtils.isValidSets(i), 
                    "Sets " + i + " should be valid");
            }
        }

        @Test
        @DisplayName("Should reject entire invalid range below minimum for reps")
        void testInvalidRepsRangeBelowMinimum() {
            for (int i = -100; i < 1; i++) {
                assertFalse(ExerciseUtils.isValidReps(i), 
                    "Reps " + i + " should be invalid");
            }
        }

        @Test
        @DisplayName("Should reject entire invalid range above maximum for reps")
        void testInvalidRepsRangeAboveMaximum() {
            for (int i = 101; i <= 1000; i += 10) {
                assertFalse(ExerciseUtils.isValidReps(i), 
                    "Reps " + i + " should be invalid");
            }
        }

        @Test
        @DisplayName("Should reject entire invalid range below minimum for sets")
        void testInvalidSetsRangeBelowMinimum() {
            for (int i = -100; i < 1; i++) {
                assertFalse(ExerciseUtils.isValidSets(i), 
                    "Sets " + i + " should be invalid");
            }
        }

        @Test
        @DisplayName("Should reject entire invalid range above maximum for sets")
        void testInvalidSetsRangeAboveMaximum() {
            for (int i = 11; i <= 100; i += 5) {
                assertFalse(ExerciseUtils.isValidSets(i), 
                    "Sets " + i + " should be invalid");
            }
        }
    }
}