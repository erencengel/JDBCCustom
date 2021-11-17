package com.scalefocus.APITests;

import com.scalefocus.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.groovy.runtime.powerassert.AssertionRenderer;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 {

    String baseUrl = "http://54.162.203.9:8000/api/spartans";

    @Test
    public void test1(){
        Response response = RestAssured.given()
                .pathParam("id", 3).contentType(ContentType.XML)
                .get(baseUrl + "/{id}");
        System.out.println(response.statusCode());
        System.out.println(response.contentType());
        System.out.println(response.body().asString());
    }

    @Test
    public void test2(){
        Response response = RestAssured.when()
                .get("http://54.162.203.9:8000/api/hello");
        System.out.println(response.statusCode());
        Assert.assertTrue(response.statusCode()==200);
        System.out.println(response.contentType());
        Assert.assertTrue(response.contentType().equals("text/plain;charset=UTF-8"));
        System.out.println(response.getHeaders().hasHeaderWithName("date"));
        Assert.assertTrue(response.getHeaders().hasHeaderWithName("date"));
        System.out.println(response.header("content-length"));
        Assert.assertTrue(response.header("content-length").equals("17"));
        response.prettyPrint();
        Assert.assertTrue(response.body().asString().equals("Hello from Sparta"));
    }

    @Test
    public void test3(){
        Response response = RestAssured.given()
                .pathParam("id", 1)
                .when().get(baseUrl + "/{id}");

        System.out.println("response.body().path(\"id\") = " + response.body().path("id"));
        System.out.println("response.body().path(\"name\") = " + response.body().path("name"));
        System.out.println("response.body().path(\"gender\") = " + response.body().path("gender"));
        System.out.println("response.body().path(\"phone\") = " + response.body().path("phone"));

        int id = response.body().path("id");
        String name = response.body().path("name");
        String gender = response.body().path("gender");
        long phone = response.body().path("phone");

        Assert.assertTrue(id==1);
        Assert.assertEquals(name,"Meade");
        Assert.assertEquals(gender, "Male");
        Assert.assertEquals(phone,3584128232l);

        Assert.assertTrue(response.statusCode()==200);
        Assert.assertTrue(response.contentType().equals("application/json"));
    }

    @Test
    public void test4(){
        Response response = RestAssured.when()
                .get(baseUrl);
        String firstname = response.body().path("name[0]");
        String secondname = response.body().path("name[1]");
        String lastname = response.body().path("name[-1]");

        System.out.println("firstname = " + firstname);
        System.out.println("secondname = " + secondname);
        System.out.println("lastname = " + lastname);

        List<String> list = response.body().path("name");
        for (String s : list) {
            System.out.println("s = " + s);
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println("name[" + i + "]= " + list.get(i));
        }

    }

    @Test
    public void test5(){
        Response response = RestAssured.given()
                .pathParam("id", 5)
                .when()
                .get(baseUrl + "/{id}");
        Assert.assertTrue(response.statusCode()==200);
        Assert.assertTrue(response.contentType().equals("application/json"));
        String name = response.body().path("name");
        Assert.assertTrue(name.equals("Blythe"));
    }

    @Test
    public void test6(){
        Response response = RestAssured.given()
                .pathParam("id", 500).contentType(ContentType.JSON)
                .when()
                .get(baseUrl + "/{id}");
        int statusCode = response.statusCode();
        String type = response.contentType();
        String errorMessage = response.body().path("error");

        System.out.println("statusCode = " + statusCode);
        System.out.println("type = " + type);
        System.out.println("errorMessage = " + errorMessage);

        Assert.assertTrue(statusCode==404);
        Assert.assertEquals(type,"application/json");
        Assert.assertEquals(errorMessage, "Not Found");
    }

    @Test
    public void test7(){
        Map<String,Object> map = new HashMap<>();
        map.put("gender","Female");
        map.put("nameContains","e");

        Response response = RestAssured.given()
                .queryParams(map)
                .contentType(ContentType.JSON)
                .when()
                .get(baseUrl + "/search");

        int statusCode = response.statusCode();
        String payloadType = response.contentType();

        System.out.println("statusCode = " + statusCode);
        System.out.println("payloadType = " + payloadType);

        Assert.assertTrue(response.body().asString().contains("Female"));

        List<String> list = response.body().path("content.name");
        for (String s : list) {
            System.out.println("s = " + s);
        }

        boolean bool = false;
        for (String s : list) {
            if(s.equals("Janette")){
                bool=true;
                break;
            }
        }
        Assert.assertEquals(bool,true);
        System.out.println("bool = " + bool);

        String targetName = response.body().path("content.name[2]");
        System.out.println("targetName = " + targetName);
    }

    @Test
    public void test8(){
        Response response = RestAssured.given()
                .pathParam("id",12)
                .get(ConfigurationReader.get("baseurl") + "/{id}");

        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("name");
        int id = jsonPath.getInt("id");
        String gender = jsonPath.getString("gender");
        long l = jsonPath.get("phone");

        System.out.println("name = " + name);
        System.out.println("id = " + id);
        System.out.println("gender = " + gender);
        System.out.println("l = " + l);


    }

}
