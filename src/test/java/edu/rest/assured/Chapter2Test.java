package edu.rest.assured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.equalTo;

public class Chapter2Test {

    public String baseUrl = "https://www.zippopotam.us/us/";

    @Test
    public void requestUsZipCode90210_checkContentType() {

        String urlFor90210 = baseUrl.concat("90210");

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
        .when()
        .get(urlFor90210)
        .then().assertThat().contentType(ContentType.JSON);
    }
    
}
