package com.scalefocus.utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIUtils {

    static String url = ConfigurationReader.get("bookit");

    public static String accessToken(String email, String password){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("email", email)
                .queryParam("password", password)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(url+"/sign");

        String token = response.path("accessToken");
        String finalToken = "Bearer " + token;
        return finalToken;
    }

}
