package com.armitage.server.ifbc.costcard.service;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.costcard.model.ScmCostCardDetailHistory;

public interface ScmCostCardDetailHistoryBiz extends BaseBiz<ScmCostCardDetailHistory> {

	public void addByCostCard(long cardId, Param param) throws AppException ;

	public void updateLastByCostCard(long cardId, Param param) throws AppException ;
	public List<ScmCostCardDetailHistory> selectByEffectDay(long cardId,Date effectDate, Param param) throws AppException ;

	public void deleteByEffectDay(long cardId, Date effectDate,Param param) throws AppException ;
	public void updateByAuditCostCard(long cardId,Date effectDate, Param param) throws AppException ;

	public void updateLastByCostCard2(long id, Param param) throws AppException;
}
