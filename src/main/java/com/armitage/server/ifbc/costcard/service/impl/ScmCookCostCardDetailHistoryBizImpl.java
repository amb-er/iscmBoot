package com.armitage.server.ifbc.costcard.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.ifbc.costcard.dao.ScmCookCostCardDetailHistoryDAO;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCardDetailHistory;
import com.armitage.server.ifbc.costcard.service.ScmCookCostCardDetailHistoryBiz;
import org.springframework.stereotype.Service;

@Service("scmCookCostCardDetailHistoryBiz")
public class ScmCookCostCardDetailHistoryBizImpl extends BaseBizImpl<ScmCookCostCardDetailHistory> implements ScmCookCostCardDetailHistoryBiz {

	@Override
	public void updateByAuditCostCard(long cardId,Date effectDate, Param param) throws AppException {
		List<ScmCookCostCardDetailHistory> scmCookCostCardDetailHistoryList = this.selectByEffectDay(cardId,effectDate, param);
		if(scmCookCostCardDetailHistoryList!=null && !scmCookCostCardDetailHistoryList.isEmpty()) {
			this.deleteByEffectDay(cardId,effectDate, param);	//删除当天开始的数据
		}
		this.updateLastByCostCard(cardId,param);	//更新最后一次的历史数据的日期
		this.addByCostCard(cardId,param);		//插入新的记录
	}

	@Override
	public List<ScmCookCostCardDetailHistory> selectByEffectDay(long cardId,Date effectDate, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cardId", cardId);
		map.put("beginDate", FormatUtils.fmtDate(effectDate));
		return ((ScmCookCostCardDetailHistoryDAO)dao).selectByEffectDay(map);
	}

	@Override
	public void deleteByEffectDay(long cardId,Date effectDate, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cardId", cardId);
		map.put("beginDate", FormatUtils.fmtDate(effectDate));
		((ScmCookCostCardDetailHistoryDAO)dao).deleteByEffectDay(map);
	}

	@Override
	public void updateLastByCostCard(long cardId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cardId", cardId);
		((ScmCookCostCardDetailHistoryDAO)dao).updateLastByCostCard(map);
	}

	@Override
	public void addByCostCard(long cardId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cardId", cardId);
		((ScmCookCostCardDetailHistoryDAO)dao).addByCostCard(map);
	}

	@Override
	public void updateLastByCostCard2(long id, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cardId", id);
		((ScmCookCostCardDetailHistoryDAO)dao).updateLastByCostCard2(map);
	}

}
