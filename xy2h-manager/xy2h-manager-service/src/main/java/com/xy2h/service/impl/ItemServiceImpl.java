package com.xy2h.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xy2h.common.pojo.EUDataGridResult;
import com.xy2h.common.utils.IDUtils;
import com.xy2h.common.utils.Xy2hResult;
import com.xy2h.mapper.TbItemDescMapper;
import com.xy2h.mapper.TbItemMapper;
import com.xy2h.mapper.TbItemParamItemMapper;
import com.xy2h.pojo.TbItem;
import com.xy2h.pojo.TbItemDesc;
import com.xy2h.pojo.TbItemExample;
import com.xy2h.pojo.TbItemParamItem;
import com.xy2h.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.0.0
 * @Date 2016/12/13
 * @Description 商品管理
 */
@Service("itemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public TbItem getItemById(long itemId) {
        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        if (item!=null){
            return item;
        }
        return null;
    }

    /**
     * 商品列表查询
     * @param page
     * @param rows
     * @return EUDataGridResult
     */
    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        //查询商品列表
        TbItemExample itemExample = new TbItemExample();
        //分页处理
        PageHelper.startPage(page,rows);
        List<TbItem> itemList = itemMapper.selectByExample(itemExample);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(itemList);
        //取记录条数
        PageInfo<TbItem> pageInfo = new PageInfo<>(itemList);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public Xy2hResult createItem(TbItem item,String desc,String itemParam) throws Exception {
        //item补全
        //生成商品ID
        Long itemId = IDUtils.genItemId();
        item.setId(itemId);
        // '商品状态，1-正常，2-下架，3-删除',
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //插入到数据库
        itemMapper.insert(item);
        //添加商品描述信息
        Xy2hResult result = insertItemDesc(itemId, desc);
        if (result.getStatus() != 200) {
            throw new Exception();
        }
        //添加规格参数
        result = insertItemParamItem(itemId, itemParam);
        if (result.getStatus() != 200) {
            throw new Exception();
        }
        return Xy2hResult.ok();
    }

    /**
     * 添加商品描述
     * <p>Title: insertItemDesc</p>
     * <p>Description: </p>
     * @param desc
     */
    private Xy2hResult insertItemDesc(Long itemId, String desc) {
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insert(itemDesc);
        return Xy2hResult.ok();
    }

    /**
     * 添加规格参数
     * <p>Title: insertItemParamItem</p>
     * <p>Description: </p>
     * @param itemId
     * @param itemParam
     * @return
     */
    private Xy2hResult insertItemParamItem(Long itemId, String itemParam) {
        //创建一个pojo
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        //向表中插入数据
        itemParamItemMapper.insert(itemParamItem);
        return Xy2hResult.ok();
    }
}
