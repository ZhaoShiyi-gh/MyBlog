$(function () {
    $("#back").click(function () {
        var r = confirm("确认返回？请注意，系统不会保存未发表的博客");
        if (r){
            return true;
        } else {
            return false;
        }
    })
})