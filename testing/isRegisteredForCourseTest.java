
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class isRegisteredForCourseTest {

    private LeaveFeedback yourObject;
    private List<Course> courses;
    private List<Student> students;

    @BeforeEach
    public void setUp() {
        
        yourObject = new  LeaveFeedback(null);  
        courses = new ArrayList<>();
        students = new ArrayList<>();
    }

    @Test
    public void testIsRegisteredForCourse() throws IOException {
       
    	String dateString = "2024/01/01";
        

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
        Course course = new Course("Math","","","","",date);
        Student student = new Student("test@email.com", "password");
        Feedback feedback = new Feedback("Good course!",3);
        
   
        students.add(student);
        courses.add(course);
        LeaveFeedback.setCourses(courses);

        student.getKurset().add(course);
        yourObject=new LeaveFeedback(student);
        LeaveFeedback.setStudents((ArrayList<Student>) students);

       
        boolean result = yourObject.isRegisteredForCourse("Math", feedback);

       
        assertTrue(result, "Student should be registered, and feedback should be added");

        
        assertTrue(course.getFeedback().contains(feedback), "Feedback should be added to the course");
      
    }

    @Test
    public void testIsRegisteredForCourseWithExistingFeedback() throws IOException {
       
        String dateString = "2024/01/01";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
		
			e.printStackTrace();
		}
        Course course = new Course("Math","","","","",date);
        Student student = new Student("test@email.com", "password");
        Feedback existingFeedback = new Feedback("Already provided feedback!",3);

       
        students.add(student);
        courses.add(course);
        student.getKurset().add(course);
        yourObject=new LeaveFeedback(student);
        course.getFeedback().add(existingFeedback);
        LeaveFeedback.setStudents((ArrayList<Student>) students);


       
        boolean result = yourObject.isRegisteredForCourse("Math", new Feedback("New feedback",5));

        assertFalse(result, "Feedback should not be added, as it already exists");

       
        assertTrue(course.getFeedback().contains(existingFeedback), "Existing feedback should remain in the course");
        
    }
}