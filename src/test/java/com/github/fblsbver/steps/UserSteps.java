package com.github.fblsbver.steps;

import com.github.fblsbver.pojos.*;
import io.restassured.response.Response;

import static com.github.fblsbver.endpoints.EndPoints.*;
import static com.github.fblsbver.specs.Specifications.*;
import static com.github.fblsbver.test_data.TestData.USER_EMAIL;
import static com.github.fblsbver.test_data.TestData.USER_PASSWORD;
import static io.restassured.RestAssured.given;

public class UserSteps {
    public static Response registerUserResponse(RegUserRequest requestBody) {
        return given()
                .spec(REQUEST_SPEC)
                .body(requestBody)
                .post(USER_REG)
                .then()
                .extract().response();
    }

    public static RegUserRequest getUserRegData(String name, String email, String password) {
        return RegUserRequest.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }

    public static Response loginUser(LoginUserRequest requestBody) {
        return given()
                .spec(REQUEST_SPEC)
                .body(requestBody)
                .post(USER_LOGIN)
                .then()
                .extract().response();

    }

    public static LoginUserRequest enterUserLoginData(String email, String password) {
        return LoginUserRequest.builder()
                .email(email)
                .password(password)
                .build();
    }

    public static String getToken(LoginUserRequest requestBody) {
        Response response = given()
                .spec(REQUEST_SPEC)
                .body(requestBody)
                .when()
                .post(USER_LOGIN)
                .then().extract().response();

       return response.then()
                .extract()
                .body().jsonPath().getObject("data", LoginUserResponse.class).getToken();
    }

    public static Response getInfoFromUserProfileResponse(){
        return given()
                .spec(REQUEST_SPEC)
                .header("x-auth-token", getToken(new LoginUserRequest(USER_EMAIL, USER_PASSWORD)))
                .when()
                .get(USER_INFO)
                .then()
                .extract()
                .response();
    }

    public static Response logoutUserResponse(){
        return given()
                .spec(REQUEST_SPEC)
                .when()
                .header("x-auth-token", getToken(new LoginUserRequest(USER_EMAIL, USER_PASSWORD)))
                .delete(USER_LOGOUT)
                .then()
                .extract()
                .response();
    }

    public static Response deleteUserResponse(){
        return given()
                .spec(REQUEST_SPEC)
                .header("x-auth-token", getToken(new LoginUserRequest(USER_EMAIL, USER_PASSWORD)))
                .when()
                .delete(USER_DELETE_ACC)
                .then()
                .extract()
                .response();
    }
}
