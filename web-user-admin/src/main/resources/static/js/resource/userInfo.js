layui.use(['form','element','layer'], function(){
	
	//初始化页面姓名
	CustomUtil.initUsernameInHTMLHead();
	
	var form = layui.form
	  ,element = layui.element
	  ,layer = layui.layer;
		  
		  
		  form.on('submit(formDemo)',function(data){
		  	lay.msg(JSON.stringify(data.field));
		  	return false;
		  });
		  
		  //点击修改按钮
		  $("#updateBtn").click(function(){
			  $(".maincontent").css('display','none');
			  $(".resetContent").css('display','block');
		  });
		  //点击取消按钮
		  $("#cancelBtn").click(function(){
			  $(".maincontent").css('display','block');
			  $(".resetContent").css('display','none');
		  });
		});