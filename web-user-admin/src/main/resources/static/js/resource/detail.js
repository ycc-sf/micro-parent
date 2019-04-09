layui.use(['form','element'], function(){
	//初始化页面姓名
	CustomUtil.initUsernameInHTMLHead();
	
	var form = layui.form
  		,element = layui.element;
	var E = window.wangEditor
	var editor = new E('#editor')
	editor.create();
	
	//暂存通过uri传递过来的参数
	CustomUtil.receiveData();
	//读取暂存参数
	var infoId = CustomUtil.requestValue("infoId");
	
	
	
	//加载内容
	loadInfoDetail(infoId);
	
	
	
    ////////////////////////////////自定义函数//////////////////////////////////////
	function loadInfoDetail(id){
		//准备参数
    	var uri = "/resource/getInfoById?id="+id;
    	//请求数据
    	$.ajax({
            url:uri,
            type: "get",
            contentType:"application/json;charset=utf-8",
            success:function(date){
            	console.log(date);
                if(date.code == 0){
                	$('#infoContent').html(date.result.infoDetailString);
                }else{
                	layer.open({
                		title: '数据请求失败，请重试'
                		,content: date.msg
                	});
                }
            }
        });
	}
	
});