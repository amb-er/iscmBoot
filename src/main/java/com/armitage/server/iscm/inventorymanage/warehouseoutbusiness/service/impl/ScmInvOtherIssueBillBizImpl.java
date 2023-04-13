package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.armitage.server.api.business.datasync.params.InvOtherIssueBillDetailParams;
import com.armitage.server.api.business.datasync.params.InvOtherIssueBillListParams;
import com.armitage.server.api.business.datasync.result.DataSyncResult;
import com.armitage.server.api.business.invotherissue.params.InvOtherIssueParams;
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
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialInventoryBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.common.model.CommonAuditOpinion;
import com.armitage.server.iscm.common.model.CommonEventHistory;
import com.armitage.server.iscm.common.service.CommonEventHistoryBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvBalBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.util.ScmWarehouseUtil;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvOtherIssueBillDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBillAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvOtherIssueBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvOtherIssueBillEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReceiveBiz;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.model.SystemState;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.CodeBiz;
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

@Service("scmInvOtherIssueBillBiz")
public class ScmInvOtherIssueBillBizImpl extends BaseBizImpl<ScmInvOtherIssueBill2> implements ScmInvOtherIssueBillBiz {
	private UsrBiz usrBiz;
	private ScmInvOtherIssueBillEntryBiz scmInvOtherIssueBillEntryBiz;
	private OrgUnitBiz orgUnitBiz;
	private ScmInvStockBiz scmInvStockBiz;
	private ScmInvBalBiz scmInvBalBiz;
	private CommonEventHistoryBiz commonEventHistoryBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private PeriodCalendarBiz periodCalendarBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private SystemStateBiz systemStateBiz;
	private SysParamBiz sysParamBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private ScmPurReceiveBiz scmPurReceiveBiz;
	private CodeBiz codeBiz;
	private ScmBillPendingBiz scmBillPendingBiz;
	private BillTypeBiz billTypeBiz;
	private OrgStorageBiz orgStorageBiz;
	private ScmMaterialInventoryBiz scmMaterialInventoryBiz;
	
	protected  HashMap<String,Long> scmMatrialMap = new HashMap<String,Long>();
	protected  HashMap<Long,Long> scmUnitMap = new HashMap<Long,Long>();
	
