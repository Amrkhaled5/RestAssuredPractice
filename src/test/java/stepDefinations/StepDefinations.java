package stepDefinations;

import Serialization.AddPlaceData;
import Serialization.Location;
import files.CommanFunctions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StepDefinations extends Utils {
    RequestSpecification res;
    ResponseSpecification ResSpec;
    Response response;
    TestDataBuild p=new TestDataBuild();
    @Given("Add place payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws FileNotFoundException {

        res=given().spec(requestSpescification()).body(p.addPlacePayLoad(name ,language,address));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resourse,String method) {

        APIResources resourceAPI=APIResources.valueOf(resourse);


        ResSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if(method.equalsIgnoreCase("POST")) {
            response = res.when().post(resourceAPI.getResource()).then().spec(ResSpec)
                    .body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response();
        } else if (method.equalsIgnoreCase("get")) {
            response = res.when().get(resourceAPI.getResource()).then().spec(ResSpec)
                    .body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response();
        }

    }
    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        assertEquals(response.getStatusCode(),200);

    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String Expectedvalue) {
        String resp=response.asString();
        JsonPath j=new JsonPath(resp);
        assertEquals(j.get(key).toString(),Expectedvalue);
    }
}
