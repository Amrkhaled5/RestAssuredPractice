package files;

public class BodyData {
    public static String addPlaceBody(){
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"AIM\",\n" +
                "  \"phone_number\": \"11122233333\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}\n";
    }
    public static String updatePlaceBody(String PlaceId,String newAddres){

        return "{ \n" +
                "\"place_id\":\""+PlaceId+"\", \n" +
                "\"address\":\""+newAddres+"\", \n" +
                "\"key\":\"qaclick123\" \n" +
                "} ";
    }
}
