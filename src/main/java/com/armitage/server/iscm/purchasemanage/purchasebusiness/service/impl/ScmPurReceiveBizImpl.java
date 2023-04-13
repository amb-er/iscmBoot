package com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.armitage.server.api.business.purreceive.params.PurRecPurOrgUnitParams;
import com.armitage.server.api.business.purreceive.params.PurReceiveAddParams;
import com.armitage.server.api.business.purreceive.params.PurReceiveDetailParams;
import com.armitage.server.api.business.purreceive.params.PurReceiveEditParams;
import com.armitage.server.api.business.purreceive.params.PurReceiveParams;
import com.armitage.server.api.business.purreceive.params.PurReceiverListParams;
import com.armitage.server.activity.util.ActivityUtil;
import com.armitage.server.activity.util.AuditMsgUtil;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
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
import com.armitage.server.iscm.basedata.model.ScmCostUseSet2;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmCostUseSetBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.common.model.CommonAuditOpinion;
import com.armitage.server.iscm.common.model.CommonEventHistory;
import com.armitage.server.iscm.common.service.CommonEventHistoryBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurReceiveDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurBillDrillResult;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurCheck;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurCheck2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurCheckBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReceiveBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReceiveEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReturnsBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerBiz;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.EmployeeBiz;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.model.Usr2;
import com.armitage.server.user.service.UsrBiz;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

@Service("scmPurReceiveBiz")
public class ScmPurReceiveBizImpl extends BaseBizImpl<ScmPurReceive2> implements ScmPurReceiveBiz {
	private static Log log = LogFactory.getLog(ScmPurReceiveBizImpl.class);

	private UsrBiz usrBiz;
	private OrgUnitBiz orgUnitBiz;
	private EmployeeBiz employeeBiz;
	private ScmPurReceiveEntryBiz scmPurReceiveEntryBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private ScmPurBuyerBiz scmPurBuyerBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private OrgPurchaseBiz orgPurchaseBiz;
	private CodeBiz codeBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private ScmPurReturnsBiz scmPurReturnsBiz;
	private ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz;
	private ScmPurOrderBiz scmPurOrderBiz;
	private ScmPurOrderEntryBiz scmPurOrderEntryBiz;
	private ScmPurCheckBiz scmPurCheckBiz;
	private PeriodCalendarBiz periodCalendarBiz;
	private SysParamBiz sysParamBiz;
	private BillTypeBiz billTypeBiz;
	private ScmBillPendingBiz scmBillPendingBiz;
	private CommonEventHistoryBiz commonEventHistoryBiz;
	private OrgStorageBiz orgStorageBiz;
	private OrgAdminBiz orgAdminBiz;
	private ScmCostUseSetBiz scmCostUseSetBiz;

	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();

	public void setScmPurBuyerBiz(ScmPurBuyerBiz scmPurBuyerBiz) {
		this.scmPurBuyerBiz = scmPurBuyerBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public void setEmployeeBiz(EmployeeBiz employeeBiz) {
		this.employeeBiz = employeeBiz;
	}

	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}

