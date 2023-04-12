
public class User {
	private int userPhoneNumber;
	private String userPassword;
	

	public User(int userPhoneNumber, String userPassword) {
		this.userPhoneNumber = userPhoneNumber;
		this.userPassword = userPassword;
	}
	
	public int getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(int userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	@Override
	public String toString() {
		return "User [userPhoneNumber : " + userPhoneNumber + ", userPassword : " + userPassword + "]";
	}
	
	
}
