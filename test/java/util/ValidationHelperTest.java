package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ValidationHelper Tests")
public class ValidationHelperTest {

    @Nested
    @DisplayName("String Pattern Matching Tests")
    class StringPatternMatchingTests {

        @Test
        @DisplayName("Should match valid email pattern")
        void testIsStringMatchPatternValid() {
            assertTrue(ValidationHelper.isStringMatchPattern("test@example.com", "^[\\w.-]+@[\\w-]+(\\.[\\w-]+)*\\.[a-zA-Z]{2,}$"));
            assertTrue(ValidationHelper.isStringMatchPattern("user.name@domain.co.uk", "^[\\w.-]+@[\\w-]+(\\.[\\w-]+)*\\.[a-zA-Z]{2,}$"));
            assertTrue(ValidationHelper.isStringMatchPattern("test123@test-domain.org", "^[\\w.-]+@[\\w-]+(\\.[\\w-]+)*\\.[a-zA-Z]{2,}$"));
        }

        @Test
        @DisplayName("Should not match invalid email pattern")
        void testIsStringMatchPatternInvalid() {
            assertFalse(ValidationHelper.isStringMatchPattern("invalid-email", "^[\\w.-]+@[\\w-]+(\\.[\\w-]+)*\\.[a-zA-Z]{2,}$"));
            assertFalse(ValidationHelper.isStringMatchPattern("@example.com", "^[\\w.-]+@[\\w-]+(\\.[\\w-]+)*\\.[a-zA-Z]{2,}$"));
            assertFalse(ValidationHelper.isStringMatchPattern("test@", "^[\\w.-]+@[\\w-]+(\\.[\\w-]+)*\\.[a-zA-Z]{2,}$"));
            assertFalse(ValidationHelper.isStringMatchPattern("test.example.com", "^[\\w.-]+@[\\w-]+(\\.[\\w-]+)*\\.[a-zA-Z]{2,}$"));
        }

        @Test
        @DisplayName("Should return false for null text")
        void testIsStringMatchPatternNullText() {
            assertFalse(ValidationHelper.isStringMatchPattern(null, "^\\w+$"));
        }

        @Test
        @DisplayName("Should return false for null pattern")
        void testIsStringMatchPatternNullPattern() {
            assertFalse(ValidationHelper.isStringMatchPattern("test", null));
        }

        @Test
        @DisplayName("Should return false for both null")
        void testIsStringMatchPatternBothNull() {
            assertFalse(ValidationHelper.isStringMatchPattern(null, null));
        }

        @ParameterizedTest
        @CsvSource({
            "user123, ^\\w+$, true",
            "user-name, ^\\w+$, false",
            "abc, ^[a-z]+$, true",
            "ABC, ^[a-z]+$, false",
            "123, ^\\d+$, true",
            "abc123, ^\\d+$, false"
        })
        @DisplayName("Should match various patterns correctly")
        void testIsStringMatchPatternWithVariousInputs(String text, String pattern, boolean expected) {
            assertEquals(expected, ValidationHelper.isStringMatchPattern(text, pattern));
        }
    }

    @Nested
    @DisplayName("String Length Validation Tests")
    class StringLengthValidationTests {

        @Test
        @DisplayName("Should validate string length within range")
        void testIsStringLengthBetweenValid() {
            assertTrue(ValidationHelper.isStringLengthBetween("abc", 2, 5));
            assertTrue(ValidationHelper.isStringLengthBetween("  test  ", 2, 10));
            assertTrue(ValidationHelper.isStringLengthBetween("a", 1, 1));
            assertTrue(ValidationHelper.isStringLengthBetween("abcdef", 6, 6));
        }

        @Test
        @DisplayName("Should reject string length outside range")
        void testIsStringLengthBetweenInvalid() {
            assertFalse(ValidationHelper.isStringLengthBetween("ab", 3, 5));
            assertFalse(ValidationHelper.isStringLengthBetween("abcdef", 2, 5));
            assertFalse(ValidationHelper.isStringLengthBetween("", 1, 5)); 
            assertFalse(ValidationHelper.isStringLengthBetween("   ", 1, 2)); 
            assertFalse(ValidationHelper.isStringLengthBetween(null, 1, 5)); 
        }

        @Test
        @DisplayName("Should handle boundary values correctly")
        void testIsStringLengthBetweenBoundaryValues() {
            assertTrue(ValidationHelper.isStringLengthBetween("ab", 2, 5));
            assertFalse(ValidationHelper.isStringLengthBetween("a", 2, 5));
            
            assertTrue(ValidationHelper.isStringLengthBetween("abcde", 2, 5));
            assertFalse(ValidationHelper.isStringLengthBetween("abcdef", 2, 5));
        }

        @Test
        @DisplayName("Should trim whitespace before checking length")
        void testIsStringLengthBetweenWithSpaces() {
            assertTrue(ValidationHelper.isStringLengthBetween("  ab  ", 2, 10));
            assertFalse(ValidationHelper.isStringLengthBetween("  a  ", 2, 10));
        }

