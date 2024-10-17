import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CourseDetails extends JFrame implements ActionListener{
	private ArrayList<Course> courses=new ArrayList<Course>();
	JFrame frame=new JFrame();
	JButton button[];
	CourseDetails(){
		
		lexoKurset();
		button=new JButton[courses.size()];
		this.setLayout(new GridLayout());
		for(int i=0;i<courses.size();i++) {
			String s=courses.get(i).getName();
		 button[i]=new JButton(s);
		 button[i].addActionListener(this);
			this.add(button[i]);
			
		}
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(500,500);
		
		
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





	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<courses.size();i++) {
			if(e.getSource()==button[i]) {
				new showDetails(courses.get(i));
			}
		}
		
	}
	
	





	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		return courses;
	}

}
