import java.awt.Color;
import java.awt.Font;
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
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

public class Login  extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Student> student=new ArrayList<Student>();
	private String emriStudentit;

	private JPanel  panel=new JPanel();
	
	
	JScrollPane sp;
	Border border=BorderFactory.createLineBorder(new Color(130,130,190));
	JLabel label=new JLabel("LOGIN  SYSTEM");
	JLabel label1=new JLabel("E-MAIL:");
	JLabel label2=new JLabel("PASSWORD:");
	JLabel label4=new JLabel();
	JButton login=new JButton("LOG IN");
	JButton signup=new JButton("SIGN UP");
	JTextField text1=new JTextField();
	JPasswordField text2=new JPasswordField();
	Student s;
	Login(){
		this.add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
	
		this.setSize(550,500);
		panel.setSize(550,500);	
	    
		
		panel.setLayout(null);
		panel.setBackground(new Color(201,193,231));
		panel.add(label);
		panel.add(text1);
		panel.add(text2);
		panel.add(login);
		panel.add(signup);
		panel.add(label1);
		panel.add(label2);
	
		label.setBounds(180,0,400,50);
		label.setFont(new Font("Serif", Font.BOLD, 24));
		label1.setFont(new Font("Serif", Font.PLAIN, 15));
		label2.setFont(new Font("Serif", Font.PLAIN, 15));
		
	
		label1.setBounds(50,50,150,50);
		
		label2.setBounds(50, 200,150,50 );
		text1.setBounds(250, 50, 250, 50);
		text1.setBorder(border);
		text2.setBorder(border);
		text2.setBounds(250, 200, 250, 50);
		login.setBounds(50,350,150,50);
		signup.setBounds(300,350,150,50);
		login.setBackground(new Color(130,130,190));
		signup.setBackground(new Color(130,130,190));
		label1.setForeground(Color.white);
		label.setForeground(Color.white);
		login.setForeground(Color.white);
		signup.setForeground(Color.white);
		label2.setForeground(Color.white);
		login.addActionListener(this);
		signup.addActionListener(this);
		
	
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(login)) {
			if(text1.getText()==null || text2.getText()=="") {
				JOptionPane.showMessageDialog(this,"Fushat nuk duhet te jene bosh");
			}
			else
			JOptionPane.showMessageDialog(null,(login(emriStudentit=text1.getText(), text2.getText())));
			if(login(text1.getText(), text2.getText()).equals("Login was succesfull")) {
				this.dispose();
				s=new Student(emriStudentit,text2.getText());
	        new Menu(s);
			}
			
				
			
		}
		else if(e.getSource().equals(signup)) {
			new SignUp();
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
  
	
	
	public String login(String email,String password) {
		lexoStudentet();
		Student std=new Student(email,password);
	
		for(Iterator<Student> iterator=student.iterator();iterator.hasNext();) {
			Student obj=iterator.next();
			if(obj.getEmail().equals(email)) {
				if(obj.getPassword().equals(password))
					return "Login was succesfull";
				else
					return "Incorrect password";
			}
				
		}
		
	
		return "User does not exist";
	
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
	
	public static void  main (String []args) {
		
		Login gui=new Login();
	}
	public List studentlist() {
		return student;
	}

	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return student;
	}
	
	

}
