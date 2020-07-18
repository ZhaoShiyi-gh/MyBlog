$(function () {
    $(".deleteBlog").click(function () {
        var r = confirm("确认删除？");
        if (r==true){

            $.ajax({
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                url:"/myblog/ajaxSpitServlet",
                type:"Get",

                success:function (data) {

                },
                error:function () {

                },
                dataType:"json"
            })

        }
        else{
            return false;
        }
    })
})

/*
$(function () {
    $(".deleteBlog").click(function () {
        var $this = $(this);
        var id = $this.data('blog.blogid');
        alert(id);
    })
})*/
