package com.zhaosy.myblog.dao.DaoImpl;

import com.zhaosy.myblog.Utils.JDBCUtils;
import com.zhaosy.myblog.dao.BlogDao;
import com.zhaosy.myblog.dao.CatalogDao;
import com.zhaosy.myblog.domain.Catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogDaoImpl implements CatalogDao {

    @Override
    public Catalog findCatalog(int catalogId) {
        Catalog catalog = new Catalog();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConn();
            String sql = "select * from catalog where catalogid = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,catalogId);
            rs = ps.executeQuery();
            while (rs.next()){
                catalog.setCatalogId(rs.getInt("catalogid"));
                catalog.setCatalogName(rs.getString("catalogname"));
                catalog.setCatalogCase(rs.getString("catalogcase"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, ps, rs);
        }
        return catalog;
    }

    @Override
    public List<Catalog> findUserCatalog(String username) {
        Catalog catalog = new Catalog();
        CatalogDao cd = new CatalogDaoImpl();
        List<Catalog> list = new ArrayList<>();
        BlogDao bd = new BlogDaoImpl();
        List<Integer> list1 = bd.fidUserSetting(username);

        for (int i:
             list1) {
            list.add(cd.findCatalog(i));
        }

        return list;
    }

    @Override
    public int findCatalogId(String catalogName) {
        int catalogId = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConn();
            String sql = "SELECT * from catalog where catalogname = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, catalogName);
            rs = ps.executeQuery();
            while (rs.next()){
                catalogId = rs.getInt("catalogid");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, ps, rs);
        }

        return catalogId;
    }

    @Override
    public List<Catalog> findCatalog() {
        List<Catalog> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConn();
            String sql = "select * from catalog";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Catalog catalog = new Catalog();
                int catalogid = rs.getInt("catalogid");
                String catalogname = rs.getString("catalogname");
                String catalogcase = rs.getString("catalogcase");
                catalog.setCatalogCase(catalogcase);
                catalog.setCatalogId(catalogid);
                catalog.setCatalogName(catalogname);
                list.add(catalog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.release(conn, ps, rs);
        }
        return list;

    }

    @Override
    public Boolean insertCatalog(String catalogName, String catalogCase) {
        Boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = JDBCUtils.getConn();
            String sql = "insert into catalog (catalogname, catalogcase) values(?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, catalogName);
            ps.setString(2, catalogCase);
            ps.execute();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, ps);
        }

        return flag;
    }
}
