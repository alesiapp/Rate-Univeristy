import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Menu extends JFrame implements ActionListener{

	    private JPanel panel;
	    private JLabel label;
	    private JButton joinButton, dropButton, leaveFeedbackButton, viewFeedbackButton
	                    ,Top_8Button, calendarButton, detailsButton,logoutButton;
	    private Student s;
	
	    Menu(Student s) {
	        this.s = s;
	        this.setTitle("SELECT AN OPTION.");
	        this.setSize(550, 600);
	        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	        this.setResizable(false);
	        this.setLocationRelativeTo(null);
	
	        panel = new JPanel(new GridBagLayout());
	        panel.setBackground(new Color(201, 193, 231));
	        this.add(panel);
	
	        label = new JLabel("SELECT AN OPTION.");
	        label.setFont(new Font("Serif", Font.BOLD, 20));
	
	        GridBagConstraints gridBagConstraints = new GridBagConstraints();
	        gridBagConstraints.gridx = 0;
	        gridBagConstraints.gridy = 0;
	        gridBagConstraints.insets = new Insets(20, 0, 20, 0);
	        panel.add(label, gridBagConstraints);
	
	        joinButton = createStyledButton("Join a course");
	        dropButton = createStyledButton("Drop a course");
	        leaveFeedbackButton = createStyledButton("Leave a feedback");
	        viewFeedbackButton = createStyledButton("View feedback");
	        Top_8Button = createStyledButton("View top-rated courses");
	        calendarButton = createStyledButton("See course calendar");
	        detailsButton = createStyledButton("Course details");
	        logoutButton = createStyledButton("Log Out");
	
	        Dimension buttonSize = new Dimension(400, 50); 
	
	        gridBagConstraints.gridy = 1;
	        gridBagConstraints.insets = new Insets(10, 0, 10, 0); 
	        
	        addButton(joinButton, gridBagConstraints, buttonSize);
	        addButton(dropButton, gridBagConstraints, buttonSize);
	        addButton(leaveFeedbackButton, gridBagConstraints, buttonSize);
	        addButton(viewFeedbackButton, gridBagConstraints, buttonSize);
	        addButton(Top_8Button, gridBagConstraints, buttonSize);
	        addButton(calendarButton, gridBagConstraints, buttonSize);
	        addButton(detailsButton, gridBagConstraints, buttonSize);
            addButton(logoutButton, gridBagConstraints, buttonSize);
	
	        panel.setSize(550, 600);
	        this.setVisible(true);
	    }
	
	    private void addButton(JButton button, GridBagConstraints gbc, Dimension size) {
	        gbc.gridy++;
	        button.setPreferredSize(size);
	        panel.add(button, gbc);
	        button.addActionListener(this);
	    }
	
	    private JButton createStyledButton(String text) {
	        JButton button = new JButton(text);
	        
	        button.setFont(new Font("Arial", Font.BOLD, 14));
	        button.setBackground(new Color(130, 130, 190));
	        button.setForeground(Color.white);
	        button.setFocusPainted(false);
	        button.setBorderPainted(false);
	        //button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	
	        button.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseEntered(java.awt.event.MouseEvent evt) {
	                button.setBackground(new Color(160, 160, 210));
	            }
	
	            public void mouseExited(java.awt.event.MouseEvent evt) {
	                button.setBackground(new Color(130, 130, 190));
	            }
	        });

        return button;
    }
	    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==joinButton) {
		new joinCourse(s);
		}
		if(e.getSource()==dropButton) {
			new DropCourse(s);
		}
		if(e.getSource()==leaveFeedbackButton) {
			new LeaveFeedback(s);
		}
		if(e.getSource()==viewFeedbackButton) {
			new ViewFeedback();
		}
		if(e.getSource()==Top_8Button) {
			new top8();
		}
		if(e.getSource()==calendarButton) {
			new SimpleCalendarGUI();
		}
		
		if(e.getSource()==detailsButton) {
			new CourseDetails();
		}
		if(e.getSource()==logoutButton) {
			new Login();
			this.dispose();
		}
	}
}
