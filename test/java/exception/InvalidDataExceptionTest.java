package exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("InvalidDataException Tests")
public class InvalidDataExceptionTest {

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create exception with message only")
        void testConstructorWithMessage() {
            String message = "Test error message";
            InvalidDataException exception = new InvalidDataException(message);
            
            assertEquals(message, exception.getMessage());
            assertNull(exception.getFieldName());
            assertNull(exception.getInvalidValue());
        }

        @Test
        @DisplayName("Should create exception with message and field info")
        void testConstructorWithMessageAndFieldInfo() {
            String message = "Invalid data";
            String fieldName = "email";
            String invalidValue = "invalid-email";
            
            InvalidDataException exception = new InvalidDataException(message, fieldName, invalidValue);
            
            assertEquals(message, exception.getMessage());
            assertEquals(fieldName, exception.getFieldName());
            assertEquals(invalidValue, exception.getInvalidValue());
        }

        @Test
        @DisplayName("Should create exception with message and cause")
        void testConstructorWithMessageAndCause() {
            String message = "Test error";
            Throwable cause = new RuntimeException("Root cause");
            
            InvalidDataException exception = new InvalidDataException(message, cause);
            
            assertEquals(message, exception.getMessage());
            assertEquals(cause, exception.getCause());
            assertNull(exception.getFieldName());
            assertNull(exception.getInvalidValue());
        }

        @Test
        @DisplayName("Should create exception with cause only")
        void testConstructorWithCause() {
            Throwable cause = new RuntimeException("Root cause");
            
            InvalidDataException exception = new InvalidDataException(cause);
            
            assertEquals(cause, exception.getCause());
            assertNull(exception.getFieldName());
            assertNull(exception.getInvalidValue());
        }
    }

    @Nested
    @DisplayName("Getter Tests")
    class GetterTests {

        @Test
        @DisplayName("Should return correct field name")
        void testGetFieldName() {
            String fieldName = "email";
            InvalidDataException exception = new InvalidDataException("Error", fieldName, "invalid");
            assertEquals(fieldName, exception.getFieldName());
        }

        @Test
        @DisplayName("Should return correct invalid value")
        void testGetInvalidValue() {
            String invalidValue = "invalid-email";
            InvalidDataException exception = new InvalidDataException("Error", "email", invalidValue);
            assertEquals(invalidValue, exception.getInvalidValue());
        }

        @Test
        @DisplayName("Should return null for field name when not set")
        void testGetFieldNameWhenNull() {
            InvalidDataException exception = new InvalidDataException("Error");
            assertNull(exception.getFieldName());
        }

        @Test
        @DisplayName("Should return null for invalid value when not set")
        void testGetInvalidValueWhenNull() {
            InvalidDataException exception = new InvalidDataException("Error");
            assertNull(exception.getInvalidValue());
        }
    }

    @Nested
    @DisplayName("ToString Tests")
    class ToStringTests {

        @Test
        @DisplayName("Should include field info in toString when available")
        void testToStringWithFieldInfo() {
            String message = "Invalid data";
            String fieldName = "age";
            String invalidValue = "-5";
            
            InvalidDataException exception = new InvalidDataException(message, fieldName, invalidValue);
            String result = exception.toString();
            
            assertTrue(result.contains(message));
            assertTrue(result.contains("Field: " + fieldName));
            assertTrue(result.contains("Invalid Value: '" + invalidValue + "'"));
        }

        @Test
        @DisplayName("Should not include field info in toString when not available")
        void testToStringWithoutFieldInfo() {
            String message = "Simple error";
            InvalidDataException exception = new InvalidDataException(message);
            String result = exception.toString();
            
            assertTrue(result.contains(message));
            assertFalse(result.contains("Field:"));
            assertFalse(result.contains("Invalid Value:"));
        }

        @Test
        @DisplayName("Should include only field name when invalid value is null")
        void testToStringWithFieldNameOnly() {
            String message = "Invalid data";
            String fieldName = "name";
            
            InvalidDataException exception = new InvalidDataException(message, fieldName, null);
            String result = exception.toString();
            
            assertTrue(result.contains(message));
            assertTrue(result.contains("Field: " + fieldName));
            assertFalse(result.contains("Invalid Value:"));
        }

        @ParameterizedTest
        @CsvSource({
            "Error message, email, invalid@",
            "Validation failed, age, -5",
            "Invalid input, name, ''"
        })
        @DisplayName("Should format toString correctly with various inputs")
        void testToStringWithVariousInputs(String message, String fieldName, String invalidValue) {
            InvalidDataException exception = new InvalidDataException(message, fieldName, invalidValue);
            String result = exception.toString();
            
            assertTrue(result.contains(message));
            assertTrue(result.contains("Field: " + fieldName));
            assertTrue(result.contains("Invalid Value: '" + invalidValue + "'"));
        }
    }
}
