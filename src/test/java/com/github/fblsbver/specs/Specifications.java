package com.github.fblsbver.specs;

import com.github.fblsbver.steps.TestProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Specifications {

    public static final RequestSpecification REQUEST_SPEC = new RequestSpecBuilder()
            .setBaseUri(TestProperties.getProperty("baseUrl"))
            .setBasePath(TestProperties.getProperty("basePath"))
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


}
