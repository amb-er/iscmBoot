
package com.armitage.server.ifbc.costcard.service;

import java.util.Date;
import java.util.List;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetail2;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetailHistory;

public interface ScmProductCostCardDetailHistoryBiz extends BaseBiz<ScmProductCostCardDetailHistory> {
	public List<ScmProductCostCardDetailHistory> selectByProductIdAndDate(String orgUnitNo,long productId,Date bizDate,Param param) throws AppException;

	public void updateByAuditCostCard(ScmProductCostCard2 scmProductCostCard, Date effectiveDate, Param param) throws AppException;
	
	public List<ScmProductCostCardDetailHistory> selectByEffectDay(long cardId,Date effectDate, Param param) throws AppException;
	
	public void deleteByEffectDay(long id, Date effectiveDate, Param param)throws AppException;

	public List<ScmProductCostCardDetailHistory> selectByEffectDayAndDetailItemId(long cardId, long itemId,Date effectiveDate, Param param) throws AppException;

	public void deleteByEffectDayAndDetailItemId(long id, Date effectiveDate, long itemId, Param param) throws AppException;

	public void updateByAuditCostCardAndDetailItemId(long id, Date date, long itemId, Param param) throws AppException;


}
