layui.use(['layer', 'table', 'ax', 'laydate'], function () {
    var $ = layui.$;
    var $ax = layui.ax;
    var layer = layui.layer;
    var table = layui.table;
    var laydate = layui.laydate;

    /**
     * 系统管理--操作日志
     */
    var FeedBackVal = {
        tableId: "feedbackTable"   //表格id
    };

    /**
     * 初始化表格的列
     */
    FeedBackVal.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'RECORDID', hide: true, sort: true, title: 'recordid'},
            {field: 'FBCONTENT', sort: true, title: '反馈内容'},
            {field: 'QQORWXNUM', sort: true, title: '客户QQ/微信'},
            {field: 'PHONENUM', sort: true, title: '客户手机号'},
            {field: 'NICKNAME', sort: true, title: '客户昵称'},
            {field: 'FBTIME', sort: true, title: '反馈时间'},
            {field: 'RESPTIME', sort: true, title: '回复时间'},
            {field: 'RESPCONTENT', sort: true, title: '回复内容'},
            {field: 'RESPONDENT', sort: true, title: '回复人'},
            {align: 'CENTER', toolbar: '#tableBar', title: '操作', minWidth: 100}
        ]];
    };

    /**
     * 点击查询按钮
     */
    FeedBackVal.search = function () {
        var queryData = {};
        queryData['fbcontent'] = $("#fbcontent").val();
        queryData['qqorwxnum'] = $("#qqorwxnum").val();
        queryData['nickname'] = $("#nickname").val();
        queryData['respcontent'] = $("#respcontent").val();
        queryData['respstate'] = $("#respstate").val();
        table.reload(FeedBackVal.tableId, {where: queryData});
    };

 

    /**
     * 点击编辑通知
     *
     * @param data 点击按钮时候的行数据
     */
    FeedBackVal.fbDetail = function (data) {
    	
//    	alert(data.RECORDID)
    	layer.open({
    	    type: 2 ,
    	    title: '反馈回复',   //标题
    	    area: ['480px', '330px'],   //宽高
    	    shade: 0.4,   //遮罩透明度
    	    content: Feng.ctxPath + '/feedback/returnMessage/' + data.RECORDID,
    	    
    	});
    };


    // 渲染表格
    var tableResult = table.render({
        elem: '#' + FeedBackVal.tableId,
        url: Feng.ctxPath + '/feedback/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: FeedBackVal.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        FeedBackVal.search();
    });

    // 工具条点击事件
    table.on('tool(' + FeedBackVal.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'detail') {
            FeedBackVal.fbDetail(data);
        }
    });
});
