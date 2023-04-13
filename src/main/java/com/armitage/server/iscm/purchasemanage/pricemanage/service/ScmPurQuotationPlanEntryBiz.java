package com.armitage.server.iscm.purchasemanage.pricemanage.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationPlanAdvQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationPlanEntry2;

public interface ScmPurQuotationPlanEntryBiz extends BaseBiz<ScmPurQuotationPlanEntry2> {

	/**
	 * 根据planId查询
	 * @param planId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurQuotationPlanEntry2> selectByPlanId(long planId, Param param) throws AppException;
	
	
	/**
	 * 根据查询物资类别
	 * @param poId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurQuotationPlanEntry2> selectQurChasIngQuery(ScmPurQuotationPlanAdvQuery scmPurQuotationPlanAdvQuery, Param param) throws AppException;
	
}
