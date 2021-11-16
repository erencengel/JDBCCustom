package com.scalefocus.jdbctests;

import com.scalefocus.utilities.ConfigurationReader;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;

public class First {

    String dbUsername = "hr";
    String dbPassword = "hr";
    String dbUrl = "jdbc:oracle:thin:@3.84.129.57:1521:xe";

    @Test
    public void test1() throws SQLException {
        System.out.println(dbUrl);
        System.out.println(dbUsername);
        System.out.println(dbPassword);

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from employees");

        //resultSet.next();
        //System.out.println(resultSet.getString(1));

        while(resultSet.next()){
            System.out.println(resultSet.getString(1) + "-" + resultSet.getString(2) + "-" + resultSet.getString(3));
        }

        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void test2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from departments");

        resultSet.next();
        System.out.println(resultSet.getRow());
        resultSet.last();
        System.out.println(resultSet.getRow());

        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void test3() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from locations");

        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        System.out.println(databaseMetaData.getDriverVersion());
        System.out.println(databaseMetaData.getDriverName());
        System.out.println(databaseMetaData.getDatabaseProductVersion());
        System.out.println(databaseMetaData.getUserName());

        System.out.println(resultSetMetaData.getColumnName(1));
        System.out.println(resultSetMetaData.getColumnCount());
        System.out.println(resultSetMetaData.getColumnTypeName(4));
        System.out.println(resultSetMetaData.getColumnDisplaySize(3));


        connection.close();
        statement.close();
        resultSet.close();

    }

    @Test
    public void test4() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from locations");

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        List<Map<String,Object>> queryData = new ArrayList<>();

        resultSet.next();
        Map<String,Object> row1 = new HashMap<>();
        row1.put(resultSetMetaData.getColumnName(1),resultSet.getString(1));
        row1.put(resultSetMetaData.getColumnName(2),resultSet.getString(2));
        row1.put(resultSetMetaData.getColumnName(3),resultSet.getString(3));
        row1.put(resultSetMetaData.getColumnName(4),resultSet.getString(4));
        queryData.add(row1);

        resultSet.next();
        Map<String,Object> row2 = new HashMap<>();
        row2.put(resultSetMetaData.getColumnName(1),resultSet.getString(1));
        row2.put(resultSetMetaData.getColumnName(2),resultSet.getString(2));
        row2.put(resultSetMetaData.getColumnName(3),resultSet.getString(3));
        row2.put(resultSetMetaData.getColumnName(4),resultSet.getString(4));
        queryData.add(row2);

        System.out.println(queryData.toString());

        resultSet.close();
        statement.close();
        connection.close();

    }

}
