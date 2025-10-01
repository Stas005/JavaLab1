package ua.model;

import ua.util.ProgressUtils;

import java.util.Date;
import java.util.Objects;

public class Progress {
    private Date date;
    private double weight;
    private double bmi;
    private Client client;
    
    public Progress() {
    }
    
    public Progress(Date date, double weight, double bmi, Client client) {
        setDate(date);
        setWeight(weight);
        setBmi(bmi);
        this.client = client;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        if(ProgressUtils.isValidDate(date))
            this.date = date;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double weight) {
        if(ProgressUtils.isValidWeight(weight))
            this.weight = weight;
    }
    
    public double getBmi() {
        return bmi;
    }
    
    public void setBmi(double bmi) {
        if(ProgressUtils.isValidBmi(bmi))
            this.bmi = bmi;
    }
    
    public Client getClient() {
        return client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    public static Progress createProgress(Date date, double weight, double bmi, Client client) {
        if(ProgressUtils.isValidDate(date) && ProgressUtils.isValidWeight(weight) && ProgressUtils.isValidBmi(bmi)) {
            return new Progress(date, weight, bmi, client);
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "Progress{" +
                "date=" + date +
                ", weight=" + weight +
                ", bmi=" + bmi +
                ", client=" + (client != null ? client.getFullName() : "No Client") +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Progress progress = (Progress) o;

        if (Double.compare(progress.weight, weight) != 0) return false;
        if (Double.compare(progress.bmi, bmi) != 0) return false;
        if (date != null ? !date.equals(progress.date) : progress.date != null) return false;
        return client != null ? client.equals(progress.client) : progress.client == null;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(date, weight, bmi, client);
    }
}
