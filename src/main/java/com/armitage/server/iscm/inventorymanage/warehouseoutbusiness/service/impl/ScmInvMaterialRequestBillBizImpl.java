package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqAddParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqDeptParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqDetailParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqEditParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqInvOrgUnitParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqPersonParams;
import com.armitage.server.api.business.invmaterialreq.params.InvReqMaterialListParams;
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
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.common.model.CommonAuditOpinion;
import com.armitage.server.iscm.common.model.CommonBillEntryStatus;
import com.armitage.server.iscm.common.model.CommonEventHistory;
import com.armitage.server.iscm.common.model.ScmAuditDetailHistory;
import com.armitage.server.iscm.common.service.CommonEventHistoryBiz;
import com.armitage.server.iscm.common.service.ScmAuditDetailHistoryBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMaterialRequestBillDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialDrillResult;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBillAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialReqBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialRequestBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialRequestBillEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditDetailParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.model.SystemState;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.OrgAdminBiz;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

@Service("scmInvMaterialRequestBillBiz")
public class ScmInvMaterialRequestBillBizImpl extends BaseBizImpl<ScmInvMaterialRequestBill2> implements ScmInvMaterialRequestBillBiz {
	private UsrBiz usrBiz;
	private OrgUnitBiz orgUnitBiz;
	private OrgAdminBiz orgAdminBiz;
	private ScmInvMaterialRequestBillEntryBiz scmInvMaterialRequestBillEntryBiz;
	private OrgStorageBiz orgStorageBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private CommonEventHistoryBiz commonEventHistoryBiz;
	private ScmInvMaterialReqBillBiz scmInvMaterialReqBillBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private BillTypeBiz billTypeBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private ScmBillPendingBiz scmBillPendingBiz;
	private SysParamBiz sysParamBiz;
	private PeriodCalendarBiz periodCalendarBiz;
	private SystemStateBiz systemStateBiz;
	private ScmAuditDetailHistoryBiz scmAuditDetailHistoryBiz;
	
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
	
