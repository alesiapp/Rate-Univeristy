import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Course implements Serializable {
	private String courseName;
	private String courseDescripiton;
	private String courseLecturer;
	private String courseTime;
	private String courseLocation;
	private int studentNumber;
	private Date courseDate;
	private List<Feedback> courseFeedback;
	
	
	public Course(String courseName,String courseDscripiton, String courseLecturer, String courseTime, String courseLocation, Date courseDate) {
	      super();
		this.courseName=courseName;
		this.courseDescripiton = courseDescripiton;
		this.courseLecturer = courseLecturer;
		this.courseTime = courseTime;
		this.courseDate=courseDate;
		this.courseLocation = courseLocation;
		this.studentNumber = 0;
		this.courseFeedback=new ArrayList();
	
    }
	
	
	public String getCourseName() {
		return courseName;
	}



	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}



	public String getCourseDescripiton() {
		return courseDescripiton;
	}



	public void setCourseDescripiton(String courseDescripiton) {
		this.courseDescripiton = courseDescripiton;
	}



	public String getCourseLecturer() {
		return courseLecturer;
	}



	public void setCourseLecturer(String courseLecturer) {
		this.courseLecturer = courseLecturer;
	}



	public String getCourseTime() {
		return courseTime;
	}



	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}



	public Date getCourseDate() {
		return courseDate;
	}



	public void setCourseDate(Date courseDate) {
		this.courseDate = courseDate;
	}



	public String getCourseLocation() {
		return courseLocation;
	}



	public void setCourseLocation(String courseLocation) {
		this.courseLocation = courseLocation;
	}



	public int getStudentNumber() {
		return studentNumber;
	}



	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}



	public List<Feedback> getCourseFeedback() {
		return courseFeedback;
	}



	public void setCourseFeedback(List<Feedback> courseFeedback) {
		this.courseFeedback = courseFeedback;
	}



	@Override
	public String toString() {
		return "Course [Name=" + courseName + ", descripiton=" + courseDescripiton + ", lecturer=" + courseLecturer + ", time=" + courseTime
				+"date="+courseDate+ ", location=" + courseLocation + ", Student Number=" + studentNumber + ", feedback=" + courseFeedback + "]";
	}
}
