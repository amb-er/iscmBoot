package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.impl;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.api.business.datareport.params.SupSupplyOfMaterialDetailsParams;
import com.armitage.server.api.business.invpurinwarehs.params.InvPurInWarehsParams;
import com.armitage.server.activity.util.ActivityUtil;
import com.armitage.server.activity.util.AuditMsgUtil;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.OperationParam;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.Param.ParamType;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.ListSortUtil;
import com.armitage.server.iaps.daily.model.Apinvoice2;
import com.armitage.server.iaps.daily.service.ApinvoiceBiz;
import com.armitage.server.iars.basedata.model.Customer2;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.CustomerBiz;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.ScmSupplierRegInvitation;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierRegInvitationBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.common.model.CommonAuditOpinion;
import com.armitage.server.iscm.common.model.CommonEventHistory;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import com.armitage.server.iscm.common.service.CommonEventHistoryBiz;
import com.armitage.server.iscm.common.service.ScmDataCollectionStepStateBiz;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvBalBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.util.ScmWarehouseUtil;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.ScmInvPurInWarehsBillDAO;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSaleIssueBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSaleIssueBillEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurBillDrillResult;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturns2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturnsEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReceiveBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReceiveEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReturnsBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReturnsEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerBiz;
import com.armitage.server.quartz.util.SupplierPlatUtil;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.Employee;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.model.SystemState;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.EmployeeBiz;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgCompanyBiz;
import com.armitage.server.system.service.OrgCostCenterBiz;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.service.SystemStateBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmInvPurInWarehsBillBiz")
public class ScmInvPurInWarehsBillBizImpl extends BaseBizImpl<ScmInvPurInWarehsBill2> implements ScmInvPurInWarehsBillBiz {

	private UsrBiz usrBiz;
	private OrgUnitBiz orgUnitBiz;
	private ScmInvPurInWarehsBillEntryBiz scmInvPurInWarehsBillEntryBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private ScmInvStockBiz scmInvStockBiz;
	private ScmInvBalBiz scmInvBalBiz;
	private OrgCostCenterBiz orgCostCenterBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private ScmPurReceiveEntryBiz scmPurReceiveEntryBiz;
	private PeriodCalendarBiz periodCalendarBiz;
	private ScmPurReturnsEntryBiz scmPurReturnsEntryBiz;
	private SysParamBiz sysParamBiz;
	private SystemStateBiz systemStateBiz;
	private BillTypeBiz billTypeBiz;
	private OrgPurchaseBiz orgPurchaseBiz;
	private ScmPurBuyerBiz scmPurBuyerBiz;
	private ApinvoiceBiz apinvoiceBiz;
	private ScmPurReturnsBiz scmPurReturnsBiz;
	private CustomerBiz customerBiz;
	private ScmInvSaleIssueBillBiz scmInvSaleIssueBillBiz;
	private ScmInvSaleIssueBillEntryBiz scmInvSaleIssueBillEntryBiz;
	private ScmPurReceiveBiz scmPurReceiveBiz;
	private CodeBiz codeBiz;
	private ScmBillPendingBiz scmBillPendingBiz;
	private CommonEventHistoryBiz commonEventHistoryBiz;
	private OrgStorageBiz orgStorageBiz;
	private OrgAdminBiz orgAdminBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private EmployeeBiz employeeBiz;
	private ScmSupplierRegInvitationBiz scmSupplierRegInvitationBiz;
	private ScmDataCollectionStepStateBiz scmDataCollectionStepStateBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	private OrgCompanyBiz orgCompanyBiz;
	
	public void setOrgCompanyBiz(OrgCompanyBiz orgCompanyBiz) {
		this.orgCompanyBiz = orgCompanyBiz;
	}

	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setOrgCostCenterBiz(OrgCostCenterBiz orgCostCenterBiz) {
		this.orgCostCenterBiz = orgCostCenterBiz;
	}

	public void setScmInvStockBiz(ScmInvStockBiz scmInvStockBiz) {
		this.scmInvStockBiz = scmInvStockBiz;
	}

	public void setScmInvBalBiz(ScmInvBalBiz scmInvBalBiz) {
		this.scmInvBalBiz = scmInvBalBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}

	public void setScmInvPurInWarehsBillEntryBiz(ScmInvPurInWarehsBillEntryBiz scmInvPurInWarehsBillEntryBiz) {
		this.scmInvPurInWarehsBillEntryBiz = scmInvPurInWarehsBillEntryBiz;
	}
	
	public void setScmPurReceiveEntryBiz(ScmPurReceiveEntryBiz scmPurReceiveEntryBiz) {
		this.scmPurReceiveEntryBiz = scmPurReceiveEntryBiz;
	}

