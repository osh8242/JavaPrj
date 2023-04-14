public class Member extends User {

	private String userId;
	private String userPhoneNumber;
	private String userName;
	private int userPoint;
	private boolean isAdmin;

	// 일반 회원 생성자함수
	public Member(String userPhoneNumber, String userPassword, String userId, String userName) {
		this(userPhoneNumber, userPassword, userId, userName, false);
		this.userPoint = 0;
		this.isAdmin = false;
	}

	// 관리자 생성자함수
	public Member(String userPhoneNumber, String userPassword, String userId, String userName, boolean isAdmin) {
		super(userPassword);
		this.userPhoneNumber = userPhoneNumber;
		this.userId = userId;
		this.userName = userName;
		this.userPoint = -10000;
		this.isAdmin = isAdmin;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserId() {

		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "Member [userPhoneNumber: " + userPhoneNumber + ", userId: " + userId + ", userName: " + userName
				+ ", userPoint: " + userPoint + ", isAdmin: " + isAdmin + "]";
	}

}
