package com.armitage.server.iscm.inventorymanage.AllocationApplication.service.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.activity.util.ActivityUtil;
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
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.dao.ScmInvMoveReqBillDAO;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBill;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBillEntry2;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.service.ScmInvMoveReqBillBiz;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.service.ScmInvMoveReqBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.service.ScmInvStockTransferBillBiz;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.service.ScmInvStockTransferBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmInvMoveReqBillBiz")
public class ScmInvMoveReqBillBizImpl extends BaseBizImpl<ScmInvMoveReqBill> implements ScmInvMoveReqBillBiz{
	
	private UsrBiz usrBiz;
	private OrgUnitBiz orgUnitBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private ScmInvMoveReqBillEntryBiz scmInvMoveReqBillEntryBiz;
	private ScmInvStockTransferBillBiz scmInvStockTransferBillBiz;
	private ScmInvStockTransferBillEntryBiz scmInvStockTransferBillEntryBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private PeriodCalendarBiz periodCalendarBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private OrgStorageBiz orgStorageBiz;
	private BillTypeBiz billTypeBiz;
	private ScmBillPendingBiz scmBillPendingBiz;
	
	public OrgUnitRelationBiz getOrgUnitRelationBiz() {
		return orgUnitRelationBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}

	public ScmInvStockTransferBillBiz getScmInvStockTransferBillBiz() {
		return scmInvStockTransferBillBiz;
	}

	public void setScmInvStockTransferBillBiz(ScmInvStockTransferBillBiz scmInvStockTransferBillBiz) {
		this.scmInvStockTransferBillBiz = scmInvStockTransferBillBiz;
	}

	public ScmInvStockTransferBillEntryBiz getScmInvStockTransferBillEntryBiz() {
		return scmInvStockTransferBillEntryBiz;
	}

	public void setScmInvStockTransferBillEntryBiz(ScmInvStockTransferBillEntryBiz scmInvStockTransferBillEntryBiz) {
		this.scmInvStockTransferBillEntryBiz = scmInvStockTransferBillEntryBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}

	public void setScmInvMoveReqBillEntryBiz(ScmInvMoveReqBillEntryBiz scmInvMoveReqBillEntryBiz) {
		this.scmInvMoveReqBillEntryBiz = scmInvMoveReqBillEntryBiz;
	}

