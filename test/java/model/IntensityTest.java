package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Intensity Tests")
public class IntensityTest {

    @Nested
    @DisplayName("Enum Value Tests")
    class EnumValueTests {

        @Test
        @DisplayName("Should have correct number of values")
        void testIntensityValues() {
            assertEquals(3, Intensity.values().length);
            assertTrue(Intensity.LOW != null);
            assertTrue(Intensity.MEDIUM != null);
            assertTrue(Intensity.HIGH != null);
        }

        @Test
        @DisplayName("Should have correct ordinal values")
        void testIntensityOrdinal() {
            assertEquals(0, Intensity.LOW.ordinal());
            assertEquals(1, Intensity.MEDIUM.ordinal());
            assertEquals(2, Intensity.HIGH.ordinal());
        }

        @Test
        @DisplayName("Should have correct names")
        void testIntensityName() {
            assertEquals("LOW", Intensity.LOW.name());
            assertEquals("MEDIUM", Intensity.MEDIUM.name());
            assertEquals("HIGH", Intensity.HIGH.name());
        }
    }

    @Nested
    @DisplayName("ValueOf Tests")
    class ValueOfTests {

        @Test
        @DisplayName("Should return correct intensity for valid string")
        void testIntensityValueOf() {
            assertEquals(Intensity.LOW, Intensity.valueOf("LOW"));
            assertEquals(Intensity.MEDIUM, Intensity.valueOf("MEDIUM"));
            assertEquals(Intensity.HIGH, Intensity.valueOf("HIGH"));
        }

        @Test
        @DisplayName("Should throw exception for invalid string")
        void testIntensityValueOfInvalid() {
            assertThrows(IllegalArgumentException.class, () -> {
                Intensity.valueOf("INVALID");
            });
        }

        @Test
        @DisplayName("Should throw exception for null string")
        void testIntensityValueOfNull() {
            assertThrows(NullPointerException.class, () -> {
                Intensity.valueOf(null);
            });
        }

        @ParameterizedTest
        @ValueSource(strings = {"LOW", "MEDIUM", "HIGH"})
        @DisplayName("Should return correct intensity for valid strings")
        void testIntensityValueOfWithValidStrings(String intensityString) {
            Intensity intensity = Intensity.valueOf(intensityString);
            assertNotNull(intensity);
            assertEquals(intensityString, intensity.name());
        }
    }

    @Nested
    @DisplayName("ToString Tests")
    class ToStringTests {

        @Test
        @DisplayName("Should return correct string representation")
        void testIntensityToString() {
            assertEquals("LOW", Intensity.LOW.toString());
            assertEquals("MEDIUM", Intensity.MEDIUM.toString());
            assertEquals("HIGH", Intensity.HIGH.toString());
        }

        @Test
        @DisplayName("Should have same toString and name")
        void testToStringEqualsName() {
            assertEquals(Intensity.LOW.name(), Intensity.LOW.toString());
            assertEquals(Intensity.MEDIUM.name(), Intensity.MEDIUM.toString());
            assertEquals(Intensity.HIGH.name(), Intensity.HIGH.toString());
        }
    }

    @Nested
    @DisplayName("Equality Tests")
    class EqualityTests {

        @Test
        @DisplayName("Should be equal to same intensity")
        void testIntensityEquality() {
            assertEquals(Intensity.LOW, Intensity.LOW);
            assertEquals(Intensity.MEDIUM, Intensity.MEDIUM);
            assertEquals(Intensity.HIGH, Intensity.HIGH);
        }

        @Test
        @DisplayName("Should not be equal to different intensity")
        void testIntensityInequality() {
            assertNotEquals(Intensity.LOW, Intensity.MEDIUM);
            assertNotEquals(Intensity.LOW, Intensity.HIGH);
            assertNotEquals(Intensity.MEDIUM, Intensity.HIGH);
        }

        @Test
        @DisplayName("Should not be equal to null")
        void testIntensityNotEqualToNull() {
            assertNotEquals(Intensity.LOW, null);
            assertNotEquals(Intensity.MEDIUM, null);
            assertNotEquals(Intensity.HIGH, null);
        }

        @Test
        @DisplayName("Should not be equal to different type")
        void testIntensityNotEqualToDifferentType() {
            assertNotEquals(Intensity.LOW, "LOW");
            assertNotEquals(Intensity.MEDIUM, 1);
            assertNotEquals(Intensity.HIGH, new Object());
        }
    }

    @Nested
    @DisplayName("HashCode Tests")
    class HashCodeTests {

        @Test
        @DisplayName("Should have same hash code for equal intensities")
        void testIntensityHashCode() {
            assertEquals(Intensity.LOW.hashCode(), Intensity.LOW.hashCode());
            assertEquals(Intensity.MEDIUM.hashCode(), Intensity.MEDIUM.hashCode());
            assertEquals(Intensity.HIGH.hashCode(), Intensity.HIGH.hashCode());
        }

        @Test
        @DisplayName("Should have different hash codes for different intensities")
        void testIntensityDifferentHashCodes() {
            assertNotEquals(Intensity.LOW.hashCode(), Intensity.MEDIUM.hashCode());
            assertNotEquals(Intensity.LOW.hashCode(), Intensity.HIGH.hashCode());
            assertNotEquals(Intensity.MEDIUM.hashCode(), Intensity.HIGH.hashCode());
        }
    }
}