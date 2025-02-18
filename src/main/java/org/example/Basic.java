package org.example;
import Files.ComplexJsonMock;
import Files.Payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static  org.hamcrest.Matchers.*;


public class Basic {
    @Test
    public void getresponse()
    {

RestAssured.baseURI="https://rahulshettyacademy.com";
given().log().all().queryParam("key","qaclick123").headers("Content-Type","application/json").body("{\n" +
        "  \"location\": {\n" +
        "    \"lat\": -38.383494,\n" +
        "    \"lng\": 33.427362\n" +
        "  },\n" +
        "  \"accuracy\": 50,\n" +
        "  \"name\": \"Frontline house\",\n" +
        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
        "  \"address\": \"29, side layout, cohen 09\",\n" +
        "  \"types\": [\n" +
        "    \"shoe park\",\n" +
        "    \"shop\"\n" +
        "  ],\n" +
        "  \"website\": \"http://google.com\",\n" +
        "  \"language\": \"French-IN\"\n" +
        "}").when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200);

    }
    @Test
    public void Assertbody()
    {

        RestAssured.baseURI="https://rahulshettyacademy.com";
        given().log().all().queryParam("key","qaclick123").headers("Content-Type","application/json").body("{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Frontline house\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}").when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"));

    }
    @Test
    public void AssertbodyfromPayload()
    {

        RestAssured.baseURI="https://rahulshettyacademy.com";
        given().log().all().queryParam("key","qaclick123").headers("Content-Type","application/json").body(Payloads.AddPlace()).when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"));

    }
    @Test
    public void Jsonpath()
    {

        RestAssured.baseURI="https://rahulshettyacademy.com";
        // will remove the log().all() to tets the body extraction
       String response= given().queryParam("key","qaclick123").headers("Content-Type","application/json").body(Payloads.AddPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().asString();
        System.out.println("The whole response :-->  "+response);

        JsonPath jp=new JsonPath(response);

        System.out.println(" the value of the key place_id :"+jp.getString("place_id"));
    }
    @Test
    public void complexJson()
    {
        JsonPath jsonPath=new JsonPath(ComplexJsonMock.responsejson());
        System.out.println("The Total Number of courses: "+jsonPath.getInt("courses.size()"));
        System.out.println("The Total Title: "+jsonPath.getString("courses[2].title"));
        System.out.println("The Amount : "+jsonPath.getInt("dashboard.purchaseAmount"));


    }
    @Test
    public void allTitle()
    {
        JsonPath jsonPath=new JsonPath(ComplexJsonMock.responsejson());
        int size=jsonPath.getInt("courses.size()");
        for (int i=0;i<size;i++)
        {
           String Titles= jsonPath.getString("courses["+i+"]");
            System.out.println(" All the Title in courses is "+ Titles);

        }
        JsonPath jsonPath2=new JsonPath(ComplexJsonMock.responsejson());
        System.out.println();
    }
    @Test
    public void numberofcopiesSold()
    {
        JsonPath jsonPath=new JsonPath(ComplexJsonMock.responsejson());
        int size=jsonPath.get("courses.size()");

        for (int i=0;i<size;i++)
        {
            String courseTitle=jsonPath.get("courses["+i+"].title");
//            int price=jsonPath.get("courses["+i+"].price");
//            int copies=jsonPath.get("courses["+i+"].copies");
//            System.out.println("The course sold for "+courseTitle+" with price "+price+" number of copies "+copies);
            if(courseTitle.equalsIgnoreCase("Cypress")){
                int RPACopies=jsonPath.get("courses["+i+"].copies");
                System.out.println("The course sold for RPA is "+RPACopies);
                break;
            }
            else {

                System.out.println(" No courses with that Name found on index " +i);
            }

        }


        }

    }



