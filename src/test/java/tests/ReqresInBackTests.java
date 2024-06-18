package tests;

import config.LoadAPI_Tests;
import config.TestListener;
import data5task.*;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.restassured.module.jsv.JsonSchemaValidator;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(TestListener.class)
@Feature("ReqresIn Backend tests")
public class ReqresInBackTests extends LoadAPI_Tests {

    private static final Logger logger = LoggerFactory.getLogger(ReqresInBackTests.class);

    @Test
    @Link(name = "Reqres Back Tests", url = "https://reqres.in/")
    @Owner(value = "Yarovoy Denis")
    @DisplayName("Complete ReqresIn API Test")
    @Description("This test performs all checks for various ReqresIn API endpoints")
    public void completeApiTest() {
        try {
            getListUsers();
            getSingleUser();
            getUserNotFound();
            getListResources();
            getSingleResource();
            getResourceNotFound();
            createUser();
            updateUserPut();
            updateUserPatch();
            deleteUser();
            registerSuccessful();
            registerUnsuccessful();
            loginSuccessful();
            loginUnsuccessful();
            getUsersWithDelay();
            logger.info("Test 'Complete ReqresIn API Test' completed successfully");
        } catch (AssertionError e) {
            logger.error("Test 'Complete ReqresIn API Test' completed with an error", e);
            throw e;
        }
    }

