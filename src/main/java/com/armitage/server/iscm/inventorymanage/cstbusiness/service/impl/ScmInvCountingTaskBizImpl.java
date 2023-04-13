package com.armitage.server.iscm.inventorymanage.cstbusiness.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.armitage.server.api.business.costcounttask.params.CountCostMaterialListParams;
import com.armitage.server.api.business.costcounttask.params.CountCostTableSaveDetailParams;
import com.armitage.server.api.business.costcounttask.params.CountCostTableSaveParams;
import com.armitage.server.api.business.datasync.params.InvCountingCostDetailParams;
import com.armitage.server.api.business.datasync.params.InvCountingCostListParams;
import com.armitage.server.api.business.datasync.result.DataSyncResult;
import com.armitage.server.api.business.invcounttask.params.CountInvMaterialListParams;
import com.armitage.server.api.business.invcounttask.params.CountInvTableSaveDetailParams;
import com.armitage.server.api.business.invcounttask.params.CountInvTableSaveParams;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.InterfaceParam;
import com.armitage.server.common.base.model.OperationParam;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.base.model.SimpleDataDisplay;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.iscm.basedata.model.ScmIdleItems;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.ScmMaterialInventory2;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.service.ScmIdleItemsBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialInventoryBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountInvTaskDiff;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterial2;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterialGroup2;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListWarehouse2;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTable2;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTableEntry2;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvCountingListMaterialBiz;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvCountingListMaterialGroupBiz;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvCountingListWarehouseBiz;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvCountingTableBiz;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvCountingTableEntryBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvCountingTaskDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountCostTaskDiff;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenter2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingListUserOrg2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTask2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTaskAdvQuery;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmCstFrmLossBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmCstInitBillBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCostConsumeBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingCostCenterBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingCostCenterEntryBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingListUserOrgBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingTaskBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvMoveBillBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvBalBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvMoveInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvOtherInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvOtherInWarehsBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialReqBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMoveIssueBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvOtherIssueBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvOtherIssueBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSaleIssueBillBiz;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgCompanyBiz;
import com.armitage.server.system.service.OrgCostCenterBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmInvCountingTaskBiz")
public class ScmInvCountingTaskBizImpl extends BaseBizImpl<ScmInvCountingTask2> implements ScmInvCountingTaskBiz {

	private UsrBiz usrBiz;
	private OrgUnitBiz orgUnitBiz;
	private OrgAdminBiz orgAdminBiz;
	private ScmInvCountingListWarehouseBiz scmInvCountingListWarehouseBiz;
	private ScmInvCountingListMaterialBiz scmInvCountingListMaterialBiz;
	private ScmInvCountingListUserOrgBiz scmInvCountingListUserOrgBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private ScmInvCountingTableBiz scmInvCountingTableBiz;
	private ScmInvCountingTableEntryBiz scmInvCountingTableEntryBiz;
	private ScmInvCountingCostCenterBiz scmInvCountingCostCenterBiz;
	private ScmInvCountingCostCenterEntryBiz scmInvCountingCostCenterEntryBiz;
	private CodeBiz codeBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private ScmInvOtherInWarehsBillBiz scmInvOtherInWarehsBillBiz;
	private ScmInvOtherInWarehsBillEntryBiz scmInvOtherInWarehsBillEntryBiz;
	private ScmInvOtherIssueBillBiz scmInvOtherIssueBillBiz;
	private ScmInvOtherIssueBillEntryBiz scmInvOtherIssueBillEntryBiz;
	private ScmInvStockBiz scmInvStockBiz;
	private ScmInvBalBiz scmInvBalBiz;
	private PeriodCalendarBiz periodCalendarBiz;
	private SysParamBiz sysParamBiz;
	private OrgCompanyBiz orgCompanyBiz;
	private OrgCostCenterBiz orgCostCenterBiz;
	private OrgStorageBiz orgStorageBiz;
	private ScmMeasureUnitBiz scmMeasureUnitBiz;
	private ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz;
	private ScmInvMoveInWarehsBillBiz scmInvMoveInWarehsBillBiz;
	private ScmInvMaterialReqBillBiz scmInvMaterialReqBillBiz;
	private ScmInvSaleIssueBillBiz scmInvSaleIssueBillBiz;
	private ScmInvMoveIssueBillBiz scmInvMoveIssueBillBiz;
	private ScmInvMoveBillBiz scmInvMoveBillBiz;
	private ScmCstFrmLossBiz scmCstFrmLossBiz;
	private ScmInvCountingListMaterialGroupBiz scmInvCountingListMaterialGroupBiz;
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	private ScmIdleItemsBiz scmIdleItemsBiz;
	private ScmMaterialInventoryBiz scmMaterialInventoryBiz;
	private ScmCstInitBillBiz scmCstInitBillBiz;
	private ScmInvCostConsumeBiz scmInvCostConsumeBiz;

	protected  HashMap<String,Long> scmMatrialMap = new HashMap<String,Long>();
	protected  HashMap<Long,Long> scmUnitMap = new HashMap<Long,Long>();
	
	public void setScmMaterialInventoryBiz(ScmMaterialInventoryBiz scmMaterialInventoryBiz) {
		this.scmMaterialInventoryBiz = scmMaterialInventoryBiz;
	}

	public void setScmInvCostConsumeBiz(ScmInvCostConsumeBiz scmInvCostConsumeBiz) {
		this.scmInvCostConsumeBiz = scmInvCostConsumeBiz;
	}

	public void setScmCstInitBillBiz(ScmCstInitBillBiz scmCstInitBillBiz) {
		this.scmCstInitBillBiz = scmCstInitBillBiz;
	}

	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	public void setScmInvStockBiz(ScmInvStockBiz scmInvStockBiz) {
		this.scmInvStockBiz = scmInvStockBiz;
	}

	public void setScmInvBalBiz(ScmInvBalBiz scmInvBalBiz) {
		this.scmInvBalBiz = scmInvBalBiz;
	}

	public void setScmInvOtherInWarehsBillBiz(ScmInvOtherInWarehsBillBiz scmInvOtherInWarehsBillBiz) {
		this.scmInvOtherInWarehsBillBiz = scmInvOtherInWarehsBillBiz;
	}

	public void setScmInvOtherInWarehsBillEntryBiz(ScmInvOtherInWarehsBillEntryBiz scmInvOtherInWarehsBillEntryBiz) {
		this.scmInvOtherInWarehsBillEntryBiz = scmInvOtherInWarehsBillEntryBiz;
	}

	public void setScmInvOtherIssueBillBiz(ScmInvOtherIssueBillBiz scmInvOtherIssueBillBiz) {
		this.scmInvOtherIssueBillBiz = scmInvOtherIssueBillBiz;
	}

	public void setScmInvOtherIssueBillEntryBiz(ScmInvOtherIssueBillEntryBiz scmInvOtherIssueBillEntryBiz) {
		this.scmInvOtherIssueBillEntryBiz = scmInvOtherIssueBillEntryBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}

	public void setScmInvCountingCostCenterBiz(ScmInvCountingCostCenterBiz scmInvCountingCostCenterBiz) {
		this.scmInvCountingCostCenterBiz = scmInvCountingCostCenterBiz;
	}

	public void setScmInvCountingCostCenterEntryBiz(ScmInvCountingCostCenterEntryBiz scmInvCountingCostCenterEntryBiz) {
		this.scmInvCountingCostCenterEntryBiz = scmInvCountingCostCenterEntryBiz;
	}

	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public void setScmInvCountingListUserOrgBiz(ScmInvCountingListUserOrgBiz scmInvCountingListUserOrgBiz) {
		this.scmInvCountingListUserOrgBiz = scmInvCountingListUserOrgBiz;
	}

	public void setScmInvCountingListMaterialBiz(ScmInvCountingListMaterialBiz scmInvCountingListMaterialBiz) {
		this.scmInvCountingListMaterialBiz = scmInvCountingListMaterialBiz;
	}

	public void setScmInvCountingTableEntryBiz(ScmInvCountingTableEntryBiz scmInvCountingTableEntryBiz) {
		this.scmInvCountingTableEntryBiz = scmInvCountingTableEntryBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setScmInvCountingTableBiz(ScmInvCountingTableBiz scmInvCountingTableBiz) {
		this.scmInvCountingTableBiz = scmInvCountingTableBiz;
	}

	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setScmInvCountingListWarehouseBiz(ScmInvCountingListWarehouseBiz scmInvCountingListWarehouseBiz) {
		this.scmInvCountingListWarehouseBiz = scmInvCountingListWarehouseBiz;
	}
    
	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}

