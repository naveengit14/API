package ResquestResponseSPECBuilder;

import PojoClasssesForjsonPayload_SerializationGoogleAPI.GooglePayload;
import PojoClasssesForjsonPayload_SerializationGoogleAPI.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


import static io.restassured.RestAssured.given;

public class SpecBuilder {

    @Test
    public void testSpecBuilder()
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

      /**  RestAssured.baseURI="https://rahulshettyacademy.com";
        Response addPlaceResponse= (Response) given().log().all().queryParam("key","qaclick123")
                .body(gp).when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).extract();
        String response=addPlaceResponse.toString();
        System.out.println(response);
       **/
        //Request  Spec Builder
       RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").setContentType(ContentType.JSON).build();
        //Response Spec Builder
       ResponseSpecification responseSpecification=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
       Response response=given().spec(requestSpecification)
               .body(gp)
               .when()
               .post("maps/api/place/add/json")
               .then().spec(responseSpecification).extract().response();

        String res=response.asString();
        System.out.println("Response after the spec builder"+res);

    }

}
