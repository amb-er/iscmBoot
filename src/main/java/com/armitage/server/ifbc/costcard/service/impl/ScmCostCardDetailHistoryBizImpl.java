package com.armitage.server.ifbc.costcard.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.ifbc.costcard.dao.ScmCostCardDetailHistoryDAO;
import com.armitage.server.ifbc.costcard.model.ScmCostCardDetailHistory;
import com.armitage.server.ifbc.costcard.service.ScmCostCardDetailHistoryBiz;
import org.springframework.stereotype.Service;

@Service("scmCostCardDetailHistoryBiz")
public class ScmCostCardDetailHistoryBizImpl extends BaseBizImpl<ScmCostCardDetailHistory> implements ScmCostCardDetailHistoryBiz {

	@Override
	public void addByCostCard(long cardId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cardId", cardId);
		((ScmCostCardDetailHistoryDAO)dao).addByCostCard(map);
	}

	@Override
	public void updateLastByCostCard(long cardId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cardId", cardId);
		((ScmCostCardDetailHistoryDAO)dao).updateLastByCostCard(map);
	}

	@Override
	public List<ScmCostCardDetailHistory> selectByEffectDay(long cardId,Date effectDate, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cardId", cardId);
		map.put("effectDate", FormatUtils.fmtDate(effectDate));
		return ((ScmCostCardDetailHistoryDAO)dao).selectByEffectDay(map, param);
	}

	@Override
	public void deleteByEffectDay(long cardId, Date effectDate,Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cardId", cardId);
		map.put("effectDate", FormatUtils.fmtDate(effectDate));
		((ScmCostCardDetailHistoryDAO)dao).deleteByEffectDay(map);
	}

	@Override
	public void updateByAuditCostCard(long cardId,Date effectDate, Param param) throws AppException {
		List<ScmCostCardDetailHistory> scmCostCardDetailHistoryList = this.selectByEffectDay(cardId,effectDate, param);
		if(scmCostCardDetailHistoryList!=null && !scmCostCardDetailHistoryList.isEmpty()) {
			this.deleteByEffectDay(cardId,effectDate, param);	//删除影响日期的数据
		}
		this.updateLastByCostCard(cardId,param);	//更新最后一次的历史数据的日期
		this.addByCostCard(cardId,param);		//插入新的记录
	}

	@Override
	public void updateLastByCostCard2(long id, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cardId", id);
		((ScmCostCardDetailHistoryDAO)dao).updateLastByCostCard2(map);
	}

}
