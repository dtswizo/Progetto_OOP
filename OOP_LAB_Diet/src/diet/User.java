package diet;

/**
 * Represent a take-away system user
 *  
 */
public class User {
	private String name;
	private String surname;
	private String email;
	private String phone;
		
	public User(String name, String surname,String email, String phone) {
		this.name = name;
		this.surname = surname;
		this.email=email;
		this.phone = phone;
	}

	/**
	 * get user's last name
	 * @return last name
	 */
	public String getLastName() {
		return surname;
	}
	
	/**
	 * get user's first name
	 * @return first name
	 */
	public String getFirstName() {
		return name;
	}
	
	/**
	 * get user's email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * get user's phone number
	 * @return  phone number
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * change user's email
	 * @param email new email
	 */
	public void SetEmail(String email) {
		this.email=email;
	}
	
	/**
	 * change user's phone number
	 * @param phone new phone number
	 */
	public void setPhone(String phone) {
		this.phone=phone;
	}
	
}
