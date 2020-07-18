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
    <title>MyBlog|首页</title>
    <link rel="stylesheet" href="./CSS/main.css">
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

            <div id="top">
                <div id="person-information">
                    <div id="head-image">
                        <img src="./imgs/headimage.jpg" >
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
                Set<String> set = new HashSet<String>(); //创建一个Set对象是为了在首页显示的时候分类 不重复显示
                for (Catalog catalog:
                     list2) {
                    set.add(catalog.getCatalogName());
                }
                session.setAttribute("blogList", list1);
                session.setAttribute("catalogList", list2);
                session.setAttribute("catalogNameSet", set);

                Map<Integer, List<Comment>> map1 = (Map<Integer, List<Comment>>) session.getAttribute("commentMap");
                session.setAttribute("commentMap", map1);

                Map<Integer, List<Comment>> map2 = new HashMap<Integer, List<Comment>>();
                List<Integer> blogId = new ArrayList<Integer>();
                List<Integer> commentNum = new ArrayList<Integer>();
                Map<Integer, String> map3 = new LinkedHashMap<Integer, String>();
                map2.putAll(map1);
                int max = -1;
                int index = -1;

                while(map2.size()!=0){
                    for (Integer key:
                            map2.keySet()) {
                        if (max < map2.get(key).size()){
                            max = map2.get(key).size();
                            index = key;
                        }
                    }
                    blogId.add(index);
                    commentNum.add(max);
                    map2.remove(index);
                    max=-1;
                    index=-1;
                }
                for (Integer id:
                     blogId) {
                    for (Blog blog:
                         list1) {
                        if (id == blog.getBlogid()){
                            map3.put(id, blog.getHeadline());
                        }
                    }
                }
               session.setAttribute("blogNameList", map3);

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
            <form style="height: 30px" action="/myblog/addNewCatalog" target="_blank">
                <input type="text" name="insertCatalogName" style="float: left;width: 138px;background: #fffffc61;height: 30px">
                <input type="submit" value="新增分类" style="float: left;margin-left: 20px;background: #fffffc61;height: 30px">

            </form>
            <div class="item">
                <div class="induction">阅读排行</div>
                <div class="list-all">

                    <% int j = 0;%>
                    <c:forEach items="${blogNameList}" var="item" begin="0" end="4">
                        <div class="list">
                            <a href="/myblog/showArticleServlet?blogId=${item.key}" target="_blank">
                                <c:if test="${item.value.length() ge 12}">
                                    ${item.value.substring(0,11)}...
                                </c:if>
                                <c:if test="${item.value.length() lt 12}">
                                    ${item.value}
                                </c:if>
                                <%
                                    int n = commentNum.get(j);
                                    j++;

                                %>(评论:<%=  n %>)
                            </a>
                        </div>

                    </c:forEach>
                </div>

            </div>
            <div id="search">
                <form action="/myblog/searchServlet" target="_blank">
                    <fieldset>
                        <input class="text" id="search-text" name="searchContext">
                        <input id="button" type="submit" value="搜索">
                    </fieldset>

                </form>
            </div>
        </div><!--left-content-->

        <div id="right-content">
            <div id="right-content-top">最新文章</div>
            <div id="text-all">
                <div class="text">
                        <%
                            int i = 0;
                        %>
                    <c:forEach items="${blogList}" var="blog" begin="0" end="4">

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
    </div><!--container-->

    <div class="clear"></div>


    <div id="footer">
        <div>
            <a href="#"><img src="./imgs/github.png"width="20px" height="20px" class="img-circle">&nbsp;&nbsp;GitHub</a>
            &nbsp;|
            <a href="#">&nbsp;&nbsp;MyBlog</a>
            <br/>
            copyright &copy 2020
        </div>
    </div><!-- footer -->
</div><!--wrapper-->

</body>
</html>
