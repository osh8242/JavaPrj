public class Validation {

    public boolean isValidId(String id) {
        return id.matches("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$");
    }

    public boolean isValidPassword(String pw) {
        return pw.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
    }

    public boolean isValidGuestPassword(String pw) {
        return pw.matches("\\d{6}");
    }

    public boolean isValidName(String name) {
        return name.matches("^[가-힣]*$");
    }

    public boolean isValidPhonenumber(String phonenumber) {
        return phonenumber.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$");
    }
    
}