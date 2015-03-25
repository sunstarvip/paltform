// 定义构造函数
function User(ctx, tableId, dialogId, formId) {
    Base.call(this, ctx, tableId, dialogId, formId);
}

// 定义重载方法与拓展方法
var userExtend = {
    add: function(dialogTitle) {
        Base.prototype.add.call(this, dialogTitle, '/platform/account/user/save');
    },
    edit: function(dialogTitle) {
        Base.prototype.edit.call(this, dialogTitle, '/platform/account/user/update');
    },
    delete: function() {
        Base.prototype.delete.call(this, '删除用户','是否确认删除选中用户？', '/platform/account/user/delete');
    },
    doSearch: function(searchKeyMap) {
        $('#'+this.tableId).datagrid('load',{
            accountName: $('#'+searchKeyMap['accountName']).val(),
            phoneNum: $('#'+searchKeyMap['phoneNum']).val()
        });
    }
}

// 合并父类方法与拓展
User.prototype = $.extend({}, new Base(), userExtend);
