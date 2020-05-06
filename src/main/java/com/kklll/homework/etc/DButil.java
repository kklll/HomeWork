package com.kklll.homework.etc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author DeepBlue
 * @date 2020/3/17 19:15
 */
public class DButil {
    private static String url="jdbc:mysql://db.dlddw.xyz:10083/DB?characterEncoding=UTF-8&&serverTimezone=UTC";
    private static String username="root";
    private static String password="hades54524";
    public static Connection getConnect() throws Exception {
        //是否有数据库驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }
}
