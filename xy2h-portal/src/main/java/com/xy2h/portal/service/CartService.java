package com.xy2h.portal.service;

import com.xy2h.common.utils.Xy2hResult;
import com.xy2h.portal.pojo.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CartService {

	Xy2hResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response);
	List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
	Xy2hResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
}
