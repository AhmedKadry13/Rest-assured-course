package edu.rest.assured;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.testng.Assert.assertEquals;

public class Chapter4Test {

    private static RequestSpecification requestSpecs;
    private static ResponseSpecification responseSpecs;

    public static String baseUrl = "https://www.zippopotam.us/";

    @BeforeClass
    public static void createRequestSpecification() {
        requestSpecs = new RequestSpecBuilder().setBaseUri(baseUrl)
                    .build();
    }

    @BeforeClass
    public static void createResponseSpecification() {
        responseSpecs = new ResponseSpecBuilder()
                        .expectStatusCode(200)
                        .expectContentType(ContentType.JSON)
                        .build();
    }

    @Test
    public void requestUsZipCode90210_useRequestSpecs() {
        given()
            .spec(requestSpecs)
        .when()
            .get("us/90210")
        .then()
            .assertThat().statusCode(200);
    }

    @Test
    public void requestUsZipCode90210_useResponseSpecs() {
        given()
            .spec(requestSpecs)
        .when()
            .get("us/90210")
        .then()
            .spec(responseSpecs)
            .and()
            .assertThat().body("places[0].'place name'",equalTo("Beverly Hills"));
    }

    @Test
    public void requestUsZipCode90210_extractPlaceName() {
        String expectedPlaceName = "Beverly Hills";
        String placeName = 
        given()
            .spec(requestSpecs)
        .when()
            .get("us/90210")
        .then()
            .spec(responseSpecs)
            .and()
            .extract().path("places[0].'place name'");
        
        assertEquals(placeName.equals(expectedPlaceName), true);
    }



}
