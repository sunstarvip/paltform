// 定义构造函数
function Role(ctx, tableId, dialogId, formId) {
    Base.call(this, ctx, tableId, dialogId, formId);
}

// 定义重载方法与拓展方法
var roleExtend = {
    add: function(dialogTitle) {
        Base.prototype.add.call(this, dialogTitle, '/platform/account/role/save');
    },
    edit: function(dialogTitle) {
        Base.prototype.edit.call(this, dialogTitle, '/platform/account/role/update');
    },
    delete: function() {
        Base.prototype.delete.call(this, '删除角色','是否确认删除选中角色？', '/platform/account/role/delete');
    },
    doSearch: function(searchKey) {
        $('#'+this.tableId).datagrid('load',{
            name: $('#'+searchKey).val()
        });
    }
}

// 合并父类方法与拓展
Role.prototype = $.extend({}, new Base(), roleExtend);

