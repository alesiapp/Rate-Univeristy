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
	private ArrayList<Course> courses=new ArrayList();
    private JTable vleresimetTable;
    private String[][] data;
    private JScrollPane scrollPane;
    private JLabel label;
    private JTextField textfield;
    private JButton button;
    private JPanel panel=new JPanel();
    

    public ViewFeedback() {
    	lexoKurset();
    	button=new JButton("View");
    	label=new JLabel("Zgjidh kursin");
    	textfield=new JTextField();
    	label.setBounds(50,50,100,100);
    	textfield.setBounds(200,50,100,100);
    	button.setBounds(400,200,100,100);
    	button.addActionListener(this);
        panel.add(button);
    	panel.add(label);
    	panel.add(textfield);
        this.setTitle("Vleresimet per Kurset"); 
        panel.setSize(800, 600);
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(panel); 
        this.setVisible(true);
        this.setResizable(false);
       
    }
    private void initializeUI() {
        Course currentCourse=null;
        for (int i = 0; i < courses.size(); i++) {
        	if(courses.get(i).getName().equalsIgnoreCase(textfield.getText()))
        	currentCourse = courses.get(i);
         //   System.out.println(currentCourse.getName());
 }
        
            data = new String[currentCourse.getFeedback().size()][4]; // Assuming Feedback has 3 attributes + courseName
            System.out.println(currentCourse.getFeedback().size());
            
            for (int j = 0; j < currentCourse.getFeedback().size(); j++) {
                data[j][0] = currentCourse.getName();
                System.out.println(data[j][0]);
                data[j][1] = currentCourse.getFeedback().get(j).getStudentFeedback();
                System.out.println(data[j][1]);
                data[j][2] = String.valueOf(currentCourse.getFeedback().get(j).getRating());
                System.out.println(data[j][2]);
            }
       
        String[] columnNames = {"Course Name", "Feedback Text", "Rating"};

        
        vleresimetTable = new JTable(data, columnNames);
        //vleresimetTable.setEnabled(false);

        scrollPane = new JScrollPane(vleresimetTable);
        scrollPane.setBounds(0, 0, 800, 680);
        panel.add(scrollPane);

      
      
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
	public void delete() {
	    for (Course course : courses) {
	        Iterator<Feedback> feedbackIterator = course.getFeedback().iterator();
	        while (feedbackIterator.hasNext()) {
	            Feedback feedback = feedbackIterator.next();
	            if (feedback.getDate().isBefore(LocalDate.now())) {
	                feedbackIterator.remove();
	                ruajKurset(); 
	            }
	        }
	    }
	}
	public void setCourseList(List<Course> courses2) {
		// TODO Auto-generated method stub
		courses=(ArrayList<Course>) courses2;
	}
}