import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SimpleCalendarGUI extends JFrame {
    private static final String prove = null;
	private JLabel monthLabel;
    private JLabel yearLabel; 
    private JPanel calendarPanel;
   // private int nextButtonClickCount = 0; 
    private ArrayList<Course> courses = new ArrayList<>();
    private Map<Date, ArrayList<Course>> kursePerDaten;
  

    public SimpleCalendarGUI() {
    	
    	lexoKurset();
    	
    	 kursePerDaten = new HashMap<>();
    	
        JPanel mainPanel = new JPanel(new BorderLayout());

        monthLabel = new JLabel("", JLabel.CENTER);
        yearLabel = new JLabel("", JLabel.CENTER);
        updateMonthLabel();

        calendarPanel = new JPanel(new GridLayout(0, 7, 5, 5));

       JButton prevButton = new JButton("<<");
        prevButton.addActionListener(new ActionListener() {
         
            public void actionPerformed(ActionEvent e) {
                navigateMonth(-1);
            }
        });

        JButton nextButton = new JButton(">>");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateMonth(1);
            }
        });

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(prevButton, BorderLayout.WEST);
        headerPanel.add(monthLabel, BorderLayout.CENTER);
        headerPanel.add(yearLabel, BorderLayout.EAST); 
        headerPanel.add(nextButton, BorderLayout.EAST);
       // headerPanel.add(localDateButton, BorderLayout.SOUTH);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(calendarPanel, BorderLayout.CENTER);

        this.add(mainPanel);
        this.setTitle("Kalendar i Thjeshtë");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        showCurrentMonth();
    }

    
    private void updateMonthLabel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy");
        monthLabel.setText(dateFormat.format(new Date()));
    }

    private void navigateMonth(int delta) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, delta);
        monthLabel.setText(new SimpleDateFormat("MMMM yyyy").format(calendar.getTime()));
        showMonth(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
    }
    
    private final String getMonthName(int month) {
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month - 1];
    }

    private void showMonth(int month, int year) {
        calendarPanel.removeAll();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat dayFormat = new SimpleDateFormat("E");

        String[] daysOfWeek = {"Sun","Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : daysOfWeek) {
            JLabel dayLabel = new JLabel(day, JLabel.CENTER);
            dayLabel.setFont(new Font("Arial", Font.BOLD, 12));
            calendarPanel.add(dayLabel);
        }

        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        for (int i = 1; i < firstDayOfWeek-1; i++) {
            calendarPanel.add(new JLabel(""));
        }

        for (int i = 1; i <= daysInMonth; i++) {
            JButton dayButton = new JButton(String.valueOf(i));

            List<Course> kurset = kursePerDaten.getOrDefault(calendar.getTime(), new ArrayList<>());

            dayButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();
                    int clickedDay = Integer.parseInt(clickedButton.getText());

                    Calendar clickedDate = Calendar.getInstance();
                    clickedDate.setTime(calendar.getTime());
                    clickedDate.set(Calendar.DAY_OF_MONTH, clickedDay);

                    int month = clickedDate.get(Calendar.MONTH);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    String formattedDate = String.format("%d/%s/%02d", clickedDate.get(Calendar.YEAR), getMonthName(month), clickedDay);

                    //JOptionPane.showMessageDialog(null, "Keni klikuar në datën: " + formattedDate);
                    showKursInfo(courses, formattedDate);
                }
                
              
            });

            if (dayFormat.format(calendar.getTime()).equals("Mon")) {
                dayButton.setForeground(Color.RED); 
            }
            
            
            if (i == Calendar.getInstance().get(Calendar.DAY_OF_MONTH) &&
                    month == Calendar.getInstance().get(Calendar.MONTH) &&
                    year == Calendar.getInstance().get(Calendar.YEAR)) {
                dayButton.setBackground(Color.BLUE); }

            calendarPanel.add(dayButton);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        this.revalidate();
        this.repaint();
    }

    private void showCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        showMonth(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
    }

    public static void main(String[] args) {
        SimpleCalendarGUI kalendari = new SimpleCalendarGUI();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        String dateString = "2024/01/01";
        String dateString1 = "2024/01/15";

        try {
            
            Date date = dateFormat.parse(dateString);
            Date date1 = dateFormat.parse(dateString1);

          
            Course course1 = new Course("Kurs1", "Kurs1", "kurs1", "10:00", "101", date);
            Course course2 = new Course("Kurs2", "Kurs2", "kurs2", "10:00", "101", date);
            Course course3 = new Course("Kurs3", "Kurs3", "kurs3", "10:00", "101", date1);
           
         //   kalendari.courses.add(course1);
        //    kalendari.courses.add(course2);
           // kalendari.courses.add(course3);
            kalendari.showCurrentMonth();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
             
    

    private void showKursInfo(ArrayList<Course> courses2, String formattedDate) {
    	//lexoKurset();
    	/*for (Course kurs : courses2) {
    		System.out.println(kurs.getName());
    		System.out.println("Data e kursit " + kurs.getDate());
    	}*/
        if (courses2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nuk ka kurse për këtë datë: " + formattedDate);
        } else {
            StringBuilder message = new StringBuilder( "Kurset per daten: " +formattedDate + ":\n");

            for (Course kurs : courses2) {
            	System.out.println("Data e kursit " + kurs.getDate());
                String kursDate = formatDate(kurs.getDate(), "yyyy/MMMM/dd");
                
                //System.out.println("Data e kursit " + kursDate);
                //System.out.println("Data e formatuar " + formattedDate);
                

                if (Objects.equals(kursDate, formattedDate)) {
                    message.append("- ").append(kurs.getName()).append("\n");
                } else {
                	message.append("-");
                }
            }

            JOptionPane.showMessageDialog(null, message.toString());
        }
    }


    private String formatDate(Date string, String pattern) {
        if (string == null) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(string);
    }
    
    public void lexoKurset() {
		boolean cont = true;
        try (ObjectInputStream lexo = new ObjectInputStream(new FileInputStream("Kurs.txt"))) {
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
    
	private void showKursInfo(List<Course> kurset) {
	
		
        if (kurset.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nuk ka kurse për këtë datë.");
        } else {
            StringBuilder message = new StringBuilder("Kurset për këtë datë:\n");
            for (Course kurs : kurset) {
                message.append("- ").append(kurs.getName()).append("\n");
            }
            JOptionPane.showMessageDialog(null, message.toString());
        }
    }


	public ArrayList<Course> getCourses() {
		// TODO Auto-generated method stub
		return courses;
	}public void setCourses(ArrayList<Course> c1) {
		// TODO Auto-generated method stub
		c1=courses;
	}
}
	