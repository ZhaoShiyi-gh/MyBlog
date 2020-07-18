$(function () {
    $("#commentReport").click(function () {

        var comment = $("#comment").val();
        var blogId = $("#commentReport").attr("blogId");
        $.ajax({
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url:"/myblog/commentInsertServlet",
            type:"post",
            data:{'commentContent':comment,'blogId': blogId},
            dataType:'json',
            success:function (data) {
                if (data.msg) {

                    $("#span").html("发表成功");
                    $("#span").css("color","green");
                    setTimeout(function() { $("#span").hide(); }, 5000);
                }
                else {
                    $("#span").html("发表失败");
                    $("#span").css("color","red");
                    setTimeout(function() { $("#span").hide(); }, 5000);
                }
            },
            error:function () {
            },
            dataType: "json"
        })

    })
})