	public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}

	public void setOrgCompanyBiz(OrgCompanyBiz orgCompanyBiz) {
		this.orgCompanyBiz = orgCompanyBiz;
	}

	public void setOrgCostCenterBiz(OrgCostCenterBiz orgCostCenterBiz) {
		this.orgCostCenterBiz = orgCostCenterBiz;
	}

	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}

	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}

	public void setScmInvPurInWarehsBillBiz(ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz) {
		this.scmInvPurInWarehsBillBiz = scmInvPurInWarehsBillBiz;
	}
	
	public void setScmInvMoveInWarehsBillBiz(ScmInvMoveInWarehsBillBiz scmInvMoveInWarehsBillBiz) {
		this.scmInvMoveInWarehsBillBiz = scmInvMoveInWarehsBillBiz;
	}

	public void setScmInvMaterialReqBillBiz(ScmInvMaterialReqBillBiz scmInvMaterialReqBillBiz) {
		this.scmInvMaterialReqBillBiz = scmInvMaterialReqBillBiz;
	}
	
	public void setScmInvSaleIssueBillBiz(ScmInvSaleIssueBillBiz scmInvSaleIssueBillBiz) {
		this.scmInvSaleIssueBillBiz = scmInvSaleIssueBillBiz;
	}

	public void setScmInvMoveIssueBillBiz(ScmInvMoveIssueBillBiz scmInvMoveIssueBillBiz) {
		this.scmInvMoveIssueBillBiz = scmInvMoveIssueBillBiz;
	}
	
	public void setScmInvMoveBillBiz(ScmInvMoveBillBiz scmInvMoveBillBiz) {
		this.scmInvMoveBillBiz = scmInvMoveBillBiz;
	}

	public void setScmCstFrmLossBiz(ScmCstFrmLossBiz scmCstFrmLossBiz) {
		this.scmCstFrmLossBiz = scmCstFrmLossBiz;
	}

	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}

	public void setScmInvCountingListMaterialGroupBiz(ScmInvCountingListMaterialGroupBiz scmInvCountingListMaterialGroupBiz) {
		this.scmInvCountingListMaterialGroupBiz = scmInvCountingListMaterialGroupBiz;
	}

	public void setScmIdleItemsBiz(ScmIdleItemsBiz scmIdleItemsBiz) {
		this.scmIdleItemsBiz = scmIdleItemsBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		if(param instanceof OperationParam) {
			StringBuffer orgUnitNos= new StringBuffer("");
			if(((OperationParam)param).getModuleId()==629723300) {
				//盘存
				List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(param.getOrgUnitNo(), param);
				if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()) {
					for(OrgCostCenter2 orgCostCenter:orgCostCenterList) {
						if(StringUtils.isNotBlank(orgUnitNos.toString()))
							orgUnitNos.append(",");
						orgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
					}
				}
			}else {
				//盘点
				List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(param.getOrgUnitNo(), param);
				if(orgStorageList!=null && !orgStorageList.isEmpty()) {
					for(OrgStorage2 orgStorage:orgStorageList) {
						if(StringUtils.isNotBlank(orgUnitNos.toString()))
							orgUnitNos.append(",");
						orgUnitNos.append("'").append(orgStorage.getOrgUnitNo()).append("'");
					}
				}
			}
			if(StringUtils.isNotBlank(orgUnitNos.toString())) {
				page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "." + ScmInvCountingTask2.FN_ORGUNITNO), 
						new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "." + ScmInvCountingTask2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgUnitNos.toString()));
			}else {
				page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "." + ScmInvCountingTask2.FN_ORGUNITNO), 
						new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "." + ScmInvCountingTask2.FN_ORGUNITNO), QueryParam.QUERY_EQ, param.getOrgUnitNo()));
			}
		}else if(param instanceof InterfaceParam && page.getModel() != null && page.getModel() instanceof ScmInvCountingTaskAdvQuery) {
			ScmInvCountingTaskAdvQuery scmInvCountingTaskAdvQuery = (ScmInvCountingTaskAdvQuery) page.getModel();
			StringBuffer orgUnitNos= new StringBuffer("");
			if(scmInvCountingTaskAdvQuery.isCostCenter()) {
				//盘存
				List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(param.getOrgUnitNo(), param);
				if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()) {
					for(OrgCostCenter2 orgCostCenter:orgCostCenterList) {
						if(StringUtils.isNotBlank(orgUnitNos.toString()))
							orgUnitNos.append(",");
						orgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
					}
				}
			}else {
				//盘点
				List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(param.getOrgUnitNo(), param);
				if(orgStorageList!=null && !orgStorageList.isEmpty()) {
					for(OrgStorage2 orgStorage:orgStorageList) {
						if(StringUtils.isNotBlank(orgUnitNos.toString()))
							orgUnitNos.append(",");
						orgUnitNos.append("'").append(orgStorage.getOrgUnitNo()).append("'");
					}
				}
			}
			if(StringUtils.isNotBlank(orgUnitNos.toString())) {
				page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "." + ScmInvCountingTask2.FN_ORGUNITNO), 
						new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "." + ScmInvCountingTask2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgUnitNos.toString()));
			}else {
				page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "." + ScmInvCountingTask2.FN_ORGUNITNO), 
						new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "." + ScmInvCountingTask2.FN_ORGUNITNO), QueryParam.QUERY_EQ, param.getOrgUnitNo()));
			}
		}else {
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "." + ScmInvCountingTask2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "." + ScmInvCountingTask2.FN_ORGUNITNO), QueryParam.QUERY_EQ, param.getOrgUnitNo()));
		}
	}
	
    @Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		HashMap<String,Object> cacheMap = new HashMap<String,Object>();
		List<ScmInvCountingTask2> scmInvCountingTaskList = list;
		if(scmInvCountingTaskList != null && !scmInvCountingTaskList.isEmpty()){
			int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
			for(ScmInvCountingTask2 scmInvCountingTask : scmInvCountingTaskList){
				if(!scmInvCountingTask.isCostCenter()){
					List<ScmInvCountingTableEntry2> scmInvCountingTableEntryList = scmInvCountingTableEntryBiz.selectByTaskId(scmInvCountingTask.getTaskId(), param);
					if(scmInvCountingTableEntryList != null && !scmInvCountingTableEntryList.isEmpty()){
						BigDecimal accountAmt = BigDecimal.ZERO;
						BigDecimal amt = BigDecimal.ZERO;
						BigDecimal accountTaxAmt = BigDecimal.ZERO;
						BigDecimal taxAmt = BigDecimal.ZERO;
						for(ScmInvCountingTableEntry2 scmInvCountingTableEntry : scmInvCountingTableEntryList){
							if(scmInvCountingTableEntry.getAccountAmt() != null){
								accountAmt = accountAmt.add(scmInvCountingTableEntry.getAccountAmt()).setScale(amtPrecision, RoundingMode.HALF_UP);
							}
							if(scmInvCountingTableEntry.getAmt() != null){
								amt = amt.add(scmInvCountingTableEntry.getAmt()).setScale(amtPrecision, RoundingMode.HALF_UP);
							}
							if(scmInvCountingTableEntry.getAccountTaxAmt() != null){
								accountTaxAmt = accountTaxAmt.add(scmInvCountingTableEntry.getAccountTaxAmt()).setScale(amtPrecision, RoundingMode.HALF_UP);
							}
							if(scmInvCountingTableEntry.getTaxAmt() != null){
								taxAmt = taxAmt.add(scmInvCountingTableEntry.getTaxAmt()).setScale(amtPrecision, RoundingMode.HALF_UP);
							}
						}
						scmInvCountingTask.setAccountAmt(accountAmt);
						scmInvCountingTask.setAmt(amt);
						scmInvCountingTask.setAccountTaxAmt(accountTaxAmt);
						scmInvCountingTask.setTaxAmt(taxAmt);
					}
					if(StringUtils.isNotBlank(scmInvCountingTask.getWrOrDept())) {
						String[] orgUnitNos = StringUtils.split(scmInvCountingTask.getWrOrDept(),",");
						StringBuffer orgUnitNames = new StringBuffer(""); 
						for(String orgUnitNo:orgUnitNos) {
							ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.select(Long.valueOf(orgUnitNo), param);
							if(scmInvWareHouse!=null) {
								if(StringUtils.isNotBlank(orgUnitNames.toString()))
									orgUnitNames.append(",");
								orgUnitNames.append(scmInvWareHouse.getWhName());
							}
						}
						scmInvCountingTask.setWrOrDept(orgUnitNames.toString());
					}
				}else{
					List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList = scmInvCountingCostCenterEntryBiz.selectByTaskId(scmInvCountingTask.getTaskId(), param);
					if(scmInvCountingCostCenterEntryList != null && !scmInvCountingCostCenterEntryList.isEmpty()){
						BigDecimal accountAmt = BigDecimal.ZERO;
						BigDecimal amt = BigDecimal.ZERO;
						BigDecimal accountTaxAmt = BigDecimal.ZERO;
						BigDecimal taxAmt = BigDecimal.ZERO;
						for(ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry : scmInvCountingCostCenterEntryList){
							if(scmInvCountingCostCenterEntry.getAccountAmt() != null){
								accountAmt = accountAmt.add(scmInvCountingCostCenterEntry.getAccountAmt()).setScale(amtPrecision, RoundingMode.HALF_UP);
							}
							if(scmInvCountingCostCenterEntry.getAmt() != null){
								amt = amt.add(scmInvCountingCostCenterEntry.getAmt()).setScale(amtPrecision, RoundingMode.HALF_UP);
							}
							if(scmInvCountingCostCenterEntry.getAccountTaxAmt() != null){
								accountTaxAmt = accountTaxAmt.add(scmInvCountingCostCenterEntry.getAccountTaxAmt()).setScale(amtPrecision, RoundingMode.HALF_UP);
							}
							if(scmInvCountingCostCenterEntry.getTaxAmt() != null){
								taxAmt = taxAmt.add(scmInvCountingCostCenterEntry.getTaxAmt()).setScale(amtPrecision, RoundingMode.HALF_UP);
							}
						}
						scmInvCountingTask.setAccountAmt(accountAmt);
						scmInvCountingTask.setAmt(amt);
						scmInvCountingTask.setAccountTaxAmt(accountTaxAmt);
						scmInvCountingTask.setTaxAmt(taxAmt);
					}
					if(StringUtils.isNotBlank(scmInvCountingTask.getWrOrDept())) {
						String[] orgUnitNos = StringUtils.split(scmInvCountingTask.getWrOrDept(),",");
						StringBuffer orgUnitNames = new StringBuffer(""); 
						for(String orgUnitNo:orgUnitNos) {
							OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(orgUnitNo, param);
							if(orgBaseUnit!=null) {
								if(StringUtils.isNotBlank(orgUnitNames.toString()))
									orgUnitNames.append(",");
								orgUnitNames.append(orgBaseUnit.getOrgUnitName());
							}
						}
						scmInvCountingTask.setWrOrDept(orgUnitNames.toString());
					}
				}
				if (StringUtils.isNotBlank(scmInvCountingTask.getCreator())){
					//制单人
					Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvCountingTask.getCreator());
					if(usr==null){
						usr = usrBiz.selectByCode(scmInvCountingTask.getCreator(), param);
						cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvCountingTask.getCreator(),usr);
					}
					if (usr != null) {
						if(scmInvCountingTask.getDataDisplayMap()==null){
							scmInvCountingTask.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
						}
						scmInvCountingTask.getDataDisplayMap().put(ScmInvCountingTask2.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
					}
				}
				if(StringUtils.isNotBlank(scmInvCountingTask.getStatus())){
					Code code = null;
					if(StringUtils.isNotBlank(scmInvCountingTask.getTaskNo())){
						if(!scmInvCountingTask.isCostCenter()){
							code = codeBiz.selectByCategoryAndCode("countingStatus", scmInvCountingTask.getStatus());
						}else{
							code = codeBiz.selectByCategoryAndCode("costingStatus", scmInvCountingTask.getStatus());
						}
					}
					if(code!=null)
						scmInvCountingTask.setStatusName(code.getName());
				}
				if (StringUtils.isNotBlank(scmInvCountingTask.getConfirmedBy())){
					Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvCountingTask.getConfirmedBy());
					if(usr==null){
						usr = usrBiz.selectByCode(scmInvCountingTask.getConfirmedBy(), param);
						cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvCountingTask.getConfirmedBy(),usr);
					}
					if (usr != null) {
						scmInvCountingTask.setConvertMap(ScmInvCountingTask2.FN_CONFIRMEDBY, usr);
					}
				}
			}
		}
	}
    
    @Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmInvCountingTask2 scmInvCountingTask = (ScmInvCountingTask2) bean.getList().get(0);
		if(scmInvCountingTask != null && scmInvCountingTask.getTaskId() > 0){
			List<ScmInvCountingListWarehouse2> scmInvCountingListWarehouseList = scmInvCountingListWarehouseBiz.selectByTaskId(scmInvCountingTask.getTaskId(), param);
			if(scmInvCountingListWarehouseList != null && !scmInvCountingListWarehouseList.isEmpty()){
				bean.setList2(scmInvCountingListWarehouseList);
			}
			List<ScmInvCountingListMaterial2> scmInvCountingListMaterialList = scmInvCountingListMaterialBiz.selectByTaskId(scmInvCountingTask.getTaskId(), param);
			if(scmInvCountingListMaterialList != null && !scmInvCountingListMaterialList.isEmpty()){
				bean.setList3(scmInvCountingListMaterialList);
			}
			List<ScmInvCountingListMaterialGroup2> scmInvCountingListMaterialGroup2List = scmInvCountingListMaterialGroupBiz.selectByTaskId(scmInvCountingTask.getTaskId(), param);
			if (scmInvCountingListMaterialGroup2List != null && !scmInvCountingListMaterialGroup2List.isEmpty()) {
				bean.setList5(scmInvCountingListMaterialGroup2List);
			}
			List<ScmInvCountingListUserOrg2> scmInvCountingListUserOrgList = scmInvCountingListUserOrgBiz.selectByTaskId(scmInvCountingTask.getTaskId(), param);
			if(scmInvCountingListUserOrgList != null && !scmInvCountingListUserOrgList.isEmpty()){
				HashMap<String,Object> cacheMap = new HashMap<String,Object>();
				for(ScmInvCountingListUserOrg2 scmInvCountingListUserOrg : scmInvCountingListUserOrgList){
					if (StringUtils.isNotBlank(scmInvCountingListUserOrg.getUseOrgUnitNo())){
						//库存组织
						OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvCountingListUserOrg.getUseOrgUnitNo());
						if(orgBaseUnit==null){
							orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvCountingListUserOrg.getUseOrgUnitNo(), param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvCountingListUserOrg.getUseOrgUnitNo(),orgBaseUnit);
						}
						if (orgBaseUnit != null) {
							scmInvCountingListUserOrg.setUseOrgUnitNoName(orgBaseUnit.getOrgUnitName());
						}
					}
				}
				bean.setList4(scmInvCountingListUserOrgList);
			}
		}
	}

	@Override
	protected void beforeAdd(ScmInvCountingTask2 entity, Param param) throws AppException {
		if(entity != null){
			//获取期间
			PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(entity.getBizDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
			}
			entity.setPeriodId(periodCalendar.getPeriodId());
			entity.setAccountYear(periodCalendar.getAccountYear());
			entity.setAccountPeriod(periodCalendar.getAccountPeriod());
			String orgCompany = entity.getFinOrgUnitNo();
			if(!entity.isCostCenter()) {
				Page page = new Page();
				page.setModelClass(OrgCompany2.class);
				page.setShowCount(Integer.MAX_VALUE);
				List<String> arglist = new ArrayList<>();
				arglist.add("type=to");
				arglist.add("relationType="+OrgUnitRelationType.INVTOFIN);
				arglist.add("fromOrgUnitNo="+entity.getOrgUnitNo());
				List<OrgCompany2> orgCompanyList = orgCompanyBiz.queryPage(page, arglist, "queryPageEx", param);
				if(orgCompanyList==null || orgCompanyList.isEmpty())
					throw new AppException("iscm.inventorymanage.ScmInvMaterialRequestBillBizImpl.reqOrgUnit.notfinorg");
				orgCompany = orgCompanyList.get(0).getOrgUnitNo();
			}
			entity.setFinOrgUnitNo(orgCompany);
			
			//检查任务编号是否重复
			Page page = new Page();
			page.setModelClass(ScmInvCountingTask2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmInvCountingTask2.FN_CONTROLUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "." +ScmInvCountingTask2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			page.getParam().put(ScmInvCountingTask2.FN_TASKNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "." +ScmInvCountingTask2.FN_TASKNO, QueryParam.QUERY_EQ,entity.getTaskNo()));
			
			List<String> arglist = new ArrayList<>();
			List<ScmInvCountingTask2> scmInvCountingTaskList = this.queryPage(page, arglist, "findAllPage", param);
			
			if (scmInvCountingTaskList != null && !scmInvCountingTaskList.isEmpty()) {
				//若任务编号重复，则重新生成任务编号
				String date = StringUtils.replace(FormatUtils.fmtDate(entity.getCreateDate()), "-", "");
				StringBuffer s = new StringBuffer("");
				if(!entity.isCostCenter()) {
					s.append("TC").append(date);
				} else {
					s.append("TW").append(date);
				}
				
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("createDate", FormatUtils.fmtDate(FormatUtils.parseDateTime(date, "yyyyMMdd")));
				map.put("controlUnitNo", param.getControlUnitNo());
				ScmInvCountingTask2 scmInvCountingTask= ((ScmInvCountingTaskDAO) dao).selectMaxIdByDate(map);
				if(scmInvCountingTask != null && StringUtils.isNotBlank(scmInvCountingTask.getTaskNo())
						&& scmInvCountingTask.getTaskNo().contains(date)){
					String str = StringUtils.substring(scmInvCountingTask.getTaskNo(), (scmInvCountingTask.getTaskNo().indexOf(date)+date.length()));
					str = CodeAutoCalUtil.autoAddOne(str);
					str = (s.append(str)).toString();
					entity.setTaskNo(str);
				}else{
					entity.setTaskNo((s.append("001").toString()));
				}
			}
		}
	}
	
	@Override
	protected void beforeUpdate(ScmInvCountingTask2 oldEntity,ScmInvCountingTask2 newEntity, Param param) throws AppException {
		//获取期间
		PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(newEntity.getBizDate(), param);
		if(periodCalendar==null){
			throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
		}
		if (!StringUtils.equals(oldEntity.getStatus(), newEntity.getStatus())) {
			switch (StringUtils.lowerCase(newEntity.getStatus())) {
			case "c":
				if (StringUtils.contains("I,O,C", oldEntity.getStatus())) {
					throw new AppException("iscm.inventorymanage.scminvcountingtask.cancelcounting.cancelCounting");
				}
				break;
			}
		}
		newEntity.setPeriodId(periodCalendar.getPeriodId());
		newEntity.setAccountYear(periodCalendar.getAccountYear());
		newEntity.setAccountPeriod(periodCalendar.getAccountPeriod());
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmInvCountingTask2 scmInvCountingTask = (ScmInvCountingTask2) bean.getList().get(0);
		if(scmInvCountingTask != null && scmInvCountingTask.getTaskId() > 0){
			List<ScmInvCountingListWarehouse2> scmInvCountingListWarehouseList = bean.getList2();
			if(scmInvCountingListWarehouseList != null && !scmInvCountingListWarehouseList.isEmpty()){
				for(ScmInvCountingListWarehouse2 scmInvCountingListWarehouse : scmInvCountingListWarehouseList){
					scmInvCountingListWarehouse.setTaskId(scmInvCountingTask.getTaskId());
					scmInvCountingListWarehouseBiz.add(scmInvCountingListWarehouse, param);
				}
			}
			List<ScmInvCountingListMaterial2> scmInvCountingListMaterialList = bean.getList3();
			if(scmInvCountingListMaterialList != null && !scmInvCountingListMaterialList.isEmpty()){
				for(ScmInvCountingListMaterial2 scmInvCountingListMaterial : scmInvCountingListMaterialList){
					scmInvCountingListMaterial.setTaskId(scmInvCountingTask.getTaskId());
					scmInvCountingListMaterialBiz.add(scmInvCountingListMaterial, param);
				}
			}
			List<ScmInvCountingListUserOrg2> scmInvCountingListUserOrgList = bean.getList4();
			if(scmInvCountingListUserOrgList != null && !scmInvCountingListUserOrgList.isEmpty()){
				for(ScmInvCountingListUserOrg2 scmInvCountingListUserOrg : scmInvCountingListUserOrgList){
					scmInvCountingListUserOrg.setTaskId(scmInvCountingTask.getTaskId());
					scmInvCountingListUserOrgBiz.add(scmInvCountingListUserOrg, param);
				}
			}
			List<ScmInvCountingListMaterialGroup2> scmInvCountingListMaterialGroup2List = bean.getList5();
			if (scmInvCountingListMaterialGroup2List != null && !scmInvCountingListMaterialGroup2List.isEmpty()) {
				for (ScmInvCountingListMaterialGroup2 scmInvCountingListMaterialGroup2 : scmInvCountingListMaterialGroup2List) {
					scmInvCountingListMaterialGroup2.setTaskId(scmInvCountingTask.getTaskId());
					scmInvCountingListMaterialGroupBiz.add(scmInvCountingListMaterialGroup2, param);
				}
			}
		}
	}
	
	public CommonBean splitOrgAdd(CommonBean bean, Param param) {
		ScmInvCountingTask2 scmInvCountingTask = (ScmInvCountingTask2) bean.getList().get(0);
		List<ScmInvCountingListMaterial2> scmInvCountingListMaterialList = bean.getList3();//物资
		List<ScmInvCountingListUserOrg2> scmInvCountingListUserOrgList = bean.getList4();//部门
		List<ScmInvCountingListMaterialGroup2> scmInvCountingListMaterialGroup2List = bean.getList5();//物资类别
		if (!scmInvCountingTask.isAssignLocation()) {
			//未选定盘存部门，生成所有可盘部门
			scmInvCountingListUserOrgList = generateInvCountingOrg(scmInvCountingTask,scmInvCountingListUserOrgList,param);
		}
		if (scmInvCountingListUserOrgList != null && !scmInvCountingListUserOrgList.isEmpty()) {
			for (ScmInvCountingListUserOrg2 scmInvCountingListUserOrg2 : scmInvCountingListUserOrgList) {
				if (scmInvCountingListUserOrg2.isSelectOrExclude()) {
					//生成盘存任务
					splitGenerateTask(scmInvCountingTask,bean,param);
					//生成盘存部门
					ScmInvCountingListUserOrg2 scmInvCountingListUserOrg22 = new ScmInvCountingListUserOrg2();
					BeanUtils.copyProperties(scmInvCountingListUserOrg2, scmInvCountingListUserOrg22);
					ArrayList<ScmInvCountingListUserOrg2> scmInvCountingListUserOrgListsArrayList = new ArrayList();
					scmInvCountingListUserOrgListsArrayList.add(scmInvCountingListUserOrg22);
					bean.setList4(scmInvCountingListUserOrgListsArrayList);
					bean.setList3(scmInvCountingListMaterialList);
					bean.setList5(scmInvCountingListMaterialGroup2List);
					this.add(bean, param);
				}
			}
		}
		return bean;
	}

	private void splitGenerateTask(ScmInvCountingTask2 scmInvCountingTask, CommonBean bean, Param param) {
		ArrayList<ScmInvCountingTask2> scmInvCountingTask2List = new ArrayList();
		ScmInvCountingTask2 scmInvCountingTask2 = new ScmInvCountingTask2();
		BeanUtils.copyProperties(scmInvCountingTask, scmInvCountingTask2);
		scmInvCountingTask2.setFromWhNo(null);
		scmInvCountingTask2.setToWhNo(null);
		scmInvCountingTask2.setFromDeptNo(null);
		scmInvCountingTask2.setToDeptNo(null);
		scmInvCountingTask2.setAssignLocation(true);
		//检查任务编号是否重复
		Page page = new Page();
		page.setModelClass(ScmInvCountingTask2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmInvCountingTask2.FN_CONTROLUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "." +ScmInvCountingTask2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
		page.getParam().put(ScmInvCountingTask2.FN_TASKNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "." +ScmInvCountingTask2.FN_TASKNO, QueryParam.QUERY_EQ,scmInvCountingTask2.getTaskNo()));
		
		List<String> arglist = new ArrayList<>();
		List<ScmInvCountingTask2> scmInvCountingTaskList = this.queryPage(page, arglist, "findAllPage", param);
		if (scmInvCountingTaskList != null && !scmInvCountingTaskList.isEmpty()) {
			//若任务编号重复，则重新生成任务编号
			String date = StringUtils.replace(FormatUtils.fmtDate(scmInvCountingTask2.getCreateDate()), "-", "");
			StringBuffer s = new StringBuffer("");
			if(!scmInvCountingTask2.isCostCenter()) {
				s.append("TC").append(date);
			} else {
				s.append("TW").append(date);
			}
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("createDate", FormatUtils.fmtDate(FormatUtils.parseDateTime(date, "yyyyMMdd")));
			map.put("controlUnitNo", param.getControlUnitNo());
			ScmInvCountingTask2 scmInvCountingTask3= ((ScmInvCountingTaskDAO) dao).selectMaxIdByDate(map);
			if(scmInvCountingTask3 != null && StringUtils.isNotBlank(scmInvCountingTask3.getTaskNo())
					&& scmInvCountingTask3.getTaskNo().contains(date)){
				String str = StringUtils.substring(scmInvCountingTask3.getTaskNo(), (scmInvCountingTask3.getTaskNo().indexOf(date)+date.length()));
				str = CodeAutoCalUtil.autoAddOne(str);
				str = (s.append(str)).toString();
				scmInvCountingTask2.setTaskNo(str);
			}else{
				scmInvCountingTask2.setTaskNo((s.append("001").toString()));
			}
		}
		scmInvCountingTask2List.add(scmInvCountingTask2);
		bean.setList(scmInvCountingTask2List);
	}

	private List<ScmInvCountingListUserOrg2> generateInvCountingOrg(ScmInvCountingTask2 scmInvCountingTask,List<ScmInvCountingListUserOrg2> scmInvCountingListUserOrgList,Param param) {
		List<String> orgUnitNoList = new ArrayList<>();
		List<String> orgAdminList = new ArrayList<>();
		Page page = new Page();
		page.setModelClass(OrgCostCenter2.class);
		page.setShowCount(Integer.MAX_VALUE);
		ArrayList argList = new ArrayList();
		argList.add("orgUnitNo=" + scmInvCountingTask.getOrgUnitNo());
		List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.queryPage(page, argList, "findChildByOrg", param);
		StringBuilder cstOrgUnitNos = new StringBuilder("");
        if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()){
            for(OrgCostCenter2 orgCostCenter:orgCostCenterList){
            	if (!StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
            		if(StringUtils.isNotBlank(cstOrgUnitNos.toString())) {
                        cstOrgUnitNos.append(",");
                    }
            		cstOrgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
                }
            }
        }else{
            cstOrgUnitNos.append("'0'");
        }
		page.setModelClass(ScmInvStock2.class);
		page.setShowCount(Integer.MAX_VALUE);
        argList = new ArrayList();
        argList.add("costOrgUnitNo="+cstOrgUnitNos);
        List<ScmInvStock2> scmInvStockList = scmInvStockBiz.queryPage(page, argList, "selectDeptByFinPage", param);
        if (scmInvStockList != null && !scmInvStockList.isEmpty()) {
            for (int i = 0; i < scmInvStockList.size(); i++) {
                boolean flag = true;
                if (StringUtils.isNotBlank(scmInvCountingTask.getFromDeptNo())) {
                    flag = false;
                    if ((scmInvStockList.get(i).getOrgUnitNo()).compareToIgnoreCase(scmInvCountingTask.getFromDeptNo()) >= 0) {
                        flag = true;
                    }
                }
                if (flag && StringUtils.isNotBlank(scmInvCountingTask.getToDeptNo())) {
                    flag = false;
                    if ((scmInvStockList.get(i).getOrgUnitNo()).compareToIgnoreCase(scmInvCountingTask.getToDeptNo()) <= 0) {
                        flag = true;
                    }
                }
                if (flag) {
                	orgAdminList.add(scmInvStockList.get(i).getOrgUnitNo());
                }
            }
        }
		if (orgAdminList != null && !orgAdminList.isEmpty()) {
			int i, j;
			for (i = 0; i < orgAdminList.size(); i++) {
				boolean flag = false;
				if (scmInvCountingListUserOrgList != null && !scmInvCountingListUserOrgList.isEmpty()) {
					for (j = 0; j < scmInvCountingListUserOrgList.size(); j++) {
						if (!scmInvCountingListUserOrgList.get(j).isSelectOrExclude()
								&& scmInvCountingListUserOrgList.get(j)
										.getUseOrgUnitNo().equals(orgAdminList.get(i))) {
							flag = true;
						}
					}
				}
				if (!flag) {
					orgUnitNoList.add(orgAdminList.get(i));
				}
			}
		}
		if (orgUnitNoList != null && !orgUnitNoList.isEmpty()) {
			ArrayList<ScmInvCountingListUserOrg2> scmInvCountingCostCenter2s = new ArrayList();
			for(String costOrgUnitNo : orgUnitNoList){
				ScmInvCountingListUserOrg2 scmInvCountingListUserOrg2 = new ScmInvCountingListUserOrg2(true);
				scmInvCountingListUserOrg2.setTaskId(scmInvCountingTask.getTaskId());
				scmInvCountingListUserOrg2.setUseOrgUnitNo(costOrgUnitNo);
				scmInvCountingListUserOrg2.setSelectOrExclude(true);
				scmInvCountingCostCenter2s.add(scmInvCountingListUserOrg2);
			}
			return scmInvCountingCostCenter2s;
		}
		return null;
	}

	@Override
	protected void afterUpdate(ScmInvCountingTask2 oldEntity,ScmInvCountingTask2 newEntity, Param param) throws AppException {
		if(newEntity!=null && StringUtils.isNotBlank(newEntity.getTaskNo())){
			//更新状态
			if(newEntity.getTaskNo().startsWith("TC")){
				List<ScmInvCountingTable2> scmInvCountingTableList = (List<ScmInvCountingTable2>) scmInvCountingTableBiz.selectByTaskNo(newEntity.getTaskNo(),param.getControlUnitNo(), param);
				if(scmInvCountingTableList!=null && !scmInvCountingTableList.isEmpty()){
					for(ScmInvCountingTable2 scmInvCountingTable : scmInvCountingTableList){
						scmInvCountingTable.setStatus(newEntity.getStatus());
						scmInvCountingTable.setTaskBegTime(newEntity.getCountingBegTime());
						scmInvCountingTable.setTaskEndTime(newEntity.getCountingEndTime());
						scmInvCountingTableBiz.update(scmInvCountingTable, param);
					}
				}
			}else if(newEntity.getTaskNo().startsWith("TW")){
				List<ScmInvCountingCostCenter2> scmInvCountingCostCenterList = (List<ScmInvCountingCostCenter2>) scmInvCountingCostCenterBiz.selectByTaskNo(newEntity.getTaskNo(),param.getControlUnitNo(), param);
				if(scmInvCountingCostCenterList!=null && !scmInvCountingCostCenterList.isEmpty()){
					for(ScmInvCountingCostCenter2 scmInvCountingCostCenter : scmInvCountingCostCenterList){
						scmInvCountingCostCenter.setStatus(newEntity.getStatus());
						scmInvCountingCostCenter.setTaskBegTime(newEntity.getCountingBegTime());
						scmInvCountingCostCenter.setTaskEndTime(newEntity.getCountingEndTime());
						scmInvCountingCostCenterBiz.update(scmInvCountingCostCenter, param);
					}
				}
			}
		}
	}
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvCountingTask2 scmInvCountingTask = (ScmInvCountingTask2) bean.getList().get(0);
		if(scmInvCountingTask != null && scmInvCountingTask.getTaskId() > 0){
			List<ScmInvCountingListWarehouse2> scmInvCountingListWarehouseList = bean.getList2();
			if(scmInvCountingListWarehouseList != null && !scmInvCountingListWarehouseList.isEmpty()){
				for(ScmInvCountingListWarehouse2 scmInvCountingListWarehouse : scmInvCountingListWarehouseList){
					if(scmInvCountingListWarehouse.getId() > 0){
						scmInvCountingListWarehouseBiz.update(scmInvCountingListWarehouse, param);
					}else{
						scmInvCountingListWarehouse.setTaskId(scmInvCountingTask.getTaskId());
						scmInvCountingListWarehouseBiz.add(scmInvCountingListWarehouse, param);
					}
				}
			}
			List<ScmInvCountingListMaterial2> scmInvCountingListMaterialList = bean.getList3();
			if(scmInvCountingListMaterialList != null && !scmInvCountingListMaterialList.isEmpty()){
				for(ScmInvCountingListMaterial2 scmInvCountingListMaterial : scmInvCountingListMaterialList){
					if(scmInvCountingListMaterial.getId() > 0){
						scmInvCountingListMaterialBiz.update(scmInvCountingListMaterial, param);
					}else{
						scmInvCountingListMaterial.setTaskId(scmInvCountingTask.getTaskId());
						scmInvCountingListMaterialBiz.add(scmInvCountingListMaterial, param);
					}
				}
			}
			List<ScmInvCountingListUserOrg2> scmInvCountingListUserOrgList = bean.getList4();
			if(scmInvCountingListUserOrgList != null && !scmInvCountingListUserOrgList.isEmpty()){
				for(ScmInvCountingListUserOrg2 scmInvCountingListUserOrg : scmInvCountingListUserOrgList){
					if(scmInvCountingListUserOrg.getId() > 0){
						scmInvCountingListUserOrgBiz.update(scmInvCountingListUserOrg, param);
					}else{
						scmInvCountingListUserOrg.setTaskId(scmInvCountingTask.getTaskId());
						scmInvCountingListUserOrgBiz.add(scmInvCountingListUserOrg, param);
					}
				}
			}
			List<ScmInvCountingListMaterialGroup2> scmInvCountingListMaterialGroup2List = bean.getList5();
			if (scmInvCountingListMaterialGroup2List != null && !scmInvCountingListMaterialGroup2List.isEmpty()) {
				for (ScmInvCountingListMaterialGroup2 scmInvCountingListMaterialGroup2 : scmInvCountingListMaterialGroup2List) {
					if (scmInvCountingListMaterialGroup2.getId()>0) {
						scmInvCountingListMaterialGroupBiz.update(scmInvCountingListMaterialGroup2, param);
					}else {
						scmInvCountingListMaterialGroup2.setTaskId(scmInvCountingTask.getTaskId());
						scmInvCountingListMaterialGroupBiz.add(scmInvCountingListMaterialGroup2, param);
					}
				}
			}
		}
	}
	
	@Override
	protected void afterDelete(ScmInvCountingTask2 entity, Param param) throws AppException {
		if(entity != null && entity.getTaskId() > 0){
			scmInvCountingListWarehouseBiz.deleteByTaskId(entity.getTaskId(), param);
			scmInvCountingListMaterialBiz.deleteByTaskId(entity.getTaskId(), param);
			scmInvCountingListUserOrgBiz.deleteByTaskId(entity.getTaskId(), param);
			scmInvCountingListMaterialGroupBiz.deleteByTaskId(entity.getTaskId(),param);
		}
	}

	@Override
    public ScmInvCountingTask2 selectMaxIdByDate(String date, String taskType, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		if(StringUtils.isNotBlank(date)){
			map.put("date", date);
		}
		if(StringUtils.isNotBlank(taskType)){
			map.put("taskType", taskType);
		}
		map.put("controlUnitNo", param.getControlUnitNo());
		return ((ScmInvCountingTaskDAO) dao).selectMaxIdByDate(map);
    }

	@Override
	public ScmInvCountingTask2 generateCounting(ScmInvCountingTask2 scmInvCountingTask, Param param)
			throws AppException {
		if (scmInvCountingTask != null) {
			// 取出盘点仓库
			List<Long> wareHouseIdList = new ArrayList<>();
			List<ScmInvCountingListWarehouse2> scmInvCountingListWarehouseList = scmInvCountingListWarehouseBiz
					.selectByTaskId(scmInvCountingTask.getTaskId(), param);
			if (scmInvCountingTask.isAssignLocation()) {
				if (scmInvCountingListWarehouseList != null && !scmInvCountingListWarehouseList.isEmpty()) {
					for (ScmInvCountingListWarehouse2 scmInvCountingListWarehouse : scmInvCountingListWarehouseList) {
						if (scmInvCountingListWarehouse.isSelectOrExclude() && !wareHouseIdList.contains(scmInvCountingListWarehouse.getWareHouseId())) {
							wareHouseIdList.add(scmInvCountingListWarehouse.getWareHouseId());
						}
					}
				}
			} else {
				List<ScmInvWareHouse> scmInvWareHouseList = new ArrayList<>();
				if (StringUtils.isNotBlank(scmInvCountingTask.getFromWhNo())
						|| StringUtils.isNotBlank(scmInvCountingTask.getToWhNo())) {
					scmInvWareHouseList = scmInvWareHouseBiz.selectByOrgUnitNo(scmInvCountingTask.getOrgUnitNo(),
							scmInvCountingTask.getFromWhNo(), scmInvCountingTask.getToWhNo(), param);
				} else {
					scmInvWareHouseList = scmInvWareHouseBiz.selectByOrgUnitNo(scmInvCountingTask.getOrgUnitNo(), null,
							null, param);
				}
				if (scmInvWareHouseList != null && !scmInvWareHouseList.isEmpty()) {
					int i, j;
					for (i = 0; i < scmInvWareHouseList.size(); i++) {
						boolean flag = false;
						if (scmInvCountingListWarehouseList != null && !scmInvCountingListWarehouseList.isEmpty()) {
							for (j = 0; j < scmInvCountingListWarehouseList.size(); j++) {
								if (!scmInvCountingListWarehouseList.get(j).isSelectOrExclude()
										&& scmInvCountingListWarehouseList.get(j)
												.getWareHouseId() == scmInvWareHouseList.get(i).getId()) {
									flag = true;
								}
							}
						}
						if (!flag) {
							wareHouseIdList.add(scmInvWareHouseList.get(i).getId());
						}
					}
				}
			}
			//取出盘点物料
			StringBuffer itemIdList = new StringBuffer("");
			ArrayList<ScmMaterialGroup> scmMaterialGroupList = new ArrayList();
			List<ScmInvCountingListMaterialGroup2> scmInvCountingListMaterialGroupList = scmInvCountingListMaterialGroupBiz.selectByTaskId(scmInvCountingTask.getTaskId(), param);
			if (scmInvCountingListMaterialGroupList != null && !scmInvCountingListMaterialGroupList.isEmpty() && scmInvCountingTask.isAssignItemGroup()) {
				for (ScmInvCountingListMaterialGroup2 scmInvCountingListMaterialGroup2 : scmInvCountingListMaterialGroupList) {
					scmMaterialGroupList.addAll(scmMaterialGroupBiz.findChild(scmInvCountingListMaterialGroup2.getClassId(), param)); 
				}
			}
			List<ScmInvCountingListMaterial2> scmInvCountingListMaterialList = scmInvCountingListMaterialBiz
					.selectByTaskId(scmInvCountingTask.getTaskId(), param);
			if (scmInvCountingTask.isAssignItem()) {
				if (scmInvCountingListMaterialList != null && !scmInvCountingListMaterialList.isEmpty()) {
					for (ScmInvCountingListMaterial2 scmInvCountingListMaterial : scmInvCountingListMaterialList) {
						if (scmInvCountingListMaterial.isSelectOrExclude()) {
							itemIdList.append(scmInvCountingListMaterial.getItemId()).append(",");
						}
					}
				}
			} else {
				List<ScmMaterial2> scmMaterialList = new ArrayList<>();
				if (StringUtils.isNotBlank(scmInvCountingTask.getFromItemNo())
						|| StringUtils.isNotBlank(scmInvCountingTask.getToItemNo())) {
					scmMaterialList = scmMaterialBiz.findCountingMaterial(scmInvCountingTask.getOrgUnitNo(),null,
							scmInvCountingTask.getFromItemNo(), scmInvCountingTask.getToItemNo(), "TC", param);
				} else {
					scmMaterialList = scmMaterialBiz.findCountingMaterial(scmInvCountingTask.getOrgUnitNo(), null,null,
							null, "TC", param);
				}
				if (scmMaterialList != null && !scmMaterialList.isEmpty()) {
					int i, j;
					for (i = 0; i < scmMaterialList.size(); i++) {
						boolean flag = false;
						boolean itemGroupFlag = false;
						if (scmInvCountingListMaterialList != null && !scmInvCountingListMaterialList.isEmpty()) {
							for (j = 0; j < scmInvCountingListMaterialList.size(); j++) {
								if (!scmInvCountingListMaterialList.get(j).isSelectOrExclude()
										&& scmInvCountingListMaterialList.get(j).getItemId() == scmMaterialList.get(i).getId()) {
									flag = true;
								}
							}
						}
						// 指定物资类别过滤
						if (scmInvCountingTask.isAssignItemGroup()) {
							if (scmInvCountingListMaterialGroupList != null && !scmInvCountingListMaterialGroupList.isEmpty()) {
								if (scmMaterialGroupList != null && !scmMaterialGroupList.isEmpty()) {
									for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
										if (scmMaterialList.get(i).getGroupId() == scmMaterialGroup.getId()) {
											itemGroupFlag = true;
										}
									}
								}
							}
						}else {
							itemGroupFlag=true;
						}
						if (!flag && itemGroupFlag) {
							itemIdList.append(scmMaterialList.get(i).getId()).append(",");
						}
					}
				}
			}
			//生成盘点表  
			if (wareHouseIdList != null && !wareHouseIdList.isEmpty()) {
				for(long warehouseId : wareHouseIdList){
					ScmInvCountingTable2 scmInvCountingTable = new ScmInvCountingTable2(true);
					scmInvCountingTable.setTaskId(scmInvCountingTask.getTaskId());
					scmInvCountingTable.setOrgUnitNo(scmInvCountingTask.getOrgUnitNo());
					scmInvCountingTable.setWareHouseId(warehouseId);
					scmInvCountingTable.setBizDate(scmInvCountingTask.getBizDate());
//					scmInvCountingTable.setCountingPerson(param.getUsrCode());
					scmInvCountingTable.setStatus("I");
					scmInvCountingTable.setCreator(scmInvCountingTask.getCreator());
					scmInvCountingTable.setCreateDate(scmInvCountingTask.getCreateDate());
					scmInvCountingTable.setControlUnitNo(scmInvCountingTask.getControlUnitNo());
					scmInvCountingTableBiz.add(scmInvCountingTable, param);
				}
			}
			//生成盘点明细表  
			if(StringUtils.isNotBlank(itemIdList.toString())){
				scmInvCountingTableEntryBiz.addByItemIdList(scmInvCountingTask.getTaskId(),StringUtils.substring(itemIdList.toString(), 0, (itemIdList.toString().length())-1), scmInvCountingTask.isGenAccQty(), scmInvCountingTask.isGenTableForZero(), param);
			}
			//删除无结存的物资
			scmInvCountingTableBiz.deleteNotExist(scmInvCountingTask.getTaskId(), param);
			scmInvCountingTask.setStatus("T");
			this.update(scmInvCountingTask, param);	
			return scmInvCountingTask;
		}
		return null;
	}

    @Override
    public List<ScmInvCountingTask2> selectByDate(String date, String orgUnitNo, Param param) throws AppException {
        
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("date", date);
        map.put("orgUnitNo", orgUnitNo);
        List<ScmInvCountingTask2> taskList = ((ScmInvCountingTaskDAO) dao).selectByDate(map);
        return taskList;
    }

	@Override
	public List<ScmInvCountingTask2> selectByOrgUnitNoAndWareHouseId(String orgUnitNo,long taskId, String warehouseIdList,
			Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("finOrgUnitNo", orgUnitNo);
        map.put("orgUnitNo", param.getOrgUnitNo());
        map.put("taskId", taskId);
        map.put("warehouseIdList", warehouseIdList);
        List<ScmInvCountingTask2> taskList = ((ScmInvCountingTaskDAO) dao).selectByOrgUnitNoAndWareHouseId(map);
        return taskList;
	}

	@Override
	public List<ScmInvCountingTask2> selectByOrgUnitNoAndUseOrgUnitNo(String orgUnitNo,long taskId, String orgUnitNoList,
			Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("finOrgUnitNo", orgUnitNo);
        map.put("orgUnitNo", param.getOrgUnitNo());
        map.put("taskId", taskId);
        map.put("orgUnitNoList", orgUnitNoList);
        List<ScmInvCountingTask2> taskList = ((ScmInvCountingTaskDAO) dao).selectByOrgUnitNoAndUseOrgUnitNo(map);
        return taskList;
	}

	@Override
	public ScmInvCountingTask2 generateCosting(ScmInvCountingTask2 scmInvCountingTask, Param param)
			throws AppException {
		if (scmInvCountingTask != null) {
			//取出盘存部门
			List<String> orgUnitNoList = new ArrayList<>();
			List<ScmInvCountingListUserOrg2> scmInvCountingListUserOrgList = scmInvCountingListUserOrgBiz.selectByTaskId(scmInvCountingTask.getTaskId(), param);
			if (scmInvCountingTask.isAssignLocation()) {
				if (scmInvCountingListUserOrgList != null && !scmInvCountingListUserOrgList.isEmpty()) {
					for (ScmInvCountingListUserOrg2 scmInvCountingListUserOrg : scmInvCountingListUserOrgList) {
						if (scmInvCountingListUserOrg.isSelectOrExclude() && !orgUnitNoList.contains(scmInvCountingListUserOrg.getUseOrgUnitNo())) {
							orgUnitNoList.add(scmInvCountingListUserOrg.getUseOrgUnitNo());
						}
					}
				}
			} else {
				List<String> orgAdminList = new ArrayList<>();
				Page page = new Page();
				page.setModelClass(OrgCostCenter2.class);
				page.setShowCount(Integer.MAX_VALUE);
				ArrayList argList = new ArrayList();
				argList.add("orgUnitNo=" + scmInvCountingTask.getOrgUnitNo());
				List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.queryPage(page, argList, "findChildByOrg", param);
				StringBuilder cstOrgUnitNos = new StringBuilder("");
		        if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()){
		            for(OrgCostCenter2 orgCostCenter:orgCostCenterList){
		            	if (!StringUtils.equals("1", orgCostCenter.getCostCenterType())) {
		            		if(StringUtils.isNotBlank(cstOrgUnitNos.toString())) {
		                        cstOrgUnitNos.append(",");
		                    }
		            		cstOrgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
		                }
		            }
		        }else{
		            cstOrgUnitNos.append("'0'");
		        }
				page.setModelClass(ScmInvStock2.class);
				page.setShowCount(Integer.MAX_VALUE);
		        argList = new ArrayList();
		        argList.add("costOrgUnitNo="+cstOrgUnitNos);
		        List<ScmInvStock2> scmInvStockList = scmInvStockBiz.queryPage(page, argList, "selectDeptByFinPage", param);
		        if (scmInvStockList != null && !scmInvStockList.isEmpty()) {
		            for (int i = 0; i < scmInvStockList.size(); i++) {
		                boolean flag = true;
		                if (StringUtils.isNotBlank(scmInvCountingTask.getFromDeptNo())) {
		                    flag = false;
		                    if ((scmInvStockList.get(i).getOrgUnitNo()).compareToIgnoreCase(scmInvCountingTask.getFromDeptNo()) >= 0) {
		                        flag = true;
		                    }
		                }
		                if (flag && StringUtils.isNotBlank(scmInvCountingTask.getToDeptNo())) {
		                    flag = false;
		                    if ((scmInvStockList.get(i).getOrgUnitNo()).compareToIgnoreCase(scmInvCountingTask.getToDeptNo()) <= 0) {
		                        flag = true;
		                    }
		                }
		                if (flag) {
		                	orgAdminList.add(scmInvStockList.get(i).getOrgUnitNo());
		                }
		            }
		        }
				if (orgAdminList != null && !orgAdminList.isEmpty()) {
					int i, j;
					for (i = 0; i < orgAdminList.size(); i++) {
						boolean flag = false;
						if (scmInvCountingListUserOrgList != null && !scmInvCountingListUserOrgList.isEmpty()) {
							for (j = 0; j < scmInvCountingListUserOrgList.size(); j++) {
								if (!scmInvCountingListUserOrgList.get(j).isSelectOrExclude()
										&& scmInvCountingListUserOrgList.get(j)
												.getUseOrgUnitNo().equals(orgAdminList.get(i))) {
									flag = true;
								}
							}
						}
						if (!flag) {
							orgUnitNoList.add(orgAdminList.get(i));
						}
					}
				}
			}
			StringBuffer depts = new StringBuffer("");
			for(String dept:orgUnitNoList) {
				if(StringUtils.isNotBlank(depts.toString()))
					depts.append(",");
				depts.append("'").append(dept).append("'");
			}
			//取出盘点物料
			StringBuffer itemIdList = new StringBuffer("");
			ArrayList<ScmMaterialGroup> scmMaterialGroupList = new ArrayList();
			List<ScmInvCountingListMaterialGroup2> scmInvCountingListMaterialGroupList = scmInvCountingListMaterialGroupBiz.selectByTaskId(scmInvCountingTask.getTaskId(), param);
			if (scmInvCountingListMaterialGroupList != null && !scmInvCountingListMaterialGroupList.isEmpty() && scmInvCountingTask.isAssignItemGroup()) {
				for (ScmInvCountingListMaterialGroup2 scmInvCountingListMaterialGroup2 : scmInvCountingListMaterialGroupList) {
					scmMaterialGroupList.addAll(scmMaterialGroupBiz.findChild(scmInvCountingListMaterialGroup2.getClassId(), param)); 
				}
			}
			List<ScmInvCountingListMaterial2> scmInvCountingListMaterialList = scmInvCountingListMaterialBiz
					.selectByTaskId(scmInvCountingTask.getTaskId(), param);
			if (scmInvCountingTask.isAssignItem()) {
				if (scmInvCountingListMaterialList != null && !scmInvCountingListMaterialList.isEmpty()) {
					for (ScmInvCountingListMaterial2 scmInvCountingListMaterial : scmInvCountingListMaterialList) {
						if (scmInvCountingListMaterial.isSelectOrExclude()) {
							itemIdList.append(scmInvCountingListMaterial.getItemId()).append(",");
						}
					}
				}
			} else {
				List<ScmMaterial2> scmMaterialList = new ArrayList<>();
				if (StringUtils.isNotBlank(scmInvCountingTask.getFromItemNo())
						|| StringUtils.isNotBlank(scmInvCountingTask.getToItemNo())) {
					scmMaterialList = scmMaterialBiz.findCountingMaterial(scmInvCountingTask.getOrgUnitNo(),depts.toString(),
							scmInvCountingTask.getFromItemNo(), scmInvCountingTask.getToItemNo(), "TW", param);
				} else {
					scmMaterialList = scmMaterialBiz.findCountingMaterial(scmInvCountingTask.getOrgUnitNo(),depts.toString(), null,
							null, "TW", param);
				}
				if (scmMaterialList != null && !scmMaterialList.isEmpty()) {
					int i, j;
					for (i = 0; i < scmMaterialList.size(); i++) {
						boolean flag = false;
						boolean itemGroupFlag = false;
						if (scmInvCountingListMaterialList != null && !scmInvCountingListMaterialList.isEmpty()) {
							for (j = 0; j < scmInvCountingListMaterialList.size(); j++) {
								if (!scmInvCountingListMaterialList.get(j).isSelectOrExclude()
										&& scmInvCountingListMaterialList.get(j).getItemId() == scmMaterialList.get(i).getId()) {
									flag = true;
								}
							}
						}
						// 指定物资类别过滤
						if (scmInvCountingTask.isAssignItemGroup()) {
							if (scmInvCountingListMaterialGroupList != null && !scmInvCountingListMaterialGroupList.isEmpty()) {
								if (scmMaterialGroupList != null && !scmMaterialGroupList.isEmpty()) {
									for (ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList) {
										if (scmMaterialList.get(i).getGroupId() == scmMaterialGroup.getId()) {
											itemGroupFlag = true;
										}
									}
								}
							}
						}else {
							itemGroupFlag=true;
						}
						if (!flag && itemGroupFlag) {
							itemIdList.append(scmMaterialList.get(i).getId()).append(",");
						}
					}
				}
			}
			//生成盘存表  
			if (orgUnitNoList != null && !orgUnitNoList.isEmpty()) {
				for(String costOrgUnitNo : orgUnitNoList){
					ScmInvCountingCostCenter2 scmInvCountingCostCenter = new ScmInvCountingCostCenter2(true);
					scmInvCountingCostCenter.setTaskId(scmInvCountingTask.getTaskId());
					Page page = new Page();
					page.setModelClass(OrgCostCenter2.class);
					page.setShowCount(Integer.MAX_VALUE);
			        ArrayList argList = new ArrayList();
			        argList.add("type=to");
			        argList.add("relationType=" + OrgUnitRelationType.ADMINTOCOST);
			        argList.add("fromOrgUnitNo=" + costOrgUnitNo);
			        List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.queryPage(page, argList, "queryPageEx", param);
			        if(orgCostCenterList==null || orgCostCenterList.isEmpty()) {
			        	OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(costOrgUnitNo, param);
			        	throw new AppException(Message.getMessage(param.getLang(), "iscm.purchasemanage.purchasebusiness.orgUnitRelation.error.notcst",new String[]{orgBaseUnit==null?costOrgUnitNo:orgBaseUnit.getOrgUnitName()}));
			        }
					scmInvCountingCostCenter.setOrgUnitNo(orgCostCenterList.get(0).getOrgUnitNo());
					scmInvCountingCostCenter.setUseOrgUnitNo(costOrgUnitNo);
					scmInvCountingCostCenter.setBizDate(scmInvCountingTask.getBizDate());
					scmInvCountingCostCenter.setCountingPerson(param.getUsrCode());
					scmInvCountingCostCenter.setStatus("I");
					scmInvCountingCostCenter.setCreator(scmInvCountingTask.getCreator());
					scmInvCountingCostCenter.setCreateDate(scmInvCountingTask.getCreateDate());
					scmInvCountingCostCenterBiz.add(scmInvCountingCostCenter, param);
				}
			}
			//生成盘存明细表  
			if(StringUtils.isNotBlank(itemIdList.toString())){
				scmInvCountingCostCenterEntryBiz.addByItemIdList(scmInvCountingTask.getTaskId(),StringUtils.substring(itemIdList.toString(), 0, (itemIdList.toString().length())-1), scmInvCountingTask.isGenAccQty(), scmInvCountingTask.isGenTableForZero(), param);
			}
			//删除无结存的物资
			scmInvCountingCostCenterBiz.deleteNotExist(scmInvCountingTask.getTaskId(), param);
			//只有一个部门时更新盘存任务的制单组织
			List<ScmInvCountingCostCenter2> taskInvCountingCostCenterList = (List<ScmInvCountingCostCenter2>) scmInvCountingCostCenterBiz.selectByTaskNo(scmInvCountingTask.getTaskNo(),param.getControlUnitNo(), param);
			if(taskInvCountingCostCenterList!=null && !taskInvCountingCostCenterList.isEmpty() && taskInvCountingCostCenterList.size() == 1){
				scmInvCountingTask.setOrgUnitNo(taskInvCountingCostCenterList.get(0).getOrgUnitNo());
			}
			scmInvCountingTask.setStatus("T");
			this.update(scmInvCountingTask, param);
			return scmInvCountingTask;
		}
		return null;
	}
	
	@Override
	public List<String> countingFinish(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException {
		if(scmInvCountingTask !=null){
			List<String> msgList = new ArrayList<String>();// 提示列表
			if(!scmInvCountingTask.isCostCenter()){
				//盘点
				//检查账存
				int count = scmInvCountingTableEntryBiz.checkAccount(scmInvCountingTask.getTaskId(), param);
//				if(count > 0){
//					msgList.add(Message.getMessage(param.getLang(),
//							"iscm.inventorymanage.cstbusiness.ScmInvCountingTask.countingFinish.error"));
//					return msgList;
//				}
				//检查库存组织对应的财务组织是否有维护物资的财务资料
				List<OrgCompany2> orgCompanylist2 = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, scmInvCountingTask.getOrgUnitNo(), false, null, param);
				if(orgCompanylist2 == null || orgCompanylist2.isEmpty()){
					msgList.add(Message.getMessage(param.getLang(),
							"iscm.inventorymanage.cstbusiness.ScmInvCountingTask.countingFinish.error2"));
					return msgList;
				}
				List<ScmInvCountingTableEntry2> scmInvCountingTableEntryList = scmInvCountingTableEntryBiz.selectByTaskId(scmInvCountingTask.getTaskId(), param);
				if(scmInvCountingTableEntryList != null && !scmInvCountingTableEntryList.isEmpty()){
					StringBuffer itemIds = new StringBuffer("");
					for(ScmInvCountingTableEntry2 scmInvCountingTableEntry : scmInvCountingTableEntryList){
						if(!scmInvCountingTableEntry.isUsrAdd()){
							continue;
						}
						if(StringUtils.isNotBlank(itemIds.toString()))
							itemIds.append(",");
						itemIds.append(scmInvCountingTableEntry.getItemId());
					}
					if(StringUtils.isNotBlank(itemIds.toString())) {
						Page page = new Page();
						page.setModelClass(ScmMaterial2.class);
						page.setShowCount(Integer.MAX_VALUE);
						page.setSqlCondition("ScmMaterial.id in("+itemIds.toString()+")");
						List<String> arglist = new ArrayList<>();
						arglist.add("controlUnitNo="+param.getControlUnitNo());
						arglist.add("orgUnitNo="+orgCompanylist2.get(0).getOrgUnitNo());
						List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, arglist, "findByFinAllPage", param);
						if(scmMaterialList==null || scmMaterialList.isEmpty()){
							msgList.add(Message.getMessage(param.getLang(),"iscm.inventorymanage.ScmInvCountingTask.notfinancialdata"));
						}else {
							for(ScmInvCountingTableEntry2 scmInvCountingTableEntry : scmInvCountingTableEntryList){
								if(!scmInvCountingTableEntry.isUsrAdd()){
									continue;
								}
								String[] msparam = {orgCompanylist2.get(0).getOrgUnitName(),scmInvCountingTableEntry.getItemName()};
								boolean exists=false;
								for(ScmMaterial2 scmMaterial:scmMaterialList) {
									if(scmInvCountingTableEntry.getItemId()==scmMaterial.getId()) {
										exists = true;
										break;
									}
								}
								if(!exists)
									msgList.add(Message.getMessage(param.getLang(),
											"iscm.inventorymanage.otherinwarehsbill.post.error", msparam));
							}
						}
						if(msgList != null && !msgList.isEmpty()){
							return msgList;
						}
					}
				}
				
				//拆分
				splitCountingTask(scmInvCountingTask,param);
				scmInvCountingTableEntryList = scmInvCountingTableEntryBiz.selectForOtherInWh(scmInvCountingTask.getTaskId(), param);
				if(scmInvCountingTableEntryList != null && !scmInvCountingTableEntryList.isEmpty()){
					HashMap<Long,Object> inMap = new HashMap<>();
					//盘盈入库
					int i = 0;
					for(ScmInvCountingTableEntry2 scmInvCountingTableEntry : scmInvCountingTableEntryList){
						ScmInvOtherInWarehsBillEntry2 scmInvOtherInWarehsBillEntry = new ScmInvOtherInWarehsBillEntry2(true);
						if(inMap.containsKey(scmInvCountingTableEntry.getWareHouseId())){
							ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill = (ScmInvOtherInWarehsBill2) (inMap.get(scmInvCountingTableEntry.getWareHouseId()));
							//明细
							scmInvOtherInWarehsBillEntry.setWrId(scmInvOtherInWarehsBill.getWrId());
							i = i+1;
							scmInvOtherInWarehsBillEntry.setLineId(i);
							scmInvOtherInWarehsBillEntry.setItemId(scmInvCountingTableEntry.getItemId());
							scmInvOtherInWarehsBillEntry.setUnit(scmInvCountingTableEntry.getUnit());
							scmInvOtherInWarehsBillEntry.setPieUnit(scmInvCountingTableEntry.getPieUnit());
							scmInvOtherInWarehsBillEntry.setOrgUnitNo(scmInvCountingTableEntry.getOrgUnitNo());
							scmInvOtherInWarehsBillEntry.setWareHouseId(scmInvCountingTableEntry.getWareHouseId());
							scmInvOtherInWarehsBillEntry.setLot((StringUtils.isNotBlank(scmInvCountingTableEntry.getLot()) ? scmInvCountingTableEntry.getLot() : scmInvOtherInWarehsBill.getWrNo()));
							scmInvOtherInWarehsBillEntry.setQty(scmInvCountingTableEntry.getDifferQty());
							ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvOtherInWarehsBillEntry.getItemId(), param);
							if(scmMaterial==null){
								throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
							}
							scmInvOtherInWarehsBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
							BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvOtherInWarehsBillEntry.getItemId(), scmInvOtherInWarehsBillEntry.getUnit(), param);//库存单位转换系数
							scmInvOtherInWarehsBillEntry.setBaseQty(scmInvOtherInWarehsBillEntry.getQty().multiply(invConvRate));
							scmInvOtherInWarehsBillEntry.setPieQty(scmInvCountingTableEntry.getDifferPieQty());
							scmInvOtherInWarehsBillEntry.setPrice(scmInvCountingTableEntry.getPrice());
							scmInvOtherInWarehsBillEntry.setAmt(scmInvCountingTableEntry.getDifferAmt());
							scmInvOtherInWarehsBillEntry.setExpDate(scmInvCountingTableEntry.getExpDate());
							scmInvOtherInWarehsBillEntry.setOffset(false);
							scmInvOtherInWarehsBillEntry.setSourceBillDtlId(scmInvCountingTableEntry.getId());
							scmInvOtherInWarehsBillEntry.setTaxRate(scmInvCountingTableEntry.getTaxRate());
							scmInvOtherInWarehsBillEntry.setTaxPrice(scmInvCountingTableEntry.getTaxPrice());
							scmInvOtherInWarehsBillEntry.setTaxAmt(scmInvCountingTableEntry.getTaxAmt());
							scmInvOtherInWarehsBillEntry.setOrgSourceVendorId(scmInvCountingTableEntry.getVendorId());
							scmInvOtherInWarehsBillEntryBiz.add(scmInvOtherInWarehsBillEntry, param);
						}else{
							ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill = new ScmInvOtherInWarehsBill2(true);
							scmInvOtherInWarehsBill.setCreator(param.getUsrCode());
							scmInvOtherInWarehsBill.setCreateDate(CalendarUtil.getDate(param));
							scmInvOtherInWarehsBill.setWareHouseId(scmInvCountingTableEntry.getWareHouseId());
							scmInvOtherInWarehsBill.setWrNo((scmInvOtherInWarehsBillBiz.getBillNo(scmInvOtherInWarehsBill.getCreateDate(), param)));
							scmInvOtherInWarehsBill.setBizType("1");
							scmInvOtherInWarehsBill.setFinOrgUnitNo(orgCompanylist2.get(0).getOrgUnitNo());
							scmInvOtherInWarehsBill.setOrgUnitNo(scmInvCountingTask.getOrgUnitNo());
							scmInvOtherInWarehsBill.setBizDate(scmInvCountingTableEntry.getBizDate());
							scmInvOtherInWarehsBill.setOffset(false);
							scmInvOtherInWarehsBill.setBillType("CountTable");
							scmInvOtherInWarehsBill.setCurrencyNo(orgCompanylist2.get(0).getBaseCurrency());
							//获取期间
							PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(scmInvOtherInWarehsBill.getBizDate(), param);
							if(periodCalendar==null){
								throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
							}
							scmInvOtherInWarehsBill.setPeriodId(periodCalendar.getPeriodId());
							scmInvOtherInWarehsBill.setAccountYear(periodCalendar.getAccountYear());
							scmInvOtherInWarehsBill.setAccountPeriod(periodCalendar.getAccountPeriod());
							scmInvOtherInWarehsBill.setStatus("I");
							scmInvOtherInWarehsBill.setControlUnitNo(scmInvCountingTask.getControlUnitNo());
							scmInvOtherInWarehsBill.setCreateOrgUnitNo(scmInvCountingTask.getOrgUnitNo());
							scmInvOtherInWarehsBillBiz.add(scmInvOtherInWarehsBill, param);
							inMap.put(scmInvCountingTableEntry.getWareHouseId(), scmInvOtherInWarehsBill);
							//明细
							scmInvOtherInWarehsBillEntry.setWrId(scmInvOtherInWarehsBill.getWrId());
							i = 1;
							scmInvOtherInWarehsBillEntry.setLineId(i);
							scmInvOtherInWarehsBillEntry.setItemId(scmInvCountingTableEntry.getItemId());
							scmInvOtherInWarehsBillEntry.setUnit(scmInvCountingTableEntry.getUnit());
							scmInvOtherInWarehsBillEntry.setPieUnit(scmInvCountingTableEntry.getPieUnit());
							scmInvOtherInWarehsBillEntry.setOrgUnitNo(scmInvCountingTableEntry.getOrgUnitNo());
							scmInvOtherInWarehsBillEntry.setWareHouseId(scmInvCountingTableEntry.getWareHouseId());
							scmInvOtherInWarehsBillEntry.setLot((StringUtils.isNotBlank(scmInvCountingTableEntry.getLot()) ? scmInvCountingTableEntry.getLot() : scmInvOtherInWarehsBill.getWrNo()));
							scmInvOtherInWarehsBillEntry.setQty(scmInvCountingTableEntry.getDifferQty());
							ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvOtherInWarehsBillEntry.getItemId(), param);
							if(scmMaterial==null){
								throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
							}
							scmInvOtherInWarehsBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
							BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvOtherInWarehsBillEntry.getItemId(), scmInvOtherInWarehsBillEntry.getUnit(), param);//库存单位转换系数
							scmInvOtherInWarehsBillEntry.setBaseQty(scmInvOtherInWarehsBillEntry.getQty().multiply(invConvRate));
							scmInvOtherInWarehsBillEntry.setPieQty(scmInvCountingTableEntry.getDifferPieQty());
							scmInvOtherInWarehsBillEntry.setPrice(scmInvCountingTableEntry.getPrice());
							scmInvOtherInWarehsBillEntry.setAmt(scmInvCountingTableEntry.getDifferAmt());
							scmInvOtherInWarehsBillEntry.setExpDate(scmInvCountingTableEntry.getExpDate());
							scmInvOtherInWarehsBillEntry.setOffset(false);
							scmInvOtherInWarehsBillEntry.setSourceBillDtlId(scmInvCountingTableEntry.getId());
							scmInvOtherInWarehsBillEntry.setTaxRate(scmInvCountingTableEntry.getTaxRate());
							scmInvOtherInWarehsBillEntry.setTaxPrice(scmInvCountingTableEntry.getTaxPrice());
							scmInvOtherInWarehsBillEntry.setTaxAmt(scmInvCountingTableEntry.getTaxAmt());
							scmInvOtherInWarehsBillEntry.setOrgSourceVendorId(scmInvCountingTableEntry.getVendorId());
							scmInvOtherInWarehsBillEntryBiz.add(scmInvOtherInWarehsBillEntry, param);
						}
					}
					scmInvCountingTableEntryBiz.updateLot(scmInvCountingTask.getTaskId(),param);
				}
				scmInvCountingTableEntryList = scmInvCountingTableEntryBiz.selectForOtherOutWh(scmInvCountingTask.getTaskId(), param);
				if(scmInvCountingTableEntryList != null && !scmInvCountingTableEntryList.isEmpty()){
					HashMap<Long,Object> outMap = new HashMap<>();
					//盘亏出库
					int i = 0;
					for(ScmInvCountingTableEntry2 scmInvCountingTableEntry : scmInvCountingTableEntryList){
						ScmInvOtherIssueBillEntry2 scmInvOtherIssueBillEntry = new ScmInvOtherIssueBillEntry2(true);
						if(outMap.containsKey(scmInvCountingTableEntry.getWareHouseId())){
							ScmInvOtherIssueBill2 scmInvOtherIssueBill = (ScmInvOtherIssueBill2) (outMap.get(scmInvCountingTableEntry.getWareHouseId()));
							//明细
							scmInvOtherIssueBillEntry.setOtId(scmInvOtherIssueBill.getOtId());
							i = i+1;
							scmInvOtherIssueBillEntry.setLineId(i);
							scmInvOtherIssueBillEntry.setItemId(scmInvCountingTableEntry.getItemId());
							scmInvOtherIssueBillEntry.setUnit(scmInvCountingTableEntry.getUnit());
							scmInvOtherIssueBillEntry.setPieUnit(scmInvCountingTableEntry.getPieUnit());
							scmInvOtherIssueBillEntry.setOrgUnitNo(scmInvCountingTableEntry.getOrgUnitNo());
							scmInvOtherIssueBillEntry.setWareHouseId(scmInvCountingTableEntry.getWareHouseId());
							scmInvOtherIssueBillEntry.setLot((StringUtils.isNotBlank(scmInvCountingTableEntry.getLot()) ? scmInvCountingTableEntry.getLot() : scmInvOtherIssueBill.getOtNo()));
							scmInvOtherIssueBillEntry.setQty(scmInvCountingTableEntry.getDifferQty());
							ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvOtherIssueBillEntry.getItemId(), param);
							if(scmMaterial==null){
								throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
							}
							scmInvOtherIssueBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
							BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvOtherIssueBillEntry.getItemId(), scmInvOtherIssueBillEntry.getUnit(), param);//库存单位转换系数
							scmInvOtherIssueBillEntry.setBaseQty(scmInvOtherIssueBillEntry.getQty().multiply(invConvRate));
							scmInvOtherIssueBillEntry.setPieQty(scmInvCountingTableEntry.getDifferPieQty());
							scmInvOtherIssueBillEntry.setPrice(scmInvCountingTableEntry.getPrice());
							scmInvOtherIssueBillEntry.setAmt(scmInvCountingTableEntry.getDifferAmt());
							scmInvOtherIssueBillEntry.setExpDate(scmInvCountingTableEntry.getExpDate());
							scmInvOtherIssueBillEntry.setOffset(false);
							scmInvOtherIssueBillEntry.setSourceBillDtlId(scmInvCountingTableEntry.getTableId());
							scmInvOtherIssueBillEntry.setTaxRate(scmInvCountingTableEntry.getTaxRate());
							scmInvOtherIssueBillEntry.setTaxPrice(scmInvCountingTableEntry.getTaxPrice());
							scmInvOtherIssueBillEntry.setTaxAmt(scmInvCountingTableEntry.getTaxAmt());
							scmInvOtherIssueBillEntry.setStockId(scmInvCountingTableEntry.getStockId());
							scmInvOtherIssueBillEntryBiz.add(scmInvOtherIssueBillEntry, param);
						}else{
							ScmInvOtherIssueBill2 scmInvOtherIssueBill = new ScmInvOtherIssueBill2(true);
							scmInvOtherIssueBill.setCreator(param.getUsrCode());
							scmInvOtherIssueBill.setCreateDate(CalendarUtil.getDate(param));
							scmInvOtherIssueBill.setWareHouseId(scmInvCountingTableEntry.getWareHouseId());
							scmInvOtherIssueBill.setOtNo((scmInvOtherIssueBillBiz.getBillNo(scmInvOtherIssueBill.getCreateDate(), param)));
							scmInvOtherIssueBill.setBizType("1");
							scmInvOtherIssueBill.setFinOrgUnitNo(orgCompanylist2.get(0).getOrgUnitNo());
							scmInvOtherIssueBill.setOrgUnitNo(scmInvCountingTask.getOrgUnitNo());
							scmInvOtherIssueBill.setBizDate(scmInvCountingTableEntry.getBizDate());
							scmInvOtherIssueBill.setOffset(false);
							scmInvOtherIssueBill.setBillType("CountTable");
							scmInvOtherIssueBill.setCurrencyNo(orgCompanylist2.get(0).getBaseCurrency());
							//获取期间
							PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(scmInvOtherIssueBill.getBizDate(), param);
							if(periodCalendar==null){
								throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
							}
							scmInvOtherIssueBill.setPeriodId(periodCalendar.getPeriodId());
							scmInvOtherIssueBill.setAccountYear(periodCalendar.getAccountYear());
							scmInvOtherIssueBill.setAccountPeriod(periodCalendar.getAccountPeriod());
							scmInvOtherIssueBill.setStatus("I");
							scmInvOtherIssueBill.setControlUnitNo(scmInvCountingTask.getControlUnitNo());
							scmInvOtherIssueBill.setCreateOrgUnitNo(scmInvCountingTask.getOrgUnitNo());
							scmInvOtherIssueBillBiz.add(scmInvOtherIssueBill, param);
							outMap.put(scmInvCountingTableEntry.getWareHouseId(), scmInvOtherIssueBill);
							//明细
							scmInvOtherIssueBillEntry.setOtId(scmInvOtherIssueBill.getOtId());
							i = 1;
							scmInvOtherIssueBillEntry.setLineId(i);
							scmInvOtherIssueBillEntry.setItemId(scmInvCountingTableEntry.getItemId());
							scmInvOtherIssueBillEntry.setUnit(scmInvCountingTableEntry.getUnit());
							scmInvOtherIssueBillEntry.setPieUnit(scmInvCountingTableEntry.getPieUnit());
							scmInvOtherIssueBillEntry.setOrgUnitNo(scmInvCountingTableEntry.getOrgUnitNo());
							scmInvOtherIssueBillEntry.setWareHouseId(scmInvCountingTableEntry.getWareHouseId());
							scmInvOtherIssueBillEntry.setLot((StringUtils.isNotBlank(scmInvCountingTableEntry.getLot()) ? scmInvCountingTableEntry.getLot() : scmInvOtherIssueBill.getOtNo()));
							scmInvOtherIssueBillEntry.setQty(scmInvCountingTableEntry.getDifferQty());
							ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvOtherIssueBillEntry.getItemId(), param);
							if(scmMaterial==null){
								throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
							}
							scmInvOtherIssueBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
							BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvOtherIssueBillEntry.getItemId(), scmInvOtherIssueBillEntry.getUnit(), param);//库存单位转换系数
							scmInvOtherIssueBillEntry.setBaseQty(scmInvOtherIssueBillEntry.getQty().multiply(invConvRate));
							scmInvOtherIssueBillEntry.setPieQty(scmInvCountingTableEntry.getDifferPieQty());
							scmInvOtherIssueBillEntry.setPrice(scmInvCountingTableEntry.getPrice());
							scmInvOtherIssueBillEntry.setAmt(scmInvCountingTableEntry.getDifferAmt());
							scmInvOtherIssueBillEntry.setExpDate(scmInvCountingTableEntry.getExpDate());
							scmInvOtherIssueBillEntry.setOffset(false);
							scmInvOtherIssueBillEntry.setSourceBillDtlId(scmInvCountingTableEntry.getTableId());
							scmInvOtherIssueBillEntry.setTaxRate(scmInvCountingTableEntry.getTaxRate());
							scmInvOtherIssueBillEntry.setTaxPrice(scmInvCountingTableEntry.getTaxPrice());
							scmInvOtherIssueBillEntry.setTaxAmt(scmInvCountingTableEntry.getTaxAmt());
							scmInvOtherIssueBillEntry.setStockId(scmInvCountingTableEntry.getStockId());
							scmInvOtherIssueBillEntryBiz.add(scmInvOtherIssueBillEntry, param);
						}
					}
				}
			}else{
				//是否开启闲置管理
				String enableIdleItems = sysParamBiz.getValue(param.getControlUnitNo(), "SCM_EnableIdleItems", "N", param);
				//盘存
				//检查账存
				int count = scmInvCountingCostCenterEntryBiz.checkAccount(scmInvCountingTask.getTaskId(), param);
//				if(count > 0){
//					msgList.add(Message.getMessage(param.getLang(),
//							"iscm.inventorymanage.cstbusiness.ScmInvCountingTask.countingFinish.error"));
//					return msgList;
//				}
				//检查成本中心对应的财务组织是否有维护物资的财务资料
				List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList = scmInvCountingCostCenterEntryBiz.selectByTaskId(scmInvCountingTask.getTaskId(), param);
				if(scmInvCountingCostCenterEntryList != null && !scmInvCountingCostCenterEntryList.isEmpty()){
					HashMap<String,Object> cacheMap = new HashMap<String,Object>();
					for(ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry : scmInvCountingCostCenterEntryList){
						if(!scmInvCountingCostCenterEntry.isUsrAdd()){
							continue;
						}
						String orgunitName ="";
						OrgCompany2 orgCompany = null;
						String orgUnitNo = scmInvCountingCostCenterEntry.getOrgUnitNo();
						if (StringUtils.isNotBlank(orgUnitNo)){
							//组织
							OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+orgUnitNo);
							if(orgBaseUnit==null){
								orgBaseUnit = orgUnitBiz.selectbyOrgNo(orgUnitNo, param);
								cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+orgUnitNo,orgBaseUnit);
							}
							if (orgBaseUnit != null) {
								orgunitName = orgBaseUnit.getOrgUnitName();
							}
							orgCompany = (OrgCompany2) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgCompany2.class)+"_"+orgUnitNo);
							if(orgCompany==null && !cacheMap.containsKey(ClassUtils.getFinalModelSimpleName(OrgCompany2.class)+"_"+orgUnitNo)){
								List<OrgCompany2> orgCompanylist = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.COSTTOFIN, orgUnitNo, false, null, param);
								if(orgCompanylist != null && !orgCompanylist.isEmpty()){
									orgCompany = orgCompanylist.get(0);
									cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgCompany2.class)+"_"+orgUnitNo,orgCompany);
								}
							}
						}
						String[] msparam = {orgunitName,scmInvCountingCostCenterEntry.getItemName()};
						if(orgCompany==null){
							msgList.add(Message.getMessage(param.getLang(),
									"iscm.inventorymanage.otherinwarehsbill.post.error", msparam));
							continue;
						}else{
							Page page = new Page();
							page.setModelClass(ScmMaterial2.class);
							page.setShowCount(Integer.MAX_VALUE);
							page.setSqlCondition("ScmMaterial.id="+scmInvCountingCostCenterEntry.getItemId());
							List<String> arglist = new ArrayList<>();
							arglist.add("controlUnitNo="+param.getControlUnitNo());
							arglist.add("orgUnitNo="+orgCompany.getOrgUnitNo());
							List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, arglist, "findByFinAllPage", param);
							if(scmMaterialList==null || scmMaterialList.isEmpty()){
								msgList.add(Message.getMessage(param.getLang(),
										"iscm.inventorymanage.otherinwarehsbill.post.error", msparam));
								continue;
							}
						}
					}
					if(msgList != null && !msgList.isEmpty()){
						return msgList;
					}
				}
				//拆分
				splitCountingTask(scmInvCountingTask,param);
				count = scmInvCountingCostCenterEntryBiz.selectByTaskIdAndFinOrg(scmInvCountingTask.getTaskId(), scmInvCountingTask.getFinOrgUnitNo(), param);
				if(count > 0){
					int count1 = scmInvStockBiz.checkCostCenter(scmInvCountingTask.getTaskId(), scmInvCountingTask.getFinOrgUnitNo(), param);
					if(count1 > 0){
						throw new AppException(Message.getMessage(param.getLang(),"iscm.inventorymanage.cstbusiness.ScmInvCountingTask.countingFinish.error4"));
					}
					int count2 = scmInvStockBiz.checkCostCenter2(scmInvCountingTask.getTaskId(), scmInvCountingTask.getFinOrgUnitNo(), param);
					if(count2 > 0){
						throw new AppException(Message.getMessage(param.getLang(),
								"iscm.inventorymanage.cstbusiness.ScmInvCountingTask.countingFinish.error5"));
					}
					//结存
					scmInvStockBiz.updateByCostCenter(scmInvCountingTask.getTaskId(), scmInvCountingTask.getFinOrgUnitNo(), param);
					scmInvStockBiz.addByCostCenter(scmInvCountingTask.getTaskId(), scmInvCountingTask.getFinOrgUnitNo(), param);
					//期间余额
					//获取期间	
					PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(scmInvCountingTask.getBizDate(), param);
					if(periodCalendar==null){
						throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
					}
					scmInvBalBiz.updateByCostCenter(scmInvCountingTask.getTaskId(), scmInvCountingTask.getFinOrgUnitNo(), periodCalendar.getAccountYear(), periodCalendar.getAccountPeriod(), param);
					scmInvBalBiz.addByCostCenter(scmInvCountingTask.getTaskId(), scmInvCountingTask.getFinOrgUnitNo(), 
					        periodCalendar.getPeriodId(), periodCalendar.getAccountYear(), periodCalendar.getAccountPeriod(), param);
//					scmInvCostBiz.updateByCostCenter(scmInvCountingTask.getTaskId(), scmInvCountingTask.getFinOrgUnitNo(), param);
//					scmInvCostBiz.addByCostCenter(scmInvCountingTask.getTaskId(), scmInvCountingTask.getFinOrgUnitNo(), param);
				}
				if (StringUtils.equals("Y", enableIdleItems)) {
					scmInvCountingCostCenterEntryList = scmInvCountingCostCenterEntryBiz.selectByTaskId(scmInvCountingTask.getTaskId(), param);
					//添加标识为闲置的明细数据
					this.addIdleItems(scmInvCountingTask,scmInvCountingCostCenterEntryList,param);
				}
			}
			scmInvCountingTask.setStatus("O");
			scmInvCountingTask.setCountingEndTime(CalendarUtil.getDate(param));
			int updateRow = this.updateFinishStatus(scmInvCountingTask, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvCountingTask.finish.error", new String[] {scmInvCountingTask.getTaskNo()}));
			}
		}
		return null;
	}
	
	private void addIdleItems(ScmInvCountingTask2 scmInvCountingTask,List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList, Param param) {
		ArrayList<ScmIdleItems> addIdleItems = new ArrayList<>();
		ArrayList<ScmIdleItems> updateIdleItems = new ArrayList<>();
		if (scmInvCountingCostCenterEntryList != null && !scmInvCountingCostCenterEntryList.isEmpty()) {
			//根据盘存部门分组
			Map<String, List<ScmInvCountingCostCenterEntry2>> scmInvCountingCostCenterEntryGroupList = scmInvCountingCostCenterEntryList.stream().collect(Collectors.groupingBy(ScmInvCountingCostCenterEntry2::getUseOrgUnitNo));
			for (Entry<String, List<ScmInvCountingCostCenterEntry2>> mapKey : scmInvCountingCostCenterEntryGroupList.entrySet()) {
				List<ScmInvCountingCostCenterEntry2> list = scmInvCountingCostCenterEntryGroupList.get(mapKey.getKey());
				ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry2 = list.get(0);
				List<ScmIdleItems> scmIdleItemList = scmIdleItemsBiz.selectByCostCenterOrg(scmInvCountingCostCenterEntry2.getOrgUnitNo(),scmInvCountingCostCenterEntry2.getUseOrgUnitNo(),param);
				HashMap<String,OrgCompany2> companyMap = new HashMap<String,OrgCompany2>();
				for (ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry : list) {
					if (!(scmInvCountingCostCenterEntry.getQty().compareTo(BigDecimal.ZERO)>0)) {
						continue;
					}
					//判断物资是否手工取消闲置
					if (!scmInvCountingCostCenterEntry.isIdle() && scmIdleItemList != null && !scmIdleItemList.isEmpty()) {
						if (!scmIdleItemList.stream().filter(ScmIdleItems -> ScmIdleItems.getItemId() == scmInvCountingCostCenterEntry.getItemId()).findAny().isPresent()) {
							continue;
						}
					}
					boolean flag = true;
					if (scmIdleItemList != null && !scmIdleItemList.isEmpty()) {
						for (ScmIdleItems scmIdleItems : scmIdleItemList) {
							if (scmInvCountingCostCenterEntry.getItemId()== scmIdleItems.getItemId() && StringUtils.equals(scmIdleItems.getLot(),scmInvCountingCostCenterEntry.getLot())) {
								scmIdleItems.setTableId(scmInvCountingCostCenterEntry.getTableId());
								scmIdleItems.setIdleCauseId(scmInvCountingCostCenterEntry.getIdleCauseId());
								scmIdleItems.setEditDate(new Date());
								scmIdleItems.setEditor(param.getUsrCode());
								flag=false;
								if (scmInvCountingCostCenterEntry.isIdle()) {
									scmIdleItems.setIdleStatus(true);
									scmIdleItems.setNewIdle(true);
								}else {
									scmIdleItems.setIdleStatus(false);
									scmIdleItems.setNewIdle(false);
									scmIdleItems.setIdleBillType("CountCostCenter");
									scmIdleItems.setIdleBillId(scmInvCountingTask.getTaskId());
								}
								updateIdleItems.add(scmIdleItems);
								break;
							}
						}
					}
					if (flag && scmInvCountingCostCenterEntry.isIdle()) {
						ScmIdleItems scmIdleItems = new ScmIdleItems(true);
						scmIdleItems.setTableId(scmInvCountingCostCenterEntry.getTableId());
						scmIdleItems.setBizDate(scmInvCountingCostCenterEntry.getBizDate());
						OrgCompany2 orgCompany = companyMap.get(scmInvCountingCostCenterEntry.getOrgUnitNo());
						if(orgCompany!=null) {
							scmIdleItems.setFinOrgUnitNo(orgCompany.getOrgUnitNo());
						}else {
							Page orgCompanyPage = new Page();
							orgCompanyPage.setModelClass(OrgCompany2.class);
							orgCompanyPage.setShowCount(Integer.MAX_VALUE);
							List<String> orgCompanyArglist = new ArrayList<>();
							orgCompanyArglist.add("type=to");
							orgCompanyArglist.add("relationType="+OrgUnitRelationType.COSTTOFIN);
							orgCompanyArglist.add("fromOrgUnitNo="+scmInvCountingCostCenterEntry.getOrgUnitNo());
							List<OrgCompany2> orgCompanyList = orgCompanyBiz.queryPage(orgCompanyPage, orgCompanyArglist, "queryPageEx", param);
							if(orgCompanyList!=null && !orgCompanyList.isEmpty()) {
								companyMap.put(scmInvCountingCostCenterEntry.getOrgUnitNo(), orgCompanyList.get(0));
								scmIdleItems.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
							}
						}
						scmIdleItems.setOrgUnitNo(scmInvCountingCostCenterEntry.getOrgUnitNo());
						scmIdleItems.setUseOrgUnitNo(scmInvCountingCostCenterEntry.getUseOrgUnitNo());
						scmIdleItems.setItemId(scmInvCountingCostCenterEntry.getItemId());
						scmIdleItems.setLot(scmInvCountingCostCenterEntry.getLot());
						scmIdleItems.setControlUnitNo(param.getControlUnitNo());
						scmIdleItems.setCreateDate(new Date());
						scmIdleItems.setCreator(param.getUsrCode());
						scmIdleItems.setIdleCauseId(scmInvCountingCostCenterEntry.getIdleCauseId());
						scmIdleItems.setIdleStatus(true);
						scmIdleItems.setNewIdle(true);
						scmIdleItems.setUnit(scmInvCountingCostCenterEntry.getUnit());
						addIdleItems.add(scmIdleItems);
					}
				}
			}
		}
		if (addIdleItems != null && !addIdleItems.isEmpty()) {
			scmIdleItemsBiz.add(addIdleItems, param);
		}
		if (updateIdleItems != null && !updateIdleItems.isEmpty()) {
			scmIdleItemsBiz.update(updateIdleItems, param);
		}
	}

	private int updateFinishStatus(ScmInvCountingTask2 scmInvCountingTask, Param param) {
		String needConfirm = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_CountingTaskNeedConfirm", "N", param);
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("taskId",scmInvCountingTask.getTaskId());
		map.put("countingEndTime",FormatUtils.fmtDateTime(scmInvCountingTask.getCountingEndTime()));
		map.put("status", scmInvCountingTask.getStatus());
		map.put("needConfirm",needConfirm);
		return ((ScmInvCountingTaskDAO)dao).updateFinishStatus(map);
	}

	private void splitCountingTask(ScmInvCountingTask2 scmInvCountingTask, Param param){
		int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
		if(scmInvCountingTask != null && !scmInvCountingTask.isCostCenter()){
			List<OrgCompany2> orgCompanylist2 = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, scmInvCountingTask.getOrgUnitNo(), false, null, param);
			if(orgCompanylist2 != null && !orgCompanylist2.isEmpty()){
				List<ScmInvCountingTableEntry2> scmInvCountingTableEntryList = scmInvCountingTableEntryBiz.selectByTaskId(scmInvCountingTask.getTaskId(), param);
				if(scmInvCountingTableEntryList != null && !scmInvCountingTableEntryList.isEmpty()){
					for(int i=scmInvCountingTableEntryList.size()-1;i>=0;i--){
						ScmInvCountingTableEntry2 scmInvCountingTableEntry = scmInvCountingTableEntryList.get(i);
						if(scmInvCountingTableEntry.isUsrAdd() || StringUtils.isNotBlank(scmInvCountingTableEntry.getLot())){
							continue;
						}
						BigDecimal differQty = scmInvCountingTableEntry.getDifferQty();
                    	HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("orgUnitNo", scmInvCountingTableEntry.getOrgUnitNo());
                        map.put("itemId", scmInvCountingTableEntry.getItemId());
                        map.put("wareHouseId", scmInvCountingTableEntry.getWareHouseId());
                        map.put("costMethod", scmInvCountingTableEntry.getCosting());
                        map.put("unit", scmInvCountingTableEntry.getUnit());
                        List<ScmInvStock2> stocklist = scmInvStockBiz.findByWareHouse(map, param);
                        if(stocklist != null && !stocklist.isEmpty()){
                        	for (ScmInvStock2 scmInvStock : stocklist) {
                        		BigDecimal stockQty = scmInvStock.getQty();
                        		ScmInvCountingTableEntry2 scmInvCountingTableEntry2 = new ScmInvCountingTableEntry2(true);
                        		BeanUtils.copyProperties(scmInvCountingTableEntry, scmInvCountingTableEntry2);
                        		scmInvCountingTableEntry2.setId(0);
                        		if(BigDecimal.ZERO.compareTo(differQty) < 0){
                        			//盘盈
                        			if(BigDecimal.ZERO.compareTo(stockQty) > 0){
                        				scmInvCountingTableEntry2.setLot(scmInvStock.getLot());
                        				scmInvCountingTableEntry2.setAccountQty(scmInvStock.getQty());
                        				scmInvCountingTableEntry2.setQty(BigDecimal.ZERO);
                        				scmInvCountingTableEntry2.setPrice(scmInvStock.getPrice());
                        				scmInvCountingTableEntry2.setTaxPrice(scmInvStock.getTaxPrice());
                        				scmInvCountingTableEntry2.setTaxRate(scmInvStock.getTaxRate());
                        				scmInvCountingTableEntry2.setAmt(BigDecimal.ZERO);
                        				scmInvCountingTableEntry2.setTaxAmt(BigDecimal.ZERO);
                        				scmInvCountingTableEntry2.setDifferQty((scmInvStock.getQty()).negate());
                        				scmInvCountingTableEntry2.setDifferAmt((scmInvStock.getAmt()).negate());
                        				scmInvCountingTableEntry2.setDifferTaxAmt((scmInvStock.getTaxAmt()).negate());
                        				scmInvCountingTableEntry2.setAccountAmt(scmInvStock.getAmt());
                        				scmInvCountingTableEntry2.setAccountTaxAmt(scmInvStock.getTaxAmt());
                        				scmInvCountingTableEntry2.setStockId(scmInvStock.getId());
                        				scmInvCountingTableEntry2.setExpDate(scmInvStock.getExpDate());
                        				scmInvCountingTableEntry2.setVendorId(scmInvStock.getVendorId());
                        				scmInvCountingTableEntryBiz.add(scmInvCountingTableEntry2, param);
                        				differQty = differQty.add(stockQty);
                        				continue;
                        			}else{
                        				scmInvCountingTableEntry2.setLot(scmInvStock.getLot());
                        				scmInvCountingTableEntry2.setAccountQty(scmInvStock.getQty());
                        				scmInvCountingTableEntry2.setQty((scmInvStock.getQty().add(differQty)));
                        				scmInvCountingTableEntry2.setPrice(scmInvStock.getPrice());
                        				scmInvCountingTableEntry2.setTaxPrice(scmInvStock.getTaxPrice());
                        				scmInvCountingTableEntry2.setTaxRate(scmInvStock.getTaxRate());
                        				scmInvCountingTableEntry2.setAmt((scmInvStock.getAmt().add(differQty.multiply(scmInvStock.getPrice()))).setScale(amtPrecision, RoundingMode.HALF_UP));
                        				scmInvCountingTableEntry2.setTaxAmt((scmInvStock.getTaxAmt().add(differQty.multiply(scmInvStock.getTaxPrice()))).setScale(amtPrecision, RoundingMode.HALF_UP));
                        				scmInvCountingTableEntry2.setDifferQty(differQty);
                        				scmInvCountingTableEntry2.setDifferAmt((differQty.multiply(scmInvStock.getPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
                        				scmInvCountingTableEntry2.setDifferTaxAmt((differQty.multiply(scmInvStock.getTaxPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
                        				scmInvCountingTableEntry2.setAccountAmt(scmInvStock.getAmt());
                        				scmInvCountingTableEntry2.setAccountTaxAmt(scmInvStock.getTaxAmt());
                        				scmInvCountingTableEntry2.setStockId(scmInvStock.getId());
                        				scmInvCountingTableEntry2.setExpDate(scmInvStock.getExpDate());
                        				scmInvCountingTableEntry2.setVendorId(scmInvStock.getVendorId());
                        				scmInvCountingTableEntryBiz.add(scmInvCountingTableEntry2, param);
                        				differQty = BigDecimal.ZERO;
                        				continue;
                        			}
                        		}else if(BigDecimal.ZERO.compareTo(differQty) > 0){
                        			//盘亏
                        			if(BigDecimal.ZERO.compareTo(stockQty) > 0){
                        				scmInvCountingTableEntry2.setLot(scmInvStock.getLot());
                        				scmInvCountingTableEntry2.setAccountQty(scmInvStock.getQty());
                        				scmInvCountingTableEntry2.setQty(BigDecimal.ZERO);
                        				scmInvCountingTableEntry2.setPrice(scmInvStock.getPrice());
                        				scmInvCountingTableEntry2.setTaxPrice(scmInvStock.getTaxPrice());
                        				scmInvCountingTableEntry2.setTaxRate(scmInvStock.getTaxRate());
                        				scmInvCountingTableEntry2.setAmt(BigDecimal.ZERO);
                        				scmInvCountingTableEntry2.setTaxAmt(BigDecimal.ZERO);
                        				scmInvCountingTableEntry2.setDifferQty((scmInvStock.getQty()).negate());
                        				scmInvCountingTableEntry2.setDifferAmt((scmInvStock.getAmt()).negate());
                        				scmInvCountingTableEntry2.setDifferTaxAmt((scmInvStock.getTaxAmt()).negate());
                        				scmInvCountingTableEntry2.setAccountAmt(scmInvStock.getAmt());
                        				scmInvCountingTableEntry2.setAccountTaxAmt(scmInvStock.getTaxAmt());
                        				scmInvCountingTableEntry2.setStockId(scmInvStock.getId());
                        				scmInvCountingTableEntry2.setExpDate(scmInvStock.getExpDate());
                        				scmInvCountingTableEntry2.setVendorId(scmInvStock.getVendorId());
                        				scmInvCountingTableEntryBiz.add(scmInvCountingTableEntry2, param);
                        				differQty = differQty.add(stockQty);
                        				continue;
                        			}else{
                        				if(stockQty.compareTo(differQty.abs()) >= 0){
                        					//此批次够亏损
                        					scmInvCountingTableEntry2.setLot(scmInvStock.getLot());
                            				scmInvCountingTableEntry2.setAccountQty(scmInvStock.getQty());
                            				scmInvCountingTableEntry2.setQty((scmInvStock.getQty().add(differQty)));
                            				scmInvCountingTableEntry2.setPrice(scmInvStock.getPrice());
                            				scmInvCountingTableEntry2.setTaxPrice(scmInvStock.getTaxPrice());
                            				scmInvCountingTableEntry2.setTaxRate(scmInvStock.getTaxRate());
                            				if(stockQty.compareTo(differQty.abs()) == 0){
                            					scmInvCountingTableEntry2.setAmt(BigDecimal.ZERO);
                                				scmInvCountingTableEntry2.setTaxAmt(BigDecimal.ZERO);
                                				scmInvCountingTableEntry2.setDifferAmt(scmInvStock.getAmt().negate());
                                				scmInvCountingTableEntry2.setDifferTaxAmt(scmInvStock.getTaxAmt().negate());
                            				}else{
                            					scmInvCountingTableEntry2.setAmt((scmInvStock.getAmt().add(differQty.multiply(scmInvStock.getPrice()))).setScale(amtPrecision, RoundingMode.HALF_UP));
                                				scmInvCountingTableEntry2.setTaxAmt((scmInvStock.getTaxAmt().add(differQty.multiply(scmInvStock.getTaxPrice()))).setScale(amtPrecision, RoundingMode.HALF_UP));
                                				scmInvCountingTableEntry2.setDifferAmt((differQty.multiply(scmInvStock.getPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
                                				scmInvCountingTableEntry2.setDifferTaxAmt((differQty.multiply(scmInvStock.getTaxPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
                            				}
                            				scmInvCountingTableEntry2.setDifferQty(differQty);
                            				scmInvCountingTableEntry2.setAccountAmt(scmInvStock.getAmt());
                            				scmInvCountingTableEntry2.setAccountTaxAmt(scmInvStock.getTaxAmt());
                            				scmInvCountingTableEntry2.setStockId(scmInvStock.getId());
                            				scmInvCountingTableEntry2.setExpDate(scmInvStock.getExpDate());
                            				scmInvCountingTableEntry2.setVendorId(scmInvStock.getVendorId());
                            				scmInvCountingTableEntryBiz.add(scmInvCountingTableEntry2, param);
                            				differQty = BigDecimal.ZERO;
                            				continue;
                        				}else{
                        					//此批次不足亏损
                        					scmInvCountingTableEntry2.setLot(scmInvStock.getLot());
                            				scmInvCountingTableEntry2.setAccountQty(scmInvStock.getQty());
                            				scmInvCountingTableEntry2.setQty(BigDecimal.ZERO);
                            				scmInvCountingTableEntry2.setPrice(scmInvStock.getPrice());
                            				scmInvCountingTableEntry2.setTaxPrice(scmInvStock.getTaxPrice());
                            				scmInvCountingTableEntry2.setTaxRate(scmInvStock.getTaxRate());
                            				scmInvCountingTableEntry2.setAmt(BigDecimal.ZERO);
                            				scmInvCountingTableEntry2.setTaxAmt(BigDecimal.ZERO);
                            				scmInvCountingTableEntry2.setDifferQty((scmInvStock.getQty()).negate());
                            				scmInvCountingTableEntry2.setDifferAmt((scmInvStock.getAmt()).negate());
                            				scmInvCountingTableEntry2.setDifferTaxAmt((scmInvStock.getTaxAmt()).negate());
                            				scmInvCountingTableEntry2.setAccountAmt(scmInvStock.getAmt());
                            				scmInvCountingTableEntry2.setAccountTaxAmt(scmInvStock.getTaxAmt());
                            				scmInvCountingTableEntry2.setStockId(scmInvStock.getId());
                            				scmInvCountingTableEntry2.setExpDate(scmInvStock.getExpDate());
                            				scmInvCountingTableEntry2.setVendorId(scmInvStock.getVendorId());
                            				scmInvCountingTableEntryBiz.add(scmInvCountingTableEntry2, param);
                            				differQty = differQty.add(stockQty);
                            				continue;
                        				}
                        			}
                        		}else if(BigDecimal.ZERO.compareTo(differQty) == 0){
                        			//无盈亏
                        			if(BigDecimal.ZERO.compareTo(stockQty) > 0){
                        				scmInvCountingTableEntry2.setLot(scmInvStock.getLot());
                        				scmInvCountingTableEntry2.setAccountQty(scmInvStock.getQty());
                        				scmInvCountingTableEntry2.setQty(BigDecimal.ZERO);
                        				scmInvCountingTableEntry2.setPrice(scmInvStock.getPrice());
                        				scmInvCountingTableEntry2.setTaxPrice(scmInvStock.getTaxPrice());
                        				scmInvCountingTableEntry2.setTaxRate(scmInvStock.getTaxRate());
                        				scmInvCountingTableEntry2.setAmt(BigDecimal.ZERO);
                        				scmInvCountingTableEntry2.setTaxAmt(BigDecimal.ZERO);
                        				scmInvCountingTableEntry2.setDifferQty((scmInvStock.getQty()).negate());
                        				scmInvCountingTableEntry2.setDifferAmt((scmInvStock.getAmt()).negate());
                        				scmInvCountingTableEntry2.setDifferTaxAmt((scmInvStock.getTaxAmt()).negate());
                        				scmInvCountingTableEntry2.setAccountAmt(scmInvStock.getAmt());
                        				scmInvCountingTableEntry2.setAccountTaxAmt(scmInvStock.getTaxAmt());
                        				scmInvCountingTableEntry2.setStockId(scmInvStock.getId());
                        				scmInvCountingTableEntry2.setExpDate(scmInvStock.getExpDate());
                        				scmInvCountingTableEntry2.setVendorId(scmInvStock.getVendorId());
                        				scmInvCountingTableEntryBiz.add(scmInvCountingTableEntry2, param);
                        				differQty = differQty.add(stockQty);
                        				continue;
                        			}else{
                        				scmInvCountingTableEntry2.setLot(scmInvStock.getLot());
                        				scmInvCountingTableEntry2.setAccountQty(scmInvStock.getQty());
                        				scmInvCountingTableEntry2.setQty(scmInvStock.getQty());
                        				scmInvCountingTableEntry2.setPrice(scmInvStock.getPrice());
                        				scmInvCountingTableEntry2.setTaxPrice(scmInvStock.getTaxPrice());
                        				scmInvCountingTableEntry2.setTaxRate(scmInvStock.getTaxRate());
                        				scmInvCountingTableEntry2.setAmt(scmInvStock.getAmt());
                        				scmInvCountingTableEntry2.setTaxAmt(scmInvStock.getTaxAmt());
                        				scmInvCountingTableEntry2.setDifferQty(BigDecimal.ZERO);
                        				scmInvCountingTableEntry2.setDifferAmt(BigDecimal.ZERO);
                        				scmInvCountingTableEntry2.setDifferTaxAmt(BigDecimal.ZERO);
                        				scmInvCountingTableEntry2.setAccountAmt(scmInvStock.getAmt());
                        				scmInvCountingTableEntry2.setAccountTaxAmt(scmInvStock.getTaxAmt());
                        				scmInvCountingTableEntry2.setStockId(scmInvStock.getId());
                        				scmInvCountingTableEntry2.setExpDate(scmInvStock.getExpDate());
                        				scmInvCountingTableEntry2.setVendorId(scmInvStock.getVendorId());
                        				scmInvCountingTableEntryBiz.add(scmInvCountingTableEntry2, param);
                        				differQty = BigDecimal.ZERO;
                        				continue;
                        			}
                        		}
                        	}
                        }
                        scmInvCountingTableEntryBiz.delete(scmInvCountingTableEntry, param);
					}
				}
			}
		}else if(scmInvCountingTask != null && scmInvCountingTask.isCostCenter()){
			List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList = scmInvCountingCostCenterEntryBiz.selectByTaskId(scmInvCountingTask.getTaskId(), param);
			if(scmInvCountingCostCenterEntryList != null && !scmInvCountingCostCenterEntryList.isEmpty()){
				HashMap<String, List<ScmInvStock2>> stockMap = new HashMap<String, List<ScmInvStock2>>();
				for(int i=scmInvCountingCostCenterEntryList.size()-1;i>=0;i--){
					ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry = scmInvCountingCostCenterEntryList.get(i);
					if(scmInvCountingCostCenterEntry.isUsrAdd() || StringUtils.isNotBlank(scmInvCountingCostCenterEntry.getLot())){
						continue;
					}
					//查询结存
					List<ScmInvStock2> stocklist = null;
					if(!stockMap.containsKey(scmInvCountingCostCenterEntry.getOrgUnitNo()+"_"+scmInvCountingCostCenterEntry.getUseOrgUnitNo())){
						stockMap.put(
								scmInvCountingCostCenterEntry.getOrgUnitNo() + "_"
										+ scmInvCountingCostCenterEntry.getUseOrgUnitNo(),
								scmInvStockBiz.findByUseOrgCounting(scmInvCountingCostCenterEntry.getOrgUnitNo(),
										scmInvCountingCostCenterEntry.getUseOrgUnitNo(), param));
					}
					stocklist = stockMap.get(scmInvCountingCostCenterEntry.getOrgUnitNo()+"_"+scmInvCountingCostCenterEntry.getUseOrgUnitNo());
					BigDecimal differQty = scmInvCountingCostCenterEntry.getDifferQty();
					BigDecimal productAddQty = scmInvCountingCostCenterEntry.getProductAddQty();
                    if(stocklist != null && !stocklist.isEmpty()){
                     	for (ScmInvStock2 scmInvStock : stocklist) {
                     		if(scmInvCountingCostCenterEntry.getItemId()==scmInvStock.getItemId()) {
	                    		BigDecimal stockQty = scmInvStock.getQty();
	                    		ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry2 = new ScmInvCountingCostCenterEntry2(true);
	                    		BeanUtils.copyProperties(scmInvCountingCostCenterEntry, scmInvCountingCostCenterEntry2);
	                    		scmInvCountingCostCenterEntry2.setId(0);
	                    		if(BigDecimal.ZERO.compareTo(differQty) < 0){
	                    			//盘盈
	                    			scmInvCountingCostCenterEntry2.setLot(scmInvStock.getLot());
	                				scmInvCountingCostCenterEntry2.setAccountQty(scmInvStock.getQty());
	                				scmInvCountingCostCenterEntry2.setQty((scmInvStock.getQty().add(differQty)).subtract(productAddQty));
	                				scmInvCountingCostCenterEntry2.setPrice(scmInvStock.getPrice());
	                				scmInvCountingCostCenterEntry2.setTaxPrice(scmInvStock.getTaxPrice());
	                				scmInvCountingCostCenterEntry2.setTaxRate(scmInvStock.getTaxRate());
	                				scmInvCountingCostCenterEntry2.setProductAddAmt(productAddQty.multiply(scmInvStock.getPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
	                				scmInvCountingCostCenterEntry2.setProductAddTaxAmt(productAddQty.multiply(scmInvStock.getTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
	                				scmInvCountingCostCenterEntry2.setAmt((scmInvStock.getAmt().add(differQty.multiply(scmInvStock.getPrice()).subtract(scmInvCountingCostCenterEntry2.getProductAddAmt())).setScale(amtPrecision, RoundingMode.HALF_UP)));
	                				scmInvCountingCostCenterEntry2.setTaxAmt((scmInvStock.getTaxAmt().add(differQty.multiply(scmInvStock.getTaxPrice()).subtract(scmInvCountingCostCenterEntry2.getProductAddTaxAmt())).setScale(amtPrecision, RoundingMode.HALF_UP)));
	                				scmInvCountingCostCenterEntry2.setDifferQty(differQty);
	                				scmInvCountingCostCenterEntry2.setDifferAmt((differQty.multiply(scmInvStock.getPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
	                				scmInvCountingCostCenterEntry2.setDifferTaxAmt((differQty.multiply(scmInvStock.getTaxPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
	                				scmInvCountingCostCenterEntry2.setAccountAmt(scmInvStock.getAmt());
	                				scmInvCountingCostCenterEntry2.setAccountTaxAmt(scmInvStock.getTaxAmt());
	                				scmInvCountingCostCenterEntry2.setStockId(scmInvStock.getId());
	                				scmInvCountingCostCenterEntry2.setExpDate(scmInvStock.getExpDate());
	                				scmInvCountingCostCenterEntry2.setVendorId(scmInvStock.getVendorId());
	                				scmInvCountingCostCenterEntry2.setProductAddQty(productAddQty);
	                				scmInvCountingCostCenterEntryBiz.add(scmInvCountingCostCenterEntry2, param);
	                				differQty = BigDecimal.ZERO;
	                				productAddQty = BigDecimal.ZERO;
	                				continue;
	                    		}else if(BigDecimal.ZERO.compareTo(differQty) > 0){
	                    			//盘亏
	                    			if(stockQty.compareTo(differQty.abs()) >= 0){
	                					//此批次够亏损
	                					scmInvCountingCostCenterEntry2.setLot(scmInvStock.getLot());
	                    				scmInvCountingCostCenterEntry2.setAccountQty(scmInvStock.getQty());
	                    				if(productAddQty.add(differQty).compareTo(BigDecimal.ZERO)>0) {
		                    				scmInvCountingCostCenterEntry2.setQty(scmInvStock.getQty());
		                    				scmInvCountingCostCenterEntry2.setProductAddQty(productAddQty.add(differQty));
	                    				}else {
		                    				scmInvCountingCostCenterEntry2.setQty(scmInvStock.getQty().add(differQty).subtract(productAddQty));
		                    				scmInvCountingCostCenterEntry2.setProductAddQty(productAddQty);
	                    				}
	                    				scmInvCountingCostCenterEntry2.setProductAddAmt(scmInvCountingCostCenterEntry2.getProductAddQty().multiply(scmInvStock.getPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
		                				scmInvCountingCostCenterEntry2.setProductAddTaxAmt(scmInvCountingCostCenterEntry2.getProductAddQty().multiply(scmInvStock.getTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
	                    				scmInvCountingCostCenterEntry2.setPrice(scmInvStock.getPrice());
	                    				scmInvCountingCostCenterEntry2.setTaxPrice(scmInvStock.getTaxPrice());
	                    				scmInvCountingCostCenterEntry2.setTaxRate(scmInvStock.getTaxRate());
	                    				if(stockQty.compareTo(differQty.abs()) == 0){
	                    					scmInvCountingCostCenterEntry2.setAmt(BigDecimal.ZERO);
	                        				scmInvCountingCostCenterEntry2.setTaxAmt(BigDecimal.ZERO);
	                        				scmInvCountingCostCenterEntry2.setDifferAmt(scmInvStock.getAmt().negate());
	                        				scmInvCountingCostCenterEntry2.setDifferTaxAmt(scmInvStock.getTaxAmt().negate());
	                    				}else{
	                    					scmInvCountingCostCenterEntry2.setAmt((scmInvStock.getAmt().add(differQty.multiply(scmInvStock.getPrice())).subtract(scmInvCountingCostCenterEntry2.getProductAddAmt())).setScale(amtPrecision, RoundingMode.HALF_UP));
	                        				scmInvCountingCostCenterEntry2.setTaxAmt((scmInvStock.getTaxAmt().add(differQty.multiply(scmInvStock.getTaxPrice())).subtract(scmInvCountingCostCenterEntry2.getProductAddTaxAmt())).setScale(amtPrecision, RoundingMode.HALF_UP));
	                        				scmInvCountingCostCenterEntry2.setDifferAmt((differQty.multiply(scmInvStock.getPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
	                        				scmInvCountingCostCenterEntry2.setDifferTaxAmt((differQty.multiply(scmInvStock.getTaxPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
	                    				}
	                    				scmInvCountingCostCenterEntry2.setDifferQty(differQty);
	                    				scmInvCountingCostCenterEntry2.setAccountAmt(scmInvStock.getAmt());
	                    				scmInvCountingCostCenterEntry2.setAccountTaxAmt(scmInvStock.getTaxAmt());
	                    				scmInvCountingCostCenterEntry2.setStockId(scmInvStock.getId());
	                    				scmInvCountingCostCenterEntry2.setExpDate(scmInvStock.getExpDate());
	                    				scmInvCountingCostCenterEntry2.setVendorId(scmInvStock.getVendorId());
	                    				scmInvCountingCostCenterEntryBiz.add(scmInvCountingCostCenterEntry2, param);
	                    				differQty = BigDecimal.ZERO;
	                    				productAddQty = BigDecimal.ZERO;
	                    				continue;
	                				}else if (scmInvStock.getQty().compareTo(BigDecimal.ZERO)>0){
	                					//此批次不足亏损
	                					scmInvCountingCostCenterEntry2.setLot(scmInvStock.getLot());
	                    				scmInvCountingCostCenterEntry2.setAccountQty(scmInvStock.getQty());
	                    				scmInvCountingCostCenterEntry2.setQty(BigDecimal.ZERO);
	                    				scmInvCountingCostCenterEntry2.setPrice(scmInvStock.getPrice());
	                    				scmInvCountingCostCenterEntry2.setTaxPrice(scmInvStock.getTaxPrice());
	                    				scmInvCountingCostCenterEntry2.setTaxRate(scmInvStock.getTaxRate());
	                    				scmInvCountingCostCenterEntry2.setAmt(BigDecimal.ZERO);
	                    				scmInvCountingCostCenterEntry2.setTaxAmt(BigDecimal.ZERO);
	                    				scmInvCountingCostCenterEntry2.setDifferQty((scmInvStock.getQty()).negate());
	                    				scmInvCountingCostCenterEntry2.setDifferAmt((scmInvStock.getAmt()).negate());
	                    				scmInvCountingCostCenterEntry2.setDifferTaxAmt((scmInvStock.getTaxAmt()).negate());
	                    				scmInvCountingCostCenterEntry2.setAccountAmt(scmInvStock.getAmt());
	                    				scmInvCountingCostCenterEntry2.setAccountTaxAmt(scmInvStock.getTaxAmt());
	                    				scmInvCountingCostCenterEntry2.setStockId(scmInvStock.getId());
	                    				scmInvCountingCostCenterEntry2.setExpDate(scmInvStock.getExpDate());
	                    				scmInvCountingCostCenterEntry2.setVendorId(scmInvStock.getVendorId());
		                				scmInvCountingCostCenterEntry2.setProductAddQty(BigDecimal.ZERO);
		                				scmInvCountingCostCenterEntry2.setProductAddAmt(BigDecimal.ZERO);
		                				scmInvCountingCostCenterEntry2.setProductAddTaxAmt(BigDecimal.ZERO);
	                    				scmInvCountingCostCenterEntryBiz.add(scmInvCountingCostCenterEntry2, param);
	                    				differQty = differQty.add(stockQty);
	                    				continue;
	                				}
	                    		}else if(BigDecimal.ZERO.compareTo(differQty) == 0){
	                    			//无盈亏
	                    			scmInvCountingCostCenterEntry2.setLot(scmInvStock.getLot());
	                				scmInvCountingCostCenterEntry2.setAccountQty(scmInvStock.getQty());
	                				if(scmInvStock.getQty().subtract(productAddQty).compareTo(BigDecimal.ZERO)<0) {
	                					scmInvCountingCostCenterEntry2.setQty(BigDecimal.ZERO);
	                					scmInvCountingCostCenterEntry2.setProductAddQty(scmInvStock.getQty());
	                					productAddQty = productAddQty.subtract(scmInvStock.getQty());
	                				}else {
	                					scmInvCountingCostCenterEntry2.setQty(scmInvStock.getQty().subtract(productAddQty));
	                					scmInvCountingCostCenterEntry2.setProductAddQty(productAddQty);
	                					productAddQty=BigDecimal.ZERO;
	                				}
	                				scmInvCountingCostCenterEntry2.setProductAddAmt(scmInvCountingCostCenterEntry2.getProductAddQty().multiply(scmInvStock.getPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
	                				scmInvCountingCostCenterEntry2.setProductAddTaxAmt(scmInvCountingCostCenterEntry2.getProductAddQty().multiply(scmInvStock.getTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
	                				scmInvCountingCostCenterEntry2.setPrice(scmInvStock.getPrice());
	                				scmInvCountingCostCenterEntry2.setTaxPrice(scmInvStock.getTaxPrice());
	                				scmInvCountingCostCenterEntry2.setTaxRate(scmInvStock.getTaxRate());
	                				scmInvCountingCostCenterEntry2.setAmt(scmInvStock.getAmt().subtract(scmInvCountingCostCenterEntry2.getProductAddAmt()));
	                				scmInvCountingCostCenterEntry2.setTaxAmt(scmInvStock.getTaxAmt().subtract(scmInvCountingCostCenterEntry2.getProductAddTaxAmt()));
	                				scmInvCountingCostCenterEntry2.setDifferQty(BigDecimal.ZERO);
	                				scmInvCountingCostCenterEntry2.setDifferAmt(BigDecimal.ZERO);
	                				scmInvCountingCostCenterEntry2.setDifferTaxAmt(BigDecimal.ZERO);
	                				scmInvCountingCostCenterEntry2.setAccountAmt(scmInvStock.getAmt());
	                				scmInvCountingCostCenterEntry2.setAccountTaxAmt(scmInvStock.getTaxAmt());
	                				scmInvCountingCostCenterEntry2.setStockId(scmInvStock.getId());
	                				scmInvCountingCostCenterEntry2.setExpDate(scmInvStock.getExpDate());
	                				scmInvCountingCostCenterEntry2.setVendorId(scmInvStock.getVendorId());
	                				scmInvCountingCostCenterEntryBiz.add(scmInvCountingCostCenterEntry2, param);
	                				differQty = BigDecimal.ZERO;
	                				continue;
	                    		}
                     		}
                    	}
                     	if(differQty.compareTo(BigDecimal.ZERO)!=0) {
                     		throw new AppException("iscm.inventorymanage.cstbusiness.ScmInvCountingTask.countingFinish.error5");
                     	}
                    }
                    scmInvCountingCostCenterEntryBiz.delete(scmInvCountingCostCenterEntry, param);
				}
			}
		}
	}

    
	@Override
	public List<ScmInvCountingTask2> queryCountCostTaskList(ScmInvCountingTaskAdvQuery scmInvCountingTaskAdvQuery,int pageNum, Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(ScmInvCountingTask2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		page.setModel(scmInvCountingTaskAdvQuery);	//findPage时底层会触发doAdvQuery，增加查询条件
		
		return this.findPage(page, param);
	}

	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvCountingTaskAdvQuery) {
				ScmInvCountingTaskAdvQuery scmInvCountingTaskAdvQuery = (ScmInvCountingTaskAdvQuery) page.getModel();
				if(StringUtils.isNotBlank(scmInvCountingTaskAdvQuery.getTaskNo())){
					page.getParam().put(ScmInvCountingTask2.FN_TASKNO,new QueryParam(ScmInvCountingTask2.FN_TASKNO, QueryParam.QUERY_LIKE,"%"+scmInvCountingTaskAdvQuery.getTaskNo()+"%"));
				}
				if(scmInvCountingTaskAdvQuery.getBizDateFrom()!=null){
					if(scmInvCountingTaskAdvQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmInvCountingTask2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "." +ScmInvCountingTask2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvCountingTaskAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(scmInvCountingTaskAdvQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmInvCountingTask2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "." +ScmInvCountingTask2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvCountingTaskAdvQuery.getBizDateFrom())));
					}
				}else if(scmInvCountingTaskAdvQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmInvCountingTask2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "." +ScmInvCountingTask2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmInvCountingTaskAdvQuery.getBizDateTo())));
				}
				if(scmInvCountingTaskAdvQuery.getCreateDateFrom()!=null){
					if(scmInvCountingTaskAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmInvCountingTask2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "." +ScmInvCountingTask2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvCountingTaskAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvCountingTaskAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmInvCountingTask2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "." +ScmInvCountingTask2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvCountingTaskAdvQuery.getCreateDateFrom())));
					}
				}else if(scmInvCountingTaskAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmInvCountingTask2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "." +ScmInvCountingTask2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvCountingTaskAdvQuery.getCreateDateTo(),1))));
				}
				page.getParam().put(ScmInvCountingTask2.FN_COSTCENTER,new QueryParam(ScmInvCountingTask2.FN_COSTCENTER, QueryParam.QUERY_EQ,scmInvCountingTaskAdvQuery.isCostCenter()?"1":"0"));
			}
		}
	}

	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page,HashMap<String, Object> map, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvCountingTaskAdvQuery) {
				ScmInvCountingTaskAdvQuery scmInvCountingTaskAdvQuery = (ScmInvCountingTaskAdvQuery) page.getModel();
				if(scmInvCountingTaskAdvQuery.isFromInterface())
					map.put("sortByDate", "Y");
				if(StringUtils.isNotBlank(scmInvCountingTaskAdvQuery.getUseOrgUnitNo())){
					map.put("useOrgUnitNo", scmInvCountingTaskAdvQuery.getUseOrgUnitNo());
				}
				if (StringUtils.isNotEmpty(scmInvCountingTaskAdvQuery.getWareHouseNo())) {
					map.put("whNo", scmInvCountingTaskAdvQuery.getWareHouseNo());
				}
			}
		}
		return map;
	}
	@Override
	public List<OrgAdmin2> queryCountCostTaskDepteList(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException {
		List<ScmInvCountingCostCenter2> scmInvCountingCostCenterList = (List<ScmInvCountingCostCenter2>) scmInvCountingCostCenterBiz.selectByTaskNo(scmInvCountingTask.getTaskNo(),param.getControlUnitNo(), param);
		if(scmInvCountingCostCenterList!=null && !scmInvCountingCostCenterList.isEmpty()){
			StringBuffer orgs=new StringBuffer("");
			for(ScmInvCountingCostCenter2 scmInvCountingCostCenter:scmInvCountingCostCenterList){
				if(StringUtils.isNotBlank(orgs.toString()))
					orgs.append(",");
				orgs.append("'").append(scmInvCountingCostCenter.getUseOrgUnitNo()).append("'");
			}
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ClassUtils.getFinalModelSimpleName(OrgAdmin2.class) + "."
					+ OrgAdmin2.FN_ORGUNITNO,
					new QueryParam(ClassUtils
							.getFinalModelSimpleName(OrgAdmin2.class)
							+ "."
							+ OrgAdmin2.FN_ORGUNITNO, QueryParam.QUERY_IN, orgs
							.toString()));
			return (List<OrgAdmin2>) orgAdminBiz.findAll(map, param);
			
		}
		return null;
	}

	@Override
	public ScmInvCountingCostCenter2 queryCountCostTable(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException {
		if(scmInvCountingTask==null)
			throw new AppException("webservice.params.null");
		if(StringUtils.isBlank(scmInvCountingTask.getTaskNo()))
			throw new AppException("webservice.params.null");
		if(StringUtils.isBlank(scmInvCountingTask.getUseOrgUnitNo()))
			throw new AppException("webservice.params.null");
		ScmInvCountingTask2 selectByTaskNo = this.selectByTaskNo(scmInvCountingTask.getTaskNo(), param);
		ScmInvCountingCostCenter2 scmInvCountingCostCenter = scmInvCountingCostCenterBiz.selectByTaskNoAndUseOrgUnitNo(scmInvCountingTask.getTaskNo(), scmInvCountingTask.getUseOrgUnitNo(), param.getControlUnitNo(), param);
		if (selectByTaskNo != null) {
			scmInvCountingCostCenter.setStatus(selectByTaskNo.getStatus());
		}
		if(scmInvCountingCostCenter!=null){
			scmInvCountingCostCenter.setScmInvCountingCostCenterEntryList(scmInvCountingCostCenterEntryBiz.selectByTableId(scmInvCountingCostCenter.getTableId(), param));
		}
		return scmInvCountingCostCenter;
	}

	@Override
	public void doSaveCountCostTable(CountCostTableSaveParams countCostTableSaveParams, Param param) throws AppException {
		ScmInvCountingTask2 scmInvCountingTask = this.selectByTaskNo(countCostTableSaveParams.getTaskNo(),param);
		if(scmInvCountingTask!=null){
			if(StringUtils.equals(scmInvCountingTask.getStatus(),"C")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.scminvcountingtask.startcounting.cancelError2",new String[]{countCostTableSaveParams.getTaskNo()}));
			}
			if(StringUtils.equals(scmInvCountingTask.getStatus(),"O")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.scminvcountingtask.startcounting.finishError2",new String[]{countCostTableSaveParams.getTaskNo()}));
			}
			if(!StringUtils.equals(scmInvCountingTask.getStatus(),"B")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.scminvcountingtask.countingfinish.notStart.error2",new String[]{countCostTableSaveParams.getTaskNo()}));
			}
		}
		ScmInvCountingCostCenter2 scmInvCountingCostCenter = scmInvCountingCostCenterBiz.selectByTaskNoAndUseOrgUnitNo(countCostTableSaveParams.getTaskNo(),countCostTableSaveParams.getDeptNo(),param.getControlUnitNo(), param);
		if(scmInvCountingCostCenter!=null){
			List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList = scmInvCountingCostCenterEntryBiz.selectByTableId(scmInvCountingCostCenter.getTableId(), param);
			List<CountCostTableSaveDetailParams> countCostTableSaveDetailParamsList = countCostTableSaveParams.getDetailList();
			if(scmInvCountingCostCenterEntryList!=null && !scmInvCountingCostCenterEntryList.isEmpty()){
				for(ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry:scmInvCountingCostCenterEntryList){
					for(int i =countCostTableSaveDetailParamsList.size()-1;i>=0;i--){
						CountCostTableSaveDetailParams countCostTableSaveDetailParams = countCostTableSaveDetailParamsList.get(i);
						if(StringUtils.equals(scmInvCountingCostCenterEntry.getItemNo(), countCostTableSaveDetailParams.getItemNo())){
							countCostTableSaveDetailParamsList.remove(i);
							scmInvCountingCostCenterEntry.setQty(countCostTableSaveDetailParams.getQty());
//							scmInvCountingCostCenterEntry.setPrice(countCostTableSaveDetailParams.getPrice());
//							scmInvCountingCostCenterEntry.setTaxPrice(countCostTableSaveDetailParams.getTaxPrice());
							scmInvCountingCostCenterEntry.setAmt(scmInvCountingCostCenterEntry.getQty().multiply(scmInvCountingCostCenterEntry.getPrice()));
							scmInvCountingCostCenterEntry.setTaxAmt(scmInvCountingCostCenterEntry.getQty().multiply(scmInvCountingCostCenterEntry.getTaxPrice()));
							if(scmInvCountingCostCenterEntry.getPieUnit() != 0){
								scmInvCountingCostCenterEntry.setPieQty(countCostTableSaveDetailParams.getPieQty());
							}
							scmInvCountingCostCenterEntry.setDifferPieQty(scmInvCountingCostCenterEntry.getPieQty().subtract(scmInvCountingCostCenterEntry.getAccountPieQty()));
							scmInvCountingCostCenterEntry.setDifferQty(scmInvCountingCostCenterEntry.getQty().subtract(scmInvCountingCostCenterEntry.getAccountQty()));
							scmInvCountingCostCenterEntry.setDifferAmt(scmInvCountingCostCenterEntry.getAmt().subtract(scmInvCountingCostCenterEntry.getAccountAmt()));
							scmInvCountingCostCenterEntry.setDifferTaxAmt(scmInvCountingCostCenterEntry.getTaxAmt().subtract(scmInvCountingCostCenterEntry.getAccountTaxAmt()));
							scmInvCountingCostCenterEntry.setCounted(countCostTableSaveDetailParams.isCounted());
							scmInvCountingCostCenterEntryBiz.updateDirect(scmInvCountingCostCenterEntry, param);
						}
					}
				}
			}else{
				//全部手工新增
				scmInvCountingCostCenterEntryList = new ArrayList<>();
			}
			for(CountCostTableSaveDetailParams countCostTableSaveDetailParams:countCostTableSaveDetailParamsList){
				ScmMaterial2 scmMaterial = scmMaterialBiz.selectByItemNo(param.getControlUnitNo(), countCostTableSaveDetailParams.getItemNo(), param);
				if(scmMaterial==null){
					throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
				}else{
					scmMaterial = scmMaterialBiz.findByInvItemId(param.getControlUnitNo(), param.getOrgUnitNo(), scmMaterial.getId(), param);
					ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry = new ScmInvCountingCostCenterEntry2(true);
					BeanUtils.copyProperties(countCostTableSaveDetailParams, scmInvCountingCostCenterEntry);
					scmInvCountingCostCenterEntry.setTableId(scmInvCountingCostCenter.getTableId());
					scmInvCountingCostCenterEntry.setItemId(scmMaterial.getId());
					scmInvCountingCostCenterEntry.setUnit(scmMaterial.getUnitId());
					scmInvCountingCostCenterEntry.setPieUnit(scmMaterial.getPieUnitId());
					if(scmInvCountingCostCenterEntry.getPieUnit() == 0){
						scmInvCountingCostCenterEntry.setPieQty(BigDecimal.ZERO);
					}
					scmInvCountingCostCenterEntry.setUsrAdd(true);
					scmInvCountingCostCenterEntryBiz.add(scmInvCountingCostCenterEntry, param);
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
	}
	
	@Override
	public List<ScmInvCountCostTaskDiff> queryCountCostTaskDiff(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException {
		List<ScmInvCountCostTaskDiff> resultList = new ArrayList<>();
		Long taskId = scmInvCountingTask.getTaskId();
		if(taskId==0) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("taskNo", scmInvCountingTask.getTaskNo());
			map.put("controlUnitNo", param.getControlUnitNo());
			List<ScmInvCountingTask2> scmInvCountingTaskList = this.findAll(map, param);
			if(scmInvCountingTaskList!=null && !scmInvCountingTaskList.isEmpty()){
				taskId = scmInvCountingTaskList.get(0).getTaskId();
			}
		}
		List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList = scmInvCountingCostCenterBiz.queryCountCostTaskDiff(taskId,scmInvCountingTask.isShowSum(),param);
		if(scmInvCountingCostCenterEntryList!=null && !scmInvCountingCostCenterEntryList.isEmpty()){
			HashMap<String,Object> dataCacheMap = new HashMap<String,Object>();
        	for(ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry:scmInvCountingCostCenterEntryList){
        		ScmInvCountCostTaskDiff scmInvCountCostTaskDiff = new ScmInvCountCostTaskDiff();
        		BeanUtils.copyProperties(scmInvCountingCostCenterEntry, scmInvCountCostTaskDiff);
        		if(StringUtils.isNotBlank(scmInvCountingCostCenterEntry.getUseOrgUnitNo())) {
        			scmInvCountCostTaskDiff.setDeptNo(scmInvCountingCostCenterEntry.getUseOrgUnitNo());
        			OrgBaseUnit2 orgBaseUnit =  (OrgBaseUnit2) dataCacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit2.class)+"_"+scmInvCountingCostCenterEntry.getUseOrgUnitNo());
        			if(orgBaseUnit==null) {
        				orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvCountingCostCenterEntry.getUseOrgUnitNo(), param);
        				dataCacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit2.class)+"_"+scmInvCountingCostCenterEntry.getUseOrgUnitNo(), orgBaseUnit);
        			}
        			if(orgBaseUnit!=null)
        				scmInvCountCostTaskDiff.setDeptName(orgBaseUnit.getOrgUnitName());
        		}
        		if(scmInvCountingCostCenterEntry.getItemId()>0) {
        			ScmMaterial2 scmMaterial = (ScmMaterial2) dataCacheMap.get(ClassUtils.getFinalModelSimpleName(ScmMaterial2.class)+"_"+scmInvCountingCostCenterEntry.getItemId());
        			if(scmMaterial==null) {
        				scmMaterial = scmMaterialBiz.selectDirect(scmInvCountingCostCenterEntry.getItemId(), param);
        				dataCacheMap.put(ClassUtils.getFinalModelSimpleName(ScmMaterial2.class)+"_"+scmInvCountingCostCenterEntry.getItemId(), scmMaterial);
        			}
        			if(scmMaterial!=null) {
        				scmInvCountCostTaskDiff.setItemNo(scmMaterial.getItemNo());
        				scmInvCountCostTaskDiff.setItemName(scmMaterial.getItemName());
        				scmInvCountCostTaskDiff.setSpec(scmMaterial.getSpec());
        			}
        		}
        		if(scmInvCountingCostCenterEntry.getUnit()>0) {
        			ScmMeasureUnit scmMeasureUnit = (ScmMeasureUnit) dataCacheMap.get(ClassUtils.getFinalModelSimpleName(ScmMeasureUnit.class)+"_"+scmInvCountingCostCenterEntry.getUnit());
        			if(scmMeasureUnit==null) {
        				scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvCountingCostCenterEntry.getUnit(), param);
        				dataCacheMap.put(ClassUtils.getFinalModelSimpleName(ScmMeasureUnit.class)+"_"+scmInvCountingCostCenterEntry.getUnit(), scmMeasureUnit);
        			}
        			if(scmMeasureUnit!=null)
        				scmInvCountCostTaskDiff.setUnitName(scmMeasureUnit.getUnitName());
        		}
        		if(scmInvCountingCostCenterEntry.getPieUnit()>0) {
        			ScmMeasureUnit scmMeasureUnit = (ScmMeasureUnit) dataCacheMap.get(ClassUtils.getFinalModelSimpleName(ScmMeasureUnit.class)+"_"+scmInvCountingCostCenterEntry.getPieUnit());
        			if(scmMeasureUnit==null) {
        				scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvCountingCostCenterEntry.getPieUnit(), param);
        				dataCacheMap.put(ClassUtils.getFinalModelSimpleName(ScmMeasureUnit.class)+"_"+scmInvCountingCostCenterEntry.getPieUnit(), scmMeasureUnit);
        			}
        			if(scmMeasureUnit!=null)
        				scmInvCountCostTaskDiff.setPieUnitName(scmMeasureUnit.getUnitName());
        		}
        		resultList.add(scmInvCountCostTaskDiff);
        	}
        }
		return resultList;
	}

	@Override
	public List<ScmInvWareHouse> queryCountInvTaskWareHouseList(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException {
		List<ScmInvCountingTable2> scmInvCountingTableList = (List<ScmInvCountingTable2>) scmInvCountingTableBiz.selectByTaskNo(scmInvCountingTask.getTaskNo(),param.getControlUnitNo(), param);
		if(scmInvCountingTableList!=null && !scmInvCountingTableList.isEmpty()){
			StringBuffer orgs=new StringBuffer("");
			for(ScmInvCountingTable2 scmInvCountingTable:scmInvCountingTableList){
				if(StringUtils.isNotBlank(orgs.toString()))
					orgs.append(",");
				orgs.append(scmInvCountingTable.getWareHouseId());
			}
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ScmInvWareHouse.FN_ID, new QueryParam(ScmInvWareHouse.FN_ID, QueryParam.QUERY_IN, orgs.toString()));
			return (List<ScmInvWareHouse>) scmInvWareHouseBiz.findAll(map, param);
			
		}
		return null;
	}

	@Override
	public ScmInvCountingTable2 queryCountInvTable(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException {
		if(scmInvCountingTask==null)
			throw new AppException("webservice.params.null");
		if(StringUtils.isBlank(scmInvCountingTask.getTaskNo()))
			throw new AppException("webservice.params.null");
		if(StringUtils.isBlank(scmInvCountingTask.getWareHouseNo()))
			throw new AppException("webservice.params.null");
		ScmInvCountingTask2 scmInvCountingTask2 = this.selectByTaskNo(scmInvCountingTask.getTaskNo(), param);
		ScmInvCountingTable2 scmInvCountingTable = scmInvCountingTableBiz.selectByTaskNoAndWhNo(scmInvCountingTask.getTaskNo(),scmInvCountingTask.getWareHouseNo(),param.getControlUnitNo(), param);
		if (scmInvCountingTask2 != null) {
			scmInvCountingTable.setStatus(scmInvCountingTask2.getStatus());
		}
		if(scmInvCountingTable!=null){
			scmInvCountingTable.setScmInvCountingTableEntryList(scmInvCountingTableEntryBiz.selectByTableId(scmInvCountingTable.getTableId(), param));
		}
		return scmInvCountingTable;
	}

	@Override
	public void doSaveCountInvTable(CountInvTableSaveParams countInvTableSaveParams, Param param) throws AppException {
		ScmInvCountingTask2 scmInvCountingTask = this.selectByTaskNo(countInvTableSaveParams.getTaskNo(),param);
		if(scmInvCountingTask!=null){
			if(StringUtils.equals(scmInvCountingTask.getStatus(),"C")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.scminvcountingtask.startcounting.cancelError",new String[]{countInvTableSaveParams.getTaskNo()}));
			}
			if(StringUtils.equals(scmInvCountingTask.getStatus(),"O")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.scminvcountingtask.startcounting.finishError",new String[]{countInvTableSaveParams.getTaskNo()}));
			}
			if(!StringUtils.equals(scmInvCountingTask.getStatus(),"B")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.scminvcountingtask.countingfinish.notStart.error",new String[]{countInvTableSaveParams.getTaskNo()}));
			}
		}
		ScmInvCountingTable2 scmInvCountingTable = scmInvCountingTableBiz.selectByTaskNoAndWhNo(countInvTableSaveParams.getTaskNo(),countInvTableSaveParams.getWareHouseNo(), param.getControlUnitNo(),param);
		if(scmInvCountingTable!=null){
			List<ScmInvCountingTableEntry2> ScmInvCountingTableEntryList = scmInvCountingTableEntryBiz.selectByTableId(scmInvCountingTable.getTableId(), param);
			List<CountInvTableSaveDetailParams> countInvTableSaveDetailParamsList = countInvTableSaveParams.getDetailList();
			if(ScmInvCountingTableEntryList!=null && !ScmInvCountingTableEntryList.isEmpty()){
				for(ScmInvCountingTableEntry2 scmInvCountingTableEntry:ScmInvCountingTableEntryList){
					for(int i =countInvTableSaveDetailParamsList.size()-1;i>=0;i--){
						CountInvTableSaveDetailParams countInvTableSaveDetailParams = countInvTableSaveDetailParamsList.get(i);
						if(StringUtils.equals(scmInvCountingTableEntry.getItemNo(), countInvTableSaveDetailParams.getItemNo())){
							countInvTableSaveDetailParamsList.remove(i);
							scmInvCountingTableEntry.setQty(countInvTableSaveDetailParams.getQty());
							//scmInvCountingTableEntry.setPrice(countInvTableSaveDetailParams.getPrice());
							//scmInvCountingTableEntry.setTaxPrice(countInvTableSaveDetailParams.getTaxPrice());
							scmInvCountingTableEntry.setAmt(scmInvCountingTableEntry.getQty().multiply(scmInvCountingTableEntry.getPrice()));
							scmInvCountingTableEntry.setTaxAmt(scmInvCountingTableEntry.getQty().multiply(scmInvCountingTableEntry.getTaxPrice()));
							if(scmInvCountingTableEntry.getPieUnit() != 0){
								scmInvCountingTableEntry.setPieQty(countInvTableSaveDetailParams.getPieQty());
							}
							scmInvCountingTableEntry.setDifferPieQty(scmInvCountingTableEntry.getPieQty().subtract(scmInvCountingTableEntry.getAccountPieQty()));
							scmInvCountingTableEntry.setDifferQty(scmInvCountingTableEntry.getQty().subtract(scmInvCountingTableEntry.getAccountQty()));
							scmInvCountingTableEntry.setDifferAmt(scmInvCountingTableEntry.getAmt().subtract(scmInvCountingTableEntry.getAccountAmt()));
							scmInvCountingTableEntry.setDifferTaxAmt(scmInvCountingTableEntry.getTaxAmt().subtract(scmInvCountingTableEntry.getAccountTaxAmt()));
							scmInvCountingTableEntry.setCounted(countInvTableSaveDetailParams.isCounted());
							scmInvCountingTableEntryBiz.updateDirect(scmInvCountingTableEntry, param);
						}
					}
				}
			}else{
				//全部手工新增
				ScmInvCountingTableEntryList = new ArrayList<>();
			}
			for(CountInvTableSaveDetailParams countInvTableSaveDetailParams:countInvTableSaveDetailParamsList){
				ScmMaterial2 scmMaterial = scmMaterialBiz.selectByItemNo(param.getControlUnitNo(), countInvTableSaveDetailParams.getItemNo(), param);
				if(scmMaterial==null){
					throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
				}else{
					scmMaterial = scmMaterialBiz.findByInvItemId(param.getControlUnitNo(), param.getOrgUnitNo(), scmMaterial.getId(), param);
					ScmInvCountingTableEntry2 scmInvCountingTableEntry = new ScmInvCountingTableEntry2(true);
					BeanUtils.copyProperties(countInvTableSaveDetailParams, scmInvCountingTableEntry);
					scmInvCountingTableEntry.setTableId(scmInvCountingTable.getTableId());
					scmInvCountingTableEntry.setWareHouseId(scmInvCountingTable.getWareHouseId());
					scmInvCountingTableEntry.setOrgUnitNo(scmInvCountingTable.getOrgUnitNo());
					scmInvCountingTableEntry.setItemId(scmMaterial.getId());
					scmInvCountingTableEntry.setUnit(scmMaterial.getUnitId());
					scmInvCountingTableEntry.setPieUnit(scmMaterial.getPieUnitId());
					if(scmInvCountingTableEntry.getPieUnit() == 0){
						scmInvCountingTableEntry.setPieQty(BigDecimal.ZERO);
					}
					scmInvCountingTableEntry.setUsrAdd(true);
					scmInvCountingTableEntryBiz.add(scmInvCountingTableEntry, param);
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
	}

	@Override
	public List<ScmInvCountInvTaskDiff> queryCountInvTaskDiff(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException {
		List<ScmInvCountInvTaskDiff> resultList = new ArrayList<>();
		Long taskId = scmInvCountingTask.getTaskId();
		if(taskId==0) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("taskNo", scmInvCountingTask.getTaskNo());
			map.put("controlUnitNo", param.getControlUnitNo());
			List<ScmInvCountingTask2> scmInvCountingTaskList = this.findAll(map, param);
			if(scmInvCountingTaskList!=null && !scmInvCountingTaskList.isEmpty()){
				taskId = scmInvCountingTaskList.get(0).getTaskId();
			}
		}
		List<ScmInvCountingTableEntry2> scmInvCountingTableEntryList = scmInvCountingTableBiz.queryCountInvTaskDiff(taskId,scmInvCountingTask.isShowSum(),param);
		if(scmInvCountingTableEntryList!=null && !scmInvCountingTableEntryList.isEmpty()) {
			HashMap<String,Object> dataCacheMap = new HashMap<String,Object>();
			for(ScmInvCountingTableEntry2 scmInvCountingTableEntry:scmInvCountingTableEntryList){
				ScmInvCountInvTaskDiff scmInvCountInvTaskDiff = new ScmInvCountInvTaskDiff();
        		BeanUtils.copyProperties(scmInvCountingTableEntry, scmInvCountInvTaskDiff);
        		if(scmInvCountingTableEntry.getWareHouseId()>0) {
        			ScmInvWareHouse scmInvWareHouse = (ScmInvWareHouse) dataCacheMap.get(ClassUtils.getFinalModelSimpleName(ScmInvWareHouse.class)+"_"+scmInvCountingTableEntry.getWareHouseId());
        			if(scmInvWareHouse==null) {
        				scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvCountingTableEntry.getWareHouseId(), param);
        				dataCacheMap.put(ClassUtils.getFinalModelSimpleName(ScmInvWareHouse.class)+"_"+scmInvCountingTableEntry.getWareHouseId(), scmInvWareHouse);
        			}
         			if(scmInvWareHouse!=null) {
        				scmInvCountInvTaskDiff.setWareHouseNo(scmInvWareHouse.getWhNo());
        				scmInvCountInvTaskDiff.setWareHouseName(scmInvWareHouse.getWhName());
        			}
        		}
        		if(scmInvCountingTableEntry.getItemId()>0) {
        			ScmMaterial2 scmMaterial = (ScmMaterial2) dataCacheMap.get(ClassUtils.getFinalModelSimpleName(ScmMaterial2.class)+"_"+scmInvCountingTableEntry.getItemId());
        			if(scmMaterial==null) {
        				scmMaterial = scmMaterialBiz.selectDirect(scmInvCountingTableEntry.getItemId(), param);
        				dataCacheMap.put(ClassUtils.getFinalModelSimpleName(ScmMaterial2.class)+"_"+scmInvCountingTableEntry.getItemId(), scmMaterial);
        			}
        			if(scmMaterial!=null) {
        				scmInvCountInvTaskDiff.setItemNo(scmMaterial.getItemNo());
        				scmInvCountInvTaskDiff.setItemName(scmMaterial.getItemName());
        				scmInvCountInvTaskDiff.setSpec(scmMaterial.getSpec());
        			}
        		}
        		if(scmInvCountingTableEntry.getUnit()>0) {
        			ScmMeasureUnit scmMeasureUnit = (ScmMeasureUnit) dataCacheMap.get(ClassUtils.getFinalModelSimpleName(ScmMeasureUnit.class)+"_"+scmInvCountingTableEntry.getUnit());
        			if(scmMeasureUnit==null) {
        				scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvCountingTableEntry.getUnit(), param);
        				dataCacheMap.put(ClassUtils.getFinalModelSimpleName(ScmMeasureUnit.class)+"_"+scmInvCountingTableEntry.getUnit(), scmMeasureUnit);
        			}
        			if(scmMeasureUnit!=null)
        				scmInvCountInvTaskDiff.setUnitName(scmMeasureUnit.getUnitName());
        		}
        		if(scmInvCountingTableEntry.getPieUnit()>0) {
        			ScmMeasureUnit scmMeasureUnit = (ScmMeasureUnit) dataCacheMap.get(ClassUtils.getFinalModelSimpleName(ScmMeasureUnit.class)+"_"+scmInvCountingTableEntry.getPieUnit());
        			if(scmMeasureUnit==null) {
        				scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvCountingTableEntry.getPieUnit(), param);
        				dataCacheMap.put(ClassUtils.getFinalModelSimpleName(ScmMeasureUnit.class)+"_"+scmInvCountingTableEntry.getPieUnit(), scmMeasureUnit);
        			}
        			if(scmMeasureUnit!=null)
        				scmInvCountInvTaskDiff.setPieUnitName(scmMeasureUnit.getUnitName());
        		}
        		resultList.add(scmInvCountInvTaskDiff);
        	}
		}
		return resultList;
	}

	@Override
	public List<ScmMaterial2> queryCountInvMaterialList(CountInvMaterialListParams countInvMaterialListParams,int pageNum, Param param) throws AppException{
		Page page = new Page();
		page.setModelClass(ScmMaterial2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		List<String> arglist = new ArrayList<String>();
		arglist.add("orgUnitNo="+param.getOrgUnitNo());
		arglist.add("controlUnitNo="+param.getControlUnitNo());
		if(StringUtils.isNotBlank(countInvMaterialListParams.getMixParam())){
			page.setSqlCondition("(ScmMaterial.itemNo like '%"+countInvMaterialListParams.getMixParam()+"%' or ScmMaterial.itemName like '%"+countInvMaterialListParams.getMixParam()+
					"%' or ScmMaterial.barCode like '%"+countInvMaterialListParams.getMixParam()+"%' or ScmMaterial.pym like '%"+countInvMaterialListParams.getMixParam()+"%')");
		}
		return scmMaterialBiz.queryPage(page, arglist, "findByInvAllPage", param);
	}

	@Override
	public List<ScmMaterial2> queryCountCostMaterialList(CountCostMaterialListParams countCostMaterialListParams,int pageNum, Param param) throws AppException {
		List<OrgStorage2> orgStorageList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOINV, countCostMaterialListParams.getDeptNo(), false, null, param);
		if(orgStorageList==null || orgStorageList.isEmpty()){
			throw new AppException("iscm.purchasemanage.controller.ScmPurRequireFormController.notadmintoinv");
		}
		String invOrgUnitNo="";
		for(OrgStorage2 orgStorage:orgStorageList){
			if(orgStorage.isDefault()){
				invOrgUnitNo=orgStorage.getOrgUnitNo();
				break;
			}
		}
		if(StringUtils.isBlank(invOrgUnitNo))
			invOrgUnitNo = orgStorageList.get(0).getOrgUnitNo();
		Page page = new Page();
		page.setModelClass(ScmMaterial2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		List<String> arglist = new ArrayList<String>();
		arglist.add("orgUnitNo="+invOrgUnitNo);
		arglist.add("controlUnitNo="+param.getControlUnitNo());
		if(StringUtils.isNotBlank(countCostMaterialListParams.getMixParam())){
			page.setSqlCondition("(ScmMaterial.itemNo like '%"+countCostMaterialListParams.getMixParam()+"%' or ScmMaterial.itemName like '%"+countCostMaterialListParams.getMixParam()+
					"%' or ScmMaterial.barCode like '%"+countCostMaterialListParams.getMixParam()+"%' or ScmMaterial.pym like '%"+countCostMaterialListParams.getMixParam()+"%')");
		}
		return scmMaterialBiz.queryPage(page, arglist, "findByInvAllPage", param);
	}

	@Override
	protected void beforeDelete(ScmInvCountingTask2 entity, Param param)
			throws AppException {
		ScmInvCountingTask2 scmInvCountingTask = this.selectDirect(entity.getPK(), param);
		if(!StringUtils.equals(scmInvCountingTask.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getTaskNo()}));
		}
	}

	@Override
	public List<OrgAdmin2> queryDeptList(OrgAdmin2 orgAdmin,int pageNum, Param param) throws AppException {
		Page page = new Page();
		page.setModelClass(OrgAdmin2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
        ArrayList argList = new ArrayList();
        argList.add("type=from");
        argList.add("relationType=" + OrgUnitRelationType.ADMINTOFIN);
        argList.add("toOrgUnitNo=" + param.getOrgUnitNo());
        if(StringUtils.isNotBlank(orgAdmin.getOrgUnitName())){
        	page.getParam().put((ClassUtils.getFinalModelSimpleName(OrgBaseUnit2.class) + "." + OrgBaseUnit2.FN_ORGUNITNAME), 
    				new QueryParam((ClassUtils.getFinalModelSimpleName(OrgBaseUnit2.class) + "." + OrgBaseUnit2.FN_ORGUNITNAME), QueryParam.QUERY_LIKE, "%"+orgAdmin.getOrgUnitName()+"%"));
        }
        List<OrgAdmin2> orgAdminList = orgAdminBiz.queryPage(page, argList, "queryPageEx", param);
        /*if(StringUtils.isNotBlank(orgAdmin.getOrgUnitName())){
	        if (orgAdminList != null && !orgAdminList.isEmpty()) {
	            for (int i = 0; i < orgAdminList.size(); i++) {
	                if (!StringUtils.contains(orgAdminList.get(i).getOrgUnitName(),orgAdmin.getOrgUnitName())) {
	                	orgAdminList.remove(i);
	                }
	            }
	        }
        }*/
        return orgAdminList;
	}

	@Override
	public ScmInvCountingTask2 refreshAccount(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException {
		if(scmInvCountingTask != null) {
			ScmInvCountingTask2 scmInvCountingTask2 = this.selectDirect(scmInvCountingTask.getTaskId(), param);
			if(scmInvCountingTask2 != null && StringUtils.contains("O,C", scmInvCountingTask2.getStatus())) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.scmInvCountingTask.refreshAccount.statusError", new String[] {scmInvCountingTask2.getTaskNo()}));
			}
			if(!scmInvCountingTask.isCostCenter()){
				scmInvCountingTableEntryBiz.refreshAccount(scmInvCountingTask,param);
			}else {
				scmInvCountingCostCenterEntryBiz.refreshAccount(scmInvCountingTask,param);
			}
		}
		return null;
	}

	
	@Override
	public ScmInvCountingTask2 selectByTaskNo(String taskNo, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("taskNo", taskNo);
		map.put("controlUnitNo", param.getControlUnitNo());
		List<ScmInvCountingTask2> scmInvCountingTaskList = this.findAll(map, param);
		if(scmInvCountingTaskList!=null && !scmInvCountingTaskList.isEmpty())
			return scmInvCountingTaskList.get(0);
		return null;
	}

	@Override
	public List<ScmInvCountingTask2> checkUnPostBill(String finOrgUnitNo, long periodId, Param param)
			throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmInvCountingTaskDAO)dao).checkUnPostBill(map);
	}

	@Override
	public ScmInvCountingTask2 confirm(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException {
		if(scmInvCountingTask!=null) {
			scmInvCountingTask = this.selectDirect(scmInvCountingTask.getTaskId(), param);
			if(!StringUtils.equals("B", scmInvCountingTask.getStatus())) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvCountingTask.confirm.error", new String[] {scmInvCountingTask.getTaskNo()}));
			}
			scmInvCountingTask.setStatus("E");
			scmInvCountingTask.setConfirmedBy(param.getUsrCode());
			return this.updateDirect(scmInvCountingTask, param);
		}
		return null;
	}
	
	@Override
	public ScmInvCountingTask2 generateCountingCheck(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException {
		List<String> list=new ArrayList<String>(Arrays.asList(new String[8]));
		HashMap<String, Object> map=new HashMap<String,Object>();
		if (scmInvCountingTask != null) {
			ScmInvCountingTask2 task2 = this.select(scmInvCountingTask.getTaskId(), param);
			if (task2 !=null) {
				if (!StringUtils.equals("A", task2.getStatus())) {
					throw new AppException("iscm.inventorymanage.scminvcountingtask.generatecounting.notAccess",new String[]{task2.getTaskNo()});
				}
			}else {
				throw new AppException("iscm.inventorymanage.scminvcountingtask.generatecounting.taskIsNull");
			}
			// 取出盘点仓库
			List<Long> wareHouseIdList = new ArrayList<>();
			List<ScmInvCountingListWarehouse2> scmInvCountingListWarehouseList = scmInvCountingListWarehouseBiz
					.selectByTaskId(scmInvCountingTask.getTaskId(), param);
			if (scmInvCountingTask.isAssignLocation()) {
				if (scmInvCountingListWarehouseList != null && !scmInvCountingListWarehouseList.isEmpty()) {
					for (ScmInvCountingListWarehouse2 scmInvCountingListWarehouse : scmInvCountingListWarehouseList) {
						if (scmInvCountingListWarehouse.isSelectOrExclude() && !wareHouseIdList.contains(scmInvCountingListWarehouse.getWareHouseId())) {
							wareHouseIdList.add(scmInvCountingListWarehouse.getWareHouseId());
						}
					}
				}
			} else {
				List<ScmInvWareHouse> scmInvWareHouseList = new ArrayList<>();
				if (StringUtils.isNotBlank(scmInvCountingTask.getFromWhNo())
						|| StringUtils.isNotBlank(scmInvCountingTask.getToWhNo())) {
					scmInvWareHouseList = scmInvWareHouseBiz.selectByOrgUnitNo(scmInvCountingTask.getOrgUnitNo(),
							scmInvCountingTask.getFromWhNo(), scmInvCountingTask.getToWhNo(), param);
				} else {
					scmInvWareHouseList = scmInvWareHouseBiz.selectByOrgUnitNo(scmInvCountingTask.getOrgUnitNo(), null,
							null, param);
				}
				if (scmInvWareHouseList != null && !scmInvWareHouseList.isEmpty()) {
					int i, j;
					for (i = 0; i < scmInvWareHouseList.size(); i++) {
						boolean flag = false;
						if (scmInvCountingListWarehouseList != null && !scmInvCountingListWarehouseList.isEmpty()) {
							for (j = 0; j < scmInvCountingListWarehouseList.size(); j++) {
								if (!scmInvCountingListWarehouseList.get(j).isSelectOrExclude()
										&& scmInvCountingListWarehouseList.get(j)
												.getWareHouseId() == scmInvWareHouseList.get(i).getId()) {
									flag = true;
								}
							}
						}
						if (!flag) {
							wareHouseIdList.add(scmInvWareHouseList.get(i).getId());
						}
					}
				}
			}
			StringBuffer wareHouseIds = new StringBuffer("");
			for(long wareHouseId:wareHouseIdList){
				if(StringUtils.isNotBlank(wareHouseIds.toString()))
					wareHouseIds.append(",");
				wareHouseIds.append(wareHouseId);
			}
			map.put("orgUnitNo",scmInvCountingTask.getOrgUnitNo());
			map.put("wareHouseIds",wareHouseIds.toString());
			map.put("bizDate",FormatUtils.fmtDate(scmInvCountingTask.getBizDate()));
			//采购入库
			List<Map<String,Object>> scmInvPurInWareshBillMaps=scmInvPurInWarehsBillBiz.countUnPostBill(map);
			if(scmInvPurInWareshBillMaps!=null && scmInvPurInWareshBillMaps.size()>0){
				long total=0;
				List<String> wrNoList=new ArrayList<>();
				for(int i=0;i<scmInvPurInWareshBillMaps.size();i++){
					total+=(long)scmInvPurInWareshBillMaps.get(i).get("number");
					String wrNo=scmInvPurInWareshBillMaps.get(i).get("data")==null?"":scmInvPurInWareshBillMaps.get(i).get("data").toString();
					if(i==0){
						if(StringUtils.isNotBlank(wrNo)){
							String[] wrNoArrays=wrNo.split(",");
							wrNoList=new ArrayList(Arrays.asList(wrNoArrays));
							
						}
					}else if(wrNoList.size()<5){
						if(StringUtils.isNotBlank(wrNo)){
							String[] wrNoArrays=wrNo.split(",");
							for(int j=0;j<wrNoArrays.length;j++){
								if(wrNoList.size()<5){
									wrNoList.add(wrNoArrays[j]);
								}else{
									break;
								}							
							}							
						}
					}
				}
				if(total>0)
					list.add(0,Message.getMessage(param.getLang(), "iscm.warehouseinbusiness.scmInvPurInWarehsBill.generateCounting.noCheck.number", new String[] {String.valueOf(total),wrNoList.toString()}));
			}
			//其它入库
			List<Map<String,Object>> scmInvOtherInWareshBillMaps=scmInvOtherInWarehsBillBiz.countUnPostBill(map);
			if(scmInvOtherInWareshBillMaps!=null && scmInvOtherInWareshBillMaps.size()>0){
				long total=0;
				List<String> wrNoList=new ArrayList<>();
				for(int i=0;i<scmInvOtherInWareshBillMaps.size();i++){
					total+=(long)scmInvOtherInWareshBillMaps.get(i).get("number");
					String wrNo=scmInvOtherInWareshBillMaps.get(i).get("data")==null?"":scmInvOtherInWareshBillMaps.get(i).get("data").toString();
					if(i==0){
						if(StringUtils.isNotBlank(wrNo)){
							String[] wrNoArrays=wrNo.split(",");
							wrNoList=new ArrayList(Arrays.asList(wrNoArrays));
						}
					}else if(wrNoList.size()<5){
						if(StringUtils.isNotBlank(wrNo)){
							String[] wrNoArrays=wrNo.split(",");
							for(int j=0;j<wrNoArrays.length;j++){
								if(wrNoList.size()<5){
									wrNoList.add(wrNoArrays[j]);
								}else{
									break;
								}							
							}							
						}
					}
				}
				if(total>0)
					list.add(1,Message.getMessage(param.getLang(), "iscm.warehouseinbusiness.scmInvOtherInWarehsBill.generateCounting.noCheck.number", new String[] {String.valueOf(total),wrNoList.toString()}));
			}
			//调拨入库
			List<Map<String,Object>> scmInvMoveInWareshBillMaps=scmInvMoveInWarehsBillBiz.countUnPostBill(map);
			if(scmInvMoveInWareshBillMaps!=null && scmInvMoveInWareshBillMaps.size()>0){
				long total=0;
				List<String> wrNoList=new ArrayList<>();
				for(int i=0;i<scmInvMoveInWareshBillMaps.size();i++){
					total+=(long)scmInvMoveInWareshBillMaps.get(i).get("number");
					String wrNo=scmInvMoveInWareshBillMaps.get(i).get("data")==null?"":scmInvMoveInWareshBillMaps.get(i).get("data").toString();
					if(i==0){
						if(StringUtils.isNotBlank(wrNo)){
							String[] wrNoArrays=wrNo.split(",");
							wrNoList=new ArrayList(Arrays.asList(wrNoArrays));
						}
					}else if(wrNoList.size()<5){
						if(StringUtils.isNotBlank(wrNo)){
							String[] wrNoArrays=wrNo.split(",");
							for(int j=0;j<wrNoArrays.length;j++){
								if(wrNoList.size()<5){
									wrNoList.add(wrNoArrays[j]);
								}else{
									break;
								}							
							}							
						}
					}
				}
				if(total>0)
					list.add(2,Message.getMessage(param.getLang(), "iscm.warehouseinbusiness.scmInvMoveInWarehsBill.generateCounting.noCheck.number", new String[] {String.valueOf(total),wrNoList.toString()}));
			}
			//领料出库
			List<Map<String,Object>> scmInvMaterialReqBillMaps=scmInvMaterialReqBillBiz.countUnPostBill(map);
			if(scmInvMaterialReqBillMaps!=null && scmInvMaterialReqBillMaps.size()>0){
				long total=0;
				List<String> otNoList=new ArrayList<>();
				for(int i=0;i<scmInvMaterialReqBillMaps.size();i++){
					total+=(long)scmInvMaterialReqBillMaps.get(i).get("number");
					String wrNo=scmInvMaterialReqBillMaps.get(i).get("data")==null?"":scmInvMaterialReqBillMaps.get(i).get("data").toString();
					if(i==0){
						if(StringUtils.isNotBlank(wrNo)){
							String[] wrNoArrays=wrNo.split(",");
							otNoList=new ArrayList(Arrays.asList(wrNoArrays));
						}
					}else if(otNoList.size()<5){
						if(StringUtils.isNotBlank(wrNo)){
							String[] wrNoArrays=wrNo.split(",");
							for(int j=0;j<wrNoArrays.length;j++){
								if(otNoList.size()<5){
									otNoList.add(wrNoArrays[j]);
								}else{
									break;
								}							
							}							
						}
					}
				}
				if(total>0)
					list.add(3,Message.getMessage(param.getLang(), "iscm.warehouseoutbusiness.scmInvMaterialReqBill.generateCounting.noCheck.number", new String[] {String.valueOf(total),otNoList.toString()}));
			}
			//其它出库
			List<Map<String,Object>> scmInvOtherIssueBillMaps=scmInvOtherIssueBillBiz.countUnPostBill(map);
			if(scmInvOtherIssueBillMaps!=null && scmInvOtherIssueBillMaps.size()>0){
				long total=0;
				List<String> otNoList=new ArrayList<>();
				for(int i=0;i<scmInvOtherIssueBillMaps.size();i++){
					total+=(long)scmInvOtherIssueBillMaps.get(i).get("number");
					String wrNo=scmInvOtherIssueBillMaps.get(i).get("data")==null?"":scmInvOtherIssueBillMaps.get(i).get("data").toString();
					if(i==0){
						if(StringUtils.isNotBlank(wrNo)){
							String[] wrNoArrays=wrNo.split(",");
							otNoList=new ArrayList(Arrays.asList(wrNoArrays));
						}
					}else if(otNoList.size()<5){
						if(StringUtils.isNotBlank(wrNo)){
							String[] wrNoArrays=wrNo.split(",");
							for(int j=0;j<wrNoArrays.length;j++){
								if(otNoList.size()<5){
									otNoList.add(wrNoArrays[j]);
								}else{
									break;
								}							
							}							
						}
					}
				}
				if(total>0)
					list.add(4,Message.getMessage(param.getLang(), "iscm.warehouseoutbusiness.scmInvOtherIssueBill.generateCounting.noCheck.number", new String[] {String.valueOf(total),otNoList.toString()}));
			}
			//销售出库
			List<Map<String,Object>> scmInvSaleIssueBillMaps=scmInvSaleIssueBillBiz.countUnPostBill(map);
			if(scmInvSaleIssueBillMaps!=null && scmInvSaleIssueBillMaps.size()>0){
				long total=0;
				List<String> otNoList=new ArrayList<>();
				for(int i=0;i<scmInvSaleIssueBillMaps.size();i++){
					total+=(long)scmInvSaleIssueBillMaps.get(i).get("number");
					String wrNo=scmInvSaleIssueBillMaps.get(i).get("data")==null?"":scmInvSaleIssueBillMaps.get(i).get("data").toString();
					if(i==0){
						if(StringUtils.isNotBlank(wrNo)){
							String[] wrNoArrays=wrNo.split(",");
							otNoList=new ArrayList(Arrays.asList(wrNoArrays));
						}
					}else if(otNoList.size()<5){
						if(StringUtils.isNotBlank(wrNo)){
							String[] wrNoArrays=wrNo.split(",");
							for(int j=0;j<wrNoArrays.length;j++){
								if(otNoList.size()<5){
									otNoList.add(wrNoArrays[j]);
								}else{
									break;
								}							
							}							
						}
					}
				}
				if(total>0)
					list.add(5,Message.getMessage(param.getLang(), "iscm.warehouseoutbusiness.scmInvSaleIssueBill.generateCounting.noCheck.number", new String[] {String.valueOf(total),otNoList.toString()}));
			}
			//调拨出库
			List<Map<String,Object>> scmInvMoveIssueBillMaps=scmInvMoveIssueBillBiz.countUnPostBill(map);
			if(scmInvMoveIssueBillMaps!=null && scmInvMoveIssueBillMaps.size()>0){
				long total=0;
				List<String> otNoList=new ArrayList<>();
				for(int i=0;i<scmInvMoveIssueBillMaps.size();i++){
					total+=(long)scmInvMoveIssueBillMaps.get(i).get("number");
					String wrNo=scmInvMoveIssueBillMaps.get(i).get("data")==null?"":scmInvMoveIssueBillMaps.get(i).get("data").toString();
					if(i==0){
						if(StringUtils.isNotBlank(wrNo)){
							String[] wrNoArrays=wrNo.split(",");
							otNoList=new ArrayList(Arrays.asList(wrNoArrays));
						}
					}else if(otNoList.size()<5){
						if(StringUtils.isNotBlank(wrNo)){
							String[] wrNoArrays=wrNo.split(",");
							for(int j=0;j<wrNoArrays.length;j++){
								if(otNoList.size()<5){
									otNoList.add(wrNoArrays[j]);
								}else{
									break;
								}							
							}							
						}
					}
				}
				if(total>0)
					list.add(6,Message.getMessage(param.getLang(), "iscm.warehouseoutbusiness.scmInvMoveIssueBill.generateCounting.noCheck.number", new String[] {String.valueOf(total),otNoList.toString()}));
			}
			scmInvCountingTask.setList(list);		
		}
		return scmInvCountingTask;
	}

	@Override
	public ScmInvCountingTask2 generateCostingCheck(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException{
		List<String> list=new ArrayList<String>(Arrays.asList(new String[8]));
		HashMap<String, Object> map=new HashMap<String,Object>();
		if (scmInvCountingTask != null) {
			ScmInvCountingTask2 task2 = this.select(scmInvCountingTask.getTaskId(), param);
			if (task2 !=null) {
				if (!StringUtils.equals("A", task2.getStatus())) {
					throw new AppException("iscm.inventorymanage.scminvcountingtask.generatecounting.notAccess2",new String[]{task2.getTaskNo()});
				}
			}else {
				throw new AppException("iscm.inventorymanage.scminvcountingtask.generatecounting.taskIsNull2");
			}
			//取出盘存部门
			List<String> orgUnitNoList = new ArrayList<>();
			List<ScmInvCountingListUserOrg2> scmInvCountingListUserOrgList = scmInvCountingListUserOrgBiz.selectByTaskId(scmInvCountingTask.getTaskId(), param);
			if (scmInvCountingTask.isAssignLocation()) {
				if (scmInvCountingListUserOrgList != null && !scmInvCountingListUserOrgList.isEmpty()) {
					for (ScmInvCountingListUserOrg2 scmInvCountingListUserOrg : scmInvCountingListUserOrgList) {
						if (scmInvCountingListUserOrg.isSelectOrExclude() && !orgUnitNoList.contains(scmInvCountingListUserOrg.getUseOrgUnitNo())) {
							orgUnitNoList.add(scmInvCountingListUserOrg.getUseOrgUnitNo());
						}
					}
				}
			} else {
				List<String> orgAdminList = new ArrayList<>();
				Page page = new Page();
				page.setModelClass(OrgCostCenter2.class);
				page.setShowCount(Integer.MAX_VALUE);
				ArrayList argList = new ArrayList();
				argList.add("orgUnitNo=" + scmInvCountingTask.getOrgUnitNo());
				List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.queryPage(page, argList, "findChildByOrg", param);
				StringBuilder cstOrgUnitNos = new StringBuilder("");
		        if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()){
		            for(OrgCostCenter2 orgCostCenter:orgCostCenterList){
		                if(StringUtils.isNotBlank(cstOrgUnitNos.toString()))
		                    cstOrgUnitNos.append(",");
		                cstOrgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
		            }
		        }else{
		            cstOrgUnitNos.append("'0'");
		        }
				page.setModelClass(ScmInvStock2.class);
				page.setShowCount(Integer.MAX_VALUE);
		        argList = new ArrayList();
		        argList.add("costOrgUnitNo="+cstOrgUnitNos);
		        List<ScmInvStock2> scmInvStockList = scmInvStockBiz.queryPage(page, argList, "selectDeptByFinPage", param);
		        if (scmInvStockList != null && !scmInvStockList.isEmpty()) {
		            for (int i = 0; i < scmInvStockList.size(); i++) {
		                boolean flag = true;
		                if (StringUtils.isNotBlank(scmInvCountingTask.getFromDeptNo())) {
		                    flag = false;
		                    if ((scmInvStockList.get(i).getOrgUnitNo()).compareToIgnoreCase(scmInvCountingTask.getFromDeptNo()) >= 0) {
		                        flag = true;
		                    }
		                }
		                if (StringUtils.isNotBlank(scmInvCountingTask.getToDeptNo())) {
		                    flag = false;
		                    if ((scmInvStockList.get(i).getOrgUnitNo()).compareToIgnoreCase(scmInvCountingTask.getToDeptNo()) <= 0) {
		                        flag = true;
		                    }
		                }
		                if (flag) {
		                	orgAdminList.add(scmInvStockList.get(i).getOrgUnitNo());
		                }
		            }
		        }
				if (orgAdminList != null && !orgAdminList.isEmpty()) {
					int i, j;
					for (i = 0; i < orgAdminList.size(); i++) {
						boolean flag = false;
						if (scmInvCountingListUserOrgList != null && !scmInvCountingListUserOrgList.isEmpty()) {
							for (j = 0; j < scmInvCountingListUserOrgList.size(); j++) {
								if (!scmInvCountingListUserOrgList.get(j).isSelectOrExclude()
										&& scmInvCountingListUserOrgList.get(j)
												.getUseOrgUnitNo().equals(orgAdminList.get(i))) {
									flag = true;
								}
							}
						}
						if (!flag) {
							orgUnitNoList.add(orgAdminList.get(i));
						}
					}
				}
			}
			StringBuffer depts = new StringBuffer("");
			for(String dept:orgUnitNoList) {
				if(StringUtils.isNotBlank(depts.toString()))
					depts.append(",");
				depts.append("'").append(dept).append("'");
			}
			
			map.put("useOrgUnitNos",depts.toString());
			map.put("bizDate",FormatUtils.fmtDate(scmInvCountingTask.getBizDate()));
			//采购入库
			List<Map<String,Object>> scmInvPurInWareshBillMaps=scmInvPurInWarehsBillBiz.countCostUnPostBill(map);
			if(scmInvPurInWareshBillMaps!=null && scmInvPurInWareshBillMaps.size()>0){
				long total=0;
				List<String> wrNoList=new ArrayList<>();
				for(int i=0;i<scmInvPurInWareshBillMaps.size();i++){
					total+=(long)scmInvPurInWareshBillMaps.get(i).get("number");
					String wrNo=scmInvPurInWareshBillMaps.get(i).get("data")==null?"":scmInvPurInWareshBillMaps.get(i).get("data").toString();
					if(i==0){
						if(StringUtils.isNotBlank(wrNo)){
							String[] wrNoArrays=wrNo.split(",");
							wrNoList=new ArrayList(Arrays.asList(wrNoArrays));
						}
					}else if(wrNoList.size()<5){
						if(StringUtils.isNotBlank(wrNo)){
							String[] wrNoArrays=wrNo.split(",");
							for(int j=0;j<wrNoArrays.length;j++){
								if(wrNoList.size()<5){
									wrNoList.add(wrNoArrays[j]);
								}else{
									break;
								}							
							}							
						}
					}
				}
				if(total>0)
					list.add(0,Message.getMessage(param.getLang(), "iscm.warehouseinbusiness.scmInvPurInWarehsBill.generateCostingCheck.noCheck.number", new String[] {String.valueOf(total),wrNoList.toString()}));
			}
			//调拨入库
			List<Map<String,Object>> scmInvMoveInWareshBillMaps=scmInvMoveInWarehsBillBiz.countCostUnPostBill(map);
			if(scmInvMoveInWareshBillMaps!=null && scmInvMoveInWareshBillMaps.size()>0){
				long total=0;
				List<String> wrNoList=new ArrayList<>();
				for(int i=0;i<scmInvMoveInWareshBillMaps.size();i++){
					total+=(long)scmInvMoveInWareshBillMaps.get(i).get("number");
					String wrNo=scmInvMoveInWareshBillMaps.get(i).get("data")==null?"":scmInvMoveInWareshBillMaps.get(i).get("data").toString();
					if(i==0){
						if(StringUtils.isNotBlank(wrNo)){
							String[] wrNoArrays=wrNo.split(",");
							wrNoList=new ArrayList(Arrays.asList(wrNoArrays));
						}
					}else if(wrNoList.size()<5){
						if(StringUtils.isNotBlank(wrNo)){
							String[] wrNoArrays=wrNo.split(",");
							for(int j=0;j<wrNoArrays.length;j++){
								if(wrNoList.size()<5){
									wrNoList.add(wrNoArrays[j]);
								}else{
									break;
								}							
							}							
						}
					}
				}
				if(total>0)
					list.add(1,Message.getMessage(param.getLang(), "iscm.warehouseinbusiness.scmInvMoveInWarehsBill.generateCostingCheck.noCheck.number", new String[] {String.valueOf(total),wrNoList.toString()}));
			}
			//领料出库
			List<Map<String,Object>> scmInvMaterialReqBillMaps=scmInvMaterialReqBillBiz.countCostUnPostBill(map);
			if(scmInvMaterialReqBillMaps!=null && scmInvMaterialReqBillMaps.size()>0){
				long total=0;
				List<String> otNoList=new ArrayList<>();
				for(int i=0;i<scmInvMaterialReqBillMaps.size();i++){
					total+=(long)scmInvMaterialReqBillMaps.get(i).get("number");
					String wrNo=scmInvMaterialReqBillMaps.get(i).get("data")==null?"":scmInvMaterialReqBillMaps.get(i).get("data").toString();
					if(i==0){
						if(StringUtils.isNotBlank(wrNo)){
							String[] wrNoArrays=wrNo.split(",");
							otNoList=new ArrayList(Arrays.asList(wrNoArrays));
						}
					}else if(otNoList.size()<5){
						if(StringUtils.isNotBlank(wrNo)){
							String[] wrNoArrays=wrNo.split(",");
							for(int j=0;j<wrNoArrays.length;j++){
								if(otNoList.size()<5){
									otNoList.add(wrNoArrays[j]);
								}else{
									break;
								}							
							}							
						}
					}
				}
				if(total>0)
					list.add(2,Message.getMessage(param.getLang(), "iscm.warehouseoutbusiness.scmInvMaterialReqBill.generateCostingCheck.noCheck.number", new String[] {String.valueOf(total),otNoList.toString()}));
			}
			//成本中心报损
			List<Map<String,Object>> scmCstFrmLossMaps=scmCstFrmLossBiz.countCostUnPostBill(map);
			if(scmCstFrmLossMaps!=null && scmCstFrmLossMaps.size()>0){
				long total=0;
				List<String> billNoList=new ArrayList<>();
				for(int i=0;i<scmCstFrmLossMaps.size();i++){
					total+=(long)scmCstFrmLossMaps.get(i).get("number");
					String billNo=scmCstFrmLossMaps.get(i).get("data")==null?"":scmCstFrmLossMaps.get(i).get("data").toString();
					if(i==0){
						if(StringUtils.isNotBlank(billNo)){
							String[] billNoArrays=billNo.split(",");
							billNoList=new ArrayList(Arrays.asList(billNoArrays));
						}
					}else if(billNoList.size()<5){
						if(StringUtils.isNotBlank(billNo)){
							String[] billNoArrays=billNo.split(",");
							for(int j=0;j<billNoArrays.length;j++){
								if(billNoList.size()<5){
									billNoList.add(billNoArrays[j]);
								}else{
									break;
								}							
							}							
						}
					}
				}
				if(total>0)
					list.add(3,Message.getMessage(param.getLang(), "iscm.cstbusiness.scmCstFrmLoss.generateCostingCheck.noCheck.number", new String[] {String.valueOf(total),billNoList.toString()}));
			}
			//成本转移单
			List<Map<String,Object>> scmInvMoveBillMaps=scmInvMoveBillBiz.countUnPostBill(map);
			if(scmInvMoveBillMaps!=null && scmInvMoveBillMaps.size()>0){
				long total=0;
				List<String> wtNoList=new ArrayList<>();
				for(int i=0;i<scmInvMoveBillMaps.size();i++){
					total+=(long)scmInvMoveBillMaps.get(i).get("number");
					String wtNo=scmInvMoveBillMaps.get(i).get("data")==null?"":scmInvMoveBillMaps.get(i).get("data").toString();
					if(i==0){
						if(StringUtils.isNotBlank(wtNo)){
							String[] wtNoArrays=wtNo.split(",");
							wtNoList=new ArrayList(Arrays.asList(wtNoArrays));
						}
					}else if(wtNoList.size()<5){
						if(StringUtils.isNotBlank(wtNo)){
							String[] wtNoArrays=wtNo.split(",");
							for(int j=0;j<wtNoArrays.length;j++){
								if(wtNoList.size()<5){
									wtNoList.add(wtNoArrays[j]);
								}else{
									break;
								}							
							}							
						}
					}
				}
				if(total>0)
					list.add(4,Message.getMessage(param.getLang(), "iscm.cstbusiness.scmInvMoveBill.generateCounting.noCheck.number", new String[] {String.valueOf(total),wtNoList.toString()}));
			}
			//部门期初结存
			List<Map<String,Object>> scmCstInitBillList =scmCstInitBillBiz.countUnPostBill(map);
			if (scmCstInitBillList != null && !scmCstInitBillList.isEmpty()) {
				long total=0;
				List<String> wtNoList=new ArrayList<>();
				for(int i=0;i<scmCstInitBillList.size();i++){
					total+=(long)scmCstInitBillList.get(i).get("number");
					String wtNo=scmCstInitBillList.get(i).get("data")==null?"":scmCstInitBillList.get(i).get("data").toString();
					if(i==0){
						if(StringUtils.isNotBlank(wtNo)){
							String[] wtNoArrays=wtNo.split(",");
							wtNoList=new ArrayList(Arrays.asList(wtNoArrays));
						}
					}else if(wtNoList.size()<5){
						if(StringUtils.isNotBlank(wtNo)){
							String[] wtNoArrays=wtNo.split(",");
							for(int j=0;j<wtNoArrays.length;j++){
								if(wtNoList.size()<5){
									wtNoList.add(wtNoArrays[j]);
								}else{
									break;
								}							
							}							
						}
					}
				}
				if(total>0)
					list.add(4,Message.getMessage(param.getLang(), "iscm.warehouseinbusiness.ScmCstInitBill.generateCostingCheck.noCheck.number", new String[] {String.valueOf(total),wtNoList.toString()}));
			}
			//成本中心耗用
			List<Map<String,Object>> scmInvCostConsumeList = scmInvCostConsumeBiz.countUnPostBill(map);
			if (scmInvCostConsumeList != null && !scmInvCostConsumeList.isEmpty()) {
				long total=0;
				List<String> wtNoList=new ArrayList<>();
				for(int i=0;i<scmInvCostConsumeList.size();i++){
					total+=(long)scmInvCostConsumeList.get(i).get("number");
					String wtNo=scmInvCostConsumeList.get(i).get("data")==null?"":scmInvCostConsumeList.get(i).get("data").toString();
					if(i==0){
						if(StringUtils.isNotBlank(wtNo)){
							String[] wtNoArrays=wtNo.split(",");
							wtNoList=new ArrayList(Arrays.asList(wtNoArrays));
						}
					}else if(wtNoList.size()<5){
						if(StringUtils.isNotBlank(wtNo)){
							String[] wtNoArrays=wtNo.split(",");
							for(int j=0;j<wtNoArrays.length;j++){
								if(wtNoList.size()<5){
									wtNoList.add(wtNoArrays[j]);
								}else{
									break;
								}							
							}							
						}
					}
				}
				if(total>0)
					list.add(4,Message.getMessage(param.getLang(), "iscm.warehouseinbusiness.ScmInvCostConsumeBiz.generateCostingCheck.noCheck.number", new String[] {String.valueOf(total),wtNoList.toString()}));
			}
			scmInvCountingTask.setList(list);
		}
		return scmInvCountingTask;
	}

	@Override
	public ScmInvCountingTask2 queryByTaskNo(String object, Param createParam) throws AppException {
		if (StringUtils.isNotBlank(object)) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("TaskNo", object);
			map.put("controlUnitNo", createParam.getControlUnitNo());
			return ((ScmInvCountingTaskDAO) dao).queryByTaskNo(map);
		}
		return null;
	}

	@Override
	public DataSyncResult dataSync(InvCountingCostListParams invCountingCostListParam,List<ScmInvCountingTask2> scmInvCountingTask2s, Param param) throws AppException {
		DataSyncResult dataSyncResult = new DataSyncResult();
		if(StringUtils.isBlank(invCountingCostListParam.getTaskNo())&& StringUtils.equals(invCountingCostListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billNo.notExist");
		}
		if(StringUtils.isBlank(invCountingCostListParam.getPostStatus())&& StringUtils.equals(invCountingCostListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billStatus.notExist");
		}
		if(StringUtils.isBlank(invCountingCostListParam.getUseOrgUnitNo())&& StringUtils.equals(invCountingCostListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billOrgUnitNo.notExist");
		}
		if(invCountingCostListParam.getBizDate() == null&& StringUtils.equals(invCountingCostListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billBizDate.notExist");
		}
		if((invCountingCostListParam.getDetailList() == null || invCountingCostListParam.getDetailList().isEmpty())&& StringUtils.equals(invCountingCostListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billEntry.notExist");
		}
		ScmInvCountingTask2 scmInvCountingTaskAdd = null;
		for(ScmInvCountingTask2 scmInvCountingTask:scmInvCountingTask2s) {
			if(StringUtils.equals(invCountingCostListParam.getTaskNo(), scmInvCountingTask.getTaskNo())) {
				//盘存表
				List<ScmInvCountingCostCenter2> scmInvCountingCostCenters = scmInvCountingCostCenterBiz.selectByTaskNo(scmInvCountingTask.getTaskNo(), param.getControlUnitNo(), param);
				ScmInvCountingCostCenter2 scmInvCountingCostCenter = new ScmInvCountingCostCenter2();
				if(scmInvCountingCostCenters != null && !scmInvCountingCostCenters.isEmpty()) {
					scmInvCountingCostCenter = scmInvCountingCostCenters.get(0);
				}
				//删除				
				if(StringUtils.equals(invCountingCostListParam.getDelete(),"Y")) {
					scmInvCountingCostCenterBiz.delete(scmInvCountingCostCenter, param);
					delete(scmInvCountingTask, param);
					dataSyncResult.setNo(invCountingCostListParam.getTaskNo());
					dataSyncResult.setErrCode("0");
					return dataSyncResult;
				}
				//获取期间	
				PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(scmInvCountingTask.getBizDate(), param);
				if(periodCalendar==null){
					throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
				}
				//盘存表
				if(scmInvCountingCostCenter != null && !scmInvCountingCostCenters.isEmpty()) {
					scmInvCountingCostCenter.setUseOrgUnitNo(invCountingCostListParam.getUseOrgUnitNo());
					List<OrgCostCenter2> outOrgCostCenterList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOCOST,scmInvCountingCostCenter.getUseOrgUnitNo(), false, null, param);
					if (outOrgCostCenterList == null || outOrgCostCenterList.isEmpty()) {
						throw new AppException("iscm.inventorymanage.cstbusiness.service.impl.ScmInvMoveBillBizImpl.noOutCst");
					}
					scmInvCountingCostCenter.setOrgUnitNo(outOrgCostCenterList.get(0).getOrgUnitNo());
					//盘存明细
					ArrayList<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryAdd = new ArrayList<ScmInvCountingCostCenterEntry2>();
					for(InvCountingCostDetailParams invCountingCostDetailParam :invCountingCostListParam.getDetailList()) {
						ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry = new ScmInvCountingCostCenterEntry2(true);
						if(!scmMatrialMap.containsKey(invCountingCostDetailParam.getItemNo())) {
							ScmMaterial2 selectByItemNo = scmMaterialBiz.selectByItemNo(param.getControlUnitNo(), invCountingCostDetailParam.getItemNo(), param);
							if(selectByItemNo == null)
								throw new AppException("iscm.api.datePut.billItemNo.notExist");
							scmMatrialMap.put(selectByItemNo.getItemNo(), selectByItemNo.getId());
						}
						BeanUtils.copyProperties(invCountingCostDetailParam, scmInvCountingCostCenterEntry);
						scmInvCountingCostCenterEntry.setItemId(scmMatrialMap.get(invCountingCostDetailParam.getItemNo()));
						if(!scmUnitMap.containsKey(scmInvCountingCostCenterEntry.getItemId())) {
							ScmMaterialInventory2 selectByItemIdAndOrgUnitNo = scmMaterialInventoryBiz.selectByItemId(scmInvCountingCostCenterEntry.getItemId(), param.getOrgUnitNo(), param.getControlUnitNo(), param);
							if(selectByItemIdAndOrgUnitNo == null)
								throw new AppException("iscm.api.datePut.billUnitNo.notExist");
							scmUnitMap.put(selectByItemIdAndOrgUnitNo.getItemId(), selectByItemIdAndOrgUnitNo.getUnitId());
						}
						scmInvCountingCostCenterEntry.setUnit(scmUnitMap.get(scmInvCountingCostCenterEntry.getItemId()));
						scmInvCountingCostCenterEntry.setTableId(scmInvCountingCostCenter.getTableId());
						//帐存						
						scmInvCountingCostCenterEntry.setAccountAmt(scmInvCountingCostCenterEntry.getAccountQty().multiply(scmInvCountingCostCenterEntry.getPrice()));
						scmInvCountingCostCenterEntry.setAccountTaxAmt(scmInvCountingCostCenterEntry.getAccountQty().multiply(scmInvCountingCostCenterEntry.getTaxPrice()));
						//实存		
						scmInvCountingCostCenterEntry.setAmt(scmInvCountingCostCenterEntry.getQty().multiply(scmInvCountingCostCenterEntry.getPrice()));
						scmInvCountingCostCenterEntry.setTaxAmt(scmInvCountingCostCenterEntry.getQty().multiply(scmInvCountingCostCenterEntry.getTaxPrice()));
						//差异数量
						scmInvCountingCostCenterEntry.setDifferQty(scmInvCountingCostCenterEntry.getQty().subtract(scmInvCountingCostCenterEntry.getAccountQty()));
						scmInvCountingCostCenterEntry.setDifferAmt(scmInvCountingCostCenterEntry.getAmt().subtract(scmInvCountingCostCenterEntry.getAccountAmt()));
						scmInvCountingCostCenterEntry.setDifferTaxAmt(scmInvCountingCostCenterEntry.getTaxAmt().subtract(scmInvCountingCostCenterEntry.getAccountTaxAmt()));
						scmInvCountingCostCenterEntryAdd.add(scmInvCountingCostCenterEntry);
					}
					//2、	过账 》过账 ok
					if(StringUtils.equals(invCountingCostListParam.getPostStatus(), "1")&& "O,C".contains(scmInvCountingTask.getStatus())) {
						throw new AppException("iscm.api.business.datasync.controller.posted");
					//3、	非过账 》过账
					}else if(!"O,C".contains(scmInvCountingTask.getStatus())&& StringUtils.equals(invCountingCostListParam.getPostStatus(), "1")
							) {
						//更新主子表
						refreshData(invCountingCostListParam, scmInvCountingTask,param,periodCalendar);
						scmInvCountingTask.setStatus("O");
						update(scmInvCountingTask, param);
						scmInvCountingCostCenterBiz.update(scmInvCountingCostCenter, param);
						scmInvCountingCostCenterEntryBiz.deleteByTableId(scmInvCountingCostCenter.getTableId(), param);
						scmInvCountingCostCenterEntryBiz.add(scmInvCountingCostCenterEntryAdd, param);
						//期间余额
						scmInvBalBiz.updateByCostCenter(scmInvCountingTask.getTaskId(), scmInvCountingTask.getFinOrgUnitNo(), periodCalendar.getAccountYear(), periodCalendar.getAccountPeriod(), param);
						scmInvBalBiz.addByCostCenter(scmInvCountingTask.getTaskId(), scmInvCountingTask.getFinOrgUnitNo(), 
						        periodCalendar.getPeriodId(), periodCalendar.getAccountYear(), periodCalendar.getAccountPeriod(), param);
						//返回结果					
						dataSyncResult.setNo(invCountingCostListParam.getTaskNo());
						dataSyncResult.setErrCode("0");
						return dataSyncResult;
					//4、	过账 》非过账	ok				
					}else if("O,C".contains(scmInvCountingTask.getStatus()) && StringUtils.equals(invCountingCostListParam.getPostStatus(), "0")){
						throw new AppException("iscm.api.datePut.ScmInvCountingTaskBizImpl.dataSync");
					//5、	非过账 》非过账				
					}else{
						refreshData(invCountingCostListParam, scmInvCountingTask,param,periodCalendar);
						update(scmInvCountingTask, param);
						scmInvCountingCostCenterBiz.update(scmInvCountingCostCenter, param);
						scmInvCountingCostCenterEntryBiz.deleteByTableId(scmInvCountingCostCenter.getTableId(), param);
						scmInvCountingCostCenterEntryBiz.add(scmInvCountingCostCenterEntryAdd, param);
						dataSyncResult.setNo(invCountingCostListParam.getTaskNo());
						dataSyncResult.setErrCode("0");
						return dataSyncResult;
					}
				}else {
					//有任务单号，没有盘存表
					scmInvCountingTaskAdd = scmInvCountingTask;
				}
			}
		}
		if(StringUtils.equals(invCountingCostListParam.getDelete(),"Y")) {
			dataSyncResult.setNo(invCountingCostListParam.getTaskNo());
			dataSyncResult.setErrCode("0");
			return dataSyncResult;
		}
		//1、	新增 √
		if(scmInvCountingTaskAdd == null) {
			scmInvCountingTaskAdd = new ScmInvCountingTask2(true);
			BeanUtils.copyProperties(invCountingCostListParam, scmInvCountingTaskAdd);
			scmInvCountingTaskAdd.setOrgUnitNo(param.getOrgUnitNo());
			List<OrgCompany2> orgCompanylist2 = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.COSTTOFIN, scmInvCountingTaskAdd.getOrgUnitNo(), false, null, param);
			if(orgCompanylist2 != null && !orgCompanylist2.isEmpty()){
				scmInvCountingTaskAdd.setFinOrgUnitNo(orgCompanylist2.get(0).getOrgUnitNo());
			}
			//获取期间	
			PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(scmInvCountingTaskAdd.getBizDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
			}
			scmInvCountingTaskAdd.setStatus("I");
			if(StringUtils.equals(invCountingCostListParam.getPostStatus(),"1")) 
				scmInvCountingTaskAdd.setStatus("O");
			scmInvCountingTaskAdd.setPeriodId(periodCalendar.getPeriodId());
			scmInvCountingTaskAdd.setAccountYear(periodCalendar.getAccountYear());
			scmInvCountingTaskAdd.setAccountPeriod(periodCalendar.getAccountPeriod());
			scmInvCountingTaskAdd.setCountingBegTime(scmInvCountingTaskAdd.getBizDate());
			scmInvCountingTaskAdd.setCountingEndTime(scmInvCountingTaskAdd.getBizDate());
			scmInvCountingTaskAdd.setCostCenter(true);
			scmInvCountingTaskAdd.setAssignLocation(true);
			scmInvCountingTaskAdd.setCreator(param.getUsrCode());
			scmInvCountingTaskAdd.setCreateDate(scmInvCountingTaskAdd.getBizDate());
			scmInvCountingTaskAdd.setControlUnitNo(param.getControlUnitNo());
		}
		((ScmInvCountingTaskDAO) dao).add(scmInvCountingTaskAdd);
		ArrayList<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryAdd2 = new ArrayList<ScmInvCountingCostCenterEntry2>();
		
		ScmInvCountingCostCenter2 scmInvCountingCostCenter =new ScmInvCountingCostCenter2(true);
		scmInvCountingCostCenter.setUseOrgUnitNo(invCountingCostListParam.getUseOrgUnitNo());
		List<OrgCostCenter2> outOrgCostCenterList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOCOST,scmInvCountingCostCenter.getUseOrgUnitNo(), false, null, param);
		if (outOrgCostCenterList == null || outOrgCostCenterList.isEmpty()) {
			throw new AppException("iscm.inventorymanage.cstbusiness.service.impl.ScmInvMoveBillBizImpl.noOutCst");
		}
		scmInvCountingCostCenter.setTaskId(scmInvCountingTaskAdd.getTaskId());
		scmInvCountingCostCenter.setOrgUnitNo(outOrgCostCenterList.get(0).getOrgUnitNo());
		scmInvCountingCostCenter.setBizDate(scmInvCountingTaskAdd.getBizDate());
		scmInvCountingCostCenter.setTaskBegTime(scmInvCountingTaskAdd.getBizDate());
		scmInvCountingCostCenter.setTaskEndTime(scmInvCountingTaskAdd.getBizDate());
		scmInvCountingCostCenter.setCountingPerson(param.getUsrCode());
		scmInvCountingCostCenter.setCreator(param.getUsrCode());
		scmInvCountingCostCenter.setStatus("B");
		scmInvCountingCostCenter.setCreateDate(scmInvCountingTaskAdd.getBizDate());
		scmInvCountingCostCenter.setControlUnitNo(param.getControlUnitNo());
		scmInvCountingCostCenter = scmInvCountingCostCenterBiz.add(scmInvCountingCostCenter, param);
		for(InvCountingCostDetailParams invCountingCostDetailParam :invCountingCostListParam.getDetailList()) {
			ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry = new ScmInvCountingCostCenterEntry2(true);
			BeanUtils.copyProperties(invCountingCostDetailParam, scmInvCountingCostCenterEntry);
			if(!scmMatrialMap.containsKey(invCountingCostDetailParam.getItemNo())) {
				ScmMaterial2 selectByItemNo = scmMaterialBiz.selectByItemNo(param.getControlUnitNo(), invCountingCostDetailParam.getItemNo(), param);
				if(selectByItemNo == null)
					throw new AppException("iscm.api.datePut.billItemNo.notExist");
				scmMatrialMap.put(selectByItemNo.getItemNo(), selectByItemNo.getId());
			}
			scmInvCountingCostCenterEntry.setItemId(scmMatrialMap.get(invCountingCostDetailParam.getItemNo()));
			if(!scmUnitMap.containsKey(scmInvCountingCostCenterEntry.getItemId())) {
				ScmMaterialInventory2 selectByItemIdAndOrgUnitNo = scmMaterialInventoryBiz.selectByItemId(scmInvCountingCostCenterEntry.getItemId(), param.getOrgUnitNo(), param.getControlUnitNo(), param);
				if(selectByItemIdAndOrgUnitNo == null)
					throw new AppException("iscm.api.datePut.billUnitNo.notExist");
				scmUnitMap.put(selectByItemIdAndOrgUnitNo.getItemId(), selectByItemIdAndOrgUnitNo.getUnitId());
			}
			scmInvCountingCostCenterEntry.setUnit(scmUnitMap.get(scmInvCountingCostCenterEntry.getItemId()));
			scmInvCountingCostCenterEntry.setTableId(scmInvCountingCostCenter.getTableId());
			//帐存						
			scmInvCountingCostCenterEntry.setAccountAmt(scmInvCountingCostCenterEntry.getAccountQty().multiply(scmInvCountingCostCenterEntry.getPrice()));
			scmInvCountingCostCenterEntry.setAccountTaxAmt(scmInvCountingCostCenterEntry.getAccountQty().multiply(scmInvCountingCostCenterEntry.getTaxPrice()));
			//实存		
			scmInvCountingCostCenterEntry.setAmt(scmInvCountingCostCenterEntry.getQty().multiply(scmInvCountingCostCenterEntry.getPrice()));
			scmInvCountingCostCenterEntry.setTaxAmt(scmInvCountingCostCenterEntry.getQty().multiply(scmInvCountingCostCenterEntry.getTaxPrice()));
			//差异数量
			scmInvCountingCostCenterEntry.setDifferQty(scmInvCountingCostCenterEntry.getQty().subtract(scmInvCountingCostCenterEntry.getAccountQty()));
			scmInvCountingCostCenterEntry.setDifferAmt(scmInvCountingCostCenterEntry.getAmt().subtract(scmInvCountingCostCenterEntry.getAccountAmt()));
			scmInvCountingCostCenterEntry.setDifferTaxAmt(scmInvCountingCostCenterEntry.getTaxAmt().subtract(scmInvCountingCostCenterEntry.getAccountTaxAmt()));
			scmInvCountingCostCenterEntryAdd2.add(scmInvCountingCostCenterEntry);
		}
		scmInvCountingCostCenterEntryBiz.add(scmInvCountingCostCenterEntryAdd2, param);
		if(StringUtils.equals(invCountingCostListParam.getPostStatus(),"1")) {
			//期间余额
			scmInvBalBiz.updateByCostCenter(scmInvCountingTaskAdd.getTaskId(), scmInvCountingTaskAdd.getFinOrgUnitNo(), scmInvCountingTaskAdd.getAccountYear(), scmInvCountingTaskAdd.getAccountPeriod(), param);
			scmInvBalBiz.addByCostCenter(scmInvCountingTaskAdd.getTaskId(), scmInvCountingTaskAdd.getFinOrgUnitNo(), 
					scmInvCountingTaskAdd.getPeriodId(), scmInvCountingTaskAdd.getAccountYear(), scmInvCountingTaskAdd.getAccountPeriod(), param);
		}
		dataSyncResult.setNo(invCountingCostListParam.getTaskNo());
		dataSyncResult.setErrCode("0");
		return dataSyncResult;
	}

	private void refreshData(InvCountingCostListParams invCountingCostListParam,ScmInvCountingTask2 scmInvCountingTask,Param param,PeriodCalendar periodCalendar) {
		//更新单据，没有update				
		BeanUtils.copyProperties(invCountingCostListParam, scmInvCountingTask);
		scmInvCountingTask.setOrgUnitNo(param.getOrgUnitNo());
		List<OrgCompany2> orgCompanylist2 = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.COSTTOFIN, scmInvCountingTask.getOrgUnitNo(), false, null, param);
		if(orgCompanylist2 != null && !orgCompanylist2.isEmpty()){
			scmInvCountingTask.setFinOrgUnitNo(orgCompanylist2.get(0).getOrgUnitNo());
		}
		scmInvCountingTask.setPeriodId(periodCalendar.getPeriodId());
		scmInvCountingTask.setAccountYear(periodCalendar.getAccountYear());
		scmInvCountingTask.setAccountPeriod(periodCalendar.getAccountPeriod());
		scmInvCountingTask.setCountingBegTime(scmInvCountingTask.getBizDate());
		scmInvCountingTask.setCountingEndTime(scmInvCountingTask.getBizDate());
		scmInvCountingTask.setCostCenter(true);
		scmInvCountingTask.setAssignLocation(true);
		scmInvCountingTask.setCreator(param.getUsrCode());
		scmInvCountingTask.setCreateDate(scmInvCountingTask.getBizDate());
		scmInvCountingTask.setControlUnitNo(param.getControlUnitNo());
	}
	
}

