package hydraulic;

/**
 * Represents a source of water, i.e. the initial element for the simulation.
 *
 * The status of the source is defined through the method
 * {@link #setFlow(double) setFlow()}.
 */
public class Source extends Element {
	private Element uscita;
	private double flowIn=0;
	private double flowOut;
	
    
	public Source(String name) {
		super(name);
		 
	
		//TODO: complete
	}

	/**
	 * defines the flow produced by the source
	 * 
	 * @param flow
	 */
	public void setFlow(double flow){
		this.flowOut=flow;
		lastflow=flow;
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
	public double getFlowOut() {
		return flowOut;
	}
	@Override
	 void simulate(double inFlow,SimulationObserver observer) {
		observer.notifyFlow("Source",getName(),SimulationObserver.NO_FLOW ,flowOut);
		uscita.simulate(flowOut, observer);
	}
	public Element[] splitOutput(){
		return null;
	}
	
}
