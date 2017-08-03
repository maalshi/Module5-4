import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class Test1 {

    @BeforeTest
    public void initTest(){
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
    }

    @Test
    public void checkStatusCode(){
        Response rp = given().get("/posts").andReturn();
        //given().headers();
        int actualStatusCode = rp.getStatusCode();
        System.out.println(actualStatusCode);
        Assert.assertEquals(actualStatusCode, 200);
    }

    @Test
    public void checkResponseHeader(){
        Response rp = given().get("/posts").andReturn();
        String valueOfContentTypeHeader = rp.getHeader("content-type");
        Assert.assertTrue(valueOfContentTypeHeader.contains("application/json"));
    }

    @Test
    public void checkResponseBody() {
        Response rp = given().get("/posts").andReturn();
        Post[] posts = rp.as(Post[].class);
        Assert.assertEquals(posts.length, 100);
    }
}
