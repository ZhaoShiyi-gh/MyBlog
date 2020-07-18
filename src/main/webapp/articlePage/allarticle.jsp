<%@ page import="com.zhaosy.myblog.domain.Blog" %>
<%@ page import="com.zhaosy.myblog.domain.Catalog" %>
<%@ page import="com.zhaosy.myblog.domain.Comment" %>
<%@ page import="java.util.*" %><%--
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
    <title>MyBlog&nbsp;|&nbsp;全部博文</title>
    <link rel="stylesheet" href="../CSS/main.css">
    <script src="../jquery/jquery-3.2.1.min.js"></script>
    <script src="../JS/ajaxspitpage.js"></script>
    <script src="../JS/ajaxdeleteConfirm.js"></script>

</head>
<body>
<div id="wrapper">
    <div id="menu">
        <ul>
            <li><a href="/myblog/searchInformationServlet">home</a></li>
            <li><a href="/myblog/beforeAddarticleServlet">写博客</a></li>
            <li><a href="/myblog/showAllServlet">全部博文</a></li>
            <li><a href="about.html">About</a></li>
        </ul>
    </div><!--menu-->
    <div class="clear"></div>

    <div id="container" style="height: 82%">
        <div id="left-content">

            <div class="clear"></div>


            <%
                List<Blog> userBlog = (List<Blog>) session.getAttribute("userBlog");
                session.setAttribute("userBlog", userBlog);
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
            <div id="right-content-top">全部文章</div>
            <div id="text-all">
                <div class="text" >
                    <%
                        int i = 0;
                    %>
                    <c:forEach items="${userBlog}" var="blog">

                        <div class="text-name">
                            <a href="#">${blog.headline}</a>
                        </div>
                        <div class="text-information">
                            发表时间：${blog.reportData}
                            <%
                                String catalogname = "";
                                int catalogId = 0;
                                if(userBlog.size() != 0){
//                                    catalogname = userBlog.get(i).getCatalogName();
                                    catalogId = userBlog.get(i).getCatalogid();
                                }
                            %>
                            &nbsp;&nbsp;&nbsp;分类：<%= catalogId %>
                            <% i++; %>
                                &nbsp;&nbsp;&nbsp;评论：${commentMap.get(blog.blogid).size()}
                        </div>


                        <div class="text-content">
                            <c:if test="${blog.blogcontent.length() ge 50}">
                                ${blog.blogcontent.substring(0,100)}......
                            </c:if>
                            <c:if test="${blog.blogcontent.length() lt 50}">
                                ${blog.blogcontent}
                            </c:if>
                        </div>
                        <a href="/myblog/showArticleServlet?blogId=${blog.blogid}" target="_blank">阅读全文</a>
                        <a href="/myblog/deleteArticleServlet?blogId=${blog.blogid}" style="float: right;color: red;font-size: 15px;margin-top: 5px" class="deleteBlog">删除</a>
                    </c:forEach>
                </div>  <%--text--%>
            </div><!--text-all-->
            <div style="color: white;float: right;margin-top: 10px">
                    <c:if test="${sessionScope.pageNum != 1}">
                        <input type="button" value="上一页" style="background: #ffff00" id="previous">
                    </c:if>
                    <c:if test="${sessionScope.pageNum == 1}">
                        <input type="button" value="首页" style="background: #ffff00">
                    </c:if>

                    第${sessionScope.pageNum}页
                    <c:if test="${sessionScope.pageNum gt (sessionScope.blogList.size())/5}">
                        <input type="button" value="尾页" style="background: #ffff00">
                    </c:if>
                    <c:if test="${sessionScope.pageNum le (sessionScope.blogList.size())/5}">
                        <input type="button" value="下一页" style="background: #ffff00" id="next">
                    </c:if>
                </div>

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
