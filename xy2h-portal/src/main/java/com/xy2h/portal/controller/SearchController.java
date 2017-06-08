//package com.xy2h.portal.controller;
//
//import java.io.UnsupportedEncodingException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.xy2h.portal.pojo.SearchResult;
//import com.xy2h.portal.service.SearchService;
//
///**
// * 商品详情页面展示
// * @Author XingJun Qi
// * @MyBlog www.qixingjun.tech
// * @Version 1.0.0
// * @Date 2017/2/24
// * @Description
// */
//@Controller
//public class SearchController {
//
//	@Autowired
//	private SearchService searchService;
//
//	@RequestMapping("/search")
//	public String search(@RequestParam("q")String queryString, @RequestParam(defaultValue="1")Integer page, Model model) {
//		if (queryString != null) {
//			try {
//				queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//		}
//		SearchResult searchResult = searchService.search(queryString, page);
//		//向页面传递参数
//		model.addAttribute("query", queryString);
//		model.addAttribute("totalPages", searchResult.getPageCount());
//		model.addAttribute("itemList", searchResult.getItemList());
//		model.addAttribute("page", page);
//
//		return "search";
//
//	}
//}
