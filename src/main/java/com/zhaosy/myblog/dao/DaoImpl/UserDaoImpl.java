package com.zhaosy.myblog.dao.DaoImpl;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zhaosy.myblog.Utils.JDBCUtils;
import com.zhaosy.myblog.dao.UserDao;
import com.zhaosy.myblog.domain.User;

import java.sql.*;

//查找是否存在某个用户，如果存在，将这个用户的名称和密码返回
public class UserDaoImpl implements UserDao {
    @Override
    public User login(String username, String password) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        User user = null;
        try {
            conn = JDBCUtils.getConn();
            System.out.println(conn);
            String sql = "select * from users";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("usename").equals(username) && rs.getString("password").equals(password)) {
                    user = new User(username, password);
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.release(conn, ps, rs);
        }
        return user;
    }

//    查找用户名是否存在
    @Override
    public Boolean findUsername(String username) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean flag = false;

        try {
            conn = JDBCUtils.getConn();
            String sql = "select * from users where usename = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            rs = ps.executeQuery();

            while (rs.next()){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, ps, rs);
        }
        return flag;
    }

//    插入用户名，密码和邮箱
    @Override
    public Boolean saveUser(String username, String password, String email) {
        Boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = JDBCUtils.getConn();
            String sql = "insert into users (usename, password, qq) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.setString(3,email);
            ps.executeUpdate() ;
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, ps);
        }
        return flag;
    }

    @Override
    public int findUserId(String username) {
        int userId = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConn();
            String sql = "select * from users where usename = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            rs = ps.executeQuery();
            while ( rs.next()){
                userId = rs.getInt("id");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, ps, rs);
        }

        return userId;
    }
}
