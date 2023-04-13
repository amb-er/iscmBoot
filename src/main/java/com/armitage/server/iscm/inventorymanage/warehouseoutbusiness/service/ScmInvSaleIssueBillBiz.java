package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.api.business.invSaleIssueBill.params.InvSaleIssueBillAddParams;
import com.armitage.server.api.business.invSaleIssueBill.params.InvSaleIssueReturnBillAddParams;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;

public interface ScmInvSaleIssueBillBiz extends BaseBiz<ScmInvSaleIssueBill2> {
    public List<String> postBillCheck(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param) throws AppException;
    public ScmInvSaleIssueBill2 postBill(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param) throws AppException;
    public ScmInvSaleIssueBill2 cancelPostBill(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param) throws AppException;
	public ScmInvSaleIssueBill2 doSubmit(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param) throws AppException;
	public ScmInvSaleIssueBill2 doUnSubmit(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param) throws AppException;
	public List<ScmInvSaleIssueBill2> SelectBySaleOrder(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException;
	/**
	 * 根据财务组织检查是否存在未过账单据
	 * @param finOrgUnitNo
	 * @param periodId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvSaleIssueBill2> checkUnPostBill(String finOrgUnitNo,long periodId, Param param) throws AppException;
	/**
	 * 根据财务组织检查是否存在已过账单据
	 * @param finOrgUnitNo
	 * @param periodId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvSaleIssueBill2> checkPostedBill(String finOrgUnitNo,long periodId, Param param) throws AppException;
	/**
	 * 查询退货单有关的内部供应商销售退货单
	 * @param wrId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvSaleIssueBill2> selectByPurInwareHouse(long wrId, Param param) throws AppException;

	/**
	 * 盘点时统计销售出库未过账单据
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> countUnPostBill(HashMap<String, Object> map);
	
	public ScmInvSaleIssueBill2 doAddSaleIssueBill(InvSaleIssueBillAddParams invSaleIssueBillAddParams, Param param) throws AppException;
	public ScmInvSaleIssueBill2 doAddSaleIssueReturnBill(InvSaleIssueReturnBillAddParams invSaleIssueReturnBillAddParams, Param param) throws AppException;
	public ScmInvSaleIssueBill2 queryInvSaleIssueBill(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param) throws AppException;
    /**
     * 生成应收单
     * @param scmInvSaleIssueBillList
     * @param param
     * @return
     * @throws AppException
     */
    public CommonBean generateArInvoice(ScmDataCollectionStepState2 stepState,final List<ScmInvSaleIssueBill2> scmInvSaleIssueBillList, final Param param) throws AppException;
	public ScmInvSaleIssueBill2 updatePrtCount(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param) throws AppException;
	/**
	 * 审批
	 * @param commonAuditParams
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmInvSaleIssueBill2 doAudit(CommonAuditParams commonAuditParams, Param param) throws AppException;
	/**
	 * 反审
	 * @param scmInvSaleIssueBill
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmInvSaleIssueBill2 doUnAudit(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param) throws AppException;
}
