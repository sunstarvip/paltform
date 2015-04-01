// 定义构造函数
function Menu(ctx, tableId, dialogId, formId) {
    Base.call(this, ctx, tableId, dialogId, formId);
}

// 定义重载方法与拓展方法
var menuExtend = {
    add: function(dialogTitle) {
        Base.prototype.add.call(this, dialogTitle, '/platform/system/menu/save');
    },
    edit: function(dialogTitle) {
        Base.prototype.edit.call(this, dialogTitle, '/platform/system/menu/update');
    },
    delete: function() {
        Base.prototype.delete.call(this, '删除系统菜单','是否确认删除选中系统菜单？', '/platform/system/menu/delete');
    },
    doSearch: function(searchKeyMap) {
        $('#'+this.tableId).datagrid('load',{
            name: $('#'+searchKeyMap['name']).val(),
            type: $('#'+searchKeyMap['type']).val()
        });
    }
}

// 合并父类方法与拓展
Menu.prototype = $.extend({}, new Base(), menuExtend);
