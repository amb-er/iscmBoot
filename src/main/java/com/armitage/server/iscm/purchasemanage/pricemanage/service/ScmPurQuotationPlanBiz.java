package com.armitage.server.iscm.purchasemanage.pricemanage.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationPlan;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationPlanAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurInquiryGroup;


public interface ScmPurQuotationPlanBiz extends BaseBiz<ScmPurQuotationPlan> {

	/**
	 * 提交
	 * @param scmPurQuotationPlan
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurQuotationPlan submit(ScmPurQuotationPlan scmPurQuotationPlan, Param param) throws AppException;
	
	/**
	 * 取消提交
	 * @param scmPurQuotationPlan
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurQuotationPlan undoSubmit(ScmPurQuotationPlan scmPurQuotationPlan, Param param) throws AppException;

	/**
	 * 下达
	 * @param scmPurQuotationPlan
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurQuotationPlan release(ScmPurQuotationPlan scmPurQuotationPlan, Param param) throws AppException;

	/**
	 * 取消下达
	 * @param scmPurQuotationPlan
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurQuotationPlan undoRelease(ScmPurQuotationPlan scmPurQuotationPlan, Param param) throws AppException;

	/**
	 * 分配市调任务
	 * @param scmPurQuotationPlanList
	 * @param scmPurInquiryGroupList
	 * @param param
	 */
	public void distribute(List<ScmPurQuotationPlan> scmPurQuotationPlanList,List<ScmPurInquiryGroup> scmPurInquiryGroupList, Param param);
	
	public CommonBean getDataForLeadInto(ScmPurQuotationPlanAdvQuery scmPurQuotationPlanAdvQuery, Param param);

	public ScmPurQuotationPlan updatePrtCount(ScmPurQuotationPlan scmPurQuotationPlan, Param param) throws AppException;

}
