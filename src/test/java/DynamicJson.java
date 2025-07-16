import files.BodyData;
import files.CommanFunctions;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {
    @Test(dataProvider = "BookData")
    public void addBook(String isbn,String aise){
        RestAssured.baseURI="http://216.10.245.166";

        String response=given().log().all().header("Content-Type","application/json").
        body(BodyData.addBookBody(isbn,aise)).when().post("/Library/Addbook.php").
                then().assertThat().statusCode(200).extract().response().asString();

        JsonPath js= CommanFunctions.stringToJson(response);
        String id=js.get("ID");
        System.out.println(id);
    }

    @DataProvider(name = "BookData")
    public Object[][] getBookData(){
        return new Object[][]{{"amr","123"},{"ziad","234"},{"khaled","456"}};
    }
}
