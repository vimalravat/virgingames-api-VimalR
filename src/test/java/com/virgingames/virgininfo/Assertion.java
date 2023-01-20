package com.virgingames.virgininfo;

import com.virgingames.constants.EndPoint;
import com.virgingames.constants.Path;
import com.virgingames.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasKey;


public class Assertion extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://www.virgingames.com";
        RestAssured.registerParser("text/plain", Parser.JSON);
        response = given()
                .header("Content-Type", "application/json")
                .when()
                .basePath(Path.PATH)
                .get(EndPoint.GET_ALL_RECORDS)
                .then().statusCode(200);
    }

    @Test
    public void getAllBingoRecords() {

        RestAssured.registerParser("text/plain", Parser.JSON);
        Response response = given()
                .when()
                .basePath(Path.PATH)
                .get(EndPoint.GET_ALL_RECORDS);
        response.then().statusCode(200);

        response.prettyPrint();
    }

    @Test
    public void test001() {
        response.body("bingoLobbyInfoResource.streams[10]", hasEntry("streamName", "80BallBingoVGN"));
        response.body("bingoLobbyInfoResource.streams[10]", hasEntry("streamId", 3544));
        response.body("bingoLobbyInfoResource.streams[10]", hasEntry("defaultGameFrequency", 240000));
        response.body("bingoLobbyInfoResource.streams[0]", hasEntry("streamName", "Adventure"));
    }
    @Test
    public void test002() {
        response.body("bingoLobbyInfoResource.streams[0]", hasKey("gameRef"));
        response.body("bingoLobbyInfoResource.streams[0]", hasKey("streamId"));
        response.body("bingoLobbyInfoResource.streams[0]", hasKey("streamName"));
        response.body("bingoLobbyInfoResource.streams[0]", hasKey("startTime"));
    }

}
