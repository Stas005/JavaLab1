package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserUtils Tests")
public class UserUtilsTest {

    @Nested
    @DisplayName("Name Formatting Tests")
    class NameFormattingTests {

        @Test
        @DisplayName("Should format name correctly")
        void testFormatName() {
            String result = UserUtils.capitalizeText("john");
            assertEquals("John", result);
        }

        @Test
        @DisplayName("Should handle already formatted name")
        void testFormatNameAlreadyFormatted() {
            String result = UserUtils.capitalizeText("John");
            assertEquals("John", result);
        }

        @Test
        @DisplayName("Should handle all caps name")
        void testFormatNameAllCaps() {
            String result = UserUtils.capitalizeText("JOHN");
            assertEquals("John", result);
        }

        @Test
        @DisplayName("Should handle mixed case name")
        void testFormatNameMixedCase() {
            String result = UserUtils.capitalizeText("jOhN");
            assertEquals("John", result);
        }

        @Test
        @DisplayName("Should handle name with spaces")
        void testFormatNameWithSpaces() {
            String result = UserUtils.capitalizeText("  john  ");
            assertEquals("John", result);
        }

        @Test
        @DisplayName("Should return null for null input")
        void testFormatNameNull() {
            String result = UserUtils.capitalizeText(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Should return empty string for empty string")
        void testFormatNameEmpty() {
            String result = UserUtils.capitalizeText("");
            assertEquals("", result);
        }

        @Test
        @DisplayName("Should return whitespace for whitespace only")
        void testFormatNameWhitespace() {
            String result = UserUtils.capitalizeText("   ");
            assertEquals("   ", result);
        }

        @ParameterizedTest
        @ValueSource(strings = {"alice", "BOB", "cHaRlIe", "  david  ", "EVE"})
        @DisplayName("Should format various name formats correctly")
        void testFormatNameWithVariousInputs(String input) {
            String result = UserUtils.capitalizeText(input);
            assertNotNull(result);
            assertTrue(result.matches("^[A-Z][a-z]+$"));
        }
    }

    @Nested
    @DisplayName("Email Validation Tests")
    class EmailValidationTests {

        @Test
        @DisplayName("Should validate correct email")
        void testIsValidEmailValid() {
            assertTrue(UserUtils.isValidEmail("john.doe@university.edu"));
        }

        @Test
        @DisplayName("Should reject invalid email format")
        void testIsValidEmailInvalid() {
            assertFalse(UserUtils.isValidEmail("invalid-email"));
        }

        @Test
        @DisplayName("Should reject email without @")
        void testIsValidEmailNoAt() {
            assertFalse(UserUtils.isValidEmail("johndoeuniversity.edu"));
        }

        @Test
        @DisplayName("Should reject email without domain")
        void testIsValidEmailNoDomain() {
            assertFalse(UserUtils.isValidEmail("john.doe@"));
        }

        @Test
        @DisplayName("Should reject email without local part")
        void testIsValidEmailNoLocal() {
            assertFalse(UserUtils.isValidEmail("@university.edu"));
        }

        @Test
        @DisplayName("Should return false for null email")
        void testIsValidEmailNull() {
            assertFalse(UserUtils.isValidEmail(null));
        }

        @Test
        @DisplayName("Should return false for empty email")
        void testIsValidEmailEmpty() {
            assertFalse(UserUtils.isValidEmail(""));
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "user@domain.com",
            "test.email@university.edu",
            "user123@example.org",
            "a@b.co"
        })
        @DisplayName("Should validate various valid email formats")
        void testIsValidEmailWithValidEmails(String email) {
            assertTrue(UserUtils.isValidEmail(email));
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "invalid-email",
            "@domain.com",
            "user@",
            "user@domain",
            "user.domain.com",
            "user@.com",
            "user@domain.",
            ""
        })
        @DisplayName("Should reject various invalid email formats")
        void testIsValidEmailWithInvalidEmails(String email) {
            assertFalse(UserUtils.isValidEmail(email));
        }
    }

    @Nested
    @DisplayName("Email Generation Tests")
    class EmailGenerationTests {

        @Test
        @DisplayName("Should generate email correctly")
        void testGenerateEmail() {
            String result = UserUtils.generateEmailFromNames("john", "doe");
            assertEquals("john.doe@university.edu", result);
        }

        @Test
        @DisplayName("Should handle names with spaces")
        void testGenerateEmailWithSpaces() {
            String result = UserUtils.generateEmailFromNames("  john  ", "  doe  ");
            assertEquals("john.doe@university.edu", result);
        }

        @Test
        @DisplayName("Should handle mixed case names")
        void testGenerateEmailMixedCase() {
            String result = UserUtils.generateEmailFromNames("JOHN", "DOE");
            assertEquals("john.doe@university.edu", result);
        }

        @Test
        @DisplayName("Should return null for null first name")
        void testGenerateEmailNullFirstName() {
            String result = UserUtils.generateEmailFromNames(null, "doe");
            assertNull(result);
        }

