layui.use(['form','element','layer'], function(){
	//初始化页面姓名
	CustomUtil.initUsernameInHTMLHead();
	 
	//当前位置坐标
	var thisPosition = new AMap.LngLat(113.50928629557302, 34.812260470921);
	
	var form = layui.form
  		,element = layui.element
  		,layer = layui.layer;
	
//	alert(11111);
	//创建地图
	var map = new AMap.Map('detailMap',{
		zoom:11,
		center:[113.6347424, 34.7218855],
		viewMode:'3D'
	});
	
	
	var E = window.wangEditor
	var editor = new E('#editor')
	editor.create();
	
	//暂存通过uri传递过来的参数
	CustomUtil.receiveData();
	//读取暂存参数
	var infoId = CustomUtil.requestValue("infoId");
	
//	console.log(infoId);
	
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
                	$('#title').html(date.result.infoTitle);
                	$('#infoContent').html(date.result.infoDetailString);
                	var marker = new AMap.Marker({
                		position: [date.result.locationX,date.result.locationY],
                		title:date.result.infoTitle
                	});
                	map.add(marker);
                	map.setCenter([date.result.locationX,date.result.locationY]);
                }else{
                	layer.open({
                		title: '数据请求失败，请重试'
                		,content: date.msg
                	});
                }
            }
        });
	}
	
	function mapInfo(){
		
	}
	
});