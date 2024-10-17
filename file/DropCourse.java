
import java.awt.Color;
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
import java.awt.GridLayout;

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
	private ArrayList<Course> courseList=new ArrayList<Course>();
	private ArrayList<Student> studentList=new ArrayList<Student>();
	
	JButton button[];

	Student student;
	
	DropCourse(Student student){
		this.student=student;
		readCourses();
		 readStudents();
		button=new JButton[courseList.size()];
		this.setLayout(new GridLayout());
		addButtons();
		this.setVisible(true);
		this.setSize(550,500);
		this.setResizable(false);
		this.setTitle("DROP COURSE");

			
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
	
	public void readStudents() {
		
		  
		boolean cont = true;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("students.txt"))) {
            while(cont){
                  Object obj=null;
                try {
                    obj = input.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
	            }catch (EOFException e) {
	            	e.getMessage();
	            	break;
	            }
                
                  if(obj != null)
                     studentList.add((Student) obj);
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
  
	
	public void readCourses() {
		boolean cont = true;
        try (ObjectInputStream input= new ObjectInputStream(new FileInputStream("courses.txt"))) {
            while(cont){
                  Object obj=null;
                try {
                    obj = input.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
	            }catch (EOFException e) {
	            	e.getMessage();
	            	break;
	            }
                
                  if(obj != null)
                     courseList.add((Course) obj);
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
	public void saveStudent() throws IOException {
		
		emptyStudent();
		
	
	  
		 try {
	            FileOutputStream save = new FileOutputStream("students.txt",true);
	            try {
	                ObjectOutputStream saveStd = new ObjectOutputStream(save);
	                for (Object obj : studentList){
	                    try {
	                        saveStd.writeObject(obj);
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
	
	

	public void emptyStudent() {
		File file = new File("students.txt");
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
	
	public void saveCourse() {
		emptyCourses();
		 try {
	            FileOutputStream save = new FileOutputStream("courses.txt", true);
	            try {
	                ObjectOutputStream saveCourses= new ObjectOutputStream(save);
	                for (Object o : courseList){
	                    try {
	                        saveCourses.writeObject(o);
	                       
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
		File file = new File("courses.txt");
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
	

	private boolean dropCourse(String courseNameToDrop) {
             readStudents();
	     readCourses();
	    for (Student s : studentList) {
	        if (s.getStudentEmail().equals(s.getStudentEmail())) {
	            List<Course> studentCourses = s.getCourses();
	            Course courseToRemove = null;

	            for (Course course : studentCourses) {
	                if (course.getCourseName().equalsIgnoreCase(courseNameToDrop)) {
	                    courseToRemove = course;
				 course.setStudentNumber(course.getStudentNumber()-1);
	                    break;
	                }
	            }

	            if (courseToRemove != null) {
	                studentCourses.remove(courseToRemove);
	                try {
	                    saveStudent();
	                   
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	                return true; // Course dropped successfully
	            }
	        }
	    }
	    return false;
	}
	public void setStudent(ArrayList<Student> st) {
		studentList=st;
	}

	public ArrayList<Student> getStudents() {
		// TODO Auto-generated method stub
		return studentList;
	}
	public void setCourses(ArrayList<Course> cs) {
		courseList=cs;
	}
	public ArrayList<Course> getCourses() {
		// TODO Auto-generated method stub
		return courseList;
	}
	public void addButtons() {
		
		for (Student std : studentList) {
	        if (std.getStudentEmail().equals(student.getStudentEmail())) {
	            for (int i = 0; i < std.getCourses().size(); i++) {
	                button[i] =new JButton( std.getCourses().get(i).getCourseName());
	                button[i].addActionListener(this);
	                this.add(button[i]);
	            }
	            break; // No need to continue searching once the student is found
	        }
	    }
	}
}

