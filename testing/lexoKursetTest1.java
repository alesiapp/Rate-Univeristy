
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

public class lexoKursetTest1 {

    private SimpleCalendarGUI yourClass;

    @BeforeEach
    public void setUp() {
        yourClass = new SimpleCalendarGUI();
    } 
    @Test
    
    public void testLexoKursetEmptyFile() throws IOException {
        // Set up an empty test file
        String filePath = "C:\\Users\\alesi\\Desktop\\Kurs.txt";
        createEmptyTestFile(filePath);
        List<Course> courses=new ArrayList<>();
        // Invoke lexoKurset and verify that the courses list is empty
        yourClass.lexoKurset();

        // Assuming you have a method to get the courses list in YourClass
        

        // Verify that the courses list is empty
        assertTrue(courses.isEmpty());

        // Clean up the test file
        deleteTestFile(filePath);
    }

    @Test
    public void testLexoKursetSuccess() throws IOException {
        // Set up a test file with serialized Course objects
    	String filePath ="C:\\Users\\alesi\\Desktop\\Kurs.txt";
        createTestFile(filePath);

        
        ArrayList<Course> courses = (ArrayList<Course>) yourClass.getCourses();

       
        yourClass.lexoKurset();

       
        assertFalse(courses.isEmpty());

        
        deleteTestFile(filePath);
    }

    

    @Test
    public void testLexoKursetFileNotFound() {
        // Invoke lexoKurset with a non-existent file and verify no exception is thrown
        assertDoesNotThrow(() -> yourClass.lexoKurset());
    }

    private void createTestFile(String filePath) throws IOException {
        // Serialize multiple Course objects and write to the file
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

    // Helper method to create an empty test file
    private void createEmptyTestFile(String filePath) throws IOException {
        new File(filePath).createNewFile();
    }

    // Helper method to delete a test file
    private void deleteTestFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
