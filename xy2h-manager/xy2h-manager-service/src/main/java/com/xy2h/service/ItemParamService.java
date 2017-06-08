package com.xy2h.service;

import com.xy2h.common.utils.Xy2hResult;
import com.xy2h.pojo.TbItemParam;

public interface ItemParamService {
	Xy2hResult getItemParamByCid(long cid);
	Xy2hResult insertItemParam(TbItemParam itemParam);
}
