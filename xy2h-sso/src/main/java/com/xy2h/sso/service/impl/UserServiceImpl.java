package com.xy2h.sso.service.impl;

import com.xy2h.common.utils.CookieUtils;
import com.xy2h.common.utils.JsonUtils;
import com.xy2h.common.utils.Xy2hResult;
import com.xy2h.mapper.TbUserMapper;
import com.xy2h.pojo.TbUser;
import com.xy2h.pojo.TbUserExample;
import com.xy2h.sso.dao.JedisClient;
import com.xy2h.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper userMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;

	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIRE;

	@Override
	public Xy2hResult checkData(String content, Integer type) {

		TbUserExample example = new TbUserExample();
		TbUserExample.Criteria criteria = example.createCriteria();

		// 对数据进行校验
		if (1 == type) {
			// 用户名校验
			criteria.andUsernameEqualTo(content);
		} else if (2 == type) {
			// 电话校验
			criteria.andPhoneEqualTo(content);
		} else {
			// 邮件校验
			criteria.andEmailEqualTo(content);
		}
		// 执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return Xy2hResult.ok(true);
		}
		return Xy2hResult.ok(false);
	}

	@Override
	public Xy2hResult createUser(TbUser user) {
		user.setCreated(new Date());
		user.setUpdated(new Date());
		// md5加密
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword()
				.getBytes()));

		userMapper.insert(user);
		return Xy2hResult.ok();
	}

	// 用户登录
	@Override
	public Xy2hResult userLogin(String username, String password,
								HttpServletRequest request, HttpServletResponse response) {
		TbUserExample example = new TbUserExample();
		TbUserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);

		List<TbUser> list = userMapper.selectByExample(example);
		// 如果没有该用户名
		if (null == list || list.size() == 0) {
			return Xy2hResult.build(400, "用户名或密码错误");
		}
		TbUser user = list.get(0);
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(
				user.getPassword())) {
			return Xy2hResult.build(400, "用户名或密码错误");
		}
		// 生成token
		String token = UUID.randomUUID().toString();
		// 把用户信息写入redis
		user.setPassword(null);
		jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token,
				JsonUtils.objectToJson(user));
		// 设置过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token,
				SSO_SESSION_EXPIRE);

		// 添加写cookie的逻辑，cookie的有效期是关闭浏览器失效
		CookieUtils.setCookie(request, response, "TT_TOKEN", token);
		return Xy2hResult.ok(token);
	}


	@Override
	public Xy2hResult getUserByToken(String token) {

		// 根据token从redis中查询用户信息
		String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
		if (StringUtils.isBlank(json)) {
			return Xy2hResult.build(400, "会话过期，请重新登录");
		}
		// 更新过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token,
				SSO_SESSION_EXPIRE);
		// 返回用户信息
		return Xy2hResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
	}

	// 退出登录
	@Override
	public Xy2hResult userLogout(String token) {
		// 根据token从redis删除用户信息
		jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
		// 返回用户信息
		return Xy2hResult.ok();
	}
}
