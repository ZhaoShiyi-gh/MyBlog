package com.zhaosy.myblog.dao.DaoImpl;

import com.zhaosy.myblog.Utils.JDBCUtils;
import com.zhaosy.myblog.dao.CommentDao;
import com.zhaosy.myblog.domain.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    @Override
    public Boolean commentInsert(String commentContent, int userid, int blogid) {
        Boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        Date date = new Date(System.currentTimeMillis());
        try {
            conn = JDBCUtils.getConn();
            String sql = "insert into comments (commentcontent, commentdate, useid, blogid) values (?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, commentContent);
            ps.setDate(2, date);
            ps.setInt(3, userid);
            ps.setInt(4,blogid);
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
    public List<Comment> findBlogIdComment(int blogId) {
        List<Comment> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConn();
            String sql = "select * from comments where blogid = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, blogId);
            rs = ps.executeQuery();
            while (rs.next()){
                Comment comment = new Comment();
                comment.setBlogId(rs.getInt("blogid"));
                comment.setCommentContent(rs.getString("commentcontent"));
                comment.setCommentDate(rs.getDate("commentdate"));
                comment.setCommentId(rs.getInt("commentsid"));
                comment.setUseId(rs.getInt("useid"));
                list.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, ps, rs);
        }
        return list;
    }
}
