// 定义构造函数
function Base(ctx, tableId, dialogId, formId) {
    this.ctx = ctx;
    this.tableId = tableId;
    this.dialogId = dialogId;
    this.formId = formId;
    this.urlPath = '';
};

Base.prototype = {
    add: function(dialogTitle, urlPath) {
        $('#'+this.dialogId).dialog('open').dialog('setTitle', dialogTitle);
        $('#'+this.formId).form('clear');
        this.urlPath = this.ctx + urlPath;
    },
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
    edit: function(dialogTitle, urlPath) {
        var row = $('#'+this.tableId).datagrid('getSelected');
        if(row) {
            $('#'+this.dialogId).dialog('open').dialog('setTitle', dialogTitle);
            $('#'+this.formId).form('load', row);
            this.urlPath = this.ctx + urlPath;
        }
    },
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
    cancel: function() {
        $('#'+this.dialogId).dialog('close');
    }
}