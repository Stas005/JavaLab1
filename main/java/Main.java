import model.*;
import repositories.GenericRepository;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static void setupLogging() {
        try {
            FileHandler fileHandler = new FileHandler("logs/application.log");
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.err.println("Could not create log file: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        setupLogging();
        logger.info("Generic Repository Demonstration");
        
        try {
            demonstrateGenericRepository();
            
            logger.info("Application completed successfully");
            
        } catch (Exception e) {
            logger.severe("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void demonstrateGenericRepository() {
        
        GenericRepository<User> userRepository = new GenericRepository<>(User::getEmail);
        GenericRepository<Coach> coachRepository = new GenericRepository<>(Coach::getEmail);
        GenericRepository<Exercise> exerciseRepository = new GenericRepository<>(Exercise::name);
        
        System.out.println("--- Creating Different Object Types ---");
        
        User user1 = new User("John", "Doe", "john.doe@university.edu");
        User user2 = new User("Jane", "Smith", "jane.smith@university.edu");
        User user3 = new User("Bob", "Johnson", "bob.johnson@university.edu");
        
        Coach coach1 = new Coach("Alice", "Williams", "alice.williams@university.edu", 5);
        Coach coach2 = new Coach("Charlie", "Brown", "charlie.brown@university.edu", 10);
        
        Exercise exercise1 = Exercise.createExercise("Push-ups", 10, 3);
        Exercise exercise2 = Exercise.createExercise("Sit-ups", 15, 3);
        Exercise exercise3 = Exercise.createExercise("Squats", 12, 4);
        
        System.out.println("\n--- Adding Objects to Repositories ---");
        
        System.out.println("Adding users...");
        userRepository.add(user1);
        userRepository.add(user2);
        userRepository.add(user3);
        
        System.out.println("\nAdding coaches...");
        coachRepository.add(coach1);
        coachRepository.add(coach2);
        
        System.out.println("\nAdding exercises...");
        exerciseRepository.add(exercise1);
        exerciseRepository.add(exercise2);
        exerciseRepository.add(exercise3);
        
        System.out.println("\nFind by Identity");
        
        User foundUser = userRepository.findByIdentity("john.doe@university.edu");
        if (foundUser != null) {
            System.out.println("Found user: " + foundUser.getFullName() + " (" + foundUser.getEmail() + ")");
        }
        
        User foundUser2 = userRepository.findByIdentity("jane.smith@university.edu");
        if (foundUser2 != null) {
            System.out.println("Found user: " + foundUser2.getFullName() + " (" + foundUser2.getEmail() + ")");
        }
        
        Coach foundCoach = coachRepository.findByIdentity("alice.williams@university.edu");
        if (foundCoach != null) {
            System.out.println("Found coach: " + foundCoach.getFullName() + " (" + foundCoach.getEmail() + ", " + foundCoach.getExperienceYears() + " years)");
        }
        
        Exercise foundExercise = exerciseRepository.findByIdentity("Push-ups");
        if (foundExercise != null) {
            System.out.println("Found exercise: " + foundExercise.name() + " (" + foundExercise.reps() + " reps, " + foundExercise.sets() + " sets)");
        }
        
        System.out.println("\n--- Demonstrating Duplicate Handling ---");
        
        User duplicateUser = new User("John", "Doe", "john.doe@university.edu");
        System.out.println("\nTry to add duplicate user with email: john.doe@university.edu");
        System.out.println("User to add: " + duplicateUser.getFullName() + " (" + duplicateUser.getEmail() + ")");
        userRepository.add(duplicateUser);
        System.out.println(userRepository.getAll().size());
        System.out.println("Original user still in repository: " + userRepository.findByIdentity("john.doe@university.edu").getFullName());
        
        Exercise duplicateExercise = Exercise.createExercise("Push-ups", 20, 5);
        System.out.println("\nTry to add duplicate exercise with name: Push-ups");
        exerciseRepository.add(duplicateExercise);
        System.out.println(exerciseRepository.getAll().size());
        Exercise existingExercise = exerciseRepository.findByIdentity("Push-ups");
        System.out.println("Original exercise still in repository: " + existingExercise.name() + " (" + existingExercise.reps() + " reps, " + existingExercise.sets() + " sets)");
        
        System.out.println("\n--- GetAll ---");
        
        List<User> allUsers = userRepository.getAll();
        System.out.println("\nAll users in repository (" + allUsers.size() + "):");
        for (User user : allUsers) {
            System.out.println("  - " + user.getFullName() + " (" + user.getEmail() + ")");
        }
        
        List<Coach> allCoaches = coachRepository.getAll();
        System.out.println("\nAll coaches in repository (" + allCoaches.size() + "):");
        for (Coach coach : allCoaches) {
            System.out.println("  - " + coach.getFullName() + " (" + coach.getEmail() + ", " + coach.getExperienceYears() + " years)");
        }
        
        List<Exercise> allExercises = exerciseRepository.getAll();
        System.out.println("\nAll exercises in repository (" + allExercises.size() + "):");
        for (Exercise exercise : allExercises) {
            System.out.println("  - " + exercise.name() + " (" + exercise.reps() + " reps, " + exercise.sets() + " sets)");
        }
        
        System.out.println("\n--- Delete ---");
        
        boolean deleted = userRepository.delete("bob.johnson@university.edu");
        System.out.println("Deleted user with email 'bob.johnson@university.edu': " + deleted);
        System.out.println(userRepository.getAll().size());
        
        boolean deletedCoach = coachRepository.delete("charlie.brown@university.edu");
        System.out.println("Deleted coach with email 'charlie.brown@university.edu': " + deletedCoach);
        System.out.println(coachRepository.getAll().size());
        
        boolean deletedExercise = exerciseRepository.delete("Squats");
        System.out.println("Deleted exercise with name 'Squats': " + deletedExercise);
        System.out.println(exerciseRepository.getAll().size());
        
        System.out.println("\n--- COMPLETE ---");
    }
}