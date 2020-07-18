package com.zhaosy.myblog.Utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    static String driverClass = null;
    static String url = null;
    static String name = null;
    static String password = null;

    /*static {
        Properties properties = new Properties();
        try {
            InputStream is = new FileInputStream("D://projects//IDEAWorkplce//MyBlog//src//main//java//jdbc.properties");
            properties.load(is);

            driverClass = properties.getProperty("driverClass");
            url = properties.getProperty("url");
            name = properties.getProperty("name");
            password = properties.getProperty("password");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static Connection getConn(){
       /* Connection conn = null;
        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url, name, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;*/
//       使用c3p0连接池获取连接
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void release(Connection conn, Statement st, ResultSet rs){
        rsClose(rs);
        stClose(st);
        connClose(conn);
    }

    public static void release(Connection conn, Statement st){
        stClose(st);
        connClose(conn);
    }

    public static void rsClose(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void stClose(Statement st){
        try {
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void connClose(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
