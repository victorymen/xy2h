package com.xy2h.order.controller;

import com.xy2h.common.utils.ExceptionUtil;
import com.xy2h.common.utils.Xy2hResult;
import com.xy2h.order.pojo.Order;
import com.xy2h.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/create")
	@ResponseBody
	public Xy2hResult createOrder(@RequestBody Order order){
		try {
			Xy2hResult result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return Xy2hResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
