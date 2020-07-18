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

            <li><a href="./login.jsp">登录</a></li>
            <li><a href="./register.jsp">注册</a></li>
            <li><a href="about.html">关于此项目</a></li>
            <div style="display: inline;float: right;margin-right: 100px">我的博客</div>
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


            <div class="item">
                <div class="induction">分类</div>
                <div class="list-all">
                </div>

            </div>  <%--item--%>
            <form style="height: 30px" action="#">
                <input type="text" name="insertCatalogName" style="float: left;width: 138px;background: #fffffc61;height: 30px">
                <input type="submit" value="新增分类" style="float: left;margin-left: 20px;background: #fffffc61;height: 30px">

            </form>
            <div class="item">
                <div class="induction">阅读排行</div>
                <div class="list-all">

                </div>

            </div>
            <div id="search">
                <form action="#" target="_blank">
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

                        <div class="text-name">
                            <a href="#"></a>
                        </div>
                        <div class="text-information">
                            发表时间：

                            &nbsp;&nbsp;&nbsp;分类

                            &nbsp;&nbsp;&nbsp;评论：
                        </div>
                        <div class="text-content">

                        </div>
                        <a href="#">阅读全文</a>

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
