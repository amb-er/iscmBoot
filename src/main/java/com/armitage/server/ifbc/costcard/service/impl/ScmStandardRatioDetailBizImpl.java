package com.armitage.server.ifbc.costcard.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.costcard.dao.ScmStandardRatioDetailDAO;
import com.armitage.server.ifbc.costcard.model.ScmStandardRatioDetail;
import com.armitage.server.ifbc.costcard.service.ScmStandardRatioDetailBiz;
import org.springframework.stereotype.Service;

@Service("scmStandardRatioDetailBiz")
public class ScmStandardRatioDetailBizImpl extends BaseBizImpl<ScmStandardRatioDetail> implements ScmStandardRatioDetailBiz {

	@Override
	public void deleteByRateId(long rateId, Param param) throws AppException {
		List<ScmStandardRatioDetail> scmStandardRatioDetailList = this.selectByRateId(rateId, param);
		this.delete(scmStandardRatioDetailList, param);
	}

	@Override
	public List<ScmStandardRatioDetail> selectByRateId(long rateId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("rateId", rateId);
		return ((ScmStandardRatioDetailDAO)dao).selectByRateId(map);
	}

}
