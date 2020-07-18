<%@ page import="com.zhaosy.myblog.domain.Blog" isELIgnored="false"%>
<%@ page import="com.zhaosy.myblog.domain.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>阅读全文</title>

    <meta charset="UTF-8">
    <title>md</title>
    <link rel="stylesheet" href="../editor.md-master/css/editormd.preview.css" />
    <script src="../jquery/jquery-3.2.1.min.js"></script>
    <script src="../editor.md-master/lib/marked.min.js"></script>
    <script src="../editor.md-master/lib/prettify.min.js"></script>
    <script src="../editor.md-master/lib/raphael.min.js"></script>
    <script src="../editor.md-master/lib/underscore.min.js"></script>
    <script src="../editor.md-master/lib/sequence-diagram.min.js"></script>
    <script src="../editor.md-master/lib/flowchart.min.js"></script>
    <script src="../editor.md-master/lib/jquery.flowchart.min.js"></script>
    <script src="../editor.md-master/editormd.min.js"></script>


    <script src="../JS/ajaxcommentInsert.js"></script>
</head>

<body style="background:#eee;">
    <%
        Blog blog = (Blog) session.getAttribute("showBlog");
        session.setAttribute("now", blog);

    %>
    <div id="doc-content">
        <textarea style="display:none;" placeholder="markdown语言">${now.blogcontent}</textarea>
    </div>


    <div>
        评论区：
        <c:forEach items="${sessionScope.commentMap.get(now.blogid)}" var="comment">
            <div style="border: 1px solid;font-size: 10px;height: 20px;width: 500px; margin-top:2px;background: #0D334960">
                ${comment.getCommentContent()};
            </div>
        </c:forEach>

    </div>
    <div style="margin-top: 30px">
        <form>
            <div>
                我要评论：
            </div>
            <div>
                <textarea placeholder="请输入评论" name="commmentContent" id="comment" style="background-image: linear-gradient(135deg, #f5f7fa50 0%, #c3cfe250 100%);height:50px; width:500px;float: left"></textarea>
            </div>
            <input type="button" blogId="${now.blogid}" id="commentReport" value="发表评论" style="background-image: linear-gradient(120deg, #f093fb30 0%, #f5576c30 100%);margin-top: 27px">
            <span id="span"></span>
        </form>
    </div>

    <br />
    <script type="text/javascript">
        var testEditor;
        $(function () {
            testEditor = editormd.markdownToHTML("doc-content", {//注意：这里是上面DIV的id
                htmlDecode: "style,script,iframe",
                emoji: true,
                taskList: true,
                tocm: true,
                tex: true, // 默认不解析
                flowChart: true, // 默认不解析
                sequenceDiagram: true, // 默认不解析
                codeFold: true
            });});
    </script>

</body>
</html>