	public void setScmMaterialInventoryBiz(ScmMaterialInventoryBiz scmMaterialInventoryBiz) {
		this.scmMaterialInventoryBiz = scmMaterialInventoryBiz;
	}
	public void setSystemStateBiz(SystemStateBiz systemStateBiz) {
        this.systemStateBiz = systemStateBiz;
    }
    public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}
	public void setScmInvOtherIssueBillEntryBiz(
			ScmInvOtherIssueBillEntryBiz scmInvOtherIssueBillEntryBiz) {
		this.scmInvOtherIssueBillEntryBiz = scmInvOtherIssueBillEntryBiz;
	}
	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}
	public void setScmInvStockBiz(ScmInvStockBiz scmInvStockBiz) {
		this.scmInvStockBiz = scmInvStockBiz;
	}
	public void setScmInvBalBiz(ScmInvBalBiz scmInvBalBiz) {
		this.scmInvBalBiz = scmInvBalBiz;
	}
	
	public void setCommonEventHistoryBiz(CommonEventHistoryBiz commonEventHistoryBiz) {
		this.commonEventHistoryBiz = commonEventHistoryBiz;
	}
	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}
	public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}
	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}
	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}
	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
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
	
	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}
	
	
	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." + ScmInvOtherIssueBill2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." + ScmInvOtherIssueBill2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." + ScmInvOtherIssueBill2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." + ScmInvOtherIssueBill2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}
	
	@Override
	protected void afterSelect(ScmInvOtherIssueBill2 entity, Param param) throws AppException {
		if (entity != null){
			setConvertMap(entity,param);
		}
	}
	
	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		List<ScmInvOtherIssueBill2> scmInvOtherIssueBillList = list;
		if(scmInvOtherIssueBillList != null && !scmInvOtherIssueBillList.isEmpty()){
			for(ScmInvOtherIssueBill2 scmInvOtherIssueBill : scmInvOtherIssueBillList){
				//视图增加待审人
				ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmInvOtherIssueBill.getOtId(), "InvOthIssueBill",param);
				StringBuffer usrName = new StringBuffer("");
				if (scmBillPendingUsr != null) {
					scmInvOtherIssueBill.setPendingUsr(scmBillPendingUsr.getUsrCodes());
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
				scmInvOtherIssueBill.setPendingUsrName(usrName.toString());
				
				scmInvOtherIssueBill.setNetAmt(scmInvOtherIssueBill.getAmt());
				setConvertMap(scmInvOtherIssueBill,param);
				if("I,R".contains(scmInvOtherIssueBill.getStatus())) {
					scmInvOtherIssueBill.setPendingUsrName("");
				}
			}
		}
	}
	
	private void setConvertMap(ScmInvOtherIssueBill2 scmInvOtherIssueBill,Param param) {
		if(scmInvOtherIssueBill!=null) {
			if (StringUtils.isNotBlank(scmInvOtherIssueBill.getCreator())){
				//制单人  
				Usr usr = usrBiz.selectByCode(scmInvOtherIssueBill.getCreator(), param);
				if (usr != null) {
					if(scmInvOtherIssueBill.getDataDisplayMap()==null){
						scmInvOtherIssueBill.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
					}
					scmInvOtherIssueBill.getDataDisplayMap().put(ScmInvOtherIssueBill2.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
					scmInvOtherIssueBill.setConvertMap(ScmInvOtherIssueBill2.FN_CREATOR, usr);
					scmInvOtherIssueBill.setCreatorName(usr.getName());
				}
			}
			if (StringUtils.isNotBlank(scmInvOtherIssueBill.getOrgUnitNo())){
				//库存组织
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvOtherIssueBill.getOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmInvOtherIssueBill.setConvertMap(ScmInvOtherIssueBill2.FN_ORGUNITNO, orgBaseUnit);
				}
			}
			
			if (StringUtils.isNotBlank(scmInvOtherIssueBill.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmInvOtherIssueBill.getEditor(), param);
				if (usr != null) {
					scmInvOtherIssueBill.setConvertMap(ScmInvOtherIssueBill.FN_EDITOR, usr);
					scmInvOtherIssueBill.setEditorName(usr.getName());
				}
			}
			
			if (StringUtils.isNotBlank(scmInvOtherIssueBill.getChecker())){
				//审核人
				Usr usr = usrBiz.selectByCode(scmInvOtherIssueBill.getChecker(), param);
				if (usr != null) {
					scmInvOtherIssueBill.setConvertMap(ScmInvOtherIssueBill.FN_CHECKER, usr);
					scmInvOtherIssueBill.setCheckerName(usr.getName());
				}
			}
			if (scmInvOtherIssueBill.getWareHouseId() > 0){
				//仓库
				ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvOtherIssueBill.getWareHouseId(), param);
				if (scmInvWareHouse != null) {
					scmInvOtherIssueBill.setConvertMap(ScmInvOtherIssueBill.FN_WAREHOUSEID, scmInvWareHouse);
					scmInvOtherIssueBill.setWareHouseCode(scmInvWareHouse.getWhNo());
					scmInvOtherIssueBill.setWareHouseName(scmInvWareHouse.getWhName());
				}
			}
			if (scmInvOtherIssueBill.getVendorId() > 0){
				Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmInvOtherIssueBill.getVendorId(), param);
				if(scmsupplier!=null)
					scmInvOtherIssueBill.setConvertMap(ScmInvOtherIssueBill.FN_VENDORID, scmsupplier);
			}
			if(StringUtils.isNotBlank(scmInvOtherIssueBill.getStatus())){
				Code code = codeBiz.selectByCategoryAndCode("warehouseStatus", scmInvOtherIssueBill.getStatus());
				if(code!=null)
					scmInvOtherIssueBill.setStatusName(code.getName());
			}
			if (StringUtils.isNotBlank(scmInvOtherIssueBill.getCreateOrgUnitNo())){
				//制单组织
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvOtherIssueBill.getCreateOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmInvOtherIssueBill.setConvertMap(scmInvOtherIssueBill.FN_CREATEORGUNITNO, orgBaseUnit);
				}
			}
			if(StringUtils.isNotBlank(scmInvOtherIssueBill.getBizType())){
				Code code = codeBiz.selectByCategoryAndCode("devoteWHBizType", scmInvOtherIssueBill.getBizType());
				if(code!=null)
					scmInvOtherIssueBill.setBizTypeName(code.getName());
			}
			scmInvOtherIssueBill.setNetAmt(scmInvOtherIssueBill.getTotalTaxAmt().subtract(scmInvOtherIssueBill.getTotalAmt()));
		}
	}
	
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmInvOtherIssueBill2 scmInvOtherIssueBill = (ScmInvOtherIssueBill2) bean.getList().get(0);
		if(scmInvOtherIssueBill != null && scmInvOtherIssueBill.getOtId() > 0){
			HashMap<String,Object> cacheMap = new HashMap<String,Object>();
			List<ScmInvOtherIssueBillEntry2> scmInvOtherIssueBillEntryList = scmInvOtherIssueBillEntryBiz.selectByOtId(scmInvOtherIssueBill.getOtId(), param);
			if(scmInvOtherIssueBillEntryList != null && !scmInvOtherIssueBillEntryList.isEmpty()){
				bean.setList2(scmInvOtherIssueBillEntryList);
			}
		}
	}
	
	@Override
	protected void beforeAdd(ScmInvOtherIssueBill2 entity, Param param) throws AppException {
		if(entity != null){
			String code = CodeAutoCalUtil.getBillCode("InvOthIssueBill", entity, param);
			entity.setOtNo(code);
//			entity.setOtNo((getBillNo(entity.getCreateDate(),param)));
			List<OrgCompany2> orgCompanylist = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, entity.getOrgUnitNo(), false, null, param);
			if(orgCompanylist==null || orgCompanylist.isEmpty()) {
				throw new AppException("iscm.inventorymanage.cstbusiness.ScmInvCountingTask.countingFinish.error2");
			}
			entity.setFinOrgUnitNo(orgCompanylist.get(0).getOrgUnitNo());
			//获取期间
			PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(entity.getBizDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
			}
			entity.setPeriodId(periodCalendar.getPeriodId());
			entity.setAccountYear(periodCalendar.getAccountYear());
			entity.setAccountPeriod(periodCalendar.getAccountPeriod());
			
//			BillType2 billType = billTypeBiz.selectByOrgAndCode(entity.getFinOrgUnitNo(), "InvOthIssueBill", param);
//			if(billType!=null && billType.isNeedAudit()) {
//				if(StringUtils.isBlank(billType.getWorkFlowNo())) {
//					throw new AppException(Message.getMessage(param.getLang(), "iscm.workflow.needaudit.notprocess", new String[] {Message.getMessage(param.getLang(),"field.ScmInvOtherIssueBill")}));
//				}
//				entity.setWorkFlowNo(billType.getWorkFlowNo());
//			}
		}
	}
	
	@Override
	protected void beforeUpdate(ScmInvOtherIssueBill2 oldEntity,
			ScmInvOtherIssueBill2 newEntity, Param param) throws AppException {
		//获取期间
		PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(newEntity.getBizDate(), param);
		if(periodCalendar==null){
			throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
		}
		newEntity.setPeriodId(periodCalendar.getPeriodId());
		newEntity.setAccountYear(periodCalendar.getAccountYear());
		newEntity.setAccountPeriod(periodCalendar.getAccountPeriod());
		
//		BillType2 billType = billTypeBiz.selectByOrgAndCode(newEntity.getFinOrgUnitNo(), "InvOthIssueBill", param);
//		if(billType!=null && billType.isNeedAudit()) {
//			if(StringUtils.isBlank(billType.getWorkFlowNo())) {
//				throw new AppException(Message.getMessage(param.getLang(), "iscm.workflow.needaudit.notprocess", new String[] {Message.getMessage(param.getLang(),"field.ScmInvOtherIssueBill")}));
//			}
//			newEntity.setWorkFlowNo(billType.getWorkFlowNo());
//		}
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmInvOtherIssueBill2 scmInvOtherIssueBill = (ScmInvOtherIssueBill2) bean.getList().get(0);
		if(scmInvOtherIssueBill != null && scmInvOtherIssueBill.getOtId() > 0){
			//新增出库明细
			List<ScmInvOtherIssueBillEntry2> scmInvOtherIssueBillEntryList = bean.getList2();
			if(scmInvOtherIssueBillEntryList != null && !scmInvOtherIssueBillEntryList.isEmpty()){
                int lineId = 1;
				for(ScmInvOtherIssueBillEntry2 scmInvOtherIssueBillEntry : scmInvOtherIssueBillEntryList){
					if(StringUtils.equals("7", scmInvOtherIssueBill.getBizType()) && StringUtils.isBlank(scmInvOtherIssueBillEntry.getReqFinOrgUnitNo())) {
						throw new AppException(Message.getMessage(param.getLang(),"iscm.server.ScmInvOtherIssueBillBizImpl.reqFinOrgUnitNo.isnull",new String[] {String.valueOf(lineId)}));
					}
					scmInvOtherIssueBillEntry.setLineId(lineId);
					scmInvOtherIssueBillEntry.setOtId(scmInvOtherIssueBill.getOtId());
					scmInvOtherIssueBillEntry.setOrgUnitNo(scmInvOtherIssueBill.getOrgUnitNo());
					if(scmInvOtherIssueBill.getWareHouseId()>0)
						scmInvOtherIssueBillEntry.setWareHouseId(scmInvOtherIssueBill.getWareHouseId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvOtherIssueBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvOtherIssueBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvOtherIssueBillEntry.getItemId(), scmInvOtherIssueBillEntry.getUnit(), param);//库存单位转换系数
					scmInvOtherIssueBillEntry.setBaseQty(scmInvOtherIssueBillEntry.getQty().multiply(invConvRate));
					scmInvOtherIssueBillEntryBiz.add(scmInvOtherIssueBillEntry, param);
                    lineId = lineId+1;
				}
			}
		}
	}
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvOtherIssueBill2 scmInvOtherIssueBill = (ScmInvOtherIssueBill2) bean.getList().get(0);
		if(scmInvOtherIssueBill != null && scmInvOtherIssueBill.getOtId() > 0){
			//更新出库明细    
			List<ScmInvOtherIssueBillEntry2> scmInvOtherIssueBillEntryList = bean.getList2();
			if(scmInvOtherIssueBillEntryList != null && !scmInvOtherIssueBillEntryList.isEmpty()){
                int lineId = 1;
				for(ScmInvOtherIssueBillEntry2 scmInvOtherIssueBillEntry : scmInvOtherIssueBillEntryList){
					if(StringUtils.equals("7", scmInvOtherIssueBill.getBizType()) && StringUtils.isBlank(scmInvOtherIssueBillEntry.getReqFinOrgUnitNo())) {
						throw new AppException(Message.getMessage(param.getLang(),"iscm.server.ScmInvOtherIssueBillBizImpl.reqFinOrgUnitNo.isnull",new String[] {String.valueOf(lineId)}));
					}
					if (StringUtils.equals("I", scmInvOtherIssueBill.getStatus())) {
						scmInvOtherIssueBillEntry.setLineId(lineId);
					}
					scmInvOtherIssueBillEntry.setOtId(scmInvOtherIssueBill.getOtId());
					scmInvOtherIssueBillEntry.setOrgUnitNo(scmInvOtherIssueBill.getOrgUnitNo());
					if(scmInvOtherIssueBill.getWareHouseId()>0)
						scmInvOtherIssueBillEntry.setWareHouseId(scmInvOtherIssueBill.getWareHouseId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvOtherIssueBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvOtherIssueBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvOtherIssueBillEntry.getItemId(), scmInvOtherIssueBillEntry.getUnit(), param);//库存单位转换系数
					scmInvOtherIssueBillEntry.setBaseQty(scmInvOtherIssueBillEntry.getQty().multiply(invConvRate));
					scmInvOtherIssueBillEntry.setOtId(scmInvOtherIssueBill.getOtId());
                    lineId = lineId+1;
				}
				scmInvOtherIssueBillEntryBiz.update(scmInvOtherIssueBill, scmInvOtherIssueBillEntryList, ScmInvOtherIssueBillEntry2.FN_OTID, param);
			}
		}
	}
	

	public ScmInvOtherIssueBill2 updateStatus(ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param) throws AppException {
		if(scmInvOtherIssueBill != null){
			return this.updateDirect(scmInvOtherIssueBill, param);
		}
		return null;
	}

	@Override
	public ScmInvOtherIssueBill2 postBill(ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param)
			throws AppException {
		scmInvOtherIssueBill = this.selectDirect(scmInvOtherIssueBill.getOtId(), param);
        if (scmInvOtherIssueBill != null) {
            if(!StringUtils.equals("A",scmInvOtherIssueBill.getStatus())) {
            	throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvOtherIssueBill.getOtNo()}));
    		}
			// 1 查出明细
            List<ScmInvOtherIssueBillEntry2> scmInvOtherIssueBillEntryList = scmInvOtherIssueBillEntryBiz.selectByOtId(scmInvOtherIssueBill.getOtId(), param);
            if(scmInvOtherIssueBillEntryList != null && !scmInvOtherIssueBillEntryList.isEmpty()){
    			scmInvOtherIssueBillEntryList = (List<ScmInvOtherIssueBillEntry2>)ListSortUtil.sort(scmInvOtherIssueBillEntryList, "lineId","Desc");	//按行号排序
    			int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
    			HashMap<String,List<ScmInvStock2>> qtyMap = new HashMap<String,List<ScmInvStock2>>();
    			int lineId=1;
                for (ScmInvOtherIssueBillEntry2 scmInvOtherIssueBillEntry2 : scmInvOtherIssueBillEntryList) {
                	StringBuffer info = new StringBuffer("");
    				info.append(scmInvOtherIssueBillEntry2.getOrgUnitNo()).append("_")
    						.append(scmInvOtherIssueBillEntry2.getWareHouseId()).append("_")
    						.append(String.valueOf(scmInvOtherIssueBillEntry2.getItemId())).append("_");
    				if(!qtyMap.containsKey((info.toString()))){
	                    // 2 批次拆单, 查询结存(先进先出还是后进先出)
	                    Page page = new Page();
	                    page.setModelClass(ScmMaterial2.class);
	                    page.setShowCount(Integer.MAX_VALUE);
	                    page.setSqlCondition("ScmMaterial.id="+scmInvOtherIssueBillEntry2.getItemId());
	                    List<String> argList = new ArrayList<String>();
	                    argList.add("orgUnitNo=" + param.getOrgUnitNo());
	                    argList.add("controlUnitNo=" + param.getControlUnitNo());
	                    List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "findBySingleFinAllPage", param);
	                    HashMap<String, Object> map = new HashMap<String, Object>();
	                    map.put("orgUnitNo", scmInvOtherIssueBillEntry2.getOrgUnitNo());
	                    map.put("itemId", scmInvOtherIssueBillEntry2.getItemId());
	                    map.put("wareHouseId", scmInvOtherIssueBillEntry2.getWareHouseId());
	                    map.put("bizDate", FormatUtils.fmtDate(scmInvOtherIssueBill.getBizDate()));
	                    map.put("unit", scmInvOtherIssueBillEntry2.getUnit());
	                    if (scmMaterialList != null && scmMaterialList.size() > 0) {
	                        map.put("costMethod", scmMaterialList.get(0).getCosting());
	                    }
	                    if(scmInvOtherIssueBill.getVendorId()>0) {
	                    	//指定出库哪个供应商则按供应商来扣减结存
	                    	map.put("vendorId", scmInvOtherIssueBill.getVendorId());
	                    }
	                    List<ScmInvStock2> list = scmInvStockBiz.findByDate(map, param);
	                    if (list == null || list.size() == 0) {
	                        throw new AppException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl.common.stockNotEnough",new String[]{scmInvOtherIssueBillEntry2.getItemName()});
	                    }
	                    qtyMap.put(info.toString(), list);
    				}
    				List<ScmInvStock2> stocklist = qtyMap.get(info.toString());
    				if(stocklist != null && !stocklist.isEmpty()){
    					if(StringUtils.isNotBlank(scmInvOtherIssueBillEntry2.getLot())) {
                    		//有批次则先按批次找，如不够再按先进先出拆单
                    		for (ScmInvStock2 scmInvStock : stocklist) {
                    			if(StringUtils.equals(scmInvOtherIssueBillEntry2.getLot(), scmInvStock.getLot())) {
    		                		if(scmInvOtherIssueBillEntry2.getQty().compareTo(BigDecimal.ZERO)>0) {
    		                			if(setDataFromStock(scmInvStock,scmInvOtherIssueBillEntry2,amtPrecision,lineId,param))
    		                				lineId = lineId + 1;
    		                		}else {
    		                			break;
    		                		}
                    			}
                    		}
                    	}
                    	for (ScmInvStock2 scmInvStock : stocklist) {
                    		if(scmInvOtherIssueBillEntry2.getQty().compareTo(BigDecimal.ZERO)>0) {
                    			if(setDataFromStock(scmInvStock,scmInvOtherIssueBillEntry2,amtPrecision,lineId,param))
	                				lineId = lineId + 1;
                    		}else {
                    			break;
                    		}
                    	}
    				}
                    // 删除原来的明细
                    scmInvOtherIssueBillEntryBiz.delete(scmInvOtherIssueBillEntry2, param);
                }
                scmInvOtherIssueBillEntryList = scmInvOtherIssueBillEntryBiz.selectOutEffectRow(scmInvOtherIssueBill.getOtId(), param);
            	// 即使库存(结存) 
    			int updateRow = scmInvStockBiz.updateByOtherIssueOutSub(scmInvOtherIssueBill.getOtId(), param);
    			if(updateRow<scmInvOtherIssueBillEntryList.size()){
        			throw new AppException("iscm.inventorymanage.common.post.error.rowsnotequal");
        		}
    			// 期间余额 
    			scmInvBalBiz.updateByOtherIssueOutSub(scmInvOtherIssueBill.getOtId(), param);
    			// 期间移动平均即时成本表
