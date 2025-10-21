import model.*;
import exception.InvalidDataException;
import util.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final String DATA_DIR = "data";

    private static void setupLogging() {
        try {
            FileHandler fileHandler = new FileHandler("logs/application.log");
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            System.err.println("Could not create log file: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        setupLogging();
        logger.info("Starting application - CSV Data Import Demo");
        
        try {
            List<User> users = importUsersFromCSV();
            List<Coach> coaches = importCoachesFromCSV();
            List<model.Level> levels = importLevelsFromCSV();
            List<model.Intensity> intensities = importIntensitiesFromCSV();
            List<Exercise> exercises = importExercisesFromCSV();
            
            displayImportedData(users, coaches, levels, intensities, exercises);
            
            demonstrateObjectCreation(coaches, levels, intensities, exercises);
            
            logger.info("Application completed successfully");
            
        } catch (Exception e) {
            logger.severe("Fatal error in application: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static List<User> importUsersFromCSV() {
        List<User> users = new ArrayList<>();
        String csvFile = DATA_DIR + "/users.csv";
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get(csvFile))) {
            logger.info("Starting import of users from: " + csvFile);
            
            String line;
            boolean isFirstLine = true;
            int lineNumber = 0;
            
            while ((line = br.readLine()) != null) {
                lineNumber++;
                
                try {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }
                    
                    String[] data = line.split(",");
                    if (data.length != 3) {
                        throw new InvalidDataException(
                            "Invalid number of columns in users.csv at line " + lineNumber,
                            "columns", String.valueOf(data.length)
                        );
                    }
                    
                    String firstName = data[0].trim();
                    String lastName = data[1].trim();
                    String email = data[2].trim();
                    
                    if (!UserUtils.isValidName(firstName)) {
                        throw new InvalidDataException(
                            "Invalid first name in users.csv at line " + lineNumber,
                            "firstName", firstName
                        );
                    }
                    
                    if (!UserUtils.isValidName(lastName)) {
                        throw new InvalidDataException(
                            "Invalid last name in users.csv at line " + lineNumber,
                            "lastName", lastName
                        );
                    }
                    
                    if (!UserUtils.isValidEmail(email)) {
                        throw new InvalidDataException(
                            "Invalid email in users.csv at line " + lineNumber,
                            "email", email
                        );
                    }
                    
                    User user = new User(firstName, lastName, email);
                    users.add(user);
                    logger.fine("Successfully created user: " + user.getFullName());
                    
                } catch (InvalidDataException e) {
                    logger.warning("Invalid data in users.csv at line " + lineNumber + ": " + e.getMessage());
                } catch (Exception e) {
                    logger.warning("Error processing line " + lineNumber + " in users.csv: " + e.getMessage());
                }
            }
            
            logger.info("Successfully imported " + users.size() + " users from CSV");
            
        } catch (FileNotFoundException e) {
            logger.severe("Users CSV file not found: " + csvFile);
            throw new RuntimeException("Required data file missing: " + csvFile, e);
        } catch (IOException e) {
            logger.severe("Error reading users CSV file: " + e.getMessage());
            throw new RuntimeException("Failed to read users data", e);
        }
        
        return users;
    }
    

    private static List<Coach> importCoachesFromCSV() {
        List<Coach> coaches = new ArrayList<>();
        String csvFile = DATA_DIR + "/coaches.csv";
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get(csvFile))) {
            logger.info("Starting import of coaches from: " + csvFile);
            
            String line;
            boolean isFirstLine = true;
            int lineNumber = 0;
            
            while ((line = br.readLine()) != null) {
                lineNumber++;
                
                try {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }
                    
                    String[] data = line.split(",");
                    if (data.length != 4) {
                        throw new InvalidDataException(
                            "Invalid number of columns in coaches.csv at line " + lineNumber,
                            "columns", String.valueOf(data.length)
                        );
                    }
                    
                    String firstName = data[0].trim();
                    String lastName = data[1].trim();
                    String email = data[2].trim();
                    String experienceStr = data[3].trim();
                    
                    int experienceYears;
                    try {
                        experienceYears = Integer.parseInt(experienceStr);
                    } catch (NumberFormatException e) {
                        throw new InvalidDataException(
                            "Invalid experience years format in coaches.csv at line " + lineNumber,
                            "experienceYears", experienceStr
                        );
                    }
                    
                    if (!UserUtils.isValidName(firstName)) {
                        throw new InvalidDataException(
                            "Invalid first name in coaches.csv at line " + lineNumber,
                            "firstName", firstName
                        );
                    }
                    
                    if (!UserUtils.isValidName(lastName)) {
                        throw new InvalidDataException(
                            "Invalid last name in coaches.csv at line " + lineNumber,
                            "lastName", lastName
                        );
                    }
                    
                    if (!CoachUtils.isValidExperienceYears(experienceYears)) {
                        throw new InvalidDataException(
                            "Invalid experience years in coaches.csv at line " + lineNumber,
                            "experienceYears", String.valueOf(experienceYears)
                        );
                    }
                    
                    Coach coach = new Coach(firstName, lastName, email, experienceYears);
                    coaches.add(coach);
                    logger.fine("Successfully created coach: " + coach.getFullName());
                    
                } catch (InvalidDataException e) {
                    logger.warning("Invalid data in coaches.csv at line " + lineNumber + ": " + e.getMessage());
                } catch (Exception e) {
                    logger.warning("Error processing line " + lineNumber + " in coaches.csv: " + e.getMessage());
                }
            }
            
            logger.info("Successfully imported " + coaches.size() + " coaches from CSV");
            
        } catch (FileNotFoundException e) {
            logger.severe("Coaches CSV file not found: " + csvFile);
            throw new RuntimeException("Required data file missing: " + csvFile, e);
        } catch (IOException e) {
            logger.severe("Error reading coaches CSV file: " + e.getMessage());
            throw new RuntimeException("Failed to read coaches data", e);
        }
        
        return coaches;
    }
    
    private static List<model.Level> importLevelsFromCSV() {
        List<model.Level> levels = new ArrayList<>();
        String csvFile = DATA_DIR + "/levels.csv";
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get(csvFile))) {
            logger.info("Starting import of levels from: " + csvFile);
            
            String line;
            boolean isFirstLine = true;
            int lineNumber = 0;
            
            while ((line = br.readLine()) != null) {
                lineNumber++;
                
                try {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }
                    
                    String levelStr = line.trim();
                    model.Level level = null;
                    try {
                        level = model.Level.valueOf(levelStr);
                    } catch (IllegalArgumentException e) {
                        throw new InvalidDataException("Invalid level value: " + levelStr, "level", levelStr);
                    }
                    levels.add(level);
                    logger.fine("Successfully imported level: " + level);
                    
                } catch (IllegalArgumentException e) {
                    logger.warning("Invalid level in levels.csv at line " + lineNumber + ": " + line);
                } catch (Exception e) {
                    logger.warning("Error processing line " + lineNumber + " in levels.csv: " + e.getMessage());
                }
            }
            
            logger.info("Successfully imported " + levels.size() + " levels from CSV");
            
        } catch (FileNotFoundException e) {
            logger.severe("Levels CSV file not found: " + csvFile);
            throw new RuntimeException("Required data file missing: " + csvFile, e);
        } catch (IOException e) {
            logger.severe("Error reading levels CSV file: " + e.getMessage());
            throw new RuntimeException("Failed to read levels data", e);
        }
        
        return levels;
    }
    
    private static List<model.Intensity> importIntensitiesFromCSV() {
        List<model.Intensity> intensities = new ArrayList<>();
        String csvFile = DATA_DIR + "/intensities.csv";
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get(csvFile))) {
            logger.info("Starting import of intensities from: " + csvFile);
            
            String line;
            boolean isFirstLine = true;
            int lineNumber = 0;
            
            while ((line = br.readLine()) != null) {
                lineNumber++;
                
                try {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }
                    
                    String intensityStr = line.trim();
                    model.Intensity intensity = model.Intensity.valueOf(intensityStr);
                    intensities.add(intensity);
                    logger.fine("Successfully imported intensity: " + intensity);
                    
                } catch (IllegalArgumentException e) {
                    logger.warning("Invalid intensity in intensities.csv at line " + lineNumber + ": " + line);
                } catch (Exception e) {
                    logger.warning("Error processing line " + lineNumber + " in intensities.csv: " + e.getMessage());
                }
            }
            
            logger.info("Successfully imported " + intensities.size() + " intensities from CSV");
            
        } catch (FileNotFoundException e) {
            logger.severe("Intensities CSV file not found: " + csvFile);
            throw new RuntimeException("Required data file missing: " + csvFile, e);
        } catch (IOException e) {
            logger.severe("Error reading intensities CSV file: " + e.getMessage());
            throw new RuntimeException("Failed to read intensities data", e);
        }
        
        return intensities;
    }
    
    private static List<Exercise> importExercisesFromCSV() {
        List<Exercise> exercises = new ArrayList<>();
        String csvFile = DATA_DIR + "/exercises.csv";
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get(csvFile))) {
            logger.info("Starting import of exercises from: " + csvFile);
            
            String line;
            boolean isFirstLine = true;
            int lineNumber = 0;
            
            while ((line = br.readLine()) != null) {
                lineNumber++;
                
                try {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }
                    
                    String[] data = line.split(",");
                    if (data.length != 3) {
                        throw new InvalidDataException(
                            "Invalid number of columns in exercises.csv at line " + lineNumber,
                            "columns", String.valueOf(data.length)
                        );
                    }
                    
                    String name = data[0].trim();
                    String repsStr = data[1].trim();
                    String setsStr = data[2].trim();
                    
                    int reps;
                    try {
                        reps = Integer.parseInt(repsStr);
                    } catch (NumberFormatException e) {
                        throw new InvalidDataException(
                            "Invalid reps format in exercises.csv at line " + lineNumber,
                            "reps", repsStr
                        );
                    }
                    
                    int sets;
                    try {
                        sets = Integer.parseInt(setsStr);
                    } catch (NumberFormatException e) {
                        throw new InvalidDataException(
                            "Invalid sets format in exercises.csv at line " + lineNumber,
                            "sets", setsStr
                        );
                    }
                    
                    if (!ExerciseUtils.isValidName(name)) {
                        throw new InvalidDataException(
                            "Invalid exercise name in exercises.csv at line " + lineNumber,
                            "name", name
                        );
                    }
                    
                    if (!ExerciseUtils.isValidReps(reps)) {
                        throw new InvalidDataException(
                            "Invalid reps value in exercises.csv at line " + lineNumber,
                            "reps", String.valueOf(reps)
                        );
                    }
                    
                    if (!ExerciseUtils.isValidSets(sets)) {
                        throw new InvalidDataException(
                            "Invalid sets value in exercises.csv at line " + lineNumber,
                            "sets", String.valueOf(sets)
                        );
                    }
                    
                    Exercise exercise = new Exercise(name, reps, sets);
                    exercises.add(exercise);
                    logger.fine("Successfully created exercise: " + exercise.name());
                    
                } catch (InvalidDataException e) {
                    logger.warning("Invalid data in exercises.csv at line " + lineNumber + ": " + e.getMessage());
                } catch (Exception e) {
                    logger.warning("Error processing line " + lineNumber + " in exercises.csv: " + e.getMessage());
                }
            }
            
            logger.info("Successfully imported " + exercises.size() + " exercises from CSV");
            
        } catch (FileNotFoundException e) {
            logger.severe("Exercises CSV file not found: " + csvFile);
            throw new RuntimeException("Required data file missing: " + csvFile, e);
        } catch (IOException e) {
            logger.severe("Error reading exercises CSV file: " + e.getMessage());
            throw new RuntimeException("Failed to read exercises data", e);
        }
        
        return exercises;
    }
    
    private static void displayImportedData(List<User> users, List<Coach> coaches, List<model.Level> levels, List<model.Intensity> intensities, List<Exercise> exercises) {
        logger.info("Displaying imported data summary");
        
        System.out.println("\n=== IMPORTED DATA SUMMARY ===");
        System.out.println("Users imported: " + users.size());
        System.out.println("Coaches imported: " + coaches.size());
        System.out.println("Levels imported: " + levels.size());
        System.out.println("Intensities imported: " + intensities.size());
        System.out.println("Exercises imported: " + exercises.size());
        
        System.out.println("\n=== SAMPLE DATA ===");
        if (!users.isEmpty()) {
            System.out.println("Sample User: " + users.get(0));
        }
        if (!coaches.isEmpty()) {
            System.out.println("Sample Coach: " + coaches.get(0));
        }
        if (!exercises.isEmpty()) {
            System.out.println("Sample Exercise: " + exercises.get(0));
        }
    }
    
    private static void demonstrateObjectCreation(List<Coach> coaches, List<model.Level> levels, 
                                                List<model.Intensity> intensities, List<Exercise> exercises) {
        logger.info("Demonstrating object creation with error handling");
        
        System.out.println("\n=== OBJECT CREATION DEMONSTRATION ===");
        
        try {
            if (!coaches.isEmpty() && !levels.isEmpty()) {
                Coach coach = coaches.get(0);
                model.Level level = levels.get(0);
                
                Client client = Client.createClient("Alice", "Johnson", level, coach);
                System.out.println("Successfully created client: " + client.getFullName());
                logger.info("Successfully created client: " + client.getFullName());
            }
        } catch (Exception e) {
            logger.warning("Error creating client: " + e.getMessage());
        }
        
        try {
            Exercise invalidExercise = Exercise.createExercise("", -1, 0);
            if (invalidExercise == null) {
                throw new InvalidDataException("Failed to create exercise with invalid data", "exercise", "name='', reps=-1, sets=0");
            }
        } catch (InvalidDataException e) {
            System.out.println("Caught custom InvalidDataException: " + e.getMessage());
            System.out.println("Field: " + e.getFieldName() + ", Invalid Value: " + e.getInvalidValue());
            logger.info("Successfully demonstrated InvalidDataException: " + e.getMessage());
        }
        
        try {
            if (Math.random() > 0.5) {
                throw new IllegalArgumentException("Simulated illegal argument");
            } else {
                throw new NullPointerException("Simulated null pointer");
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Caught exception with multi-catch: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            logger.info("Demonstrated multi-catch exception handling: " + e.getClass().getSimpleName());
        }
        
        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("nonexistent.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found (expected): " + e.getMessage());
            logger.info("Demonstrated FileNotFoundException handling");
        } catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
            logger.warning("IO error occurred: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                    System.out.println("Reader closed in finally block");
                } catch (IOException e) {
                    logger.warning("Error closing reader: " + e.getMessage());
                }
            } else {
                System.out.println("No reader to close in finally block");
            }
            logger.info("Finally block executed");
        }
    }
}