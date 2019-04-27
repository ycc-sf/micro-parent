layui.use(['form','element','layer'], function(){
	
	//初始化页面姓名
	CustomUtil.initUsernameInHTMLHead();
	loadUserInfo();
	
	var form = layui.form
	  ,element = layui.element
	  ,layer = layui.layer;
		  
	form.on('submit()',function(data){
		var formJson = JSON.stringify(data.field);
		console.log("表单", data);
		console.log("提交表单：", formJson);
		var uri = "/resource/updateUserById";
		$.ajax({
            url:uri,
            type: "put",
            data: formJson,
            contentType:"application/json;charset=utf-8",
            success:function(data){
            	console.log(data);
                if(data.code == 0){
                	
                	CustomUtil.myAlert("修改成功", 130);
//                	$('#modal-alert').iziModal('open');
                	//重新异步加载修改后的信息
    		    	loadUserInfo();
    		    	//隐藏表单
    				$(".maincontent").css('display','block');
    				$(".resetContent").css('display','none');
                	
//                	layer.open({
//                		title: '成功'
//                		,content: '修改成功！'
//            			,yes: function(index, layero){
//            				//重新异步加载修改后的信息
//            		    	loadUserInfo();
//            		    	//隐藏表单
//            				$(".maincontent").css('display','block');
//            				$(".resetContent").css('display','none');
//            			    layer.close(index); //如果设定了yes回调，需进行手工关闭
//            			  }
//                	});
                }else{
                	layer.open({
                		title: '数据请求失败，请重试'
                		,content: data.msg
                	});
                }
            }
        });
	    return false;
	});
	  
	  //点击修改按钮
	$("#updateBtn").click(function(){
		//表单数据初始化
		$("input[name=username]").val($("#username").html());
		$("input[name=realName]").val($("#realNameDisplay").html());
		$("input[name=phonenum]").val($("#phonenum").html());
		$("input[name=email]").val($("#email").html());
		$("input[name=password]").val($("#password").attr("pass"));
		
		$("input[name=roleId]").val($("#roleId").val());
		$("input[name=gender]").val($("#gender").val());
    	//显示修改表单
		
//		myAnimate_close($(".maincontent"));
    	$(".maincontent").css('display','none');
    	$(".resetContent").css('display','block');
	});
	  //点击取消按钮
//	$("#cancelBtn").click(function(){
//		  $(".maincontent").css('display','block');
//		  $(".resetContent").css('display','none');
//	});
	  ///////////////////////////////////////自定义函数///////////////////////////////////
	  function loadUserInfo(){
		//准备参数
    	var uri = "/resource/getUserInfo";
    	//请求数据
    	$.ajax({
            url:uri,
            type: "get",
            contentType:"application/json;charset=utf-8",
            success:function(data){
            	console.log(data);
                if(data.code == 0){
                	//页面展示数据初始化
                	$("#realNameDisplay").html(data.result.realName);
                	$("#username").html(data.result.username);
                	$("#phonenum").html(data.result.phonenum);
                	$("#email").html(data.result.email);
                	$("#password").attr("pass", data.result.password);
                	
                	$("#roleId").val(data.result.roleId);
                	$("#gender").val(data.result.gender);
                	
        			//重新渲染
//        			form.render();
                }else{
                	layer.open({
                		title: '数据请求失败，请重试'
                		,content: data.msg
                	});
                }
            }
        });
	  }
});