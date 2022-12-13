package university;

public class Course {
	private static final int MAX_STUDENTS = 100;
	private String materia;
	private String NomeDocente;
	private int CID;
	private int nExamspassed=0;
	private float totalgrade=0;
	private Student StudentiFrequentanti[]= new Student[MAX_STUDENTS];
	public Course(int CID,String materia, String nomeDocente) {
		super();
		this.materia = materia;
		NomeDocente = nomeDocente;
		this.CID=CID;
	}

	@Override
	public String toString() {
		return CID+","+materia+","+NomeDocente;
	}
	public void enroll(Student s) {
		int flag=0;
		for (int i=0; i< MAX_STUDENTS && flag==0 ; i++){
			if(StudentiFrequentanti[i]== null) {
				StudentiFrequentanti[i]=s;
				flag=1;
			}
		}
	}
	public String giveListAttendees() {
		String result="";
		for (int i=0; i<MAX_STUDENTS ;i++) {
			if(StudentiFrequentanti[i]!= null) {
			result+=StudentiFrequentanti[i].toString();
			result+="\n";}
		}
		return result;
		
	}
	public void cPassed(int grade) {
		nExamspassed++;
		totalgrade+=grade;
	}
	public float courseAvgRes(int ID) {
		float res;
		if(nExamspassed==0)
			return 0;
		res=totalgrade/nExamspassed;
		return res;
	}	
	public String getTitle() {
		return materia;
	}
}
