package util;

import model.Intensity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("WorkoutUtils Tests")
public class WorkoutUtilsTest {

    @Nested
    @DisplayName("Title Validation Tests")
    class TitleValidationTests {

        @Test
        @DisplayName("Should validate valid titles")
        void testIsValidTitleValid() {
            assertTrue(WorkoutUtils.isValidTitle("Morning Workout"));
            assertTrue(WorkoutUtils.isValidTitle("  Evening Session  "));
            assertTrue(WorkoutUtils.isValidTitle("Very Long Workout Title That Should Still Be Valid"));
        }

        @Test
        @DisplayName("Should reject invalid titles")
        void testIsValidTitleInvalid() {
            assertFalse(WorkoutUtils.isValidTitle("AB"));
            assertFalse(WorkoutUtils.isValidTitle(""));
            assertFalse(WorkoutUtils.isValidTitle("   ")); 
            assertFalse(WorkoutUtils.isValidTitle(null));
        }

        @Test
        @DisplayName("Should handle boundary values for title")
        void testIsValidTitleBoundaryValues() {
            assertTrue(WorkoutUtils.isValidTitle("ABC")); 
            assertTrue(WorkoutUtils.isValidTitle("A".repeat(50)));
            assertFalse(WorkoutUtils.isValidTitle("A".repeat(51))); 
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "ABC", "Morning Workout", "Evening Session", "HIIT Training",
            "Strength Training", "Cardio Blast", "Yoga Flow", "CrossFit WOD"
        })
        @DisplayName("Should validate various valid titles")
        void testIsValidTitleWithValidTitles(String title) {
            assertTrue(WorkoutUtils.isValidTitle(title));
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "AB", "", "   ", "VeryLongWorkoutTitleThatExceedsTheMaximumAllowedLengthForWorkoutTitles"
        })
        @DisplayName("Should reject various invalid titles")
        void testIsValidTitleWithInvalidTitles(String title) {
            assertFalse(WorkoutUtils.isValidTitle(title));
        }
    }

    @Nested
    @DisplayName("Duration Validation Tests")
    class DurationValidationTests {

        @Test
        @DisplayName("Should validate valid durations")
        void testIsValidDurationValid() {
            assertTrue(WorkoutUtils.isValidDuration(5));
            assertTrue(WorkoutUtils.isValidDuration(30));
            assertTrue(WorkoutUtils.isValidDuration(90));
            assertTrue(WorkoutUtils.isValidDuration(180));
        }

        @Test
        @DisplayName("Should reject invalid durations")
        void testIsValidDurationInvalid() {
            assertFalse(WorkoutUtils.isValidDuration(4));
            assertFalse(WorkoutUtils.isValidDuration(0));
            assertFalse(WorkoutUtils.isValidDuration(-10));
            assertFalse(WorkoutUtils.isValidDuration(181));
            assertFalse(WorkoutUtils.isValidDuration(300));
        }

        @Test
        @DisplayName("Should handle boundary values for duration")
        void testIsValidDurationBoundaryValues() {
            assertTrue(WorkoutUtils.isValidDuration(5));
            
            assertTrue(WorkoutUtils.isValidDuration(180));
            
            assertFalse(WorkoutUtils.isValidDuration(4));
            
            assertFalse(WorkoutUtils.isValidDuration(181));
        }

        @Test
        @DisplayName("Should validate middle range durations")
        void testIsValidDurationMiddleRange() {
            assertTrue(WorkoutUtils.isValidDuration(10));
            assertTrue(WorkoutUtils.isValidDuration(45));
            assertTrue(WorkoutUtils.isValidDuration(120));
            assertTrue(WorkoutUtils.isValidDuration(150));
        }

        @ParameterizedTest
        @ValueSource(ints = {5, 10, 15, 30, 45, 60, 90, 120, 150, 180})
        @DisplayName("Should validate various valid durations")
        void testIsValidDurationWithValidValues(int duration) {
            assertTrue(WorkoutUtils.isValidDuration(duration));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 4, 181, 200, 300, -1, -10})
        @DisplayName("Should reject various invalid durations")
        void testIsValidDurationWithInvalidValues(int duration) {
            assertFalse(WorkoutUtils.isValidDuration(duration));
        }
    }

    @Nested
    @DisplayName("Intensity Parsing Tests")
    class IntensityParsingTests {

        @Test
        @DisplayName("Should parse valid intensity strings")
        void testParseIntensityValid() {
            assertEquals(Intensity.LOW, WorkoutUtils.parseIntensity("low"));
            assertEquals(Intensity.MEDIUM, WorkoutUtils.parseIntensity("medium"));
            assertEquals(Intensity.MEDIUM, WorkoutUtils.parseIntensity("med"));
            assertEquals(Intensity.HIGH, WorkoutUtils.parseIntensity("high"));
        }

        @Test
        @DisplayName("Should handle case insensitive parsing")
        void testParseIntensityCaseInsensitive() {
            assertEquals(Intensity.LOW, WorkoutUtils.parseIntensity("LOW"));
            assertEquals(Intensity.LOW, WorkoutUtils.parseIntensity("Low"));
            assertEquals(Intensity.MEDIUM, WorkoutUtils.parseIntensity("MEDIUM"));
            assertEquals(Intensity.MEDIUM, WorkoutUtils.parseIntensity("Medium"));
            assertEquals(Intensity.MEDIUM, WorkoutUtils.parseIntensity("MED"));
            assertEquals(Intensity.MEDIUM, WorkoutUtils.parseIntensity("Med"));
            assertEquals(Intensity.HIGH, WorkoutUtils.parseIntensity("HIGH"));
            assertEquals(Intensity.HIGH, WorkoutUtils.parseIntensity("High"));
        }

        @Test
        @DisplayName("Should handle intensity strings with spaces")
        void testParseIntensityWithSpaces() {
            assertEquals(Intensity.LOW, WorkoutUtils.parseIntensity("  low  "));
            assertEquals(Intensity.MEDIUM, WorkoutUtils.parseIntensity("  medium  "));
            assertEquals(Intensity.MEDIUM, WorkoutUtils.parseIntensity("  med  "));
            assertEquals(Intensity.HIGH, WorkoutUtils.parseIntensity("  high  "));
        }

        @Test
        @DisplayName("Should return null for invalid intensity strings")
        void testParseIntensityInvalid() {
            assertNull(WorkoutUtils.parseIntensity("invalid"));
            assertNull(WorkoutUtils.parseIntensity(""));
            assertNull(WorkoutUtils.parseIntensity("   "));
            assertNull(WorkoutUtils.parseIntensity(null));
        }

        @Test
        @DisplayName("Should return null for partial matches")
        void testParseIntensityPartialMatch() {
            assertNull(WorkoutUtils.parseIntensity("lo"));
            assertNull(WorkoutUtils.parseIntensity("mediumm"));
            assertNull(WorkoutUtils.parseIntensity("highh"));
        }

        @ParameterizedTest
        @ValueSource(strings = {"low", "LOW", "Low", "  low  ", "medium", "MEDIUM", "Medium", "  medium  ", "med", "MED", "Med", "  med  ", "high", "HIGH", "High", "  high  "})
        @DisplayName("Should parse various valid intensity formats")
        void testParseIntensityWithValidFormats(String intensity) {
            Intensity result = WorkoutUtils.parseIntensity(intensity);
            assertNotNull(result);
        }

        @ParameterizedTest
        @ValueSource(strings = {"invalid", "lo", "mediumm", "highh", "", "   ", "test", "xyz"})
        @DisplayName("Should return null for various invalid intensity formats")
        void testParseIntensityWithInvalidFormats(String intensity) {
            assertNull(WorkoutUtils.parseIntensity(intensity));
        }
    }

    @Nested
    @DisplayName("Intensity Validation Tests")
    class IntensityValidationTests {

        @Test
        @DisplayName("Should validate valid intensity strings")
        void testIsValidIntensityValid() {
            assertTrue(WorkoutUtils.isValidIntensity("low"));
            assertTrue(WorkoutUtils.isValidIntensity("medium"));
            assertTrue(WorkoutUtils.isValidIntensity("med"));
            assertTrue(WorkoutUtils.isValidIntensity("high"));
        }

        @Test
        @DisplayName("Should handle case insensitive validation")
        void testIsValidIntensityCaseInsensitive() {
            assertTrue(WorkoutUtils.isValidIntensity("LOW"));
            assertTrue(WorkoutUtils.isValidIntensity("Medium"));
            assertTrue(WorkoutUtils.isValidIntensity("MED"));
            assertTrue(WorkoutUtils.isValidIntensity("HIGH"));
        }

        @Test
        @DisplayName("Should handle intensity strings with spaces")
        void testIsValidIntensityWithSpaces() {
            assertTrue(WorkoutUtils.isValidIntensity("  low  "));
            assertTrue(WorkoutUtils.isValidIntensity("  medium  "));
            assertTrue(WorkoutUtils.isValidIntensity("  med  "));
            assertTrue(WorkoutUtils.isValidIntensity("  high  "));
        }

        @Test
        @DisplayName("Should reject invalid intensity strings")
        void testIsValidIntensityInvalid() {
            assertFalse(WorkoutUtils.isValidIntensity("invalid"));
            assertFalse(WorkoutUtils.isValidIntensity(""));
            assertFalse(WorkoutUtils.isValidIntensity("   "));
            assertFalse(WorkoutUtils.isValidIntensity(null));
        }

        @ParameterizedTest
        @ValueSource(strings = {"low", "LOW", "Low", "  low  ", "medium", "MEDIUM", "Medium", "  medium  ", "med", "MED", "Med", "  med  ", "high", "HIGH", "High", "  high  "})
        @DisplayName("Should validate various valid intensity formats")
        void testIsValidIntensityWithValidFormats(String intensity) {
            assertTrue(WorkoutUtils.isValidIntensity(intensity));
        }

        @ParameterizedTest
        @ValueSource(strings = {"invalid", "lo", "mediumm", "highh", "", "   ", "test", "xyz"})
        @DisplayName("Should reject various invalid intensity formats")
        void testIsValidIntensityWithInvalidFormats(String intensity) {
            assertFalse(WorkoutUtils.isValidIntensity(intensity));
        }
    }

    @Nested
    @DisplayName("Range Validation Tests")
    class RangeValidationTests {

        @Test
        @DisplayName("Should validate entire valid duration range")
        void testValidDurationRange() {
            for (int duration = 5; duration <= 180; duration += 5) {
                assertTrue(WorkoutUtils.isValidDuration(duration), 
                    "Duration " + duration + " should be valid");
            }
        }

        @Test
        @DisplayName("Should reject entire invalid duration range below minimum")
        void testInvalidDurationRangeBelowMinimum() {
            for (int duration = 0; duration < 5; duration++) {
                assertFalse(WorkoutUtils.isValidDuration(duration), 
                    "Duration " + duration + " should be invalid");
            }
        }

        @Test
        @DisplayName("Should reject entire invalid duration range above maximum")
        void testInvalidDurationRangeAboveMaximum() {
            for (int duration = 181; duration <= 300; duration += 10) {
                assertFalse(WorkoutUtils.isValidDuration(duration), 
                    "Duration " + duration + " should be invalid");
            }
        }
    }
}