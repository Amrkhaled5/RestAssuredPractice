package resources;

import Serialization.AddPlaceData;
import Serialization.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public AddPlaceData addPlacePayLoad(String name ,String language,String address){
        AddPlaceData p=new AddPlaceData();
        p.setAccuracy(50);
        p.setAddres(address);
        p.setName(name);
        p.setPhoneNumber("0123123123");
        p.setWebsite("http://google.com");
        p.setLanguage(language);

        Location l=new Location();
        l.setLat("-38.43324");
        l.setLng("60.43324");
        p.setLocation(l);
        List<String> li=new ArrayList<>();
        li.add("shop1");
        li.add("shop2");
        li.add("shop3");
        p.setTypes(li);
        return p;
    }
}
