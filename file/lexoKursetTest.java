import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class readCoursesTest {

    private CourseDetails yourClass;

    @BeforeEach
    public void setUp() {
        yourClass = new CourseDetails();
    } 
    @Test
    
    public void testReadCoursesEmptyFile() throws IOException {
       
        String filePath = "Kurs.txt";
        createEmptyTestFile(filePath);
        List<Course> courseList=new ArrayList<>();
        
        yourClass.readCourses();

        assertTrue(courseList.isEmpty());

        deleteTestFile(filePath);
    }


    @Test
    public void testReadCoursesSuccess() throws IOException {
      
        String filePath = "Kurs.txt";
        createTestFile(filePath);

        
        ArrayList<Course> courseList = (ArrayList<Course>) yourClass.getCourses();

       
        yourClass.readCourses();

       
        assertFalse(courseList.isEmpty());

        
        deleteTestFile(filePath);
    }

    

    @Test
    public void testReadCoursesFileNotFound() {
       
        assertDoesNotThrow(() -> yourClass.readCourses());
    }

    private void createTestFile(String filePath) throws IOException {
    
        List<Course> courseList = Arrays.asList(
                new Course("Math", "Math Course", "Dr. Smith", "9:00 AM", "Room 101", new Date()),
                new Course("Physics", "Physics Course", "Prof. Johnson", "2:00 PM", "Room 201", new Date())
        );

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (Course course : courseList) {
                oos.writeObject(course);
            }
        }
    }

    
    private void createEmptyTestFile(String filePath) throws IOException {
        new File(filePath).createNewFile();
    }

   
    private void deleteTestFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
