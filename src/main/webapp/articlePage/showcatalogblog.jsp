<%@ page import="java.util.List" %>
<%@ page import="com.zhaosy.myblog.domain.Blog" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.zhaosy.myblog.domain.Catalog" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %><%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2020/7/8
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>分类&nbsp;|&nbsp;${name}</title>
    <link rel="stylesheet" href="../CSS/main.css">
</head>
<body>
<div id="wrapper">
    <div id="menu">
        <ul>
            <li><a href="/myblog/main.jsp">home</a></li>
            <li><a href="/myblog/beforeAddarticleServlet">写博客</a></li>
            <li><a href="/myblog/showAllServlet">全部博文</a></li>
            <li><a href="about.html">About</a></li>
        </ul>
    </div><!--menu-->
    <div class="clear"></div>

    <div id="container" style="height: 82%">
        <div id="left-content">

            <div id="top">
                <div id="person-information">
                    <div id="head-image">
                        <img src="../imgs/headimage.jpg" >
                    </div>
                    <h3><div id="username">Coloe的个人空间</div></h3>
                </div>
                <p><div id="myWord">生命只有一次</div></p>

            </div><!--top-->

            <div class="clear"></div>

            <%
                Map map = (Map) session.getAttribute("userMap");
                List<Blog> list1 = (List<Blog>) map.get("blogs");
                List<Catalog> list2 = (List<Catalog>) map.get("userCatalog");
            %>

            <%
                List<Blog> listCatalog = (List<Blog>) session.getAttribute("catalogBlogList");
                session.setAttribute("listCatalog", listCatalog);
            %>
            <div class="item">
                <div class="induction">分类</div>
                <div class="list-all">
                    <c:forEach items="${catalogNameSet}" var="catalogName">
                        <div class="list"><a href="/myblog/findCatalogServlet?catalogName=${catalogName}">
                                ${catalogName}
                        </a></div>
                    </c:forEach>

                </div>
            </div>  <%--item--%>
            <div class="item">
                <div class="induction">阅读排行</div>
                <div class="list-all">

                    <c:forEach items="${blogNameList}" var="item" begin="0" end="4">
                        <div class="list">
                            <a href="/myblog/showArticleServlet?blogId=${item.key}" target="_blank">
                                <c:if test="${item.value.length() ge 12}">
                                    ${item.value.substring(0,11)}...
                                </c:if>
                                <c:if test="${item.value.length() lt 12}">
                                    ${item.value}
                                </c:if>

                            </a>
                        </div>

                    </c:forEach>
                </div>

            </div>
            <div id="search">
                <form action="/myblog/searchServlet">
                    <fieldset>
                        <input class="text" id="search-text">
                        <input id="button" type="submit" value="搜索">
                    </fieldset>

                </form>
            </div>
        </div><!--left-content-->

        <div id="right-content">
            <div id="right-content-top">文章</div>
            <div id="text-all">
                <div class="text">
                    <%
                        int i = 0;
                    %>
                    <c:forEach items="${listCatalog}" var="blog">
                        <div class="text-name">
                            <a href="/myblog/showArticleServlet?blogId=${blog.blogid}">${blog.headline}</a>
                        </div>
                        <div class="text-information">
                            发表时间：${blog.reportData}
                            <%
                                String catalogname = list2.get(i).getCatalogName();
                                i++;
                            %>
                            &nbsp;&nbsp;&nbsp;分类：<%= catalogname %>
                            &nbsp;&nbsp;&nbsp;评论：${commentMap.get(blog.blogid).size()}
                        </div>
                        <div class="text-content">
                            <c:if test="${blog.blogcontent.length() ge 20}">
                                ${blog.blogcontent.substring(0, 19)}...
                            </c:if>
                            <c:if test="${blog.blogcontent.length() lt 20}">

                                ${blog.blogcontent}
                            </c:if>
                        </div>
                        <a href="/myblog/showArticleServlet?blogId=${blog.blogid}">阅读全文</a>
                    </c:forEach>
                </div>  <%--text--%>

            </div><!--text-all-->
        </div><!--right-content-->
    </div><!--container-->

    <div class="clear"></div>


    <div id="footer">
        <div>
            <a href="https://github.com/ZhaoShiyi-gh/MyBlog"><img src="../imgs/github.png"width="20px" height="20px" class="img-circle">&nbsp;&nbsp;GitHub</a>
            &nbsp;|
            <a href="#">&nbsp;&nbsp;MyBlog</a>
            <br/>
            copyright &copy 2020
        </div>
    </div><!-- footer -->
</div><!--wrapper-->

</body>
</html>
