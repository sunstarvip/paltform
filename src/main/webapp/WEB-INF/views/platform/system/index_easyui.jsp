<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.DarKnight.com.cn/jsp-extends" prefix="inheritance" %>
<%--<c:set var="ctx" value="${pageContext.request.contextPath}"/>--%>

<%--重定义父页面name=scriptSrc的内容--%>
<inheritance:override name="scriptSrc">
    <script type="text/javascript" src="${ctx}/static/project/platform/base/base.js" ></script>
    <%--<script type="text/javascript" src="${ctx}/static/project/platform/menu/menu.js" ></script>--%>
</inheritance:override>

<inheritance:override name="body">
    <body class="easyui-layout" id="mainPanel" >

        <%-- North Block --%>
    <inheritance:block name="north">
        <div region="north" split="true" style="height:100px;">
            <inheritance:block name="northContent">
                <div class="easyui-layout" id="northPanel" fit="true" style="vertical-align:middle;">
                    <div region="east" split="true" style="width:200px;">
                        <div id="userDiv">您好，Admin</div>
                        <%--<div class="easyui-layout" id="userOptPanel" fit="true" style="vertical-align:middle;">--%>
                            <%--<div region="center">--%>
                                <%--您好，Admin--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                </div>
            </inheritance:block>
        </div>
    </inheritance:block>

        <%-- South Block --%>
    <inheritance:block name="south">
        <div region="south" title="South Block" split="true" collapsed="true" style="height:100px;">
            <inheritance:block name="southContent">

            </inheritance:block>
        </div>
    </inheritance:block>
        <%--<div data-options="region:'south',title:'South Title',split:true,collapsed:true" style="height:100px;"></div>--%>

        <%-- East Block --%>
    <inheritance:block name="east">
        <div region="east" title="East Block" split="true" collapsed="true" style="width:100px;">
            <inheritance:block name="eastContent">

            </inheritance:block>
        </div>
    </inheritance:block>
        <%--<div data-options="region:'east',title:'East',split:true,collapsed:true" style="width:100px;"></div>--%>

        <%-- West Block --%>
    <inheritance:block name="west">
        <div region="west" title="系统菜单" split="true" style="width:200px;">
            <inheritance:block name="westContent">
                <ul id="menuTree" class="easyui-tree">

                </ul>
            </inheritance:block>
        </div>
    </inheritance:block>
        <%--<div data-options="region:'west',title:'West',split:true" style="width:200px;"></div>--%>

        <%-- Center Block --%>
    <inheritance:block name="center">
        <div class="easyui-tabs" id="centerBlock" region="center" style="padding:5px;">
            <inheritance:block name="centerContent">

            </inheritance:block>
        </div>
    </inheritance:block>
        <%--<div data-options="region:'center',title:'center title'" style="padding:5px;"></div>--%>

        <div id="dialogBlock" ></div>
        <%--<div id="dlg-buttons">--%>
            <%--<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="menu.save()">保存</a>--%>
            <%--<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="menu.cancel()">取消</a>--%>
        <%--</div>--%>

    <inheritance:block name="scriptBlock">
        <inheritance:block name="northScript">
        </inheritance:block>

        <inheritance:block name="southScript">
        </inheritance:block>

        <inheritance:block name="eastScript">
        </inheritance:block>

        <inheritance:block name="westScript">
            <script>
                // 打开系统菜单对应的tab页面
                function addTab(title, url){
                    if ($('#centerBlock').tabs('exists', title)) {
                        $('#centerBlock').tabs('select', title);
                    } else {
                        // 处理内部url与外部url之间的关系
                        if(!!url && url.indexOf("http://") == -1) {
                            url = '${ctx}' + url;
                        }
                        var content = '<iframe scrolling="auto" frameborder="0" src="'+url+'" style="width:100%;height:100%;"></iframe>';
                        $('#centerBlock').tabs('add', {
                            title: title,
                            content: content,
                            closable: true  // 是否可关闭
                        });
                    }
                }

                // 用户登出
                function userLogout() {
                    window.location.href='${ctx}/logout';
                }

                // 获取登录用户信息
                function getUserInfo() {
                    $.get('${ctx}/platform/account/user/getLoginUser',
                            function(resultData, status) {
                                var resultData = eval('('+resultData+')');
                                if (resultData['status']=='success') {
                                    // 重载角色信息列表
                                    $('#userDiv').text('您好，'+resultData['dataInfo']);
                                    var logoutBtnStr = '<a href="${ctx}/logout">退出</a>'
                                    $('#userDiv').append(logoutBtnStr);
                                }
                            });
                }

                //页面JS初始化
                $(function() {
                    // 添加点击左侧系统菜单在右侧tab标签中打开页面的效果
                    $('#menuTree').tree({
                        url: "${ctx}/platform/system/menu/menuTree",
                        onSelect: function(node){
                            addTab(node['text'], node['urlPath']);
                        }
                    });
                    // 获取登录用户信息
                    getUserInfo();
                });
            </script>
        </inheritance:block>

        <inheritance:block name="centerScript">
        </inheritance:block>
    </inheritance:block>

    </body>
</inheritance:override>


<!-- 继承父类 base.jsp -->
<%@ include file="/WEB-INF/views/base/base.jsp" %>
