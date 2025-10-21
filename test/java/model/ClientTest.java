package model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Client Tests")
public class ClientTest {

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create client with default constructor")
        void testDefaultConstructor() {
            Client client = new Client();
            
            assertNull(client.getFirstName());
            assertNull(client.getLastName());
            assertNull(client.getEmail());
            assertNull(client.getLevel());
            assertNull(client.getCoach());
        }

        @Test
        @DisplayName("Should create client with parameterized constructor")
        void testParameterizedConstructor() {
            String firstName = "john";
            String lastName = "doe";
            String email = "john.doe@university.edu";
            Level level = Level.BEGINNER;
            Coach coach = new Coach("jane", "smith", "jane.smith@university.edu", 5);
            
            Client client = new Client(firstName, lastName, email, level, coach);
            
            assertEquals("John", client.getFirstName());
            assertEquals("Doe", client.getLastName());
            assertEquals(email, client.getEmail());
            assertEquals(level, client.getLevel());
            assertEquals(coach, client.getCoach());
        }

        @ParameterizedTest
        @EnumSource(Level.class)
        @DisplayName("Should create client with different levels")
        void testConstructorWithDifferentLevels(Level level) {
            Coach coach = new Coach("jane", "smith", "jane.smith@university.edu", 5);
            Client client = new Client("john", "doe", "john.doe@university.edu", level, coach);
            
            assertEquals(level, client.getLevel());
        }
    }

    @Nested
    @DisplayName("Setter Tests")
    class SetterTests {

        @Test
        @DisplayName("Should set level")
        void testSetLevel() {
            Client client = new Client();
            Level level = Level.INTERMEDIATE;
            
            client.setLevel(level);
            
            assertEquals(level, client.getLevel());
        }

        @Test
        @DisplayName("Should set coach")
        void testSetCoach() {
            Client client = new Client();
            Coach coach = new Coach("alice", "bob", "alice.bob@university.edu", 3);
            
            client.setCoach(coach);
            
            assertEquals(coach, client.getCoach());
        }

        @Test
        @DisplayName("Should set null level")
        void testSetNullLevel() {
            Client client = new Client();
            client.setLevel(null);
            
            assertNull(client.getLevel());
        }

        @Test
        @DisplayName("Should set null coach")
        void testSetNullCoach() {
            Client client = new Client();
            client.setCoach(null);
            
            assertNull(client.getCoach());
        }
    }

    @Nested
    @DisplayName("Factory Method Tests")
    class FactoryMethodTests {

        @Test
        @DisplayName("Should create client with level object")
        void testCreateClientWithLevelObject() {
            Level level = Level.ADVANCED;
            Coach coach = new Coach("jane", "smith", "jane.smith@university.edu", 10);
            
            Client client = Client.createClient("john", "doe", level, coach);
            
            assertNotNull(client);
            assertEquals("John", client.getFirstName());
            assertEquals("Doe", client.getLastName());
            assertEquals("john.doe@university.edu", client.getEmail());
            assertEquals(level, client.getLevel());
            assertEquals(coach, client.getCoach());
        }

        @Test
        @DisplayName("Should create client with level string")
        void testCreateClientWithLevelString() {
            String levelString = "beginner";
            Coach coach = new Coach("jane", "smith", "jane.smith@university.edu", 5);
            
            Client client = Client.createClient("john", "doe", levelString, coach);
            
            assertNotNull(client);
            assertEquals("John", client.getFirstName());
            assertEquals("Doe", client.getLastName());
            assertEquals("john.doe@university.edu", client.getEmail());
            assertEquals(Level.BEGINNER, client.getLevel());
            assertEquals(coach, client.getCoach());
        }

        @Test
        @DisplayName("Should return null for invalid level string")
        void testCreateClientWithInvalidLevelString() {
            String levelString = "invalid";
            Coach coach = new Coach("jane", "smith", "jane.smith@university.edu", 5);
            
            Client client = Client.createClient("john", "doe", levelString, coach);
            
            assertNull(client);
        }

        @Test
        @DisplayName("Should return null for invalid names")
        void testCreateClientWithInvalidNames() {
            Level level = Level.BEGINNER;
            Coach coach = new Coach("jane", "smith", "jane.smith@university.edu", 5);
            
            Client client = Client.createClient("a", "b", level, coach);
            
            assertNull(client);
        }

        @Test
        @DisplayName("Should return null for null level")
        void testCreateClientWithNullLevel() {
            Coach coach = new Coach("jane", "smith", "jane.smith@university.edu", 5);
            
            Client client = Client.createClient("john", "doe", (Level) null, coach);
            
            assertNull(client);
        }

        @ParameterizedTest
        @CsvSource({
            "beginner, BEGINNER",
            "intermediate, INTERMEDIATE", 
            "advanced, ADVANCED",
            "beg, BEGINNER",
            "inter, INTERMEDIATE",
            "adv, ADVANCED"
        })
        @DisplayName("Should create client with various level string formats")
        void testCreateClientWithVariousLevelStrings(String levelString, Level expectedLevel) {
            Coach coach = new Coach("jane", "smith", "jane.smith@university.edu", 5);
            Client client = Client.createClient("john", "doe", levelString, coach);
            
            assertNotNull(client);
            assertEquals(expectedLevel, client.getLevel());
        }
    }

    @Nested
    @DisplayName("String Representation Tests")
    class StringRepresentationTests {

        @Test
        @DisplayName("Should return correct toString format")
        void testToString() {
            Level level = Level.INTERMEDIATE;
            Coach coach = new Coach("jane", "smith", "jane.smith@university.edu", 7);
            Client client = new Client("john", "doe", "john.doe@university.edu", level, coach);
            
            String result = client.toString();
            
            assertTrue(result.contains("Client{"));
            assertTrue(result.contains("firstName='John'"));
            assertTrue(result.contains("lastName='Doe'"));
            assertTrue(result.contains("email='john.doe@university.edu'"));
            assertTrue(result.contains("level='INTERMEDIATE'"));
            assertTrue(result.contains("coach=Jane Smith"));
        }

        @Test
        @DisplayName("Should handle null coach in toString")
        void testToStringWithNullCoach() {
            Level level = Level.BEGINNER;
            Client client = new Client("john", "doe", "john.doe@university.edu", level, null);
            
            String result = client.toString();
            
            assertTrue(result.contains("coach=No Coach"));
        }

        @Test
        @DisplayName("Should handle null level in toString")
        void testToStringWithNullLevel() {
            Coach coach = new Coach("jane", "smith", "jane.smith@university.edu", 5);
            Client client = new Client("john", "doe", "john.doe@university.edu", null, coach);
            
            String result = client.toString();
            
            assertTrue(result.contains("level='null'"));
        }
    }

    @Nested
    @DisplayName("Equality Tests")
    class EqualityTests {

        @Test
        @DisplayName("Should be equal to identical client")
        void testEquals() {
            Level level1 = Level.BEGINNER;
            Level level2 = Level.BEGINNER;
            Coach coach1 = new Coach("jane", "smith", "jane.smith@university.edu", 5);
            Coach coach2 = new Coach("jane", "smith", "jane.smith@university.edu", 5);
            
            Client client1 = new Client("john", "doe", "john.doe@university.edu", level1, coach1);
            Client client2 = new Client("john", "doe", "john.doe@university.edu", level2, coach2);
            Client client3 = new Client("jane", "doe", "jane.doe@university.edu", level1, coach1);
            
            assertEquals(client1, client2);
            assertNotEquals(client1, client3);
            assertNotEquals(client1, null);
            assertEquals(client1, client1);
        }

        @Test
        @DisplayName("Should have same hash code for equal clients")
        void testHashCode() {
            Level level = Level.ADVANCED;
            Coach coach = new Coach("jane", "smith", "jane.smith@university.edu", 10);
            
            Client client1 = new Client("john", "doe", "john.doe@university.edu", level, coach);
            Client client2 = new Client("john", "doe", "john.doe@university.edu", level, coach);
            
            assertEquals(client1.hashCode(), client2.hashCode());
        }

        @Test
        @DisplayName("Should not be equal to different type")
        void testEqualsWithDifferentType() {
            Level level = Level.BEGINNER;
            Coach coach = new Coach("jane", "smith", "jane.smith@university.edu", 5);
            Client client = new Client("john", "doe", "john.doe@university.edu", level, coach);
            String notClient = "not a client";
            
            assertNotEquals(client, notClient);
        }

        @Test
        @DisplayName("Should not be equal to client with different level")
        void testEqualsWithDifferentLevel() {
            Coach coach = new Coach("jane", "smith", "jane.smith@university.edu", 5);
            Client client1 = new Client("john", "doe", "john.doe@university.edu", Level.BEGINNER, coach);
            Client client2 = new Client("john", "doe", "john.doe@university.edu", Level.ADVANCED, coach);
            
            assertNotEquals(client1, client2);
        }

        @Test
        @DisplayName("Should not be equal to client with different coach")
        void testEqualsWithDifferentCoach() {
            Level level = Level.BEGINNER;
            Coach coach1 = new Coach("jane", "smith", "jane.smith@university.edu", 5);
            Coach coach2 = new Coach("alice", "bob", "alice.bob@university.edu", 3);
            Client client1 = new Client("john", "doe", "john.doe@university.edu", level, coach1);
            Client client2 = new Client("john", "doe", "john.doe@university.edu", level, coach2);
            
            assertNotEquals(client1, client2);
        }
    }
}
