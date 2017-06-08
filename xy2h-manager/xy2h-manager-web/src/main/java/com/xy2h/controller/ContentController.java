package com.xy2h.controller;

import com.xy2h.common.utils.Xy2hResult;
import com.xy2h.pojo.TbContent;
import com.xy2h.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 内容管理Controller
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.0.0
 * @Date 2016/12/13
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
//
	@RequestMapping("/save")
	@ResponseBody
	public Xy2hResult insertContent(TbContent content) {
		Xy2hResult result = contentService.insertContent(content);
		return result;
	}
}
