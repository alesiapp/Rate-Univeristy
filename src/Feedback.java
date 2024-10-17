import java.io.Serializable;
import java.time.LocalDate;

public class Feedback implements Serializable {
	private String studentFeedback;
	private int rating;
	private LocalDate date;
	
	
	
	public Feedback(String studentFeedback, int rating) {
		super();
		this.studentFeedback = studentFeedback;
		this.rating = rating;
		date=LocalDate.now();
	}
	public String getStudentFeedback() {
		return studentFeedback;
	}
	public void setStudentFeedback(String studentFeedback) {
		this.studentFeedback = studentFeedback;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Feedback [studentFeedback=" + studentFeedback + ", rating=" + rating + ", date=" + date + "]";
	}
	
	
	

}
