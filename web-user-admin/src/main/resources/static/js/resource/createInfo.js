layui.use(['form','element'], function(){
	
	//当前位置坐标
	var thisPosition = new AMap.LngLat(113.50928629557302, 34.812260470921);
//	var thisPosition = {
//	        P: 34.812260470921,
//	        R: 113.50928629557302,
//	        lat: 34.81226,
//	        lng: 113.509286
//	};
	
	//加载信息类型
	loadInfoType();
	//layui渲染
	var form = layui.form
	  ,element = layui.element;
	
	//初始化头部用户信息
	CustomUtil.initUsernameInHTMLHead();
	
	//详情富文本编辑器初始化
	var E = window.wangEditor
	var editor = new E('#editor')
	editor.create();
	
	//初始化地图
    var map = new AMap.Map('mapContainer', {
        zoom:11,//级别
        center: [thisPosition.getLng(), thisPosition.getLat()],//中心点坐标
        viewMode:'3D'//使用3D视图
    });
    
    //当前位置
    var marker = new AMap.Marker({
        position:[thisPosition.getLng(), thisPosition.getLat()]//位置
    })
    map.add(marker);//添加到地图
    
    //地图绑定点击事件
    map.on('click',function(e){
    	//移动点标记到点击位置
	    marker.setPosition(e.lnglat);
	    //更新本页地址全局变量到点击目标点
	    thisPosition = e.lnglat;
	    console.log("点击了：", e.lnglat);
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
            //切换地图中心到当前位置
            map.setCenter(data.position);
            //移动点标记到当前位置
            marker.setPosition(data.position);
        }
        function onError (data) {
            // 定位出错
            // alert("由于浏览器不支持等原因导致定位失败，默认加载郑州市信息");
            layer.msg('由于浏览器不支持等原因导致定位失败');
        }
    })
    
	//////////////////////////自定义函数///////////////////////////////////
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