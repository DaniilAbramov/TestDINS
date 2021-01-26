package io.swagger.phoneBook;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class PhoneBookTest {


    @Test
    public void getUsersTest() {
        RestAssured.given()
                .log().all()
                .baseUri("http://localhost:8080")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .get()
                .then().log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response()
                .prettyPrint();
    }

    @Test
    public void postUserTest() {
        RestAssured.given()
                .log().all()
                .baseUri("http://localhost:8080")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"id\": 3,\n" +
                        "    \"firstName\": \"Hodor\",\n" +
                        "    \"lastName\": \"Hodor\"\n" +
                        "  }")
                .post()
                .then().log().body()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().response()
                .prettyPrint();
    }

    @Test
    public void getUserByIdTest() {
        RestAssured.given()
                .log().all()
                .baseUri("http://localhost:8080")
                .basePath("/users/{userId}")
                .pathParam("userId", 2)
                .contentType(ContentType.JSON)
                .get()
                .then().log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response()
                .prettyPrint();
    }

    @Test
    public void userPutByIdTest() {
        RestAssured.given()
                .log().all()
                .baseUri("http://localhost:8080")
                .basePath("/users/{userId}")
                .pathParam("userId", 2)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"id\": 2,\n" +
                        "    \"firstName\": \"Hodor\",\n" +
                        "    \"lastName\": \"Stark\"\n" +
                        "  }")
                .put()
                .then().log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response()
                .prettyPrint();
    }
    @Test
    public void getUserContactByIdTest() {
        RestAssured.given()
                .log().all()
                .baseUri("http://localhost:8080")
                .basePath("/users/{userId}/contacts")
                .pathParam("userId", 1)
                .contentType(ContentType.JSON)
                .get()
                .then().log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response()
                .prettyPrint();
    }


    @Test
    public void createContactUserByIdTest() {
        RestAssured.given()
                .log().all()
                .baseUri("http://localhost:8080")
                .basePath("/users/{userId}/contacts")
                .pathParam("userId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 3,\n" +
                        "  \"firstName\": \"Hodor\",\n" +
                        "  \"lastName\": \"Hodor\",\n" +
                        "  \"phone\": \"7999999999\",\n" +
                        "  \"email\": \"Hodor@hodor.ru\"\n" +
                        "}")
                .post()
                .then().log().body()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().response()
                .prettyPrint();
    }

    // Тест с ошибкой 500
    @Test
    public void deleteUserByIdTest() {
        RestAssured.given()
                .log().all()
                .baseUri("http://localhost:8080")
                .basePath("/users/{userId}")
                .pathParam("userId", 1)
                .contentType(ContentType.JSON)
                .delete()
                .then().log().body()
                .statusCode(HttpStatus.SC_ACCEPTED)
                .extract().response()
                .prettyPrint();
    }

    //тест с ошибкой 404 (в userId передается contactId и наоборот)
    @Test
    public void getContactByIdUserByIdTest() {
        RestAssured.given()
                .log().all()
                .baseUri("http://localhost:8080")
                .basePath("/users/{userId}/contacts/{contactId}")
                .pathParam("userId", 1)
                .pathParam("contactId", 2)
                .contentType(ContentType.JSON)
                .get()
                .then().log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response()
                .prettyPrint();
    }

    //тест с ошибкой 404 (в userId передается contactId и наоборот)
    @Test
    public void putContactByIdUserByIdTest() {
        RestAssured.given()
                .log().all()
                .baseUri("http://localhost:8080")
                .basePath("/users/{userId}/contacts/{contactId}")
                .pathParam("userId", 2)
                .pathParam("contactId", 2)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 2,\n" +
                        "  \"firstName\": \"Jonh\",\n" +
                        "  \"lastName\": \"Snow\",\n" +
                        "  \"phone\": \"1231231111\",\n" +
                        "  \"email\": \"jonn@mail.ru\"\n" +
                        "}")
                .when().put()
                .then().log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response()
                .prettyPrint();
    }


}
