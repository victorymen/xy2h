package com.xy2h.service.impl;

import com.xy2h.mapper.TbAdminUserMapper;
import com.xy2h.pojo.TbAdminUser;
import com.xy2h.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.0.0
 * @Date 2016/12/27
 * @Description
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {
    @Autowired
    TbAdminUserMapper adminUserMapper;

    @Override
    public TbAdminUser getAdminUserById(long id) {
        TbAdminUser adminUser = adminUserMapper.selectByPrimaryKey(id);
        if (adminUser!=null){
            return adminUser;
        }
        return null;
    }

    @Override
    public String login(HttpSession session, String username, String password) {
        //在Session里保存信息
        if (username=="admin"&&password=="admin"){
            //重定向
            return "true";
        }
        return "false";
    }
}