	public void setScmPurReceiveEntryBiz(ScmPurReceiveEntryBiz scmPurReceiveEntryBiz) {
		this.scmPurReceiveEntryBiz = scmPurReceiveEntryBiz;
	}
	
	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setOrgPurchaseBiz(OrgPurchaseBiz orgPurchaseBiz) {
		this.orgPurchaseBiz = orgPurchaseBiz;
	}

	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}

	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}

	public void setScmPurReturnsBiz(ScmPurReturnsBiz scmPurReturnsBiz) {
		this.scmPurReturnsBiz = scmPurReturnsBiz;
	}

	public void setScmInvPurInWarehsBillBiz(
			ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz) {
		this.scmInvPurInWarehsBillBiz = scmInvPurInWarehsBillBiz;
	}

	public void setScmPurOrderBiz(ScmPurOrderBiz scmPurOrderBiz) {
		this.scmPurOrderBiz = scmPurOrderBiz;
	}

	public void setScmPurOrderEntryBiz(ScmPurOrderEntryBiz scmPurOrderEntryBiz) {
		this.scmPurOrderEntryBiz = scmPurOrderEntryBiz;
	}

	public void setScmPurCheckBiz(ScmPurCheckBiz scmPurCheckBiz) {
		this.scmPurCheckBiz = scmPurCheckBiz;
	}

	public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}

	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}

	public void setScmBillPendingBiz(ScmBillPendingBiz scmBillPendingBiz) {
		this.scmBillPendingBiz = scmBillPendingBiz;
	}
	
	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}

	public void setCommonEventHistoryBiz(CommonEventHistoryBiz commonEventHistoryBiz) {
		this.commonEventHistoryBiz = commonEventHistoryBiz;
	}
	
	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}

	public void setScmCostUseSetBiz(ScmCostUseSetBiz scmCostUseSetBiz) {
		this.scmCostUseSetBiz = scmCostUseSetBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." + ScmPurReceive2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." + ScmPurReceive2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." + ScmPurReceive2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." + ScmPurReceive2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		if(list != null && !list.isEmpty()){
			for(ScmPurReceive2 scmPurReceive : (List<ScmPurReceive2>)list){
				setConvertMap(scmPurReceive,param);
				if("I,R".contains(scmPurReceive.getStatus())) {
					scmPurReceive.setPendingUsrName("");
				}
			} 
		}
	}
	
	@Override
	protected void afterSelect(ScmPurReceive2 entity, Param param) throws AppException {
		setConvertMap(entity,param);
	}
	
	@Override
	public void setConvertMap(ScmPurReceive2 scmPurReceive,Param param) throws AppException{
		if(StringUtils.isNotBlank(scmPurReceive.getPendingUsr())) {
			StringBuffer usrName = new StringBuffer("");
			String[] usrCodes = StringUtils.split(scmPurReceive.getPendingUsr(), ",");
			for(String usrCode : usrCodes) {
				Usr usr = usrBiz.selectByCode(usrCode, param);
				if(usr != null) {
					if(StringUtils.isNotBlank(usrName.toString())) 
						usrName.append(",");
					usrName.append(usr.getName());
				}
			}
			scmPurReceive.setPendingUsrName(usrName.toString());
		}
		if (StringUtils.isNotBlank(scmPurReceive.getCreator())){
			//制单人
			Usr usr = usrBiz.selectByCode(scmPurReceive.getCreator(), param);
			if (usr != null) {
				if(scmPurReceive.getDataDisplayMap()==null){
					scmPurReceive.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
				}
				scmPurReceive.getDataDisplayMap().put(ScmPurReceive.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
				scmPurReceive.setConvertMap(ScmPurReceive.FN_CREATOR, usr);
				scmPurReceive.setCreatorName(usr.getName());
			}
		}
		if (StringUtils.isNotBlank(scmPurReceive.getOrgUnitNo())){
			//库存组织
			OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurReceive.getOrgUnitNo(), param);
			if (orgBaseUnit != null) {
				scmPurReceive.setConvertMap(ScmPurReceive.FN_ORGUNITNO, orgBaseUnit);
			}
		}
		if (scmPurReceive.getVendorId() > 0){
			//供应商
			Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurReceive.getVendorId(), param);
			if (scmsupplier != null) {
				scmPurReceive.setVendorNo(scmsupplier.getVendorNo());
				scmPurReceive.setConvertMap(ScmPurReceive.FN_VENDORID, scmsupplier);
				scmPurReceive.setVendorName(scmsupplier.getVendorName());
			}
		}
		if (StringUtils.isNotBlank(scmPurReceive.getReceiver())){
			//收货人
			Usr usr = usrBiz.selectByCode(scmPurReceive.getReceiver(), param);
			if (usr != null) {
				scmPurReceive.setConvertMap(ScmPurReceive.FN_RECEIVER, usr);
				scmPurReceive.setReceiverName(usr.getName());
			}
		}
		if (StringUtils.isNotBlank(scmPurReceive.getEditor())){
			//修改人
			Usr usr = usrBiz.selectByCode(scmPurReceive.getEditor(), param);
			if (usr != null) {
				scmPurReceive.setConvertMap(ScmPurReceive.FN_EDITOR, usr);
			}
		}
		if (StringUtils.isNotBlank(scmPurReceive.getChecker())){
			//审核人
			Usr usr = usrBiz.selectByCode(scmPurReceive.getChecker(), param);
			if (usr != null) {
				scmPurReceive.setCheckerName(usr.getName());
				scmPurReceive.setConvertMap(ScmPurReceive.FN_CHECKER, usr);
			}
		}
		if (StringUtils.isNotBlank(scmPurReceive.getPurOrgUnitNo())){
			//采购组织
			OrgPurchase2  orgPurchase = orgPurchaseBiz.selectByOrgUnitNo(scmPurReceive.getPurOrgUnitNo(), param);
			if (orgPurchase != null) {
				scmPurReceive.setPurOrgUnitName(orgPurchase.getOrgUnitName());
				scmPurReceive.setConvertMap(ScmPurReceive.FN_PURORGUNITNO, orgPurchase);
			}
		}
		if (scmPurReceive.getBuyerId() > 0){
			//采购员
			ScmPurBuyer2 scmPurBuyer = scmPurBuyerBiz.selectDirect(scmPurReceive.getBuyerId(), param);
			if (scmPurBuyer != null) {
				scmPurReceive.setBuyerCode(scmPurBuyer.getBuyerCode());
				scmPurReceive.setBuyerName(scmPurBuyer.getBuyerName());
				scmPurReceive.setConvertMap(ScmPurReceive.FN_BUYERID, scmPurBuyer);
			}
		}

		if(StringUtils.isNotBlank(scmPurReceive.getStatus())){
			Code code = codeBiz.selectByCategoryAndCode("quotationStatus", scmPurReceive.getStatus());
			if(code!=null)
				scmPurReceive.setStatusName(code.getName());
		}
		
		if(StringUtils.isNotBlank(scmPurReceive.getUseOrgUnitNos())){
			String[] useOrgUnitNos = StringUtils.split(scmPurReceive.getUseOrgUnitNos(), ",");
			StringBuffer useOrgUnitName = new StringBuffer("");
			for(String useOrgUnitNO : useOrgUnitNos) {
				OrgAdmin2 orgAdmin = orgAdminBiz.selectByOrgUnitNo(useOrgUnitNO, param);
				if(orgAdmin != null) {
					if(StringUtils.isNotBlank(useOrgUnitName.toString())) 
						useOrgUnitName.append(",");
					useOrgUnitName.append(orgAdmin.getOrgUnitName());
				}
			}
			scmPurReceive.setUseOrgUnitName(useOrgUnitName.toString());
		}
		
		if(StringUtils.isNotBlank(scmPurReceive.getWareHouseIds())){
			String[] wareHouseIds = StringUtils.split(scmPurReceive.getWareHouseIds(), ",");
			StringBuffer wareHouseName = new StringBuffer("");
			for(String wareHouseId : wareHouseIds) {
				ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(Integer.parseInt(wareHouseId), param);
				if(scmInvWareHouse != null) {
					if(StringUtils.isNotBlank(wareHouseName.toString())) 
						wareHouseName.append(",");
					wareHouseName.append(scmInvWareHouse.getWhName());
				}
			}
			scmPurReceive.setWareHouseName(wareHouseName.toString());
		}
		
		scmPurReceive.setTaxAmount(scmPurReceive.getTaxAmt().subtract(scmPurReceive.getAmt()));
	}
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		long ss = new Date().getTime();
		ScmPurReceive2 scmPurReceive = (ScmPurReceive2) bean.getList().get(0);
		HashMap<String,Object> cacheMap = new HashMap<String,Object>();
		if(scmPurReceive != null && scmPurReceive.getId() > 0){
			List<ScmPurReceiveEntry2> scmPurReceiveEntryList = scmPurReceiveEntryBiz.selectByPvId(scmPurReceive.getId(), param);
			if(scmPurReceiveEntryList != null && !scmPurReceiveEntryList.isEmpty()){
				for(ScmPurReceiveEntry2 scmPurReceiveEntry : scmPurReceiveEntryList){
					if (scmPurReceiveEntry.getBuyerId() > 0){
						//采购员
						ScmPurBuyer2 scmPurBuyer = (ScmPurBuyer2) cacheMap.get(ClassUtils.getFinalModelSimpleName(ScmPurBuyer2.class)+"_"+scmPurReceiveEntry.getBuyerId());
						if(scmPurBuyer==null){
							scmPurBuyer = scmPurBuyerBiz.selectDirect(scmPurReceiveEntry.getBuyerId(), param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(ScmPurBuyer2.class)+"_"+scmPurReceiveEntry.getBuyerId(),scmPurBuyer);
						}
						if (scmPurBuyer != null) {
							scmPurReceiveEntry.setConvertMap(ScmPurReceiveEntry2.FN_BUYERID, scmPurBuyer);
						}
					}
				}
				bean.setList2(scmPurReceiveEntryList);
			}
		}
	}

	@Override
	protected void beforeAdd(CommonBean bean, Param param) throws AppException {
		ScmPurReceive2 scmPurReceive2 = (ScmPurReceive2) bean.getList().get(0);
		if(scmPurReceive2 != null){
			String code = CodeAutoCalUtil.getBillCode("PurReceive", scmPurReceive2, param);
			scmPurReceive2.setPvNo(code);
			List<ScmPurReceiveEntry2> scmPurReceiveEntryList = bean.getList2();
            BigDecimal amt= BigDecimal.ZERO;
            BigDecimal taxAmt= BigDecimal.ZERO;
            List<String> poNoList = new ArrayList();
            List<String> useOrgList = new ArrayList();
            List<String> whsList = new ArrayList();
			if(scmPurReceiveEntryList != null && !scmPurReceiveEntryList.isEmpty()){
				for(ScmPurReceiveEntry2 scmPurReceiveEntry : scmPurReceiveEntryList){
                    amt = amt.add(scmPurReceiveEntry.getAmt());
                    taxAmt = taxAmt.add(scmPurReceiveEntry.getTaxAmt());
                    if(StringUtils.isNotBlank(scmPurReceiveEntry.getPoNo()) && !poNoList.contains(scmPurReceiveEntry.getPoNo()))
                    	poNoList.add(scmPurReceiveEntry.getPoNo());
                    if(StringUtils.isNotBlank(scmPurReceiveEntry.getUseOrgUnitNo()) && !useOrgList.contains(scmPurReceiveEntry.getUseOrgUnitNo()))
                    	useOrgList.add(scmPurReceiveEntry.getUseOrgUnitNo());
                    if(scmPurReceiveEntry.getWareHouseId()>0 && !whsList.contains(String.valueOf(scmPurReceiveEntry.getWareHouseId())))
                    	whsList.add(String.valueOf(scmPurReceiveEntry.getWareHouseId()));
				}
			}
			scmPurReceive2.setAmt(amt);
			scmPurReceive2.setTaxAmt(taxAmt);
			scmPurReceive2.setPoNos(StringUtils.left(StringUtils.join(poNoList, ","),250));
			scmPurReceive2.setUseOrgUnitNos(StringUtils.left(StringUtils.join(useOrgList, ","),250));
			scmPurReceive2.setWareHouseIds(StringUtils.left(StringUtils.join(whsList, ","),250));
			//获取期间
			PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(scmPurReceive2.getReceiveDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
			}
			scmPurReceive2.setPeriodId(periodCalendar.getPeriodId());
			scmPurReceive2.setAccountYear(periodCalendar.getAccountYear());
			scmPurReceive2.setAccountPeriod(periodCalendar.getAccountPeriod());
//			BillType2 billType = billTypeBiz.selectByOrgAndCode(scmPurReceive2.getFinOrgUnitNo(), "PurReceive", param);
//			if(billType!=null && billType.isNeedAudit()) {
//				if(StringUtils.isBlank(billType.getWorkFlowNo())) {
//					throw new AppException(Message.getMessage(param.getLang(), "iscm.workflow.needaudit.notprocess", new String[] {Message.getMessage(param.getLang(),"field.ScmPurReceive")}));
//				}
//				scmPurReceive2.setWorkFlowNo(billType.getWorkFlowNo());
//			}
		}
	}
	
	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmPurReceive2 scmPurReceive = (ScmPurReceive2) bean.getList().get(0);
		if(scmPurReceive != null && scmPurReceive.getId() > 0){
			//新增收货明细
			List<ScmPurReceiveEntry2> scmPurReceiveEntryList = bean.getList2();
			if(scmPurReceiveEntryList != null && !scmPurReceiveEntryList.isEmpty()){
				StringBuffer itemIds = new StringBuffer();
				for(ScmPurReceiveEntry2 scmPurReceiveEntry : scmPurReceiveEntryList){
					if(StringUtils.isNotBlank(itemIds.toString()))
						itemIds.append(",");
					itemIds.append(scmPurReceiveEntry.getItemId());
				}
				String rebateType = sysParamBiz.getValue(scmPurReceive.getOrgUnitNo(), "SCM_RebateType", "2", param);//折让方式
				String notRawMaterails = ScmMaterialUtil.getNotRawMaterail(scmPurReceive.getFinOrgUnitNo(), itemIds.toString(), param);
				if(StringUtils.isNotBlank(notRawMaterails))
					throw new AppException(Message.getMessage(param.getLang(), "iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.rawMaterial", new String[]{notRawMaterails.toString()}));
				int lineId = 1;
				for(ScmPurReceiveEntry2 scmPurReceiveEntry : scmPurReceiveEntryList){
					scmPurReceiveEntry.setLineId(lineId);
					scmPurReceiveEntry.setPvId(scmPurReceive.getId());
					scmPurReceiveEntry.setInvOrgUnitNo(scmPurReceive.getOrgUnitNo());
					scmPurReceiveEntry.setPurOrgUnitNo(scmPurReceive.getPurOrgUnitNo());
					scmPurReceiveEntry.setBuyerId(scmPurReceive.getBuyerId());
					scmPurReceiveEntry.setPurGroupId(scmPurReceive.getPurGroupId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmPurReceiveEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmPurReceiveEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					//让步接收数量=让步接收比例*不合格数量
					if(scmPurReceiveEntry.getConcessiveRecRate().compareTo(BigDecimal.ZERO)>0 ) {
						if (StringUtils.equals("2", rebateType) || scmPurReceive.isUnified()) {
							scmPurReceiveEntry.setConcessiveRecQty(scmPurReceiveEntry.getUnqualifiedQty().multiply(scmPurReceiveEntry.getConcessiveRecRate()));
						}else {
							scmPurReceiveEntry.setConcessiveRecQty(scmPurReceiveEntry.getUnqualifiedQty());
						}
					}else {
						if (StringUtils.equals("2", rebateType) || scmPurReceive.isUnified()) {
							scmPurReceiveEntry.setConcessiveRecQty(BigDecimal.ZERO);
						}else {
							scmPurReceiveEntry.setConcessiveRecQty(scmPurReceiveEntry.getUnqualifiedQty());
						}
					}
					BigDecimal purConvRate = ScmMaterialUtil.getConvRate(scmPurReceiveEntry.getItemId(), scmPurReceiveEntry.getPurUnit(), param);//采购单位转换系数
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmPurReceiveEntry.getItemId(), scmPurReceiveEntry.getUnit(), param);//库存单位转换系数
					if(scmPurReceive.isUnified()){
						//统配以配送数量进行入库
						scmPurReceiveEntry.setInvQty(scmPurReceiveEntry.getDeliveryQty().multiply(purConvRate).divide(invConvRate,4,BigDecimal.ROUND_HALF_UP));	//入库数量
					}else{
						//直配以收货数量-不合格数据+让步接收数量
						scmPurReceiveEntry.setInvQty((scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty()).add(scmPurReceiveEntry.getConcessiveRecQty())).multiply(purConvRate).divide(invConvRate,4,BigDecimal.ROUND_HALF_UP));	//入库数量
					}
					scmPurReceiveEntry.setBaseQty(scmPurReceiveEntry.getInvQty().multiply(invConvRate));
					//单价数量不为0，金额为0时重算
					if (scmPurReceive.getEditType()>0) {
						int amtPrec = Integer.parseInt(sysParamBiz.getValue(param.getControlUnitNo(), "SCM_AmtPrecision", "2", param));
						int pricePrec = Integer.parseInt(sysParamBiz.getValue(param.getControlUnitNo(), "SCM_PricePrecision", "2", param));
						int qtyPrec = Integer.parseInt(sysParamBiz.getValue(param.getControlUnitNo(), "SCM_QtyPrecision", "2", param));
						String priceMode = sysParamBiz.getValue(param.getControlUnitNo(), "SCM_PriceMode", "1", param);
						String receiveEditPrice = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_ReceiveEditPrice", "1", param);
						if (scmPurReceive.getEditType()==2) {//改数量
							if(scmPurReceive.isUnified()){
								//统配
								if(StringUtils.equals("1", priceMode)){
	                                BigDecimal d = (scmPurReceiveEntry.getDeliveryQty()).multiply(scmPurReceiveEntry.getTaxPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
	                                scmPurReceiveEntry.setTaxAmt(d);
	                                scmPurReceiveEntry.setAmt(d.divide((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate()),amtPrec,RoundingMode.HALF_UP));
	                            }else{
	                            	BigDecimal b = (scmPurReceiveEntry.getDeliveryQty()).multiply(scmPurReceiveEntry.getPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
	                                scmPurReceiveEntry.setAmt(b);
	                                scmPurReceiveEntry.setTaxAmt(b.multiply((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate())).setScale(amtPrec,RoundingMode.HALF_UP));
	                            }
		                    }else{
		                        //直配
		                    	if(StringUtils.equals("1", priceMode)){
                                    BigDecimal d = null;
                                    if (StringUtils.equals("2",rebateType)){
                                        d = (scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty()).add(scmPurReceiveEntry.getConcessiveRecQty())).multiply(scmPurReceiveEntry.getTaxPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
                                    }else {
                                        d = ((scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty())).multiply(scmPurReceiveEntry.getTaxPrice()).add(scmPurReceiveEntry.getConcessiveRecQty().multiply(scmPurReceiveEntry.getTaxPrice()).multiply(scmPurReceiveEntry.getConcessiveRecRate()))).setScale(amtPrec, RoundingMode.HALF_UP);
                                    }
                                    scmPurReceiveEntry.setTaxAmt(d);
                                    scmPurReceiveEntry.setAmt(d.divide((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate()),amtPrec,RoundingMode.HALF_UP));
                                }else{
                                    BigDecimal b = null;
                                    if (StringUtils.equals("2",rebateType)){
                                        b = (scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty()).add(scmPurReceiveEntry.getConcessiveRecQty())).multiply(scmPurReceiveEntry.getPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
                                    }else {
                                        b = ((scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty())).multiply(scmPurReceiveEntry.getPrice()).add(scmPurReceiveEntry.getConcessiveRecQty().multiply(scmPurReceiveEntry.getPrice()).multiply(scmPurReceiveEntry.getConcessiveRecRate()))).setScale(amtPrec, RoundingMode.HALF_UP);
                                    }
                                    scmPurReceiveEntry.setAmt(b);
                                    scmPurReceiveEntry.setTaxAmt(b.multiply((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate())).setScale(amtPrec,RoundingMode.HALF_UP));
                                }
		                    }	
						}else if (scmPurReceive.getEditType()==1) {//改金额
							if(StringUtils.equals("1", priceMode)){
								if(scmPurReceive.isUnified()){
                                    //统配
                                    if((BigDecimal.ZERO).compareTo(scmPurReceiveEntry.getDeliveryQty())!=0){
                                        BigDecimal c = (scmPurReceiveEntry.getTaxAmt()).divide(scmPurReceiveEntry.getDeliveryQty(),pricePrec,RoundingMode.HALF_UP);
                                        scmPurReceiveEntry.setTaxPrice(c);
                                        BigDecimal a = (scmPurReceiveEntry.getTaxPrice()).divide((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate()),pricePrec,RoundingMode.HALF_UP);
                                        scmPurReceiveEntry.setPrice(a);
                                    }
                                }else{
                                    //直配
                                    if (scmPurReceiveEntry.getPriceBillId() > 0 && (!StringUtils.equals("Y", receiveEditPrice) 
                                            || !StringUtils.contains("6,7",scmPurReceiveEntry.getRefPriceStatus()))) {// 有来源单, 修改的是数量
                                    	if (BigDecimal.ZERO.compareTo(scmPurReceiveEntry.getTaxPrice())!= 0) {
                                    		BigDecimal b = null;
                                            if (StringUtils.equals("2",rebateType)) {
                                                b = (scmPurReceiveEntry.getTaxAmt()).divide(scmPurReceiveEntry.getTaxPrice(),qtyPrec,RoundingMode.HALF_UP).add(scmPurReceiveEntry.getConcessiveRecQty()).subtract(scmPurReceiveEntry.getUnqualifiedQty());
                                                scmPurReceiveEntry.setQty(b);
                                            }else {
                                                b = (scmPurReceiveEntry.getTaxAmt()).divide(scmPurReceiveEntry.getTaxPrice(),qtyPrec,RoundingMode.HALF_UP).add(scmPurReceiveEntry.getUnqualifiedQty()).subtract(scmPurReceiveEntry.getConcessiveRecQty().multiply(scmPurReceiveEntry.getConcessiveRecRate()));
                                                scmPurReceiveEntry.setQty(b);
                                            }
										}
                                    } else { // 无来源单, 修改的是单价
                                    	if (BigDecimal.ZERO.compareTo(scmPurReceiveEntry.getQty())!= 0) {
                                    		if (StringUtils.equals("2",rebateType)) {
                                                BigDecimal c = (scmPurReceiveEntry.getTaxAmt()).divide((scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty()).add(scmPurReceiveEntry.getConcessiveRecQty())), pricePrec, RoundingMode.HALF_UP);
                                                scmPurReceiveEntry.setTaxPrice(c);
                                            }else {
                                            	BigDecimal c = (scmPurReceiveEntry.getTaxAmt()).divide(scmPurReceiveEntry.getQty(),qtyPrec,RoundingMode.HALF_UP).add(scmPurReceiveEntry.getUnqualifiedQty()).subtract(scmPurReceiveEntry.getConcessiveRecQty().multiply(scmPurReceiveEntry.getConcessiveRecRate()));
                                                scmPurReceiveEntry.setTaxPrice(c);
                                            }
										}
                                    	BigDecimal a = (scmPurReceiveEntry.getTaxPrice()).divide((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate()),pricePrec,RoundingMode.HALF_UP);
                                        scmPurReceiveEntry.setPrice(a);
                                    }
                                }
                                BigDecimal c = (scmPurReceiveEntry.getTaxAmt()).divide((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate()),amtPrec,RoundingMode.HALF_UP);
                                scmPurReceiveEntry.setAmt(c);
							}else {
								if(scmPurReceive.isUnified()){
                                    //统配
									if((BigDecimal.ZERO).compareTo(scmPurReceiveEntry.getDeliveryQty())!=0){
	                                    BigDecimal b = (scmPurReceiveEntry.getAmt()).divide(scmPurReceiveEntry.getDeliveryQty(), pricePrec, RoundingMode.HALF_UP);
	                                    scmPurReceiveEntry.setPrice(b);
	                                    BigDecimal c = (scmPurReceiveEntry.getAmt()).divide(scmPurReceiveEntry.getDeliveryQty(),pricePrec,RoundingMode.HALF_UP);
                                        scmPurReceiveEntry.setTaxPrice(c);
									}
                                }else{
                                	if (scmPurReceiveEntry.getPriceBillId() > 0 && (!StringUtils.equals("Y", receiveEditPrice) 
                                            || !StringUtils.contains("6,7",scmPurReceiveEntry.getRefPriceStatus()))) {// 有来源单, 修改的是数量
                                		if((BigDecimal.ZERO).compareTo(scmPurReceiveEntry.getPrice())!=0){
                                			if (StringUtils.equals("2",rebateType)) {
                                                BigDecimal b = (scmPurReceiveEntry.getAmt()).divide(scmPurReceiveEntry.getPrice(), qtyPrec, RoundingMode.HALF_UP).add(scmPurReceiveEntry.getConcessiveRecQty()).subtract(scmPurReceiveEntry.getUnqualifiedQty());
                                                scmPurReceiveEntry.setQty(b);
                                            }else {
                                            	BigDecimal b = (scmPurReceiveEntry.getAmt()).divide(scmPurReceiveEntry.getPrice(),qtyPrec,RoundingMode.HALF_UP).add(scmPurReceiveEntry.getUnqualifiedQty()).subtract(scmPurReceiveEntry.getConcessiveRecQty().multiply(scmPurReceiveEntry.getConcessiveRecRate()));
                                                scmPurReceiveEntry.setQty(b);
                                            }
                                		}
                                	}else {
                                		 //直配
                                		if((BigDecimal.ZERO).compareTo(scmPurReceiveEntry.getQty())!=0){
                                			if (StringUtils.equals("2",rebateType)){
                                                BigDecimal b = (scmPurReceiveEntry.getAmt()).divide((scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty()).add(scmPurReceiveEntry.getConcessiveRecQty())), pricePrec, RoundingMode.HALF_UP);
                                                scmPurReceiveEntry.setPrice(b);
                                            }else {
                                            	BigDecimal b = (scmPurReceiveEntry.getAmt()).divide(scmPurReceiveEntry.getQty().add(scmPurReceiveEntry.getUnqualifiedQty()).subtract(scmPurReceiveEntry.getConcessiveRecQty().multiply(scmPurReceiveEntry.getConcessiveRecRate())),pricePrec,RoundingMode.HALF_UP);
                                                scmPurReceiveEntry.setPrice(b);
                                            }
                                		}
									}
                                }
                                BigDecimal c = (scmPurReceiveEntry.getPrice()).multiply((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate())).setScale(pricePrec, RoundingMode.HALF_UP);
                                scmPurReceiveEntry.setTaxPrice(c);
                                BigDecimal d = (scmPurReceiveEntry.getAmt()).multiply((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate())).setScale(amtPrec, RoundingMode.HALF_UP);
                                scmPurReceiveEntry.setTaxAmt(d);
							}
						}else if(scmPurReceive.getEditType()==3){//改单价
							if(StringUtils.equals("1", priceMode)){
								BigDecimal a = (scmPurReceiveEntry.getTaxPrice()).divide((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate()),pricePrec,RoundingMode.HALF_UP);
                                scmPurReceiveEntry.setPrice(a);
								if(scmPurReceive.isUnified()){
                                    //统配
                                    BigDecimal d = (scmPurReceiveEntry.getDeliveryQty()).multiply(scmPurReceiveEntry.getTaxPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
                                    scmPurReceiveEntry.setTaxAmt(d);
                                }else{
                                    //直配
                                	BigDecimal d = null;
                                	if (StringUtils.equals("2",rebateType)){
                                		d = (scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty()).add(scmPurReceiveEntry.getConcessiveRecQty())).multiply(scmPurReceiveEntry.getTaxPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
                                	}else {
                                        d = ((scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty())).multiply(scmPurReceiveEntry.getTaxPrice()).add(scmPurReceiveEntry.getConcessiveRecQty().multiply(scmPurReceiveEntry.getTaxPrice()).multiply(scmPurReceiveEntry.getConcessiveRecRate()))).setScale(amtPrec, RoundingMode.HALF_UP);
									}
                                    scmPurReceiveEntry.setTaxAmt(d);
                                }
                                BigDecimal b = (scmPurReceiveEntry.getTaxPrice()).divide((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate()), pricePrec, RoundingMode.HALF_UP);
                                scmPurReceiveEntry.setPrice(b);
                                BigDecimal c = (scmPurReceiveEntry.getTaxAmt()).divide((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate()), amtPrec, RoundingMode.HALF_UP);
                                scmPurReceiveEntry.setAmt(c);
							}else {
								BigDecimal c = (scmPurReceiveEntry.getPrice()).multiply((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate())).setScale(pricePrec,RoundingMode.HALF_UP);
	                            scmPurReceiveEntry.setTaxPrice(c);
	                            if(scmPurReceive.isUnified()){
	                                //统配
	                                BigDecimal b = (scmPurReceiveEntry.getDeliveryQty()).multiply(scmPurReceiveEntry.getPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
	                                scmPurReceiveEntry.setAmt(b);
	                                BigDecimal d = (scmPurReceiveEntry.getAmt()).multiply((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate())).setScale(amtPrec,RoundingMode.HALF_UP);
	                                scmPurReceiveEntry.setTaxAmt(d);
	                            }else{
	                                //直配
	                            	 BigDecimal b = null;
	                            	if (StringUtils.equals("2",rebateType)){
	                            		b = (scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty()).add(scmPurReceiveEntry.getConcessiveRecQty())).multiply(scmPurReceiveEntry.getPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
                                	}else {
                                        b = ((scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty())).multiply(scmPurReceiveEntry.getPrice()).add(scmPurReceiveEntry.getConcessiveRecQty().multiply(scmPurReceiveEntry.getPrice()).multiply(scmPurReceiveEntry.getConcessiveRecRate()))).setScale(amtPrec, RoundingMode.HALF_UP);
									}
	                                scmPurReceiveEntry.setAmt(b);
	                                BigDecimal d = (scmPurReceiveEntry.getAmt()).multiply((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate())).setScale(amtPrec,RoundingMode.HALF_UP);
	                                scmPurReceiveEntry.setTaxAmt(d);
	                            }
							}
						}
					}
					scmPurReceiveEntryBiz.add(scmPurReceiveEntry, param);
					lineId = lineId+1;
				}
			}
