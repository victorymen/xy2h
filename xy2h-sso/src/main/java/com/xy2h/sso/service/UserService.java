package com.xy2h.sso.service;

import com.xy2h.common.utils.Xy2hResult;
import com.xy2h.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface UserService {
	
	Xy2hResult checkData(String content, Integer type);
	Xy2hResult createUser(TbUser user);
	Xy2hResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

	Xy2hResult getUserByToken(String token);

	/*
	 *
	 * 退出登录
	 */
	Xy2hResult userLogout(String token);
}
