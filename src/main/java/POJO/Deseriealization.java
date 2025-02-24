package POJO;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class Deseriealization {
    public String TokenGEtFromServer;
   @BeforeMethod
   public void setBaseURI()
   {
       RestAssured.baseURI="https://rahulshettyacademy.com";

   }
                @Test
                public void getClientSecret()
        {
           String token= given()
                    .formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                    .formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                    .formParam("grant_type","client_credentials")
                    .formParam("scope","trust")
                    .log().all()
                    .when()
                    .post("/oauthapi/oauth2/resourceOwner/token")
                    .then().log().all()
                    .assertThat().statusCode(200)
                    .extract().response().asString();
                    JsonPath jsonPath=new JsonPath(token);
            TokenGEtFromServer= jsonPath.getString("access_token");
            System.out.println("Token got from the request is "+TokenGEtFromServer);
//                    String Response2=    given()
//                    .queryParam("access_token",TokenGEtFromServer)
//                    .log().all()
//                    .when()
//                    .get("oauthapi/getCourseDetails")
//                    .then().log().all()
//                .assertThat().statusCode(401)
//                .toString();
//                JsonPath jsonPath2=new JsonPath(Response2);
//                String courseTitle=  jsonPath2.getString("$.courses.api[0].courseTitle");
//                String coursePrice =jsonPath2.getString("$.courses.api[0].price");
//                System.out.println(" here is the details "+courseTitle+" and price "+coursePrice);

        }

    @Test
    public void GetCoursesDetailsWithSecretKey()
    {

        getClientSecret();
        String courseDetails=    given()
                .queryParam("access_token",TokenGEtFromServer)
                .log().all()
                .when()
                .get("oauthapi/getCourseDetails")
                .then().log().all()
                .assertThat().statusCode(401)
                .toString();
    }

    @Test
    public void usingPOJOClasses()
        {
            getClientSecret();
            GetCourses gc=    given()
                    .queryParam("access_token",TokenGEtFromServer)
                    .log().all()
                    .when()
                    .get("oauthapi/getCourseDetails").as(GetCourses.class);
            System.out.println(gc.getCourses().getWebAutomation().get(1).getCourseTitle().toString());
            System.out.println(gc.getCourses().getWebAutomation().get(1).getPrice().toString());
            System.out.println(gc.getLinkedIn());
            List<Api> apiCourses=gc.getCourses().getApi();
            for(int i=0;i< apiCourses.size();i++)
            {
                if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI webservices testing"))
                {
                    System.out.println(apiCourses.get(i).getPrice().toString());
                   // gc.getCourses().getMobile().get(1).getCourseTitle()
                }

            }


        }
        @Test
    public void assignmentToPrintAllWebAutomationCourseTitle()
        {
            getClientSecret();
            GetCourses gc=    given()
                    .queryParam("access_token",TokenGEtFromServer)
                    .log().all()
                    .when()
                    .get("oauthapi/getCourseDetails").as(GetCourses.class);
            List<WebAutomation>webAutomationsList=gc.getCourses().getWebAutomation();
            for(int i=0;i<webAutomationsList.size();i++)
            {
                System.out.println("Courses List for Web Automation  is "+webAutomationsList.get(i).getCourseTitle());

            }



        }

    }

