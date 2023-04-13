package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service;

import java.util.List;

import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqAddParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqDeptParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqEditParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqInvOrgUnitParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqPersonParams;
import com.armitage.server.api.business.invmaterialreq.params.InvReqMaterialListParams;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialDrillResult;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBillAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.user.model.Usr2;

public interface ScmInvMaterialRequestBillBiz extends BaseBiz<ScmInvMaterialRequestBill2> {
	public List<ScmInvMaterialRequestBill2> queryInvMaterialReqList(ScmInvMaterialRequestBillAdvQuery scmInvMaterialRequestBillAdvQuery,
			int pageNum, Param param) throws AppException;

	public ScmInvMaterialRequestBill2 queryInvMaterialReq(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill,Param param) throws AppException;

	public void doDelInvMaterialReq(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill,Param param) throws AppException;

	public ScmInvMaterialRequestBill2 doSubmit(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill,Param param) throws AppException;
	public ScmInvMaterialRequestBill2 doUnSubmit(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill,Param param) throws AppException;

	public List<OrgAdmin2> queryInvMaterialReqDept(InvMaterialReqDeptParams invMaterialReqDeptParams, Param param) throws AppException;

	public List<OrgStorage2> queryPurReqPurOrgUnit(InvMaterialReqInvOrgUnitParams invMaterialReqInvOrgUnitParams,Param param) throws AppException;

	public List<Usr2> queryInvMaterialReqPerson(InvMaterialReqPersonParams invMaterialReqPersonParams,int pageNum, Param param) throws AppException;

	public List<ScmMaterial2> queryInvReqMaterialList(InvReqMaterialListParams invReqMaterialListParams, int pageNum,Param param) throws AppException;

	public ScmInvMaterialRequestBill2 release(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill, Param param) throws AppException;

	public ScmInvMaterialRequestBill2 undoRelease(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill,Param param) throws AppException;

	public ScmInvMaterialRequestBill2 finish(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill, Param param) throws AppException;

	public ScmInvMaterialRequestBill2 undoFinish(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill,Param param) throws AppException;

	public ScmInvMaterialRequestBill2 doAddMaterialReq(InvMaterialReqAddParams invMaterialReqAddParams, Param param) throws AppException;

	public void doEditMaterialReq(InvMaterialReqEditParams invMaterialReqEditParams, Param param) throws AppException;
	
	public void generateMaterialReqBill(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill, Param param) throws AppException;
	
	/**
	 * 审批领料申请单
	 * @param commonAuditParams
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmInvMaterialRequestBill2 doAuditInvMaterialReq(CommonAuditParams commonAuditParams,Param param) throws AppException;
	
	/**
	 * 取消审批领料申请单
	 * @param scmInvMaterialRequestBill
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmInvMaterialRequestBill2 doUnAuditInvMaterialReq(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill,Param param) throws AppException;
	
	public ScmInvMaterialRequestBill2 updatePrtCount(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill,Param param) throws AppException;
	
	public List<ScmInvMaterialDrillResult> selectDrillBills(ScmInvMaterialRequestBill2 scmInvMaterialRequestBille, Param param) throws AppException;
}
