

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class deleteTest {

    private ViewFeedback yourObject;
    private List<Course> courses;

    @BeforeEach
    public void setUp() {
      
        yourObject = new ViewFeedback();  
        courses = new ArrayList<>();
    }

    @Test
    public void testDelete() throws IOException {
       
        String dateString = "2024/01/01";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;

        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Course course1 = new Course("Math", "", "", "", "", date);
        Course course2 = new Course("Physics", "", "", "", "", date);

       
        Feedback outdatedFeedback1 = new Feedback("Outdated feedback", 1);
        outdatedFeedback1.setDate(LocalDate.now().minusDays(5));
        System.out.println(outdatedFeedback1.getDate());
        Feedback outdatedFeedback2 = new Feedback("Another outdated feedback", 4);
        outdatedFeedback2.setDate(LocalDate.now().minusDays(2));

       
        Feedback currentFeedback1 = new Feedback("Current feedback", 1);
        Feedback currentFeedback2 = new Feedback("Future feedback", 4);
        currentFeedback2.setDate(LocalDate.now().plusDays(2));
        currentFeedback1.setDate(LocalDate.now().plusDays(2));

       
        course1.getFeedback().add(0, currentFeedback1);
        course1.getFeedback().add(1, outdatedFeedback1);
        course2.getFeedback().add(0, outdatedFeedback2);
        course2.getFeedback().add(1, currentFeedback2);

        courses.add(course1);
        courses.add(course2);
        yourObject.setCourseList(courses);


        yourObject.delete();

        
        assertEquals(1, course1.getFeedback().size(), "Only current feedback should remain in course1");
        assertEquals(1, course2.getFeedback().size(), "Only current feedback should remain in course2");
    }
}