//    			scmInvCostBiz.updateByOtherIssueOutSub(scmInvOtherIssueBill.getOtId(), param);
            }
                        
            // 主表(postDate=getDate(),E; otId, status='A';) 
			if (!(ParamType.Operation.equals(param.getParamType()) && ((OperationParam) param).getOperationId()==629223199L)) {
	            scmInvOtherIssueBill.setCheckDate(CalendarUtil.getDate(param));
	            scmInvOtherIssueBill.setChecker(param.getUsrCode());
	            scmInvOtherIssueBill.setPostDate(CalendarUtil.getDate(param));
			}
	        scmInvOtherIssueBill.setStatus("E");
            int updateRow = this.updatePostedStatus(scmInvOtherIssueBill, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvOtherIssueBill.getOtNo()}));
			}
			if (!(ParamType.Operation.equals(param.getParamType()) && ((OperationParam) param).getOperationId()==629223199L)) {
				afterPostBill(scmInvOtherIssueBill,param);
			}
		}
		return scmInvOtherIssueBill;
	}

	private int updatePostedStatus(ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param) {
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("otId",scmInvOtherIssueBill.getOtId());
		map.put("checker",scmInvOtherIssueBill.getChecker());
		map.put("checkDate",scmInvOtherIssueBill.getCheckDate()==null?null:FormatUtils.fmtDateTime(scmInvOtherIssueBill.getCheckDate()));
		map.put("status", scmInvOtherIssueBill.getStatus());
		map.put("postDate", scmInvOtherIssueBill.getPostDate()==null?null:FormatUtils.fmtDateTime(scmInvOtherIssueBill.getPostDate()));
		return ((ScmInvOtherIssueBillDAO)dao).updatePostedStatus(map);
	}
	private void afterPostBill(ScmInvOtherIssueBill2 scmInvOtherIssueBill,Param param) {
    	if(StringUtils.equals("7", scmInvOtherIssueBill.getBizType()))
    		scmPurReceiveBiz.generateFromOtherIssue(scmInvOtherIssueBill, param);
	}
	private boolean setDataFromStock(ScmInvStock2 scmInvStock,ScmInvOtherIssueBillEntry2 scmInvOtherIssueBillEntry2,int amtPrecision,Integer lineId,Param param) {
		boolean flag=false;
		BigDecimal stockQty = scmInvStock.getQty();
		BigDecimal stockPieQty = scmInvStock.getPieQty();
		BigDecimal stockBaseQty = scmInvStock.getBaseQty();
		BigDecimal stockAmt = (scmInvStock.getAmt());
		BigDecimal stockTaxAmt = (scmInvStock.getTaxAmt());
		if (stockQty.compareTo(scmInvOtherIssueBillEntry2.getQty()) > 0) {
			ScmInvOtherIssueBillEntry2 scmInvOtherIssueBillEntry = new ScmInvOtherIssueBillEntry2();
			BeanUtils.copyProperties(scmInvOtherIssueBillEntry2, scmInvOtherIssueBillEntry, new String[] { "id" });
			scmInvOtherIssueBillEntry.setLot(scmInvStock.getLot());
			scmInvOtherIssueBillEntry.setStockId(scmInvStock.getId());
			scmInvOtherIssueBillEntry.setPrice(scmInvStock.getPrice());
			scmInvOtherIssueBillEntry.setTaxPrice(scmInvStock.getTaxPrice());
			scmInvOtherIssueBillEntry.setTaxRate(scmInvStock.getTaxRate());
			scmInvOtherIssueBillEntry.setAmt(scmInvOtherIssueBillEntry.getQty().multiply(scmInvOtherIssueBillEntry.getPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
			scmInvOtherIssueBillEntry.setTaxAmt(scmInvOtherIssueBillEntry.getQty().multiply(scmInvOtherIssueBillEntry.getTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
			scmInvOtherIssueBillEntry.setOrgSourceId(scmInvStock.getSourceBillId());
			scmInvOtherIssueBillEntry.setOrgSourceVendorId(scmInvStock.getVendorId());
			scmInvOtherIssueBillEntry.setInvDate(scmInvStock.getInvDate());
			scmInvOtherIssueBillEntry.setLineId(lineId);
			scmInvOtherIssueBillEntryBiz.add(scmInvOtherIssueBillEntry, param);
			scmInvOtherIssueBillEntry2.setQty(BigDecimal.ZERO);
			scmInvOtherIssueBillEntry2.setPieQty(BigDecimal.ZERO);
			scmInvOtherIssueBillEntry2.setBaseQty(BigDecimal.ZERO);
    		scmInvStock.setQty(scmInvStock.getQty().subtract(scmInvOtherIssueBillEntry.getQty()));
    		scmInvStock.setPieQty(scmInvStock.getPieQty().subtract(scmInvOtherIssueBillEntry.getPieQty()));
    		scmInvStock.setBaseQty(scmInvStock.getBaseQty().subtract(scmInvOtherIssueBillEntry.getBaseQty()));
    		scmInvStock.setAmt(scmInvStock.getAmt().subtract(scmInvOtherIssueBillEntry.getAmt()));
    		scmInvStock.setTaxAmt(scmInvStock.getTaxAmt().subtract(scmInvOtherIssueBillEntry.getTaxAmt()));
    		flag=true;
		} else if (stockQty.compareTo(BigDecimal.ZERO) > 0) {
			ScmInvOtherIssueBillEntry2 scmInvOtherIssueBillEntry = new ScmInvOtherIssueBillEntry2();
			BeanUtils.copyProperties(scmInvOtherIssueBillEntry2, scmInvOtherIssueBillEntry, new String[] { "id" });
			scmInvOtherIssueBillEntry.setLot(scmInvStock.getLot());
			scmInvOtherIssueBillEntry.setStockId(scmInvStock.getId());
			scmInvOtherIssueBillEntry.setPrice(scmInvStock.getPrice());
			scmInvOtherIssueBillEntry.setTaxPrice(scmInvStock.getTaxPrice());
			scmInvOtherIssueBillEntry.setTaxRate(scmInvStock.getTaxRate());
			scmInvOtherIssueBillEntry.setQty(stockQty);
			scmInvOtherIssueBillEntry.setPieQty(stockPieQty);
			scmInvOtherIssueBillEntry.setBaseQty(stockBaseQty);
			scmInvOtherIssueBillEntry.setAmt(stockAmt);
			scmInvOtherIssueBillEntry.setTaxAmt(stockTaxAmt);
			scmInvOtherIssueBillEntry.setOrgSourceId(scmInvStock.getSourceBillId());
			scmInvOtherIssueBillEntry.setOrgSourceVendorId(scmInvStock.getVendorId());
			scmInvOtherIssueBillEntry.setInvDate(scmInvStock.getInvDate());
			scmInvOtherIssueBillEntry.setLineId(lineId);
			scmInvOtherIssueBillEntryBiz.add(scmInvOtherIssueBillEntry, param);
			scmInvOtherIssueBillEntry2.setQty(scmInvOtherIssueBillEntry2.getQty().subtract(scmInvOtherIssueBillEntry.getQty()));
			scmInvOtherIssueBillEntry2.setPieQty(scmInvOtherIssueBillEntry2.getPieQty().subtract(scmInvOtherIssueBillEntry.getPieQty()));
			scmInvOtherIssueBillEntry2.setBaseQty(scmInvOtherIssueBillEntry2.getBaseQty().subtract(scmInvOtherIssueBillEntry.getBaseQty()));
			scmInvStock.setQty(BigDecimal.ZERO);
    		scmInvStock.setPieQty(BigDecimal.ZERO);
    		scmInvStock.setBaseQty(BigDecimal.ZERO);
    		scmInvStock.setAmt(BigDecimal.ZERO);
    		scmInvStock.setTaxAmt(BigDecimal.ZERO);
    		flag=true;
		}
		return flag;
	}
	@Override
	public List<String> postBillCheck(ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param) throws AppException {
		// 财务组织会计期间等于当前期间
	    List<String> msgList = new ArrayList<String>();// 提示列表
	    scmInvOtherIssueBill = this.selectDirect(scmInvOtherIssueBill.getOtId(), param);
        if (scmInvOtherIssueBill != null) {
            if(!StringUtils.equals("A",scmInvOtherIssueBill.getStatus())) {
    			msgList.add(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvOtherIssueBill.getOtNo()}));
    			return msgList;
    		}
            SystemState systemState = systemStateBiz.selectBySystemId(scmInvOtherIssueBill.getFinOrgUnitNo(), 170, param);
            if(systemState==null){
                msgList.add(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
                return msgList;
            }
            if (systemState.getCurrentPeriodId() != scmInvOtherIssueBill.getPeriodId()) {
                msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmInvOtherIssueBill.getOtNo()}));
                return msgList;
            }
            
            // 检查盘点物资冻结
            // 检查冻结仓库
            List<ScmInvOtherIssueBill2> scmInvOtherIssueBillList = ((ScmInvOtherIssueBillDAO) dao).checkWareHouseFree(scmInvOtherIssueBill.getOtId());
            if (scmInvOtherIssueBillList != null && !scmInvOtherIssueBillList.isEmpty()) {
            	msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
                return msgList;
//            	for (ScmInvOtherIssueBill2 scmInvOtherIssueBill2 : scmInvOtherIssueBillList) {
//	                // 检查冻结物资
//	                HashMap<String,Object> map = new HashMap<String,Object>();
//	                map.put("taskId", scmInvOtherIssueBill2.getTaskId());
//	                map.put("otId", scmInvOtherIssueBill.getOtId());
//	                int count = ((ScmInvOtherIssueBillDAO) dao).checkMaterialFree(map);
//	                if (count > 0) {
//	                    msgList.add(Message.getMessage(param.getLang(), 
//	                            "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
//	                    return msgList;
//	                }
//            	}
            }
            
    		List<ScmInvOtherIssueBillEntry2> list = scmInvOtherIssueBillEntryBiz.selectInvQty(scmInvOtherIssueBill.getOtId(), param);
    		if (list != null && list.size() > 0) {
    		    for (int i = 0; i < list.size(); i++) {
    		        if (StringUtils.isNotBlank(list.get(i).getLot())) {
    		            msgList.add(Message.getMessage(param.getLang(), 
    		                    "com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl.ScmInvSaleIssueBillBizImpl.count", 
    		                    new String[] {list.get(i).getItemNo(), list.get(i).getItemName(), list.get(i).getLot(),
    		                            list.get(i).getQty().toString(), list.get(i).getInvQty().toString()}));
    		        } else {
    		            msgList.add(Message.getMessage(param.getLang(), 
    		                    "com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl.ScmInvSaleIssueBillBizImpl.count2", 
    		                    new String[] {list.get(i).getItemNo(), list.get(i).getItemName(), 
    		                            list.get(i).getQty().toString(), list.get(i).getInvQty().toString()}));
    		        }
                }
    		    return msgList;
            }
    		
        }
        return msgList;
	}

	@Override
	public ScmInvOtherIssueBill2 cancelPostBill(ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param)
			throws AppException {
		scmInvOtherIssueBill = this.selectDirect(scmInvOtherIssueBill.getOtId(), param);
        if (scmInvOtherIssueBill != null) {
            if(!StringUtils.equals("E",scmInvOtherIssueBill.getStatus())) {
            	throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvOtherIssueBill.getOtNo()}));
    		}
			SystemState systemState = systemStateBiz.selectBySystemId(scmInvOtherIssueBill.getFinOrgUnitNo(), 170, param);
            if(systemState==null){
            	throw new AppException(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
            }
            if (systemState.getCurrentPeriodId() != scmInvOtherIssueBill.getPeriodId()) {
            	throw new AppException(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmInvOtherIssueBill.getOtNo()}));
            }
            
            // 检查盘点物资冻结
            // 检查冻结仓库
            List<ScmInvOtherIssueBill2> scmInvOtherIssueBillList = ((ScmInvOtherIssueBillDAO) dao).checkWareHouseFree(scmInvOtherIssueBill.getOtId());
            if (scmInvOtherIssueBillList != null && !scmInvOtherIssueBillList.isEmpty()) {
            	throw new AppException(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
//            	for (ScmInvOtherIssueBill2 scmInvOtherIssueBill2 : scmInvOtherIssueBillList) {
//	                // 检查冻结物资
//	                HashMap<String,Object> map = new HashMap<String,Object>();
//	                map.put("taskId", scmInvOtherIssueBill2.getTaskId());
//	                map.put("otId", scmInvOtherIssueBill.getOtId());
//	                int count = ((ScmInvOtherIssueBillDAO) dao).checkMaterialFree(map);
//	                if (count > 0) {
//	                	throw new AppException(Message.getMessage(param.getLang(), 
//	                            "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
//	                }
//            	}
            }
            
        	 // 即时库存(结存) 
            scmInvStockBiz.updateByOtherIssueInSub(scmInvOtherIssueBill.getOtId(), param);
            // 期间余额   
            scmInvBalBiz.updateByOtherIssueInSub(scmInvOtherIssueBill.getOtId(), param);
            // 期间移动平均即时成本表
//            scmInvCostBiz.updateByOtherIssueInSub(scmInvOtherIssueBill.getOtId(), param);
            // 主表(A) 
            scmInvOtherIssueBill.setCheckDate(null);
            scmInvOtherIssueBill.setChecker("");
            scmInvOtherIssueBill.setStatus("A");
            scmInvOtherIssueBill.setPostDate(null);
            int updateRow = this.updatePostedStatus(scmInvOtherIssueBill, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvOtherIssueBill.getOtNo()}));
			}
            afterCancelPostBill(scmInvOtherIssueBill, param);
        }
        return scmInvOtherIssueBill;
	}

	private void afterCancelPostBill(ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param){
    	if(StringUtils.equals("7", scmInvOtherIssueBill.getBizType())) {
    		scmPurReceiveBiz.deleteFromOtherIssue(scmInvOtherIssueBill, param);
    	}
    }
	@Override
	public String getBillNo(Date date, Param param) throws AppException {
		String dateStr = StringUtils.replace(FormatUtils.fmtDate(date), "-", "");
		StringBuffer s = new StringBuffer("");
		s.append("WI").append(dateStr);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("createDate", dateStr);
		map.put("controlUnitNo", param.getControlUnitNo());
		ScmInvOtherIssueBill2 scmInvOtherIssueBill= ((ScmInvOtherIssueBillDAO) dao).selectMaxIdByDate(map);
		if(scmInvOtherIssueBill != null && StringUtils.isNotBlank(scmInvOtherIssueBill.getOtNo())
				&& scmInvOtherIssueBill.getOtNo().contains(dateStr)){
			String str = StringUtils.substring(scmInvOtherIssueBill.getOtNo(), (scmInvOtherIssueBill.getOtNo().indexOf(dateStr)+dateStr.length()));
			str = CodeAutoCalUtil.autoAddOne(str);
			str = (s.append(str)).toString();
			return str;
		}else{
			return (s.append("001").toString());
		}
	}
	@Override
	protected void beforeDelete(ScmInvOtherIssueBill2 entity, Param param)
			throws AppException {
		ScmInvOtherIssueBill2 scmInvOtherIssueBill = this.selectDirect(entity.getOtId(), param);
		if(!StringUtils.equals(scmInvOtherIssueBill.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getOtNo()}));
		}
		if(entity != null && entity.getOtId() > 0){
			//删除出库明细
			scmInvOtherIssueBillEntryBiz.deleteByOtId(entity.getOtId(), param);
		}
	}
	
	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvOtherIssueBill2 entry = (ScmInvOtherIssueBill2) bean.getList().get(0);
		if(entry!=null){
			ScmInvOtherIssueBill2 scmInvOtherIssueBill = this.select(entry.getPK(), param);
			if(!StringUtils.contains("I,D,P", scmInvOtherIssueBill.getStatus())){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
		}
	}
	@Override
	public List<ScmInvOtherIssueBill2> checkUnPostBill(String finOrgUnitNo,long periodId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmInvOtherIssueBillDAO)dao).checkUnPostBill(map);
	}
	@Override
	public List<ScmInvOtherIssueBill2> checkPostedBill(String finOrgUnitNo,long periodId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmInvOtherIssueBillDAO)dao).checkPostedBill(map);
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvOtherIssueBillAdvQuery) {
				ScmInvOtherIssueBillAdvQuery scmInvOtherIssueBillAdvQuery = (ScmInvOtherIssueBillAdvQuery) page.getModel();
				if(scmInvOtherIssueBillAdvQuery.getWareHouseId()>0) {
					page.getParam().put(ScmInvOtherIssueBill2.FN_WAREHOUSEID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." +ScmInvOtherIssueBill2.FN_WAREHOUSEID, QueryParam.QUERY_EQ,String.valueOf(scmInvOtherIssueBillAdvQuery.getWareHouseId())));
				}
				if(scmInvOtherIssueBillAdvQuery.getBizDateFrom()!=null){
					if(scmInvOtherIssueBillAdvQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmInvOtherIssueBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." +ScmInvOtherIssueBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvOtherIssueBillAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(scmInvOtherIssueBillAdvQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmInvOtherIssueBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." +ScmInvOtherIssueBill2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvOtherIssueBillAdvQuery.getBizDateFrom())));
					}
				}else if(scmInvOtherIssueBillAdvQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmInvOtherIssueBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." +ScmInvOtherIssueBill2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmInvOtherIssueBillAdvQuery.getBizDateTo())));
				}
				if(scmInvOtherIssueBillAdvQuery.getCreateDateFrom()!=null){
					if(scmInvOtherIssueBillAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmInvOtherIssueBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." +ScmInvOtherIssueBill2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvOtherIssueBillAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvOtherIssueBillAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmInvOtherIssueBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." +ScmInvOtherIssueBill2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvOtherIssueBillAdvQuery.getCreateDateFrom())));
					}
				}else if(scmInvOtherIssueBillAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmInvOtherIssueBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." +ScmInvOtherIssueBill2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvOtherIssueBillAdvQuery.getCreateDateTo(),1))));
				}
				if(scmInvOtherIssueBillAdvQuery.getVendorId()>0) {
					page.getParam().put(ScmInvOtherIssueBill2.FN_VENDORID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." +ScmInvOtherIssueBill2.FN_VENDORID, QueryParam.QUERY_EQ,String.valueOf(scmInvOtherIssueBillAdvQuery.getVendorId())));
				}
				if(StringUtils.isNotBlank(scmInvOtherIssueBillAdvQuery.getReqFinOrgUnitNo())) {
					page.getParam().put(ScmInvOtherIssueBillEntry2.FN_REQFINORGUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBillEntry2.class) + "." +ScmInvOtherIssueBillEntry2.FN_REQFINORGUNITNO, QueryParam.QUERY_EQ,scmInvOtherIssueBillAdvQuery.getReqFinOrgUnitNo()));
				}
				String filterWarehouseByUsr = ScmWarehouseUtil.filterWarehouseByUsr("", param);
				if (StringUtils.isNotBlank(filterWarehouseByUsr)) {
					page.getParam().put(ScmInvOtherIssueBill2.FN_WAREHOUSEID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." +ScmInvOtherIssueBill2.FN_WAREHOUSEID, QueryParam.QUERY_IN,filterWarehouseByUsr));
				}
			}
		}
	}
	@Override
	public void generateDepositIssue(String orgUnitNo,ScmPurOrder2 scmPurOrder, List<ScmPurOrderEntry2> scmPurOrderEntryList,Param param) throws AppException {
		int qtyPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_QtyPrecision", "4", param));
		int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
		StringBuffer itemNos = new StringBuffer("");
		for(ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList) {
			if(StringUtils.isNotBlank(itemNos.toString()))
				itemNos.append(",");
			itemNos.append("'").append(scmPurOrderEntry.getItemNo()).append("'");
		}
        Page page = new Page();
		page.setModelClass(ScmMaterial2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.setSqlCondition("ScmMaterial.itemNo in ("+itemNos.toString()+")");
		ArrayList argList = new ArrayList();
        argList.add("orgUnitNo="+orgUnitNo);
        argList.add("controlUnitNo=" + param.getControlUnitNo());
        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "findByInvAllPage", param);
		//按收货组织分单
        List<String> list = new ArrayList<>();
        for (ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList) {
        	if(!list.contains(scmPurOrderEntry.getReqFinOrgUnitNo())){
        		list.add(scmPurOrderEntry.getReqFinOrgUnitNo());
				CommonBean bean = new CommonBean();
		        List<ScmInvOtherIssueBill2> scmInvOtherIssueBillList = new ArrayList<>();
		        List<ScmInvOtherIssueBillEntry2> scmInvOtherIssueBillEntryList = new ArrayList<>();
		        ScmInvOtherIssueBill2 scmInvOtherIssueBill = new ScmInvOtherIssueBill2(true);
		        scmInvOtherIssueBill.setStatus("I");
		        scmInvOtherIssueBill.setOrgUnitNo(orgUnitNo);
		        scmInvOtherIssueBill.setVendorId(scmPurOrder.getVendorId());
		        scmInvOtherIssueBill.setBizDate(FormatUtils.parseDate(FormatUtils.fmtDate(CalendarUtil.getDate(param))));
		        // 来源单类型
		        scmInvOtherIssueBill.setBillType("PurOrder");	//来自采购订单
		        scmInvOtherIssueBill.setBizType("7");	//寄存出库
		        scmInvOtherIssueBill.setCurrencyNo(scmPurOrder.getCurrencyNo());
		        scmInvOtherIssueBill.setExchangeRate(scmPurOrder.getExchangeRate());
		        scmInvOtherIssueBill.setCreateOrgUnitNo(orgUnitNo);
		        scmInvOtherIssueBill.setCreator(param.getUsrCode());
		        scmInvOtherIssueBill.setCreateDate(CalendarUtil.getDate(param));
		        scmInvOtherIssueBill.setControlUnitNo(param.getControlUnitNo());
		        scmInvOtherIssueBillList.add(scmInvOtherIssueBill);
		        bean.setList(scmInvOtherIssueBillList);
		        HashMap<Long, List<ScmInvStock2>> stockMap = new HashMap<Long, List<ScmInvStock2>>();
		        for (int i = 0; i < scmPurOrderEntryList.size(); i++) {
		        	if(StringUtils.equals(scmPurOrderEntryList.get(i).getReqFinOrgUnitNo(),scmPurOrderEntry.getReqFinOrgUnitNo())) {
			        	ScmInvOtherIssueBillEntry2 scmInvOtherIssueBillEntry = new ScmInvOtherIssueBillEntry2(true);
			        	for(ScmMaterial2 scmMaterial:scmMaterialList){
							if(StringUtils.equals(scmMaterial.getItemNo(), scmPurOrderEntryList.get(i).getItemNo())){
					            scmInvOtherIssueBillEntry.setUnit(scmMaterial.getUnitId());
					            scmInvOtherIssueBillEntry.setPieUnit(scmPurOrderEntryList.get(i).getPieUnit());
					            scmInvOtherIssueBillEntry.setPieQty(scmPurOrderEntryList.get(i).getPieQty());
								if(scmPurOrderEntryList.get(i).getPurUnit()!=scmMaterial.getUnitId()) {
									BigDecimal purConvRate = ScmMaterialUtil.getConvRate(scmPurOrderEntryList.get(i).getItemId(), scmPurOrderEntryList.get(i).getPurUnit(), param);
									BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmPurOrderEntryList.get(i).getItemId(), scmMaterial.getUnitId(), param);
									scmInvOtherIssueBillEntry.setQty(scmPurOrderEntryList.get(i).getQty().multiply(purConvRate).divide(invConvRate, qtyPrecision, RoundingMode.HALF_UP));
								}else {
						            scmInvOtherIssueBillEntry.setQty(scmPurOrderEntryList.get(i).getQty());
								}
								break;
							}
						}
			            scmInvOtherIssueBillEntry.setLineId(i+1);
			            scmInvOtherIssueBillEntry.setItemId(scmPurOrderEntryList.get(i).getItemId());
			            scmInvOtherIssueBillEntry.setTaxRate(BigDecimal.ZERO);
			            scmInvOtherIssueBillEntry.setSourceBillDtlId(scmPurOrderEntryList.get(i).getId());
			            scmInvOtherIssueBillEntry.setReqFinOrgUnitNo(scmPurOrderEntryList.get(i).getReqFinOrgUnitNo());
			            scmInvOtherIssueBillEntry.setRemarks(scmPurOrderEntryList.get(i).getRemarks());
			            List<ScmInvStock2> scmInvStockList;
			            if(stockMap.containsKey(scmPurOrderEntryList.get(i).getItemId())) {
			            	scmInvStockList = stockMap.get(scmPurOrderEntryList.get(i).getItemId());
			            }else {
			            	scmInvStockList = scmInvStockBiz.selectWareHsForSale(orgUnitNo,scmPurOrderEntryList.get(i).getItemId(), param);
			            	stockMap.put(scmPurOrderEntryList.get(i).getItemId(), scmInvStockList);
			            }
			            if (scmInvStockList != null && !scmInvStockList.isEmpty()) {
			            	for(ScmInvStock2 scmInvStock:scmInvStockList) {
			            		if(scmInvStock.getQty().compareTo(scmInvOtherIssueBillEntry.getQty())>=0) {
			            			scmInvOtherIssueBillEntry.setWareHouseId(scmInvStock.getWareHouseId());
			                        scmInvOtherIssueBillEntry.setPrice(scmInvStock.getPrice());
			                        scmInvOtherIssueBillEntry.setAmt(scmInvOtherIssueBillEntry.getQty().multiply(scmInvOtherIssueBillEntry.getPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
			                        scmInvOtherIssueBillEntry.setTaxPrice(scmInvStock.getTaxPrice());
			                        scmInvOtherIssueBillEntry.setTaxAmt(scmInvOtherIssueBillEntry.getQty().multiply(scmInvOtherIssueBillEntry.getTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
			                        scmInvStock.setQty(scmInvStock.getQty().subtract(scmInvOtherIssueBillEntry.getQty()));
			                        break;
			            		}
			            	}
			            }
			            scmInvOtherIssueBillEntryList.add(scmInvOtherIssueBillEntry);
		        	}
		        }
		        bean.setList2(scmInvOtherIssueBillEntryList);
		        this.add(bean, param);
        	}
        }
	}
	@Override
	public void delByPurOrder(ScmPurOrder2 scmPurOrder, Param param) throws AppException {
		List<ScmInvOtherIssueBill2> scmInvOtherIssueBillList = this.selectByPoId(scmPurOrder.getId(), param);
		if (scmInvOtherIssueBillList != null && !scmInvOtherIssueBillList.isEmpty()) {
			for (ScmInvOtherIssueBill2 scmInvOtherIssueBill:scmInvOtherIssueBillList) {
				if(scmInvOtherIssueBill!=null){
					if(!StringUtils.equals("I",scmInvOtherIssueBill.getStatus())){
						throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.scmInvOtherIssueBill.delByPurOrder.error", new String[]{scmInvOtherIssueBill.getOtNo()}));
					}
				}
			}
			this.delete(scmInvOtherIssueBillList, param);
		}
	}
	@Override
	public List<ScmInvOtherIssueBill2> selectByPoId(long poId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("poId", poId);
		return ((ScmInvOtherIssueBillDAO)dao).selectByPoId(map);
	}
	@Override
	public ScmInvOtherIssueBill2 submit(ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param) throws AppException {
		ScmInvOtherIssueBill2 scmInvOtherIssue = null;
		if(scmInvOtherIssueBill.getOtId()>0){
			scmInvOtherIssue = this.selectDirect(scmInvOtherIssueBill.getOtId(), param);
		} else {
			Page page=new Page();
			page.setModelClass(ScmInvOtherIssueBill2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmInvOtherIssueBill2.FN_OTNO,
					new QueryParam(ScmInvOtherIssueBill2.FN_OTNO, QueryParam.QUERY_EQ,
							scmInvOtherIssueBill.getOtNo()));
			
			List<ScmInvOtherIssueBill2> scmInvOtherIssueList =this.findPage(page, param);
			
			if(scmInvOtherIssueList!=null && !scmInvOtherIssueList.isEmpty()){
				scmInvOtherIssue = scmInvOtherIssueList.get(0);
			}
		}

		if(scmInvOtherIssue!=null){
			if(!StringUtils.equals(scmInvOtherIssue.getStatus(),"I")){
				throw new AppException("iscm.purchasemanage.error.commit");
			}else if(scmInvOtherIssue.getStatus().equals("I")){
				List<ScmInvOtherIssueBillEntry2> scmInvOtherIssueBillEntryList = scmInvOtherIssueBillEntryBiz.selectByOtId(scmInvOtherIssue.getOtId(), param);
				
				BillType2 billType = billTypeBiz.selectByOrgAndCode(scmInvOtherIssue.getFinOrgUnitNo(), "InvOthIssueBill", param);
				boolean needAudit = false;
				if(billType!=null && billType.isNeedAudit())
					needAudit = true;
				if(needAudit){
					String processInstanceId = this.startWorkFlow(billType, scmInvOtherIssue, scmInvOtherIssueBillEntryList, param);
					scmInvOtherIssue.setWorkFlowNo(processInstanceId);
					ActivityUtil activityUtil = new ActivityUtil();
					//判断当前流程是否结束
					boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
					if(isCompleteTask) {
						if(param.getUsrCode()!=null ){
							scmInvOtherIssue.setChecker(param.getUsrCode());
							scmInvOtherIssue.setSubmitter(param.getUsrCode());
						}
						scmInvOtherIssue.setCheckDate(CalendarUtil.getDate(param));
						scmInvOtherIssue.setSubmitDate(CalendarUtil.getDate(param));
						scmInvOtherIssue.setStatus("A");
					}else {
						if(param.getUsrCode()!=null ){
							scmInvOtherIssue.setChecker(param.getUsrCode());
							scmInvOtherIssue.setSubmitter(param.getUsrCode());
						}
						scmInvOtherIssue.setSubmitDate(CalendarUtil.getDate(param));
						scmInvOtherIssue.setStatus("D");
						this.sendAuditMsg(scmInvOtherIssue, billType.getBillCode(), param);
					}
				}else{
					if(billType.isUseFlow()) {
						//启用工作流（不影响状态）
						String processInstanceId = startWorkFlow(billType, scmInvOtherIssue, scmInvOtherIssueBillEntryList, param);
						scmInvOtherIssue.setWorkFlowNo(processInstanceId);
						scmInvOtherIssue.setSubmitDate(CalendarUtil.getDate(param));
						ActivityUtil activityUtil = new ActivityUtil();
						//判断当前流程是否结束
						boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
						if(!isCompleteTask) {
							sendAuditMsg(scmInvOtherIssue,billType.getBillCode(),param);
						}
					}
					
					if(param.getUsrCode()!=null ){
						scmInvOtherIssue.setChecker(param.getUsrCode());
						scmInvOtherIssue.setSubmitter(param.getUsrCode());
					}
					scmInvOtherIssue.setCheckDate(CalendarUtil.getDate(param));
					scmInvOtherIssue.setSubmitDate(CalendarUtil.getDate(param));
					scmInvOtherIssue.setStatus("A");
				}
				try{
					this.updateStatus(scmInvOtherIssue, param);
				}catch(AppException e){
					throw e;
				}
				SystemState systemState = systemStateBiz.selectBySystemId(scmInvOtherIssue.getFinOrgUnitNo(), 170, param);
				if(systemState==null){
					throw new AppException("com.armitage.iars.daily.util.disenable.unable");
				}
				if (systemState.getCurrentPeriodId() == scmInvOtherIssue.getPeriodId()) {
				if(StringUtils.equals("A", scmInvOtherIssue.getStatus())) {
					//通过或部分通过时检查是否自动过帐
					if(billType!=null && billType.isAutoRelease()) {
						List<String> msgList = this.postBillCheck(scmInvOtherIssue, param);
						if (msgList != null && !msgList.isEmpty()) {
							StringBuilder detailInfo = new StringBuilder("");
							if(StringUtils.contains("7,8", scmInvOtherIssue.getBizType())){
								detailInfo.append(Message.getMessage(param.getLang(),"iscm.inventorymanage.depositissuesbill.post.errorTitle"));
	                        }else{
	                        	detailInfo.append(Message.getMessage(param.getLang(),"iscm.inventorymanage.otherissuesbill.post.errorTitle"));
	                        }
							
							for (String str : msgList) {
	                            detailInfo.append(str).append("\n");
	                        }
							
							throw new AppException(detailInfo.toString(), new String[]{scmInvOtherIssue.getOtNo()});
						}
						this.postBill(scmInvOtherIssue, param);
					}
				}
			}
			}
		}else{
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.find.ordernotexists");
		}
		return scmInvOtherIssue;
	}
	
	private String startWorkFlow(BillType2 billType,ScmInvOtherIssueBill2 scmInvOtherIssue,List<ScmInvOtherIssueBillEntry2> scmInvOtherIssueBillEntryList, Param param) {
		BigDecimal totalAmt = BigDecimal.ZERO;
		BigDecimal totalTaxAmt = BigDecimal.ZERO;
		if(scmInvOtherIssueBillEntryList != null && !scmInvOtherIssueBillEntryList.isEmpty()){
			for(ScmInvOtherIssueBillEntry2 scmInvOtherIssueBillEntry : scmInvOtherIssueBillEntryList){
				totalAmt = totalAmt.add(scmInvOtherIssueBillEntry.getAmt());
				totalTaxAmt = totalTaxAmt.add(scmInvOtherIssueBillEntry.getTaxAmt());
			}
		}
		scmInvOtherIssue.setTotalAmt(totalAmt);
		scmInvOtherIssue.setTotalTaxAmt(totalTaxAmt);
		
		CommonBean bean = new CommonBean();
		List<ScmInvOtherIssueBill2> scmInvMoveBillList = new ArrayList<>();
		scmInvMoveBillList.add(scmInvOtherIssue);
		bean.setList(scmInvMoveBillList);
		bean.setList2(scmInvOtherIssueBillEntryList);
		String processInstanceId = ActivityUtil.startProcessInstance(bean, billType.getId(), billType.getWorkFlowNo(), param);
		return processInstanceId;
	}
	
	private void sendAuditMsg(ScmInvOtherIssueBill2 scmInvOtherIssue,String billCode,Param param) {
		String usrList= ActivityUtil.findAssigneeByProcessInstanceId(scmInvOtherIssue.getWorkFlowNo(),param);
		if(StringUtils.isNotBlank(usrList)) {
			scmBillPendingBiz.addPendingBill(usrList, scmInvOtherIssue, param);
			InvOtherIssueParams invOtherIssueParams = new InvOtherIssueParams();
			invOtherIssueParams.setOtNo(scmInvOtherIssue.getOtNo());
			AuditMsgUtil.sendAuditMsg(billCode,scmInvOtherIssue,invOtherIssueParams, usrList, param);
		}
	}
	@Override
	public ScmInvOtherIssueBill2 undoSubmit(ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param)
			throws AppException {
		ScmInvOtherIssueBill2 scmInvOtherIssue = null;
		if (scmInvOtherIssueBill.getOtId() > 0) {
			scmInvOtherIssue = this.selectDirect(scmInvOtherIssueBill.getOtId(), param);
		} else {
			Page page=new Page();
			page.setModelClass(ScmInvOtherIssueBill2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmInvOtherIssueBill2.FN_OTNO,
					new QueryParam(ScmInvOtherIssueBill2.FN_OTNO, QueryParam.QUERY_EQ,
							scmInvOtherIssueBill.getOtNo()));
			
			List<ScmInvOtherIssueBill2> scmInvOtherIssueList =this.findPage(page, param);
			
			if(scmInvOtherIssueList!=null && !scmInvOtherIssueList.isEmpty()){
				scmInvOtherIssue = scmInvOtherIssueList.get(0);
			}
		}
		
		if(scmInvOtherIssue!=null){
			BillType2 billType = billTypeBiz.selectByOrgAndCode(scmInvOtherIssue.getFinOrgUnitNo(), "InvOthIssueBill", param);
			boolean needAudit = false;
			if(billType!=null && billType.isNeedAudit() && StringUtils.isNotBlank(scmInvOtherIssue.getWorkFlowNo()))
				needAudit = true;
			
			//单据设置审批流程，但设置没有审批节点，状态为通过，可以取消通过
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ScmBillPending.FN_BILLID, new QueryParam(ScmBillPending.FN_BILLID, QueryParam.QUERY_EQ, String.valueOf(scmInvOtherIssue.getOtId())));
			map.put(ScmBillPending.FN_BILLTYPE, new QueryParam(ScmBillPending.FN_BILLTYPE, QueryParam.QUERY_EQ, billType.getBillCode()));
			List<ScmBillPending> scmBillPendingList = scmBillPendingBiz.findAll(map, param);
			
			if(needAudit){
				if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
					if(!StringUtils.equals(scmInvOtherIssue.getStatus(),"A"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				} else {
					if(!StringUtils.equals(scmInvOtherIssue.getStatus(),"D"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				}
			}else{
				//启用审批进入审核中(再调整为启用工作流)的单据的状态不是A
				if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
				if(!StringUtils.equals(scmInvOtherIssue.getStatus(),"A"))
					throw new AppException("iscm.purchasemanage.error.cancelCommit");
				} else {
					if(!StringUtils.contains("D,A",scmInvOtherIssue.getStatus()))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				}
			} 
			
			if(scmInvOtherIssue.getStatus().equals("A") || scmInvOtherIssue.getStatus().equals("D")){
				scmInvOtherIssue.setChecker(null);
				scmInvOtherIssue.setCheckDate(null);
				scmInvOtherIssue.setSubmitter(null);
				scmInvOtherIssue.setSubmitDate(null);
				scmInvOtherIssue.setStatus("I");
				try{
					this.updateStatus(scmInvOtherIssue, param);
					scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),scmInvOtherIssue, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.find.ordernotexists");
		}
		return scmInvOtherIssue;
	}
	@Override
	public ScmInvOtherIssueBill2 queryInvOtherIssue(
			ScmInvOtherIssueBill2 scmInvOtherIssue, Param param)
			throws AppException {
		Page page=new Page();
		page.setModelClass(ScmInvOtherIssueBill2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmInvOtherIssueBill2.FN_OTNO,new QueryParam(ScmInvOtherIssueBill2.FN_OTNO, QueryParam.QUERY_EQ, scmInvOtherIssue.getOtNo()));
		
		List<ScmInvOtherIssueBill2> scmInvOtherIssueBillList =this.findPage(page, param);
		ScmInvOtherIssueBill2 scmInvOtherIssueBill2 = new ScmInvOtherIssueBill2();
		if(scmInvOtherIssueBillList!=null && !scmInvOtherIssueBillList.isEmpty()){
			scmInvOtherIssueBill2 = scmInvOtherIssueBillList.get(0);
			scmInvOtherIssueBill2.setScmInvOtherIssueBillEntryList(scmInvOtherIssueBillEntryBiz.selectByOtId(scmInvOtherIssueBill2.getOtId(), param));
			
			//增加待审人
			ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmInvOtherIssueBill2.getOtId(), "InvOthIssueBill",param);
			if (scmBillPendingUsr != null) {
				scmInvOtherIssueBill2.setPendingUsr(scmBillPendingUsr.getUsrCodes());
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmInvOtherIssueBill2;
	}
	@Override
	public ScmInvOtherIssueBill2 doAuditInvOtherIssue(
			CommonAuditParams commonAuditParams, Param param)
			throws AppException {
		ScmInvOtherIssueBill2 invOtherIssue = null;
		
		ScmInvOtherIssueBill2 scmInvOtherIssueBill= new ScmInvOtherIssueBill2();
		scmInvOtherIssueBill.setOtId(commonAuditParams.getBillId());
		scmInvOtherIssueBill.setOtNo(commonAuditParams.getBillNo());
		String opinion = commonAuditParams.getOpinion();
		if(StringUtils.equals("Y", opinion) || StringUtils.equals("agree", opinion)) {
			opinion="agree";
		}else if(StringUtils.equals("N", opinion) || StringUtils.equals("refuse", opinion)) {
			opinion="refuse";
		}
		if(scmInvOtherIssueBill.getOtId()>0){
			invOtherIssue = this.selectDirect(scmInvOtherIssueBill.getOtId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmInvOtherIssueBill2.FN_OTNO,
					new QueryParam(ScmInvOtherIssueBill2.FN_OTNO, QueryParam.QUERY_EQ,
							scmInvOtherIssueBill.getOtNo()));
			map.put(ScmInvOtherIssueBill2.FN_CONTROLUNITNO, new QueryParam(ScmInvOtherIssueBill2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
							param.getControlUnitNo()));
			List<ScmInvOtherIssueBill2> scmInvOtherIssueList =this.findAll(map, param);
			
			if(scmInvOtherIssueList!=null && !scmInvOtherIssueList.isEmpty()){
				invOtherIssue = scmInvOtherIssueList.get(0);
			}
		}
		
		if (invOtherIssue != null) {
			this.setConvertMap(invOtherIssue, param);
			BillType2 billType = billTypeBiz.selectByOrgAndCode(invOtherIssue.getFinOrgUnitNo(), "InvOthIssueBill", param);
			if(!(invOtherIssue.getStatus().equals("D") || invOtherIssue.getStatus().equals("P")) && billType.isNeedAudit()){
				throw new AppException("iscm.purchasemanage.error.audit");
			}
			if(StringUtils.equals("R", opinion)) {
				scmBillPendingBiz.deletePendingBill(param.getUsrCode(),invOtherIssue, param);
				commonEventHistoryBiz.updateInvalid(invOtherIssue,"",param);
				
				CommonAuditOpinion commonAuditOpinionR = new CommonAuditOpinion();
				commonAuditOpinionR.setPreStepNo(invOtherIssue.getStepNo());
				String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
				commonAuditOpinionR.setStepNo(stepNo);
				commonAuditOpinionR.setActiveType("A");
				commonAuditOpinionR.setOpinion(commonAuditParams.getOpinion());
				commonAuditOpinionR.setRemarks(commonAuditParams.getOpinionRemarks());
				commonEventHistoryBiz.addEventHistory(invOtherIssue, commonAuditOpinionR, param);
				
				if(billType.isNeedAudit()) {
					//驳回则将单据变回新单状态
					invOtherIssue.setStatus("I");
					invOtherIssue.setChecker(null);
					invOtherIssue.setCheckDate(null);
					return this.updateDirect(invOtherIssue, param);
				}else {
					//不需要审批时驳回需模拟反过账、反提交
					if(StringUtils.equals("E",invOtherIssue.getStatus())) {
						invOtherIssue = this.cancelPostBill(invOtherIssue, param);
					}
					if(StringUtils.equals("A",invOtherIssue.getStatus())) {
						this.undoSubmit(invOtherIssue, param);
					}
					
					invOtherIssue.setStatus("I");
					invOtherIssue.setChecker(null);
					invOtherIssue.setCheckDate(null);
					return this.updateDirect(invOtherIssue, param);
				}
			}
			String processInstanceId = invOtherIssue.getWorkFlowNo();
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
			if(billType.isNeedAudit() || (billType.isUseFlow() && StringUtils.contains("D,P", invOtherIssue.getStatus()))) {
				//P：审核中，A：通过，N：未通过
				if ("agree".equals(opinion)) {
					if (isCompleteTask) {
						invOtherIssue.setStatus("A");
					} else {
						invOtherIssue.setStatus("P");
					}
				} else {
					invOtherIssue.setStatus("N");
				}
			} else {
				if (billType.isUseFlow()) {
					if(StringUtils.equals("refuse", opinion)) {
						//审批拒绝
						if(StringUtils.equals("E",invOtherIssue.getStatus())) {
							invOtherIssue = this.cancelPostBill(invOtherIssue, param);
			}
						invOtherIssue.setStatus("N");
					}
					
				}
			}
			scmBillPendingBiz.updatePendingBill(param.getUsrCode(), invOtherIssue, param);
			invOtherIssue.setChecker(param.getUsrCode());
			if ("agree".equals(opinion)) {
				String usrList= ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId,param);
				if(StringUtils.isNotBlank(usrList)) {
					scmBillPendingBiz.addPendingBill(usrList, invOtherIssue, param);
					InvOtherIssueParams invOtherIssueParams = new InvOtherIssueParams();
					invOtherIssueParams.setOtNo(invOtherIssue.getOtNo());
					AuditMsgUtil.sendAuditMsg("InvOthIssueBill",invOtherIssue,invOtherIssueParams, usrList, param);
				}
			}
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setPreStepNo(invOtherIssue.getStepNo());
			String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
			try {
				invOtherIssue.setStepNo(stepNo);
				invOtherIssue.setCheckDate(CalendarUtil.getDate(param));
				this.updateStatus(invOtherIssue, param);
			} catch (Exception e) {
				throw e;
			}
			commonAuditOpinion.setStepNo(stepNo);
			commonAuditOpinion.setActiveType("A");
			commonAuditOpinion.setOpinion(commonAuditParams.getOpinion());
			commonAuditOpinion.setRemarks(commonAuditParams.getOpinionRemarks());
			commonEventHistoryBiz.addEventHistory(invOtherIssue, commonAuditOpinion, param);
			SystemState systemState = systemStateBiz.selectBySystemId(invOtherIssue.getFinOrgUnitNo(), 170, param);
			if(systemState==null){
				throw new AppException("com.armitage.iars.daily.util.disenable.unable");
			}
			if (systemState.getCurrentPeriodId() == invOtherIssue.getPeriodId()) {
			if(StringUtils.equals("A", invOtherIssue.getStatus())) {
				//通过或部分通过时检查是否自动过帐
				if(billType!=null && billType.isAutoRelease()) {
					List<String> msgList = this.postBillCheck(invOtherIssue, param);
					if (msgList != null && !msgList.isEmpty()) {
						StringBuilder detailInfo = new StringBuilder("");
						if(StringUtils.contains("7,8", invOtherIssue.getBizType())){
							detailInfo.append(Message.getMessage(param.getLang(),"iscm.inventorymanage.depositissuesbill.post.errorTitle"));
                        }else{
                        	detailInfo.append(Message.getMessage(param.getLang(),"iscm.inventorymanage.otherissuesbill.post.errorTitle"));
                        }
						
						for (String str : msgList) {
                            detailInfo.append(str).append("\n");
                        }
						
						throw new AppException(detailInfo.toString(), new String[]{invOtherIssue.getOtNo()});
					}
					this.postBill(invOtherIssue, param);
				}
			}
			}
                        }else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return invOtherIssue;
	}
	@Override
	public ScmInvOtherIssueBill2 doUnAuditInvOtherIssue(
			ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param)
			throws AppException {
		ScmInvOtherIssueBill2 invInvOtherIssue = null;
		List<ScmInvOtherIssueBill2> scmInvOterIssueList = new ArrayList<> ();
		List<ScmInvOtherIssueBillEntry2> scmInvOtherIssueEntryList = new ArrayList<> ();
		boolean isFirstTask = false;
		
		if(scmInvOtherIssueBill.getOtId()>0){
			invInvOtherIssue = this.selectDirect(scmInvOtherIssueBill.getOtId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmInvOtherIssueBill2.FN_OTNO,
					new QueryParam(ScmInvOtherIssueBill2.FN_OTNO, QueryParam.QUERY_EQ,
							scmInvOtherIssueBill.getOtNo()));
			map.put(ScmInvOtherIssueBill2.FN_CONTROLUNITNO, new QueryParam(ScmInvOtherIssueBill2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
					param.getControlUnitNo()));
			
			scmInvOterIssueList =this.findAll(map, param);
			if(scmInvOterIssueList!=null && !scmInvOterIssueList.isEmpty()){
				invInvOtherIssue=scmInvOterIssueList.get(0);
			}
		}
		
		if (invInvOtherIssue != null) {
			BillType2 billType = billTypeBiz.selectByOrgAndCode(invInvOtherIssue.getFinOrgUnitNo(), "InvOthIssueBill", param);
			if(!StringUtils.contains("A,B,N,P", invInvOtherIssue.getStatus()) && billType.isNeedAudit()){
				throw new AppException("iscm.purchasemanage.unaudit.error.status");
			}
			if(!StringUtils.contains("A,B,N,P,E", invInvOtherIssue.getStatus()) && billType.isUseFlow()){
				throw new AppException("iscm.purchasemanage.unaudit.error.status");
			}
			String processInstanceId = invInvOtherIssue.getWorkFlowNo();
			scmInvOtherIssueEntryList = scmInvOtherIssueBillEntryBiz.selectByOtId(invInvOtherIssue.getOtId(), param);
			
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//反审批当前已审节点
				CommonBean bean = new CommonBean();
				scmInvOterIssueList.add(invInvOtherIssue);
				bean.setList(scmInvOterIssueList);
				bean.setList2(scmInvOtherIssueEntryList);
				
				long billTypeId = 0;
				if (billType!=null && billType.isNeedAudit()) {
					billTypeId = billType.getId();
				}
				
				processInstanceId = ActivityUtil.antiAuditProcess(bean, billTypeId, processInstanceId, param.getUsrCode(),  param);
				isFirstTask = ActivityUtil.isFirstTask(processInstanceId, param.getUsrCode(), param);
				invInvOtherIssue.setWorkFlowNo(processInstanceId);
			} else {
				//流程编号不存在
				throw new AppException("com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.doAuditPurRequire.processInstanceId");
			}
			
			//启用审批，或启用工作流（对于历史数据的处理）
			if(billType.isNeedAudit() || (billType.isUseFlow() && StringUtils.contains("N,D,P", invInvOtherIssue.getStatus()))) {
			String status = "";
			if (isFirstTask) {
				status = "D";
			} else {
				status = "P";
			}
			invInvOtherIssue.setStatus(status);
			String tableName = ClassUtils.getFinalModelSimpleName(invInvOtherIssue);
			CommonEventHistory commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,invInvOtherIssue.getStepNo(),invInvOtherIssue.getPK(),param);
			if(commonEventHistory!=null && StringUtils.isNotBlank(commonEventHistory.getPreStepNo())) {
				commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,commonEventHistory.getPreStepNo(),invInvOtherIssue.getPK(),param);
			}
			if(commonEventHistory!=null) {
				invInvOtherIssue.setChecker(commonEventHistory.getOper());
				invInvOtherIssue.setCheckDate(commonEventHistory.getOperDate());
			}else {
				invInvOtherIssue.setChecker(null);
				invInvOtherIssue.setCheckDate(null);
			}
			this.updateStatus(invInvOtherIssue, param);
			} else {
				if (billType.isUseFlow()) {
					//启用审批时订单的状态已更新为审批中时，再启用工作流，反审到首审批节点时，应调整为待审状态
					if (isFirstTask) {
						if (StringUtils.equals("P", invInvOtherIssue.getStatus())) {
							invInvOtherIssue.setStatus("D");
							
						}
					}
					this.updateStatus(invInvOtherIssue, param);
				}
			}
			scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),invInvOtherIssue, param);
			commonEventHistoryBiz.updateInvalid(invInvOtherIssue,invInvOtherIssue.getStepNo(),param);
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setStepNo(invInvOtherIssue.getStepNo());
			commonAuditOpinion.setActiveType("U");
			commonAuditOpinion.setOpinion("Y");
			commonEventHistoryBiz.addEventHistory(invInvOtherIssue, commonAuditOpinion, param);
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}

		return invInvOtherIssue;
	}
	
	@Override
	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map) {
		return ((ScmInvOtherIssueBillDAO)dao).countUnPostBill(map);
	}
	@Override
	public ScmInvOtherIssueBill2 updatePrtCount(ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param)
			throws AppException {
		if(scmInvOtherIssueBill.getOtId()>0){
			ScmInvOtherIssueBill2 bill = this.selectDirect(scmInvOtherIssueBill.getOtId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmInvOtherIssueBill;
	}
	
	@Override
	public DataSyncResult dataSync(InvOtherIssueBillListParams invOtherIssueBillListParam,List<ScmInvOtherIssueBill2> scmInvOtherInWarehsBill2s, Param param) throws AppException {
		DataSyncResult dataSyncResult = new DataSyncResult();
		if(StringUtils.isBlank(invOtherIssueBillListParam.getOtNo())&& StringUtils.equals(invOtherIssueBillListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billNo.notExist");
		}
		if(StringUtils.isBlank(invOtherIssueBillListParam.getPostStatus())&& StringUtils.equals(invOtherIssueBillListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billStatus.notExist");
		}
		if(invOtherIssueBillListParam.getBizDate() == null&& StringUtils.equals(invOtherIssueBillListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billBizDate.notExist");
		}
		if((invOtherIssueBillListParam.getDetailList() == null || invOtherIssueBillListParam.getDetailList().isEmpty())&& StringUtils.equals(invOtherIssueBillListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billEntry.notExist");
		}
		if(!StringUtils.isNotBlank(invOtherIssueBillListParam.getWhNo())&& StringUtils.equals(invOtherIssueBillListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billWhNo.notExist");
		}
		//获取仓库					
		ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectByWhNo(param.getOrgUnitNo(), invOtherIssueBillListParam.getWhNo(), param);
		if(scmInvWareHouse == null&& StringUtils.equals(invOtherIssueBillListParam.getDelete(), "N"))
			throw new AppException("iscm.api.datePut.billWhNo.notExist");
		for(ScmInvOtherIssueBill2 scmInvOtherIssueBill:scmInvOtherInWarehsBill2s) {
			if(StringUtils.equals(invOtherIssueBillListParam.getOtNo(), scmInvOtherIssueBill.getOtNo())) {
				//删除				
				if(StringUtils.equals(invOtherIssueBillListParam.getDelete(),"Y")) {
					scmInvOtherIssueBillEntryBiz.deleteByOtId(scmInvOtherIssueBill.getOtId(), param);
					((ScmInvOtherIssueBillDAO) dao).delete(scmInvOtherIssueBill.getPK()+"");
					dataSyncResult.setNo(invOtherIssueBillListParam.getOtNo());
					dataSyncResult.setErrCode("0");
					return dataSyncResult;
				}
				//构建明细				
				ArrayList<ScmInvOtherIssueBillEntry2> scmInvOtherIssueBillEntryAdd = new ArrayList<ScmInvOtherIssueBillEntry2>();
				for(InvOtherIssueBillDetailParams invOtherIssueBillDetailParam :invOtherIssueBillListParam.getDetailList()) {
					ScmInvOtherIssueBillEntry2 scmInvOtherIssueBillEntry = new ScmInvOtherIssueBillEntry2(true);
					if(!scmMatrialMap.containsKey(invOtherIssueBillDetailParam.getItemNo())) {
						ScmMaterial2 selectByItemNo = scmMaterialBiz.selectByItemNo(param.getControlUnitNo(), invOtherIssueBillDetailParam.getItemNo(), param);
						if(selectByItemNo == null)
							throw new AppException("iscm.api.datePut.billItemNo.notExist");
						scmMatrialMap.put(selectByItemNo.getItemNo(), selectByItemNo.getId());
					}
					BeanUtils.copyProperties(invOtherIssueBillDetailParam, scmInvOtherIssueBillEntry);
					scmInvOtherIssueBillEntry.setItemId(scmMatrialMap.get(invOtherIssueBillDetailParam.getItemNo()));
					if(!scmUnitMap.containsKey(scmInvOtherIssueBillEntry.getItemId())) {
						ScmMaterialInventory2 selectByItemIdAndOrgUnitNo = scmMaterialInventoryBiz.selectByItemId(scmInvOtherIssueBillEntry.getItemId(), param.getOrgUnitNo(), param.getControlUnitNo(), param);
						if(selectByItemIdAndOrgUnitNo == null)
							throw new AppException("iscm.api.datePut.billUnitNo.notExist");
						scmUnitMap.put(selectByItemIdAndOrgUnitNo.getItemId(), selectByItemIdAndOrgUnitNo.getUnitId());
					}
					scmInvOtherIssueBillEntry.setUnit(scmUnitMap.get(scmInvOtherIssueBillEntry.getItemId()));
					scmInvOtherIssueBillEntry.setOrgUnitNo(param.getOrgUnitNo());
					scmInvOtherIssueBillEntry.setWareHouseId(scmInvWareHouse.getId());
					scmInvOtherIssueBillEntry.setOtId(scmInvOtherIssueBill.getOtId());
					scmInvOtherIssueBillEntry.setBaseQty(invOtherIssueBillDetailParam.getQty());
					scmInvOtherIssueBillEntry.setInvDate(scmInvOtherIssueBill.getBizDate());
					scmInvOtherIssueBillEntryAdd.add(scmInvOtherIssueBillEntry);
				}
				//2、	过账 》过账 ok
				if(StringUtils.equals(invOtherIssueBillListParam.getPostStatus(), "1")&& "E,C".contains(scmInvOtherIssueBill.getStatus())) {
					throw new AppException("iscm.api.business.datasync.controller.posted");
				//3、	非过账 》过账
				}else if(!"E,C".contains(scmInvOtherIssueBill.getStatus())&& StringUtils.equals(invOtherIssueBillListParam.getPostStatus(), "1")) {
					//更新主子表
					refreshData(invOtherIssueBillListParam,scmInvOtherIssueBill,param,scmInvWareHouse);
					scmInvOtherIssueBill.setStatus("E");
					update(scmInvOtherIssueBill, param);
					scmInvOtherIssueBillEntryBiz.deleteByOtId(scmInvOtherIssueBill.getOtId(), param);
					scmInvOtherIssueBillEntryBiz.add(scmInvOtherIssueBillEntryAdd, param);
					//期间余额
					scmInvBalBiz.updateByOtherIssueOutSub(scmInvOtherIssueBill.getOtId(), param);
					scmInvBalBiz.addByOtherIssueOutForDataSnyc(scmInvOtherIssueBill.getOtId(), param);
					//返回结果					
					dataSyncResult.setNo(invOtherIssueBillListParam.getOtNo());
					dataSyncResult.setErrCode("0");
					return dataSyncResult;
				//4、	过账 》非过账	ok				
				}else if("E,C".contains(scmInvOtherIssueBill.getStatus()) && StringUtils.equals(invOtherIssueBillListParam.getPostStatus(), "0")){
					throw new AppException("iscm.api.business.datasync.controller.cancelPost");
				//5、	非过账 》非过账				
				}else{
					refreshData(invOtherIssueBillListParam,scmInvOtherIssueBill,param,scmInvWareHouse);
					update(scmInvOtherIssueBill, param);
					scmInvOtherIssueBillEntryBiz.deleteByOtId(scmInvOtherIssueBill.getOtId(), param);
					scmInvOtherIssueBillEntryBiz.add(scmInvOtherIssueBillEntryAdd, param);
					dataSyncResult.setNo(invOtherIssueBillListParam.getOtNo());
					dataSyncResult.setErrCode("0");
					return dataSyncResult;
				}
			}
		}
		if(StringUtils.equals(invOtherIssueBillListParam.getDelete(),"Y")) {
			dataSyncResult.setNo(invOtherIssueBillListParam.getOtNo());
			dataSyncResult.setErrCode("0");
			return dataSyncResult;
		}
		//1、	新增 √
		ArrayList<ScmInvOtherIssueBill2> scmInvOtherInWarehsBillAdd = new ArrayList<ScmInvOtherIssueBill2>();
		ArrayList<ScmInvOtherIssueBillEntry2> scmInvOtherIssueBillEntryAdd2 = new ArrayList<ScmInvOtherIssueBillEntry2>();
		ScmInvOtherIssueBill2 scmInvOtherIssueBill = new ScmInvOtherIssueBill2(true);
		BeanUtils.copyProperties(invOtherIssueBillListParam, scmInvOtherIssueBill);
		scmInvOtherIssueBill.setWareHouseId(scmInvWareHouse.getId()); 
		scmInvOtherIssueBill.setOrgUnitNo(param.getOrgUnitNo());
		scmInvOtherIssueBill.setControlUnitNo(param.getControlUnitNo());
		beforeAdd(scmInvOtherIssueBill, param);
		scmInvOtherIssueBill.setStatus("I");
		if(StringUtils.equals(invOtherIssueBillListParam.getPostStatus(),"1")) 
			scmInvOtherIssueBill.setStatus("E");
		scmInvOtherIssueBill.setCreator("");
		scmInvOtherIssueBill.setBizType("1");
		scmInvOtherIssueBill.setCreateDate(invOtherIssueBillListParam.getBizDate());
		scmInvOtherIssueBill.setOtNo(invOtherIssueBillListParam.getOtNo());
		List<OrgCompany2> orgCompanylist2 = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, scmInvOtherIssueBill.getOrgUnitNo(), false, null, param);
		if(orgCompanylist2 != null && !orgCompanylist2.isEmpty()){
			scmInvOtherIssueBill.setFinOrgUnitNo(orgCompanylist2.get(0).getOrgUnitNo());
		}else {
			throw new AppException("iscm.api.datePut.billInvOrgUnitNo.notToFin");
		}
		((ScmInvOtherIssueBillDAO) dao).add(scmInvOtherIssueBill);
		for(InvOtherIssueBillDetailParams invOtherIssueBillDetailParam :invOtherIssueBillListParam.getDetailList()) {
			ScmInvOtherIssueBillEntry2 scmInvOtherIssueBillEntry = new ScmInvOtherIssueBillEntry2(true);
			BeanUtils.copyProperties(invOtherIssueBillDetailParam, scmInvOtherIssueBillEntry);
			if(!scmMatrialMap.containsKey(invOtherIssueBillDetailParam.getItemNo())) {
				ScmMaterial2 selectByItemNo = scmMaterialBiz.selectByItemNo(param.getControlUnitNo(), invOtherIssueBillDetailParam.getItemNo(), param);
				if(selectByItemNo == null)
					throw new AppException("iscm.api.datePut.billItemNo.notExist");
				scmMatrialMap.put(selectByItemNo.getItemNo(), selectByItemNo.getId());
			}
			scmInvOtherIssueBillEntry.setItemId(scmMatrialMap.get(invOtherIssueBillDetailParam.getItemNo()));
			if(!scmUnitMap.containsKey(scmInvOtherIssueBillEntry.getItemId())) {
				ScmMaterialInventory2 selectByItemIdAndOrgUnitNo = scmMaterialInventoryBiz.selectByItemId(scmInvOtherIssueBillEntry.getItemId(), param.getOrgUnitNo(), param.getControlUnitNo(), param);
				if(selectByItemIdAndOrgUnitNo == null)
					throw new AppException("iscm.api.datePut.billUnitNo.notExist");
				scmUnitMap.put(selectByItemIdAndOrgUnitNo.getItemId(), selectByItemIdAndOrgUnitNo.getUnitId());
			}
			scmInvOtherIssueBillEntry.setUnit(scmUnitMap.get(scmInvOtherIssueBillEntry.getItemId()));
			scmInvOtherIssueBillEntry.setStatus("I");
			if(StringUtils.equals(invOtherIssueBillListParam.getPostStatus(),"1")) 
				scmInvOtherIssueBillEntry.setStatus("E");
			scmInvOtherIssueBillEntry.setBaseQty(invOtherIssueBillDetailParam.getQty());
			scmInvOtherIssueBillEntry.setInvDate(scmInvOtherIssueBill.getBizDate());
			scmInvOtherIssueBillEntry.setPieQty(BigDecimal.ZERO);
			scmInvOtherIssueBillEntry.setWareHouseId(scmInvWareHouse.getId());
			scmInvOtherIssueBillEntry.setOtId(scmInvOtherIssueBill.getOtId());
			scmInvOtherIssueBillEntryAdd2.add(scmInvOtherIssueBillEntry);
		}
		scmInvOtherIssueBillEntryBiz.add(scmInvOtherIssueBillEntryAdd2, param);
		if(StringUtils.equals(invOtherIssueBillListParam.getPostStatus(),"1")) {
			//盘亏时 期间余额
			scmInvBalBiz.updateByOtherIssueOutSub(scmInvOtherIssueBill.getOtId(), param);
			scmInvBalBiz.addByOtherIssueOutForDataSnyc(scmInvOtherIssueBill.getOtId(), param);
		}
		dataSyncResult.setNo(invOtherIssueBillListParam.getOtNo());
		dataSyncResult.setErrCode("0");
		return dataSyncResult;
	}
	private void refreshData(InvOtherIssueBillListParams invOtherIssueBillListParam,ScmInvOtherIssueBill2 scmInvOtherIssueBill, Param param, ScmInvWareHouse scmInvWareHouse) throws AppException{
		BeanUtils.copyProperties(invOtherIssueBillListParam, scmInvOtherIssueBill);
		List<OrgCompany2> orgCompanylist2 = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, scmInvOtherIssueBill.getOrgUnitNo(), false, null, param);
		if(orgCompanylist2 != null && !orgCompanylist2.isEmpty()){
			scmInvOtherIssueBill.setFinOrgUnitNo(orgCompanylist2.get(0).getOrgUnitNo());
		}else {
			throw new AppException("iscm.api.datePut.billInvOrgUnitNo.notToFin");
		}
		scmInvOtherIssueBill.setBizType("1");
		scmInvOtherIssueBill.setCreateOrgUnitNo(param.getOrgUnitNo());
		scmInvOtherIssueBill.setWareHouseId(scmInvWareHouse.getId());
		scmInvOtherIssueBill.setOrgUnitNo(param.getOrgUnitNo());
		scmInvOtherIssueBill.setControlUnitNo(param.getControlUnitNo());
	}

}
