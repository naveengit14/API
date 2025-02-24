package PojoClasssesForjsonPayload_SerializationGoogleAPI;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Serialization {


    @Test
    public void addDataFromSerialization()
    {

        GooglePayload gp=new GooglePayload();
        gp.setAccuracy(50);
        gp.setAddress("699 magnolia dr,12345");
        gp.setLanguage("Hindi-IN");
        gp.setPhone_number("+9112346789");
        gp.setWebsite("https://google.com");
        gp.setName("First name");
        List<String> list=new ArrayList<>();
        list.add("shoes park");
        list.add("shop");
        gp.setTypes(list);
        Location location=new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        gp.setLocation(location);

        RestAssured.baseURI="https://rahulshettyacademy.com";
      Response addPlaceResponse= (Response) given().log().all().queryParam("key","qaclick123")
                .body(gp).when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).extract();
                String response=addPlaceResponse.toString();
                System.out.println(response);

    }

}


