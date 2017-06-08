package com.xy2h.service;

import com.xy2h.common.pojo.EUTreeNode;
import com.xy2h.common.utils.Xy2hResult;

import java.util.List;


public interface ContentCategoryService {

	List<EUTreeNode> getCategoryList(long parentId);
	Xy2hResult insertContentCategory(long parentId, String name);
}
