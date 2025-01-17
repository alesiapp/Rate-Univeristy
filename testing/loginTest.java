
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;

public class loginTest {

    @Test
    public void testSuccessfulLogin() {
        Login yourClass = new Login();
        yourClass.studentlist().clear();
        yourClass.studentlist().add(new Student("test@example.com", "password123"));
        String result = yourClass.login("test@example.com", "password123");

        assertEquals("Login was succesfull", result);
    }

    @Test
    public void testIncorrectPassword() {
       Login yourClass = new Login();
       yourClass.studentlist().add(new Student("test@example.com", "password123"));
       

        String result = yourClass.login("test@example.com", "wrongpassword");

        assertEquals("Incorrect password", result);
    }

    @Test
    public void testUserDoesNotExist() {
       Login yourClass = new Login();
       yourClass.studentlist().clear();
      

        String result = yourClass.login("nonexistent@example.com", "password123");

        assertEquals("User does not exist", result);
    }

  
}