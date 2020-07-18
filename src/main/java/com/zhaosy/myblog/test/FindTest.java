package com.zhaosy.myblog.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zhaosy.myblog.Utils.JDBCUtils;
import com.zhaosy.myblog.dao.BlogDao;
import com.zhaosy.myblog.dao.CatalogDao;
import com.zhaosy.myblog.dao.CommentDao;
import com.zhaosy.myblog.dao.DaoImpl.BlogDaoImpl;
import com.zhaosy.myblog.dao.DaoImpl.CatalogDaoImpl;
import com.zhaosy.myblog.dao.DaoImpl.CommentDaoImpl;
import com.zhaosy.myblog.dao.DaoImpl.UserDaoImpl;
import com.zhaosy.myblog.dao.UserDao;
import com.zhaosy.myblog.domain.Blog;
import com.zhaosy.myblog.domain.Catalog;
import com.zhaosy.myblog.domain.Comment;
import com.zhaosy.myblog.domain.User;
import com.zhaosy.myblog.service.BlogService;
import com.zhaosy.myblog.service.CatalogService;
import com.zhaosy.myblog.service.UserService;
import com.zhaosy.myblog.service.serviceImpl.BlogServiceImpl;
import com.zhaosy.myblog.service.serviceImpl.CatalogServicelmpl;
import com.zhaosy.myblog.service.serviceImpl.UserServiceImpl;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class FindTest {

    @Test
    public void findUser() {
        System.out.println(23);
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        System.out.println("11111111");

        try {
            conn = JDBCUtils.getConn();
            st = conn.createStatement();


            String sql = "select * from users";
            rs = st.executeQuery(sql);


            while(rs.next()){
                String username = rs.getString("usename");
                String password = rs.getString("password");
                System.out.println(username + ":" +password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testDbutils(){
//        Connection conn = JDBCUtils.getConn();
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource());

        try {
            System.out.println("#########################################");
            qr.update("insert into users values(null, ?, ?, ?, ? ,?, ?)","mini", "nick", "male", "15746","1546","123145.");
            System.out.println("#########################################");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test(){
        UserDao user = new UserDaoImpl();
        System.out.println(user.findUsername("admin"));

    }

    @Test
    public void saveTest(){
        UserDao user = new UserDaoImpl();
        System.out.println(user.saveUser("my", "159", "15623"));
    }

    @Test
    public void findBlog(){
        BlogDao bd = new BlogDaoImpl();
        List<Blog> list = bd.findUserBlog("admin");
        for (Blog blog:
             list) {
            System.out.println(blog.getBlogid());
        }
    }

    @Test
    public void findSetting() {
        BlogDao bd = new BlogDaoImpl();
        List<Integer> list = bd.fidUserSetting("admin");
        System.out.println(list.get(0));
    }

    @Test
    public void findCatalog(){
        CatalogDao cd = new CatalogDaoImpl();
        Catalog catalog = cd.findCatalog(1);
        System.out.println(catalog.getCatalogName());
    }

    @Test
    public void findUserInfor(){
        CatalogDao cd = new CatalogDaoImpl();
        List<Catalog> list = cd.findUserCatalog("admin");
        System.out.println(list.get(0).getCatalogName());
    }


    @Test
    public void testFindUderInfoService(){
        UserService us = new UserServiceImpl();
        Map map = us.findUserInfo("admin");
        List<Catalog> list= (List) map.get("userCatalog");
        System.out.println(list.get(0).getCatalogName());
    }

    @Test
    public void testfindUser(){
        UserService us = new UserServiceImpl();
        Map map = us.findUserInfo("admin");
        List<Catalog> list = (List<Catalog>) map.get("userCatalog");
        String Name = list.get(0).getCatalogName();
        System.out.println(Name);
    }

    @Test
    public void testInsertBlog(){
        BlogDao bd = new BlogDaoImpl();

        Boolean x = bd.insertBlog("nisd", "fasd", 1, "dajaihao",1);
        System.out.println(x);
    }

    @Test
    public void testInsertSetting() {
        BlogDao bd = new BlogDaoImpl();
        Boolean b = bd.insertSetting(2,1);
        System.out.println(b);
    }

    @Test
    public void testFindCatalogId(){
        CatalogDao cd = new CatalogDaoImpl();
        System.out.println(cd.findCatalogId("java"));
    }

    @Test
    public void testAddBlog(){
        User user = new User("admin", "100.");
        BlogService bs = new BlogServiceImpl();
        bs.addBlog("afds", "weqtr", "buagfia", user, "java");
    }

    @Test
    public void testUserId(){
        UserDao userDao = new UserDaoImpl();
        int x = userDao.findUserId("admin");
        System.out.println(x);
    }

    @Test
    public void testFindCatalog(){
        CatalogService cs = new CatalogServicelmpl();
        int x = cs.findCatalogId("java");
        System.out.println(x);
    }

    @Test
    public void testFindUserBlog(){
        BlogDao bd = new BlogDaoImpl();
        List<Blog> list = bd.findUserBlog("admin", 1);
        System.out.println(list.get(0).getBlogcontent());
    }


    @Test
    public void testFindUserAllBlog(){
        BlogService bs = new BlogServiceImpl();
        List<Blog> userBlog = bs.findUserBlog("admin", 1);
        System.out.println(userBlog.size());
    }

    @Test
    public void testDeleteBlog(){
        BlogService bs = new BlogServiceImpl();
        System.out.println(bs.deleteBlog(1));

    }

    @Test
    public void testFindCat(){
        CatalogService cs = new CatalogServicelmpl();
        List<Catalog> catalogs = cs.findCatalog();
        System.out.println(catalogs.size());
    }

    @Test
    public void testInsert(){
        CatalogDao cd = new CatalogDaoImpl();
        System.out.println(cd.insertCatalog("深度学习", "神经网络相关"));

    }

    @Test
    public void testInsertComment(){
        CommentDao cd = new CommentDaoImpl();
        System.out.println(cd.commentInsert("你好",1,60));
    }

    @Test
    public void testFindCommnent(){
        CommentDao cd = new CommentDaoImpl();
        List<Comment> comment = cd.findBlogIdComment(60);
        System.out.println(comment.size());
    }

    @Test
    public void testAmbious(){
        BlogDao bd = new BlogDaoImpl();
        List<Blog> ambiousSearch = bd.findAmbiousSearch("admin", "测试");
        System.out.println(ambiousSearch.size());
    }

    @Test
    public void testAmb(){
        BlogService bs = new BlogServiceImpl();
        List<Blog> ambiousSearch = bs.findAmbiousSearch("admin", "测试");
        System.out.println(ambiousSearch.size());
    }
}
