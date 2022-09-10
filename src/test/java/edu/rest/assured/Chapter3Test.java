package edu.rest.assured;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.equalTo;

public class Chapter3Test {

    public String baseUrl = "https://www.zippopotam.us/";

    @DataProvider(name = "testData")
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][] {
            { "us", "90210", "Beverly Hills" },
            { "us", "12345", "Schenectady" },
            { "ca", "B2R", "Waverley" }
        };
    }

    @Test(dataProvider = "testData")
    public void requestUsZipCodes_FromDataProvider(String countryCode, String zipCode, String expectedPlaceName) {

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
            .pathParam("countryCode", countryCode)
            .pathParam("zipCode", zipCode)
        .when()
            .get("https://www.zippopotam.us/{countryCode}/{zipCode}")
        .then()
            .assertThat().statusCode(200)
            .and().assertThat().body("places[0].'place name'",equalTo(expectedPlaceName));
    }

    @Test(dataProvider = "testData")
    public void requestUsZipCodes_FromDataProvider_alternative(String countryCode, String zipCode, String expectedPlaceName) {

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
            .baseUri(baseUrl)
            .basePath(countryCode + "/" + zipCode)
        .when()
            .get("")
        .then()
            .assertThat().statusCode(200)
            .and().assertThat().body("places[0].'place name'",equalTo(expectedPlaceName));
    }

    
}
