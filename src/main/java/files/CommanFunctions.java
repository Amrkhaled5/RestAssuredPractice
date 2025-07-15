package files;

import io.restassured.path.json.JsonPath;

public class CommanFunctions {
    public static JsonPath stringToJson(String res){
        JsonPath js=new JsonPath(res);
        return js;
    }
}
