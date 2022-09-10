package edu.rest.assured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;

import static org.hamcrest.Matchers.hasSize;

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

    @Test
    public void requestUsZipCode90210_logRequestAndResponse() {

        String urlFor90210 = baseUrl.concat("90210");

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
        .log().all()
        .when()
        .get(urlFor90210)
        .then().log().body();
    }

    @Test
    public void requestUsZipCode90210_checkLoaction() {

        String urlFor90210 = baseUrl.concat("90210");

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
        .when()
        .get(urlFor90210)
        .then().assertThat().statusCode(200)
        .and().assertThat().body("places[0].state",equalTo("California"));
        
    }


    @Test
    public void requestUsZipCode90210_checkListOfLoactionNames() {

        String urlFor90210 = baseUrl.concat("90210");

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
        .when()
        .get(urlFor90210)
        .then().assertThat().statusCode(200)
        .and().assertThat().body("places.'place name'",hasItem("Beverly Hills"));
        
    }

    @Test
    public void requestUsZipCode90210_checkCountOfLoactionNames() {

        String urlFor90210 = baseUrl.concat("90210");

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
        .when()
        .get(urlFor90210)
        .then().assertThat().statusCode(200)
        .and().assertThat().body("places.'place name'",hasSize(1));
        
    }

    @Test
    public void requestUsZipCode90210_checkCountOfLoactionNamesByNot() {

        String urlFor90210 = baseUrl.concat("90210");

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
        .when()
        .get(urlFor90210)
        .then().assertThat().statusCode(200)
        .and().assertThat().body("places.'place name'",not(hasSize(2)));
        
    }

    
    
}
