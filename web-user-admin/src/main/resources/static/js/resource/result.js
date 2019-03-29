layui.use(['form','table','element'], function(){
    var form = layui.form
        ,element = layui.element;
    var table = layui.table;

    table.render({
        elem:'.layui-table',
        url:'xxx',
        page:true,
        cols:[[
            {field:'id',title:'ID',width:120,sort:true,fixed:'left',align:'center'},
            {field:'category',title:'类别',width:120,align:'center'},
            {field:'issuer',title:'发布人',width:120,align:'center'},
            {field:'telphone',title:'联系方式',width:120,align:'center'},
            {field:'address',title:'地址',width:120,align:'center'},
            {field:'creatTime',title:'发布时间',width:120,align:'center'},
            {field:'right',title:'操作',width:150,toolbar:"#barDemo",align:'center'}
        ]]
    })

    form.on('submit(formDemo)',function(data){
        lay.msg(JSON.stringify(data.field));
        return false;
    });
});