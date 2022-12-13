package mountainhuts;

public class AltitudeRanges {
	private int minAltitude;
	private int maxAltitude;
	private String input;
	public AltitudeRanges(String input) {
		this.input=input;
		computeAltitude(input);
	}
	
	public void computeAltitude(String input) {
		String[] temp=new String[1];
		temp=input.split("-");
		minAltitude=Integer.parseInt(temp[0]);
		maxAltitude=Integer.parseInt(temp[1]);	
	}
	public int getMinAltitude() {
		return minAltitude;
	}
	
	public int getMaxAltitude() {
		return maxAltitude;
	}
	public String getInput() {
		return input;
	}
	
	
	
}
