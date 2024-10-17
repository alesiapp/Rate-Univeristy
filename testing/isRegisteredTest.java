

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class isRegisteredTest {

    @Test
    public void testIsRegistered() {
    	String dateString = "2024/01/01";
        

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
		
			e.printStackTrace();
		}
      
    	
        List<Student> students = new ArrayList<>();
        Course courseToCheck = new Course("Kurs1", "", "", "", "",date);

        Student studentWithCourse = new Student("test@email.com","pass123");
        studentWithCourse.getKurset().add(new Course("Kurs1", "", "", "", "",date));

        students.add(studentWithCourse);

       
        Student studentWithoutCourse = new Student("other@email.com","pass123");
        students.add(studentWithoutCourse);
        joinCourse yourObject = new joinCourse(studentWithCourse);
      
        yourObject.setStudentList(students);
 
        
        assertTrue(yourObject.isRegistered(courseToCheck), "Expected course should be registered");

      
        
        Course nonRegisteredCourse = new Course("English", "", "", "", "",date);
        yourObject = new joinCourse(studentWithoutCourse);
        assertFalse(yourObject.isRegistered(nonRegisteredCourse), "Non-registered course should not be found");
    }
}