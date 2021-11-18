package com.scalefocus.APITests;

import com.scalefocus.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Day7 {

    String url = ConfigurationReader.get("baseurl");

    @Test
    public void test1(){
        Spartan spartanPut = new Spartan();
        spartanPut.setName("İdrisAli");
        spartanPut.setGender("Male");
        spartanPut.setPhone(1234567890l);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", 159)
                .body(spartanPut)
                .when()
                .put(url + "/{id}");

        response.prettyPrint();
    }

    @Test
    public void test2(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","İdrisAlii");
        map.put("gender","Male");
        map.put("phone",1234567890l);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", 159)
                .body(map)
                .when()
                .put(url + "/{id}");

        System.out.println("response.statusCode() = " + response.statusCode());
    }

    @Test
    public void test3(){
        Spartan spartan = new Spartan();
        spartan.setName("İdrisAliiiii");

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("id", 159)
                .body(spartan)
                .when()
                .patch(url + "/{id}");

        System.out.println("response.statusCode() = " + response.statusCode());
        response.prettyPrint();
    }

    @Test
    public void test4(){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", 159)
                .when()
                .delete(url + "/{id}");

        System.out.println("response.statusCode() = " + response.statusCode());
        response.prettyPrint();

    }

}
