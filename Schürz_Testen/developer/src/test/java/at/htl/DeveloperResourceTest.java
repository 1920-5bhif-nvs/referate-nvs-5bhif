package at.htl;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.persistence.Table;

import java.sql.SQLOutput;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeveloperResourceTest {

    @Test
    @Order(1)
    public void testPostDeveloperEndpoint(){
        given()
                .header("Content-Type","application/json")
                .body("{\"name\":\"Alex\"}")
        .when()
                .post("/developer")
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
    @Order(2)
    public void testGetDeveloperEndpoint() {
        Response response = given()
                .when().get("/developer/Alex")
                .then()
                    .statusCode(200)
                    .body("name",is("Alex")
                    ).extract().response();
        System.out.println(response.jsonPath().get().toString());
    }

    @Test
    @Order(3)
    public void deleteDeveloperEndpoint() {
        given()
                .header("Content-Type","application/json")
        .when()
                .delete("/developer/1")
        .then()
                .statusCode(200);
    }

}