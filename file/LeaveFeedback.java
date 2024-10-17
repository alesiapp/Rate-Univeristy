import javax.swing.*;
import java.awt.*;
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

public class LeaveFeedback extends JFrame implements ActionListener {
    private ArrayList<Course> courseList = new ArrayList<Course>();
    private ArrayList<Student> studentList = new ArrayList<Student>();
     JPanel panel = new JPanel();
     JLabel feedbackLabel;
     JTextArea feedbackTextArea;
     JLabel ratingLabel;
     JComboBox<Integer> ratingComboBox;
     JButton submitButton;
     JTextArea courseNameTextArea;
     Student student;

    public LeaveFeedback(Student student) {
        this.student = student;
        this.add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(550, 500);
	this.setTitle("LEAVE FEEDBACK");
        this.setLayout(new FlowLayout());
        this.setBackground(new Color(201, 193, 231));


        JLabel label = new JLabel("Choose a course to leave feedback:");
         courseNameTextArea = new JTextArea(1, 20);

        feedbackLabel = new JLabel("Feedback:");
        feedbackTextArea = new JTextArea(10, 30);

        ratingLabel = new JLabel("Rating:");
        Integer[] ratings = {1, 2, 3, 4, 5};
        ratingComboBox = new JComboBox<>(ratings);

        submitButton = createStyledButton("Submit");
        submitButton.addActionListener(this);

       add(label);
        add(courseNameTextArea);
        add(feedbackLabel);
        add(feedbackTextArea);
        add(ratingLabel);
        add(ratingComboBox);
        add(submitButton);
    }

    public void readStudents() {
		
		  
  		boolean cont = true;
          try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("Student.txt"))) {
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
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("courses.txt"))) {
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
	            FileOutputStream save = new FileOutputStream("Student.txt",true);
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
		File file = new File("Student.txt");
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
	                ObjectOutputStream saveCourses = new ObjectOutputStream(save);
	                for (Object o : courseList){
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
		File file = new File("course.txt");
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
	public boolean isRegisteredForCourse(String name,Feedback f) throws IOException{
		readCourses();
		readStudents();
	
		for(int i=0;i<studentList.size();i++) {
			 if(studentList.get(i).getStudentEmail().equals(student.getStudenntEmail())&&studentList.get(i).getStudentPassword().equals(student.getStudentPassword())) {
				
		     for(int j=0;j<studentList.get(i).getCourses().size();j++) {
		    	 if(studentList.get(i).getCourses().get(j).getCourseName().equalsIgnoreCase(name)) {
				  if(studentList.get(i).getCourses().get(j).getCourseFeedback().size()==0) {
		    		 studentList.get(i).getCourses().get(j).getCourseFeedback().add(f);
		    		 for(int k=0;k<courseList.size();k++) {
		    			 if(courseList.get(k).getCourseName().equalsIgnoreCase(name)) {
		    				 courseList.get(k).getCourseFeedback().add(f);
		    			 }
		    		 }
		    		 saveCourse();
		    		 saveStudent();
		    		 return true;
		    	 }
				 return false;
	}}
    }return false;
		}
	

 @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
        	String feedbackText = feedbackTextArea.getText();
    
                int selectedRating = (int) ratingComboBox.getSelectedItem();
 
                Feedback feedback = new Feedback(feedbackText, selectedRating);

            try {
				if (isRegisteredForCourse(courseNameTextArea.getText(),feedback)) {
				    System.out.println("Feedback submitted: " + feedback);

				   

				    JOptionPane.showMessageDialog(this, "Feedback submitted successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				   

				    feedbackTextArea.setText("");
				    courseNameTextArea.setText("");
				} else {
				    JOptionPane.showMessageDialog(this, "You are not registered for this course.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (HeadlessException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }
	public static void setStudents(ArrayList<Student> students) {
		studentList=students;
		
	}

}
