// 定义对象
User = {
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
        this.urlPath = this.ctx+'/platform/account/user/save';
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
                    $('#'+User.dialogId).dialog('close');
                    // 重载权限信息列表
                    $('#'+User.tableId).datagrid('reload');
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
            this.urlPath = this.ctx+'/platform/account/user/update';
        }
    },
    delete: function() {
        var row = $('#'+this.tableId).datagrid('getSelected');
        if (row){
            $.messager.confirm('删除用户','是否确认删除选中用户？',
                function(e) {
                    if(e) {
                        $.post(User.ctx+'/platform/account/user/delete',
                            {userId: row.id},
                            function(result) {
                                var result = eval('('+result+')');
                                if(result['status']=='success') {
                                    // 重载用户信息列表
                                    $('#'+User.tableId).datagrid('reload');
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
    doSearch: function(searchKeyMap) {
        $('#'+this.tableId).datagrid('load',{
            accountName: $('#'+searchKeyMap['accountName']).val(),
            phoneNum: $('#'+searchKeyMap['phoneNum']).val()
        });
    }
}
