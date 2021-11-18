package com.scalefocus.APITests;

import com.scalefocus.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
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

    @Test
    public void test2(){

        Spartan spartan = new Spartan();
        spartan.setGender("Male");
        spartan.setPhone(1234567890l);
        spartan.setName("İdris");

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartan)
                .when()
                .post(url);
        System.out.println(response.statusCode());
        response.prettyPrint();
    }

    @Test
    public void test3(){

        CustomRegion customRegion = new CustomRegion(7, "Cydeo Turkey-2");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(customRegion)
                .when()
                .post(ConfigurationReader.get("baseurlforords") + "/regions/");

        System.out.println(response.statusCode());
        JsonPath jsonPath = response.jsonPath();
        int region_id = jsonPath.get("region_id");
        System.out.println("region_id = " + region_id);
        String region_name = jsonPath.getString("region_name");
        List<String> list = jsonPath.getList("links.rel");
        for (String s : list) {
            System.out.println("s = " + s);
        }

    }

}
