package com.scalefocus.APITests;

import com.scalefocus.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.*;

public class Day5 {

    String url = ConfigurationReader.get("baseurl");
    String neourl = ConfigurationReader.get("baseurlforords");

    @Test
    public void test1(){
        Response response = RestAssured.given()
                .pathParam("id", 1)
                .contentType(ContentType.JSON)
                .when()
                .get(url+"/{id}");

        Map<String,Object> map = response.body().as(Map.class);
        System.out.println(map.toString());
        Collection<Object> values = map.values();
        for (Object value : values) {
            System.out.println(value);
        }
        Set<String> strings = map.keySet();
        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    public void test2(){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(url);
        List<Map<String,Object>> list = response.body().as(List.class);
        System.out.println(list.get(2).get("name"));

    }

    @Test
    public void test3(){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(ConfigurationReader.get("baseurlforords") + "/regions");
        Map<String,Object> map = response.body().as(Map.class);
        System.out.println(map.get("hasMore"));
        System.out.println(map.get("limit"));
        List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("items");
        System.out.println(list.get(0).get("region_id"));
        System.out.println(list.get(1).get("region_name"));
        Map<String,Object> map2 = (Map<String, Object>) list.get(0).get("links");
        System.out.println(map2.get("rel"));
        System.out.println(map2.get("href"));

    }

    @Test
    public void test4(){
        Response response = RestAssured.given()
                .pathParam("id", 1)
                .contentType(ContentType.JSON)
                .when()
                .get(url + "/{id}");
        Spartan spartan = response.body().as(Spartan.class);
        System.out.println(spartan.getName());
        System.out.println(spartan.getGender());
        System.out.println(spartan.getId());
        System.out.println(spartan.getPhone());
    }

    @Test
    public void test5(){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(neourl + "/regions");

        CustomHomework customHomework = response.body().as(CustomHomework.class);
        System.out.println(customHomework.getHasMore());
        System.out.println(customHomework.getLimit());
        List<Item> items = customHomework.getItems();
        System.out.println(items.get(0).getRegionName());
        System.out.println(items.get(1).getRegionName());
        List<Link> links = items.get(2).getLinks();
        System.out.println(links.get(0).getHref());
    }

}
