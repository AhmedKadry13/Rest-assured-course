package edu.rest.assured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

class AppTest {
    
    @Test
    public void testGeneralSettings(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        String generalLink = "https://shayal.panorama-q.com/api/v1/advertisements";

        given().header("Accept", "application/json")
                .header("x-localization", "ar")
                .header("Cache-Control",  "no-cache")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
        .when().get(generalLink).then().assertThat().statusCode(200);
    }
}
