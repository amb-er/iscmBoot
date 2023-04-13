package com.armitage.server.iscm.purchasemanage.purchasebusiness.service;

import java.util.List;

import com.armitage.server.api.business.purreceive.params.PurRecPurOrgUnitParams;
import com.armitage.server.api.business.purreceive.params.PurReceiveAddParams;
import com.armitage.server.api.business.purreceive.params.PurReceiveEditParams;
import com.armitage.server.api.business.purreceive.params.PurReceiverListParams;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurBillDrillResult;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveAdvQuery;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.user.model.Usr2;

public interface ScmPurReceiveBiz extends BaseBiz<ScmPurReceive2> {
	/*
	 * 删除收货单
	 */
	public void doDelPurReceive(ScmPurReceive2 scmPurReceive, Param param) throws AppException;
	
	/*
	 * 提交收货单
	 */
	public ScmPurReceive2 doSubmitPurReceive(ScmPurReceive2 scmPurReceive, Param param) throws AppException;
	
	/*
	 * 取消提交收货单
	 */
	public ScmPurReceive2 doUnSubmitPurReceive(ScmPurReceive2 scmPurReceive, Param param) throws AppException;

	/**
	 * 查询收货单列表接口
	 * @param scmPurReceiveAdvQuery
	 * @param pageNum
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurReceive2> queryPurReceiveList(ScmPurReceiveAdvQuery scmPurReceiveAdvQuery, int pageNum, Param param) throws AppException;

	/**
	 * 查询收货单详情接口
	 * @param scmPurReceive
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurReceive2 queryPurReceive(ScmPurReceive2 scmPurReceive, Param param) throws AppException;
	
	/**
	 * 新增收货单接口
	 * @param purReceiveAddParams
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurReceive2 doAddPurReceive(PurReceiveAddParams purReceiveAddParams, Param param) throws AppException;
	/**
	 * 修改收货单接口
	 * @param purReceiveEditParams
	 * @param param
	 * @throws AppException
	 */
	public void doEditPurReceive(PurReceiveEditParams purReceiveEditParams, Param param) throws AppException;

	/**
	 * 下达
	 * @param scmPurReceive
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurReceive2 doRelease(ScmPurReceive2 scmPurReceive, Param param) throws AppException;

	/**
	 * 取消下达
	 * @param scmPurReceive
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurReceive2 doUndoRelease(ScmPurReceive2 scmPurReceive, Param param) throws AppException;
	
	/**
	 * 根据销售出库生成收货单（内部供应商订货时）
	 * @param scmInvSaleIssueBill
	 * @param param
	 * @throws AppException
	 */
	public void generateFromSaleIssue(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param) throws AppException;

	public List<OrgPurchase2> queryPurRecPurOrgUnit(PurRecPurOrgUnitParams purRecPurOrgUnitParams, Param param) throws AppException;

	public List<Usr2> queryPurReceiverList(PurReceiverListParams purReceiverListParams, int pageNum,Param param) throws AppException;

	/**
	 * 采购订单生成收货单
	 * @param scmPurOrder
	 * @param scmPurOrderEntryList
	 * @param param
	 * @throws AppException
	 */
	public void generateFromPurOrder(ScmPurOrder2 scmPurOrder, List<ScmPurOrderEntry2> scmPurOrderEntryList, Param param) throws AppException;

	/**
	 * 查询采购订单所涉及的收货单
	 * @param pruOrder
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurReceive2> selectByPurOrder(ScmPurOrder2 pruOrder, Param param) throws AppException;

	/**
	 * 开始收货
	 * @param scmPurReceive
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurReceive2 startReceive(ScmPurReceive2 scmPurReceive, Param param) throws AppException;

	public void delByPurOrder(ScmPurOrder2 scmPurOrder, Param param) throws AppException;

	public List<ScmInvPurInWarehsBill2>  generateWrReceipts(List<ScmPurReceive2> scmPurReceiveList, Param param) throws AppException;
	
	public ScmPurReceive2 doUndoFinish(ScmPurReceive2 scmPurReceive, Param param) throws AppException;
	public ScmPurReceive2 selectByCkId(long ckId,Param param) throws AppException;

	public void deleteFromSaleIssue(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param) throws AppException;
	public List<ScmPurReceive2> selectBySaleIssueBill(long otId, Param param) throws AppException;

	public void generateFromOtherIssue(ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param) throws AppException;

	public void deleteFromOtherIssue(ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param) throws AppException;
	public List<ScmPurReceive2> selectByOtherIssueBill(long otId, Param param) throws AppException;
	public List<OrgAdmin2> queryPurReceiveDept(String invOrgUnitNo,int pageNum, Param param) throws AppException;
	public List<ScmInvWareHouse> queryPurReceiveWareHouses(String invOrgUnitNo,int pageNum, Param param) throws AppException;
	
	/**
	 * 审批收货单
	 * @param commonAuditParams
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurReceive2 doAuditPurReceive(CommonAuditParams commonAuditParams, Param param) throws AppException;
	
	/**
	 * 取消审批收货单
	 * @param scmPurReceive
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurReceive2 doUnAuditPurReceive(ScmPurReceive2 scmPurReceive, Param param) throws AppException;
	
	public List<ScmPurBillDrillResult> selectDrillBills(ScmPurReceive2 scmPurReceive, Param param) throws AppException;

	/**
	 * 移动端生成入库单
	 * @param pvNo
	 * @param createParam
	 * @throws AppException
	 */
	public void mobileGenerateWrReceipts(String pvNo, Param createParam) throws AppException;

	public void setConvertMap(ScmPurReceive2 scmPurReceive,Param param) throws AppException;
	public void doAdvQuery(Page page, Param param)throws AppException;
	public ScmPurReceive2 selectByBillCode(String pvNo, Param param) throws AppException;

	public ScmPurReceive2 updatePrtCount(ScmPurReceive2 scmPurReceive, Param param) throws AppException;
}

