package ua.model;

import ua.util.CoachUtils;
import ua.util.UserUtils;

import java.util.Objects;

public class Coach extends User{
    private int experienceYears;
    
    public Coach() {
        super();
    }
    
    public Coach(String firstName, String lastName, String email, int experienceYears) {
        super(firstName, lastName, email);
        setExperienceYears(experienceYears);
    }
    
    public int getExperienceYears() {
        return experienceYears;
    }
    
    public void setExperienceYears(int experienceYears) {
        if (CoachUtils.isValidExperienceYears(experienceYears)) {
            this.experienceYears = experienceYears;
        }
    }
    
    public static Coach createCoach(String firstName, String lastName, int experienceYears) {
        if (CoachUtils.isValidExperienceYears(experienceYears) && UserUtils.isValidName(firstName) && UserUtils.isValidName(lastName)) {
            String email = UserUtils.generateEmailFromNames(firstName, lastName);
            return new Coach(firstName, lastName, email, experienceYears);
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "Coach{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", experienceYears=" + experienceYears +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Coach coach = (Coach) o;
        return experienceYears == coach.experienceYears;
    }
    
    @Override
    public int hashCode() {
       return Objects.hash(super.hashCode(), experienceYears);
    }
}