//			String receiveQtyZero = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_ReceiveQtyZero", "N", param);
//			if(StringUtils.equals(receiveQtyZero, "N"))
				scmPurOrderBiz.writeBackQtyAndStatus(scmPurReceiveEntryList,param);	//整单回写订货单
		}
	}
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmPurReceive2 scmPurReceive = (ScmPurReceive2) bean.getList().get(0);
		if(scmPurReceive != null && scmPurReceive.getId() > 0){
			//更新收货明细
			List<ScmPurReceiveEntry2> scmPurReceiveEntryList = bean.getList2();
			if(scmPurReceiveEntryList != null && !scmPurReceiveEntryList.isEmpty()){
				StringBuffer itemIds = new StringBuffer();
				for(ScmPurReceiveEntry2 scmPurReceiveEntry : scmPurReceiveEntryList){
					if(StringUtils.isNotBlank(itemIds.toString()))
						itemIds.append(",");
					itemIds.append(scmPurReceiveEntry.getItemId());
				}
				String notRawMaterails = ScmMaterialUtil.getNotRawMaterail(scmPurReceive.getFinOrgUnitNo(), itemIds.toString(), param);
				String rebateType = sysParamBiz.getValue(scmPurReceive.getOrgUnitNo(), "SCM_RebateType", "2", param);//折让方式
				if(StringUtils.isNotBlank(notRawMaterails))
					throw new AppException(Message.getMessage(param.getLang(), "iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.rawMaterial", new String[]{notRawMaterails.toString()}));
				int lineId = 1;
				for(ScmPurReceiveEntry2 scmPurReceiveEntry : scmPurReceiveEntryList){
					if (StringUtils.equals("I", scmPurReceive.getStatus())) {
						scmPurReceiveEntry.setLineId(lineId);
					}
					scmPurReceiveEntry.setPvId(scmPurReceive.getId());
					scmPurReceiveEntry.setInvOrgUnitNo(scmPurReceive.getOrgUnitNo());
					scmPurReceiveEntry.setPurOrgUnitNo(scmPurReceive.getPurOrgUnitNo());
					scmPurReceiveEntry.setBuyerId(scmPurReceive.getBuyerId());
					scmPurReceiveEntry.setPurGroupId(scmPurReceive.getPurGroupId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmPurReceiveEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmPurReceiveEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					//让步接收数量=让步接收比例*不合格数量
					if(scmPurReceiveEntry.getConcessiveRecRate().compareTo(BigDecimal.ZERO)>0 ) {
						if (StringUtils.equals("2", rebateType) || scmPurReceive.isUnified()) {
							scmPurReceiveEntry.setConcessiveRecQty(scmPurReceiveEntry.getUnqualifiedQty().multiply(scmPurReceiveEntry.getConcessiveRecRate()));
						}else {
							scmPurReceiveEntry.setConcessiveRecQty(scmPurReceiveEntry.getUnqualifiedQty());
						}
					}else {
						if (StringUtils.equals("2", rebateType) || scmPurReceive.isUnified()) {
							scmPurReceiveEntry.setConcessiveRecQty(BigDecimal.ZERO);
						}else {
							scmPurReceiveEntry.setConcessiveRecQty(scmPurReceiveEntry.getUnqualifiedQty());
						}
					}
					BigDecimal purConvRate = ScmMaterialUtil.getConvRate(scmPurReceiveEntry.getItemId(), scmPurReceiveEntry.getPurUnit(), param);//采购单位转换系数
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmPurReceiveEntry.getItemId(), scmPurReceiveEntry.getUnit(), param);//库存单位转换系数
					if(scmPurReceive.isUnified()){
						//统配以配送数量进行入库
						scmPurReceiveEntry.setInvQty(scmPurReceiveEntry.getDeliveryQty().multiply(purConvRate).divide(invConvRate,4,BigDecimal.ROUND_HALF_UP));	//入库数量
					}else{
						//直配以收货数量-不合格数据+让步接收数量
						scmPurReceiveEntry.setInvQty((scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty()).add(scmPurReceiveEntry.getConcessiveRecQty())).multiply(purConvRate).divide(invConvRate,4,BigDecimal.ROUND_HALF_UP));	//入库数量
					}
					scmPurReceiveEntry.setBaseQty(scmPurReceiveEntry.getInvQty().multiply(invConvRate));
					lineId = lineId+1;
					if (scmPurReceive.getEditType()>0) {
						int amtPrec = Integer.parseInt(sysParamBiz.getValue(param.getControlUnitNo(), "SCM_AmtPrecision", "2", param));
						int pricePrec = Integer.parseInt(sysParamBiz.getValue(param.getControlUnitNo(), "SCM_PricePrecision", "2", param));
						int qtyPrec = Integer.parseInt(sysParamBiz.getValue(param.getControlUnitNo(), "SCM_QtyPrecision", "2", param));
						String priceMode = sysParamBiz.getValue(param.getControlUnitNo(), "SCM_PriceMode", "1", param);
						String receiveEditPrice = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_ReceiveEditPrice", "1", param);
						if (scmPurReceive.getEditType()==2) {//改数量
							if(scmPurReceive.isUnified()){
								//统配
								if(StringUtils.equals("1", priceMode)){
	                                BigDecimal d = (scmPurReceiveEntry.getDeliveryQty()).multiply(scmPurReceiveEntry.getTaxPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
	                                scmPurReceiveEntry.setTaxAmt(d);
	                                scmPurReceiveEntry.setAmt(d.divide((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate()),amtPrec,RoundingMode.HALF_UP));
	                            }else{
	                            	BigDecimal b = (scmPurReceiveEntry.getDeliveryQty()).multiply(scmPurReceiveEntry.getPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
	                                scmPurReceiveEntry.setAmt(b);
	                                scmPurReceiveEntry.setTaxAmt(b.multiply((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate())).setScale(amtPrec,RoundingMode.HALF_UP));
	                            }
		                    }else{
		                        //直配
		                    	if(StringUtils.equals("1", priceMode)){
                                    BigDecimal d = null;
                                    if (StringUtils.equals("2",rebateType)){
                                        d = (scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty()).add(scmPurReceiveEntry.getConcessiveRecQty())).multiply(scmPurReceiveEntry.getTaxPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
                                    }else {
                                        d = ((scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty())).multiply(scmPurReceiveEntry.getTaxPrice()).add(scmPurReceiveEntry.getConcessiveRecQty().multiply(scmPurReceiveEntry.getTaxPrice()).multiply(scmPurReceiveEntry.getConcessiveRecRate()))).setScale(amtPrec, RoundingMode.HALF_UP);
                                    }
                                    scmPurReceiveEntry.setTaxAmt(d);
                                    scmPurReceiveEntry.setAmt(d.divide((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate()),amtPrec,RoundingMode.HALF_UP));
                                }else{
                                    BigDecimal b = null;
                                    if (StringUtils.equals("2",rebateType)){
                                        b = (scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty()).add(scmPurReceiveEntry.getConcessiveRecQty())).multiply(scmPurReceiveEntry.getPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
                                    }else {
                                        b = ((scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty())).multiply(scmPurReceiveEntry.getPrice()).add(scmPurReceiveEntry.getConcessiveRecQty().multiply(scmPurReceiveEntry.getPrice()).multiply(scmPurReceiveEntry.getConcessiveRecRate()))).setScale(amtPrec, RoundingMode.HALF_UP);
                                    }
                                    scmPurReceiveEntry.setAmt(b);
                                    scmPurReceiveEntry.setTaxAmt(b.multiply((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate())).setScale(amtPrec,RoundingMode.HALF_UP));
                                }
		                    }	
						}else if (scmPurReceive.getEditType()==1) {//改金额
							if(StringUtils.equals("1", priceMode)){
								if(scmPurReceive.isUnified()){
                                    //统配
                                    if((BigDecimal.ZERO).compareTo(scmPurReceiveEntry.getDeliveryQty())!=0){
                                        BigDecimal c = (scmPurReceiveEntry.getTaxAmt()).divide(scmPurReceiveEntry.getDeliveryQty(),pricePrec,RoundingMode.HALF_UP);
                                        scmPurReceiveEntry.setTaxPrice(c);
                                        BigDecimal a = (scmPurReceiveEntry.getTaxPrice()).divide((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate()),pricePrec,RoundingMode.HALF_UP);
                                        scmPurReceiveEntry.setPrice(a);
                                    }
                                }else{
                                    //直配
                                    if (scmPurReceiveEntry.getPriceBillId() > 0 && (!StringUtils.equals("Y", receiveEditPrice) 
                                            || !StringUtils.contains("6,7",scmPurReceiveEntry.getRefPriceStatus()))) {// 有来源单, 修改的是数量
                                    	if (BigDecimal.ZERO.compareTo(scmPurReceiveEntry.getTaxPrice())!= 0) {
                                    		BigDecimal b = null;
                                            if (StringUtils.equals("2",rebateType)) {
                                                b = (scmPurReceiveEntry.getTaxAmt()).divide(scmPurReceiveEntry.getTaxPrice(),qtyPrec,RoundingMode.HALF_UP).add(scmPurReceiveEntry.getUnqualifiedQty()).subtract(scmPurReceiveEntry.getConcessiveRecQty());
                                                scmPurReceiveEntry.setQty(b);
                                            }else {
                                                b = (scmPurReceiveEntry.getTaxAmt()).divide(scmPurReceiveEntry.getTaxPrice(),qtyPrec,RoundingMode.HALF_UP).add(scmPurReceiveEntry.getUnqualifiedQty()).subtract(scmPurReceiveEntry.getConcessiveRecQty().multiply(scmPurReceiveEntry.getConcessiveRecRate()));
                                                scmPurReceiveEntry.setQty(b);
                                            }
										}
                                    } else { // 无来源单, 修改的是单价
                                    	if (BigDecimal.ZERO.compareTo(scmPurReceiveEntry.getQty())!= 0) {
                                    		if (StringUtils.equals("2",rebateType)) {
                                                BigDecimal c = (scmPurReceiveEntry.getTaxAmt()).divide((scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty()).add(scmPurReceiveEntry.getConcessiveRecQty())), pricePrec, RoundingMode.HALF_UP);
                                                scmPurReceiveEntry.setTaxPrice(c);
                                            }else {
                                                BigDecimal c = (scmPurReceiveEntry.getTaxAmt()).divide((scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty()).add(scmPurReceiveEntry.getConcessiveRecQty().multiply(scmPurReceiveEntry.getConcessiveRecRate()))), pricePrec, RoundingMode.HALF_UP);
                                                scmPurReceiveEntry.setTaxPrice(c);
                                            }
										}
                                    	BigDecimal a = (scmPurReceiveEntry.getTaxPrice()).divide((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate()),pricePrec,RoundingMode.HALF_UP);
                                        scmPurReceiveEntry.setPrice(a);
                                    }
                                }
                                BigDecimal c = (scmPurReceiveEntry.getTaxAmt()).divide((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate()),amtPrec,RoundingMode.HALF_UP);
                                scmPurReceiveEntry.setAmt(c);
							}else {
								if(scmPurReceive.isUnified()){
                                    //统配
									if((BigDecimal.ZERO).compareTo(scmPurReceiveEntry.getDeliveryQty())!=0){
	                                    BigDecimal b = (scmPurReceiveEntry.getAmt()).divide(scmPurReceiveEntry.getDeliveryQty(), pricePrec, RoundingMode.HALF_UP);
	                                    scmPurReceiveEntry.setPrice(b);
	                                    BigDecimal c = (scmPurReceiveEntry.getAmt()).divide(scmPurReceiveEntry.getDeliveryQty(),pricePrec,RoundingMode.HALF_UP);
                                        scmPurReceiveEntry.setTaxPrice(c);
									}
                                }else{
                                	if (scmPurReceiveEntry.getPriceBillId() > 0 && (!StringUtils.equals("Y", receiveEditPrice) 
                                            || !StringUtils.contains("6,7",scmPurReceiveEntry.getRefPriceStatus()))) {// 有来源单, 修改的是数量
                                		if((BigDecimal.ZERO).compareTo(scmPurReceiveEntry.getPrice())!=0){
                                			if (StringUtils.equals("2",rebateType)) {
                                                BigDecimal b = (scmPurReceiveEntry.getAmt()).divide(scmPurReceiveEntry.getPrice(), qtyPrec, RoundingMode.HALF_UP).add(scmPurReceiveEntry.getConcessiveRecQty()).subtract(scmPurReceiveEntry.getUnqualifiedQty());
                                                scmPurReceiveEntry.setQty(b);
                                            }else {
                                            	BigDecimal b = (scmPurReceiveEntry.getAmt()).divide(scmPurReceiveEntry.getPrice(),qtyPrec,RoundingMode.HALF_UP).add(scmPurReceiveEntry.getUnqualifiedQty()).subtract(scmPurReceiveEntry.getConcessiveRecQty().multiply(scmPurReceiveEntry.getConcessiveRecRate()));
                                                scmPurReceiveEntry.setQty(b);
                                            }
                                		}
                                	}else {
                                		 //直配
                                		if((BigDecimal.ZERO).compareTo(scmPurReceiveEntry.getQty())!=0){
                                			if (StringUtils.equals("2",rebateType)){
                                                BigDecimal b = (scmPurReceiveEntry.getAmt()).divide((scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty()).add(scmPurReceiveEntry.getConcessiveRecQty())), pricePrec, RoundingMode.HALF_UP);
                                                scmPurReceiveEntry.setPrice(b);
                                            }else {
                                            	BigDecimal b = (scmPurReceiveEntry.getAmt()).divide(scmPurReceiveEntry.getQty()).add(scmPurReceiveEntry.getUnqualifiedQty()).subtract(scmPurReceiveEntry.getConcessiveRecQty().multiply(scmPurReceiveEntry.getConcessiveRecRate()));
                                                scmPurReceiveEntry.setPrice(b);
                                            }
                                		}
									}
                                }
                                BigDecimal c = (scmPurReceiveEntry.getPrice()).multiply((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate())).setScale(pricePrec, RoundingMode.HALF_UP);
                                scmPurReceiveEntry.setTaxPrice(c);
                                BigDecimal d = (scmPurReceiveEntry.getAmt()).multiply((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate())).setScale(amtPrec, RoundingMode.HALF_UP);
                                scmPurReceiveEntry.setTaxAmt(d);
							}
						}else if(scmPurReceive.getEditType()==3){//改单价
							if(StringUtils.equals("1", priceMode)){
								BigDecimal a = (scmPurReceiveEntry.getTaxPrice()).divide((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate()),pricePrec,RoundingMode.HALF_UP);
                                scmPurReceiveEntry.setPrice(a);
								if(scmPurReceive.isUnified()){
                                    //统配
                                    BigDecimal d = (scmPurReceiveEntry.getDeliveryQty()).multiply(scmPurReceiveEntry.getTaxPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
                                    scmPurReceiveEntry.setTaxAmt(d);
                                }else{
                                    //直配
                                	BigDecimal d = null;
                                	if (StringUtils.equals("2",rebateType)){
                                		d = (scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty()).add(scmPurReceiveEntry.getConcessiveRecQty())).multiply(scmPurReceiveEntry.getTaxPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
                                	}else {
                                        d = ((scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty())).multiply(scmPurReceiveEntry.getTaxPrice()).add(scmPurReceiveEntry.getConcessiveRecQty().multiply(scmPurReceiveEntry.getTaxPrice()).multiply(scmPurReceiveEntry.getConcessiveRecRate()))).setScale(amtPrec, RoundingMode.HALF_UP);
									}
                                    scmPurReceiveEntry.setTaxAmt(d);
                                }
                                BigDecimal b = (scmPurReceiveEntry.getTaxPrice()).divide((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate()), pricePrec, RoundingMode.HALF_UP);
                                scmPurReceiveEntry.setPrice(b);
                                BigDecimal c = (scmPurReceiveEntry.getTaxAmt()).divide((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate()), amtPrec, RoundingMode.HALF_UP);
                                scmPurReceiveEntry.setAmt(c);
							}else {
								BigDecimal c = (scmPurReceiveEntry.getPrice()).multiply((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate())).setScale(pricePrec,RoundingMode.HALF_UP);
	                            scmPurReceiveEntry.setTaxPrice(c);
	                            if(scmPurReceive.isUnified()){
	                                //统配
	                                BigDecimal b = (scmPurReceiveEntry.getDeliveryQty()).multiply(scmPurReceiveEntry.getPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
	                                scmPurReceiveEntry.setAmt(b);
	                                BigDecimal d = (scmPurReceiveEntry.getAmt()).multiply((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate())).setScale(amtPrec,RoundingMode.HALF_UP);
	                                scmPurReceiveEntry.setTaxAmt(d);
	                            }else{
	                                //直配
	                            	 BigDecimal b = null;
	                            	if (StringUtils.equals("2",rebateType)){
	                            		b = (scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty()).add(scmPurReceiveEntry.getConcessiveRecQty())).multiply(scmPurReceiveEntry.getPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
                                	}else {
                                        b = ((scmPurReceiveEntry.getQty().subtract(scmPurReceiveEntry.getUnqualifiedQty())).multiply(scmPurReceiveEntry.getPrice()).add(scmPurReceiveEntry.getConcessiveRecQty().multiply(scmPurReceiveEntry.getPrice()).multiply(scmPurReceiveEntry.getConcessiveRecRate()))).setScale(amtPrec, RoundingMode.HALF_UP);
									}
	                                scmPurReceiveEntry.setAmt(b);
	                                BigDecimal d = (scmPurReceiveEntry.getAmt()).multiply((BigDecimal.ONE).add(scmPurReceiveEntry.getTaxRate())).setScale(amtPrec,RoundingMode.HALF_UP);
	                                scmPurReceiveEntry.setTaxAmt(d);
	                            }
							}
						}
					}
				}
				scmPurReceiveEntryBiz.update(scmPurReceive, scmPurReceiveEntryList, ScmPurReceiveEntry2.FN_PVID, param);
			}
		}
	}
	
	private ScmPurReceive2 updateStatus(ScmPurReceive2 scmPurReceive, Param param) throws AppException {
		if(scmPurReceive != null){
			ScmPurReceive2 scmPurReceive2 = this.updateDirect(scmPurReceive, param);
			if(scmPurReceive2 != null){
				scmPurReceiveEntryBiz.updateRowStatusByPvId(scmPurReceive2.getId(), scmPurReceive2.getStatus(), scmPurReceive2.getChecker(), scmPurReceive2.getCheckDate(), param);
				return scmPurReceive2;
			}
		}
		return null;
	}

	@Override
	public void doDelPurReceive(ScmPurReceive2 scmPurReceive, Param param) throws AppException {
		ScmPurReceive2 scmPurReceive2 = this.selectByBillCode(scmPurReceive.getPvNo(), param);
		if(scmPurReceive2!=null){
			try {
				this.delete(scmPurReceive2, param);
			} catch (AppException e) {
				throw e;
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
	}

	@Override
	public ScmPurReceive2 doSubmitPurReceive(ScmPurReceive2 scmPurReceive, Param param) throws AppException {
		String receiveValidatePeriodValid = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_ReceiveValidatePeriodValid", "N", param);
		ScmPurReceive2 receive = null;
		if(scmPurReceive.getId()>0){
			receive = this.selectDirect(scmPurReceive.getId(), param);
		}else{
			receive = this.selectByBillCode(scmPurReceive.getPvNo(), param);
		}
		if(receive!=null){
			String openCostUse = sysParamBiz.getValue(receive.getFinOrgUnitNo(), "SCM_OpenCostUse", "N", param);
			this.setConvertMap(receive, param);
			if (StringUtils.equals("Y", receiveValidatePeriodValid)) {
				List<ScmPurReceiveEntry2> scmPurReceiveEntry2s = scmPurReceiveEntryBiz.selectByPvId(receive.getId(),
						param);
				if (scmPurReceiveEntry2s != null && !scmPurReceiveEntry2s.isEmpty()) {
					for (ScmPurReceiveEntry2 scmPurReceiveEntry2 : scmPurReceiveEntry2s) {
						if (BigDecimal.ZERO.compareTo(scmPurReceiveEntry2.getQty()) < 0
								&& StringUtils.equals("Y", scmPurReceiveEntry2.getPeriodValid())
								&& scmPurReceiveEntry2.getProductDate() == null) {
							throw new AppException(Message.getMessage(param.getLang(),"iscm.purchasemanage.purchasebusiness.ScmPurReceive.error.validatePeriodValid",new String[] { receive.getPvNo(), scmPurReceiveEntry2.getItemName() }));
						}
						if (StringUtils.equals("Y", openCostUse)) {
							if (StringUtils.isNotBlank(scmPurReceiveEntry2.getUseOrgUnitNo())) {
								if (!(scmPurReceiveEntry2.getCostUseTypeId()>0)){
									throw new AppException(Message.getMessage(param.getLang(),"iscm.purchasemanage.purchasebusiness.ScmPurReceive.error.validateCostUseType",new String[] { receive.getPvNo(), scmPurReceiveEntry2.getItemName() }));
		                        }
							}
						}
					}
				}
			}
			if(!receive.getStatus().equals("I")){
				throw new AppException("iscm.purchasemanage.error.commit");
			}else if(receive.getStatus().equals("I")){
				Page page=new Page();
				page.setModelClass(ScmPurReceiveEntry2.class);
				page.setShowCount(Integer.MAX_VALUE);
				page.getParam().put(ScmPurReceiveEntry2.FN_PVID,new QueryParam(ScmPurReceiveEntry2.FN_PVID, QueryParam.QUERY_EQ,String.valueOf(receive.getId())));
				page.setSqlCondition("(scmpurreceiveentry.wareHouseId=0 and ifnull(scmpurreceiveentry.useOrgUnitNo,'')='')");
				List<ScmPurReceiveEntry2> scmPurReceiveEntryList = scmPurReceiveEntryBiz.findPage(page, param);
				if(scmPurReceiveEntryList!=null && !scmPurReceiveEntryList.isEmpty())
					throw new AppException(Message.getMessage(param.getLang(),"iscm.purchasemanage.purchasebusiness.ScmPurReceive.error.warehouseAnddeptisnull",new String[] {receive.getPvNo()}));
				BillType2 billType = billTypeBiz.selectByOrgAndCode(receive.getFinOrgUnitNo(), "PurReceive", param);
				boolean needAudit = false;
				if(billType!=null && billType.isNeedAudit())
					needAudit = true;
				if(needAudit){			
					CommonBean bean = new CommonBean();
					List<ScmPurReceive2> scmPurReceiveList = new ArrayList<>();
					scmPurReceiveList.add(receive);
					bean.setList(scmPurReceiveList);
					bean.setList2(scmPurReceiveEntryList);
					String processInstanceId = ActivityUtil.startProcessInstance(bean, billType.getId(), billType.getWorkFlowNo(), param);
					receive.setWorkFlowNo(processInstanceId);
					ActivityUtil activityUtil = new ActivityUtil();
					//判断当前流程是否结束
					boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
					if(isCompleteTask) {
						if(param.getUsrCode()!=null ){
							receive.setChecker(param.getUsrCode());
							receive.setSubmitter(param.getUsrCode());
						}
						receive.setCheckDate(CalendarUtil.getDate(param));
						receive.setSubmitDate(CalendarUtil.getDate(param));
						if(receive.getReceiveTime()==null)
							receive.setReceiveTime(CalendarUtil.getDate(param));
						receive.setStatus("A");
						receive.setPendingUsr("");
					}else {
						if(param.getUsrCode()!=null ){
							receive.setSubmitter(param.getUsrCode());
						}
						receive.setSubmitDate(CalendarUtil.getDate(param));
						receive.setStatus("D");
						String usrList= ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId,param);
						if(StringUtils.isNotBlank(usrList)) {
							receive.setPendingUsr(StringUtils.left(usrList,250));
							scmBillPendingBiz.addPendingBill(usrList, receive, param);
							PurReceiveParams purReceiveParams = new PurReceiveParams();
							purReceiveParams.setPvNo(receive.getPvNo());
							AuditMsgUtil.sendAuditMsg(billType.getBillCode(),receive,purReceiveParams, usrList, param);
						}else {
							receive.setPendingUsr("");
						}
					}
				}else{
					if(param.getUsrCode()!=null ){
						receive.setChecker(param.getUsrCode());
						receive.setSubmitter(param.getUsrCode());
					}
					receive.setCheckDate(CalendarUtil.getDate(param));
					receive.setSubmitDate(CalendarUtil.getDate(param));
					if(receive.getReceiveTime()==null)
						receive.setReceiveTime(CalendarUtil.getDate(param));
					receive.setStatus("A");
					receive.setPendingUsr("");
				}
				try {
					this.updateStatus(receive, param);
				} catch (Exception e) {
					throw e;
				}
				if(StringUtils.contains("A,B", receive.getStatus())) {
					//通过或部分通过时检查是否自动下达
					if(billType!=null && billType.isAutoRelease()) {
						this.doRelease(receive, param);
					}
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return receive;
	}

	@Override
	public ScmPurReceive2 doUnSubmitPurReceive(ScmPurReceive2 scmPurReceive, Param param) throws AppException {
		ScmPurReceive2 receive = null;
		if(scmPurReceive.getId()>0){
			receive = this.selectDirect(scmPurReceive.getId(), param);
		}else{
			receive=this.selectByBillCode(scmPurReceive.getPvNo(), param);
		}
		if(receive!=null){
			BillType2 billType = billTypeBiz.selectByOrgAndCode(receive.getFinOrgUnitNo(), "PurReceive", param);
			boolean needAudit = false;
			if(billType!=null && billType.isNeedAudit() && StringUtils.isNotBlank(receive.getWorkFlowNo()))
				needAudit = true;
			
			//单据设置审批流程，但设置没有审批节点，状态为通过，可以取消通过
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ScmBillPending.FN_BILLID, new QueryParam(ScmBillPending.FN_BILLID, QueryParam.QUERY_EQ, String.valueOf(receive.getId())));
			map.put(ScmBillPending.FN_BILLTYPE, new QueryParam(ScmBillPending.FN_BILLTYPE, QueryParam.QUERY_EQ, billType.getBillCode()));
			List<ScmBillPending> scmBillPendingList = scmBillPendingBiz.findAll(map, param);
			
			if(needAudit){
				if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
					if(!StringUtils.equals(receive.getStatus(),"A"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				} else {
					if(!StringUtils.equals(receive.getStatus(),"D"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				}
			}else{
				if(!StringUtils.equals(receive.getStatus(),"A"))
					throw new AppException("iscm.purchasemanage.error.cancelCommit");
			} 
			if(receive.getStatus().equals("A") || receive.getStatus().equals("D")){
				receive.setChecker(null);
				receive.setSubmitter(null);
				receive.setCheckDate(null);
				receive.setSubmitDate(null);
				receive.setStatus("I");
				receive.setPendingUsr("");
				try {
					this.updateStatus(receive, param);
					scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),receive, param);
				} catch (Exception e) {
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return receive;
	}
	
	@Override
	public List<ScmPurReceive2> queryPurReceiveList(ScmPurReceiveAdvQuery scmPurReceiveAdvQuery, int pageNum,Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(ScmPurReceive2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		page.setModel(scmPurReceiveAdvQuery);	//findPage时底层会触发doAdvQuery，增加查询条件
		page.setSqlCondition("(scmpurreceive.unified=0 or Exists(Select 1 From scmpurcheck Where id=scmpurreceive.ckId And flag=1))");
		
		return this.findPage(page, param);
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmPurReceiveAdvQuery) {
				ScmPurReceiveAdvQuery scmPurReceiveAdvQuery = (ScmPurReceiveAdvQuery) page.getModel();
				if(StringUtils.isNotBlank(scmPurReceiveAdvQuery.getPvNo())){
					page.getParam().put(ScmPurReceive2.FN_PVNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." +ScmPurReceive2.FN_PVNO, QueryParam.QUERY_LIKE,"%"+scmPurReceiveAdvQuery.getPvNo()+"%"));
				}
				if(scmPurReceiveAdvQuery.getVendorId()>0){
					page.getParam().put(ScmPurReceive2.FN_VENDORID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." +ScmPurReceive2.FN_VENDORID, QueryParam.QUERY_EQ,String.valueOf(scmPurReceiveAdvQuery.getVendorId())));
				}else{
					if(StringUtils.isNotBlank(scmPurReceiveAdvQuery.getVendorCode())){
						Scmsupplier2 scmsupplier = scmsupplierBiz.selectByCode(scmPurReceiveAdvQuery.getVendorCode(), param);
						if(scmsupplier!=null){
							page.getParam().put(ScmPurReceive2.FN_VENDORID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." +ScmPurReceive2.FN_VENDORID, QueryParam.QUERY_EQ,String.valueOf(scmsupplier.getId())));
						}
					}
				}
				if(StringUtils.isNotBlank(scmPurReceiveAdvQuery.getReceiver())){
					page.getParam().put(ScmPurReceive2.FN_RECEIVER,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." +ScmPurReceive2.FN_RECEIVER, QueryParam.QUERY_EQ,scmPurReceiveAdvQuery.getReceiver()));
				}
				if(scmPurReceiveAdvQuery.getRecDateFrom()!=null){
					if(scmPurReceiveAdvQuery.getRecDateTo()!=null) {
						page.getParam().put(ScmPurReceive2.FN_RECEIVEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." +ScmPurReceive2.FN_RECEIVEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmPurReceiveAdvQuery.getRecDateFrom()),FormatUtils.fmtDate(scmPurReceiveAdvQuery.getRecDateTo())));
					}else {
						page.getParam().put(ScmPurReceive2.FN_RECEIVEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." +ScmPurReceive2.FN_RECEIVEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmPurReceiveAdvQuery.getRecDateFrom())));
					}
				}else if(scmPurReceiveAdvQuery.getRecDateTo()!=null) {
					page.getParam().put(ScmPurReceive2.FN_RECEIVEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." +ScmPurReceive2.FN_RECEIVEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmPurReceiveAdvQuery.getRecDateTo())));
				}
				if(scmPurReceiveAdvQuery.getCreateDateFrom()!=null){
					if(scmPurReceiveAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmPurReceive2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." +ScmPurReceive2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmPurReceiveAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurReceiveAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmPurReceive2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." +ScmPurReceive2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmPurReceiveAdvQuery.getCreateDateFrom())));
					}
				}else if(scmPurReceiveAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmPurReceive2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." +ScmPurReceive2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurReceiveAdvQuery.getCreateDateTo(),1))));
				}
				if(StringUtils.isNotBlank(scmPurReceiveAdvQuery.getPurOrgUnitNo())){
					page.getParam().put(ScmPurReceive2.FN_PURORGUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." +ScmPurReceive2.FN_PURORGUNITNO, QueryParam.QUERY_EQ,scmPurReceiveAdvQuery.getPurOrgUnitNo()));
				}
				if(StringUtils.isNotBlank(scmPurReceiveAdvQuery.getStatus())){
					String status[] = StringUtils.split(scmPurReceiveAdvQuery.getStatus(), ",");
					StringBuffer statusBuffer = new StringBuffer("");
					for(String sta:status){
						if(StringUtils.isNotBlank(statusBuffer.toString()))
							statusBuffer.append(",");
						statusBuffer.append("'").append(sta).append("'");
					}
					if(StringUtils.isNotBlank(statusBuffer.toString())){
						page.getParam().put(ScmPurReceive2.FN_STATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." + ScmPurReceive2.FN_STATUS, QueryParam.QUERY_IN,statusBuffer.toString()));
					}
				}
				if(StringUtils.isNotBlank(scmPurReceiveAdvQuery.getDeptOrWarehs())){
					if(StringUtils.equals("W", scmPurReceiveAdvQuery.getStoreType())) {
						if(StringUtils.isNotBlank(page.getSqlCondition())){
							page.setSqlCondition((page.getSqlCondition()+" and  scmpurreceive.id in (Select pvId From scmpurreceiveentry Where wareHouseId="+scmPurReceiveAdvQuery.getDeptOrWarehs()+")"));
						}else{
							page.setSqlCondition("scmpurreceive.id in (Select pvId From scmpurreceiveentry Where wareHouseId="+scmPurReceiveAdvQuery.getDeptOrWarehs()+")");
						}
					}else {
						if(StringUtils.isNotBlank(page.getSqlCondition())){
							page.setSqlCondition((page.getSqlCondition()+" and  scmpurreceive.id in (Select pvId From scmpurreceiveentry Where useOrgUnitNo='"+scmPurReceiveAdvQuery.getDeptOrWarehs()+"')"));
						}else{
							page.setSqlCondition("scmpurreceive.id in (Select pvId From scmpurreceiveentry Where useOrgUnitNo='"+scmPurReceiveAdvQuery.getDeptOrWarehs()+"')");
						}
					}
				}
				if (StringUtils.isNotBlank(scmPurReceiveAdvQuery.getPoNo())) {
					page.getParam().put(ScmPurReceive2.FN_PONOS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." +ScmPurReceive2.FN_PONOS, QueryParam.QUERY_LIKE,"%"+scmPurReceiveAdvQuery.getPoNo()+"%"));
				}
			}
		}
	}

	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page,HashMap<String, Object> map, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmPurReceiveAdvQuery) {
				ScmPurReceiveAdvQuery scmPurReceiveAdvQuery = (ScmPurReceiveAdvQuery) page.getModel();
				if(scmPurReceiveAdvQuery.isFromInterface())
					map.put("sortByDate", "Y");
			}
		}
		return map;
	}
	@Override
	public ScmPurReceive2 queryPurReceive(ScmPurReceive2 scmPurReceive,Param param) throws AppException {
		ScmPurReceive2 scmPurReceive2 = this.selectByBillCode(scmPurReceive.getPvNo(), param);
		if(scmPurReceive2!=null){
			setConvertMap(scmPurReceive2, param);
			scmPurReceive2.setScmPurReceiveEntryList(scmPurReceiveEntryBiz.selectByPvId(scmPurReceive2.getId(), param));
			
			//增加待审人
			ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmPurReceive2.getId(), "PurReceive",param);
			if (scmBillPendingUsr != null) {
				scmPurReceive2.setPendingUsr(scmBillPendingUsr.getUsrCodes());
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmPurReceive2;
	}

	@Override
	public ScmPurReceive2 doAddPurReceive(PurReceiveAddParams purReceiveAddParams, Param param) throws AppException {
		CommonBean bean = new CommonBean();
		List<ScmPurReceive2> scmPurReceiveList = new ArrayList<>();
		ScmPurReceive2 scmPurReceive=new ScmPurReceive2();
		try {
			BeanUtils.copyProperties(purReceiveAddParams, scmPurReceive);
			if(scmPurReceive.getReceiveDate() != null){
				scmPurReceive.setReceiveDate(FormatUtils.parseDate(FormatUtils.fmtDate(scmPurReceive.getReceiveDate())));
			}
			if(StringUtils.isNotBlank(purReceiveAddParams.getVendorCode())){
				Scmsupplier2 scmsupplier = scmsupplierBiz.selectByCode(purReceiveAddParams.getVendorCode(), param);
				if(scmsupplier==null)
					throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurReceiveBizImpl.error.suppliernotexists");
				scmPurReceive.setVendorId(scmsupplier.getId()); 
			}
			if(StringUtils.isNotBlank(purReceiveAddParams.getBuyerCode())){
				ScmPurBuyer2 scmPurBuyer = scmPurBuyerBiz.selectByCode(purReceiveAddParams.getBuyerCode(), param);
				if(scmPurBuyer==null)
					throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurReceiveBizImpl.error.suppliernotexists");
				scmPurReceive.setBuyerId(scmPurBuyer.getId());
			}
			List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, param.getOrgUnitNo(), false, null, param);
			if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
			}
			scmPurReceive.setCreateDate(CalendarUtil.getDate(param));
			scmPurReceive.setCreator(param.getUsrCode());

			scmPurReceiveList.add(scmPurReceive);
			bean.setList(scmPurReceiveList);
			List<PurReceiveDetailParams> detailList = purReceiveAddParams.getDetailList();
			if(detailList!=null && !detailList.isEmpty()){
				StringBuffer itemNos = new StringBuffer("");
				for(PurReceiveDetailParams detailParams:detailList){
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
		        argList.add("purOrgUnitNo="+purReceiveAddParams.getPurOrgUnitNo());
		        argList.add("invOrgUnitNo="+param.getOrgUnitNo());
		        argList.add("controlUnitNo=" + param.getControlUnitNo());
		        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "findByPurAndInvAllPage", param);
				List<ScmPurReceiveEntry2> scmPurReceiveEntryList = new ArrayList<>();
				for(PurReceiveDetailParams detailParams:detailList){
					ScmPurReceiveEntry2 scmPurReceiveEntry = new ScmPurReceiveEntry2(true);
					BeanUtils.copyProperties(detailParams, scmPurReceiveEntry);
					for(ScmMaterial2 scmMaterial:scmMaterialList){
						if(StringUtils.equals(scmMaterial.getItemNo(), detailParams.getItemNo())){
							scmPurReceiveEntry.setItemId(scmMaterial.getId());
							scmPurReceiveEntry.setPurUnit(scmMaterial.getPurUnitId());
							scmPurReceiveEntry.setBaseUnit(scmMaterial.getBaseUnitId());
							scmPurReceiveEntry.setUnit(scmMaterial.getUnitId());
							scmPurReceiveEntry.setPieUnit(scmMaterial.getPieUnitId());
							break;
						}
					}
					scmPurReceiveEntry.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
					scmPurReceiveEntry.setRowStatus("I");
					scmPurReceiveEntry.setInvOrgUnitNo(param.getOrgUnitNo());
					if(StringUtils.isNotBlank(detailParams.getWareHouseNo())){
						ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectByWhNo(param.getOrgUnitNo(),detailParams.getWareHouseNo(),param);
						if(scmInvWareHouse==null)
							throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemisnull");
						scmPurReceiveEntry.setWareHouseId(scmInvWareHouse.getId());
					}
					scmPurReceiveEntry.setBalanceSupplierId(scmPurReceive.getVendorId());
					if(BigDecimal.ZERO.compareTo(scmPurReceiveEntry.getPrice())==0){
						String priceBillId = "";
						if (StringUtils.contains("6,7",scmPurReceiveEntry.getRefPriceStatus())) {
							priceBillId = String.valueOf(scmPurReceiveEntry.getPriceBillId());
						}
						ScmMaterialPrice scmMaterialPrice = ScmMaterialUtil.getMaterialPrice(purReceiveAddParams.getPurOrgUnitNo(),scmPurReceive.getVendorId(), String.valueOf(scmPurReceiveEntry.getItemId()), scmPurReceiveEntry.getPurUnit(),scmPurReceive.getReceiveDate(),orgCompanyList.get(0).getOrgUnitNo(),priceBillId, param);
						if(scmMaterialPrice!=null){
							scmPurReceiveEntry.setPrice(scmMaterialPrice.getPrice());
							scmPurReceiveEntry.setAmt(scmPurReceiveEntry.getQty().multiply(scmMaterialPrice.getPrice()));
							scmPurReceiveEntry.setTaxPrice(scmPurReceiveEntry.getPrice().multiply(BigDecimal.ONE.add(scmPurReceiveEntry.getTaxRate())));
							scmPurReceiveEntry.setTaxAmt(scmPurReceiveEntry.getAmt().multiply(BigDecimal.ONE.add(scmPurReceiveEntry.getTaxRate())));
							scmPurReceiveEntry.setPriceBillId(scmMaterialPrice.getPriceBillId());
							scmPurReceiveEntry.setRefPriceStatus(scmMaterialPrice.getRefPriceStatus());
						}else{
							scmPurReceiveEntry.setPrice(BigDecimal.ZERO);
							scmPurReceiveEntry.setAmt(BigDecimal.ZERO);
						}
					}
					scmPurReceiveEntryList.add(scmPurReceiveEntry);
				}
				bean.setList2(scmPurReceiveEntryList);
			}
			this.add(bean, param);
		} catch (Exception e) {
			throw e;
		}
		
		return scmPurReceive;
	}

	@Override
	public void doEditPurReceive(PurReceiveEditParams purReceiveEditParams,Param param) throws AppException {
		if(purReceiveEditParams!=null){
			CommonBean bean = new CommonBean();
			
			ScmPurReceive2 scmPurReceive = this.selectByBillCode(purReceiveEditParams.getPvNo(), param);
			List<ScmPurReceive2> scmPurReceiveList =new ArrayList();
			if(scmPurReceive!=null){
				//更新主表数据
				scmPurReceiveList.add(scmPurReceive);
				BeanUtils.copyProperties(purReceiveEditParams, scmPurReceive);
				if(scmPurReceive.getReceiveDate() != null){
					scmPurReceive.setReceiveDate(FormatUtils.parseDate(FormatUtils.fmtDate(scmPurReceive.getReceiveDate())));
				}
				if(StringUtils.isNotBlank(purReceiveEditParams.getVendorCode())){
					Scmsupplier2 scmsupplier = scmsupplierBiz.selectByCode(purReceiveEditParams.getVendorCode(), param);
					if(scmsupplier==null)
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurReceiveBizImpl.error.suppliernotexists");
					scmPurReceive.setVendorId(scmsupplier.getId()); 
				}
				if(StringUtils.isNotBlank(purReceiveEditParams.getBuyerCode())){
					Param purParam = new Param();
					BeanUtils.copyProperties(param, purParam);
					purParam.setOrgUnitNo(scmPurReceive.getPurOrgUnitNo());
					ScmPurBuyer2 scmPurBuyer = scmPurBuyerBiz.selectByCode(purReceiveEditParams.getBuyerCode(), purParam);
					if(scmPurBuyer==null)
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurReceiveBizImpl.error.buyernotexists");
					scmPurReceive.setBuyerId(scmPurBuyer.getId());
				}
				List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, scmPurReceive.getOrgUnitNo(), false, null, param);
				if(orgCompanyList==null || orgCompanyList.isEmpty()){
					throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
				}
				bean.setList(scmPurReceiveList);
				List<PurReceiveDetailParams> detailList = purReceiveEditParams.getDetailList();
				List<ScmPurReceiveEntry2> scmPurReceiveEntryList = scmPurReceiveEntryBiz.selectByPvId(scmPurReceive.getId(), param);
				if(scmPurReceiveEntryList==null || scmPurReceiveEntryList.isEmpty()){
					scmPurReceiveEntryList = new ArrayList<>();
					StringBuffer itemNos = new StringBuffer("");
					for(PurReceiveDetailParams detailParams:detailList){
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
			        argList.add("purOrgUnitNo="+purReceiveEditParams.getPurOrgUnitNo());
			        argList.add("invOrgUnitNo="+param.getOrgUnitNo());
			        argList.add("controlUnitNo=" + param.getControlUnitNo());
			        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "findByPurAndInvAllPage", param);
					for(PurReceiveDetailParams detailParams:detailList){
						ScmPurReceiveEntry2 scmPurReceiveEntry = new ScmPurReceiveEntry2(true);
						BeanUtils.copyProperties(detailParams, scmPurReceiveEntry);
						for(ScmMaterial2 scmMaterial:scmMaterialList){
							if(StringUtils.equals(scmMaterial.getItemNo(), detailParams.getItemNo())){
								scmPurReceiveEntry.setItemId(scmMaterial.getId());
								scmPurReceiveEntry.setPurUnit(scmMaterial.getPurUnitId());
								scmPurReceiveEntry.setBaseUnit(scmMaterial.getBaseUnitId());
								scmPurReceiveEntry.setUnit(scmMaterial.getUnitId());
								scmPurReceiveEntry.setPieUnit(scmMaterial.getPieUnitId());
								scmPurReceiveEntry.setReceiveTopRatio(scmMaterial.getReceiveTopRatio());
								break;
							}
						}
						if(scmPurReceive.getCkId()>0) {
							//需验收则判断收货数量不能大于配送数量
							if(scmPurReceiveEntry.getQty().compareTo(scmPurReceiveEntry.getDeliveryQty())>0) {
								throw new AppException(Message.getMessage(param.getLang(),"iscm.purchasebusiness.scmPurReceive.qty.exceeddeliveryqty",new String[]{scmPurReceiveEntry.getItemName()}));
							}
						}
						if (scmPurReceiveEntry.getPoDtlId()>0) {
							if((scmPurReceiveEntry.getQty().add(scmPurReceiveEntry.getReceiveQty())).compareTo(scmPurReceiveEntry.getOrderQty().multiply((BigDecimal.ONE.add(scmPurReceiveEntry.getReceiveTopRatio().divide(new BigDecimal("100"))))))>0){
								throw new AppException(Message.getMessage(param.getLang(),"iscm.purchasebusiness.scmPurReceive.qty.exceed",new String[]{scmPurReceiveEntry.getItemName()}));
	                        }
						}
						scmPurReceiveEntry.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
						scmPurReceiveEntry.setRowStatus("I");
						scmPurReceiveEntry.setInvOrgUnitNo(param.getOrgUnitNo());
						if(StringUtils.isNotBlank(detailParams.getWareHouseNo())){
							ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectByWhNo(param.getOrgUnitNo(),detailParams.getWareHouseNo(),param);
							if(scmInvWareHouse==null)
								throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemisnull");
							scmPurReceiveEntry.setWareHouseId(scmInvWareHouse.getId());
						}
						scmPurReceiveEntry.setBalanceSupplierId(scmPurReceive.getVendorId());
						if(BigDecimal.ZERO.compareTo(scmPurReceiveEntry.getPrice())==0){
							String priceBillId = "";
							if (StringUtils.contains("6,7",scmPurReceiveEntry.getRefPriceStatus())) {
								priceBillId = String.valueOf(scmPurReceiveEntry.getPriceBillId());
							}
							ScmMaterialPrice scmMaterialPrice = ScmMaterialUtil.getMaterialPrice(purReceiveEditParams.getPurOrgUnitNo(), scmPurReceive.getVendorId(), String.valueOf(scmPurReceiveEntry.getItemId()), scmPurReceiveEntry.getPurUnit(),scmPurReceive.getReceiveDate(), orgCompanyList.get(0).getOrgUnitNo(),priceBillId,param);
							if(scmMaterialPrice!=null){
								scmPurReceiveEntry.setPrice(scmMaterialPrice.getPrice());
								scmPurReceiveEntry.setAmt(scmPurReceiveEntry.getQty().multiply(scmMaterialPrice.getPrice()));
								scmPurReceiveEntry.setTaxPrice(scmPurReceiveEntry.getPrice().multiply(BigDecimal.ONE.add(scmPurReceiveEntry.getTaxRate())));
								scmPurReceiveEntry.setTaxAmt(scmPurReceiveEntry.getAmt().multiply(BigDecimal.ONE.add(scmPurReceiveEntry.getTaxRate())));
								scmPurReceiveEntry.setPriceBillId(scmMaterialPrice.getPriceBillId());
								scmPurReceiveEntry.setRefPriceStatus(scmMaterialPrice.getRefPriceStatus());
							}else{
								scmPurReceiveEntry.setPrice(BigDecimal.ZERO);
								scmPurReceiveEntry.setAmt(BigDecimal.ZERO);
							}
						}
						scmPurReceiveEntryList.add(scmPurReceiveEntry);
					}
				}else{
					//先删除不存在行的记录
					for(int i = scmPurReceiveEntryList.size()-1;i>=0;i--){
						ScmPurReceiveEntry2 scmPurReceiveEntry2 = scmPurReceiveEntryList.get(i);
						boolean exists = false;
						for(PurReceiveDetailParams detailParams:detailList){
							if(detailParams.getLineId()==scmPurReceiveEntry2.getLineId()){
								exists = true;
								break;
							}
						}
						if(!exists)
							scmPurReceiveEntryList.remove(i);
					}
					StringBuffer itemNos = new StringBuffer("");
					for(PurReceiveDetailParams detailParams:detailList){
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
			        argList.add("purOrgUnitNo="+purReceiveEditParams.getPurOrgUnitNo());
			        argList.add("invOrgUnitNo="+param.getOrgUnitNo());
			        argList.add("controlUnitNo=" + param.getControlUnitNo());
			        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "findByPurAndInvAllPage", param);
					//更新时是根据行号进行更新的
					int lineId = 0;
					for(PurReceiveDetailParams detailParams:detailList){
						ScmPurReceiveEntry2 scmPurReceiveEntry = new ScmPurReceiveEntry2(true);
						boolean exists = false;
						for(ScmPurReceiveEntry2 scmPurReceiveEntry2:scmPurReceiveEntryList){
							if(detailParams.getLineId()==scmPurReceiveEntry2.getLineId() && detailParams.getLineId()!=0){
								scmPurReceiveEntry = scmPurReceiveEntry2;
								exists = true;
								break;
							}
						}
						BeanUtils.copyProperties(detailParams, scmPurReceiveEntry);
						for(ScmMaterial2 scmMaterial:scmMaterialList){
							if(StringUtils.equals(scmMaterial.getItemNo(), detailParams.getItemNo())){
								scmPurReceiveEntry.setItemId(scmMaterial.getId());
								scmPurReceiveEntry.setPurUnit(scmMaterial.getPurUnitId());
								scmPurReceiveEntry.setBaseUnit(scmMaterial.getBaseUnitId());
								scmPurReceiveEntry.setUnit(scmMaterial.getUnitId());
								scmPurReceiveEntry.setPieUnit(scmMaterial.getPieUnitId());
								scmPurReceiveEntry.setReceiveTopRatio(scmMaterial.getReceiveTopRatio());
								break;
							}
						}
						if(scmPurReceive.getCkId()>0) {
							//需验收则判断收货数量不能大于配送数量
							if(scmPurReceiveEntry.getQty().compareTo(scmPurReceiveEntry.getDeliveryQty())>0) {
								throw new AppException(Message.getMessage(param.getLang(),"iscm.purchasebusiness.scmPurReceive.qty.exceeddeliveryqty",new String[]{scmPurReceiveEntry.getItemName()}));
							}
						}
						if (scmPurReceiveEntry.getPoDtlId()>0) {
							if((scmPurReceiveEntry.getQty().add(scmPurReceiveEntry.getReceiveQty())).compareTo(scmPurReceiveEntry.getOrderQty().multiply((BigDecimal.ONE.add(scmPurReceiveEntry.getReceiveTopRatio().divide(new BigDecimal("100"))))))>0){
								throw new AppException(Message.getMessage(param.getLang(),"iscm.purchasebusiness.scmPurReceive.qty.exceed",new String[]{scmPurReceiveEntry.getItemName()}));
	                        }
						}
						scmPurReceiveEntry.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
						scmPurReceiveEntry.setRowStatus("I");
						scmPurReceiveEntry.setInvOrgUnitNo(param.getOrgUnitNo());
						if(StringUtils.isNotBlank(detailParams.getWareHouseNo())){
							ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectByWhNo(param.getOrgUnitNo(),detailParams.getWareHouseNo(),param);
							if(scmInvWareHouse==null)
								throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemisnull");
							scmPurReceiveEntry.setWareHouseId(scmInvWareHouse.getId());
						}
						scmPurReceiveEntry.setBalanceSupplierId(scmPurReceive.getVendorId());
						if(BigDecimal.ZERO.compareTo(scmPurReceiveEntry.getPrice())==0){
							String priceBillId = "";
							if (StringUtils.contains("6,7",scmPurReceiveEntry.getRefPriceStatus())) {
								priceBillId = String.valueOf(scmPurReceiveEntry.getPriceBillId());
							}
							ScmMaterialPrice scmMaterialPrice = ScmMaterialUtil.getMaterialPrice(purReceiveEditParams.getPurOrgUnitNo(), scmPurReceive.getVendorId(), String.valueOf(scmPurReceiveEntry.getItemId()), scmPurReceiveEntry.getPurUnit(),scmPurReceive.getReceiveDate(), orgCompanyList.get(0).getOrgUnitNo(),priceBillId,param);
							if(scmMaterialPrice!=null){
								scmPurReceiveEntry.setPrice(scmMaterialPrice.getPrice());
								scmPurReceiveEntry.setAmt(scmPurReceiveEntry.getQty().multiply(scmMaterialPrice.getPrice()));
								scmPurReceiveEntry.setTaxPrice(scmPurReceiveEntry.getPrice().multiply(BigDecimal.ONE.add(scmPurReceiveEntry.getTaxRate())));
								scmPurReceiveEntry.setTaxAmt(scmPurReceiveEntry.getAmt().multiply(BigDecimal.ONE.add(scmPurReceiveEntry.getTaxRate())));
								scmPurReceiveEntry.setPriceBillId(scmMaterialPrice.getPriceBillId());
								scmPurReceiveEntry.setRefPriceStatus(scmMaterialPrice.getRefPriceStatus());
							}else{
								scmPurReceiveEntry.setPrice(BigDecimal.ZERO);
								scmPurReceiveEntry.setAmt(BigDecimal.ZERO);
							}
						}
						if(!exists)
							scmPurReceiveEntryList.add(lineId, scmPurReceiveEntry);
						lineId = lineId +1;
					}
				}
				//更新子表数据
				bean.setList2(scmPurReceiveEntryList);
				this.update(bean, param);
			}else{
				throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
			}
		}else{
			throw new AppException("webservice.params.null");
		}
	}

	@Override
	public ScmPurReceive2 doRelease(ScmPurReceive2 scmPurReceive,Param param) throws AppException {
		ScmPurReceive2 receive = null;
		if(scmPurReceive.getId()>0){
			receive = this.selectDirect(scmPurReceive.getId(), param);
		}else{
			receive = this.selectByBillCode(scmPurReceive.getPvNo(), param);
		}
		if(receive!=null){
			if (!StringUtils.equals("A", receive.getStatus())) {
				throw new AppException("iscm.purchasemanage.error.release");
			}else if(receive.getStatus().equals("A")){
				receive.setStatus("E");
				receive.setPendingUsr("");
				try {
					this.updateStatus(receive, param);
					scmPurOrderBiz.writeBackInvQty(receive,1,param);
				} catch (Exception e) {
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return receive;
	}

	private void generateReturns(ScmPurReceive2 receive, Param param) throws AppException {
		scmPurReturnsBiz.generateReturns(receive,param);
	}

	private ScmInvPurInWarehsBill2 generatePurInWarehsBill(ScmPurReceive2 receive, Param param) throws AppException {
		return scmInvPurInWarehsBillBiz.generatePurInWarehsBillByReceive(receive, param);
	}

	@Override
	public ScmPurReceive2 doUndoRelease(ScmPurReceive2 scmPurReceive,Param param) throws AppException {
		ScmPurReceive2 receive = null;
		if(scmPurReceive.getId()>0){
			receive = this.selectDirect(scmPurReceive.getId(), param);
		}else{
			receive = this.selectByBillCode(scmPurReceive.getPvNo(), param);
		}
		if(receive!=null){
			if (!StringUtils.equals("E", receive.getStatus())) {
				throw new AppException("iscm.purchasemanage.error.cancelRelease");
			}else if(receive.getStatus().equals("E")){
				List<String> billList = checkFollowUpBill(receive,param);
				if(billList!=null && !billList.isEmpty()){
					StringBuffer billNos = new StringBuffer("");
					for(String billNo:billList) {
						if(StringUtils.isNotBlank(billNos.toString()))
							billNos.append(",");
						billNos.append(billNo);
					}
					throw new AppException(Message.getMessage(param.getLang(),"iscm.inventorymanage.scmPurReceive.doUndoRelease.error.existsPurWarehsBill",new String[] {billNos.toString()}));
				}
				receive.setStatus("A");
				receive.setPendingUsr("");
				try {
					this.updateStatus(receive, param);
					scmPurOrderBiz.writeBackInvQty(receive,-1,param);
				} catch (Exception e) {
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return receive;
	}

	private List<String> checkFollowUpBill(ScmPurReceive2 receive, Param param) {
		List<String> billList = new ArrayList();
		List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList = scmInvPurInWarehsBillBiz.selectByPurReceive(receive,param);
		if(scmInvPurInWarehsBillList!=null && !scmInvPurInWarehsBillList.isEmpty()){
			for(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill:scmInvPurInWarehsBillList) {
				if (!billList.contains(scmInvPurInWarehsBill.getWrNo())) {
					billList.add(scmInvPurInWarehsBill.getWrNo());
				}
			}
		}
		return billList;
	}

	@Override
	public void generateFromSaleIssue(ScmInvSaleIssueBill2 scmInvSaleIssueBill,Param param) throws AppException {
		List<ScmPurOrder2> scmPurOrderList = scmPurOrderBiz.selectBySaleIssueBill(scmInvSaleIssueBill.getOtId(),param);
		if(scmPurOrderList!=null && !scmPurOrderList.isEmpty()){
			for(ScmPurOrder2 scmPurOrder:scmPurOrderList){
				List<ScmPurOrderEntry2> scmPurOrderEntryList = scmPurOrderEntryBiz.selectByPoIdAndSaleIssueBill(scmPurOrder.getId(),scmInvSaleIssueBill.getOtId(),param);
				if(scmPurOrderEntryList!=null && !scmPurOrderEntryList.isEmpty())
					this.generateFromPurOrder(scmPurOrder, scmPurOrderEntryList, param);
			}
		}
	}

	@Override
	public List<OrgPurchase2> queryPurRecPurOrgUnit(PurRecPurOrgUnitParams purRecPurOrgUnitParams, Param param) throws AppException {
		Page page = new Page();
		page.setModelClass(OrgPurchase2.class);
		page.setShowCount(Integer.MAX_VALUE);
		List<String> arglist = new ArrayList<String>();
		arglist.add("type=from");
		arglist.add("relationType="+OrgUnitRelationType.PURTOINV);
		arglist.add("toOrgUnitNo="+param.getOrgUnitNo());
		return orgPurchaseBiz.queryPage(page, arglist, "queryPageEx", param);
	}

	@Override
	public List<Usr2> queryPurReceiverList(PurReceiverListParams purReceiverListParams, int pageNum,Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(Usr2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		StringBuffer buff = new StringBuffer("(SELECT status from employee WHERE id=Usr.empid)='1'");
		if(StringUtils.isNotBlank(purReceiverListParams.getReceiverName())){
			buff.append("and usr.name like '%"+purReceiverListParams.getReceiverName()+"%'");
		}
		List<String> arglist = new ArrayList<>();
		arglist.add("orgUnitNo="+param.getOrgUnitNo());
		page.setSqlCondition(buff.toString());
		return usrBiz.queryPage(page, arglist, "findForRolePage", param);
	}

	@Override
	public void generateFromPurOrder(ScmPurOrder2 scmPurOrder,List<ScmPurOrderEntry2> scmPurOrderEntryList, Param param)
			throws AppException {
		int pricePrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PricePrecision", "4", param));
		int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
		long ckId = 0;
        String ckNo = "";
        scmPurOrderEntryList = (List<ScmPurOrderEntry2>)ListSortUtil.sort(scmPurOrderEntryList, "lineId", "ASC");
        //按收货组织分单
        List<String> list = new ArrayList<>();
        String receiveQtyZero = sysParamBiz.getValue(param.getControlUnitNo(), "SCM_ReceiveQtyZero", "N", param);
        HashMap<String,String> splitTypeMap = new HashMap<String,String>();
        for (ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList) {
            String splitByDept = splitTypeMap.get(scmPurOrderEntry.getReceiveOrgUnitNo());
            if(StringUtils.isEmpty(splitByDept)) {
            	splitByDept = sysParamBiz.getValue(scmPurOrderEntry.getReceiveOrgUnitNo(), "SCM_ReceiveSplitByDept", "N", param);
            	splitTypeMap.put(scmPurOrderEntry.getReceiveOrgUnitNo(), splitByDept);
            }
        	String key = scmPurOrderEntry.getReceiveOrgUnitNo()+"_"+FormatUtils.fmtDate(scmPurOrderEntry.getReqDate());
        	if(StringUtils.equals("Y", splitByDept)) {
        		key=key+"_"+scmPurOrderEntry.getReceiveWareHouseId()+"_"+(scmPurOrderEntry.getReqOrgUnitNo()==null?"":scmPurOrderEntry.getReqOrgUnitNo());
        	}
            if(!list.contains(key)){
            	if(scmPurOrder.isUnified()){
                    //统配时先生成验收记录表
                    ScmPurCheck2 scmPurCheck = new ScmPurCheck2();
                    scmPurCheck.setOrgUnitNo(scmPurOrder.getOrgUnitNo());
                    scmPurCheck.setPoId(scmPurOrder.getId());
                    scmPurCheck.setPoNo(scmPurOrder.getPoNo());
                    scmPurCheck.setCheckDate(scmPurOrderEntry.getReqDate());
                    scmPurCheck.setCreator(param.getUsrCode());
                    scmPurCheck.setCreateDate(CalendarUtil.getDate(param));
                    scmPurCheck.setFlag(false);
                    scmPurCheck.setRemarks(scmPurOrder.getRemarks());
                    scmPurCheck.setControlUnitNo(param.getControlUnitNo());
                    scmPurCheck = scmPurCheckBiz.add(scmPurCheck, param);
                    ckNo = scmPurCheck.getCkNo();
                    ckId = scmPurCheck.getId();
                }
                list.add(key);
                CommonBean bean = new CommonBean();
                List<ScmPurReceive2> scmPurReceiveList = new ArrayList<>();
                List<ScmPurReceiveEntry2> scmPurReceiveEntryList = new ArrayList<>();
                ScmPurReceive2 scmPurReceive = new ScmPurReceive2(true);
                scmPurReceive.setStatus("I");
                scmPurReceive.setOrgUnitNo(scmPurOrderEntry.getReceiveOrgUnitNo()); //收货组织
                List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, scmPurOrderEntry.getReceiveOrgUnitNo(), false, null, param);
                if(orgCompanyList==null || orgCompanyList.isEmpty()){
                    throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
                }
                scmPurReceive.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
                scmPurReceive.setCkNo(ckNo);
                scmPurReceive.setCkId(ckId);
                scmPurReceive.setVendorId(scmPurOrder.getVendorId());
                scmPurReceive.setReceiveDate(scmPurOrderEntry.getReqDate());
                scmPurReceive.setUnified(scmPurOrder.isUnified());
                scmPurReceive.setPurOrgUnitNo(scmPurOrder.getOrgUnitNo());  //采购组织
                scmPurReceive.setBuyerId(scmPurOrder.getBuyerId());
                scmPurReceive.setPurGroupId(scmPurOrder.getPurGroupId());
                scmPurReceive.setCurrencyNo(scmPurOrder.getCurrencyNo());
                scmPurReceive.setExchangeRate(scmPurOrder.getExchangeRate());
                scmPurReceive.setExistsSource(true);
                scmPurReceive.setCreator(param.getUsrCode());
                scmPurReceive.setCreateDate(CalendarUtil.getDate(param));
                scmPurReceive.setRemarks(scmPurOrder.getRemarks());
                scmPurReceive.setControlUnitNo(param.getControlUnitNo());
                scmPurReceiveList.add(scmPurReceive);
                bean.setList(scmPurReceiveList);
                HashMap<String, String> invToFinMap = new HashMap<String, String>();
                for (int i=0;i<scmPurOrderEntryList.size();i++) {
                    if(StringUtils.equals(scmPurOrderEntryList.get(i).getReceiveOrgUnitNo(),scmPurOrderEntry.getReceiveOrgUnitNo())
                    		&& scmPurOrderEntryList.get(i).getReqDate().compareTo(scmPurOrderEntry.getReqDate())==0
                    		&& (StringUtils.equals("N", splitByDept) || (StringUtils.equals("Y", splitByDept) 
                    				&& scmPurOrderEntryList.get(i).getReceiveWareHouseId()==scmPurOrderEntry.getReceiveWareHouseId() 
                    				&& StringUtils.equals((scmPurOrderEntryList.get(i).getReqOrgUnitNo()==null?"":scmPurOrderEntryList.get(i).getReqOrgUnitNo()),(scmPurOrderEntry.getReqOrgUnitNo()==null?"":scmPurOrderEntry.getReqOrgUnitNo()))))){
                        ScmPurReceiveEntry2 scmPurReceiveEntry = new ScmPurReceiveEntry2(true);
                        scmPurReceiveEntry.setLineId(i+1);
                        scmPurReceiveEntry.setItemId(scmPurOrderEntryList.get(i).getItemId());
                        scmPurReceiveEntry.setPurUnit(scmPurOrderEntryList.get(i).getPurUnit());
                        scmPurReceiveEntry.setPieUnit(scmPurOrderEntryList.get(i).getPieUnit());
                        scmPurReceiveEntry.setPieQty(scmPurOrderEntryList.get(i).getPieQty());
                        ScmMaterial2 scmMaterial = scmMaterialBiz.findByInvItemId(param.getControlUnitNo(),scmPurOrderEntry.getReceiveOrgUnitNo(),scmPurOrderEntryList.get(i).getItemId(), param);
                        if(scmMaterial==null)
                            throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurOrderBizImpl.generatePvOrder.error.notScmMaterial");
                        if(scmMaterial.getUnitId()==0)
                        	throw new AppException(Message.getMessage(param.getLang(),"iscm.inventorymanage.warehouseoutbusiness.ScmInvMoveIssueBillBizImpl.generateInWaresBill.error.noUnit",new String[]{scmMaterial.getItemName()}));
                        if (!StringUtils.equals("A", scmMaterial.getStatus())) {
							throw new AppException(scmMaterial.getItemName()+" "+ Message.getMessage(param.getLang(),"iscm.purchasemanage.purchasebusiness.ScmPurRequireEntryServiceImpl.generateOrder.validateItemBasic"));
						}
                        if (!StringUtils.equals("A", scmMaterial.getInvStatus())) {
                        	throw new AppException(scmMaterial.getItemName()+" "+Message.getMessage(param.getLang(),"iscm.purchasemanage.purchasebusiness.ScmPurRequireEntryServiceImpl.generateOrder.validateItemInv"));
						}
                        scmPurReceiveEntry.setUnit(scmMaterial.getUnitId());
                        scmPurReceiveEntry.setBaseUnit(scmPurOrderEntryList.get(i).getBaseUnit());
                        scmPurReceiveEntry.setOrderQty(scmPurOrderEntryList.get(i).getQty());
                        //生成验收单时送货数量=订货数量
                        if(StringUtils.equals("Y", receiveQtyZero)) {
                        	if(scmPurOrder.isUnified()) {
                        		scmPurReceiveEntry.setDeliveryQty(BigDecimal.ZERO);
                        	}else {
                        		scmPurReceiveEntry.setDeliveryQty(scmPurOrderEntryList.get(i).getQty());
                        	}
	                        scmPurReceiveEntry.setQty(BigDecimal.ZERO);
	                        scmPurReceiveEntry.setAmt(BigDecimal.ZERO);
	                        scmPurReceiveEntry.setCheckAmt(BigDecimal.ZERO);
	                        scmPurReceiveEntry.setTaxAmt(BigDecimal.ZERO);
	                        scmPurReceiveEntry.setCheckTaxAmt(BigDecimal.ZERO);
                        }else {
	                        scmPurReceiveEntry.setDeliveryQty(scmPurOrderEntryList.get(i).getQty());
	                        scmPurReceiveEntry.setQty(scmPurOrderEntryList.get(i).getQty());
	                        scmPurReceiveEntry.setAmt(scmPurOrderEntryList.get(i).getAmt().setScale(amtPrecision, RoundingMode.HALF_UP));
	                        scmPurReceiveEntry.setCheckAmt(scmPurOrderEntryList.get(i).getAmt());
	                        scmPurReceiveEntry.setTaxAmt(scmPurOrderEntryList.get(i).getTaxAmt());
	                        scmPurReceiveEntry.setCheckTaxAmt(scmPurOrderEntryList.get(i).getTaxAmt());
                        }
                        scmPurReceiveEntry.setPrice(scmPurOrderEntryList.get(i).getPrice().setScale(pricePrecision, RoundingMode.HALF_UP));
                        scmPurReceiveEntry.setTaxRate(scmPurOrderEntryList.get(i).getTaxRate());
                        scmPurReceiveEntry.setTaxPrice(scmPurOrderEntryList.get(i).getTaxPrice());
                        if(scmPurOrderEntryList.get(i).getReceiveWareHouseId()>0){
                            //仓库收货
                            scmPurReceiveEntry.setInvOrgUnitNo(scmPurOrderEntryList.get(i).getReceiveOrgUnitNo());
                            scmPurReceiveEntry.setWareHouseId(scmPurOrderEntryList.get(i).getReceiveWareHouseId());
                        }else{
                            //部门收货
                            scmPurReceiveEntry.setUseOrgUnitNo(scmPurOrderEntryList.get(i).getReqOrgUnitNo());
                        }
                        if(!invToFinMap.containsKey(scmPurOrderEntry.getReceiveOrgUnitNo())){
                            orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, scmPurOrderEntry.getReceiveOrgUnitNo(), false, null, param);
                            if(orgCompanyList==null || orgCompanyList.isEmpty()){
                                throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
                            }
                            invToFinMap.put(scmPurOrderEntry.getReceiveOrgUnitNo(),orgCompanyList.get(0).getOrgUnitNo());
                        }
                        scmPurReceiveEntry.setFinOrgUnitNo(invToFinMap.get(scmPurOrderEntry.getReceiveOrgUnitNo()));
                        scmPurReceiveEntry.setBalanceSupplierId(scmPurOrder.getVendorId());
                        scmPurReceiveEntry.setReqOrgUnitNo(scmPurOrderEntryList.get(i).getReqOrgUnitNo());
                        scmPurReceiveEntry.setPurOrgUnitNo(scmPurOrder.getOrgUnitNo());
                        scmPurReceiveEntry.setPriceBillId(scmPurOrderEntryList.get(i).getPriceBillId());
                        scmPurReceiveEntry.setRefPriceStatus(scmPurOrderEntryList.get(i).getRefPriceStatus());
                        scmPurReceiveEntry.setPoDtlId(scmPurOrderEntryList.get(i).getId());
                        scmPurReceiveEntry.setPoNo(scmPurOrderEntryList.get(i).getPoNo());
                        scmPurReceiveEntry.setRemarks(scmPurOrderEntryList.get(i).getRemarks());
                        scmPurReceiveEntry.setRowStatus("I");
                        scmPurReceiveEntryList.add(scmPurReceiveEntry);
                        bean.setList2(scmPurReceiveEntryList);
                    }
                }
                String openCostUse = sysParamBiz.getValue(scmPurReceive.getFinOrgUnitNo(), "SCM_OpenCostUse", "N", param);
                if (StringUtils.equals("Y",openCostUse)) {
                	genCostTypeId(bean,param);
				}
                this.add(bean, param);
            }
        }
	}

	private void genCostTypeId(CommonBean bean, Param param) {
		List<ScmCostUseSet2> scmCostUseSet2List = new ArrayList<>();
		List<ScmPurReceiveEntry2> scmPurReceiveEntryList = bean.getList2();
		if (scmPurReceiveEntryList != null && !scmPurReceiveEntryList.isEmpty()) {
			scmPurReceiveEntryList = scmPurReceiveEntryList.stream().filter(s->StringUtils.isNotBlank(s.getUseOrgUnitNo())).collect(Collectors.toList());
			if (scmPurReceiveEntryList != null && !scmPurReceiveEntryList.isEmpty()) {
				Map<String, List<ScmPurReceiveEntry2>> map = scmPurReceiveEntryList.stream().collect(Collectors.groupingBy(ScmPurReceiveEntry2::getUseOrgUnitNo));
				Map<String, String> map1 = new HashMap<>();
				for (Map.Entry<String, List<ScmPurReceiveEntry2>> m : map.entrySet()) {
					StringBuffer buffer = new StringBuffer();
					for (ScmPurReceiveEntry2 scmPurReceiveEntry2 : m.getValue()) {
						if (StringUtils.isNotBlank(buffer.toString())) {
							buffer.append(",");
						}
						buffer.append(scmPurReceiveEntry2.getItemId());
					}
					if (StringUtils.isNotBlank(buffer.toString())) {
						map1.put(m.getKey(), buffer.toString());
						buffer = new StringBuffer();
					}
				}
				for (Map.Entry<String, String> m : map1.entrySet()) {
					List<ScmCostUseSet2> scmCostUseSet2s = scmCostUseSetBiz.getScmCostUseSetByItemId(m.getValue(),m.getKey(),param);
					scmCostUseSet2List.addAll(scmCostUseSet2s);
				}
			}
			if (scmCostUseSet2List != null && !scmCostUseSet2List.isEmpty()) {
				for (ScmPurReceiveEntry2 scmPurReceiveEntry2 : scmPurReceiveEntryList) {
					for (ScmCostUseSet2 scmCostUseSet2 : scmCostUseSet2List) {
						if (StringUtils.equals(scmCostUseSet2.getCostOrgUnitNo(), scmPurReceiveEntry2.getUseOrgUnitNo()) && scmCostUseSet2.getItemId()==scmPurReceiveEntry2.getItemId()) {
							scmPurReceiveEntry2.setCostUseTypeId(scmCostUseSet2.getCostUseTypeId());
						}
					}
				}
			}
		}
	}

	@Override
	public List<ScmPurReceive2> selectByPurOrder(ScmPurOrder2 scmPurOrder, Param param)	throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("poId", scmPurOrder.getId());
		return ((ScmPurReceiveDAO)dao).selectByPurOrder(map);
	}

	@Override
	protected void beforeDelete(ScmPurReceive2 entity, Param param)
			throws AppException {
		ScmPurReceive2 scmPurReceive = this.selectDirect(entity.getId(), param);
		if(!StringUtils.equals("I",scmPurReceive.getStatus()))
			throw new AppException(Message.getMessage(param.getLang(),"iscm.inventorymanage.delete.error",new String[]{entity.getPvNo()}));
		//删除收货明细
		scmPurReceiveEntryBiz.deleteByPvId(entity.getId(), param);
	}

	@Override
	public ScmPurReceive2 startReceive(ScmPurReceive2 scmPurReceive, Param param) throws AppException {
		scmPurReceive = (ScmPurReceive2) this.select(scmPurReceive, param);
		scmPurReceive.setReceiveTime(CalendarUtil.getDate(param));
		return this.updateDirect(scmPurReceive, param);
	}

	@Override
	public void delByPurOrder(ScmPurOrder2 scmPurOrder, Param param)
			throws AppException {
        if(scmPurOrder.isUnified()){
            //统配删除验收记录表
            scmPurCheckBiz.delByPurOrder(scmPurOrder, param);
        }
        List<ScmPurReceive2> scmPurReceiveList = this.selectByPurOrder(scmPurOrder,param);
        if(scmPurReceiveList!=null && !scmPurReceiveList.isEmpty()){
        	for(ScmPurReceive2 scmPurReceive:scmPurReceiveList){
        		if(!StringUtils.equals("I",scmPurReceive.getStatus())){
    				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.scmPurReceive.delByPurOrder.error", new String[]{scmPurReceive.getPvNo()}));
    			}
        	}
        	this.delete(scmPurReceiveList, param);
        }
	}
	
	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmPurReceive2 entry = (ScmPurReceive2) bean.getList().get(0);
		if(entry!=null) {
			ScmPurReceive2 scmPurReceive = this.select(entry.getPK(), param);
			if(!StringUtils.equals(scmPurReceive.getStatus(),"I")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
			if(entry.getReceiveTime()==null) {
				entry.setReceiveTime(CalendarUtil.getDate(param));
			}
			List<ScmPurReceiveEntry2> scmPurReceiveEntryList = bean.getList2();
	        BigDecimal amt= BigDecimal.ZERO;
	        BigDecimal taxAmt= BigDecimal.ZERO;
	        List<String> poNoList = new ArrayList();
            List<String> useOrgList = new ArrayList();
            List<String> whsList = new ArrayList();
			if(scmPurReceiveEntryList != null && !scmPurReceiveEntryList.isEmpty()){
				for(ScmPurReceiveEntry2 scmPurReceiveEntry : scmPurReceiveEntryList){
	                amt = amt.add(scmPurReceiveEntry.getAmt());
	                taxAmt = taxAmt.add(scmPurReceiveEntry.getTaxAmt());
	                if(StringUtils.isNotBlank(scmPurReceiveEntry.getPoNo()) && !poNoList.contains(scmPurReceiveEntry.getPoNo()))
                    	poNoList.add(scmPurReceiveEntry.getPoNo());
	                   if(StringUtils.isNotBlank(scmPurReceiveEntry.getUseOrgUnitNo()) && !useOrgList.contains(scmPurReceiveEntry.getUseOrgUnitNo()))
	                    	useOrgList.add(scmPurReceiveEntry.getUseOrgUnitNo());
	                    if(scmPurReceiveEntry.getWareHouseId()>0 && !whsList.contains(String.valueOf(scmPurReceiveEntry.getWareHouseId())))
	                    	whsList.add(String.valueOf(scmPurReceiveEntry.getWareHouseId()));
				}
			}
			entry.setAmt(amt);
			entry.setTaxAmt(taxAmt);
			entry.setPoNos(StringUtils.left(StringUtils.join(poNoList, ","),250));
			entry.setUseOrgUnitNos(StringUtils.left(StringUtils.join(useOrgList, ","),250));
			entry.setWareHouseIds(StringUtils.left(StringUtils.join(whsList, ","),250));
			//获取期间
			PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(entry.getReceiveDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
			}
			entry.setPeriodId(periodCalendar.getPeriodId());
			entry.setAccountYear(periodCalendar.getAccountYear());
			entry.setAccountPeriod(periodCalendar.getAccountPeriod());
		}
	}

	@Override
	public List<ScmInvPurInWarehsBill2> generateWrReceipts(List<ScmPurReceive2> scmPurReceiveList, Param param)	throws AppException {
		List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList = new ArrayList<>();
		if(scmPurReceiveList!=null && !scmPurReceiveList.isEmpty()){
			for(ScmPurReceive2 scmPurReceives:scmPurReceiveList){
				//收货单下达后需处理：1、生成入库单；2、统配时如果存在不合格或配送差异，则生成退货申请
				if(scmPurReceives.isUnified()){
					generateReturns(scmPurReceives,param);
				}
				ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = generatePurInWarehsBill(scmPurReceives,param);
				if(scmInvPurInWarehsBill!=null)
					scmInvPurInWarehsBillList.add(scmInvPurInWarehsBill);
			}
		}
		return scmInvPurInWarehsBillList;
	}

    @Override
    public ScmPurReceive2 doUndoFinish(ScmPurReceive2 scmPurReceive, Param param) throws AppException {
        ScmPurReceive2 receive = null;
        if(scmPurReceive.getId()>0){
            receive = this.selectDirect(scmPurReceive.getId(), param);
        }else{
            receive = this.selectByBillCode(scmPurReceive.getPvNo(), param);
        }
        if(receive!=null){
            if (!StringUtils.equals("C", receive.getStatus()) && !StringUtils.equals("F", receive.getStatus())) {
                throw new AppException("iscm.purchasemanage.error.cancelFinish");
            }else if("C".equals(receive.getStatus()) || "F".equals(receive.getStatus())){
                List<String> billList = checkFollowUpBill(receive,param);
            	if(billList!=null && !billList.isEmpty()){
					StringBuffer billNos = new StringBuffer("");
					for(String billNo:billList) {
						if(StringUtils.isNotBlank(billNos.toString()))
							billNos.append(",");
						billNos.append(billNo);
					}
					throw new AppException(Message.getMessage(param.getLang(),"iscm.inventorymanage.scmPurReceive.doUndoFinish.error.existsPurWarehsBill",new String[] {billNos.toString()}));
				}      
            	receive.setStatus("A");
                try {
                    this.updateStatus(receive, param);
                } catch (Exception e) {
                    throw e;
                }
            }
        }else{
            throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
        }
        return receive;
    }

	@Override
	public ScmPurReceive2 selectByCkId(long ckId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ckId", ckId);
		return ((ScmPurReceiveDAO)dao).selectByCkId(map);
	}

	@Override
	public void deleteFromSaleIssue(ScmInvSaleIssueBill2 scmInvSaleIssueBill, Param param) throws AppException {
		List<ScmPurCheck2> scmPurCheckList = scmPurCheckBiz.selectBySaleIssueBill(scmInvSaleIssueBill.getOtId(), param);
		if(scmPurCheckList!=null && !scmPurCheckList.isEmpty()) {
			for(ScmPurCheck scmPurCheck:scmPurCheckList) {
				if(scmPurCheck.isFlag()) {
					throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvSaleIssueBill.error.checkbillconfirmed", new String[] {scmPurCheck.getCkNo()}));
				}
			}
			scmPurCheckBiz.delete(scmPurCheckList, param);
		}
		List<ScmPurReceive2> scmPurReceiveList = this.selectBySaleIssueBill(scmInvSaleIssueBill.getOtId(), param);
		if(scmPurReceiveList!=null && !scmPurReceiveList.isEmpty()) {
			for(ScmPurReceive2 scmPurReceive:scmPurReceiveList) {
				if(!StringUtils.equals("I",scmPurReceive.getStatus())) {
					throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvSaleIssueBill.error.ReceiveBillStarted", new String[] {scmPurReceive.getPvNo()}));
				}
			}
			this.delete(scmPurReceiveList, param);
		}
	}

	@Override
	public List<ScmPurReceive2> selectBySaleIssueBill(long otId, Param param) throws AppException {
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("otId", otId);
		return ((ScmPurReceiveDAO)dao).selectBySaleIssueBill(map);
	}

	@Override
	public void generateFromOtherIssue(ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param) throws AppException {
		List<ScmPurOrder2> scmPurOrderList = scmPurOrderBiz.selectByOtherIssueBill(scmInvOtherIssueBill.getOtId(),param);
		if(scmPurOrderList!=null && !scmPurOrderList.isEmpty()){
			for(ScmPurOrder2 scmPurOrder:scmPurOrderList){
				List<ScmPurOrderEntry2> scmPurOrderEntryList = scmPurOrderEntryBiz.selectByPoIdAndOtherIssueBill(scmPurOrder.getId(),scmInvOtherIssueBill.getOtId(),param);
				if(scmPurOrderEntryList!=null && !scmPurOrderEntryList.isEmpty())
					this.generateFromPurOrder(scmPurOrder, scmPurOrderEntryList, param);
			}
		}
	}

	@Override
	public void deleteFromOtherIssue(ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param) throws AppException {
		List<ScmPurCheck2> scmPurCheckList = scmPurCheckBiz.selectByOtherIssueBill(scmInvOtherIssueBill.getOtId(), param);
		if(scmPurCheckList!=null && !scmPurCheckList.isEmpty()) {
			for(ScmPurCheck scmPurCheck:scmPurCheckList) {
				if(scmPurCheck.isFlag()) {
					throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvOtherIssueBill.error.checkbillconfirmed", new String[] {scmPurCheck.getCkNo()}));
				}
			}
			scmPurCheckBiz.delete(scmPurCheckList, param);
		}
		List<ScmPurReceive2> scmPurReceiveList = this.selectByOtherIssueBill(scmInvOtherIssueBill.getOtId(), param);
		if(scmPurReceiveList!=null && !scmPurReceiveList.isEmpty()) {
			for(ScmPurReceive2 scmPurReceive:scmPurReceiveList) {
				if(!StringUtils.equals("I",scmPurReceive.getStatus())) {
					throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvOtherIssueBill.error.ReceiveBillStarted", new String[] {scmPurReceive.getPvNo()}));
				}
			}
			this.delete(scmPurReceiveList, param);
		}
	}

	@Override
	public List<ScmPurReceive2> selectByOtherIssueBill(long otId, Param param) throws AppException{
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("otId", otId);
		return ((ScmPurReceiveDAO)dao).selectByOtherIssueBill(map);
	}

	@Override
	public List<OrgAdmin2> queryPurReceiveDept(String invOrgUnitNo,int pageNum, Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(OrgAdmin2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		if(StringUtils.isBlank(invOrgUnitNo))
			invOrgUnitNo = param.getOrgUnitNo();
		List<OrgAdmin2> orgAdminList = orgUnitRelationBiz.findFromOrgUnitByType(OrgUnitRelationType.ADMINTOINV, invOrgUnitNo, false, page, param);
		if(orgAdminList!=null && !orgAdminList.isEmpty()) {
			for(int i=orgAdminList.size()-1;i>=0;i--) {
				OrgAdmin2 orgAdmin=orgAdminList.get(i);
				if(!StringUtils.equals("2",orgAdmin.getOrgType()))
					orgAdminList.remove(i);
			}
		}
		return orgAdminList;
	}

	@Override
	public List<ScmInvWareHouse> queryPurReceiveWareHouses(String invOrgUnitNo,int pageNum, Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(ScmInvWareHouse.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		if(StringUtils.isBlank(invOrgUnitNo))
			invOrgUnitNo = param.getOrgUnitNo();
		List<String> arglist = new ArrayList<>();
		arglist.add("orgUnitNo="+invOrgUnitNo);
		arglist.add("usrCode="+param.getUsrCode());
		return scmInvWareHouseBiz.queryPage(page, arglist, "selectByUsrAndOrgPage", param);
	}
	
	@Override
	public ScmPurReceive2 doAuditPurReceive(CommonAuditParams commonAuditParams,
			Param param) throws AppException {
		ScmPurReceive2 receive = null;
		
		ScmPurReceive2 scmPurReceive= new ScmPurReceive2();
		scmPurReceive.setId(commonAuditParams.getBillId());
		scmPurReceive.setPvNo(commonAuditParams.getBillNo());
		String opinion = commonAuditParams.getOpinion();
		if(StringUtils.equals("Y", opinion) || StringUtils.equals("agree", opinion)) {
			opinion="agree";
		}else if(StringUtils.equals("N", opinion) || StringUtils.equals("refuse", opinion)) {
			opinion="refuse";
		}
		if(scmPurReceive.getId()>0){
			receive = this.selectDirect(scmPurReceive.getId(), param);
		}else{
			receive = this.selectByBillCode(scmPurReceive.getPvNo(), param);
		}
		
		if (receive != null) {
			this.setConvertMap(receive, param);
			if(!(receive.getStatus().equals("D") || receive.getStatus().equals("P"))){
				throw new AppException("iscm.purchasemanage.error.audit");
			}
			if(StringUtils.equals("R", opinion)) {
				scmBillPendingBiz.deletePendingBill(param.getUsrCode(),receive, param);
				commonEventHistoryBiz.updateInvalid(receive,"",param);
				
				CommonAuditOpinion commonAuditOpinionR = new CommonAuditOpinion();
				commonAuditOpinionR.setPreStepNo(receive.getStepNo());
				String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
				commonAuditOpinionR.setStepNo(stepNo);
				commonAuditOpinionR.setActiveType("A");
				commonAuditOpinionR.setOpinion(commonAuditParams.getOpinion());
				commonAuditOpinionR.setRemarks(commonAuditParams.getOpinionRemarks());
				commonEventHistoryBiz.addEventHistory(receive, commonAuditOpinionR, param);
				
				//驳回则将单据变回新单状态
				receive.setStatus("I");
				receive.setPendingUsr("");
				receive.setChecker(null);
				receive.setCheckDate(null);
				return this.updateDirect(receive, param);
			}
			String processInstanceId = receive.getWorkFlowNo();
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
			
			//P：审核中，A：通过，N：未通过
			if ("agree".equals(opinion)) {
				if (isCompleteTask) {
					receive.setStatus("A");
				} else {
					receive.setStatus("P");
				}
			} else {
				reSetBillPendingUsr(receive,param);	
				receive.setStatus("N");
			}
			scmBillPendingBiz.updatePendingBill(param.getUsrCode(), receive, param);
			receive.setChecker(param.getUsrCode());
			if ("agree".equals(opinion)) {
				String usrList= ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId,param);
				if(StringUtils.isNotBlank(usrList)) {
					receive.setPendingUsr(StringUtils.left(usrList,250));
					scmBillPendingBiz.addPendingBill(usrList, receive, param);
					PurReceiveParams purReceiveParams = new PurReceiveParams();
					purReceiveParams.setPvNo(receive.getPvNo());
					AuditMsgUtil.sendAuditMsg("PurReceive",receive,purReceiveParams, usrList, param);
				}else {
					receive.setPendingUsr("");
				}
			}
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setPreStepNo(receive.getStepNo());
			String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
			try {
				receive.setStepNo(stepNo);
				receive.setCheckDate(CalendarUtil.getDate(param));
				this.updateStatus(receive, param);
			} catch (Exception e) {
				throw e;
			}
			commonAuditOpinion.setStepNo(stepNo);
			commonAuditOpinion.setActiveType("A");
			commonAuditOpinion.setOpinion(commonAuditParams.getOpinion());
			commonAuditOpinion.setRemarks(commonAuditParams.getOpinionRemarks());
			commonEventHistoryBiz.addEventHistory(receive, commonAuditOpinion, param);
			if(StringUtils.contains("A,B", receive.getStatus())) {
				//通过或部分通过时检查是否自动下达
				BillType2 billType = billTypeBiz.selectByOrgAndCode(receive.getFinOrgUnitNo(), "PurReceive", param);
				if(billType!=null && billType.isAutoRelease()) {
					this.doRelease(receive, param);
				}
			}
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return receive;
	}

	private void reSetBillPendingUsr(ScmPurReceive2 scmPurReceive, Param param) {
		ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmPurReceive.getId(), "PurReceive",param);
		if (scmBillPendingUsr != null) {
			scmPurReceive.setPendingUsr(scmBillPendingUsr.getUsrCodes());
		}else {
			scmPurReceive.setPendingUsr("");
		}
	}
	@Override
	public ScmPurReceive2 doUnAuditPurReceive(ScmPurReceive2 scmPurReceive,
			Param param) throws AppException {
		ScmPurReceive2 receive = null;
		List<ScmPurReceive2> scmPurReceiveList = new ArrayList<> ();
		List<ScmPurReceiveEntry2> scmPurReceiveEntryList = new ArrayList<> ();
		boolean isFirstTask = false;
		
		if(scmPurReceive.getId()>0){
			receive = this.selectDirect(scmPurReceive.getId(), param);
		}else{
			receive=this.selectByBillCode(scmPurReceive.getPvNo(), param);
		}
		
		if (receive != null) {
			if(!StringUtils.contains("A,B,N,P", receive.getStatus())){
				throw new AppException("iscm.purchasemanage.unaudit.error.status");
			}
			String processInstanceId = receive.getWorkFlowNo();
			scmPurReceiveEntryList = scmPurReceiveEntryBiz.selectByPvId(receive.getId(), param);
			if(scmPurReceiveEntryList == null || scmPurReceiveEntryList.isEmpty()){
				//抛出异常处理
				throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
			}
			
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//反审批当前已审节点
				CommonBean bean = new CommonBean();
				scmPurReceiveList.add(receive);
				bean.setList(scmPurReceiveList);
				bean.setList2(scmPurReceiveEntryList);
				
				BillType2 billType = billTypeBiz.selectByOrgAndCode(receive.getFinOrgUnitNo(), "PurReceive", param);
				long billTypeId = 0;
				if (billType!=null && billType.isNeedAudit()) {
					billTypeId = billType.getId();
				}
				
				processInstanceId = ActivityUtil.antiAuditProcess(bean, billTypeId, processInstanceId, param.getUsrCode(),  param);
				isFirstTask = ActivityUtil.isFirstTask(processInstanceId, param.getUsrCode(), param);
				receive.setWorkFlowNo(processInstanceId);
			} else {
				//流程编号不存在
				throw new AppException("com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.doAuditPurRequire.processInstanceId");
			}
			
			String status = "";
			if (isFirstTask) {
				status = "D";
			} else {
				status = "P";
			}
			receive.setStatus(status);
			String tableName = ClassUtils.getFinalModelSimpleName(receive);
			CommonEventHistory commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,receive.getStepNo(),receive.getPK(),param);
			if(commonEventHistory!=null && StringUtils.isNotBlank(commonEventHistory.getPreStepNo())) {
				commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,commonEventHistory.getPreStepNo(),receive.getPK(),param);
			}
			if(commonEventHistory!=null) {
				receive.setChecker(commonEventHistory.getOper());
				receive.setCheckDate(commonEventHistory.getOperDate());
			}else {
				receive.setChecker(null);
				receive.setCheckDate(null);
			}
			this.updateStatus(receive, param);
			scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),receive, param);
			commonEventHistoryBiz.updateInvalid(receive,receive.getStepNo(),param);
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setStepNo(receive.getStepNo());
			commonAuditOpinion.setActiveType("U");
			commonAuditOpinion.setOpinion("Y");
			commonEventHistoryBiz.addEventHistory(receive, commonAuditOpinion, param);
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return receive;
	}

	@Override
	public List<ScmPurBillDrillResult> selectDrillBills(ScmPurReceive2 scmPurReceive, Param param) throws AppException {
		if(scmPurReceive != null){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("pvId", scmPurReceive.getId());
			return ((ScmPurReceiveDAO)dao).selectDrillBills(map);
		}
		return null;
	}

	@Override
	public void mobileGenerateWrReceipts(String pvNos, Param param) throws AppException {
		if (StringUtils.isNotEmpty(pvNos)) {
			HashMap<String,Object> map = new HashMap<String,Object>();
			StringBuffer stringBuffer = new StringBuffer("");
			if (StringUtils.contains(pvNos, ",")) {//多个单号以，拆分
				String[] split = pvNos.split(",");
				for (String string : split) {
					stringBuffer.append("'").append(string).append("'").append(",");
				}
				stringBuffer.deleteCharAt(stringBuffer.length()-1);
				map.put("pvNos", stringBuffer.toString());
			}else {
				stringBuffer.append("'").append(pvNos).append("'");
				map.put("pvNos", stringBuffer.toString());
			}
			map.put("controlUnitNo", param.getControlUnitNo());
			List<ScmPurReceive2> scmPurReceive2s = ((ScmPurReceiveDAO)dao).findBypvNos(map);
			if (scmPurReceive2s != null && !scmPurReceive2s.isEmpty()) {
				for (ScmPurReceive2 scmPurReceive2 : scmPurReceive2s) {
					if (!StringUtils.equals("E", scmPurReceive2.getStatus())) {
						String message = Message.getMessage(param.getLang(),"iscm.purchasemanage.purchasebusiness.find.mobileGenerateWrReceipts.checkRelease",new String[] {scmPurReceive2.getPvNo()});
						throw new AppException(message);
					}
				}
				this.generateWrReceipts(scmPurReceive2s, param);
			}else {
				throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
			}
		}else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
	}

	@Override
	public ScmPurReceive2 selectByBillCode(String pvNo, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("pvNo",pvNo);
		map.put("controlUnitNo",param.getControlUnitNo());
		return ((ScmPurReceiveDAO)dao).selectByBillCode(map);
	}

	@Override
	public ScmPurReceive2 updatePrtCount(ScmPurReceive2 scmPurReceive, Param param) throws AppException {
		if(scmPurReceive.getId()>0){
			ScmPurReceive2 bill = this.selectDirect(scmPurReceive.getId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmPurReceive;
	}
}

