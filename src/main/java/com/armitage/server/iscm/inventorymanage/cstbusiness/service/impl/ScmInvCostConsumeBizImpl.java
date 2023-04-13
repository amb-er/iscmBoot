package com.armitage.server.iscm.inventorymanage.cstbusiness.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.armitage.server.api.business.costconsume.params.CostConsumeAddParams;
import com.armitage.server.api.business.costconsume.params.CostConsumeDetailParams;
import com.armitage.server.api.business.datasync.params.InvCostConsumeDetailParams;
import com.armitage.server.api.business.datasync.params.InvCostConsumeListParams;
import com.armitage.server.api.business.datasync.result.DataSyncResult;
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
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialInventory2;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialInventoryBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvCostConsumeDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsume2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeImPort;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeImPortDetail;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeQuery;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCostConsumeBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCostConsumeEntryBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvBalBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockBiz;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.model.SystemState;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.OrgCompanyBiz;
import com.armitage.server.system.service.OrgCostCenterBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.service.SystemStateBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;

@Service("scmInvCostConsumeBiz")
public class ScmInvCostConsumeBizImpl extends BaseBizImpl<ScmInvCostConsume2> implements ScmInvCostConsumeBiz {
    
    private UsrBiz usrBiz;
    private OrgUnitBiz orgUnitBiz;
    private ScmInvCostConsumeEntryBiz scmInvCostConsumeEntryBiz;
    private ScmInvStockBiz scmInvStockBiz;
	private ScmInvBalBiz scmInvBalBiz;
	private ScmMaterialBiz scmMaterialBiz;
    private PeriodCalendarBiz periodCalendarBiz;
    private SysParamBiz sysParamBiz;
    private SystemStateBiz systemStateBiz;
    private OrgUnitRelationBiz orgUnitRelationBiz;
    private OrgCostCenterBiz orgCostCenterBiz;
    private BillTypeBiz billTypeBiz;
    private OrgCompanyBiz orgCompanyBiz;
    private ScmMaterialInventoryBiz scmMaterialInventoryBiz;
    
