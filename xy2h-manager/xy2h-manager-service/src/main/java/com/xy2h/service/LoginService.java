package com.xy2h.service;

import com.xy2h.pojo.TbAdminUser;
import javax.servlet.http.HttpSession;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.0.0
 * @Date 2016/12/27
 * @Description
 */
public interface LoginService {
    TbAdminUser getAdminUserById(long id);
    String login(HttpSession session, String username, String password);
}
