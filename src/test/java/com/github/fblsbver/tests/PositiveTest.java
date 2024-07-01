package com.github.fblsbver.tests;

import com.github.fblsbver.pojos.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static com.github.fblsbver.steps.UserSteps.*;
import static com.github.fblsbver.test_data.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PositiveTest {

    @Test
    @Order(1)
    public void successRegTest() {
        RegUserResponse response = registerUserResponse(new RegUserRequest(USER_NAME, USER_EMAIL, USER_PASSWORD))
                .then()
                .statusCode(201)
                .extract().body().jsonPath().getObject("data", RegUserResponse.class);

        assertThat(response)
                .extracting(RegUserResponse::getName)
                .isNotNull()
                .isEqualTo(USER_NAME);
    }

    @Test
    @Order(2)
    public void successLogin() {
        LoginUserResponse response = loginUser(new LoginUserRequest(USER_EMAIL, USER_PASSWORD))
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getObject("data", LoginUserResponse.class);

        assertThat(response)
                .isNotNull()
                .extracting(LoginUserResponse::getEmail)
                .isEqualTo(USER_EMAIL);

    }

    @Test
    @Order(3)
    public void getInfoFromUserProfile() {
        String name = getInfoFromUserProfileResponse()
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getObject("data", RegUserResponse.class)
                .getName();

        assertThat(name)
                .isNotNull();

    }

    @Test
    @Order(4)
    public void logoutUser() {
        String message = logoutUserResponse()
                .then()
                .statusCode(200)
                .extract().body().as(DeleteUserResponse.class)
                .getMessage();

        assertThat(message)
                .isEqualTo("User has been successfully logged out");

    }

    @Test
    @Order(5)
    public void deleteUser() {
        String message = deleteUserResponse()
                .then()
                .statusCode(200)
                .extract().body().as(DeleteUserResponse.class)
                .getMessage();

        assertThat(message)
                .isEqualTo("Account successfully deleted");

    }

}
