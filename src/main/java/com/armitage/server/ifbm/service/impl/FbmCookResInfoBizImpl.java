package com.armitage.server.ifbm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.ifbm.dao.FbmCookResInfoDAO;
import com.armitage.server.ifbm.model.FbmCookResInfo2;
import com.armitage.server.ifbm.service.FbmCookResInfoBiz;
import org.springframework.stereotype.Service;

@Service("fbmCookResInfoBiz")
public class FbmCookResInfoBizImpl extends BaseBizImpl<FbmCookResInfo2> implements FbmCookResInfoBiz {

	@Override
	public List<FbmCookResInfo2> selectCookSalePrice(String orgUnitNo, String controlUnitNo,String fbcOrgUnitNo, Date accDate, List<Long> cookList,
			Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo",orgUnitNo);
		map.put("controlUnitNo",controlUnitNo);
		map.put("fbcOrgUnitNo",fbcOrgUnitNo);
		map.put("accDate",FormatUtils.fmtDate(accDate));
		map.put("cookList",cookList);
		return ((FbmCookResInfoDAO)dao).selectCookSalePrice(map);
	}

}
