import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ruajStudentTest {

    private DropCourse yourObject;
    private String filePath;

    @BeforeEach
    public void setUp() {
        yourObject = new DropCourse(new Student("",""));  
        filePath = "C:\\Users\\alesi\\Desktop\\Student.txt";
    }

    @AfterEach
    public void tearDown() {

        File fileToDelete = new File(filePath);
        if (fileToDelete.exists()) {
            fileToDelete.delete();
        }
    }

    @Test
    public void testRuajStudent() {
      
        Student student1 = new Student("John", "john@example.com");
        Student student2 = new Student("Alice", "alice@example.com");
        ArrayList<Student> student=new ArrayList<>();

   
        student.add(student1);
        student.add(student2);
        yourObject.setStudent(student);

       
        assertDoesNotThrow(() -> yourObject.ruajStudent(), "Exception should not be thrown during saving");


        File file = new File(filePath);
        assertTrue(file.exists(), "File should exist");
        assertTrue(file.length() > 0, "File should not be empty");

       
    }
}
