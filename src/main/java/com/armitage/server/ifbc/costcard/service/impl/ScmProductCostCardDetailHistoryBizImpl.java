
package com.armitage.server.ifbc.costcard.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.ifbc.costcard.dao.ScmCostCardDetailHistoryDAO;
import com.armitage.server.ifbc.costcard.dao.ScmProductCostCardDetailHistoryDAO;
import com.armitage.server.ifbc.costcard.model.ScmCostCardDetailHistory;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetail2;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetailHistory;
import com.armitage.server.ifbc.costcard.service.ScmProductCostCardDetailHistoryBiz;
import org.springframework.stereotype.Service;

@Service("scmProductCostCardDetailHistoryBiz")
public class ScmProductCostCardDetailHistoryBizImpl extends BaseBizImpl<ScmProductCostCardDetailHistory> implements ScmProductCostCardDetailHistoryBiz {

	@Override
	public List<ScmProductCostCardDetailHistory> selectByProductIdAndDate(String orgUnitNo,long productId, Date bizDate, Param param)
			throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("productId", productId);
		map.put("bizDate", FormatUtils.fmtDate(bizDate));
		return ((ScmProductCostCardDetailHistoryDAO)dao).selectByProductIdAndDate(map);
	}

	@Override
	public void updateByAuditCostCard(ScmProductCostCard2 scmProductCostCard, Date effectiveDate, Param param) throws AppException {
		Date delDate = effectiveDate;
		if (delDate.compareTo(CalendarUtil.getDate(param)) > 0) {
			delDate = CalendarUtil.getDate(param);
		}
		List<ScmProductCostCardDetailHistory> scmProductCostCardDetailHistories = this.selectByEffectDay(scmProductCostCard.getId(), delDate,param);
		if (scmProductCostCardDetailHistories != null && !scmProductCostCardDetailHistories.isEmpty()) {
			this.deleteByEffectDay(scmProductCostCard.getId(), delDate, param); // 删除影响日期的数据
		}
		this.updateLastByCostCard(scmProductCostCard.getId(), effectiveDate, param); // 更新最后一次的历史数据的日期
		if(scmProductCostCard.getFlag()) {
			this.addByCostCard(scmProductCostCard.getId(), param); // 插入新的记录	
		}
	}
	
	public void updateLastByCostCard(long cardId,Date effectiveDate, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cardId", cardId);
		map.put("effectiveDate", effectiveDate);
		((ScmProductCostCardDetailHistoryDAO)dao).updateLastByCostCard(map);
	}
	
	public void addByCostCard(long cardId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cardId", cardId);
		((ScmProductCostCardDetailHistoryDAO)dao).addByCostCard(map);
	}
	
	public void deleteByEffectDay(long id, Date effectiveDate, Param param)throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cardId", id);
		map.put("effectDate", FormatUtils.fmtDate(effectiveDate));
		((ScmProductCostCardDetailHistoryDAO)dao).deleteByEffectDay(map);
	}

	public List<ScmProductCostCardDetailHistory> selectByEffectDay(long cardId,Date effectDate, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cardId", cardId);
		map.put("effectDate", FormatUtils.fmtDate(effectDate));
		return ((ScmProductCostCardDetailHistoryDAO)dao).selectByEffectDay(map, param);
	}

	@Override
	public List<ScmProductCostCardDetailHistory> selectByEffectDayAndDetailItemId(long cardId, long itemId,
			Date effectiveDate, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cardId", cardId);
		map.put("effectDate", FormatUtils.fmtDate(effectiveDate));
		map.put("itemId", itemId);
		return ((ScmProductCostCardDetailHistoryDAO)dao).selectByEffectDayAndDetailItemId(map, param);
	}

	@Override
	public void deleteByEffectDayAndDetailItemId(long id, Date effectiveDate, long itemId, Param param)
			throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cardId", id);
		map.put("effectDate", FormatUtils.fmtDate(effectiveDate));
		map.put("itemId", itemId);
		((ScmProductCostCardDetailHistoryDAO)dao).deleteByEffectDayAndDetailItemId(map);
	}

	@Override
	public void updateByAuditCostCardAndDetailItemId(long id, Date date, long itemId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cardId", id);
		map.put("effectDate", FormatUtils.fmtDate(date));
		map.put("itemId", itemId);
		((ScmProductCostCardDetailHistoryDAO)dao).updateByAuditCostCardAndDetailItemId(map);
	}

}
