package ru.yandex.practicum.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static ru.yandex.practicum.constant.EnvConst.*;


public class UserApi {

    public Response deleteUser(String accessToken) {

        return given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .baseUri(URL)
                .when()
                .delete(DELETE_USER_ENDPOINT);
    }

    public String getAccessToken(String email, String password) {

        return given()
                .contentType(ContentType.JSON)
                .baseUri(URL)
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .when()
                .post(LOGIN_USER_ENDPOINT)
                .path("accessToken");
    }

    public Response createUser(String email, String password, String name) {

        return given()
                .contentType(ContentType.JSON)
                .baseUri(URL)
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\", \"name\": \"" + name + "\"}")
                .when()
                .post(CREATE_USER_ENDPOINT);
    }
}
