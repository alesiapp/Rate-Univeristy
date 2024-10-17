import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Course implements Serializable {
	private String name;
	private String descripiton;
	private String lecturer;
	private String time;
	private String location;
	private int studentNr;
	private Date date;
	private List<Feedback> feedback;
	
	
	public Course(String name,String descripiton, String lecturer, String time, String location, Date date) {
		super();
		this.name=name;
		this.descripiton = descripiton;
		this.lecturer = lecturer;
		this.time = time;
		this.location = location;
		this.studentNr = 0;
		this.date = (date != null) ? date : new Date();
		this.feedback=new ArrayList();
	
    }
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescripiton() {
		return descripiton;
	}
	@Override
	public String toString() {
		return "Course [name=" + name + ", descripiton=" + descripiton + ", lecturer=" + lecturer + ", time=" + time
				+ ", location=" + location + ", studentNr=" + studentNr + ", feedback=" + feedback + "]";
	}

	public void setDescripiton(String descripiton) {
		this.descripiton = descripiton;
	}
	public String getLecturer() {
		return lecturer;
	}
	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getStudentNr() {
		return studentNr;
	}
	public void setStudentNr(int studentNr) {
		this.studentNr = studentNr;
	}
	public List<Feedback> getFeedback() {
		return feedback;
	}
	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}

	public Date getDate() {
		// TODO Auto-generated method stub
		return date;
	}}
	