import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class joinCourseTest {

    @Test
    public void testShtoKursSuccess() throws IOException {
    	Student testStudent = new Student("test@email.com", "testPassword");
        joinCourse yourClass = new joinCourse(testStudent);
        yourClass.setStudent(new ArrayList());
        yourClass.setCourses(new ArrayList());
  
      

     
        
        Course testCourse = new Course("Test Course", "Course Description", "Test Lecturer", "10:00 AM", "Room 101", new Date());


        yourClass.getStudent().add(testStudent);
        ((joinCourse) yourClass).getCourses().add(testCourse);

        assertFalse(yourClass.isRegistered(testCourse)); 

        assertTrue(yourClass.shtoKurs("Test Course"));

     
        assertTrue(yourClass.isRegistered(testCourse));
        assertEquals(1, testCourse.getStudentNr());
    }

    @Test
    public void testShtoKursFailureCourseNotFound() throws IOException {
    	Student testStudent = new Student("test@email.com", "testPassword");
        joinCourse yourClass = new joinCourse(testStudent);

       
        yourClass.setStudent(new ArrayList<Student>());
        yourClass.setCourses(new ArrayList<Course>());

       Course testCourse = new Course("Test", "Course Description", "Test Lecturer", "10:00 AM", "Room 101", new Date());
    
        yourClass.getStudent().add(testStudent);

        yourClass.getCourses().add(testCourse);
        assertFalse(yourClass.shtoKurs("Nonexistent Course")); 

       
        
        assertTrue(((Student) yourClass.getStudent().get(0)).getKurset().isEmpty());
        
    }

    @Test
    public void testShtoKursFailureAlreadyRegistered() throws IOException {
    	Student testStudent = new Student("test@email.com", "testPassword");
        joinCourse yourClass = new joinCourse(testStudent);

        
        yourClass.setStudent(new ArrayList());
        yourClass.setCourses(new ArrayList());

       
        Course testCourse = new Course("Test Course", "Course Description", "Test Lecturer", "10:00 AM", "Room 101", new Date());

  
        yourClass.getStudent().add(testStudent);
        yourClass.getCourses().add(testCourse);

       
        yourClass.shtoKurs("Test Course");

        assertFalse(yourClass.shtoKurs("Test Course")); 

       
        assertEquals(1, testCourse.getStudentNr());
    }

   

}
