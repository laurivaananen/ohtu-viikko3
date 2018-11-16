package ohtu;

import java.util.List;
import java.util.ArrayList;

public class Submission {
    private String course;
    private int week;
    private int hours;
    private List<Integer> exercises = new ArrayList<Integer>();
    private int maxExercises;

    public Submission(){

    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public void setExercises(List<Integer> exercises) {
        this.exercises = exercises;
    }

    public List<Integer> getExercises() {
        return exercises;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public int totalExercises() {
        return this.exercises.stream().mapToInt(i -> i.intValue()).sum();
    }

    public void setMaxExercises(int maxExercises) {
        this.maxExercises = maxExercises;
    }
    
    public int getMaxExercises() {
        return maxExercises;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("viikko ");
        sb.append(this.week);
        sb.append(":\n");
        sb.append(" tehtyjä tehtäviä yhteensä ");
        sb.append(this.exercises.size());
        sb.append("/");
        sb.append(getMaxExercises());
        sb.append(" aikaa kului ");
        sb.append(this.hours);
        sb.append(" tehdyt tehtävät: ");
        sb.append(this.exercises.toString().substring(1, this.exercises.toString().length() - 1));
        return sb.toString();
    }
    
}