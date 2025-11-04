package repositories;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@DisplayName("GenericRepository Tests")
public class GenericRepositoryTest {

    @Nested
    @DisplayName("User Repository Tests")
    class UserRepositoryTests {
        private GenericRepository<User> userRepository;

        @BeforeEach
        void setUp() {
            userRepository = new GenericRepository<>(User::getEmail);
        }

        @Test
        @DisplayName("Should add user successfully")
        void testAddUser() {
            User user = new User("John", "Doe", "john.doe@university.edu");
            userRepository.add(user);
            
            List<User> allUsers = userRepository.getAll();
            assertEquals(1, allUsers.size());
            assertTrue(allUsers.contains(user));
        }

        @Test
        @DisplayName("Should prevent duplicate users by email")
        void testAddDuplicateUser() {
            User user1 = new User("John", "Doe", "john.doe@university.edu");
            User user2 = new User("Jane", "Smith", "john.doe@university.edu");
            
            userRepository.add(user1);
            userRepository.add(user2);
            
            List<User> allUsers = userRepository.getAll();
            assertEquals(1, allUsers.size());
            assertEquals(user1, allUsers.get(0));
        }

        @Test
        @DisplayName("Should find user by email identity")
        void testFindUserByIdentity() {
            User user = new User("John", "Doe", "john.doe@university.edu");
            userRepository.add(user);
            
            User found = userRepository.findByIdentity("john.doe@university.edu");
            assertNotNull(found);
            assertEquals(user, found);
        }

        @Test
        @DisplayName("Should return null for non-existent user")
        void testFindNonExistentUser() {
            User found = userRepository.findByIdentity("nonexistent@university.edu");
            assertNull(found);
        }

        @Test
        @DisplayName("Should delete user by email")
        void testDeleteUser() {
            User user = new User("John", "Doe", "john.doe@university.edu");
            userRepository.add(user);
            
            boolean deleted = userRepository.delete("john.doe@university.edu");
            assertTrue(deleted);
            
            List<User> allUsers = userRepository.getAll();
            assertTrue(allUsers.isEmpty());
        }

        @Test
        @DisplayName("Should return false when deleting non-existent user")
        void testDeleteNonExistentUser() {
            boolean deleted = userRepository.delete("nonexistent@university.edu");
            assertFalse(deleted);
        }

        @Test
        @DisplayName("Should return empty list when repository is empty")
        void testGetAllEmptyRepository() {
            List<User> allUsers = userRepository.getAll();
            assertTrue(allUsers.isEmpty());
        }

        @Test
        @DisplayName("Should return all users")
        void testGetAllUsers() {
            User user1 = new User("John", "Doe", "john.doe@university.edu");
            User user2 = new User("Jane", "Smith", "jane.smith@university.edu");
            User user3 = new User("Bob", "Johnson", "bob.johnson@university.edu");
            
            userRepository.add(user1);
            userRepository.add(user2);
            userRepository.add(user3);
            
            List<User> allUsers = userRepository.getAll();
            assertEquals(3, allUsers.size());
            assertTrue(allUsers.contains(user1));
            assertTrue(allUsers.contains(user2));
            assertTrue(allUsers.contains(user3));
        }
    }

    @Nested
    @DisplayName("Coach Repository Tests")
    class CoachRepositoryTests {
        private GenericRepository<Coach> coachRepository;

        @BeforeEach
        void setUp() {
            coachRepository = new GenericRepository<>(Coach::getEmail);
        }

        @Test
        @DisplayName("Should add coach successfully")
        void testAddCoach() {
            Coach coach = new Coach("John", "Doe", "john.doe@university.edu", 5);
            coachRepository.add(coach);
            
            List<Coach> allCoaches = coachRepository.getAll();
            assertEquals(1, allCoaches.size());
            assertTrue(allCoaches.contains(coach));
        }

        @Test
        @DisplayName("Should prevent duplicate coaches by email")
        void testAddDuplicateCoach() {
            Coach coach1 = new Coach("John", "Doe", "john.doe@university.edu", 5);
            Coach coach2 = new Coach("Jane", "Smith", "john.doe@university.edu", 10);
            
            coachRepository.add(coach1);
            coachRepository.add(coach2);
            
            List<Coach> allCoaches = coachRepository.getAll();
            assertEquals(1, allCoaches.size());
            assertEquals(coach1, allCoaches.get(0));
        }

        @Test
        @DisplayName("Should find coach by email identity")
        void testFindCoachByIdentity() {
            Coach coach = new Coach("John", "Doe", "john.doe@university.edu", 5);
            coachRepository.add(coach);
            
            Coach found = coachRepository.findByIdentity("john.doe@university.edu");
            assertNotNull(found);
            assertEquals(coach, found);
        }

        @Test
        @DisplayName("Should delete coach by email")
        void testDeleteCoach() {
            Coach coach = new Coach("John", "Doe", "john.doe@university.edu", 5);
            coachRepository.add(coach);
            
            boolean deleted = coachRepository.delete("john.doe@university.edu");
            assertTrue(deleted);
            
            List<Coach> allCoaches = coachRepository.getAll();
            assertTrue(allCoaches.isEmpty());
        }
    }

