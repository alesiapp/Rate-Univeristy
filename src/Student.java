import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private List<Course> kurset;
	
	public Student(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		kurset=new ArrayList<Course>();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Course> getKurset() {
		return kurset;
	}
	public void setKurset(List<Course> kurset) {
		this.kurset = kurset;
	}
	public void joinCourse(Course course) {
		if(!kurset.contains(course))
		kurset.add(course);
		else 
			throw new IllegalArgumentException ("Studenti eshte i regjistruar ne kurs");
	}
	public void dropCourse(Course course) {
		if(kurset.contains(course)) {
			kurset.remove(course);
		}
		else
			throw new IllegalArgumentException ("Studenti nuk eshte i regjistruar ne kurs");
	}

	

}
