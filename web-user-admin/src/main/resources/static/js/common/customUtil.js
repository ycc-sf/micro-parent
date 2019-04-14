/**
 * 工具
 */
var CustomUtil = (function() {
	//私有变量
	var currentTime = new Date().getTime(); 
	//公有方法
	return {
		
		//初始化网页头部用户名
		initUsernameInHTMLHead : function(){
			var realName = $.cookie("realName");
		    $("#realName").html(realName);
		},
		
		
		//加载信息类型
		loadInfoType : function(){
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
	    },
		
		
		// 接收uri参数
		receiveData : function(){
			var url = decodeURI(window.location.href);  
			var index = url.indexOf('?');  
			if (index < 0) {  
			    return;  
			}  
			var parameters = {};  
			var entrys = url.substring(++index, url.length).split('&');  
			for (var i = 0, len = entrys.length; i < len; i++) {  
			    var entry = entrys[i].split('=');  
			    parameters[entry[0]] = entry[1];  
			}  
			$.data(document, 'parameters', parameters);
		},
		
		//获取通过receiveData函数存储的值
		requestValue : function(key) {  
		    var parameters = $.data(document, 'parameters');  
		    return parameters === undefined ? undefined : parameters[key];  
		}
	}
})();
 