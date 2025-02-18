package org.example;
import Files.Payloads;



import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;


public class Dynamicjson {
    @Test
    public void addBook()
    {
        RestAssured.baseURI="http://216.10.245.166";
      String response=  given().
                header("Content-Type","application/json").
                body(Payloads.addBookpayLoad()).
                when().
                post("/Library/Addbook.php").
                then().
                assertThat().
                statusCode(200).
                extract().
                response().asString();
        JsonPath jsonPath=new JsonPath(response);
        String id =jsonPath.get("ID");
        String msg=jsonPath.get("Msg");
        System.out.println(" the ID of the book is "+id+" and status message is "+msg);


    }
}
