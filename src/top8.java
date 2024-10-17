import java.awt.Color;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
public class top8 extends JFrame{
	private ArrayList<Course> courses=new ArrayList<Course>();
	String[][] emrat;
	double rating[];
	JPanel  panel=new JPanel();
	JTable kurset;
	JScrollPane sp;
	
	top8(){
		
		lexoKurset();
		String[] column= {"KURSET "};
		emrat=new String[courses.size()][1];
		rating=new double[courses.size()];
		fill();
		rendit(emrat,rating);
		
		update();
		kurset=new JTable(emrat,column);
		
		sp=new JScrollPane(kurset);  
		sp=new JScrollPane(kurset);   
		sp.setBackground(new Color(255,248,220));
		sp.setBounds(30,50,480,150);
		//panel.add(dropButton);
		this.add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(550,500);
		panel.setSize(550,500);
		this.add(panel);
		panel.add(sp);
		panel.setLayout(null);
		panel.setBackground(new Color(201,193,231));

	
		
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
	public void fill() {
		int i=0;
		for(Course c:courses) {
			if(c.getFeedback().size()!=0) {
			int shuma=0,nr=0;
			for(Feedback f:c.getFeedback()) {
				shuma+=f.getRating();
				nr++;
			}
			emrat[i][0]=c.getName();
			rating[i]=(shuma/nr);
			i++;
		}
			}
	}
	
	public void rendit(String [][] emrat,double rating[]) {
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
	        String tempEmrat = (String) emrat[i][0];

	        rating[i] = rating[maxIndex];
	        emrat[i][0] = emrat[maxIndex][0];

	        rating[maxIndex] = tempRating;
	        emrat[maxIndex][0] = tempEmrat;
	    }
	}
	
	public void update() {
		for(int i=0;i<emrat.length ;i++) {
			emrat[i][0]=emrat[i][0]+"               "+rating[i];
		}
	}

	
}
