package hydraulic;

/**
 * Represents the generic abstract element of an hydraulics system.
 * It is the base class for all elements.
 *
 * Any element can be connect to a downstream element
 * using the method {@link #connect(Element) connect()}.
 */
public abstract class Element {
	private String test;
	private String name;
	protected double lastflow;
	private double tmp=-1;
	/**
	 * Constructor
	 * @param name the name of the element
	 */
	public Element(String name){
		// TODO: to be implemented
		this.name=name;
	}

	/**
	 * getter method
	 * @return the name of the element
	 */
	public String getName(){
		// TODO: to be implemented
		return this.name;
	}

	/**
	 * Connects this element to a given element.
	 * The given element will be connected downstream of this element
	 * @param elem the element that will be placed downstream
	 */
	public void connect(Element elem){
		
	}
	
	
	/**
	 * Retrieves the element connected downstream of this
	 * @return downstream element
	 */
	public Element getOutput(){
		// TODO: to be implemented
		return null;
	}
	public abstract Element[] splitOutput();
	
	public double getFlowIn() {
		return tmp;
	}
	
	public double getFlowOut() {
		return tmp;
	}
	
	abstract void simulate(double inFlow,SimulationObserver observer);
}
