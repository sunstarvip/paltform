package com.darknight.platform.account.user.controller;

import com.darknight.core.base.entity.DataGridEntity;
import com.darknight.core.base.entity.ResultEntity;
import com.darknight.core.util.JsonUtil;
import com.darknight.platform.account.role.entity.Role;
import com.darknight.platform.account.role.service.RoleService;
import com.darknight.platform.account.user.entity.User;
import com.darknight.platform.account.user.service.UserService;
import com.darknight.platform.security.shiro.util.ShiroPasswordUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 用户管理类
 * Created by DarKnight on 2014/5/26 0026.
 */
@RestController
@RequestMapping(value = "platform/account/user")
class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private RoleService roleService;

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Resource
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 通过用户ID查询对应用户对象
     * @param userId 用户ID
     * @return User 用户对象
     */
    @ModelAttribute("user")
    public User getUser(@RequestParam(value = "userId", required = false) String userId) {
        if (StringUtils.isNotBlank(userId)) {
            return userService.find(userId);
        }
        User user = new User();
        return user;
    }

    /**
     * 通过角色ID列表查询对应角色实体列表
     * @param roleIdList 角色ID列表
     * @return List<Role> 角色实体列表
     */
    @ModelAttribute("roleList")
    public List<Role> getRoleList(@RequestParam(value = "roleList.id", required = false) List<String> roleIdList) {
        List<Role> roleList = new ArrayList<>();
        if (roleIdList != null && !roleIdList.isEmpty()) {
            roleList = roleService.find(roleIdList);
        }
        return roleList;
    }

    /**
     * 分页查询用户列表数据
     * @param request
     * @param pageable 分页容器
     * @return
     */
    @RequestMapping(value={"dataGrid"})
    public String dataGrid(HttpServletRequest request, @PageableDefault(10) Pageable pageable) {
        //由于easyUI默认页码从1开始, 分页查询时需要相应处理
        String pageStr = request.getParameter("page");
        String rowsStr = request.getParameter("rows");
        //获取查询条件
        String accountName = request.getParameter("accountName");
        String phoneNum = request.getParameter("phoneNum");
        //封装查询条件
        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("like_accountName", accountName);
        searchMap.put("phoneNum", phoneNum);

        PageRequest pageRequest = null;
        if(StringUtils.isNumeric(pageStr) && StringUtils.isNumeric(rowsStr)) {
            Integer page = Integer.valueOf(pageStr);
            Integer rows = Integer.valueOf(rowsStr);
            pageRequest = new PageRequest(page-1, rows);
        }else {
            pageRequest = new PageRequest(0, 10);
        }

        // 生成分页数据
        Page<User> userPage = userService.findSearchPage(searchMap, pageRequest);
        // 利用分页查询出来的数据生成数据表格，并JSON化
        DataGridEntity<User> userDataGrid = userService.makeDataGrid(userPage);

        String dataGridJson = JsonUtil.objToJsonString(userDataGrid);
        return dataGridJson;
    }

    /**
     * 查询用户账户名是否存在
     * @param accountName 用户账户名
     * @return
     */
    @RequestMapping(value={"checkAccountName"})
    public String checkAccountName(@RequestParam("accountName") String accountName, @RequestParam(value="id", required=false) String id) {
        // 操作状态
        boolean status = false;
        // 查询用户账户名是否已经存在对应用户
        if(StringUtils.isNotBlank(accountName)) {
            User user = userService.findByAccountName(accountName);
            if(user == null) {
                status = true;
            }else {
                // 编辑用户时当前用户ID与账户名称查询出的用户ID相同
                if(StringUtils.isNotBlank(id) && StringUtils.equals(id, user.getId())) {
                    status = true;
                }
            }
        }

        return JsonUtil.objToJsonString(status);
    }

    /**
     * 保存用户
     * @param user 用户对象
     * @return
     */
    @RequestMapping(value={"save"}, method={RequestMethod.POST})
    public String save(User user, @ModelAttribute("roleList") List<Role> roleList) {
        //保存操作结果
        ResultEntity resultData = new ResultEntity();

        //加密密码
        if(user != null) {
            // 设定默认初始密码
            user.setPassword("123456");
            ShiroPasswordUtil.getPassword(user);
            user.setRoleList(roleList);
            user = userService.save(user);
            // 修改操作状态为成功
            resultData.setStatus(ResultEntity.Status.SUCCESS);
            // 添加操作成功的返回信息
            resultData.setMsgInfo("新增用户保存成功");
        }else {
            // 保存异常日志
            logger.info("UserController.save(User user, List<Role> roleList)异常");
            logger.info("其中user为null");
            // 添加操作错误的返回信息
            resultData.setMsgInfo("新增用户保存失败");
        }

        return JsonUtil.objToJsonString(resultData);
    }

    /**
     * 更新用户
     * @param user 用户对象
     * @return
     */
    @RequestMapping(value={"update"}, method={RequestMethod.POST})
    public String update(User user, @ModelAttribute("roleList") List<Role> roleList) {
        //保存操作结果
        ResultEntity resultData = new ResultEntity();

        //加密密码
        if(user != null) {
            user.setUpdateTime(new Date());
            user.setRoleList(roleList);
            user = userService.save(user);

            // 修改操作状态为成功
            resultData.setStatus(ResultEntity.Status.SUCCESS);
            // 添加操作成功的返回信息
            resultData.setMsgInfo("用户更新成功");
        }else {
            // 保存异常日志
            logger.info("UserController.update(User user, List<Role> roleList)异常");
            logger.info("其中user为null");
            // 添加操作错误的返回信息
            resultData.setMsgInfo("用户更新失败");
        }

        return JsonUtil.objToJsonString(resultData);
    }

    @RequestMapping(value={"resetPwd"}, method={RequestMethod.POST})
    public String resetPwd(@RequestParam(value="userId") String userId) {
        //保存操作结果
        ResultEntity resultData = new ResultEntity();

        //加密密码
        if(StringUtils.isNotBlank(userId)) {
            User user = userService.find(userId);
            if(user != null && StringUtils.equals(User.VisibleTag.YES, user.getVisibleTag())) {
                user.setUpdateTime(new Date());
                // 设定默认初始密码
                user.setPassword("123456");
                ShiroPasswordUtil.getPassword(user);
                user = userService.save(user);

                // 修改操作状态为成功
                resultData.setStatus(ResultEntity.Status.SUCCESS);
                // 添加操作成功的返回信息
                resultData.setMsgInfo("密码重置成功");
            }
        }else {
            // 保存异常日志
            logger.error("UserController.restorePwd(String userId)异常");
            logger.error("userId 用户ID 为null或空白字符");
            // 添加操作错误的返回信息
            resultData.setMsgInfo("密码重置失败");
        }

        return JsonUtil.objToJsonString(resultData);
    }

    /**
     * 删除用户
     * 逻辑删除，若需物理删除建议使用SQL脚本
     * @param userId 用户ID
     * @return
     */
    @RequestMapping(value={"delete"}, method={RequestMethod.POST})
    public String delete(@RequestParam("id") String userId) {
        //保存操作结果
        ResultEntity resultData = new ResultEntity();

        if(StringUtils.isNotBlank(userId)) {
            userService.delete(userId);

            // 修改操作状态为成功
            resultData.setStatus(ResultEntity.Status.SUCCESS);
            // 添加操作成功的返回信息
            resultData.setMsgInfo("用户删除成功");
        }else {
            // 保存异常日志
            logger.error("UserController.delete(String userId)异常");
            logger.error("userId 用户ID 为null或空白字符");
            // 添加操作错误的返回信息
            resultData.setMsgInfo("用户删除失败");
        }

        return JsonUtil.objToJsonString(resultData);
    }

    @RequestMapping(value={"getLoginUser"})
    public String getLoginUser() {
        //保存操作结果
        ResultEntity resultData = new ResultEntity();

        // 获取当前登录对象
        Subject loginUser = SecurityUtils.getSubject();
        if(loginUser != null && loginUser.isAuthenticated()) {
            String dataInfo = loginUser.getPrincipal().toString();
            resultData.setDataInfo(dataInfo);
            resultData.setStatus(ResultEntity.Status.SUCCESS);
        }

        return JsonUtil.objToJsonString(resultData);
    }

}
