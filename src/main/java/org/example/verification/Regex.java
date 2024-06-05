package org.example.verification;

public class Regex {
    public boolean isValidEmail(String email) {
        String regex = "^(.+)@(.+)$";
        return email.matches(regex);
    }
    public boolean isValidPassword(String password) {
        // Password must be at least 8 characters long and contain at least one letter and one digit
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        return password.matches(regex);
    }
}
