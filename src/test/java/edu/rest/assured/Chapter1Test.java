package edu.rest.assured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.equalTo;


public class Chapter1Test {

    @Test
    public void requestUsZipCode90210() {

        String urlFor90210 = "https://www.zippopotam.us/us/90210";

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
        .when()
        .get(urlFor90210)
        .then().assertThat().body("places[0].'place name'",equalTo("Beverly Hills"));
    }
    
}
