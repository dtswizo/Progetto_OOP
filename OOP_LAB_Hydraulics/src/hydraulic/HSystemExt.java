package hydraulic;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystemExt extends HSystem{
	
	/**
	 * Prints the layout of the system starting at each Source
	 */
	public String layout(){
		int selector=0;
		int localselector=0;
		String result="";
		boolean flagSplit=false;
		boolean lastFlagSplit;
		Element[] splitOutput;
		int ricorsione=0;
		int flagRamo=0;
		String[] types= new String[]{"Source","Tap","Split","Sink"};
		
		for (int i = 0; i<nElements; i++) {
			selector=selectInstance(ElementVector[i],selector);
	        if(ricorsione!=1) {
	        	System.out.print("["+ElementVector[i].getName()+"]"+types[selector]);
	        	result+="["+ElementVector[i].getName()+"]"+types[selector];
	        }
			if(ElementVector[i+1]!=null && !(ElementVector[i] instanceof Split)) {
			System.out.print("->");
			result+="->";
			}
			if(ElementVector[i] instanceof Split) {
				ricorsione=1;
				result+="+->";
				System.out.print("+->");
				splitOutput= new Element[ElementVector[i].splitOutput().length];
				int k;
				int j;
				int z=i+1;
		        for(k=0;k<ElementVector[i].splitOutput().length;k++) {
		        	flagRamo=0;
		        	for(j=z;ElementVector[j]!=null && flagRamo==0;j++) {
		        		localselector=selectInstance(ElementVector[j],localselector);
		        		System.out.print("["+ElementVector[j].getName()+"]"+types[selector]);
		        		result+="["+ElementVector[j].getName()+"]"+types[selector];
		        		if(localselector==3) {
		        			flagRamo=1;
		        			System.out.print("\n");
		        			result+="\n";
		        			z=j+1;
		        		}
		        	}
		        }
			}
			
			
		}
		
		return result;
	}
	
	/**
	 * Deletes a previously added element with the given name from the system
	 */
	public boolean deleteElement(String name) {
		int index=0;
		int flag=0;
		int selector=0;
		for(int i=0;i<nElements && flag==0;i++) {
			if(ElementVector[i].getName().equals(name)){
				index=i;
				flag=1;
			}
		}
		selector=selectInstance(ElementVector[index],selector);
		ElementVector[index-1].connect(ElementVector[index+1]);
		for(int i=index;i<nElements;i++) {
			ElementVector[i]=ElementVector[i+1];
		}
		if(selector==2)
			return false;
		else
			return true;
	}

	/**
	 * starts the simulation of the system; if enableMaxFlowCheck is true,
	 * checks also the elements maximum flows against the input flow
	 */
	public int selectInstance(Element vector,int selector) {
		if(vector instanceof Source)
			selector=0;
		else if(vector instanceof Tap)
			selector=1;
		else if(vector instanceof Split) 
			selector=2;
		else if(vector instanceof Sink)
			selector=3;
		return selector;
		}
	public void simulate(SimulationObserverExt observer, boolean enableMaxFlowCheck) {
		// TODO: to be implemented
	}
	
}
