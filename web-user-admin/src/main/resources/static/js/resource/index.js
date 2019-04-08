layui.use(['form','element', 'layer'], function(){
	////////////////////////////////本页面全局变量////////////////////////////////////
	var thisPosition = {
	        P: 34.812260470921,
	        R: 113.50928629557302,
	        lat: 34.81226,
	        lng: 113.509286
	};//当前位置坐标
	var infoRange = 200;//要加载的信息的半径
	
	///////////////////////////////初始化执行//////////////////////////////////////
    //初始化页面姓名
	CustomUtil.initUsernameInHTMLHead();
//    var realName = $.cookie("realName");
//    $("#realName").html(realName);
    
    //加载最新发布
    loadLatestInfo();
    
    //layui初始化变量
    var form = layui.form
		,element = layui.element
		,layer = layui.layer;
    
    //初始化地图
    var map = new AMap.Map('mapContainer', {
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
            // alert("由于浏览器不支持等原因导致定位失败，默认加载郑州市信息");
            layer.msg('由于浏览器不支持等原因导致定位失败，默认加载郑州市信息');
        }
    })

    //点标记
    var marker1 = new AMap.Marker({
        position:[116.39, 39.9]//位置
    })
    //map.add(marker1);//添加到地图
    //map.remove(marker)//移除标记

    // form.on('submit(formDemo)',function(data){
    //     lay.msg(JSON.stringify(data.field));
    //     return false;
    // })
    
    
    ////////////////////////////////自定义函数//////////////////////////////////////
    //加载最新发布
    function loadLatestInfo(){
    	//准备参数
    	var uri = "/resource/findRangedInfoList?x="+thisPosition.R + "&y="+thisPosition.P +"&range="+infoRange + "&number=11";
    	//请求数据
    	$.ajax({
            url:uri,
            type: "get",
            contentType:"application/json;charset=utf-8",
            success:function(date){
            	console.log(date);
                if(date.code == 0){
                	//超长截取
//                	Handlebars.registerHelper("compare1",function(v1,options){
//        				if(v1.length>6){
//        					return v1.substring(0,6)+"...";
//        				}else{
//        					return v1;
//        				}
//        			});
        			var source = $("#latestInfoContent").html();
        			var template = Handlebars.compile(source);
        			$("#latestInfoFrame").html(template(date.result));
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
