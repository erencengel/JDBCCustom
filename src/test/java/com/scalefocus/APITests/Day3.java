package com.scalefocus.APITests;

import com.scalefocus.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Day3 {

    String url = ConfigurationReader.get("baseurlforords");

    @Test
    public void test1(){
        Response response = RestAssured.given()
                .queryParams("q", "{\"region_id\":2}")
                .contentType(ContentType.JSON)
                .when()
                .get(url + "/countries");
        JsonPath jsonPath = response.jsonPath();
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());
        String countryName = response.body().path("items.country_name[4]");
        System.out.println("countryName = " + countryName);
        List<String> list = response.body().path("links.rel");
        for (String s : list) {
            System.out.println("s = " + s);
        }
        List<String> list1 = jsonPath.getList("items.country_id");
        for (String s : list1) {
            System.out.println("s = " + s);
        }

    }

    @Test
    public void test2(){
        Response response = RestAssured.given()
                .queryParams("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get(url + "/employees");
        response.prettyPrint();
    }

    @Test
    public void test3(){
        Response response = RestAssured.given()
                .when()
                .get(url + "/employees");
        String one = response.body().path("items.links[1].href[0]");
        System.out.println("one = " + one);
    }

    @Test
    public void test4(){
        Response response = RestAssured.given()
                .queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get(url + "/employees");
        JsonPath jsonPath = response.jsonPath();
        List<String> list = jsonPath.getList("items.job_id");
        for (String s : list) {
            System.out.println("s = " + s);
            Assert.assertTrue(s.equals("IT_PROG"));
        }
    }

    @Test
    public void test5(){
        Response response = RestAssured.when().get(url + "/countries");
        JsonPath jsonPath = response.jsonPath();
        List<String> list = jsonPath.getList("items.country_name");
//        for (String s : list) {
//            System.out.println("s = " + s);
//        }
        System.out.println("list.size() = " + list.size());
        String country = jsonPath.getString("items.country_name[0]");
        System.out.println("country = " + country);

        List<String> list1 = jsonPath.getList("items.findAll{it.region_id>3}.country_name");
        for (String s : list1) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void test6(){
        Response response = RestAssured.given()
                .queryParam("limit", 107)
                .when()
                .get(url + "/employees");
        JsonPath jsonPath = response.jsonPath();
        List<String> list = jsonPath.getList("items.findAll{it.job_id==\"IT_PROG\"}.first_name");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("list.get("+i+") = " + list.get(i));
        }

        List<String> list1 = response.body().path("items.findAll{it.salary>15000}.first_name");
        for (String s : list1) {
            System.out.println("s = " + s);
        }

        int name = jsonPath.get("items.min{it.salary}.salary");
        System.out.println("name = " + name);

    }
}
