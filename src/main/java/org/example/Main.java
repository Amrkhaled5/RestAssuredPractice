package org.example;

import Serialization.AddPlaceData;
import Serialization.Location;
import files.BodyData;
import files.CommanFunctions;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Main {
    public static void main(String[] args) {
        //Validate if Api working as expected

        //Given -> all input details
        //When -> Submit the API - resource,http method
        //Then -> validate the response

        //Add place -> Update place with new Addres -> Get place to validate if new addres is present in response

        RestAssured.baseURI="https://rahulshettyacademy.com";

        //Using Serialization to send data to body
        AddPlaceData p=new AddPlaceData();
        p.setAccuracy(50);
        p.setAddres("Cairo,EGYPT");
        p.setName("AIM");
        p.setPhoneNumber("0123123123");
        p.setWebsite("http://google.com");
        p.setLanguage("French");
        Location l=new Location();
        l.setLat("-38.43324");
        l.setLng("60.43324");
        p.setLocation(l);
        List<String> li=new ArrayList<>();
        li.add("shop1");
        li.add("shop2");
        li.add("shop3");
        p.setTypes(li);

        //Add Place
        RequestSpecification ReqSpec=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").setContentType(ContentType.JSON).build();
        ResponseSpecification ResSpec=new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
        //        queryParam("key","qaclick123").header("Content-Type","application/json")
        String response=given().log().all().spec(ReqSpec)
                .body(p)
                .when().post("/maps/api/place/add/json").then().spec(ResSpec)
                .body("scope",equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();

        JsonPath js=CommanFunctions.stringToJson(response); // Parse String to Json
        String PlaceId=js.getString("place_id");

        //Update Place
        String newAddres="EGYPT";
        given().log().all().spec(ReqSpec)
                .body(BodyData.updatePlaceBody(PlaceId,newAddres))
                .when().put("/maps/api/place/update/json").then().spec(ResSpec)
                .body("msg",equalTo("Address successfully updated"));

        //Get Place
        String getPlaceResponse= given().log().all().queryParam("key","qaclick123").queryParam("place_id",PlaceId)
                .when().get("/maps/api/place/get/json").then().spec(ResSpec).extract().response().asString();

        JsonPath js2= CommanFunctions.stringToJson(getPlaceResponse); // Parse String to Json
        String actualAddress=js2.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress,newAddres);

    }
}