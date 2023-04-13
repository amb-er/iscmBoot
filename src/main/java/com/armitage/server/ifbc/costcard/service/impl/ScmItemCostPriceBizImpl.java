package com.armitage.server.ifbc.costcard.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.ifbc.costcard.dao.ScmItemCostPriceDAO;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPrice2;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLog;
import com.armitage.server.ifbc.costcard.service.ScmItemCostPriceBiz;
import com.armitage.server.ifbc.costcard.service.ScmItemCostPriceHistoryBiz;
import com.armitage.server.ifbc.costcard.service.ScmItemCostPriceLogBiz;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import org.springframework.stereotype.Service;

@Service("scmItemCostPriceBiz")
public class ScmItemCostPriceBizImpl extends BaseBizImpl<ScmItemCostPrice2> implements ScmItemCostPriceBiz {
	private ScmMeasureUnitBiz scmMeasureUnitBiz;
	private ScmItemCostPriceHistoryBiz scmItemCostPriceHistoryBiz;
	private ScmItemCostPriceLogBiz scmItemCostPriceLogBiz;
	
	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}

	public void setScmItemCostPriceHistoryBiz(ScmItemCostPriceHistoryBiz scmItemCostPriceHistoryBiz) {
		this.scmItemCostPriceHistoryBiz = scmItemCostPriceHistoryBiz;
	}

	public void setScmItemCostPriceLogBiz(ScmItemCostPriceLogBiz scmItemCostPriceLogBiz) {
		this.scmItemCostPriceLogBiz = scmItemCostPriceLogBiz;
	}

	@Override
	protected void afterSelect(ScmItemCostPrice2 entity, Param param) throws AppException {
		if(entity!=null) {
			setConvertMap(entity,param);
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list!=null && !list.isEmpty()) {
			for(ScmItemCostPrice2 scmItemCostPrice:(List<ScmItemCostPrice2>)list) {
				setConvertMap(scmItemCostPrice,param);
			}
		}
	}
	
	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmItemCostPrice2.class) + "." + ScmItemCostPrice2.FN_ORGUNITNO), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmItemCostPrice2.class) + "." + ScmItemCostPrice2.FN_ORGUNITNO), QueryParam.QUERY_EQ, param.getOrgUnitNo()));
	}

	private void setConvertMap(ScmItemCostPrice2 scmItemCostPrice,Param param) {
		if(scmItemCostPrice!=null) {
			if(scmItemCostPrice.getInvUnitId()>0) {
				ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmItemCostPrice.getInvUnitId(), param);
				if(scmMeasureUnit!=null) {
					scmItemCostPrice.setConvertMap(ScmItemCostPrice2.FN_INVUNITID, scmMeasureUnit);
				}
			}
			if(scmItemCostPrice.getCstUnitId()>0) {
				ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmItemCostPrice.getCstUnitId(), param);
				if(scmMeasureUnit!=null) {
					scmItemCostPrice.setConvertMap(ScmItemCostPrice2.FN_CSTUNITID, scmMeasureUnit);
				}
			}
		}
	}
	
	@Override
	public int updateByPriceUpdSet(String orgUnitNo, Param param) throws AppException {
		if(StringUtils.isNoneBlank(orgUnitNo)){
			ScmItemCostPriceLog scmItemCostPriceLog = scmItemCostPriceLogBiz.addUpdLog(orgUnitNo, param);
			//批量更新价格
			int rows = this.batchAddItemPrice(scmItemCostPriceLog, param);
			//批次更新历史价格表
			this.updateItemPriceHistory(scmItemCostPriceLog, param);
			return rows;
		}
		return 0;
	}

	@Override
	public int batchAddItemPrice(ScmItemCostPriceLog scmItemCostPriceLog, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("logId",scmItemCostPriceLog.getId());
		return ((ScmItemCostPriceDAO)dao).batchAddItemPrice(map);
	}
	
	@Override
	public void updateItemPriceHistory(ScmItemCostPriceLog scmItemCostPriceLog,Param param) throws AppException {
		scmItemCostPriceHistoryBiz.updateItemPriceHistory(scmItemCostPriceLog, param);
	}

	@Override
	public List<ScmItemCostPrice2> selectItemCost(String resOrgUnitNos, String fmtDate, Param param)
			throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("resOrgUnitNos",resOrgUnitNos);
		map.put("date", fmtDate);
		return ((ScmItemCostPriceDAO)dao).selectItemCost(map);
	}

}
