package model;


import util.PlanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Plan {
    private Date startDate;
    private Client client;
    private List<Workout> workouts;
    
    public Plan() {
        this.workouts = new ArrayList<Workout>();
    }
    
    public Plan(List<Workout> workouts, Date startDate, Client client) {
        setStartDate(startDate);
        this.client = client;
        this.workouts = workouts;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        if(PlanUtils.isValidStartDate(startDate))
            this.startDate = startDate;
    }
    
    public Client getClient() {
        return client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    public List<Workout> getWorkouts() {
        return workouts;
    }
    
    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }
    
    public void addWorkout(Workout workout) {
        this.workouts.add(workout);
    }
    
    public void removeWorkout(Workout workout) {
        this.workouts.remove(workout);
    }
    
    public static Plan createPlan(List<Workout> workouts, Date startDate, Client client) {
        if(PlanUtils.isValidStartDate(startDate)) {
            return new Plan(workouts, startDate, client);
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "Plan{" +
                "startDate=" + startDate +
                ", client=" + (client != null ? client.getFullName() : "No Client") +
                ", workouts=" + (workouts != null ? workouts.size() : 0) +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plan plan = (Plan) o;

        if (startDate != null ? !startDate.equals(plan.startDate) : plan.startDate != null) return false;
        if (client != null ? !client.equals(plan.client) : plan.client != null) return false;
        return workouts != null ? workouts.equals(plan.workouts) : plan.workouts == null;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(startDate, client, workouts);
    }
}
