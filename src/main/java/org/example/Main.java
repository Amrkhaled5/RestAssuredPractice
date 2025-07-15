package org.example;

import files.BodyData;
import files.CommanFunctions;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Main {
    public static void main(String[] args) {
        //Validate if Api working as expected

        //Given -> all input details
        //When -> Submit the API - resource,http method
        //Then -> validate the response

        //Add place -> Update place with new Addres -> Get place to validate if new addres is present in response

        //Add Place
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(BodyData.addPlaceBody())
                .when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
                .body("scope",equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();

        JsonPath js=CommanFunctions.stringToJson(response); // Parse String to Json
        String PlaceId=js.getString("place_id");

        //Update Place
        String newAddres="EGYPT";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(BodyData.updatePlaceBody(PlaceId,newAddres))
                .when().put("/maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
                .body("msg",equalTo("Address successfully updated"));

        //Get Place

        String getPlaceResponse= given().log().all().queryParam("key","qaclick123").queryParam("place_id",PlaceId)
                .when().get("/maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract().response().asString();

        JsonPath js2= CommanFunctions.stringToJson(getPlaceResponse); // Parse String to Json
        String actualAddress=js2.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress,newAddres);

    }
}