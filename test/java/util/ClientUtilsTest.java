package util;

import model.Level;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ClientUtils Tests")
public class ClientUtilsTest {

    @Nested
    @DisplayName("Level Parsing Tests")
    class LevelParsingTests {

        @Test
        @DisplayName("Should parse beginner level correctly")
        void testParseLevelBeginner() {
            Level result = ClientUtils.parseLevel("beginner");
            assertEquals(Level.BEGINNER, result);
        }

        @Test
        @DisplayName("Should parse intermediate level correctly")
        void testParseLevelIntermediate() {
            Level result = ClientUtils.parseLevel("intermediate");
            assertEquals(Level.INTERMEDIATE, result);
        }

        @Test
        @DisplayName("Should parse advanced level correctly")
        void testParseLevelAdvanced() {
            Level result = ClientUtils.parseLevel("advanced");
            assertEquals(Level.ADVANCED, result);
        }

        @Test
        @DisplayName("Should handle case insensitive parsing")
        void testParseLevelCaseInsensitive() {
            assertEquals(Level.BEGINNER, ClientUtils.parseLevel("BEGINNER"));
            assertEquals(Level.INTERMEDIATE, ClientUtils.parseLevel("Intermediate"));
            assertEquals(Level.ADVANCED, ClientUtils.parseLevel("AdVaNcEd"));
        }

        @Test
        @DisplayName("Should handle abbreviated forms")
        void testParseLevelAbbreviated() {
            assertEquals(Level.BEGINNER, ClientUtils.parseLevel("beg"));
            assertEquals(Level.INTERMEDIATE, ClientUtils.parseLevel("inter"));
            assertEquals(Level.ADVANCED, ClientUtils.parseLevel("adv"));
        }

        @Test
        @DisplayName("Should handle abbreviated forms case insensitive")
        void testParseLevelAbbreviatedCaseInsensitive() {
            assertEquals(Level.BEGINNER, ClientUtils.parseLevel("BEG"));
            assertEquals(Level.INTERMEDIATE, ClientUtils.parseLevel("INTER"));
            assertEquals(Level.ADVANCED, ClientUtils.parseLevel("ADV"));
        }

        @Test
        @DisplayName("Should return null for invalid level")
        void testParseLevelInvalid() {
            Level result = ClientUtils.parseLevel("invalid");
            assertNull(result);
        }

        @Test
        @DisplayName("Should return null for null input")
        void testParseLevelNull() {
            Level result = ClientUtils.parseLevel(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Should return null for empty string")
        void testParseLevelEmpty() {
            Level result = ClientUtils.parseLevel("");
            assertNull(result);
        }

        @Test
        @DisplayName("Should return null for whitespace only")
        void testParseLevelWhitespace() {
            Level result = ClientUtils.parseLevel("   ");
            assertNull(result);
        }

        @Test
        @DisplayName("Should handle names with spaces")
        void testParseLevelWithSpaces() {
            assertEquals(Level.BEGINNER, ClientUtils.parseLevel("  beginner  "));
            assertEquals(Level.INTERMEDIATE, ClientUtils.parseLevel("  intermediate  "));
            assertEquals(Level.ADVANCED, ClientUtils.parseLevel("  advanced  "));
        }

        @ParameterizedTest
        @CsvSource({
            "beginner, BEGINNER",
            "intermediate, INTERMEDIATE", 
            "advanced, ADVANCED",
            "beg, BEGINNER",
            "inter, INTERMEDIATE",
            "adv, ADVANCED",
            "BEGINNER, BEGINNER",
            "INTERMEDIATE, INTERMEDIATE",
            "ADVANCED, ADVANCED"
        })
        @DisplayName("Should parse various level string formats")
        void testParseLevelWithVariousFormats(String input, Level expected) {
            Level result = ClientUtils.parseLevel(input);
            assertEquals(expected, result);
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "invalid", "level", "test", "123", "begin", "intermediatee", "advancedd",
            "begg", "interr", "advv", "null", "empty", "spaces   "
        })
        @DisplayName("Should return null for various invalid inputs")
        void testParseLevelWithInvalidInputs(String input) {
            Level result = ClientUtils.parseLevel(input);
            assertNull(result);
        }
    }

    @Nested
    @DisplayName("Level Validation Tests")
    class LevelValidationTests {

        @Test
        @DisplayName("Should validate beginner level")
        void testIsValidLevelBeginner() {
            assertTrue(ClientUtils.isValidLevel("beginner"));
        }

        @Test
        @DisplayName("Should validate intermediate level")
        void testIsValidLevelIntermediate() {
            assertTrue(ClientUtils.isValidLevel("intermediate"));
        }

        @Test
        @DisplayName("Should validate advanced level")
        void testIsValidLevelAdvanced() {
            assertTrue(ClientUtils.isValidLevel("advanced"));
        }

        @Test
        @DisplayName("Should validate abbreviated forms")
        void testIsValidLevelAbbreviated() {
            assertTrue(ClientUtils.isValidLevel("beg"));
            assertTrue(ClientUtils.isValidLevel("inter"));
            assertTrue(ClientUtils.isValidLevel("adv"));
        }

        @Test
        @DisplayName("Should validate case insensitive")
        void testIsValidLevelCaseInsensitive() {
            assertTrue(ClientUtils.isValidLevel("BEGINNER"));
            assertTrue(ClientUtils.isValidLevel("Intermediate"));
            assertTrue(ClientUtils.isValidLevel("AdVaNcEd"));
        }

        @Test
        @DisplayName("Should reject invalid level")
        void testIsValidLevelInvalid() {
            assertFalse(ClientUtils.isValidLevel("invalid"));
        }

        @Test
        @DisplayName("Should return false for null input")
        void testIsValidLevelNull() {
            assertFalse(ClientUtils.isValidLevel(null));
        }

        @Test
        @DisplayName("Should return false for empty string")
        void testIsValidLevelEmpty() {
            assertFalse(ClientUtils.isValidLevel(""));
        }

        @Test
        @DisplayName("Should return false for whitespace only")
        void testIsValidLevelWhitespace() {
            assertFalse(ClientUtils.isValidLevel("   "));
        }

        @Test
        @DisplayName("Should handle names with spaces")
        void testIsValidLevelWithSpaces() {
            assertTrue(ClientUtils.isValidLevel("  beginner  "));
            assertTrue(ClientUtils.isValidLevel("  intermediate  "));
            assertTrue(ClientUtils.isValidLevel("  advanced  "));
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "beginner", "intermediate", "advanced",
            "beg", "inter", "adv",
            "BEGINNER", "INTERMEDIATE", "ADVANCED",
            "Beginner", "Intermediate", "Advanced",
            "  beginner  ", "  intermediate  ", "  advanced  "
        })
        @DisplayName("Should validate various valid level formats")
        void testIsValidLevelWithValidFormats(String level) {
            assertTrue(ClientUtils.isValidLevel(level));
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "invalid", "level", "test", "123", "begin", "intermediatee", "advancedd",
            "begg", "interr", "advv", "null", "empty", "spaces   "
        })
        @DisplayName("Should reject various invalid level formats")
        void testIsValidLevelWithInvalidFormats(String level) {
            assertFalse(ClientUtils.isValidLevel(level));
        }
    }
}