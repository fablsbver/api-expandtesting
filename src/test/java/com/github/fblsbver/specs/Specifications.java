package com.github.fblsbver.specs;

import com.github.fblsbver.config.App;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    public static final RequestSpecification request = new RequestSpecBuilder()
            .setBaseUri(App.config.baseUrl())
            .setBasePath(App.config.basePath())
            .setContentType(ContentType.JSON)
            .build();


    public static final ResponseSpecification responseSuccess = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    public static final ResponseSpecification responseCreate = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .build();
    public static final ResponseSpecification responseBadRequest = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .build();

    public static final ResponseSpecification responseUnauthorized = new ResponseSpecBuilder()
            .expectStatusCode(401)
            .build();




}
