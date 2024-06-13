package com.github.fblsbver.steps;

import com.github.fblsbver.config.App;
import com.github.fblsbver.pojos.*;

import java.util.Map;

import static com.github.fblsbver.specs.Specifications.*;
import static io.restassured.RestAssured.given;

public class UserSteps {

    public RegUserResponse registerUser(RegUserRequest requestBody) {
        return given()
                .spec(request)
                .body(requestBody)
                .post(App.config.userReg())
                .then().log().all()
                .spec(responseCreate)
                .extract().body().jsonPath().getObject("data", RegUserResponse.class);

    }

    public RegUserRequest getUserRegData(String name, String email, String password) {
        return RegUserRequest.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }

    public LoginUserResponse loginUser(LoginUserRequest requestBody) {
        return given()
                .spec(request)
                .body(requestBody)
                .post(App.config.userLogin())
                .then().log().all()
                .spec(responseSuccess)
                .extract().body().jsonPath().getObject("data", LoginUserResponse.class);

    }


    public LoginUserRequest enterUserLoginData(String email, String password) {
        return LoginUserRequest.builder()
                .email(email)
                .password(password)
                .build();
    }


    public static String getToken(LoginUserRequest requestBody) {
        Map<String, String> data = given()
                .spec(request)
                .body(requestBody)
                .post(App.config.userLogin())
                .then()
                .extract().body().jsonPath().getMap("data");

        return data.get("token");
    }


}
