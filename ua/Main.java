package ua;

import ua.model.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Coach coach1 = new Coach();
        coach1.setFirstName("John");
        coach1.setLastName("Doe");
        coach1.setEmail("coachmail@gmail.com");
        coach1.setExperienceYears(5);
        System.out.println("Coach First Name: " + coach1.getFirstName());
        System.out.println("Coach Last Name: " + coach1.getLastName());
        System.out.println("Coach Full Name: " + coach1.getFullName());
        System.out.println("Coach Email: " + coach1.getEmail());
        System.out.println("Coach Experience Years: " + coach1.getExperienceYears());
        
        Coach coach2 = Coach.createCoach("Jane", "Smith", 10);
        System.out.println("Created Coach: " + coach2);

        System.out.println(coach1.equals(coach2));

        
        Client client1 = new Client();
        
        client1.setFirstName("Alice");
        client1.setLastName("Johnson");
        client1.setEmail("clientmail@gmail.com");
        client1.SetLevel("Intermediate");
        client1.setCoach(coach1);
        System.out.println("Client First Name: " + client1.getFirstName());
        System.out.println("Client Last Name: " + client1.getLastName());
        System.out.println("Client Full Name: " + client1.getFullName());
        System.out.println("Client Email: " + client1.getEmail());
        System.out.println("Client Level: " + client1.getLevel());
        System.out.println("Client's Coach: " + (client1.getCoach() != null ? client1.getCoach().getFullName() : "No Coach"));
        
        Client client2 = Client.createClient("Bob", "Brown", "Beginner", coach2);
        System.out.println("Created Client: " + client2);


        Progress progress1 = new Progress();
        progress1.setDate(new java.util.Date());
        progress1.setWeight(75.5);
        progress1.setBmi(22.5);
        progress1.setClient(client1);
        System.out.println("Progress Date: " + progress1.getDate());
        System.out.println("Progress Weight: " + progress1.getWeight());
        System.out.println("Progress BMI: " + progress1.getBmi());
        System.out.println("Progress Client: " + (progress1.getClient() != null ? progress1.getClient().getFullName() : "No Client"));
        
        Progress progress2 = Progress.createProgress(new java.util.Date(), 80.0, 24.0, client2);
        System.out.println("Created Progress: " + progress2);


        Exercise exercise1 = new Exercise();
        exercise1.setName("Push-ups");
        exercise1.setReps(15);
        exercise1.setSets(3);
        System.out.println("Exercise Name: " + exercise1.getName());
        System.out.println("Exercise Reps: " + exercise1.getReps());
        System.out.println("Exercise Sets: " + exercise1.getSets());
        Exercise exercise2 = Exercise.createExercise("Squats", 20, 4);
        System.out.println("Created Exercise: " + exercise2);
        Exercise exercise3 = Exercise.createExercise("Plank", 1, 3);
        System.out.println("Created Exercise: " + exercise3);

        List<Exercise> exercises = List.of(exercise1, exercise2, exercise3);
        
        
        Workout workout1 = new Workout();
        workout1.setTitle("Morning Cardio");
        workout1.setDurationMinutes(30);
        workout1.setIntensity("Medium");
        workout1.addExercise(exercise1);
        workout1.addExercise(exercise2);
        System.out.println("Workout Title: " + workout1.getTitle());
        System.out.println("Workout Duration: " + workout1.getDurationMinutes() + " minutes");
        System.out.println("Workout Intensity: " + workout1.getIntensity());
        System.out.println("Workout Exercises: " + workout1.getExercises());
        Workout workout2 = Workout.createWorkout("Evening Strength", 45, "High", exercises);
        System.out.println("Created Workout: " + workout2);
        
        List<Workout> workouts = List.of(workout1, workout2);
        
        Plan plan1 = new Plan();
        plan1.setStartDate(new java.util.Date());
        plan1.setClient(client1);
        plan1.addWorkout(workout1);
        System.out.println("Plan Start Date: " + plan1.getStartDate());
        System.out.println("Plan Client: " + (plan1.getClient() != null ? plan1.getClient().getFullName() : "No Client"));
        System.out.println("Plan Workouts: " + plan1.getWorkouts());
        Plan plan2 = Plan.createPlan(workouts, new java.util.Date(), client2);
        System.out.println("Created Plan: " + plan2);
    }
}