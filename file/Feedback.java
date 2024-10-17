
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Feedback implements Serializable {
	private String studentFeedback;
	private int studentRating;
	private LocalDate feedbackDate;
	private ArrayList<Course> courseList=new ArrayList<Course>();
	private String courseName;
	
	
	public Feedback(String studentFeedback, int studentRating) {
		super();
		this.studentFeedback = studentFeedback;
		this.studentRating = studentRating;
		
		feedbackDate=LocalDate.now();
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName=courseName;
	}
	public String getStudentFeedback() {
		return studentFeedback;
	}
	public void setStudentFeedback(String studentFeedback) {
		this.studentFeedback = studentFeedback;
	}
	public int getStudentRating() {
		return studentRating;
	}
	public void setStudentRating(int studentRating) {
		this.studentRating = studentRating;
	}
	public LocalDate getFeedbackDate() {
		return feedbackDate;
	}
	public void setFeedbackDate(LocalDate feedbackDate) {
		this.feedbackDate = feedbackDate;
	}
	@Override
	public String toString() {
		return "Feedback [studentFeedback=" + studentFeedback + ", rating=" + studentRating + ", date=" + feedbackDate + " ]";
	}
	
	
	
	

}
