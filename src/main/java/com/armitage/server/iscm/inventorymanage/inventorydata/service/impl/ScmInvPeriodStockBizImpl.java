package com.armitage.server.iscm.inventorymanage.inventorydata.service.impl;

import java.util.HashMap;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvPeriodStockDAO;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvPeriodStock;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvPeriodStockBiz;
import org.springframework.stereotype.Service;

@Service("scmInvPeriodStockBiz")
public class ScmInvPeriodStockBizImpl extends BaseBizImpl<ScmInvPeriodStock> implements ScmInvPeriodStockBiz {
	private PeriodCalendarBiz periodCalendarBiz;
	
	public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}

	@Override
	public void turnoffStock(String orgUnitNo, long periodId,boolean costCenter, Param param)
			throws AppException {
		PeriodCalendar periodCalendar = periodCalendarBiz.selectDirect(periodId, param);
		if(periodCalendar!=null){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("orgUnitNo", orgUnitNo);
			map.put("periodId",periodId);
			map.put("accountYear", periodCalendar.getAccountYear());
			map.put("accountPeriod", periodCalendar.getAccountPeriod());
			((ScmInvPeriodStockDAO)dao).turnoffStock(map);
		}
	}

	@Override
	public void turnbackStock(String orgUnitNo, long periodId,boolean costCenter, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("periodId",periodId);
		((ScmInvPeriodStockDAO)dao).turnbackStock(map);
	}

}
