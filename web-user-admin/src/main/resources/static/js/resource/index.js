layui.use(['form','element', 'layer'], function(){
	////////////////////////////////本页面全局变量////////////////////////////////////
	//当前位置坐标
	var thisPosition = new AMap.LngLat(113.50928629557302, 34.812260470921);
	var infoRange = 200;//要加载的信息的半径
	
	///////////////////////////////初始化执行//////////////////////////////////////
	//加载动画
    $('html').loading({
		loadingWidth:240,
		title:'加载中',
		name:'mapLoading',
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
	//初始化页面姓名
	CustomUtil.initUsernameInHTMLHead();
    
    //layui初始化变量
    var form = layui.form
		,element = layui.element
		,layer = layui.layer;
    //加载搜索框的下拉内容
    loadInfoType();
    
    form.on('submit()',function(data){
       window.open("html/resource/result.html?infoType=" + data.field.infoType
    		   	 + "&title=" + data.field.title, "_blank");
       return false;
    })
    
    var map;
    setTimeout(function () {
    	//初始化地图
        map = new AMap.Map('mapContainer', {
            zoom:11,//级别
            center: [113.6347424, 34.7218855],//中心点坐标
            viewMode:'3D'//使用3D视图
        });
        //定位
        map.plugin('AMap.Geolocation', function() {
            var geolocation = new AMap.Geolocation({
                // 是否使用高精度定位，默认：true
                enableHighAccuracy: true,
                // 设置定位超时时间，默认：无穷大
                timeout: 10000,
                // 定位按钮的停靠位置的偏移量，默认：Pixel(10, 20)
                buttonOffset: new AMap.Pixel(10, 20),
                //  定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
                zoomToAccuracy: true,
                //  定位按钮的排放位置,  RB表示右下
                buttonPosition: 'RB'
            })
            geolocation.getCurrentPosition()
            AMap.event.addListener(geolocation, 'complete', onComplete)
            AMap.event.addListener(geolocation, 'error', onError)
            function onComplete (data) {
                // data是具体的定位信息
                console.log("定位成功", data);
                layer.msg("定位成功");
                //修改当前位置
                thisPosition = data.position;
                map.setCenter(data.position);
            }
            function onError (data) {
                // 定位出错
//                layer.msg('由于浏览器不支持等原因导致定位失败，默认加载郑州市信息');
            }
        })
        
        //定位成功后，根据定位信息加载最新发布和地图打点
        loadLatestInfo();
        //去除加载动画
        removeLoading('mapLoading');
	}, 500);

    ////////////////////////////////自定义函数//////////////////////////////////////
    //加载最新发布
    function loadLatestInfo(){
    	//准备参数
    	var uri = "/resource/findRangedInfoList?x="+thisPosition.getLng() + "&y="+thisPosition.getLat() +"&range="+infoRange + "&number=11";
    	//请求数据
    	$.ajax({
            url:uri,
            type: "get",
            contentType:"application/json;charset=utf-8",
            success:function(data){
            	console.log(data);
                if(data.code == 0){
                	//handlebar遍历左侧推荐信息
        			var source = $("#latestInfoContent").html();
        			var template = Handlebars.compile(source);
        			$("#latestInfoFrame").html(template(data.result));
        			//在地图标记推荐信息
        			for(var i = 0; i< data.result.length; i++){
            		    var marker = new AMap.Marker({
            		        position:[data.result[i].locationX, data.result[i].locationY]//位置
            		    	,title:data.result[i].infoTitle
            		    })
            		    marker.setExtData(data.result[i].id);//给marker设置信息id
            		    map.add(marker);//添加到地图
            		    //点标记点击事件
            		    marker.on("click",function(e) {
            		    	var infoId = this.getExtData();//获取点标记上的文章id
            		    	window.open("html/resource/detail.html?infoId=" + infoId, "_blank");
            		    });
        			}
                }else{
                	layer.open({
                		title: '数据请求失败，请重试'
                		,content: data.msg
                	});
                }
            }
        });
    }
    
    //加载信息类型
    function loadInfoType(){
    	//准备参数
    	var uri = "/resource/getInfoType";
    	//请求数据
    	$.ajax({
            url:uri,
            type: "get",
            contentType:"application/json;charset=utf-8",
            success:function(data){
            	console.log(data);
                if(data.code == 0){
                	console.log("下拉列表", data);
                	//handlebar遍历左侧推荐信息
        			var source = $("#infoTypeContent").html();
        			var template = Handlebars.compile(source);
        			$("#infoTypeFrame").html(template(data.result));
        			//select重新渲染
        			form.render('select');
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
