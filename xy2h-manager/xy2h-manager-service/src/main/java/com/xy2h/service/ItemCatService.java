package com.xy2h.service;

import com.xy2h.common.pojo.EUTreeNode;

import java.util.List;

public interface ItemCatService {

	List<EUTreeNode> getCatList(long parentId);
}
