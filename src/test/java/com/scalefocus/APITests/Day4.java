package com.scalefocus.APITests;

import com.scalefocus.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;



public class Day4 {

    String endPoint = ConfigurationReader.get("baseurl");

    @Test
    public void test1(){
        RestAssured.given()
                .pathParam("id",10)
                .contentType(ContentType.JSON)
                .when()
                .get(endPoint+"/{id}")
                .then()
                .assertThat()
                .statusCode(200).and()
                .contentType("application/json")
                .body("name",equalTo("KUYTs"),"gender",is("Female"),"id",equalTo(10),"phone",is(3312820936l));
    }

    @Test
    public void test2(){
        RestAssured.given().contentType(ContentType.JSON)
                .when()
                .get(endPoint)
                .then()
                .assertThat()
                .statusCode(200).and()
                .contentType(equalTo("application/json")).and()
                .body("name",hasItems("Meade","Nels"));

    }

}
