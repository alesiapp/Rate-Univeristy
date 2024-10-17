import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

public class SignUpTest{

    @Test
   public void testSuccessfulSignup() throws IOException {
        SignUp yourClass = new SignUp();
       
        ArrayList<Student> list=new ArrayList<>();
        
  
        yourClass.setstudentlist(new ArrayList() );
        String result = yourClass.signup("newuser123@example.com", "password123", "password123");

        assertEquals("Te dhenat u ruajten", result);
    }

    @Test
    public void testUserAlreadyExists() throws IOException {
       SignUp yourClass = new SignUp();
       ArrayList<Student> list=new ArrayList<>();
       list.add(new Student("newuser@example.com", "password123")); 
       yourClass.setstudentlist(list );
       
       

        String result = yourClass.signup("newuser@example.com", "password123", "password123");

        assertEquals("User already exists", result);
    }

    @Test
    public void testPasswordMismatch() throws IOException {
        SignUp yourClass = new SignUp();
        ArrayList<Student> list=new ArrayList<>();
        yourClass.setstudentlist( list );

        yourClass.signup("existinguser@example.com", "password123", "password123");
       
       

        String result = yourClass.signup("existinguser@example.com", "password123", "differentpassword");

        assertEquals("The passwords do not match", result);
    }

  
  
}