        @Test
        @DisplayName("Should return null for null last name")
        void testGenerateEmailNullLastName() {
            String result = UserUtils.generateEmailFromNames("john", null);
            assertNull(result);
        }

        @Test
        @DisplayName("Should return null for empty first name")
        void testGenerateEmailEmptyFirstName() {
            String result = UserUtils.generateEmailFromNames("", "doe");
            assertNull(result);
        }

        @Test
        @DisplayName("Should return null for empty last name")
        void testGenerateEmailEmptyLastName() {
            String result = UserUtils.generateEmailFromNames("john", "");
            assertNull(result);
        }

        @ParameterizedTest
        @CsvSource({
            "alice, bob, alice.bob@university.edu",
            "JANE, SMITH, jane.smith@university.edu",
            "  charlie  ,  brown  , charlie.brown@university.edu"
        })
        @DisplayName("Should generate email with various name combinations")
        void testGenerateEmailWithVariousNames(String firstName, String lastName, String expectedEmail) {
            String result = UserUtils.generateEmailFromNames(firstName, lastName);
            assertEquals(expectedEmail, result);
        }
    }

    @Nested
    @DisplayName("Name Validation Tests")
    class NameValidationTests {

        @Test
        @DisplayName("Should validate correct name")
        void testIsValidNameValid() {
            assertTrue(UserUtils.isValidName("John"));
        }

        @Test
        @DisplayName("Should reject name too short")
        void testIsValidNameTooShort() {
            assertFalse(UserUtils.isValidName("A"));
        }

        @Test
        @DisplayName("Should reject name too long")
        void testIsValidNameTooLong() {
            String longName = "VeryLongNameThatExceedsTheMaximumAllowedLengthForNamesAndStillContinues";
            assertFalse(UserUtils.isValidName(longName));
        }

        @Test
        @DisplayName("Should validate name with numbers")
        void testIsValidNameWithNumbers() {
            assertTrue(UserUtils.isValidName("John123"));
        }

        @Test
        @DisplayName("Should validate name with special characters")
        void testIsValidNameWithSpecialChars() {
            assertTrue(UserUtils.isValidName("John-Doe"));
        }

        @Test
        @DisplayName("Should return false for null name")
        void testIsValidNameNull() {
            assertFalse(UserUtils.isValidName(null));
        }

        @Test
        @DisplayName("Should return false for empty name")
        void testIsValidNameEmpty() {
            assertFalse(UserUtils.isValidName(""));
        }

        @Test
        @DisplayName("Should return false for whitespace only")
        void testIsValidNameWhitespace() {
            assertFalse(UserUtils.isValidName("   "));
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "Alice", "Bob", "Charlie", "David", "Eve",
            "John", "Jane", "Michael", "Sarah", "Robert"
        })
        @DisplayName("Should validate various valid names")
        void testIsValidNameWithValidNames(String name) {
            assertTrue(UserUtils.isValidName(name));
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "A", "VeryLongNameThatExceedsTheMaximumAllowedLengthForNamesAndStillContinues"
        })
        @DisplayName("Should reject various invalid names")
        void testIsValidNameWithInvalidNames(String name) {
            assertFalse(UserUtils.isValidName(name));
        }
    }

    @Nested
    @DisplayName("Text Capitalization Tests")
    class TextCapitalizationTests {

        @Test
        @DisplayName("Should capitalize text correctly")
        void testCapitalizeText() {
            String result = UserUtils.capitalizeText("hello world");
            assertEquals("Hello world", result);
        }

        @Test
        @DisplayName("Should handle already capitalized text")
        void testCapitalizeTextAlreadyCapitalized() {
            String result = UserUtils.capitalizeText("Hello world");
            assertEquals("Hello world", result);
        }

        @Test
        @DisplayName("Should handle all caps text")
        void testCapitalizeTextAllCaps() {
            String result = UserUtils.capitalizeText("HELLO WORLD");
            assertEquals("Hello world", result);
        }

        @Test
        @DisplayName("Should handle single word")
        void testCapitalizeTextSingleWord() {
            String result = UserUtils.capitalizeText("hello");
            assertEquals("Hello", result);
        }

        @Test
        @DisplayName("Should handle empty string")
        void testCapitalizeTextEmpty() {
            String result = UserUtils.capitalizeText("");
            assertEquals("", result);
        }

        @Test
        @DisplayName("Should return null for null input")
        void testCapitalizeTextNull() {
            String result = UserUtils.capitalizeText(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Should handle text with spaces")
        void testCapitalizeTextWithSpaces() {
            String result = UserUtils.capitalizeText("  hello world  ");
            assertEquals("Hello world", result);
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "hello", "HELLO", "hELLO", "  hello  ",
            "hello world", "HELLO WORLD", "hELLO wORLD"
        })
        @DisplayName("Should capitalize various text formats correctly")
        void testCapitalizeTextWithVariousInputs(String input) {
            String result = UserUtils.capitalizeText(input);
            assertNotNull(result);
            assertTrue(result.matches("^[A-Z].*"));
        }
    }
}