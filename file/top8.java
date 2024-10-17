import java.awt.Color;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.*;
public class Top8 extends JFrame{
	private ArrayList<Course> courseList=new ArrayList<Course>();
	Object[][] nameOfCourses;
	double rating[];
	JPanel  panel=new JPanel();
	JTable  courseTable;
	JScrollPane scrollPane;
	
	Top8(){
		
		readCourses();
		String[] column= {"COURSES "};
		nameOfCourses=new String[courseList.size()][1];
		rating=new double[courseList.size()];
		fill();
		rendit();
		for(int i=0;i<courseList.size();i++) {
			System.out.println(nameOfCourses[i][0]);
		}
		update();
		courseTable=new JTable(nameOfCourses,column);
		
		scrollPane=new JScrollPane(courseTable);  
		scrollPane=new JScrollPane(courseTable);   
		scrollPane.setBackground(new Color(255,248,220));
		scrollPane.setBounds(30,50,480,150);
		//panel.add(dropButton);
		this.add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(550,500);
		this.setTitle("TOP RATED COURSES");
		panel.setSize(550,500);
		this.add(panel);
		panel.add(scrollPane);
		panel.setLayout(null);
		panel.setBackground(new Color(201,193,231));

	
		
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
	public void fill() {
		int i=0;
		for(Course c:courseList) {
			if(c.getCourseFeedback().size()!=0) {
			int shuma=0,nr=0;
			for(Feedback f:c.getCourseFeedback()) {
				shuma+=f.getStudentRating();
				nr++;
			}
			nameOfCourses[i][0]=c.getCourseName();
			rating[i]=(shuma/nr);
			i++;
		}
			}
	}
	
	public void rendit() {
	    int n = rating.length;

	    for (int i = 0; i < n - 1; i++) {
	        int maxIndex = i;

	        for (int j = i + 1; j < n; j++) {
	            if (rating[j] > rating[maxIndex]) {
	                maxIndex = j;
	            }
	        }

	        // Swap the maximum element with the element at position i
	        double tempRating = rating[i];
	        String tempNames = (String) nameOfCourses[i][0];

	        rating[i] = rating[maxIndex];
	        nameOfCourses[i][0] = nameOfCourses[maxIndex][0];

	        rating[maxIndex] = tempRating;
	        nameOfCourses[maxIndex][0] = tempNames;
	    }
	}
	public static void main(String args[]) {
		new Top8();
	}
	public void update() {
		for(int i=0;i<nameOfCourses.length;i++) {
			nameOfCourses[i][0]=nameOfCourses[i][0]+"               "+rating[i];
		}
	}
	
}
