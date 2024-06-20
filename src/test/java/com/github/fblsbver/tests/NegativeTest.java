package com.github.fblsbver.tests;

import com.github.fblsbver.pojos.LoginUserRequest;
import com.github.fblsbver.pojos.OtherResponses;
import com.github.fblsbver.pojos.RegUserRequest;
import org.junit.jupiter.api.Test;

import static com.github.fblsbver.steps.UserSteps.loginUser;
import static com.github.fblsbver.steps.UserSteps.registerUserResponse;
import static com.github.fblsbver.test_data.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;


public class NegativeTest {
    public static final String INCORRECT_NAME = "rty";
    public static final String INCORRECT_EMAIL = "tttttt";
    public static final String INCORRECT_PASSWORD = "ttryyuwqo";

    @Test
    public void inputInvalidName() {
        RegUserRequest userRequest = new RegUserRequest(INCORRECT_NAME, USER_EMAIL, USER_PASSWORD);

        String message = registerUserResponse(userRequest)
                .then()
                .statusCode(400)
                .extract().body().as(OtherResponses.class)
                .getMessage();

        assertThat(message)
                .isNotNull()
                .isEqualTo("User name must be between 4 and 30 characters");

    }

    @Test
    public void inputInvalidEmail() {

        RegUserRequest userRequest = new RegUserRequest(USER_NAME, INCORRECT_EMAIL, USER_PASSWORD);

        String message = registerUserResponse(userRequest)
                .then()
                .statusCode(400)
                .extract().body().as(OtherResponses.class)
                .getMessage();

        assertThat(message)
                .isNotNull()
                .isEqualTo("A valid email address is required");

    }

    @Test
    public void inputIncorrectEmailOrPassword() {
        LoginUserRequest userRequest = new LoginUserRequest(USER_EMAIL, INCORRECT_PASSWORD);

        String message = loginUser(userRequest)
                .then()
                .statusCode(401)
                .extract().body().as(OtherResponses.class)
                .getMessage();

        assertThat(message)
                .isNotNull()
                .isEqualTo("Incorrect email address or password");

    }
}
