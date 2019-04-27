$(function(){
    var timer;
    autoShow();//轮播

    function autoShow(){
        var i = 0;
        var picList = $(".section-thumbnail");
        picList.each(function(index, item){
            console.log(item);
        })

        //定时器
        timer = setInterval(function(){
            //添加下一个active
            //console.log(i);
            $(picList[i]).click();
            i++;
            //当图片是最后一张的后面时，设置图片为第一张
            if(i == 5){
                i = 0;
            }

        },1000);
    }
    
    //登录按钮事件
    $("#loginBtn").click(function () {
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
        console.log($("form").serialize()); //输出字符串
        console.log($("form").serializeArray()); //输出数组
        var param = {
            username: $("#username").val(),
            password: $("#password").val()
        }

        $.ajax({
            url:"/resource/login",
            type: "post",
            contentType:"application/json;charset=utf-8",
            data:JSON.stringify(param),
            success:function(result){
                if(result.code == 0){
                    location.href = "/index.html";
                }else{
                	removeLoading('loginLoading');
                    alert("失败：" + result.msg);
                }
            }
        });

    });
    
    $(document).keydown(function(event){
        if(event.keyCode==13){
            $("#loginBtn").click();
        }
    });
});