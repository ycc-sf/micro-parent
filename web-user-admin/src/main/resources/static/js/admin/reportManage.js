layui.use(['table','form'], function(){
  var table = layui.table;
  var form = layui.form;

  var pageNo = 1;
  var infoTable = {
      elem: '#layuiTable' //指定原始表格元素选择器（推荐id选择器）
      , url: '/admin/pageReport'
      , where: {
      }
      , page : true
      , cols: [[ //标题栏
          {field:'id',title:'ID',width:120,sort:true,align:'center'}
          ,{field:'infoId', hide:true}
          ,{field:'isDeal',title:'状态',width:120,align:'center'
        	  ,templet: function (data){
	        	  console.log("moban:", data);
	        	  if(data.isDeal == 0){
	        		  return "未解决";
	        	  }
	        	  return "解决";
	          }
          }
          ,{field:'userId',title:'举报人id',width:120,align:'center'}
          ,{field:'infoTitle',title:'信息标题',align:'center'}
	      ,{field:'reportDetail',title:'举报详情',align:'center'}
	      ,{field:'createDate',title:'发布时间',width:120,align:'center'}
	      ,{title:'操作',width:220,/*toolbar:"#barDemo",*/align:'center'
	    	  ,templet: function (data){
	        	  var btns = "<a class='layui-btn layui-btn-normal layui-btn-sm' lay-event='detail'>信息详情</a>";
	        	  if(data.isDeal == 1){
	        		  return btns += "";
	        	  }
	        	  return btns += "<a class='layui-btn layui-btn-danger layui-btn-sm' lay-event='deal'>标记解决</a>";
	    	  }
	      }
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
  		window.open("/html/resource/detail.html?infoId=" + obj.data.infoId, "_blank");
  	} else if(layEvent === 'deal'){
  		//删除
  		layer.confirm('要标记解决吗？', {icon:3, title:"提示"}, function(index){
  			  //获取当前行的appCode
  			  $.ajax({
  		    	  url:"/admin/updateReportById?id=" + obj.data.id,
  		    	  type:"put",
  		    	  success:function(res){
  		    		  if(res.code == 0){
  		    			  //重载table数据
  		    			  infoTableRender.reload({ //此处是上文提到的 初始化标识id
//  		    				  where: data.field,
  		    		          page: {
  		    		              curr: 1 //重新从第 1 页开始
  		    		          }
  		    		      });
  		    			  layer.close(index);
  		    			  layer.msg("成功");
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

});