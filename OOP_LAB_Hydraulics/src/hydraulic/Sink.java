package hydraulic;

/**
 * Represents the sink, i.e. the terminal element of a system
 *
 */
public class Sink extends Element {
	private Element ingresso;

	/**
	 * Constructor
	 * @param name
	 */
	public Sink(String name) {
		super(name);
		//TODO: complete
	}
	
	@Override
	 void simulate(double inFlow,SimulationObserver observer) {
		observer.notifyFlow("Sink",getName(),inFlow,SimulationObserver.NO_FLOW);
	}
	public Element[] splitOutput(){
		return null;
	}
	
}
