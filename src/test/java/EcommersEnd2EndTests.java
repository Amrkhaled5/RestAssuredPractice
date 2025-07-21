import EcommersPOJO.Login;
import EcommersPOJO.LoginResponse;
import EcommersPOJO.OrderDetail;
import EcommersPOJO.Orders;
import files.CommanFunctions;
import groovy.grape.GrapeIvy;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class EcommersEnd2EndTests {
    private String token;
    private String userId;
    private String productId;

    @Test(priority = 1)
    public void LoginFlow(){
        Login loginReq=new Login();
        loginReq.setUserEmail("amr@gmail.com");
        loginReq.setUserPassword("123@asdasdA");

        RequestSpecification req=new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
        RequestSpecification reqLogin=given().spec(req).body(loginReq);
        LoginResponse res=reqLogin.when().post("/api/ecom/auth/login").then()
                .extract().response().as(LoginResponse.class);

        token=res.getToken();
        userId=res.getUserId();
    }

    @Test(priority = 2)
    public void AddProduct(){
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).addHeader("authorization",token).build();
        RequestSpecification reqAddProduct=given().spec(req).param("productName","Laptop")
                .param("productAddedBy",userId).param("productCategory","fashion").param("productSubCategory","shirts")
                .param("productPrice","11500").param("productDescription","lenovo")
                .param("productFor","men");

        String addproductRes=reqAddProduct.when().post("/api/ecom/product/add-product").then().extract().response().asString();
        System.out.println(addproductRes);
        JsonPath js=new JsonPath(addproductRes);
        System.out.println(addproductRes);
        productId=js.getString("productId");
    }

    @Test(priority = 3)
    public void CreatOrder(){
        OrderDetail order=new OrderDetail();
        order.setCountry("EGYPT");
        order.setProductOrderId(productId);
        List<OrderDetail> orderDetails=new ArrayList<OrderDetail>();
        orderDetails.add(order);
        Orders orders=new Orders();
        orders.setOrders(orderDetails);


        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).addHeader("authorization",token).build();
        RequestSpecification creatOrderReq=given().spec(req).body(orders);
        String responseAddOrder=creatOrderReq.when().post("/api/ecom/order/create-order").then().extract().response().asString();
        System.out.println(responseAddOrder);
    }

    @Test(priority = 4)
    public void DeleteOrder(){
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).addHeader("authorization",token).build();
        RequestSpecification deleteOrderReq=given().spec(req).pathParam("productId",productId);
        String deleteProductResponse=deleteOrderReq.when().delete("/api/ecom/product/delete-product/{productId}").then().extract().response().asString();

        JsonPath j= CommanFunctions.stringToJson(deleteProductResponse);
        Assert.assertEquals("Product Deleted Successfully",j.get("message"));
    }
}
