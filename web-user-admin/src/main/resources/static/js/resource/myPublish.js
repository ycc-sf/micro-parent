layui.use(['form','table','element'], function(){
	var form = layui.form
		,element = layui.element;
	var table = layui.table; 
	//初始化页面姓名
	CustomUtil.initUsernameInHTMLHead();
	//加载信息类型
    loadInfoType();
	
    var pageNo = 1;
    var infoTable = {
        elem: '#infoTable' //指定原始表格元素选择器（推荐id选择器）
        , url: '/resource/pageInfo'
        , where: {
        	userId : 999
        }
        , page : true
        , cols: [[ //标题栏
            {field:'id',title:'ID',width:120,sort:true,align:'center'},
	          {field:'infoTitle',title:'标题',align:'center'},
	          {field:'createDate',title:'发布时间',width:120,align:'center'},
	          {title:'操作',width:200,toolbar:"#barDemo",align:'center'}
        ]]
        , contentType: 'application/json'
        , method: 'post'
        , request: {
      	  	pageName: 'pageNo' //页码的参数名称，默认：page
            , limitName: 'pageSize' //每页数据量的参数名，默认：limit
        }
        , done: function (res) {
        }
        ,parseData: function(res){ //res 即为原始返回的数据
        	console.log("res", res);
        	if(res.code != 0){
          	  layer.open({
            		title: '数据请求失败，请重试'
            		,content: res.msg
            	});
            }else{
            	return {
                    "code": res.code, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.result.totalElements, //解析数据长度
                    "data": res.result.content //解析数据列表
                  };
            }
        }
    };
    var infoTableRender = table.render(infoTable);
    
    //监听表格按钮
    table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    	var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    	if(layEvent === 'detail'){
    		//新标签页打开详情
    		window.open("/html/resource/detail.html?infoId=" + obj.data.id, "_blank");
    	} else if(layEvent === 'delete'){
    		//删除
    		layer.confirm('真的要删除吗？', {icon:3, title:"提示"}, function(index){
    			  //获取当前行的appCode
    			  $.ajax({
    		    	  url:"/resource/removeInfoById?id=" + obj.data.id,
    		    	  type:"delete",
    		    	  success:function(res){
    		    		  if(res.code == 0){
    		    			  //重载table数据
    		    			  infoTableRender.reload({ //此处是上文提到的 初始化标识id
//    		    				  where: data.field,
    		    		          page: {
    		    		              curr: 1 //重新从第 1 页开始
    		    		          }
    		    		      });
    		    			  layer.close(index);
    		    			  layer.msg("删除成功");
    		    		  }else{
    		    			  layer.close(index);
    		    			  layer.msg(res.msg);
    		    		  }
    		    	  }
    		      });
    		  });
    	}
  	});
    
    //条件搜索
    form.on('submit(formDemo)',function(data){
    	data.field.userId = 999;
        console.log(JSON.stringify(data.field));
        //重载table数据
    	infoTableRender.reload({ //此处是上文提到的 初始化标识id
            where: data.field,
            page: {
                curr: 1 //重新从第 1 页开始
            }
        });
        return false;
    });
	///////////////////////////自定义函数///////////////////////////////////
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