package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Coach Tests")
public class CoachTest {

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create coach with default constructor")
        void testDefaultConstructor() {
            Coach coach = new Coach();
            
            assertNull(coach.getFirstName());
            assertNull(coach.getLastName());
            assertNull(coach.getEmail());
            assertEquals(0, coach.getExperienceYears());
        }

        @Test
        @DisplayName("Should create coach with parameterized constructor")
        void testParameterizedConstructor() {
            String firstName = "jane";
            String lastName = "smith";
            String email = "jane.smith@university.edu";
            int experienceYears = 5;
            
            Coach coach = new Coach(firstName, lastName, email, experienceYears);
            
            assertEquals("Jane", coach.getFirstName());
            assertEquals("Smith", coach.getLastName());
            assertEquals(email, coach.getEmail());
            assertEquals(experienceYears, coach.getExperienceYears());
        }

        @ParameterizedTest
        @CsvSource({
            "john, doe, john.doe@university.edu, 3",
            "JANE, SMITH, jane.smith@university.edu, 10",
            "  alice  ,  bob  , alice.bob@university.edu, 5"
        })
        @DisplayName("Should create coach with various input formats")
        void testParameterizedConstructorWithVariousInputs(String firstName, String lastName, String email, int experienceYears) {
            Coach coach = new Coach(firstName, lastName, email, experienceYears);
            
            assertNotNull(coach.getFirstName());
            assertNotNull(coach.getLastName());
            assertEquals(email, coach.getEmail());
            assertEquals(experienceYears, coach.getExperienceYears());
        }
    }

    @Nested
    @DisplayName("Setter Tests")
    class SetterTests {

        @Test
        @DisplayName("Should set valid experience years")
        void testSetExperienceYearsValid() {
            Coach coach = new Coach();
            int experienceYears = 10;
            
            coach.setExperienceYears(experienceYears);
            
            assertEquals(experienceYears, coach.getExperienceYears());
        }

        @Test
        @DisplayName("Should not set invalid experience years")
        void testSetExperienceYearsInvalid() {
            Coach coach = new Coach();
            int experienceYears = -1;
            
            coach.setExperienceYears(experienceYears);
            
            assertEquals(0, coach.getExperienceYears());
        }

        @Test
        @DisplayName("Should not set experience years too high")
        void testSetExperienceYearsTooHigh() {
            Coach coach = new Coach();
            int experienceYears = 100;
            
            coach.setExperienceYears(experienceYears);
            
            assertEquals(0, coach.getExperienceYears()); 
        }

        @Test
        @DisplayName("Should handle boundary values correctly")
        void testSetExperienceYearsBoundaryValues() {
            Coach coach = new Coach();
            
            coach.setExperienceYears(0);
            assertEquals(0, coach.getExperienceYears());
            
            coach.setExperienceYears(50);
            assertEquals(50, coach.getExperienceYears());
        }

        @ParameterizedTest
        @ValueSource(ints = {-1, -10, 51, 100})
        @DisplayName("Should not set invalid experience years")
        void testSetExperienceYearsWithInvalidValues(int invalidExperience) {
            Coach coach = new Coach();
            coach.setExperienceYears(invalidExperience);
            assertEquals(0, coach.getExperienceYears());
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 25, 50})
        @DisplayName("Should set valid experience years")
        void testSetExperienceYearsWithValidValues(int validExperience) {
            Coach coach = new Coach();
            coach.setExperienceYears(validExperience);
            assertEquals(validExperience, coach.getExperienceYears());
        }
    }

    @Nested
    @DisplayName("Factory Method Tests")
    class FactoryMethodTests {

        @Test
        @DisplayName("Should create coach with valid parameters")
        void testCreateCoachValid() {
            Coach coach = Coach.createCoach("alice", "bob", 3);
            
            assertNotNull(coach);
            assertEquals("Alice", coach.getFirstName());
            assertEquals("Bob", coach.getLastName());
            assertEquals("alice.bob@university.edu", coach.getEmail());
            assertEquals(3, coach.getExperienceYears());
        }

        @Test
        @DisplayName("Should return null for invalid experience")
        void testCreateCoachInvalidExperience() {
            Coach coach = Coach.createCoach("alice", "bob", -1);
            
            assertNull(coach);
        }

        @Test
        @DisplayName("Should return null for invalid names")
        void testCreateCoachInvalidNames() {
            Coach coach = Coach.createCoach("a", "b", 5);
            
            assertNull(coach);
        }

        @Test
        @DisplayName("Should handle boundary experience values")
        void testCreateCoachBoundaryExperience() {
            Coach coach1 = Coach.createCoach("alice", "bob", 0);
            assertNotNull(coach1);
            assertEquals(0, coach1.getExperienceYears());
            
            Coach coach2 = Coach.createCoach("alice", "bob", 50);
            assertNotNull(coach2);
            assertEquals(50, coach2.getExperienceYears());
        }

        @ParameterizedTest
        @CsvSource({
            "john, doe, 5",
            "JANE, SMITH, 10",
            "  alice  ,  bob  , 3"
        })
        @DisplayName("Should create coach with various valid parameters")
        void testCreateCoachWithValidParameters(String firstName, String lastName, int experienceYears) {
            Coach coach = Coach.createCoach(firstName, lastName, experienceYears);
            
            assertNotNull(coach);
            assertNotNull(coach.getFirstName());
            assertNotNull(coach.getLastName());
            assertNotNull(coach.getEmail());
            assertEquals(experienceYears, coach.getExperienceYears());
        }
    }

    @Nested
    @DisplayName("String Representation Tests")
    class StringRepresentationTests {

        @Test
        @DisplayName("Should return correct toString format")
        void testToString() {
            Coach coach = new Coach("jane", "smith", "jane.smith@university.edu", 7);
            
            String result = coach.toString();
            
            assertTrue(result.contains("Coach{"));
            assertTrue(result.contains("firstName='Jane'"));
            assertTrue(result.contains("lastName='Smith'"));
            assertTrue(result.contains("email='jane.smith@university.edu'"));
            assertTrue(result.contains("experienceYears=7"));
        }

        @Test
        @DisplayName("Should handle null values in toString")
        void testToStringWithNullValues() {
            Coach coach = new Coach();
            String result = coach.toString();
            
            assertTrue(result.contains("Coach{"));
            assertTrue(result.contains("firstName='null'"));
            assertTrue(result.contains("lastName='null'"));
            assertTrue(result.contains("email='null'"));
            assertTrue(result.contains("experienceYears=0"));
        }
    }

    @Nested
    @DisplayName("Equality Tests")
    class EqualityTests {

        @Test
        @DisplayName("Should be equal to identical coach")
        void testEquals() {
            Coach coach1 = new Coach("jane", "smith", "jane.smith@university.edu", 5);
            Coach coach2 = new Coach("jane", "smith", "jane.smith@university.edu", 5);
            Coach coach3 = new Coach("john", "smith", "john.smith@university.edu", 5);
            Coach coach4 = new Coach("jane", "smith", "jane.smith@university.edu", 10);
            
            assertEquals(coach1, coach2);
            assertNotEquals(coach1, coach3);
            assertNotEquals(coach1, coach4);
            assertNotEquals(coach1, null);
            assertEquals(coach1, coach1);
        }

        @Test
        @DisplayName("Should have same hash code for equal coaches")
        void testHashCode() {
            Coach coach1 = new Coach("jane", "smith", "jane.smith@university.edu", 5);
            Coach coach2 = new Coach("jane", "smith", "jane.smith@university.edu", 5);
            
            assertEquals(coach1.hashCode(), coach2.hashCode());
        }

        @Test
        @DisplayName("Should not be equal to different type")
        void testEqualsWithDifferentType() {
            Coach coach = new Coach("jane", "smith", "jane.smith@university.edu", 5);
            String notCoach = "not a coach";
            
            assertNotEquals(coach, notCoach);
        }

        @Test
        @DisplayName("Should not be equal to coach with different experience")
        void testEqualsWithDifferentExperience() {
            Coach coach1 = new Coach("jane", "smith", "jane.smith@university.edu", 5);
            Coach coach2 = new Coach("jane", "smith", "jane.smith@university.edu", 10);
            
            assertNotEquals(coach1, coach2);
        }
    }
}