import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String studentEmail;
	private String studentPassword;
	private List<Course> courses;
	
	public Student(String studentEmail, String studentPassword) {
		super();
		this.studentEmail = studentEmail;
		this.studentPassword = studentPassword;
		courses=new ArrayList<Course>();
	}
	
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getStudentPassword() {
		return studentPassword;
	}
	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public void joinCourse(Course course) {
		if(!courses.contains(course))
		courses.add(course);
		else 
			throw new IllegalArgumentException ("Student is enrolled in the course.");
	}
	public void dropCourse(Course course) {
		if(courses.contains(course)) {
			courses.remove(course);
		}
		else
			throw new IllegalArgumentException ("Student is not enrolled in the course.");
	}
}
