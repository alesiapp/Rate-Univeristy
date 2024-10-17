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

public class lexoKursetTest {

    private CourseDetails yourClass;

    @BeforeEach
    public void setUp() {
        yourClass = new CourseDetails();
    } 
    @Test
    
    public void testLexoKursetEmptyFile() throws IOException {
       
        String filePath ="C:\\Users\\alesi\\Desktop\\Kurs.txt";
        createEmptyTestFile(filePath);
        List<Course> courses=new ArrayList<>();
        
        yourClass.lexoKurset();

        assertTrue(courses.isEmpty());

        deleteTestFile(filePath);
    }


    @Test
    public void testLexoKursetSuccess() throws IOException {
      
        String filePath ="C:\\Users\\alesi\\Desktop\\Kurs.txt";
        createTestFile(filePath);

        
        ArrayList<Course> courses = (ArrayList<Course>) yourClass.getCourses();

       
        yourClass.lexoKurset();

       
        assertFalse(courses.isEmpty());

        
        deleteTestFile(filePath);
    }

    

    @Test
    public void testLexoKursetFileNotFound() {
       
        assertDoesNotThrow(() -> yourClass.lexoKurset());
    }

    private void createTestFile(String filePath) throws IOException {
        List<Course> courses = Arrays.asList(
                new Course("Math", "Math Course", "Dr. Smith", "9:00 AM", "Room 101", new Date()),
                new Course("Physics", "Physics Course", "Prof. Johnson", "2:00 PM", "Room 201", new Date())
        );

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (Course course : courses) {
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
