package com.xy2h.order.service.impl;

import java.util.Date;
import java.util.List;

import com.xy2h.common.utils.Xy2hResult;
import com.xy2h.mapper.TbOrderItemMapper;
import com.xy2h.mapper.TbOrderMapper;
import com.xy2h.mapper.TbOrderShippingMapper;
import com.xy2h.order.dao.JedisClient;
import com.xy2h.order.service.OrderService;
import com.xy2h.pojo.TbOrder;
import com.xy2h.pojo.TbOrderItem;
import com.xy2h.pojo.TbOrderShipping;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	
	@Autowired
	private TbOrderMapper orderMapper;
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	@Autowired
	private TbOrderShippingMapper orderShippingMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${ORDER_INIT_ID}")
	private String ORDER_INIT_ID;
	
	@Value("${ORDER_GEN_KEY}")
	private String ORDER_GEN_KEY;
	
	@Value("${ORDER_DETAIL_GEN_KEY}")
	private String ORDER_DETAIL_GEN_KEY;
	
	@Override
	public Xy2hResult createOrder(TbOrder order, List<TbOrderItem> itemList,
								  TbOrderShipping orderShipping) {
		
		//向订单表中插入记录
		String string = jedisClient.get(ORDER_GEN_KEY);
		if(StringUtils.isBlank(string)){
			jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
		}
		//获得订单号
		long orderId = jedisClient.incr(ORDER_GEN_KEY);
		
		
		//补全pojo的属性
		order.setOrderId(orderId+"");
		
		//1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，
		order.setStatus(1);
		Date date=new Date();
		order.setCreateTime(date);
		order.setUpdateTime(date);
		order.setBuyerRate(0);  //0未评价，1评价
		
		orderMapper.insert(order);
		//插入订单明细
		
		for (TbOrderItem tbOrderItem : itemList) {
			//补全订单明细
			long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
			tbOrderItem.setId(orderDetailId+"");
			
			//向订单明细插入到记录
			tbOrderItem.setOrderId(orderId+"");
			orderItemMapper.insert(tbOrderItem);
		}
		//插入物流表
		//补全物流表
		orderShipping.setOrderId(orderId+"");
		orderShipping.setUpdated(date);
		orderShipping.setCreated(date);
		orderShippingMapper.insert(orderShipping);
		
		return Xy2hResult.ok(orderId);
	}

}