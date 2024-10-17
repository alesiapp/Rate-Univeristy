import java.awt.Container;
import java.awt.GridLayout;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class joinCourse extends JFrame implements ActionListener{
	private  ArrayList<Course> courses=new ArrayList<Course>();
	private  ArrayList<Student> student=new ArrayList<Student>();
	Student s;
	JButton button[];
	joinCourse(Student s){
		lexoKurset();
		this.s=s;
		button=new JButton[courses.size()];
		this.setLayout(new GridLayout(0,3));
		addButtons();
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(500,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<button.length;i++) {
			if(e.getSource()==button[i]) {
				try { 
					System.out.println(isRegistered(courses.get(i)));
					boolean c=shtoKurs(button[i].getText());
					System.out.println(button[i].getText());
					
					//System.out.println(courses.get(i).getName());
					if(c==true) {
						JOptionPane.showMessageDialog(null, "You are now registered in the course   ");
					}
					else
						JOptionPane.showMessageDialog(null, "You are already registered in the course");
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
	
	

	
	public boolean shtoKurs(String name) throws IOException {
		 lexoStudentet();
		lexoKurset();
		 for(int i=0;i<student.size();i++) {
			 if(student.get(i).getEmail().equals(s.getEmail())&&student.get(i).getPassword().equals(s.getPassword())) {
		     for(int j=0;j<courses.size();j++) {
			if(courses.get(j).getName().equalsIgnoreCase(name)) {
				if(!isRegistered(courses.get(j))) {
					courses.get(j).setStudentNr(courses.get(j).getStudentNr()+1);
				student.get(i).getKurset().add(courses.get(j));
				ruajStudent();
				return true;
				}
			}
			
		}
		  
		     }
	}
		 return false;
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
	public boolean isRegistered(Course course) {
		for(Student x:student) {
			if(x.getEmail().equals(s.getEmail())) {
				for(Course c:x.getKurset()) {
					if(c.getName().equals(course.getName()))
						return true;
				}
				break;
			}
		}
		return false;
		
	}
	public void addButtons() {
		for(int i=0;i<courses.size();i++) {
			String k=courses.get(i).getName();
			 button[i]=new JButton(k);
			 button[i].addActionListener(this);
				this.add(button[i]);
				
			}
	}

	public void setStudentList(List<Student> studentList) {
		student=(ArrayList<Student>) studentList;
		
	}

	public void setCourseList(List<Course> courseList) {
		courses=(ArrayList<Course>) courseList;
		
	}

	public ArrayList getStudent() {
		// TODO Auto-generated method stub
		return student;
	}

	public ArrayList getCourses() {
		// TODO Auto-generated method stub
		return courses;
	}
	public void setCourses(ArrayList a) {
		courses=a;
	}
	public void setStudent(ArrayList a) {
		student=a;
	}
	
	
	
}
