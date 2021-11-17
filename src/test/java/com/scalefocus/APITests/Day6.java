package com.scalefocus.APITests;

import com.scalefocus.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Day6 {

    String url = ConfigurationReader.get("baseurl");

    @Test
    public void test1(){
        Map<String,Object> map = new HashMap<>();
        map.put("name", "Alperen1");
        map.put("gender","Male");
        map.put("phone",1234567890l);
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .post(url);
        System.out.println(response.statusCode());
        System.out.println(response.contentType());
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        String story = jsonPath.get("success");
        int i = jsonPath.get("data.id");
        String str = jsonPath.getString("data.name");
        System.out.println(story);
        System.out.println(i);
        System.out.println(str);

    }

}
