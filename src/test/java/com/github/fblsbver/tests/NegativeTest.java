package com.github.fblsbver.tests;

import com.github.fblsbver.config.App;
import com.github.fblsbver.pojos.LoginUserRequest;
import com.github.fblsbver.pojos.OtherResponses;
import com.github.fblsbver.pojos.RegUserRequest;
import org.junit.jupiter.api.Test;

import static com.github.fblsbver.specs.Specifications.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


public class NegativeTest {

    String name = App.config.userName();
    String incorrectName = "rty";
    String email = App.config.userEmail();
    String incorrectEmail = "tttttt";
    String password = App.config.userPassword();
    String incorrectPassword = "ttryyuwqo";

    @Test
    public void inputInvalidName() {

        RegUserRequest userRequest = new RegUserRequest(incorrectName, email, password);

        String message = given()
                .spec(request)
                .body(userRequest)
                .when()
                .post(App.config.userReg())
                .then().log().all()
                .spec(responseBadRequest)
                .extract().body().as(OtherResponses.class)
                .getMessage();

        assertThat(message)
                .isNotNull()
                .isEqualTo("User name must be between 4 and 30 characters");

    }

    @Test
    public void inputInvalidEmail() {

        RegUserRequest userRequest = new RegUserRequest(name, incorrectEmail, password);

        String message = given()
                .spec(request)
                .body(userRequest)
                .when()
                .post(App.config.userReg())
                .then().log().all()
                .spec(responseBadRequest)
                .extract().body().as(OtherResponses.class)
                .getMessage();

        assertThat(message)
                .isNotNull()
                .isEqualTo("A valid email address is required");

    }

    @Test
    public void inputIncorrectEmailOrPassword() {
        LoginUserRequest userRequest = new LoginUserRequest(email, incorrectPassword);

        String message = given()
                .spec(request)
                .body(userRequest)
                .when()
                .post(App.config.userLogin())
                .then().log().all()
                .spec(responseUnauthorized)
                .extract().body().as(OtherResponses.class)
                .getMessage();

        assertThat(message)
                .isNotNull()
                .isEqualTo("Incorrect email address or password");

    }
}
