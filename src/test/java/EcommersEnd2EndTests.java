import EcommersPOJO.Login;
import EcommersPOJO.LoginResponse;
import groovy.grape.GrapeIvy;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class EcommersEnd2EndTests {

    @Test
    public void LoginFlow(){
        Login loginReq=new Login();
        loginReq.setUserEmail("amr@gmail.com");
        loginReq.setUserPassword("123@asdasdA");

        RequestSpecification req=new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
        RequestSpecification reqLogin=given().log().all().spec(req).body(loginReq);
        LoginResponse res=reqLogin.when().post("/api/ecom/auth/login").then().log().all()
                .extract().response().as(LoginResponse.class);

        System.out.println(res.getMessage());
        System.out.println(res.getToken());
        System.out.println(res.getUserId());
    }
}
