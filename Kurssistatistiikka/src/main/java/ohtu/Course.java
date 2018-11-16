package ohtu;

import java.util.List;
import java.util.ArrayList;

public class Course {
    public String _id;
    public String name;
    public String url;
    public int week;
    public boolean enabled;
    public String term;
    public int year;
    public int __v;
    public String fullName;
    public boolean miniproject;
    public List<Integer> exercises;
    private List<Submission> submissions = new ArrayList<Submission>();
    private int totalStudents;
    private int totalSubmissions;
    private int totalHours;

    public Course(){

    }

    public int totalExercises() {
        return this.exercises.stream().mapToInt(x -> x.intValue()).sum();
    }

    public void setSubmission(Submission submission) {
        this.submissions.add(submission);
    }

    public List<Submission> getSubmissions() {
        return this.submissions;
    }

    /**
     * @param totalHours the totalHours to set
     */
    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    /**
     * @param totalStudents the totalStudents to set
     */
    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    /**
     * @param totalSubmissions the totalSubmissions to set
     */
    public void setTotalSubmissions(int totalSubmissions) {
        this.totalSubmissions = totalSubmissions;
    }

    /**
     * @return the totalHours
     */
    public int getTotalHours() {
        return totalHours;
    }

    /**
     * @return the totalStudents
     */
    public int getTotalStudents() {
        return totalStudents;
    }

    /**
     * @return the totalSubmissions
     */
    public int getTotalSubmissions() {
        return totalSubmissions;
    }

    @Override
    public String toString() {
        return name + " " + term + " " + year;
    }
    
}