layui.use('element', function(){
	var element = layui.element;
	
    //初始化页面姓名
	CustomUtil.initUsernameInHTMLHead();
	//默认加载初始动画页面
	loadHtml("whitePage.html");

	//////////////////////////自定义函数////////////////////////////////
	
});

function loadHtml(url){
	$("#contentDiv").load(url);
}