	public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}
	
	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}
	
	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}

	public void setScmBillPendingBiz(ScmBillPendingBiz scmBillPendingBiz) {
		this.scmBillPendingBiz = scmBillPendingBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvMoveReqBill.class) + "." + ScmInvMoveReqBill.FN_REQORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvMoveReqBill.class) + "." + ScmInvMoveReqBill.FN_REQORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvMoveReqBill.class) + "." + ScmInvMoveReqBill.FN_REQORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvMoveReqBill.class) + "." + ScmInvMoveReqBill.FN_REQORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		if(list != null && !list.isEmpty()){
			for(ScmInvMoveReqBill scmInvMoveReqBill : (List<ScmInvMoveReqBill>)list){
				setConvertMap(scmInvMoveReqBill,param);
			}
		}
	}

	private void setConvertMap(ScmInvMoveReqBill scmInvMoveReqBill, Param param) {
		if(scmInvMoveReqBill!=null) {
			if(StringUtils.isNotBlank(scmInvMoveReqBill.getReqOrgUnitNo())){
				//申调库存组织
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveReqBill.getReqOrgUnitNo(), param);
				if (orgBaseUnit!= null) {
					scmInvMoveReqBill.setConvertMap(ScmInvMoveReqBill.FN_REQORGUNITNO, orgBaseUnit);
				}
			}
			if(StringUtils.isNotBlank(scmInvMoveReqBill.getOutOrgUnitNo())){
				//发货组织
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveReqBill.getOutOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmInvMoveReqBill.setConvertMap(ScmInvMoveReqBill.FN_OUTORGUNITNO, orgBaseUnit);
				}
			}
			if (scmInvMoveReqBill.getReqWareHouseId() > 0){
				//仓库
				ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvMoveReqBill.getReqWareHouseId(), param);
				if (scmInvWareHouse != null) {
					scmInvMoveReqBill.setConvertMap(ScmInvMoveReqBill.FN_REQWAREHOUSEID, scmInvWareHouse);
				}
			}
			if (StringUtils.isNotBlank(scmInvMoveReqBill.getCreator())){
				//制单人
				Usr usr = usrBiz.selectByCode(scmInvMoveReqBill.getCreator(), param);
				if (usr != null) {
					scmInvMoveReqBill.setConvertMap(ScmInvMoveReqBill.FN_CREATOR, usr);
					if(scmInvMoveReqBill.getDataDisplayMap()==null){
						scmInvMoveReqBill.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
					}
					scmInvMoveReqBill.getDataDisplayMap().put(ScmInvMoveReqBill.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
				}
			}
			if (StringUtils.isNotBlank(scmInvMoveReqBill.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmInvMoveReqBill.getEditor(), param);
				if (usr != null) {
					scmInvMoveReqBill.setConvertMap(ScmInvMoveReqBill.FN_EDITOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmInvMoveReqBill.getChecker())){
				//审核人
				Usr usr = usrBiz.selectByCode(scmInvMoveReqBill.getChecker(), param);
				if (usr != null) {
					scmInvMoveReqBill.setConvertMap(ScmInvMoveReqBill.FN_CHECKER, usr);
				}
			}
		}
	}
	@Override
	protected void afterSelect(ScmInvMoveReqBill entity, Param param) throws AppException {
		if (entity != null){
			setConvertMap(entity,param);
		}
	}
	
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmInvMoveReqBill scmInvMoveReqBill = (ScmInvMoveReqBill) bean.getList().get(0);
		HashMap<String,Object> cacheMap = new HashMap<String,Object>();
		if(scmInvMoveReqBill != null && scmInvMoveReqBill.getReqId() > 0){
			List<ScmInvMoveReqBillEntry2> scmInvMoveReqBillEntryList = scmInvMoveReqBillEntryBiz.selectByReqId(scmInvMoveReqBill.getReqId(), param);
			if(scmInvMoveReqBillEntryList != null && !scmInvMoveReqBillEntryList.isEmpty()){
				for(ScmInvMoveReqBillEntry2 scmInvMoveReqBillEntry : scmInvMoveReqBillEntryList){
					if (scmInvMoveReqBillEntry.getReqWareHouseId() > 0){
						//仓库
						ScmInvWareHouse scmInvWareHouse = (ScmInvWareHouse) cacheMap.get(ClassUtils.getFinalModelSimpleName(ScmInvWareHouse.class)+"_"+scmInvMoveReqBillEntry.getReqWareHouseId());
						if(scmInvWareHouse==null){
							scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvMoveReqBillEntry.getReqWareHouseId(), param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(ScmInvWareHouse.class)+"_"+scmInvMoveReqBillEntry.getReqWareHouseId(),scmInvWareHouse);
						}
						if (scmInvWareHouse != null) {
							scmInvMoveReqBillEntry.setConvertMap(ScmInvMoveReqBillEntry2.FN_REQWAREHOUSEID, scmInvWareHouse);
						}
					}
					if(StringUtils.isNotBlank(scmInvMoveReqBillEntry.getUseOrgUnitNo())){
						//组织
						OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvMoveReqBillEntry.getUseOrgUnitNo());
						if(orgBaseUnit==null){
							orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveReqBillEntry.getUseOrgUnitNo(), param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvMoveReqBillEntry.getUseOrgUnitNo(),orgBaseUnit);
						}
						if (orgBaseUnit != null) {
							scmInvMoveReqBillEntry.setConvertMap(ScmInvMoveReqBillEntry2.FN_USEORGUNITNO, orgBaseUnit);
						}
					}
				}
				bean.setList2(scmInvMoveReqBillEntryList);
			}
		}
	}

	@Override
	protected void beforeAdd(ScmInvMoveReqBill entity, Param param) throws AppException {
		if(entity != null){
			String code = CodeAutoCalUtil.getBillCode("InvMoveReqBill", entity, param);
			entity.setReqNo(code);
		}
		//获取期间
		PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(entity.getBizDate(), param);
		if(periodCalendar==null){
			throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
		}
		entity.setPeriodId(periodCalendar.getPeriodId());
		entity.setAccountYear(periodCalendar.getAccountYear());
		entity.setAccountPeriod(periodCalendar.getAccountPeriod());
	}
	
	@Override
	protected void beforeUpdate(ScmInvMoveReqBill oldEntity,ScmInvMoveReqBill newEntity, Param param) throws AppException {
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
		ScmInvMoveReqBill scmInvMoveReqBill = (ScmInvMoveReqBill) bean.getList().get(0);
		if(scmInvMoveReqBill != null && scmInvMoveReqBill.getReqId() > 0){
			//新增报价明细
			List<ScmInvMoveReqBillEntry2> scmInvMoveReqBillEntryList = bean.getList2();
			if(scmInvMoveReqBillEntryList != null && !scmInvMoveReqBillEntryList.isEmpty()){
				int lineId = 1;
				for(ScmInvMoveReqBillEntry2 scmInvMoveReqBillEntry : scmInvMoveReqBillEntryList){
					scmInvMoveReqBillEntry.setLineId(lineId);
					scmInvMoveReqBillEntry.setReqId(scmInvMoveReqBill.getReqId());
					scmInvMoveReqBillEntry.setReqWareHouseId(scmInvMoveReqBill.getReqWareHouseId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvMoveReqBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvMoveReqBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvMoveReqBillEntry.getItemId(), scmInvMoveReqBillEntry.getUnit(), param);//库存单位转换系数
					scmInvMoveReqBillEntry.setBaseQty(scmInvMoveReqBillEntry.getQty().multiply(invConvRate));
					scmInvMoveReqBillEntryBiz.add(scmInvMoveReqBillEntry, param);
					lineId = lineId+1;
				}
			}
		}
	}
	

	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvMoveReqBill scmInvMoveReqBill = (ScmInvMoveReqBill) bean.getList().get(0);
		if(scmInvMoveReqBill != null && scmInvMoveReqBill.getReqId() > 0){
			//更新报价明细
			List<ScmInvMoveReqBillEntry2> scmInvMoveReqBillEntryList = bean.getList2();
			if(scmInvMoveReqBillEntryList != null && !scmInvMoveReqBillEntryList.isEmpty()){
				int lineId = 1;
				for(ScmInvMoveReqBillEntry2 scmInvMoveReqBillEntry : scmInvMoveReqBillEntryList){
					scmInvMoveReqBillEntry.setReqId(scmInvMoveReqBill.getReqId());
					if (StringUtils.equals("I", scmInvMoveReqBill.getStatus())) {
						scmInvMoveReqBillEntry.setLineId(lineId);
					}
					scmInvMoveReqBillEntry.setReqId(scmInvMoveReqBill.getReqId());
					scmInvMoveReqBillEntry.setReqWareHouseId(scmInvMoveReqBill.getReqWareHouseId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvMoveReqBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvMoveReqBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvMoveReqBillEntry.getItemId(), scmInvMoveReqBillEntry.getUnit(), param);//库存单位转换系数
					scmInvMoveReqBillEntry.setBaseQty(scmInvMoveReqBillEntry.getQty().multiply(invConvRate));
					lineId = lineId+1;
				}
				scmInvMoveReqBillEntryBiz.update(scmInvMoveReqBill, scmInvMoveReqBillEntryList, ScmInvMoveReqBillEntry2.FN_REQID, param);
			}
		}
	}
	
	@Override
	protected void afterDelete(ScmInvMoveReqBill entity, Param param) throws AppException {
		if(entity != null && entity.getReqId() > 0){
			//删除报价明细
			scmInvMoveReqBillEntryBiz.deleteByReqId(entity.getReqId(), param);
		}
	}
	
	
	private ScmInvMoveReqBill updateStatus(ScmInvMoveReqBill scmInvMoveReqBill, Param param) throws AppException {
		if(scmInvMoveReqBill != null){
			ScmInvMoveReqBill scmInvMoveReqBill2 = this.updateDirect(scmInvMoveReqBill, param);
			if(scmInvMoveReqBill2 != null){
				scmInvMoveReqBillEntryBiz.updateRowStatusByReqId(scmInvMoveReqBill2.getReqId(), scmInvMoveReqBill2.getStatus(), scmInvMoveReqBill.getChecker(), scmInvMoveReqBill.getCheckDate(), param);
				return scmInvMoveReqBill2;
			}
		}
		return null;
	}

	@Override
	public List<ScmInvMoveReqBill> selectByReqId(long reqId, Param param)
			throws AppException {
		if(reqId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("reqId", reqId);
			return ((ScmInvMoveReqBillDAO) dao).selectByReqId(map);
		}
		return null;
	}

	@Override
	protected void beforeDelete(ScmInvMoveReqBill entity, Param param)
			throws AppException {
		ScmInvMoveReqBill scmInvMoveReqBill = this.selectDirect(entity.getReqId(), param);
		if(scmInvMoveReqBill!=null && !StringUtils.equals(scmInvMoveReqBill.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getReqNo()}));
		}
	}
	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvMoveReqBill entry = (ScmInvMoveReqBill) bean.getList().get(0);
		if(entry!=null){
			ScmInvMoveReqBill scmInvMoveReqBill = this.selectDirect(entry.getPK(), param);
			if(!StringUtils.equals(scmInvMoveReqBill.getStatus(),"I")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
		}
	}

	@Override
	public ScmInvMoveReqBill submit(ScmInvMoveReqBill scmInvMoveReqBill, Param param) throws AppException {
		ScmInvMoveReqBill invBill = null;
		if(scmInvMoveReqBill.getReqId()>0){
			invBill = this.selectDirect(scmInvMoveReqBill.getReqId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmInvMoveReqBill.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmInvMoveReqBill.FN_REQNO,
					new QueryParam(ScmInvMoveReqBill.FN_REQNO, QueryParam.QUERY_EQ,
							scmInvMoveReqBill.getReqNo()));
			
			List<ScmInvMoveReqBill> scmInvMoveReqBillList =this.findPage(page, param);
			if(scmInvMoveReqBillList!=null && !scmInvMoveReqBillList.isEmpty()){
				invBill = scmInvMoveReqBillList.get(0);
			}
		}
		if(invBill!=null) {
			if(!invBill.getStatus().equals("I")){
				throw new AppException("iscm.purchasemanage.error.commit");
			}else if(invBill.getStatus().equals("I")){
				BillType2 billType = billTypeBiz.selectByOrgAndCode(invBill.getFinOrgUnitNo(), "InvMoveReqBill", param);
				boolean needAudit = false;
				if(billType!=null && billType.isNeedAudit())
					needAudit = true;
				
				if(needAudit){
					//发起流程
					List<ScmInvMoveReqBillEntry2> scmInvMoveReqBillEntryList = scmInvMoveReqBillEntryBiz.selectByReqId(scmInvMoveReqBill.getReqId(), param);
					
					CommonBean bean = new CommonBean();
					List<ScmInvMoveReqBill> scmInvMoveReqBillList = new ArrayList();
					scmInvMoveReqBillList.add(invBill);
					bean.setList(scmInvMoveReqBillList);
					bean.setList2(scmInvMoveReqBillEntryList);
					String processInstanceId = ActivityUtil.startProcessInstance(bean, billType.getId(), billType.getWorkFlowNo(), param);
					invBill.setWorkFlowNo(processInstanceId);
					ActivityUtil activityUtil = new ActivityUtil();
					//判断当前流程是否结束
					boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
					if(isCompleteTask) {
						if(param.getUsrCode()!=null ){
							invBill.setChecker(param.getUsrCode());
							invBill.setSubmitter(param.getUsrCode());
						}
						invBill.setCheckDate(CalendarUtil.getDate(param));
						invBill.setStatus("A");
					}else {
						invBill.setStatus("D");
						String usrList= ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId,param);
						if(StringUtils.isNotBlank(usrList)) {
							scmBillPendingBiz.addPendingBill(usrList, invBill, param);
//							PurQuotationParams purQuotationParams = new PurQuotationParams();
//							purQuotationParams.setPqNo(invBill.getPqNo());
//							AuditMsgUtil.sendAuditMsg(billType.getBillCode(), invBill, purQuotationParams, usrList, param);
						}
					}
				}else{
					if(param.getUsrCode()!=null ){
						invBill.setEditor(param.getUsrCode());
						invBill.setSubmitter(param.getUsrCode());
					}
					invBill.setEditDate(CalendarUtil.getDate(param));
					invBill.setSubmitDate(CalendarUtil.getDate(param));
					invBill.setStatus("A");
				}
				
				try{
					this.updateStatus(invBill, param);
				}catch(AppException e){
					throw e;
				}
				if(StringUtils.contains("A,B", invBill.getStatus())) {
					//通过或部分通过时检查是否自动下达
					if(billType!=null && billType.isAutoRelease()) {
						this.release(invBill, param);
					}
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return invBill;
	}

	@Override
	public ScmInvMoveReqBill undoSubmit(ScmInvMoveReqBill scmInvMoveReqBill, Param param) throws AppException {
		ScmInvMoveReqBill invBill = null;
		if(scmInvMoveReqBill.getReqId()>0){
			invBill = this.selectDirect(scmInvMoveReqBill.getReqId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmInvMoveReqBill.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmInvMoveReqBill.FN_REQNO,
					new QueryParam(ScmInvMoveReqBill.FN_REQNO, QueryParam.QUERY_EQ,
							scmInvMoveReqBill.getReqNo()));
			
			List<ScmInvMoveReqBill> scmInvMoveReqBillList =this.findPage(page, param);
			if(scmInvMoveReqBillList!=null && !scmInvMoveReqBillList.isEmpty()){
				invBill = scmInvMoveReqBillList.get(0);
			}
		}
		if(invBill!=null) {
			BillType2 billType = billTypeBiz.selectByOrgAndCode(scmInvMoveReqBill.getFinOrgUnitNo(), "InvMoveReqBill", param);
			boolean needAudit = false;
			if(billType!=null && billType.isNeedAudit() && StringUtils.isNotBlank(invBill.getWorkFlowNo()))
				needAudit = true;
			
			//单据设置审批流程，但设置没有审批节点，状态为通过，可以取消通过
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ScmBillPending.FN_BILLID, new QueryParam(ScmBillPending.FN_BILLID, QueryParam.QUERY_EQ, String.valueOf(invBill.getReqId())));
			map.put(ScmBillPending.FN_BILLTYPE, new QueryParam(ScmBillPending.FN_BILLTYPE, QueryParam.QUERY_EQ, billType.getBillCode()));
			List<ScmBillPending> scmBillPendingList = scmBillPendingBiz.findAll(map, param);

			if(needAudit){
				if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
					if(!StringUtils.equals(invBill.getStatus(),"A"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				} else {
					if(!StringUtils.equals(invBill.getStatus(),"D"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				}
			}else{
				//启用审批进入审核中(再调整为启用工作流)的单据的状态不是A
				if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
					if(!StringUtils.equals(invBill.getStatus(),"A"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				} else {
					if(!StringUtils.contains("A,D", invBill.getStatus()))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				}
				
			} 
			if(invBill.getStatus().equals("A") || invBill.getStatus().equals("D")){
				if(param.getUsrCode()!=null ){
					invBill.setChecker(null);
					invBill.setSubmitter(null);
				}
				invBill.setCheckDate(null);
				invBill.setSubmitDate(null);
				invBill.setStatus("I");
				try{
					this.updateStatus(invBill, param);
					scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),invBill, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return invBill;
	}

	@Override
	public ScmInvMoveReqBill release(ScmInvMoveReqBill scmInvMoveReqBill, Param param) throws AppException {
		if(!StringUtils.equals("A",scmInvMoveReqBill.getStatus()))
			throw new AppException("iscm.purchasemanage.error.release");
		scmInvMoveReqBill.setStatus("E");
		return this.updateDirect(scmInvMoveReqBill, param);
	}

	@Override
	public ScmInvMoveReqBill undoRelease(ScmInvMoveReqBill scmInvMoveReqBill, Param param) throws AppException {
		if(!StringUtils.equals("E",scmInvMoveReqBill.getStatus()))
			throw new AppException("iscm.purchasemanage.error.cancelRelease");
		scmInvMoveReqBill.setStatus("A");
		return this.updateDirect(scmInvMoveReqBill, param);
	}

	@Override
	public ScmInvMoveReqBill finish(ScmInvMoveReqBill scmInvMoveReqBill, Param param) throws AppException {
		ScmInvMoveReqBill purBill = null;
		if(scmInvMoveReqBill.getReqId()>0){
			purBill = this.selectDirect(scmInvMoveReqBill.getReqId(),param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmInvMoveReqBill.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmInvMoveReqBill.FN_REQNO,new QueryParam(ScmInvMoveReqBill.FN_REQNO, QueryParam.QUERY_EQ,scmInvMoveReqBill.getReqNo()));
			
			List<ScmInvMoveReqBill> scmBillList =this.findPage(page, param);
			if(scmBillList!=null && !scmBillList.isEmpty()){
				purBill=scmBillList.get(0);
			}
		}
		if(purBill!=null){
			if(!purBill.getStatus().equals("E")){
				throw new AppException("iscm.purchasemanage.error.finish");
			}else if(purBill.getStatus().equals("E")){
				if(param.getUsrCode()!=null ){
					purBill.setChecker(param.getUsrCode());
					purBill.setCheckDate(CalendarUtil.getDate(param));
				}
				purBill.setStatus("C");
				try{
					this.updateStatus(purBill, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return purBill;
	}

	@Override
	public ScmInvMoveReqBill undoFinish(ScmInvMoveReqBill scmInvMoveReqBill, Param param) throws AppException {
		ScmInvMoveReqBill purBill = null;
		if(scmInvMoveReqBill.getReqId()>0){
			purBill = this.selectDirect(scmInvMoveReqBill.getReqId(),param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmInvMoveReqBill.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmInvMoveReqBill.FN_REQNO,new QueryParam(ScmInvMoveReqBill.FN_REQNO, QueryParam.QUERY_EQ,scmInvMoveReqBill.getReqNo()));
			
			List<ScmInvMoveReqBill> scmBillList =this.findPage(page, param);
			if(scmBillList!=null && !scmBillList.isEmpty()){
				purBill=scmBillList.get(0);
			}
		}
		if(purBill!=null){
			if(!purBill.getStatus().equals("C")){
				throw new AppException("iscm.purchasemanage.error.cancelFinish");
			}else if(purBill.getStatus().equals("C")){
				if(param.getUsrCode()!=null ){
					purBill.setChecker(param.getUsrCode());
					purBill.setCheckDate(CalendarUtil.getDate(param));
				}
				purBill.setStatus("E");
				try{
					this.updateStatus(purBill, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return purBill;
	}

	@Override
	public ScmInvMoveReqBill updatePrtCount(ScmInvMoveReqBill ScmInvMoveReqBill, Param param) throws AppException {
		if(ScmInvMoveReqBill.getReqId()>0){
			ScmInvMoveReqBill bill = this.selectDirect(ScmInvMoveReqBill.getReqId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return ScmInvMoveReqBill;
	}

}
