package com.zhaosy.myblog.dao.DaoImpl;


import com.zhaosy.myblog.Utils.JDBCUtils;
import com.zhaosy.myblog.dao.BlogDao;
import com.zhaosy.myblog.domain.Blog;
import com.zhaosy.myblog.domain.Setting;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BlogDaoImpl implements BlogDao {
    @Override
    public List<Blog> findUserBlog(String username) {

        List<Blog> list = new ArrayList<>();

        int userid = 0;
        Connection conn = null;
        PreparedStatement ps =null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConn();
            String sql1 = "select * from users where usename = ? ";
            ps = conn.prepareStatement(sql1);
            ps.setString(1,username);
            rs = ps.executeQuery();

            while (rs.next()){
                userid =rs.getInt("id");
                break;
            }

            String sql2 = "select * from blogs where useid = ? order by blogid desc";
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, userid);
            rs = ps.executeQuery();

            while (rs.next()){
                Blog blog = new Blog();
                blog.setBlogid(rs.getInt("blogid"));
                blog.setBlogcontent(rs.getString("blogcontent"));
                blog.setImage(rs.getString("image"));
                blog.setReportData(rs.getDate("reportdate"));
                blog.setUserid(rs.getInt("useid"));
                blog.setHeadline(rs.getString("headline"));
                blog.setCatalogid(rs.getInt("catalogid"));
                list.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }   finally {
            JDBCUtils.release(conn, ps, rs);
        }


        return list;
    }

    @Override
    public List<Blog> findUserBlog(String username, int pageNum){

        List<Blog> list = new ArrayList<>();

        int userid = 0;
        Connection conn = null;
        PreparedStatement ps =null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConn();
            String sql1 = "select * from users where usename = ?";
            ps = conn.prepareStatement(sql1);
            ps.setString(1,username);
            rs = ps.executeQuery();

            while (rs.next()){
                userid =rs.getInt("id");
                break;
            }

            String sql2 = "select * from blogs where useid = ? order by ? desc LIMIT ?, ?";
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, userid);
            ps.setString(2, "blogid");
            ps.setInt(3, 5*(pageNum-1));
            ps.setInt(4, 5);
            rs = ps.executeQuery();

            while (rs.next()){
                Blog blog = new Blog();
                blog.setBlogid(rs.getInt("blogid"));
                blog.setBlogcontent(rs.getString("blogcontent"));
                blog.setImage(rs.getString("image"));
                blog.setReportData(rs.getDate("reportdate"));
                blog.setUserid(rs.getInt("useid"));
                blog.setHeadline(rs.getString("headline"));
                blog.setCatalogid(rs.getInt("catalogid"));
                list.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }   finally {
            JDBCUtils.release(conn, ps, rs);
        }


        return list;
    }

    @Override
    public List<Integer> fidUserSetting(String username) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int blogId = 0;
        int catalogId = 0;
        int userId = 0;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();

        try {
            conn = JDBCUtils.getConn();
            String sql1 = "select id from users where usename=?";
            ps = conn.prepareStatement(sql1);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()){
                userId = rs.getInt("id");
            }

            String sql2 = "select * from blogs where useid = ?";
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                blogId = rs.getInt("blogid");
                list1.add(blogId);
            }

            for (int i:
                 list1) {
                String sql3 = "select * from setting where blogid = ?";
                ps = conn.prepareStatement(sql3);
                ps.setInt(1, i);
                rs = ps.executeQuery();
                while (rs.next()){
                    catalogId = rs.getInt("catalogid");
                    list2.add(catalogId);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, ps, rs);
        }
        return list2;
    }

    @Override
    public Boolean insertBlog(String content, String image, int userId, String headline, int catalogId) {
        Boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;

//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date data = new Date(System.currentTimeMillis());
        try {
            conn = JDBCUtils.getConn();
            String sql = "insert into blogs(blogcontent, image, reportdate, useid, headline, catalogid) values (?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,content);
            ps.setString(2,image);
            ps.setDate(3, data);
            ps.setInt(4, userId);
            ps.setString(5,headline);
            ps.setInt(6,catalogId);
            ps.execute();
            flag=true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, ps);
        }
        return flag;
    }

    @Override
    public Boolean insertSetting(int blogid, int catalogid) {
        Boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConn();
            String sql = "insert into setting (catalogid, blogid) values (?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,catalogid);
            ps.setInt(2,blogid);
            ps.execute();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, ps);
        }
        return flag;
    }

    @Override
    public Boolean findsetting(int blogId, int catalogId) {
        Boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConn();
            String sql = "select * from setting where blogid = ? and catalogid = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,blogId);
            ps.setInt(2,catalogId);
            rs = ps.executeQuery();
            while (rs.next()){
                flag =true;
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, ps, rs);
        }

        return flag;
    }

    @Override
    public Boolean deleteBlog(int blogId) {
        Boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = JDBCUtils.getConn();
            String sql = "delete from comments where blogid = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, blogId);
            ps.execute();



            sql = "delete from setting where blogid = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, blogId);
            ps.execute();

            sql = "delete from blogs where blogid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, blogId);
            ps.execute();

            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.release(conn, ps);
        }
        return flag;
    }


    @Override
    public List<Blog> findAmbiousSearch(String username, String searchContext) {
        List<Blog> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        try {
            conn = JDBCUtils.getConn();
            String sql = "select * from blogs, users where blogs.useid = users.id and blogcontent like ? and usename = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+searchContext + "%");
            ps.setString(2, username);
            rs = ps.executeQuery();
            while (rs.next()){
                Blog blog = new Blog();
                blog.setBlogid(rs.getInt("blogid"));
                blog.setBlogcontent(rs.getString("blogcontent"));
                blog.setImage(rs.getString("image"));
                blog.setReportData(rs.getDate("reportdate"));
                blog.setUserid(rs.getInt("useid"));
                blog.setHeadline(rs.getString("headline"));
                blog.setCatalogid(rs.getInt("catalogid"));
                list.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, ps, rs);
        }

        return list;
    }
}