	protected  HashMap<String,Long> scmMatrialMap = new HashMap<String,Long>();
	protected  HashMap<Long,Long> scmUnitMap = new HashMap<Long,Long>();
    
    
    public void setScmMaterialInventoryBiz(ScmMaterialInventoryBiz scmMaterialInventoryBiz) {
		this.scmMaterialInventoryBiz = scmMaterialInventoryBiz;
	}
	public void setSystemStateBiz(SystemStateBiz systemStateBiz) {
        this.systemStateBiz = systemStateBiz;
    }
    public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}
	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}
	public void setScmInvCostConsumeEntryBiz(
			ScmInvCostConsumeEntryBiz scmInvCostConsumeEntryBiz) {
		this.scmInvCostConsumeEntryBiz = scmInvCostConsumeEntryBiz;
	}
	public void setScmInvStockBiz(ScmInvStockBiz scmInvStockBiz) {
		this.scmInvStockBiz = scmInvStockBiz;
	}
	public void setScmInvBalBiz(ScmInvBalBiz scmInvBalBiz) {
		this.scmInvBalBiz = scmInvBalBiz;
	}
	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}
	public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}
	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
        this.orgUnitRelationBiz = orgUnitRelationBiz;
    }
	public void setOrgCostCenterBiz(OrgCostCenterBiz orgCostCenterBiz) {
        this.orgCostCenterBiz = orgCostCenterBiz;
    }
	
    public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}
	public void setOrgCompanyBiz(OrgCompanyBiz orgCompanyBiz) {
		this.orgCompanyBiz = orgCompanyBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvCostConsume2.class) + "." + ScmInvCostConsume2.FN_ORGUNITNO), 
	   				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvCostConsume2.class) + "." + ScmInvCostConsume2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvCostConsume2.class) + "." + ScmInvCostConsume2.FN_ORGUNITNO), 
	   				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvCostConsume2.class) + "." + ScmInvCostConsume2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
   	}

    public ScmInvCostConsume2 updateStatus(ScmInvCostConsume2 scmInvCostConsume,Param param) throws AppException {
        if(scmInvCostConsume != null){
        	return this.updateDirect(scmInvCostConsume, param);
        }
        return null;
    }

    @Override
    protected void beforeAdd(ScmInvCostConsume2 entity, Param param) throws AppException {
        if(entity != null){
            // 创建耗用单号
        	String code = CodeAutoCalUtil.getBillCode("InvCostConsume", entity, param);
			entity.setDcNo(code);
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
	protected void beforeUpdate(ScmInvCostConsume2 oldEntity,ScmInvCostConsume2 newEntity, Param param) throws AppException {
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
    protected void afterAdd(CommonBean bean, Param param) throws AppException {
        ScmInvCostConsume2 scmInvCostConsume = (ScmInvCostConsume2) bean.getList().get(0);
        if(scmInvCostConsume != null && scmInvCostConsume.getDcId() > 0){
            //新增入库明细
            List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntryList = bean.getList2();
            if(scmInvCostConsumeEntryList != null && !scmInvCostConsumeEntryList.isEmpty()){
				int lineId = 1;
                for(ScmInvCostConsumeEntry2 scmInvCostConsumeEntry : scmInvCostConsumeEntryList){
                	scmInvCostConsumeEntry.setLineId(lineId);
                    scmInvCostConsumeEntry.setDcId(scmInvCostConsume.getDcId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvCostConsumeEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvCostConsumeEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvCostConsumeEntry.getItemId(), scmInvCostConsumeEntry.getUnit(), param);//库存单位转换系数
					scmInvCostConsumeEntry.setBaseQty(scmInvCostConsumeEntry.getQty().multiply(invConvRate));
                    scmInvCostConsumeEntryBiz.add(scmInvCostConsumeEntry, param);
					lineId = lineId+1;
                }
            }
        }
    }

    @Override
    protected void afterSelect(ScmInvCostConsume2 entity, Param param) throws AppException {
        if (entity != null){
            HashMap<String,Object> cacheMap = new HashMap<String,Object>();   
            // 成本中心
            if (StringUtils.isNotBlank(entity.getOrgUnitNo())) {
               OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(entity.getOrgUnitNo(), param);
               if (orgBaseUnit != null) {
                   entity.setConvertMap(ScmInvCostConsume2.FN_ORGUNITNO, orgBaseUnit);
               }
            }
            // 耗用部门
            if (StringUtils.isNotBlank(entity.getUseOrgUnitNo())) {
               OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(entity.getUseOrgUnitNo(), param);
               if (orgBaseUnit != null) {
                   entity.setConvertMap(ScmInvCostConsume2.FN_USEORGUNITNO, orgBaseUnit);
               }
            }
            if (StringUtils.isNotBlank(entity.getCreator())){
                //制单人
                Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+entity.getCreator());
                if(usr==null){
                    usr = usrBiz.selectByCode(entity.getCreator(), param);
                    cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+entity.getCreator(),usr);
                }
                if (usr != null) {
                    entity.setConvertMap(ScmInvCostConsume2.FN_CREATOR, usr);
                }
            }
            if (StringUtils.isNotBlank(entity.getEditor())){
                //修改人
                Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+entity.getEditor());
                if(usr==null){
                    usr = usrBiz.selectByCode(entity.getEditor(), param);
                    cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+entity.getEditor(),usr);
                }
                if (usr != null) {
                    entity.setConvertMap(ScmInvCostConsume2.FN_EDITOR, usr);
                }
            }
            if (StringUtils.isNotBlank(entity.getChecker())){
                //审核人
                Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+entity.getChecker());
                if(usr==null){
                    usr = usrBiz.selectByCode(entity.getChecker(), param);
                    cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+entity.getChecker(),usr);
                }
                if (usr != null) {
                    entity.setConvertMap(ScmInvCostConsume2.FN_CHECKER, usr);
                }
            }
            entity.setTaxAmount(entity.getTaxAmt().subtract(entity.getAmt()));
        }
    }

    @Override
    protected void afterSelect(CommonBean bean, Param param) throws AppException {
        ScmInvCostConsume2 scmInvCostConsume = (ScmInvCostConsume2) bean.getList().get(0);
        if(scmInvCostConsume != null && scmInvCostConsume.getDcId() > 0){
            HashMap<String,Object> cacheMap = new HashMap<String,Object>();
            bean.setList2(scmInvCostConsumeEntryBiz.selectByDcId(scmInvCostConsume.getDcId(), param));
        }
    }

    @Override
    protected void afterFindPage(List list, Page page, Param param) throws AppException {
        HashMap<String,Object> cacheMap = new HashMap<String,Object>();
        List<ScmInvCostConsume2> scmInvCostConsumeList = list;
        if(scmInvCostConsumeList != null && !scmInvCostConsumeList.isEmpty()){
            for(ScmInvCostConsume2 scmInvCostConsume : scmInvCostConsumeList){
                if (StringUtils.isNotBlank(scmInvCostConsume.getCreator())){
                    //制单人
                    Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvCostConsume.getCreator());
                    if(usr==null){
                        usr = usrBiz.selectByCode(scmInvCostConsume.getCreator(), param);
                        cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvCostConsume.getCreator(),usr);
                    }
                    if (usr != null) {
                        if(scmInvCostConsume.getDataDisplayMap()==null){
                            scmInvCostConsume.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
                        }
                        scmInvCostConsume.getDataDisplayMap().put(ScmInvCostConsume2.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
                    }
                }
                if (StringUtils.isNotBlank(scmInvCostConsume.getUseOrgUnitNo())){
                    //耗用部门
                    OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvCostConsume.getUseOrgUnitNo());
                    if(orgBaseUnit==null){
                        orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvCostConsume.getUseOrgUnitNo(), param);
                        cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvCostConsume.getUseOrgUnitNo(),orgBaseUnit);
                    }
                    if (orgBaseUnit != null) {
                        scmInvCostConsume.setConvertMap(ScmInvCostConsume2.FN_USEORGUNITNO, orgBaseUnit);
                    }
                }
                if (StringUtils.isNotBlank(scmInvCostConsume.getOrgUnitNo())){
                    //成本中心
                    OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvCostConsume.getOrgUnitNo());
                    if(orgBaseUnit==null){
                        orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvCostConsume.getOrgUnitNo(), param);
                        cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvCostConsume.getOrgUnitNo(),orgBaseUnit);
                    }
                    if (orgBaseUnit != null) {
                        scmInvCostConsume.setConvertMap(ScmInvCostConsume2.FN_ORGUNITNO, orgBaseUnit);
                    }
                }
                scmInvCostConsume.setTaxAmount(scmInvCostConsume.getTaxAmt().subtract(scmInvCostConsume.getAmt()));
            }
        }
    }

    @Override
    protected void afterUpdate(CommonBean bean, Param param) throws AppException {
        ScmInvCostConsume2 scmInvCostConsume = (ScmInvCostConsume2) bean.getList().get(0);
        if(scmInvCostConsume != null && scmInvCostConsume.getDcId() > 0){
            //更新入库明细
            List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntryList = bean.getList2();
            if(scmInvCostConsumeEntryList != null && !scmInvCostConsumeEntryList.isEmpty()){
				int lineId = 1;
                for(ScmInvCostConsumeEntry2 scmInvCostConsumeEntry : scmInvCostConsumeEntryList){
                	if (StringUtils.equals("I", scmInvCostConsume.getStatus())) {
                		scmInvCostConsumeEntry.setLineId(lineId);
                	}
                    scmInvCostConsumeEntry.setDcId(scmInvCostConsume.getDcId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvCostConsumeEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvCostConsumeEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvCostConsumeEntry.getItemId(), scmInvCostConsumeEntry.getUnit(), param);//库存单位转换系数
					scmInvCostConsumeEntry.setBaseQty(scmInvCostConsumeEntry.getQty().multiply(invConvRate));
                    scmInvCostConsumeEntry.setDcId(scmInvCostConsume.getDcId());
					lineId = lineId+1;
                }
                scmInvCostConsumeEntryBiz.update(scmInvCostConsume, scmInvCostConsumeEntryList, ScmInvCostConsumeEntry2.FN_DCID, param);
            }
        }
    }

    @Override
    protected void afterDelete(ScmInvCostConsume2 entity, Param param) throws AppException {
        if(entity != null && entity.getDcId() > 0){
            //删除入库明细
            scmInvCostConsumeEntryBiz.deleteByDcId(entity.getDcId(), param);
        }
    }

	@Override
	public List<String> postBillCheck(ScmInvCostConsume2 scmInvCostConsume,
			Param param) throws AppException {
		List<String> msgList = new ArrayList<String>();// 提示列表
		scmInvCostConsume = this.selectDirect(scmInvCostConsume.getDcId(), param);
		if(scmInvCostConsume != null){
	        if(!StringUtils.equals("A",scmInvCostConsume.getStatus())) {
				msgList.add(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvCostConsume.getDcNo()}));
				return msgList;
			}
		    SystemState systemState = systemStateBiz.selectBySystemId(scmInvCostConsume.getFinOrgUnitNo(), 170, param);
            if(systemState==null){
                msgList.add(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
                return msgList;
            }
            if (systemState.getCurrentPeriodId() != scmInvCostConsume.getPeriodId()) {
                msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmInvCostConsume.getDcNo()}));
                return msgList;
            }
            
            // 检查冻结部门
            List<ScmInvCostConsume2> scmInvCostConsumeList = ((ScmInvCostConsumeDAO) dao).checkCostCenterFree(scmInvCostConsume.getDcId());
            
            if (scmInvCostConsumeList != null && !scmInvCostConsumeList.isEmpty()) {
            	msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingCostcenter"));
                return msgList;
//            	for (ScmInvCostConsume2 scmInvCostConsume2 : scmInvCostConsumeList) {
//	                // 检查冻结物资
//	                HashMap<String,Object> map = new HashMap<String,Object>();
//	                map.put("taskId", scmInvCostConsume2.getTaskId());
//	                map.put("dcId", scmInvCostConsume.getDcId());
//	                int count = ((ScmInvCostConsumeDAO) dao).checkMaterialFree(map);
//	                if (count > 0) {
//	                    msgList.add(Message.getMessage(param.getLang(), 
//	                            "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingCostcenter"));
//	                    return msgList;
//	                }
//            	}
            }
            
			List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntryList = scmInvCostConsumeEntryBiz.checkStockByReturnWareHouse(scmInvCostConsume.getDcId(), param);
			if(scmInvCostConsumeEntryList != null && !scmInvCostConsumeEntryList.isEmpty()){
				for(ScmInvCostConsumeEntry2 scmInvCostConsumeEntry : scmInvCostConsumeEntryList){
					if(StringUtils.isNotBlank(scmInvCostConsumeEntry.getLot())){
						String[] msparam = {scmInvCostConsumeEntry.getItemNo(),scmInvCostConsumeEntry.getItemName(),
								scmInvCostConsumeEntry.getLot(),(scmInvCostConsumeEntry.getQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
								(scmInvCostConsumeEntry.getInvQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()};
						msgList.add(Message.getMessage(param.getLang(),
								"iscm.inventorymanage.warehouseoutbusiness.ScmInvMaterialReqBill.postBillCheck.error", msparam));
					}else{
						String[] msparam = {scmInvCostConsumeEntry.getItemNo(),scmInvCostConsumeEntry.getItemName(),
								(scmInvCostConsumeEntry.getQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
								(scmInvCostConsumeEntry.getInvQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()};
						msgList.add(Message.getMessage(param.getLang(),
								"iscm.inventorymanage.warehouseoutbusiness.ScmInvMaterialReqBill.postBillCheck.error2", msparam));
					}
				}
			}
		}
		return msgList;
	}

	@Override
	public ScmInvCostConsume2 postBill(ScmInvCostConsume2 scmInvCostConsume,
			Param param) throws AppException {
		scmInvCostConsume = this.selectDirect(scmInvCostConsume.getDcId(), param);
		if(scmInvCostConsume != null){
			if(!StringUtils.equals("A",scmInvCostConsume.getStatus())) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvCostConsume.getDcNo()}));
    		}
			
			//按批次拆单
			splitBillByOutWarehouse(scmInvCostConsume, param);
			
    		List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntryList = scmInvCostConsumeEntryBiz.selectOutEffectRow(scmInvCostConsume.getDcId(), param);
			//即时库存
    		int updateRow = scmInvStockBiz.updateByCostConsume(scmInvCostConsume.getDcId(), param);
    		if(updateRow<scmInvCostConsumeEntryList.size()){
    			throw new AppException("iscm.inventorymanage.common.post.error.rowsnotequal");
    		}
			//期间余额
			scmInvBalBiz.updateByCostConsume(scmInvCostConsume.getDcId(), param);
			if (!(ParamType.Operation.equals(param.getParamType()) && ((OperationParam) param).getOperationId()==629223199L)) {
				scmInvCostConsume.setCheckDate(CalendarUtil.getDate(param));
				scmInvCostConsume.setChecker(param.getUsrCode());
				scmInvCostConsume.setPostDate(CalendarUtil.getDate(param));
			}
			scmInvCostConsume.setStatus("E");
			updateRow = this.updatePostedStatus(scmInvCostConsume, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvCostConsume.getDcNo()}));
			}
		}
		return scmInvCostConsume;
		
	}
	
	private int updatePostedStatus(ScmInvCostConsume2 scmInvCostConsume, Param param) {
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("dcId",scmInvCostConsume.getDcId());
		map.put("checker",scmInvCostConsume.getChecker());
		map.put("checkDate",scmInvCostConsume.getCheckDate()==null?null:FormatUtils.fmtDate(scmInvCostConsume.getCheckDate()));
		map.put("status", scmInvCostConsume.getStatus());
		map.put("postDate", scmInvCostConsume.getPostDate()==null?null:FormatUtils.fmtDateTime(scmInvCostConsume.getPostDate()));
		return ((ScmInvCostConsumeDAO)dao).updatePostedStatus(map);
	}
	/**
	 * 按批次拆单
	 * @param scmInvCostConsume
	 * @param param
	 */
	private void splitBillByOutWarehouse(ScmInvCostConsume2 scmInvCostConsume, Param param) {
		List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntryList = scmInvCostConsumeEntryBiz.selectByDcId(scmInvCostConsume.getDcId(), param);
		if(scmInvCostConsumeEntryList != null && !scmInvCostConsumeEntryList.isEmpty()) {
        	String fields[]={"itemId","id"};
			String sorts[]={"ASC","Desc"};
			scmInvCostConsumeEntryList = (List<ScmInvCostConsumeEntry2>)ListSortUtil.sort(scmInvCostConsumeEntryList, fields,sorts);	//按物资排序
			int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
			HashMap<String,List<ScmInvStock2>> qtyMap = new HashMap<String,List<ScmInvStock2>>();
			int lineId=1;
			for(int i=scmInvCostConsumeEntryList.size()-1; i>=0; i--){ 
				ScmInvCostConsumeEntry2 scmInvCostConsumeEntry = scmInvCostConsumeEntryList.get(i);
				StringBuffer info = new StringBuffer("");
				info.append(scmInvCostConsume.getOrgUnitNo()).append("_")
						.append(scmInvCostConsume.getUseOrgUnitNo()).append("_")
						.append(String.valueOf(scmInvCostConsumeEntry.getItemId()));
				if(!qtyMap.containsKey((info.toString()))){
					//查询计价方式
					Page page = new Page();
					page.setModelClass(ScmMaterial2.class);
					page.setShowCount(Integer.MAX_VALUE);
					page.setSqlCondition("ScmMaterial.id="+scmInvCostConsumeEntry.getItemId());
					ArrayList argList = new ArrayList();
			        argList.add("orgUnitNo="+scmInvCostConsume.getFinOrgUnitNo());
			        argList.add("controlUnitNo=" + param.getControlUnitNo());
                    List<ScmMaterial2> materialList = scmMaterialBiz.queryPage(page, argList, "findBySingleFinAllPage", param);
                    //获取costMethod来决定升序还是降序排序
                    if (materialList != null && materialList.size() > 0 && StringUtils.isNotBlank(materialList.get(0).getCosting())) {
                    	HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("useOrgUnitNo", scmInvCostConsume.getUseOrgUnitNo());		
                        map.put("costOrgUnitNo", scmInvCostConsume.getOrgUnitNo());		
                        map.put("itemId", scmInvCostConsumeEntry.getItemId());
                        map.put("bizDate", scmInvCostConsume.getBizDate());
                        map.put("costMethod", materialList.get(0).getCosting());
                        map.put("unit", scmInvCostConsumeEntry.getUnit());
                        List<ScmInvStock2> stocklist = scmInvStockBiz.findByReturnWarehouse(map, param);
                        qtyMap.put(info.toString(), stocklist);
                    }
				}
				List<ScmInvStock2> stocklist = qtyMap.get(info.toString());
				if(stocklist != null && !stocklist.isEmpty()){
					if(StringUtils.isNotBlank(scmInvCostConsumeEntry.getLot())) {
                		//有批次则先按批次找，如不够再按先进先出拆单
                		for (ScmInvStock2 scmInvStock : stocklist) {
                			if(StringUtils.equals(scmInvCostConsumeEntry.getLot(), scmInvStock.getLot())) {
		                		if(scmInvCostConsumeEntry.getQty().compareTo(BigDecimal.ZERO)>0) {
		                			if(setDataFromStock(scmInvStock,scmInvCostConsumeEntry,amtPrecision,lineId,param))
		                				lineId = lineId + 1;
		                		}else {
		                			break;
		                		}
                			}
                		}
                	}
                	for (ScmInvStock2 scmInvStock : stocklist) {
                		if(scmInvCostConsumeEntry.getQty().compareTo(BigDecimal.ZERO)>0) {
                			if(setDataFromStock(scmInvStock,scmInvCostConsumeEntry,amtPrecision,lineId,param))
                				lineId = lineId + 1;
                		}else {
                			break;
                		}
                	}
                    scmInvCostConsumeEntryBiz.delete(scmInvCostConsumeEntry, param);
				}
			}
		}
		
		//
		List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntryList2 = scmInvCostConsumeEntryBiz.selectByDcId(scmInvCostConsume.getDcId(), param);
		if(scmInvCostConsumeEntryList2 != null && !scmInvCostConsumeEntryList2.isEmpty()){
			//重新设置LineId,防止错乱
			for(int i=0; i<scmInvCostConsumeEntryList2.size(); i++){
				scmInvCostConsumeEntryList2.get(i).setLineId(i+1);
			}
		}
	}
	
	private boolean setDataFromStock(ScmInvStock2 scmInvStock,ScmInvCostConsumeEntry2 scmInvCostConsumeEntry,int amtPrecision,int lineId,Param param) {
		boolean flag = false;
		BigDecimal stockQty = scmInvStock.getQty();
		BigDecimal stockPieQty = scmInvStock.getPieQty();
		BigDecimal stockBaseQty = scmInvStock.getBaseQty();
		BigDecimal stockAmt = (scmInvStock.getAmt());
		BigDecimal stockTaxAmt = (scmInvStock.getTaxAmt());
		if (stockQty.compareTo(scmInvCostConsumeEntry.getQty()) > 0) {
			ScmInvCostConsumeEntry2 scmInvCostConsumeEntry2= new ScmInvCostConsumeEntry2(true);
    		scmInvCostConsumeEntry2.setDcId(scmInvCostConsumeEntry.getDcId());
    		scmInvCostConsumeEntry2.setLineId(lineId);
    		scmInvCostConsumeEntry2.setItemId(scmInvStock.getItemId());
    		scmInvCostConsumeEntry2.setLot(scmInvStock.getLot());
    		scmInvCostConsumeEntry2.setUnit(scmInvStock.getUnit());
    		scmInvCostConsumeEntry2.setQty(scmInvCostConsumeEntry.getQty());
    		scmInvCostConsumeEntry2.setBaseUnit(scmInvStock.getBaseUnit());
    		scmInvCostConsumeEntry2.setBaseQty(scmInvCostConsumeEntry.getBaseQty());
    		scmInvCostConsumeEntry2.setPieUnit(scmInvStock.getPieUnit());
    		scmInvCostConsumeEntry2.setPieQty(scmInvCostConsumeEntry.getPieQty());
    		scmInvCostConsumeEntry2.setPrice(scmInvStock.getPrice());
    		scmInvCostConsumeEntry2.setTaxRate(scmInvStock.getTaxRate());
    		scmInvCostConsumeEntry2.setTaxPrice(scmInvStock.getTaxPrice());
    		scmInvCostConsumeEntry2.setAmt((scmInvCostConsumeEntry2.getQty().multiply(scmInvCostConsumeEntry2.getPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
    		scmInvCostConsumeEntry2.setTaxAmt((scmInvCostConsumeEntry2.getQty().multiply(scmInvCostConsumeEntry2.getTaxPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
    		scmInvCostConsumeEntry2.setExpDate(scmInvStock.getExpDate());
    		scmInvCostConsumeEntry2.setOffset(false);
    		scmInvCostConsumeEntry2.setSourceBillDtlId(scmInvCostConsumeEntry.getSourceBillDtlId());
    		scmInvCostConsumeEntry2.setStockId(scmInvStock.getId());
    		scmInvCostConsumeEntry2.setRemarks(scmInvCostConsumeEntry.getRemarks());
    		scmInvCostConsumeEntry2.setOrgSourceId(scmInvStock.getSourceBillId());
    		scmInvCostConsumeEntry2.setOrgSourceVendorId(scmInvStock.getVendorId());
    		scmInvCostConsumeEntryBiz.add(scmInvCostConsumeEntry2, param);
    		scmInvCostConsumeEntry.setQty(BigDecimal.ZERO);
    		scmInvCostConsumeEntry.setPieQty(BigDecimal.ZERO);
    		scmInvCostConsumeEntry.setBaseQty(BigDecimal.ZERO);
    		scmInvStock.setQty(scmInvStock.getQty().subtract(scmInvCostConsumeEntry2.getQty()));
    		scmInvStock.setPieQty(scmInvStock.getPieQty().subtract(scmInvCostConsumeEntry2.getPieQty()));
    		scmInvStock.setBaseQty(scmInvStock.getBaseQty().subtract(scmInvCostConsumeEntry2.getBaseQty()));
    		scmInvStock.setAmt(scmInvStock.getAmt().subtract(scmInvCostConsumeEntry2.getAmt()));
    		scmInvStock.setTaxAmt(scmInvStock.getTaxAmt().subtract(scmInvCostConsumeEntry2.getTaxAmt()));
    		flag = true;
		}else if (stockQty.compareTo(BigDecimal.ZERO) > 0) {
			ScmInvCostConsumeEntry2 scmInvCostConsumeEntry2= new ScmInvCostConsumeEntry2(true);
    		scmInvCostConsumeEntry2.setDcId(scmInvCostConsumeEntry.getDcId());
    		scmInvCostConsumeEntry2.setLineId(lineId);
    		scmInvCostConsumeEntry2.setItemId(scmInvStock.getItemId());
    		scmInvCostConsumeEntry2.setLot(scmInvStock.getLot());
    		scmInvCostConsumeEntry2.setUnit(scmInvStock.getUnit());
    		scmInvCostConsumeEntry2.setQty(stockQty);
    		scmInvCostConsumeEntry2.setBaseUnit(scmInvStock.getBaseUnit());
    		scmInvCostConsumeEntry2.setBaseQty(stockBaseQty);
    		scmInvCostConsumeEntry2.setPieUnit(scmInvStock.getPieUnit());
    		scmInvCostConsumeEntry2.setPieQty(stockPieQty);
    		scmInvCostConsumeEntry2.setPrice(scmInvStock.getPrice());
    		scmInvCostConsumeEntry2.setTaxRate(scmInvStock.getTaxRate());
    		scmInvCostConsumeEntry2.setTaxPrice(scmInvStock.getTaxPrice());
    		scmInvCostConsumeEntry2.setAmt(stockAmt);
    		scmInvCostConsumeEntry2.setTaxAmt(stockTaxAmt);
    		scmInvCostConsumeEntry2.setExpDate(scmInvStock.getExpDate());
    		scmInvCostConsumeEntry2.setOffset(false);
    		scmInvCostConsumeEntry2.setSourceBillDtlId(scmInvCostConsumeEntry.getSourceBillDtlId());
    		scmInvCostConsumeEntry2.setStockId(scmInvStock.getId());
    		scmInvCostConsumeEntry2.setRemarks(scmInvCostConsumeEntry.getRemarks());
    		scmInvCostConsumeEntry2.setOrgSourceId(scmInvStock.getSourceBillId());
    		scmInvCostConsumeEntry2.setOrgSourceVendorId(scmInvStock.getVendorId());
    		scmInvCostConsumeEntryBiz.add(scmInvCostConsumeEntry2, param);
    		scmInvCostConsumeEntry.setQty(scmInvCostConsumeEntry.getQty().subtract(scmInvCostConsumeEntry2.getQty()));
    		scmInvCostConsumeEntry.setPieQty(scmInvCostConsumeEntry.getPieQty().subtract(scmInvCostConsumeEntry2.getPieQty()));
    		scmInvCostConsumeEntry.setBaseQty(scmInvCostConsumeEntry.getBaseQty().subtract(scmInvCostConsumeEntry2.getBaseQty()));
    		scmInvStock.setQty(BigDecimal.ZERO);
    		scmInvStock.setPieQty(BigDecimal.ZERO);
    		scmInvStock.setBaseQty(BigDecimal.ZERO);
    		scmInvStock.setAmt(BigDecimal.ZERO);
    		scmInvStock.setTaxAmt(BigDecimal.ZERO);
    		flag = true;
		}
		return flag;
	}
	
	private HashMap<String,List<BigDecimal>> getSumMap(List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntryList) {
		if(scmInvCostConsumeEntryList != null && !scmInvCostConsumeEntryList.isEmpty()){ 
			HashMap<String,List<BigDecimal>> qtyMap = new HashMap<String,List<BigDecimal>>();
			for(ScmInvCostConsumeEntry2 scmInvCostConsumeEntry : scmInvCostConsumeEntryList){ 
				StringBuffer info = new StringBuffer("");
				info.append(String.valueOf(scmInvCostConsumeEntry.getItemId())).append("_")
					.append(String.valueOf(scmInvCostConsumeEntry.getUnit())).append("_")
					.append(scmInvCostConsumeEntry.getLot());
				if(!qtyMap.containsKey((info.toString()))){ 
					List<BigDecimal> qtyList = new ArrayList<>();
					BigDecimal sumQty = BigDecimal.ZERO;
					BigDecimal sumPieQty = BigDecimal.ZERO;
					BigDecimal sumBaseQty = BigDecimal.ZERO;
					BigDecimal sumAmt = BigDecimal.ZERO;
					/*if(scmInvCostConsumeEntry.getQty() != null){
						sumQty = sumQty.add(scmInvCostConsumeEntry.getQty());
					}
					if(scmInvCostConsumeEntry.getPieQty() != null){
						sumPieQty = sumPieQty.add(scmInvCostConsumeEntry.getPieQty());
					}
					if(scmInvCostConsumeEntry.getBaseQty() != null){
						sumBaseQty = sumBaseQty.add(scmInvCostConsumeEntry.getBaseQty());
					}
					if(scmInvCostConsumeEntry.getAmt() != null){
						sumAmt = sumAmt.add(scmInvCostConsumeEntry.getAmt());
					}*/
					qtyList.add(sumQty);
					qtyList.add(sumPieQty);
					qtyList.add(sumBaseQty);
					qtyList.add(sumAmt);
					qtyMap.put(info.toString(), qtyList);
				} else {
					List<BigDecimal> qtyList = qtyMap.get((info.toString()));
					BigDecimal sumQty = qtyList.get(0);
					BigDecimal sumPieQty = qtyList.get(1);
					BigDecimal sumBaseQty = qtyList.get(2);
					BigDecimal sumAmt = qtyList.get(3);
					/*if(scmInvCostConsumeEntry.getQty() != null){
						sumQty = sumQty.add(scmInvCostConsumeEntry.getQty());
					}
					if(scmInvCostConsumeEntry.getPieQty() != null){
						sumPieQty = sumPieQty.add(scmInvCostConsumeEntry.getPieQty());
					}
					if(scmInvCostConsumeEntry.getBaseQty() != null){
						sumBaseQty = sumBaseQty.add(scmInvCostConsumeEntry.getBaseQty());
					}
					if(scmInvCostConsumeEntry.getAmt() != null){
						sumAmt = sumAmt.add(scmInvCostConsumeEntry.getAmt());
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
	
	@Override
	public List<String> cancelPostBillCheck(ScmInvCostConsume2 scmInvCostConsume, Param param) throws AppException {
		List<String> msgList = new ArrayList<String>();// 提示列表
		scmInvCostConsume = this.selectDirect(scmInvCostConsume.getDcId(), param);
		if(scmInvCostConsume != null){
	        if(!StringUtils.equals("E",scmInvCostConsume.getStatus())) {
	   			msgList.add(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvCostConsume.getDcNo()}));
	   			return msgList;
			}
        }
		SystemState systemState = systemStateBiz.selectBySystemId(scmInvCostConsume.getFinOrgUnitNo(), 170, param);
        if(systemState==null){
            msgList.add(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
            return msgList;
        }
        if (systemState.getCurrentPeriodId() != scmInvCostConsume.getPeriodId()) {
            msgList.add(Message.getMessage(param.getLang(), 
                    "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmInvCostConsume.getDcNo()}));
            return msgList;
        }
        
        // 检查冻结部门
        List<ScmInvCostConsume2> scmInvCostConsumeList = ((ScmInvCostConsumeDAO) dao).checkCostCenterFree(scmInvCostConsume.getDcId());
        
        if (scmInvCostConsumeList != null && !scmInvCostConsumeList.isEmpty()) {
        	msgList.add(Message.getMessage(param.getLang(), 
                    "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingCostcenter"));
            return msgList;
        }
        
		return null;
	}

	@Override
	public ScmInvCostConsume2 cancelPostBill(
			ScmInvCostConsume2 scmInvCostConsume, Param param)
			throws AppException {
		scmInvCostConsume = this.selectDirect(scmInvCostConsume.getDcId(), param);
		if(scmInvCostConsume != null){
	        if(!StringUtils.equals("E",scmInvCostConsume.getStatus())) {
	        	throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvCostConsume.getDcNo()}));
			}
			//即时库存
			scmInvStockBiz.updateByCancelCostConsume(scmInvCostConsume.getDcId(), param);
			//期间余额
			scmInvBalBiz.updateByCancelCostConsume(scmInvCostConsume.getDcId(), param);
			scmInvCostConsume.setCheckDate(null);
			scmInvCostConsume.setChecker("");
			scmInvCostConsume.setStatus("A");
			scmInvCostConsume.setPostDate(null);
			int updateRow = this.updatePostedStatus(scmInvCostConsume, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvCostConsume.getDcNo()}));
			}
		}
		return scmInvCostConsume;
	}
	
	@Override
	protected void beforeDelete(ScmInvCostConsume2 entity, Param param)	throws AppException {
		ScmInvCostConsume2 scmInvCostConsume = this.selectDirect(entity.getDcId(), param);
		if (!scmInvCostConsume.isGenerate()) {
			if (scmInvCostConsume != null && !StringUtils.equals(scmInvCostConsume.getStatus(), "I")) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error",
						new String[] { entity.getDcNo() }));
			}
		}
	}
	
	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvCostConsume2 entry = (ScmInvCostConsume2) bean.getList().get(0);
		if(entry!=null){
			ScmInvCostConsume2 scmInvCostConsume = this.select(entry.getPK(), param);
			if(!StringUtils.equals(scmInvCostConsume.getStatus(),"I")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
		}
	}
	@Override
	public List<ScmInvCostConsume2> checkUnPostBill(String finOrgUnitNo,long periodId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmInvCostConsumeDAO)dao).checkUnPostBill(map);
	}
	@Override
	public List<ScmInvCostConsume2> checkPostedBill(String finOrgUnitNo,long periodId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmInvCostConsumeDAO)dao).checkPostedBill(map);
	}
    @Override
    public ScmInvCostConsume2 importExcel(ScmInvCostConsumeImPort scmInvCostConsumeImPort, Param param) throws AppException {
        if(scmInvCostConsumeImPort != null) {
            List<OrgCostCenter2> orgCostCenterlist = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOCOST, scmInvCostConsumeImPort.getUseOrgUnitNo(), false, null, param);
            if(orgCostCenterlist==null || orgCostCenterlist.isEmpty()) {
                throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvCostConsume.importExcel.noadmintoCost"));
            }

            CommonBean bean = new CommonBean();
            List<ScmInvCostConsume2> scmInvCostConsumeList = new ArrayList();
            ScmInvCostConsume2 scmInvCostConsume = new ScmInvCostConsume2(true);
            scmInvCostConsume.setBizDate(scmInvCostConsumeImPort.getBizDate());
            scmInvCostConsume.setUseOrgUnitNo(scmInvCostConsumeImPort.getUseOrgUnitNo());
            scmInvCostConsume.setOrgUnitNo(orgCostCenterlist.get(0).getOrgUnitNo());
            scmInvCostConsume.setFinOrgUnitNo(scmInvCostConsumeImPort.getFinOrgUnitNO());
            scmInvCostConsume.setCreator(param.getUsrCode());
            scmInvCostConsume.setCreateDate(CalendarUtil.getDate(param));
            scmInvCostConsume.setStatus("I");
            scmInvCostConsume.setControlUnitNo(param.getControlUnitNo());
            scmInvCostConsumeList.add(scmInvCostConsume);
            bean.setList(scmInvCostConsumeList);
            StringBuffer itemNos = new StringBuffer("");
            List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntryList = new ArrayList<ScmInvCostConsumeEntry2>();
            List<ScmInvCostConsumeImPortDetail> scmInvCostConsumeImPortDetailList = scmInvCostConsumeImPort.getDetailList();
                
            for(ScmInvCostConsumeImPortDetail scmInvCostConsumeImPortDetail : scmInvCostConsumeImPortDetailList) {
                if(StringUtils.isBlank(scmInvCostConsumeImPortDetail.getItemNo())){
                    throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemisnull");
                }
                if(StringUtils.isNotBlank(itemNos.toString()))
                    itemNos.append(",");
                itemNos.append("'").append(scmInvCostConsumeImPortDetail.getItemNo()).append("'");
            }
                
            Page page = new Page();
            page.setModelClass(ScmMaterial2.class);
            page.setShowCount(Integer.MAX_VALUE);
            page.setSqlCondition("ScmMaterial.itemNo in ("+itemNos.toString()+")");
            ArrayList argList = new ArrayList();
            argList.add("orgUnitNo=" + param.getOrgUnitNo());
            argList.add("controlUnitNo=" + param.getControlUnitNo());
            List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "findByInvAllPage", param);
                
            for(ScmInvCostConsumeImPortDetail scmInvCostConsumeImPortDetail : scmInvCostConsumeImPortDetailList) {
                ScmInvCostConsumeEntry2 scmInvCostConsumeEntry = new ScmInvCostConsumeEntry2(true);
                BeanUtils.copyProperties(scmInvCostConsumeImPortDetail,scmInvCostConsumeEntry);
                for(ScmMaterial2 scmMaterial:scmMaterialList){
                    if(StringUtils.equals(scmMaterial.getItemNo(), scmInvCostConsumeImPortDetail.getItemNo())){
                        scmInvCostConsumeEntry.setItemId(scmMaterial.getId());
                        scmInvCostConsumeEntry.setUnit(scmMaterial.getUnitId());
                        break;
                    }
                }
                scmInvCostConsumeEntry.setTaxPrice(scmInvCostConsumeEntry.getPrice());
                scmInvCostConsumeEntry.setTaxAmt(scmInvCostConsumeEntry.getAmt());
                scmInvCostConsumeEntry.setTaxRate(BigDecimal.ZERO);
                scmInvCostConsumeEntryList.add(scmInvCostConsumeEntry);

            }
            bean.setList2(scmInvCostConsumeEntryList);
            bean = this.add(bean, param);
            return (ScmInvCostConsume2) bean.getList().get(0);
        }
        return null;
    }
	@Override
	public ScmInvCostConsume2 submit(ScmInvCostConsume2 scmInvCostConsume, Param param) throws AppException {
		scmInvCostConsume = this.selectDirect(scmInvCostConsume.getDcId(), param);
		if(scmInvCostConsume!=null){
			if(!scmInvCostConsume.getStatus().equals("I")){
				throw new AppException("iscm.inventorymanage.error.commit");
			}else if(scmInvCostConsume.getStatus().equals("I")){
				if(param.getUsrCode()!=null ){
					scmInvCostConsume.setChecker(param.getUsrCode());
					scmInvCostConsume.setSubmitter(param.getUsrCode());
				}
				scmInvCostConsume.setCheckDate(CalendarUtil.getDate(param));
				scmInvCostConsume.setSubmitDate(CalendarUtil.getDate(param));
				scmInvCostConsume.setStatus("A");
				try{
					this.updateStatus(scmInvCostConsume, param);
				}catch(AppException e){
					throw e;
				}
				SystemState systemState = systemStateBiz.selectBySystemId(scmInvCostConsume.getFinOrgUnitNo(), 170, param);
				if(systemState==null){
					throw new AppException("com.armitage.iars.daily.util.disenable.unable");
				}
				if (systemState.getCurrentPeriodId() == scmInvCostConsume.getPeriodId()) {
					if(StringUtils.equals("A", scmInvCostConsume.getStatus())) {
						//通过或部分通过时检查是否自动过帐
						BillType2 billType = billTypeBiz.selectByOrgAndCode(scmInvCostConsume.getFinOrgUnitNo(), "InvCostConsume", param);
						if(billType!=null && billType.isAutoRelease()) {
							List<String> msgList = this.postBillCheck(scmInvCostConsume, param);
							if (msgList != null && !msgList.isEmpty()) {
								StringBuilder detailInfo = new StringBuilder("");
		                        detailInfo.append(Message.getMessage(param.getLang(),"iscm.inventorymanage.scminvcostconsume.post.errorTitle"));
	
								for (String str : msgList) {
		                            detailInfo.append(str).append("\n");
		                        }
								
								throw new AppException(detailInfo.toString(), new String[]{scmInvCostConsume.getDcNo()});
							}
							
							this.postBill(scmInvCostConsume, param);
						}
					}
				}
			}
		}else{
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.find.ordernotexists");
		}
		return scmInvCostConsume;
	}
	
	@Override
	public ScmInvCostConsume2 undoSubmit(ScmInvCostConsume2 scmInvCostConsume, Param param) throws AppException {
		scmInvCostConsume = this.selectDirect(scmInvCostConsume.getDcId(), param);
		if(scmInvCostConsume!=null){
			if(!scmInvCostConsume.getStatus().equals("A")){
				throw new AppException("iscm.inventorymanage.error.cancelCommit");
			}else if(scmInvCostConsume.getStatus().equals("A")){
				scmInvCostConsume.setChecker(null);
				scmInvCostConsume.setSubmitter(null);
				scmInvCostConsume.setCheckDate(null);
				scmInvCostConsume.setSubmitDate(null);
				scmInvCostConsume.setStatus("I");
				try{
					this.updateStatus(scmInvCostConsume, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.find.ordernotexists");
		}
		return scmInvCostConsume;
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvCostConsumeQuery) {
				ScmInvCostConsumeQuery scmInvCostConsumeQuery = (ScmInvCostConsumeQuery) page.getModel();
				if(scmInvCostConsumeQuery.getBizDateFrom()!=null){
					if(scmInvCostConsumeQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmInvCostConsume2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume2.class) + "." +ScmInvCostConsume2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvCostConsumeQuery.getBizDateFrom()),FormatUtils.fmtDate(scmInvCostConsumeQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmInvCostConsume2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume2.class) + "." +ScmInvCostConsume2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvCostConsumeQuery.getBizDateFrom())));
					}
				}else if(scmInvCostConsumeQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmInvCostConsume2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume2.class) + "." +ScmInvCostConsume2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmInvCostConsumeQuery.getBizDateTo())));
				}
				if(scmInvCostConsumeQuery.getCreateDateFrom()!=null){
					if(scmInvCostConsumeQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmInvCostConsume2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume2.class) + "." +ScmInvCostConsume2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvCostConsumeQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvCostConsumeQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmInvCostConsume2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume2.class) + "." +ScmInvCostConsume2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvCostConsumeQuery.getCreateDateFrom())));
					}
				}else if(scmInvCostConsumeQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmInvCostConsume2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume2.class) + "." +ScmInvCostConsume2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvCostConsumeQuery.getCreateDateTo(),1))));
				}
				if(StringUtils.isNotBlank(scmInvCostConsumeQuery.getCostOrgUnitNo())) {
					page.getParam().put(ScmInvCostConsume2.FN_ORGUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume2.class) + "." +ScmInvCostConsume2.FN_ORGUNITNO, QueryParam.QUERY_EQ,String.valueOf(scmInvCostConsumeQuery.getCostOrgUnitNo())));
				}
			}
		}
	}
	@Override
	public List<ScmInvCostConsume2> selectGenerateBill(long periodId,String finOrgUnitNo, Param param)
			throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("periodId", periodId);
		map.put("generate", true);
		map.put("finOrgUnitNo", finOrgUnitNo);
		return ((ScmInvCostConsumeDAO)dao).selectGenerateBill(map);
	}
	@Override
	public ScmInvCostConsume2 doAddCostConsume(CostConsumeAddParams costConsumeAddParams, Param param)
			throws AppException {
		if(costConsumeAddParams!=null) {
			if(StringUtils.isNotBlank(costConsumeAddParams.getRefNo())) {
				//检查请求号，防止重单
				HashMap<String,Object> map = new HashMap<String,Object>();
				map.put("refNo", costConsumeAddParams.getRefNo());
				map.put("controlUnitNo",param.getControlUnitNo());
				List<ScmInvCostConsume2> scmInvCostConsumeList = this.findAll(map, param);
				if(scmInvCostConsumeList!=null && !scmInvCostConsumeList.isEmpty()) {
					throw new AppException("iscm.service.ScmInvCostConsumeBizImpl.doAddCostConsume.duplicaterefno");
				}
			}
			CommonBean bean = new CommonBean();
			List<ScmInvCostConsume2> scmInvCostConsumeList = new ArrayList<>();
			ScmInvCostConsume2 scmInvCostConsume=new ScmInvCostConsume2(true);
			try {
				BeanUtils.copyProperties(costConsumeAddParams, scmInvCostConsume);
				Page orgCompanyPage = new Page();
				orgCompanyPage.setModelClass(OrgCompany2.class);
				orgCompanyPage.setShowCount(Integer.MAX_VALUE);
				List<String> orgCompanyArglist = new ArrayList<>();
				orgCompanyArglist.add("type=to");
				orgCompanyArglist.add("relationType="+OrgUnitRelationType.COSTTOFIN);
				orgCompanyArglist.add("fromOrgUnitNo="+param.getOrgUnitNo());
				List<OrgCompany2> orgCompanyList = orgCompanyBiz.queryPage(orgCompanyPage, orgCompanyArglist, "queryPageEx", param);
				if(orgCompanyList==null || orgCompanyList.isEmpty())
					throw new AppException("iscm.inventorymanage.common.cstOrgUnit.notfinorg");
				scmInvCostConsume.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
				scmInvCostConsume.setStatus("I");
				scmInvCostConsume.setGenerate(false);
				scmInvCostConsume.setOffset(false);
				scmInvCostConsume.setOrgUnitNo(param.getOrgUnitNo());
				scmInvCostConsume.setCreateDate(CalendarUtil.getDate(param));
				scmInvCostConsume.setCreator(param.getUsrCode());
				scmInvCostConsume.setRefNo(costConsumeAddParams.getRefNo());
				if(scmInvCostConsume.getBizDate() != null){
					scmInvCostConsume.setBizDate(FormatUtils.parseDate(FormatUtils.fmtDate(scmInvCostConsume.getBizDate())));
				}
				if(StringUtils.isNotBlank(costConsumeAddParams.getUseDeptNo())) {
					List<OrgAdmin2> orgAdminList2 = orgUnitRelationBiz.findFromOrgUnitByType(OrgUnitRelationType.ADMINTOCOST, param.getOrgUnitNo(), false, null, param);
					if(orgAdminList2 != null && !orgAdminList2.isEmpty()){
						boolean flag = false;
						for(OrgAdmin2 orgAdmin : orgAdminList2){
							if(StringUtils.equals(orgAdmin.getOrgUnitNo(), costConsumeAddParams.getUseDeptNo())){
								flag = true;
							}
						}
						if(!flag){
							throw new AppException("iscm.inventorymanage.cstbusiness.ScmInvCostConsumeBizImpl.error.notadmintocost");
						}
					}
					scmInvCostConsume.setUseOrgUnitNo(costConsumeAddParams.getUseDeptNo());
				}
				scmInvCostConsumeList.add(scmInvCostConsume);
				bean.setList(scmInvCostConsumeList);
				List<CostConsumeDetailParams> detailList = costConsumeAddParams.getDetailList();
				if(detailList!=null && !detailList.isEmpty()){
					StringBuffer itemNos = new StringBuffer("");
					for(CostConsumeDetailParams detailParams:detailList){
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
					argList.add("orgUnitNo="+scmInvCostConsume.getUseOrgUnitNo());
					argList.add("vendorId=0");
			        argList.add("controlUnitNo=" + param.getControlUnitNo());
			        argList.add("wareHouseId=0");
			        argList.add("costCenter=1");
			        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "selectByStockPage", param);
					List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntryList = new ArrayList<>();
					for(CostConsumeDetailParams detailParams:detailList){
						ScmInvCostConsumeEntry2 scmInvCostConsumeEntry = new ScmInvCostConsumeEntry2(true);
						BeanUtils.copyProperties(detailParams, scmInvCostConsumeEntry);
						for(ScmMaterial2 scmMaterial:scmMaterialList){
							if(StringUtils.equals(scmMaterial.getItemNo(), detailParams.getItemNo())){
								scmInvCostConsumeEntry.setItemId(scmMaterial.getId());
								scmInvCostConsumeEntry.setUnit(scmMaterial.getUnitId());
								scmInvCostConsumeEntry.setPieUnit(scmMaterial.getPieUnitId());
								scmInvCostConsumeEntry.setBaseUnit(scmMaterial.getBaseUnitId());
								scmInvCostConsumeEntry.setPrice(scmMaterial.getPrice());
								scmInvCostConsumeEntry.setAmt(scmInvCostConsumeEntry.getQty().multiply(scmMaterial.getPrice()));
								scmInvCostConsumeEntry.setTaxPrice(scmMaterial.getTaxPrice());
								scmInvCostConsumeEntry.setTaxAmt(scmInvCostConsumeEntry.getQty().multiply(scmMaterial.getTaxPrice()));
								break;
							}
						}
						BigDecimal convRate = ScmMaterialUtil.getConvRate(scmInvCostConsumeEntry.getItemId(), scmInvCostConsumeEntry.getUnit(), param);
						scmInvCostConsumeEntry.setBaseQty(scmInvCostConsumeEntry.getQty().multiply(convRate));
						scmInvCostConsumeEntryList.add(scmInvCostConsumeEntry);
					}
					bean.setList2(scmInvCostConsumeEntryList);
				}
				this.add(bean, param);
			} catch (Exception e) {
				throw e;
			}
			
			return scmInvCostConsume;
		}
		return null;
	}
	@Override
	public List<ScmInvCostConsume2> selectGenerateBillBySourceType(String useOrgUnitNos,
			String sourceType, Date bizDate, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("useOrgUnitNos", useOrgUnitNos);
		map.put("sourceType", sourceType);
		map.put("bizDate", FormatUtils.fmtDate(bizDate));
		return ((ScmInvCostConsumeDAO)dao).selectGenerateBillBySourceType(map);
	}
	
	@Override
	public ScmInvCostConsume2 updatePrtCount(ScmInvCostConsume2 scmInvCostConsume, Param param) throws AppException {
		if(scmInvCostConsume.getDcId()>0){
			ScmInvCostConsume2 bill = this.selectDirect(scmInvCostConsume.getDcId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmInvCostConsume;
	}
	
	@Override
	public DataSyncResult dataSync(InvCostConsumeListParams invCostConsumeListParam,List<ScmInvCostConsume2> scmInvCostConsumes, Param param) throws AppException {
		DataSyncResult dataSyncResult = new DataSyncResult();
		if(StringUtils.isBlank(invCostConsumeListParam.getDcNo())&& StringUtils.equals(invCostConsumeListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billNo.notExist");
		}
		if(StringUtils.isBlank(invCostConsumeListParam.getUseOrgUnitNo())&& StringUtils.equals(invCostConsumeListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billOrgUnitNo.notExist");
		}
		if(StringUtils.isBlank(invCostConsumeListParam.getPostStatus())&& StringUtils.equals(invCostConsumeListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billStatus.notExist");
		}
		if(invCostConsumeListParam.getBizDate() == null&& StringUtils.equals(invCostConsumeListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billBizDate.notExist");
		}
		if((invCostConsumeListParam.getDetailList() == null || invCostConsumeListParam.getDetailList().isEmpty())&& StringUtils.equals(invCostConsumeListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billEntry.notExist");
		}
		for(ScmInvCostConsume2 scmInvCostConsume2:scmInvCostConsumes) {
			if(StringUtils.equals(invCostConsumeListParam.getDcNo(), scmInvCostConsume2.getDcNo())) {
				//删除				
				if(StringUtils.equals(invCostConsumeListParam.getDelete(),"Y")) {
					scmInvCostConsumeEntryBiz.deleteByDcId(scmInvCostConsume2.getDcId(), param);
					((ScmInvCostConsumeDAO) dao).delete(scmInvCostConsume2.getPK()+"");
					dataSyncResult.setNo(invCostConsumeListParam.getDcNo());
					dataSyncResult.setErrCode("0");
					return dataSyncResult;
				}
				//时间戳判断,目前还不确定有没有的传
				ArrayList<ScmInvCostConsumeEntry2> scmInvCostConsumeEntryAdd = new ArrayList<ScmInvCostConsumeEntry2>();
				for(InvCostConsumeDetailParams invCostConsumeDetaiParams :invCostConsumeListParam.getDetailList()) {
					ScmInvCostConsumeEntry2 scmInvCostConsumeEntry = new ScmInvCostConsumeEntry2(true);
					if(!scmMatrialMap.containsKey(invCostConsumeDetaiParams.getItemNo())) {
						ScmMaterial2 selectByItemNo = scmMaterialBiz.selectByItemNo(param.getControlUnitNo(), invCostConsumeDetaiParams.getItemNo(), param);
						if(selectByItemNo == null)
							throw new AppException("iscm.api.datePut.billItemNo.notExist");
						scmMatrialMap.put(selectByItemNo.getItemNo(), selectByItemNo.getId());
					}
					BeanUtils.copyProperties(invCostConsumeDetaiParams, scmInvCostConsumeEntry);
					scmInvCostConsumeEntry.setItemId(scmMatrialMap.get(invCostConsumeDetaiParams.getItemNo()));
					if(!scmUnitMap.containsKey(scmInvCostConsumeEntry.getItemId())) {
						ScmMaterialInventory2 selectByItemIdAndOrgUnitNo = scmMaterialInventoryBiz.selectByItemId(scmInvCostConsumeEntry.getItemId(), param.getOrgUnitNo(), param.getControlUnitNo(), param);
						if(selectByItemIdAndOrgUnitNo == null)
							throw new AppException("iscm.api.datePut.billUnitNo.notExist");
						scmUnitMap.put(selectByItemIdAndOrgUnitNo.getItemId(), selectByItemIdAndOrgUnitNo.getUnitId());
					}
					scmInvCostConsumeEntry.setUnit(scmUnitMap.get(scmInvCostConsumeEntry.getItemId()));
					scmInvCostConsumeEntry.setDcId(scmInvCostConsume2.getDcId());
					scmInvCostConsumeEntry.setDcNo(scmInvCostConsume2.getDcNo());
					scmInvCostConsumeEntry.setBaseQty(invCostConsumeDetaiParams.getQty());
					scmInvCostConsumeEntry.setInvDate(scmInvCostConsume2.getBizDate());
					scmInvCostConsumeEntryAdd.add(scmInvCostConsumeEntry);
				}
				//2.过账的单据 第二次过账 这里最好再根据上面时间戳过滤
				if(StringUtils.equals(invCostConsumeListParam.getPostStatus(), "1")&& "E,C".contains(scmInvCostConsume2.getStatus())) {
					throw new AppException("iscm.api.business.datasync.controller.posted");
				//3、	非过账 》过账
				}else if(!"E,C".contains(scmInvCostConsume2.getStatus())&& StringUtils.equals(invCostConsumeListParam.getPostStatus(), "1")) {
					//更新单据
					BeanUtils.copyProperties(invCostConsumeListParam, scmInvCostConsume2);
					scmInvCostConsume2.setStatus("E");
					List<OrgCostCenter2> OrgCostCenterList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOCOST,scmInvCostConsume2.getUseOrgUnitNo(), false, null, param);
					if (OrgCostCenterList == null || OrgCostCenterList.isEmpty()) {
						throw new AppException("iscm.inventorymanage.cstbusiness.service.impl.ScmInvMoveBillBizImpl.noOutCst");
					}
					scmInvCostConsume2.setOrgUnitNo(OrgCostCenterList.get(0).getOrgUnitNo());
					List<OrgCompany2> orgCompanylist2 = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.COSTTOFIN, scmInvCostConsume2.getUseOrgUnitNo(), false, null, param);
					if(orgCompanylist2 != null && !orgCompanylist2.isEmpty()){
						scmInvCostConsume2.setFinOrgUnitNo(orgCompanylist2.get(0).getOrgUnitNo());
					}else {
						throw new AppException("iscm.api.datePut.billOrgUnitNo.notToFin");
					}
					update(scmInvCostConsume2, param);
					scmInvCostConsumeEntryBiz.deleteByDcId(scmInvCostConsume2.getDcId(), param);
					scmInvCostConsumeEntryBiz.add(scmInvCostConsumeEntryAdd, param);
					//期间余额
					scmInvBalBiz.updateByCostConsume(scmInvCostConsume2.getDcId(), param);
					scmInvBalBiz.addByCostConsumPost(scmInvCostConsume2.getDcId(), param);
					//返回结果					
					dataSyncResult.setNo(invCostConsumeListParam.getDcNo());
					dataSyncResult.setErrCode("0");
					return dataSyncResult;
				//4.单据反过账					
				}else if("E,C".contains(scmInvCostConsume2.getStatus()) && StringUtils.equals(invCostConsumeListParam.getPostStatus(), "0")){
					throw new AppException("iscm.api.business.datasync.controller.cancelPost");
				//5.更新单据					
				}else{
					BeanUtils.copyProperties(invCostConsumeListParam, scmInvCostConsume2);
					List<OrgCostCenter2> OrgCostCenterList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOCOST,scmInvCostConsume2.getUseOrgUnitNo(), false, null, param);
					if (OrgCostCenterList == null || OrgCostCenterList.isEmpty()) {
						throw new AppException("iscm.inventorymanage.cstbusiness.service.impl.ScmInvMoveBillBizImpl.noOutCst");
					}
					scmInvCostConsume2.setOrgUnitNo(OrgCostCenterList.get(0).getOrgUnitNo());
					List<OrgCompany2> orgCompanylist2 = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.COSTTOFIN, scmInvCostConsume2.getUseOrgUnitNo(), false, null, param);
					if(orgCompanylist2 != null && !orgCompanylist2.isEmpty()){
						scmInvCostConsume2.setFinOrgUnitNo(orgCompanylist2.get(0).getOrgUnitNo());
					}else {
						throw new AppException("iscm.api.datePut.billOrgUnitNo.notToFin");
					}
					update(scmInvCostConsume2, param);
					scmInvCostConsumeEntryBiz.deleteByDcId(scmInvCostConsume2.getDcId(), param);
					scmInvCostConsumeEntryBiz.add(scmInvCostConsumeEntryAdd, param);
					dataSyncResult.setNo(invCostConsumeListParam.getDcNo());
					dataSyncResult.setErrCode("0");
					return dataSyncResult;
				}
			}
		}
		if(StringUtils.equals(invCostConsumeListParam.getDelete(),"Y")) {
			dataSyncResult.setNo(invCostConsumeListParam.getDcNo());
			dataSyncResult.setErrCode("0");
			return dataSyncResult;
		}
		ArrayList<ScmInvCostConsume2> scmInvMoveBillAdd = new ArrayList<ScmInvCostConsume2>();
		ArrayList<ScmInvCostConsumeEntry2> scmInvMoveBillEntryAdd2 = new ArrayList<ScmInvCostConsumeEntry2>();
		ScmInvCostConsume2 scmInvCostConsume = new ScmInvCostConsume2(true);
		BeanUtils.copyProperties(invCostConsumeListParam, scmInvCostConsume);
		beforeAdd(scmInvCostConsume, param);
		List<OrgCostCenter2> OrgCostCenterList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOCOST,scmInvCostConsume.getUseOrgUnitNo(), false, null, param);
		if (OrgCostCenterList == null || OrgCostCenterList.isEmpty()) {
			throw new AppException("iscm.inventorymanage.cstbusiness.service.impl.ScmInvMoveBillBizImpl.noOutCst");
		}
		scmInvCostConsume.setStatus("I");
		if(StringUtils.equals(invCostConsumeListParam.getPostStatus(),"1")) 
			scmInvCostConsume.setStatus("E");
		scmInvCostConsume.setOrgUnitNo(OrgCostCenterList.get(0).getOrgUnitNo());
		scmInvCostConsume.setCreator("");
		scmInvCostConsume.setCreateDate(invCostConsumeListParam.getBizDate());
		scmInvCostConsume.setDcNo(invCostConsumeListParam.getDcNo());
		scmInvCostConsume.setControlUnitNo(param.getControlUnitNo());
		List<OrgCompany2> orgCompanylist2 = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.COSTTOFIN, scmInvCostConsume.getUseOrgUnitNo(), false, null, param);
		if(orgCompanylist2 != null && !orgCompanylist2.isEmpty()){
			scmInvCostConsume.setFinOrgUnitNo(orgCompanylist2.get(0).getOrgUnitNo());
		}else {
			throw new AppException("iscm.api.datePut.billOrgUnitNo.notToFin");
		}
		((ScmInvCostConsumeDAO) dao).add(scmInvCostConsume);
		for(InvCostConsumeDetailParams invCostConsumeDetailParams :invCostConsumeListParam.getDetailList()) {
			ScmInvCostConsumeEntry2 scmInvCostConsumeEntry = new ScmInvCostConsumeEntry2(true);
			BeanUtils.copyProperties(invCostConsumeDetailParams, scmInvCostConsumeEntry);
			if(!scmMatrialMap.containsKey(invCostConsumeDetailParams.getItemNo())) {
				ScmMaterial2 selectByItemNo = scmMaterialBiz.selectByItemNo(param.getControlUnitNo(), invCostConsumeDetailParams.getItemNo(), param);
				if(selectByItemNo == null)
					throw new AppException("iscm.api.datePut.billItemNo.notExist");
				scmMatrialMap.put(selectByItemNo.getItemNo(), selectByItemNo.getId());
			}
			scmInvCostConsumeEntry.setItemId(scmMatrialMap.get(invCostConsumeDetailParams.getItemNo()));
			if(!scmUnitMap.containsKey(scmInvCostConsumeEntry.getItemId())) {
				ScmMaterialInventory2 selectByItemIdAndOrgUnitNo = scmMaterialInventoryBiz.selectByItemId(scmInvCostConsumeEntry.getItemId(), param.getOrgUnitNo(), param.getControlUnitNo(), param);
				if(selectByItemIdAndOrgUnitNo == null)
					throw new AppException("iscm.api.datePut.billUnitNo.notExist");
				scmUnitMap.put(selectByItemIdAndOrgUnitNo.getItemId(), selectByItemIdAndOrgUnitNo.getUnitId());
			}
			scmInvCostConsumeEntry.setStatus("I");
			if(StringUtils.equals(invCostConsumeListParam.getPostStatus(),"1")) 
				scmInvCostConsumeEntry.setStatus("E");
			scmInvCostConsumeEntry.setUnit(scmUnitMap.get(scmInvCostConsumeEntry.getItemId()));
			scmInvCostConsumeEntry.setDcNo(scmInvCostConsume.getDcNo());
			scmInvCostConsumeEntry.setBaseQty(invCostConsumeDetailParams.getQty());
			scmInvCostConsumeEntry.setInvDate(scmInvCostConsume.getBizDate());
			scmInvCostConsumeEntry.setDcId(scmInvCostConsume.getDcId());
			scmInvMoveBillEntryAdd2.add(scmInvCostConsumeEntry);
		}
		scmInvCostConsumeEntryBiz.add(scmInvMoveBillEntryAdd2, param);
		if(StringUtils.equals(invCostConsumeListParam.getPostStatus(),"1")) {
			//期间余额
			scmInvBalBiz.updateByCostConsume(scmInvCostConsume.getDcId(), param);
			scmInvBalBiz.addByCostConsumPost(scmInvCostConsume.getDcId(), param);
		}
		dataSyncResult.setNo(invCostConsumeListParam.getDcNo());
		dataSyncResult.setErrCode("0");
		return dataSyncResult;
	}
	@Override
	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map) {
		return ((ScmInvCostConsumeDAO)dao).countUnPostBill(map);
	}

}
