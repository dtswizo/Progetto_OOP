package hydraulic;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystem {
	
	/**
	 * Adds a new element to the system
	 * @param elem
	 */
	protected Element[] ElementVector=new Element[100];
	protected int nElements=0;
	public void addElement(Element elem){
		ElementVector[nElements]=elem;
		nElements++;
		
		// TODO: to be implemented
	}
	
	/**
	 * returns the element added so far to the system.
	 * If no element is present in the system an empty array (length==0) is returned.
	 * 
	 * @return an array of the elements added to the hydraulic system
	 */
	public Element[] getElements(){
	 Element[] temp=new Element[nElements];
	  for(int i=0;i<nElements;i++) {
		  temp[i]=ElementVector[i];
	  }
		
		return temp;
	}
	
	/**
	 * starts the simulation of the system
	 */
	public void simulate(SimulationObserver observer){
	Element[] tmp= new Element[nElements];
	tmp=getElements();
	for(Element e : tmp) {
		if(e instanceof Source) {
			e.simulate(-1,observer);
		}
	}
		
	
		
	}

}
