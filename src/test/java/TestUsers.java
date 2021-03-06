import businessobjects.Users;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class TestUsers {

    public static final String URI = "http://jsonplaceholder.typicode.com";
    public static final String USERS_PATH = "/users";

    @BeforeTest
    public void initTest(){
        RestAssured.baseURI = URI;
    }

    @Test
    public void checkStatusCode(){
        Response rp = given().get(USERS_PATH).andReturn();
        int actualStatusCode = rp.getStatusCode();
        System.out.println(actualStatusCode);
        Assert.assertEquals(actualStatusCode, 200);
    }

    @Test
    public void checkResponseHeader(){
        Response rp = given().get(USERS_PATH).andReturn();
        String valueOfContentTypeHeader = rp.getHeader("content-type");
        Assert.assertTrue(valueOfContentTypeHeader != null);
        Assert.assertTrue(valueOfContentTypeHeader.equals("application/json; charset=utf-8"));
    }

    @Test
    public void checkResponseBody() {
        Response rp = given().get(USERS_PATH).andReturn();
        Users[] users = rp.as(Users[].class);
        Assert.assertEquals(users.length, 10);
    }
}
