package com.xy2h.service;

import com.xy2h.common.pojo.EUDataGridResult;
import com.xy2h.common.utils.Xy2hResult;
import com.xy2h.pojo.TbItem;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.0.0
 * @Date 2016/12/13
 * @Description 商品管理
 */
public interface ItemService {
    TbItem getItemById(long itemId);
    EUDataGridResult getItemList(int page,int rows);
    Xy2hResult createItem(TbItem item,String desc,String itemParam) throws Exception;
}
