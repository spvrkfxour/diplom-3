package ru.yandex.practicum.api;

import static io.restassured.RestAssured.given;
import static ru.yandex.practicum.constant.EnvConst.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.yandex.practicum.dto.CreateUserRequest;
import ru.yandex.practicum.dto.LoginUserRequest;


public class UserApi {

    public Response deleteUser(String accessToken) {

        return given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .baseUri(URL)
                .when()
                .delete(DELETE_USER_ENDPOINT);
    }

    public String getAccessToken(LoginUserRequest request) {

        return given()
                .contentType(ContentType.JSON)
                .baseUri(URL)
                .body(request)
                .when()
                .post(LOGIN_USER_ENDPOINT)
                .path("accessToken");
    }

    public Response createUser(CreateUserRequest request) {

        return given()
                .contentType(ContentType.JSON)
                .baseUri(URL)
                .body(request)
                .when()
                .post(CREATE_USER_ENDPOINT);
    }
}
