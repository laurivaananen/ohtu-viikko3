package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.IntStream;;

public class Main {

    public static void main(String[] args) throws IOException {
        String studentNr = "8";

        if ( args.length > 0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";

        String coursesUrl = "https://studies.cs.helsinki.fi/courses/courseinfo";

        String ohtuUrl = "https://studies.cs.helsinki.fi/courses/ohtu2018/stats";

        String railsUrl = "https://studies.cs.helsinki.fi/courses/rails2018/stats";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        String coursesBodyText = Request.Get(coursesUrl).execute().returnContent().asString();
        String ohtuText = Request.Get(ohtuUrl).execute().returnContent().asString();
        String railsText = Request.Get(railsUrl).execute().returnContent().asString();

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course[] courses = mapper.fromJson(coursesBodyText, Course[].class);
        JsonParser parser = new JsonParser();
        JsonObject ohtuData = parser.parse(ohtuText).getAsJsonObject();
        JsonObject railsData = parser.parse(railsText).getAsJsonObject();

        for (int x = 0; x < courses.length; x++) {
            Course course = courses[x];

            JsonObject dataSet = null;
            int students = 0;
            int hour_total = 0;
            int exercise_total = 0;

            if (course.name.equals("ohtu2018")) {
                dataSet = ohtuData;
                for (String key : dataSet.keySet()) {
                    students += dataSet.getAsJsonObject(key).get("students").getAsInt();
                    hour_total += dataSet.getAsJsonObject(key).get("hour_total").getAsInt();
                    exercise_total += dataSet.getAsJsonObject(key).get("exercise_total").getAsInt();
                }
            } else if (course.name.equals("rails2018")) {
                dataSet = railsData;
                for (String key : dataSet.keySet()) {
                    students += dataSet.getAsJsonObject(key).get("students").getAsInt();
                    hour_total += dataSet.getAsJsonObject(key).get("hour_total").getAsInt();
                    exercise_total += dataSet.getAsJsonObject(key).get("exercise_total").getAsInt();
                }
            }

            course.setTotalHours(hour_total);
            course.setTotalStudents(students);
            course.setTotalSubmissions(exercise_total);

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

            sb = new StringBuilder();
            sb.append("\n");
            sb.append("kursilla yhteensä ");
            sb.append(course.getTotalStudents());
            sb.append(" palautusta, palautettuja tehtäviä ");
            sb.append(course.getTotalSubmissions());
            sb.append(" kpl, aikaa käytetty yhteensä ");
            sb.append(course.getTotalHours());
            sb.append(" tuntia");
            System.out.println(sb.toString());
            }
        }

        
    }
}
