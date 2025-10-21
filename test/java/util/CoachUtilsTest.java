package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CoachUtils Tests")
public class CoachUtilsTest {

    @Nested
    @DisplayName("Experience Validation Tests")
    class ExperienceValidationTests {

        @Test
        @DisplayName("Should validate minimum experience years")
        void testIsValidExperienceYearsMinimum() {
            assertTrue(CoachUtils.isValidExperienceYears(0));
        }

        @Test
        @DisplayName("Should validate maximum experience years")
        void testIsValidExperienceYearsMaximum() {
            assertTrue(CoachUtils.isValidExperienceYears(50));
        }

        @Test
        @DisplayName("Should validate middle range experience years")
        void testIsValidExperienceYearsMiddle() {
            assertTrue(CoachUtils.isValidExperienceYears(25));
        }

        @Test
        @DisplayName("Should reject negative experience years")
        void testIsValidExperienceYearsNegative() {
            assertFalse(CoachUtils.isValidExperienceYears(-1));
        }

        @Test
        @DisplayName("Should reject experience years above maximum")
        void testIsValidExperienceYearsAboveMaximum() {
            assertFalse(CoachUtils.isValidExperienceYears(51));
        }

        @Test
        @DisplayName("Should reject large negative experience years")
        void testIsValidExperienceYearsLargeNegative() {
            assertFalse(CoachUtils.isValidExperienceYears(-10));
        }

        @Test
        @DisplayName("Should reject very large experience years")
        void testIsValidExperienceYearsVeryLarge() {
            assertFalse(CoachUtils.isValidExperienceYears(100));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 5, 10, 25, 40, 50})
        @DisplayName("Should validate various valid experience years")
        void testIsValidExperienceYearsWithValidValues(int experienceYears) {
            assertTrue(CoachUtils.isValidExperienceYears(experienceYears));
        }

        @ParameterizedTest
        @ValueSource(ints = {-1, -5, -10, 51, 60, 100, 1000})
        @DisplayName("Should reject various invalid experience years")
        void testIsValidExperienceYearsWithInvalidValues(int experienceYears) {
            assertFalse(CoachUtils.isValidExperienceYears(experienceYears));
        }
    }

    @Nested
    @DisplayName("Boundary Value Tests")
    class BoundaryValueTests {

        @Test
        @DisplayName("Should handle exact boundary values")
        void testBoundaryValues() {
            assertTrue(CoachUtils.isValidExperienceYears(0));
            
            assertTrue(CoachUtils.isValidExperienceYears(50));
            
            assertFalse(CoachUtils.isValidExperienceYears(-1));
            
            assertFalse(CoachUtils.isValidExperienceYears(51));
        }

        @Test
        @DisplayName("Should handle edge cases around boundaries")
        void testEdgeCasesAroundBoundaries() {
            assertTrue(CoachUtils.isValidExperienceYears(1));
            assertTrue(CoachUtils.isValidExperienceYears(49));
            
            assertFalse(CoachUtils.isValidExperienceYears(-2));
            assertFalse(CoachUtils.isValidExperienceYears(52));
        }
    }

    @Nested
    @DisplayName("Range Validation Tests")
    class RangeValidationTests {

        @Test
        @DisplayName("Should validate entire valid range")
        void testValidRange() {
            for (int i = 0; i <= 50; i++) {
                assertTrue(CoachUtils.isValidExperienceYears(i), 
                    "Experience years " + i + " should be valid");
            }
        }

        @Test
        @DisplayName("Should reject entire invalid range below minimum")
        void testInvalidRangeBelowMinimum() {
            for (int i = -100; i < 0; i++) {
                assertFalse(CoachUtils.isValidExperienceYears(i), 
                    "Experience years " + i + " should be invalid");
            }
        }

        @Test
        @DisplayName("Should reject entire invalid range above maximum")
        void testInvalidRangeAboveMaximum() {
            for (int i = 51; i <= 1000; i += 10) {
                assertFalse(CoachUtils.isValidExperienceYears(i), 
                    "Experience years " + i + " should be invalid");
            }
        }
    }

    @Nested
    @DisplayName("Special Value Tests")
    class SpecialValueTests {

        @Test
        @DisplayName("Should handle zero experience years")
        void testZeroExperienceYears() {
            assertTrue(CoachUtils.isValidExperienceYears(0));
        }

        @Test
        @DisplayName("Should handle single digit experience years")
        void testSingleDigitExperienceYears() {
            for (int i = 1; i <= 9; i++) {
                assertTrue(CoachUtils.isValidExperienceYears(i));
            }
        }

        @Test
        @DisplayName("Should handle double digit experience years")
        void testDoubleDigitExperienceYears() {
            for (int i = 10; i <= 50; i++) {
                assertTrue(CoachUtils.isValidExperienceYears(i));
            }
        }

        @Test
        @DisplayName("Should handle typical experience ranges")
        void testTypicalExperienceRanges() {
            assertTrue(CoachUtils.isValidExperienceYears(0));
            assertTrue(CoachUtils.isValidExperienceYears(1));
            
            assertTrue(CoachUtils.isValidExperienceYears(10));
            assertTrue(CoachUtils.isValidExperienceYears(20));
            
            assertTrue(CoachUtils.isValidExperienceYears(30));
            assertTrue(CoachUtils.isValidExperienceYears(40));
            
            assertTrue(CoachUtils.isValidExperienceYears(50));
        }
    }
}