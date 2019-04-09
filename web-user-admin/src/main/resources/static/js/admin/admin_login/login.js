$(function(){
    Victor("container", "output");   //登陆背景函数
    $("#entry_name").focus();
    $(document).keydown(function(event){
        if(event.keyCode==13){
            $("#entry_btn").click();
        }
    });
    
    //绑定登录按钮事件
    $("#entry_btn").click(function () {
        var param = {
            username: $("#entry_name").val(),
            password: $("#entry_password").val()
        }

        $.ajax({
            url:"/admin/login",
            type: "post",
            contentType:"application/json;charset=utf-8",
            data:JSON.stringify(param),
            success:function(result){
                if(result.code == 0){
                    location.href = "/html/admin/index.html";
                }else{
                    alert("失败：" + result.msg);
                }
            }
        });

    });
    
    /////////////////////自定义函数/////////////////////////
    
    
    
    
});