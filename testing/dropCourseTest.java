import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class dropCourseTest {

    private DropCourse yourClass;
    private ArrayList<Student> studentList;

    @Before
    public void setUp() {
    	studentList = (ArrayList<Student>) createTestStudentList();
       yourClass= new DropCourse(studentList.get(0));
       yourClass.setStudent(studentList);
        
    }

    @Test
    public void testDropCourseSuccess() throws IOException {
        assertTrue(yourClass.dropCourse("Kurs1"));


        assertEquals(0, studentList.get(0).getKurset().size());
    }

    @Test
    public void testDropCourseCourseNotFound() throws IOException {
        assertFalse(yourClass.dropCourse("NonexistentCourse"));

     
        assertEquals(1, studentList.get(0).getKurset().size());
    }

    
    private List<Student> createTestStudentList() {
        List<Student> students = new ArrayList<>();

        String dateString = "2024/01/01";
       

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        Student student = new Student("test@example.com", "password");
        Course courseToDrop = new Course("Kurs1", "", "", "", "",date);
        student.getKurset().add(courseToDrop);
        students.add(student);

        return students;
    }
}