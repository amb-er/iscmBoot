package com.armitage.server.iscm.inventorymanage.inventorydata.service.impl;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Service;

import com.armitage.server.api.business.datasync.params.InvStockListParams;
import com.armitage.server.api.business.datasync.result.DataSyncResult;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialInventory2;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialInventoryBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStockNo;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockNoBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.util.OrgUnitRelationType;

@Service("scmInvStockNoBiz")
public class ScmInvStockNoBizImpl extends BaseBizImpl<ScmInvStockNo> implements ScmInvStockNoBiz {
	
	public ScmMaterialBiz scmMaterialBiz;
	public ScmMaterialInventoryBiz scmMaterialInventoryBiz;
	public OrgUnitRelationBiz orgUnitRelationBiz;
	public OrgAdminBiz orgAdminBiz;
	public ScmInvWareHouseBiz scmInvWareHouseBiz;
	public ScmInvStockBiz scmInvStockBiz;
	
	public ScmMaterialBiz getScmMaterialBiz() {
		return scmMaterialBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public ScmMaterialInventoryBiz getScmMaterialInventoryBiz() {
		return scmMaterialInventoryBiz;
	}

	public void setScmMaterialInventoryBiz(ScmMaterialInventoryBiz scmMaterialInventoryBiz) {
		this.scmMaterialInventoryBiz = scmMaterialInventoryBiz;
	}

	public OrgUnitRelationBiz getOrgUnitRelationBiz() {
		return orgUnitRelationBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}

	public OrgAdminBiz getOrgAdminBiz() {
		return orgAdminBiz;
	}

	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}

