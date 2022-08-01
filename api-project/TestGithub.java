package Project;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestGithub {
    RequestSpecification reqSpec;
    String token="";

    int id;

    @BeforeClass
    public void setup() {
        reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.github.com")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", token)
                .build();
    }

    @Test(priority=1)
    public void postMethod(){
        String requestBody= "{\"title\": \"TestAPIKey\", \"key\": \"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCpNrtoqX2qSUPNYcq5nH3zDo/hxSTb371kdXXsZtUzVS4SBB25Yox2RxSAde68Sg1vw/1zu1tGsiaA+lTnmN77NOn34TN6IfyUVLwuh4ghx9qBTt/vFh95YOQrpe6gzFmXGhw0NPUgLvPHKi78Y9HQsvSgGJXMgXBaq4xAmRqspdzB7Q1RkavvWx35mIMMf7Vibx/yLlFVtN4Ao7Z4+w62I3vtyEni7SzX2HIEv41UOy47he91SwO8LI6nCG6A5aZ3PYODyjc9b56SA7dIQ1h0zVgGxH0ltGhKj5FIz2mILJClyJOj5Tp11owI6c6Yc0E4hlPLtpZx4kzG3LC6VWj7\"}";
        Response response = given().spec(reqSpec)
                .body(requestBody)
                .when().post("/user/keys");
//        id = response.then().extract().path("id");
        System.out.println(response.getBody().asPrettyString());
        response.then().statusCode(201);


    }

    @Test(priority=2)
    public void getMethod(){
        Response response = given().spec(reqSpec)
                .pathParam("keyId", id)
                .when().get("/user/keys/{keyId}");
        System.out.println(response.getBody().asPrettyString());
        response.then().statusCode(200);

    }
    @Test(priority=3)
    public void deleteMethod(){
        Response response = given().spec(reqSpec)
                .pathParam("keyId", id)
                .when().delete("/user/keys/{keyId}");
        System.out.println(response.getBody().asPrettyString());
        response.then().statusCode(204);
    }

}