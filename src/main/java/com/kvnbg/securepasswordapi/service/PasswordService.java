package com.kvnbg.securepasswordapi.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PasswordService {
    public List<String> check(String password) {
        List<String> failures = new ArrayList<>();

        validateLength(password, failures);
        validateAtLeastOneUppercase(password, failures);
        validateAtLeastOneLowercase(password, failures);
        validateAtLeastOneNumber(password, failures);
        validateAtLeastOneSpecialChar(password, failures);

        return failures;
    }

    private void validateAtLeastOneSpecialChar(String password, List<String> failures) {
        if(!Pattern.matches(".*[\\W].*", password)) {
            failures.add("The password does not contain at least one special character");
        }
    }

    private void validateAtLeastOneNumber(String password, List<String> failures) {
        if(!Pattern.matches(".*[0-1].*", password)) {
            failures.add("The password does not contain at least one digit");
        }
    }

    private void validateAtLeastOneLowercase(String password, List<String> failures) {
        if(!Pattern.matches(".*[a-z].*", password)) {
            failures.add("The password does not contain at least one lowercase letter");
        }
    }

    private void validateAtLeastOneUppercase(String password, List<String> failures) {
        if(!Pattern.matches(".*[A-Z].*", password)) {
            failures.add("The password does not contain at least one uppercase letter");
        }
    }

    private void validateLength(String password, List<String> failures) {
        if(password != null && password.length() < 8) {
            failures.add("Password must be at least 8 characters");
        }
    }
}
