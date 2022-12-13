package diet;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeMap;

import diet.Order.OrderStatus;

/**
 * Represents a restaurant in the take-away system
 *
 */
public class Restaurant {
	private String name;
	private Food food;
	private TreeMap <Integer,Integer> aperturaChiusura = new TreeMap<>();
	private LinkedList<Order> orderList = new LinkedList<>();
	private LinkedList<Order> orderListWithStatus = new LinkedList<>();
	private int openClose[];
	private int vsize;
	private Takeaway takeaway=new Takeaway();

	/**
	 * Constructor for a new restaurant.
	 * 
	 * Materials and recipes are taken from
	 * the food object provided as argument.
	 * 
	 * @param name	unique name for the restaurant
	 * @param food	reference food object
	 */
	public Restaurant(String name, Food food) {
		this.name = name;
		this.food = food;
	}
	
	/**
	 * gets the name of the restaurant
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Define opening hours.
	 * 
	 * The opening hours are considered in pairs.
	 * Each pair has the initial time and the final time
	 * of opening intervals.
	 * 
	 * for a restaurant opened from 8:15 until 14:00 and from 19:00 until 00:00, 
	 * is thoud be called as {@code setHours("08:15", "14:00", "19:00", "00:00")}.
	 * 
	 * @param hm a list of opening hours
	 */
	public void setHours(String ... hm) {
		int t1;
		int t2;
		openClose= new int[hm.length];
		vsize=hm.length;
		for(int i=0;i<hm.length;i+=2) {
			TimeManagement t = new TimeManagement(hm[i],hm[i+1]);
			openClose[i]=t.getIntTime(1);
			openClose[i+1]=t.getIntTime(2);
		 }
		
	}
	
	public Menu getMenu(String name) {
		return null;
	}
	
	/**
	 * Creates a new menu
	 * 
	 * @param name name of the menu
	 * 
	 * @return the newly created menu
	 */
	public Menu createMenu(String name) {
		Menu  m=food.createMenu(name);
		return m;
	}

	/**
	 * Find all orders for this restaurant with 
	 * the given status.
	 * 
	 * The output is a string formatted as:
	 * <pre>
	 * Napoli, Judi Dench : (19:00):
	 * 	M6->1
	 * Napoli, Ralph Fiennes : (19:00):
	 * 	M1->2
	 * 	M6->1
	 * </pre>
	 * 
	 * The orders are sorted by name of restaurant, name of the user, and delivery time.
	 * 
	 * @param status the status of the searched orders
	 * 
	 * @return the description of orders satisfying the criterion
	 */
	public String ordersWithStatus(OrderStatus status) {
		String res="";
		this.orderList=takeaway.getOrderList();
		for(Order O : orderList) {
			if(O.getRestaurant()==this && O.getStatus()==status)
				orderListWithStatus.add(O);
		}
		Collections.sort(orderListWithStatus, new Comparator<Order>() {
            @Override
			public int compare(Order o1, Order o2) {
           	int flag=0;
            	if(o1.getRestaurant().getName().equals(o2.getRestaurant().getName()))
            		flag=1;
            	if(o1.getUser().getFirstName().equals(o2.getUser().getFirstName()))
            		flag=2;
            	if(flag==0)
				return o1.getRestaurant().getName().compareTo(o2.getRestaurant().getName());
            	else if(flag==1)
            		return o1.getUser().getFirstName().compareTo(o2.getUser().getFirstName());
            	else if(flag==2) {
            		return o1.getHm()-o2.getHm();
            	}
            	return 0;
           }
        });
//		for(Order o : orderListWithStatus) {
//			String hm=o.getStringHm();
//			res+=o.getRestaurant().getName()+", ";
//			res+=o.getUser().getFirstName()+" "+
//			o.getUser().getLastName()+" : "+"("+hm+"):\n";
//			for(String key: menus.keySet()) {
//				res+="\t"+key+"->"+o.menus.get(key)+"\n";
//			}
//		}
//		
		return "Suca";
	}

	public int[] getOpenClose() {
		return openClose;
	}
	public int getVsize() {
		return vsize;
	}

	public Takeaway getTakeaway() {
		return takeaway;
	}

	public void setTakeAway(Takeaway takeaway) {
		this.takeaway = takeaway;
	}

}
