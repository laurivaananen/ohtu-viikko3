package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.IntStream;;

public class Main {

    public static void main(String[] args) throws IOException {
        String studentNr = "88";

        if ( args.length > 0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";

        String coursesUrl = "https://studies.cs.helsinki.fi/courses/courseinfo";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        String coursesBodyText = Request.Get(coursesUrl).execute().returnContent().asString();

        // System.out.println("json-muotoinen data:");
        // System.out.println(bodyText);

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course[] courses = mapper.fromJson(coursesBodyText, Course[].class);

        for (int x = 0; x < courses.length; x++) {
            Course course = courses[x];
            for (int y = 0; y < subs.length; y++) {
                if ( subs[y].getCourse().equals(course.name )) {
                    Submission sub = subs[y];
                    int maxExercises = course.exercises.get(sub.getWeek());
                    sub.setMaxExercises(maxExercises);
                    course.setSubmission(sub);
                }
            }
        }

        System.out.println("opiskelijanumero: " + studentNr);

        for ( Course course : courses ) {
            if (course.getSubmissions().size() > 0) {
                System.out.println("\n" + course + "\n");
                for ( Submission submission : course.getSubmissions() ) {
                    System.out.println(submission);
                }
            StringBuilder sb = new StringBuilder();
            sb.append("\nyhteensä: ");
            Stream<Submission> stream = course.getSubmissions().stream();
            sb.append(stream.mapToInt(x -> x.getExercises().size()).sum());
            sb.append(" tehtävää ");
            Stream<Submission> stream_2 = course.getSubmissions().stream();
            sb.append(stream_2.mapToInt(x -> x.getHours()).sum());
            sb.append(" tuntia");
            System.out.println(sb.toString());
            }
        }

        
    }
}
