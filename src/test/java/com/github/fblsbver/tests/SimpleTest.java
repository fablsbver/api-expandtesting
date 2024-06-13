package com.github.fblsbver.tests;

import org.junit.jupiter.api.Test;

import static com.github.fblsbver.specs.Specifications.request;
import static com.github.fblsbver.specs.Specifications.responseSuccess;
import static io.restassured.RestAssured.given;

public class SimpleTest {

    @Test
    public void healthCheck() {
        given()
                .spec(request)
                .when()
                .get("/health-check")
                .then()
                .spec(responseSuccess);
    }
}
