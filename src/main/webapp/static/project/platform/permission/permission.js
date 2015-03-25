// 定义构造函数
function Permission(ctx, tableId, dialogId, formId) {
    Base.call(this, ctx, tableId, dialogId, formId);
}

// 定义重载方法与拓展方法
var permissionExtend = {
    add: function(dialogTitle) {
        Base.prototype.add.call(this, dialogTitle, '/platform/account/permission/save');
    },
    edit: function(dialogTitle) {
        Base.prototype.edit.call(this, dialogTitle, '/platform/account/permission/update');
    },
    delete: function() {
        Base.prototype.delete.call(this, '删除权限', '是否确认删除选中权限？', '/platform/account/permission/delete');
    },
    doSearch: function(searchKey) {
        $('#'+this.tableId).datagrid('load',{
            name: $('#'+searchKey).val()
        });
    }
}

// 合并父类方法与拓展
Permission.prototype = $.extend({}, new Base(), permissionExtend);