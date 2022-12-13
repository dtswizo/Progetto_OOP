package diet;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;
    
/**
 * Represents the main class in the
 * take-away system.
 * 
 * It allows adding restaurant, users, and creating orders.
 *
 */
public class Takeaway {
	private HashMap <String,Restaurant> resMap= new HashMap<>();
	private HashMap <String,Restaurant> openResMap= new HashMap<>();
	private TreeMap <String,User> userMap= new TreeMap<>();
	private LinkedList<Order> orderList = new LinkedList<>();

	/**
	 * Adds a new restaurant to the take-away system
	 * 
	 * @param r the restaurant to be added
	 */
	public void addRestaurant(Restaurant r) {
		resMap.put(r.getName(), r);
		r.setTakeAway(this);
		
	}
	
	/**
	 * Returns the collections of restaurants
	 * 
	 * @return collection of added restaurants
	 */
	public Collection<String> restaurants() {
		return resMap.keySet();
	}
	
	/**
	 * Define a new user
	 * 
	 * @param firstName first name of the user
	 * @param lastName  last name of the user
	 * @param email     email
	 * @param phoneNumber telephone number
	 * @return
	 */
	public User registerUser(String firstName, String lastName, String email, String phoneNumber) {
		User u = new User(firstName,lastName,email,phoneNumber);
		userMap.put(u.getLastName()+" "+u.getFirstName(), u);
		return u;
	}
	
	/**
	 * Gets the collection of registered users
	 * 
	 * @return the collection of users
	 */
	public Collection<User> users(){
		return userMap.values();
	}
	
	/**
	 * Create a new order by a user to a given restaurant.
	 * 
	 * The order is initially empty and is characterized
	 * by a desired delivery time. 
	 * 
	 * @param user				user object
	 * @param restaurantName	restaurant name
	 * @param h					delivery time hour
	 * @param m					delivery time minutes
	 * @return
	 */
	public Order createOrder(User user, String restaurantName, int h, int m) {
		Restaurant r= resMap.get(restaurantName);
		Order o = new Order(user,r,h,m);
		orderList.add(o);
		return o;
	}
	
	/**
	 * Retrieves the collection of restaurant that are open
	 * at the given time.
	 * 
	 * @param time time to check open
	 * 
	 * @return collection of restaurants
	 */
	public Collection<Restaurant> openedRestaurants(String time){ 
		int res;
		int h,m;
		String elaborazione[];
		elaborazione=time.split(":");
		h=Integer.parseInt(elaborazione[0]);
		m=Integer.parseInt(elaborazione[1]);
		TimeManagement tm= new TimeManagement("test","test");
		res=tm.hmToSeconds(h,m);
		for(Restaurant r : resMap.values()) {
			for(int i=0;i<r.getVsize();i+=2) {
				int[] openClose=r.getOpenClose();
					if(res>=openClose[i] && res<openClose[i+1]
							&& openClose[i+1]-res>=3600) {
					    openResMap.put(r.getName(), r);
					}
				    //devo controllare se è aperto per almeno 1 ora
			}
		}
		
		return openResMap.values();
	}

	public LinkedList<Order> getOrderList() {
		return orderList;
	}
		
	
}
