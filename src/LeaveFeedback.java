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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LeaveFeedback extends JFrame implements ActionListener {
	private static  ArrayList<Course> courses=new ArrayList<Course>();
	private static  ArrayList<Student> student=new ArrayList<Student>();
    JPanel panel = new JPanel();
    JLabel feedbackLabel;
    JTextArea feedbackTextArea;
    JLabel ratingLabel;
    JComboBox<Integer> ratingComboBox;
    JButton submitButton;
    JTextArea courseNameTextArea;
    // Flag to indicate registration status
    Student s;
    public LeaveFeedback(Student s) {
    	this.s=s;
        this.add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(550, 500);
        this.setLayout(new FlowLayout());
        this.setBackground(new Color(201, 193, 231));

   

        JLabel label = new JLabel("Jepni emrin e kursit qe deshironi te lini feedback:");
        courseNameTextArea = new JTextArea(1, 20);

        feedbackLabel = new JLabel("Feedback:");
        feedbackTextArea = new JTextArea(5, 20);

        ratingLabel = new JLabel("Rating:");
        Integer[] ratings = {1, 2, 3, 4, 5};
        ratingComboBox = new JComboBox<>(ratings);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        add(label);
        add(courseNameTextArea);
        add(feedbackLabel);
        add(feedbackTextArea);
        add(ratingLabel);
        add(ratingComboBox);
        add(submitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
        	String feedbackText = feedbackTextArea.getText();
    
                int selectedRating = (int) ratingComboBox.getSelectedItem();
 
                Feedback feedback = new Feedback(feedbackText, selectedRating);

            // Check registration status before allowing feedback submission
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
	public boolean isRegisteredForCourse(String name,Feedback f) throws IOException{
		lexoKurset();
		lexoStudentet();
	
		for(int i=0;i<student.size();i++) {
			 if(student.get(i).getEmail().equals(s.getEmail())&&student.get(i).getPassword().equals(s.getPassword())) {
				
		     for(int j=0;j<student.get(i).getKurset().size();j++) {
		    	 if(student.get(i).getKurset().get(j).getName().equalsIgnoreCase(name)) {
		    		 if(student.get(i).getKurset().get(j).getFeedback().size()==0) {
		    		 student.get(i).getKurset().get(j).getFeedback().add(f);
		    		 ruajStudent();
		    		 for(int k=0;k<courses.size();k++) {
		    			 if(courses.get(k).getName().equalsIgnoreCase(name)) {
		    				 courses.get(k).getFeedback().add(f);
		    				 ruajKurset();
		    			 }
		    		 }
		    		
		    		
		    		 return true;
		    	 }
		    		 return false;
		    	 }
	}
		     }
    }
		return false;
		}

	public static void setStudents(ArrayList<Student> students) {
		student=students;
		
	}

	public static void setCourses(List<Course> courses2) {
		// TODO Auto-generated method stub
		courses=(ArrayList<Course>) courses2;
	}}