package com.armitage.server.ifbm.service;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbm.model.FbmCookResInfo2;

public interface FbmCookResInfoBiz extends BaseBiz<FbmCookResInfo2> {
	public List<FbmCookResInfo2> selectCookSalePrice(String orgUnitNo,String controlUnitNo,String fbcOrgUnitNo,Date accDate,List<Long> cookList,Param param) throws AppException;
}