        @ParameterizedTest
        @CsvSource({
            "abc, 2, 5, true",
            "ab, 2, 5, true",
            "abcde, 2, 5, true",
            "a, 2, 5, false",
            "abcdef, 2, 5, false",
            "'', 1, 5, false",
            "   , 1, 2, false"
        })
        @DisplayName("Should validate string length with various inputs")
        void testIsStringLengthBetweenWithVariousInputs(String text, int min, int max, boolean expected) {
            assertEquals(expected, ValidationHelper.isStringLengthBetween(text, min, max));
        }
    }

    @Nested
    @DisplayName("Integer Range Validation Tests")
    class IntegerRangeValidationTests {

        @Test
        @DisplayName("Should validate integer within range")
        void testIsNumberInRangeIntValid() {
            assertTrue(ValidationHelper.isNumberInRange(5, 1, 10));
            assertTrue(ValidationHelper.isNumberInRange(1, 1, 10));
            assertTrue(ValidationHelper.isNumberInRange(10, 1, 10));
            assertTrue(ValidationHelper.isNumberInRange(0, 0, 0)); 
        }

        @Test
        @DisplayName("Should reject integer outside range")
        void testIsNumberInRangeIntInvalid() {
            assertFalse(ValidationHelper.isNumberInRange(0, 1, 10));
            assertFalse(ValidationHelper.isNumberInRange(11, 1, 10)); 
            assertFalse(ValidationHelper.isNumberInRange(-5, 1, 10)); 
        }

        @Test
        @DisplayName("Should handle boundary values correctly")
        void testIsNumberInRangeIntBoundaryValues() {
            assertTrue(ValidationHelper.isNumberInRange(1, 1, 10));
            assertFalse(ValidationHelper.isNumberInRange(0, 1, 10));
            
            assertTrue(ValidationHelper.isNumberInRange(10, 1, 10));
            assertFalse(ValidationHelper.isNumberInRange(11, 1, 10));
        }

        @ParameterizedTest
        @CsvSource({
            "5, 1, 10, true",
            "1, 1, 10, true",
            "10, 1, 10, true",
            "0, 1, 10, false",
            "11, 1, 10, false",
            "-5, 1, 10, false"
        })
        @DisplayName("Should validate integer range with various inputs")
        void testIsNumberInRangeIntWithVariousInputs(int number, int min, int max, boolean expected) {
            assertEquals(expected, ValidationHelper.isNumberInRange(number, min, max));
        }
    }

    @Nested
    @DisplayName("Double Range Validation Tests")
    class DoubleRangeValidationTests {

        @Test
        @DisplayName("Should validate double within range")
        void testIsNumberInRangeDoubleValid() {
            assertTrue(ValidationHelper.isNumberInRange(5.5, 1.0, 10.0));
            assertTrue(ValidationHelper.isNumberInRange(1.0, 1.0, 10.0)); 
            assertTrue(ValidationHelper.isNumberInRange(10.0, 1.0, 10.0)); 
            assertTrue(ValidationHelper.isNumberInRange(0.0, 0.0, 0.0));
        }

        @Test
        @DisplayName("Should reject double outside range")
        void testIsNumberInRangeDoubleInvalid() {
            assertFalse(ValidationHelper.isNumberInRange(0.9, 1.0, 10.0));
            assertFalse(ValidationHelper.isNumberInRange(10.1, 1.0, 10.0));
            assertFalse(ValidationHelper.isNumberInRange(-5.0, 1.0, 10.0)); 
        }

        @Test
        @DisplayName("Should handle boundary values correctly")
        void testIsNumberInRangeDoubleBoundaryValues() {
            assertTrue(ValidationHelper.isNumberInRange(1.0, 1.0, 10.0));
            assertFalse(ValidationHelper.isNumberInRange(0.9, 1.0, 10.0));
            
            assertTrue(ValidationHelper.isNumberInRange(10.0, 1.0, 10.0));
            assertFalse(ValidationHelper.isNumberInRange(10.1, 1.0, 10.0));
        }

        @Test
        @DisplayName("Should handle precision correctly")
        void testIsNumberInRangeDoublePrecision() {
            assertTrue(ValidationHelper.isNumberInRange(1.0000001, 1.0, 10.0));
            assertTrue(ValidationHelper.isNumberInRange(9.9999999, 1.0, 10.0));
            assertFalse(ValidationHelper.isNumberInRange(0.9999999, 1.0, 10.0));
            assertFalse(ValidationHelper.isNumberInRange(10.0000001, 1.0, 10.0));
        }

        @ParameterizedTest
        @CsvSource({
            "5.5, 1.0, 10.0, true",
            "1.0, 1.0, 10.0, true",
            "10.0, 1.0, 10.0, true",
            "0.9, 1.0, 10.0, false",
            "10.1, 1.0, 10.0, false",
            "-5.0, 1.0, 10.0, false"
        })
        @DisplayName("Should validate double range with various inputs")
        void testIsNumberInRangeDoubleWithVariousInputs(double number, double min, double max, boolean expected) {
            assertEquals(expected, ValidationHelper.isNumberInRange(number, min, max));
        }
    }
}
