package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Level Tests")
public class LevelTest {

    @Nested
    @DisplayName("Enum Value Tests")
    class EnumValueTests {

        @Test
        @DisplayName("Should have correct number of values")
        void testLevelValues() {
            assertEquals(3, Level.values().length);
            assertTrue(Level.BEGINNER != null);
            assertTrue(Level.INTERMEDIATE != null);
            assertTrue(Level.ADVANCED != null);
        }

        @Test
        @DisplayName("Should have correct ordinal values")
        void testLevelOrdinal() {
            assertEquals(0, Level.BEGINNER.ordinal());
            assertEquals(1, Level.INTERMEDIATE.ordinal());
            assertEquals(2, Level.ADVANCED.ordinal());
        }

        @Test
        @DisplayName("Should have correct names")
        void testLevelName() {
            assertEquals("BEGINNER", Level.BEGINNER.name());
            assertEquals("INTERMEDIATE", Level.INTERMEDIATE.name());
            assertEquals("ADVANCED", Level.ADVANCED.name());
        }
    }

    @Nested
    @DisplayName("ValueOf Tests")
    class ValueOfTests {

        @Test
        @DisplayName("Should return correct level for valid string")
        void testLevelValueOf() {
            assertEquals(Level.BEGINNER, Level.valueOf("BEGINNER"));
            assertEquals(Level.INTERMEDIATE, Level.valueOf("INTERMEDIATE"));
            assertEquals(Level.ADVANCED, Level.valueOf("ADVANCED"));
        }

        @Test
        @DisplayName("Should throw exception for invalid string")
        void testLevelValueOfInvalid() {
            assertThrows(IllegalArgumentException.class, () -> {
                Level.valueOf("INVALID");
            });
        }

        @Test
        @DisplayName("Should throw exception for null string")
        void testLevelValueOfNull() {
            assertThrows(NullPointerException.class, () -> {
                Level.valueOf(null);
            });
        }

        @ParameterizedTest
        @ValueSource(strings = {"BEGINNER", "INTERMEDIATE", "ADVANCED"})
        @DisplayName("Should return correct level for valid strings")
        void testLevelValueOfWithValidStrings(String levelString) {
            Level level = Level.valueOf(levelString);
            assertNotNull(level);
            assertEquals(levelString, level.name());
        }
    }

    @Nested
    @DisplayName("ToString Tests")
    class ToStringTests {

        @Test
        @DisplayName("Should return correct string representation")
        void testLevelToString() {
            assertEquals("BEGINNER", Level.BEGINNER.toString());
            assertEquals("INTERMEDIATE", Level.INTERMEDIATE.toString());
            assertEquals("ADVANCED", Level.ADVANCED.toString());
        }

        @Test
        @DisplayName("Should have same toString and name")
        void testToStringEqualsName() {
            assertEquals(Level.BEGINNER.name(), Level.BEGINNER.toString());
            assertEquals(Level.INTERMEDIATE.name(), Level.INTERMEDIATE.toString());
            assertEquals(Level.ADVANCED.name(), Level.ADVANCED.toString());
        }
    }

    @Nested
    @DisplayName("Equality Tests")
    class EqualityTests {

        @Test
        @DisplayName("Should be equal to same level")
        void testLevelEquality() {
            assertEquals(Level.BEGINNER, Level.BEGINNER);
            assertEquals(Level.INTERMEDIATE, Level.INTERMEDIATE);
            assertEquals(Level.ADVANCED, Level.ADVANCED);
        }

        @Test
        @DisplayName("Should not be equal to different level")
        void testLevelInequality() {
            assertNotEquals(Level.BEGINNER, Level.INTERMEDIATE);
            assertNotEquals(Level.BEGINNER, Level.ADVANCED);
            assertNotEquals(Level.INTERMEDIATE, Level.ADVANCED);
        }

        @Test
        @DisplayName("Should not be equal to null")
        void testLevelNotEqualToNull() {
            assertNotEquals(Level.BEGINNER, null);
            assertNotEquals(Level.INTERMEDIATE, null);
            assertNotEquals(Level.ADVANCED, null);
        }

        @Test
        @DisplayName("Should not be equal to different type")
        void testLevelNotEqualToDifferentType() {
            assertNotEquals(Level.BEGINNER, "BEGINNER");
            assertNotEquals(Level.INTERMEDIATE, 1);
            assertNotEquals(Level.ADVANCED, new Object());
        }
    }

    @Nested
    @DisplayName("HashCode Tests")
    class HashCodeTests {

        @Test
        @DisplayName("Should have same hash code for equal levels")
        void testLevelHashCode() {
            assertEquals(Level.BEGINNER.hashCode(), Level.BEGINNER.hashCode());
            assertEquals(Level.INTERMEDIATE.hashCode(), Level.INTERMEDIATE.hashCode());
            assertEquals(Level.ADVANCED.hashCode(), Level.ADVANCED.hashCode());
        }

        @Test
        @DisplayName("Should have different hash codes for different levels")
        void testLevelDifferentHashCodes() {
            assertNotEquals(Level.BEGINNER.hashCode(), Level.INTERMEDIATE.hashCode());
            assertNotEquals(Level.BEGINNER.hashCode(), Level.ADVANCED.hashCode());
            assertNotEquals(Level.INTERMEDIATE.hashCode(), Level.ADVANCED.hashCode());
        }
    }
}