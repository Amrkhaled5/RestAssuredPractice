import files.CommanFunctions;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class JiraAPISTest {
    String Token="Basic YW1yazQ0MTVAZ21haWwuY29tOkFUQVRUM3hGZkdGMDIyUmduUkdZ" +
            "MllSTW44YU5KejZOWkNqUUt4WWlpYW5FeExvY04zVWdHWXdZbWNubkl5b" +
            "zFvckN3OUlKVmJDT2J5YllQUGk2MVpVQ1hSdS1iQWJweXh3M21GSHdueE" +
            "REOGI2ZndnamdwUjRrbEY3eEhSSDJZNDkxNGEwWmpJb091V2VobDM5TVZ" +
            "2QW1xcHNtMGhkWWlUUXN6MndWZW02U3U5SEgxS2otR3Y0WT0zQ0M2QTJG" +
            "NA";
    String bugId;
    @Test(priority = -1)
    public void creatBug(){

        RestAssured.baseURI="https://amrkhaled.atlassian.net";
        String createBugResponse=given().header("Content-Type","application/json")
                .header("Authorization",Token).body("{\n" +
                        "    \"fields\": {\n" +
                        "       \"project\":\n" +
                        "       {\n" +
                        "          \"key\": \"SCRUM\"\n" +
                        "       },\n" +
                        "       \"summary\": \"User name in Login page has inCorrect place holder\",\n" +
                        "       \"issuetype\": {\n" +
                        "          \"name\": \"Bug\"\n" +
                        "       }\n" +
                        "   }\n" +
                        "}\n").post("rest/api/3/issue").then().assertThat()
                .statusCode(201).contentType("application/json").extract().response().asString();
        JsonPath js = CommanFunctions.stringToJson(createBugResponse);
        bugId=js.getString("id");
    }

    @Test
    public void addAttachment(){
        given()	.pathParam("key", bugId).header("X-Atlassian-Token","no-check")
                .header("Authorization",Token).multiPart("file",new File("C:\\Users\\Amrkh\\Pictures\\WhatsApp Image 2025-07-13 at 21.00.57_cae14b63.jpg"))
                .post("rest/api/3/issue/{key}/attachments").then().assertThat().statusCode(200);
    }
}
