package com.github.fblsbver.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static com.github.fblsbver.endpoints.EndPoints.BASE_PATH;
import static com.github.fblsbver.steps.TestProperties.getProperty;

public class Specifications {
    public static final RequestSpecification REQUEST_SPEC = new RequestSpecBuilder()
            .setBaseUri(getProperty("baseUrl"))
            .setBasePath(BASE_PATH)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

}
