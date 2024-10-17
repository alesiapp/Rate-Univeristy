import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.jupiter.api.Test;

public class emptyCourseTest {
	@Test
	 public void testEmptySuccess() throws IOException {
	        ViewFeedback yourClass = new ViewFeedback();

	        
	        String filePath = "C:\\Users\\alesi\\Desktop\\Kurs.txt";
	        createTestFile(filePath, "Some content");

	        yourClass.emptyCourses();

	        assertTrue(isFileEmpty(filePath));

	        deleteTestFile(filePath);
	    }

	    @Test
	    public void testEmptyEmptyFile() throws IOException {
	        ViewFeedback yourClass = new ViewFeedback();

	        String filePath = "C:\\Users\\alesi\\Desktop\\Kurs.txt";
	        createEmptyTestFile(filePath);

	        yourClass.emptyCourses();

	        assertTrue(isFileEmpty(filePath));

	        deleteTestFile(filePath);
	    }

	    
	    private void createTestFile(String filePath, String content) throws IOException {
	        try (PrintWriter writer = new PrintWriter(filePath)) {
	            writer.print(content);
	        }
	    }

	   
	    private void createEmptyTestFile(String filePath) throws IOException {
	        new File(filePath).createNewFile();
	    }

	   
	    private boolean isFileEmpty(String filePath) throws IOException {
	        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	            return reader.readLine() == null;
	        }
	    }

	    private void deleteTestFile(String filePath) {
	        File file = new File(filePath);
	        if (file.exists()) {
	            file.delete();
	        }
	    }

}
