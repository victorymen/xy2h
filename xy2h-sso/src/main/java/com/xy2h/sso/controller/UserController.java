package com.xy2h.sso.controller;

import com.xy2h.common.utils.ExceptionUtil;
import com.xy2h.common.utils.Xy2hResult;
import com.xy2h.pojo.TbUser;
import com.xy2h.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {
		
		Xy2hResult result = null;
		
		//参数有效性校验
		if (StringUtils.isBlank(param)) {
			result = Xy2hResult.build(400, "校验内容不能为空");
		}
		if (type == null) {
			result = Xy2hResult.build(400, "校验内容类型不能为空");
		}
		if (type != 1 && type != 2 && type != 3 ) {
			result = Xy2hResult.build(400, "校验内容类型错误");
		}
		//校验出错
		if (null != result) {
			if (null != callback) {
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			} else {
				return result; 
			}
		}
		//调用服务
		try {
			result = userService.checkData(param, type);
			
		} catch (Exception e) {
			result = Xy2hResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		if (null != callback) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		} else {
			return result; 
		}
	}
	
	
	//创建用户
	@RequestMapping(value="/register",method= RequestMethod.POST)
	@ResponseBody
	public Xy2hResult createUser(TbUser user){
		try {
			Xy2hResult result = userService.createUser(user);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return Xy2hResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

	//接收表单，包含用户名和密码
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public  Xy2hResult userLogin(String username, String password,
								 HttpServletRequest request, HttpServletResponse response){
		try {
			Xy2hResult result = userService.userLogin(username, password,request,response);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return Xy2hResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

	//接收token调用service返回用户信息

	@RequestMapping("/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable  String token,String callback){

		Xy2hResult result=null;

		try {
			result = userService.getUserByToken(token);

		} catch (Exception e) {
			e.printStackTrace();
			return Xy2hResult.build(500, ExceptionUtil.getStackTrace(e));
		}

		//判断是否为json调用
		if(StringUtils.isBlank(callback)){
			return result;
		}else{
			MappingJacksonValue  mappingJacksonValue=new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
	}

	//退出登录
	@RequestMapping("/logout/{token}")
	@ResponseBody
	public Object userLogout(@PathVariable String token, String callback) {
		Xy2hResult result = null;
		try {
			result = userService.userLogout(token);
		} catch (Exception e) {
			e.printStackTrace();
			result = Xy2hResult.build(500, ExceptionUtil.getStackTrace(e));
		}


		if (StringUtils.isBlank(callback)) {
			return result;
		} else {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(
					result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
	}

}

