package com.xy2h.portal.service;

import com.xy2h.portal.pojo.ItemInfo;

public interface ItemService {

	ItemInfo getItemById(Long itemId);
	String getItemDescById(Long itemId);
	String getItemParam(Long itemId);

}
