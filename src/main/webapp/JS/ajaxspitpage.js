

$(function () {
    $("#next").click(function () {

        $.ajax({
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url:"/myblog/ajaxSpitServlet",
            type:"Get",

            data:{'username':name},
            // dataType:'json',
            success:function (data) {
                var pageNum = data.pageNum + 1;
                location.href="/myblog/splitPageServlet?pageNum=" + pageNum;
            },
            error:function () {
            },
            dataType: "json"
        })
    })
})

$(function () {
    $("#previous").click(function () {
        $.ajax({
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url:"/myblog/ajaxSpitServlet",
            type:"Get",

            data:{'username':name},
            // dataType:'json',
            success:function (data) {
                var pageNum = data.pageNum - 1;
                location.href="/myblog/splitPageServlet?pageNum=" + pageNum;
            },
            error:function () {
            },
            dataType: "json"
        })
    })
})
