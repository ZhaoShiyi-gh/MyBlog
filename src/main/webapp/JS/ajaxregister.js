$(function () {
    $("#username").blur(function () {

        var name = $(this).val();

        $.ajax({
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url:"/myblog/ajaxLoginServlet",
            type:"Get",

            data:{'username':name},
            // dataType:'json',
            success:function (data) {
                if (data.userexsit) {
                   $("#span1").html("对不起,用户名已存在");
                   $("#span1").css("color","red");
                 }
                 else {
                    $("#span1").html("恭喜您,用户名可用");
                    $("#span1").css("color","green");
                }
            },
            error:function () {
            },
            dataType: "json"
        })
    })

})
