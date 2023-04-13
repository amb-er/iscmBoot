package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.api.business.datasync.params.InvMaterialReqListSParams;
import com.armitage.server.api.business.datasync.result.DataSyncResult;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillAddParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillDeptParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillEditParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillPersonParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillWareHouseParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvReqBillLotListParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvReqBillMaterialListParams;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialDrillResult;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.user.model.Usr;

public interface ScmInvMaterialReqBillBiz extends BaseBiz<ScmInvMaterialReqBill2> {
	public List<String> postBillCheck(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param) throws AppException;
	public List<String> cancelPostBillCheck(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param) throws AppException;
	public ScmInvMaterialReqBill2 postBill(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param) throws AppException;
	public ScmInvMaterialReqBill2 cancelPostBill(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param) throws AppException;
	
	/*
	 * 删除领料单
	 */
	public void doDelInvMaterialReq(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param) throws AppException;
	/*
	 * 提交领料单
	 */
	public ScmInvMaterialReqBill2 doSubmitInvMaterialReq(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param) throws AppException;
	
	/*
	 *取消提交领料单 
	 */
	public ScmInvMaterialReqBill2 doUnSubmitInvMaterialReq(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param) throws AppException;
	/**
	 * 根据财务组织检查是否存在未过账单据
	 * @param finOrgUnitNo
	 * @param periodId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvMaterialReqBill2> checkUnPostBill(String finOrgUnitNo,long periodId, Param param) throws AppException;
	/**
	 * 根据财务组织检查是否存在已过账单据
	 * @param finOrgUnitNo
	 * @param periodId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvMaterialReqBill2> checkPostedBill(String finOrgUnitNo,long periodId, Param param) throws AppException;
	
	public ScmInvMaterialReqBill2 doAddMaterialReqBill(InvMaterialReqBillAddParams invMaterialReqBillAddParams, Param param) throws AppException;
	
	public ScmInvMaterialReqBill2 doEditMaterialReqBill(InvMaterialReqBillEditParams invMaterialReqBillEditParams, Param param) throws AppException;
	
	/**
	 * 获取领料单详情
	 * @param scmInvMaterialReqBill
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmInvMaterialReqBill2 queryInvMaterialReqBill(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param) throws AppException;
	
	/**
	 * 审批领料单
	 * @param commonAuditParams
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmInvMaterialReqBill2 doAuditInvMaterialReqBill(CommonAuditParams commonAuditParams, Param param) throws AppException;
	
	/**
	 * 取消审批领料单
	 * @param scmInvMaterialReqBill
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmInvMaterialReqBill2 doUnAuditInvMaterialReqBill(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param) throws AppException;

	/**
	 * 盘点时统计领料出库未过账数据
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> countUnPostBill(HashMap<String, Object> map);
	
	/**
	 * 盘存时统计领料出库未过账单据
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> countCostUnPostBill(HashMap<String, Object> map);
	
	/**
	 * 查询领料出库单列表
	 * @param scmInvMaterialReqBillAdvQuery
	 * @param pageIndex
	 * @param createParam
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvMaterialReqBill2> queryInvMaterialReqBillList(ScmInvMaterialReqBillAdvQuery scmInvMaterialReqBillAdvQuery, int pageIndex, Param createParam) throws AppException;
	/**
	 * 查询领料部门
	 * @param invMaterialReqBillDeptParams
	 * @param createParam
	 * @return
	 * @throws AppException
	 */
	public List<OrgAdmin2> queryInvMaterialReqBillDept(InvMaterialReqBillDeptParams invMaterialReqBillDeptParams,Param createParam) throws AppException;
	/**
	 * 查询领料仓库
	 * @param invMaterialReqBillWareHouseParams
	 * @param createParam
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvWareHouse> queryInvMaterialReqBillWareHouse(InvMaterialReqBillWareHouseParams invMaterialReqBillWareHouseParams, Param createParam) throws AppException;
	/**
	 * 查询可以领料物资
	 * @param invReqBillMaterialListParams
	 * @param pageIndex
	 * @param createParam
	 * @return
	 * @throws AppException
	 */
	public List<ScmMaterial2> queryInvReqBillMaterialList(InvReqBillMaterialListParams invReqBillMaterialListParams, int pageIndex, Param createParam) throws AppException;
	/**
	 * 查询可用物资批次
	 * @param invReqBillLotListParams
	 * @param pageIndex
	 * @param createParam
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvStock2> queryInvReqBillLotList(InvReqBillLotListParams invReqBillLotListParams, int pageIndex,Param createParam) throws AppException;
	/**
	 * 查询领料人
	 * @param invMaterialReqBillPersonParams
	 * @param createParam
	 * @return
	 * @throws AppException
	 */
	public List<Usr> queryInvMaterialReqBillPerson(InvMaterialReqBillPersonParams invMaterialReqBillPersonParams,int pageIndex,Param createParam) throws AppException;
	
	public List<ScmInvMaterialDrillResult> selectDrillBills(ScmInvMaterialReqBill2 scmInvMaterialReqBill,Param param) throws AppException;
	public ScmInvMaterialReqBill2 updatePrtCount(ScmInvMaterialReqBill2 scmInvMaterialReqBill,Param param) throws AppException;
	public DataSyncResult dataSync(InvMaterialReqListSParams invMaterialReqListSParam,
			List<ScmInvMaterialReqBill2> scmInvMaterialReqBills, Param param);
}

