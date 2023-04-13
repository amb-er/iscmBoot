package com.armitage.server.ifbm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.ifbm.dao.FbmDishPrcDAO;
import com.armitage.server.ifbm.model.FbmDishPrc2;
import com.armitage.server.ifbm.service.FbmDishPrcBiz;
import org.springframework.stereotype.Service;

@Service("fbmDishPrcBiz")
public class FbmDishPrcBizImpl extends BaseBizImpl<FbmDishPrc2> implements FbmDishPrcBiz {

	@Override
	public FbmDishPrc2 selectByDishAndSpecId(String orgUnitNo, long dishId, long dishSpecId, Param param)
			throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo",orgUnitNo);
		map.put("dishId",dishId);
		map.put("dishSpecId",dishSpecId);
		return ((FbmDishPrcDAO)dao).selectByDishAndSpecId(map);
	}

	@Override
	public FbmDishPrc2 selectByDishSpecCode(String orgUnitNo, String dishSpecCode, long dishId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo",orgUnitNo);
		map.put("dishSpecCode",dishSpecCode);
		map.put("dishId",dishId);
		return ((FbmDishPrcDAO)dao).selectByDishSpecCode(map);
	}

	@Override
	public List<FbmDishPrc2> selectDishSalePrice(String orgUnitNo, String controlUnitNo,String fbcOrgUnitNo, Date accDate, List<Long> dishList,
			Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo",orgUnitNo);
		map.put("controlUnitNo",controlUnitNo);
		map.put("fbcOrgUnitNo",fbcOrgUnitNo);
		map.put("accDate",FormatUtils.fmtDate(accDate));
		map.put("dishList",dishList);
		return ((FbmDishPrcDAO)dao).selectDishSalePrice(map);
	}

}
