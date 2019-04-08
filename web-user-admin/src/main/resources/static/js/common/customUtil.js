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
 