layui.use(['form','element','layer'], function(){
	//初始化页面姓名
	CustomUtil.initUsernameInHTMLHead();
	var realName = $.cookie("realName");
    $("#commentUser").html(realName);

	//当前位置坐标
	var thisPosition = new AMap.LngLat(113.50928629557302, 34.812260470921);
	
	var form = layui.form
  		,element = layui.element
  		,layer = layui.layer;
	
	//暂存通过uri传递过来的参数
	CustomUtil.receiveData();
	//读取暂存参数
	var infoId = CustomUtil.requestValue("infoId");
	
	//加载
	layer.load();
	//加载内容
	loadInfoDetail(infoId);
	
	//创建地图
	var map = new AMap.Map('detailMap',{
		zoom:11,
		center:[113.6347424, 34.7218855],
		viewMode:'3D'
	});
	//关闭加载
	layer.closeAll('loading');
	//评论提交监听事件
	$("#commentBtn").click(publishComment);
	
	//加载评论内容
	commentInfos(infoId);
	
	
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
	
	//发布评论
	function publishComment(){
//		console.log();$("#userName").attr("value")
		
		var url = "/resource/addComment";
		var param = {
			infoId:infoId,
			commDetailString:$("#editor").val()
		};
//		console.log(param);
		$.ajax({
			url:url,
			data:JSON.stringify(param),
			type:"post",
			contentType:"application/json;charset=utf-8",
			success:function(data){
				if(data.code === 0){
					console.log("发表", data);
					$("#editor").val('');
					layer.msg('发表成功');
					commentInfos(infoId);
				}else{
					layer.open({
                		title: '失败！'
                		,content: data.msg
                	});
				}
			}
		});
	}
	
	//加载评论内容
	function commentInfos(id){
			console.log(id);
			var url = "/resource/pageComment?pageNo=1&pageSize=100";
			var param = {
				infoId:id	
			};
			$.ajax({
				url:url,
				data:JSON.stringify(param),
				type:"post",
				contentType:"application/json;charset=utf-8",
				success:function(data){
					console.log(data);
					if(data.code == 0){
						var source = $("#commentInfoBody").html();
	        			var template = Handlebars.compile(source);
	        			$("#commentModalInfo").html(template(data.result.content));
	        			
					}else{
						layer.open({
	                		title: '评论加载失败！'
	                		,content: data.msg
	                	});
					}
				}
			});
	}
	
});