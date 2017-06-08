package com.xy2h.order.service;

import com.xy2h.common.utils.Xy2hResult;
import com.xy2h.pojo.TbOrder;
import com.xy2h.pojo.TbOrderItem;
import com.xy2h.pojo.TbOrderShipping;

import java.util.List;

public interface OrderService {
	
	Xy2hResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
}
