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

    @Override
    public String toString() {
        return name + " " + term + " " + year;
    }
    
}