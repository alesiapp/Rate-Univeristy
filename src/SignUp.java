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

public class SignUp extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private ArrayList<Student> student=new ArrayList<Student>();
	JLabel label=new JLabel("SIGN UP ");
	JLabel label1=new JLabel("E-mail:");
	JLabel label2=new JLabel("Password:");
	JLabel label3=new JLabel("Confirm password");
	JButton signup=new JButton("SIGN UP");
	JTextField text1=new JTextField();
	JPasswordField text2=new JPasswordField();
	JPasswordField text3=new JPasswordField();
	JPanel panel=new JPanel();
	Border border=BorderFactory.createLineBorder(new Color(130,130,190));
	SignUp()
	{
		
		this.add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
	
		this.setSize(550,500);
		panel.setSize(550,500);	
	    
		
		panel.setLayout(null);
		panel.setBackground(new Color(201,193,231));
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(text1);
		panel.add(text2);
		panel.add(text3);
		panel.add(signup);
		panel.add(label);
		label.setFont(new Font("Serif", Font.PLAIN, 24));
		label3.setFont(new Font("Serif", Font.PLAIN, 13));
		label1.setFont(new Font("Serif", Font.PLAIN, 15));
		label2.setFont(new Font("Serif", Font.PLAIN, 15));
		label.setBounds(200, 0, 150, 50);
		label1.setBounds(50,100,100,50);
		label2.setBounds(50,200,100,50);
		label3.setBounds(50,300,100,50);
		text1.setBorder(border);
		text2.setBorder(border);
		text3.setBorder(border);
		label1.setForeground(Color.white);
		label.setForeground(Color.white);
		label2.setForeground(Color.white);
		label3.setForeground(Color.white);
		text1.setBounds(250,100,200,50);
		text2.setBounds(250,200,200,50);
		text3.setBounds(250,300,200,50);
		signup.setBounds(150,400,200,40);
		signup.setBackground(new Color(130,130,190));
		signup.setForeground(Color.white);
		signup.addActionListener(this);
		lexoStudentet();
	
		
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
	public void ruajStudent() {
		empty();
		
		 try {
	            FileOutputStream ruaj = new FileOutputStream("C:\\Users\\alesi\\Desktop\\Student.txt", true);
	            try (ObjectOutputStream ruajSt = new ObjectOutputStream(ruaj)) {
	                for (Object obj : student){
	                    try {
	                        ruajSt.writeObject(obj);
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
	public String signup(String email,String password,String confirm) throws IOException {
		//lexoStudentet();
		Student std=new Student(email,password);
		if(!password.equals(confirm))
			return "The passwords do not match";
		for(Iterator<Student> iterator=student.iterator();iterator.hasNext();) 
		{
			Student obj=iterator.next();
			if(obj.getEmail().equals(email))
				return "User already exists";
		}
		
		
		
		student.add(std);
		ruajStudent();
		return "Te dhenat u ruajten";
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==signup) {
			if(text1.getText().equals("") || text2.getText().equals("") || text3.getText().equals("")) {
				JOptionPane.showMessageDialog(this,"Fushat nuk duhet te jene bosh");
				new SignUp();
			}
			else
			try {
				String s=signup(text1.getText(),text2.getText(),text3.getText());
				JOptionPane.showMessageDialog(null,s);
				if(s.equals("Te dhenat u ruajten")){
					this.dispose();
					new Login();
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	public void setstudentlist(ArrayList<Student> studentlist) {
		student=studentlist;
	}

	
}
