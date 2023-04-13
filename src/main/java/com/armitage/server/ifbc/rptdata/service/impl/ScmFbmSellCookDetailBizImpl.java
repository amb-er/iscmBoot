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
import com.armitage.server.ifbc.rptdata.dao.ScmFbmSellCookDetailDAO;
import com.armitage.server.ifbc.rptdata.model.ScmFbmSellCookDetail;
import com.armitage.server.ifbc.rptdata.service.ScmFbmSellCookDetailBiz;
import com.armitage.server.ifbm.model.FbmSellCookDetail;
import com.armitage.server.ifbm.service.FbmSellDetailBiz;
import org.springframework.stereotype.Service;

@Service("scmFbmSellCookDetailBiz")
public class ScmFbmSellCookDetailBizImpl extends BaseBizImpl<ScmFbmSellCookDetail> implements ScmFbmSellCookDetailBiz {
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
			List<FbmSellCookDetail> fbmSellCookDetailList = fbmSellDetailBiz.selectCookSellDetail(resOrgUnitNo, controlUnitNo, scmAccountingCycle.getStartDate(), scmAccountingCycle.getEndDate(), param);
			if(fbmSellCookDetailList!=null && !fbmSellCookDetailList.isEmpty()) {
				List<ScmFbmSellCookDetail> scmFbmSellCookDetailList = new ArrayList();
				for(FbmSellCookDetail fbmSellCookDetail:fbmSellCookDetailList) {
					ScmFbmSellCookDetail scmFbmSellCookDetail = new ScmFbmSellCookDetail(true);
					BeanUtil.copyProperties(scmFbmSellCookDetail, fbmSellCookDetail);
					scmFbmSellCookDetail.setOrgUnitNo(orgUnitNo);
					scmFbmSellCookDetailList.add(scmFbmSellCookDetail);
				}
				this.batchAdd(scmFbmSellCookDetailList, param);
			}
		}
	}

	@Override
	public void batchAdd(List<ScmFbmSellCookDetail> scmFbmSellCookDetailList, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("scmFbmSellCookDetailList", scmFbmSellCookDetailList);
		((ScmFbmSellCookDetailDAO)dao).batchAdd(map);
	}

	@Override
	public void delRptData(Date begDate, Date endDate, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo", param.getOrgUnitNo());
		map.put("begDate", FormatUtils.fmtDate(begDate));
		map.put("endDate", FormatUtils.fmtDate(endDate));
		((ScmFbmSellCookDetailDAO)dao).delRptData(map);
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
		List<FbmSellCookDetail> fbmSellCookDetailList = fbmSellDetailBiz.selectCookSellDetail(resOrgUnitNo, controlUnitNo, begDate, endDate, param);
		if(fbmSellCookDetailList!=null && !fbmSellCookDetailList.isEmpty()) {
			List<ScmFbmSellCookDetail> scmFbmSellCookDetailList = new ArrayList();
			for(FbmSellCookDetail fbmSellCookDetail:fbmSellCookDetailList) {
				ScmFbmSellCookDetail scmFbmSellCookDetail = new ScmFbmSellCookDetail(true);
				BeanUtil.copyProperties(scmFbmSellCookDetail, fbmSellCookDetail);
				scmFbmSellCookDetail.setOrgUnitNo(orgUnitNo);
				scmFbmSellCookDetailList.add(scmFbmSellCookDetail);
			}
			this.batchAdd(scmFbmSellCookDetailList, param);
		}
	}

}
