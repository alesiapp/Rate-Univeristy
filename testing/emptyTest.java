import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class emptyTest {

    @Test
    public void testEmptySuccess() throws IOException {
        LeaveFeedback yourClass = new LeaveFeedback(new Student("",""));

        
        String filePath = "C:\\Users\\alesi\\Desktop\\Student.txt";
        createTestFile(filePath, "Some content");

        
        yourClass.empty();

        
        assertTrue(isFileEmpty(filePath));

       
        deleteTestFile(filePath);
    }

    @Test
    public void testEmptyEmptyFile() throws IOException {
        LeaveFeedback yourClass = new LeaveFeedback(new Student("",""));

      
        String filePath = "C:\\Users\\alesi\\Desktop\\Student.txt";
        createEmptyTestFile(filePath);

      
        yourClass.empty();

        
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
