package com.armitage.server.ifbc.costcard.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLog;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLogDetail;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLogDetail2;
import com.armitage.server.ifbc.costcard.service.ScmItemCostPriceBiz;
import com.armitage.server.ifbc.costcard.service.ScmItemCostPriceLogBiz;
import com.armitage.server.ifbc.costcard.service.ScmItemCostPriceLogDetailBiz;
import org.springframework.stereotype.Service;

@Service("scmItemCostPriceLogBiz")
public class ScmItemCostPriceLogBizImpl extends BaseBizImpl<ScmItemCostPriceLog> implements ScmItemCostPriceLogBiz {
	private ScmItemCostPriceLogDetailBiz scmItemCostPriceLogDetailBiz;
	private ScmItemCostPriceBiz scmItemCostPriceBiz;

	public void setScmItemCostPriceLogDetailBiz(ScmItemCostPriceLogDetailBiz scmItemCostPriceLogDetailBiz) {
		this.scmItemCostPriceLogDetailBiz = scmItemCostPriceLogDetailBiz;
	}

	public void setScmItemCostPriceBiz(ScmItemCostPriceBiz scmItemCostPriceBiz) {
		this.scmItemCostPriceBiz = scmItemCostPriceBiz;
	}

	@Override
	public ScmItemCostPriceLog addUpdLog(String orgUnitNo, Param param) throws AppException {
		ScmItemCostPriceLog scmItemCostPriceLog = new ScmItemCostPriceLog(true);
		scmItemCostPriceLog.setOrgUnitNo(orgUnitNo);
		scmItemCostPriceLog.setBeginDate(CalendarUtil.relativeDate(FormatUtils.parseDate(FormatUtils.fmtDate(new Date())),-1));
		scmItemCostPriceLog.setEndDate(FormatUtils.parseDate(FormatUtils.fmtDate(new Date())));
		scmItemCostPriceLog.setPriceDate(FormatUtils.parseDate(FormatUtils.fmtDate(new Date())));
		scmItemCostPriceLog.setOperDate(new Date());
		scmItemCostPriceLog.setControlUnitNo(param.getControlUnitNo());
		scmItemCostPriceLog = this.add(scmItemCostPriceLog, param);
		List<ScmItemCostPriceLogDetail2> itemPrice = scmItemCostPriceLogDetailBiz.getItemPrice(scmItemCostPriceLog, param);
		List<ScmItemCostPriceLogDetail2> itemPriceAdd = new ArrayList<>();
		for (ScmItemCostPriceLogDetail2 scmItemCostPriceLogDetail2 : itemPrice) {
			scmItemCostPriceLogDetail2.setLogId(scmItemCostPriceLog.getId());
			itemPriceAdd.add(scmItemCostPriceLogDetail2);
		}
		scmItemCostPriceLogDetailBiz.add(itemPriceAdd, param);
		return scmItemCostPriceLog;
	}

	@Override
	public List<ScmItemCostPriceLogDetail2> getItemPrice(ScmItemCostPriceLog scmItemCostPriceLog, Param param)
			throws AppException {
		return scmItemCostPriceLogDetailBiz.getItemPrice(scmItemCostPriceLog,param);
	}

	@Override
	protected void beforeAdd(ScmItemCostPriceLog entity, Param param) throws AppException {
		entity.setPriceDate(FormatUtils.parseDate(FormatUtils.fmtDate(new Date())));
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmItemCostPriceLog scmItemCostPriceLog = (ScmItemCostPriceLog) bean.getList().get(0);
		List<ScmItemCostPriceLogDetail2> dtlList = bean.getList2();
		if (scmItemCostPriceLog != null && dtlList != null && !dtlList.isEmpty()) {
			for(ScmItemCostPriceLogDetail2 scmItemCostPriceLogDetail:dtlList) {
				scmItemCostPriceLogDetail.setLogId(scmItemCostPriceLog.getId());
			}
			scmItemCostPriceLogDetailBiz.batchAdd(dtlList, param);
			//批量更新价格
			scmItemCostPriceBiz.batchAddItemPrice(scmItemCostPriceLog, param);
			//批次更新历史价格表
			scmItemCostPriceBiz.updateItemPriceHistory(scmItemCostPriceLog, param);
		}
	}

}
