/**
 * 定义构造函数
 * @param ctx WEB运行环境
 * @param tableId 展示列表数据的表格ID
 * @param dialogId 增改实体的窗口ID
 * @param formId 增改实体的表单ID
 * @param treeId 关联展示的左侧菜单树ID
 * @constructor
 */
function Menu(ctx, tableId, dialogId, formId, treeId) {
    this.treeId = treeId;
    Base.call(this, ctx, tableId, dialogId, formId);
}

/**
 * 定义重载方法与拓展方法
 * @type {{add: Function, save: Function, edit: Function, delete: Function, doSearch: Function}}
 */
var menuExtend = {
    /**
     * 重载新增方法
     * @param dialogTitle 增改菜单的弹窗标题
     */
    add: function(dialogTitle) {
        Base.prototype.add.call(this, dialogTitle, '/platform/system/menu/save');
    },
    /**
     * 重载保存方法
     * @param treeId 菜单保存后需要重新加载的菜单树ID
     */
    save: function(treeId) {
        Base.prototype.save.call(this);
        // 重载左侧系统菜单树
        $('#'+this.treeId).tree('reload');
    },
    /**
     * 重载编辑方法
     * @param dialogTitle 增改菜单的弹窗标题
     */
    edit: function(dialogTitle) {
        Base.prototype.edit.call(this, dialogTitle, '/platform/system/menu/update');
        // 重载左侧系统菜单树
        $('#'+this.treeId).tree('reload');
    },
    /**
     * 重载删除方法
     */
    delete: function() {
        Base.prototype.delete.call(this, '删除系统菜单','是否确认删除选中系统菜单？', '/platform/system/menu/delete');
        // 重载左侧系统菜单树
        $('#'+this.treeId).tree('reload');
    },
    /**
     * 自定义查询方法
     * @param searchKeyMap 保存查询条件的Map
     */
    doSearch: function(searchKeyMap) {
        $('#'+this.tableId).datagrid('load',{
            name: $('#'+searchKeyMap['name']).val()
        });
    }
}

/**
 * 合并父类方法与重载或拓展的方法
 */
Menu.prototype = $.extend({}, new Base(), menuExtend);
