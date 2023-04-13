package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.api.business.datasync.params.InvOtherIssueBillListParams;
import com.armitage.server.api.business.datasync.result.DataSyncResult;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;

public interface ScmInvOtherIssueBillBiz extends BaseBiz<ScmInvOtherIssueBill2> {
	public ScmInvOtherIssueBill2 submit(ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param) throws AppException;
	public ScmInvOtherIssueBill2 undoSubmit(ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param) throws AppException;
	public ScmInvOtherIssueBill2 postBill(ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param) throws AppException;
    public List<String> postBillCheck(ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param) throws AppException;
    public ScmInvOtherIssueBill2 cancelPostBill(ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param) throws AppException;
    public String getBillNo(Date date, Param param) throws AppException;
	/**
	 * 根据财务组织检查是否存在未过账单据
	 * @param finOrgUnitNo
	 * @param periodId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvOtherIssueBill2> checkUnPostBill(String finOrgUnitNo,long periodId, Param param) throws AppException;
	/**
	 * 根据财务组织检查是否存在已过账单据
	 * @param finOrgUnitNo
	 * @param periodId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvOtherIssueBill2> checkPostedBill(String finOrgUnitNo,long periodId, Param param) throws AppException;
	public void generateDepositIssue(String orgUnitNo,ScmPurOrder2 scmPurOrder, List<ScmPurOrderEntry2> scmPurOrderEntryList,Param param) throws AppException;
	public void delByPurOrder(ScmPurOrder2 scmPurOrder, Param param) throws AppException;
	public List<ScmInvOtherIssueBill2> selectByPoId(long poId, Param param) throws AppException;
	
	/**
	 * 获取其他出库详情
	 * @param scmInvOtherIssue
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmInvOtherIssueBill2 queryInvOtherIssue(ScmInvOtherIssueBill2 scmInvOtherIssue, Param param) throws AppException;
	
	/**
	 * 审批其他出库单
	 * @param commonAuditParams
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmInvOtherIssueBill2 doAuditInvOtherIssue(CommonAuditParams commonAuditParams, Param param) throws AppException;
	
	/**
	 * 取消审批其他出库单
	 * @param scmInvOtherIssueBill
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmInvOtherIssueBill2 doUnAuditInvOtherIssue(ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param) throws AppException;

	/**
	 * 盘点时统计其它出库的未过账单据
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> countUnPostBill(HashMap<String, Object> map);
	public ScmInvOtherIssueBill2 updatePrtCount(ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param) throws AppException;
	public DataSyncResult dataSync(InvOtherIssueBillListParams invOtherIssueBillListParam,
			List<ScmInvOtherIssueBill2> scmInvOtherInWarehsBill2s, Param param) throws AppException;
}