package com.darknight.platform.util.controller;

import com.darknight.platform.account.user.entity.User;
import com.darknight.platform.account.user.service.UserService;
import com.darknight.platform.security.shiro.util.ShiroPasswordUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by DarKnight on 2015/2/16.
 */
@Controller
@RequestMapping(value = "platform/util/test")
public class TestController {
    private static final Log log = LogFactory.getLog(TestController.class);

    private UserService userService;

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 初始化项目后通过该方法可以为项目生成默认管理员Admin对象
     * @return
     */
    @RequestMapping(value = "initAdmin")
    @ResponseBody
    public String initAdmin() {
        User adminUser = new User();
        adminUser.setCreateTime(new Date());
        adminUser.setUpdateTime(new Date());
        adminUser.setAccountName("admin");
        adminUser.setPassword("admin");

        adminUser.setName("admin");
        adminUser.setSort(0);

        //对admin用户初始密码经行MD5加密
        String password = ShiroPasswordUtil.getPassword(adminUser);

        String state = "fail";
        try{
            adminUser = userService.save(adminUser);
            state = "success";
            //记录操作日志
            log.info("Admin对象初始化成功，userId为：" + adminUser.getId());
        }catch (Exception e) {
            //记录操作日志
            log.error("Admin对象初始化失败", e);
        }

        return state;
    }
}
