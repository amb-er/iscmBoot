package com.armitage.server.iscm.purchasemanage.purchasebusiness.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntryAdvQuery;

public interface ScmPurRequireEntryBiz extends BaseBiz<ScmPurRequireEntry2> {
	
	/**
	 * 根据prId查询
	 * @param prId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurRequireEntry2> selectByPrId(long prId, Param param) throws AppException;
	
	/**
	 * 根据prId删除
	 * @param prId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByPrId(long prId, Param param) throws AppException;
	
	/**
	 * 更新状态
	 * @param prId
	 * @param status
	 * @param checker
	 * @param checkDate
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void updateRowStatusByPrId(long prId, String status, String checker, Date checkDate, Param param) throws AppException;
	
	/**
	 * 直接更新状态
	 * @param scmPurRequireEntryList
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurRequireEntry2> updateStatus(List<ScmPurRequireEntry2> scmPurRequireEntryList, Param param) throws AppException;
	
	/**
	 * 根据行状态更新单状态
	 * @param scmPurRequireEntry
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void updateBillStatusByEntry(ScmPurRequireEntry2 scmPurRequireEntry, Param param) throws AppException;
	
	/**
	 * 生成订货单
	 * @param scmPurRequireEntryList
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public CommonBean generateOrder(ScmDataCollectionStepState2 stepState,final List<ScmPurRequireEntry2> scmPurRequireEntryList,final Param param) throws AppException;
	
	/**
	 * 获取引入数据
	 * @param scmPurRequireEntryAdvQuery
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public CommonBean getDataForLeadInto(ScmPurRequireEntryAdvQuery scmPurRequireEntryAdvQuery, Param param) throws AppException;

	public void writeBackByPurOrder(ScmPurOrderEntry2 oldEntity,ScmPurOrderEntry2 newEntity, Param param) throws AppException;
	
	/**
	 * 根据buyerId查询
	 * @param map
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurRequireEntry2> selectByBuyerId(HashMap<String, Object> map) throws AppException;
	
	/**
	 * 自动分配
	 * @param scmPurRequireEntryList
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurRequireEntry2> autoAssign(List<ScmPurRequireEntry2> scmPurRequireEntryList, Param param) throws AppException;

	/**
	 * 取消拒绝
	 * @param scmPurRequireEntryList
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<String> cancelRefuse(List<ScmPurRequireEntry2> scmPurRequireEntryList, Param param) throws AppException;
	
	/**
	 * 根据请购Id，行号更新状态
	 * @param prId
	 * @param status
	 * @param checker
	 * @param checkDate
	 * @param param
	 * @throws AppException
	 */
	public void updateRowStatusByLineId(long prId, String status, String checker, Date checkDate, int lineId, Param param) throws AppException;
	
	/**
	 * 下达更新行状态
	 * @param scmPurRequire
	 * @param param
	 * @throws AppException
	 */
	public void doRelease(ScmPurRequire2 scmPurRequire, Param param) throws AppException;
	/**
	 * 取消下达更新行状态
	 * @param scmPurRequire
	 * @param param
	 * @throws AppException
	 */
	public void doUndoRelease(ScmPurRequire2 scmPurRequire, Param param) throws AppException;
	
	/**
	 * 查看请购单后续单据状态
	 * @param scmPurRequireEntryList
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurRequireEntry2> viewPurRequestStatus(ScmPurRequire2 scmPurRequire, Param param) throws AppException;
	
	
	/**
	 * 生成临时定价单
	 * @param scmPurRequireEntryList
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public CommonBean generateTempPrice(List<ScmPurRequireEntry2> scmPurRequireEntryList, Param param) throws AppException;
	
	/**
	 * 更新临时定价的价格
	 * @param scmPurPrice
	 * @param param
	 * @throws AppException
	 */
	public void updatePurRequestByPmId(ScmPurPriceEntry2 scmPurPriceEntry, Param param) throws AppException;
	
	/**
	 * 更新临时定价的价格来源Id
	 * @param scmPurPriceEntry
	 * @param param
	 * @throws AppException
	 */
	public void updatePurRequestPriceBillIdByPmId(ScmPurPriceEntry2 scmPurPriceEntry, Param param) throws AppException;
	
	public List<ScmPurRequireEntry2> selectByPrId2(long prId, Param param) throws AppException;

	public List<ScmPurRequireEntry2> undoReleaseCheck(HashMap<Object, Object> map, Param param) throws AppException;

	public List<ScmPurRequireEntry2> clearPurRequirePrice(HashMap<Object, Object> map, Param param) throws AppException;
	
	public List selectByPurOrgUnitNo(String object, Param createParam) throws AppException;

	public List<ScmPurRequireEntry2> selectByPrIds(String prids, Param createParam) throws AppException;

	/**
	 * 拒绝
	 * @param scmPurRequireEntryList
	 * @param createParam
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurRequireEntry2> refuse(List<ScmPurRequireEntry2> scmPurRequireEntryList, Param createParam) throws AppException;
	public void setConvertMap(ScmPurRequireEntry2 scmPurRequireEntry,Param param);
	public List<ScmPurRequireEntry2> selectAutoOrderListByPrId(long prId, Param param) throws AppException;
	public List<ScmPurOrder2> asynGenerateOrder(List<ScmPurRequireEntry2> scmPurRequireEntryList,boolean directPurchase,Param param) throws AppException;

}
