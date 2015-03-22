// 定义构造函数
function Permission() {
    this.ctx = '';
    this.tableId = '';
    this.dialogId = '';
    this.formId = '';
    this.urlPath = '';

    this.init = function(ctx, tableId, dialogId, formId) {
        this.ctx = ctx;
        this.tableId = tableId;
        this.dialogId = dialogId;
        this.formId = formId;
    };

    this.add = function(dialogTitle) {
        $('#'+this.dialogId).dialog('open').dialog('setTitle', dialogTitle);
        $('#'+this.formId).form('clear');
        this.urlPath = this.ctx+'/platform/account/permission/save';
    };

    this.save = function() {
        $('#'+this.formId).form('submit',{
            url: this.url,
            onSubmit: function(){
                return $(this).form('validate');
            },
            success: function(result){
                if (result['status']=='success'){
                    // 关闭对话框
                    $('#'+this.dialogId).dialog('close');
                    // 重载权限信息列表
                    $('#'+this.tableId).datagrid('reload');
                }else {
                    $.messager.show({
                        title: 'Error',
                        msg: result
                    });
                }
            }
        });
    };

    this.edit = function(dialogTitle) {
        var row = $('#'+this.tableId).datagrid('getSelected');
        if(row) {
            $('#'+this.dialogId).dialog('open').dialog('setTitle', dialogTitle);
            $('#'+this.formId).form('load', row);
            this.url = this.ctx+'/platform/account/permission/update';
        }
    };

    this.delete = function() {
        var row = $('#'+this.tableId).datagrid('getSelected');
        if (row){
            $.messager.confirm('删除权限','是否确认删除选中权限？',
                function(e) {
                    if(e) {
                        $.post(this.ctx+'/platform/account/permission/delete',
                            {roleId: row.id},
                            function(result) {
                                if (result['status']=='success') {
                                    // 重载角色信息列表
                                    $('#'+this.tableId).datagrid('reload');
                                }else {
                                    // 展示错误信息
                                    $.messager.show({
                                        title: 'Error',
                                        msg: result
                                    });
                                }
                            },
                            'json');
                    }
                }
            );
        }
    };

    this.cancel = function() {
        $('#'+this.dialogId).dialog('close');
    };

    this.doSearch = function(searchKey) {
        $('#'+this.tableId).datagrid('load',{
            name: $('#'+searchKey).val()
        });
    };
}