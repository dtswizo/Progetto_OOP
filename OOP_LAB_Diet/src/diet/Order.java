package diet;

import java.util.TreeMap;

/**
 * Represents an order in the take-away system
 */
public class Order {
	private User user;
	private Restaurant restaurant;
	private int h;
	private int m;
	private int[] openClose;
	private TreeMap <String,Integer> menus = new TreeMap<>();
	private OrderStatus status;
	private PaymentMethod pmethod;
	private String scontrino="";
	private String hm="";
	public Order(User user,Restaurant restaurant, int h, int m) {
		this.user=user;
		this.restaurant = restaurant;
		this.h = h;
		this.m = m;
		this.status=OrderStatus.ORDERED;
		this.pmethod=PaymentMethod.CASH;
		openClose=new int[restaurant.getVsize()];
		checkOrderTime();
	}
 
	/**
	 * Defines the possible order status
	 */
	public enum OrderStatus {
		ORDERED, READY, DELIVERED;
	}
	/**
	 * Defines the possible valid payment methods
	 */
	public enum PaymentMethod {
		PAID, CASH, CARD;
	}
		
	/**
	 * Total order price
	 * @return order price
	 */
	public double Price() {
		return -1.0;
	}
	
	/**
	 * define payment method
	 * 
	 * @param method payment method
	 */
	public void setPaymentMethod(PaymentMethod method) {
		pmethod=method;
	}
	
	/**
	 * get payment method
	 * 
	 * @return payment method
	 */
	public PaymentMethod getPaymentMethod() {
		return pmethod;
	}
	
	/**
	 * change order status
	 * @param newStatus order status
	 */
	public void setStatus(OrderStatus newStatus) {
		status=newStatus;
	
	}
	
	/**
	 * get current order status
	 * @return order status
	 */
	public OrderStatus getStatus(){
		return status;
	}
	
	/**
	 * Add a new menu with the relative order to the order.
	 * The menu must be defined in the {@link Food} object
	 * associated the restaurant that created the order.
	 * 
	 * @param menu     name of the menu
	 * @param quantity quantity of the menu
	 * @return this order to enable method chaining
	 */
	public Order addMenus(String menu, int quantity) {
		int tempValue;
		if(menus.containsKey(menu)) {
			menus.put(menu,quantity);
		}
		else {
		menus.put(menu,quantity);	
		}

		return this;
	}
	
	/**
	 * Converts to a string as:
	 * <pre>
	 * RESTAURANT_NAME, USER_FIRST_NAME USER_LAST_NAME : DELIVERY(HH:MM):
	 * 	MENU_NAME_1->MENU_QUANTITY_1
	 * 	...
	 * 	MENU_NAME_k->MENU_QUANTITY_k
	 * </pre>
	 */
	public void checkOrderTime() {
		int res;
		h=h*60*60;
		m=m*60;
		res=m+h;
		int vicino=Integer.MAX_VALUE;
		int index=0;
		int resto=0;
		boolean orarioGiusto=false;
		this.openClose=restaurant.getOpenClose();
		for(int i=0;i<openClose.length && orarioGiusto==false;i+=2) {
			if(res>=openClose[i] && res<=openClose[i+1]) 
				orarioGiusto=true;
			if(i+2<=openClose.length-1) {
			 if(openClose[i+2]-res<vicino) {
			vicino=openClose[i+2]-res;
			index=i+2;
			}
		   }
		 }
		if (orarioGiusto==false) {
			resto=openClose[index]%3600;
			this.h=openClose[index]/3600;
			if(resto!=0)
			this.m=openClose[index]/resto;
			else
		    this.m=0;
		}
		
}
	@Override
	public String toString() {
		TimeManagement tm=new TimeManagement(scontrino,hm); //queste due stringhe sono inutili
		                                                    //ma non mi va di cambiare il costruttore
		hm=tm.hmToString(h, m);
		scontrino=restaurant.getName()+", "+user.getFirstName()+" "+user.getLastName()
		+" : ";
		scontrino+="("+hm+"):\n";
		for(String key: menus.keySet()) {
			scontrino+="\t"+key+"->"+menus.get(key)+"\n";
		}
		
		return scontrino;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public User getUser() {
		return user;
	}

   public int getHm() {
	   return h+m;
   }
   public String getStringHm() {
	   TimeManagement tm=new TimeManagement(scontrino,hm);
	   hm=tm.hmToString(h, m);
	   return hm;
   }
	
}