	public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}

	public void setScmPurReturnsEntryBiz(ScmPurReturnsEntryBiz scmPurReturnsEntryBiz) {
		this.scmPurReturnsEntryBiz = scmPurReturnsEntryBiz;
	}

    public void setSystemStateBiz(SystemStateBiz systemStateBiz) {
        this.systemStateBiz = systemStateBiz;
    }

    public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
        this.billTypeBiz = billTypeBiz;
    }

    public void setOrgPurchaseBiz(OrgPurchaseBiz orgPurchaseBiz) {
		this.orgPurchaseBiz = orgPurchaseBiz;
	}

	public void setScmPurBuyerBiz(ScmPurBuyerBiz scmPurBuyerBiz) {
		this.scmPurBuyerBiz = scmPurBuyerBiz;
	}

	public void setApinvoiceBiz(ApinvoiceBiz apinvoiceBiz) {
        this.apinvoiceBiz = apinvoiceBiz;
    }

	public void setScmPurReturnsBiz(ScmPurReturnsBiz scmPurReturnsBiz) {
		this.scmPurReturnsBiz = scmPurReturnsBiz;
	}

	public void setCustomerBiz(CustomerBiz customerBiz) {
		this.customerBiz = customerBiz;
	}

	public void setScmInvSaleIssueBillBiz(ScmInvSaleIssueBillBiz scmInvSaleIssueBillBiz) {
		this.scmInvSaleIssueBillBiz = scmInvSaleIssueBillBiz;
	}

	public void setScmInvSaleIssueBillEntryBiz(ScmInvSaleIssueBillEntryBiz scmInvSaleIssueBillEntryBiz) {
		this.scmInvSaleIssueBillEntryBiz = scmInvSaleIssueBillEntryBiz;
	}

	public void setScmPurReceiveBiz(ScmPurReceiveBiz scmPurReceiveBiz) {
		this.scmPurReceiveBiz = scmPurReceiveBiz;
	}
	
	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}

	public void setScmBillPendingBiz(ScmBillPendingBiz scmBillPendingBiz) {
		this.scmBillPendingBiz = scmBillPendingBiz;
	}
	
	public void setCommonEventHistoryBiz(CommonEventHistoryBiz commonEventHistoryBiz) {
		this.commonEventHistoryBiz = commonEventHistoryBiz;
	}

	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}
	
	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}

	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}
	
	public void setEmployeeBiz(EmployeeBiz employeeBiz) {
		this.employeeBiz = employeeBiz;
	}

	public void setScmSupplierRegInvitationBiz(ScmSupplierRegInvitationBiz scmSupplierRegInvitationBiz) {
		this.scmSupplierRegInvitationBiz = scmSupplierRegInvitationBiz;
	}

	public void setScmDataCollectionStepStateBiz(ScmDataCollectionStepStateBiz scmDataCollectionStepStateBiz) {
		this.scmDataCollectionStepStateBiz = scmDataCollectionStepStateBiz;
	}

	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}

	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(param.getOrgUnitNo(), param);
		if (orgStorageList != null && !orgStorageList.isEmpty()) {
			StringBuffer orgunitList = new StringBuffer("");
			for (OrgStorage2 orgStorage : orgStorageList) {
				if(StringUtils.isNotBlank(orgunitList.toString()))
					orgunitList.append(",");
				orgunitList.append("'").append(orgStorage.getOrgUnitNo()).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." + ScmInvPurInWarehsBill2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." + ScmInvPurInWarehsBill2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." + ScmInvPurInWarehsBill2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." + ScmInvPurInWarehsBill2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}
	@Override
	protected void afterSelect(ScmInvPurInWarehsBill2 entity, Param param) throws AppException {
		setConvertMap(entity,param);
	}
	
	public void setConvertMap(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill,Param param) throws AppException{
		if (scmInvPurInWarehsBill != null){
			if(StringUtils.isNotBlank(scmInvPurInWarehsBill.getPendingUsr())) {
				StringBuffer usrName = new StringBuffer("");
				String[] usrCodes = StringUtils.split(scmInvPurInWarehsBill.getPendingUsr(), ",");
				for(String usrCode : usrCodes) {
					Usr usr = usrBiz.selectByCode(usrCode, param);
					if(usr != null) {
						if(StringUtils.isNotBlank(usrName.toString())) 
							usrName.append(",");
						usrName.append(usr.getName());
					}
				}
				scmInvPurInWarehsBill.setPendingUsrName(usrName.toString());
			}
			if (scmInvPurInWarehsBill.getVendorId() > 0){
				//供应商
				Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmInvPurInWarehsBill.getVendorId(), param);
				if (scmsupplier != null) {
					scmInvPurInWarehsBill.setConvertMap(ScmInvPurInWarehsBill2.FN_VENDORID, scmsupplier);
					scmInvPurInWarehsBill.setVendorNo(scmsupplier.getVendorNo());
					scmInvPurInWarehsBill.setVendorName(scmsupplier.getVendorName());
				}
			}
			if (StringUtils.isNotBlank(scmInvPurInWarehsBill.getCreator())){
				//制单人
				Usr usr = usrBiz.selectByCode(scmInvPurInWarehsBill.getCreator(), param);
				if (usr != null) {
					scmInvPurInWarehsBill.setConvertMap(ScmInvPurInWarehsBill2.FN_CREATOR, usr);
					scmInvPurInWarehsBill.setCreatorName(usr.getName());
				}
			}
			if (StringUtils.isNotBlank(scmInvPurInWarehsBill.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmInvPurInWarehsBill.getEditor(), param);
				if (usr != null) {
					scmInvPurInWarehsBill.setConvertMap(ScmInvPurInWarehsBill2.FN_EDITOR, usr);
					scmInvPurInWarehsBill.setEditorName(usr.getName());
				}
			}
			if (StringUtils.isNotBlank(scmInvPurInWarehsBill.getChecker())){
				//审核人
				Usr usr = usrBiz.selectByCode(scmInvPurInWarehsBill.getChecker(), param);
				if (usr != null) {
					scmInvPurInWarehsBill.setConvertMap(ScmInvPurInWarehsBill2.FN_CHECKER, usr);
					scmInvPurInWarehsBill.setCheckerName(usr.getName());
				}
			}
			if (StringUtils.isNotBlank(scmInvPurInWarehsBill.getBillType())){
	            //来源单类型
	            BillType2 billType = billTypeBiz.selectByOrgAndCode(scmInvPurInWarehsBill.getFinOrgUnitNo(), scmInvPurInWarehsBill.getBillType(), param);
	            if (billType != null) {
	            	scmInvPurInWarehsBill.setConvertMap(ScmInvSaleOrder.FN_BILLTYPE, billType);
	            }
	        }
			if (StringUtils.isNotBlank(scmInvPurInWarehsBill.getCreateOrgUnitNo())){
				//制单组织
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvPurInWarehsBill.getCreateOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmInvPurInWarehsBill.setConvertMap(ScmInvPurInWarehsBill2.FN_CREATEORGUNITNO, orgBaseUnit);
				}
			}
			if (StringUtils.isNotBlank(scmInvPurInWarehsBill.getOrgUnitNo())){
				//库存组织
				OrgStorage2 orgStorage = orgStorageBiz.selectByOrgUnitNo(scmInvPurInWarehsBill.getOrgUnitNo(), param);
				if (orgStorage != null) {
					scmInvPurInWarehsBill.setConvertMap(ScmInvPurInWarehsBill2.FN_ORGUNITNO, orgStorage);
					scmInvPurInWarehsBill.setOrgUnitName(orgStorage.getOrgUnitName());
				}
			}
			if (StringUtils.isNotBlank(scmInvPurInWarehsBill.getPurOrgUnitNo())){
				//采购组织
				OrgPurchase2  orgPurchase = orgPurchaseBiz.selectByOrgUnitNo(scmInvPurInWarehsBill.getPurOrgUnitNo(), param);
				if (orgPurchase != null) {
					scmInvPurInWarehsBill.setConvertMap(ScmPurReceive.FN_PURORGUNITNO, orgPurchase);
					scmInvPurInWarehsBill.setPurOrgUnitName(orgPurchase.getOrgUnitName());
				}
			}
			if (scmInvPurInWarehsBill.getBuyerId() > 0){
				//采购员
				ScmPurBuyer2 scmPurBuyer = scmPurBuyerBiz.selectDirect(scmInvPurInWarehsBill.getBuyerId(), param);
				if (scmPurBuyer != null) {
					scmInvPurInWarehsBill.setConvertMap(ScmPurReceive.FN_BUYERID, scmPurBuyer);
					scmInvPurInWarehsBill.setBuyerCode(scmPurBuyer.getBuyerCode());
					scmInvPurInWarehsBill.setBuyerName(scmPurBuyer.getBuyerName());
					Employee employee = employeeBiz.selectDirect(scmPurBuyer.getEmpId(), param);
					if(employee != null && StringUtils.isNotBlank(employee.getCellphone())){
						scmInvPurInWarehsBill.setBuyerPhone(employee.getCellphone());
					}
				}
			}
			if(StringUtils.isNotBlank(scmInvPurInWarehsBill.getStatus())){
				Code code = codeBiz.selectByCategoryAndCode("warehouseStatus", scmInvPurInWarehsBill.getStatus());
				if(code!=null)
					scmInvPurInWarehsBill.setStatusName(code.getName());
			}
			if(StringUtils.isNotBlank(scmInvPurInWarehsBill.getReceiver())){
				Usr usr = usrBiz.selectByCode(scmInvPurInWarehsBill.getReceiver(), param);
				if (usr != null) {
					scmInvPurInWarehsBill.setConvertMap(ScmPurReceive.FN_RECEIVER, usr);
					scmInvPurInWarehsBill.setReceiverName(usr.getName());
				}
			}
			if(StringUtils.isNotBlank(scmInvPurInWarehsBill.getBizType())){
				Code code = codeBiz.selectByCategoryAndCode("wareHouseBizType", scmInvPurInWarehsBill.getBizType());
				if(code!=null)
					scmInvPurInWarehsBill.setBizTypeName(code.getName());
			}
			
			if(StringUtils.isNotBlank(scmInvPurInWarehsBill.getUseOrgUnitNos())){
				String[] useOrgUnitNos = StringUtils.split(scmInvPurInWarehsBill.getUseOrgUnitNos(), ",");
				StringBuffer useOrgUnitName = new StringBuffer("");
				for(String useOrgUnitNO : useOrgUnitNos) {
					OrgAdmin2 orgAdmin = orgAdminBiz.selectByOrgUnitNo(useOrgUnitNO, param);
					if(orgAdmin != null) {
						if(StringUtils.isNotBlank(useOrgUnitName.toString())) 
							useOrgUnitName.append(",");
						useOrgUnitName.append(orgAdmin.getOrgUnitName());
					}
				}
				scmInvPurInWarehsBill.setUseOrgUnitName(useOrgUnitName.toString());
			}
			
			if(StringUtils.isNotBlank(scmInvPurInWarehsBill.getWareHouseIds())){
				String[] wareHouseIds = StringUtils.split(scmInvPurInWarehsBill.getWareHouseIds(), ",");
				StringBuffer wareHouseName = new StringBuffer("");
				for(String wareHouseId : wareHouseIds) {
					ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(Integer.parseInt(wareHouseId), param);
					if(scmInvWareHouse != null) {
						if(StringUtils.isNotBlank(wareHouseName.toString())) 
							wareHouseName.append(",");
						wareHouseName.append(scmInvWareHouse.getWhName());
					}
				}
				scmInvPurInWarehsBill.setWareHouseName(wareHouseName.toString());
			}
		}
	}
	@Override
	protected void afterFindPage(List list, Page page, Param param)	throws AppException {
		if(list!=null && !list.isEmpty()) {
			for(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill : (List<ScmInvPurInWarehsBill2>)list){
				setConvertMap(scmInvPurInWarehsBill,param);
				if("I,R".contains(scmInvPurInWarehsBill.getStatus())) {
					scmInvPurInWarehsBill.setPendingUsrName("");
				}
			}
		}
	}
	
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = (ScmInvPurInWarehsBill2) bean.getList().get(0);
		if(scmInvPurInWarehsBill != null && scmInvPurInWarehsBill.getWrId() > 0){
			bean.setList2(scmInvPurInWarehsBillEntryBiz.selectByWrId(scmInvPurInWarehsBill.getWrId(), param));
		}
	}

	@Override
	protected void beforeAdd(CommonBean bean, Param param) throws AppException {
		ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = (ScmInvPurInWarehsBill2) bean.getList().get(0);
        if(scmInvPurInWarehsBill != null){
        	String code = CodeAutoCalUtil.getBillCode("InvPurInWhsBill", scmInvPurInWarehsBill, param);
        	scmInvPurInWarehsBill.setWrNo(code);
			//获取期间
			PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(scmInvPurInWarehsBill.getBizDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
			}
			scmInvPurInWarehsBill.setPeriodId(periodCalendar.getPeriodId());
			scmInvPurInWarehsBill.setAccountYear(periodCalendar.getAccountYear());
			scmInvPurInWarehsBill.setAccountPeriod(periodCalendar.getAccountPeriod());
			//新增入库明细
			List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = bean.getList2();
            BigDecimal amt= BigDecimal.ZERO;
            BigDecimal taxAmt= BigDecimal.ZERO;
            List<String> pvNoList = new ArrayList();
            List<String> useOrgList = new ArrayList();
            List<String> whsList = new ArrayList();
			if(scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()){
				for(ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList){
                    amt = amt.add(scmInvPurInWarehsBillEntry.getAmt());
                    taxAmt = taxAmt.add(scmInvPurInWarehsBillEntry.getTaxAmt());
                    if(StringUtils.isNotBlank(scmInvPurInWarehsBillEntry.getPvNo()) && !pvNoList.contains(scmInvPurInWarehsBillEntry.getPvNo()))
                    	pvNoList.add(scmInvPurInWarehsBillEntry.getPvNo());
                    if(StringUtils.isNotBlank(scmInvPurInWarehsBillEntry.getUseOrgUnitNo()) && !useOrgList.contains(scmInvPurInWarehsBillEntry.getUseOrgUnitNo()))
                    	useOrgList.add(scmInvPurInWarehsBillEntry.getUseOrgUnitNo());
                    if(scmInvPurInWarehsBillEntry.getWareHouseId()>0 && !whsList.contains(String.valueOf(scmInvPurInWarehsBillEntry.getWareHouseId())))
                    	whsList.add(String.valueOf(scmInvPurInWarehsBillEntry.getWareHouseId()));
				}
			}
			scmInvPurInWarehsBill.setAmt(amt);
			scmInvPurInWarehsBill.setTaxAmt(taxAmt);
			scmInvPurInWarehsBill.setPvNos(StringUtils.left(StringUtils.join(pvNoList, ","),250));
			scmInvPurInWarehsBill.setUseOrgUnitNos(StringUtils.left(StringUtils.join(useOrgList, ","),250));
			scmInvPurInWarehsBill.setWareHouseIds(StringUtils.left(StringUtils.join(whsList, ","),250));
			BillType2 billType = billTypeBiz.selectByOrgAndCode(scmInvPurInWarehsBill.getFinOrgUnitNo(), "InvPurInWhsBill", param);
			if(billType!=null && billType.isNeedAudit()) {
				if(StringUtils.isBlank(billType.getWorkFlowNo())) {
					throw new AppException(Message.getMessage(param.getLang(), "iscm.workflow.needaudit.notprocess", new String[] {Message.getMessage(param.getLang(),"field.ScmInvPurInWarehsBill")}));
				}
				scmInvPurInWarehsBill.setWorkFlowNo(billType.getWorkFlowNo());
			}
        }
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = (ScmInvPurInWarehsBill2) bean.getList().get(0);
        if(scmInvPurInWarehsBill != null){
			//新增入库明细
			List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = bean.getList2();
			if(scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()){
				StringBuffer itemIds = new StringBuffer();
				for(ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList){
					if(StringUtils.isNotBlank(itemIds.toString()))
						itemIds.append(",");
					itemIds.append(scmInvPurInWarehsBillEntry.getItemId());
				}
				String notRawMaterails = ScmMaterialUtil.getNotRawMaterail(scmInvPurInWarehsBill.getFinOrgUnitNo(), itemIds.toString(), param);
				if(StringUtils.isNotBlank(notRawMaterails))
					throw new AppException(Message.getMessage(param.getLang(), "iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.rawMaterial", new String[]{notRawMaterails.toString()}));
				//生成批次号
				generateLot(scmInvPurInWarehsBill,scmInvPurInWarehsBillEntryList);
	            int lineId = 1;
				for(ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList){
					scmInvPurInWarehsBillEntry.setLineId(lineId);
					scmInvPurInWarehsBillEntry.setWrId(scmInvPurInWarehsBill.getWrId());
					scmInvPurInWarehsBillEntry.setBalanceVendorId(scmInvPurInWarehsBill.getVendorId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvPurInWarehsBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvPurInWarehsBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvPurInWarehsBillEntry.getItemId(), scmInvPurInWarehsBillEntry.getUnit(), param);//库存单位转换系数
					scmInvPurInWarehsBillEntry.setBaseQty(scmInvPurInWarehsBillEntry.getQty().multiply(invConvRate));
					scmInvPurInWarehsBillEntry.setPurOrgUnitNo(scmInvPurInWarehsBill.getPurOrgUnitNo());
					scmInvPurInWarehsBillEntry.setBuyerId(scmInvPurInWarehsBill.getBuyerId());
					scmInvPurInWarehsBillEntry.setPurGroupId(scmInvPurInWarehsBill.getPurGroupId());
					if(StringUtils.isNotBlank(scmInvPurInWarehsBillEntry.getUseOrgUnitNo()) && StringUtils.isBlank(scmInvPurInWarehsBillEntry.getCostOrgUnitNo())) {
						List<OrgCostCenter2> orgCenterList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOCOST, scmInvPurInWarehsBillEntry.getUseOrgUnitNo(), false, null, param);
                        OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvPurInWarehsBillEntry.getUseOrgUnitNo(), param);
            			if(orgCenterList==null || orgCenterList.isEmpty()){
            				throw new AppException(Message.getMessage(param.getLang(),"iscm.purchasemanage.purchasebusiness.orgUnitRelation.error.notcst",new String[]{orgBaseUnit.getOrgUnitName()}));
            			}
            			scmInvPurInWarehsBillEntry.setCostOrgUnitNo(orgCenterList.get(0).getOrgUnitNo());
					}
					//单价数量不为0，金额为0时重算
					if (scmInvPurInWarehsBillEntry.getQty().compareTo(BigDecimal.ZERO) > 0 
                    		&& scmInvPurInWarehsBillEntry.getPrice().compareTo(BigDecimal.ZERO) > 0 
                    		&& scmInvPurInWarehsBillEntry.getAmt().compareTo(BigDecimal.ZERO) == 0) {
                    	int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
                        int qtyPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_QtyPrecision", "2", param));
                        BigDecimal b = (scmInvPurInWarehsBillEntry.getQty()).multiply(scmInvPurInWarehsBillEntry.getPrice()).setScale(amtPrecision, RoundingMode.HALF_UP);
                        scmInvPurInWarehsBillEntry.setAmt(b);
                        BigDecimal c = (scmInvPurInWarehsBillEntry.getQty()).multiply(scmInvPurInWarehsBillEntry.getConvrate()).setScale(qtyPrecision, RoundingMode.HALF_UP);
                        scmInvPurInWarehsBillEntry.setBaseQty(c);
                        BigDecimal d = (scmInvPurInWarehsBillEntry.getQty()).multiply(scmInvPurInWarehsBillEntry.getTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP);
                        scmInvPurInWarehsBillEntry.setTaxAmt(d);
                    }
					scmInvPurInWarehsBillEntryBiz.add(scmInvPurInWarehsBillEntry, param);
	                lineId = lineId+1;
				}
			}
        }
	}

	
	@Override
	protected void beforeUpdate(CommonBean bean, Param param)throws AppException {
		ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = (ScmInvPurInWarehsBill2) bean.getList().get(0);
		if(scmInvPurInWarehsBill != null && scmInvPurInWarehsBill.getWrId() > 0){
			ScmInvPurInWarehsBill2 scmInvPurInWarehsBill2 = this.select(scmInvPurInWarehsBill.getPK(), param);
			if(!StringUtils.equals(scmInvPurInWarehsBill2.getStatus(),"I")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
			//更新入库明细
			List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = bean.getList2();
            BigDecimal amt= BigDecimal.ZERO;
            BigDecimal taxAmt= BigDecimal.ZERO;
            List<String> pvNoList = new ArrayList();
            List<String> useOrgList = new ArrayList();
            List<String> whsList = new ArrayList();
			if(scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()){
				for(ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList){
                    amt = amt.add(scmInvPurInWarehsBillEntry.getAmt());
                    taxAmt = taxAmt.add(scmInvPurInWarehsBillEntry.getTaxAmt());
                    if(StringUtils.isNotBlank(scmInvPurInWarehsBillEntry.getPvNo()) && !pvNoList.contains(scmInvPurInWarehsBillEntry.getPvNo()))
                    	pvNoList.add(scmInvPurInWarehsBillEntry.getPvNo());
                    if(StringUtils.isNotBlank(scmInvPurInWarehsBillEntry.getUseOrgUnitNo()) && !useOrgList.contains(scmInvPurInWarehsBillEntry.getUseOrgUnitNo()))
                    	useOrgList.add(scmInvPurInWarehsBillEntry.getUseOrgUnitNo());
                    if(scmInvPurInWarehsBillEntry.getWareHouseId()>0 && !whsList.contains(String.valueOf(scmInvPurInWarehsBillEntry.getWareHouseId())))
                    	whsList.add(String.valueOf(scmInvPurInWarehsBillEntry.getWareHouseId()));
				}
			}
			scmInvPurInWarehsBill.setAmt(amt);
			scmInvPurInWarehsBill.setTaxAmt(taxAmt);
			scmInvPurInWarehsBill.setPvNos(StringUtils.left(StringUtils.join(pvNoList, ","),250));
			scmInvPurInWarehsBill.setUseOrgUnitNos(StringUtils.left(StringUtils.join(useOrgList, ","),250));
			scmInvPurInWarehsBill.setWareHouseIds(StringUtils.left(StringUtils.join(whsList, ","),250));
		}
		//获取期间
		PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(scmInvPurInWarehsBill.getBizDate(), param);
		if(periodCalendar==null){
			throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
		}
		scmInvPurInWarehsBill.setPeriodId(periodCalendar.getPeriodId());
		scmInvPurInWarehsBill.setAccountYear(periodCalendar.getAccountYear());
		scmInvPurInWarehsBill.setAccountPeriod(periodCalendar.getAccountPeriod());
		
//		BillType2 billType = billTypeBiz.selectByOrgAndCode(scmInvPurInWarehsBill.getFinOrgUnitNo(), "InvPurInWhsBill", param);
//		if(billType!=null && billType.isNeedAudit()) {
//			if(StringUtils.isBlank(billType.getWorkFlowNo())) {
//				throw new AppException(Message.getMessage(param.getLang(), "iscm.workflow.needaudit.notprocess", new String[] {Message.getMessage(param.getLang(),"field.ScmInvPurInWarehsBill")}));
//			}
//			scmInvPurInWarehsBill.setWorkFlowNo(billType.getWorkFlowNo());
//		}
	}

	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = (ScmInvPurInWarehsBill2) bean.getList().get(0);
		if(scmInvPurInWarehsBill != null && scmInvPurInWarehsBill.getWrId() > 0){
			//更新入库明细
			List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = bean.getList2();
			if(scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()){
				StringBuffer itemIds = new StringBuffer();
				for(ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList){
					if(StringUtils.isNotBlank(itemIds.toString()))
						itemIds.append(",");
					itemIds.append(scmInvPurInWarehsBillEntry.getItemId());
				}
				String notRawMaterails = ScmMaterialUtil.getNotRawMaterail(scmInvPurInWarehsBill.getFinOrgUnitNo(), itemIds.toString(), param);
				if(StringUtils.isNotBlank(notRawMaterails))
					throw new AppException(Message.getMessage(param.getLang(), "iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.rawMaterial", new String[]{notRawMaterails.toString()}));
				//生成批次号
				generateLot(scmInvPurInWarehsBill,scmInvPurInWarehsBillEntryList);
                int lineId = 1;
				for(ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList){
					if (StringUtils.equals("I", scmInvPurInWarehsBill.getStatus())) {
						scmInvPurInWarehsBillEntry.setLineId(lineId);
					}
					scmInvPurInWarehsBillEntry.setWrId(scmInvPurInWarehsBill.getWrId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvPurInWarehsBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvPurInWarehsBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvPurInWarehsBillEntry.getItemId(), scmInvPurInWarehsBillEntry.getUnit(), param);//库存单位转换系数
					scmInvPurInWarehsBillEntry.setBaseQty(scmInvPurInWarehsBillEntry.getQty().multiply(invConvRate));
					scmInvPurInWarehsBillEntry.setWrId(scmInvPurInWarehsBill.getWrId());
					scmInvPurInWarehsBillEntry.setBalanceVendorId(scmInvPurInWarehsBill.getVendorId());
					scmInvPurInWarehsBillEntry.setPurOrgUnitNo(scmInvPurInWarehsBill.getPurOrgUnitNo());
					scmInvPurInWarehsBillEntry.setBuyerId(scmInvPurInWarehsBill.getBuyerId());
					scmInvPurInWarehsBillEntry.setPurGroupId(scmInvPurInWarehsBill.getPurGroupId());
					if(StringUtils.isNotBlank(scmInvPurInWarehsBillEntry.getUseOrgUnitNo()) && StringUtils.isBlank(scmInvPurInWarehsBillEntry.getCostOrgUnitNo())) {
						List<OrgCostCenter2> orgCenterList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOCOST, scmInvPurInWarehsBillEntry.getUseOrgUnitNo(), false, null, param);
                        OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvPurInWarehsBillEntry.getUseOrgUnitNo(), param);
            			if(orgCenterList==null || orgCenterList.isEmpty()){
            				throw new AppException(Message.getMessage(param.getLang(),"iscm.purchasemanage.purchasebusiness.orgUnitRelation.error.notcst",new String[]{orgBaseUnit.getOrgUnitName()}));
            			}
            			scmInvPurInWarehsBillEntry.setCostOrgUnitNo(orgCenterList.get(0).getOrgUnitNo());
					}
                    lineId = lineId+1;
                    
                    if (scmInvPurInWarehsBillEntry.getQty().compareTo(BigDecimal.ZERO) > 0 
                    		&& scmInvPurInWarehsBillEntry.getPrice().compareTo(BigDecimal.ZERO) > 0 
                    		&& scmInvPurInWarehsBillEntry.getAmt().compareTo(BigDecimal.ZERO) == 0) {
                    	int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
                        int qtyPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_QtyPrecision", "2", param));
                        BigDecimal b = (scmInvPurInWarehsBillEntry.getQty()).multiply(scmInvPurInWarehsBillEntry.getPrice()).setScale(amtPrecision, RoundingMode.HALF_UP);
                        scmInvPurInWarehsBillEntry.setAmt(b);
                        BigDecimal c = (scmInvPurInWarehsBillEntry.getQty()).multiply(scmInvPurInWarehsBillEntry.getConvrate()).setScale(qtyPrecision, RoundingMode.HALF_UP);
                        scmInvPurInWarehsBillEntry.setBaseQty(c);
                        BigDecimal d = (scmInvPurInWarehsBillEntry.getQty()).multiply(scmInvPurInWarehsBillEntry.getTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP);
                        scmInvPurInWarehsBillEntry.setTaxAmt(d);
                    }
				}
				scmInvPurInWarehsBillEntryBiz.update(scmInvPurInWarehsBill, scmInvPurInWarehsBillEntryList, ScmInvPurInWarehsBillEntry2.FN_WRID, param);
			}
		}
	}
	
	private ScmInvPurInWarehsBill2 updateStatus(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param) throws AppException {
		if(scmInvPurInWarehsBill != null){
			String checker = scmInvPurInWarehsBill.getChecker();
			Date checkDate = scmInvPurInWarehsBill.getCheckDate();
			String status = scmInvPurInWarehsBill.getStatus();
			Date postDate = scmInvPurInWarehsBill.getPostDate();
			String workFlowNo = scmInvPurInWarehsBill.getWorkFlowNo();
			Date submitDate = scmInvPurInWarehsBill.getSubmitDate();
			String submitter = scmInvPurInWarehsBill.getSubmitter();
			String pendingUsr = scmInvPurInWarehsBill.getPendingUsr();
			scmInvPurInWarehsBill = this.selectDirect(scmInvPurInWarehsBill.getWrId(), param);
			scmInvPurInWarehsBill.setChecker(checker);
			scmInvPurInWarehsBill.setCheckDate(checkDate);
			scmInvPurInWarehsBill.setStatus(status);
			scmInvPurInWarehsBill.setPostDate(postDate);
			scmInvPurInWarehsBill.setWorkFlowNo(workFlowNo);
			scmInvPurInWarehsBill.setSubmitDate(submitDate);
			scmInvPurInWarehsBill.setSubmitter(submitter);
			scmInvPurInWarehsBill.setPendingUsr(pendingUsr);
			ScmInvPurInWarehsBill2 scmInvPurInWarehsBill2 = this.updateDirect(scmInvPurInWarehsBill, param);
			return scmInvPurInWarehsBill2;
		}
		return null;
	}
	
	@Override
	public List<String> postBillCheck(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param)
			throws AppException {
	    List<String> msgList = new ArrayList<String>();// 提示列表
	    scmInvPurInWarehsBill = this.selectDirect(scmInvPurInWarehsBill.getWrId(), param);
		if(!StringUtils.equals("A",scmInvPurInWarehsBill.getStatus())) {
			msgList.add(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvPurInWarehsBill.getWrNo()}));
			return msgList;
		}
	    // 期间检查
	    SystemState systemState = systemStateBiz.selectBySystemId(scmInvPurInWarehsBill.getFinOrgUnitNo(), 170, param);
	    if(systemState==null){
            msgList.add(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
            return msgList;
        }
        if (systemState.getCurrentPeriodId() != scmInvPurInWarehsBill.getPeriodId()) {
            msgList.add(Message.getMessage(param.getLang(), 
                    "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmInvPurInWarehsBill.getWrNo()}));
            return msgList;
        }
        
        // 检查冻结仓库
        List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList = ((ScmInvPurInWarehsBillDAO) dao).checkWareHouseFree(scmInvPurInWarehsBill.getWrId());
        if (scmInvPurInWarehsBillList != null && !scmInvPurInWarehsBillList.isEmpty()) {
        	msgList.add(Message.getMessage(param.getLang(), 
                    "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
            return msgList;
//        	for (ScmInvPurInWarehsBill2 scmInvPurInWarehsBill3 : scmInvPurInWarehsBillList) {
//	            // 检查冻结物资
//	            HashMap<String,Object> map = new HashMap<String,Object>();
//	            map.put("taskId", scmInvPurInWarehsBill3.getTaskId());
//	            map.put("wrId", scmInvPurInWarehsBill.getWrId());
//	            int count = ((ScmInvPurInWarehsBillDAO) dao).checkMaterialFree(map);
//	            if (count > 0) {
//	                msgList.add(Message.getMessage(param.getLang(), 
//	                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
//	                return msgList;
//	            }
//        	}
        }
        
        // 检查冻结部门
        List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList2 = ((ScmInvPurInWarehsBillDAO) dao).checkCostCenterFree(scmInvPurInWarehsBill.getWrId());
        
        if (scmInvPurInWarehsBillList2 != null && !scmInvPurInWarehsBillList2.isEmpty()) {
        	msgList.add(Message.getMessage(param.getLang(), 
                    "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingCostcenter"));
            return msgList;
//        	for (ScmInvPurInWarehsBill2 scmInvPurInWarehsBill3 : scmInvPurInWarehsBillList2) {
//	            // 检查冻结物资
//	            HashMap<String,Object> map = new HashMap<String,Object>();
//	            map.put("taskId", scmInvPurInWarehsBill3.getTaskId());
//	            map.put("wrId", scmInvPurInWarehsBill.getWrId());
//	            int count = ((ScmInvPurInWarehsBillDAO) dao).costCenterMaterialFree(map);
//	            if (count > 0) {
//	                msgList.add(Message.getMessage(param.getLang(), 
//	                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingCostcenter"));
//	                return msgList;
//	            }
//        	}
        }
        
		List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = new ArrayList<>();
		if(scmInvPurInWarehsBill != null){
			if(StringUtils.equals("6", scmInvPurInWarehsBill.getBizType()) || StringUtils.equals("8", scmInvPurInWarehsBill.getBizType())){
				//退货过账时检查结存
				scmInvPurInWarehsBillEntryList = scmInvPurInWarehsBillEntryBiz.checkStock(scmInvPurInWarehsBill.getWrId(), param);
				if(scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()){
					for(ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList){
						//if(scmInvPurInWarehsBillEntry.getWareHouseId()==0) {
							//String[] msparam = {scmInvPurInWarehsBillEntry.getItemNo(),scmInvPurInWarehsBillEntry.getItemName()};
							//msgList.add(Message.getMessage(param.getLang(),
									//"iscm.inventorymanage.warehouseoutbusiness.ScmInvMaterialReqBill.postBillCheck.error", msparam));
						//}
						if(StringUtils.isNotBlank(scmInvPurInWarehsBillEntry.getLot())){
							if(scmInvPurInWarehsBillEntry.getPieQty().compareTo(BigDecimal.ZERO)==0) {
								String[] msparam = {scmInvPurInWarehsBillEntry.getItemNo(),scmInvPurInWarehsBillEntry.getItemName(),
										scmInvPurInWarehsBillEntry.getLot(),(scmInvPurInWarehsBillEntry.getQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
										(scmInvPurInWarehsBillEntry.getInvQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()};
								msgList.add(Message.getMessage(param.getLang(),
										"iscm.inventorymanage.warehouseoutbusiness.ScmInvMaterialReqBill.postBillCheck.error", msparam));
							}else {
								String[] msparam = {scmInvPurInWarehsBillEntry.getItemNo(),scmInvPurInWarehsBillEntry.getItemName(),
										scmInvPurInWarehsBillEntry.getLot(),(scmInvPurInWarehsBillEntry.getQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
										(scmInvPurInWarehsBillEntry.getPieQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
										(scmInvPurInWarehsBillEntry.getInvQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
										(scmInvPurInWarehsBillEntry.getInvPieQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()};
								msgList.add(Message.getMessage(param.getLang(),
										"iscm.inventorymanage.warehouseoutbusiness.out.postBillCheck.pieqty.error", msparam));
							}
						}else{
							if(scmInvPurInWarehsBillEntry.getPieQty().compareTo(BigDecimal.ZERO)==0) {
								String[] msparam = {scmInvPurInWarehsBillEntry.getItemNo(),scmInvPurInWarehsBillEntry.getItemName(),
										(scmInvPurInWarehsBillEntry.getQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
										(scmInvPurInWarehsBillEntry.getInvQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()};
								msgList.add(Message.getMessage(param.getLang(),
										"iscm.inventorymanage.warehouseoutbusiness.ScmInvMaterialReqBill.postBillCheck.error2", msparam));
							}else {
								String[] msparam = {scmInvPurInWarehsBillEntry.getItemNo(),scmInvPurInWarehsBillEntry.getItemName(),
										(scmInvPurInWarehsBillEntry.getQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
										(scmInvPurInWarehsBillEntry.getPieQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
										(scmInvPurInWarehsBillEntry.getInvQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
										(scmInvPurInWarehsBillEntry.getInvPieQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()};
								msgList.add(Message.getMessage(param.getLang(),
										"iscm.inventorymanage.warehouseoutbusiness.out.postBillCheck.pieqty.error2", msparam));
							}
						}
					}
				}
			}
			if(msgList == null || msgList.isEmpty()){
				//过账时检查库存组织对应的财务组织是否有维护物资的财务资料
				scmInvPurInWarehsBillEntryList = scmInvPurInWarehsBillEntryBiz.selectByWrId(scmInvPurInWarehsBill.getWrId(), param);
				if(scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()){
					HashMap<String,Object> cacheMap = new HashMap<String,Object>();
					for(ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList){
						String orgunitName ="";
						OrgCompany2 orgCompany = null;
						String orgUnitNo = "";
						if(scmInvPurInWarehsBillEntry.getWareHouseId() == 0){
							orgUnitNo = scmInvPurInWarehsBillEntry.getUseOrgUnitNo();
						}else{
							orgUnitNo = scmInvPurInWarehsBillEntry.getOrgUnitNo();
						}
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
								List<OrgCompany2> orgCompanylist = orgUnitRelationBiz.findToOrgUnitByType(scmInvPurInWarehsBillEntry.getWareHouseId() == 0?OrgUnitRelationType.ADMINTOFIN:OrgUnitRelationType.INVTOFIN, orgUnitNo, false, null, param);
								if(orgCompanylist != null && !orgCompanylist.isEmpty()){
									orgCompany = orgCompanylist.get(0);
									cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgCompany2.class)+"_"+orgUnitNo,orgCompany);
								}
							}
						}
						String[] msparam = {orgunitName,scmInvPurInWarehsBillEntry.getItemName()};
						if(orgCompany==null){
							msgList.add(Message.getMessage(param.getLang(),
									"iscm.inventorymanage.otherinwarehsbill.post.error", msparam));
							continue;
						}else{
							String queryXml = null;
							ScmMaterial2 scmMaterial2 = null;
							if(StringUtils.equals("6", scmInvPurInWarehsBill.getBizType()) || StringUtils.equals("8", scmInvPurInWarehsBill.getBizType())){
								queryXml = "findBySingleFinAllPage";
								scmMaterial2 = scmMaterialBiz.findByFinItemId(param.getControlUnitNo(), orgCompany.getOrgUnitNo(), scmInvPurInWarehsBillEntry.getItemId(), param);
							}else {
								queryXml = "findByFinAllListPage";
								ScmMaterial2 findByFinItemId = scmMaterialBiz.findByFinItemId(param.getControlUnitNo(), orgCompany.getOrgUnitNo(), scmInvPurInWarehsBillEntry.getItemId(), param);
								if (findByFinItemId != null && StringUtils.equals("A", findByFinItemId.getStatus())) {
									scmMaterial2 = findByFinItemId;
								}
							}
							if(scmMaterial2==null){
								if(StringUtils.equalsIgnoreCase("findByFinAllListPage", queryXml)){
									msgList.add(Message.getMessage(param.getLang(),
											"iscm.inventorymanage.purInWarehsBill.post.scmmaterial.error", msparam));
								}else{
									msgList.add(Message.getMessage(param.getLang(),
											"iscm.inventorymanage.otherinwarehsbill.post.error", msparam));
								}
								continue;
							}
						}
					}
				}
			}
		}
		return msgList;
	}

	@Override
	public List<String> postBill(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param) throws AppException {
		List<String> rtnList = new ArrayList<String>();
		if(scmInvPurInWarehsBill != null){
			scmInvPurInWarehsBill = this.selectDirect(scmInvPurInWarehsBill.getWrId(), param);
			rtnList = postBillCheck(scmInvPurInWarehsBill,param);
			if(rtnList!=null && !rtnList.isEmpty())
				return rtnList;
			if(StringUtils.equals("6", scmInvPurInWarehsBill.getBizType()) || StringUtils.equals("8", scmInvPurInWarehsBill.getBizType())){
				//采购退货拆单
				splitBillByOutWarehouse(scmInvPurInWarehsBill,param);
	    		List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = scmInvPurInWarehsBillEntryBiz.selectOutEffectRow(scmInvPurInWarehsBill.getWrId(), param);
				//即时库存
				int updateRow = scmInvStockBiz.updateByPurInWarehsBillOut(scmInvPurInWarehsBill.getWrId(), param);
	            if(updateRow<scmInvPurInWarehsBillEntryList.size()){
	            	throw new AppException(Message.getMessage(param.getLang(),"iscm.inventorymanage.common.post.error.rowsnotequal"));
	    		}
				//期间余额
				scmInvBalBiz.updateByPurInWarehsBillOut(scmInvPurInWarehsBill.getWrId(), param);
				scmInvBalBiz.addByPurInWarehsBillOut(scmInvPurInWarehsBill.getWrId(), param);
				//移动平均即时成本
//				scmInvCostBiz.updateByPurInWarehsBillOut(scmInvPurInWarehsBill.getWrId(), param);
			}else{
				//即时库存
	    		scmInvStockBiz.updateByPurInWarehsBill(scmInvPurInWarehsBill.getWrId(), null, param);
	    		scmInvStockBiz.addByPurInWarehsBill(scmInvPurInWarehsBill.getWrId(), scmInvPurInWarehsBill.getUnitedBillId(), null, param);
				//期间余额
				scmInvBalBiz.updateByPurInWarehsBill(scmInvPurInWarehsBill.getWrId(), param);
				scmInvBalBiz.addByPurInWarehsBill(scmInvPurInWarehsBill.getWrId(), param);
				//移动平均即时成本
//				scmInvCostBiz.updateByPurInWarehsBill(scmInvPurInWarehsBill.getWrId(), param);
//				scmInvCostBiz.addByPurInWarehsBill(scmInvPurInWarehsBill.getWrId(), param);
			}
			if (!(ParamType.Operation.equals(param.getParamType()) && ((OperationParam) param).getOperationId()==629223199L)) {
				scmInvPurInWarehsBill.setCheckDate(CalendarUtil.getDate(param));
				scmInvPurInWarehsBill.setChecker(param.getUsrCode());
				scmInvPurInWarehsBill.setPostDate(CalendarUtil.getDate(param));
			}
			scmInvPurInWarehsBill.setStatus("E");
			int updateRow = this.updatePostedStatus(scmInvPurInWarehsBill, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvPurInWarehsBill.getWrNo()}));
			}

			if (!(ParamType.Operation.equals(param.getParamType()) && ((OperationParam) param).getOperationId()==629223199L)) {
				afterPostBill(scmInvPurInWarehsBill,param);	//过账后生成内部供应商销售退货流程（如是采购退货时）
			}
		}
		return rtnList;
	}
	
	private int updatePostedStatus(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param) {
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("wrId",scmInvPurInWarehsBill.getWrId());
		map.put("checker",scmInvPurInWarehsBill.getChecker());
		map.put("checkDate",scmInvPurInWarehsBill.getCheckDate()==null?null:FormatUtils.fmtDate(scmInvPurInWarehsBill.getCheckDate()));
		map.put("status", scmInvPurInWarehsBill.getStatus());
		map.put("postDate", scmInvPurInWarehsBill.getPostDate()==null?null:FormatUtils.fmtDateTime(scmInvPurInWarehsBill.getPostDate()));
		return ((ScmInvPurInWarehsBillDAO)dao).updatePostedStatus(map);
	}

	private void afterPostBill(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param) {
		if(StringUtils.contains("6", scmInvPurInWarehsBill.getBizType())) {
			//退货时判断供应商是否内部供应商，是则生成销售退货
			Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmInvPurInWarehsBill.getVendorId(),param);
			if(scmsupplier!=null && StringUtils.equals("2",scmsupplier.getRole())) {
				List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = scmInvPurInWarehsBillEntryBiz.selectByWrId(scmInvPurInWarehsBill.getWrId(), param);
		        CommonBean bean = new CommonBean();
		        List<ScmInvSaleIssueBill2> scmInvSaleIssueBillList = new ArrayList<>();
		        List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList = new ArrayList<>();
		        ScmInvSaleIssueBill2 scmInvSaleIssueBill = new ScmInvSaleIssueBill2(true);
		        // 销售出库主表
		        scmInvSaleIssueBill.setStatus("I");
		        scmInvSaleIssueBill.setBizDate(FormatUtils.parseDate(FormatUtils.fmtDate(CalendarUtil.getDate(param))));
		        scmInvSaleIssueBill.setBizType("6");
		        scmInvSaleIssueBill.setBillType("InvPurInWhsBill");
		        scmInvSaleIssueBill.setCurrencyNo(scmInvPurInWarehsBill.getCurrencyNo());
		        // 客户
                HashMap<String, Object> custIdMap = new HashMap<String, Object>();
                custIdMap.put(Customer2.FN_FLAG, 1);
                custIdMap.put(Customer2.FN_ORGUNITNO, scmInvPurInWarehsBill.getOrgUnitNo());
                custIdMap.put(Customer2.FN_CONTROLUNITNO, param.getControlUnitNo());
                List<Customer2> customerList = customerBiz.findAll(custIdMap, param);
                if (customerList == null || customerList.isEmpty()) {
                    throw new AppException("iscm.purchasemanage.purchasebusiness.service.impl.ScmPurOrderBizImpl.generateSaleOrder.noCustId");
                }
		        scmInvSaleIssueBill.setCustId(customerList.get(0).getId());
		        scmInvSaleIssueBill.setExchangeRate(scmInvPurInWarehsBill.getExchangeRate());
		        scmInvSaleIssueBill.setCreator(param.getUsrCode());
		        scmInvSaleIssueBill.setCreateDate(CalendarUtil.getDate(param));
		        scmInvSaleIssueBill.setRemarks(scmInvPurInWarehsBill.getRemarks());
		        scmInvSaleIssueBill.setCreateOrgUnitNo(scmsupplier.getOrgUnitNo());
		        scmInvSaleIssueBill.setControlUnitNo(param.getControlUnitNo());
		        scmInvSaleIssueBill.setOrgUnitNo(scmsupplier.getOrgUnitNo());
		        List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, scmsupplier.getOrgUnitNo(), false, null, param);
		        if(orgCompanyList==null || orgCompanyList.isEmpty()){
		          throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
		        }
		        scmInvSaleIssueBill.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
		        scmInvSaleIssueBillList.add(scmInvSaleIssueBill);
		        bean.setList(scmInvSaleIssueBillList);
		        int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
		        HashMap<Long, List<ScmInvStock2>> stockMap = new HashMap<Long, List<ScmInvStock2>>();
		        // 销售出库明细表
		        for (int i = 0; i < scmInvPurInWarehsBillEntryList.size(); i++) {
		        	ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry = scmInvPurInWarehsBillEntryList.get(i);
		        	Page page = new Page();
                    page.setModelClass(ScmMaterial2.class);
                    page.setShowCount(Integer.MAX_VALUE);
                    page.setSqlCondition("ScmMaterial.id="+scmInvPurInWarehsBillEntry.getItemId());
                    List<String> argList = new ArrayList<String>();
                    argList.add("orgUnitNo=" + scmInvPurInWarehsBill.getFinOrgUnitNo());
                    argList.add("controlUnitNo=" + param.getControlUnitNo());
                    List<ScmMaterial2> materialList = scmMaterialBiz.queryPage(page, argList, "findByFinAllPage", param);
                    String costMethod = "FIFO";
                    if (materialList != null && materialList.size() > 0) {
                        costMethod = materialList.get(0).getCosting();
                    }
		        	List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList2 = scmInvSaleIssueBillEntryBiz.selectSaleIssueByPurOut(scmInvPurInWarehsBillEntry.getId(),costMethod,param);
		        	if(scmInvSaleIssueBillEntryList2!=null && !scmInvSaleIssueBillEntryList2.isEmpty()) {
		        		for(ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry2:scmInvSaleIssueBillEntryList2) {
							if (scmInvPurInWarehsBillEntry.getQty().compareTo(BigDecimal.ZERO) > 0) {
								ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry = setDataFromSaleIssue(scmInvSaleIssueBillEntry2, scmInvPurInWarehsBillEntry, amtPrecision,param);
								scmInvSaleIssueBillEntry.setOrgUnitNo(scmsupplier.getOrgUnitNo());
								scmInvSaleIssueBillEntry.setBalanceCustId(customerList.get(0).getId());
								scmInvSaleIssueBillEntry.setAmt(scmInvSaleIssueBillEntry.getPrice().multiply(scmInvSaleIssueBillEntry.getQty()).setScale(amtPrecision, RoundingMode.HALF_UP));
								scmInvSaleIssueBillEntry.setTaxAmt(scmInvSaleIssueBillEntry.getTaxPrice().multiply(scmInvSaleIssueBillEntry.getQty()).setScale(amtPrecision, RoundingMode.HALF_UP));
								scmInvSaleIssueBillEntry.setSaleTaxAmt(scmInvSaleIssueBillEntry.getSaleQty().multiply(scmInvSaleIssueBillEntry.getSaleTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
								scmInvSaleIssueBillEntryList.add(scmInvSaleIssueBillEntry);
							}
		        		}
		        	}else {
		        		ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry = new ScmInvSaleIssueBillEntry2(true);
			            scmInvSaleIssueBillEntry.setItemId(scmInvPurInWarehsBillEntryList.get(i).getItemId());
			            scmInvSaleIssueBillEntry.setUnit(scmInvPurInWarehsBillEntryList.get(i).getUnit());
			            scmInvSaleIssueBillEntry.setBaseUnit(scmInvPurInWarehsBillEntryList.get(i).getBaseUnit());
			            scmInvSaleIssueBillEntry.setOrgUnitNo(scmsupplier.getOrgUnitNo());
			            scmInvSaleIssueBillEntry.setQty(scmInvPurInWarehsBillEntryList.get(i).getQty());
			            scmInvSaleIssueBillEntry.setSaleQty(scmInvPurInWarehsBillEntryList.get(i).getQty());
			            scmInvSaleIssueBillEntry.setBaseQty(scmInvPurInWarehsBillEntryList.get(i).getBaseQty());
			            scmInvSaleIssueBillEntry.setTaxRate(BigDecimal.ZERO);
			            scmInvSaleIssueBillEntry.setSaleTaxRate(BigDecimal.ZERO);
			            scmInvSaleIssueBillEntry.setBalanceCustId(customerList.get(0).getId());
			            scmInvSaleIssueBillEntry.setSourceBillDtlId(scmInvPurInWarehsBillEntryList.get(i).getId());
			            scmInvSaleIssueBillEntry.setRemarks(scmInvPurInWarehsBillEntryList.get(i).getRemarks());
			            scmInvSaleIssueBillEntry.setSaleTaxPrice(scmInvPurInWarehsBillEntryList.get(i).getTaxPrice());
			            scmInvSaleIssueBillEntry.setSaleTaxAmt(scmInvPurInWarehsBillEntryList.get(i).getTaxAmt());
			            scmInvSaleIssueBillEntry.setPrice(scmInvPurInWarehsBillEntryList.get(i).getPrice());
                        scmInvSaleIssueBillEntry.setAmt(scmInvSaleIssueBillEntry.getQty().multiply(scmInvSaleIssueBillEntry.getPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
			            scmInvSaleIssueBillEntry.setTaxPrice(scmInvPurInWarehsBillEntryList.get(i).getTaxPrice());
                        scmInvSaleIssueBillEntry.setTaxAmt(scmInvSaleIssueBillEntry.getQty().multiply(scmInvSaleIssueBillEntry.getTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
			            scmInvSaleIssueBillEntryList.add(scmInvSaleIssueBillEntry);
		        	}
		            
		        }
		        bean.setList2(scmInvSaleIssueBillEntryList);
		        scmInvSaleIssueBillBiz.add(bean, param);
			}
		}
	}
	
	private ScmInvSaleIssueBillEntry2 setDataFromSaleIssue(ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntryOut,ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry,int amtPrecision,Param param) throws AppException{
		BigDecimal outQty = scmInvSaleIssueBillEntryOut.getQty();
		BigDecimal outPieQty = scmInvSaleIssueBillEntryOut.getPieQty();
		ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry = new ScmInvSaleIssueBillEntry2(true);
		if (outQty.compareTo(scmInvPurInWarehsBillEntry.getQty()) >= 0) {
            scmInvSaleIssueBillEntry.setItemId(scmInvPurInWarehsBillEntry.getItemId());
            scmInvSaleIssueBillEntry.setUnit(scmInvPurInWarehsBillEntry.getUnit());
            scmInvSaleIssueBillEntry.setBaseUnit(scmInvPurInWarehsBillEntry.getBaseUnit());
            scmInvSaleIssueBillEntry.setLot(scmInvSaleIssueBillEntryOut.getLot());
            scmInvSaleIssueBillEntry.setWareHouseId(scmInvSaleIssueBillEntryOut.getWareHouseId());
            scmInvSaleIssueBillEntry.setQty(scmInvPurInWarehsBillEntry.getQty());
            scmInvSaleIssueBillEntry.setPieQty(scmInvPurInWarehsBillEntry.getPieQty());
            scmInvSaleIssueBillEntry.setSaleQty(scmInvPurInWarehsBillEntry.getQty());
            scmInvSaleIssueBillEntry.setTaxRate(scmInvSaleIssueBillEntryOut.getTaxRate());
            scmInvSaleIssueBillEntry.setSaleTaxRate(scmInvSaleIssueBillEntryOut.getSaleTaxRate());
            scmInvSaleIssueBillEntry.setSourceBillDtlId(scmInvPurInWarehsBillEntry.getId());
            scmInvSaleIssueBillEntry.setRemarks(scmInvPurInWarehsBillEntry.getRemarks());
            scmInvSaleIssueBillEntry.setSaleTaxPrice(scmInvPurInWarehsBillEntry.getTaxPrice());
            scmInvSaleIssueBillEntry.setPrice(scmInvSaleIssueBillEntryOut.getPrice());
            scmInvSaleIssueBillEntry.setTaxPrice(scmInvSaleIssueBillEntryOut.getTaxPrice());
            scmInvSaleIssueBillEntry.setOrgSourceId(scmInvSaleIssueBillEntryOut.getOrgSourceId());
            scmInvSaleIssueBillEntry.setOrgSourceVendorId(scmInvSaleIssueBillEntryOut.getOrgSourceVendorId());
            scmInvPurInWarehsBillEntry.setQty(BigDecimal.ZERO);
		}else {
            scmInvSaleIssueBillEntry.setItemId(scmInvPurInWarehsBillEntry.getItemId());
            scmInvSaleIssueBillEntry.setUnit(scmInvPurInWarehsBillEntry.getUnit());
            scmInvSaleIssueBillEntry.setBaseUnit(scmInvPurInWarehsBillEntry.getBaseUnit());
            scmInvSaleIssueBillEntry.setLot(scmInvSaleIssueBillEntryOut.getLot());
            scmInvSaleIssueBillEntry.setWareHouseId(scmInvSaleIssueBillEntryOut.getWareHouseId());
            scmInvSaleIssueBillEntry.setQty(outQty);
            scmInvSaleIssueBillEntry.setPieQty(outPieQty);
            scmInvSaleIssueBillEntry.setSaleQty(outQty);
            scmInvSaleIssueBillEntry.setBaseQty(scmInvPurInWarehsBillEntry.getBaseQty());
            scmInvSaleIssueBillEntry.setTaxRate(scmInvSaleIssueBillEntryOut.getTaxRate());
            scmInvSaleIssueBillEntry.setSaleTaxRate(scmInvSaleIssueBillEntryOut.getSaleTaxRate());
            scmInvSaleIssueBillEntry.setSourceBillDtlId(scmInvPurInWarehsBillEntry.getId());
            scmInvSaleIssueBillEntry.setRemarks(scmInvPurInWarehsBillEntry.getRemarks());
            scmInvSaleIssueBillEntry.setSaleTaxPrice(scmInvPurInWarehsBillEntry.getTaxPrice());
            scmInvSaleIssueBillEntry.setSaleTaxAmt(scmInvSaleIssueBillEntry.getSaleTaxPrice().multiply(scmInvSaleIssueBillEntry.getSaleQty()).setScale(amtPrecision, RoundingMode.HALF_UP));
            scmInvSaleIssueBillEntry.setPrice(scmInvSaleIssueBillEntryOut.getPrice());
            scmInvSaleIssueBillEntry.setTaxPrice(scmInvSaleIssueBillEntryOut.getTaxPrice());
            scmInvSaleIssueBillEntry.setOrgSourceId(scmInvSaleIssueBillEntryOut.getOrgSourceId());
            scmInvSaleIssueBillEntry.setOrgSourceVendorId(scmInvSaleIssueBillEntryOut.getOrgSourceVendorId());
            scmInvPurInWarehsBillEntry.setQty(scmInvPurInWarehsBillEntry.getQty().subtract(scmInvSaleIssueBillEntry.getQty()));
            scmInvPurInWarehsBillEntry.setPieQty(scmInvPurInWarehsBillEntry.getPieQty().subtract(scmInvSaleIssueBillEntry.getPieQty()));
            scmInvPurInWarehsBillEntry.setTaxAmt(scmInvPurInWarehsBillEntry.getTaxAmt().subtract(scmInvSaleIssueBillEntry.getSaleTaxAmt()));
		}
		return scmInvSaleIssueBillEntry;
	}
	/*
	 * 退货拆单 
	 */
	private void splitBillByOutWarehouse(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param){
		List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = scmInvPurInWarehsBillEntryBiz.selectByWrId(scmInvPurInWarehsBill.getWrId(), param);
        String fields[]={"itemId","id"};
		String sorts[]={"ASC","ASC"};
		scmInvPurInWarehsBillEntryList = (List<ScmInvPurInWarehsBillEntry2>)ListSortUtil.sort(scmInvPurInWarehsBillEntryList, fields,sorts);	//按物资排序
		int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
		String returByVendor = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_ReturByVendor", "Y", param);
		String storageWineVendor = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_WineStorageVendor", "0", param);    //客人存酒供应商
		long storageWineVendorId = 0;
        if (StringUtils.isNotBlank(storageWineVendor)) {
        	Scmsupplier2 scmsupplier = scmsupplierBiz.selectByCode(storageWineVendor,param);
        	if(scmsupplier!=null) {
        		storageWineVendorId = scmsupplier.getId();
        	}
        }
		HashMap<String,List<ScmInvStock2>> qtyMap = new HashMap<String,List<ScmInvStock2>>();
		int lineId = 1;
		if(scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()){
			for(int i=scmInvPurInWarehsBillEntryList.size()-1;i>=0;i--){
				ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry = scmInvPurInWarehsBillEntryList.get(i);
				StringBuffer info = new StringBuffer("");
				info.append(scmInvPurInWarehsBillEntry.getOrgUnitNo()).append("_");
				if(scmInvPurInWarehsBillEntry.getWareHouseId() == 0){
					info.append(scmInvPurInWarehsBillEntry.getUseOrgUnitNo()).append("_");
				}else{
					info.append(scmInvPurInWarehsBillEntry.getWareHouseId()).append("_");
				}
				info.append(String.valueOf(scmInvPurInWarehsBillEntry.getItemId()));
				if(!qtyMap.containsKey((info.toString()))){
					//查询计价方式
					Page page = new Page();
					page.setModelClass(ScmMaterial2.class);
					page.setShowCount(Integer.MAX_VALUE);
					page.setSqlCondition("ScmMaterial.id="+scmInvPurInWarehsBillEntry.getItemId());
					ArrayList argList = new ArrayList();
			        argList.add("orgUnitNo="+scmInvPurInWarehsBillEntry.getFinOrgUnitNo());
			        argList.add("controlUnitNo=" + param.getControlUnitNo());
                    List<ScmMaterial2> materialList = scmMaterialBiz.queryPage(page, argList, "findBySingleFinAllPage", param);
                    //获取costMethod来决定升序还是降序排序
                    if (materialList != null && materialList.size() > 0 && StringUtils.isNotBlank(materialList.get(0).getCosting())) {
                    	HashMap<String, Object> map = new HashMap<String, Object>();
                    	if(scmInvPurInWarehsBillEntry.getWareHouseId() == 0){
                    		map.put("orgUnitNo", scmInvPurInWarehsBillEntry.getUseOrgUnitNo());
                    	}else{
                    		if(StringUtils.isBlank(scmInvPurInWarehsBillEntry.getOrgUnitNo())){
                    			map.put("orgUnitNo", scmInvPurInWarehsBill.getOrgUnitNo());
                    		}else{
                    			map.put("orgUnitNo", scmInvPurInWarehsBillEntry.getOrgUnitNo());
                    		}
                    	}
                        map.put("itemId", scmInvPurInWarehsBillEntry.getItemId());
                        map.put("wareHouseId", scmInvPurInWarehsBillEntry.getWareHouseId());
                        map.put("bizDate", FormatUtils.fmtDate(scmInvPurInWarehsBill.getBizDate()));
                        map.put("costOrgUnitNo", scmInvPurInWarehsBillEntry.getCostOrgUnitNo());
                        map.put("costMethod", materialList.get(0).getCosting());
                        map.put("unit", scmInvPurInWarehsBillEntry.getUnit());
                        if(StringUtils.equals("Y", returByVendor) && storageWineVendorId != scmInvPurInWarehsBill.getVendorId())
                        	map.put("vendorId", scmInvPurInWarehsBill.getVendorId());
                        List<ScmInvStock2> stocklist = scmInvStockBiz.findByOutAndReturnWarehouse(map, param);
                        qtyMap.put(info.toString(), stocklist);
                    }
				}
                List<ScmInvStock2> stocklist = qtyMap.get(info.toString());
				if(stocklist != null && !stocklist.isEmpty()){
					if(StringUtils.isNotBlank(scmInvPurInWarehsBillEntry.getLot())) {
                		//有批次则先按批次找，如不够再按先进先出拆单
                		for (ScmInvStock2 scmInvStock : stocklist) {
                			if(StringUtils.equals(scmInvPurInWarehsBillEntry.getLot(), scmInvStock.getLot())) {
		                		if(scmInvPurInWarehsBillEntry.getQty().compareTo(BigDecimal.ZERO)>0) {
		                			if(setDataFromStock(scmInvStock,scmInvPurInWarehsBillEntry,amtPrecision,lineId,param))
		                				lineId = lineId+1;
		                		}else {
		                			break;
		                		}
                			}
                		}
                	}
                	for (ScmInvStock2 scmInvStock : stocklist) {
                		if(scmInvPurInWarehsBillEntry.getQty().compareTo(BigDecimal.ZERO)>0) {
                			if(setDataFromStock(scmInvStock,scmInvPurInWarehsBillEntry,amtPrecision,lineId,param))
                				lineId = lineId+1;
                		}else {
                			break;
                		}
                	}
                    scmInvPurInWarehsBillEntryBiz.delete(scmInvPurInWarehsBillEntry, param);
				}
			}
		}
		List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList2 = scmInvPurInWarehsBillEntryBiz.selectByWrId(scmInvPurInWarehsBill.getWrId(), param);
		if(scmInvPurInWarehsBillEntryList2 != null && !scmInvPurInWarehsBillEntryList2.isEmpty()){
			BigDecimal amt= BigDecimal.ZERO;
            BigDecimal taxAmt= BigDecimal.ZERO;
			//重新设置LineId,防止错乱
			for(int i=0;i<scmInvPurInWarehsBillEntryList2.size();i++){
				scmInvPurInWarehsBillEntryList2.get(i).setLineId(i+1);
                amt = amt.add(scmInvPurInWarehsBillEntryList2.get(i).getAmt());
                taxAmt = taxAmt.add(scmInvPurInWarehsBillEntryList2.get(i).getTaxAmt());
			}
			scmInvPurInWarehsBill.setAmt(amt);
			scmInvPurInWarehsBill.setTaxAmt(taxAmt);
			this.updateDirect(scmInvPurInWarehsBill, param);
		}
	}
	
	private boolean setDataFromStock(ScmInvStock2 scmInvStock,ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry,int amtPrecision,int lineId,Param param) {
		boolean flag=false;
		BigDecimal stockQty = scmInvStock.getQty();
		BigDecimal stockPieQty = scmInvStock.getPieQty();
		BigDecimal stockBaseQty = scmInvStock.getBaseQty();
		BigDecimal stockAmt = (scmInvStock.getAmt());
		BigDecimal stockTaxAmt = (scmInvStock.getTaxAmt());
		if (stockQty.compareTo(scmInvPurInWarehsBillEntry.getQty()) > 0) {
			ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry2= new ScmInvPurInWarehsBillEntry2(true);
    		scmInvPurInWarehsBillEntry2.setWrId(scmInvPurInWarehsBillEntry.getWrId());
    		scmInvPurInWarehsBillEntry2.setLineId(scmInvPurInWarehsBillEntry.getLineId()+1);
    		scmInvPurInWarehsBillEntry2.setItemId(scmInvStock.getItemId());
    		scmInvPurInWarehsBillEntry2.setLot(scmInvStock.getLot());
    		scmInvPurInWarehsBillEntry2.setUnit(scmInvPurInWarehsBillEntry.getUnit());
    		scmInvPurInWarehsBillEntry2.setQty(scmInvPurInWarehsBillEntry.getQty());
    		scmInvPurInWarehsBillEntry2.setBaseUnit(scmInvStock.getBaseUnit());
    		scmInvPurInWarehsBillEntry2.setBaseQty(scmInvPurInWarehsBillEntry.getBaseQty());
    		scmInvPurInWarehsBillEntry2.setPieUnit(scmInvStock.getPieUnit());
    		scmInvPurInWarehsBillEntry2.setPieQty(scmInvPurInWarehsBillEntry.getPieQty());
//    		if(StringUtils.equals("WMA",materialList.get(0).getCosting())){
//    			scmInvPurInWarehsBillEntry2.setPrice(scmInvPurInWarehsBillEntry.getPrice());
//    			scmInvPurInWarehsBillEntry2.setTaxRate(scmInvPurInWarehsBillEntry.getTaxRate());
//    			scmInvPurInWarehsBillEntry2.setTaxPrice(scmInvPurInWarehsBillEntry.getTaxPrice());
//    			//精度暂时设置为4位，应该由系统参数控制，后续再加
//    			scmInvPurInWarehsBillEntry2.setAmt((scmInvPurInWarehsBillEntry2.getQty().multiply(scmInvPurInWarehsBillEntry2.getPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
//    			scmInvPurInWarehsBillEntry2.setTaxAmt((scmInvPurInWarehsBillEntry2.getQty().multiply(scmInvPurInWarehsBillEntry2.getTaxPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
//    		}else{
			scmInvPurInWarehsBillEntry2.setPrice(scmInvStock.getPrice());
			scmInvPurInWarehsBillEntry2.setTaxRate(scmInvStock.getTaxRate());
			scmInvPurInWarehsBillEntry2.setTaxPrice(scmInvStock.getTaxPrice());
			scmInvPurInWarehsBillEntry2.setAmt((scmInvPurInWarehsBillEntry2.getQty().multiply(scmInvPurInWarehsBillEntry2.getPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
			scmInvPurInWarehsBillEntry2.setTaxAmt((scmInvPurInWarehsBillEntry2.getQty().multiply(scmInvPurInWarehsBillEntry2.getTaxPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
//    		}
    		scmInvPurInWarehsBillEntry2.setProductDate(scmInvPurInWarehsBillEntry.getProductDate());
    		scmInvPurInWarehsBillEntry2.setExpDate(scmInvStock.getExpDate());
    		scmInvPurInWarehsBillEntry2.setOffset(false);
    		scmInvPurInWarehsBillEntry2.setUseOrgUnitNo(scmInvPurInWarehsBillEntry.getUseOrgUnitNo());
    		scmInvPurInWarehsBillEntry2.setCostOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo());
    		scmInvPurInWarehsBillEntry2.setOrgUnitNo(scmInvPurInWarehsBillEntry.getOrgUnitNo());
    		scmInvPurInWarehsBillEntry2.setWareHouseId(scmInvPurInWarehsBillEntry.getWareHouseId());
    		scmInvPurInWarehsBillEntry2.setBalanceVendorId(scmInvPurInWarehsBillEntry.getBalanceVendorId());
    		scmInvPurInWarehsBillEntry2.setStorageOrgUnitNo(scmInvPurInWarehsBillEntry.getStorageOrgUnitNo());
    		scmInvPurInWarehsBillEntry2.setRequireOrgUnitNo(scmInvPurInWarehsBillEntry.getRequireOrgUnitNo());
    		scmInvPurInWarehsBillEntry2.setPurOrgUnitNo(scmInvPurInWarehsBillEntry.getPurOrgUnitNo());
    		scmInvPurInWarehsBillEntry2.setBuyerId(scmInvPurInWarehsBillEntry.getBuyerId());
    		scmInvPurInWarehsBillEntry2.setPurGroupId(scmInvPurInWarehsBillEntry.getPurGroupId());
    		scmInvPurInWarehsBillEntry2.setSourceBillDtlId(scmInvPurInWarehsBillEntry.getSourceBillDtlId());
    		scmInvPurInWarehsBillEntry2.setPriceBillId(scmInvPurInWarehsBillEntry.getPriceBillId());
    		scmInvPurInWarehsBillEntry2.setRefPriceStatus(scmInvPurInWarehsBillEntry.getRefPriceStatus());
    		scmInvPurInWarehsBillEntry2.setStockId(scmInvStock.getId());
    		scmInvPurInWarehsBillEntry2.setRemarks(scmInvPurInWarehsBillEntry.getRemarks());
    		scmInvPurInWarehsBillEntry2.setReqReturnBaseQty(BigDecimal.ZERO);
    		scmInvPurInWarehsBillEntry2.setBuildAP(false);
    		scmInvPurInWarehsBillEntry2.setFinOrgUnitNo(scmInvStock.getFinOrgUnitNo());
    		scmInvPurInWarehsBillEntry2.setOrgSourceId(scmInvStock.getSourceBillId());
    		scmInvPurInWarehsBillEntry2.setOrgSourceVendorId(scmInvStock.getVendorId());
    		scmInvPurInWarehsBillEntry2.setInvDate(scmInvStock.getInvDate());
    		scmInvPurInWarehsBillEntryBiz.add(scmInvPurInWarehsBillEntry2, param);
    		scmInvPurInWarehsBillEntry.setQty(BigDecimal.ZERO);
    		scmInvPurInWarehsBillEntry.setPieQty(BigDecimal.ZERO);
    		scmInvPurInWarehsBillEntry.setBaseQty(BigDecimal.ZERO);
    		scmInvStock.setQty(scmInvStock.getQty().subtract(scmInvPurInWarehsBillEntry2.getQty()));
    		scmInvStock.setPieQty(scmInvStock.getPieQty().subtract(scmInvPurInWarehsBillEntry2.getPieQty()));
    		scmInvStock.setBaseQty(scmInvStock.getBaseQty().subtract(scmInvPurInWarehsBillEntry2.getBaseQty()));
    		scmInvStock.setAmt(scmInvStock.getAmt().subtract(scmInvPurInWarehsBillEntry2.getAmt()));
    		scmInvStock.setTaxAmt(scmInvStock.getTaxAmt().subtract(scmInvPurInWarehsBillEntry2.getTaxAmt()));
    		flag=true;
		} else if (stockQty.compareTo(BigDecimal.ZERO) > 0) {
			ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry2= new ScmInvPurInWarehsBillEntry2(true);
    		scmInvPurInWarehsBillEntry2.setWrId(scmInvPurInWarehsBillEntry.getWrId());
    		scmInvPurInWarehsBillEntry2.setLineId(scmInvPurInWarehsBillEntry.getLineId()+1);
    		scmInvPurInWarehsBillEntry2.setItemId(scmInvStock.getItemId());
    		scmInvPurInWarehsBillEntry2.setLot(scmInvStock.getLot());
    		scmInvPurInWarehsBillEntry2.setUnit(scmInvPurInWarehsBillEntry.getUnit());
    		scmInvPurInWarehsBillEntry2.setQty(stockQty);
    		scmInvPurInWarehsBillEntry2.setBaseUnit(scmInvStock.getBaseUnit());
    		scmInvPurInWarehsBillEntry2.setBaseQty(stockBaseQty);
    		scmInvPurInWarehsBillEntry2.setPieUnit(scmInvStock.getPieUnit());
    		scmInvPurInWarehsBillEntry2.setPieQty(stockPieQty);
//    		if(StringUtils.equals("WMA",materialList.get(0).getCosting())){
//    			scmInvPurInWarehsBillEntry2.setPrice(scmInvPurInWarehsBillEntry.getPrice());
//    			scmInvPurInWarehsBillEntry2.setTaxRate(scmInvPurInWarehsBillEntry.getTaxRate());
//    			scmInvPurInWarehsBillEntry2.setTaxPrice(scmInvPurInWarehsBillEntry.getTaxPrice());
//    			//精度暂时设置为4位，应该由系统参数控制，后续再加
//    			scmInvPurInWarehsBillEntry2.setAmt((scmInvPurInWarehsBillEntry2.getQty().multiply(scmInvPurInWarehsBillEntry2.getPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
//    			scmInvPurInWarehsBillEntry2.setTaxAmt((scmInvPurInWarehsBillEntry2.getQty().multiply(scmInvPurInWarehsBillEntry2.getTaxPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
//    		}else{
			scmInvPurInWarehsBillEntry2.setPrice(scmInvStock.getPrice());
			scmInvPurInWarehsBillEntry2.setTaxRate(scmInvStock.getTaxRate());
			scmInvPurInWarehsBillEntry2.setTaxPrice(scmInvStock.getTaxPrice());
			scmInvPurInWarehsBillEntry2.setAmt(stockAmt);
			scmInvPurInWarehsBillEntry2.setTaxAmt(stockTaxAmt);
//    		}
    		scmInvPurInWarehsBillEntry2.setProductDate(scmInvPurInWarehsBillEntry.getProductDate());
    		scmInvPurInWarehsBillEntry2.setExpDate(scmInvStock.getExpDate());
    		scmInvPurInWarehsBillEntry2.setOffset(false);
    		scmInvPurInWarehsBillEntry2.setUseOrgUnitNo(scmInvPurInWarehsBillEntry.getUseOrgUnitNo());
    		scmInvPurInWarehsBillEntry2.setCostOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo());
    		scmInvPurInWarehsBillEntry2.setOrgUnitNo(scmInvPurInWarehsBillEntry.getOrgUnitNo());
    		scmInvPurInWarehsBillEntry2.setWareHouseId(scmInvPurInWarehsBillEntry.getWareHouseId());
    		scmInvPurInWarehsBillEntry2.setBalanceVendorId(scmInvPurInWarehsBillEntry.getBalanceVendorId());
    		scmInvPurInWarehsBillEntry2.setStorageOrgUnitNo(scmInvPurInWarehsBillEntry.getStorageOrgUnitNo());
    		scmInvPurInWarehsBillEntry2.setRequireOrgUnitNo(scmInvPurInWarehsBillEntry.getRequireOrgUnitNo());
    		scmInvPurInWarehsBillEntry2.setPurOrgUnitNo(scmInvPurInWarehsBillEntry.getPurOrgUnitNo());
    		scmInvPurInWarehsBillEntry2.setBuyerId(scmInvPurInWarehsBillEntry.getBuyerId());
    		scmInvPurInWarehsBillEntry2.setPurGroupId(scmInvPurInWarehsBillEntry.getPurGroupId());
    		scmInvPurInWarehsBillEntry2.setSourceBillDtlId(scmInvPurInWarehsBillEntry.getSourceBillDtlId());
    		scmInvPurInWarehsBillEntry2.setPriceBillId(scmInvPurInWarehsBillEntry.getPriceBillId());
    		scmInvPurInWarehsBillEntry2.setRefPriceStatus(scmInvPurInWarehsBillEntry.getRefPriceStatus());
    		scmInvPurInWarehsBillEntry2.setStockId(scmInvStock.getId());
    		scmInvPurInWarehsBillEntry2.setRemarks(scmInvPurInWarehsBillEntry.getRemarks());
    		scmInvPurInWarehsBillEntry2.setReqReturnBaseQty(BigDecimal.ZERO);
    		scmInvPurInWarehsBillEntry2.setBuildAP(false);
    		scmInvPurInWarehsBillEntry2.setFinOrgUnitNo(scmInvStock.getFinOrgUnitNo());
    		scmInvPurInWarehsBillEntry2.setOrgSourceId(scmInvStock.getSourceBillId());
    		scmInvPurInWarehsBillEntry2.setOrgSourceVendorId(scmInvStock.getVendorId());
    		scmInvPurInWarehsBillEntry2.setInvDate(scmInvStock.getInvDate());
    		scmInvPurInWarehsBillEntryBiz.add(scmInvPurInWarehsBillEntry2, param);
    		scmInvPurInWarehsBillEntry.setQty(scmInvPurInWarehsBillEntry.getQty().subtract(scmInvPurInWarehsBillEntry2.getQty()));
    		scmInvPurInWarehsBillEntry.setPieQty(scmInvPurInWarehsBillEntry.getPieQty().subtract(scmInvPurInWarehsBillEntry2.getPieQty()));
    		scmInvPurInWarehsBillEntry.setBaseQty(scmInvPurInWarehsBillEntry.getBaseQty().subtract(scmInvPurInWarehsBillEntry2.getBaseQty()));
    		scmInvStock.setQty(scmInvStock.getQty().subtract(scmInvPurInWarehsBillEntry2.getQty()));
    		scmInvStock.setPieQty(scmInvStock.getPieQty().subtract(scmInvPurInWarehsBillEntry2.getPieQty()));
    		scmInvStock.setBaseQty(scmInvStock.getBaseQty().subtract(scmInvPurInWarehsBillEntry2.getBaseQty()));
    		scmInvStock.setAmt(scmInvStock.getAmt().subtract(scmInvPurInWarehsBillEntry2.getAmt()));
    		scmInvStock.setTaxAmt(scmInvStock.getTaxAmt().subtract(scmInvPurInWarehsBillEntry2.getTaxAmt()));
    		flag=true;
		}
		return flag;
	}
	/*
	 * 获取汇总数据
	 */
	private HashMap<String,List<BigDecimal>> getSumMap(List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList){
		if(scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()){
			HashMap<String,List<BigDecimal>> qtyMap = new HashMap<String,List<BigDecimal>>();
			for(ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList){
				StringBuffer info = new StringBuffer("");
				info.append(String.valueOf(scmInvPurInWarehsBillEntry.getItemId())).append("_")
					.append(String.valueOf(scmInvPurInWarehsBillEntry.getUnit())).append("_")
					.append(scmInvPurInWarehsBillEntry.getLot()).append("_");
				if(scmInvPurInWarehsBillEntry.getWareHouseId() == 0){
					info.append(scmInvPurInWarehsBillEntry.getWareHouseId()).append("_")
					.append(scmInvPurInWarehsBillEntry.getUseOrgUnitNo());
				}else{
					info.append(scmInvPurInWarehsBillEntry.getWareHouseId()).append("_")
					.append(scmInvPurInWarehsBillEntry.getOrgUnitNo());
				}
				if(!qtyMap.containsKey((info.toString()))){
					List<BigDecimal> qtyList = new ArrayList<>();
					BigDecimal sumQty = BigDecimal.ZERO;
					BigDecimal sumPieQty = BigDecimal.ZERO;
					BigDecimal sumBaseQty = BigDecimal.ZERO;
					BigDecimal sumAmt = BigDecimal.ZERO;
					if(scmInvPurInWarehsBillEntry.getQty() != null){
						sumQty = sumQty.add(scmInvPurInWarehsBillEntry.getQty());
					}
					if(scmInvPurInWarehsBillEntry.getPieQty() != null){
						sumPieQty = sumPieQty.add(scmInvPurInWarehsBillEntry.getPieQty());
					}
					if(scmInvPurInWarehsBillEntry.getBaseQty() != null){
						sumBaseQty = sumBaseQty.add(scmInvPurInWarehsBillEntry.getBaseQty());
					}
					if(scmInvPurInWarehsBillEntry.getAmt() != null){
						sumAmt = sumAmt.add(scmInvPurInWarehsBillEntry.getAmt());
					}
					qtyList.add(sumQty);
					qtyList.add(sumPieQty);
					qtyList.add(sumBaseQty);
					qtyList.add(sumAmt);
					qtyMap.put(info.toString(), qtyList);
				}else{
					List<BigDecimal> qtyList = qtyMap.get((info.toString()));
					BigDecimal sumQty = qtyList.get(0);
					BigDecimal sumPieQty = qtyList.get(1);
					BigDecimal sumBaseQty = qtyList.get(2);
					BigDecimal sumAmt = qtyList.get(3);
					if(scmInvPurInWarehsBillEntry.getQty() != null){
						sumQty = sumQty.add(scmInvPurInWarehsBillEntry.getQty());
					}
					if(scmInvPurInWarehsBillEntry.getPieQty() != null){
						sumPieQty = sumPieQty.add(scmInvPurInWarehsBillEntry.getPieQty());
					}
					if(scmInvPurInWarehsBillEntry.getBaseQty() != null){
						sumBaseQty = sumBaseQty.add(scmInvPurInWarehsBillEntry.getBaseQty());
					}
					if(scmInvPurInWarehsBillEntry.getAmt() != null){
						sumAmt = sumAmt.add(scmInvPurInWarehsBillEntry.getAmt());
					}
					qtyList.clear();
					qtyList.add(sumQty);
					qtyList.add(sumPieQty);
					qtyList.add(sumBaseQty);
					qtyList.add(sumAmt);
				}
			}
			return qtyMap;
		}
		return null;
	}
	
	private String getOrgUnitNoList(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param){
		if(scmInvPurInWarehsBill != null){
			List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = scmInvPurInWarehsBillEntryBiz.selectByWrId(scmInvPurInWarehsBill.getWrId(), param);
			if(scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()){
				StringBuffer orgUnitNoList = new StringBuffer("");
				List<String> list = new ArrayList<String>();
				for(ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList){
					if(StringUtils.isNotBlank(scmInvPurInWarehsBillEntry.getCostOrgUnitNo()) && !list.contains(scmInvPurInWarehsBillEntry.getCostOrgUnitNo())){
						list.add(scmInvPurInWarehsBillEntry.getCostOrgUnitNo());
						OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvPurInWarehsBillEntry.getCostOrgUnitNo(), param);
						//以领代耗和以盘代耗都符合条件
						if(orgCostCenter != null){
							orgUnitNoList.append(scmInvPurInWarehsBillEntry.getCostOrgUnitNo()).append(",");
						}
					}
				}
				if(StringUtils.isNotBlank(orgUnitNoList.toString())){
					return StringUtils.substring(orgUnitNoList.toString(), 0, (orgUnitNoList.toString().length())-1);
				}
			}
		}
		return "";
	}
	
	private void generateLot(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill,List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList){
		if(!scmInvPurInWarehsBill.isOffset() && (StringUtils.equals("1", scmInvPurInWarehsBill.getBizType())
                || StringUtils.equals("2", scmInvPurInWarehsBill.getBizType()) || StringUtils.equals("3", scmInvPurInWarehsBill.getBizType()))){
            for(int i= 0;i<scmInvPurInWarehsBillEntryList.size();i++){
            	scmInvPurInWarehsBillEntryList.get(i).setLot("");
            }
            String addLot = CodeAutoCalUtil.autoAddOne("00");
            for(int i= 0;i<scmInvPurInWarehsBillEntryList.size();i++){
                if(StringUtils.isBlank(scmInvPurInWarehsBillEntryList.get(i).getLot())){
                    scmInvPurInWarehsBillEntryList.get(i).setLot((scmInvPurInWarehsBill.getWrNo())+"-"+addLot);
                    long itemId = scmInvPurInWarehsBillEntryList.get(i).getItemId();
                    String outLot = addLot;
                    for(int j= i+1;j<scmInvPurInWarehsBillEntryList.size();j++){
                    	if(StringUtils.isBlank(scmInvPurInWarehsBillEntryList.get(j).getLot())){
                    		if(itemId == scmInvPurInWarehsBillEntryList.get(j).getItemId()){
                    			outLot = CodeAutoCalUtil.autoAddOne(outLot);
                                scmInvPurInWarehsBillEntryList.get(j).setLot((scmInvPurInWarehsBill.getWrNo())+"-"+outLot);
                            }
                    	}
                    }
                }
            }
        }
	}

	@Override
	public List<String> cancelPostBillCheck(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param)
			throws AppException {
		List<String> msgList = new ArrayList<String>();// 提示列表
		scmInvPurInWarehsBill = this.selectDirect(scmInvPurInWarehsBill.getWrId(), param);
		if(scmInvPurInWarehsBill != null && StringUtils.contains("1,3", scmInvPurInWarehsBill.getBizType())){
			if(!StringUtils.equals("E",scmInvPurInWarehsBill.getStatus())) {
				msgList.add(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvPurInWarehsBill.getWrNo()}));
				return msgList;
			}
			int count = ((ScmInvPurInWarehsBillDAO) dao).checkfollowupbill(scmInvPurInWarehsBill.getWrId());
			if(count > 0){
				String[] msparam = {scmInvPurInWarehsBill.getWrNo()};
				msgList.add(Message.getMessage(param.getLang(),
						"iscm.inventorymanage.purinwarehsbill.cancelpost.error", msparam));
			}else{
				count = ((ScmInvPurInWarehsBillDAO) dao).checkStock(scmInvPurInWarehsBill.getWrId());
				if(count > 0){
					msgList.add(Message.getMessage(param.getLang(),
							"iscm.inventorymanage.purinwarehsbill.cancelpost.error2"));
				}
			}
			return msgList;
		}
		return null;
	}

	@Override
	public ScmInvPurInWarehsBill2 cancelPostBill(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param)
			throws AppException {
		scmInvPurInWarehsBill = this.selectDirect(scmInvPurInWarehsBill.getWrId(), param);
        if (scmInvPurInWarehsBill != null) {
			if(!StringUtils.equals("E",scmInvPurInWarehsBill.getStatus())) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvPurInWarehsBill.getWrNo()}));
    		}
	        
	        // 检查冻结仓库
	        List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList = ((ScmInvPurInWarehsBillDAO) dao).checkWareHouseFree(scmInvPurInWarehsBill.getWrId());
	        if (scmInvPurInWarehsBillList != null && !scmInvPurInWarehsBillList.isEmpty()) {
	        	throw new AppException(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
//	        	ScmInvPurInWarehsBill2 scmInvPurInWarehsBill3 = scmInvPurInWarehsBillList.get(0);
//	            // 检查冻结物资
//	            HashMap<String,Object> map = new HashMap<String,Object>();
//	            map.put("taskId", scmInvPurInWarehsBill3.getTaskId());
//	            map.put("wrId", scmInvPurInWarehsBill.getWrId());
//	            int count = ((ScmInvPurInWarehsBillDAO) dao).checkMaterialFree(map);
//	            if (count > 0) {
//	            	throw new AppException(Message.getMessage(param.getLang(), 
//	                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
//	            }
	        }
	        
	        // 检查冻结部门
	        List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList2 = ((ScmInvPurInWarehsBillDAO) dao).checkCostCenterFree(scmInvPurInWarehsBill.getWrId());
	        
	        if (scmInvPurInWarehsBillList2 != null && !scmInvPurInWarehsBillList2.isEmpty()) {
	        	throw new AppException(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingCostcenter"));
//	        	for (ScmInvPurInWarehsBill2 scmInvPurInWarehsBill3 : scmInvPurInWarehsBillList2) {
//		            // 检查冻结物资
//		            HashMap<String,Object> map = new HashMap<String,Object>();
//		            map.put("taskId", scmInvPurInWarehsBill3.getTaskId());
//		            map.put("wrId", scmInvPurInWarehsBill.getWrId());
//		            int count = ((ScmInvPurInWarehsBillDAO) dao).costCenterMaterialFree(map);
//		            if (count > 0) {
//		            	throw new AppException(Message.getMessage(param.getLang(), 
//		                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingCostcenter"));
//		            }
//	        	}
	        }
	        
			// 期间检查
		    SystemState systemState = systemStateBiz.selectBySystemId(scmInvPurInWarehsBill.getFinOrgUnitNo(), 170, param);
		    if(systemState==null){
		    	throw new AppException(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
	        }
	        if (systemState.getCurrentPeriodId() != scmInvPurInWarehsBill.getPeriodId()) {
	        	throw new AppException(Message.getMessage(param.getLang(), 
	                    "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmInvPurInWarehsBill.getWrNo()}));
	        }
			if(StringUtils.equals("6", scmInvPurInWarehsBill.getBizType()) || StringUtils.equals("8", scmInvPurInWarehsBill.getBizType())){
				//采购退货
				//即时库存
				scmInvStockBiz.updateByCancelPurInWarehsBillOut(scmInvPurInWarehsBill.getWrId(), param);
				//期间余额
				scmInvBalBiz.updateByCancelPurInWarehsBillOut(scmInvPurInWarehsBill.getWrId(), param);
				//移动平均即时成本
//				scmInvCostBiz.updateByCancelPurInWarehsBillOut(scmInvPurInWarehsBill.getWrId(), param);
			}else{
				//即时库存
				List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = scmInvPurInWarehsBillEntryBiz.checkStock(scmInvPurInWarehsBill.getWrId(), param);
		        StringBuffer msg = new StringBuffer();  
				if(scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()){
		            for(ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList){
		            	if(StringUtils.isNotBlank(scmInvPurInWarehsBillEntry.getLot())){
		            		if(scmInvPurInWarehsBillEntry.getPieQty().compareTo(BigDecimal.ZERO)==0) {
		            			String[] msparam = {scmInvPurInWarehsBillEntry.getItemNo(),scmInvPurInWarehsBillEntry.getItemName(),
		            					scmInvPurInWarehsBillEntry.getLot(),(scmInvPurInWarehsBillEntry.getQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
		            					(scmInvPurInWarehsBillEntry.getInvQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()};
		            			msg.append(Message.getMessage(param.getLang(),
		            					"iscm.inventorymanage.warehouseoutbusiness.ScmInvMaterialReqBill.postBillCheck.error", msparam)).append("\r\n");
		            		}else {
		            			String[] msparam = {scmInvPurInWarehsBillEntry.getItemNo(),scmInvPurInWarehsBillEntry.getItemName(),
		                			scmInvPurInWarehsBillEntry.getLot(),(scmInvPurInWarehsBillEntry.getQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
		                			(scmInvPurInWarehsBillEntry.getPieQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
		                			(scmInvPurInWarehsBillEntry.getInvQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
		                			(scmInvPurInWarehsBillEntry.getInvPieQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()};
		            			msg.append(Message.getMessage(param.getLang(),
		                			"iscm.inventorymanage.warehouseoutbusiness.out.postBillCheck.pieqty.error", msparam)).append("\r\n");
		            		}
		            	}else{
		            		if(scmInvPurInWarehsBillEntry.getPieQty().compareTo(BigDecimal.ZERO)==0) {
		            			String[] msparam = {scmInvPurInWarehsBillEntry.getItemNo(),scmInvPurInWarehsBillEntry.getItemName(),
		            				  (scmInvPurInWarehsBillEntry.getQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
		            				  (scmInvPurInWarehsBillEntry.getInvQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()};
		            			msg.append(Message.getMessage(param.getLang(),
		            				  "iscm.inventorymanage.warehouseoutbusiness.ScmInvMaterialReqBill.postBillCheck.error2", msparam)).append("\r\n");
		            		}else {
		            			String[] msparam = {scmInvPurInWarehsBillEntry.getItemNo(),scmInvPurInWarehsBillEntry.getItemName(),
		            					(scmInvPurInWarehsBillEntry.getQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
		            					(scmInvPurInWarehsBillEntry.getPieQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
		            					(scmInvPurInWarehsBillEntry.getInvQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
		            					(scmInvPurInWarehsBillEntry.getInvPieQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()};
		            			msg.append(Message.getMessage(param.getLang(),
		            					"iscm.inventorymanage.warehouseoutbusiness.out.postBillCheck.pieqty.error2", msparam)).append("\r\n");
		            		}
		            	}
		            }
		         }
				if(StringUtils.isNotBlank(msg.toString())) {
					throw new AppException(msg.toString());
				}
	    		List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList1 = scmInvPurInWarehsBillEntryBiz.selectCancelOutEffectRow(scmInvPurInWarehsBill.getWrId(), param);
				int updateRow = scmInvStockBiz.updateByCancelPurInWarehsBill(scmInvPurInWarehsBill.getWrId(), param);
				if(updateRow<scmInvPurInWarehsBillEntryList1.size()){
        			throw new AppException("iscm.inventorymanage.common.post.error.rowsnotequal");
        		}
				//期间余额
				scmInvBalBiz.updateByCancelPurInWarehsBill(scmInvPurInWarehsBill.getWrId(), param);
				//移动平均即时成本
//				scmInvCostBiz.updateByCancelPurInWarehsBill(scmInvPurInWarehsBill.getWrId(), param);
			}
			scmInvPurInWarehsBill.setCheckDate(null);
			scmInvPurInWarehsBill.setChecker("");
			scmInvPurInWarehsBill.setStatus("A");
			scmInvPurInWarehsBill.setPostDate(null);
			int updateRow = this.updatePostedStatus(scmInvPurInWarehsBill, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvPurInWarehsBill.getWrNo()}));
			}
			afterCancelPostBill(scmInvPurInWarehsBill,  param);
		}
		return scmInvPurInWarehsBill;
	}

	private void afterCancelPostBill(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param) {
		if(StringUtils.contains("6,8", scmInvPurInWarehsBill.getBizType())) {
			//同步删除内部交易的销售退货
			Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmInvPurInWarehsBill.getVendorId(),param);
			if(scmsupplier!=null && StringUtils.equals("2",scmsupplier.getRole())) {
				List<ScmInvSaleIssueBill2> scmInvSaleIssueBillList = scmInvSaleIssueBillBiz.selectByPurInwareHouse(scmInvPurInWarehsBill.getWrId(), param);
				if(scmInvSaleIssueBillList!=null && !scmInvSaleIssueBillList.isEmpty()) {
					for(ScmInvSaleIssueBill2 scmInvSaleIssueBill:scmInvSaleIssueBillList) {
						if(!StringUtils.equals(scmInvSaleIssueBill.getStatus(),"I")) {
							throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvPurInWarehsBill.cancelpost.error.saleIssuebillnotnewstatus", new String[]{scmInvSaleIssueBill.getOtNo()}));
						}
					}
					scmInvSaleIssueBillBiz.delete(scmInvSaleIssueBillList, param);
				}
			}
		}
	}
	
	@Override
	public ScmInvPurInWarehsBill2 generatePurInWarehsBillByReceive(ScmPurReceive2 receive, Param param)
			throws AppException {
		receive = scmPurReceiveBiz.select(receive.getId(), param);
	    if ("E".equals(receive.getStatus())) { // 下达状态才生成
	        List<ScmPurReceiveEntry2> scmPurReceiveEntryList = scmPurReceiveEntryBiz.selectByNotWr(receive.getId(), param);
	        if(scmPurReceiveEntryList!=null && !scmPurReceiveEntryList.isEmpty()){
	            int pricePrecision = Integer.parseInt(sysParamBiz.getValue(receive.getOrgUnitNo(), "SCM_PricePrecision", "4", param));
	            String rebateType = sysParamBiz.getValue(receive.getOrgUnitNo(), "SCM_RebateType", "2", param);//折让方式
	            CommonBean bean = new CommonBean();
	            List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList = new ArrayList<>();
	            List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = new ArrayList<>();
	            ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = new ScmInvPurInWarehsBill2(true);
	            scmInvPurInWarehsBill.setStatus("I");
	            scmInvPurInWarehsBill.setBizType("1");
	            scmInvPurInWarehsBill.setFinOrgUnitNo(receive.getFinOrgUnitNo());
	            scmInvPurInWarehsBill.setCreateOrgUnitNo(receive.getOrgUnitNo());
	            scmInvPurInWarehsBill.setOrgUnitNo(receive.getOrgUnitNo());
	            scmInvPurInWarehsBill.setVendorId(receive.getVendorId());
	            PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(receive.getReceiveDate(), param);
				if(periodCalendar==null){
					throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
				}
				SystemState systemState = systemStateBiz.selectBySystemId(scmInvPurInWarehsBill.getFinOrgUnitNo(), 170, param);
			    if(systemState==null){
			    	throw new AppException(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
		        }
		        if (systemState.getCurrentPeriodId() <= periodCalendar.getPeriodId()) {
		        	scmInvPurInWarehsBill.setBizDate(receive.getReceiveDate());
		        }else {
		        	PeriodCalendar pCalendar = periodCalendarBiz.select(systemState.getCurrentPeriodId(), param);
		        	Date parseDate = FormatUtils.parseDate(FormatUtils.fmtDateTime(pCalendar.getStartDate(),"yyyy-MM")+"-01");
		        	Date begDate = FormatUtils.parseDate(FormatUtils.fmtDateTime(receive.getReceiveDate(),"yyyy-MM")+"-01");
		        	Calendar calendar = Calendar.getInstance();
		            calendar.setTime(begDate);
		            calendar.add(Calendar.MONTH, 1);
		            Date endDate=calendar.getTime();
		        	scmInvPurInWarehsBill.setBizDate(parseDate);
		        }
	            scmInvPurInWarehsBill.setBillType("PurReceive");	//来源单为收货单
	            scmInvPurInWarehsBill.setUnified(receive.isUnified());
	            scmInvPurInWarehsBill.setReceiver(receive.getReceiver());
	            scmInvPurInWarehsBill.setCurrencyNo(receive.getCurrencyNo());
	            scmInvPurInWarehsBill.setExchangeRate(receive.getExchangeRate());
	            scmInvPurInWarehsBill.setCreator(param.getUsrCode());
	            scmInvPurInWarehsBill.setCreateDate(CalendarUtil.getDate(param));
	            scmInvPurInWarehsBill.setControlUnitNo(param.getControlUnitNo());
	            scmInvPurInWarehsBill.setPurOrgUnitNo(receive.getPurOrgUnitNo());
	            scmInvPurInWarehsBill.setBuyerId(receive.getBuyerId());
	            scmInvPurInWarehsBill.setPurGroupId(receive.getPurGroupId());
	            scmInvPurInWarehsBill.setRemarks(receive.getRemarks());
	            scmInvPurInWarehsBillList.add(scmInvPurInWarehsBill);
	            bean.setList(scmInvPurInWarehsBillList);
	            HashMap<String,Object> cstHashMap = new HashMap<String,Object>();
	            ScmPurReceiveEntry2 lastScmPurReceiveEntry = null;
	            for(ScmPurReceiveEntry2 scmPurReceiveEntry:scmPurReceiveEntryList){
	                BigDecimal purConvRate = scmPurReceiveEntry.getPurConvRate();//采购单位转换系数
	                BigDecimal invConvRate = scmPurReceiveEntry.getConvrate();//库存单位转换系数
	                //入库明细
	                ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry = new ScmInvPurInWarehsBillEntry2(true);
	                scmInvPurInWarehsBillEntry.setItemId(scmPurReceiveEntry.getItemId());
	                scmInvPurInWarehsBillEntry.setUnit(scmPurReceiveEntry.getUnit());
	                scmInvPurInWarehsBillEntry.setPieUnit(scmPurReceiveEntry.getPieUnit());
	                scmInvPurInWarehsBillEntry.setBaseUnit(scmPurReceiveEntry.getBaseUnit());
	                if (scmPurReceiveEntry.getPieQty()==null) {
						scmPurReceiveEntry.setPieQty(BigDecimal.ZERO);
					}
	                scmInvPurInWarehsBillEntry.setPieQty(scmPurReceiveEntry.getPieQty());
	                scmInvPurInWarehsBillEntry.setPrice(scmPurReceiveEntry.getPrice().multiply(invConvRate).divide(purConvRate,pricePrecision,BigDecimal.ROUND_HALF_UP));
	                if (StringUtils.equals(rebateType, "1") && !receive.isUnified()) {//统配下单价折让失效
	                	BigDecimal qty = scmPurReceiveEntry.getInvQty().subtract(scmPurReceiveEntry.getConcessiveRecQty());
	                	scmInvPurInWarehsBillEntry.setAmt(qty.multiply(scmPurReceiveEntry.getPrice()));
	                	scmInvPurInWarehsBillEntry.setTaxAmt(qty.multiply(scmPurReceiveEntry.getTaxPrice()));
	                	scmInvPurInWarehsBillEntry.setQty(qty);
					}else {
						scmInvPurInWarehsBillEntry.setAmt(scmPurReceiveEntry.getAmt());
						scmInvPurInWarehsBillEntry.setTaxAmt(scmPurReceiveEntry.getTaxAmt());
						scmInvPurInWarehsBillEntry.setQty(scmPurReceiveEntry.getInvQty());
					}
	                scmInvPurInWarehsBillEntry.setTaxRate(scmPurReceiveEntry.getTaxRate());
	                scmInvPurInWarehsBillEntry.setTaxPrice(scmPurReceiveEntry.getTaxPrice().multiply(invConvRate).divide(purConvRate,pricePrecision,BigDecimal.ROUND_HALF_UP));
	                scmInvPurInWarehsBillEntry.setProductDate(scmPurReceiveEntry.getProductDate());
	                scmInvPurInWarehsBillEntry.setExpDate(scmPurReceiveEntry.getExpDate());
	                scmInvPurInWarehsBillEntry.setCostUseTypeId(scmPurReceiveEntry.getCostUseTypeId());
	                scmInvPurInWarehsBillEntry.setUseOrgUnitNo(scmPurReceiveEntry.getUseOrgUnitNo());
	                if(StringUtils.isNotBlank(scmPurReceiveEntry.getUseOrgUnitNo())){
	                    if(!cstHashMap.containsKey(scmPurReceiveEntry.getUseOrgUnitNo())){
	                        List<OrgCostCenter2> orgCenterList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOCOST, scmPurReceiveEntry.getUseOrgUnitNo(), false, null, param);
	                        OrgBaseUnit2 useOrgUnitNo = orgUnitBiz.selectbyOrgNo(scmPurReceiveEntry.getUseOrgUnitNo(), param);
	                        if(orgCenterList==null || orgCenterList.isEmpty()){
	                            throw new AppException(Message.getMessage(param.getLang(),"iscm.purchasemanage.purchasebusiness.orgUnitRelation.error.notcst",new String[]{useOrgUnitNo.getOrgUnitName()}));
	                        }
	                        cstHashMap.put(scmPurReceiveEntry.getUseOrgUnitNo(), orgCenterList.get(0));
	                    }
	                    scmInvPurInWarehsBillEntry.setCostOrgUnitNo(((OrgCostCenter2)cstHashMap.get(scmPurReceiveEntry.getUseOrgUnitNo())).getOrgUnitNo());
	                }
	                scmInvPurInWarehsBillEntry.setOrgUnitNo(scmPurReceiveEntry.getInvOrgUnitNo());
	                scmInvPurInWarehsBillEntry.setWareHouseId(scmPurReceiveEntry.getWareHouseId());
	                scmInvPurInWarehsBillEntry.setFinOrgUnitNo(scmPurReceiveEntry.getFinOrgUnitNo());
	                scmInvPurInWarehsBillEntry.setBalanceVendorId(scmPurReceiveEntry.getBalanceSupplierId());
	                scmInvPurInWarehsBillEntry.setStorageOrgUnitNo(scmPurReceiveEntry.getStorageOrgUnitNo());
	                scmInvPurInWarehsBillEntry.setRequireOrgUnitNo(scmPurReceiveEntry.getReqOrgUnitNo());
	                scmInvPurInWarehsBillEntry.setPurOrgUnitNo(scmPurReceiveEntry.getPurOrgUnitNo());
	                scmInvPurInWarehsBillEntry.setBuyerId(scmPurReceiveEntry.getBuyerId());
	                scmInvPurInWarehsBillEntry.setPurGroupId(scmPurReceiveEntry.getPurGroupId());
	                scmInvPurInWarehsBillEntry.setSourceBillDtlId(scmPurReceiveEntry.getId());
	                scmInvPurInWarehsBillEntry.setPvNo(scmPurReceiveEntry.getPvNo());
	                scmInvPurInWarehsBillEntry.setPriceBillId(scmPurReceiveEntry.getPriceBillId());
	                scmInvPurInWarehsBillEntry.setRefPriceStatus(scmPurReceiveEntry.getRefPriceStatus());
	                scmInvPurInWarehsBillEntry.setRemarks(scmPurReceiveEntry.getRemarks());
	                scmInvPurInWarehsBillEntry.setNotWriteBack(true);
	                //此处需优化
	                scmPurReceiveEntry.setRowStatus("C");
	                scmPurReceiveEntry.setAddInQty(scmPurReceiveEntry.getInvQty());
	                if (StringUtils.equals(rebateType, "1")&& !receive.isUnified() && BigDecimal.ZERO.compareTo(scmPurReceiveEntry.getConcessiveRecQty())<0) {
	                	ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry2 = rebateTypeEdit(receive,scmPurReceiveEntry,scmInvPurInWarehsBillEntry,param);
	                	scmInvPurInWarehsBillEntryList.add(scmInvPurInWarehsBillEntry2);
	                	scmPurReceiveEntry.setAddInQty(scmPurReceiveEntry.getAddInQty().add(scmInvPurInWarehsBillEntry2.getQty()));
	                }
	                scmPurReceiveEntryBiz.updateDirect(scmPurReceiveEntry, param);
	                lastScmPurReceiveEntry = scmPurReceiveEntry;
	                if (StringUtils.equals(rebateType, "1")&& !receive.isUnified()&& BigDecimal.ZERO.compareTo(scmInvPurInWarehsBillEntry.getQty())==0) {
						continue;
					}
	                scmInvPurInWarehsBillEntryList.add(scmInvPurInWarehsBillEntry);
	            }
	            bean.setList2(scmInvPurInWarehsBillEntryList);
	            this.add(bean, param);
	            scmPurReceiveEntryBiz.updateBillStatusByEntry(lastScmPurReceiveEntry, param);
	            return scmInvPurInWarehsBill;
	        }else {
	        	//明细没有符合条件的数据则单据直接关闭
	        	receive.setStatus("C");
	        	scmPurReceiveBiz.update(receive, param);
	        }
        }
		return null;
	}


	private ScmInvPurInWarehsBillEntry2 rebateTypeEdit(ScmPurReceive2 receive,ScmPurReceiveEntry2 scmPurReceiveEntry,ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry, Param param) {
		BigDecimal purConvRate = scmPurReceiveEntry.getPurConvRate();//采购单位转换系数
        BigDecimal invConvRate = scmPurReceiveEntry.getConvrate();//库存单位转换系数
        int pricePrecision = Integer.parseInt(sysParamBiz.getValue(receive.getOrgUnitNo(), "SCM_PricePrecision", "4", param));
        int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
		ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry2 = new ScmInvPurInWarehsBillEntry2(true);
		BeanUtil.copyProperties(scmInvPurInWarehsBillEntry2, scmInvPurInWarehsBillEntry);
		scmInvPurInWarehsBillEntry2.setQty(scmPurReceiveEntry.getUnqualifiedQty());
		BigDecimal price = scmPurReceiveEntry.getPrice().multiply(invConvRate).divide(purConvRate,pricePrecision,BigDecimal.ROUND_HALF_UP);
		if (BigDecimal.ZERO.compareTo(scmPurReceiveEntry.getConcessiveRecRate())!=0) {
			price = price.multiply(scmPurReceiveEntry.getConcessiveRecRate());
		}else {
			price = BigDecimal.ZERO;
		}
		scmInvPurInWarehsBillEntry2.setPrice(price);
		scmInvPurInWarehsBillEntry2.setAmt(scmInvPurInWarehsBillEntry2.getQty().multiply(price));
		scmInvPurInWarehsBillEntry2.setTaxRate(scmPurReceiveEntry.getTaxRate());
        BigDecimal c = (scmInvPurInWarehsBillEntry2.getPrice()).multiply((BigDecimal.ONE).add(scmInvPurInWarehsBillEntry2.getTaxRate())).setScale(pricePrecision,RoundingMode.HALF_UP);
        scmInvPurInWarehsBillEntry2.setTaxPrice(c.multiply(invConvRate).divide(purConvRate,pricePrecision,BigDecimal.ROUND_HALF_UP));
        BigDecimal d = (scmInvPurInWarehsBillEntry2.getAmt()).multiply((BigDecimal.ONE).add(scmInvPurInWarehsBillEntry2.getTaxRate())).setScale(amtPrecision,RoundingMode.HALF_UP);
        scmInvPurInWarehsBillEntry2.setTaxAmt(d);
        scmInvPurInWarehsBillEntry2.setRemarks(Message.getMessage(param.getLang(),"iscm.inventorymanage.warehouseinbusiness.ScmInvPurInWarehsBillBizImpl.rebateTypeEdit"));
        scmInvPurInWarehsBillEntry2.setPriceBillId(0);
        scmInvPurInWarehsBillEntry2.setRefPriceStatus("0");
		return scmInvPurInWarehsBillEntry2;
	}

	@Override
	public List<ScmInvPurInWarehsBill2> checkUnPostBill(String finOrgUnitNo,long periodId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmInvPurInWarehsBillDAO)dao).checkUnPostBill(map);
	}

	@Override
	public void generatePurInWarehsBillByReturns(ScmPurReturns2 scmPurReturns,
			Param param) throws AppException {
		List<ScmPurReturnsEntry2> scmPurReturnsEntryList = scmPurReturnsEntryBiz.selectByNotRt(scmPurReturns.getId(), param);
		if(scmPurReturnsEntryList!=null && !scmPurReturnsEntryList.isEmpty()){
			int pricePrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PricePrecision", "4", param));
			CommonBean bean = new CommonBean();
            List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList = new ArrayList<>();
            List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = new ArrayList<>();
            ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = new ScmInvPurInWarehsBill2(true);
            scmInvPurInWarehsBill.setStatus("I");
            scmInvPurInWarehsBill.setBizType("6");	//普通采购退货
            scmInvPurInWarehsBill.setFinOrgUnitNo(scmPurReturns.getFinOrgUnitNo());
            scmInvPurInWarehsBill.setCreateOrgUnitNo(scmPurReturns.getOrgUnitNo());
            scmInvPurInWarehsBill.setOrgUnitNo(scmPurReturns.getOrgUnitNo());
            scmInvPurInWarehsBill.setVendorId(scmPurReturns.getVendorId());
            scmInvPurInWarehsBill.setBizDate(FormatUtils.parseDate(FormatUtils.fmtDate(CalendarUtil.getDate(param))));
            scmInvPurInWarehsBill.setBillType("PurReturns");	//来源单为退货单
            scmInvPurInWarehsBill.setUnified(scmPurReturns.isUnified());
            scmInvPurInWarehsBill.setCurrencyNo(scmPurReturns.getCurrencyNo());
            scmInvPurInWarehsBill.setExchangeRate(scmPurReturns.getExchangeRate());
            scmInvPurInWarehsBill.setCreator(param.getUsrCode());
            scmInvPurInWarehsBill.setCreateDate(CalendarUtil.getDate(param));
            scmInvPurInWarehsBill.setControlUnitNo(param.getControlUnitNo());
            scmInvPurInWarehsBill.setPurOrgUnitNo(scmPurReturns.getPurOrgUnitNo());
            scmInvPurInWarehsBillList.add(scmInvPurInWarehsBill);
            bean.setList(scmInvPurInWarehsBillList);
            HashMap<String,Object> cstHashMap = new HashMap<String,Object>();
            for(ScmPurReturnsEntry2 scmPurReturnsEntry:scmPurReturnsEntryList){
        		//入库明细
            	ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry = new ScmInvPurInWarehsBillEntry2(true);
        		scmInvPurInWarehsBillEntry.setItemId(scmPurReturnsEntry.getItemId());
        		scmInvPurInWarehsBillEntry.setUnit(scmPurReturnsEntry.getUnit());
        		scmInvPurInWarehsBillEntry.setPieUnit(scmPurReturnsEntry.getPieUnit());
        		scmInvPurInWarehsBillEntry.setBaseUnit(scmPurReturnsEntry.getBaseUnit());
        		scmInvPurInWarehsBillEntry.setQty(scmPurReturnsEntry.getInvQty());
        		scmInvPurInWarehsBillEntry.setPieQty(scmPurReturnsEntry.getPieQty());
        		scmInvPurInWarehsBillEntry.setPrice(scmPurReturnsEntry.getAmt().divide(scmPurReturnsEntry.getInvQty(),pricePrecision,BigDecimal.ROUND_HALF_UP));
        		scmInvPurInWarehsBillEntry.setAmt(scmPurReturnsEntry.getAmt());
        		scmInvPurInWarehsBillEntry.setTaxRate(scmPurReturnsEntry.getTaxRate());
        		scmInvPurInWarehsBillEntry.setTaxPrice(scmInvPurInWarehsBillEntry.getPrice().multiply(BigDecimal.ONE.add(scmInvPurInWarehsBillEntry.getTaxRate())));
        		scmInvPurInWarehsBillEntry.setTaxAmt(scmPurReturnsEntry.getTaxAmt());
        		scmInvPurInWarehsBillEntry.setProductDate(scmPurReturnsEntry.getProductDate());
        		scmInvPurInWarehsBillEntry.setExpDate(scmPurReturnsEntry.getExpDate());
        		scmInvPurInWarehsBillEntry.setUseOrgUnitNo(scmPurReturnsEntry.getUseOrgUnitNo());
        		scmInvPurInWarehsBillEntry.setCostUseTypeId(scmPurReturnsEntry.getCostUseTypeId());
        		if(StringUtils.isNotBlank(scmPurReturnsEntry.getUseOrgUnitNo())){
        			if(!cstHashMap.containsKey(scmPurReturnsEntry.getUseOrgUnitNo())){
            			List<OrgCostCenter2> orgCenterList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOCOST, scmPurReturnsEntry.getUseOrgUnitNo(), false, null, param);
                        OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurReturnsEntry.getUseOrgUnitNo(), param);
            			if(orgCenterList==null || orgCenterList.isEmpty()){
            				throw new AppException(Message.getMessage(param.getLang(),"iscm.purchasemanage.purchasebusiness.orgUnitRelation.error.notcst",new String[]{orgBaseUnit.getOrgUnitName()}));
            			}
            			cstHashMap.put(scmPurReturnsEntry.getUseOrgUnitNo(), orgCenterList.get(0));
        			}
            		scmInvPurInWarehsBillEntry.setCostOrgUnitNo(((OrgCostCenter2)cstHashMap.get(scmPurReturnsEntry.getUseOrgUnitNo())).getOrgUnitNo());
        		}
        		scmInvPurInWarehsBillEntry.setOrgUnitNo(scmPurReturnsEntry.getInvOrgUnitNo());
        		scmInvPurInWarehsBillEntry.setWareHouseId(scmPurReturnsEntry.getWareHouseId());
        		scmInvPurInWarehsBillEntry.setFinOrgUnitNo(scmPurReturnsEntry.getFinOrgUnitNo());
        		scmInvPurInWarehsBillEntry.setBalanceVendorId(scmPurReturnsEntry.getBalanceSupplierId());
        		scmInvPurInWarehsBillEntry.setStorageOrgUnitNo(scmPurReturnsEntry.getStorageOrgUnitNo());
        		scmInvPurInWarehsBillEntry.setRequireOrgUnitNo(scmPurReturnsEntry.getRequireOrgUnitNo());
        		scmInvPurInWarehsBillEntry.setPurOrgUnitNo(scmPurReturnsEntry.getPurOrgUnitNo());
        		if(scmPurReturnsEntry.getSourceDtlId()>0) {
        			//退货申请由收货单生成则需找回原入库单的批次
        			HashMap<String,Object> map = new HashMap<String,Object>();
        			map.put(ScmInvPurInWarehsBillEntry.FN_SOURCEBILLDTLID, scmPurReturnsEntry.getSourceDtlId());
        			List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList2 = scmInvPurInWarehsBillEntryBiz.findAll(map, param);
        			if(scmInvPurInWarehsBillEntryList2!=null && !scmInvPurInWarehsBillEntryList2.isEmpty())
        				scmInvPurInWarehsBillEntry.setLot(scmInvPurInWarehsBillEntryList2.get(0).getLot());
        		}
        		scmInvPurInWarehsBillEntry.setSourceBillDtlId(scmPurReturnsEntry.getId());
        		scmInvPurInWarehsBillEntry.setRemarks(scmPurReturnsEntry.getRemarks());
        		scmInvPurInWarehsBillEntryList.add(scmInvPurInWarehsBillEntry);
            }
            bean.setList2(scmInvPurInWarehsBillEntryList);
            this.add(bean, param);
		}
	}

	@Override
	public List<ScmInvPurInWarehsBill2> selectByPurReceive(ScmPurReceive2 receive, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pvId", receive.getId());
		return ((ScmInvPurInWarehsBillDAO)dao).selectByPurReceive(map);
	}

	@Override
	public List<ScmInvPurInWarehsBill2> selectByPurReturns(ScmPurReturns2 pruReturns, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rtId", pruReturns.getId());
		return ((ScmInvPurInWarehsBillDAO)dao).selectByPurReturns(map);
	}

	@Override
	protected void beforeDelete(ScmInvPurInWarehsBill2 entity, Param param)
			throws AppException {
		ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = this.selectDirect(entity.getWrId(), param);
		if(scmInvPurInWarehsBill!=null && !StringUtils.equals(scmInvPurInWarehsBill.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getWrNo()}));
		}
		if(StringUtils.equals("1", entity.getBizType())) {
			//由收货生成的采购入库，检查是否有生成退货申请，有则一并删除
			List<ScmPurReturns2> scmPurReturnsList = scmPurReturnsBiz.selectByPurInwareHouse(entity.getWrId(), param);
			if(scmPurReturnsList!=null && !scmPurReturnsList.isEmpty()) {
				for(ScmPurReturns2 scmPurReturns:scmPurReturnsList) {
					if(!StringUtils.equals(scmPurReturns.getStatus(),"I")) {
						throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvPurInWarehsBill.error.returnbillnotnewstatus", new String[]{scmPurReturns.getRtNo()}));
					}
				}
				scmPurReturnsBiz.delete(scmPurReturnsList, param);
			}
		}else if(StringUtils.contains("6,8", entity.getBizType())) {
			if(StringUtils.equals("6", entity.getBizType())) {
				//由采购通货生成的退货入库不能直接删除，只能由退货申请取消下达进行删除
				List<ScmPurReturns2> scmPurReturnsList = scmPurReturnsBiz.selectByPurInwareHouseReturn(entity.getWrId(), param);
				if(scmPurReturnsList!=null && !scmPurReturnsList.isEmpty()) {
					for(ScmPurReturns2 scmPurReturns:scmPurReturnsList) {
						if(StringUtils.equals("E",scmPurReturns.getStatus())) {
							throw new AppException("iscm.inventorymanage.ScmInvPurInWarehsBill.error.buildbyreturn");
						}
					}
				}
			}
			//采购退货需同步删除内部供应商的销售退货单
			Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmInvPurInWarehsBill.getVendorId(),param);
			if(scmsupplier!=null && StringUtils.equals("2",scmsupplier.getRole())) {
				List<ScmInvSaleIssueBill2> scmInvSaleIssueBillList = scmInvSaleIssueBillBiz.selectByPurInwareHouse(entity.getWrId(), param);
				if(scmInvSaleIssueBillList!=null && !scmInvSaleIssueBillList.isEmpty()) {
					for(ScmInvSaleIssueBill2 scmInvSaleIssueBill:scmInvSaleIssueBillList) {
						if(!StringUtils.equals(scmInvSaleIssueBill.getStatus(),"I")) {
							throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvPurInWarehsBill.error.saleIssuebillnotnewstatus", new String[]{scmInvSaleIssueBill.getOtNo()}));
						}
					}
					scmInvSaleIssueBillBiz.delete(scmInvSaleIssueBillList, param);
				}
			}
		}
		if(entity != null && entity.getWrId() > 0){
			//删除入库明细
			scmInvPurInWarehsBillEntryBiz.deleteByWrId(entity.getWrId(), param);
		}
		SupplierPlatUtil supplierPlatUtil = new SupplierPlatUtil();
		supplierPlatUtil.changeSyncData(entity, param);
	}

	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvPurInWarehsBillAdvQuery) {
				ScmInvPurInWarehsBillAdvQuery scmInvPurInWarehsBillAdvQuery = (ScmInvPurInWarehsBillAdvQuery) page.getModel();
				if(StringUtils.isNotBlank(scmInvPurInWarehsBillAdvQuery.getWrNo())){
					page.getParam().put(ScmInvPurInWarehsBill2.FN_WRNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_WRNO, QueryParam.QUERY_LIKE,"%"+scmInvPurInWarehsBillAdvQuery.getWrNo()+"%"));
				}
				if(scmInvPurInWarehsBillAdvQuery.getVendorId()>0){
					page.getParam().put(ScmInvPurInWarehsBill2.FN_VENDORID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_VENDORID, QueryParam.QUERY_EQ,String.valueOf(scmInvPurInWarehsBillAdvQuery.getVendorId())));
				}
				if(StringUtils.isNotBlank(scmInvPurInWarehsBillAdvQuery.getBizType())){
					page.getParam().put(ScmInvPurInWarehsBill2.FN_BIZTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_BIZTYPE, QueryParam.QUERY_EQ,String.valueOf(scmInvPurInWarehsBillAdvQuery.getBizType())));
				}
				if(scmInvPurInWarehsBillAdvQuery.getBizDateFrom()!=null){
					if(scmInvPurInWarehsBillAdvQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmInvPurInWarehsBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvPurInWarehsBillAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(scmInvPurInWarehsBillAdvQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmInvPurInWarehsBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvPurInWarehsBillAdvQuery.getBizDateFrom())));
					}
				}else if(scmInvPurInWarehsBillAdvQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmInvPurInWarehsBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmInvPurInWarehsBillAdvQuery.getBizDateTo())));
				}
				if(scmInvPurInWarehsBillAdvQuery.getCreateDateFrom()!=null){
					if(scmInvPurInWarehsBillAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmInvPurInWarehsBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvPurInWarehsBillAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvPurInWarehsBillAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmInvPurInWarehsBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvPurInWarehsBillAdvQuery.getCreateDateFrom())));
					}
				}else if(scmInvPurInWarehsBillAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmInvPurInWarehsBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvPurInWarehsBillAdvQuery.getCreateDateTo(),1))));
				}
				if(StringUtils.isNotBlank(scmInvPurInWarehsBillAdvQuery.getPurOrgUnitNo())){
					if(StringUtils.isNotBlank(page.getSqlCondition())){
						page.setSqlCondition((page.getSqlCondition()+" and scminvpurinwarehsbill.wrId in (Select wrId From scminvpurinwarehsbillEntry Where purOrgUnitNo='"+scmInvPurInWarehsBillAdvQuery.getPurOrgUnitNo()+"')"));
					}else{
						page.setSqlCondition("scminvpurinwarehsbill.wrId in (Select wrId From scminvpurinwarehsbillEntry Where purOrgUnitNo='"+scmInvPurInWarehsBillAdvQuery.getPurOrgUnitNo()+"')");
					}
				}
				if(StringUtils.isNotBlank(scmInvPurInWarehsBillAdvQuery.getStatus())){
					String status[] = StringUtils.split(scmInvPurInWarehsBillAdvQuery.getStatus(), ",");
					StringBuffer statusBuffer = new StringBuffer("");
					for(String sta:status){
						if(StringUtils.isNotBlank(statusBuffer.toString()))	
							statusBuffer.append(",");
						statusBuffer.append("'").append(sta).append("'");
					}
					if(StringUtils.isNotBlank(statusBuffer.toString())){
						page.getParam().put(ScmInvPurInWarehsBill2.FN_STATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_STATUS, QueryParam.QUERY_IN,statusBuffer.toString()));
					}
				}
				if(StringUtils.isNotBlank(scmInvPurInWarehsBillAdvQuery.getDeptOrWarehs())){
					if(StringUtils.equals("W", scmInvPurInWarehsBillAdvQuery.getStoreType())) {
						if(StringUtils.isNotBlank(page.getSqlCondition())){
							page.setSqlCondition((page.getSqlCondition()+" and scminvpurinwarehsbill.wrId in (Select wrId From scminvpurinwarehsbillEntry Where wareHouseId="+scmInvPurInWarehsBillAdvQuery.getDeptOrWarehs()+")"));	
						}else{
							page.setSqlCondition("scminvpurinwarehsbill.wrId in (Select wrId From scminvpurinwarehsbillEntry Where wareHouseId="+scmInvPurInWarehsBillAdvQuery.getDeptOrWarehs()+")");
						}
					}else {
						if(StringUtils.isNotBlank(page.getSqlCondition())){
							page.setSqlCondition((page.getSqlCondition()+" and scminvpurinwarehsbill.wrId in (Select wrId From scminvpurinwarehsbillEntry Where useOrgUnitNo='"+scmInvPurInWarehsBillAdvQuery.getDeptOrWarehs()+"')"));	
						}else{
							page.setSqlCondition("scminvpurinwarehsbill.wrId in (Select wrId From scminvpurinwarehsbillEntry Where useOrgUnitNo='"+scmInvPurInWarehsBillAdvQuery.getDeptOrWarehs()+"')");
						}
					}
				}
				String filterWarehouseByUsr = ScmWarehouseUtil.filterWarehouseByUsr("", param);
				if (StringUtils.isNotBlank(filterWarehouseByUsr)) {
					if(StringUtils.isNotBlank(page.getSqlCondition())){
						page.setSqlCondition((page.getSqlCondition()+" and scminvpurinwarehsbill.wrId in (Select wrId From scminvpurinwarehsbillEntry Where wareHouseId in("+filterWarehouseByUsr+"))"));	
					}else{
						page.setSqlCondition("scminvpurinwarehsbill.wrId in (Select wrId From scminvpurinwarehsbillEntry Where wareHouseId in("+filterWarehouseByUsr+"))");
					}
				}
				if(StringUtils.isNotBlank(scmInvPurInWarehsBillAdvQuery.getOrgUnitNo())) {
					if(StringUtils.isNotBlank(page.getSqlCondition())){
						page.setSqlCondition(page.getSqlCondition()+" and ScmInvPurInWarehsBill.orgUnitNo='"+scmInvPurInWarehsBillAdvQuery.getOrgUnitNo()+"'");
					}else {
						page.setSqlCondition("ScmInvPurInWarehsBill.orgUnitNo='"+scmInvPurInWarehsBillAdvQuery.getOrgUnitNo()+"'");
					}
				}
				if(StringUtils.isNotBlank(scmInvPurInWarehsBillAdvQuery.getPvNos())) {
					page.getParam().put(ScmInvPurInWarehsBill2.FN_PVNOS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_PVNOS, QueryParam.QUERY_LIKE,"%"+scmInvPurInWarehsBillAdvQuery.getPvNos()+"%"));
				}
			}
		}
 	}

	@Override
	public List<ScmInvPurInWarehsBill2> checkPostedBill(String finOrgUnitNo,long periodId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmInvPurInWarehsBillDAO)dao).checkPostedBill(map);
	}

    @Override
    public List<Apinvoice2> generateApInvoice(List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList, Param param)
            throws AppException {
        List<Apinvoice2> apInvoiceList = new ArrayList<>();
        if(scmInvPurInWarehsBillList!=null && !scmInvPurInWarehsBillList.isEmpty()){
            for(ScmInvPurInWarehsBill2 scmInvPurInWarehsBills : scmInvPurInWarehsBillList){
            	if (BigDecimal.ZERO.compareTo(scmInvPurInWarehsBills.getTaxAmt()) == 0) {
            		//回写数据
            		List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = scmInvPurInWarehsBillEntryBiz.selectByWrId(scmInvPurInWarehsBills.getWrId(), param);
            		if (!(scmInvPurInWarehsBillEntryList == null || scmInvPurInWarehsBillEntryList.isEmpty())) {
            			for(ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList){
            				scmInvPurInWarehsBillEntry.setBuildAP(true);
                            scmInvPurInWarehsBillEntryBiz.updateBuildAPStatus(scmInvPurInWarehsBillEntry, param);
            			}
            		}
                	continue;
                }
                apInvoiceList.add(generateApInvoice(scmInvPurInWarehsBills, param));
            }
        }
        return apInvoiceList;
    }
    
    @Override
	public CommonBean asyngenerateApInvoice(ScmDataCollectionStepState2 stepState,
			final List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList, final Param param) throws AppException {
    	final CommonBean commonBean = new CommonBean(); 
    	final List<Apinvoice2> apInvoiceList = new ArrayList<>();
		ScmDataCollectionStepState2 scmDataCollectionStepState = scmDataCollectionStepStateBiz.updateByAsynProcessed(stepState,ScmDataCollectionStepState2.SATATE_RUN, null, param);
		final ScmDataCollectionStepState2 tempScmStepData = new ScmDataCollectionStepState2();
		BeanUtil.copyProperties(tempScmStepData, scmDataCollectionStepState);
		//进行夜核步骤操作
		ExecutorService executors = Executors.newCachedThreadPool();
		executors.execute(new Runnable() {
			@Override
			public void run() {
				try {
					  if(scmInvPurInWarehsBillList!=null && !scmInvPurInWarehsBillList.isEmpty()){
						  apInvoiceList.addAll(generateApInvoice(scmInvPurInWarehsBillList, param));
						  System.out.println(apInvoiceList.size());
				        }
					//更新状态
					scmDataCollectionStepStateBiz.updateByAsynProcessed(tempScmStepData, ScmDataCollectionStepState.SATATE_SUCCESS,String.valueOf(apInvoiceList==null?0:apInvoiceList.size()), param);
				} catch (Exception e) {
					//保存错误信息
					scmDataCollectionStepStateBiz.updateByAsynProcessed(tempScmStepData, ScmDataCollectionStepState.SATATE_FAIL, e.getMessage(), param);
				}
			}
		});
		stepState.setState(scmDataCollectionStepState.getState());
		commonBean.setObject(stepState);
		return commonBean;
	}

    private Apinvoice2 generateApInvoice(ScmInvPurInWarehsBill2 scmInvPurInWarehsBills, Param param) {
        return apinvoiceBiz.generateApInvoicesBillByPurInWarehs(scmInvPurInWarehsBills, param);
    }

	@Override
	public List<String> submit(List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList, Param param) throws AppException {
		List<String> rntList = new ArrayList<>();
		if(scmInvPurInWarehsBillList!=null && !scmInvPurInWarehsBillList.isEmpty()) {
			for(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill2:scmInvPurInWarehsBillList) {
				ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = new ScmInvPurInWarehsBill2();
				if(scmInvPurInWarehsBill2.getWrId()>0){
					scmInvPurInWarehsBill = this.selectDirect(scmInvPurInWarehsBill2.getWrId(),param);
				} else {
					Page page=new Page();
					page.setModelClass(ScmInvPurInWarehsBill2.class);
					page.setShowCount(Integer.MAX_VALUE);
					page.getParam().put(
							ScmInvPurInWarehsBill2.FN_WRNO,
							new QueryParam(ScmInvPurInWarehsBill2.FN_WRNO, QueryParam.QUERY_EQ,
									scmInvPurInWarehsBill2.getWrNo()));
					
					List<ScmInvPurInWarehsBill2> scmInvPurInWarehsList =this.findPage(page, param);
					
					if(scmInvPurInWarehsList!=null && !scmInvPurInWarehsList.isEmpty()){
						scmInvPurInWarehsBill = scmInvPurInWarehsList.get(0);
					}
				}
				
				if(scmInvPurInWarehsBill!=null){
					this.setConvertMap(scmInvPurInWarehsBill, param);
					if(!scmInvPurInWarehsBill.getStatus().equals("I")){
						rntList.add(Message.getMessage(param.getLang(),"iscm.common.submit.error.billnotnewstatus",new String[] {scmInvPurInWarehsBill.getWrNo()}));
					}else if(scmInvPurInWarehsBill.getStatus().equals("I")){
						List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsEntryList = scmInvPurInWarehsBillEntryBiz.selectByWrId(scmInvPurInWarehsBill.getWrId(), param);
						
						BillType2 billType = billTypeBiz.selectByOrgAndCode(scmInvPurInWarehsBill.getFinOrgUnitNo(), "InvPurInWhsBill", param);
						boolean needAudit = false;
						if(billType!=null && billType.isNeedAudit())
							needAudit = true;
						if(needAudit){
							String processInstanceId = startWorkFlow(billType, scmInvPurInWarehsBill,scmInvPurInWarehsEntryList, param);
							scmInvPurInWarehsBill.setWorkFlowNo(processInstanceId);
							ActivityUtil activityUtil = new ActivityUtil();
							//判断当前流程是否结束
							boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
							if(isCompleteTask) {
								if(param.getUsrCode()!=null ){
									scmInvPurInWarehsBill.setChecker(param.getUsrCode());
									scmInvPurInWarehsBill.setSubmitter(param.getUsrCode());
								}
								scmInvPurInWarehsBill.setCheckDate(CalendarUtil.getDate(param));
								scmInvPurInWarehsBill.setSubmitDate(CalendarUtil.getDate(param));
								scmInvPurInWarehsBill.setStatus("A");
								scmInvPurInWarehsBill.setPendingUsr("");
							}else {
								if(param.getUsrCode()!=null ){
									scmInvPurInWarehsBill.setSubmitter(param.getUsrCode());
								}
								scmInvPurInWarehsBill.setSubmitDate(CalendarUtil.getDate(param));

								scmInvPurInWarehsBill.setStatus("D");
								sendAuditMsg(scmInvPurInWarehsBill,billType.getBillCode(),param);
							}
						}else{
							if(billType.isUseFlow()) {
								//启用工作流（不影响状态）
								String processInstanceId = startWorkFlow(billType, scmInvPurInWarehsBill,scmInvPurInWarehsEntryList, param);
								scmInvPurInWarehsBill.setWorkFlowNo(processInstanceId);
								scmInvPurInWarehsBill.setSubmitDate(CalendarUtil.getDate(param));
								ActivityUtil activityUtil = new ActivityUtil();
								//判断当前流程是否结束
								boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
								if(!isCompleteTask) {
									sendAuditMsg(scmInvPurInWarehsBill,billType.getBillCode(),param);
								}else{
									scmInvPurInWarehsBill.setPendingUsr("");
								}
							}
							if(param.getUsrCode()!=null ){
								scmInvPurInWarehsBill.setChecker(param.getUsrCode());
								scmInvPurInWarehsBill.setSubmitter(param.getUsrCode());
							}
							scmInvPurInWarehsBill.setCheckDate(CalendarUtil.getDate(param));
							scmInvPurInWarehsBill.setSubmitDate(CalendarUtil.getDate(param));
							scmInvPurInWarehsBill.setStatus("A");
						}
						
						try{
							this.updateStatus(scmInvPurInWarehsBill, param);
						}catch(AppException e){
							throw e;
						}
						SystemState systemState = systemStateBiz.selectBySystemId(scmInvPurInWarehsBill.getFinOrgUnitNo(), 170, param);
						if(systemState==null){
							rntList.add(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
							return rntList;
						}
						if (systemState.getCurrentPeriodId() == scmInvPurInWarehsBill.getPeriodId()) {
							if(StringUtils.equals("A", scmInvPurInWarehsBill.getStatus())) {
								//通过或部分通过时检查是否自动过帐
								if(billType!=null && billType.isAutoRelease()) {
									List<String> msgList = this.postBillCheck(scmInvPurInWarehsBill, param);
									if (msgList != null && !msgList.isEmpty()) {
										StringBuilder detailInfo = new StringBuilder("");
				                        detailInfo.append(Message.getMessage(param.getLang(),"iscm.inventorymanage.purinwarehsbill.post.errorTitle"));
	
										for (String str : msgList) {
				                            detailInfo.append(str).append("\n");
				                        }
										
										throw new AppException(detailInfo.toString(), new String[]{scmInvPurInWarehsBill.getWrNo()});
									}
									
									this.postBill(scmInvPurInWarehsBill, param);
								}
							}
						}
					}
				}else{
					rntList.add(Message.getMessage(param.getLang(),"iscm.common.error.ordernotexists",new String[] {scmInvPurInWarehsBill2.getWrNo()}));
				}
			}
		}
		return rntList;
	}

	private String startWorkFlow(BillType2 billType,ScmInvPurInWarehsBill2 scmInvPurInWarehsBill,List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsEntryList, Param param) {
		CommonBean bean = new CommonBean();
		List<ScmInvPurInWarehsBill2> scmInvPurInWarehsList = new ArrayList<>();
		scmInvPurInWarehsList.add(scmInvPurInWarehsBill);
		bean.setList(scmInvPurInWarehsList);
		bean.setList2(scmInvPurInWarehsEntryList);
		String processInstanceId = ActivityUtil.startProcessInstance(bean, billType.getId(), billType.getWorkFlowNo(), param);
		return processInstanceId;
	}
	
	private void sendAuditMsg(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill,String billCode,Param param) {
		String usrList= ActivityUtil.findAssigneeByProcessInstanceId(scmInvPurInWarehsBill.getWorkFlowNo(),param);
		if(StringUtils.isNotBlank(usrList)) {
			scmInvPurInWarehsBill.setPendingUsr(StringUtils.left(usrList,250));
			scmBillPendingBiz.addPendingBill(usrList, scmInvPurInWarehsBill, param);
			InvPurInWarehsParams invPurInWarehsParams = new InvPurInWarehsParams();
			invPurInWarehsParams.setWrNo(scmInvPurInWarehsBill.getWrNo());
			AuditMsgUtil.sendAuditMsg(billCode,scmInvPurInWarehsBill,invPurInWarehsParams, usrList, param);
		}else {
			scmInvPurInWarehsBill.setPendingUsr("");
		}
	}
	@Override
	public List<String> undoSubmit(List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList, Param param) throws AppException {
		List<String> rntList = new ArrayList<>();
		if(scmInvPurInWarehsBillList!=null && !scmInvPurInWarehsBillList.isEmpty()) {
			for(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill2:scmInvPurInWarehsBillList) {
				ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = new ScmInvPurInWarehsBill2();
				if(scmInvPurInWarehsBill2.getWrId()>0){
					scmInvPurInWarehsBill = this.selectDirect(scmInvPurInWarehsBill2.getWrId(),param);
				} else {
					Page page=new Page();
					page.setModelClass(ScmInvPurInWarehsBill2.class);
					page.setShowCount(Integer.MAX_VALUE);
					page.getParam().put(
							ScmInvPurInWarehsBill2.FN_WRNO,
							new QueryParam(ScmInvPurInWarehsBill2.FN_WRNO, QueryParam.QUERY_EQ,
									scmInvPurInWarehsBill2.getWrNo()));
					
					List<ScmInvPurInWarehsBill2> scmInvPurInWarehsList =this.findPage(page, param);
					
					if(scmInvPurInWarehsList!=null && !scmInvPurInWarehsList.isEmpty()){
						scmInvPurInWarehsBill = scmInvPurInWarehsList.get(0);
					}
				}
				
				if(scmInvPurInWarehsBill!=null){
					BillType2 billType = billTypeBiz.selectByOrgAndCode(scmInvPurInWarehsBill.getFinOrgUnitNo(), "InvPurInWhsBill", param);
					boolean needAudit = false;
					if(billType!=null && billType.isNeedAudit() && StringUtils.isNotBlank(scmInvPurInWarehsBill.getWorkFlowNo()))
						needAudit = true;
					
					//单据设置审批流程，但设置没有审批节点，状态为通过，可以取消通过
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put(ScmBillPending.FN_BILLID, new QueryParam(ScmBillPending.FN_BILLID, QueryParam.QUERY_EQ, String.valueOf(scmInvPurInWarehsBill.getWrId())));
					map.put(ScmBillPending.FN_BILLTYPE, new QueryParam(ScmBillPending.FN_BILLTYPE, QueryParam.QUERY_EQ, billType.getBillCode()));
					List<ScmBillPending> scmBillPendingList = scmBillPendingBiz.findAll(map, param);
					
					if(needAudit){
						if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
							if(!StringUtils.equals(scmInvPurInWarehsBill.getStatus(),"A"))
								throw new AppException("iscm.purchasemanage.error.cancelCommit");
						} else {
							if(!StringUtils.equals(scmInvPurInWarehsBill.getStatus(),"D"))
								throw new AppException("iscm.purchasemanage.error.cancelCommit");
						}
					}else{
						//启用审批进入审核中(再调整为启用工作流)的单据的状态不是A
						if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
							if(!StringUtils.equals(scmInvPurInWarehsBill.getStatus(),"A"))
								throw new AppException("iscm.purchasemanage.error.cancelCommit");	
						} else {
							if(!StringUtils.contains("A,D", scmInvPurInWarehsBill.getStatus()))
								throw new AppException("iscm.purchasemanage.error.cancelCommit");
						}
					} 
					
					if(scmInvPurInWarehsBill.getStatus().equals("A") || scmInvPurInWarehsBill.getStatus().equals("D")){
						scmInvPurInWarehsBill.setChecker(null);
						scmInvPurInWarehsBill.setSubmitter(null);
						scmInvPurInWarehsBill.setCheckDate(null);
						scmInvPurInWarehsBill.setSubmitDate(null);
						scmInvPurInWarehsBill.setStatus("I");
						scmInvPurInWarehsBill.setPendingUsr("");
						try{
							this.updateStatus(scmInvPurInWarehsBill, param);
							scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),scmInvPurInWarehsBill, param);
						}catch(AppException e){
							throw e;
						}
					}
					
				}else{
					rntList.add(Message.getMessage(param.getLang(),"iscm.common.error.ordernotexists",new String[] {scmInvPurInWarehsBill2.getWrNo()}));
				}
			}
		}
		return rntList;
	}

	@Override
	public List<ScmInvPurInWarehsBill2> selectBySaleIssueBill(long otId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("otId", otId);
		return ((ScmInvPurInWarehsBillDAO)dao).selectBySaleIssueBill(map);
	}

	@Override
	public ScmInvPurInWarehsBill2 queryInvPurInWarehs(
			ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param)
			throws AppException {
		Page page=new Page();
		page.setModelClass(ScmInvPurInWarehsBill2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmInvPurInWarehsBill2.FN_WRNO,new QueryParam(ScmInvPurInWarehsBill2.FN_WRNO, QueryParam.QUERY_EQ, scmInvPurInWarehsBill.getWrNo()));
		
		List<ScmInvPurInWarehsBill2> scmInvPurInWarehsList =this.findPage(page, param);
		ScmInvPurInWarehsBill2 scmInvPurInWarehs2 = new ScmInvPurInWarehsBill2();
		if(scmInvPurInWarehsList!=null && !scmInvPurInWarehsList.isEmpty()){
			scmInvPurInWarehs2 = scmInvPurInWarehsList.get(0);
			scmInvPurInWarehs2.setScmInvPurInWarehsBillEntryList(scmInvPurInWarehsBillEntryBiz.selectByWrId(scmInvPurInWarehs2.getWrId(), param));
			
			//增加待审人
			ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmInvPurInWarehs2.getWrId(), "InvPurInWhsBill",param);
			if (scmBillPendingUsr != null) {
				scmInvPurInWarehs2.setPendingUsr(scmBillPendingUsr.getUsrCodes());
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmInvPurInWarehs2;
	}

	@Override
	public ScmInvPurInWarehsBill2 doAuditInvPurInWarehs(
			CommonAuditParams commonAuditParams, Param param)
			throws AppException {
		ScmInvPurInWarehsBill2 invPurInWarehs = null;
		
		ScmInvPurInWarehsBill2 scmInvPurInWarehsBill= new ScmInvPurInWarehsBill2();
		scmInvPurInWarehsBill.setWrId(commonAuditParams.getBillId());
		scmInvPurInWarehsBill.setWrNo(commonAuditParams.getBillNo());
		String opinion = commonAuditParams.getOpinion();
		if(StringUtils.equals("Y", opinion) || StringUtils.equals("agree", opinion)) {
			opinion="agree";
		}else if(StringUtils.equals("N", opinion) || StringUtils.equals("refuse", opinion)) {
			opinion="refuse";
		}
		if(scmInvPurInWarehsBill.getWrId()>0){
			invPurInWarehs = this.selectDirect(scmInvPurInWarehsBill.getWrId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmInvPurInWarehsBill2.FN_WRNO,
					new QueryParam(ScmInvPurInWarehsBill2.FN_WRNO, QueryParam.QUERY_EQ,
							scmInvPurInWarehsBill.getWrNo()));
			map.put(ScmInvPurInWarehsBill2.FN_CONTROLUNITNO, new QueryParam(ScmInvPurInWarehsBill2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
							param.getControlUnitNo()));
			List<ScmInvPurInWarehsBill2> scmInvPurInWarehsList =this.findAll(map, param);
			
			if(scmInvPurInWarehsList!=null && !scmInvPurInWarehsList.isEmpty()){
				invPurInWarehs = scmInvPurInWarehsList.get(0);
			}
		}
		
		if (invPurInWarehs != null) {
			this.setConvertMap(invPurInWarehs, param);
			BillType2 billType = billTypeBiz.selectByOrgAndCode(invPurInWarehs.getFinOrgUnitNo(), "InvPurInWhsBill", param);
			if(!(invPurInWarehs.getStatus().equals("D") || invPurInWarehs.getStatus().equals("P")) && billType.isNeedAudit()){
				throw new AppException("iscm.purchasemanage.error.audit");
			}

			if(StringUtils.equals("R", opinion)) {
				scmBillPendingBiz.deletePendingBill(param.getUsrCode(),invPurInWarehs, param);
				commonEventHistoryBiz.updateInvalid(invPurInWarehs,"",param);
				
				CommonAuditOpinion commonAuditOpinionR = new CommonAuditOpinion();
				commonAuditOpinionR.setPreStepNo(invPurInWarehs.getStepNo());
				String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
				commonAuditOpinionR.setStepNo(stepNo);
				commonAuditOpinionR.setActiveType("A");
				commonAuditOpinionR.setOpinion(commonAuditParams.getOpinion());
				commonAuditOpinionR.setRemarks(commonAuditParams.getOpinionRemarks());
				commonEventHistoryBiz.addEventHistory(invPurInWarehs, commonAuditOpinionR, param);
				
				if(billType.isNeedAudit()) {
					//驳回则将单据变回新单状态
					invPurInWarehs.setStatus("I");
					invPurInWarehs.setPendingUsr("");
					invPurInWarehs.setChecker(null);
					invPurInWarehs.setCheckDate(null);
					return this.updateDirect(invPurInWarehs, param);
				}else {
					//不需要审批时驳回需模拟反过账、反提交
					if(StringUtils.equals("E",invPurInWarehs.getStatus())) {
						invPurInWarehs = this.cancelPostBill(invPurInWarehs, param);
					}
					if(StringUtils.equals("A",invPurInWarehs.getStatus())) {
						List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList = new ArrayList();
						scmInvPurInWarehsBillList.add(invPurInWarehs);
						this.undoSubmit(scmInvPurInWarehsBillList, param);
					}
					invPurInWarehs.setStatus("I");
					invPurInWarehs.setPendingUsr("");
					invPurInWarehs.setChecker(null);
					invPurInWarehs.setCheckDate(null);
					return this.updateDirect(invPurInWarehs, param);
				}
			}
			String processInstanceId = invPurInWarehs.getWorkFlowNo();
			boolean isCompleteTask = true;
			
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//完成审批任务
				ActivityUtil.completeTaskByAssigneeAndOpinion(processInstanceId, param.getUsrCode(), opinion,  param);
				ActivityUtil activityUtil = new ActivityUtil();
				//判断当前流程是否结束
				isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
			} else {
				//流程编号不存在
				throw new AppException("com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.doAuditPurRequire.processInstanceId");
			}
			
			//启用审批，或启用工作流（对于历史数据的处理）
			if(billType.isNeedAudit() || (billType.isUseFlow() && StringUtils.contains("D,P", invPurInWarehs.getStatus()))) {
				//P：审核中，A：通过，N：未通过
				if ("agree".equals(opinion)) {
					if (isCompleteTask) {
						invPurInWarehs.setStatus("A");
					} else {
						invPurInWarehs.setStatus("P");
					}
				} else {
					invPurInWarehs.setStatus("N");
				}
			} else {
				if (billType.isUseFlow()) {
					if(StringUtils.equals("refuse", opinion)) {
						//审批拒绝
						if(StringUtils.equals("E",invPurInWarehs.getStatus())) {
							invPurInWarehs = this.cancelPostBill(invPurInWarehs, param);
						}
						invPurInWarehs.setStatus("N");
					}
				}
			}
			scmBillPendingBiz.updatePendingBill(param.getUsrCode(), invPurInWarehs, param);
			invPurInWarehs.setChecker(param.getUsrCode());
			if ("agree".equals(opinion)) {
				this.sendAuditMsg(invPurInWarehs, billType.getBillCode(), param);
			}
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setPreStepNo(invPurInWarehs.getStepNo());
			String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
			try {
				invPurInWarehs.setStepNo(stepNo);
				invPurInWarehs.setCheckDate(CalendarUtil.getDate(param));
				this.updateStatus(invPurInWarehs, param);
			} catch (Exception e) {
				throw e;
			}
			commonAuditOpinion.setStepNo(stepNo);
			commonAuditOpinion.setActiveType("A");
			commonAuditOpinion.setOpinion(commonAuditParams.getOpinion());
			commonAuditOpinion.setRemarks(commonAuditParams.getOpinionRemarks());
			commonEventHistoryBiz.addEventHistory(invPurInWarehs, commonAuditOpinion, param);
			SystemState systemState = systemStateBiz.selectBySystemId(invPurInWarehs.getFinOrgUnitNo(), 170, param);
			if(systemState==null){
				throw new AppException(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
			}
			if (systemState.getCurrentPeriodId() == invPurInWarehs.getPeriodId()) {
				if(StringUtils.equals("A", invPurInWarehs.getStatus())) {
					//通过或部分通过时检查是否自动过帐
					if(billType!=null && billType.isAutoRelease()) {
						List<String> msgList = this.postBillCheck(invPurInWarehs, param);
						if (msgList != null && !msgList.isEmpty()) {
							StringBuilder detailInfo = new StringBuilder("");
	                        detailInfo.append(Message.getMessage(param.getLang(),"iscm.inventorymanage.purinwarehsbill.post.errorTitle"));
	
							for (String str : msgList) {
	                            detailInfo.append(str).append("\n");
	                        }
							
							throw new AppException(detailInfo.toString(), new String[]{invPurInWarehs.getWrNo()});
						}
						
						this.postBill(invPurInWarehs, param);
					}
				}
			}
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return invPurInWarehs;
	}

	private void reSetBillPendingUsr(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param) {
		String scmBillPendingUsr = ActivityUtil.findAssigneeByProcessInstanceId(scmInvPurInWarehsBill.getWorkFlowNo(),param);
		if (StringUtils.isNotBlank(scmBillPendingUsr)) {
			scmInvPurInWarehsBill.setPendingUsr(StringUtils.left(scmBillPendingUsr,250));
		}else {
			scmInvPurInWarehsBill.setPendingUsr("");
		}
	}
	@Override
	public ScmInvPurInWarehsBill2 doUnAuditInvPurInWarehs(
			ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param)
			throws AppException {
		ScmInvPurInWarehsBill2 invInvPurInWarehs = null;
		List<ScmInvPurInWarehsBill2> scmInvPurInWarehsList = new ArrayList<> ();
		List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsEntryList = new ArrayList<> ();
		boolean isFirstTask = false;
		
		if(scmInvPurInWarehsBill.getWrId()>0){
			invInvPurInWarehs = this.selectDirect(scmInvPurInWarehsBill.getWrId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmInvPurInWarehsBill2.FN_WRNO,
					new QueryParam(ScmInvPurInWarehsBill2.FN_WRNO, QueryParam.QUERY_EQ,
							scmInvPurInWarehsBill.getWrNo()));
			map.put(ScmInvPurInWarehsBill2.FN_CONTROLUNITNO, new QueryParam(ScmInvPurInWarehsBill2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
					param.getControlUnitNo()));
			
			scmInvPurInWarehsList = this.findAll(map, param);
			
			if(scmInvPurInWarehsList!=null && !scmInvPurInWarehsList.isEmpty()){
				invInvPurInWarehs=scmInvPurInWarehsList.get(0);
			}
		}
		
		if (invInvPurInWarehs != null) {
			BillType2 billType = billTypeBiz.selectByOrgAndCode(invInvPurInWarehs.getFinOrgUnitNo(), "InvPurInWhsBill", param);
			if(!StringUtils.contains("A,B,N,P", invInvPurInWarehs.getStatus()) && billType.isNeedAudit()){
				throw new AppException("iscm.purchasemanage.unaudit.error.status");
			}
			if(!StringUtils.contains("A,B,N,P,E", invInvPurInWarehs.getStatus()) && billType.isUseFlow()){
				throw new AppException("iscm.purchasemanage.unaudit.error.status");
			}
			String processInstanceId = invInvPurInWarehs.getWorkFlowNo();
			scmInvPurInWarehsEntryList = scmInvPurInWarehsBillEntryBiz.selectByWrId(invInvPurInWarehs.getWrId(), param);
			if(scmInvPurInWarehsEntryList == null || scmInvPurInWarehsEntryList.isEmpty()){
				//抛出异常处理
				throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
			}
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//反审批当前已审节点
				CommonBean bean = new CommonBean();
				scmInvPurInWarehsList.add(invInvPurInWarehs);
				bean.setList(scmInvPurInWarehsList);
				bean.setList2(scmInvPurInWarehsEntryList);
				
				long billTypeId = 0;
				if (billType!=null && billType.isNeedAudit()) {
					billTypeId = billType.getId();
				}
				
				processInstanceId = ActivityUtil.antiAuditProcess(bean, billTypeId, processInstanceId, param.getUsrCode(),  param);
				isFirstTask = ActivityUtil.isFirstTask(processInstanceId, param.getUsrCode(), param);
				invInvPurInWarehs.setWorkFlowNo(processInstanceId);
			} else {
				//流程编号不存在
				throw new AppException("com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.doAuditPurRequire.processInstanceId");
			}
			
			//启用审批，或启用工作流（对于历史数据的处理）
			if(billType.isNeedAudit() || (billType.isUseFlow() && StringUtils.contains("N,D,P", invInvPurInWarehs.getStatus()))) {
				String status = "";
				if (isFirstTask) {
					status = "D";
				} else {
					status = "P";
				}
				invInvPurInWarehs.setStatus(status);
				reSetBillPendingUsr(invInvPurInWarehs,param);
				String tableName = ClassUtils.getFinalModelSimpleName(invInvPurInWarehs);
				CommonEventHistory commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,invInvPurInWarehs.getStepNo(),invInvPurInWarehs.getPK(),param);
				if(commonEventHistory!=null && StringUtils.isNotBlank(commonEventHistory.getPreStepNo())) {
					commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,commonEventHistory.getPreStepNo(),invInvPurInWarehs.getPK(),param);
				}
				if(commonEventHistory!=null) {
					invInvPurInWarehs.setChecker(commonEventHistory.getOper());
					invInvPurInWarehs.setCheckDate(commonEventHistory.getOperDate());
				}else {
					invInvPurInWarehs.setChecker(null);
					invInvPurInWarehs.setCheckDate(null);
				}
				this.updateStatus(invInvPurInWarehs, param);
			}else {
				if (billType.isUseFlow()) {
					//启用审批时订单的状态已更新为审批中时，再启用工作流，反审到首审批节点时，应调整为待审状态
					if (isFirstTask) {
						if (StringUtils.equals("P", invInvPurInWarehs.getStatus())) {
							invInvPurInWarehs.setStatus("D");
						}
					}
					this.updateStatus(invInvPurInWarehs, param);
				}
			}
			scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),invInvPurInWarehs, param);
			commonEventHistoryBiz.updateInvalid(invInvPurInWarehs,invInvPurInWarehs.getStepNo(),param);
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setStepNo(invInvPurInWarehs.getStepNo());
			commonAuditOpinion.setActiveType("U");
			commonAuditOpinion.setOpinion("Y");
			commonEventHistoryBiz.addEventHistory(invInvPurInWarehs, commonAuditOpinion, param);
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		
		return invInvPurInWarehs;
	}

	@Override
	public List<ScmInvPurInWarehsBill2> selectByPurOrder(String detailIdList, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("detailIdList", detailIdList);
		return ((ScmInvPurInWarehsBillDAO)dao).selectByPurOrder(map);
	}

	@Override
	public BigDecimal getTotalPurInWarehsQty(
			ScmInvPurInWarehsBillAdvQuery scmInvPurInWarehsBillAdvQuery,
			Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("begDate", FormatUtils.fmtDate(scmInvPurInWarehsBillAdvQuery.getBizDateFrom()));
		map.put("endDate", FormatUtils.fmtDate(scmInvPurInWarehsBillAdvQuery.getBizDateTo()));
		map.put("itemId", scmInvPurInWarehsBillAdvQuery.getItemId());
		map.put("purOrgUnitNo", scmInvPurInWarehsBillAdvQuery.getPurOrgUnitNo());
		return ((ScmInvPurInWarehsBillDAO)dao).getTotalPurInWarehsQty(map);
	}

	@Override
	public ScmInvPurInWarehsBill2 selectPoNoAndPvNoById(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId", wrId);
			return ((ScmInvPurInWarehsBillDAO)dao).selectPoNoAndPvNoById(map);
		}
		return null;
	}

	@Override
	public void updatePushed(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param) throws AppException {
		if(scmInvPurInWarehsBill != null && scmInvPurInWarehsBill.getWrId() > 0){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId", scmInvPurInWarehsBill.getWrId());
			map.put("pushed", (scmInvPurInWarehsBill.isPushed() ? 1 : 0));
			((ScmInvPurInWarehsBillDAO)dao).updatePushed(map);
		}
	}

	@Override
	public void updateConfirmStatus(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param) throws AppException {
		if(scmInvPurInWarehsBill != null && StringUtils.isNotBlank(scmInvPurInWarehsBill.getWrNo())
				&& StringUtils.isNotBlank(scmInvPurInWarehsBill.getConfirmStatus())){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrNo", scmInvPurInWarehsBill.getWrNo());
			map.put("controlUnitNo", param.getControlUnitNo());
			map.put("confirmStatus", scmInvPurInWarehsBill.getConfirmStatus());
			((ScmInvPurInWarehsBillDAO)dao).updateConfirmStatus(map);
		}
	}

	@Override
	public void updateVersion(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param) throws AppException {
		if(scmInvPurInWarehsBill != null && scmInvPurInWarehsBill.getWrId() > 0){
			ScmInvPurInWarehsBill2 scmInvPurInWarehsBill2 = this.selectDirect(scmInvPurInWarehsBill.getWrId(), param);
			if(scmInvPurInWarehsBill2 != null){
				HashMap<String,Object> map = new HashMap<String,Object>();
				map.put("wrId", scmInvPurInWarehsBill2.getWrId());
				map.put("version", scmInvPurInWarehsBill2.getVersion());
				((ScmInvPurInWarehsBillDAO)dao).updateVersion(map);
			}
		}
	}
	
	@Override
	public List<Map<String,Object>> countUnPostBill(HashMap<String, Object> map) {
		return ((ScmInvPurInWarehsBillDAO)dao).countUnPostBill(map);
	}

	@Override
	public List<Map<String, Object>> countCostUnPostBill(HashMap<String, Object> map) {
		return ((ScmInvPurInWarehsBillDAO)dao).countCostUnPostBill(map);
	}

	@Override
	public ScmInvPurInWarehsBill2 selectByWrNo(String wrNo, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("controlUnitNo", param.getControlUnitNo());
		map.put("wrNo", wrNo);
		return ((ScmInvPurInWarehsBillDAO)dao).selectByWrNo(map);
	}

	@Override
	protected void afterUpdate(ScmInvPurInWarehsBill2 oldEntity, ScmInvPurInWarehsBill2 newEntity, Param param)
			throws AppException {
		if(oldEntity!=null && newEntity!=null && oldEntity.getVendorId()!=newEntity.getVendorId()) {
			ScmSupplierRegInvitation oldscmSupplierRegInvitation = scmSupplierRegInvitationBiz.selectByVendorIdAndCtrl(oldEntity.getVendorId(),param.getControlUnitNo(),param);
			if(oldscmSupplierRegInvitation!=null && oldscmSupplierRegInvitation.getPlatSupplierId()>0) {
				ScmSupplierRegInvitation scmSupplierRegInvitation = scmSupplierRegInvitationBiz.selectByVendorIdAndCtrl(newEntity.getVendorId(),param.getControlUnitNo(),param);
				if(scmSupplierRegInvitation==null || scmSupplierRegInvitation.getPlatSupplierId()==0) {
					SupplierPlatUtil supplierPlatUtil = new SupplierPlatUtil();
					supplierPlatUtil.changeSyncData(oldEntity, param);
				}
			}
		}
	}

	@Override
	public List<ScmInvPurInWarehsBill2> querySupSupplyOfMaterialDetails(SupSupplyOfMaterialDetailsParams supplyOfMaterialDetailsParams,
			Param param) throws AppException {
		if(supplyOfMaterialDetailsParams != null) {
			int summaryLevel = supplyOfMaterialDetailsParams.getSummaryLevel();
			HashMap<String,Object> map = new HashMap<>();
			StringBuffer orgStringBuffer = new StringBuffer("");
			OrgCompany2 selectByOrgUnitNo = orgCompanyBiz.selectByOrgUnitNo(supplyOfMaterialDetailsParams.getInvOrgUnitNo(), param);
			if(selectByOrgUnitNo == null) {
				throw new AppException("iscm.scmmaterial.queryInvOrgMaterialList.error.finOrgUnitNonotexists");
			}
			List<OrgStorage2> orgStorageList = orgUnitRelationBiz.findFromOrgUnitByType(OrgUnitRelationType.INVTOFIN, supplyOfMaterialDetailsParams.getInvOrgUnitNo(), false, null, param);
//			List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(supplyOfMaterialDetailsParams.getInvOrgUnitNo(), param);
			if(orgStorageList!=null && !orgStorageList.isEmpty()) {
				for(OrgStorage2 orgStorage:orgStorageList) {
					if(StringUtils.isNotBlank(orgStringBuffer.toString()))
						orgStringBuffer.append(",");
					orgStringBuffer.append("'").append(orgStorage.getOrgUnitNo()).append("'");
				}
			}
			if(StringUtils.isNotBlank(orgStringBuffer.toString())) {
				map.put("invOrgUnitNo", orgStringBuffer.toString());
			}else {
				throw new AppException("iscm.scmmaterial.queryInvOrgMaterialList.error.invOrgUnitNonotexists");
			}
			StringBuffer wrno = new StringBuffer();
			if (StringUtils.isNotBlank(supplyOfMaterialDetailsParams.getWrNo())) {
				String[] split = supplyOfMaterialDetailsParams.getWrNo().split(",");
				for (String string : split) {
					if (StringUtils.isNotBlank(wrno.toString())) {
						wrno.append(",");
					}
					wrno.append("'").append(string).append("'");
				}
			}
			map.put("wrNo", wrno.toString());
			map.put("bizType", supplyOfMaterialDetailsParams.getBizType());
			map.put("begDate", supplyOfMaterialDetailsParams.getBegDate());
			map.put("endDate", supplyOfMaterialDetailsParams.getEndDate());
			map.put("controlUnitNo", param.getControlUnitNo());
			List<ScmInvPurInWarehsBill2> querySupSupplyOfMaterialDetails = ((ScmInvPurInWarehsBillDAO)dao).querySupSupplyOfMaterialDetails(map,param);
			for (ScmInvPurInWarehsBill2 scmInvPurInWarehsBill : querySupSupplyOfMaterialDetails) {
        		long groupId = scmInvPurInWarehsBill.getGroupId();
        		if(groupId>0) {
        			String longNoList[] = StringUtils.split(scmInvPurInWarehsBill.getLongNo(),",");
        			if(summaryLevel>0) {
	        			if(longNoList.length>=summaryLevel) {
	        				groupId = Long.valueOf(longNoList[summaryLevel-1]);
	        			}else {
	        				groupId = Long.valueOf(longNoList[longNoList.length-2]);
	        			}
        			}
        			ScmMaterialGroup scmMaterialGroup = scmMaterialGroupBiz.selectDirect(groupId, param);
        			if(scmMaterialGroup!=null) {
        				scmInvPurInWarehsBill.setGroupId(groupId);
        				scmInvPurInWarehsBill.setClassCode(scmMaterialGroup.getClassCode());
        				scmInvPurInWarehsBill.setMaterialClassName(scmMaterialGroup.getClassName());
        			}
        		}
				if (scmInvPurInWarehsBill.getTaxRateStr() != null){
					//税率
					PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmInvPurInWarehsBill.getTaxRateStr(), null, param);
					if (pubSysBasicInfo != null) {
						scmInvPurInWarehsBill.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
					}
				}
				if (scmInvPurInWarehsBill.getVendorId() > 0){
					//供应商
					Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmInvPurInWarehsBill.getVendorId(), param);
					if (scmsupplier != null) {
						scmInvPurInWarehsBill.setConvertMap(ScmInvPurInWarehsBill2.FN_VENDORID, scmsupplier);
						scmInvPurInWarehsBill.setVendorNo(scmsupplier.getVendorNo());
						scmInvPurInWarehsBill.setVendorName(scmsupplier.getVendorName());
					}
				}
				if (StringUtils.isNotBlank(scmInvPurInWarehsBill.getCreator())){
					//制单人
					Usr usr = usrBiz.selectByCode(scmInvPurInWarehsBill.getCreator(), param);
					if (usr != null) {
						scmInvPurInWarehsBill.setConvertMap(ScmInvPurInWarehsBill2.FN_CREATOR, usr);
						scmInvPurInWarehsBill.setCreatorName(usr.getName());
					}
				}
				if(StringUtils.isNotBlank(scmInvPurInWarehsBill.getUseOrgUnitNos())){
					String[] useOrgUnitNos = StringUtils.split(scmInvPurInWarehsBill.getUseOrgUnitNos(), ",");
					StringBuffer useOrgUnitName = new StringBuffer("");
					for(String useOrgUnitNO : useOrgUnitNos) {
						OrgAdmin2 orgAdmin = orgAdminBiz.selectByOrgUnitNo(useOrgUnitNO, param);
						if(orgAdmin != null) {
							if(StringUtils.isNotBlank(useOrgUnitName.toString())) 
								useOrgUnitName.append(",");
							useOrgUnitName.append(orgAdmin.getOrgUnitName());
						}
					}
					scmInvPurInWarehsBill.setUseOrgUnitName(useOrgUnitName.toString());
				}
				
				if(StringUtils.isNotBlank(scmInvPurInWarehsBill.getWareHouseIds())){
					String[] wareHouseIds = StringUtils.split(scmInvPurInWarehsBill.getWareHouseIds(), ",");
					StringBuffer wareHouseName = new StringBuffer("");
					for(String wareHouseId : wareHouseIds) {
						ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(Integer.parseInt(wareHouseId), param);
						if(scmInvWareHouse != null) {
							if(StringUtils.isNotBlank(wareHouseName.toString())) 
								wareHouseName.append(",");
							wareHouseName.append(scmInvWareHouse.getWhName());
						}
					}
					scmInvPurInWarehsBill.setWareHouseName(wareHouseName.toString());
				}
				if(StringUtils.isNotBlank(scmInvPurInWarehsBill.getBizType())){
					Code code = codeBiz.selectByCategoryAndCode("wareHouseBizType", scmInvPurInWarehsBill.getBizType());
					if(code!=null)
						scmInvPurInWarehsBill.setBizTypeName(code.getName());
				}
			}
			return querySupSupplyOfMaterialDetails;
		}
		return null;
	}

	@Override
	public List getTotalPurInWarehsQtyByItems(ScmInvPurInWarehsBillAdvQuery scmInvPurInWarehsBillAdvQuery, Param createParam)
			throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("begDate", FormatUtils.fmtDate(scmInvPurInWarehsBillAdvQuery.getBizDateFrom()));
		map.put("endDate", FormatUtils.fmtDate(scmInvPurInWarehsBillAdvQuery.getBizDateTo()));
		map.put("itemId", scmInvPurInWarehsBillAdvQuery.getItemId());
		map.put("purOrgUnitNo", scmInvPurInWarehsBillAdvQuery.getPurOrgUnitNo());
		return ((ScmInvPurInWarehsBillDAO)dao).getTotalPurInWarehsQtyByItems(map);
	}
	
	@Override
	public List<ScmPurBillDrillResult> selectDrillBills(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param)
			throws AppException {
		if(scmInvPurInWarehsBill != null) {
			List<String> itemList=new ArrayList<String>();
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("wrId", scmInvPurInWarehsBill.getWrId());
			List<ScmPurBillDrillResult> scmPurBillDrillResultList=((ScmInvPurInWarehsBillDAO)dao).selectDrillBills(map);
			for (int item = 0; item < scmPurBillDrillResultList.size(); item++) {
				System.out.println(scmPurBillDrillResultList.get(item).getWrDetailId());
				itemList.add(scmPurBillDrillResultList.get(item).getWrDetailId());
			}
			List<ScmPurBillDrillResult> scmPurBillDrillResultIAPSList=apinvoiceBiz.selectDrillBillsAPI(itemList);
			for (int iscm = 0; iscm < scmPurBillDrillResultList.size(); iscm++) {
				for (int iaps = 0; iaps < scmPurBillDrillResultIAPSList.size(); iaps++) {
					if (scmPurBillDrillResultList.get(iscm).getWrDetailId().equals(scmPurBillDrillResultIAPSList.get(iaps).getApiSourceDtlId())) {
						scmPurBillDrillResultList.get(iscm).setApiIds(scmPurBillDrillResultIAPSList.get(iaps).getApiIds());
						scmPurBillDrillResultList.get(iscm).setApiNo(scmPurBillDrillResultIAPSList.get(iaps).getApiNo());
						scmPurBillDrillResultList.get(iscm).setApiStatus(scmPurBillDrillResultIAPSList.get(iaps).getApiStatus());
						scmPurBillDrillResultList.get(iscm).setApPIds(scmPurBillDrillResultIAPSList.get(iaps).getApPIds());
						scmPurBillDrillResultList.get(iscm).setApPNo(scmPurBillDrillResultIAPSList.get(iaps).getApPNo());
						scmPurBillDrillResultList.get(iscm).setApPStatus(scmPurBillDrillResultIAPSList.get(iaps).getApPStatus());
					}
				}
			}
			return scmPurBillDrillResultList;
		}
		return null;
	}
	@Override
	public List<ScmPurBillDrillResult> selectDrillBillsISCM(List<String> list) throws AppException {
		if (!list.isEmpty()) {
			List<ScmPurBillDrillResult> list2 = ((ScmInvPurInWarehsBillDAO)dao).selectDrillBillsISCM(list);
			return ((ScmInvPurInWarehsBillDAO)dao).selectDrillBillsISCM(list);
		}
		return null;
	}

	@Override
	public BigDecimal getInvPrice(String orgUnitNo, String itemNo, Param param) throws AppException {
		return scmInvPurInWarehsBillEntryBiz.getInvPrice(orgUnitNo, itemNo, param);
	}

	@Override
	public List<ScmInvPurInWarehsBillEntry2> getInvPriceList(String invOrgUnitNo, String itemIds, Param param)throws AppException {
		return scmInvPurInWarehsBillEntryBiz.getInvPriceList(invOrgUnitNo, itemIds, param);
	}

	@Override
	public ScmInvPurInWarehsBill2 updatePrtCount(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param)
			throws AppException {
		if(scmInvPurInWarehsBill.getWrId()>0){
			ScmInvPurInWarehsBill2 bill = this.selectDirect(scmInvPurInWarehsBill.getWrId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmInvPurInWarehsBill;
	}
}

