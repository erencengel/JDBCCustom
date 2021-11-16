package com.scalefocus.jdbctests;

import com.scalefocus.utilities.DBUtils;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class DbUtilsPractice {

    @Test
    public void test1() throws Exception {
        DBUtils.createConnection();
        List<List<Object>> queryResultList = DBUtils.
                getQueryResultList("select location_id,postal_code,city from locations where rownum<6");
        for (List<Object> list : queryResultList) {
            System.out.println(list.toString());
        }
        List<Map<String, Object>> queryResultMap = DBUtils.
                getQueryResultMap("select location_id,postal_code,city from locations where rownum<6");
        for (Map<String, Object> map : queryResultMap) {
            System.out.println(map.toString());
        }
        Map<String, Object> rowMap = DBUtils.
                getRowMap("select location_id,postal_code,city from locations where rownum<6");
        System.out.println(rowMap.toString());
        List<String> columnNames = DBUtils.
                getColumnNames("select location_id,postal_code,city from locations where rownum<6");
        for (String columnName : columnNames) {
            System.out.println(columnName);
        }
        System.out.println(DBUtils.getRowCount());

        DBUtils.destroy();
    }



}
