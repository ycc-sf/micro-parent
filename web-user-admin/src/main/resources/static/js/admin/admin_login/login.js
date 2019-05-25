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
    	$('body').loading({
			loadingWidth:240,
			title:'请稍等',
			name:'loginLoading',
			discription:'',
			direction:'column',
			type:'origin',
			// originBg:'#71EA71',
			originDivWidth:40,
			originDivHeight:40,
			originWidth:6,
			originHeight:6,
			smallLoading:false,
			loadingMaskBg:'rgba(0,0,0,0.2)'
		});
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
                	CustomUtil.myAlert(result.msg, 180);
                	removeLoading('loginLoading');
                }
            }
        });

    });
    
    /////////////////////自定义函数/////////////////////////
    
    
    
    
});