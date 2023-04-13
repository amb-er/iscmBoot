package com.armitage.server.ifbc.rptdata.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycle;
import com.armitage.server.ifbc.basedata.model.ScmResOrgUnitMap;
import com.armitage.server.ifbc.basedata.service.ScmAccountingCycleBiz;
import com.armitage.server.ifbc.basedata.service.ScmResOrgUnitMapBiz;
import com.armitage.server.ifbc.rptdata.dao.ScmFbmSellDetailDAO;
import com.armitage.server.ifbc.rptdata.model.ScmFbmSellDetail;
import com.armitage.server.ifbc.rptdata.service.ScmFbmSellDetailBiz;
import com.armitage.server.ifbm.model.FbmSellDetail;
import com.armitage.server.ifbm.service.FbmSellDetailBiz;
import org.springframework.stereotype.Service;

@Service("scmFbmSellDetailBiz")
public class ScmFbmSellDetailBizImpl extends BaseBizImpl<ScmFbmSellDetail> implements ScmFbmSellDetailBiz {
	private ScmAccountingCycleBiz scmAccountingCycleBiz;
	private ScmResOrgUnitMapBiz scmResOrgUnitMapBiz;
	private FbmSellDetailBiz fbmSellDetailBiz;
	
	public void setScmAccountingCycleBiz(ScmAccountingCycleBiz scmAccountingCycleBiz) {
		this.scmAccountingCycleBiz = scmAccountingCycleBiz;
	}

	public void setScmResOrgUnitMapBiz(ScmResOrgUnitMapBiz scmResOrgUnitMapBiz) {
		this.scmResOrgUnitMapBiz = scmResOrgUnitMapBiz;
	}

	public void setFbmSellDetailBiz(FbmSellDetailBiz fbmSellDetailBiz) {
		this.fbmSellDetailBiz = fbmSellDetailBiz;
	}

	@Override
	public void calcRptData(String orgUnitNo, long begPeriodId, long endPeriodId, Param param) throws AppException {
		List<ScmAccountingCycle> scmAccountingCycleList = scmAccountingCycleBiz.selectByBegAndEndId(orgUnitNo, begPeriodId, endPeriodId, param);
		if(scmAccountingCycleList==null || scmAccountingCycleList.isEmpty()) {
			throw new AppException("field.ScmAccountingCycle.error.wrongperiod");
		}
		String resOrgUnitNo = param.getOrgUnitNo();
		String controlUnitNo = param.getControlUnitNo();
		ScmResOrgUnitMap scmResOrgUnitMap = scmResOrgUnitMapBiz.selectByResOrgUnit(resOrgUnitNo, param);
		if(scmResOrgUnitMap!=null && !scmResOrgUnitMap.isShared()) {
			resOrgUnitNo = scmResOrgUnitMap.getFbmResOrgUnitNo();
			controlUnitNo = scmResOrgUnitMap.getFbmControlUnitNo();
		}
		for(ScmAccountingCycle scmAccountingCycle:scmAccountingCycleList) {
			this.delRptData(scmAccountingCycle.getStartDate(), scmAccountingCycle.getEndDate(), param);
			List<FbmSellDetail> fbmSellDetailList = fbmSellDetailBiz.selectDishSellDetail(resOrgUnitNo, controlUnitNo, scmAccountingCycle.getStartDate(), scmAccountingCycle.getEndDate(), param);
			if(fbmSellDetailList!=null && !fbmSellDetailList.isEmpty()) {
				List<ScmFbmSellDetail> scmFbmSellDetailList = new ArrayList();
				for(FbmSellDetail fbmSellDetail:fbmSellDetailList) {
					ScmFbmSellDetail scmFbmSellDetail = new ScmFbmSellDetail(true);
					BeanUtil.copyProperties(scmFbmSellDetail, fbmSellDetail);
					scmFbmSellDetail.setOrgUnitNo(orgUnitNo);
					scmFbmSellDetailList.add(scmFbmSellDetail);
				}
				this.batchAdd(scmFbmSellDetailList, param);
			}
		}
	}

	@Override
	public void batchAdd(List<ScmFbmSellDetail> scmFbmSellDetailList, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("scmFbmSellDetailList", scmFbmSellDetailList);
		((ScmFbmSellDetailDAO)dao).batchAdd(map);
	}

	@Override
	public void delRptData(Date begDate, Date endDate, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo", param.getOrgUnitNo());
		map.put("begDate", FormatUtils.fmtDate(begDate));
		map.put("endDate", FormatUtils.fmtDate(endDate));
		((ScmFbmSellDetailDAO)dao).delRptData(map);
	}

	@Override
	public void calcRptDataByTask(String orgUnitNo, Date begDate, Date endDate, Param param) throws AppException {
		param.setOrgUnitNo(orgUnitNo);
		String resOrgUnitNo = param.getOrgUnitNo();
		String controlUnitNo = param.getControlUnitNo();
		ScmResOrgUnitMap scmResOrgUnitMap = scmResOrgUnitMapBiz.selectByResOrgUnit(resOrgUnitNo, param);
		if(scmResOrgUnitMap!=null && !scmResOrgUnitMap.isShared()) {
			resOrgUnitNo = scmResOrgUnitMap.getFbmResOrgUnitNo();
			controlUnitNo = scmResOrgUnitMap.getFbmControlUnitNo();
		}
		this.delRptData(begDate, endDate, param);
		List<FbmSellDetail> fbmSellDetailList = fbmSellDetailBiz.selectDishSellDetail(resOrgUnitNo, controlUnitNo, begDate, endDate, param);
		if(fbmSellDetailList!=null && !fbmSellDetailList.isEmpty()) {
			List<ScmFbmSellDetail> scmFbmSellDetailList = new ArrayList();
			for(FbmSellDetail fbmSellDetail:fbmSellDetailList) {
				ScmFbmSellDetail scmFbmSellDetail = new ScmFbmSellDetail(true);
				BeanUtil.copyProperties(scmFbmSellDetail, fbmSellDetail);
				scmFbmSellDetail.setOrgUnitNo(orgUnitNo);
				scmFbmSellDetailList.add(scmFbmSellDetail);
			}
			this.batchAdd(scmFbmSellDetailList, param);
		}
	}

}
