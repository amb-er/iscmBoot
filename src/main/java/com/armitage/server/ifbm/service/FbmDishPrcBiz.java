package com.armitage.server.ifbm.service;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbm.model.FbmDishPrc2;

public interface FbmDishPrcBiz extends BaseBiz<FbmDishPrc2> {
	public FbmDishPrc2 selectByDishAndSpecId(String orgUnitNo,long dishId,long dishSpecId,Param param) throws AppException;
	public FbmDishPrc2 selectByDishSpecCode(String orgUnitNo,String dishSpecCode,long dishId,Param param) throws AppException;
	public List<FbmDishPrc2> selectDishSalePrice(String orgUnitNo,String controlUnitNo,String fbcOrgUnitNo,Date accDate,List<Long> dishList,Param param) throws AppException;
}
