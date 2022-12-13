package university;

import java.util.logging.Logger;

/**
 * This class is an extended version of the {@Link University} class.
 * 
 *
 */
public class UniversityExt extends University {
	
	private final static Logger logger = Logger.getLogger("University");

	public UniversityExt(String name) {
		super(name);
		// Example of logging
		logger.info("Creating extended university object");
	}

	/**
	 * records the grade (integer 0-30) for an exam can 
	 * 
	 * @param studentId the ID of the student
	 * @param courseID	course code 
	 * @param grade		grade ( 0-30)
	 */
	public void exam(int studentId, int courseID, int grade) {
		ListaStudenti[studentId-INITIAL_ID].cPassed(courseID,grade);
		ListaCorsi[courseID-INITIAL_CID].cPassed(grade);
		logger.info("Student "+studentId+" took an exam in course "+courseID+" with grade "+grade);
	}

	/**
	 * Computes the average grade for a student and formats it as a string
	 * using the following format 
	 * 
	 * {@code "Student STUDENT_ID : AVG_GRADE"}. 
	 * 
	 * If the student has no exam recorded the method
	 * returns {@code "Student STUDENT_ID hasn't taken any exams"}.
	 * 
	 * @param studentId the ID of the student
	 * @return the average grade formatted as a string.
	 */
	public String studentAvg(int studentId) {
		float avg;
		String result;
		avg=ListaStudenti[studentId-INITIAL_ID].studentAvgRes(studentId);
		if(avg!=0) {
			result="Student "+studentId+" : "+avg;
			return result;
		}
		else
			return "Student "+studentId+"hasn't taken any exams";
		
	}
	
	/**
	 * Computes the average grades of all students that took the exam for a given course.
	 * 
	 * The format is the following: 
	 * {@code "The average for the course COURSE_TITLE is: COURSE_AVG"}.
	 * 
	 * If no student took the exam for that course it returns {@code "No student has taken the exam in COURSE_TITLE"}.
	 * 
	 * @param courseId	course code 
	 * @return the course average formatted as a string
	 */
	public String courseAvg(int courseId) {
		float avg;
		String result="";
		avg=ListaCorsi[courseId-INITIAL_CID].courseAvgRes(courseId);
		if(avg!=0) {
			result+="The average for the course "+ListaCorsi[courseId-INITIAL_CID].getTitle()+" is: "+avg;
			return result;
		}
		else {
			result+="No student has taken the exam in"+ListaCorsi[courseId-INITIAL_CID].getTitle();
			return result;
		}
		
	}
	
	/**
	 * Retrieve information for the best students to award a price.
	 * 
	 * The students' score is evaluated as the average grade of the exams they've taken. 
	 * To take into account the number of exams taken and not only the grades, 
	 * a special bonus is assigned on top of the average grade: 
	 * the number of taken exams divided by the number of courses the student is enrolled to, multiplied by 10.
	 * The bonus is added to the exam average to compute the student score.
	 * 
	 * The method returns a string with the information about the three students with the highest score. 
	 * The students appear one per row (rows are terminated by a new-line character {@code '\n'}) 
	 * and each one of them is formatted as: {@code "STUDENT_FIRSTNAME STUDENT_LASTNAME : SCORE"}.
	 * 
	 * @return info of the best three students.
	 */
	public String topThreeStudents() {
		for (int i=0; ListaStudenti[i]!= null; i++) {
			ListaStudenti[i].calcolaPunteggio();
		}
		int count=0;
		int flag=0;
		String result="";
		TopStudents=ListaStudenti;
		int n = nStudents; 
        Student temp = new Student(0,null,null);  
         for(int i=0; i < n; i++){  
                 for(int j=1; j < (n-i); j++){  
                          if(TopStudents[j-1].calcolaPunteggio() > TopStudents[j].calcolaPunteggio()){ 
                                 //swap elements  
                                 temp = TopStudents[j-1];  
                                 TopStudents[j-1] = TopStudents[j];  
                                 TopStudents[j] = temp;  
                         }  
                          
                 }  
         }
         for (int i=n-1;i>=n-3 && flag==0;i--) {
        	 if(TopStudents[i].calcolaPunteggio()!=0)
        	 result+=TopStudents[i].getName()+" "+TopStudents[i].getSurname()+" : "+TopStudents[i].calcolaPunteggio();
        	 if(count!=n-1)
        	 result+='\n';
        	 count++;
        	 if(count==n)
        		 flag=1;
        
        	 }
         return result;
         }
	//logger 
	@Override
	public int enroll(String first, String last){ 
		int id=super.enroll(first, last);
		logger.info("New student enrolled:"+ListaStudenti[id-INITIAL_ID].toString());
		return id;
	}
	@Override
	public int activate(String title, String teacher){
		int CID;
		 CID=super.activate(title, teacher);
		 logger.info("New course activated: "+ListaCorsi[CID-INITIAL_CID].toString());
		 return CID;
		}
	@Override
	public void register(int studentID, int courseCode){
		super.register(studentID, courseCode);
		logger.info("Student "+studentID+" signed up for course "+courseCode);
	 }
}
