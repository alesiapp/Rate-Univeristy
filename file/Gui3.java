import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.ScrollPane;
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
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class JoinCourse extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private  ArrayList<Course> courseList=new ArrayList<Course>();
	private  ArrayList<Student> studentList=new ArrayList<Student>();

	Student student;
	JButton button[];
	JoinCourse(Student student){
		readCourses();
		this.student=student;
		button=new JButton[courseList.size()];
		this.setLayout(new GridLayout(0,3));
		addButtons();
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(500,500);
		this.setTitle("JOIN COURSE");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		
		
	}
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<button.length;i++) {
			if(e.getSource()==button[i]) {
				try {
					System.out.println(isRegistered(courseList.get(i)));
					boolean c=addCourse(button[i].getText());
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
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("Kurs.txt"))) {
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
	
	


	public boolean addCourse(String name) throws IOException {
		 readStudents();
		 readCourses();
		 for(int i=0;i<studentList.size();i++) {
			 if(studentList.get(i).getStudentEmail().equals(student.getStudentEmail())&&studentList.get(i).getStudentPassword().equals(student.getStudentPassword())) {
		     for(int j=0;j<courseList.size();j++) {
			if(courseList.get(j).getCourseName().equalsIgnoreCase(name)) {
				if(!isRegistered(courseList.get(j))) {
				courseList.get(j).setStudentNumber(courseList.get(j).getStudentNumber()+1);
				studentList.get(i).getCourses().add(courseList.get(j));
				saveStudent();
				return true;
				}
			}
			
		}
		     }
	}
		return false;
	}
	public void saveStudent() {
		emptyStudent();
		
		 try {
	            FileOutputStream save = new FileOutputStream("Student.txt", true);
	            try (ObjectOutputStream saveStd = new ObjectOutputStream(save)) {
	                for (Object obj : student){
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
	public boolean isRegistered(Course course) {
		for(Student x:studentList) {
			if(x.getStudentEmail().equals(student.getStudentEmail())) {
				for(Course c:x.getCourses()) {
					if(c.getCourseName().equals(course.getCourseName()))
						return true;
				}
			}
		}
		return false;
		
	}
	public void addButtons() {
		for(int i=0;i<courseList.size();i++) {
			String k=courseList.get(i).getCourseName();
			 button[i]=new JButton(k);
			 button[i].addActionListener(this);
				this.add(button[i]);
				
			}
	}
	public void setStudentList(List<Student> studentList) {
		studentList=(ArrayList<Student>) studentList;
		
	}

	public void setCourseList(List<Course> courseList) {
		courseList=(ArrayList<Course>) courseList;
		
	}
	public ArrayList<Student> getStudents() {
		// TODO Auto-generated method stub
		return studentList;
	}
	
	public ArrayList<Course> getCourses() {
		// TODO Auto-generated method stub
		return courseList;
	}
	
} 
