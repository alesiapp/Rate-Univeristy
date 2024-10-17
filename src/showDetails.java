import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class showDetails extends JFrame {
	private ArrayList<Course> courses=new ArrayList<Course>();
	JFrame frame = new JFrame();
    Course s;
    private JLabel nameLabel;
    private JLabel startDateLabel;
    private JLabel lecturerLabel;
    private JLabel locationLabel;
    private JLabel timeLabel;
    private JTextArea descriptionTextArea;

    public showDetails(Course s) {
        this.s = s;
        setTitle("Course Information");
        setSize(600, 400);  // Adjusted size for better layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create labels
        nameLabel = new JLabel("Course Name: " + s.getName());
        startDateLabel = new JLabel("Starting Date: " + formatDate(s.getDate()));
        lecturerLabel = new JLabel("Lecturer: " + s.getLecturer());
        locationLabel = new JLabel("Location: " + s.getLocation());
        timeLabel = new JLabel("Time: " + s.getTime());

        // Create JTextArea for description
        descriptionTextArea = new JTextArea(s.getDescripiton());
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);

        // Set layout to null for manual positioning
        setLayout(null);

        // Set positions
        nameLabel.setBounds(10, 10, 400, 20);
        startDateLabel.setBounds(10, 30, 400, 20);
        lecturerLabel.setBounds(10, 50, 100, 20);
        locationLabel.setBounds(10, 70, 100, 20);
        timeLabel.setBounds(170, 70, 100, 20);

        // Wider space for description
        descriptionTextArea.setBounds(10, 100, 570, 200);

        // Add labels to the frame
        add(nameLabel);
        add(startDateLabel);
        add(lecturerLabel);
        add(locationLabel);
        add(timeLabel);
        add(descriptionTextArea);

        this.setVisible(true);
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
	private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }



}
