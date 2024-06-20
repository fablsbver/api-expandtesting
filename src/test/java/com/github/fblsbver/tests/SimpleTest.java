package com.github.fblsbver.tests;

import org.junit.jupiter.api.Test;

import static com.github.fblsbver.specs.Specifications.REQUEST_SPEC;
import static io.restassured.RestAssured.given;

public class SimpleTest {

    @Test
    public void healthCheck() {
        given()
                .spec(REQUEST_SPEC)
                .when()
                .get("/health-check")
                .then()
                .statusCode(200);
    }
}
