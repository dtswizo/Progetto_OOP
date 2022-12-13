package diet;

public class TimeManagement {
	private int h;
	private int m;
	String elaborazione[];
	String apertura;
	String chiusura;
	public TimeManagement(String apertura, String chiusura) {
		this.apertura = apertura;
		this.chiusura = chiusura;
	}
	
	public int getIntTime(int mode) {
		if (mode==1) {
				elaborazione=apertura.split(":");
				h=Integer.parseInt(elaborazione[0]);
				m=Integer.parseInt(elaborazione[1]);
				return hmToSeconds(h,m);
				}
		else {
			   elaborazione=chiusura.split(":");
			   h=Integer.parseInt(elaborazione[0]);
			   m=Integer.parseInt(elaborazione[1]);
			   return hmToSeconds(h,m);
			   }
		}
	
	 public int hmToSeconds(int h,int m) {
		   int res;
		   h=h*60*60;
		   m=m*60;
		   return h+m;
		 }
	 public String hmToString(int h,int m) {
		 String res="";
		 if(h>=0 && h<=9)
			 res+="0"+h;
		 else
			 res+=h;
		 res+=":";
		 if(m>=0 && m<=9)
			 res+="0"+m;
		 else
			 res+=m;
		 return res;
	 }
}
