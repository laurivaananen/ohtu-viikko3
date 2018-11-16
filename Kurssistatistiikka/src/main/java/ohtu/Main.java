package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.IntStream;;

public class Main {

    public static void main(String[] args) throws IOException {
        String studentNr = "3";

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

        System.out.println("opiskelijanumvero: " + studentNr + "\n");

        for ( Submission submission : subs ) {
            System.out.println(submission);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\nyhteensä: ");
        Stream<Submission> stream = Arrays.stream(subs);
        sb.append(stream.mapToInt(x -> x.getExercises().size()).sum());
        sb.append(" tehtävää ");
        Stream<Submission> stream_2 = Arrays.stream(subs);
        sb.append(stream_2.mapToInt(x -> x.getHours()).sum());
        sb.append(" tuntia");
        System.out.println(sb.toString());
    }
}
