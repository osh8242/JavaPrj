public class Validation {

	public boolean isValidId(String id) {
		// 시작은 영문으로만, '_'를 제외한 특수문자 안되며 영문, 숫자, '_'으로만 이루어진 5 ~ 12자 이하
		if (id.matches("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$")) {
			return true;
		}
		return false;
	}

	public boolean isValidPassword(String pw) {
		if (pw.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$")) {
			return true;
		}
		return false;
	}

	public boolean isValidGuestPassword(String pw) {
		if (pw.matches("\\d{6}")) {
			return true;
		}
		return false;
	}

	public boolean isValidEmail(String email) {
		if (email.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?")) {
			return true;
		}
		return false;
	}

	public boolean isValidName(String name) {
		if (name.matches("^[가-힣]*$")) {
			return true;
		}
		return false;
	}

	public boolean isValidPhonenumber(String phonenuber) {
		if (phonenuber.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")) {
			return true;
		}
		return false;
	}
}