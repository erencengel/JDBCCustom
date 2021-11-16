package com.scalefocus.jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;

public class DynamicList {

    String dbUsername = "hr";
    String dbPassword = "hr";
    String dbUrl = "jdbc:oracle:thin:@3.84.129.57:1521:xe";

    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select first_name,last_name,salary,job_id from" +
                " employees where rownum<6");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        List<Map<String,Object>> queryList = new ArrayList<>();

        while(resultSet.next()){
            Map<String,Object> map = new HashMap<>();
            for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
                    map.put(resultSetMetaData.getColumnName(i), resultSet.getString(i));
            }
            queryList.add(map);
        }
        System.out.println(queryList.toString());

        for (Map<String, Object> map : queryList) {
            System.out.println(map.toString());
        }

        resultSet.close();
        statement.close();
        connection.close();
    }



}