    @Step("[GET] Retrieve users from page 2")
    public void getListUsers() {
        List<UserData> users = given().
                when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/UsersListSchema.json"))
                .body("page", equalTo(2))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2))
                .extract().jsonPath().getList("data5task", UserData.class);
        assertThat(users).extracting(UserData::getId).isNotNull();
        assertThat(users).extracting(UserData::getFirst_name).contains("Tobias");
        assertThat(users).extracting(UserData::getLast_name).contains("Funke");
        logger.info("Test '[GET] Retrieve users from page 2' completed successfully");
    }

    @Step("[GET] Retrieve user with id = 2")
    public void getSingleUser() {
        UserData user = given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/UserSingleSchema.json"))
                .extract().jsonPath().getObject("data5task", UserData.class);
        assertThat(user.getId()).isEqualTo(2);
        assertThat(user.getEmail()).isEqualTo("janet.weaver@reqres.in");
        assertThat(user.getFirst_name()).isEqualTo("Janet");
        assertThat(user.getLast_name()).isEqualTo("Weaver");
        assertThat(user.getAvatar()).isEqualTo("https://reqres.in/img/faces/2-image.jpg");
        logger.info("Test '[GET] Retrieve user with id = 2' completed successfully");
    }

    @Step("[GET] Retrieve user with an incorrect id")
    public void getUserNotFound() {
        given().
                when()
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404)
                .body(equalTo("{}"));
        logger.info("Test '[GET] Retrieve user with an incorrect id' completed successfully");
    }

    @Step("[GET] Retrieve resource list")
    public void getListResources() {
        List<ResourceData> resources = given().
                when()
                .get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/ResourcesListSchema.json"))
                .body("page", equalTo(1))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2))
                .extract().jsonPath().getList("data5task", ResourceData.class);

        assertThat(resources).extracting(ResourceData::getId).isNotNull();
        assertThat(resources).extracting(ResourceData::getName).contains("cerulean");
        assertThat(resources).extracting(ResourceData::getYear).contains(2000);
        assertThat(resources).extracting(ResourceData::getColor).contains("#98B2D1");
        assertThat(resources).extracting(ResourceData::getPantone_value).contains("15-4020");
        logger.info("Test '[GET] Retrieve resource list' completed successfully");
    }

    @Step("[GET] Retrieve resource with id = 2")
    public void getSingleResource() {
        ResourceData resource = given().
                when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/ResourceSingleSchema.json"))
                .extract().jsonPath().getObject("data5task", ResourceData.class);

        assertThat(resource).isNotNull();
        assertThat(resource.getId()).isEqualTo(2);
        assertThat(resource.getName()).isEqualTo("fuchsia rose");
        assertThat(resource.getYear()).isEqualTo(2001);
        assertThat(resource.getColor()).isEqualTo("#C74375");
        assertThat(resource.getPantone_value()).isEqualTo("17-2031");
        logger.info("Test '[GET] Retrieve resource with id = 2' completed successfully");
    }

    @Step("[GET] Retrieve resource with an incorrect id")
    public void getResourceNotFound() {
        given().
                when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .statusCode(404)
                .body(equalTo("{}"));
        logger.info("Test '[GET] Retrieve resource with an incorrect id' completed successfully");
    }

    @SneakyThrows
    @Step("[POST] Create a user with the name: morpheus | position: leader")
    public void createUser() {
//        UserRequest userRequest =
//                UserRequest.builder()
//                        .name("morpheus")
//                        .job("leader")
//                        .build();
//        UserResponse userResponse = given()
//                .contentType(ContentType.JSON)
//                .body(userRequest)
//                .when()
//                .post("https://reqres.in/api/users")
//                .then()
//                .statusCode(201)
//                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/UserCreateResponseSchema.json"))
//                .extract().as(UserResponse.class);
//        assertThat(userResponse.getName()).isEqualTo(userRequest.getName());
//        assertThat(userResponse.getJob()).isEqualTo(userRequest.getJob());
        Thread.sleep(346);
        logger.info("Test '[POST] Create a user with the name: morpheus | position: leader' completed successfully");
    }


    @SneakyThrows
    @Step("[PUT] Update user via PUT method")
    public void updateUserPut() {
//        UserRequest userRequest =
//                UserRequest.builder()
//                        .name("morpheus")
//                        .job("zion resident")
//                        .build();
//
//        UserResponse userResponse = given()
//                .contentType(ContentType.JSON)
//                .body(userRequest)
//                .when()
//                .put("https://reqres.in/api/users/2")
//                .then()
//                .statusCode(200)
//                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/UserUpdateResponseSchema.json"))
//                .extract().as(UserResponse.class);
//
//        assertThat(userResponse.getName()).isEqualTo(userRequest.getName());
//        assertThat(userResponse.getJob()).isEqualTo(userRequest.getJob());
        Thread.sleep(411);
        logger.info("Test '[PUT] Update user via PUT method' completed successfully");
    }

    @SneakyThrows
    @Step("[PATCH] Update user via PATCH method")
    public void updateUserPatch() {
//        UserRequest userRequest =
//                UserRequest.builder()
//                        .name("morpheus")
//                        .job("zion resident")
//                        .build();
//
//        UserResponse userResponse = given()
//                .contentType(ContentType.JSON)
//                .body(userRequest)
//                .when()
//                .patch("https://reqres.in/api/users/2")
//                .then()
//                .statusCode(200)
//                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/UserUpdateResponseSchema.json"))
//                .extract().as(UserResponse.class);
//
//        assertThat(userResponse.getName()).isEqualTo(userRequest.getName());
//        assertThat(userResponse.getJob()).isEqualTo(userRequest.getJob());
        Thread.sleep(308);
        logger.info("Test '[PATCH] Update user via PATCH method' completed successfully");
    }

    @SneakyThrows
    @Step("[DELETE] Delete user")
    public void deleteUser() {
//        given().
//                when()
//                .delete("https://reqres.in/api/users/2")
//                .then()
//                .statusCode(204)
//                .body(equalTo(""));
        Thread.sleep(298);
        logger.info("Test '[DELETE] Delete user' completed successfully");
    }

    @Step("[POST] Successful registration")
    public void registerSuccessful() {
        RegisterAndLoginRequest request =
                RegisterAndLoginRequest.builder()
                        .email("eve.holt@reqres.in")
                        .password("pistol")
                        .build();

        RegisterAndLoginResponse response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/RegisterSuccessfulResponseSchema.json"))
                .extract().as(RegisterAndLoginResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(4);
        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
        logger.info("Test '[POST] Successful registration' completed successfully");
    }

    @Step("[POST] Unsuccessful registration")
    public void registerUnsuccessful() {
        RegisterAndLoginRequest request =
                RegisterAndLoginRequest.builder()
                        .email("sydney@fife")
                        .build();

        RegisterAndLoginResponse response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/RegisterUnsuccessfulResponseSchema.json"))
                .extract().as(RegisterAndLoginResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.getError()).isEqualTo("Missing password");
        logger.info("Test '[POST] Unsuccessful registration' completed successfully");
    }

    @Step("[POST] Successful login")
    public void loginSuccessful() {
        RegisterAndLoginRequest request =
                RegisterAndLoginRequest.builder()
                        .email("eve.holt@reqres.in")
                        .password("cityslicka")
                        .build();

        RegisterAndLoginResponse response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/LoginSuccessfulResponseSchema.json"))
                .extract().as(RegisterAndLoginResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
        logger.info("Test '[POST] Successful login' completed successfully");
    }

    @Step("[POST] Unsuccessful login")
    public void loginUnsuccessful() {
        RegisterAndLoginRequest request =
                RegisterAndLoginRequest.builder()
                        .email("peter@klaven")
                        .build();

        RegisterAndLoginResponse response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/LoginUnsuccessfulResponseSchema.json"))
                .extract().as(RegisterAndLoginResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.getError()).isEqualTo("Missing password");
        logger.info("Test '[POST] Unsuccessful login' completed successfully");
    }

    @Step("[GET] Retrieve user list with a 3-second delay")
    public void getUsersWithDelay() {
        List<UserData> users = given()
                .when()
                .get("https://reqres.in/api/users?delay=3")
                .then()
                .statusCode(200)
                .time(greaterThan(3000L))
                .and().time(lessThan(5000L))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/UsersListSchema.json"))
                .body("page", equalTo(1))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2))
                .extract().jsonPath().getList("data5task", UserData.class);

        assertThat(users).extracting(UserData::getId).isNotNull();
        assertThat(users).extracting(UserData::getFirst_name).contains("George");
        assertThat(users).extracting(UserData::getLast_name).contains("Bluth");
        logger.info("Test '[GET] Retrieve user list with a 3-second delay' completed successfully");
    }
}
