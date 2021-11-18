package com.scalefocus.APITests;

import com.scalefocus.utilities.APIUtils;
import com.scalefocus.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

public class Day8 {

    String url = ConfigurationReader.get("bookit");

    @Test
    public void test1(){
        String email = "sbirdbj@fc2.com";
        String password = "asenorval";

        String token = APIUtils.accessToken(email,password);

        Response response = RestAssured.given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(url + "/api/users");

        JsonPath jsonPath = response.jsonPath();
        List<String> firstName = jsonPath.getList("firstName");
        for (String s : firstName) {
            System.out.println("s = " + s);
        }

    }

}
