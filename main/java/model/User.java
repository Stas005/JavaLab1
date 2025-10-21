package model;


import util.UserUtils;

import java.util.Objects;

public class User {
    protected String firstName;
    protected String lastName;
    protected String email;

    public User() {
    }

    public User(String firstName, String lastName, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }

    public String getFullName() {
        return UserUtils.formarName(firstName, lastName);
    }

    public  String getFirstName() {
        return firstName;
    }

    public  void setFirstName(String firstName) {
        if (UserUtils.isValidName(firstName)) {
            this.firstName = UserUtils.capitalizeText(firstName);
        }
    }

    public  String getLastName() {
        return lastName;
    }

    public  void setLastName(String lastName) {
        if (UserUtils.isValidName(lastName)) {
            this.lastName = UserUtils.capitalizeText(lastName);
        }
    }

    public  String getEmail() {
        return email;
    }

    public  void setEmail(String email) {
        if (email != null) {
            String formattedEmail = UserUtils.formatEmail(email);
            if (UserUtils.isValidEmail(formattedEmail)) {
                this.email = formattedEmail;
            }
        }
    }

    public  static User createUser(String firstName, String lastName) {
        if (UserUtils.isValidName(firstName) && UserUtils.isValidName(lastName)) {
            String email = UserUtils.generateEmailFromNames(firstName, lastName);
            return new User(firstName, lastName, email);
        }
        return null;
    }
    
    @Override
    public  String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    
    @Override
    public  boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        User user = (User) obj;

        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email);
    }
    
    @Override
    public  int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }
}
