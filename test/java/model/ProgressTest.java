package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

@DisplayName("Progress Tests")
public class ProgressTest {

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create progress with default constructor")
        void testDefaultConstructor() {
            Progress progress = new Progress();
            
            assertNull(progress.getDate());
            assertEquals(0.0, progress.getWeight(), 0.001);
            assertEquals(0.0, progress.getBmi(), 0.001);
            assertNull(progress.getClient());
        }

        @Test
        @DisplayName("Should create progress with parameterized constructor")
        void testParameterizedConstructor() {
            Date date = new Date();
            double weight = 70.5;
            double bmi = 22.5;
            Client client = new Client("john", "doe", "john.doe@university.edu", 
                                     Level.BEGINNER, null);
            
            Progress progress = new Progress(date, weight, bmi, client);
            
            assertEquals(date, progress.getDate());
            assertEquals(weight, progress.getWeight(), 0.001);
            assertEquals(bmi, progress.getBmi(), 0.001);
            assertEquals(client, progress.getClient());
        }

        @Test
        @DisplayName("Should create progress with null values")
        void testConstructorWithNullValues() {
            Progress progress = new Progress(null, 0.0, 0.0, null);
            
            assertNull(progress.getDate());
            assertEquals(0.0, progress.getWeight(), 0.001);
            assertEquals(0.0, progress.getBmi(), 0.001);
            assertNull(progress.getClient());
        }

