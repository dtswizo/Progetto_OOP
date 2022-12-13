package hydraulic;

/**
 * Represents a tap that can interrupt the flow.
 * 
 * The status of the tap is defined by the method
 * {@link #setOpen(boolean) setOpen()}.
 */

public class Tap extends Element {
	private Element ingresso;
	private Element uscita;
	private boolean aperto;
	private double flowIn=lastflow;
	private double flowOut=0;
	


	public Tap(String name) {
		super(name);
		//TODO: complete
	}
	
	/**
	 * Defines whether the tap is open or closed.
	 * 
	 * @param open  opening level
	 */
	public void setOpen(boolean open){
		aperto=open;
		flowOut=flowIn;
	}
	@Override
	public void connect(Element elem){
		uscita=elem;
		
	}
	@Override
	public Element getOutput(){
		return uscita;
	}
	@Override
	public double getFlowIn() {
		return flowIn;
	}
	
	@Override
	public double getFlowOut() {
		return flowOut;
	}

	@Override
	 void simulate(double inFlow,SimulationObserver observer) {
		double outFlow=aperto?inFlow:0;
		observer.notifyFlow("Tap",getName(),inFlow,outFlow);
		uscita.simulate(outFlow, observer);
	}
	public Element[] splitOutput(){
		return null;
	}
}
