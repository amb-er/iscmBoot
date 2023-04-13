package com.armitage.server.ifbm.service;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbm.model.FbmSellCookDetail;
import com.armitage.server.ifbm.model.FbmSellDetail;

public interface FbmSellDetailBiz extends BaseBiz<FbmSellDetail> {

	public List<FbmSellDetail> selectDishSellDetail(String orgUnitNo,String controlUnitNo,Date begDate,Date endDate, Param param) throws AppException ;
	public List<FbmSellCookDetail> selectCookSellDetail(String orgUnitNo,String controlUnitNo,Date begDate,Date endDate, Param param) throws AppException ;

}
