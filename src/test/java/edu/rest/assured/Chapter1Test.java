package edu.rest.assured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.equalTo;


public class Chapter1Test {

    public String baseUrl = "https://www.zippopotam.us/us/";


    @Test
    public void requestUsZipCode90210_checkLoaction() {

        String urlFor90210 = baseUrl.concat("90210");

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
        .when()
        .get(urlFor90210)
        .then().assertThat().statusCode(200)
        .and().assertThat().body("places[0].'place name'",equalTo("Beverly Hills"));
        
    }

    
}