$(function () {
    $("#bt1").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        $.ajax({
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url:"/myblog/loginServlet",
            type:"Get",

            data:{'username':name, 'password':password},
            success:function (data) {
                if (data.msg){
                    location.href="/myblog/searchInformationServlet"
                } else {
                    alert("用户名或密码错误")
                }
            },
            error:function () {
            },
            dataType: "json"
        })
    })
})