package com.darknight.platform.system.menu.controller;

import com.darknight.core.base.entity.DataGridEntity;
import com.darknight.core.util.JsonUtil;
import com.darknight.platform.system.menu.entity.Menu;
import com.darknight.platform.system.menu.entity.MenuNode;
import com.darknight.platform.system.menu.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单实体控制层
 * Created by DarKnight on 2015/4/1.
 */
@RestController
@RequestMapping(value = "platform/system/menu")
public class MenuController {
    private MenuService menuService;

    @Resource
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 通过系统菜单ID由Spring注入系统菜单对象
     * @param menuId 系统菜单ID
     * @return
     */
    @ModelAttribute("menu")
    public Menu getMenu(@RequestParam(value = "menuId", required = false) String menuId) {
        if (StringUtils.isNotBlank(menuId)) {
            return menuService.find(menuId);
        }
        Menu menu = new Menu();
        return menu;
    }

    /**
     * 查询列表数据
     * @param request
     * @return
     */
    @RequestMapping(value={"dataGrid"})
    public String dataGrid(HttpServletRequest request) {
        //由于easyUI默认页码从1开始, 分页查询时需要相应处理
        String pageStr = request.getParameter("page");
        String rowsStr = request.getParameter("rows");
        //获取查询条件
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        //封装查询条件
        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("like_name", name);
        searchMap.put("like_type", type);

        PageRequest pageRequest = null;
        if(StringUtils.isNumeric(pageStr) && StringUtils.isNumeric(rowsStr)) {
            Integer page = Integer.valueOf(pageStr);
            Integer rows = Integer.valueOf(rowsStr);
            pageRequest = new PageRequest(page-1, rows);
        }else {
            pageRequest = new PageRequest(0, 10);
        }

        // 生成分页数据
        Page<Menu> menuPage = menuService.findSearchPage(searchMap, pageRequest);
        // 利用分页查询出来的数据生成数据表格，并JSON化
        DataGridEntity<Menu> menuDataGrid = menuService.makeDataGrid(menuPage);

        String dataGridJson = JsonUtil.objToJsonString(menuDataGrid);
        return dataGridJson;
    }

    /**
     * 查询菜单树
     * @param request
     * @return
     */
    @RequestMapping(value={"menuTree"})
    public String menuTree(HttpServletRequest request) {
        List<Menu> menuList = menuService.findAllVisibleTopMenu();
        List<MenuNode> menuNodeList = menuService.makeNode(menuList);

        String listJson = JsonUtil.objToJsonString(menuNodeList);
        return listJson;
    }

    /**
     * 保存系统菜单
     * @param menu 系统菜单对象
     * @return
     */
    @RequestMapping(value={"save"}, method={RequestMethod.POST})
    public String save(Menu menu) {
        //保存操作状态
        String status = "success";
        if(menu != null) {
            // 处理页面中可能为系统菜单自动添加空对象作为父级菜单，导致保存报错的BUG
            if(menu.getParent() != null && StringUtils.isBlank(menu.getParent().getId())) {
                menu.setParent(null);
            }
            menu = menuService.save(menu);
        }else {
            status = "fail";
        }

        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("status", status);

        return JsonUtil.objToJsonString(resultMap);
    }

    /**
     * 更新系统菜单
     * @param menu 系统菜单对象
     * @return
     */
    @RequestMapping(value={"update"}, method={RequestMethod.POST})
    public String update(Menu menu) {
        //保存操作状态
        String status = "success";
        if(menu != null) {
            // 处理页面中可能为系统菜单自动添加空对象作为父级菜单，导致保存报错的BUG
            if(menu.getParent() != null && StringUtils.isBlank(menu.getParent().getId())) {
                menu.setParent(null);
            }
            menu.setUpdateTime(new Date());
            menu = menuService.save(menu);
        }else {
            status = "fail";
        }

        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("status", status);

        return JsonUtil.objToJsonString(resultMap);
    }

    /**
     * 删除系统菜单
     * 逻辑删除，若需物理删除建议使用SQL脚本
     * @param menuId 系统菜单ID
     * @return
     */
    @RequestMapping(value={"delete"}, method={RequestMethod.POST})
    public String delete(@RequestParam("id") String menuId) {
        //保存操作状态
        String status = "success";
        if(StringUtils.isNotBlank(menuId)) {
            menuService.delete(menuId);
        }else {
            status = "fail";
        }

        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("status", status);

        return JsonUtil.objToJsonString(resultMap);
    }
}