        @Test
        @DisplayName("Should create progress with various valid parameters")
        void testConstructorWithVariousParameters() {
            Date date1 = new Date();
            Date date2 = new Date(System.currentTimeMillis() + 86400000);
            Date date3 = new Date(System.currentTimeMillis() - 86400000);
            
            Client client = new Client("alice", "bob", "alice.bob@university.edu", 
                                     Level.INTERMEDIATE, null);
            
            Progress progress1 = new Progress(date1, 65.5, 21.2, client);
            Progress progress2 = new Progress(date2, 70.0, 23.5, client);
            Progress progress3 = new Progress(date3, 75.8, 25.1, client);
            
            assertEquals(date1, progress1.getDate());
            assertEquals(65.5, progress1.getWeight(), 0.001);
            assertEquals(21.2, progress1.getBmi(), 0.001);
            
            assertEquals(date2, progress2.getDate());
            assertEquals(70.0, progress2.getWeight(), 0.001);
            assertEquals(23.5, progress2.getBmi(), 0.001);
            
            assertEquals(date3, progress3.getDate());
            assertEquals(75.8, progress3.getWeight(), 0.001);
            assertEquals(25.1, progress3.getBmi(), 0.001);
        }
    }

    @Nested
    @DisplayName("Setter Tests")
    class SetterTests {

        @Test
        @DisplayName("Should set date")
        void testSetDate() {
            Progress progress = new Progress();
            Date date = new Date();
            
            progress.setDate(date);
            
            assertEquals(date, progress.getDate());
        }

        @Test
        @DisplayName("Should set weight")
        void testSetWeight() {
            Progress progress = new Progress();
            double weight = 68.5;
            
            progress.setWeight(weight);
            
            assertEquals(weight, progress.getWeight(), 0.001);
        }

        @Test
        @DisplayName("Should set BMI")
        void testSetBmi() {
            Progress progress = new Progress();
            double bmi = 24.2;
            
            progress.setBmi(bmi);
            
            assertEquals(bmi, progress.getBmi(), 0.001);
        }

        @Test
        @DisplayName("Should set client")
        void testSetClient() {
            Progress progress = new Progress();
            Client client = new Client("jane", "smith", "jane.smith@university.edu", 
                                     Level.ADVANCED, null);
            
            progress.setClient(client);
            
            assertEquals(client, progress.getClient());
        }

        @Test
        @DisplayName("Should set null values")
        void testSetNullValues() {
            Progress progress = new Progress();
            
            progress.setDate(null);
            progress.setClient(null);
            
            assertNull(progress.getDate());
            assertNull(progress.getClient());
        }

        @ParameterizedTest
        @ValueSource(doubles = {50.0, 65.5, 80.0, 100.0})
        @DisplayName("Should set valid weights")
        void testSetValidWeights(double weight) {
            Progress progress = new Progress();
            progress.setWeight(weight);
            assertEquals(weight, progress.getWeight(), 0.001);
        }

        @ParameterizedTest
        @ValueSource(doubles = {18.5, 22.0, 25.0, 30.0})
        @DisplayName("Should set valid BMI values")
        void testSetValidBmiValues(double bmi) {
            Progress progress = new Progress();
            progress.setBmi(bmi);
            assertEquals(bmi, progress.getBmi(), 0.001);
        }
    }

    @Nested
    @DisplayName("Getter Tests")
    class GetterTests {

        @Test
        @DisplayName("Should return date")
        void testGetDate() {
            Progress progress = new Progress();
            Date date = new Date();
            progress.setDate(date);
            
            assertEquals(date, progress.getDate());
        }

        @Test
        @DisplayName("Should return weight")
        void testGetWeight() {
            Progress progress = new Progress();
            double weight = 72.3;
            progress.setWeight(weight);
            
            assertEquals(weight, progress.getWeight(), 0.001);
        }

        @Test
        @DisplayName("Should return BMI")
        void testGetBmi() {
            Progress progress = new Progress();
            double bmi = 23.8;
            progress.setBmi(bmi);
            
            assertEquals(bmi, progress.getBmi(), 0.001);
        }

        @Test
        @DisplayName("Should return client")
        void testGetClient() {
            Progress progress = new Progress();
            Client client = new Client("alice", "bob", "alice.bob@university.edu", 
                                     Level.INTERMEDIATE, null);
            progress.setClient(client);
            
            assertEquals(client, progress.getClient());
        }
    }

    @Nested
    @DisplayName("Validation Tests")
    class ValidationTests {

        @Test
        @DisplayName("Should handle valid date range")
        void testValidDateRange() {
            Progress progress = new Progress();
            Date pastDate = new Date(System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000);
            Date futureDate = new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000);
            Date today = new Date();
            
            progress.setDate(pastDate);
            assertEquals(pastDate, progress.getDate());
            
            progress.setDate(futureDate);
            assertEquals(futureDate, progress.getDate());
            
            progress.setDate(today);
            assertEquals(today, progress.getDate());
        }

        @Test
        @DisplayName("Should handle valid weight range")
        void testValidWeightRange() {
            Progress progress = new Progress();
            
            progress.setWeight(30.0);
            assertEquals(30.0, progress.getWeight(), 0.001);
            
            progress.setWeight(200.0);
            assertEquals(200.0, progress.getWeight(), 0.001);
        }

        @Test
        @DisplayName("Should handle valid BMI range")
        void testValidBmiRange() {
            Progress progress = new Progress();
            
            progress.setBmi(15.0);
            assertEquals(15.0, progress.getBmi(), 0.001);
            
            progress.setBmi(50.0);
            assertEquals(50.0, progress.getBmi(), 0.001);
        }
    }

    @Nested
    @DisplayName("String Representation Tests")
    class StringRepresentationTests {

        @Test
        @DisplayName("Should return correct toString format")
        void testToString() {
            Date date = new Date();
            double weight = 70.5;
            double bmi = 22.5;
            Client client = new Client("john", "doe", "john.doe@university.edu", 
                                     Level.BEGINNER, null);
            
            Progress progress = new Progress(date, weight, bmi, client);
            String result = progress.toString();
            
            assertTrue(result.contains("Progress{"));
            assertTrue(result.contains("date=" + date));
            assertTrue(result.contains("weight=" + weight));
            assertTrue(result.contains("bmi=" + bmi));
            assertTrue(result.contains("client=" + client.getFullName()));
        }

        @Test
        @DisplayName("Should handle null values in toString")
        void testToStringWithNullValues() {
            Progress progress = new Progress();
            String result = progress.toString();
            
            assertTrue(result.contains("Progress{"));
            assertTrue(result.contains("date=null"));
            assertTrue(result.contains("weight=0.0"));
            assertTrue(result.contains("bmi=0.0"));
            assertTrue(result.contains("client=No Client"));
        }
    }

    @Nested
    @DisplayName("Equality Tests")
    class EqualityTests {

        @Test
        @DisplayName("Should be equal to identical progress")
        void testEquals() {
            Date date = new Date();
            double weight = 70.5;
            double bmi = 22.5;
            Client client = new Client("john", "doe", "john.doe@university.edu", 
                                     Level.BEGINNER, null);
            
            Progress progress1 = new Progress(date, weight, bmi, client);
            Progress progress2 = new Progress(date, weight, bmi, client);
            Progress progress3 = new Progress(new Date(System.currentTimeMillis() + 86400000), weight, bmi, client);
            
            assertEquals(progress1, progress2);
            assertNotEquals(progress1, progress3);
            assertNotEquals(progress1, null);
            assertEquals(progress1, progress1);
        }

        @Test
        @DisplayName("Should have same hash code for equal progress")
        void testHashCode() {
            Date date = new Date();
            double weight = 70.5;
            double bmi = 22.5;
            Client client = new Client("john", "doe", "john.doe@university.edu", 
                                     Level.BEGINNER, null);
            
            Progress progress1 = new Progress(date, weight, bmi, client);
            Progress progress2 = new Progress(date, weight, bmi, client);
            
            assertEquals(progress1.hashCode(), progress2.hashCode());
        }

        @Test
        @DisplayName("Should not be equal to different type")
        void testEqualsWithDifferentType() {
            Progress progress = new Progress();
            String notProgress = "not progress";
            
            assertNotEquals(progress, notProgress);
        }

        @Test
        @DisplayName("Should not be equal to progress with different date")
        void testEqualsWithDifferentDate() {
            double weight = 70.5;
            double bmi = 22.5;
            Client client = new Client("john", "doe", "john.doe@university.edu", 
                                     Level.BEGINNER, null);
            
            Progress progress1 = new Progress(new Date(), weight, bmi, client);
            Progress progress2 = new Progress(new Date(System.currentTimeMillis() + 86400000), weight, bmi, client);
            
            assertNotEquals(progress1, progress2);
        }

        @Test
        @DisplayName("Should not be equal to progress with different weight")
        void testEqualsWithDifferentWeight() {
            Date date = new Date();
            double bmi = 22.5;
            Client client = new Client("john", "doe", "john.doe@university.edu", 
                                     Level.BEGINNER, null);
            
            Progress progress1 = new Progress(date, 70.5, bmi, client);
            Progress progress2 = new Progress(date, 71.0, bmi, client);
            
            assertNotEquals(progress1, progress2);
        }

        @Test
        @DisplayName("Should not be equal to progress with different BMI")
        void testEqualsWithDifferentBmi() {
            Date date = new Date();
            double weight = 70.5;
            Client client = new Client("john", "doe", "john.doe@university.edu", 
                                     Level.BEGINNER, null);
            
            Progress progress1 = new Progress(date, weight, 22.5, client);
            Progress progress2 = new Progress(date, weight, 23.0, client);
            
            assertNotEquals(progress1, progress2);
        }

        @Test
        @DisplayName("Should not be equal to progress with different client")
        void testEqualsWithDifferentClient() {
            Date date = new Date();
            double weight = 70.5;
            double bmi = 22.5;
            Client client1 = new Client("john", "doe", "john.doe@university.edu", 
                                      Level.BEGINNER, null);
            Client client2 = new Client("jane", "smith", "jane.smith@university.edu", 
                                      Level.INTERMEDIATE, null);
            
            Progress progress1 = new Progress(date, weight, bmi, client1);
            Progress progress2 = new Progress(date, weight, bmi, client2);
            
            assertNotEquals(progress1, progress2);
        }
    }
}