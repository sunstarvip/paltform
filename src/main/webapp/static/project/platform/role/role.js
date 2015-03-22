// 定义对象
Role = {
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
        this.urlPath = this.ctx+'/platform/account/role/save';
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
                    $('#'+Role.dialogId).dialog('close');
                    // 重载权限信息列表
                    $('#'+Role.tableId).datagrid('reload');
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
            this.urlPath = this.ctx+'/platform/account/role/update';
        }
    },
    delete: function() {
        var row = $('#'+this.tableId).datagrid('getSelected');
        if (row){
            $.messager.confirm('删除角色','是否确认删除选中角色？',
                function(e) {
                    if(e) {
                        $.post(Role.ctx+'/platform/account/role/delete',
                            {roleId: row.id},
                            function(result) {
                                var result = eval('('+result+')');
                                if(result['status']=='success') {
                                    // 重载角色信息列表
                                    $('#'+Role.tableId).datagrid('reload');
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