	public ScmInvWareHouseBiz getScmInvWareHouseBiz() {
		return scmInvWareHouseBiz;
	}

	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}

	public ScmInvStockBiz getScmInvStockBiz() {
		return scmInvStockBiz;
	}

	public void setScmInvStockBiz(ScmInvStockBiz scmInvStockBiz) {
		this.scmInvStockBiz = scmInvStockBiz;
	}

	public HashMap<String, Object> getOrgAdminMap() {
		return orgAdminMap;
	}

	public void setOrgAdminMap(HashMap<String, Object> orgAdminMap) {
		this.orgAdminMap = orgAdminMap;
	}

	protected  HashMap<String,Object> orgAdminMap = new HashMap<String,Object>();
	protected  HashMap<String,OrgCostCenter2> adminToCostMap = new HashMap<String,OrgCostCenter2>();
	protected  HashMap<String,OrgCompany2> costToFinMap = new HashMap<String,OrgCompany2>();
	protected  HashMap<String,OrgCompany2> invToFinMap = new HashMap<String,OrgCompany2>();
	protected  HashMap<String,Long> scmMatrialMap = new HashMap<String,Long>();
	protected  HashMap<Long,Long> scmUnitMap = new HashMap<Long,Long>();
	
	@Override
	public DataSyncResult dataSync(InvStockListParams invStockListParam, List<ScmInvStock2> scmInvStockS, Param param) throws AppException {
		DataSyncResult dataSyncResult = new DataSyncResult();
		for(ScmInvStock2 scmInvStock :scmInvStockS) {
			if(StringUtils.equals(invStockListParam.getInvNo(), scmInvStock.getInvStockNo())) {
				if(StringUtils.equals(invStockListParam.getDelete(),"Y")) {
					delete(new ScmInvStockNo(scmInvStock.getInvStockNoId()), param);
					scmInvStockBiz.delete(scmInvStock, param);
					dataSyncResult.setNo(invStockListParam.getInvNo());
					dataSyncResult.setErrCode("0");
					return dataSyncResult;
				}
				refreshData(scmInvStock,invStockListParam,param);
				scmInvStockBiz.update(scmInvStock, param);
				//返回结果
				dataSyncResult.setNo(invStockListParam.getInvNo());
				dataSyncResult.setErrCode("0");
				return dataSyncResult;
			}
		}
		if(StringUtils.equals(invStockListParam.getDelete(),"Y")) {
			dataSyncResult.setNo(invStockListParam.getInvNo());
			dataSyncResult.setErrCode("0");
			return dataSyncResult;
		}
		ScmInvStock2 scmInvStockAdd = new ScmInvStock2(true);
		refreshData(scmInvStockAdd,invStockListParam,param);
		scmInvStockBiz.add(scmInvStockAdd, param);
		ScmInvStockNo scmInvStockNo = new ScmInvStockNo();
		scmInvStockNo.setInvStockId(scmInvStockAdd.getId());
		scmInvStockNo.setInvStockNo(invStockListParam.getInvNo());
		add(scmInvStockNo,param);
		dataSyncResult.setNo(invStockListParam.getInvNo());
		dataSyncResult.setErrCode("0");
		return dataSyncResult;
	}

	private void refreshData(ScmInvStock2 scmInvStock,InvStockListParams invStockListParam, Param param) throws AppException{
		scmInvStock.setInvDate(invStockListParam.getInvDate());
		scmInvStock.setLot(invStockListParam.getLot());
		scmInvStock.setQty(invStockListParam.getQty());
		scmInvStock.setBaseQty(invStockListParam.getQty());
		scmInvStock.setAmt(invStockListParam.getAmt());
		scmInvStock.setPrice(invStockListParam.getPrice());
		scmInvStock.setTaxRate(invStockListParam.getTaxRate());
		scmInvStock.setTaxPrice(invStockListParam.getTaxPrice());
		scmInvStock.setTaxAmt(invStockListParam.getTaxAmt());
		scmInvStock.setPieQty(BigDecimal.ZERO);
		//获取物资和单位			
		if(!scmMatrialMap.containsKey(invStockListParam.getItemNo())) {
			ScmMaterial2 selectByItemNo = scmMaterialBiz.selectByItemNo(param.getControlUnitNo(), invStockListParam.getItemNo(), param);
			if(selectByItemNo == null)
				throw new AppException("iscm.api.datePut.billItemNo.notExist");
			scmMatrialMap.put(selectByItemNo.getItemNo(), selectByItemNo.getId());
		}
		scmInvStock.setItemId(scmMatrialMap.get(invStockListParam.getItemNo()));
		if(!scmUnitMap.containsKey(scmInvStock.getItemId())) {
			ScmMaterialInventory2 selectByItemIdAndOrgUnitNo = scmMaterialInventoryBiz.selectByItemId(scmInvStock.getItemId(), param.getOrgUnitNo(), param.getControlUnitNo(), param);
			if(selectByItemIdAndOrgUnitNo == null)
				throw new AppException("iscm.api.datePut.billUnitNo.notExist");
			scmUnitMap.put(selectByItemIdAndOrgUnitNo.getItemId(), selectByItemIdAndOrgUnitNo.getUnitId());
		}
		scmInvStock.setUnit(scmUnitMap.get(scmInvStock.getItemId()));
		OrgAdmin2 orgAdmin = null;
		if(orgAdminMap.containsKey(invStockListParam.getOrgUnitNo()) && orgAdminMap.get(invStockListParam.getOrgUnitNo()) != null) {
			orgAdmin = (OrgAdmin2) orgAdminMap.get(invStockListParam.getOrgUnitNo());
		}else {
			orgAdmin = orgAdminBiz.selectByOrgUnitNo(invStockListParam.getOrgUnitNo(), param);
			orgAdminMap.put(invStockListParam.getOrgUnitNo(), orgAdmin);
		}
		if(orgAdmin != null) {
			scmInvStock.setOrgUnitNo(orgAdmin.getOrgUnitNo());
			// 设置成本中心
			OrgCostCenter2 orgCostCenter = null;
			if(adminToCostMap.containsKey(orgAdmin.getOrgUnitNo()) && adminToCostMap.get(orgAdmin.getOrgUnitNo()) != null) {
				orgCostCenter = (OrgCostCenter2) adminToCostMap.get(orgAdmin.getOrgUnitNo());
			}else {
				List<OrgCostCenter2> orgCostCenterList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOCOST,orgAdmin.getOrgUnitNo(), false, null, param);
				if (orgCostCenterList == null || orgCostCenterList.isEmpty()) {
					throw new AppException("iscm.inventorymanage.cstbusiness.service.impl.ScmInvMoveBillBizImpl.noOutCst");
				}
				orgCostCenter = orgCostCenterList.get(0);
				adminToCostMap.put(orgAdmin.getOrgUnitNo(), orgCostCenter);
			}
			scmInvStock.setCostOrgUnitNo(orgCostCenter.getOrgUnitNo()); 
			scmInvStock.setCostCenter(true);
			//设置财务组织
			OrgCompany2 orgCompany = null;
			if(costToFinMap.containsKey(scmInvStock.getCostOrgUnitNo()) && costToFinMap.get(scmInvStock.getCostOrgUnitNo()) != null) {
				orgCompany = (OrgCompany2) costToFinMap.get(scmInvStock.getCostOrgUnitNo());
			}else {
				List<OrgCompany2> orgCompanylist2 = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.COSTTOFIN, scmInvStock.getCostOrgUnitNo(), false, null, param);
				if (orgCompanylist2 == null || orgCompanylist2.isEmpty()) {
					throw new AppException("iscm.inventorymanage.cstbusiness.service.impl.ScmInvMoveBillBizImpl.noOutCst");
				}
				orgCompany = orgCompanylist2.get(0);
				costToFinMap.put(scmInvStock.getCostOrgUnitNo(), orgCompany);
			}
			scmInvStock.setFinOrgUnitNo(orgCompany.getOrgUnitNo());
		}else{
			//设置库存组织，仓库					
			ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectByWhNo(param.getOrgUnitNo(), invStockListParam.getOrgUnitNo(), param);
			if(scmInvWareHouse == null)
				throw new AppException("iscm.api.datePut.billWhNo.notExist");
			scmInvStock.setOrgUnitNo(param.getOrgUnitNo());
			scmInvStock.setCostCenter(false);
			scmInvStock.setWareHouseId(scmInvWareHouse.getId());
			
			//设置财务组织					
			OrgCompany2 orgCompany = null;
			if(invToFinMap.containsKey(param.getOrgUnitNo()) && invToFinMap.get(param.getOrgUnitNo()) != null) {
				orgCompany = (OrgCompany2) invToFinMap.get(param.getOrgUnitNo());
			}else {
				List<OrgCompany2> orgCompanylist2 = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, param.getOrgUnitNo(), false, null, param);
				if (orgCompanylist2 == null || orgCompanylist2.isEmpty()) {
					throw new AppException("iscm.inventorymanage.cstbusiness.service.impl.ScmInvMoveBillBizImpl.noOutCst");
				}
				orgCompany = orgCompanylist2.get(0);
				invToFinMap.put(param.getOrgUnitNo(), orgCompany);
			}
			scmInvStock.setFinOrgUnitNo(orgCompany.getOrgUnitNo());
		}
	}
}

