package resources;

public enum APIResources {
    addPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");

    private String resourse;
    APIResources(String resourse){
        this.resourse=resourse;
    }
    public String getResource(){
        return resourse;
    }
}
