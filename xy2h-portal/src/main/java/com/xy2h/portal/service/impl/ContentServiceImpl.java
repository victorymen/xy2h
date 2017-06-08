package com.xy2h.portal.service.impl;

import com.xy2h.common.utils.HttpClientUtil;
import com.xy2h.common.utils.JsonUtils;
import com.xy2h.common.utils.Xy2hResult;
import com.xy2h.pojo.TbContent;
import com.xy2h.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调用服务层服务，查询内容列表
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.0.0
 * @Date 2017/2/24
 * @Description
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;

	@Override
	public String getContentList() {
		//调用服务层的服务
		String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
		//把字符串转换成xy2hResult
		try {
			Xy2hResult xy2hResult = Xy2hResult.formatToList(result, TbContent.class);
			//取内容列表
			List<TbContent> list = (List<TbContent>) xy2hResult.getData();
			List<Map> resultList = new ArrayList<>();
 			//创建一个jsp页码要求的pojo列表
			for (TbContent tbContent : list) {
				Map map = new HashMap<>();
				map.put("src", "http://192.168.0.106/images/2017/06/07/1496840183246660.jpg");
				map.put("height", 240);
				map.put("width", 670);
				map.put("srcB", "http://192.168.0.106/images/2017/06/07/1496840183246660.jpg");
				map.put("widthB", 550);
				map.put("heightB", 240);
				map.put("href", "item/149683981317071.html");
				map.put("alt", "苹果10大促销苹果10大促销");

//				map.put("src", tbContent.getPic());
//				map.put("height", 240);
//				map.put("width", 670);
//				map.put("srcB", tbContent.getPic2());
//				map.put("widthB", 550);
//				map.put("heightB", 240);
//				map.put("href", tbContent.getUrl());
//				map.put("alt", tbContent.getSubTitle());

				resultList.add(map);
			}
			return JsonUtils.objectToJson(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
