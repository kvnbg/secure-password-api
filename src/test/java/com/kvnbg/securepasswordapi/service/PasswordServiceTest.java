package com.kvnbg.securepasswordapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PasswordServiceTest {

    @Test
    void check_all_validation_not_pass() {
        PasswordService passwordService = new PasswordService();
        var password = "1";
        var expectedFailures = Arrays.asList("Password must be at least 8 characters",
                "The password does not contain at least one uppercase letter",
                "The password does not contain at least one lowercase letter",
                "The password does not contain at least one special character");


        var resultFailure = passwordService.check(password);

        Assertions.assertEquals(resultFailure, expectedFailures);
    }

    @ParameterizedTest
    @CsvSource({
            "Aa1#, Password must be at least 8 characters",
            "12345678a#, The password does not contain at least one uppercase letter",
            "12345678A#, The password does not contain at least one lowercase letter",
            "12345678Aa, The password does not contain at least one special character",
            "12345678Aa#, "

    })
    void check_validations(String password, String validationMessage) {
        PasswordService passwordService = new PasswordService();

        var expectedFailures = validationMessage != null ? List.of(validationMessage) : Collections.emptyList();

        var resultFailure = passwordService.check(password);

        Assertions.assertEquals(resultFailure, expectedFailures);
    }
}