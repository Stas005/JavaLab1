package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DisplayName("Plan Tests")
public class PlanTest {

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create plan with default constructor")
        void testDefaultConstructor() {
            Plan plan = new Plan();
            
            assertNull(plan.getStartDate());
            assertNull(plan.getClient());
            assertNotNull(plan.getWorkouts());
            assertTrue(plan.getWorkouts().isEmpty());
        }

        @Test
        @DisplayName("Should create plan with parameterized constructor")
        void testParameterizedConstructor() {
            Date startDate = new Date(System.currentTimeMillis() + 86400000); // Tomorrow
            Client client = new Client("john", "doe", "john.doe@university.edu", 
                                     model.Level.BEGINNER, null);
            List<Workout> workouts = new ArrayList<>();
            
            Plan plan = new Plan(workouts, startDate, client);
            
            assertEquals(startDate, plan.getStartDate());
            assertEquals(client, plan.getClient());
            assertEquals(workouts, plan.getWorkouts());
        }

        @Test
        @DisplayName("Should create plan with null values")
        void testConstructorWithNullValues() {
            Plan plan = new Plan(null, null, null);
            
            assertNull(plan.getStartDate());
            assertNull(plan.getClient());
            assertNull(plan.getWorkouts());
        }
    }

    @Nested
    @DisplayName("Setter Tests")
    class SetterTests {

        @Test
        @DisplayName("Should set start date")
        void testSetStartDate() {
            Plan plan = new Plan();
            Date startDate = new Date(System.currentTimeMillis() + 86400000); // Tomorrow
            
            plan.setStartDate(startDate);
            
            assertEquals(startDate, plan.getStartDate());
        }

        @Test
        @DisplayName("Should set client")
        void testSetClient() {
            Plan plan = new Plan();
            Client client = new Client("jane", "smith", "jane.smith@university.edu", 
                                     model.Level.INTERMEDIATE, null);
            
            plan.setClient(client);
            
            assertEquals(client, plan.getClient());
        }

        @Test
        @DisplayName("Should set workouts")
        void testSetWorkouts() {
            Plan plan = new Plan();
            List<Workout> workouts = new ArrayList<>();
            workouts.add(new Workout("Workout 1", 30, Intensity.LOW, new ArrayList<>()));
            
            plan.setWorkouts(workouts);
            
            assertEquals(workouts, plan.getWorkouts());
        }

        @Test
        @DisplayName("Should set null values")
        void testSetNullValues() {
            Plan plan = new Plan();
            
            plan.setStartDate(null);
            plan.setClient(null);
            plan.setWorkouts(null);
            
            assertNull(plan.getStartDate());
            assertNull(plan.getClient());
            assertNull(plan.getWorkouts());
        }
    }

    @Nested
    @DisplayName("Getter Tests")
    class GetterTests {

        @Test
        @DisplayName("Should return start date")
        void testGetStartDate() {
            Plan plan = new Plan();
            Date startDate = new Date(System.currentTimeMillis() + 86400000); // Tomorrow
            plan.setStartDate(startDate);
            
            assertEquals(startDate, plan.getStartDate());
        }

        @Test
        @DisplayName("Should return client")
        void testGetClient() {
            Plan plan = new Plan();
            Client client = new Client("alice", "bob", "alice.bob@university.edu", 
                                     model.Level.ADVANCED, null);
            plan.setClient(client);
            
            assertEquals(client, plan.getClient());
        }

        @Test
        @DisplayName("Should return workouts")
        void testGetWorkouts() {
            Plan plan = new Plan();
            List<Workout> workouts = new ArrayList<>();
            workouts.add(new Workout("Morning Workout", 45, Intensity.MEDIUM, new ArrayList<>()));
            workouts.add(new Workout("Evening Workout", 30, Intensity.HIGH, new ArrayList<>()));
            plan.setWorkouts(workouts);
            
            assertEquals(workouts, plan.getWorkouts());
            assertEquals(2, plan.getWorkouts().size());
        }
    }

    @Nested
    @DisplayName("Validation Tests")
    class ValidationTests {

        @Test
        @DisplayName("Should validate start date in future")
        void testValidateStartDateFuture() {
            Plan plan = new Plan();
            Date futureDate = new Date(System.currentTimeMillis() + 86400000); // Tomorrow
            
            plan.setStartDate(futureDate);
            
            assertEquals(futureDate, plan.getStartDate());
        }

        @Test
        @DisplayName("Should reject start date in past")
        void testValidateStartDatePast() {
            Plan plan = new Plan();
            Date pastDate = new Date(System.currentTimeMillis() - 86400000); // Yesterday
            
            plan.setStartDate(pastDate);
            
            assertNull(plan.getStartDate()); // Should be null because past dates are rejected
        }

        @Test
        @DisplayName("Should handle start date tomorrow")
        void testValidateStartDateTomorrow() {
            Plan plan = new Plan();
            Date tomorrow = new Date(System.currentTimeMillis() + 86400000); // Tomorrow
            
            plan.setStartDate(tomorrow);
            
            assertEquals(tomorrow, plan.getStartDate());
        }
    }

    @Nested
    @DisplayName("String Representation Tests")
    class StringRepresentationTests {

        @Test
        @DisplayName("Should return correct toString format")
        void testToString() {
            Date startDate = new Date(System.currentTimeMillis() + 86400000); // Tomorrow
            Client client = new Client("john", "doe", "john.doe@university.edu", 
                                     model.Level.BEGINNER, null);
            List<Workout> workouts = new ArrayList<>();
            workouts.add(new Workout("Workout 1", 30, Intensity.LOW, new ArrayList<>()));
            
            Plan plan = new Plan(workouts, startDate, client);
            String result = plan.toString();
            
            assertTrue(result.contains("Plan{"));
            assertTrue(result.contains("startDate=" + startDate));
            assertTrue(result.contains("client=" + client.getFullName()));
            assertTrue(result.contains("workouts=" + workouts.size()));
        }

        @Test
        @DisplayName("Should handle null values in toString")
        void testToStringWithNullValues() {
            Plan plan = new Plan();
            String result = plan.toString();
            
            assertTrue(result.contains("Plan{"));
            assertTrue(result.contains("startDate=null"));
            assertTrue(result.contains("client=No Client"));
            assertTrue(result.contains("workouts=0"));
        }
    }

    @Nested
    @DisplayName("Equality Tests")
    class EqualityTests {

        @Test
        @DisplayName("Should be equal to identical plan")
        void testEquals() {
            Date startDate = new Date(System.currentTimeMillis() + 86400000); // Tomorrow
            Client client = new Client("john", "doe", "john.doe@university.edu", 
                                     model.Level.BEGINNER, null);
            List<Workout> workouts = new ArrayList<>();
            
            Plan plan1 = new Plan(workouts, startDate, client);
            Plan plan2 = new Plan(workouts, startDate, client);
            Plan plan3 = new Plan(workouts, new Date(System.currentTimeMillis() + 172800000), client); // Day after tomorrow
            
            assertEquals(plan1, plan2);
            assertNotEquals(plan1, plan3);
            assertNotEquals(plan1, null);
            assertEquals(plan1, plan1);
        }

        @Test
        @DisplayName("Should have same hash code for equal plans")
        void testHashCode() {
            Date startDate = new Date(System.currentTimeMillis() + 86400000); // Tomorrow
            Client client = new Client("john", "doe", "john.doe@university.edu", 
                                     model.Level.BEGINNER, null);
            List<Workout> workouts = new ArrayList<>();
            
            Plan plan1 = new Plan(workouts, startDate, client);
            Plan plan2 = new Plan(workouts, startDate, client);
            
            assertEquals(plan1.hashCode(), plan2.hashCode());
        }

        @Test
        @DisplayName("Should not be equal to different type")
        void testEqualsWithDifferentType() {
            Plan plan = new Plan();
            String notPlan = "not a plan";
            
            assertNotEquals(plan, notPlan);
        }

        @Test
        @DisplayName("Should not be equal to plan with different start date")
        void testEqualsWithDifferentStartDate() {
            Client client = new Client("john", "doe", "john.doe@university.edu", 
                                     model.Level.BEGINNER, null);
            List<Workout> workouts = new ArrayList<>();
            
            Plan plan1 = new Plan(workouts, new Date(System.currentTimeMillis() + 86400000), client);
            Plan plan2 = new Plan(workouts, new Date(System.currentTimeMillis() + 172800000), client); // Day after tomorrow
            
            assertNotEquals(plan1, plan2);
        }

        @Test
        @DisplayName("Should not be equal to plan with different client")
        void testEqualsWithDifferentClient() {
            Date startDate = new Date(System.currentTimeMillis() + 86400000); // Tomorrow
            Client client1 = new Client("john", "doe", "john.doe@university.edu", 
                                      model.Level.BEGINNER, null);
            Client client2 = new Client("jane", "smith", "jane.smith@university.edu", 
                                      model.Level.INTERMEDIATE, null);
            List<Workout> workouts = new ArrayList<>();
            
            Plan plan1 = new Plan(workouts, startDate, client1);
            Plan plan2 = new Plan(workouts, startDate, client2);
            
            assertNotEquals(plan1, plan2);
        }
    }
}