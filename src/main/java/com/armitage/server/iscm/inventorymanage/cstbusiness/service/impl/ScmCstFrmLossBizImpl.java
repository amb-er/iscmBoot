package com.armitage.server.iscm.inventorymanage.cstbusiness.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.api.business.cstfrmloss.params.CstFrmLossParams;
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
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.ListSortUtil;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.common.model.CommonAuditOpinion;
import com.armitage.server.iscm.common.model.CommonEventHistory;
import com.armitage.server.iscm.common.service.CommonEventHistoryBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmCstFrmLossDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLoss2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLossCostQuery;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLossEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmCstFrmLossBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmCstFrmLossEntryBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvBalBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmBaseAttachmentBiz;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.model.SystemState;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgCostCenterBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.service.SystemStateBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmCstFrmLossBiz")
public class ScmCstFrmLossBizImpl extends BaseBizImpl<ScmCstFrmLoss2> implements ScmCstFrmLossBiz {
	private UsrBiz usrBiz;
    private OrgUnitBiz orgUnitBiz;
	private OrgCostCenterBiz orgCostCenterBiz;
	private PeriodCalendarBiz periodCalendarBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private ScmCstFrmLossEntryBiz scmCstFrmLossEntryBiz;
	private SystemStateBiz systemStateBiz;
	private ScmInvStockBiz scmInvStockBiz;
	private ScmInvBalBiz scmInvBalBiz;
	private SysParamBiz sysParamBiz;
	private BillTypeBiz billTypeBiz;
	private ScmBillPendingBiz scmBillPendingBiz;
	private CommonEventHistoryBiz commonEventHistoryBiz;
	private CodeBiz codeBiz;
	private ScmBaseAttachmentBiz scmBaseAttachmentBiz;

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}
	
	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}
	
	public void setOrgCostCenterBiz(OrgCostCenterBiz orgCostCenterBiz) {
        this.orgCostCenterBiz = orgCostCenterBiz;
    }
	
	public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}
	
	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}
	
	public void setScmCstFrmLossEntryBiz(ScmCstFrmLossEntryBiz scmCstFrmLossEntryBiz) {
		this.scmCstFrmLossEntryBiz = scmCstFrmLossEntryBiz;
	}
	
	public void setSystemStateBiz(SystemStateBiz systemStateBiz) {
		this.systemStateBiz = systemStateBiz;
	}
	
	public void setScmInvStockBiz(ScmInvStockBiz scmInvStockBiz) {
		this.scmInvStockBiz = scmInvStockBiz;
	}
	
	public void setScmInvBalBiz(ScmInvBalBiz scmInvBalBiz) {
		this.scmInvBalBiz = scmInvBalBiz;
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
	
	public void setCommonEventHistoryBiz(CommonEventHistoryBiz commonEventHistoryBiz) {
		this.commonEventHistoryBiz = commonEventHistoryBiz;
	}
	
	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}
	
	public void setScmBaseAttachmentBiz(ScmBaseAttachmentBiz scmBaseAttachmentBiz) {
		this.scmBaseAttachmentBiz = scmBaseAttachmentBiz;
	}

	@Override
   	protected void beforeFindPage(Page page, Param param) throws AppException {
    	List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(param.getOrgUnitNo(), param);
		if (orgCostCenterList != null && !orgCostCenterList.isEmpty()) {
			StringBuffer orgunitList = new StringBuffer("");
			for (OrgCostCenter2 orgCostCenter : orgCostCenterList) {
				if(StringUtils.isNotBlank(orgunitList.toString()))
					orgunitList.append(",");
				orgunitList.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss2.class) + "." + ScmCstFrmLoss2.FN_ORGUNITNO), 
	   				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss2.class) + "." + ScmCstFrmLoss2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss2.class) + "." + ScmCstFrmLoss2.FN_ORGUNITNO), 
	   				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss2.class) + "." + ScmCstFrmLoss2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
   	}
	
	@Override
    protected void afterFindPage(List list, Page page, Param param) throws AppException {
        HashMap<String,Object> cacheMap = new HashMap<String,Object>();
        List<ScmCstFrmLoss2> scmCstFrmLossList = list;
        if(scmCstFrmLossList != null && !scmCstFrmLossList.isEmpty()){
            for(ScmCstFrmLoss2 scmCstFrmLoss : scmCstFrmLossList){
            	setConvertMap(scmCstFrmLoss, param);
            }
        }
    }
	
	@Override
    protected void beforeAdd(ScmCstFrmLoss2 entity, Param param) throws AppException {
        if(entity != null){
            // 创建报损单号
        	String code = CodeAutoCalUtil.getBillCode("CstFrmLoss", entity, param);
			entity.setBillNo(code);
            //获取期间
			PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(entity.getBizDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
			}
			entity.setPeriodId(periodCalendar.getPeriodId());
			entity.setAccountYear(periodCalendar.getAccountYear());
			entity.setAccountPeriod(periodCalendar.getAccountPeriod());
        }
    }
	
	@Override
    protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmCstFrmLoss2 scmCstFrmLoss = (ScmCstFrmLoss2) bean.getList().get(0);
        if(scmCstFrmLoss != null && scmCstFrmLoss.getId() > 0){
            //新增报损明细
            List<ScmCstFrmLossEntry2> scmCstFrmLossEntryList = bean.getList2();
            if(scmCstFrmLossEntryList != null && !scmCstFrmLossEntryList.isEmpty()){
				int lineId = 1;
                for(ScmCstFrmLossEntry2 scmCstFrmLossEntry : scmCstFrmLossEntryList){
                	scmCstFrmLossEntry.setLineId(lineId);
                    scmCstFrmLossEntry.setBillId(scmCstFrmLoss.getId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmCstFrmLossEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmCstFrmLossEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmCstFrmLossEntry.getItemId(), scmCstFrmLossEntry.getUnit(), param);//库存单位转换系数
					scmCstFrmLossEntry.setBaseQty(scmCstFrmLossEntry.getQty().multiply(invConvRate));
					scmCstFrmLossEntryBiz.add(scmCstFrmLossEntry, param);
					lineId = lineId+1;
                }
            }
        }
    }
	
	@Override
    protected void afterSelect(ScmCstFrmLoss2 entity, Param param) throws AppException {
		setConvertMap(entity, param);
	}
	
	@Override
    protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmCstFrmLoss2 scmCstFrmLoss = (ScmCstFrmLoss2) bean.getList().get(0);
        if(scmCstFrmLoss != null && scmCstFrmLoss.getId() > 0){
            HashMap<String,Object> cacheMap = new HashMap<String,Object>();
            bean.setList2(scmCstFrmLossEntryBiz.selectByBillId(scmCstFrmLoss.getId(), param));
        }
    }
	
	@Override
	protected void beforeUpdate(ScmCstFrmLoss2 oldEntity,ScmCstFrmLoss2 newEntity, Param param) throws AppException {
    	//获取期间
		PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(newEntity.getBizDate(), param);
		if(periodCalendar==null){
			throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
		}
		newEntity.setPeriodId(periodCalendar.getPeriodId());
		newEntity.setAccountYear(periodCalendar.getAccountYear());
		newEntity.setAccountPeriod(periodCalendar.getAccountPeriod());
	}
	
	@Override
    protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmCstFrmLoss2 scmCstFrmLoss = (ScmCstFrmLoss2) bean.getList().get(0);
        if(scmCstFrmLoss != null && scmCstFrmLoss.getId() > 0){
            //更新入库明细
            List<ScmCstFrmLossEntry2> scmCstFrmLossEntryList = bean.getList2();
            if(scmCstFrmLossEntryList != null && !scmCstFrmLossEntryList.isEmpty()){
				int lineId = 1;
                for(ScmCstFrmLossEntry2 scmCstFrmLossEntry : scmCstFrmLossEntryList){
                	scmCstFrmLossEntry.setLineId(lineId);
                    scmCstFrmLossEntry.setBillId(scmCstFrmLoss.getId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmCstFrmLossEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmCstFrmLossEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmCstFrmLossEntry.getItemId(), scmCstFrmLossEntry.getUnit(), param);//库存单位转换系数
					scmCstFrmLossEntry.setBaseQty(scmCstFrmLossEntry.getQty().multiply(invConvRate));
                    scmCstFrmLossEntry.setBillId(scmCstFrmLoss.getId());
					lineId = lineId+1;
                }
                scmCstFrmLossEntryBiz.update(scmCstFrmLoss, scmCstFrmLossEntryList, ScmCstFrmLossEntry2.FN_BILLID, param);
            }
        }
    }
	
	@Override
	protected void beforeDelete(ScmCstFrmLoss2 entity, Param param)	throws AppException {
		ScmCstFrmLoss2 scmCstFrmLoss = this.selectDirect(entity.getId(), param);
		if(scmCstFrmLoss!=null && !StringUtils.equals(scmCstFrmLoss.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getBillNo()}));
		}
	}
	
	@Override
    protected void afterDelete(ScmCstFrmLoss2 entity, Param param) throws AppException {
        if(entity != null && entity.getId() > 0){
            //删除入库明细
        	scmCstFrmLossEntryBiz.deleteByBillId(entity.getId(), param);
        	//删除附件
        	scmBaseAttachmentBiz.update(entity, null,ScmBaseAttachment.FN_BILLID,param);
        }
    }
	
	private void setConvertMap(ScmCstFrmLoss2 scmCstFrmLoss,Param param) {
		if (scmCstFrmLoss != null){ 
			HashMap<String,Object> cacheMap = new HashMap<String,Object>();
			 if (StringUtils.isNotBlank(scmCstFrmLoss.getCreator())){
                 //制单人
                 Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmCstFrmLoss.getCreator());
                 if(usr==null){
                     usr = usrBiz.selectByCode(scmCstFrmLoss.getCreator(), param);
                     cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmCstFrmLoss.getCreator(),usr);
                 }
                 if (usr != null) {
                	 scmCstFrmLoss.setConvertMap(ScmCstFrmLoss2.FN_CREATOR, usr);
                	 scmCstFrmLoss.setCreatorName(usr.getName());
                 }
             }
			 if (StringUtils.isNotBlank(scmCstFrmLoss.getEditor())){
	                //修改人
	                Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmCstFrmLoss.getEditor());
	                if(usr==null){
	                    usr = usrBiz.selectByCode(scmCstFrmLoss.getEditor(), param);
	                    cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmCstFrmLoss.getEditor(),usr);
	                }
	                if (usr != null) {
	                	scmCstFrmLoss.setConvertMap(ScmCstFrmLoss2.FN_EDITOR, usr);
	                	scmCstFrmLoss.setEditorName(usr.getName());
	                }
	            }
	            if (StringUtils.isNotBlank(scmCstFrmLoss.getChecker())){
	                //审核人
	                Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmCstFrmLoss.getChecker());
	                if(usr==null){
	                    usr = usrBiz.selectByCode(scmCstFrmLoss.getChecker(), param);
	                    cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmCstFrmLoss.getChecker(),usr);
	                }
	                if (usr != null) {
	                	scmCstFrmLoss.setConvertMap(ScmCstFrmLoss2.FN_CHECKER, usr);
	                	scmCstFrmLoss.setCheckerName(usr.getName());
	                }
	            }
             if (StringUtils.isNotBlank(scmCstFrmLoss.getCostOrgUnitNo())){
                 //报损部门
                 OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmCstFrmLoss.getCostOrgUnitNo());
                 if(orgBaseUnit==null){
                     orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmCstFrmLoss.getCostOrgUnitNo(), param);
                     cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmCstFrmLoss.getCostOrgUnitNo(),orgBaseUnit);
                 }
                 if (orgBaseUnit != null) {
                     scmCstFrmLoss.setConvertMap(ScmCstFrmLoss2.FN_COSTORGUNITNO, orgBaseUnit);
                     scmCstFrmLoss.setCostOrgUnitName(orgBaseUnit.getOrgUnitName());
                 }
             }
             if (StringUtils.isNotBlank(scmCstFrmLoss.getOrgUnitNo())){
                 //成本中心
                 OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmCstFrmLoss.getOrgUnitNo());
                 if(orgBaseUnit==null){
                     orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmCstFrmLoss.getOrgUnitNo(), param);
                     cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmCstFrmLoss.getOrgUnitNo(),orgBaseUnit);
                 }
                 if (orgBaseUnit != null) {
                     scmCstFrmLoss.setConvertMap(ScmCstFrmLoss2.FN_ORGUNITNO, orgBaseUnit);
                     scmCstFrmLoss.setOrgUnitName(orgBaseUnit.getOrgUnitName());
                 }
             }
             
             if(StringUtils.isNotBlank(scmCstFrmLoss.getStatus())){
 				Code code = codeBiz.selectByCategoryAndCode("warehouseStatus", scmCstFrmLoss.getStatus());
 				if(code!=null)
 					scmCstFrmLoss.setStatusName(code.getName());
 			}
             
             ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmCstFrmLoss.getId(), "CstFrmLoss",param);
             StringBuffer usrName = new StringBuffer("");
			 if (scmBillPendingUsr != null) {
				 scmCstFrmLoss.setPendingUsr(scmBillPendingUsr.getUsrCodes());
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
			 scmCstFrmLoss.setPendingUsrName(usrName.toString());
			 
			 scmCstFrmLoss.setTaxAmount(scmCstFrmLoss.getTotalTaxAmt().subtract(scmCstFrmLoss.getTotalAmt()));
		}
	}

	@Override
	public ScmCstFrmLoss2 submit(ScmCstFrmLoss2 scmCstFrmLoss, Param param)
			throws AppException {
		scmCstFrmLoss = this.selectDirect(scmCstFrmLoss.getId(), param);
		if(scmCstFrmLoss!=null){
			this.setConvertMap(scmCstFrmLoss, param);
			if(!scmCstFrmLoss.getStatus().equals("I")){
				throw new AppException("iscm.purchasemanage.error.commit");
			}else if(scmCstFrmLoss.getStatus().equals("I")){
				List<ScmCstFrmLossEntry2> scmCstFrmLossEntryList = scmCstFrmLossEntryBiz.selectByBillId(scmCstFrmLoss.getId(), param);
				
				BillType2 billType = billTypeBiz.selectByOrgAndCode(scmCstFrmLoss.getFinOrgUnitNo(), "CstFrmLoss", param);
				boolean needAudit = false;
				if(billType!=null && billType.isNeedAudit())
					needAudit = true;
				if(needAudit){
					String processInstanceId = startWorkFlow(billType, scmCstFrmLoss,scmCstFrmLossEntryList, param);
					scmCstFrmLoss.setWorkFlowNo(processInstanceId);
					ActivityUtil activityUtil = new ActivityUtil();
					//判断当前流程是否结束
					boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
					if(isCompleteTask) {
						if(param.getUsrCode()!=null ){
							scmCstFrmLoss.setChecker(param.getUsrCode());
							scmCstFrmLoss.setSubmitter(param.getUsrCode());
						}
						scmCstFrmLoss.setCheckDate(CalendarUtil.getDate(param));
						scmCstFrmLoss.setSubmitDate(CalendarUtil.getDate(param));
						scmCstFrmLoss.setStatus("A");
					}else {
						if(param.getUsrCode()!=null ){
							scmCstFrmLoss.setSubmitter(param.getUsrCode());
						}
						scmCstFrmLoss.setSubmitDate(CalendarUtil.getDate(param));

						scmCstFrmLoss.setStatus("D");
						sendAuditMsg(scmCstFrmLoss,billType.getBillCode(),param);
					}
				}else{
					if(billType.isUseFlow()) {
						//启用工作流（不影响状态）
						String processInstanceId = startWorkFlow(billType, scmCstFrmLoss,scmCstFrmLossEntryList, param);
						scmCstFrmLoss.setWorkFlowNo(processInstanceId);
						scmCstFrmLoss.setSubmitDate(CalendarUtil.getDate(param));
						ActivityUtil activityUtil = new ActivityUtil();
						//判断当前流程是否结束
						boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
						if(!isCompleteTask) {
							sendAuditMsg(scmCstFrmLoss,billType.getBillCode(),param);
						}
					}
					if(param.getUsrCode()!=null ){
						scmCstFrmLoss.setChecker(param.getUsrCode());
						scmCstFrmLoss.setSubmitter(param.getUsrCode());
					}
					scmCstFrmLoss.setCheckDate(CalendarUtil.getDate(param));
					scmCstFrmLoss.setSubmitDate(CalendarUtil.getDate(param));
					scmCstFrmLoss.setStatus("A");
				}
				
				try{
					this.updateStatus(scmCstFrmLoss, param);
				}catch(AppException e){
					throw e;
				}
				SystemState systemState = systemStateBiz.selectBySystemId(scmCstFrmLoss.getFinOrgUnitNo(), 170, param);
				if(systemState==null){
					throw new AppException("com.armitage.iars.daily.util.disenable.unable");
				}
				if (systemState.getCurrentPeriodId() == scmCstFrmLoss.getPeriodId()) {
					if(StringUtils.equals("A", scmCstFrmLoss.getStatus())) {
						//通过或部分通过时检查是否自动过帐
						if(billType!=null && billType.isAutoRelease()) {
							List<String> msgList = this.postBillCheck(scmCstFrmLoss, param);
							if (msgList != null && !msgList.isEmpty()) {
								StringBuilder detailInfo = new StringBuilder("");
		                        detailInfo.append(Message.getMessage(param.getLang(),"iscm.inventorymanage.scmcstfrmloss.post.errorTitle"));
	
								for (String str : msgList) {
		                            detailInfo.append(str).append("\n");
		                        }
								
								throw new AppException(detailInfo.toString(), new String[]{scmCstFrmLoss.getBillNo()});
							}
							
							this.postBill(scmCstFrmLoss, param);
						}
					}
				}
			}
		}else{
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.find.ordernotexists");
		}
		return scmCstFrmLoss;
	}

	@Override
	public ScmCstFrmLoss2 undoSubmit(ScmCstFrmLoss2 scmCstFrmLoss, Param param)
			throws AppException {
		scmCstFrmLoss = this.selectDirect(scmCstFrmLoss.getId(), param);
		
		if(scmCstFrmLoss!=null){
			BillType2 billType = billTypeBiz.selectByOrgAndCode(scmCstFrmLoss.getFinOrgUnitNo(), "CstFrmLoss", param);
			boolean needAudit = false;
			if(billType!=null && billType.isNeedAudit() && StringUtils.isNotBlank(scmCstFrmLoss.getWorkFlowNo()))
				needAudit = true;
			
			//单据设置审批流程，但设置没有审批节点，状态为通过，可以取消通过
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ScmBillPending.FN_BILLID, new QueryParam(ScmBillPending.FN_BILLID, QueryParam.QUERY_EQ, String.valueOf(scmCstFrmLoss.getId())));
			map.put(ScmBillPending.FN_BILLTYPE, new QueryParam(ScmBillPending.FN_BILLTYPE, QueryParam.QUERY_EQ, billType.getBillCode()));
			List<ScmBillPending> scmBillPendingList = scmBillPendingBiz.findAll(map, param);
			
			if(needAudit){
				if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
					if(!StringUtils.equals(scmCstFrmLoss.getStatus(),"A"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				} else {
					if(!StringUtils.equals(scmCstFrmLoss.getStatus(),"D"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				}
			}else{
				//启用审批进入审核中(再调整为启用工作流)的单据的状态不是A
				if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
					if(!StringUtils.equals(scmCstFrmLoss.getStatus(),"A"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				} else {
					if(!StringUtils.contains("D,A",scmCstFrmLoss.getStatus()))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				}
			} 
			
			if(scmCstFrmLoss.getStatus().equals("A") || scmCstFrmLoss.getStatus().equals("D")){
				scmCstFrmLoss.setChecker(null);
				scmCstFrmLoss.setSubmitter(null);
				scmCstFrmLoss.setCheckDate(null);
				scmCstFrmLoss.setSubmitDate(null);
				scmCstFrmLoss.setStatus("I");
				try{
					this.updateStatus(scmCstFrmLoss, param);
					scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),scmCstFrmLoss, param);
				}catch(AppException e){
					throw e;
				}
			}
			
		} else{
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.find.ordernotexists");
		}
		return scmCstFrmLoss;
	}
	
	public ScmCstFrmLoss2 updateStatus(ScmCstFrmLoss2 scmCstFrmLoss,Param param) throws AppException {
        if(scmCstFrmLoss != null){
        	return this.updateDirect(scmCstFrmLoss, param);
        }
        return null;
    }

	@Override
	public List<String> postBillCheck(ScmCstFrmLoss2 scmCstFrmLoss, Param param)
			throws AppException {
		List<String> msgList = new ArrayList<String>();// 提示列表
		scmCstFrmLoss = this.selectDirect(scmCstFrmLoss.getId(), param);
		if(scmCstFrmLoss != null){
	        if(!StringUtils.equals("A",scmCstFrmLoss.getStatus())) {
				msgList.add(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmCstFrmLoss.getBillNo()}));
				return msgList;
			}
		    SystemState systemState = systemStateBiz.selectBySystemId(scmCstFrmLoss.getFinOrgUnitNo(), 170, param);
            if(systemState==null){
                msgList.add(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
                return msgList;
            }
            if (systemState.getCurrentPeriodId() != scmCstFrmLoss.getPeriodId()) {
                msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmCstFrmLoss.getBillNo()}));
                return msgList;
            }
            
            // 检查冻结部门
            List<ScmCstFrmLoss2> scmCstFrmLossList = ((ScmCstFrmLossDAO) dao).checkCostCenterFree(scmCstFrmLoss.getId());
            
            if (scmCstFrmLossList != null && !scmCstFrmLossList.isEmpty()) {
            	msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingCostcenter"));
                return msgList;
//            	for (ScmCstFrmLoss2 scmCstFrmLoss2 : scmCstFrmLossList) {
//	                // 检查冻结物资
//	                HashMap<String,Object> map = new HashMap<String,Object>();
//	                map.put("taskId", scmCstFrmLoss2.getTaskId());
//	                map.put("id", scmCstFrmLoss.getId());
//	                int count = ((ScmCstFrmLossDAO) dao).checkMaterialFree(map);
//	                if (count > 0) {
//	                    msgList.add(Message.getMessage(param.getLang(), 
//	                            "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingCostcenter"));
//	                    return msgList;
//	                }
//            	}
            }
		}
		return msgList;
	}

	@Override
	public ScmCstFrmLoss2 postBill(ScmCstFrmLoss2 scmCstFrmLoss, Param param)
			throws AppException {
		scmCstFrmLoss = this.selectDirect(scmCstFrmLoss.getId(), param);
		if(scmCstFrmLoss != null){
			if(!StringUtils.equals("A",scmCstFrmLoss.getStatus())) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmCstFrmLoss.getBillNo()}));
    		}
			
			//按批次拆单
			splitBillByOutWarehouse(scmCstFrmLoss, param);
			
			List<ScmCstFrmLossEntry2> scmCstFrmLossEntryList = scmCstFrmLossEntryBiz.selectOutEffectRow(scmCstFrmLoss.getId(), param);
			//即时库存
    		int updateRow = scmInvStockBiz.updateByCstFrmLoss(scmCstFrmLoss.getId(), param);
    		if(updateRow<scmCstFrmLossEntryList.size()){
    			throw new AppException("iscm.inventorymanage.common.post.error.rowsnotequal");
    		}
			//期间余额
			scmInvBalBiz.updateByCstFrmLoss(scmCstFrmLoss.getId(), param);
			//移动平均即时成本
			if (!(ParamType.Operation.equals(param.getParamType()) && ((OperationParam) param).getOperationId()==629223199L)) {
				scmCstFrmLoss.setCheckDate(CalendarUtil.getDate(param));
				scmCstFrmLoss.setChecker(param.getUsrCode());
				scmCstFrmLoss.setPostDate(CalendarUtil.getDate(param));
			}
			scmCstFrmLoss.setStatus("E");
			updateRow = this.updatePostedStatus(scmCstFrmLoss, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmCstFrmLoss.getBillNo()}));
			}
		}
		return scmCstFrmLoss;
	}
	
	private void splitBillByOutWarehouse(ScmCstFrmLoss2 scmCstFrmLoss, Param param) {
		List<ScmCstFrmLossEntry2> scmCstFrmLossEntryList = scmCstFrmLossEntryBiz.selectByBillId(scmCstFrmLoss.getId(), param);
		if(scmCstFrmLossEntryList != null && !scmCstFrmLossEntryList.isEmpty()) {
        	String fields[]={"itemId","id"};
			String sorts[]={"ASC","Desc"};
			scmCstFrmLossEntryList = (List<ScmCstFrmLossEntry2>)ListSortUtil.sort(scmCstFrmLossEntryList, fields,sorts);	//按物资排序
			int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
			HashMap<String,List<ScmInvStock2>> qtyMap = new HashMap<String,List<ScmInvStock2>>();
			int lineId=1;
			for(int i=scmCstFrmLossEntryList.size()-1; i>=0; i--){ 
				ScmCstFrmLossEntry2 scmCstFrmLossEntry = scmCstFrmLossEntryList.get(i);
				StringBuffer info = new StringBuffer("");
				info.append(scmCstFrmLoss.getOrgUnitNo()).append("_")
						.append(scmCstFrmLoss.getCostOrgUnitNo()).append("_")
						.append(String.valueOf(scmCstFrmLossEntry.getItemId()));
				if(!qtyMap.containsKey((info.toString()))){
					//查询计价方式
					Page page = new Page();
					page.setModelClass(ScmMaterial2.class);
					page.setShowCount(Integer.MAX_VALUE);
					page.setSqlCondition("ScmMaterial.id="+scmCstFrmLossEntry.getItemId());
					ArrayList argList = new ArrayList();
			        argList.add("orgUnitNo="+scmCstFrmLoss.getFinOrgUnitNo());
			        argList.add("controlUnitNo=" + param.getControlUnitNo());
                    List<ScmMaterial2> materialList = scmMaterialBiz.queryPage(page, argList, "findBySingleFinAllPage", param);
                    //获取costMethod来决定升序还是降序排序
                    if (materialList != null && materialList.size() > 0 && StringUtils.isNotBlank(materialList.get(0).getCosting())) {
                    	HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("useOrgUnitNo", scmCstFrmLoss.getCostOrgUnitNo());		
                        map.put("costOrgUnitNo", scmCstFrmLoss.getOrgUnitNo());		
                        map.put("itemId", scmCstFrmLossEntry.getItemId());
                        map.put("bizDate", scmCstFrmLoss.getBizDate());
                        map.put("costMethod", materialList.get(0).getCosting());
                        map.put("unit", scmCstFrmLossEntry.getUnit());
                        List<ScmInvStock2> stocklist = scmInvStockBiz.findByReturnWarehouse(map, param);
                        qtyMap.put(info.toString(), stocklist);
                    }
				}
				List<ScmInvStock2> stocklist = qtyMap.get(info.toString());
				if(stocklist != null && !stocklist.isEmpty()){
					if(StringUtils.isNotBlank(scmCstFrmLossEntry.getLot())) {
                		//有批次则先按批次找，如不够再按先进先出拆单
                		for (ScmInvStock2 scmInvStock : stocklist) {
                			if(StringUtils.equals(scmCstFrmLossEntry.getLot(), scmInvStock.getLot())) {
		                		if(scmCstFrmLossEntry.getQty().compareTo(BigDecimal.ZERO)>0) {
		                			if(setDataFromStock(scmInvStock,scmCstFrmLossEntry,amtPrecision,lineId,param))
		                				lineId = lineId + 1;
		                		}else {
		                			break;
		                		}
                			}
                		}
                	}
                	for (ScmInvStock2 scmInvStock : stocklist) {
                		if(scmCstFrmLossEntry.getQty().compareTo(BigDecimal.ZERO)>0) {
                			if(setDataFromStock(scmInvStock,scmCstFrmLossEntry,amtPrecision,lineId,param))
                				lineId = lineId + 1;
                		}else {
                			break;
                		}
                	}
                	scmCstFrmLossEntryBiz.delete(scmCstFrmLossEntry, param);
				}
			}
		}
		
		//
		List<ScmCstFrmLossEntry2> scmCstFrmLossEntryList2 = scmCstFrmLossEntryBiz.selectByBillId(scmCstFrmLoss.getId(), param);
		if(scmCstFrmLossEntryList2 != null && !scmCstFrmLossEntryList2.isEmpty()){
			//重新设置LineId,防止错乱
			for(int i=0; i<scmCstFrmLossEntryList2.size(); i++){
				scmCstFrmLossEntryList2.get(i).setLineId(i+1);
			}
		}
	}
	
	private boolean setDataFromStock(ScmInvStock2 scmInvStock,ScmCstFrmLossEntry2 scmCstFrmLossEntry,int amtPrecision,int lineId,Param param) {
		boolean flag = false;
		BigDecimal stockQty = scmInvStock.getQty();
		BigDecimal stockPieQty = scmInvStock.getPieQty();
		BigDecimal stockBaseQty = scmInvStock.getBaseQty();
		BigDecimal stockAmt = (scmInvStock.getAmt());
		BigDecimal stockTaxAmt = (scmInvStock.getTaxAmt());
		if (stockQty.compareTo(scmCstFrmLossEntry.getQty()) > 0) {
			ScmCstFrmLossEntry2 scmCstFrmLossEntry2= new ScmCstFrmLossEntry2(true);
    		scmCstFrmLossEntry2.setBillId(scmCstFrmLossEntry.getBillId());
    		scmCstFrmLossEntry2.setLineId(lineId);
    		scmCstFrmLossEntry2.setItemId(scmInvStock.getItemId());
    		scmCstFrmLossEntry2.setLot(scmInvStock.getLot());
    		scmCstFrmLossEntry2.setUnit(scmInvStock.getUnit());
    		scmCstFrmLossEntry2.setQty(scmCstFrmLossEntry.getQty());
    		scmCstFrmLossEntry2.setBaseUnit(scmInvStock.getBaseUnit());
    		scmCstFrmLossEntry2.setBaseQty(scmCstFrmLossEntry.getBaseQty());
    		scmCstFrmLossEntry2.setPieUnit(scmInvStock.getPieUnit());
    		scmCstFrmLossEntry2.setPieQty(scmCstFrmLossEntry.getPieQty());
    		scmCstFrmLossEntry2.setPrice(scmInvStock.getPrice());
    		scmCstFrmLossEntry2.setTaxRate(scmInvStock.getTaxRate());
    		scmCstFrmLossEntry2.setTaxPrice(scmInvStock.getTaxPrice());
    		scmCstFrmLossEntry2.setAmt((scmCstFrmLossEntry2.getQty().multiply(scmCstFrmLossEntry2.getPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
    		scmCstFrmLossEntry2.setTaxAmt((scmCstFrmLossEntry2.getQty().multiply(scmCstFrmLossEntry2.getTaxPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
    		scmCstFrmLossEntry2.setExpDate(scmInvStock.getExpDate());
    		scmCstFrmLossEntry2.setStockId(scmInvStock.getId());
    		scmCstFrmLossEntry2.setRemarks(scmCstFrmLossEntry.getRemarks());
    		scmCstFrmLossEntry2.setOrgSourceId(scmInvStock.getSourceBillId());
    		scmCstFrmLossEntry2.setOrgSourceVendorId(scmInvStock.getVendorId());
    		scmCstFrmLossEntryBiz.add(scmCstFrmLossEntry2, param);
    		scmCstFrmLossEntry.setQty(BigDecimal.ZERO);
    		scmCstFrmLossEntry.setPieQty(BigDecimal.ZERO);
    		scmCstFrmLossEntry.setBaseQty(BigDecimal.ZERO);
    		scmInvStock.setQty(scmInvStock.getQty().subtract(scmCstFrmLossEntry2.getQty()));
    		scmInvStock.setPieQty(scmInvStock.getPieQty().subtract(scmCstFrmLossEntry2.getPieQty()));
    		scmInvStock.setBaseQty(scmInvStock.getBaseQty().subtract(scmCstFrmLossEntry2.getBaseQty()));
    		scmInvStock.setAmt(scmInvStock.getAmt().subtract(scmCstFrmLossEntry2.getAmt()));
    		scmInvStock.setTaxAmt(scmInvStock.getTaxAmt().subtract(scmCstFrmLossEntry2.getTaxAmt()));
    		flag = true;
		}else if (stockQty.compareTo(BigDecimal.ZERO) > 0) {
			ScmCstFrmLossEntry2 scmCstFrmLossEntry2= new ScmCstFrmLossEntry2(true);
    		scmCstFrmLossEntry2.setBillId(scmCstFrmLossEntry.getBillId());
    		scmCstFrmLossEntry2.setLineId(lineId);
    		scmCstFrmLossEntry2.setItemId(scmInvStock.getItemId());
    		scmCstFrmLossEntry2.setLot(scmInvStock.getLot());
    		scmCstFrmLossEntry2.setUnit(scmInvStock.getUnit());
    		scmCstFrmLossEntry2.setQty(stockQty);
    		scmCstFrmLossEntry2.setBaseUnit(scmInvStock.getBaseUnit());
    		scmCstFrmLossEntry2.setBaseQty(stockBaseQty);
    		scmCstFrmLossEntry2.setPieUnit(scmInvStock.getPieUnit());
    		scmCstFrmLossEntry2.setPieQty(stockPieQty);
    		scmCstFrmLossEntry2.setPrice(scmInvStock.getPrice());
    		scmCstFrmLossEntry2.setTaxRate(scmInvStock.getTaxRate());
    		scmCstFrmLossEntry2.setTaxPrice(scmInvStock.getTaxPrice());
    		scmCstFrmLossEntry2.setAmt(stockAmt);
    		scmCstFrmLossEntry2.setTaxAmt(stockTaxAmt);
    		scmCstFrmLossEntry2.setExpDate(scmInvStock.getExpDate());
    		scmCstFrmLossEntry2.setStockId(scmInvStock.getId());
    		scmCstFrmLossEntry2.setRemarks(scmCstFrmLossEntry.getRemarks());
    		scmCstFrmLossEntry2.setOrgSourceId(scmInvStock.getSourceBillId());
    		scmCstFrmLossEntry2.setOrgSourceVendorId(scmInvStock.getVendorId());
    		scmCstFrmLossEntryBiz.add(scmCstFrmLossEntry2, param);
    		scmCstFrmLossEntry.setQty(scmCstFrmLossEntry.getQty().subtract(scmCstFrmLossEntry2.getQty()));
    		scmCstFrmLossEntry.setPieQty(scmCstFrmLossEntry.getPieQty().subtract(scmCstFrmLossEntry2.getPieQty()));
    		scmCstFrmLossEntry.setBaseQty(scmCstFrmLossEntry.getBaseQty().subtract(scmCstFrmLossEntry2.getBaseQty()));
    		scmInvStock.setQty(BigDecimal.ZERO);
    		scmInvStock.setPieQty(BigDecimal.ZERO);
    		scmInvStock.setBaseQty(BigDecimal.ZERO);
    		scmInvStock.setAmt(BigDecimal.ZERO);
    		scmInvStock.setTaxAmt(BigDecimal.ZERO);
    		flag = true;
		}
		return flag;
	}

	@Override
	public List<String> cancelPostBillCheck(ScmCstFrmLoss2 scmCstFrmLoss,
			Param param) throws AppException {
		List<String> msgList = new ArrayList<String>();// 提示列表
		scmCstFrmLoss = this.selectDirect(scmCstFrmLoss.getId(), param);
		if(scmCstFrmLoss != null){
	        if(!StringUtils.equals("E",scmCstFrmLoss.getStatus())) {
	   			msgList.add(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmCstFrmLoss.getBillNo()}));
	   			return msgList;
			}
        }
		SystemState systemState = systemStateBiz.selectBySystemId(scmCstFrmLoss.getFinOrgUnitNo(), 170, param);
        if(systemState==null){
            msgList.add(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
            return msgList;
        }
        if (systemState.getCurrentPeriodId() != scmCstFrmLoss.getPeriodId()) {
            msgList.add(Message.getMessage(param.getLang(), 
                    "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmCstFrmLoss.getBillNo()}));
            return msgList;
        }
        
        // 检查冻结部门
        List<ScmCstFrmLoss2> scmCstFrmLossList = ((ScmCstFrmLossDAO) dao).checkCostCenterFree(scmCstFrmLoss.getId());
        
        if (scmCstFrmLossList != null && !scmCstFrmLossList.isEmpty()) {
        	msgList.add(Message.getMessage(param.getLang(), 
                    "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingCostcenter"));
            return msgList;
//        	for (ScmCstFrmLoss2 scmCstFrmLoss2 : scmCstFrmLossList) {
//	            // 检查冻结物资
//	            HashMap<String,Object> map = new HashMap<String,Object>();
//	            map.put("taskId", scmCstFrmLoss2.getTaskId());
//	            map.put("id", scmCstFrmLoss.getId());
//	            int count = ((ScmCstFrmLossDAO) dao).checkMaterialFree(map);
//	            if (count > 0) {
//	                msgList.add(Message.getMessage(param.getLang(), 
//	                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingCostcenter"));
//	                return msgList;
//	            }
//        	}
        }
        
		return null;
	}

	@Override
	public ScmCstFrmLoss2 cancelPostBill(ScmCstFrmLoss2 scmCstFrmLoss,
			Param param) throws AppException {
		scmCstFrmLoss = this.selectDirect(scmCstFrmLoss.getId(), param);
		if(scmCstFrmLoss != null){
	        if(!StringUtils.equals("E",scmCstFrmLoss.getStatus())) {
	        	throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmCstFrmLoss.getBillNo()}));
			}
			//即时库存
			scmInvStockBiz.updateByCancelCstFrmLoss(scmCstFrmLoss.getId(), param);
			//期间余额
			scmInvBalBiz.updateByCancelCstFrmLoss(scmCstFrmLoss.getId(), param);
			scmCstFrmLoss.setCheckDate(null);
			scmCstFrmLoss.setChecker("");
			scmCstFrmLoss.setStatus("A");
			scmCstFrmLoss.setPostDate(null);
			int updateRow = this.updatePostedStatus(scmCstFrmLoss, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmCstFrmLoss.getBillNo()}));
			}
		}
		return scmCstFrmLoss;
	}
	
	private int updatePostedStatus(ScmCstFrmLoss2 scmCstFrmLoss, Param param) {
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("id",scmCstFrmLoss.getId());
		map.put("checker",scmCstFrmLoss.getChecker());
		map.put("checkDate",scmCstFrmLoss.getCheckDate()==null?null:FormatUtils.fmtDate(scmCstFrmLoss.getCheckDate()));
		map.put("status", scmCstFrmLoss.getStatus());
		map.put("postDate", scmCstFrmLoss.getPostDate()==null?null:FormatUtils.fmtDateTime(scmCstFrmLoss.getPostDate()));
		return ((ScmCstFrmLossDAO)dao).updatePostedStatus(map);
	}

	@Override
	public ScmCstFrmLoss2 queryCstFrmLoss(ScmCstFrmLoss2 scmCstFrmLoss,
			Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(ScmCstFrmLoss2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmCstFrmLoss2.FN_BILLNO,new QueryParam(ScmCstFrmLoss2.FN_BILLNO, QueryParam.QUERY_EQ, scmCstFrmLoss.getBillNo()));
		
		List<ScmCstFrmLoss2> scmCstFrmLossList =this.findPage(page, param);
		ScmCstFrmLoss2 scmCstFrmLoss2 = new ScmCstFrmLoss2();
		if(scmCstFrmLossList!=null && !scmCstFrmLossList.isEmpty()){
			scmCstFrmLoss2 = scmCstFrmLossList.get(0);
			scmCstFrmLoss2.setScmCstFrmLossEntryList(scmCstFrmLossEntryBiz.selectByBillId(scmCstFrmLoss2.getId(), param));
			
			//增加待审人
			ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmCstFrmLoss2.getId(), "CstFrmLoss",param);
			if (scmBillPendingUsr != null) {
				scmCstFrmLoss2.setPendingUsr(scmBillPendingUsr.getUsrCodes());
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmCstFrmLoss2;
	}

	@Override
	public ScmCstFrmLoss2 doAuditCstFrmLoss(
			CommonAuditParams commonAuditParams, Param param)
			throws AppException {
		ScmCstFrmLoss2 cstFrmLoss = null;
		
		ScmCstFrmLoss2 scmCstFrmLoss= new ScmCstFrmLoss2();
		scmCstFrmLoss.setId(commonAuditParams.getBillId());
		scmCstFrmLoss.setBillNo(commonAuditParams.getBillNo());
		String opinion = commonAuditParams.getOpinion();	
		if(StringUtils.equals("Y", opinion) || StringUtils.equals("agree", opinion)) {
			opinion="agree";
		}else if(StringUtils.equals("N", opinion) || StringUtils.equals("refuse", opinion)) {
			opinion="refuse";
		}
		if(scmCstFrmLoss.getId()>0){
			cstFrmLoss = this.selectDirect(scmCstFrmLoss.getId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmCstFrmLoss2.FN_BILLNO,
					new QueryParam(ScmCstFrmLoss2.FN_BILLNO, QueryParam.QUERY_EQ,
							scmCstFrmLoss.getBillNo()));
			map.put(ScmCstFrmLoss2.FN_CONTROLUNITNO, new QueryParam(ScmCstFrmLoss2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
							param.getControlUnitNo()));
			List<ScmCstFrmLoss2> scmCstFrmLossList =this.findAll(map, param);
			
			if(scmCstFrmLossList!=null && !scmCstFrmLossList.isEmpty()){
				cstFrmLoss = scmCstFrmLossList.get(0);
			}
		}
		
		if (cstFrmLoss != null) {
			this.setConvertMap(cstFrmLoss, param);
			BillType2 billType = billTypeBiz.selectByOrgAndCode(cstFrmLoss.getFinOrgUnitNo(), "CstFrmLoss", param);
			if(!(cstFrmLoss.getStatus().equals("D") || cstFrmLoss.getStatus().equals("P")) && billType.isNeedAudit()){
				throw new AppException("iscm.purchasemanage.error.audit");
			}

			if(StringUtils.equals("R", opinion)) {
				scmBillPendingBiz.deletePendingBill(param.getUsrCode(),cstFrmLoss, param);
				commonEventHistoryBiz.updateInvalid(cstFrmLoss,"",param);
				
				CommonAuditOpinion commonAuditOpinionR = new CommonAuditOpinion();
				commonAuditOpinionR.setPreStepNo(cstFrmLoss.getStepNo());
				String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
				commonAuditOpinionR.setStepNo(stepNo);
				commonAuditOpinionR.setActiveType("A");
				if (StringUtils.equals("agree", commonAuditOpinionR.getOpinion())) {
					commonAuditOpinionR.setOpinion("Y");
				}else if (StringUtils.equals("refuse", commonAuditOpinionR.getOpinion())) {
					commonAuditOpinionR.setOpinion("N");
				}
				commonAuditOpinionR.setOpinion(commonAuditParams.getOpinion());
				commonAuditOpinionR.setRemarks(commonAuditParams.getOpinionRemarks());
				commonEventHistoryBiz.addEventHistory(cstFrmLoss, commonAuditOpinionR, param);
				
				if(billType.isNeedAudit()) {
					//驳回则将单据变回新单状态
					cstFrmLoss.setStatus("I");
					cstFrmLoss.setChecker(null);
					cstFrmLoss.setCheckDate(null);
					return this.updateDirect(cstFrmLoss, param);
				}else {
					//不需要审批时驳回需模拟反过账、反提交
					if(StringUtils.equals("E",cstFrmLoss.getStatus())) {
						cstFrmLoss = this.cancelPostBill(cstFrmLoss, param);
					}
					if(StringUtils.equals("A",cstFrmLoss.getStatus())) {
						this.undoSubmit(cstFrmLoss, param);
					}
					cstFrmLoss.setStatus("I");
					cstFrmLoss.setChecker(null);
					cstFrmLoss.setCheckDate(null);
					return this.updateDirect(cstFrmLoss, param);
				}
			}
			String processInstanceId = cstFrmLoss.getWorkFlowNo();
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
			if(billType.isNeedAudit() || (billType.isUseFlow() && StringUtils.contains("D,P", cstFrmLoss.getStatus()))) {
				//P：审核中，A：通过，N：未通过
				if ("agree".equals(opinion)) {
					if (isCompleteTask) {
						cstFrmLoss.setStatus("A");
					} else {
						cstFrmLoss.setStatus("P");
					}
				} else {
					cstFrmLoss.setStatus("N");
				}
			}else {
				if (billType.isUseFlow()) {
					if(StringUtils.equals("refuse", opinion)) {
						//审批拒绝
						if(StringUtils.equals("E",cstFrmLoss.getStatus())) {
							cstFrmLoss = this.cancelPostBill(cstFrmLoss, param);
						}
						cstFrmLoss.setStatus("N");
					}
					
				}
			}
			scmBillPendingBiz.updatePendingBill(param.getUsrCode(), cstFrmLoss, param);
			cstFrmLoss.setChecker(param.getUsrCode());
			if ("agree".equals(opinion)) {
				this.sendAuditMsg(cstFrmLoss, billType.getBillCode(), param);
			}
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setPreStepNo(cstFrmLoss.getStepNo());
			String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
			try {
				cstFrmLoss.setStepNo(stepNo);
				cstFrmLoss.setCheckDate(CalendarUtil.getDate(param));
				this.updateStatus(cstFrmLoss, param);
			} catch (Exception e) {
				throw e;
			}
			commonAuditOpinion.setStepNo(stepNo);
			commonAuditOpinion.setActiveType("A");
			commonAuditOpinion.setOpinion(commonAuditParams.getOpinion());
			commonAuditOpinion.setRemarks(commonAuditParams.getOpinionRemarks());
			if (StringUtils.equals("agree", commonAuditOpinion.getOpinion())) {
				commonAuditOpinion.setOpinion("Y");
			}else if (StringUtils.equals("refuse", commonAuditOpinion.getOpinion())) {
				commonAuditOpinion.setOpinion("N");
			}
			commonEventHistoryBiz.addEventHistory(cstFrmLoss, commonAuditOpinion, param);
			SystemState systemState = systemStateBiz.selectBySystemId(cstFrmLoss.getFinOrgUnitNo(), 170, param);
			if(systemState==null){
				throw new AppException("com.armitage.iars.daily.util.disenable.unable");
			}
			if (systemState.getCurrentPeriodId() == cstFrmLoss.getPeriodId()) {
				if(StringUtils.equals("A", cstFrmLoss.getStatus())) {
					//通过或部分通过时检查是否自动过帐
					if(billType!=null && billType.isAutoRelease()) {
						List<String> msgList = this.postBillCheck(cstFrmLoss, param);
						if (msgList != null && !msgList.isEmpty()) {
							StringBuilder detailInfo = new StringBuilder("");
	                        detailInfo.append(Message.getMessage(param.getLang(),"iscm.inventorymanage.scmcstfrmloss.post.errorTitle"));
	
							for (String str : msgList) {
	                            detailInfo.append(str).append("\n");
	                        }
							
							throw new AppException(detailInfo.toString(), new String[]{cstFrmLoss.getBillNo()});
						}
						
						this.postBill(cstFrmLoss, param);
					}
				}
			}
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return cstFrmLoss;
	}

	@Override
	public ScmCstFrmLoss2 doUnAuditCstFrmLoss(ScmCstFrmLoss2 scmCstFrmLoss,
			Param param) throws AppException {
		ScmCstFrmLoss2 cstFrmLoss = null;
		List<ScmCstFrmLoss2> scmCstFrmLossList = new ArrayList<> ();
		List<ScmCstFrmLossEntry2> scmCstFrmLossEntryList = new ArrayList<> ();
		boolean isFirstTask = false;
		
		if(scmCstFrmLoss.getId()>0){
			cstFrmLoss = this.selectDirect(scmCstFrmLoss.getId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmCstFrmLoss2.FN_BILLNO,
					new QueryParam(ScmCstFrmLoss2.FN_BILLNO, QueryParam.QUERY_EQ,
							scmCstFrmLoss.getBillNo()));
			map.put(ScmCstFrmLoss2.FN_CONTROLUNITNO, new QueryParam(ScmCstFrmLoss2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
					param.getControlUnitNo()));
			
			scmCstFrmLossList =this.findAll(map, param);
			if(scmCstFrmLossList!=null && !scmCstFrmLossList.isEmpty()){
				cstFrmLoss=scmCstFrmLossList.get(0);
			}
		}
		
		if (cstFrmLoss != null) {
			BillType2 billType = billTypeBiz.selectByOrgAndCode(cstFrmLoss.getFinOrgUnitNo(), "CstFrmLoss", param);
			if(!StringUtils.contains("A,B,N,P", cstFrmLoss.getStatus()) && billType.isNeedAudit()){
				throw new AppException("iscm.purchasemanage.unaudit.error.status");
			}
			if(!StringUtils.contains("A,B,N,P,E", cstFrmLoss.getStatus()) && billType.isUseFlow()){
				throw new AppException("iscm.purchasemanage.unaudit.error.status");
			}
			String processInstanceId = cstFrmLoss.getWorkFlowNo();
			scmCstFrmLossEntryList = scmCstFrmLossEntryBiz.selectByBillId(cstFrmLoss.getId(), param);
			if(scmCstFrmLossEntryList == null || scmCstFrmLossEntryList.isEmpty()){
				//抛出异常处理
				throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
			}
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//反审批当前已审节点
				CommonBean bean = new CommonBean();
				scmCstFrmLossList.add(cstFrmLoss);
				bean.setList(scmCstFrmLossList);
				bean.setList2(scmCstFrmLossEntryList);
				
				long billTypeId = 0;
				if (billType!=null && billType.isNeedAudit()) {
					billTypeId = billType.getId();
				}
				
				processInstanceId = ActivityUtil.antiAuditProcess(bean, billTypeId, processInstanceId, param.getUsrCode(),  param);
				isFirstTask = ActivityUtil.isFirstTask(processInstanceId, param.getUsrCode(), param);
				cstFrmLoss.setWorkFlowNo(processInstanceId);
			} else {
				//流程编号不存在
				throw new AppException("com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.doAuditPurRequire.processInstanceId");
			}
			
			//启用审批，或启用工作流（对于历史数据的处理）
			if(billType.isNeedAudit() || (billType.isUseFlow() && StringUtils.contains("N,D,P", cstFrmLoss.getStatus()))) {
				String status = "";
				if (isFirstTask) {
					status = "D";
				} else {
					status = "P";
				}
				cstFrmLoss.setStatus(status);
				String tableName = ClassUtils.getFinalModelSimpleName(cstFrmLoss);
				CommonEventHistory commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,cstFrmLoss.getStepNo(),cstFrmLoss.getPK(),param);
				if(commonEventHistory!=null && StringUtils.isNotBlank(commonEventHistory.getPreStepNo())) {
					commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,commonEventHistory.getPreStepNo(),cstFrmLoss.getPK(),param);
				}
				if(commonEventHistory!=null) {
					cstFrmLoss.setChecker(commonEventHistory.getOper());
					cstFrmLoss.setCheckDate(commonEventHistory.getOperDate());
				}else {
					cstFrmLoss.setChecker(null);
					cstFrmLoss.setCheckDate(null);
				}
				this.updateStatus(cstFrmLoss, param);
			}
			scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),cstFrmLoss, param);
			commonEventHistoryBiz.updateInvalid(cstFrmLoss,cstFrmLoss.getStepNo(),param);
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setStepNo(cstFrmLoss.getStepNo());
			commonAuditOpinion.setActiveType("U");
			commonAuditOpinion.setOpinion("Y");
			commonEventHistoryBiz.addEventHistory(cstFrmLoss, commonAuditOpinion, param);
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		
		return cstFrmLoss;
	}
	
	private String startWorkFlow(BillType2 billType,ScmCstFrmLoss2 scmCstFrmLoss,List<ScmCstFrmLossEntry2> scmCstFrmLossEntryList, Param param) {
		CommonBean bean = new CommonBean();
		List<ScmCstFrmLoss2> scmCstFrmLossList = new ArrayList<>();
		scmCstFrmLossList.add(scmCstFrmLoss);
		bean.setList(scmCstFrmLossList);
		bean.setList2(scmCstFrmLossEntryList);
		String processInstanceId = ActivityUtil.startProcessInstance(bean, billType.getId(), billType.getWorkFlowNo(), param);
		return processInstanceId;
	}
	
	private void sendAuditMsg(ScmCstFrmLoss2 scmCstFrmLoss,String billCode,Param param) {
		String usrList= ActivityUtil.findAssigneeByProcessInstanceId(scmCstFrmLoss.getWorkFlowNo(),param);
		if(StringUtils.isNotBlank(usrList)) {
			scmBillPendingBiz.addPendingBill(usrList, scmCstFrmLoss, param);
			CstFrmLossParams cstFrmLossParams = new CstFrmLossParams();
			cstFrmLossParams.setBillNo(scmCstFrmLoss.getBillNo());
			AuditMsgUtil.sendAuditMsg(billCode,scmCstFrmLoss,cstFrmLossParams, usrList, param);
		}
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmCstFrmLossCostQuery) {
				ScmCstFrmLossCostQuery scmCstFrmLossCostQuery = (ScmCstFrmLossCostQuery) page.getModel();
				if( scmCstFrmLossCostQuery.getBizDateFrom()!=null){
					if( scmCstFrmLossCostQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmCstFrmLoss2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss2.class) + "." +ScmCstFrmLoss2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmCstFrmLossCostQuery.getBizDateFrom()),FormatUtils.fmtDate(scmCstFrmLossCostQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmCstFrmLoss2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss2.class) + "." +ScmCstFrmLoss2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmCstFrmLossCostQuery.getBizDateFrom())));
					}
				}else if( scmCstFrmLossCostQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmCstFrmLoss2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss2.class) + "." +ScmCstFrmLoss2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmCstFrmLossCostQuery.getBizDateTo())));
				}
				if( scmCstFrmLossCostQuery.getCreateDateFrom()!=null){
					if( scmCstFrmLossCostQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmCstFrmLoss2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss2.class) + "." +ScmCstFrmLoss2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmCstFrmLossCostQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmCstFrmLossCostQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmCstFrmLoss2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss2.class) + "." +ScmCstFrmLoss2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmCstFrmLossCostQuery.getCreateDateFrom())));
					}
				}else if( scmCstFrmLossCostQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmCstFrmLoss2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss2.class) + "." +ScmCstFrmLoss2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmCstFrmLossCostQuery.getCreateDateTo(),1))));
				}
				if(StringUtils.isNotBlank( scmCstFrmLossCostQuery.getCostOrgUnitNo())) {
					page.getParam().put(ScmCstFrmLoss2.FN_COSTORGUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss2.class) + "." +ScmCstFrmLoss2.FN_COSTORGUNITNO, QueryParam.QUERY_EQ,String.valueOf(scmCstFrmLossCostQuery.getCostOrgUnitNo())));
				}
			}
		}
	}

	@Override
	public List<ScmCstFrmLoss2> checkUnPostBill(String finOrgUnitNo,
			long periodId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmCstFrmLossDAO)dao).checkUnPostBill(map);
	}

	@Override
	public List<ScmCstFrmLoss2> checkPostedBill(String finOrgUnitNo,
			long periodId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmCstFrmLossDAO)dao).checkPostedBill(map);
	}

	@Override
	public List<Map<String, Object>> countCostUnPostBill(HashMap<String, Object> map) {
		return ((ScmCstFrmLossDAO) dao).countCostUnPostBill(map);
	}

	@Override
	public ScmCstFrmLoss2 updatePrtCount(ScmCstFrmLoss2 scmCstFrmLoss, Param param) throws AppException {
		if(scmCstFrmLoss.getId()>0){
			ScmCstFrmLoss2 bill = this.selectDirect(scmCstFrmLoss.getId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmCstFrmLoss;
	}
}
