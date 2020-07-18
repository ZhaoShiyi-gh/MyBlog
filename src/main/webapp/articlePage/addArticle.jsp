<%@ page import="java.util.List" %>
<%@ page import="com.zhaosy.myblog.domain.Catalog" %><%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2020/7/13
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>写博客-MyBlog</title>
    <link rel="stylesheet" href="../editor.md-master/css/editormd.css" />
    <link rel="stylesheet" href="../CSS/addArticle.css">

    <script type="text/javascript" src="../jquery/jquery-3.2.1.min.js"></script>
    <script src="../JS/backconfirm.js"></script>
    <script src="../editor.md-master/editormd.min.js"></script>
    <script type="text/javascript">
        $(function() {
            var editor = editormd("test-editormd", {
                width  : "90%",
                height : 720,
                theme : "dark",
                path   : "../editor.md-master/lib/",
                saveHTMLToTextarea : true, // 保存 HTML 到 Textarea
                toolbarAutoFixed:true,//工具栏自动固定定位的开启与禁用
                imageUpload : true,
                imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                imageUploadURL : "/uploadImage"

            });
        });
    </script>

</head>
<body>
        <form action="/myblog/addArticleServlet" method="post">

            <div id="addtop">
                <div id="left">
                    <a href="/myblog/searchInformationServlet" id="back"> &lt;&nbsp;返回主页</a>&nbsp;&nbsp;&nbsp;
                    <input type="text" placeholder="请输入文章标题" name="headline">
                </div>

                <div id="right">
                    <img src="../imgs/headimage.jpg">
                </div>
                <input type="submit" value="发布文章" id="postart">
                <%
                    List<Catalog> list = (List<Catalog>) session.getAttribute("catalog");
                    session.setAttribute("catalog", list);
                %>

                <div id="select">
                    分类:<select name="catalogName" >
                           <c:forEach items="${catalog}" var="cata">
                                <option value="${cata.catalogName}">
                                    ${cata.catalogName}
                                </option>
                           </c:forEach>

                    </select>
                </div>
            </div>

            <div id="test-editormd">

                <textarea name="content"></textarea>

            </div>

        </form>
        </body>
</html>
