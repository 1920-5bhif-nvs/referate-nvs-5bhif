package at.htl;

import at.htl.model.Item;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.annotation.PostConstruct;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ItemResourceTest {

    private String uri;

    @PostConstruct
    public void init() {
        uri = "http://localhost:8080";
    }

    @Test
    public void testItemEndpointSize(){
        given()
                .when().get(uri + "/items")
                .then()
                .statusCode(200)
                .body("size()", is(2));
    }

    @Test
    public void testItemEndpointPostOK(){
        given()
                .when()
                .header("Content-Type", "application/json")
                .body("{ \"name\": \"Pizza\", \"price\": 1.5 }")
                .post(uri + "/items")
                .then()
                .statusCode(200);
    }

    @Test
    public void testItemEndpointPostBAD_REQUEST(){
        given()
                .when()
                .header("Content-Type", "application/json")
                .body("{ \"name\": \"\", \"price\": 1.5 }")
                .post(uri + "/items")
                .then()
                .statusCode(400);
    }
    
}
