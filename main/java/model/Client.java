package model;

import util.ClientUtils;
import util.UserUtils;

import java.util.Objects;

public class Client extends User{
    private Level level;
    private Coach coach;
    
    public Client() {
        super();
    }
    
    public Client(String firstName, String lastName, String email, Level level, Coach coach) {
        super(firstName, lastName, email);
        setLevel(level);
        setCoach(coach);
    }
    
    public Level getLevel() {
        return level;
    }
    
    public void setLevel(Level level) {
        this.level = level;
    }
    
    public Coach getCoach() {
        return coach;
    }
    
    public void setCoach(Coach coach) {
        this.coach = coach;
    }
    
    public static Client createClient(String firstName, String lastName, Level level, Coach coach) {
        if (level != null && UserUtils.isValidName(firstName) && UserUtils.isValidName(lastName)) {
            String email = UserUtils.generateEmailFromNames(firstName, lastName);
            return new Client(firstName, lastName, email, level, coach);
        }
        return null;
    }

    public static Client createClient(String firstName, String lastName, String levelString, Coach coach) {
        Level parsed = ClientUtils.parseLevel(levelString);
        if (parsed != null && UserUtils.isValidName(firstName) && UserUtils.isValidName(lastName)) {
            String email = UserUtils.generateEmailFromNames(firstName, lastName);
            return new Client(firstName, lastName, email, parsed, coach);
        }
        return null;
    }
    
    @Override 
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", level='" + level + '\'' +
                ", coach=" + (coach != null ? coach.getFullName() : "No Coach") +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(level, client.level) &&
                Objects.equals(coach, client.coach);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), level, coach);
    }
}
