package com.armitage.server.ifbm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.ifbm.dao.FbmSellDetailDAO;
import com.armitage.server.ifbm.model.FbmSellCookDetail;
import com.armitage.server.ifbm.model.FbmSellDetail;
import com.armitage.server.ifbm.service.FbmSellDetailBiz;
import org.springframework.stereotype.Service;

@Service("fbmSellDetailBiz")
public class FbmSellDetailBizImpl extends BaseBizImpl<FbmSellDetail> implements FbmSellDetailBiz {

	@Override
	public List<FbmSellDetail> selectDishSellDetail(String orgUnitNo, String controlUnitNo, Date begDate,
			Date endDate, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("controlUnitNo", controlUnitNo);
		map.put("begDate", FormatUtils.fmtDate(begDate));
		map.put("endDate", FormatUtils.fmtDate(endDate));
		return ((FbmSellDetailDAO)dao).selectDishSellDetail(map);
	}

	@Override
	public List<FbmSellCookDetail> selectCookSellDetail(String orgUnitNo, String controlUnitNo, Date begDate,
			Date endDate, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("controlUnitNo", controlUnitNo);
		map.put("begDate", FormatUtils.fmtDate(begDate));
		map.put("endDate", FormatUtils.fmtDate(endDate));
		return ((FbmSellDetailDAO)dao).selectCookSellDetail(map);
	}
	

}
