package university;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {
	//costanti
	public final static int INITIAL_ID = 10000;
	public final static int INITIAL_CID = 10;
	public final static int MAX_STUDENTS = 1000;
	public final static int MAX_COURSES = 50;
	//attributi
	private String name;
	private String rector;
	protected Student ListaStudenti[]= new Student[MAX_STUDENTS];
	protected Student TopStudents[]= new Student[MAX_STUDENTS];
	protected Course ListaCorsi[]= new Course[MAX_COURSES];
	private int nextID=INITIAL_ID;
	private int nextCID=INITIAL_CID;
	protected int nStudents=0;
	/**
	 * Constructor
	 * @param name name of the university
	 */
	public University(String name){
		this.name=name;
	 }
	
	/**
	 * Getter for the name of the university
	 * 
	 * @return name of university
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first
	 * @param last
	 */
	public void setRector(String first, String last){
		rector=first+" "+last;
	}
	
	/**
	 * Retrieves the rector of the university
	 * 
	 * @return name of the rector
	 */
	public String getRector(){
		return rector;
	}
	
	/**
	 * Enrol a student in the university
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * 
	 * @return unique ID of the newly enrolled student
	 */
	public int enroll(String first, String last){ 
		ListaStudenti[nextID-INITIAL_ID]=new Student(nextID,first,last);
		nStudents++;
		return nextID++;
	}
	
	/**
	 * Retrieves the information for a given student
	 * 
	 * @param id the ID of the student
	 * 
	 * @return information about the student
	 */
	public String student(int id){
		Student s = ListaStudenti[id-INITIAL_ID];
		return s.toString();
	}
	
	/**
	 * Activates a new course with the given teacher
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * 
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
	 ListaCorsi[nextCID-INITIAL_CID]=new Course(nextCID,title,teacher);
		return nextCID++;
	}
	
	/**
	 * Retrieve the information for a given course.
	 * 
	 * The course information is formatted as a string containing 
	 * code, title, and teacher separated by commas, 
	 * e.g., {@code "10,Object Oriented Programming,James Gosling"}.
	 * 
	 * @param code unique code of the course
	 * 
	 * @return information about the course
	 */
	public String course(int code){
		Course c=ListaCorsi[code-INITIAL_CID];
		return c.toString();
	}
	
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		Student s = ListaStudenti[studentID-INITIAL_ID];
		Course c = ListaCorsi[courseCode-INITIAL_CID];
		s.enroll(c);
		c.enroll(s);
	 }
	
	/**
	 * Retrieve a list of attendees
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		String result;
		result=ListaCorsi[courseCode-INITIAL_CID].giveListAttendees();
		return result;
	}

	/**
	 * Retrieves the study plan for a student.
	 * 
	 * The study plan is reported as a string having
	 * one course per line (i.e. separated by '\n').
	 * The courses are formatted as describe in method {@link #course}
	 * 
	 * @param studentID id of the student
	 * 
	 * @return the list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		String result;
		result=ListaStudenti[studentID-INITIAL_ID].giveStudyPlan();
		 
		
		//TODO: to be implemented
		return result;
	}
	
}
