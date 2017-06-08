//package com.xy2h.portal.service.impl;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.xy2h.common.utils.Xy2hResult;
//import com.xy2h.common.utils.HttpClientUtil;
//import com.xy2h.portal.pojo.SearchResult;
//import com.xy2h.portal.service.SearchService;
//
///**
// * 商品搜索Service
// * @Author XingJun Qi
// * @MyBlog www.qixingjun.tech
// * @Version 1.0.0
// * @Date 2017/2/24
// * @Description
// */
//
//@Service
//public class SearchServiceImpl implements SearchService {
//
//	@Value("${SEARCH_BASE_URL}")
//	private String SEARCH_BASE_URL;
//
//	@Override
//	public SearchResult search(String queryString, int page) {
//		// 调用xy2h-search的服务
//		//查询参数
//		Map<String, String> param = new HashMap<>();
//		param.put("q", queryString);
//		param.put("page", page + "");
//		try {
//			//调用服务
//			String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
//			//把字符串转换成java对象
//			Xy2hResult xy2hResult = Xy2hResult.formatToPojo(json, SearchResult.class);
//			if (xy2hResult.getStatus() == 200) {
//				SearchResult result = (SearchResult) xy2hResult.getData();
//				return result;
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//}
