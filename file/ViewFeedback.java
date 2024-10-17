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
import java.util.Iterator;
import java.util.List;

public class ViewFeedback extends JFrame implements ActionListener{
	private List<Course> courseList=new ArrayList();
    private JTable reviewTable;
    private String[][] data;
    private JScrollPane scrollPane;
    private JLabel label;
    private JTextField textfield;
    private JButton button;
    private JPanel panel=new JPanel();
    

    public ViewFeedback() {
    	readCourses();
        deleteOldFeedbacks();
    	button=new JButton("View");
    	label=new JLabel("Choose a course");
    	textfield=new JTextField();
    	label.setBounds(50,50,100,100);
    	textfield.setBounds(200,50,100,100);
    	button.setBounds(400,200,100,100);
    	button.addActionListener(this);
        panel.add(button);
    	panel.add(label);
    	panel.add(textfield);
        this.setTitle("COURSE FEEDBACKS"); 
        panel.setSize(800, 600);
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(panel); 
        this.setVisible(true);
        this.setResizable(false);
       
    }
    private void initializeUI() {
    	
    
    	
       
        Course currentCourse=null;
        for (int i = 0; i < courseList.size(); i++) {
        	if(courseList.get(i).getCourseName().equalsIgnoreCase(textfield.getText()))
        	currentCourse = courseList.get(i);
         //   System.out.println(currentCourse.getName());
 }
        
            data = new String[currentCourse.getCourseFeedback().size()][4]; // Assuming Feedback has 3 attributes + courseName
        //    System.out.println(currentCourse.getCourseFeedback().size());
            
            for (int j = 0; j < currentCourse.getCourseFeedback().size(); j++) {
                data[j][0] = currentCourse.getCourseName();
       //         System.out.println(data[j][0]);
                data[j][1] = currentCourse.getCourseFeedback().get(j).getStudentFeedback();
       //        System.out.println(data[j][1]);
                data[j][2] = String.valueOf(currentCourse.getCourseFeedback().get(j).getStudentRating());
       //         System.out.println(data[j][2]);
            }
       
        String[] columnNames = {"Course Name", "Feedback Text", "Rating"};

        
        reviewTable = new JTable(data, columnNames);
        //vleresimetTable.setEnabled(false);

        scrollPane = new JScrollPane(reviewTable);
        scrollPane.setBounds(0, 0, 800, 680);
        panel.add(scrollPane);

      
      
    }
	


    public void deleteOldFeedbacks() {
    	LocalDate oneYearAgo=LocalDate.now().minusYears(1);
        for (Course s : courseList) {
            Iterator<Feedback> iterator = s.getCourseFeedback().iterator();
            while (iterator.hasNext()) {
                Feedback f = iterator.next();
                if (f.getFeedbackDate().isBefore(oneYearAgo)) {
                    iterator.remove();
                    saveCourse();
                }
            }
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
	
	

    public static void main(String[] args) {
        new ViewFeedback();
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==button) {
			panel.remove(button);
			panel.remove(label);
			panel.remove(textfield);
			 initializeUI();
		}
	}
	public void setCourseList(List<Course> courses) {
		// TODO Auto-generated method stub
		courseList=courses;
	}
}
