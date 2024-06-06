package org.example.verification;

public class Regex {
    public boolean isValidEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(regex);
    }
    public boolean isValidPassword(String password) {
        String regex = "^[A-Za-z\\d@$!%*?&]+$";
        return password.matches(regex);
    }
}
