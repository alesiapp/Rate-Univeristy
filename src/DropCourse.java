import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.html.HTMLDocument.Iterator;

public class DropCourse extends JFrame implements ActionListener{
	private ArrayList<Course> courses=new ArrayList<Course>();
	private ArrayList<Student> student=new ArrayList<Student>();
	Student s;
	JButton button[];

	DropCourse(Student s){
		this.s=s;
		lexoKurset();
		 lexoStudentet();
		button=new JButton[courses.size()];
		this.setLayout(new GridLayout());
		addButtons();
		this.setVisible(true);
		this.setSize(550,500);
		this.setResizable(false);

	


	
		
			
	}
	
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<button.length;i++) {
		if(e.getSource()==button[i]) {
        String courseNameToDrop = button[i].getText();

        try {
        boolean dropped = dropCourse( courseNameToDrop);

        if (dropped) {
            JOptionPane.showMessageDialog(null, "Course dropped successfully");
            //ruajKurset();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Course not found or could not be dropped");
        }} catch(HeadlessException e1) {
            e1.printStackTrace();
        }
        }
		}
    }
	
	public void lexoStudentet() {
		
		  
		boolean cont = true;
        try (ObjectInputStream lexo = new ObjectInputStream(new FileInputStream("C:\\Users\\alesi\\Desktop\\Student.txt"))) {
            while(cont){
                  Object obj=null;
                try {
                    obj = lexo.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
	            }catch (EOFException e) {
	            	e.getMessage();
	            	break;
	            }
                
                  if(obj != null)
                     student.add((Student) obj);
                  else
                     cont = false;
               }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

      
	}
  
	
	public void lexoKurset() {
		boolean cont = true;
        try (ObjectInputStream lexo = new ObjectInputStream(new FileInputStream("C:\\Users\\alesi\\Desktop\\Kurs.txt"))) {
            while(cont){
                  Object obj=null;
                try {
                    obj = lexo.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
	            }catch (EOFException e) {
	            	e.getMessage();
	            	break;
	            }
                
                  if(obj != null)
                     courses.add((Course) obj);
                  else
                     cont = false;
               }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

	}
	public void ruajStudent() throws IOException {
		
		empty();
		
	
	  
		 try {
	            FileOutputStream ruaj = new FileOutputStream("C:\\Users\\alesi\\Desktop\\Student.txt",true);
	            try {
	                ObjectOutputStream ruajShofer = new ObjectOutputStream(ruaj);
	                for (Object obj : student){
	                    try {
	                        ruajShofer.writeObject(obj);
	                       // System.out.println("saved");
	                    } catch (NotSerializableException e) {
	                        System.out.println("An object was not serializable, it has not been saved.");
	                        e.printStackTrace();
	                    }
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
		
	}
	
	

	public void empty() {
		File file = new File("C:\\Users\\alesi\\Desktop\\Student.txt");
		try {
	        PrintWriter writer = new PrintWriter(file);
	        writer.print("");
	        writer.flush();
	        writer.close();

	    }catch (Exception e)
	    {
	    	e.printStackTrace();
	    }
	}
	
	public void ruajKurset() {
		emptyCourses();
		 try {
	            FileOutputStream ruaj = new FileOutputStream("C:\\Users\\alesi\\Desktop\\Kurs.txt", true);
	            try {
	                ObjectOutputStream ruajKurset = new ObjectOutputStream(ruaj);
	                for (Object o : courses){
	                    try {
	                        ruajKurset.writeObject(o);
	                       
	                    } catch (NotSerializableException e) {
	                        System.out.println("An object was not serializable, it has not been saved.");
	                        e.printStackTrace();
	                    }
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	}
	public void emptyCourses() {
		File file = new File("C:\\Users\\alesi\\Desktop\\Kurs.txt");
		try {
	        PrintWriter writer = new PrintWriter(file);
	        writer.print("");
	        writer.flush();
	        writer.close();

	    }catch (Exception e)
	    {
	    	e.printStackTrace();
	    }
	}
	public static void main(String args[]) {
		//DropCourse drop=new DropCourse(null);
	}
	

	boolean dropCourse(String courseNameToDrop) {
		lexoStudentet();
	    lexoKurset();
	    for (Student s : student) {
	        if (s.getEmail().equals(s.getEmail())) {
	            List<Course> studentCourses = s.getKurset();
	            Course courseToRemove = null;

	            for (Course course : studentCourses) {
	                if (course.getName().equalsIgnoreCase(courseNameToDrop)) {
	                    courseToRemove = course;
	                    course.setStudentNr(course.getStudentNr()-1);
	                    break;
	                }
	            }

	            if (courseToRemove != null) {
	                studentCourses.remove(courseToRemove);
	                try {
	                    ruajStudent();
	                   
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	                return true; // Course dropped successfully
	            }
	        }
	    }
	    return false;
	}
	
	public void addButtons() {
		
		for (Student student : student) {
	        if (student.getEmail().equals(s.getEmail())) {
	            for (int i = 0; i < student.getKurset().size(); i++) {
	                button[i] =new JButton( student.getKurset().get(i).getName());
	                button[i].addActionListener(this);
	                this.add(button[i]);
	            }
	            break; // No need to continue searching once the student is found
	        }
	    }
	}
	public void setStudent(ArrayList<Student> st) {
		student=st;
	}

	public ArrayList<Student> getStudents() {
		// TODO Auto-generated method stub
		return student;
	}
	public void setCourses(ArrayList<Course> cs) {
		courses=cs;
	}
	public ArrayList<Course> getCourses() {
		// TODO Auto-generated method stub
		return courses;
	}
	
}



