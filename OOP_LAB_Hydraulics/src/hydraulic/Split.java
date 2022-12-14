package hydraulic;

/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will receive a stream that is
 * half the input stream of the split.
 */

public class Split extends Element {
	private Element ingresso;
	private Element[] uscite = new Element[2];

	/**
	 * Constructor
	 * 
	 * @param name
	 */
	public Split(String name) {
		super(name);
		// TODO: complete
	}

	/**
	 * returns the downstream elements
	 * 
	 * @return array containing the two downstream element
	 */
	public Element[] getOutputs() {
		return uscite;
	}

	/**
	 * connect one of the outputs of this split to a downstream component.
	 * 
	 * @param elem    the element to be connected downstream
	 * @param noutput the output number to be used to connect the element
	 */
	public void connect(Element elem, int noutput) {
		uscite[noutput] = elem;
		}

	@Override
	 void simulate(double inFlow,SimulationObserver observer) {
		double outFlow;
		outFlow=inFlow/2;
		observer.notifyFlow("Split", getName(), inFlow, outFlow,outFlow);
		for(Element e : getOutputs()) {
			e.simulate(outFlow, observer);
		}
			
		
	}
	public Element[] splitOutput(){
		return uscite;
	}
}
