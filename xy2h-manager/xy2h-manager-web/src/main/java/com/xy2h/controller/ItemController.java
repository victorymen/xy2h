package com.xy2h.controller;

import com.xy2h.common.pojo.EUDataGridResult;
import com.xy2h.common.utils.Xy2hResult;
import com.xy2h.pojo.TbItem;
import com.xy2h.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.0.0
 * @Date 2016/12/13
 * @Description 商品管理
 */

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItem(@PathVariable long itemId) {
        TbItem item = itemService.getItemById(itemId);
        return item;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page,Integer rows){
        EUDataGridResult dataGridResult = itemService.getItemList(page,rows);
        return dataGridResult;
    }

    @RequestMapping(value="/item/save", method= RequestMethod.POST)
    @ResponseBody
    private Xy2hResult createItem(TbItem item, String desc, String itemParams) throws Exception {
        Xy2hResult result = itemService.createItem(item, desc, itemParams);
        return result;
    }
}
