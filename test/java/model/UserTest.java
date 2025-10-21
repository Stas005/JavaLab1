package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User Tests")
public class UserTest {

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create user with default constructor")
        void testDefaultConstructor() {
            User user = new User();
            
            assertNull(user.getFirstName());
            assertNull(user.getLastName());
            assertNull(user.getEmail());
        }

        @Test
        @DisplayName("Should create user with parameterized constructor")
        void testParameterizedConstructor() {
            String firstName = "john";
            String lastName = "doe";
            String email = "john.doe@university.edu";
            
            User user = new User(firstName, lastName, email);
            
            assertEquals("John", user.getFirstName());
            assertEquals("Doe", user.getLastName());
            assertEquals(email, user.getEmail());
        }

        @ParameterizedTest
        @CsvSource({
            "john, doe, john.doe@university.edu",
            "JANE, SMITH, jane.smith@university.edu",
            "  alice  ,  bob  , alice.bob@university.edu"
        })
        @DisplayName("Should create user with various name formats")
        void testParameterizedConstructorWithVariousInputs(String firstName, String lastName, String email) {
            User user = new User(firstName, lastName, email);
            
            assertNotNull(user.getFirstName());
            assertNotNull(user.getLastName());
            assertEquals(email, user.getEmail());
        }
    }

    @Nested
    @DisplayName("Setter Tests")
    class SetterTests {

        @Test
        @DisplayName("Should set valid first name")
        void testSetFirstNameValid() {
            User user = new User();
            user.setFirstName("jane");
            
            assertEquals("Jane", user.getFirstName());
        }

        @Test
        @DisplayName("Should not set invalid first name")
        void testSetFirstNameInvalid() {
            User user = new User();
            user.setFirstName("a"); 
            
            assertNull(user.getFirstName());
        }

        @Test
        @DisplayName("Should not set null first name")
        void testSetFirstNameNull() {
            User user = new User();
            user.setFirstName(null);
            
            assertNull(user.getFirstName());
        }

        @Test
        @DisplayName("Should set valid last name")
        void testSetLastNameValid() {
            User user = new User();
            user.setLastName("smith");
            
            assertEquals("Smith", user.getLastName());
        }

        @Test
        @DisplayName("Should not set invalid last name")
        void testSetLastNameInvalid() {
            User user = new User();
            user.setLastName("");
            
            assertNull(user.getLastName());
        }

        @Test
        @DisplayName("Should set valid email")
        void testSetEmailValid() {
            User user = new User();
            user.setEmail("test@example.com");
            
            assertEquals("test@example.com", user.getEmail());
        }

        @Test
        @DisplayName("Should not set invalid email")
        void testSetEmailInvalid() {
            User user = new User();
            user.setEmail("invalid-email");
            
            assertNull(user.getEmail());
        }

        @Test
        @DisplayName("Should not set null email")
        void testSetEmailNull() {
            User user = new User();
            user.setEmail(null);
            
            assertNull(user.getEmail());
        }

        @ParameterizedTest
        @ValueSource(strings = {"a", "", "   ", "VeryLongNameThatExceedsTheMaximumAllowedLengthForNames"})
        @DisplayName("Should not set invalid first names")
        void testSetFirstNameWithInvalidValues(String invalidName) {
            User user = new User();
            user.setFirstName(invalidName);
            assertNull(user.getFirstName());
        }

        @ParameterizedTest
        @ValueSource(strings = {"invalid-email", "@example.com", "test@", "test.example.com"})
        @DisplayName("Should not set invalid emails")
        void testSetEmailWithInvalidValues(String invalidEmail) {
            User user = new User();
            user.setEmail(invalidEmail);
            assertNull(user.getEmail());
        }
    }

    @Nested
    @DisplayName("Getter Tests")
    class GetterTests {

        @Test
        @DisplayName("Should return full name correctly")
        void testGetFullName() {
            User user = new User("john", "doe", "john.doe@university.edu");
            
            assertEquals("John Doe", user.getFullName());
        }

        @Test
        @DisplayName("Should return empty string for full name with null names")
        void testGetFullNameWithNullNames() {
            User user = new User();
            user.setFirstName(null);
            user.setLastName(null);
            
            assertEquals("", user.getFullName());
        }

        @Test
        @DisplayName("Should return first name")
        void testGetFirstName() {
            User user = new User();
            user.setFirstName("Alice");
            assertEquals("Alice", user.getFirstName());
        }

        @Test
        @DisplayName("Should return last name")
        void testGetLastName() {
            User user = new User();
            user.setLastName("Smith");
            assertEquals("Smith", user.getLastName());
        }

        @Test
        @DisplayName("Should return email")
        void testGetEmail() {
            User user = new User();
            user.setEmail("alice.smith@university.edu");
            assertEquals("alice.smith@university.edu", user.getEmail());
        }
    }

    @Nested
    @DisplayName("Factory Method Tests")
    class FactoryMethodTests {

        @Test
        @DisplayName("Should create user with valid names")
        void testCreateUserValid() {
            User user = User.createUser("alice", "bob");
            
            assertNotNull(user);
            assertEquals("Alice", user.getFirstName());
            assertEquals("Bob", user.getLastName());
            assertEquals("alice.bob@university.edu", user.getEmail());
        }

        @Test
        @DisplayName("Should return null for invalid names")
        void testCreateUserInvalid() {
            User user = User.createUser("a", "b");
            
            assertNull(user);
        }

        @ParameterizedTest
        @CsvSource({
            "alice, bob",
            "JOHN, DOE",
            "  jane  ,  smith  "
        })
        @DisplayName("Should create user with various valid name formats")
        void testCreateUserWithValidNames(String firstName, String lastName) {
            User user = User.createUser(firstName, lastName);
            
            assertNotNull(user);
            assertNotNull(user.getFirstName());
            assertNotNull(user.getLastName());
            assertNotNull(user.getEmail());
        }
    }

    @Nested
    @DisplayName("String Representation Tests")
    class StringRepresentationTests {

        @Test
        @DisplayName("Should return correct toString format")
        void testToString() {
            User user = new User("john", "doe", "john.doe@university.edu");
            String result = user.toString();
            
            assertTrue(result.contains("User{"));
            assertTrue(result.contains("firstName='John'"));
            assertTrue(result.contains("lastName='Doe'"));
            assertTrue(result.contains("email='john.doe@university.edu'"));
        }

        @Test
        @DisplayName("Should handle null values in toString")
        void testToStringWithNullValues() {
            User user = new User();
            String result = user.toString();
            
            assertTrue(result.contains("User{"));
            assertTrue(result.contains("firstName='null'"));
            assertTrue(result.contains("lastName='null'"));
            assertTrue(result.contains("email='null'"));
        }
    }

    @Nested
    @DisplayName("Equality Tests")
    class EqualityTests {

        @Test
        @DisplayName("Should be equal to identical user")
        void testEquals() {
            User user1 = new User("john", "doe", "john.doe@university.edu");
            User user2 = new User("john", "doe", "john.doe@university.edu");
            User user3 = new User("jane", "doe", "jane.doe@university.edu");
            
            assertEquals(user1, user2);
            assertNotEquals(user1, user3);
            assertNotEquals(user1, null);
            assertEquals(user1, user1);
        }

        @Test
        @DisplayName("Should have same hash code for equal users")
        void testHashCode() {
            User user1 = new User("john", "doe", "john.doe@university.edu");
            User user2 = new User("john", "doe", "john.doe@university.edu");
            
            assertEquals(user1.hashCode(), user2.hashCode());
        }

        @Test
        @DisplayName("Should not be equal to different type")
        void testEqualsWithDifferentType() {
            User user = new User("john", "doe", "john.doe@university.edu");
            String notUser = "not a user";
            
            assertNotEquals(user, notUser);
        }
    }
}
