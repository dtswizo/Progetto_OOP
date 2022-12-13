package university;

public class Student {
	private static final int MAX_COURSES = 25;
	private String nome;
	private String cognome;
	private Course CorsiFrequentati[]= new Course[MAX_COURSES];
	private int nCorsiFrequentati=0;
	private int nExamsPassed=0;
	private float totalGrade=0;
	private float punteggio;
	private int ID;
	public Student(int id, String nome, String cognome) {
		super();
		this.ID=id;
		this.nome = nome;
		this.cognome = cognome;
	}
	@Override
	public String toString() {
		return ID+" "+nome+" "+cognome;
	}
	public void enroll(Course c) {
		int flag=0;
		for (int i=0; i< MAX_COURSES && flag==0 ; i++){
			if(CorsiFrequentati[i]== null) {
				CorsiFrequentati[i]=c;
				nCorsiFrequentati++;
				flag=1;
			}
		}
	}
	
	public String giveStudyPlan() {
		String result="";
		for (int i=0; i<MAX_COURSES ;i++) {
			if(CorsiFrequentati[i]!= null) {
			result+=CorsiFrequentati[i].toString();
			result+="\n";}
		}
		return result;
	}
	public void cPassed(int CID, int grade) {
		nExamsPassed++;
		totalGrade+=grade;
	 }
	public float studentAvgRes(int ID) {
		float res;
		if(nExamsPassed==0)
			return 0;
		else {
		res=totalGrade/nExamsPassed;
		return res;
		}
	}
	public float calcolaPunteggio() {
		punteggio=totalGrade/nExamsPassed+(nExamsPassed/nCorsiFrequentati)*10;		
		return punteggio;
	}
	public String getName() {
		return nome;
	}
	public String getSurname() {
		return cognome;
	}
	
}
