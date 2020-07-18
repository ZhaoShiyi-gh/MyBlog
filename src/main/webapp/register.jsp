<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2020/7/9
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyBolg|注册</title>
    <script src="./jquery/jquery-3.2.1.min.js"></script>
    <script src="JS/ajaxregister.js" type="text/javascript"></script>
    <link rel="stylesheet" href="./CSS/font-awesome-4.7.0/css/font-awesome.css">
    <link rel="stylesheet" href="./CSS/register.css">
</head>
<body>

    <div id="register-box">
        <h1>Register</h1>
        <form action="/myblog/registerservlet">
            <div class="form">



                <div class="item">
                    <div style="float: left;margin-left: 178px">
                    <%--<div style="display: inline">--%>
                        <i class="fa fa-user" aria-hidden="true"></i>
                        <input type="text" id="username" name="username" placeholder="请输入需要注册的名字" >
                    </div><span id="span1"></span></br>
                </div>

                <div class="item" style="margin-top: 30px">
                    <i class="fa fa-key" aria-hidden="true"></i>
                    <input type="password" name="password" placeholder="请输入注册密码">
                </div>

                <div class="item">
                    <i class="fa fa-key" aria-hidden="true"></i>
                    <input type="password" placeholder="请确认密码"><span id="repassword-span"></span>
                </div>
                <%--<div id="gender" >

                        <label>请选择性别</label><br/>
                        <input name="radiobutton" type="radio" value="男" checked="checked"/>男
                        <input name="radiobutton" type="radio" value="女" checked="checked"/>女

                </div>--%>
                <div class="item">
                    <i class="fa fa-envelope" aria-hidden="true"></i>
                    <input type="email" name="email" placeholder="请输入注册邮箱"><span id="Email-span"></span>
                </div>
            </div><%--form--%>
            <button type="submit" id="register-button">Register</button>
        </form>
    </div><%--register-box--%>
</body>
</html>
