/**
 * 定义构造函数
 * @param ctx WEB运行环境
 * @param tableId 展示列表数据的表格ID
 * @param dialogId 增改实体的窗口ID
 * @param formId 增改实体的表单ID
 * @constructor
 */
function Base(ctx, tableId, dialogId, formId) {
    this.ctx = ctx;
    this.tableId = tableId;
    this.dialogId = dialogId;
    this.formId = formId;
    this.urlPath = '';
};

/**
 * 定义通用CURD方法
 * @type {{add: Function, save: Function, edit: Function, delete: Function, cancel: Function}}
 */
Base.prototype = {
    /**
     * 通用新增方法
     * @param dialogTitle 增改实体的弹窗标题
     * @param urlPath 保存实体时的请求路径
     */
    add: function(dialogTitle, urlPath) {
        $('#'+this.dialogId).dialog('open').dialog('setTitle', dialogTitle);
        $('#'+this.formId).form('clear');
        this.urlPath = this.ctx + urlPath;
    },
    /**
     * 通用保存方法
     */
    save: function() {
        var dialogId = this.dialogId;
        var tableId = this.tableId;
        $('#'+this.formId).form('submit',{
            url: this.urlPath,
            onSubmit: function(){
                return $(this).form('validate');
            },
            success: function(result){
                var result = eval('('+result+')');
                if (result['status']=='success'){
                    // 关闭对话框
                    $('#'+dialogId).dialog('close');
                    // 重载权限信息列表
                    $('#'+tableId).datagrid('reload');
                }else {
                    $.messager.show({
                        title: '错误信息',
                        msg: result
                    });
                }
            }
        });
    },
    /**
     * 通用编辑方法
     * @param dialogTitle 增改实体的弹窗标题
     * @param urlPath 更新实体时的请求路径
     */
    edit: function(dialogTitle, urlPath) {
        var row = $('#'+this.tableId).datagrid('getSelected');
        if(row) {
            $('#'+this.dialogId).dialog('open').dialog('setTitle', dialogTitle);
            $('#'+this.formId).form('load', row);
            this.urlPath = this.ctx + urlPath;
        }
    },
    /**
     * 通用删除方法
     * @param title 删除确认时的弹窗标题
     * @param content 删除确认时的弹窗提示语
     * @param urlPath 删除实体时的请求路径
     */
    delete: function(title, content, urlPath) {
        var tableId = this.tableId;
        var ctx = this.ctx;
        var row = $('#'+tableId).datagrid('getSelected');
        if (row){
            $.messager.confirm(title, content,
                function(e) {
                    if(e) {
                        $.post(ctx + urlPath,
                            {id: row.id},
                            function(result) {
                                var result = eval('('+result+')');
                                if (result['status']=='success') {
                                    // 重载角色信息列表
                                    $('#'+tableId).datagrid('reload');
                                }else {
                                    // 展示错误信息
                                    $.messager.show({
                                        title: '错误信息',
                                        msg: result
                                    });
                                }
                            },
                            'text');
                    }
                }
            );
        }
    },
    /**
     * 通用取消方法
     * 用于放弃增改实体操作时，关闭弹窗
     */
    cancel: function() {
        $('#'+this.dialogId).dialog('close');
    },
    /**
     * 清除树型下拉列表数据
     * @param combotreeId 树型下拉列表
     * @param iconCls 图表样式
     */
    combotreeClear: function(combotreeId, iconCls) {
        $('#'+combotreeId).combotree({
            icons: [{
                iconCls: iconCls,
                handler: function(e){
                    $(e.data.target).combotree('clear');
                }
            }]
        })
    }
}