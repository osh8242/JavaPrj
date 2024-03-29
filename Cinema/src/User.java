import java.io.Serializable;

public class User implements Serializable {

	private String userPassword;

	public User(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "User [userPassword : " + userPassword + "]";
	}

}
