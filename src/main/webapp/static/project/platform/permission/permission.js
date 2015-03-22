// 定义对象
Permission = {
    ctx: '',
    tableId: '',
    dialogId: '',
    formId: '',
    urlPath: '',
    init: function(ctx, tableId, dialogId, formId) {
        this.ctx = ctx;
        this.tableId = tableId;
        this.dialogId = dialogId;
        this.formId = formId;
    },
    add: function(dialogTitle) {
        $('#'+this.dialogId).dialog('open').dialog('setTitle', dialogTitle);
        $('#'+this.formId).form('clear');
        this.urlPath = this.ctx+'/platform/account/permission/save';
    },
    save: function() {
        $('#'+this.formId).form('submit',{
            url: this.urlPath,
            onSubmit: function(){
                return $(this).form('validate');
            },
            success: function(result){
                var result = eval('('+result+')');
                if(result['status']=='success'){
                    // 关闭对话框
                    $('#'+Permission.dialogId).dialog('close');
                    // 重载权限信息列表
                    $('#'+Permission.tableId).datagrid('reload');
                }else {
                    $.messager.show({
                        title: 'Error',
                        msg: result
                    });
                }
            }
        });
    },
    edit: function(dialogTitle) {
        var row = $('#'+this.tableId).datagrid('getSelected');
        if(row) {
            $('#'+this.dialogId).dialog('open').dialog('setTitle', dialogTitle);
            $('#'+this.formId).form('load', row);
            this.urlPath = this.ctx+'/platform/account/permission/update';
        }
    },
    delete: function() {
        var row = $('#'+this.tableId).datagrid('getSelected');
        if (row){
            $.messager.confirm('删除权限','是否确认删除选中权限？',
                function(e) {
                    if(e) {
                        $.post(Permission.ctx+'/platform/account/permission/delete',
                            {permissionId: row.id},
                            function(result) {
                                var result = eval('('+result+')');
                                if(result['status']=='success') {
                                    // 重载角色信息列表
                                    $('#'+Permission.tableId).datagrid('reload');
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
    },
    cancel: function () {
        $('#'+this.dialogId).dialog('close');
    },
    doSearch: function(searchKey) {
        $('#'+this.tableId).datagrid('load',{
            name: $('#'+searchKey).val()
        });
    }
}
