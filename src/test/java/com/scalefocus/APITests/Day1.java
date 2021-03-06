package com.scalefocus.APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Day1 {

    String baseUrl = "http://54.162.203.9:8000/api/spartans";

    @Test
    public void test1() {
        Response response = RestAssured.get(baseUrl);
        System.out.println(response.asString());
        System.out.println(response.statusCode());
        System.out.println(response.contentType());
    }

    @Test
    public void test2() {
        Response response = RestAssured.given()
                            .contentType(ContentType.JSON).pathParam("id", 1)
                            .when().get(baseUrl+"/{id}");

        System.out.println(response.getStatusCode());
        System.out.println(response.contentType());
        response.prettyPrint();
    }

    @Test
    public void test3(){
        RestAssured.given()
                .contentType(ContentType.JSON).pathParam("id",1)
                .when().get(baseUrl+"/{id}")
                .then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void test4(){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("id",2)
                .when()
                .get(baseUrl+"/{id}");
        Assert.assertEquals(response.statusCode(),200);
        System.out.println(response.statusCode());
        System.out.println(response.getContentType());
        Assert.assertTrue(response.path("name").equals("Nels"));
        response.prettyPrint();
    }

    @Test
    public void test5(){
        Response response = RestAssured.given()
                .pathParam("id", 10).contentType(ContentType.JSON)
                .when()
                .get(baseUrl+"/{id}");
        response.prettyPrint();
        System.out.println(response.statusCode());
        System.out.println(response.header("Content-Type"));
        System.out.println(response.getHeaders().hasHeaderWithName("Transfer-Encoding"));
        System.out.println(response.header("Date"));
        response.body().asString();
        System.out.println(response.asString());
        //System.out.println(response.path("id"));
    }

    @Test
    public void test6(){
        RestAssured.given()
                .pathParam("id",10).contentType(ContentType.JSON)
                .when()
                .get(baseUrl+"/{id}")
                .then().assertThat()
                .contentType("application/json").statusCode(200);

        RestAssured.given()
                .pathParam("id",10).contentType(ContentType.JSON)
                .when()
                .get(baseUrl+"/{id}")
                .then().assertThat()
                .contentType(ContentType.JSON).statusCode(200);

    }
}
