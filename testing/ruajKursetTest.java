import org.junit.jupiter.api.Test;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ruajKursetTest {

    @Test
    public void testRuajKurset() {
     
String dateString = "2024/01/01";
        

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
        DropCourse yourObject = new DropCourse(new Student("","")); 
        Course course1 = new Course("Math", "","Instructor1","","",date);
        Course course2 = new Course("Physics", "","Instructor2","","",date);
       ArrayList<Course> course=new ArrayList<>();
     
        course.add(course1);
        course.add(course2);
        yourObject.setCourses(course);

        yourObject.emptyCourses();
        yourObject.ruajKurset();

   
        DropCourse loadedObject = new DropCourse(new Student("","")); 
        loadedObject.lexoKurset(); 

       
        assertEquals("Math", loadedObject.getCourses().get(0).getName(), "Course 1 name should match");
        assertEquals("Instructor1", ( loadedObject.getCourses()).get(0).getLecturer(), "Course 1 instructor should match");
        assertEquals("Physics", (loadedObject.getCourses()).get(1).getName(), "Course 2 name should match");
        assertEquals("Instructor2", (loadedObject.getCourses()).get(1).getLecturer(), "Course 2 instructor should match");

    
        File fileToDelete = new File("C:\\Users\\alesi\\Desktop\\Kurs.txt");
        if (fileToDelete.exists()) {
            fileToDelete.delete();
        }
    }
}