	public UsrBiz getUsrBiz() {
		return usrBiz;
	}
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}
	public OrgUnitBiz getOrgUnitBiz() {
		return orgUnitBiz;
	}
	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}
	public OrgAdminBiz getOrgAdminBiz() {
		return orgAdminBiz;
	}
	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}
	public ScmInvMaterialRequestBillEntryBiz getScmInvMaterialRequestBillEntryBiz() {
		return scmInvMaterialRequestBillEntryBiz;
	}
	public void setScmInvMaterialRequestBillEntryBiz(ScmInvMaterialRequestBillEntryBiz scmInvMaterialRequestBillEntryBiz) {
		this.scmInvMaterialRequestBillEntryBiz = scmInvMaterialRequestBillEntryBiz;
	}
	
	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}
	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}
	
	public void setCommonEventHistoryBiz(CommonEventHistoryBiz commonEventHistoryBiz) {
		this.commonEventHistoryBiz = commonEventHistoryBiz;
	}
	public void setScmInvMaterialReqBillBiz(
			ScmInvMaterialReqBillBiz scmInvMaterialReqBillBiz) {
		this.scmInvMaterialReqBillBiz = scmInvMaterialReqBillBiz;
	}
	
	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
        this.orgUnitRelationBiz = orgUnitRelationBiz;
    }
	
	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}
	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}
	public void setScmBillPendingBiz(ScmBillPendingBiz scmBillPendingBiz) {
		this.scmBillPendingBiz = scmBillPendingBiz;
	}
	
	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}
	public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}
	public void setSystemStateBiz(SystemStateBiz systemStateBiz) {
		this.systemStateBiz = systemStateBiz;
	}
	public void setScmAuditDetailHistoryBiz(ScmAuditDetailHistoryBiz scmAuditDetailHistoryBiz) {
		this.scmAuditDetailHistoryBiz = scmAuditDetailHistoryBiz;
	}
	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		List<OrgAdmin2> orgAdminList = orgAdminBiz.findChild(param.getOrgUnitNo(), param);
		if (orgAdminList != null && !orgAdminList.isEmpty()) {
			StringBuffer orgunitList = new StringBuffer("");
			for (OrgAdmin2 orgAdmin : orgAdminList) {
				if(StringUtils.isNotBlank(orgunitList.toString()))
					orgunitList.append(",");
				orgunitList.append("'").append(orgAdmin.getOrgUnitNo()).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvMaterialRequestBill2.class) + "." + ScmInvMaterialRequestBill2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvMaterialRequestBill2.class) + "." + ScmInvMaterialRequestBill2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvMaterialRequestBill2.class) + "." + ScmInvMaterialRequestBill2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvMaterialRequestBill2.class) + "." + ScmInvMaterialRequestBill2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}	}
	@Override
	protected void afterSelect(ScmInvMaterialRequestBill2 entity, Param param) throws AppException {
		if (entity != null){
			setConvertMap(entity,param);
		}
	}
	
	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		if(list != null && !list.isEmpty()){
			for(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill: (List<ScmInvMaterialRequestBill2>)list){
				//视图增加待审人
				ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmInvMaterialRequestBill.getReqId(), "InvMatReqBill",param);
				StringBuffer usrName = new StringBuffer("");
				if (scmBillPendingUsr != null) {
					scmInvMaterialRequestBill.setPendingUsr(scmBillPendingUsr.getUsrCodes());
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
				scmInvMaterialRequestBill.setPendingUsrName(usrName.toString());
				
				setConvertMap(scmInvMaterialRequestBill,param);
				if("I,R".contains(scmInvMaterialRequestBill.getStatus())) {
					scmInvMaterialRequestBill.setPendingUsrName("");
				}
			}
		}
	}
	
	private void setConvertMap(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill,Param param){
		if(scmInvMaterialRequestBill!=null){
			if (StringUtils.isNotBlank(scmInvMaterialRequestBill.getCreator())){
				//制单人  
				Usr usr = usrBiz.selectByCode(scmInvMaterialRequestBill.getCreator(), param);
				if (usr != null) {
					scmInvMaterialRequestBill.setCreatorName(usr.getName());
					scmInvMaterialRequestBill.setConvertMap(ScmInvMaterialRequestBill2.FN_CREATOR, usr);
					if(scmInvMaterialRequestBill.getDataDisplayMap()==null){
						scmInvMaterialRequestBill.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
					}
					scmInvMaterialRequestBill.getDataDisplayMap().put(ScmInvMaterialRequestBill2.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
				}
			}
			if (StringUtils.isNotBlank(scmInvMaterialRequestBill.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmInvMaterialRequestBill.getEditor(), param);
				if (usr != null) {
					scmInvMaterialRequestBill.setEditorName(usr.getName());
					scmInvMaterialRequestBill.setConvertMap(ScmInvMaterialRequestBill2.FN_EDITOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmInvMaterialRequestBill.getChecker())){
				//审核人
				Usr usr = usrBiz.selectByCode(scmInvMaterialRequestBill.getChecker(), param);
				if (usr != null) {
					scmInvMaterialRequestBill.setCheckerName(usr.getName());
					scmInvMaterialRequestBill.setConvertMap(ScmInvMaterialRequestBill2.FN_CHECKER, usr);
				}
			}

			if (StringUtils.isNotBlank(scmInvMaterialRequestBill.getRequestPerson())){
				//申请人
				Usr usr = usrBiz.selectByCode(scmInvMaterialRequestBill.getRequestPerson(), param);
				if (usr != null) {
					scmInvMaterialRequestBill.setRequestPersonName(usr.getName());
					scmInvMaterialRequestBill.setConvertMap(ScmInvMaterialRequestBill2.FN_REQUESTPERSON, usr);
				}
			}
			if (StringUtils.isNotBlank(scmInvMaterialRequestBill.getOrgUnitNo())){
				//申请组织
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMaterialRequestBill.getOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmInvMaterialRequestBill.setUseDeptName(orgBaseUnit.getOrgUnitName());
					scmInvMaterialRequestBill.setConvertMap(ScmInvMaterialRequestBill2.FN_ORGUNITNO, orgBaseUnit);
				}
			}
			
			if (StringUtils.isNotBlank(scmInvMaterialRequestBill.getFinOrgUnitNo())){
				//财务组织
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMaterialRequestBill.getFinOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmInvMaterialRequestBill.setConvertMap(ScmInvMaterialRequestBill2.FN_FINORGUNITNO, orgBaseUnit);
				}
			}
			
			if (StringUtils.isNotBlank(scmInvMaterialRequestBill.getInvOrgUnitNo())){
				//库存组织
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMaterialRequestBill.getInvOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmInvMaterialRequestBill.setInvOrgUnitName(orgBaseUnit.getOrgUnitName());
					scmInvMaterialRequestBill.setConvertMap(ScmInvMaterialRequestBill2.FN_INVORGUNITNO, orgBaseUnit);
				}
			}
			if (scmInvMaterialRequestBill.getWareHouseId() > 0){
				//存货仓库
				ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvMaterialRequestBill.getWareHouseId(), param);
				if (scmInvWareHouse != null) {
					scmInvMaterialRequestBill.setWareHouseNo(scmInvWareHouse.getWhNo());
					scmInvMaterialRequestBill.setWareHouseName(scmInvWareHouse.getWhName());
					scmInvMaterialRequestBill.setConvertMap(ScmInvMaterialRequestBill2.FN_WAREHOUSEID, scmInvWareHouse);
				}
			}
		}
	}
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmInvMaterialRequestBill2 scmInvMaterialRequestBill = (ScmInvMaterialRequestBill2) bean.getList().get(0);
		if(scmInvMaterialRequestBill != null && scmInvMaterialRequestBill.getReqId() > 0){
			HashMap<String,Object> cacheMap = new HashMap<String,Object>();
			List<ScmInvMaterialRequestBillEntry2> scmInvMaterialRequestBillEntryList = scmInvMaterialRequestBillEntryBiz.selectByReqId(scmInvMaterialRequestBill.getReqId(), param);
			if(scmInvMaterialRequestBillEntryList != null && !scmInvMaterialRequestBillEntryList.isEmpty()){
				bean.setList2(scmInvMaterialRequestBillEntryList);
			}
		}
	}

	@Override
	protected void beforeAdd(ScmInvMaterialRequestBill2 entity, Param param) throws AppException {
		if(entity != null){
			String code = CodeAutoCalUtil.getBillCode("InvMatReqBill", entity, param);
			entity.setReqNo(code);
			List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOFIN,entity.getOrgUnitNo(), false, null, param);
			if(orgCompanyList==null || orgCompanyList.isEmpty())
				throw new AppException("iscm.inventorymanage.ScmInvMaterialRequestBillBizImpl.reqOrgUnit.notfinorg");
			entity.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
			entity.setCurrencyNo(orgCompanyList.get(0).getBaseCurrency());
//			BillType2 billType = billTypeBiz.selectByOrgAndCode(entity.getFinOrgUnitNo(), "InvMatReqBill", param);
//			if(billType!=null && billType.isNeedAudit()) {
//				if(StringUtils.isBlank(billType.getWorkFlowNo())) {
//					throw new AppException(Message.getMessage(param.getLang(), "iscm.workflow.needaudit.notprocess", new String[] {Message.getMessage(param.getLang(),"field.ScmInvMaterialRequestBill")}));
//				}
//				entity.setWorkFlowNo(billType.getWorkFlowNo());
//			}
		}
	}
	
	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmInvMaterialRequestBill2 scmInvMaterialRequestBill = (ScmInvMaterialRequestBill2) bean.getList().get(0);
		if(scmInvMaterialRequestBill != null && scmInvMaterialRequestBill.getReqId() > 0){
			//新增领料申请明细
			List<ScmInvMaterialRequestBillEntry2> scmInvMaterialRequestBillEntryList = bean.getList2();
			if(scmInvMaterialRequestBillEntryList != null && !scmInvMaterialRequestBillEntryList.isEmpty()){
                int lineId = 1;
				for(ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry : scmInvMaterialRequestBillEntryList){
					scmInvMaterialRequestBillEntry.setLineId(lineId);
					scmInvMaterialRequestBillEntry.setReqId(scmInvMaterialRequestBill.getReqId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvMaterialRequestBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvMaterialRequestBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvMaterialRequestBillEntry.getItemId(), scmInvMaterialRequestBillEntry.getUnit(), param);//库存单位转换系数
					scmInvMaterialRequestBillEntry.setBaseQty(scmInvMaterialRequestBillEntry.getQty().multiply(invConvRate));
					scmInvMaterialRequestBillEntryBiz.add(scmInvMaterialRequestBillEntry, param);
                    lineId = lineId+1;
				}
			}
		}
	}
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvMaterialRequestBill2 scmInvMaterialRequestBill = (ScmInvMaterialRequestBill2) bean.getList().get(0);
		if(scmInvMaterialRequestBill != null && scmInvMaterialRequestBill.getReqId() > 0){
			//更新出库明细
			List<ScmInvMaterialRequestBillEntry2> scmInvMaterialRequestBillEntryList = bean.getList2();
			if(scmInvMaterialRequestBillEntryList != null && !scmInvMaterialRequestBillEntryList.isEmpty()){
                int lineId = 1;
				for(ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry : scmInvMaterialRequestBillEntryList){
					scmInvMaterialRequestBillEntry.setLineId(lineId);
					scmInvMaterialRequestBillEntry.setReqId(scmInvMaterialRequestBill.getReqId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvMaterialRequestBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvMaterialRequestBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvMaterialRequestBillEntry.getItemId(), scmInvMaterialRequestBillEntry.getUnit(), param);//库存单位转换系数
					scmInvMaterialRequestBillEntry.setBaseQty(scmInvMaterialRequestBillEntry.getQty().multiply(invConvRate));
					scmInvMaterialRequestBillEntry.setReqId(scmInvMaterialRequestBill.getReqId());
                    lineId = lineId+1;
				}
				scmInvMaterialRequestBillEntryBiz.update(scmInvMaterialRequestBill, scmInvMaterialRequestBillEntryList, ScmInvMaterialRequestBillEntry2.FN_REQID, param);
			}
		}
	}
	
	@Override
	protected void afterDelete(ScmInvMaterialRequestBill2 entity, Param param) throws AppException {
		if(entity != null && entity.getReqId() > 0){
			//删除领料申请明细
			scmInvMaterialRequestBillEntryBiz.deleteByReqId(entity.getReqId(), param);
		}
	}
	
	public ScmInvMaterialRequestBill2 updateStatus(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill, Param param) throws AppException {
		if(scmInvMaterialRequestBill != null){
			String status = scmInvMaterialRequestBill.getStatus();
			if(scmInvMaterialRequestBill != null){
				scmInvMaterialRequestBillEntryBiz.updateRowStatusByReqId(scmInvMaterialRequestBill.getReqId(), 
						scmInvMaterialRequestBill.getStatus(), scmInvMaterialRequestBill.getChecker(), scmInvMaterialRequestBill.getCheckDate(), param);
				
				List <ScmInvMaterialRequestBillEntry2> scmInvMaterialRequestBillEntryList = scmInvMaterialRequestBillEntryBiz.selectByReqId(scmInvMaterialRequestBill.getReqId(), param);
				for (ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry : scmInvMaterialRequestBillEntryList) {
					String rowStatus = scmInvMaterialRequestBillEntry.getRowStatus();
					if ("N".equals(rowStatus)) {
						if ("A".equals(status)) {
							scmInvMaterialRequestBill.setStatus("B");
						} else if ("E".equals(status) ){
							scmInvMaterialRequestBill.setStatus("S");
						} else if ("C".equals(status)) {
							scmInvMaterialRequestBill.setStatus("F");
						} else {
							scmInvMaterialRequestBill.setStatus(scmInvMaterialRequestBill.getStatus());
						}
						break;
					}
				}
				
				return this.updateDirect(scmInvMaterialRequestBill, param);
			}	
		}
		return null;
	}
	@Override
	public List<ScmInvMaterialRequestBill2> queryInvMaterialReqList(
			ScmInvMaterialRequestBillAdvQuery scmInvMaterialRequestBillAdvQuery,
			int pageNum, Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(ScmInvMaterialRequestBill2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		page.setModel(scmInvMaterialRequestBillAdvQuery);	//findPage时底层会触发doAdvQuery，增加查询条件
		
		return this.findPage(page, param);
	}

	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvMaterialRequestBillAdvQuery) {
				ScmInvMaterialRequestBillAdvQuery scmInvMaterialRequestBillAdvQuery = (ScmInvMaterialRequestBillAdvQuery) page.getModel();
				if(StringUtils.isNotBlank(scmInvMaterialRequestBillAdvQuery.getReqNo())){
					page.getParam().put(ScmInvMaterialRequestBill2.FN_REQNO,new QueryParam(ScmInvMaterialRequestBill2.FN_REQNO, QueryParam.QUERY_LIKE,"%"+scmInvMaterialRequestBillAdvQuery.getReqNo()+"%"));
				}
				if(scmInvMaterialRequestBillAdvQuery.getBizDateFrom()!=null){
					if(scmInvMaterialRequestBillAdvQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmInvMaterialRequestBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialRequestBill2.class) + "." +ScmInvMaterialRequestBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvMaterialRequestBillAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(scmInvMaterialRequestBillAdvQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmInvMaterialRequestBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialRequestBill2.class) + "." +ScmInvMaterialRequestBill2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvMaterialRequestBillAdvQuery.getBizDateFrom())));
					}
				}else if(scmInvMaterialRequestBillAdvQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmInvMaterialRequestBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialRequestBill2.class) + "." +ScmInvMaterialReqBill2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmInvMaterialRequestBillAdvQuery.getBizDateTo())));
				}
				
				if(scmInvMaterialRequestBillAdvQuery.getCreateDateFrom()!=null){
					if(scmInvMaterialRequestBillAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmInvMaterialRequestBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialRequestBill2.class) + "." +ScmInvMaterialRequestBill2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvMaterialRequestBillAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvMaterialRequestBillAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmInvMaterialRequestBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialRequestBill2.class) + "." +ScmInvMaterialRequestBill2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvMaterialRequestBillAdvQuery.getCreateDateFrom())));
					}
				}else if(scmInvMaterialRequestBillAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmInvMaterialRequestBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialRequestBill2.class) + "." +ScmInvMaterialReqBill2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvMaterialRequestBillAdvQuery.getCreateDateTo(),1))));
				}
				if(StringUtils.isNotBlank(scmInvMaterialRequestBillAdvQuery.getUseDeptNo())){
					page.getParam().put(ScmInvMaterialRequestBill2.FN_ORGUNITNO,new QueryParam(ScmInvMaterialRequestBill2.FN_ORGUNITNO, QueryParam.QUERY_EQ,scmInvMaterialRequestBillAdvQuery.getUseDeptNo()));
				}
				if(StringUtils.isNotBlank(scmInvMaterialRequestBillAdvQuery.getStatus())){
					String status[] = StringUtils.split(scmInvMaterialRequestBillAdvQuery.getStatus(), ",");
					StringBuffer statusBuffer = new StringBuffer("");
					for(String sta:status){
						if(StringUtils.isNotBlank(statusBuffer.toString()))
							statusBuffer.append(",");
						statusBuffer.append("'").append(sta).append("'");
					}
					if(StringUtils.isNotBlank(statusBuffer.toString())){
						page.getParam().put(ScmInvMaterialRequestBill2.FN_STATUS,new QueryParam(ScmInvMaterialRequestBill2.FN_STATUS, QueryParam.QUERY_IN,statusBuffer.toString()));
					}
				}
				if(StringUtils.isNotBlank(scmInvMaterialRequestBillAdvQuery.getCreator())){
					page.getParam().put(ScmInvMaterialRequestBill2.FN_CREATOR,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialRequestBill2.class) + "." +ScmInvMaterialRequestBill2.FN_CREATOR, QueryParam.QUERY_EQ,scmInvMaterialRequestBillAdvQuery.getCreator()));
				}
				if(StringUtils.isNotBlank(scmInvMaterialRequestBillAdvQuery.getOrgUnitNo())){
					page.getParam().put(ScmInvMaterialRequestBill2.FN_ORGUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialRequestBill2.class) + "." +ScmInvMaterialRequestBill2.FN_ORGUNITNO, QueryParam.QUERY_EQ,scmInvMaterialRequestBillAdvQuery.getOrgUnitNo()));
				}
				if(scmInvMaterialRequestBillAdvQuery.getWareHouseId()>0) {
					page.getParam().put(ScmInvMaterialRequestBill2.FN_WAREHOUSEID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialRequestBill2.class) + "." +ScmInvMaterialRequestBill2.FN_WAREHOUSEID, QueryParam.QUERY_EQ,String.valueOf(scmInvMaterialRequestBillAdvQuery.getWareHouseId())));
				}
			}
		}
	}
	
	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page,HashMap<String, Object> map, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvMaterialRequestBillAdvQuery) {
				ScmInvMaterialRequestBillAdvQuery scmInvMaterialRequestBillAdvQuery = (ScmInvMaterialRequestBillAdvQuery) page.getModel();
				if(scmInvMaterialRequestBillAdvQuery.isFromInterface())
					map.put("sortByDate", "Y");
			}
		}
		return map;
	}
	@Override
	public ScmInvMaterialRequestBill2 queryInvMaterialReq(
			ScmInvMaterialRequestBill2 scmInvMaterialRequestBill, Param param)
			throws AppException {
		Page page=new Page();
		page.setModelClass(ScmInvMaterialRequestBill2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmInvMaterialRequestBill2.FN_REQNO,new QueryParam(ScmInvMaterialRequestBill2.FN_REQNO, QueryParam.QUERY_EQ,scmInvMaterialRequestBill.getReqNo()));
		
		List<ScmInvMaterialRequestBill2> scmInvMaterialRequestBillList =this.findPage(page, param);
		ScmInvMaterialRequestBill2 scmInvMaterialRequestBill2 = new ScmInvMaterialRequestBill2();
		if(scmInvMaterialRequestBillList!=null && !scmInvMaterialRequestBillList.isEmpty()){
			scmInvMaterialRequestBill2 = scmInvMaterialRequestBillList.get(0);
			scmInvMaterialRequestBill2.setScmInvMaterialRequestBillEntryList(scmInvMaterialRequestBillEntryBiz.selectByReqId(scmInvMaterialRequestBill2.getReqId(), param));
			
			//待审接口增加待审人
			ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmInvMaterialRequestBill2.getReqId(), "InvMatReqBill",param);
			if (scmBillPendingUsr != null) {
				scmInvMaterialRequestBill2.setPendingUsr(scmBillPendingUsr.getUsrCodes());
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmInvMaterialRequestBill2;
	}
	@Override
	public void doDelInvMaterialReq(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill, Param param)
			throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put(ScmInvMaterialRequestBill2.FN_REQNO, scmInvMaterialRequestBill.getReqNo());
		map.put(ScmInvMaterialRequestBill2.FN_CONTROLUNITNO, param.getControlUnitNo());
		List<ScmInvMaterialRequestBill2> scmInvMaterialRequestBillList = this.findAll(map, param);
		if(scmInvMaterialRequestBillList!=null && !scmInvMaterialRequestBillList.isEmpty()){
			try {
				this.delete(scmInvMaterialRequestBillList, param);
			} catch (Exception e) {
				throw e;
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
	}
	@Override
	public ScmInvMaterialRequestBill2 doSubmit(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill, Param param)
			throws AppException {
		ScmInvMaterialRequestBill2 materialRequest = null;
		if(scmInvMaterialRequestBill.getReqId()>0){
			materialRequest = this.selectDirect(scmInvMaterialRequestBill.getReqId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmInvMaterialRequestBill2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmInvMaterialRequestBill2.FN_REQNO,
					new QueryParam(ScmInvMaterialRequestBill2.FN_REQNO, QueryParam.QUERY_EQ,
							scmInvMaterialRequestBill.getReqNo()));
			
			List<ScmInvMaterialRequestBill2> scmInvMaterialRequestBillList =this.findPage(page, param);
			if(scmInvMaterialRequestBillList!=null && !scmInvMaterialRequestBillList.isEmpty()){
				materialRequest=scmInvMaterialRequestBillList.get(0);
			}
		}
		if(materialRequest!=null){
			this.setConvertMap(materialRequest, param);
			if(!StringUtils.equals(materialRequest.getStatus(),"I")){
				throw new AppException("iscm.purchasemanage.error.commit");
			}else if(materialRequest.getStatus().equals("I")){
				BillType2 billType = billTypeBiz.selectByOrgAndCode(materialRequest.getFinOrgUnitNo(), "InvMatReqBill", param);
				boolean needAudit = false;
				if(billType!=null && billType.isNeedAudit())
					needAudit = true;
				if(needAudit){
					//发起流程
					BigDecimal totalAmt = BigDecimal.ZERO;
					List<ScmInvMaterialRequestBillEntry2> scmInvMaterialRequestBillEntryList = scmInvMaterialRequestBillEntryBiz.selectByReqId(materialRequest.getReqId(), param);
					if(scmInvMaterialRequestBillEntryList != null && !scmInvMaterialRequestBillEntryList.isEmpty()){
						for(ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry : scmInvMaterialRequestBillEntryList){
							totalAmt = totalAmt.add(scmInvMaterialRequestBillEntry.getAmt());
						}
					}
					materialRequest.setTotalAmt(totalAmt);
					CommonBean bean = new CommonBean();
					List<ScmInvMaterialRequestBill2> scmInvMaterialReauestBillList = new ArrayList<>();
					scmInvMaterialReauestBillList.add(materialRequest);
					bean.setList(scmInvMaterialReauestBillList);
					bean.setList2(scmInvMaterialRequestBillEntryList);
					String processInstanceId = ActivityUtil.startProcessInstance(bean, billType.getId(), billType.getWorkFlowNo(), param);
					materialRequest.setWorkFlowNo(processInstanceId);
					ActivityUtil activityUtil = new ActivityUtil();
					//判断当前流程是否结束
					boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
					if(isCompleteTask) {
						if(param.getUsrCode()!=null ){
							materialRequest.setChecker(param.getUsrCode());
							materialRequest.setSubmitter(param.getUsrCode());
						}
						materialRequest.setCheckDate(CalendarUtil.getDate(param));
						materialRequest.setSubmitDate(CalendarUtil.getDate(param));
						materialRequest.setStatus("A");
					}else {
						if(param.getUsrCode()!=null ){
							materialRequest.setSubmitter(param.getUsrCode());
						}
						materialRequest.setSubmitDate(CalendarUtil.getDate(param));
						materialRequest.setStatus("D");
						String usrList= ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId,param);
						if(StringUtils.isNotBlank(usrList)) {
							scmBillPendingBiz.addPendingBill(usrList, materialRequest, param);
							InvMaterialReqParams invMaterialReqParams = new InvMaterialReqParams();
							invMaterialReqParams.setReqNo(materialRequest.getReqNo());
							AuditMsgUtil.sendAuditMsg(billType.getBillCode(),materialRequest,invMaterialReqParams, usrList, param);
						}
					}
				}else{
					if(param.getUsrCode()!=null ){
						materialRequest.setChecker(param.getUsrCode());
						materialRequest.setSubmitter(param.getUsrCode());
					}
					materialRequest.setCheckDate(CalendarUtil.getDate(param));
					materialRequest.setSubmitDate(CalendarUtil.getDate(param));
					materialRequest.setStatus("A");
				}
				try {
					this.updateStatus(materialRequest, param);
				} catch (Exception e) {
					throw e;
				}
				if(StringUtils.contains("A,B", materialRequest.getStatus())) {
					//通过或部分通过时检查是否自动下达
					if(billType!=null && billType.isAutoRelease()) {
						this.release(materialRequest, param);
					}
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return materialRequest;
	}
	@Override
	public ScmInvMaterialRequestBill2 doUnSubmit(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill, Param param)
			throws AppException {
		ScmInvMaterialRequestBill2 require = null;
		if(scmInvMaterialRequestBill.getReqId()>0){
			require = this.selectDirect(scmInvMaterialRequestBill.getReqId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmInvMaterialRequestBill2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmInvMaterialRequestBill2.FN_REQNO,
					new QueryParam(ScmInvMaterialRequestBill2.FN_REQNO, QueryParam.QUERY_EQ,
							scmInvMaterialRequestBill.getReqNo()));
			
			List<ScmInvMaterialRequestBill2> scmInvMaterialRequestBillList =this.findPage(page, param);
			if(scmInvMaterialRequestBillList!=null && !scmInvMaterialRequestBillList.isEmpty()){
				require=scmInvMaterialRequestBillList.get(0);
			}
		}
		if(require!=null){
			BillType2 billType = billTypeBiz.selectByOrgAndCode(require.getFinOrgUnitNo(), "InvMatReqBill", param);
			boolean needAudit = false;
			if(billType!=null && billType.isNeedAudit() && StringUtils.isNotBlank(require.getWorkFlowNo()))
				needAudit = true;
			
			//单据设置审批流程，但设置没有审批节点，状态为通过，可以取消通过
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ScmBillPending.FN_BILLID, new QueryParam(ScmBillPending.FN_BILLID, QueryParam.QUERY_EQ, String.valueOf(require.getReqId())));
			map.put(ScmBillPending.FN_BILLTYPE, new QueryParam(ScmBillPending.FN_BILLTYPE, QueryParam.QUERY_EQ, billType.getBillCode()));
			List<ScmBillPending> scmBillPendingList = scmBillPendingBiz.findAll(map, param);
			
			
			
			if(needAudit){
				if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
					if(!StringUtils.equals(require.getStatus(),"A"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				} else {
					if(!StringUtils.equals(require.getStatus(),"D"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				}
			}else{
				if(!StringUtils.equals(require.getStatus(),"A"))
					throw new AppException("iscm.purchasemanage.error.cancelCommit");
			} 
			if(require.getStatus().equals("A") || require.getStatus().equals("D")){
				require.setChecker(null);
				require.setCheckDate(null);
				require.setSubmitter(null);
				require.setSubmitDate(null);
				require.setStatus("I");
				try{
					this.updateStatus(require, param);
					scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),require, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return require;
	}
	@Override
	public List<OrgAdmin2> queryInvMaterialReqDept(InvMaterialReqDeptParams invMaterialReqDeptParams, Param param)
			throws AppException {
		Page page = new Page();
		page.setModelClass(OrgAdmin2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if (StringUtils.isNotBlank(invMaterialReqDeptParams.getDeptName())) {
			page.getParam().put((ClassUtils.getFinalModelSimpleName(OrgBaseUnit2.class) + "." + OrgBaseUnit2.FN_ORGUNITNAME), 
    				new QueryParam((ClassUtils.getFinalModelSimpleName(OrgBaseUnit2.class) + "." + OrgBaseUnit2.FN_ORGUNITNAME), QueryParam.QUERY_LIKE, "%"+invMaterialReqDeptParams.getDeptName()+"%"));
		}
		OrgAdmin2 orgAdmin2 = orgAdminBiz.selectByOrgUnitNo(param.getOrgUnitNo(), param);
		page.setSqlCondition("OrgAdmin.longNo like '" + orgAdmin2.getLongNo()+ "%'  and orgType='2' And Exists(Select 1 From orgunitrelation Where fromOrgUnitNo=OrgAdmin.orgUnitNo And relationType='AdminToCost')");
		return orgAdminBiz.findPage(page, param);
	}
	@Override
	public List<OrgStorage2> queryPurReqPurOrgUnit(
			InvMaterialReqInvOrgUnitParams invMaterialReqInvOrgUnitParams,
			Param param) throws AppException {
		if(invMaterialReqInvOrgUnitParams!=null && StringUtils.isNotBlank(invMaterialReqInvOrgUnitParams.getDeptNo())){
			Page page = new Page();
			page.setModelClass(OrgStorage2.class);
			page.setShowCount(Integer.MAX_VALUE);
			List<String> arglist = new ArrayList<String>();
			arglist.add("type=to");
			arglist.add("relationType="+OrgUnitRelationType.ADMINTOINV);
			arglist.add("fromOrgUnitNo="+invMaterialReqInvOrgUnitParams.getDeptNo());
			return orgStorageBiz.queryPage(page, arglist, "queryPageEx", param);
		}
		return null;
	}
	@Override
	public List<Usr2> queryInvMaterialReqPerson(
			InvMaterialReqPersonParams invMaterialReqPersonParams, int pageNum,
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
		if(StringUtils.isNotBlank(invMaterialReqPersonParams.getRequestPersonName())){
			page.setSqlCondition("Usr.name like '"+invMaterialReqPersonParams.getRequestPersonName()+"%'");
		}
		return usrBiz.findPage(page, param);
	}
	@Override
	public List<ScmMaterial2> queryInvReqMaterialList(
			InvReqMaterialListParams invReqMaterialListParams, int pageNum,
			Param param) throws AppException {
		OrgStorage2  orgStorage = orgStorageBiz.selectByOrgUnitNo(invReqMaterialListParams.getInvOrgUnitNo(), param);
		if (orgStorage == null) {
			throw new AppException("iscm.scmpurrequire.querypurreqmateriallist.error.orgpurchasenotexists");
		}
		ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectByWhNo(orgStorage.getOrgUnitNo(), invReqMaterialListParams.getWareHouseNo(), param);
		if(scmInvWareHouse == null){
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.ScmInvMaterialReqBillBizImpl.error.warehouseisnull");
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
		arglist.add("orgUnitNo="+orgStorage.getOrgUnitNo());
		arglist.add("controlUnitNo="+orgStorage.getControlUnitNo());
		arglist.add("wareHouseId="+scmInvWareHouse.getId());
		arglist.add("costCenter=0");
		arglist.add("vendorId=0");
		if(StringUtils.isNotBlank(invReqMaterialListParams.getMixParam())){
			page.setSqlCondition("(ScmMaterial.itemNo like '%"+invReqMaterialListParams.getMixParam()+"%' or ScmMaterial.itemName like '%"+invReqMaterialListParams.getMixParam()+
					"%' or ScmMaterial.barCode like '%"+invReqMaterialListParams.getMixParam()+"%' or ScmMaterial.pym like '%"+invReqMaterialListParams.getMixParam()+"%')");
		}
		return scmMaterialBiz.queryPage(page, arglist, "selectByStockPage", param);
	}
	@Override
	public ScmInvMaterialRequestBill2 release(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill, Param param) throws AppException {
		ScmInvMaterialRequestBill2 require = null;
		if(scmInvMaterialRequestBill.getReqId()>0){
			require = this.selectDirect(scmInvMaterialRequestBill.getReqId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmInvMaterialRequestBill2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmInvMaterialRequestBill2.FN_REQNO,
					new QueryParam(ScmInvMaterialRequestBill2.FN_REQNO, QueryParam.QUERY_EQ,
							scmInvMaterialRequestBill.getReqNo()));
			
			List<ScmInvMaterialRequestBill2> scmInvMaterialRequestBillList =this.findPage(page, param);
			if(scmInvMaterialRequestBillList!=null && !scmInvMaterialRequestBillList.isEmpty()){
				require=scmInvMaterialRequestBillList.get(0);
			}
		}
		if(require!=null){
			if(!StringUtils.contains("A,B", require.getStatus())){
				throw new AppException("iscm.purchasemanage.error.release");
			}else{
				if(require.getStatus().equals("A")){
					require.setStatus("E");
				}else {
					require.setStatus("S");
				}
				try {
					this.updateDirect(require, param);
					scmInvMaterialRequestBillEntryBiz.doRelease(require, param);
					this.generateMaterialReqBill(require, param);
				} catch (Exception e) {
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return require;
	}
	@Override
	public ScmInvMaterialRequestBill2 undoRelease(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill, Param param) throws AppException {
		ScmInvMaterialRequestBill2 require = null;
		if(scmInvMaterialRequestBill.getReqId()>0){
			require = this.selectDirect(scmInvMaterialRequestBill.getReqId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmInvMaterialRequestBill2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmInvMaterialRequestBill2.FN_REQNO,
					new QueryParam(ScmInvMaterialRequestBill2.FN_REQNO, QueryParam.QUERY_EQ,
							scmInvMaterialRequestBill.getReqNo()));
			
			List<ScmInvMaterialRequestBill2> scmInvMaterialRequestBillList =this.findPage(page, param);
			if(scmInvMaterialRequestBillList!=null && !scmInvMaterialRequestBillList.isEmpty()){
				require=scmInvMaterialRequestBillList.get(0);
			}
		}
		if(require!=null){
			if(!StringUtils.contains("E,S",require.getStatus())){
				throw new AppException("iscm.purchasemanage.error.cancelRelease");
			}else{
				if(require.getStatus().equals("E")){
					require.setStatus("A");
				}else {
					require.setStatus("B");
				}
				try {
					this.updateDirect(require, param);
					scmInvMaterialRequestBillEntryBiz.doUndoRelease(require, param);
				} catch (Exception e) {
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return require;
	}
	@Override
	public ScmInvMaterialRequestBill2 finish(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill, Param param)	throws AppException {
		ScmInvMaterialRequestBill2 require = null;
		if(scmInvMaterialRequestBill.getReqId()>0){
			require = this.selectDirect(scmInvMaterialRequestBill.getReqId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmInvMaterialRequestBill2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmInvMaterialRequestBill2.FN_REQNO,
					new QueryParam(ScmInvMaterialRequestBill2.FN_REQNO, QueryParam.QUERY_EQ,
							scmInvMaterialRequestBill.getReqNo()));
			
			List<ScmInvMaterialRequestBill2> scmInvMaterialRequestBillList =this.findPage(page, param);
			if(scmInvMaterialRequestBillList!=null && !scmInvMaterialRequestBillList.isEmpty()){
				require=scmInvMaterialRequestBillList.get(0);
			}
		}
		if(require!=null){
			if(!StringUtils.equals(require.getStatus(),"E")){
				throw new AppException("iscm.purchasemanage.error.finish");
			}else if(require.getStatus().equals("E")){
				require.setStatus("C");
				try {
					this.updateStatus(require, param);
				} catch (Exception e) {
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return require;
	}
	@Override
	public ScmInvMaterialRequestBill2 undoFinish(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill, Param param)	throws AppException {
		ScmInvMaterialRequestBill2 require = null;
		if(scmInvMaterialRequestBill.getReqId()>0){
			require = this.selectDirect(scmInvMaterialRequestBill.getReqId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmInvMaterialRequestBill2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmInvMaterialRequestBill2.FN_REQNO,
					new QueryParam(ScmInvMaterialRequestBill2.FN_REQNO, QueryParam.QUERY_EQ,
							scmInvMaterialRequestBill.getReqNo()));
			
			List<ScmInvMaterialRequestBill2> scmInvMaterialRequestBillList =this.findPage(page, param);
			if(scmInvMaterialRequestBillList!=null && !scmInvMaterialRequestBillList.isEmpty()){
				require=scmInvMaterialRequestBillList.get(0);
			}
		}
		if(require!=null){
			if(!StringUtils.equals(require.getStatus(),"C")){
				throw new AppException("iscm.purchasemanage.error.cancelFinish");
			}else if(require.getStatus().equals("C")){
				require.setStatus("E");
				try {
					this.updateStatus(require, param);
				} catch (Exception e) {
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return require;
	}
	@Override
	public ScmInvMaterialRequestBill2 doAddMaterialReq(
			InvMaterialReqAddParams invMaterialReqAddParams, Param param)
			throws AppException {
		CommonBean bean = new CommonBean();
		List<ScmInvMaterialRequestBill2> scmInvMaterialRequestBillList = new ArrayList<>();
		ScmInvMaterialRequestBill2 scmInvMaterialRequestBill=new ScmInvMaterialRequestBill2(true);
		try {
			BeanUtils.copyProperties(invMaterialReqAddParams, scmInvMaterialRequestBill);
			scmInvMaterialRequestBill.setStatus("I");
			scmInvMaterialRequestBill.setOrgUnitNo(invMaterialReqAddParams.getUseDeptNo());
			scmInvMaterialRequestBill.setCreateDate(CalendarUtil.getDate(param));
			scmInvMaterialRequestBill.setCreator(param.getUsrCode());
			if(scmInvMaterialRequestBill.getBizDate() != null){
				scmInvMaterialRequestBill.setBizDate(FormatUtils.parseDate(FormatUtils.fmtDate(scmInvMaterialRequestBill.getBizDate())));
			}
			if(StringUtils.isNotBlank(invMaterialReqAddParams.getWareHouseNo())) {
				ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectByWhNo(invMaterialReqAddParams.getInvOrgUnitNo(), invMaterialReqAddParams.getWareHouseNo(), param);
				if(scmInvWareHouse!=null)
					scmInvMaterialRequestBill.setWareHouseId(scmInvWareHouse.getId());
			}
			scmInvMaterialRequestBillList.add(scmInvMaterialRequestBill);
			bean.setList(scmInvMaterialRequestBillList);
			List<InvMaterialReqDetailParams> detailList = invMaterialReqAddParams.getDetailList();
			if(detailList!=null && !detailList.isEmpty()){
				StringBuffer itemNos = new StringBuffer("");
				for(InvMaterialReqDetailParams detailParams:detailList){
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
		        argList.add("orgUnitNo="+invMaterialReqAddParams.getInvOrgUnitNo());
		        argList.add("controlUnitNo=" + param.getControlUnitNo());
		        argList.add("wareHouseId=0");
		        argList.add("costCenter=0");
		        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "selectByStock", param);
				List<ScmInvMaterialRequestBillEntry2> scmInvMaterialRequestBillEntryList = new ArrayList<>();
				for(InvMaterialReqDetailParams detailParams:detailList){
					ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry = new ScmInvMaterialRequestBillEntry2(true);
					BeanUtils.copyProperties(detailParams, scmInvMaterialRequestBillEntry);
					for(ScmMaterial2 scmMaterial:scmMaterialList){
						if(StringUtils.equals(scmMaterial.getItemNo(), detailParams.getItemNo())){
							scmInvMaterialRequestBillEntry.setItemId(scmMaterial.getId());
							scmInvMaterialRequestBillEntry.setUnit(scmMaterial.getUnitId());
							scmInvMaterialRequestBillEntry.setPieUnit(scmMaterial.getPieUnitId());
							scmInvMaterialRequestBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
							scmInvMaterialRequestBillEntry.setPrice(scmMaterial.getPrice());
							scmInvMaterialRequestBillEntry.setAmt(scmInvMaterialRequestBillEntry.getQty().multiply(scmMaterial.getPrice()));
							scmInvMaterialRequestBillEntry.setTaxPrice(scmMaterial.getTaxPrice());
							scmInvMaterialRequestBillEntry.setTaxAmt(scmInvMaterialRequestBillEntry.getQty().multiply(scmMaterial.getTaxPrice()));
							break;
						}
					}
					scmInvMaterialRequestBillEntry.setRowStatus("I");
					BigDecimal convRate = ScmMaterialUtil.getConvRate(scmInvMaterialRequestBillEntry.getItemId(), scmInvMaterialRequestBillEntry.getUnit(), param);
					scmInvMaterialRequestBillEntry.setBaseQty(scmInvMaterialRequestBillEntry.getQty().multiply(convRate));
					scmInvMaterialRequestBillEntryList.add(scmInvMaterialRequestBillEntry);
				}
				bean.setList2(scmInvMaterialRequestBillEntryList);
			}
			this.add(bean, param);
		} catch (Exception e) {
			throw e;
		}
		
		return scmInvMaterialRequestBill;
	}
	@Override
	public void doEditMaterialReq(InvMaterialReqEditParams invMaterialReqEditParams, Param param)
			throws AppException {
		if(invMaterialReqEditParams!=null){
			CommonBean bean = new CommonBean();
			
			Page page=new Page();
			page.setModelClass(ScmInvMaterialRequestBill2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmInvMaterialRequestBill2.FN_REQNO,new QueryParam(ScmInvMaterialRequestBill2.FN_REQNO, QueryParam.QUERY_EQ,invMaterialReqEditParams.getReqNo()));
			List<ScmInvMaterialRequestBill2> scmInvMaterialRequestBillList =this.findPage(page, param);
			if(scmInvMaterialRequestBillList!=null && !scmInvMaterialRequestBillList.isEmpty()){
//				if(!StringUtils.equals(scmInvMaterialRequestBillList.get(0).getStatus(),"I")){
//					throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
//				}
				//更新主表数据
				ScmInvMaterialRequestBill2 scmInvMaterialRequestBill = scmInvMaterialRequestBillList.get(0);
				BeanUtils.copyProperties(invMaterialReqEditParams, scmInvMaterialRequestBill);
				if(scmInvMaterialRequestBill.getBizDate() != null){
					scmInvMaterialRequestBill.setBizDate(FormatUtils.parseDate(FormatUtils.fmtDate(scmInvMaterialRequestBill.getBizDate())));
				}
				scmInvMaterialRequestBill.setOrgUnitNo(invMaterialReqEditParams.getUseDeptNo());
				scmInvMaterialRequestBill.setEditDate(CalendarUtil.getDate(param));
				scmInvMaterialRequestBill.setEditor(param.getUsrCode());
				if(StringUtils.isNotBlank(invMaterialReqEditParams.getWareHouseNo())) {
					ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectByWhNo(invMaterialReqEditParams.getInvOrgUnitNo(), invMaterialReqEditParams.getWareHouseNo(), param);
					if(scmInvWareHouse!=null)
						scmInvMaterialRequestBill.setWareHouseId(scmInvWareHouse.getId());
				}
				bean.setList(scmInvMaterialRequestBillList);
				List<InvMaterialReqDetailParams> detailList = invMaterialReqEditParams.getDetailList();
				List<ScmInvMaterialRequestBillEntry2> scmInvMaterialRequestBillEntryList = scmInvMaterialRequestBillEntryBiz.selectByReqId(scmInvMaterialRequestBill.getReqId(), param);
				if(scmInvMaterialRequestBillEntryList==null || scmInvMaterialRequestBillEntryList.isEmpty()){
					scmInvMaterialRequestBillEntryList = new ArrayList<>();
					StringBuffer itemNos = new StringBuffer("");
					for(InvMaterialReqDetailParams detailParams:detailList){
						if(StringUtils.isBlank(detailParams.getItemNo())){
							throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemisnull");
						}
						if(StringUtils.isNotBlank(itemNos.toString()))
							itemNos.append(",");
						itemNos.append("'").append(detailParams.getItemNo()).append("'");
					}
					page = new Page();
					page.setModelClass(ScmMaterial2.class);
					page.setShowCount(Integer.MAX_VALUE);
					page.setSqlCondition("ScmMaterial.itemNo in ("+itemNos.toString()+")");
					ArrayList argList = new ArrayList();
					argList.add("orgUnitNo="+invMaterialReqEditParams.getInvOrgUnitNo());
			        argList.add("controlUnitNo=" + param.getControlUnitNo());
			        argList.add("wareHouseId=0");
			        argList.add("costCenter=0");
			        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "selectByStock", param);
					for(InvMaterialReqDetailParams detailParams:detailList){
						ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry = new ScmInvMaterialRequestBillEntry2(true);
						BeanUtils.copyProperties(detailParams, scmInvMaterialRequestBillEntry);
						for(ScmMaterial2 scmMaterial:scmMaterialList){
							if(StringUtils.equals(scmMaterial.getItemNo(), detailParams.getItemNo())){
								scmInvMaterialRequestBillEntry.setItemId(scmMaterial.getId());
								scmInvMaterialRequestBillEntry.setUnit(scmMaterial.getUnitId());
								scmInvMaterialRequestBillEntry.setPieUnit(scmMaterial.getPieUnitId());
								scmInvMaterialRequestBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
								scmInvMaterialRequestBillEntry.setPrice(scmMaterial.getPrice());
								scmInvMaterialRequestBillEntry.setAmt(scmInvMaterialRequestBillEntry.getQty().multiply(scmMaterial.getPrice()));
								scmInvMaterialRequestBillEntry.setTaxPrice(scmMaterial.getTaxPrice());
								scmInvMaterialRequestBillEntry.setTaxAmt(scmInvMaterialRequestBillEntry.getQty().multiply(scmMaterial.getTaxPrice()));
								break;
							}
						}
						scmInvMaterialRequestBillEntry.setRowStatus("I");
						BigDecimal convRate = ScmMaterialUtil.getConvRate(scmInvMaterialRequestBillEntry.getItemId(), scmInvMaterialRequestBillEntry.getUnit(), param);
						scmInvMaterialRequestBillEntry.setBaseQty(scmInvMaterialRequestBillEntry.getQty().multiply(convRate));
						scmInvMaterialRequestBillEntryList.add(scmInvMaterialRequestBillEntry);
					}
				}else{
					//先删除不存在行的记录
					for(int i = scmInvMaterialRequestBillEntryList.size()-1;i>=0;i--){
						ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry2 = scmInvMaterialRequestBillEntryList.get(i);
						boolean exists = false;
						for(InvMaterialReqDetailParams detailParams:detailList){
							if(detailParams.getLineId()==scmInvMaterialRequestBillEntry2.getLineId()){
								exists = true;
								break;
							}
						}
						if(!exists)
							scmInvMaterialRequestBillEntryList.remove(i);
					}
					StringBuffer itemNos = new StringBuffer("");
					for(InvMaterialReqDetailParams detailParams:detailList){
						if(StringUtils.isBlank(detailParams.getItemNo())){
							throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemisnull");
						}
						if(StringUtils.isNotBlank(itemNos.toString()))
							itemNos.append(",");
						itemNos.append("'").append(detailParams.getItemNo()).append("'");
					}
					page = new Page();
					page.setModelClass(ScmMaterial2.class);
					page.setShowCount(Integer.MAX_VALUE);
					page.setSqlCondition("ScmMaterial.itemNo in ("+itemNos.toString()+")");
					ArrayList argList = new ArrayList();
					argList.add("orgUnitNo="+invMaterialReqEditParams.getInvOrgUnitNo());
			        argList.add("controlUnitNo=" + param.getControlUnitNo());
			        argList.add("wareHouseId=0");
			        argList.add("costCenter=0");
			        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "selectByStock", param);
					//更新时是根据行号进行更新的
					int lineId = 0;
					for(InvMaterialReqDetailParams detailParams:detailList){
						ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry = new ScmInvMaterialRequestBillEntry2(true);
						boolean exists = false;
						for(ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry2:scmInvMaterialRequestBillEntryList){
							if(detailParams.getLineId()==scmInvMaterialRequestBillEntry2.getLineId() && detailParams.getLineId()!=0){
								scmInvMaterialRequestBillEntry = scmInvMaterialRequestBillEntry2;
								exists = true;
								break;
							}
						}
						BeanUtils.copyProperties(detailParams, scmInvMaterialRequestBillEntry);
						for(ScmMaterial2 scmMaterial:scmMaterialList){
							if(StringUtils.equals(scmMaterial.getItemNo(), detailParams.getItemNo())){
								scmInvMaterialRequestBillEntry.setItemId(scmMaterial.getId());
								scmInvMaterialRequestBillEntry.setUnit(scmMaterial.getUnitId());
								scmInvMaterialRequestBillEntry.setPieUnit(scmMaterial.getPieUnitId());
								scmInvMaterialRequestBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
								scmInvMaterialRequestBillEntry.setPrice(scmMaterial.getPrice());
								scmInvMaterialRequestBillEntry.setAmt(scmInvMaterialRequestBillEntry.getQty().multiply(scmMaterial.getPrice()));
								scmInvMaterialRequestBillEntry.setTaxPrice(scmMaterial.getTaxPrice());
								scmInvMaterialRequestBillEntry.setTaxAmt(scmInvMaterialRequestBillEntry.getQty().multiply(scmMaterial.getTaxPrice()));
								break;
							}
						}
						BigDecimal convRate = ScmMaterialUtil.getConvRate(scmInvMaterialRequestBillEntry.getItemId(), scmInvMaterialRequestBillEntry.getUnit(), param);
						scmInvMaterialRequestBillEntry.setBaseQty(scmInvMaterialRequestBillEntry.getQty().multiply(convRate));
						scmInvMaterialRequestBillEntry.setRowStatus("I");
						if(!exists)
							scmInvMaterialRequestBillEntryList.add(lineId, scmInvMaterialRequestBillEntry);
						lineId = lineId +1;
					}
				}
				//更新子表数据
				bean.setList2(scmInvMaterialRequestBillEntryList);
				this.update(bean, param);
			}else{
				throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
			}
		}else{
			throw new AppException("webservice.params.null");
		}
	}
	@Override
	protected void beforeDelete(ScmInvMaterialRequestBill2 entity, Param param)
			throws AppException {
		ScmInvMaterialRequestBill2 scmInvMaterialRequestBill = this.selectDirect(entity.getReqId(), param);
		if(scmInvMaterialRequestBill!=null && !StringUtils.equals(scmInvMaterialRequestBill.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getReqNo()}));
		}
	}
	
	@Override
	public void generateMaterialReqBill(
			ScmInvMaterialRequestBill2 scmInvMaterialRequestBill, Param param)
			throws AppException {
		List<ScmInvMaterialRequestBillEntry2> scmInvMaterialRequestBillEntryList = scmInvMaterialRequestBillEntryBiz.selectByReqId(scmInvMaterialRequestBill.getReqId(), param);
		if (scmInvMaterialRequestBillEntryList == null || scmInvMaterialRequestBillEntryList.isEmpty()) {
            throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
        }
		CommonBean bean = new CommonBean();
        List<ScmInvMaterialReqBill2> scmInvMaterialReqBillList = new ArrayList<>();
        List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = new ArrayList<>();
        ScmInvMaterialReqBill2 scmInvMaterialReqBill = new ScmInvMaterialReqBill2(true);
        
        scmInvMaterialReqBill.setBizType("1");
        Date billDate = FormatUtils.parseDate(FormatUtils.fmtDate(CalendarUtil.getDate(param)));
        PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(billDate, param);
		if(periodCalendar==null){
			throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
		}
		SystemState systemState = systemStateBiz.selectBySystemId(scmInvMaterialRequestBill.getFinOrgUnitNo(), 170, param);
	    if(systemState==null){
	    	throw new AppException(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
        }
	    if (systemState.getCurrentPeriodId() <= periodCalendar.getPeriodId()) {
	    	billDate = FormatUtils.parseDate(FormatUtils.fmtDate(CalendarUtil.getDate(param)));
        }else {
        	PeriodCalendar pCalendar = periodCalendarBiz.select(systemState.getCurrentPeriodId(), param);
        	billDate = FormatUtils.parseDate(FormatUtils.fmtDateTime(pCalendar.getStartDate(),"yyyy-MM")+"-01");
        }
        scmInvMaterialReqBill.setBizDate(billDate);
        scmInvMaterialReqBill.setFinOrgUnitNo(scmInvMaterialRequestBill.getFinOrgUnitNo());
        scmInvMaterialReqBill.setOrgUnitNo(scmInvMaterialRequestBill.getInvOrgUnitNo());
        scmInvMaterialReqBill.setWareHouseId(scmInvMaterialRequestBill.getWareHouseId());
        scmInvMaterialReqBill.setUseOrgUnitNo(scmInvMaterialRequestBill.getOrgUnitNo());
        scmInvMaterialReqBill.setUsePerson(scmInvMaterialRequestBill.getRequestPerson());
        //获取领料部门的成本中心
		 List<OrgCostCenter2> outOrgCostCenterList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOCOST, 
        		scmInvMaterialRequestBill.getOrgUnitNo(), false, null, param);
        if (outOrgCostCenterList == null || outOrgCostCenterList.isEmpty()) {
            throw new AppException("iscm.inventorymanage.warehouseoutbusiness.service.impl.ScmInvMaterialRequestBillBizImpl.noOutCst");
        }
        
        scmInvMaterialReqBill.setCostOrgUnitNo(outOrgCostCenterList.get(0).getOrgUnitNo());
        scmInvMaterialReqBill.setBillType("InvMatReqBill");
        scmInvMaterialReqBill.setCurrencyNo(scmInvMaterialRequestBill.getCurrencyNo());
        scmInvMaterialReqBill.setExchangeRate(scmInvMaterialRequestBill.getExchangeRate());
        scmInvMaterialReqBill.setCreator(param.getUsrCode());
        scmInvMaterialReqBill.setCreateDate(CalendarUtil.getDate(param));
        scmInvMaterialReqBill.setPrtcount(0);
        scmInvMaterialReqBill.setStatus("I");
        scmInvMaterialReqBill.setRemarks(scmInvMaterialRequestBill.getRemarks());
        
        //领料出库主表
        scmInvMaterialReqBillList.add(scmInvMaterialReqBill);
        bean.setList(scmInvMaterialReqBillList);
        String zeroQty = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_GenerateMaterialReqZeroQty", "N", param);
        
        //领料出库明细表
        for(int i = 0; i < scmInvMaterialRequestBillEntryList.size(); i++) {
        	ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry = new ScmInvMaterialRequestBillEntry2();
        	scmInvMaterialRequestBillEntry = scmInvMaterialRequestBillEntryList.get(i);
        	if(StringUtils.equals("N", scmInvMaterialRequestBillEntry.getRowStatus()))
        		continue;
        	ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry = new ScmInvMaterialReqBillEntry2();
        	scmInvMaterialReqBillEntry.setLineId(i+1);
        	scmInvMaterialReqBillEntry.setItemId(scmInvMaterialRequestBillEntry.getItemId());
        	scmInvMaterialReqBillEntry.setUnit(scmInvMaterialRequestBillEntry.getUnit());
        	scmInvMaterialReqBillEntry.setPieUnit(scmInvMaterialRequestBillEntry.getPieUnit());
        	scmInvMaterialReqBillEntry.setCostUseTypeId(scmInvMaterialRequestBillEntry.getCostUseTypeId());
        	scmInvMaterialReqBillEntry.setBaseUnit(scmInvMaterialRequestBillEntry.getBaseUnit());
        	scmInvMaterialReqBillEntry.setReqQty(scmInvMaterialRequestBillEntry.getQty());
        	if(StringUtils.equals("Y", zeroQty)) {
        		scmInvMaterialReqBillEntry.setQty(BigDecimal.ZERO);
            	scmInvMaterialReqBillEntry.setAmt(BigDecimal.ZERO);
            	scmInvMaterialReqBillEntry.setTaxAmt(BigDecimal.ZERO);
        	}else {
        		scmInvMaterialReqBillEntry.setQty(scmInvMaterialRequestBillEntry.getQty());
            	scmInvMaterialReqBillEntry.setAmt(scmInvMaterialRequestBillEntry.getAmt());
            	scmInvMaterialReqBillEntry.setTaxAmt(scmInvMaterialRequestBillEntry.getTaxAmt());
        	}
        	scmInvMaterialReqBillEntry.setPieQty(scmInvMaterialRequestBillEntry.getPieQty());
        	scmInvMaterialReqBillEntry.setBaseQty(scmInvMaterialRequestBillEntry.getBaseQty());
        	scmInvMaterialReqBillEntry.setPrice(scmInvMaterialRequestBillEntry.getPrice());
        	scmInvMaterialReqBillEntry.setTaxRate(scmInvMaterialRequestBillEntry.getTaxRate());
        	scmInvMaterialReqBillEntry.setTaxPrice(scmInvMaterialRequestBillEntry.getTaxPrice());
        	scmInvMaterialReqBillEntry.setSourceBillDtlId(scmInvMaterialRequestBillEntry.getId());
        	scmInvMaterialReqBillEntry.setRemarks(scmInvMaterialRequestBillEntry.getRemarks());
        	scmInvMaterialReqBillEntryList.add(scmInvMaterialReqBillEntry);
        }
        
        bean.setList2(scmInvMaterialReqBillEntryList);
        scmInvMaterialReqBillBiz.add(bean, param);
	}
	
	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvMaterialRequestBill2 entry = (ScmInvMaterialRequestBill2) bean.getList().get(0);
		if(entry!=null){
			ScmInvMaterialRequestBill2 scmInvMaterialRequestBill = this.select(entry.getPK(), param);
			if(!StringUtils.contains("I,D,P", scmInvMaterialRequestBill.getStatus())){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
		}
	}
	
	@Override
	public ScmInvMaterialRequestBill2 doAuditInvMaterialReq(
			CommonAuditParams commonAuditParams, Param param)
			throws AppException {
		ScmInvMaterialRequestBill2 scmInvMaterialRequestBill = new ScmInvMaterialRequestBill2();
		scmInvMaterialRequestBill.setReqId(commonAuditParams.getBillId());
		scmInvMaterialRequestBill.setReqNo(commonAuditParams.getBillNo());
		String opinion = commonAuditParams.getOpinion();
		if(StringUtils.equals("Y", opinion) || StringUtils.equals("agree", opinion)) {
			opinion="agree";
		}else if(StringUtils.equals("N", opinion) || StringUtils.equals("refuse", opinion)) {
			opinion="refuse";
		}
		List<ScmInvMaterialRequestBill2> scmInvMaterialRequestBillList = new ArrayList<> ();
		List<CommonAuditDetailParams> detailList = commonAuditParams.getDetailList();
		List<ScmInvMaterialRequestBillEntry2> scmInvMaterialRequestBillEntryList = new ArrayList<> ();
		int countRefuse = 0;
		
		if(scmInvMaterialRequestBill.getReqId()>0){
			scmInvMaterialRequestBill = this.selectDirect(scmInvMaterialRequestBill.getReqId(), param);
			scmInvMaterialRequestBillList.add(scmInvMaterialRequestBill);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmInvMaterialRequestBill2.FN_REQNO,
					new QueryParam(ScmInvMaterialRequestBill2.FN_REQNO, QueryParam.QUERY_EQ,
							scmInvMaterialRequestBill.getReqNo()));
			map.put(ScmInvMaterialRequestBill2.FN_CONTROLUNITNO, new QueryParam(ScmInvMaterialRequestBill2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
							param.getControlUnitNo()));
			scmInvMaterialRequestBillList =this.findAll(map, param);
			if(scmInvMaterialRequestBillList!=null && !scmInvMaterialRequestBillList.isEmpty()){
				scmInvMaterialRequestBill=scmInvMaterialRequestBillList.get(0);
			}
		}
		
		if (scmInvMaterialRequestBill != null) {
			this.setConvertMap(scmInvMaterialRequestBill, param);
			scmInvMaterialRequestBillEntryList = scmInvMaterialRequestBillEntryBiz.selectByReqId(scmInvMaterialRequestBill.getReqId(), param);
			
			if (scmInvMaterialRequestBillEntryList == null || scmInvMaterialRequestBillEntryList.isEmpty()) {
				//抛出异常处理
				throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
			}
			if(StringUtils.equals("R", opinion)) {
				scmBillPendingBiz.deletePendingBill(param.getUsrCode(),scmInvMaterialRequestBill, param);
				commonEventHistoryBiz.updateInvalid(scmInvMaterialRequestBill,"",param);
				
				CommonAuditOpinion commonAuditOpinionR = new CommonAuditOpinion();
				commonAuditOpinionR.setPreStepNo(scmInvMaterialRequestBill.getStepNo());
				String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
				commonAuditOpinionR.setStepNo(stepNo);
				commonAuditOpinionR.setActiveType("A");
				commonAuditOpinionR.setOpinion(commonAuditParams.getOpinion());
				commonAuditOpinionR.setRemarks(commonAuditParams.getOpinionRemarks());
				commonEventHistoryBiz.addEventHistory(scmInvMaterialRequestBill, commonAuditOpinionR, param);
				//记录行审批记录
				if(detailList!=null && !detailList.isEmpty()) {
					List<ScmAuditDetailHistory> scmAuditDetailHistoryList = new ArrayList<>();
					for(CommonAuditDetailParams commonAuditDetailParams : detailList) {
						String rowOpinion = commonAuditDetailParams.getRowOpinion();
						if(StringUtils.equals("Y", rowOpinion) || StringUtils.equals("agree", rowOpinion)) {
							rowOpinion="Y";
						}else if(StringUtils.equals("N", rowOpinion) || StringUtils.equals("refuse", rowOpinion)) {
							rowOpinion="N";
						}
						int lineId = commonAuditDetailParams.getLineId();
						String rowAuditRemarks = commonAuditDetailParams.getRemarks();
						if(StringUtils.isNotBlank(rowAuditRemarks)){
							rowAuditRemarks = rowAuditRemarks.replace(",", "，");
						}
						for (ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry : scmInvMaterialRequestBillEntryList) {
							if (lineId == scmInvMaterialRequestBillEntry.getLineId()) {
								ScmAuditDetailHistory scmAuditDetailHistory = new ScmAuditDetailHistory(true);
								scmAuditDetailHistory.setBillId(scmInvMaterialRequestBillEntry.getReqId());
								scmAuditDetailHistory.setBillType("InvMatReqBill");
								scmAuditDetailHistory.setLineId(lineId);
								scmAuditDetailHistory.setOpinion(rowOpinion);
								scmAuditDetailHistory.setOper(param.getUsrCode());
								scmAuditDetailHistory.setOperDate(CalendarUtil.getDate(param));
								scmAuditDetailHistory.setRemarks(rowAuditRemarks);
								scmAuditDetailHistoryList.add(scmAuditDetailHistory);
							}
						}
					}
					if(scmAuditDetailHistoryList!=null && !scmAuditDetailHistoryList.isEmpty()){
						scmAuditDetailHistoryBiz.add(scmAuditDetailHistoryList, param);
					}
				}
				
				//驳回则将单据变回新单状态
				scmInvMaterialRequestBill.setStatus("I");
				scmInvMaterialRequestBill.setChecker(null);
				scmInvMaterialRequestBill.setCheckDate(null);
				this.updateDirect(scmInvMaterialRequestBill, param);
				if(scmInvMaterialRequestBillEntryList!=null && !scmInvMaterialRequestBillEntryList.isEmpty()) {
					for (ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry:scmInvMaterialRequestBillEntryList){
						scmInvMaterialRequestBillEntry.setRowStatus("I");
						scmInvMaterialRequestBillEntryBiz.updateDirect(scmInvMaterialRequestBillEntry, param);
					}
				}
				return  scmInvMaterialRequestBill;
			}
			String processInstanceId = "";
			boolean isCompleteTask = true;
			if (scmInvMaterialRequestBill != null) {
				processInstanceId = scmInvMaterialRequestBill.getWorkFlowNo();
			}
			
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
			List<CommonBillEntryStatus> commonBillEntryStatusList = new ArrayList();
			
			//I：新建，D：待审，P：审核中，A：通过，B：部分通过，N：未通过，S：部分下达，E：下达，F：部分关闭，C：关闭，多个状态如：I,D,A
			if (scmInvMaterialRequestBillEntryList != null && scmInvMaterialRequestBillEntryList.size() > 0) {
				if ("agree".equals(opinion)) {
					if (isCompleteTask) {
						//A通过，B部分通过，N未通过
						if(detailList==null || detailList.isEmpty()) {
							//未传入明细则认为符合条件的明细都同意
							for (ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry : scmInvMaterialRequestBillEntryList) {
								CommonBillEntryStatus commonBillEntryStatus = new CommonBillEntryStatus();
								commonBillEntryStatus.setLineId(scmInvMaterialRequestBillEntry.getLineId());
								commonBillEntryStatus.setRowStatus(scmInvMaterialRequestBillEntry.getRowStatus());
								commonBillEntryStatusList.add(commonBillEntryStatus);
								if(!StringUtils.equals(scmInvMaterialRequestBillEntry.getRowStatus(), "N")) {
									scmInvMaterialRequestBillEntry.setRowStatus("A");
								}else {
									countRefuse++;
								}
							}
							if (countRefuse == 0) {
								scmInvMaterialRequestBill.setStatus("A");
							} else if (countRefuse > 0 && countRefuse < scmInvMaterialRequestBillEntryList.size()) {
								scmInvMaterialRequestBill.setStatus("B");
							} else {
								scmInvMaterialRequestBill.setStatus("N");
							}
						}else{
							for (CommonAuditDetailParams commonAuditDetailParams : detailList) {
								String rowOpinion = commonAuditDetailParams.getRowOpinion();
								int lineId = commonAuditDetailParams.getLineId();
								for (ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry : scmInvMaterialRequestBillEntryList) {
									if (lineId == scmInvMaterialRequestBillEntry.getLineId()) {
										CommonBillEntryStatus commonBillEntryStatus = new CommonBillEntryStatus();
										commonBillEntryStatus.setLineId(scmInvMaterialRequestBillEntry.getLineId());
										commonBillEntryStatus.setRowStatus(scmInvMaterialRequestBillEntry.getRowStatus());
										commonBillEntryStatusList.add(commonBillEntryStatus);
										if (!StringUtils.equals(scmInvMaterialRequestBillEntry.getRowStatus(), "N") &&
												(StringUtils.equals("agree",rowOpinion) || StringUtils.equals("Y",rowOpinion))) {
											scmInvMaterialRequestBillEntry.setRowStatus("A");
										} else {
											scmInvMaterialRequestBillEntry.setRowStatus("N");
											countRefuse++;
										}
										break;
									}
								}
							}
							if (countRefuse == 0) {
								scmInvMaterialRequestBill.setStatus("A");
							} else if (countRefuse > 0 && countRefuse < scmInvMaterialRequestBillEntryList.size()) {
								scmInvMaterialRequestBill.setStatus("B");
							} else {
								scmInvMaterialRequestBill.setStatus("N");
							}
						}
					} else {
						//P：审核中，N：未通过
						if(detailList==null || detailList.isEmpty()) {
							//未传入明细则认为符合条件的明细都同意
							for (ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry : scmInvMaterialRequestBillEntryList) {
								CommonBillEntryStatus commonBillEntryStatus = new CommonBillEntryStatus();
								commonBillEntryStatus.setLineId(scmInvMaterialRequestBillEntry.getLineId());
								commonBillEntryStatus.setRowStatus(scmInvMaterialRequestBillEntry.getRowStatus());
								commonBillEntryStatusList.add(commonBillEntryStatus);
								if(!StringUtils.equals(scmInvMaterialRequestBillEntry.getRowStatus(), "N")) {
									scmInvMaterialRequestBillEntry.setRowStatus("P");
								}else {
									countRefuse++;
								}
							}
							if (countRefuse == scmInvMaterialRequestBillEntryList.size()) {
								scmInvMaterialRequestBill.setStatus("N");
							} else {
								scmInvMaterialRequestBill.setStatus("P");
							}	
						}else{
							for(CommonAuditDetailParams commonAuditDetailParams : detailList) {
								String rowOpinion = commonAuditDetailParams.getRowOpinion();
								int lineId = commonAuditDetailParams.getLineId();
								for (ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry : scmInvMaterialRequestBillEntryList) {
									if (lineId == scmInvMaterialRequestBillEntry.getLineId()) {
										CommonBillEntryStatus commonBillEntryStatus = new CommonBillEntryStatus();
										commonBillEntryStatus.setLineId(scmInvMaterialRequestBillEntry.getLineId());
										commonBillEntryStatus.setRowStatus(scmInvMaterialRequestBillEntry.getRowStatus());
										commonBillEntryStatusList.add(commonBillEntryStatus);
										if (!StringUtils.equals(scmInvMaterialRequestBillEntry.getRowStatus(), "N") &&
												(StringUtils.equals("agree",rowOpinion) || StringUtils.equals("Y",rowOpinion))) {
											scmInvMaterialRequestBillEntry.setRowStatus("P");
										} else {
											scmInvMaterialRequestBillEntry.setRowStatus("N");
											countRefuse++;
										}
										break;
									}
								}
								
								if (countRefuse == scmInvMaterialRequestBillEntryList.size()) {
									scmInvMaterialRequestBill.setStatus("N");
								} else {
									scmInvMaterialRequestBill.setStatus("P");
								}	
							}
						}
					}
				} else {
					for (ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry : scmInvMaterialRequestBillEntryList) {
						CommonBillEntryStatus commonBillEntryStatus = new CommonBillEntryStatus();
						commonBillEntryStatus.setLineId(scmInvMaterialRequestBillEntry.getLineId());
						commonBillEntryStatus.setRowStatus(scmInvMaterialRequestBillEntry.getRowStatus());
						commonBillEntryStatusList.add(commonBillEntryStatus);
						scmInvMaterialRequestBillEntry.setRowStatus("N");
					}
					scmInvMaterialRequestBill.setStatus("N");
				}	
			}
			scmBillPendingBiz.updatePendingBill(param.getUsrCode(), scmInvMaterialRequestBill, param);
			scmInvMaterialRequestBill.setChecker(param.getUsrCode());
			if ("agree".equals(opinion)) {
				String usrList= ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId,param);
				if(StringUtils.isNotBlank(usrList)) {
					scmBillPendingBiz.addPendingBill(usrList, scmInvMaterialRequestBill, param);
					InvMaterialReqParams invMaterialReqParams = new InvMaterialReqParams();
					invMaterialReqParams.setReqNo(scmInvMaterialRequestBill.getReqNo());
					AuditMsgUtil.sendAuditMsg("InvMatReqBill",scmInvMaterialRequestBill,invMaterialReqParams, usrList, param);
				}
			}
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setPreStepNo(scmInvMaterialRequestBill.getStepNo());
			String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
			try {
				scmInvMaterialRequestBill.setStepNo(stepNo);
				scmInvMaterialRequestBill.setCheckDate(CalendarUtil.getDate(param));
				this.updateDirect(scmInvMaterialRequestBill, param);
			} catch (Exception e) {
				throw e;
			}
			
			for (ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry : scmInvMaterialRequestBillEntryList) {
				long prId = scmInvMaterialRequestBillEntry.getReqId();
				String status = scmInvMaterialRequestBillEntry.getRowStatus();
				String checker = param.getUsrCode();
				Date checkDate = CalendarUtil.getDate(param);
				int lineId = scmInvMaterialRequestBillEntry.getLineId();
				scmInvMaterialRequestBillEntryBiz.updateRowStatusByLineId(prId, status, checker, checkDate, lineId, param);
			}
			commonAuditOpinion.setStepNo(stepNo);
			commonAuditOpinion.setActiveType("A");
			commonAuditOpinion.setOpinion(commonAuditParams.getOpinion());
			commonAuditOpinion.setHandlerContent(gson.toJson(commonBillEntryStatusList));
			commonAuditOpinion.setRemarks(commonAuditParams.getOpinionRemarks());
			commonEventHistoryBiz.addEventHistory(scmInvMaterialRequestBill, commonAuditOpinion, param);
			//记录行审批记录
			if(detailList!=null && !detailList.isEmpty()) {
				List<ScmAuditDetailHistory> scmAuditDetailHistoryList = new ArrayList<>();
				for(CommonAuditDetailParams commonAuditDetailParams : detailList) {
					String rowOpinion = commonAuditDetailParams.getRowOpinion();
					if(StringUtils.equals("Y", rowOpinion) || StringUtils.equals("agree", rowOpinion)) {
						rowOpinion="Y";
					}else if(StringUtils.equals("N", rowOpinion) || StringUtils.equals("refuse", rowOpinion)) {
						rowOpinion="N";
					}
					int lineId = commonAuditDetailParams.getLineId();
					String rowAuditRemarks = commonAuditDetailParams.getRemarks();
					if(StringUtils.isNotBlank(rowAuditRemarks)){
						rowAuditRemarks = rowAuditRemarks.replace(",", "，");
					}
					for (ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry : scmInvMaterialRequestBillEntryList) {
						if (lineId == scmInvMaterialRequestBillEntry.getLineId()) {
							ScmAuditDetailHistory scmAuditDetailHistory = new ScmAuditDetailHistory(true);
							scmAuditDetailHistory.setBillId(scmInvMaterialRequestBillEntry.getReqId());
							scmAuditDetailHistory.setBillType("InvMatReqBill");
							scmAuditDetailHistory.setLineId(lineId);
							scmAuditDetailHistory.setOpinion(rowOpinion);
							scmAuditDetailHistory.setOper(param.getUsrCode());
							scmAuditDetailHistory.setOperDate(CalendarUtil.getDate(param));
							scmAuditDetailHistory.setRemarks(rowAuditRemarks);
							scmAuditDetailHistoryList.add(scmAuditDetailHistory);
						}
					}
				}
				if(scmAuditDetailHistoryList!=null && !scmAuditDetailHistoryList.isEmpty()){
					scmAuditDetailHistoryBiz.add(scmAuditDetailHistoryList, param);
				}
			}
			if(StringUtils.contains("A,B", scmInvMaterialRequestBill.getStatus())) {
				//通过或部分通过时检查是否自动下达
				BillType2 billType = billTypeBiz.selectByOrgAndCode(scmInvMaterialRequestBill.getFinOrgUnitNo(), "InvMatReqBill", param);
				if(billType!=null && billType.isAutoRelease()) {
					this.release(scmInvMaterialRequestBill, param);
				}
			}
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return scmInvMaterialRequestBill;
	}
	@Override
	public ScmInvMaterialRequestBill2 doUnAuditInvMaterialReq(
			ScmInvMaterialRequestBill2 scmInvMaterialRequestBill, Param param)
			throws AppException {
		
		ScmInvMaterialRequestBill2 scmInvMaterialReq = null;
		List<ScmInvMaterialRequestBill2> scmInvMaterialReqList = new ArrayList<> ();
		List<ScmInvMaterialRequestBillEntry2> scmInvMaterialReqEntryList = new ArrayList<> ();
		boolean isFirstTask = false;
		
		if(scmInvMaterialRequestBill.getReqId()>0){
			scmInvMaterialReq = this.selectDirect(scmInvMaterialRequestBill.getReqId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmInvMaterialRequestBill2.FN_REQNO,
					new QueryParam(ScmInvMaterialRequestBill2.FN_REQNO, QueryParam.QUERY_EQ,
							scmInvMaterialRequestBill.getReqNo()));
			map.put(ScmInvMaterialRequestBill2.FN_CONTROLUNITNO, new QueryParam(ScmInvMaterialRequestBill2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
					param.getControlUnitNo()));
			
			scmInvMaterialReqList =this.findAll(map, param);
			if(scmInvMaterialReqList!=null && !scmInvMaterialReqList.isEmpty()){
				scmInvMaterialReq=scmInvMaterialReqList.get(0);
			}
		}
		
		if (scmInvMaterialReq != null) {
			if(!StringUtils.contains("A,B,N,P", scmInvMaterialReq.getStatus())){
				throw new AppException("iscm.purchasemanage.unaudit.error.status");
			}
			String processInstanceId = scmInvMaterialReq.getWorkFlowNo();
			scmInvMaterialReqEntryList = scmInvMaterialRequestBillEntryBiz.selectByReqId(scmInvMaterialReq.getReqId(), param);
			if(scmInvMaterialReqEntryList == null || scmInvMaterialReqEntryList.isEmpty()){
				//抛出异常处理
				throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
			}

			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//反审批当前已审节点
				CommonBean bean = new CommonBean();
				scmInvMaterialReqList.add(scmInvMaterialReq);
				bean.setList(scmInvMaterialReqList);
				bean.setList2(scmInvMaterialReqEntryList);
				
				BillType2 billType = billTypeBiz.selectByOrgAndCode(scmInvMaterialReq.getFinOrgUnitNo(), "InvMatReqBill", param);
				long billTypeId = 0;
				if (billType!=null && billType.isNeedAudit()) {
					billTypeId = billType.getId();
				}
				
				processInstanceId = ActivityUtil.antiAuditProcess(bean, billTypeId, processInstanceId, param.getUsrCode(),  param);
				isFirstTask = ActivityUtil.isFirstTask(processInstanceId, param.getUsrCode(),  param);
				scmInvMaterialReq.setWorkFlowNo(processInstanceId);
			} else {
				//流程编号不存在
				throw new AppException("com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.doAuditPurRequire.processInstanceId");
			}
			
			String tableName = ClassUtils.getFinalModelSimpleName(scmInvMaterialReq);
			CommonEventHistory commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,scmInvMaterialReq.getStepNo(),scmInvMaterialReq.getPK(),param);
			if(commonEventHistory!=null && StringUtils.isNotBlank(commonEventHistory.getHandlerContent())) {
				List<CommonBillEntryStatus> commonBillEntryStatusList = gson.fromJson(commonEventHistory.getHandlerContent(),new TypeToken<List<CommonBillEntryStatus>>(){}.getType());
				if(commonBillEntryStatusList!=null && !commonBillEntryStatusList.isEmpty()) {
					for (ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry : scmInvMaterialReqEntryList) {
						for(CommonBillEntryStatus commonBillEntryStatus:commonBillEntryStatusList) {
							if(commonBillEntryStatus.getLineId()==scmInvMaterialRequestBillEntry.getLineId()) {
								scmInvMaterialRequestBillEntry.setRowStatus(commonBillEntryStatus.getRowStatus());
								commonBillEntryStatusList.remove(commonBillEntryStatus);
								break;
							}
						}
					}
				}
				if(StringUtils.isNotBlank(commonEventHistory.getPreStepNo())){
					CommonEventHistory preCommonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,scmInvMaterialReq.getStepNo(),scmInvMaterialReq.getPK(),param);
					if(preCommonEventHistory!=null) {
						scmInvMaterialReq.setChecker(preCommonEventHistory.getOper());
						scmInvMaterialReq.setCheckDate(preCommonEventHistory.getOperDate());
					}else {
						scmInvMaterialReq.setChecker(null);
						scmInvMaterialReq.setCheckDate(null);
					}
				}else {
					scmInvMaterialReq.setChecker(null);
					scmInvMaterialReq.setCheckDate(null);
				}
			}
			String status = "";
			if (isFirstTask) {
				status = "D";
			} else {
				status = "P";
			}
			scmInvMaterialReq.setStatus(status);
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		
		try {
			scmInvMaterialReq.setChecker(null);
			scmInvMaterialReq.setCheckDate(null);
			this.updateDirect(scmInvMaterialReq, param);
			scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),scmInvMaterialReq, param);
			commonEventHistoryBiz.updateInvalid(scmInvMaterialReq,scmInvMaterialReq.getStepNo(),param);
		} catch (Exception e) {
			throw e;
		}
		
		for (ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry : scmInvMaterialReqEntryList) {
			long prId = scmInvMaterialRequestBillEntry.getReqId();
			String status = scmInvMaterialRequestBillEntry.getRowStatus();
			String checker = param.getUsrCode();
			Date checkDate = CalendarUtil.getDate(param);
			int lineId = scmInvMaterialRequestBillEntry.getLineId();
			scmInvMaterialRequestBillEntryBiz.updateRowStatusByLineId(prId, status, checker, checkDate, lineId, param);
		}
		
		CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
		commonAuditOpinion.setStepNo(scmInvMaterialReq.getStepNo());
		commonAuditOpinion.setActiveType("U");
		commonAuditOpinion.setOpinion("Y");
		commonEventHistoryBiz.addEventHistory(scmInvMaterialReq, commonAuditOpinion, param);
		return scmInvMaterialReq;
	}
	@Override
	public ScmInvMaterialRequestBill2 updatePrtCount(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill, Param param)
			throws AppException {
		if(scmInvMaterialRequestBill.getReqId()>0){
			ScmInvMaterialRequestBill2 materialRequest = this.selectDirect(scmInvMaterialRequestBill.getReqId(), param);
			if(materialRequest != null){
				int prtCount = materialRequest.getPrtcount();
				materialRequest.setPrtcount((prtCount+1));
				this.updateDirect(materialRequest, param);
				return materialRequest;
			}
		}
		return scmInvMaterialRequestBill;
	}
	
	@Override
	public List<ScmInvMaterialDrillResult> selectDrillBills(ScmInvMaterialRequestBill2 scmInvMaterialRequestBille,
			Param param) throws AppException {
		if(scmInvMaterialRequestBille != null){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("reqId", scmInvMaterialRequestBille.getReqId());
			return ((ScmInvMaterialRequestBillDAO)dao).selectDrillBills(map);
		}
		return null;
	}
	
}

