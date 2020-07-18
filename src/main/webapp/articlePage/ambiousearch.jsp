<%@ page import="java.util.List" %>
<%@ page import="com.zhaosy.myblog.domain.Blog" %>
<%@ page import="com.zhaosy.myblog.domain.Catalog" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: 赵世义
  Date: 2020/7/17
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>搜索结果</title>
    <link rel="stylesheet" href="../CSS/main.css">
</head>
<body style="margin-right: 800px;background: url('../imgs/search.jpg');background-repeat: no-repeat;background-size: 100% auto;">
    <%
        List<Blog> ambiousResult = (List<Blog>) session.getAttribute("searchResult");
        session.setAttribute("searchResult", ambiousResult);
        Map map = (Map) session.getAttribute("userMap");
        List<Catalog> list2 = (List<Catalog>) map.get("userCatalog");

    %>

    <div id="right-content" style="border: none">
        <div id="right-content-top" style="border-bottom:1px solid">搜索结果</div>
        <div id="text-all" style="border-bottom: none">
            <div class="text" style="border-bottom: none">
                <%
                    int i = 0;
                %>
                <c:forEach items="${searchResult}" var="blog">

                    <div class="text-name">
                        <a href="#">${blog.headline}</a>
                    </div>
                    <div class="text-information">
                        发表时间：${blog.reportData}
                        <%
                            String catalogname = "";
                            if(list2.size() != 0){
                                catalogname = list2.get(i).getCatalogName();
                            }
                        %>
                        &nbsp;&nbsp;&nbsp;分类：<%= catalogname %>
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
                </c:forEach>
            </div>  <%--text--%>

        </div><!--text-all-->
    </div><!--right-content-->
</body>
</html>