    @Nested
    @DisplayName("Exercise Repository Tests")
    class ExerciseRepositoryTests {
        private GenericRepository<Exercise> exerciseRepository;

        @BeforeEach
        void setUp() {
            exerciseRepository = new GenericRepository<>(Exercise::name);
        }

        @Test
        @DisplayName("Should add exercise successfully")
        void testAddExercise() {
            Exercise exercise = Exercise.createExercise("Push-ups", 10, 3);
            exerciseRepository.add(exercise);
            
            List<Exercise> allExercises = exerciseRepository.getAll();
            assertEquals(1, allExercises.size());
            assertTrue(allExercises.contains(exercise));
        }

        @Test
        @DisplayName("Should prevent duplicate exercises by name")
        void testAddDuplicateExercise() {
            Exercise exercise1 = Exercise.createExercise("Push-ups", 10, 3);
            Exercise exercise2 = Exercise.createExercise("Push-ups", 15, 5);
            
            exerciseRepository.add(exercise1);
            exerciseRepository.add(exercise2);
            
            List<Exercise> allExercises = exerciseRepository.getAll();
            assertEquals(1, allExercises.size());
            assertEquals(exercise1, allExercises.get(0));
        }

        @Test
        @DisplayName("Should find exercise by name identity")
        void testFindExerciseByIdentity() {
            Exercise exercise = Exercise.createExercise("Push-ups", 10, 3);
            exerciseRepository.add(exercise);
            
            Exercise found = exerciseRepository.findByIdentity("Push-ups");
            assertNotNull(found);
            assertEquals(exercise, found);
        }

        @Test
        @DisplayName("Should delete exercise by name")
        void testDeleteExercise() {
            Exercise exercise = Exercise.createExercise("Push-ups", 10, 3);
            exerciseRepository.add(exercise);
            
            boolean deleted = exerciseRepository.delete("Push-ups");
            assertTrue(deleted);
            
            List<Exercise> allExercises = exerciseRepository.getAll();
            assertTrue(allExercises.isEmpty());
        }
    }

    @Nested
    @DisplayName("Edge Cases Tests")
    class EdgeCasesTests {
        private GenericRepository<User> userRepository;

        @BeforeEach
        void setUp() {
            userRepository = new GenericRepository<>(User::getEmail);
        }

        @Test
        @DisplayName("Should handle null item addition")
        void testAddNullItem() {
            userRepository.add(null);
            
            List<User> allUsers = userRepository.getAll();
            assertTrue(allUsers.isEmpty());
        }

        @Test
        @DisplayName("Should handle null identity search")
        void testFindByIdentityNull() {
            User found = userRepository.findByIdentity(null);
            assertNull(found);
        }

        @Test
        @DisplayName("Should handle empty identity search")
        void testFindByIdentityEmpty() {
            User found = userRepository.findByIdentity("");
            assertNull(found);
        }

        @Test
        @DisplayName("Should handle null identity deletion")
        void testDeleteNullIdentity() {
            boolean deleted = userRepository.delete(null);
            assertFalse(deleted);
        }

        @Test
        @DisplayName("Should handle empty identity deletion")
        void testDeleteEmptyIdentity() {
            boolean deleted = userRepository.delete("");
            assertFalse(deleted);
        }
    }

    @Nested
    @DisplayName("Custom IdentityExtractor Tests")
    class CustomIdentityExtractorTests {
        @Test
        @DisplayName("Should work with custom identity extractor for User")
        void testCustomUserIdentityExtractor() {
            IdentityExtractor<User> fullNameExtractor = user -> 
                user.getFirstName() + " " + user.getLastName();
            
            GenericRepository<User> repository = new GenericRepository<>(fullNameExtractor);
            
            User user1 = new User("John", "Doe", "john.doe@university.edu");
            User user2 = new User("John", "Doe", "john.doe2@university.edu");
            
            repository.add(user1);
            repository.add(user2);
            
            List<User> allUsers = repository.getAll();
            assertEquals(1, allUsers.size());
        }

        @Test
        @DisplayName("Should work with custom identity extractor for Exercise")
        void testCustomExerciseIdentityExtractor() {
            IdentityExtractor<Exercise> repsSetsExtractor = exercise -> 
                exercise.name() + "-" + exercise.reps() + "-" + exercise.sets();
            
            GenericRepository<Exercise> repository = new GenericRepository<>(repsSetsExtractor);
            
            Exercise exercise1 = Exercise.createExercise("Push-ups", 10, 3);
            Exercise exercise2 = Exercise.createExercise("Push-ups", 15, 3);
            Exercise exercise3 = Exercise.createExercise("Push-ups", 10, 3);
            
            repository.add(exercise1);
            repository.add(exercise2);
            repository.add(exercise3);
            
            List<Exercise> allExercises = repository.getAll();
            assertEquals(2, allExercises.size());
        }
    }
}
