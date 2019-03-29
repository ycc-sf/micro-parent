layui.use(['form','element', 'layer'], function(){
    /* $(".header").load("header.html");*/
    var thisPosition = {
        P: 34.812260470921,
        R: 113.50928629557302,
        lat: 34.81226,
        lng: 113.509286
    };//当前位置坐标
    var form = layui.form
        ,element = layui.element
        ,layer = layui.layer;

    //初始化页面姓名
    var realName = $.cookie("realName");
    $("#realName").html(realName);

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
            map.setCenter(data.position);
            //修改当前位置
            thisPosition = data.position;
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
    map.add(marker1);//添加到地图
    var marker2 = new AMap.Marker({
        position:[116.36123, 39.8123]//位置
    })
    map.add(marker2);//添加到地图
    var marker3 = new AMap.Marker({
        position:[116.39234, 39.9432]//位置
    })
    map.add(marker3);//添加到地图
    //map.remove(marker)//移除标记




    // form.on('submit(formDemo)',function(data){
    //     lay.msg(JSON.stringify(data.field));
    //     return false;
    // })


});

//	function loadHtml(url){
//		alert(url);
//		url = url+"&temp="+Math.random();
//		$("#contentDiv").load(url,function(response,status,xhr){
//
//	    });
//	}
//
//	$(function(){
//		$("ul li:first-child dl dd a:first-child").click();
//	});