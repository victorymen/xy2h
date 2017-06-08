package com.xy2h.portal.service.impl;

import com.xy2h.portal.pojo.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xy2h.common.utils.Xy2hResult;
import com.xy2h.common.utils.HttpClientUtil;
import com.xy2h.common.utils.JsonUtils;
import com.xy2h.portal.service.OrderService;

/**
 * 订单处理Service
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.0.0
 * @Date 2017/2/24
 * @Description
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;


	@Override
	public String createOrder(Order order) {
		//调用创建订单服务之前补全用户信息。
		//从cookie中后取TT_TOKEN的内容，根据token调用sso系统的服务根据token换取用户信息。

		//调用xy2h-order的服务提交订单。
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, JsonUtils.objectToJson(order));
		//把json转换成xy2hResult
		Xy2hResult xy2hResult = Xy2hResult.format(json);
		if (xy2hResult.getStatus() == 200) {
			Object orderId = xy2hResult.getData();
			return orderId.toString();
		}
		return "";
	}
}