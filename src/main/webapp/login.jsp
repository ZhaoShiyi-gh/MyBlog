<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyBlog|登录</title>
    <link rel="stylesheet" href="./CSS/font-awesome-4.7.0/css/font-awesome.css">
    <link rel="stylesheet" href="./CSS/longin.css">
</head>
<body>
    <form action="/myblog/loginServlet">
        <div id="login-box">
            <h1>Login</h1>
            <div class="form">
                <div class="item">
                    <i class="fa fa-user" aria-hidden="true"></i>
                    <input type="text" id="username" name="username" placeholder="Username"><span id="span1"></span>
                </div>
                <div class="item">
                    <i class="fa fa-key" aria-hidden="true"></i>
                    <input type="password" name="password" placeholder="Password" ><span id="span2" style="display: none"></span>
                </div>
            </div>
            <button type="submit">Login</button>
        </div>
    </form>
</body>
</html>