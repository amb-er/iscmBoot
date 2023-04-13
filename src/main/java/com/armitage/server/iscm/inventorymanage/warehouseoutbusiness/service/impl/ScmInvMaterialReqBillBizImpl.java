package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.armitage.server.api.business.datasync.params.InvMaterialReqDetailSParams;
import com.armitage.server.api.business.datasync.params.InvMaterialReqListSParams;
import com.armitage.server.api.business.datasync.result.DataSyncResult;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillAddParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillDeptParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillDetailParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillEditParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillPersonParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillWareHouseParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvReqBillLotListParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvReqBillMaterialListParams;
import com.armitage.server.api.business.invsaleorder.params.InvSaleOrderParams;
import com.armitage.server.activity.util.ActivityUtil;
import com.armitage.server.activity.util.AuditMsgUtil;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.OperationParam;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.Param.ParamType;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.base.model.SimpleDataDisplay;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.ListSortUtil;
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialInventory2;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialInventoryBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.common.model.CommonAuditOpinion;
import com.armitage.server.iscm.common.model.CommonEventHistory;
import com.armitage.server.iscm.common.service.CommonEventHistoryBiz;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvBalBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.util.ScmWarehouseUtil;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMaterialReqBillDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialDrillResult;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialReqBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialReqBillEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.model.SystemState;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgCompanyBiz;
import com.armitage.server.system.service.OrgCostCenterBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.service.SystemStateBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.model.Usr2;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmInvMaterialReqBillBiz")
public class ScmInvMaterialReqBillBizImpl extends BaseBizImpl<ScmInvMaterialReqBill2> implements ScmInvMaterialReqBillBiz {
	private UsrBiz usrBiz;
	private OrgUnitBiz orgUnitBiz;
	private OrgAdminBiz orgAdminBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private ScmInvMaterialReqBillEntryBiz scmInvMaterialReqBillEntryBiz;
	private OrgCostCenterBiz orgCostCenterBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private ScmInvStockBiz scmInvStockBiz;
	private ScmInvBalBiz scmInvBalBiz;
	private PeriodCalendarBiz periodCalendarBiz;
	private SysParamBiz sysParamBiz;
	private SystemStateBiz systemStateBiz;
	private BillTypeBiz billTypeBiz;
	private OrgCompanyBiz orgCompanyBiz;
	private CodeBiz codeBiz;
	private ScmBillPendingBiz scmBillPendingBiz;
	private CommonEventHistoryBiz commonEventHistoryBiz;
	private OrgStorageBiz orgStorageBiz;
	private ScmMaterialInventoryBiz scmMaterialInventoryBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	
	protected  HashMap<String,Long> scmMatrialMap = new HashMap<String,Long>();
	protected  HashMap<Long,Long> scmUnitMap = new HashMap<Long,Long>();
	
	
	
	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}

	public void setOrgCompanyBiz(OrgCompanyBiz orgCompanyBiz) {
		this.orgCompanyBiz = orgCompanyBiz;
	}

	public void setSystemStateBiz(SystemStateBiz systemStateBiz) {
        this.systemStateBiz = systemStateBiz;
    }

    public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	public void setScmInvBalBiz(ScmInvBalBiz scmInvBalBiz) {
		this.scmInvBalBiz = scmInvBalBiz;
	}

	public void setScmInvStockBiz(ScmInvStockBiz scmInvStockBiz) {
		this.scmInvStockBiz = scmInvStockBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setOrgCostCenterBiz(OrgCostCenterBiz orgCostCenterBiz) {
		this.orgCostCenterBiz = orgCostCenterBiz;
	}

	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}
	
	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public void setScmInvMaterialReqBillEntryBiz(ScmInvMaterialReqBillEntryBiz scmInvMaterialReqBillEntryBiz) {
		this.scmInvMaterialReqBillEntryBiz = scmInvMaterialReqBillEntryBiz;
	}
	public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}
	
	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
        this.billTypeBiz = billTypeBiz;
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
	
	public void setScmMaterialInventoryBiz(ScmMaterialInventoryBiz scmMaterialInventoryBiz) {
		this.scmMaterialInventoryBiz = scmMaterialInventoryBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." + ScmInvMaterialReqBill2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." + ScmInvMaterialReqBill2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." + ScmInvMaterialReqBill2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." + ScmInvMaterialReqBill2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}
	@Override
	protected void afterSelect(ScmInvMaterialReqBill2 entity, Param param) throws AppException {
		if (entity != null){
			this.setConvertMap(entity, param);
		}
	}
	
	private void setConvertMap(ScmInvMaterialReqBill2 scmInvMaterialReqBill,Param param){
		HashMap<String,Object> cacheMap = new HashMap<String,Object>();
		if (StringUtils.isNotBlank(scmInvMaterialReqBill.getUseOrgUnitNo())){
			//领料部门
			OrgAdmin2 orgAdmin = (OrgAdmin2) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgAdmin2.class)+"_"+scmInvMaterialReqBill.getUseOrgUnitNo());
			if(orgAdmin==null){
				orgAdmin = orgAdminBiz.selectByOrgUnitNo(scmInvMaterialReqBill.getUseOrgUnitNo(), param);
				cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgAdmin2.class)+"_"+scmInvMaterialReqBill.getUseOrgUnitNo(),orgAdmin);
			}
			if (orgAdmin != null) {
				scmInvMaterialReqBill.setConvertMap(ScmInvMaterialReqBill2.FN_USEORGUNITNO, orgAdmin);
				scmInvMaterialReqBill.setUseOrgUnitName(orgAdmin.getOrgUnitName());
			}
		}
		if (StringUtils.isNotBlank(scmInvMaterialReqBill.getUsePerson())){
			//领料人
			Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvMaterialReqBill.getUsePerson());
			if(usr==null){
				usr = usrBiz.selectByCode(scmInvMaterialReqBill.getUsePerson(), param);
				cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvMaterialReqBill.getUsePerson(),usr);
			}
			if (usr != null) {
				if(scmInvMaterialReqBill.getDataDisplayMap()==null){
					scmInvMaterialReqBill.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
				}
				scmInvMaterialReqBill.getDataDisplayMap().put(ScmInvMaterialReqBill2.FN_USEPERSON, SimpleDataDisplayUtil.convert(usr));
				scmInvMaterialReqBill.setRequestPersonName(usr.getName());
				scmInvMaterialReqBill.setConvertMap(ScmInvMaterialReqBill2.FN_USEPERSON,usr);
			}
		}
		if (scmInvMaterialReqBill.getWareHouseId() > 0){
			//存货仓库
			ScmInvWareHouse scmInvWareHouse = (ScmInvWareHouse) cacheMap.get(ClassUtils.getFinalModelSimpleName(ScmInvWareHouse.class)+"_"+scmInvMaterialReqBill.getWareHouseId());
			if(scmInvWareHouse==null){
				scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvMaterialReqBill.getWareHouseId(), param);
				cacheMap.put(ClassUtils.getFinalModelSimpleName(ScmInvWareHouse.class)+"_"+scmInvMaterialReqBill.getWareHouseId(),scmInvWareHouse);
			}
			if (scmInvWareHouse != null) {
				scmInvMaterialReqBill.setConvertMap(ScmInvMaterialReqBill2.FN_WAREHOUSEID, scmInvWareHouse);
				scmInvMaterialReqBill.setWareHouseCode(scmInvWareHouse.getWhNo());
				scmInvMaterialReqBill.setWareHouseName(scmInvWareHouse.getWhName());
			}
		}
		if (StringUtils.isNotBlank(scmInvMaterialReqBill.getOrgUnitNo())){
			//库存组织
			OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvMaterialReqBill.getOrgUnitNo());
			if(orgBaseUnit==null){
				orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMaterialReqBill.getOrgUnitNo(), param);
				cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvMaterialReqBill.getOrgUnitNo(),orgBaseUnit);
			}
			if (orgBaseUnit != null) {
				scmInvMaterialReqBill.setConvertMap(ScmInvMaterialReqBill2.FN_ORGUNITNO, orgBaseUnit);
				scmInvMaterialReqBill.setOrgUnitName(orgBaseUnit.getOrgUnitName());
			}
		}

		if (StringUtils.isNotBlank(scmInvMaterialReqBill.getCreator())){
			//制单人  
			Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvMaterialReqBill.getCreator());
			if(usr==null){
				usr = usrBiz.selectByCode(scmInvMaterialReqBill.getCreator(), param);
				cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvMaterialReqBill.getCreator(),usr);
			}
			if (usr != null) {
				if(scmInvMaterialReqBill.getDataDisplayMap()==null){
					scmInvMaterialReqBill.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
				}
				scmInvMaterialReqBill.getDataDisplayMap().put(ScmInvMaterialReqBill2.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
				scmInvMaterialReqBill.setCreatorName(usr.getName());
				scmInvMaterialReqBill.setConvertMap(ScmInvMaterialReqBill2.FN_CREATOR,usr);
			}
		}
		if (StringUtils.isNotBlank(scmInvMaterialReqBill.getEditor())) {
			// 修改人
			Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+ "_" + scmInvMaterialReqBill.getEditor());
			if (usr == null) {
				usr = usrBiz.selectByCode(scmInvMaterialReqBill.getEditor(), param);
				cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+ "_" + scmInvMaterialReqBill.getEditor(), usr);
			}
			if (usr != null) {
				scmInvMaterialReqBill.setConvertMap(ScmInvMaterialReqBill2.FN_EDITOR, usr);
				scmInvMaterialReqBill.setEditorName(usr.getName());
				scmInvMaterialReqBill.setConvertMap(ScmInvMaterialReqBill2.FN_EDITOR,usr);
			}
		}
		if (StringUtils.isNotBlank(scmInvMaterialReqBill.getChecker())) {
			// 审核人
			Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+ "_" + scmInvMaterialReqBill.getChecker());
			if (usr == null) {
				usr = usrBiz.selectByCode(scmInvMaterialReqBill.getChecker(), param);
				cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class) + "_" + scmInvMaterialReqBill.getChecker(), usr);
			}
			if (usr != null) {
				scmInvMaterialReqBill.setConvertMap(ScmInvMaterialReqBill2.FN_CHECKER, usr);
				scmInvMaterialReqBill.setCheckerName(usr.getName());
				scmInvMaterialReqBill.setConvertMap(ScmInvMaterialReqBill2.FN_CHECKER,usr);
			}
		}
		if(StringUtils.isNotBlank(scmInvMaterialReqBill.getStatus())){
			Code code = codeBiz.selectByCategoryAndCode("warehouseStatus", scmInvMaterialReqBill.getStatus());
			if(code!=null)
				scmInvMaterialReqBill.setStatusName(code.getName());
		}
		if(StringUtils.isNotBlank(scmInvMaterialReqBill.getBizType())){
			Code code = codeBiz.selectByCategoryAndCode("collectWHBizType", scmInvMaterialReqBill.getBizType());
			if(code!=null)
				scmInvMaterialReqBill.setBizTypeName(code.getName());
		}

		if (StringUtils.isNotBlank(scmInvMaterialReqBill.getCostOrgUnitNo())){
			//成本计算方式
			OrgCostCenter2 orgCostCenter = (OrgCostCenter2) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgCostCenter2.class)+"_"+scmInvMaterialReqBill.getCostOrgUnitNo());
			if(orgCostCenter==null){
				orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmInvMaterialReqBill.getCostOrgUnitNo(), param);
				cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgCostCenter2.class)+"_"+scmInvMaterialReqBill.getCostOrgUnitNo(),orgCostCenter);
			}
			if (orgCostCenter != null) {
				scmInvMaterialReqBill.setCostCenterType(orgCostCenter.getCostCenterType());
			}
		}

		if (StringUtils.isNotBlank(scmInvMaterialReqBill.getBillType())){
            //来源单类型
            BillType2 billType = billTypeBiz.selectByOrgAndCode(scmInvMaterialReqBill.getFinOrgUnitNo(), scmInvMaterialReqBill.getBillType(), param);
            if (billType != null) {
                scmInvMaterialReqBill.setConvertMap(ScmInvSaleOrder.FN_BILLTYPE, billType);
            }
        }
		
		if(scmInvMaterialReqBill.getAmt() != null) {
			scmInvMaterialReqBill.setTaxAmount(scmInvMaterialReqBill.getTaxAmt().subtract(scmInvMaterialReqBill.getAmt()));
		} else {
			scmInvMaterialReqBill.setTaxAmount(BigDecimal.ZERO);
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param)	throws AppException {
		if(list != null && !list.isEmpty()){
			for(ScmInvMaterialReqBill2 scmInvMaterialReqBill : (List<ScmInvMaterialReqBill2>)list){
				//视图增加待审人
				ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmInvMaterialReqBill.getOtId(), "InvMatReqout",param);
				StringBuffer usrName = new StringBuffer("");
				if (scmBillPendingUsr != null) {
					scmInvMaterialReqBill.setPendingUsr(scmBillPendingUsr.getUsrCodes());
					String[] usrCodes = StringUtils.split(scmBillPendingUsr.getUsrCodes(), ",");
					for(String usrCode : usrCodes) {
						Usr usr = usrBiz.selectByCode(usrCode, param);
						if(usr != null) {
							if(StringUtils.isNotBlank(usrName.toString())) 
								usrName.append(",");
							usrName.append(usr.getName());
						}
					}
				}
				scmInvMaterialReqBill.setPendingUsrName(usrName.toString());
				
				scmInvMaterialReqBill.setNetAmt(scmInvMaterialReqBill.getAmt());
				this.setConvertMap(scmInvMaterialReqBill, param);
				if("I,R".contains(scmInvMaterialReqBill.getStatus())) {
					scmInvMaterialReqBill.setPendingUsrName("");
				}
			}
		}
	}
	
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmInvMaterialReqBill2 scmInvMaterialReqBill = (ScmInvMaterialReqBill2) bean.getList().get(0);
		if(scmInvMaterialReqBill != null && scmInvMaterialReqBill.getOtId() > 0){
			List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.selectByOtId(scmInvMaterialReqBill.getOtId(), param);
			if(scmInvMaterialReqBillEntryList != null && !scmInvMaterialReqBillEntryList.isEmpty()){
				bean.setList2(scmInvMaterialReqBillEntryList);
			}
		}
	}

	@Override
	protected void beforeAdd(ScmInvMaterialReqBill2 entity, Param param) throws AppException {
		if(entity != null){
			String code = CodeAutoCalUtil.getBillCode("InvMatReqout", entity, param);
			entity.setOtNo(code);
			Page page = new Page();
			page.setModelClass(OrgCompany2.class);
			page.setShowCount(Integer.MAX_VALUE);
			List<String> arglist = new ArrayList<>();
			arglist.add("type=to");
			arglist.add("relationType="+OrgUnitRelationType.INVTOFIN);
			arglist.add("fromOrgUnitNo="+entity.getOrgUnitNo());
			List<OrgCompany2> orgCompanyList = orgCompanyBiz.queryPage(page, arglist, "queryPageEx", param);
			if(orgCompanyList==null || orgCompanyList.isEmpty())
				throw new AppException("iscm.inventorymanage.common.invOrgUnit.notfinorg");
			entity.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
			entity.setCurrencyNo(orgCompanyList.get(0).getBaseCurrency());
			//获取期间
			PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(entity.getBizDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
			}
			entity.setPeriodId(periodCalendar.getPeriodId());
			entity.setAccountYear(periodCalendar.getAccountYear());
			entity.setAccountPeriod(periodCalendar.getAccountPeriod());
			
//			BillType2 billType = billTypeBiz.selectByOrgAndCode(entity.getFinOrgUnitNo(), "InvMatReqout", param);
//			if(billType!=null && billType.isNeedAudit()) {
//				if(StringUtils.isBlank(billType.getWorkFlowNo())) {
//					throw new AppException(Message.getMessage(param.getLang(), "iscm.workflow.needaudit.notprocess", new String[] {Message.getMessage(param.getLang(),"field.ScmInvMaterialReqBill")}));
//				}
//				entity.setWorkFlowNo(billType.getWorkFlowNo());
//			}
		}
	}
	
	@Override
	protected void beforeUpdate(ScmInvMaterialReqBill2 oldEntity,ScmInvMaterialReqBill2 newEntity, Param param) throws AppException {
		//获取期间
		PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(newEntity.getBizDate(), param);
		if(periodCalendar==null){
			throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
		}
		newEntity.setPeriodId(periodCalendar.getPeriodId());
		newEntity.setAccountYear(periodCalendar.getAccountYear());
		newEntity.setAccountPeriod(periodCalendar.getAccountPeriod());
		
//		BillType2 billType = billTypeBiz.selectByOrgAndCode(newEntity.getFinOrgUnitNo(), "InvMatReqout", param);
//		if(billType!=null && billType.isNeedAudit()) {
//			if(StringUtils.isBlank(billType.getWorkFlowNo())) {
//				throw new AppException(Message.getMessage(param.getLang(), "iscm.workflow.needaudit.notprocess", new String[] {Message.getMessage(param.getLang(),"field.ScmInvMaterialReqBill")}));
//			}
//			newEntity.setWorkFlowNo(billType.getWorkFlowNo());
//		}
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmInvMaterialReqBill2 scmInvMaterialReqBill = (ScmInvMaterialReqBill2) bean.getList().get(0);
		scmInvMaterialReqBill.setAmt(BigDecimal.ZERO);
		scmInvMaterialReqBill.setTaxAmt(BigDecimal.ZERO);
		if(scmInvMaterialReqBill != null && scmInvMaterialReqBill.getOtId() > 0){
			//新增出库明细
			List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = bean.getList2();
			if(scmInvMaterialReqBillEntryList != null && !scmInvMaterialReqBillEntryList.isEmpty()){
                int lineId = 1;
				for(ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryList){
					scmInvMaterialReqBill.setAmt(scmInvMaterialReqBillEntry.getAmt().add(scmInvMaterialReqBill.getAmt()));
					scmInvMaterialReqBill.setTaxAmt(scmInvMaterialReqBillEntry.getTaxAmt().add(scmInvMaterialReqBill.getTaxAmt()));
					scmInvMaterialReqBillEntry.setLineId(lineId);
					scmInvMaterialReqBillEntry.setOtId(scmInvMaterialReqBill.getOtId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvMaterialReqBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvMaterialReqBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvMaterialReqBillEntry.getItemId(), scmInvMaterialReqBillEntry.getUnit(), param);//库存单位转换系数
					scmInvMaterialReqBillEntry.setBaseQty(scmInvMaterialReqBillEntry.getQty().multiply(invConvRate));
					scmInvMaterialReqBillEntryBiz.add(scmInvMaterialReqBillEntry, param);
                    lineId = lineId+1;
				}
				scmInvMaterialReqBill.setTaxAmount(scmInvMaterialReqBill.getTaxAmt().subtract(scmInvMaterialReqBill.getAmt()));
			}
		}
	}
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvMaterialReqBill2 scmInvMaterialReqBill = (ScmInvMaterialReqBill2) bean.getList().get(0);
		if(scmInvMaterialReqBill != null && scmInvMaterialReqBill.getOtId() > 0){
			//更新出库明细
			List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = bean.getList2();
			if(scmInvMaterialReqBillEntryList != null && !scmInvMaterialReqBillEntryList.isEmpty()){
                int lineId = 1;
				for(ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryList){
					if (StringUtils.equals("I", scmInvMaterialReqBill.getStatus())) {
						scmInvMaterialReqBillEntry.setLineId(lineId);
					}
					scmInvMaterialReqBillEntry.setOtId(scmInvMaterialReqBill.getOtId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvMaterialReqBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvMaterialReqBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvMaterialReqBillEntry.getItemId(), scmInvMaterialReqBillEntry.getUnit(), param);//库存单位转换系数
					scmInvMaterialReqBillEntry.setBaseQty(scmInvMaterialReqBillEntry.getQty().multiply(invConvRate));
					scmInvMaterialReqBillEntry.setOtId(scmInvMaterialReqBill.getOtId());
                    lineId = lineId+1;
                    
                    if (scmInvMaterialReqBillEntry.getQty().compareTo(BigDecimal.ZERO) > 0 
                    		&& scmInvMaterialReqBillEntry.getPrice().compareTo(BigDecimal.ZERO) > 0 
                    		&& scmInvMaterialReqBillEntry.getAmt().compareTo(BigDecimal.ZERO) == 0) {
	                    int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
	                    int qtyPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_QtyPrecision", "2", param));
	                    BigDecimal b = (scmInvMaterialReqBillEntry.getQty()).multiply(scmInvMaterialReqBillEntry.getPrice()).setScale(amtPrecision, RoundingMode.HALF_UP);
	                    scmInvMaterialReqBillEntry.setAmt(b);
	                    BigDecimal c = (scmInvMaterialReqBillEntry.getQty()).multiply(scmInvMaterialReqBillEntry.getConvrate()).setScale(qtyPrecision, RoundingMode.HALF_UP);
	                    scmInvMaterialReqBillEntry.setBaseQty(c);
	                    BigDecimal d = (scmInvMaterialReqBillEntry.getQty()).multiply(scmInvMaterialReqBillEntry.getTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP);
	                    scmInvMaterialReqBillEntry.setTaxAmt(d);
                    }
				}
				scmInvMaterialReqBillEntryBiz.update(scmInvMaterialReqBill, scmInvMaterialReqBillEntryList, ScmInvMaterialReqBillEntry2.FN_OTID, param);
			}
		}
	}
	
	@Override
	protected void afterDelete(ScmInvMaterialReqBill2 entity, Param param) throws AppException {
		if(entity != null && entity.getOtId() > 0){
			//删除出库明细
            List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.selectByOtId(entity.getOtId(), param);
            if(scmInvMaterialReqBillEntryList!=null && !scmInvMaterialReqBillEntryList.isEmpty())
            		scmInvMaterialReqBillEntryBiz.delete(scmInvMaterialReqBillEntryList, param);
		}
	}
	
	public ScmInvMaterialReqBill2 updateStatus(ScmInvMaterialReqBill2 scmInvMaterialReqBill,Param param) throws AppException {
		if(scmInvMaterialReqBill != null){
			return this.updateDirect(scmInvMaterialReqBill, param);
		}
		return null;
	}
	
	@Override
	public List<String> postBillCheck(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param)
			throws AppException {
		List<String> msgList = new ArrayList<String>();// 提示列表
		scmInvMaterialReqBill = this.selectDirect(scmInvMaterialReqBill.getOtId(), param);
		if(scmInvMaterialReqBill != null){
			if(!StringUtils.equals("A",scmInvMaterialReqBill.getStatus())) {
				msgList.add(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvMaterialReqBill.getOtNo()}));
				return msgList;
			}
		    SystemState systemState = systemStateBiz.selectBySystemId(scmInvMaterialReqBill.getFinOrgUnitNo(), 170, param);
	        if(systemState==null){
	            msgList.add(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
	            return msgList;
	        }
	        if (systemState.getCurrentPeriodId() != scmInvMaterialReqBill.getPeriodId()) {
	            msgList.add(Message.getMessage(param.getLang(), 
	                    "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmInvMaterialReqBill.getOtNo()}));
	            return msgList;
	        }
	        // 检查盘点物资冻结
	        // 检查冻结仓库
	        List<ScmInvMaterialReqBill2> scmInvMaterialReqBillList = ((ScmInvMaterialReqBillDAO) dao).checkWareHouseFree(scmInvMaterialReqBill.getOtId());
	        if (scmInvMaterialReqBillList != null && !scmInvMaterialReqBillList.isEmpty()) {
	        	msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
                return msgList;
                
//	        	for (ScmInvMaterialReqBill2 scmInvMaterialReqBill2 : scmInvMaterialReqBillList) {
//		            // 检查冻结物资
//		            HashMap<String,Object> map = new HashMap<String,Object>();
//	                map.put("taskId", scmInvMaterialReqBill2.getTaskId());
//	                map.put("otId", scmInvMaterialReqBill.getOtId());
//		            int count = ((ScmInvMaterialReqBillDAO) dao).checkMaterialFree(map);
//		            if (count > 0) {
//		                msgList.add(Message.getMessage(param.getLang(), 
//		                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
//		                return msgList;
//		            }
//	        	}
	        }
	        // 检查盘存物资冻结
	        List<ScmInvMaterialReqBill2> scmInvMaterialReqBillList2 = ((ScmInvMaterialReqBillDAO) dao).inventoryWareHouseFree(scmInvMaterialReqBill.getOtId());
	        // 检查冻结仓库
            if (scmInvMaterialReqBillList2 != null && !scmInvMaterialReqBillList2.isEmpty()) {
            	msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingCostcenter"));
                return msgList;
//            	for (ScmInvMaterialReqBill2 scmInvMaterialReqBill3 : scmInvMaterialReqBillList2) {
//	                // 检查冻结物资
//	                HashMap<String,Object> map = new HashMap<String,Object>();
//	                map.put("taskId", scmInvMaterialReqBill3.getTaskId());
//	                map.put("otId", scmInvMaterialReqBill.getOtId());
//	                int count = ((ScmInvMaterialReqBillDAO) dao).inventoryMaterialFree(map);
//	                if (count > 0) {
//	                    msgList.add(Message.getMessage(param.getLang(), 
//	                            "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingCostcenter"));
//	                    return msgList;
//	                }
//            	}
            }
			List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = new ArrayList<>();
			if(StringUtils.equals("1", scmInvMaterialReqBill.getBizType())){
				//出库
				scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.checkStockByOutWareHouse(scmInvMaterialReqBill.getOtId(), param);
			}else{
				//退仓,暂不考虑成本计算方式
				scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.checkStockByReturnWareHouse(scmInvMaterialReqBill.getOtId(), param);
			}
			if(scmInvMaterialReqBillEntryList != null && !scmInvMaterialReqBillEntryList.isEmpty()){
				for(ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryList){
					if(StringUtils.isNotBlank(scmInvMaterialReqBillEntry.getLot())){
						String[] msparam = {scmInvMaterialReqBillEntry.getItemNo(),scmInvMaterialReqBillEntry.getItemName(),
								scmInvMaterialReqBillEntry.getLot(),(scmInvMaterialReqBillEntry.getQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
								(scmInvMaterialReqBillEntry.getInvQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()};
						msgList.add(Message.getMessage(param.getLang(),
								"iscm.inventorymanage.warehouseoutbusiness.ScmInvMaterialReqBill.postBillCheck.error", msparam));
					}else{
						String[] msparam = {scmInvMaterialReqBillEntry.getItemNo(),scmInvMaterialReqBillEntry.getItemName(),
								(scmInvMaterialReqBillEntry.getQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
								(scmInvMaterialReqBillEntry.getInvQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()};
						msgList.add(Message.getMessage(param.getLang(),
								"iscm.inventorymanage.warehouseoutbusiness.ScmInvMaterialReqBill.postBillCheck.error2", msparam));
					}
				}
			}
		}
		return msgList;
	}

	@Override
	public ScmInvMaterialReqBill2 postBill(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param)
			throws AppException {
		scmInvMaterialReqBill = this.selectDirect(scmInvMaterialReqBill.getOtId(), param);
		if(scmInvMaterialReqBill != null){
			List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqEntryList = scmInvMaterialReqBillEntryBiz.selectByOtId(scmInvMaterialReqBill.getOtId(), param);
			if(scmInvMaterialReqEntryList==null || scmInvMaterialReqEntryList.isEmpty()) {
				throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
			}
			if(!StringUtils.equals("A",scmInvMaterialReqBill.getStatus())) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvMaterialReqBill.getOtNo()}));
			}
			if(StringUtils.equals("1", scmInvMaterialReqBill.getBizType())){
				//领料出库
				if(!scmInvMaterialReqBill.isOffset()){
					splitBillByOutWarehouse(scmInvMaterialReqBill,param);
				}
				postBillByOutWarehouse(scmInvMaterialReqBill,param);
			}else if(StringUtils.equals("2", scmInvMaterialReqBill.getBizType())){
				//领料退仓
				if(!scmInvMaterialReqBill.isOffset()){
					splitBillByReturnWarehouse(scmInvMaterialReqBill,param);
				}
				postBillByReturnWarehouse(scmInvMaterialReqBill,param);
			}
			if (!(ParamType.Operation.equals(param.getParamType()) && ((OperationParam) param).getOperationId()==629223199L)) {
				scmInvMaterialReqBill.setCheckDate(CalendarUtil.getDate(param));
				scmInvMaterialReqBill.setChecker(param.getUsrCode());
				scmInvMaterialReqBill.setPostDate(CalendarUtil.getDate(param));
			}
			scmInvMaterialReqBill.setStatus("E");
			int updateRow = this.updatePostedStatus(scmInvMaterialReqBill, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvMaterialReqBill.getOtNo()}));
			}
		}
		return scmInvMaterialReqBill;	
	}
	
	private int updatePostedStatus(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param) {
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("otId",scmInvMaterialReqBill.getOtId());
		map.put("checker",scmInvMaterialReqBill.getChecker());
		map.put("checkDate",scmInvMaterialReqBill.getCheckDate()==null?null:FormatUtils.fmtDate(scmInvMaterialReqBill.getCheckDate()));
		map.put("status", scmInvMaterialReqBill.getStatus());
		map.put("postDate", scmInvMaterialReqBill.getPostDate()==null?null:FormatUtils.fmtDateTime(scmInvMaterialReqBill.getPostDate()));
		return ((ScmInvMaterialReqBillDAO)dao).updatePostedStatus(map);
	}

	/*
	 * 出库拆单 
	 */
	private void splitBillByOutWarehouse(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param){
		List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.selectByOtId(scmInvMaterialReqBill.getOtId(), param);
		if(scmInvMaterialReqBillEntryList != null && !scmInvMaterialReqBillEntryList.isEmpty()){
			scmInvMaterialReqBillEntryList = (List<ScmInvMaterialReqBillEntry2>)ListSortUtil.sort(scmInvMaterialReqBillEntryList, "lineId","Desc");	//按物资排序
			int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
			HashMap<String,List<ScmInvStock2>> qtyMap = new HashMap<String,List<ScmInvStock2>>();
			int lineId=1;
			for(int i=scmInvMaterialReqBillEntryList.size()-1;i>=0;i--){
				ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry = scmInvMaterialReqBillEntryList.get(i);
				StringBuffer info = new StringBuffer("");
				info.append(scmInvMaterialReqBill.getOrgUnitNo()).append("_")
						.append(scmInvMaterialReqBill.getWareHouseId()).append("_")
						.append(String.valueOf(scmInvMaterialReqBillEntry.getItemId())).append("_");
				if(!qtyMap.containsKey((info.toString()))){
					//查询计价方式
					Page page = new Page();
					page.setModelClass(ScmMaterial2.class);
					page.setShowCount(Integer.MAX_VALUE);
					page.setSqlCondition("ScmMaterial.id="+scmInvMaterialReqBillEntry.getItemId());
					ArrayList argList = new ArrayList();
			        argList.add("orgUnitNo="+scmInvMaterialReqBill.getFinOrgUnitNo());
			        argList.add("controlUnitNo=" + param.getControlUnitNo());
                    List<ScmMaterial2> materialList = scmMaterialBiz.queryPage(page, argList, "findBySingleFinAllPage", param);
                    //获取costMethod来决定升序还是降序排序
                    if (materialList != null && materialList.size() > 0) {
                    	String cost = StringUtils.isBlank(materialList.get(0).getCosting())?"FIFO":materialList.get(0).getCosting();
                    	HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("orgUnitNo", scmInvMaterialReqBill.getOrgUnitNo());
                        map.put("itemId", scmInvMaterialReqBillEntry.getItemId());
                        map.put("wareHouseId", scmInvMaterialReqBill.getWareHouseId());
                        map.put("bizDate",FormatUtils.fmtDate(scmInvMaterialReqBill.getBizDate()));
                        map.put("costMethod", cost);
                        map.put("unit", scmInvMaterialReqBillEntry.getUnit());
                        List<ScmInvStock2> stocklist = scmInvStockBiz.findByOutWarehouse(map, param);
                        qtyMap.put(info.toString(), stocklist);
                    }
				}
				boolean deleteFlag = false;
				List<ScmInvStock2> stocklist = qtyMap.get(info.toString());
                if(stocklist != null && !stocklist.isEmpty()){
                	if(StringUtils.isNotBlank(scmInvMaterialReqBillEntry.getLot())) {
                		//有批次则先按批次找，如不够再按先进先出拆单
                		for (ScmInvStock2 scmInvStock : stocklist) {
                			if(StringUtils.equals(scmInvMaterialReqBillEntry.getLot(), scmInvStock.getLot())) {
		                		if(scmInvMaterialReqBillEntry.getQty().compareTo(BigDecimal.ZERO)>0) {
		                			deleteFlag = true;
		                			if(setDataFromStock(scmInvStock,scmInvMaterialReqBillEntry,amtPrecision,lineId,param))
		                				lineId = lineId +1;
		                		}else {
		                			break;
		                		}
                			}
                		}
                	}
                	for (ScmInvStock2 scmInvStock : stocklist) {
                		if(scmInvMaterialReqBillEntry.getQty().compareTo(BigDecimal.ZERO)>0) {
                			deleteFlag = true;
                			if(setDataFromStock(scmInvStock,scmInvMaterialReqBillEntry,amtPrecision,lineId,param))
                				lineId = lineId +1;
                		}else {
                			break;
                		}
                	}
                	if(deleteFlag) {
                		scmInvMaterialReqBillEntryBiz.delete(scmInvMaterialReqBillEntry, param);
                	}
                }else {

				}
			}
		}
		List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList2 = scmInvMaterialReqBillEntryBiz.selectByOtId(scmInvMaterialReqBill.getOtId(), param);
		if(scmInvMaterialReqBillEntryList2 != null && !scmInvMaterialReqBillEntryList2.isEmpty()){
			//重新设置LineId,防止错乱
			for(int i=0;i<scmInvMaterialReqBillEntryList2.size();i++){
				scmInvMaterialReqBillEntryList2.get(i).setLineId(i+1);
			}
		}
	}
	
	private boolean setDataFromStock(ScmInvStock2 scmInvStock,ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry,int amtPrecision,int lineId,Param param) {
		boolean flag = false;
		BigDecimal stockQty = scmInvStock.getQty();
		BigDecimal stockPieQty = scmInvStock.getPieQty();
		BigDecimal stockBaseQty = scmInvStock.getBaseQty();
		BigDecimal stockAmt = (scmInvStock.getAmt());
		BigDecimal stockTaxAmt = (scmInvStock.getTaxAmt());
		if(stockQty.compareTo(scmInvMaterialReqBillEntry.getQty()) > 0){
    		//此批次够出库数量，暂不把辅助数量加入判断
    		ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry2= new ScmInvMaterialReqBillEntry2(true);
    		scmInvMaterialReqBillEntry2.setOtId(scmInvMaterialReqBillEntry.getOtId());
    		scmInvMaterialReqBillEntry2.setLineId(scmInvMaterialReqBillEntry.getLineId()+1);
    		scmInvMaterialReqBillEntry2.setItemId(scmInvStock.getItemId());
    		scmInvMaterialReqBillEntry2.setLot(scmInvStock.getLot());
    		scmInvMaterialReqBillEntry2.setUnit(scmInvStock.getUnit());
    		if(scmInvMaterialReqBillEntry.getSourceBillDtlId()>0)
    			scmInvMaterialReqBillEntry2.setReqQty(scmInvMaterialReqBillEntry.getReqQty());
    		scmInvMaterialReqBillEntry2.setQty(scmInvMaterialReqBillEntry.getQty());
    		scmInvMaterialReqBillEntry2.setBaseUnit(scmInvStock.getBaseUnit());
    		scmInvMaterialReqBillEntry2.setBaseQty(scmInvMaterialReqBillEntry.getBaseQty());
    		scmInvMaterialReqBillEntry2.setPieUnit(scmInvStock.getPieUnit());
    		scmInvMaterialReqBillEntry2.setPieQty(scmInvMaterialReqBillEntry.getPieQty());
//    		if(StringUtils.equals("WMA",materialList.get(0).getCosting())){
//    			scmInvMaterialReqBillEntry2.setPrice(scmInvMaterialReqBillEntry.getPrice());
//    			scmInvMaterialReqBillEntry2.setTaxRate(scmInvMaterialReqBillEntry.getTaxRate());
//    			scmInvMaterialReqBillEntry2.setTaxPrice(scmInvMaterialReqBillEntry.getTaxPrice());
//    			//精度暂时设置为4位，应该由系统参数控制，后续再加
//    			scmInvMaterialReqBillEntry2.setAmt((scmInvMaterialReqBillEntry2.getQty().multiply(scmInvMaterialReqBillEntry2.getPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
//    			scmInvMaterialReqBillEntry2.setTaxAmt((scmInvMaterialReqBillEntry2.getQty().multiply(scmInvMaterialReqBillEntry2.getTaxPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
//    		}else{
			scmInvMaterialReqBillEntry2.setPrice(scmInvStock.getPrice());
			scmInvMaterialReqBillEntry2.setTaxRate(scmInvStock.getTaxRate());
			scmInvMaterialReqBillEntry2.setTaxPrice(scmInvStock.getTaxPrice());
			scmInvMaterialReqBillEntry2.setAmt((scmInvMaterialReqBillEntry2.getQty().multiply(scmInvMaterialReqBillEntry2.getPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
			scmInvMaterialReqBillEntry2.setTaxAmt((scmInvMaterialReqBillEntry2.getQty().multiply(scmInvMaterialReqBillEntry2.getTaxPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
//    		}
    		scmInvMaterialReqBillEntry2.setExpDate(scmInvStock.getExpDate());
    		scmInvMaterialReqBillEntry2.setOffset(false);
    		scmInvMaterialReqBillEntry2.setSourceBillDtlId(scmInvMaterialReqBillEntry.getSourceBillDtlId());
    		scmInvMaterialReqBillEntry2.setStockId(scmInvStock.getId());
    		scmInvMaterialReqBillEntry2.setRemarks(scmInvMaterialReqBillEntry.getRemarks());
    		scmInvMaterialReqBillEntry2.setOrgSourceId(scmInvStock.getSourceBillId());
    		scmInvMaterialReqBillEntry2.setOrgSourceVendorId(scmInvStock.getVendorId());
    		scmInvMaterialReqBillEntry2.setInvDate(scmInvStock.getInvDate());
    		scmInvMaterialReqBillEntry2.setCostUseTypeId(scmInvMaterialReqBillEntry.getCostUseTypeId());
    		scmInvMaterialReqBillEntry2.setLineId(lineId);
    		scmInvMaterialReqBillEntryBiz.add(scmInvMaterialReqBillEntry2, param);
    		scmInvStock.setQty(scmInvStock.getQty().subtract(scmInvMaterialReqBillEntry2.getQty()));
    		scmInvStock.setPieQty(scmInvStock.getPieQty().subtract(scmInvMaterialReqBillEntry2.getPieQty()));
    		scmInvStock.setBaseQty(scmInvStock.getBaseQty().subtract(scmInvMaterialReqBillEntry2.getBaseQty()));
    		scmInvStock.setAmt(scmInvStock.getAmt().subtract(scmInvMaterialReqBillEntry2.getAmt()));
    		scmInvStock.setTaxAmt(scmInvStock.getTaxAmt().subtract(scmInvMaterialReqBillEntry2.getTaxAmt()));
    		scmInvMaterialReqBillEntry.setQty(BigDecimal.ZERO);
    		scmInvMaterialReqBillEntry.setPieQty(BigDecimal.ZERO);
    		scmInvMaterialReqBillEntry.setBaseQty(BigDecimal.ZERO);
    		flag = true;
    	}else if(stockQty.compareTo(BigDecimal.ZERO) > 0){
    		//此批次不够或刚好够出库数量
    		ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry2= new ScmInvMaterialReqBillEntry2(true);
    		scmInvMaterialReqBillEntry2.setOtId(scmInvMaterialReqBillEntry.getOtId());
    		scmInvMaterialReqBillEntry2.setLineId(scmInvMaterialReqBillEntry.getLineId()+1);
    		scmInvMaterialReqBillEntry2.setItemId(scmInvStock.getItemId());
    		scmInvMaterialReqBillEntry2.setLot(scmInvStock.getLot());
    		scmInvMaterialReqBillEntry2.setUnit(scmInvStock.getUnit());
    		if(scmInvMaterialReqBillEntry.getSourceBillDtlId()>0)
    			scmInvMaterialReqBillEntry2.setReqQty(stockQty);
    		scmInvMaterialReqBillEntry2.setQty(stockQty);
    		scmInvMaterialReqBillEntry2.setBaseUnit(scmInvStock.getBaseUnit());
    		scmInvMaterialReqBillEntry2.setBaseQty(stockBaseQty);
    		scmInvMaterialReqBillEntry2.setPieUnit(scmInvStock.getPieUnit());
    		scmInvMaterialReqBillEntry2.setPieQty(stockPieQty);
    		scmInvMaterialReqBillEntry2.setCostUseTypeId(scmInvMaterialReqBillEntry.getCostUseTypeId());
//    		if(StringUtils.equals("WMA",materialList.get(0).getCosting())){
//    			scmInvMaterialReqBillEntry2.setPrice(scmInvMaterialReqBillEntry.getPrice());
//    			scmInvMaterialReqBillEntry2.setTaxRate(scmInvMaterialReqBillEntry.getTaxRate());
//    			scmInvMaterialReqBillEntry2.setTaxPrice(scmInvMaterialReqBillEntry.getTaxPrice());
//    			//精度暂时设置为4位，应该由系统参数控制，后续再加
//    			scmInvMaterialReqBillEntry2.setAmt((scmInvMaterialReqBillEntry2.getQty().multiply(scmInvMaterialReqBillEntry2.getPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
//    			scmInvMaterialReqBillEntry2.setTaxAmt((scmInvMaterialReqBillEntry2.getQty().multiply(scmInvMaterialReqBillEntry2.getTaxPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
//    		}else{
			scmInvMaterialReqBillEntry2.setPrice(scmInvStock.getPrice());
			scmInvMaterialReqBillEntry2.setTaxRate(scmInvStock.getTaxRate());
			scmInvMaterialReqBillEntry2.setTaxPrice(scmInvStock.getTaxPrice());
			scmInvMaterialReqBillEntry2.setAmt(stockAmt);
			scmInvMaterialReqBillEntry2.setTaxAmt(stockTaxAmt);
//    		}
    		scmInvMaterialReqBillEntry2.setExpDate(scmInvStock.getExpDate());
    		scmInvMaterialReqBillEntry2.setOffset(false);
    		scmInvMaterialReqBillEntry2.setSourceBillDtlId(scmInvMaterialReqBillEntry.getSourceBillDtlId());
    		scmInvMaterialReqBillEntry2.setStockId(scmInvStock.getId());
    		scmInvMaterialReqBillEntry2.setRemarks(scmInvMaterialReqBillEntry.getRemarks());
    		scmInvMaterialReqBillEntry2.setOrgSourceId(scmInvStock.getSourceBillId());
    		scmInvMaterialReqBillEntry2.setOrgSourceVendorId(scmInvStock.getVendorId());
    		scmInvMaterialReqBillEntry2.setInvDate(scmInvStock.getInvDate());
    		scmInvMaterialReqBillEntry2.setLineId(lineId);
    		scmInvMaterialReqBillEntryBiz.add(scmInvMaterialReqBillEntry2, param);
    		scmInvMaterialReqBillEntry.setReqQty((scmInvMaterialReqBillEntry.getReqQty()).subtract(scmInvMaterialReqBillEntry2.getReqQty()));
			scmInvMaterialReqBillEntry.setQty((scmInvMaterialReqBillEntry.getQty()).subtract(scmInvMaterialReqBillEntry2.getQty()));
			scmInvMaterialReqBillEntry.setBaseQty((scmInvMaterialReqBillEntry.getBaseQty()).subtract(scmInvMaterialReqBillEntry2.getBaseQty()));
			scmInvMaterialReqBillEntry.setPieQty((scmInvMaterialReqBillEntry.getPieQty()).subtract(scmInvMaterialReqBillEntry2.getPieQty()));
			scmInvStock.setQty(BigDecimal.ZERO);
    		scmInvStock.setPieQty(BigDecimal.ZERO);
    		scmInvStock.setBaseQty(BigDecimal.ZERO);
    		scmInvStock.setAmt(BigDecimal.ZERO);
    		scmInvStock.setTaxAmt(BigDecimal.ZERO);
    		flag = true;
    	}
		return flag;
	}

	/*
	 * 领料出库取消过账
	 */
	@Override
	public ScmInvMaterialReqBill2 cancelPostBill(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param)	throws AppException {
		scmInvMaterialReqBill = this.selectDirect(scmInvMaterialReqBill.getOtId(), param);
		if(scmInvMaterialReqBill != null){
			List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqEntryList = scmInvMaterialReqBillEntryBiz.selectByOtId(scmInvMaterialReqBill.getOtId(), param);
			if(scmInvMaterialReqEntryList==null || scmInvMaterialReqEntryList.isEmpty()) {
				throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
			}
			if(!StringUtils.equals("E",scmInvMaterialReqBill.getStatus())) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvMaterialReqBill.getOtNo()}));
			}
			if(StringUtils.equals("1", scmInvMaterialReqBill.getBizType())){
				//取消过账更新出库仓库结存
				int updateRow = scmInvStockBiz.updateByMaterialReqBillIn(scmInvMaterialReqBill.getOtId(), param);
				//更新期间结存
				scmInvBalBiz.updateByMaterialReqBillIn(scmInvMaterialReqBill.getOtId(), param);
				List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.selectCancelOutEffectDeptRow(scmInvMaterialReqBill.getOtId(), param);
				if(updateRow<scmInvMaterialReqBillEntryList.size()){
					throw new AppException("iscm.inventorymanage.common.post.error.rowsnotequal");
				}
				
				//更新部门结存
				updateRow = scmInvStockBiz.updateByMaterialReqBillInOrgunitNo(scmInvMaterialReqBill.getOtId(), param);	
				if(updateRow<scmInvMaterialReqBillEntryList.size()){
					throw new AppException("iscm.inventorymanage.common.post.error.rowsnotequal");
				}
				//更新部门期间结存
				scmInvBalBiz.updateByMaterialReqBillInOrgunitNo(scmInvMaterialReqBill.getOtId(), param);
			}else if(StringUtils.equals("2", scmInvMaterialReqBill.getBizType())){
				//领料退仓取消过账
				//更新部门结存
				int updateRow = scmInvStockBiz.updateByMaterialReqBillOrgunitNo(scmInvMaterialReqBill.getOtId(), param);
				//更新部门期间结存
				scmInvBalBiz.updateByMaterialReqBillOrgunitNo(scmInvMaterialReqBill.getOtId(), param);
				//更新仓库结存
				List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.selectCancelOutEffectWareHsRow(scmInvMaterialReqBill.getOtId(), param);
				if(updateRow<scmInvMaterialReqBillEntryList.size()){
					throw new AppException("iscm.inventorymanage.common.post.error.rowsnotequal");
				}
				
				updateRow = scmInvStockBiz.updateByMaterialReqBill(scmInvMaterialReqBill.getOtId(), param);
				if(updateRow<scmInvMaterialReqBillEntryList.size()){
					throw new AppException("iscm.inventorymanage.common.post.error.rowsnotequal");
				}
				//更新仓库期间结存
				scmInvBalBiz.updateByMaterialReqBill(scmInvMaterialReqBill.getOtId(), param);
			}
			scmInvMaterialReqBill.setCheckDate(null);
			scmInvMaterialReqBill.setChecker("");
			scmInvMaterialReqBill.setStatus("A");
			scmInvMaterialReqBill.setPostDate(null);
			int updateRow = this.updatePostedStatus(scmInvMaterialReqBill, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvMaterialReqBill.getOtNo()}));
			}
		}
		return scmInvMaterialReqBill;
	}
	
	/*
	 * 出库过账                                                                                                                                                                                                  
	 */
	private void postBillByOutWarehouse(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param) throws AppException{
		List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.selectOutEffectRow(scmInvMaterialReqBill.getOtId(), param);
		//更新出货仓库结存
		int updateRow = scmInvStockBiz.updateByMaterialReqBillOut(scmInvMaterialReqBill.getOtId(), param);
		if(updateRow<scmInvMaterialReqBillEntryList.size()){
			throw new AppException("iscm.inventorymanage.common.post.error.rowsnotequal");
		}
		//更新出货仓库期间结存
		scmInvBalBiz.updateByMaterialReqBillOut(scmInvMaterialReqBill.getOtId(), param);
		//移动平均更新出货仓库平均单价
//		scmInvCostBiz.updateByMaterialReqBillOut(scmInvMaterialReqBill.getOtId(), param);

		//更新部门结存
		scmInvStockBiz.updateByMaterialReqBillOutOrgunitNo(scmInvMaterialReqBill.getOtId(), param);
		//插入部门结存
		scmInvStockBiz.addByMaterialReqBillOutOrgunitNo(scmInvMaterialReqBill.getOtId(), param);
		//更新部门移动平均单价
//		scmInvCostBiz.updateByMaterialReqBillOutOrgunitNo(scmInvMaterialReqBill.getOtId(), param);
		//插入部门移动平均单价
//		scmInvCostBiz.addByMaterialReqBillOutOrgunitNo(scmInvMaterialReqBill.getOtId(), param);
		//更新部门期间结存   
		scmInvBalBiz.updateByMaterialReqBillOutOrgunitNo(scmInvMaterialReqBill.getOtId(), param);
		//插入部门期间结存
		scmInvBalBiz.addByMaterialReqBillOutOrgunitNo(scmInvMaterialReqBill.getOtId(), param);
	}
	
	/*
	 * 退仓拆单 
	 */
	private void splitBillByReturnWarehouse(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param){
		List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.selectByOtId(scmInvMaterialReqBill.getOtId(), param);
		if(scmInvMaterialReqBillEntryList != null && !scmInvMaterialReqBillEntryList.isEmpty()){
			scmInvMaterialReqBillEntryList = (List<ScmInvMaterialReqBillEntry2>)ListSortUtil.sort(scmInvMaterialReqBillEntryList,  "lineId","Desc");	//按行号排序
			int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
			HashMap<String,List<ScmInvStock2>> qtyMap = new HashMap<String,List<ScmInvStock2>>();
			int lineId=1;
			if(scmInvMaterialReqBillEntryList != null && !scmInvMaterialReqBillEntryList.isEmpty()){
				for(int i=scmInvMaterialReqBillEntryList.size()-1;i>=0;i--){
					ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry = scmInvMaterialReqBillEntryList.get(i);
					StringBuffer info = new StringBuffer("");
					info.append(scmInvMaterialReqBill.getCostOrgUnitNo()).append("_")
						.append(scmInvMaterialReqBill.getUseOrgUnitNo()).append("_")
						.append(String.valueOf(scmInvMaterialReqBillEntry.getItemId())).append("_");
					if(!qtyMap.containsKey((info.toString()))){
						//查询计价方式
						Page page = new Page();
						page.setModelClass(ScmMaterial2.class);
						page.setShowCount(Integer.MAX_VALUE);
						page.setSqlCondition("ScmMaterial.id="+scmInvMaterialReqBillEntry.getItemId());
						ArrayList argList = new ArrayList();
				        argList.add("orgUnitNo="+scmInvMaterialReqBill.getFinOrgUnitNo());
				        argList.add("controlUnitNo=" + param.getControlUnitNo());
                        List<ScmMaterial2> materialList = scmMaterialBiz.queryPage(page, argList, "findBySingleFinAllPage", param);
                        //获取costMethod来决定升序还是降序排序
                        if (materialList != null && materialList.size() > 0 && StringUtils.isNotBlank(materialList.get(0).getCosting())) {
                        	HashMap<String, Object> map = new HashMap<String, Object>();
                            map.put("useOrgUnitNo", scmInvMaterialReqBill.getUseOrgUnitNo());
                            map.put("itemId", scmInvMaterialReqBillEntry.getItemId());
                            map.put("costOrgUnitNo", scmInvMaterialReqBill.getCostOrgUnitNo());
                            map.put("bizDate", scmInvMaterialReqBill.getBizDate());
                            map.put("costMethod", materialList.get(0).getCosting());
                            map.put("unit", scmInvMaterialReqBillEntry.getUnit());
                            List<ScmInvStock2> stocklist = scmInvStockBiz.findByReturnWarehouse(map, param);
                            qtyMap.put(info.toString(), stocklist);
                        }
					}
					boolean deleteFlag = false;
					List<ScmInvStock2> stocklist = qtyMap.get(info.toString());
                    if(stocklist != null && !stocklist.isEmpty()){
                    	if(StringUtils.isNotBlank(scmInvMaterialReqBillEntry.getLot())) {
                    		//有批次则先按批次找，如不够再按先进先出拆单
                    		for (ScmInvStock2 scmInvStock : stocklist) {
                    			if(StringUtils.equals(scmInvMaterialReqBillEntry.getLot(), scmInvStock.getLot())) {
    		                		if(scmInvMaterialReqBillEntry.getQty().compareTo(BigDecimal.ZERO)>0) {
    		                			deleteFlag = true;
    		                			if(setDataFromStock(scmInvStock,scmInvMaterialReqBillEntry,amtPrecision,lineId,param))
    		                				lineId = lineId + 1;
    		                		}else {
    		                			break;
    		                		}
                    			}
                    		}
                    	}
                    	for (ScmInvStock2 scmInvStock : stocklist) {
                    		if(scmInvMaterialReqBillEntry.getQty().compareTo(BigDecimal.ZERO)>0) {
                    			deleteFlag = true;
                    			if(setDataFromStock(scmInvStock,scmInvMaterialReqBillEntry,amtPrecision,lineId,param))
	                				lineId = lineId + 1;
                    		}else {
                    			break;
                    		}
                    	}
                    	if(deleteFlag) {
                    		scmInvMaterialReqBillEntryBiz.delete(scmInvMaterialReqBillEntry, param);
                    	}
					}
				}
			}
		}
		List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList2 = scmInvMaterialReqBillEntryBiz.selectByOtId(scmInvMaterialReqBill.getOtId(), param);
		if(scmInvMaterialReqBillEntryList2 != null && !scmInvMaterialReqBillEntryList2.isEmpty()){
			//重新设置LineId,防止错乱
			for(int i=0;i<scmInvMaterialReqBillEntryList2.size();i++){
				scmInvMaterialReqBillEntryList2.get(i).setLineId(i+1);
			}
		}
	}
	
	/*
	 * 退仓过账
	 */
	private void postBillByReturnWarehouse(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param){
		List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.selectReturnEffectRow(scmInvMaterialReqBill.getOtId(), param);
		//更新退货部门结存
		int updateRow = scmInvStockBiz.updateByMaterialReqBillReturnOrgunitNo(scmInvMaterialReqBill.getOtId(), param);
		if(updateRow<scmInvMaterialReqBillEntryList.size()){
			throw new AppException("iscm.inventorymanage.common.post.error.rowsnotequal");
		}
		//更新部门期间结存
		scmInvBalBiz.updateByMaterialReqBillReturnOrgunitNo(scmInvMaterialReqBill.getOtId(), param);
		//更新部门移动平均单价
//		scmInvCostBiz.updateByMaterialReqBillReturnOrgunitNo(scmInvMaterialReqBill.getOtId(), param);

		//更新仓库结存
		scmInvStockBiz.updateByMaterialReqBillReturn(scmInvMaterialReqBill.getOtId(), param);
		//插入仓库结存
		scmInvStockBiz.addByMaterialReqBillReturn(scmInvMaterialReqBill.getOtId(), param);
		//更新仓库期间结存
		scmInvBalBiz.updateByMaterialReqBillReturn(scmInvMaterialReqBill.getOtId(), param);
		//插入仓库期间结存
		scmInvBalBiz.addByMaterialReqBillReturn(scmInvMaterialReqBill.getOtId(), param);
		//移动平均更新仓库平均单价
//		scmInvCostBiz.updateByMaterialReqBillReturn(scmInvMaterialReqBill.getOtId(), param);
	}
	
	/*
	 * 获取汇总数据
	 */
	private HashMap<String,List<BigDecimal>> getSumMap(List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList){
		if(scmInvMaterialReqBillEntryList != null && !scmInvMaterialReqBillEntryList.isEmpty()){
			HashMap<String,List<BigDecimal>> qtyMap = new HashMap<String,List<BigDecimal>>();
			for(ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryList){
				StringBuffer info = new StringBuffer("");
				info.append(String.valueOf(scmInvMaterialReqBillEntry.getItemId())).append("_")
					.append(String.valueOf(scmInvMaterialReqBillEntry.getUnit())).append("_")
					.append(scmInvMaterialReqBillEntry.getLot());
				if(!qtyMap.containsKey((info.toString()))){
					List<BigDecimal> qtyList = new ArrayList<>();
					BigDecimal sumQty = BigDecimal.ZERO;
					BigDecimal sumPieQty = BigDecimal.ZERO;
					BigDecimal sumBaseQty = BigDecimal.ZERO;
					BigDecimal sumAmt = BigDecimal.ZERO;
					/*if(scmInvMaterialReqBillEntry.getQty() != null){
						sumQty = sumQty.add(scmInvMaterialReqBillEntry.getQty());
					}
					if(scmInvMaterialReqBillEntry.getPieQty() != null){
						sumPieQty = sumPieQty.add(scmInvMaterialReqBillEntry.getPieQty());
					}
					if(scmInvMaterialReqBillEntry.getBaseQty() != null){
						sumBaseQty = sumBaseQty.add(scmInvMaterialReqBillEntry.getBaseQty());
					}
					if(scmInvMaterialReqBillEntry.getAmt() != null){
						sumAmt = sumAmt.add(scmInvMaterialReqBillEntry.getAmt());
					}*/
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
					/*if(scmInvMaterialReqBillEntry.getQty() != null){
						sumQty = sumQty.add(scmInvMaterialReqBillEntry.getQty());
					}
					if(scmInvMaterialReqBillEntry.getPieQty() != null){
						sumPieQty = sumPieQty.add(scmInvMaterialReqBillEntry.getPieQty());
					}
					if(scmInvMaterialReqBillEntry.getBaseQty() != null){
						sumBaseQty = sumBaseQty.add(scmInvMaterialReqBillEntry.getBaseQty());
					}
					if(scmInvMaterialReqBillEntry.getAmt() != null){
						sumAmt = sumAmt.add(scmInvMaterialReqBillEntry.getAmt());
					}*/
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
	
	/*
	 * 删除领料单 
	 */
	@Override
	public void doDelInvMaterialReq(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put(ScmInvMaterialReqBill2.FN_OTNO, scmInvMaterialReqBill.getOtNo());
		map.put(ScmInvMaterialReqBill2.FN_ORGUNITNO, param.getOrgUnitNo());
		List<ScmInvMaterialReqBill2> scmInvMaterialReqBillList = this.findAll(map, param);
		if(scmInvMaterialReqBillList!=null && !scmInvMaterialReqBillList.isEmpty()){
			try {
				this.delete(scmInvMaterialReqBillList, param);
			} catch (Exception e) {
				throw e;
			}
		}else{
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.find.ordernotexists");
		}
	}
	
	/*
	 * 提交领料单
	 */
	@Override
	public ScmInvMaterialReqBill2 doSubmitInvMaterialReq(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param) throws AppException {
		ScmInvMaterialReqBill2 bill=null;
		if(scmInvMaterialReqBill.getOtId()>0) {
			bill = this.selectDirect(scmInvMaterialReqBill.getOtId(), param);
		}else {
			Page page=new Page();
			page.setModelClass(ScmInvMaterialReqBill2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmInvMaterialReqBill2.FN_OTNO,
					new QueryParam(ScmInvMaterialReqBill2.FN_OTNO, QueryParam.QUERY_EQ,
							scmInvMaterialReqBill.getOtNo()));
			
			List<ScmInvMaterialReqBill2> scmInvMaterialReqBillList =this.findPage(page, param);
			if(scmInvMaterialReqBillList!=null && !scmInvMaterialReqBillList.isEmpty()){
				bill=scmInvMaterialReqBillList.get(0);
			}
		}
		if(bill!=null){
			this.setConvertMap(bill, param);
			if(!bill.getStatus().equals("I")){
				throw new AppException("iscm.inventorymanage.error.commit");
			}else if(bill.getStatus().equals("I")){
				List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqEntryList = scmInvMaterialReqBillEntryBiz.selectByOtId(bill.getOtId(), param);
				if(scmInvMaterialReqEntryList==null || scmInvMaterialReqEntryList.isEmpty()) {
					throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
				}
				
				BillType2 billType = billTypeBiz.selectByOrgAndCode(bill.getFinOrgUnitNo(), "InvMatReqout", param);
				boolean needAudit = false;
				if(billType!=null && billType.isNeedAudit())
					needAudit = true;
				if(needAudit){
					String processInstanceId = this.startWorkFlow(billType, scmInvMaterialReqBill, scmInvMaterialReqEntryList, param);
					bill.setWorkFlowNo(processInstanceId);
					ActivityUtil activityUtil = new ActivityUtil();
					//判断当前流程是否结束
					boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
					if(isCompleteTask) {
						if(param.getUsrCode()!=null ){
							bill.setChecker(param.getUsrCode());
							bill.setSubmitter(param.getUsrCode());
						}
						bill.setCheckDate(CalendarUtil.getDate(param));
						bill.setSubmitDate(CalendarUtil.getDate(param));
						bill.setStatus("A");
					}else {
						if(param.getUsrCode()!=null ){
							bill.setSubmitter(param.getUsrCode());
						}
						bill.setSubmitDate(CalendarUtil.getDate(param));
						bill.setStatus("D");
						this.sendAuditMsg(bill, billType.getBillCode(), param);
					}
				}else{
					if(billType.isUseFlow()) {
						//启用工作流（不影响状态）
						String processInstanceId = this.startWorkFlow(billType, bill, scmInvMaterialReqEntryList, param);
						bill.setWorkFlowNo(processInstanceId);
						bill.setSubmitDate(CalendarUtil.getDate(param));
						ActivityUtil activityUtil = new ActivityUtil();
						//判断当前流程是否结束
						boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
						if(!isCompleteTask) {
							this.sendAuditMsg(bill, billType.getBillCode(), param);
						}
					}
					if(param.getUsrCode()!=null ){
						bill.setChecker(param.getUsrCode());
						bill.setSubmitter(param.getUsrCode());
					}
					bill.setCheckDate(CalendarUtil.getDate(param));
					bill.setSubmitDate(CalendarUtil.getDate(param));
					bill.setStatus("A");
				}
				
				try{
					this.updateStatus(bill, param);
				}catch(AppException e){
					throw e;
				}
				SystemState systemState = systemStateBiz.selectBySystemId(bill.getFinOrgUnitNo(), 170, param);
				if(systemState==null){
					throw new AppException("com.armitage.iars.daily.util.disenable.unable");
				}
				if (systemState.getCurrentPeriodId() == bill.getPeriodId()) {
				if(StringUtils.equals("A", bill.getStatus())) {
					//通过或部分通过时检查是否自动过帐
					if(billType!=null && billType.isAutoRelease()) {
						List<String> msgList = this.postBillCheck(bill, param);
						if (msgList != null && !msgList.isEmpty()) {
							StringBuilder detailInfo = new StringBuilder("");
	                        detailInfo.append(Message.getMessage(param.getLang(),"iscm.inventorymanage.scminvmaterialreqbill.post.errorTitle"));
	
							for (String str : msgList) {
	                            detailInfo.append(str).append("\n");
	                        }
							
							throw new AppException(detailInfo.toString(), new String[]{bill.getOtNo()});
						}
						
						this.postBill(bill, param);
					}
				}
			}
			}
		}else{
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.find.ordernotexists");
		}
		return bill;
	}
	
	private String startWorkFlow(BillType2 billType,ScmInvMaterialReqBill2 scmInvMaterialReqBill,List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqEntryList, Param param) {
		CommonBean bean = new CommonBean();
		List<ScmInvMaterialReqBill2> scmInvMaterialReqList = new ArrayList<>();
		scmInvMaterialReqList.add(scmInvMaterialReqBill);
		bean.setList(scmInvMaterialReqList);
		bean.setList2(scmInvMaterialReqEntryList);
		String processInstanceId = ActivityUtil.startProcessInstance(bean, billType.getId(), billType.getWorkFlowNo(), param);
		return processInstanceId;
	}
	
	private void sendAuditMsg(ScmInvMaterialReqBill2 scmInvMaterialReqBill,String billCode,Param param) {
		String usrList= ActivityUtil.findAssigneeByProcessInstanceId(scmInvMaterialReqBill.getWorkFlowNo(),param);
		if(StringUtils.isNotBlank(usrList)) {
			scmBillPendingBiz.addPendingBill(usrList, scmInvMaterialReqBill, param);
			InvSaleOrderParams invSaleOrderParams = new InvSaleOrderParams();
			invSaleOrderParams.setOtNo(scmInvMaterialReqBill.getOtNo());
			AuditMsgUtil.sendAuditMsg(billCode,scmInvMaterialReqBill,invSaleOrderParams, usrList, param);
		}
	}
	/*
	 * 取消提交领料单
	 */
	@Override
	public ScmInvMaterialReqBill2 doUnSubmitInvMaterialReq(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param) throws AppException {
		ScmInvMaterialReqBill2 bill=null;
		if(scmInvMaterialReqBill.getOtId()>0) {
			bill = this.selectDirect(scmInvMaterialReqBill.getOtId(), param);
		}else {
			Page page=new Page();
			page.setModelClass(ScmInvMaterialReqBill2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmInvMaterialReqBill2.FN_OTNO,
					new QueryParam(ScmInvMaterialReqBill2.FN_OTNO, QueryParam.QUERY_EQ,
							scmInvMaterialReqBill.getOtNo()));
			
			List<ScmInvMaterialReqBill2> scmInvMaterialReqBillList =this.findPage(page, param);
			if(scmInvMaterialReqBillList!=null && !scmInvMaterialReqBillList.isEmpty()){
				bill=scmInvMaterialReqBillList.get(0);
			}
		}
		if(bill!=null){
			List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqEntryList = scmInvMaterialReqBillEntryBiz.selectByOtId(bill.getOtId(), param);
			if(scmInvMaterialReqEntryList==null || scmInvMaterialReqEntryList.isEmpty()) {
				throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
			}
			
			BillType2 billType = billTypeBiz.selectByOrgAndCode(bill.getFinOrgUnitNo(), "InvMatReqout", param);
			boolean needAudit = false;
			if(billType!=null && billType.isNeedAudit() && StringUtils.isNotBlank(bill.getWorkFlowNo()))
				needAudit = true;
			
			//单据设置审批流程，但设置没有审批节点，状态为通过，可以取消通过
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ScmBillPending.FN_BILLID, new QueryParam(ScmBillPending.FN_BILLID, QueryParam.QUERY_EQ, String.valueOf(bill.getOtId())));
			map.put(ScmBillPending.FN_BILLTYPE, new QueryParam(ScmBillPending.FN_BILLTYPE, QueryParam.QUERY_EQ, billType.getBillCode()));
			List<ScmBillPending> scmBillPendingList = scmBillPendingBiz.findAll(map, param);
			
			
			
			if(needAudit){
				if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
					if(!StringUtils.equals(bill.getStatus(),"A"))
						throw new AppException("iscm.inventorymanage.error.cancelCommit");
				} else {
					if(!StringUtils.equals(bill.getStatus(),"D"))
						throw new AppException("iscm.inventorymanage.error.cancelCommit");
				}
			}else{
				//启用审批进入审核中(再调整为启用工作流)的单据的状态不是A
				if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
				if(!StringUtils.equals(bill.getStatus(),"A"))
					throw new AppException("iscm.inventorymanage.error.cancelCommit");
				} else {
					if(!StringUtils.contains("D,A",bill.getStatus()))
						throw new AppException("iscm.inventorymanage.error.cancelCommit");
				}
			} 
			if(bill.getStatus().equals("A") || bill.getStatus().equals("D")){
				bill.setChecker(null);
				bill.setCheckDate(null);
				bill.setSubmitter(null);
				bill.setSubmitDate(null);
				bill.setStatus("I");
				try{
					this.updateStatus(bill, param);
					scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),bill, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.find.ordernotexists");
		}
		return bill;
	}

	@Override
	protected void beforeDelete(ScmInvMaterialReqBill2 entity, Param param)
			throws AppException {
		ScmInvMaterialReqBill2 scmInvMaterialReqBill = this.selectDirect(entity.getOtId(), param);
		if(scmInvMaterialReqBill!=null && !StringUtils.equals(scmInvMaterialReqBill.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getOtNo()}));
		}
	}

	@Override
	public List<String> cancelPostBillCheck(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param) throws AppException {
		List<String> msgList = new ArrayList<String>();// 提示列表
		scmInvMaterialReqBill = this.selectDirect(scmInvMaterialReqBill.getOtId(), param);
		if(scmInvMaterialReqBill != null){
			if(!StringUtils.equals("E",scmInvMaterialReqBill.getStatus())) {
				msgList.add(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvMaterialReqBill.getOtNo()}));
				return msgList;
			}
			SystemState systemState = systemStateBiz.selectBySystemId(scmInvMaterialReqBill.getFinOrgUnitNo(), 170, param);
	        if(systemState==null){
	            msgList.add(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
	            return msgList;
	        }
	        if (systemState.getCurrentPeriodId() != scmInvMaterialReqBill.getPeriodId()) {
	            msgList.add(Message.getMessage(param.getLang(), 
	                    "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmInvMaterialReqBill.getOtNo()}));
	            return msgList;
	        }
	        
	        // 检查盘点物资冻结
	        // 检查冻结仓库
	        List<ScmInvMaterialReqBill2> scmInvMaterialReqBillList = ((ScmInvMaterialReqBillDAO) dao).checkWareHouseFree(scmInvMaterialReqBill.getOtId());
	        if (scmInvMaterialReqBillList != null && !scmInvMaterialReqBillList.isEmpty()) {
	        	msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
                return msgList;
//	        	for (ScmInvMaterialReqBill2 scmInvMaterialReqBill2 : scmInvMaterialReqBillList) {
//		            // 检查冻结物资
//		            HashMap<String,Object> map = new HashMap<String,Object>();
//	                map.put("taskId", scmInvMaterialReqBill2.getTaskId());
//	                map.put("otId", scmInvMaterialReqBill.getOtId());
//		            int count = ((ScmInvMaterialReqBillDAO) dao).checkMaterialFree(map);
//		            if (count > 0) {
//		                msgList.add(Message.getMessage(param.getLang(), 
//		                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
//		                return msgList;
//		            }
//	        	}
	        }
	        // 检查盘存物资冻结
	        List<ScmInvMaterialReqBill2> scmInvMaterialReqBillList2 = ((ScmInvMaterialReqBillDAO) dao).inventoryWareHouseFree(scmInvMaterialReqBill.getOtId());
	        // 检查冻结仓库
            if (scmInvMaterialReqBillList2 != null && !scmInvMaterialReqBillList2.isEmpty()) {
            	msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingCostcenter"));
                return msgList;
//            	for (ScmInvMaterialReqBill2 scmInvMaterialReqBill3 : scmInvMaterialReqBillList2) {
//	                // 检查冻结物资
//	                HashMap<String,Object> map = new HashMap<String,Object>();
//	                map.put("taskId", scmInvMaterialReqBill3.getTaskId());
//	                map.put("otId", scmInvMaterialReqBill.getOtId());
//	                int count = ((ScmInvMaterialReqBillDAO) dao).inventoryMaterialFree(map);
//	                if (count > 0) {
//	                    msgList.add(Message.getMessage(param.getLang(), 
//	                            "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingCostcenter"));
//	                    return msgList;
//	                }
//            	}
            }
            
			List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = new ArrayList<>();
			if(StringUtils.equals("1", scmInvMaterialReqBill.getBizType())){
				//领料出库取消过账是采用退仓方式进行检查
				scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.checkStockByReturnWareHouse(scmInvMaterialReqBill.getOtId(), param);
			}else{
				//领料退仓取消过账时采购出库方式进行检查
				scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.checkStockByOutWareHouse(scmInvMaterialReqBill.getOtId(), param);
			}
			if(scmInvMaterialReqBillEntryList != null && !scmInvMaterialReqBillEntryList.isEmpty()){
				for(ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryList){
					if(StringUtils.isNotBlank(scmInvMaterialReqBillEntry.getLot())){
						String[] msparam = {scmInvMaterialReqBillEntry.getItemNo(),scmInvMaterialReqBillEntry.getItemName(),
								scmInvMaterialReqBillEntry.getLot(),(scmInvMaterialReqBillEntry.getQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
								(scmInvMaterialReqBillEntry.getInvQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()};
						msgList.add(Message.getMessage(param.getLang(),
								"iscm.inventorymanage.warehouseoutbusiness.ScmInvMaterialReqBill.postBillCheck.error", msparam));
					}else{
						String[] msparam = {scmInvMaterialReqBillEntry.getItemNo(),scmInvMaterialReqBillEntry.getItemName(),
								(scmInvMaterialReqBillEntry.getQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
								(scmInvMaterialReqBillEntry.getInvQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()};
						msgList.add(Message.getMessage(param.getLang(),
								"iscm.inventorymanage.warehouseoutbusiness.ScmInvMaterialReqBill.postBillCheck.error2", msparam));
					}
				}
			}
		}
		return msgList;
	}

	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvMaterialReqBill2 entry = (ScmInvMaterialReqBill2) bean.getList().get(0);
		if(entry!=null){
			ScmInvMaterialReqBill2 scmInvMaterialReqBill = this.select(entry.getPK(), param);
			if(!StringUtils.equals(scmInvMaterialReqBill.getStatus(),"I")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
		}
	}

	@Override
	public List<ScmInvMaterialReqBill2> checkUnPostBill(String finOrgUnitNo,long periodId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmInvMaterialReqBillDAO)dao).checkUnPostBill(map);
	}

	@Override
	public List<ScmInvMaterialReqBill2> checkPostedBill(String finOrgUnitNo,long periodId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmInvMaterialReqBillDAO)dao).checkPostedBill(map);
	}
	
	@Override
	public List<ScmInvMaterialReqBill2> queryInvMaterialReqBillList(ScmInvMaterialReqBillAdvQuery scmInvMaterialReqBillAdvQuery, int pageNum, Param param)
			throws AppException {
		Page page=new Page();
		page.setModelClass(ScmInvMaterialReqBill2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		if (StringUtils.isNotBlank(scmInvMaterialReqBillAdvQuery.getWareHouseNo())) {
			ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectByWhNo(param.getOrgUnitNo(), scmInvMaterialReqBillAdvQuery.getWareHouseNo(), param);
			if(scmInvWareHouse!=null)
				scmInvMaterialReqBillAdvQuery.setWareHouseId(scmInvWareHouse.getId());
		}
		page.setModel(scmInvMaterialReqBillAdvQuery);	//findPage时底层会触发doAdvQuery，增加查询条件
		return this.findPage(page, param);
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvMaterialReqBillAdvQuery) {
				ScmInvMaterialReqBillAdvQuery scmInvMaterialReqBillAdvQuery = (ScmInvMaterialReqBillAdvQuery) page.getModel();
				if(scmInvMaterialReqBillAdvQuery.getWareHouseId()>0) {
					page.getParam().put(ScmInvMaterialReqBill2.FN_WAREHOUSEID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_WAREHOUSEID, QueryParam.QUERY_EQ,String.valueOf(scmInvMaterialReqBillAdvQuery.getWareHouseId())));
				}
				String filterWarehouseByUsr = ScmWarehouseUtil.filterWarehouseByUsr("", param);
				if (StringUtils.isNotBlank(filterWarehouseByUsr)) {
					page.getParam().put(ScmInvMaterialReqBill2.FN_WAREHOUSEID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_WAREHOUSEID, QueryParam.QUERY_IN,filterWarehouseByUsr));
				}
				if(StringUtils.isNotBlank(scmInvMaterialReqBillAdvQuery.getUseOrgUnitNo())) {
					page.getParam().put(ScmInvMaterialReqBill2.FN_USEORGUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_USEORGUNITNO, QueryParam.QUERY_EQ,scmInvMaterialReqBillAdvQuery.getUseOrgUnitNo()));
				}
				if (StringUtils.isNotBlank(scmInvMaterialReqBillAdvQuery.getReqBillNo())) {
					page.getParam().put(ScmInvMaterialReqBill2.FN_OTNO, new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_OTNO,QueryParam.QUERY_LIKE,"%"+scmInvMaterialReqBillAdvQuery.getReqBillNo()+"%"));
				}
				if (StringUtils.isNotBlank(scmInvMaterialReqBillAdvQuery.getBizType())) {
					page.getParam().put(ScmInvMaterialReqBill2.FN_BIZTYPE, new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_BIZTYPE,QueryParam.QUERY_EQ,scmInvMaterialReqBillAdvQuery.getBizType()));
				}
				if (StringUtils.isNotBlank(scmInvMaterialReqBillAdvQuery.getUseDeptNo())) {
					page.getParam().put(ScmInvMaterialReqBill2.FN_USEORGUNITNO, new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_USEORGUNITNO,QueryParam.QUERY_EQ,scmInvMaterialReqBillAdvQuery.getUseDeptNo()));
				}
				if (StringUtils.isNotBlank(scmInvMaterialReqBillAdvQuery.getRequestPerson())) {
					page.getParam().put(ScmInvMaterialReqBill2.FN_USEPERSON, new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_USEPERSON,QueryParam.QUERY_EQ,scmInvMaterialReqBillAdvQuery.getRequestPerson()));
				}
				if(scmInvMaterialReqBillAdvQuery.getBizDateFrom()!=null){
					if(scmInvMaterialReqBillAdvQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmInvMaterialReqBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvMaterialReqBillAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(scmInvMaterialReqBillAdvQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmInvMaterialReqBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvMaterialReqBillAdvQuery.getBizDateFrom())));
					}
				}else if(scmInvMaterialReqBillAdvQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmInvMaterialReqBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmInvMaterialReqBillAdvQuery.getBizDateTo())));
				}
				if(scmInvMaterialReqBillAdvQuery.getCreateDateFrom()!=null){
					if(scmInvMaterialReqBillAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmInvMaterialReqBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvMaterialReqBillAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvMaterialReqBillAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmInvMaterialReqBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvMaterialReqBillAdvQuery.getCreateDateFrom())));
					}
				}else if(scmInvMaterialReqBillAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmInvMaterialReqBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvMaterialReqBillAdvQuery.getCreateDateTo(),1))));
				}
				if(StringUtils.isNotBlank(scmInvMaterialReqBillAdvQuery.getStatus())){
					String status[] = StringUtils.split(scmInvMaterialReqBillAdvQuery.getStatus(), ",");
					StringBuffer statusBuffer = new StringBuffer("");
					for(String sta:status){
						if(StringUtils.isNotBlank(statusBuffer.toString()))
							statusBuffer.append(",");
						statusBuffer.append("'").append(sta).append("'");
					}
					if(StringUtils.isNotBlank(statusBuffer.toString())){
						page.getParam().put(ScmInvMaterialReqBill2.FN_STATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +	ScmInvMaterialReqBill2.FN_STATUS, QueryParam.QUERY_IN,statusBuffer.toString()));
					}
				}
			}
		}
	}

	@Override
	public ScmInvMaterialReqBill2 doAddMaterialReqBill(InvMaterialReqBillAddParams invMaterialReqBillAddParams, Param param)
			throws AppException {
		CommonBean bean = new CommonBean();
		List<ScmInvMaterialReqBill2> scmInvMaterialReqBillList = new ArrayList<>();
		ScmInvMaterialReqBill2 scmInvMaterialReqBill = new ScmInvMaterialReqBill2(true);
		try {
			BeanUtils.copyProperties(invMaterialReqBillAddParams, scmInvMaterialReqBill);
			//scmInvMaterialReqBill.setBizDate(FormatUtils.parseDate(FormatUtils.fmtDate(invMaterialReqBillAddParams.getBizDate())));
			scmInvMaterialReqBill.setStatus("I");
			scmInvMaterialReqBill.setUsePerson(invMaterialReqBillAddParams.getRequestPerson());
			scmInvMaterialReqBill.setOrgUnitNo(invMaterialReqBillAddParams.getOrgUnitNo());
			scmInvMaterialReqBill.setCreateDate(CalendarUtil.getDate(param));
			scmInvMaterialReqBill.setCreator(param.getUsrCode());
			if(scmInvMaterialReqBill.getBizDate() != null){
				scmInvMaterialReqBill.setBizDate(FormatUtils.parseDateTime(FormatUtils.fmtDate(scmInvMaterialReqBill.getBizDate()) + " 00:00:00"));
			}
			ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectByWhNo(invMaterialReqBillAddParams.getOrgUnitNo(), invMaterialReqBillAddParams.getWareHouseNo(), param);
			if(scmInvWareHouse == null)
				throw new AppException("iscm.inventorymanage.warehouseoutbusiness.ScmInvMaterialReqBillBizImpl.error.warehouseisnull");
			scmInvMaterialReqBill.setWareHouseId(scmInvWareHouse.getId());
			Page costPage = new Page();
			costPage.setModelClass(OrgCostCenter2.class);
			costPage.setShowCount(Integer.MAX_VALUE);
			List<String> costArglist = new ArrayList<>();
			costArglist.add("type=to");
			costArglist.add("relationType="+OrgUnitRelationType.ADMINTOCOST);
			costArglist.add("fromOrgUnitNo="+invMaterialReqBillAddParams.getUseOrgUnitNo());
			List<OrgCostCenter2> costlist = orgCostCenterBiz.queryPage(costPage, costArglist, "queryPageEx", param);
			if(costlist==null || costlist.isEmpty())
				throw new AppException("iscm.inventorymanage.warehouseoutbusiness.ScmInvMaterialReqBillBizImpl.error.notadmintocost");
			scmInvMaterialReqBill.setCostOrgUnitNo(costlist.get(0).getOrgUnitNo());
			scmInvMaterialReqBillList.add(scmInvMaterialReqBill);
			bean.setList(scmInvMaterialReqBillList);
			List<InvMaterialReqBillDetailParams> detailList = invMaterialReqBillAddParams.getDetailList();
			if(detailList!=null && !detailList.isEmpty()){
				StringBuffer itemNos = new StringBuffer("");
				for(InvMaterialReqBillDetailParams detailParams:detailList){
					if(StringUtils.isBlank(detailParams.getItemNo())){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemisnull");
					}
					if(StringUtils.isNotBlank(itemNos.toString()))
						itemNos.append(",");
					itemNos.append("'").append(detailParams.getItemNo()).append("'");
				}
				Page page = new Page();
				page.setModelClass(ScmMaterial2.class);
				page.setShowCount(Integer.MAX_VALUE);
				page.setSqlCondition("ScmMaterial.itemNo in ("+itemNos.toString()+")");
				ArrayList argList = new ArrayList();
				if("1".equals(scmInvMaterialReqBill.getBizType())){
					argList.add("orgUnitNo="+scmInvMaterialReqBill.getOrgUnitNo());
					argList.add("vendorId=0");
			        argList.add("controlUnitNo=" + param.getControlUnitNo());
			        argList.add("wareHouseId="+scmInvMaterialReqBill.getWareHouseId());
			        argList.add("costCenter=0");
				}else{
					argList.add("orgUnitNo="+scmInvMaterialReqBill.getUseOrgUnitNo());
					argList.add("vendorId=0");
			        argList.add("controlUnitNo=" + param.getControlUnitNo());
			        argList.add("wareHouseId=0");
			        argList.add("costCenter=1");
				}
		        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "selectByStockPage", param);
		        List<ScmInvMaterialReqBillEntry2> scmInvMaterialRequestBillEntryList = new ArrayList<>();
		        //存储错误原因
		        List<ScmInvMaterialReqBillEntry2> msgList = new ArrayList<>();
				for(InvMaterialReqBillDetailParams detailParams:detailList){
					ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry = new ScmInvMaterialReqBillEntry2(true);
					BeanUtils.copyProperties(detailParams, scmInvMaterialReqBillEntry);
					boolean itemExist = false;
					
					HashMap<String,Object> map = new HashMap<String,Object>();
					map.put(
							ScmMaterial2.FN_ITEMNO,
							new QueryParam(ScmMaterial2.FN_ITEMNO, QueryParam.QUERY_EQ,
									scmInvMaterialReqBillEntry.getItemNo()));
					map.put(ScmMaterial2.FN_CONTROLUNITNO, new QueryParam(ScmMaterial2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
									param.getControlUnitNo()));
					List<ScmMaterial2> scmMaterialList2 = scmMaterialBiz.findAll(map, param);
					ScmMaterial2 scmMaterial2 = new ScmMaterial2();
					if(scmMaterialList2!=null && !scmMaterialList2.isEmpty()){
						scmMaterial2 = scmMaterialList2.get(0);
						itemExist = true;
						scmInvMaterialReqBillEntry.setItemId(scmMaterial2.getId());
						scmInvMaterialReqBillEntry.setPieUnit(scmMaterial2.getPieUnitId());
						scmInvMaterialReqBillEntry.setBaseUnit(scmMaterial2.getBaseUnitId());
						if(!scmInvMaterialReqBill.isPost()){
							if(scmMaterial2 != null && !"A".equals(scmMaterial2.getStatus())){
								scmInvMaterialReqBill.setPost(true);
						    }
						}
					} else {
						itemExist = false;
					}
					
					//库存有的物资
					if(scmMaterialList != null && !scmMaterialList.isEmpty()){
					for(ScmMaterial2 scmMaterial:scmMaterialList){
						if(StringUtils.equals(scmMaterial.getItemNo(), detailParams.getItemNo())){
							scmInvMaterialReqBillEntry.setItemId(scmMaterial.getId());
							scmInvMaterialReqBillEntry.setUnit(scmMaterial.getUnitId());
							scmInvMaterialReqBillEntry.setPieUnit(scmMaterial.getPieUnitId());
							scmInvMaterialReqBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
							scmInvMaterialReqBillEntry.setPrice(scmMaterial.getPrice());
							scmInvMaterialReqBillEntry.setAmt(scmInvMaterialReqBillEntry.getQty().multiply(scmMaterial.getPrice()));
							scmInvMaterialReqBillEntry.setTaxPrice(scmMaterial.getTaxPrice());
							scmInvMaterialReqBillEntry.setTaxAmt(scmInvMaterialReqBillEntry.getQty().multiply(scmMaterial.getTaxPrice()));
								
							if(StringUtils.isNotBlank(detailParams.getLot())){
								Page stockPage = new Page();
								stockPage.setModelClass(ScmInvStock2.class);
								stockPage.setShowCount(Integer.MAX_VALUE);
								stockPage.setSqlCondition("scminvstock.lot='"+detailParams.getLot()+"'");
								ArrayList stockArgList = new ArrayList();
								if("1".equals(scmInvMaterialReqBill.getBizType())){
									stockArgList.add("orgUnitNo="+scmInvMaterialReqBill.getOrgUnitNo());
									stockArgList.add("bizDate="+ FormatUtils.fmtDate(scmInvMaterialReqBill.getBizDate()));
									stockArgList.add("costCenter=0");
									stockArgList.add("wareHouseId="+scmInvMaterialReqBill.getWareHouseId());
									stockArgList.add("itemId=" + scmMaterial.getId());
								}else{
									stockArgList.add("orgUnitNo="+scmInvMaterialReqBill.getUseOrgUnitNo());
									stockArgList.add("bizDate="+ FormatUtils.fmtDate(scmInvMaterialReqBill.getBizDate()));
									stockArgList.add("costCenter=1");
									stockArgList.add("itemId=" + scmMaterial.getId());
								}
						        List<ScmInvStock2> scmInvStockList = scmInvStockBiz.queryPage(stockPage, stockArgList, "selectSaleIssueLot", param);
						        if(scmInvStockList != null && !scmInvStockList.isEmpty()){
						        	for (ScmInvStock2 scmInvStock : scmInvStockList) {
										if (detailParams.getLot().equals(scmInvStock.getLot())) {
											scmInvMaterialReqBillEntry.setPrice(scmInvStock.getPrice());
			                                scmInvMaterialReqBillEntry.setTaxPrice(scmInvStock.getTaxPrice());
			                                scmInvMaterialReqBillEntry.setAmt(scmInvStock.getPrice().multiply(scmInvMaterialReqBillEntry.getQty()));
			                                scmInvMaterialReqBillEntry.setTaxAmt(scmInvStock.getTaxPrice().multiply(scmInvMaterialReqBillEntry.getQty()));
			                                break;
										}
									}
						        }
							}
							break;
						}
					}
					}//库存有的物资
					
					if (scmInvMaterialReqBillEntry.getUnit() == 0) {
						ScmMaterialInventory2 scmMaterialInventory = scmMaterialInventoryBiz.selectByItemIdAndOrgUnitNo(scmInvMaterialReqBillEntry.getItemId(), param.getControlUnitNo(),param);
						if (scmMaterialInventory == null) {
							scmMaterialInventory = scmMaterialInventoryBiz.selectByItemIdAndOrgUnitNo(scmInvMaterialReqBillEntry.getItemId(), scmInvMaterialReqBillEntry.getOrgUnitNo(), param);
							if (scmMaterialInventory != null) {
								scmInvMaterialReqBillEntry.setUnit(scmMaterialInventory.getUnitId());
							}
 						} else {
							scmInvMaterialReqBillEntry.setUnit(scmMaterialInventory.getUnitId());
						}
					}
					
					ScmInvMaterialReqBillEntry2 msg = new ScmInvMaterialReqBillEntry2(true);
					msg.setLineId(scmInvMaterialReqBillEntry.getLineId());
					msg.setItemNo(detailParams.getItemNo());
					if(itemExist){
					BigDecimal convRate = ScmMaterialUtil.getConvRate(scmInvMaterialReqBillEntry.getItemId(), scmInvMaterialReqBillEntry.getUnit(), param);
					scmInvMaterialReqBillEntry.setBaseQty(scmInvMaterialReqBillEntry.getQty().multiply(convRate));
					scmInvMaterialRequestBillEntryList.add(scmInvMaterialReqBillEntry);
						msg.setResultCode("0");
						msg.setResultText("");
					}else{
						msg.setResultCode("-1");
						msg.setResultText(Message.getMessage(param.getLang(), "iscm.inventorymanage.scmInvMaterialReqBill.esp.itemNotExist", new String[] {detailParams.getItemNo()}));
				}
					msgList.add(msg);
				}
				scmInvMaterialReqBill.setScmInvMaterialReqBillEntryList(msgList);
				if(scmInvMaterialRequestBillEntryList == null || scmInvMaterialRequestBillEntryList.isEmpty()){
					throw new AppException("iscm.inventorymanage.scmInvMaterialReqBill.esp.itemAllNotExist");
				}
				bean.setList2(scmInvMaterialRequestBillEntryList);
			}
			this.add(bean, param);
		} catch (Exception e) {
			throw e;
		}
		return scmInvMaterialReqBill;
	}
	
	@Override
	public ScmInvMaterialReqBill2 doEditMaterialReqBill(InvMaterialReqBillEditParams invMaterialReqBillEditParams, Param param)
			throws AppException {
		if(invMaterialReqBillEditParams!=null){
			CommonBean bean = new CommonBean();
			Page page=new Page();
			page.setModelClass(ScmInvMaterialReqBill2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmInvMaterialReqBill2.FN_OTNO,new QueryParam(ScmInvMaterialReqBill2.FN_OTNO, QueryParam.QUERY_EQ,invMaterialReqBillEditParams.getOtNo()));
			List<ScmInvMaterialReqBill2> scmInvMaterialReqBillList =this.findPage(page, param);
			if(scmInvMaterialReqBillList!=null && !scmInvMaterialReqBillList.isEmpty()){
				//更新主表数据
				ScmInvMaterialReqBill2 scmInvMaterialReqBill = scmInvMaterialReqBillList.get(0);
				BeanUtils.copyProperties(invMaterialReqBillEditParams, scmInvMaterialReqBill);
				if(scmInvMaterialReqBill.getBizDate() != null){
					scmInvMaterialReqBill.setBizDate(FormatUtils.parseDate(FormatUtils.fmtDate(scmInvMaterialReqBill.getBizDate())));
				}
				if(StringUtils.isNotBlank(invMaterialReqBillEditParams.getInvOrgUnitNo())){
					scmInvMaterialReqBill.setOrgUnitNo(invMaterialReqBillEditParams.getInvOrgUnitNo());
				}
				if (StringUtils.isNotBlank(invMaterialReqBillEditParams.getRequestPerson())) {
					scmInvMaterialReqBill.setUsePerson(invMaterialReqBillEditParams.getRequestPerson());
				}
				scmInvMaterialReqBill.setEditDate(CalendarUtil.getDate(param));
				scmInvMaterialReqBill.setEditor(param.getUsrCode());
				if(StringUtils.isNotBlank(invMaterialReqBillEditParams.getWareHouseNo())) {
					ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectByWhNo(scmInvMaterialReqBill.getOrgUnitNo(), invMaterialReqBillEditParams.getWareHouseNo(), param);
					if(scmInvWareHouse!=null)
						scmInvMaterialReqBill.setWareHouseId(scmInvWareHouse.getId());
				}
				if(StringUtils.isNotBlank(invMaterialReqBillEditParams.getUseDepNo())) {
					Page costPage = new Page();
					costPage.setModelClass(OrgCostCenter2.class);
					costPage.setShowCount(Integer.MAX_VALUE);
					List<String> costArglist = new ArrayList<>();
					costArglist.add("type=to");
					costArglist.add("relationType="+OrgUnitRelationType.ADMINTOCOST);
					costArglist.add("fromOrgUnitNo="+invMaterialReqBillEditParams.getUseDepNo());
					List<OrgCostCenter2> costlist = orgCostCenterBiz.queryPage(costPage, costArglist, "queryPageEx", param);
					if(costlist!=null && !costlist.isEmpty()){
						scmInvMaterialReqBill.setCostOrgUnitNo(costlist.get(0).getOrgUnitNo());
					}
				}
				bean.setList(scmInvMaterialReqBillList);
				List<InvMaterialReqBillDetailParams> detailList = invMaterialReqBillEditParams.getDetailList();
				List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.selectByOtId(scmInvMaterialReqBill.getOtId(), param);
				if(scmInvMaterialReqBillEntryList==null || scmInvMaterialReqBillEntryList.isEmpty()){
					scmInvMaterialReqBillEntryList = new ArrayList<>();
					if(detailList!=null && !detailList.isEmpty()){
						StringBuffer itemNos = new StringBuffer("");
						for(InvMaterialReqBillDetailParams detailParams:detailList){
							if(StringUtils.isBlank(detailParams.getItemNo())){
								throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemisnull");
							}
							if(StringUtils.isNotBlank(itemNos.toString()))
								itemNos.append(",");
							itemNos.append("'").append(detailParams.getItemNo()).append("'");
						}
						Page itemPage = new Page();
						itemPage.setModelClass(ScmMaterial2.class);
						itemPage.setShowCount(Integer.MAX_VALUE);
						itemPage.setSqlCondition("ScmMaterial.itemNo in ("+itemNos.toString()+")");
						ArrayList argList = new ArrayList();
						if("1".equals(scmInvMaterialReqBill.getBizType())){
							argList.add("orgUnitNo="+scmInvMaterialReqBill.getOrgUnitNo());
							argList.add("vendorId=0");
					        argList.add("controlUnitNo=" + param.getControlUnitNo());
					        argList.add("wareHouseId="+scmInvMaterialReqBill.getWareHouseId());
					        argList.add("costCenter=0");
						}else{
							argList.add("orgUnitNo="+scmInvMaterialReqBill.getUseOrgUnitNo());
							argList.add("vendorId=0");
					        argList.add("controlUnitNo=" + param.getControlUnitNo());
					        argList.add("wareHouseId=0");
					        argList.add("costCenter=1");
						}
				        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(itemPage, argList, "selectByStockPage", param);
				        //存储错误原因
				        List<ScmInvMaterialReqBillEntry2> msgList = new ArrayList<>();
				        
						for(InvMaterialReqBillDetailParams detailParams:detailList){
							ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry = new ScmInvMaterialReqBillEntry2(true);
							BeanUtils.copyProperties(detailParams, scmInvMaterialReqBillEntry);
							boolean itemExist = false;
							
							HashMap<String,Object> map = new HashMap<String,Object>();
							map.put(
									ScmMaterial2.FN_ITEMNO,
									new QueryParam(ScmMaterial2.FN_ITEMNO, QueryParam.QUERY_EQ,
											scmInvMaterialReqBillEntry.getItemNo()));
							map.put(ScmMaterial2.FN_CONTROLUNITNO, new QueryParam(ScmMaterial2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
											param.getControlUnitNo()));
							List<ScmMaterial2> scmMaterialList2 = scmMaterialBiz.findAll(map, param);
							ScmMaterial2 scmMaterial2 = new ScmMaterial2();
							if(scmMaterialList2!=null && !scmMaterialList2.isEmpty()){
								scmMaterial2 = scmMaterialList2.get(0);
								itemExist = true;
								BeanUtils.copyProperties(detailParams, scmInvMaterialReqBillEntry);
								scmInvMaterialReqBillEntry.setItemId(scmMaterial2.getId());
								scmInvMaterialReqBillEntry.setPieUnit(scmMaterial2.getPieUnitId());
								scmInvMaterialReqBillEntry.setBaseUnit(scmMaterial2.getBaseUnitId());
								if(!scmInvMaterialReqBill.isPost()){
									if(scmMaterial2 != null && !"A".equals(scmMaterial2.getStatus())){
										scmInvMaterialReqBill.setPost(true);
								    }
								}
							} else {
								itemExist = false;
							}
							
							if(scmMaterialList != null && !scmMaterialList.isEmpty()){
								for(ScmMaterial2 scmMaterial:scmMaterialList){
									if(StringUtils.equals(scmMaterial.getItemNo(), detailParams.getItemNo())){
										itemExist = true;
										scmInvMaterialReqBillEntry.setItemId(scmMaterial.getId());
										scmInvMaterialReqBillEntry.setUnit(scmMaterial.getUnitId());
										scmInvMaterialReqBillEntry.setPieUnit(scmMaterial.getPieUnitId());
										scmInvMaterialReqBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
										scmInvMaterialReqBillEntry.setPrice(scmMaterial.getPrice());
										scmInvMaterialReqBillEntry.setAmt(scmInvMaterialReqBillEntry.getQty().multiply(scmMaterial.getPrice()));
										scmInvMaterialReqBillEntry.setTaxPrice(scmMaterial.getTaxPrice());
										scmInvMaterialReqBillEntry.setTaxAmt(scmInvMaterialReqBillEntry.getQty().multiply(scmMaterial.getTaxPrice()));
										if(StringUtils.isNotBlank(detailParams.getLot())){
											Page stockPage = new Page();
											stockPage.setModelClass(ScmInvStock2.class);
											stockPage.setShowCount(Integer.MAX_VALUE);
											stockPage.setSqlCondition("scminvstock.lot='"+detailParams.getLot()+"'");
											ArrayList stockArgList = new ArrayList();
											if("1".equals(scmInvMaterialReqBill.getBizType())){
												stockArgList.add("orgUnitNo="+scmInvMaterialReqBill.getOrgUnitNo());
												stockArgList.add("bizDate="+ FormatUtils.fmtDate(scmInvMaterialReqBill.getBizDate()));
												stockArgList.add("costCenter=0");
												stockArgList.add("wareHouseId="+scmInvMaterialReqBill.getWareHouseId());
												stockArgList.add("itemId=" + scmMaterial.getId());
											}else{
												stockArgList.add("orgUnitNo="+scmInvMaterialReqBill.getUseOrgUnitNo());
												stockArgList.add("bizDate="+ FormatUtils.fmtDate(scmInvMaterialReqBill.getBizDate()));
												stockArgList.add("costCenter=1");
												stockArgList.add("itemId=" + scmMaterial.getId());
											}
									        List<ScmInvStock2> scmInvStockList = scmInvStockBiz.queryPage(stockPage, stockArgList, "selectSaleIssueLot", param);
									        if(scmInvStockList != null && !scmInvStockList.isEmpty()){
									        	for (ScmInvStock2 scmInvStock : scmInvStockList) {
													if (detailParams.getLot().equals(scmInvStock.getLot())) {
														scmInvMaterialReqBillEntry.setPrice(scmInvStock.getPrice());
						                                scmInvMaterialReqBillEntry.setTaxPrice(scmInvStock.getTaxPrice());
						                                scmInvMaterialReqBillEntry.setAmt(scmInvStock.getPrice().multiply(scmInvMaterialReqBillEntry.getQty()));
						                                scmInvMaterialReqBillEntry.setTaxAmt(scmInvStock.getTaxPrice().multiply(scmInvMaterialReqBillEntry.getQty()));
						                                break;
													}
												}
									        }
										}
										break;
									}
								}
							}
							
							if (scmInvMaterialReqBillEntry.getUnit() == 0) {
								ScmMaterialInventory2 scmMaterialInventory = scmMaterialInventoryBiz.selectByItemIdAndOrgUnitNo(scmInvMaterialReqBillEntry.getItemId(), param.getControlUnitNo(),param);
								if (scmMaterialInventory == null) {
									scmMaterialInventory = scmMaterialInventoryBiz.selectByItemIdAndOrgUnitNo(scmInvMaterialReqBillEntry.getItemId(), scmInvMaterialReqBillEntry.getOrgUnitNo(), param);
									if (scmMaterialInventory != null) {
										scmInvMaterialReqBillEntry.setUnit(scmMaterialInventory.getUnitId());
									}
		 						} else {
									scmInvMaterialReqBillEntry.setUnit(scmMaterialInventory.getUnitId());
								}
							}
							
							ScmInvMaterialReqBillEntry2 msg = new ScmInvMaterialReqBillEntry2(true);
							msg.setLineId(scmInvMaterialReqBillEntry.getLineId());
							msg.setItemNo(detailParams.getItemNo());
							if(itemExist){
								BigDecimal convRate = ScmMaterialUtil.getConvRate(scmInvMaterialReqBillEntry.getItemId(), scmInvMaterialReqBillEntry.getUnit(), param);
								scmInvMaterialReqBillEntry.setBaseQty(scmInvMaterialReqBillEntry.getQty().multiply(convRate));
								scmInvMaterialReqBillEntryList.add(scmInvMaterialReqBillEntry);
								msg.setResultCode("0");
								msg.setResultText("");
							}else{
								msg.setResultCode("-1");
								msg.setResultText(Message.getMessage(param.getLang(), "iscm.inventorymanage.scmInvMaterialReqBill.esp.itemNotExist", new String[] {detailParams.getItemNo()}));
							}
							msgList.add(msg);
						}
						scmInvMaterialReqBill.setScmInvMaterialReqBillEntryList(msgList);
					}
				}else{
					//先删除不存在行的记录
					for(int i = scmInvMaterialReqBillEntryList.size()-1;i>=0;i--){
						ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry2 = scmInvMaterialReqBillEntryList.get(i);
						boolean exists = false;
						for(InvMaterialReqBillDetailParams detailParams:detailList){
							if(detailParams.getLineId()==scmInvMaterialReqBillEntry2.getLineId()){
								exists = true;
								break;
							}
						}
						if(!exists)
							scmInvMaterialReqBillEntryList.remove(i);
					}
					StringBuffer itemNos = new StringBuffer("");
					for(InvMaterialReqBillDetailParams detailParams:detailList){
						if(StringUtils.isBlank(detailParams.getItemNo())){
							throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemisnull");
						}
						if(StringUtils.isNotBlank(itemNos.toString()))
							itemNos.append(",");
						itemNos.append("'").append(detailParams.getItemNo()).append("'");
					}
					Page itemPage = new Page();
					itemPage.setModelClass(ScmMaterial2.class);
					itemPage.setShowCount(Integer.MAX_VALUE);
					itemPage.setSqlCondition("ScmMaterial.itemNo in ("+itemNos.toString()+")");
					ArrayList argList = new ArrayList();
					if("1".equals(scmInvMaterialReqBill.getBizType())){
						argList.add("orgUnitNo="+scmInvMaterialReqBill.getOrgUnitNo());
						argList.add("vendorId=0");
				        argList.add("controlUnitNo=" + param.getControlUnitNo());
				        argList.add("wareHouseId="+scmInvMaterialReqBill.getWareHouseId());
				        argList.add("costCenter=0");
					}else{
						argList.add("orgUnitNo="+scmInvMaterialReqBill.getUseOrgUnitNo());
						argList.add("vendorId=0");
				        argList.add("controlUnitNo=" + param.getControlUnitNo());
				        argList.add("wareHouseId=0");
				        argList.add("costCenter=1");
					}
			        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(itemPage, argList, "selectByStockPage", param);
					//更新时是根据行号进行更新的
					int lineId = 0;
					
					List<ScmInvMaterialReqBillEntry2> msgList = new ArrayList<>();
					for(InvMaterialReqBillDetailParams detailParams:detailList){
						ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry = new ScmInvMaterialReqBillEntry2(true);
						boolean itemExist = false;
						boolean exists = false;
						
						for(ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry2:scmInvMaterialReqBillEntryList){
							if(detailParams.getLineId()==scmInvMaterialReqBillEntry2.getLineId() && detailParams.getLineId()!=0){
								scmInvMaterialReqBillEntry = scmInvMaterialReqBillEntry2;
								exists = true;
								break;
							}
						}
						
						HashMap<String,Object> map = new HashMap<String,Object>();
						map.put(
								ScmMaterial2.FN_ITEMNO,
								new QueryParam(ScmMaterial2.FN_ITEMNO, QueryParam.QUERY_EQ,
										detailParams.getItemNo()));
						map.put(ScmMaterial2.FN_CONTROLUNITNO, new QueryParam(ScmMaterial2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
										param.getControlUnitNo()));
						List<ScmMaterial2> scmMaterialList2 = scmMaterialBiz.findAll(map, param);
						ScmMaterial2 scmMaterial2 = new ScmMaterial2();
						if(scmMaterialList2!=null && !scmMaterialList2.isEmpty()){
							itemExist = true;
							scmMaterial2 = scmMaterialList2.get(0);
							BeanUtils.copyProperties(detailParams, scmInvMaterialReqBillEntry);
							scmInvMaterialReqBillEntry.setItemId(scmMaterial2.getId());
							scmInvMaterialReqBillEntry.setPieUnit(scmMaterial2.getPieUnitId());
							scmInvMaterialReqBillEntry.setBaseUnit(scmMaterial2.getBaseUnitId());
							if(!scmInvMaterialReqBill.isPost()){
								if(scmMaterial2 != null && !"A".equals(scmMaterial2.getStatus())){
									scmInvMaterialReqBill.setPost(true);
							    }
							}
						} else {
							itemExist = false;
						}
						
						if(scmMaterialList != null && !scmMaterialList.isEmpty()){
							for(ScmMaterial2 scmMaterial:scmMaterialList){
								if(StringUtils.equals(scmMaterial.getItemNo(), detailParams.getItemNo())){
									scmInvMaterialReqBillEntry.setItemId(scmMaterial.getId());
									scmInvMaterialReqBillEntry.setUnit(scmMaterial.getUnitId());
									scmInvMaterialReqBillEntry.setPieUnit(scmMaterial.getPieUnitId());
									scmInvMaterialReqBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
									scmInvMaterialReqBillEntry.setPrice(scmMaterial.getPrice());
									scmInvMaterialReqBillEntry.setAmt(scmInvMaterialReqBillEntry.getQty().multiply(scmMaterial.getPrice()));
									scmInvMaterialReqBillEntry.setTaxPrice(scmMaterial.getTaxPrice());
									scmInvMaterialReqBillEntry.setTaxAmt(scmInvMaterialReqBillEntry.getQty().multiply(scmMaterial.getTaxPrice()));
									if(StringUtils.isNotBlank(detailParams.getLot())){
										Page stockPage = new Page();
										stockPage.setModelClass(ScmInvStock2.class);
										stockPage.setShowCount(Integer.MAX_VALUE);
										stockPage.setSqlCondition("scminvstock.lot='"+detailParams.getLot()+"'");
										ArrayList stockArgList = new ArrayList();
										if("1".equals(scmInvMaterialReqBill.getBizType())){
											stockArgList.add("orgUnitNo="+scmInvMaterialReqBill.getOrgUnitNo());
											stockArgList.add("bizDate="+ FormatUtils.fmtDate(scmInvMaterialReqBill.getBizDate()));
											stockArgList.add("costCenter=0");
											stockArgList.add("wareHouseId="+scmInvMaterialReqBill.getWareHouseId());
											stockArgList.add("itemId=" + scmMaterial.getId());
										}else{
											stockArgList.add("orgUnitNo="+scmInvMaterialReqBill.getUseOrgUnitNo());
											stockArgList.add("bizDate="+ FormatUtils.fmtDate(scmInvMaterialReqBill.getBizDate()));
											stockArgList.add("costCenter=1");
											stockArgList.add("itemId=" + scmMaterial.getId());
										}
								        List<ScmInvStock2> scmInvStockList = scmInvStockBiz.queryPage(stockPage, stockArgList, "selectSaleIssueLot", param);
								        if(scmInvStockList != null && !scmInvStockList.isEmpty()){
								        	for (ScmInvStock2 scmInvStock : scmInvStockList) {
												if (detailParams.getLot().equals(scmInvStock.getLot())) {
													scmInvMaterialReqBillEntry.setPrice(scmInvStock.getPrice());
					                                scmInvMaterialReqBillEntry.setTaxPrice(scmInvStock.getTaxPrice());
					                                scmInvMaterialReqBillEntry.setAmt(scmInvStock.getPrice().multiply(scmInvMaterialReqBillEntry.getQty()));
					                                scmInvMaterialReqBillEntry.setTaxAmt(scmInvStock.getTaxPrice().multiply(scmInvMaterialReqBillEntry.getQty()));
					                                break;
												}
											}
								        }
									}
									break;
								}
							}
						}
						
						if (scmInvMaterialReqBillEntry.getUnit() == 0) {
							ScmMaterialInventory2 scmMaterialInventory = scmMaterialInventoryBiz.selectByItemIdAndOrgUnitNo(scmInvMaterialReqBillEntry.getItemId(), param.getControlUnitNo(),param);
							if (scmMaterialInventory == null) {
								scmMaterialInventory = scmMaterialInventoryBiz.selectByItemIdAndOrgUnitNo(scmInvMaterialReqBillEntry.getItemId(), scmInvMaterialReqBillEntry.getOrgUnitNo(), param);
								if (scmMaterialInventory != null) {
									scmInvMaterialReqBillEntry.setUnit(scmMaterialInventory.getUnitId());
								}
	 						} else {
								scmInvMaterialReqBillEntry.setUnit(scmMaterialInventory.getUnitId());
							}
						}
						
						BigDecimal convRate = ScmMaterialUtil.getConvRate(scmInvMaterialReqBillEntry.getItemId(), scmInvMaterialReqBillEntry.getUnit(), param);
						scmInvMaterialReqBillEntry.setBaseQty(scmInvMaterialReqBillEntry.getQty().multiply(convRate));
						if(!exists && itemExist)
							scmInvMaterialReqBillEntryList.add(lineId, scmInvMaterialReqBillEntry);
						
						ScmInvMaterialReqBillEntry2 msg = new ScmInvMaterialReqBillEntry2(true);
						msg.setLineId(scmInvMaterialReqBillEntry.getLineId());
						msg.setItemNo(detailParams.getItemNo());
						if(itemExist){
							lineId = lineId +1;
							msg.setResultCode("0");
							msg.setResultText("");
						}else{
							if (exists) {
								scmInvMaterialReqBillEntryList.remove(detailParams.getLineId() - 1);
							}
							msg.setResultCode("-1");
							msg.setResultText(Message.getMessage(param.getLang(), "iscm.inventorymanage.scmInvMaterialReqBill.esp.itemNotExist", new String[] {detailParams.getItemNo()}));
						}
						msgList.add(msg);
					}
					scmInvMaterialReqBill.setScmInvMaterialReqBillEntryList(msgList);
				}
				if (scmInvMaterialReqBillEntryList != null && !scmInvMaterialReqBillEntryList.isEmpty()) {
					for(int i = 1 ; i < scmInvMaterialReqBillEntryList.size(); i ++) {
						scmInvMaterialReqBillEntryList.get(i).setLineId(i+1);
					}
					//更新子表数据
					bean.setList2(scmInvMaterialReqBillEntryList);
					this.update(bean, param);
					return scmInvMaterialReqBill;
				} else {
//					this.delete(scmInvMaterialReqBill, param);
					return scmInvMaterialReqBill;
				}
			}else{
				throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
			}
		}else{
			throw new AppException("webservice.params.null");
		}
	}

	@Override
	public ScmInvMaterialReqBill2 queryInvMaterialReqBill(
			ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param)
			throws AppException {
		Page page=new Page();
		page.setModelClass(ScmInvMaterialReqBill2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmInvMaterialReqBill2.FN_OTNO,new QueryParam(ScmInvMaterialReqBill2.FN_OTNO, QueryParam.QUERY_EQ, scmInvMaterialReqBill.getOtNo()));
		
		List<ScmInvMaterialReqBill2> scmInvMaterialReqBillList =this.findPage(page, param);
		ScmInvMaterialReqBill2 scmInvMaterialReqBill2 = new ScmInvMaterialReqBill2();
		if(scmInvMaterialReqBillList!=null && !scmInvMaterialReqBillList.isEmpty()){
			scmInvMaterialReqBill2 = scmInvMaterialReqBillList.get(0);
			scmInvMaterialReqBill2.setScmInvMaterialReqBillEntryList(scmInvMaterialReqBillEntryBiz.selectByOtId(scmInvMaterialReqBill2.getOtId(), param));
			
			//增加待审人
			ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmInvMaterialReqBill2.getOtId(), "InvMatReqout",param);
			if (scmBillPendingUsr != null) {
				scmInvMaterialReqBill2.setPendingUsr(scmBillPendingUsr.getUsrCodes());
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmInvMaterialReqBill2;
	}

	@Override
	public ScmInvMaterialReqBill2 doAuditInvMaterialReqBill(
			CommonAuditParams commonAuditParams, Param param)
			throws AppException {
		ScmInvMaterialReqBill2 invMaterialReq = null;
		
		ScmInvMaterialReqBill2 scmInvMaterialReqBill= new ScmInvMaterialReqBill2();
		scmInvMaterialReqBill.setOtId(commonAuditParams.getBillId());
		scmInvMaterialReqBill.setOtNo(commonAuditParams.getBillNo());
		String opinion = commonAuditParams.getOpinion();
		if(StringUtils.equals("Y", opinion) || StringUtils.equals("agree", opinion)) {
			opinion="agree";
		}else if(StringUtils.equals("N", opinion) || StringUtils.equals("refuse", opinion)) {
			opinion="refuse";
		}
		if(scmInvMaterialReqBill.getOtId()>0){
			invMaterialReq = this.selectDirect(scmInvMaterialReqBill.getOtId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmInvMaterialReqBill2.FN_OTNO,
					new QueryParam(ScmInvMaterialReqBill2.FN_OTNO, QueryParam.QUERY_EQ,
							scmInvMaterialReqBill.getOtNo()));
			map.put(ScmInvMaterialReqBill2.FN_CONTROLUNITNO, new QueryParam(ScmInvMaterialReqBill2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
							param.getControlUnitNo()));
			List<ScmInvMaterialReqBill2> scmInvMaterialReqList =this.findAll(map, param);
			
			if(scmInvMaterialReqList!=null && !scmInvMaterialReqList.isEmpty()){
				invMaterialReq = scmInvMaterialReqList.get(0);
			}
		}
		
		if (invMaterialReq != null) {
			List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqEntryList = scmInvMaterialReqBillEntryBiz.selectByOtId(invMaterialReq.getOtId(), param);
			if(scmInvMaterialReqEntryList==null || scmInvMaterialReqEntryList.isEmpty()) {
				throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
			}
			this.setConvertMap(invMaterialReq, param);
			BillType2 billType = billTypeBiz.selectByOrgAndCode(invMaterialReq.getFinOrgUnitNo(), "InvMatReqout", param);
			if(!(invMaterialReq.getStatus().equals("D") || invMaterialReq.getStatus().equals("P")) && billType.isNeedAudit()){
				throw new AppException("iscm.purchasemanage.error.audit");
			}
			if(StringUtils.equals("R", opinion)) {
				scmBillPendingBiz.deletePendingBill(param.getUsrCode(),invMaterialReq, param);
				commonEventHistoryBiz.updateInvalid(invMaterialReq,"",param);
				
				CommonAuditOpinion commonAuditOpinionR = new CommonAuditOpinion();
				commonAuditOpinionR.setPreStepNo(invMaterialReq.getStepNo());
				String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
				commonAuditOpinionR.setStepNo(stepNo);
				commonAuditOpinionR.setActiveType("A");
				commonAuditOpinionR.setOpinion(commonAuditParams.getOpinion());
				commonAuditOpinionR.setRemarks(commonAuditParams.getOpinionRemarks());
				commonEventHistoryBiz.addEventHistory(invMaterialReq, commonAuditOpinionR, param);
				
				if(billType.isNeedAudit()) {
					//驳回则将单据变回新单状态
					invMaterialReq.setStatus("I");
					invMaterialReq.setChecker(null);
					invMaterialReq.setCheckDate(null);
					return this.updateDirect(invMaterialReq, param);
				}else {
					//不需要审批时驳回需模拟反过账、反提交
					if(StringUtils.equals("E",invMaterialReq.getStatus())) {
						invMaterialReq = this.cancelPostBill(invMaterialReq, param);
					}
					if(StringUtils.equals("A",invMaterialReq.getStatus())) {
						this.doUnSubmitInvMaterialReq(invMaterialReq,param);
					}
					invMaterialReq.setStatus("I");
					invMaterialReq.setChecker(null);
					invMaterialReq.setCheckDate(null);
					return this.updateDirect(invMaterialReq, param);
				}
			}
			String processInstanceId = invMaterialReq.getWorkFlowNo();
			boolean isCompleteTask = true;
			
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//完成审批任务
				ActivityUtil.completeTaskByAssigneeAndOpinion(processInstanceId, param.getUsrCode(), opinion, param);
				ActivityUtil activityUtil = new ActivityUtil();
				//判断当前流程是否结束
				isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
			} else {
				//流程编号不存在
				throw new AppException("com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.doAuditPurRequire.processInstanceId");
			}
			
			//启用审批，或启用工作流（对于历史数据的处理）
			if(billType.isNeedAudit() || (billType.isUseFlow() && StringUtils.contains("D,P", invMaterialReq.getStatus()))) {
				//P：审核中，A：通过，N：未通过
				if ("agree".equals(opinion)) {
					if (isCompleteTask) {
						invMaterialReq.setStatus("A");
					} else {
						invMaterialReq.setStatus("P");
					}
				} else {
					invMaterialReq.setStatus("N");
				}
			}  else {
				if (billType.isUseFlow()) {
					if (StringUtils.equals("refuse", opinion)) {
						//审批拒绝
						if(StringUtils.equals("E",invMaterialReq.getStatus())) {
							invMaterialReq = this.cancelPostBill(invMaterialReq, param);
			}
						invMaterialReq.setStatus("N");
					}
					
				}
			}
			scmBillPendingBiz.updatePendingBill(param.getUsrCode(), invMaterialReq, param);
			invMaterialReq.setChecker(param.getUsrCode());
			if ("agree".equals(opinion)) {
				this.sendAuditMsg(invMaterialReq, billType.getBillCode(), param);
			}
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setPreStepNo(invMaterialReq.getStepNo());
			String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
			try {
				invMaterialReq.setStepNo(stepNo);
				invMaterialReq.setCheckDate(CalendarUtil.getDate(param));
				this.updateStatus(invMaterialReq, param);
			} catch (Exception e) {
				throw e;
			}
			commonAuditOpinion.setStepNo(stepNo);
			commonAuditOpinion.setActiveType("A");
			commonAuditOpinion.setOpinion(commonAuditParams.getOpinion());
			commonAuditOpinion.setRemarks(commonAuditParams.getOpinionRemarks());
			commonEventHistoryBiz.addEventHistory(invMaterialReq, commonAuditOpinion, param);
			SystemState systemState = systemStateBiz.selectBySystemId(invMaterialReq.getFinOrgUnitNo(), 170, param);
			if(systemState==null){
				throw new AppException("com.armitage.iars.daily.util.disenable.unable");
			}
			if (systemState.getCurrentPeriodId() == invMaterialReq.getPeriodId()) {
			if(StringUtils.equals("A", invMaterialReq.getStatus())) {
				//通过或部分通过时检查是否自动过帐
				if(billType!=null && billType.isAutoRelease()) {
					List<String> msgList = this.postBillCheck(invMaterialReq, param);
					if (msgList != null && !msgList.isEmpty()) {
						StringBuilder detailInfo = new StringBuilder("");
                        detailInfo.append(Message.getMessage(param.getLang(),"iscm.inventorymanage.scminvmaterialreqbill.post.errorTitle"));
	
						for (String str : msgList) {
                            detailInfo.append(str).append("\n");
                        }
						
						throw new AppException(detailInfo.toString(), new String[]{invMaterialReq.getOtNo()});
					}
					this.postBill(invMaterialReq, param);
				}
			}
			}
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return invMaterialReq;
	}

	@Override
	public ScmInvMaterialReqBill2 doUnAuditInvMaterialReqBill(
			ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param)
			throws AppException {
		ScmInvMaterialReqBill2 invMaterialReq = null;
		List<ScmInvMaterialReqBill2> scmInvMaterialReqList = new ArrayList<> ();
		List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqEntryList = new ArrayList<> ();
		boolean isFirstTask = false;
		
		if(scmInvMaterialReqBill.getOtId()>0){
			invMaterialReq = this.selectDirect(scmInvMaterialReqBill.getOtId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmInvMaterialReqBill2.FN_OTNO,
					new QueryParam(ScmInvMaterialReqBill2.FN_OTNO, QueryParam.QUERY_EQ,
							scmInvMaterialReqBill.getOtNo()));
			map.put(ScmInvMaterialReqBill2.FN_CONTROLUNITNO, new QueryParam(ScmInvMaterialReqBill2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
					param.getControlUnitNo()));
			
			scmInvMaterialReqList =this.findAll(map, param);
			if(scmInvMaterialReqList!=null && !scmInvMaterialReqList.isEmpty()){
				invMaterialReq=scmInvMaterialReqList.get(0);
			}
		}
		
		if (invMaterialReq != null) {
			BillType2 billType = billTypeBiz.selectByOrgAndCode(invMaterialReq.getFinOrgUnitNo(), "InvMatReqout", param);
			if(!StringUtils.contains("A,B,N,P", invMaterialReq.getStatus()) && billType.isNeedAudit()){
				throw new AppException("iscm.purchasemanage.unaudit.error.status");
			}
			if(!StringUtils.contains("A,B,N,P,E", invMaterialReq.getStatus()) && billType.isUseFlow()){
				throw new AppException("iscm.purchasemanage.unaudit.error.status");
			}
			String processInstanceId = invMaterialReq.getWorkFlowNo();
			scmInvMaterialReqEntryList = scmInvMaterialReqBillEntryBiz.selectByOtId(invMaterialReq.getOtId(), param);
			if(scmInvMaterialReqEntryList == null || scmInvMaterialReqEntryList.isEmpty()){
				//抛出异常处理
				throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
			}
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//反审批当前已审节点
				CommonBean bean = new CommonBean();
				scmInvMaterialReqList.add(invMaterialReq);
				bean.setList(scmInvMaterialReqList);
				bean.setList2(scmInvMaterialReqEntryList);
				
				long billTypeId = 0;
				if (billType!=null && billType.isNeedAudit()) {
					billTypeId = billType.getId();
				}
				
				processInstanceId = ActivityUtil.antiAuditProcess(bean, billTypeId, processInstanceId, param.getUsrCode(),  param);
				isFirstTask = ActivityUtil.isFirstTask(processInstanceId, param.getUsrCode(), param);
				invMaterialReq.setWorkFlowNo(processInstanceId);
			} else {
				//流程编号不存在
				throw new AppException("com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.doAuditPurRequire.processInstanceId");
			}
			
			//启用审批，或启用工作流（对于历史数据的处理）
			if(billType.isNeedAudit() || (billType.isUseFlow() && StringUtils.contains("N,D,P", invMaterialReq.getStatus()))) {
				String status = "";
				if (isFirstTask) {
					status = "D";
				} else {
					status = "P";
				}
				invMaterialReq.setStatus(status);
				String tableName = ClassUtils.getFinalModelSimpleName(invMaterialReq);
				CommonEventHistory commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,invMaterialReq.getStepNo(),invMaterialReq.getPK(),param);
				if(commonEventHistory!=null && StringUtils.isNotBlank(commonEventHistory.getPreStepNo())) {
					commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,commonEventHistory.getPreStepNo(),invMaterialReq.getPK(),param);
				}
				if(commonEventHistory!=null) {
					invMaterialReq.setChecker(commonEventHistory.getOper());
					invMaterialReq.setCheckDate(commonEventHistory.getOperDate());
				}else {
					invMaterialReq.setChecker(null);
					invMaterialReq.setCheckDate(null);
				}
				this.updateStatus(invMaterialReq, param);
			} else {
				if (billType.isUseFlow()) {
					//启用审批时订单的状态已更新为审批中时，再启用工作流，反审到首审批节点时，应调整为待审状态
					if (isFirstTask) {
						if (StringUtils.equals("P", invMaterialReq.getStatus())) {
							invMaterialReq.setStatus("D");
			}
					}
					this.updateStatus(invMaterialReq, param);
				}
			}
			scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),invMaterialReq, param);
			commonEventHistoryBiz.updateInvalid(invMaterialReq,invMaterialReq.getStepNo(),param);
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setStepNo(invMaterialReq.getStepNo());
			commonAuditOpinion.setActiveType("U");
			commonAuditOpinion.setOpinion("Y");
			commonEventHistoryBiz.addEventHistory(invMaterialReq, commonAuditOpinion, param);

		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
			
		return invMaterialReq;
	}

	@Override
	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map) {
		return ((ScmInvMaterialReqBillDAO) dao).countUnPostBill(map);
	}

	@Override
	public List<Map<String, Object>> countCostUnPostBill(HashMap<String, Object> map) {
		return ((ScmInvMaterialReqBillDAO) dao).countCostUnPostBill(map);
	}

	@Override
	public List<OrgAdmin2> queryInvMaterialReqBillDept(InvMaterialReqBillDeptParams invMaterialReqBillDeptParams,
			Param param) throws AppException {
		Page page = new Page();
		page.setModelClass(OrgAdmin2.class);
		page.setShowCount(Integer.MAX_VALUE);
//		ArrayList argList = new ArrayList();
//        argList.add("type=from");
//        argList.add("relationType=" + OrgUnitRelationType.ADMINTOINV);
//        argList.add("toOrgUnitNo=" + param.getOrgUnitNo());
        if(StringUtils.isNotBlank(invMaterialReqBillDeptParams.getDeptName())){
        	page.getParam().put((ClassUtils.getFinalModelSimpleName(OrgBaseUnit2.class) + "." + OrgBaseUnit2.FN_ORGUNITNAME), 
    				new QueryParam((ClassUtils.getFinalModelSimpleName(OrgBaseUnit2.class) + "." + OrgBaseUnit2.FN_ORGUNITNAME), QueryParam.QUERY_LIKE, "%"+invMaterialReqBillDeptParams.getDeptName()+"%"));
        }
		page.setSqlCondition("OrgAdmin.orgType='2' and OrgAdmin.orgUnitNo in (SELECT fromOrgUnitNo from orgunitrelation where toOrgUnitNo='"+param.getOrgUnitNo()+"' and relationType='AdminToInv'"+
				"and fromOrgUnitNo in (Select fromOrgUnitNo from orgunitrelation where relationType='AdminToCost' and toOrgUnitNo in "+
				"(SELECT orgUnitNo from orgcostcenter where isBizUnit=1 and longNo like '%"+param.getOrgUnitNo()+"%')))");
		return orgAdminBiz.findPage(page, param);
	}

	@Override
	public List<ScmInvWareHouse> queryInvMaterialReqBillWareHouse(InvMaterialReqBillWareHouseParams invMaterialReqBillWareHouseParams, Param param)throws AppException {
		Page page = new Page();
		page.setModelClass(ScmInvWareHouse.class);
		page.setShowCount(Integer.MAX_VALUE);
		List<String> arglist = new ArrayList<>();
		arglist.add("orgUnitNo="+param.getOrgUnitNo());
		arglist.add("usrCode="+param.getUsrCode());
		if (StringUtils.isNotBlank(invMaterialReqBillWareHouseParams.getWareHouseName())) {
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvWareHouse.class) + "." + ScmInvWareHouse.FN_WHNAME), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvWareHouse.class) + "." + ScmInvWareHouse.FN_WHNAME), QueryParam.QUERY_LIKE, "%"+invMaterialReqBillWareHouseParams.getWareHouseName()+"%"));
		}
		return scmInvWareHouseBiz.queryPage(page, arglist, "selectByUsrAndOrgPage", param);
	}

	@Override
	public List<ScmMaterial2> queryInvReqBillMaterialList(
			InvReqBillMaterialListParams invReqBillMaterialListParams, int pageNum, Param param)throws AppException {
		checkParams(invReqBillMaterialListParams.getBizType(),invReqBillMaterialListParams.getUseDeptNo(),invReqBillMaterialListParams.getWareHouseNo());
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
		arglist.add("controlUnitNo="+param.getControlUnitNo());
		if (StringUtils.equals("1", invReqBillMaterialListParams.getBizType())) {
			ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectByWhNo(param.getOrgUnitNo(), invReqBillMaterialListParams.getWareHouseNo(), param);
			if(scmInvWareHouse == null){
				throw new AppException("iscm.inventorymanage.warehouseoutbusiness.ScmInvMaterialReqBillBizImpl.error.warehouseisnull");
			}
			arglist.add("costCenter=0");
			arglist.add("vendorId=0");
			arglist.add("orgUnitNo="+param.getOrgUnitNo());
			arglist.add("wareHouseId="+scmInvWareHouse.getId());
		}else if(StringUtils.equals("2", invReqBillMaterialListParams.getBizType())){
			arglist.add("costCenter=1");
			arglist.add("vendorId=0");
			arglist.add("orgUnitNo="+invReqBillMaterialListParams.getUseDeptNo());
			arglist.add("wareHouseId=0");
		}
		if(StringUtils.isNotBlank(invReqBillMaterialListParams.getMixParam())){
			page.setSqlCondition("(ScmMaterial.itemNo like '%"+invReqBillMaterialListParams.getMixParam()+"%' or ScmMaterial.itemName like '%"+invReqBillMaterialListParams.getMixParam()+
					"%' or ScmMaterial.barCode like '%"+invReqBillMaterialListParams.getMixParam()+"%' or ScmMaterial.pym like '%"+invReqBillMaterialListParams.getMixParam()+"%')");
		}
		return scmMaterialBiz.queryPage(page, arglist, "selectByStockPage", param);
	}

	private void checkParams(String bizType, String useDeptNo, String wareHouseNo) throws AppException{
		if (StringUtils.isNotBlank(bizType)) {
			if (StringUtils.equals("1", bizType)) {
				if (StringUtils.isBlank(wareHouseNo)) {
					throw new AppException("iscm.inventorymanage.scminvmaterialreqbill.checkParams.selectWarehouse");
				}
			}else if (StringUtils.equals("2", bizType)) {
				if (StringUtils.isBlank(useDeptNo)) {
					throw new AppException("iscm.inventorymanage.scminvmaterialreqbill.checkParams.selectDepartment");
				}
			}else {
				throw new AppException("iscm.inventorymanage.scminvmaterialreqbill.checkParams.notExistBizType");
			}
		}else {
			throw new AppException("iscm.inventorymanage.scminvmaterialreqbill.checkParams.notExistBizType");
		}
	}

	@Override
	public List<ScmInvStock2> queryInvReqBillLotList(InvReqBillLotListParams invReqBillLotListParams, int pageNum,
			Param param) throws AppException {
		checkParams(invReqBillLotListParams.getBizType(), invReqBillLotListParams.getUseDeptNo(), invReqBillLotListParams.getWareHouseNo());
		if (StringUtils.isEmpty(invReqBillLotListParams.getItemNo())) {
			throw new AppException("iscm.inventorymanage.scminvmaterialreqbill.checkParams.ItemNoIsNull");
		}
		ScmMaterial2 scmMaterial = scmMaterialBiz.selectByItemNo(param.getControlUnitNo(), invReqBillLotListParams.getItemNo(), param);
		if (scmMaterial==null) {
			throw new AppException("iscm.inventorymanage.scminvmaterialreqbill.checkParams.noSuchMaterial");
		}
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
		if("1".equals(invReqBillLotListParams.getBizType())){
			ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectByWhNo(param.getOrgUnitNo(), invReqBillLotListParams.getWareHouseNo(), param);
			if(scmInvWareHouse == null){
				throw new AppException("iscm.inventorymanage.warehouseoutbusiness.ScmInvMaterialReqBillBizImpl.error.warehouseisnull");
			}
			arglist.add("orgUnitNo="+param.getOrgUnitNo());
			arglist.add("bizDate="+ FormatUtils.fmtDate(invReqBillLotListParams.getBizDate()));
			arglist.add("costCenter=0");
			arglist.add("wareHouseId="+scmInvWareHouse.getId());
			arglist.add("itemId=" + scmMaterial.getId());
		}else{
			arglist.add("orgUnitNo="+invReqBillLotListParams.getUseDeptNo());
			arglist.add("bizDate="+ FormatUtils.fmtDate(invReqBillLotListParams.getBizDate()));
			arglist.add("costCenter=1");
			arglist.add("itemId=" + scmMaterial.getId());
		}
		return scmInvStockBiz.queryPage(page, arglist, "selectSaleIssueLot", param);
	}

	@Override
	public List<Usr> queryInvMaterialReqBillPerson(InvMaterialReqBillPersonParams invMaterialReqBillPersonParams,int pageNum,
			Param param) throws AppException {
		Page page = new Page();
		page.setModelClass(Usr2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		List<String> arglist = new ArrayList();
		arglist.add("controlUnitNo="+param.getControlUnitNo());
		if (StringUtils.isNotBlank(invMaterialReqBillPersonParams.getRequestPersonName())) {
			page.getParam().put((ClassUtils.getFinalModelSimpleName(Usr.class) + "." + Usr.FN_NAME), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(Usr.class) + "." + Usr.FN_NAME), QueryParam.QUERY_LIKE, "%"+invMaterialReqBillPersonParams.getRequestPersonName()+"%"));
		}
		return usrBiz.queryPage(page, arglist, "selectByCtrlPage", param);
	}

	@Override
	public List<ScmInvMaterialDrillResult> selectDrillBills(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param)
			throws AppException {
		if (scmInvMaterialReqBill != null) {
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("otId", scmInvMaterialReqBill.getOtId());
			return ((ScmInvMaterialReqBillDAO)dao).selectDrillBills(map);
		}
		return null;
	}

	@Override
	public ScmInvMaterialReqBill2 updatePrtCount(ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param)
			throws AppException {
		if(scmInvMaterialReqBill.getOtId()>0){
			ScmInvMaterialReqBill2 bill = this.selectDirect(scmInvMaterialReqBill.getOtId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmInvMaterialReqBill;
	}

	@Override
	public DataSyncResult dataSync(InvMaterialReqListSParams invMaterialReqListSParam,
			List<ScmInvMaterialReqBill2> scmInvMaterialReqBills, Param param) {
		DataSyncResult dataSyncResult = new DataSyncResult();
		if(StringUtils.isBlank(invMaterialReqListSParam.getOtNo())&& StringUtils.equals(invMaterialReqListSParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billNo.notExist");
		}
		if(StringUtils.isBlank(invMaterialReqListSParam.getPostStatus())&& StringUtils.equals(invMaterialReqListSParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billStatus.notExist");
		}
		if(invMaterialReqListSParam.getBizDate() == null&& StringUtils.equals(invMaterialReqListSParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billBizDate.notExist");
		}
		if((invMaterialReqListSParam.getDetailList() == null || invMaterialReqListSParam.getDetailList().isEmpty())&& StringUtils.equals(invMaterialReqListSParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billEntry.notExist");
		}
		if(!StringUtils.isNotBlank(invMaterialReqListSParam.getWhNo())&& StringUtils.equals(invMaterialReqListSParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billWhNo.notExist");
		}
		//获取仓库					
		ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectByWhNo(param.getOrgUnitNo(), invMaterialReqListSParam.getWhNo(), param);
		if(scmInvWareHouse == null && StringUtils.equals(invMaterialReqListSParam.getDelete(), "N"))
			throw new AppException("iscm.api.datePut.billWhNo.notExist");
		for(ScmInvMaterialReqBill2 scmInvMaterialReqBill:scmInvMaterialReqBills) {
			//删除				
			if(StringUtils.equals(invMaterialReqListSParam.getDelete(),"Y")) {
				scmInvMaterialReqBillEntryBiz.deleteByOtId(scmInvMaterialReqBill.getOtId(), param);
				((ScmInvMaterialReqBillDAO) dao).delete(scmInvMaterialReqBill.getPK()+"");
				dataSyncResult.setNo(invMaterialReqListSParam.getOtNo());
				dataSyncResult.setErrCode("0");
				return dataSyncResult;
			}
			if(StringUtils.equals(invMaterialReqListSParam.getOtNo(), scmInvMaterialReqBill.getOtNo())) {
				//构建明细				
				ArrayList<ScmInvMaterialReqBillEntry2> scmInvOtherIssueBillEntryAdd = new ArrayList<ScmInvMaterialReqBillEntry2>();
				for(InvMaterialReqDetailSParams invMaterialReqDetailSParam :invMaterialReqListSParam.getDetailList()) {
					ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry = new ScmInvMaterialReqBillEntry2(true);
					if(!scmMatrialMap.containsKey(invMaterialReqDetailSParam.getItemNo())) {
						ScmMaterial2 selectByItemNo = scmMaterialBiz.selectByItemNo(param.getControlUnitNo(), invMaterialReqDetailSParam.getItemNo(), param);
						if(selectByItemNo == null)
							throw new AppException("iscm.api.datePut.billItemNo.notExist");
						scmMatrialMap.put(selectByItemNo.getItemNo(), selectByItemNo.getId());
					}
					BeanUtils.copyProperties(invMaterialReqDetailSParam, scmInvMaterialReqBillEntry);
					scmInvMaterialReqBillEntry.setItemId(scmMatrialMap.get(invMaterialReqDetailSParam.getItemNo()));
					if(!scmUnitMap.containsKey(scmInvMaterialReqBillEntry.getItemId())) {
						ScmMaterialInventory2 selectByItemIdAndOrgUnitNo = scmMaterialInventoryBiz.selectByItemId(scmInvMaterialReqBillEntry.getItemId(), param.getOrgUnitNo(), param.getControlUnitNo(), param);
						if(selectByItemIdAndOrgUnitNo == null)
							throw new AppException("iscm.api.datePut.billUnitNo.notExist");
						scmUnitMap.put(selectByItemIdAndOrgUnitNo.getItemId(), selectByItemIdAndOrgUnitNo.getUnitId());
					}
					scmInvMaterialReqBillEntry.setUnit(scmUnitMap.get(scmInvMaterialReqBillEntry.getItemId()));
					scmInvMaterialReqBillEntry.setOrgUnitNo(param.getOrgUnitNo());
					scmInvMaterialReqBillEntry.setWareHouseId(scmInvWareHouse.getId());
					scmInvMaterialReqBillEntry.setOtId(scmInvMaterialReqBill.getOtId());
					scmInvMaterialReqBillEntry.setBaseQty(invMaterialReqDetailSParam.getQty());
					scmInvMaterialReqBillEntry.setInvDate(scmInvMaterialReqBill.getBizDate());
					scmInvOtherIssueBillEntryAdd.add(scmInvMaterialReqBillEntry);
				}
				//2、	过账 》过账 不存在
				if(StringUtils.equals(invMaterialReqListSParam.getPostStatus(), "1")&& "E,C".contains(scmInvMaterialReqBill.getStatus())) {
					throw new AppException("iscm.api.business.datasync.controller.posted");
				//3、	非过账 》过账
				}else if(!"E,C".contains(scmInvMaterialReqBill.getStatus())&& StringUtils.equals(invMaterialReqListSParam.getPostStatus(), "1")){
					//更新主子表
					refreshData(invMaterialReqListSParam,scmInvMaterialReqBill,param,scmInvWareHouse);
					scmInvMaterialReqBill.setStatus("E");
					update(scmInvMaterialReqBill, param);
					scmInvMaterialReqBillEntryBiz.deleteByOtId(scmInvMaterialReqBill.getOtId(), param);
					scmInvMaterialReqBillEntryBiz.add(scmInvOtherIssueBillEntryAdd, param);
					//更新期间结存
					scmInvBalBiz.updateByMaterialReqBillOut(scmInvMaterialReqBill.getOtId(), param); 
					scmInvBalBiz.updateByMaterialReqBillOutOrgunitNo(scmInvMaterialReqBill.getOtId(), param);
					scmInvBalBiz.addByMaterialReqBillOutOrgunitNo(scmInvMaterialReqBill.getOtId(), param);
					scmInvBalBiz.addByMaterialReqBillOut(scmInvMaterialReqBill.getOtId(), param);
					//返回结果					
					dataSyncResult.setNo(invMaterialReqListSParam.getOtNo());
					dataSyncResult.setErrCode("0");
					return dataSyncResult;
				//4、	过账 》非过账	ok				
				}else if("E,C".contains(scmInvMaterialReqBill.getStatus()) && StringUtils.equals(invMaterialReqListSParam.getPostStatus(), "0")){
					throw new AppException("iscm.api.business.datasync.controller.cancelPost");
				//5、	非过账 》非过账				
				}else{
					refreshData(invMaterialReqListSParam,scmInvMaterialReqBill,param,scmInvWareHouse);
					scmInvMaterialReqBill.setStatus("I");
					update(scmInvMaterialReqBill, param);
					scmInvMaterialReqBillEntryBiz.deleteByOtId(scmInvMaterialReqBill.getOtId(), param);
					scmInvMaterialReqBillEntryBiz.add(scmInvOtherIssueBillEntryAdd, param);
					dataSyncResult.setNo(invMaterialReqListSParam.getOtNo());
					dataSyncResult.setErrCode("0");
					return dataSyncResult;
				}
			}
		}
		if(StringUtils.equals(invMaterialReqListSParam.getDelete(),"Y")) {
			dataSyncResult.setNo(invMaterialReqListSParam.getOtNo());
			dataSyncResult.setErrCode("0");
			return dataSyncResult;
		}
		//1、	新增 √
		ArrayList<ScmInvMaterialReqBillEntry2> scmInvOtherIssueBillEntryAdd2 = new ArrayList<ScmInvMaterialReqBillEntry2>();
		ScmInvMaterialReqBill2 scmInvMaterialReqBill = new ScmInvMaterialReqBill2(true);
		BeanUtils.copyProperties(invMaterialReqListSParam, scmInvMaterialReqBill);
		scmInvMaterialReqBill.setWareHouseId(scmInvWareHouse.getId()); 
		scmInvMaterialReqBill.setOrgUnitNo(param.getOrgUnitNo());
		scmInvMaterialReqBill.setControlUnitNo(param.getControlUnitNo());
		beforeAdd(scmInvMaterialReqBill, param);
		refreshData(invMaterialReqListSParam,scmInvMaterialReqBill,param,scmInvWareHouse);
		scmInvMaterialReqBill.setStatus("I");
		if(StringUtils.equals(invMaterialReqListSParam.getPostStatus(),"1")) 
			scmInvMaterialReqBill.setStatus("E");
		scmInvMaterialReqBill.setCreator("");
		scmInvMaterialReqBill.setBizType("1");
		scmInvMaterialReqBill.setCreateDate(invMaterialReqListSParam.getBizDate());
		scmInvMaterialReqBill.setOtNo(invMaterialReqListSParam.getOtNo());
		((ScmInvMaterialReqBillDAO) dao).add(scmInvMaterialReqBill);
		
		for(InvMaterialReqDetailSParams invMaterialReqDetailSParam :invMaterialReqListSParam.getDetailList()) {
			ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry = new ScmInvMaterialReqBillEntry2(true);
			BeanUtils.copyProperties(invMaterialReqDetailSParam, scmInvMaterialReqBillEntry);
			if(!scmMatrialMap.containsKey(invMaterialReqDetailSParam.getItemNo())) {
				ScmMaterial2 selectByItemNo = scmMaterialBiz.selectByItemNo(param.getControlUnitNo(), invMaterialReqDetailSParam.getItemNo(), param);
				if(selectByItemNo == null)
					throw new AppException("iscm.api.datePut.billItemNo.notExist");
				scmMatrialMap.put(selectByItemNo.getItemNo(), selectByItemNo.getId());
			}
			scmInvMaterialReqBillEntry.setItemId(scmMatrialMap.get(invMaterialReqDetailSParam.getItemNo()));
			if(!scmUnitMap.containsKey(scmInvMaterialReqBillEntry.getItemId())) {
				ScmMaterialInventory2 selectByItemIdAndOrgUnitNo = scmMaterialInventoryBiz.selectByItemId(scmInvMaterialReqBillEntry.getItemId(), param.getOrgUnitNo(), param.getControlUnitNo(), param);
				if(selectByItemIdAndOrgUnitNo == null)
					throw new AppException("iscm.api.datePut.billUnitNo.notExist");
				scmUnitMap.put(selectByItemIdAndOrgUnitNo.getItemId(), selectByItemIdAndOrgUnitNo.getUnitId());
			}
			scmInvMaterialReqBillEntry.setUnit(scmUnitMap.get(scmInvMaterialReqBillEntry.getItemId()));
			scmInvMaterialReqBillEntry.setStatus("I");
			if(StringUtils.equals(invMaterialReqListSParam.getPostStatus(),"1")) 
				scmInvMaterialReqBillEntry.setStatus("E");
			scmInvMaterialReqBillEntry.setBaseQty(invMaterialReqDetailSParam.getQty());
			scmInvMaterialReqBillEntry.setInvDate(scmInvMaterialReqBill.getBizDate());
			scmInvMaterialReqBillEntry.setPieQty(BigDecimal.ZERO);
			scmInvMaterialReqBillEntry.setWareHouseId(scmInvWareHouse.getId());
			scmInvMaterialReqBillEntry.setOtId(scmInvMaterialReqBill.getOtId());
			scmInvOtherIssueBillEntryAdd2.add(scmInvMaterialReqBillEntry);
		}
		scmInvMaterialReqBillEntryBiz.add(scmInvOtherIssueBillEntryAdd2, param);
		if(StringUtils.equals(invMaterialReqListSParam.getPostStatus(),"1")) {
			//更新期间结存
			scmInvBalBiz.updateByMaterialReqBillOut(scmInvMaterialReqBill.getOtId(), param); 
			scmInvBalBiz.updateByMaterialReqBillOutOrgunitNo(scmInvMaterialReqBill.getOtId(), param);
			scmInvBalBiz.addByMaterialReqBillOutOrgunitNo(scmInvMaterialReqBill.getOtId(), param);
			scmInvBalBiz.addByMaterialReqBillOut(scmInvMaterialReqBill.getOtId(), param);
		}
		dataSyncResult.setNo(invMaterialReqListSParam.getOtNo());
		dataSyncResult.setErrCode("0");
		return dataSyncResult;
	}

	private void refreshData(InvMaterialReqListSParams invMaterialReqListSParam,
			ScmInvMaterialReqBill2 scmInvMaterialReqBill, Param param, ScmInvWareHouse scmInvWareHouse) {
		BeanUtils.copyProperties(invMaterialReqListSParam, scmInvMaterialReqBill);
		List<OrgCompany2> orgCompanylist2 = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, scmInvMaterialReqBill.getOrgUnitNo(), false, null, param);
		if(orgCompanylist2 != null && !orgCompanylist2.isEmpty()){
			scmInvMaterialReqBill.setFinOrgUnitNo(orgCompanylist2.get(0).getOrgUnitNo());
		}else {
			throw new AppException("iscm.api.datePut.ScmInvCountingTaskBizImpl.dataSync");
		}
		List<OrgCostCenter2> outOrgCostCenterList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOCOST,scmInvMaterialReqBill.getUseOrgUnitNo(), false, null, param);
		if (outOrgCostCenterList == null || outOrgCostCenterList.isEmpty()) {
			throw new AppException("iscm.inventorymanage.cstbusiness.service.impl.ScmInvMoveBillBizImpl.noOutCst");
		}
		scmInvMaterialReqBill.setCostOrgUnitNo(outOrgCostCenterList.get(0).getOrgUnitNo());
		scmInvMaterialReqBill.setBizType("1");
		scmInvMaterialReqBill.setWareHouseId(scmInvWareHouse.getId());
		scmInvMaterialReqBill.setOrgUnitNo(param.getOrgUnitNo());
		scmInvMaterialReqBill.setControlUnitNo(param.getControlUnitNo());
	}
}

