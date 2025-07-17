import Deserialization.GetCoursesData;
import Deserialization.WebAutomation;
import files.CommanFunctions;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class OAuthWithPOJODeSerialization {
    String Token;
    String client_id="692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com";
    String client_secret="erZOWM9g3UtwNRj340YYaK_W";
    String grant_type="client_credentials";
    String scope="trust";
    List<String> accualCourseTitleAutomationList = Arrays.asList(
            "Selenium Webdriver Java", "Cypress", "Protractor"
    );

    @Test
    public void GetAccesToken(){
        String response = given().formParams("client_id", client_id)
            .formParams("client_secret", client_secret)
            .formParams("grant_type", grant_type)
            .formParams("scope", scope)
            .when().log().all()
            .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

        System.out.println(response);
        JsonPath jsonPath = CommanFunctions.stringToJson(response);
        String accessToken = jsonPath.getString("access_token");
        System.out.println(accessToken);

        GetCoursesData response2=given().queryParams("access_token", accessToken).when()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCoursesData.class);

//        System.out.println(response2.getInstructor());
//        System.out.println(response2.getUrl());
//        System.out.println(response2.getCourses().getApi().get(1).getCourseTitle());
//
        List<WebAutomation> webAutoList=response2.getCourses().getWebAutomation();
        ArrayList<String> myData=new ArrayList<String>();
        for(int i=0;i<webAutoList.size();i++){
            myData.add(webAutoList.get(i).getCourseTitle());
        }

        Assert.assertTrue(myData.equals(accualCourseTitleAutomationList));
    }
}

