package com.github.fblsbver.tests;

import com.github.fblsbver.config.App;
import com.github.fblsbver.pojos.*;
import com.github.fblsbver.steps.UserSteps;
import org.junit.jupiter.api.*;

import static com.github.fblsbver.specs.Specifications.request;
import static com.github.fblsbver.specs.Specifications.responseSuccess;
import static com.github.fblsbver.steps.UserSteps.getToken;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PositiveTest {

    public static final String name = App.config.userName();
    public static final String email = App.config.userEmail();
    public static final String password = App.config.userPassword();
    public static final String userNameInProfile = App.config.userNameInProfile();
    public static final String userPhoneNumber = App.config.userPhoneNumber();
    public static final String userCompany = App.config.userCompany();


    @Test
    @Order(1)
    public void successRegTest() {
        RegUserRequest user = new UserSteps().getUserRegData(name, email, password);
        RegUserResponse response = new UserSteps().registerUser(user);

        assertThat(response)
                .extracting(RegUserResponse::getName)
                .isNotNull()
                .isEqualTo(name);
    }

    @Test
    @Order(2)
    public void successLogin() {
        LoginUserRequest login = new UserSteps().enterUserLoginData(email, password);
        LoginUserResponse response = new UserSteps().loginUser(login);

        assertThat(response)
                .isNotNull()
                .extracting(LoginUserResponse::getEmail)
                .isEqualTo(email);
    }

    @Disabled
    @Test
    @Order(3)
    public void updateUserProfile() {
        UpdateUserInfoRequest updateUserInfoRequest = new UpdateUserInfoRequest(userNameInProfile, userPhoneNumber, userCompany);

        given()
                .spec(request)
                .header("x-auth-token", getToken(new LoginUserRequest(email, password)))
                .body(updateUserInfoRequest)
                .when()
                .patch(App.config.userInfo())
                .then().log().all()
                .spec(responseSuccess)
                .extract().body().as(UpdateUserInfoResponse.class);

    }

    @Test
    @Order(4)
    public void getInfoFromUserProfile() {
        String name = given()
                .spec(request)
                .header("x-auth-token", getToken(new LoginUserRequest(email, password)))
                .when()
                .get(App.config.userInfo())
                .then().log().all()
                .spec(responseSuccess)
                .extract().body().jsonPath().getObject("data", RegUserResponse.class).getName();

        assertThat(name)
                .isNotNull();


    }

    @Test
    @Order(5)
    public void logoutUser() {
        String message = given()
                .spec(request)
                .when()
                .header("x-auth-token", getToken(new LoginUserRequest(email, password)))
                .delete(App.config.userLogout())
                .then().log().all()
                .spec(responseSuccess)
                .extract().body().as(DeleteUserResponse.class)
                .getMessage();

        assertThat(message)
                .isEqualTo("User has been successfully logged out");
    }

    @Test
    @Order(6)
    public void deleteUser() {
        String message = given()
                .spec(request)
                .header("x-auth-token", getToken(new LoginUserRequest(email, password)))
                .when()
                .delete(App.config.userDeleteAcc())
                .then().log().all()
                .spec(responseSuccess)
                .extract().body().as(DeleteUserResponse.class)
                .getMessage();

        assertThat(message)
                .isEqualTo("Account successfully deleted");

    }

}
