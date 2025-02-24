package JiraAPI;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;
import java.sql.SQLOutput;

public class JIRA {

    @Test
    public void jiraGetIssueByID()
    {
        RestAssured.baseURI="https://qanaveensingh.atlassian.net/";
       String response= given()
                .header("Content-Type","application/json")
                .header("Authorization","Basic bmF2ZWVuc2luZ2guMDBAZ21haWwuY29tOkFUQVRUM3hGZkdGMEFtTVRpQS1UenhLTlE2eTRwc2t5bzVtZVY3c2hVbW4wYVoyRVpzUHEyVTdFejdfdHc4dGhpQzc1M1lUUXFGME1Ta1FqMVF4RHU4akVWSFZiQ2p6SnIxcGZISnJ1Rlg4aXhpQ3BhMmlXVFB6TmFDdmg5OFNKempIRjY2MTZHN01QLUpvczBGRFRrRGl5UGdmU21iSEhqYVFNMHpCby1ZQWtoVjYwdEw2X0xjbz03QzRDNUFFRA")
                .when()
                .get("/rest/api/3/issue/10002")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();


        System.out.println("Response from API is  ----------"+response);
        JsonPath jsonPath=new JsonPath(response);
       String FileName= jsonPath.getString("fields.attachment[0].filename");
        System.out.println("File uploaded in issue name is  ----------"+FileName);

    }


@Test
    public void createIssueJira()
{
    RestAssured.baseURI="https://qanaveensingh.atlassian.net/";
   String createResponse= given()
            .header("Content-Type","application/json")
            .header("Authorization","Basic bmF2ZWVuc2luZ2guMDBAZ21haWwuY29tOkFUQVRUM3hGZkdGMEFtTVRpQS1UenhLTlE2eTRwc2t5bzVtZVY3c2hVbW4wYVoyRVpzUHEyVTdFejdfdHc4dGhpQzc1M1lUUXFGME1Ta1FqMVF4RHU4akVWSFZiQ2p6SnIxcGZISnJ1Rlg4aXhpQ3BhMmlXVFB6TmFDdmg5OFNKempIRjY2MTZHN01QLUpvczBGRFRrRGl5UGdmU21iSEhqYVFNMHpCby1ZQWtoVjYwdEw2X0xjbz03QzRDNUFFRA")
            .body("{ \"fields\": {\n" +
                    "        \"project\": {\n" +
                    "            \"key\": \"SCRUM\"\n" +
                    "        },\n" +
                    "        \"summary\": \"Created issue from RestAssured.\",\n" +
                    "        \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\n" +
                    "        \"issuetype\": {\n" +
                    "            \"name\": \"Bug\"\n" +
                    "        }\n" +
                    "    }\n" +
                    "}")
            .when()
            .log().all()
            .post("/rest/api/2/issue")
            .then().log().all().assertThat().statusCode(201)
            .extract().response().asString();

JsonPath js=new JsonPath(createResponse);
String IssueID=js.getString("id");
System.out.println("New issue created in JIRA using RestAssured with "+IssueID);



}
@Test
    public void addImageJira()
{
    RestAssured.baseURI="https://qanaveensingh.atlassian.net/";
    String createResponse= given()
            .header("Content-Type","application/json")
            .header("Authorization","Basic bmF2ZWVuc2luZ2guMDBAZ21haWwuY29tOkFUQVRUM3hGZkdGMEFtTVRpQS1UenhLTlE2eTRwc2t5bzVtZVY3c2hVbW4wYVoyRVpzUHEyVTdFejdfdHc4dGhpQzc1M1lUUXFGME1Ta1FqMVF4RHU4akVWSFZiQ2p6SnIxcGZISnJ1Rlg4aXhpQ3BhMmlXVFB6TmFDdmg5OFNKempIRjY2MTZHN01QLUpvczBGRFRrRGl5UGdmU21iSEhqYVFNMHpCby1ZQWtoVjYwdEw2X0xjbz03QzRDNUFFRA")
            .body("{ \"fields\": {\n" +
                    "        \"project\": {\n" +
                    "            \"key\": \"SCRUM\"\n" +
                    "        },\n" +
                    "        \"summary\": \"window link not working:RestAssured.\",\n" +
                    "        \"description\": \"Create new issue and attachment in issue \",\n" +
                    "        \"issuetype\": {\n" +
                    "            \"name\": \"Bug\"\n" +
                    "        }\n" +
                    "    }\n" +
                    "}")
            .when()
            .log().all()
            .post("/rest/api/2/issue")
            .then().log().all().assertThat().statusCode(201)
            .extract().response().asString();

    JsonPath js=new JsonPath(createResponse);
    String issueID=js.getString("id");
    System.out.println("New issue created in JIRA using RestAssured with "+issueID);



    //ADDING attachment to ISSUE



   String responseAttachment= given()
           // .header("Content-Type","multipart/form-data")
            .pathParam("key",issueID)
            .header("Authorization","Basic bmF2ZWVuc2luZ2guMDBAZ21haWwuY29tOkFUQVRUM3hGZkdGMEFtTVRpQS1UenhLTlE2eTRwc2t5bzVtZVY3c2hVbW4wYVoyRVpzUHEyVTdFejdfdHc4dGhpQzc1M1lUUXFGME1Ta1FqMVF4RHU4akVWSFZiQ2p6SnIxcGZISnJ1Rlg4aXhpQ3BhMmlXVFB6TmFDdmg5OFNKempIRjY2MTZHN01QLUpvczBGRFRrRGl5UGdmU21iSEhqYVFNMHpCby1ZQWtoVjYwdEw2X0xjbz03QzRDNUFFRA")
            .header("X-Atlassian-Token","no-check")
            .multiPart("file",new File("\\C:\\Users\\qanav\\Pictures\\Shiva.jpg"))
           .log().all()
            .post("rest/api/3/issue/{key}/attachments")
           .then()
           .log().all()
           .assertThat()
           .statusCode(200)
           .extract().asString();
    System.out.println(" Reponse after the attachment" +responseAttachment);
    JsonPath js2=new JsonPath(responseAttachment);
    String fileName=js2.getString("filename");
    System.out.println(" file uploaded to Issue "+issueID+ "is named as "+fileName);


}

}
