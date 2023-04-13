package com.armitage.server.iscm.inventorymanage.inventoryinitialization.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

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
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvBalBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockBiz;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.dao.ScmInvInitBillDAO;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBill2;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBillEntry2;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBillImPort;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBillImPortDetail;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.service.ScmInvInitBillBiz;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.service.ScmInvInitBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvAccreditWh;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvAccreditWhBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.model.SystemState;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SystemStateBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmInvInitBillBiz")
public class ScmInvInitBillBizImpl extends BaseBizImpl<ScmInvInitBill2> implements ScmInvInitBillBiz {
	
	private UsrBiz usrBiz;
	private OrgUnitBiz orgUnitBiz;
	private ScmInvInitBillEntryBiz scmInvInitBillEntryBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private ScmInvStockBiz scmInvStockBiz;
	private ScmInvBalBiz scmInvBalBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private PeriodCalendarBiz periodCalendarBiz;
	private SystemStateBiz systemStateBiz;
	private ScmInvAccreditWhBiz scmInvAccreditWhBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private OrgStorageBiz orgStorageBiz;
	
	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}
	
	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}
	
	public ScmInvInitBillEntryBiz getScmInvInitBillEntryBiz() {
		return scmInvInitBillEntryBiz;
	}

	public void setScmInvInitBillEntryBiz(ScmInvInitBillEntryBiz scmInvInitBillEntryBiz) {
		this.scmInvInitBillEntryBiz = scmInvInitBillEntryBiz;
	}
	
	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}
	
	public void setScmInvStockBiz(ScmInvStockBiz scmInvStockBiz) {
		this.scmInvStockBiz = scmInvStockBiz;
	}
	
	public void setScmInvBalBiz(ScmInvBalBiz scmInvBalBiz) {
		this.scmInvBalBiz = scmInvBalBiz;
	}
	
	public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}

    public void setSystemStateBiz(SystemStateBiz systemStateBiz) {
        this.systemStateBiz = systemStateBiz;
    }

    public void setScmInvAccreditWhBiz(ScmInvAccreditWhBiz scmInvAccreditWhBiz) {
		this.scmInvAccreditWhBiz = scmInvAccreditWhBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvInitBill2.class) + "." + ScmInvInitBill2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvInitBill2.class) + "." + ScmInvInitBill2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvInitBill2.class) + "." + ScmInvInitBill2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvInitBill2.class) + "." + ScmInvInitBill2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}

	@Override
	protected void afterSelect(ScmInvInitBill2 entity, Param param) throws AppException {
		if (entity != null){
			entity.setTaxInAmt(entity.getTaxAmt().subtract(entity.getAmt()));
			setConvertMap(entity,param);
		}
	}
	
	public ScmInvInitBill2 updateStatus(ScmInvInitBill2 scmInvInitBill, Param param)
			throws AppException {
		if(scmInvInitBill != null){
			return this.updateDirect(scmInvInitBill, param);
		}
		return null;
	}
	
	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list != null && !list.isEmpty()){
			for(ScmInvInitBill2 scmInvInitBill : (List<ScmInvInitBill2>)list){
				scmInvInitBill.setTaxInAmt(scmInvInitBill.getTaxAmt().subtract(scmInvInitBill.getAmt()));
				setConvertMap(scmInvInitBill,param);
			}
		}
	}
	
	private void setConvertMap(ScmInvInitBill2 scmInvInitBill,Param param) {
		if(scmInvInitBill!=null) {
			if (StringUtils.isNotBlank(scmInvInitBill.getCreator())){
				//制单人
				Usr usr = usrBiz.selectByCode(scmInvInitBill.getCreator(), param);
				if (usr != null) {
					if(scmInvInitBill.getDataDisplayMap()==null){
						scmInvInitBill.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
					}
					scmInvInitBill.getDataDisplayMap().put(ScmInvPurInWarehsBill2.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
					scmInvInitBill.setConvertMap(ScmInvInitBill2.FN_CREATOR, usr);
				}
			}
			
			if (StringUtils.isNotBlank(scmInvInitBill.getOrgUnitNo())){
				//库存组织
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvInitBill.getOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmInvInitBill.setConvertMap(ScmInvInitBill2.FN_ORGUNITNO, orgBaseUnit);
				}
			}
			
			if (scmInvInitBill.getWareHouseId() > 0){
				//存货仓库
				ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvInitBill.getWareHouseId(), param);
				if (scmInvWareHouse != null) {
					scmInvInitBill.setConvertMap(ScmInvMaterialReqBill2.FN_WAREHOUSEID, scmInvWareHouse);
				}
			}
			if (StringUtils.isNotBlank(scmInvInitBill.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmInvInitBill.getEditor(), param);
				if (usr != null) {
					scmInvInitBill.setConvertMap(ScmInvInitBill2.FN_EDITOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmInvInitBill.getChecker())){
				//审核人
				Usr usr = usrBiz.selectByCode(scmInvInitBill.getChecker(), param);
				if (usr != null) {
					scmInvInitBill.setConvertMap(ScmInvInitBill2.FN_CHECKER, usr);
				}
			}
		}
	}
	
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmInvInitBill2 scmInvInitBill = (ScmInvInitBill2) bean.getList().get(0);
		if(scmInvInitBill != null && scmInvInitBill.getInitId() > 0){
			bean.setList2(scmInvInitBillEntryBiz.selectByInitId(scmInvInitBill.getInitId(), param));
		}
	}
	
	@Override
	protected void beforeAdd(CommonBean bean, Param param) throws AppException {
		ScmInvInitBill2 entity = (ScmInvInitBill2)bean.getList().get(0);
		if(entity != null){
//			String date = StringUtils.replace(FormatUtils.fmtDate(entity.getCreateDate()), "-", "");
//			StringBuffer s = new StringBuffer("");
//			s.append("INIT").append(date);
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("createDate", FormatUtils.fmtDate(FormatUtils.parseDateTime(date, "yyyyMMdd")));
//			map.put("controlUnitNo", param.getControlUnitNo());
//			ScmInvInitBill2 scmInvInitBill= ((ScmInvInitBillDAO) dao).selectMaxIdByDate(map);
//			if(scmInvInitBill != null && StringUtils.isNotBlank(scmInvInitBill.getBillNo())
//					&& scmInvInitBill.getBillNo().contains(date)){
//				String str = StringUtils.substring(scmInvInitBill.getBillNo(), (scmInvInitBill.getBillNo().indexOf(date)+date.length()));
//				str = CodeAutoCalUtil.autoAddOne(str);
//				str = (s.append(str)).toString();
//				entity.setBillNo(str);
//			}else{
//				entity.setBillNo((s.append("001").toString()));
//			}
			String code = CodeAutoCalUtil.getBillCode("InvInitBill", entity, param);
			entity.setBillNo(code);
			//获取期间
			PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(entity.getBizDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
			}
			entity.setPeriodId(periodCalendar.getPeriodId());
			entity.setAccountYear(periodCalendar.getAccountYear());
			entity.setAccountPeriod(periodCalendar.getAccountPeriod());
//			//新增入库明细
//			List<ScmInvInitBillEntry2> scmInvInitBillEntryList = bean.getList2();
//	        BigDecimal amt= BigDecimal.ZERO;
//	        BigDecimal taxAmt= BigDecimal.ZERO;
//			if(scmInvInitBillEntryList != null && !scmInvInitBillEntryList.isEmpty()){
//				for(ScmInvInitBillEntry2 scmInvInitBillEntry : scmInvInitBillEntryList){
//	                amt = amt.add(scmInvInitBillEntry.getAmt());
//	                taxAmt = taxAmt.add(scmInvInitBillEntry.getTaxAmt());
//				}
//			}
//			entity.setAmt(amt);
//			entity.setTaxAmt(taxAmt);
		}
	}
	
	@Override
	protected void beforeUpdate(ScmInvInitBill2 oldEntity,ScmInvInitBill2 newEntity, Param param) throws AppException {
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
		ScmInvInitBill2 scmInvInitBill = (ScmInvInitBill2) bean.getList().get(0);
		if(scmInvInitBill != null && scmInvInitBill.getInitId() > 0){
			//新增期初结存明细
			List<ScmInvInitBillEntry2> scmInvInitBillEntryList = bean.getList2();
			if(scmInvInitBillEntryList != null && !scmInvInitBillEntryList.isEmpty()){
				int lineId = 1;
				for(ScmInvInitBillEntry2 scmInvInitBillEntry : scmInvInitBillEntryList){
					scmInvInitBillEntry.setInitId(scmInvInitBill.getInitId());
					scmInvInitBillEntry.setLineId(lineId);
					scmInvInitBillEntry.setLot(scmInvInitBill.getBillNo());
//					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvInitBillEntry.getItemId(), param);
//					if(scmMaterial==null){
//						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
//					}
//					scmInvInitBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
//					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvInitBillEntry.getItemId(), scmInvInitBillEntry.getUnit(), param);//库存单位转换系数
//					scmInvInitBillEntry.setBaseQty(scmInvInitBillEntry.getQty().multiply(invConvRate));
					scmInvInitBillEntryBiz.add(scmInvInitBillEntry, param);
					lineId = lineId+1;
				}
			}
			
		}
	}
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvInitBill2 scmInvInitBill = (ScmInvInitBill2) bean.getList().get(0);
		if(scmInvInitBill != null && scmInvInitBill.getInitId() > 0){
			//更新入库明细
			List<ScmInvInitBillEntry2> scmInvInitBillEntryList = bean.getList2();
			if(scmInvInitBillEntryList != null && !scmInvInitBillEntryList.isEmpty()){
				int lineId = 1;
				for(ScmInvInitBillEntry2 scmInvInitBillEntry : scmInvInitBillEntryList){
					scmInvInitBillEntry.setInitId(scmInvInitBill.getInitId());
					if (StringUtils.equals("I", scmInvInitBill.getStatus())) {
						scmInvInitBillEntry.setLineId(lineId);
					}
					scmInvInitBillEntry.setLot(scmInvInitBill.getBillNo());
					if(StringUtils.isBlank(scmInvInitBillEntry.getLot())){
						scmInvInitBillEntry.setLot(scmInvInitBill.getBillNo());
					}
					lineId = lineId+1;
				}
				scmInvInitBillEntryBiz.update(scmInvInitBill, scmInvInitBillEntryList, ScmInvInitBillEntry2.FN_INITID, param);
			}
		}
	}
	
	@Override
	protected void afterDelete(ScmInvInitBill2 entity, Param param) throws AppException {
		if(entity != null && entity.getInitId() > 0){
			//删除入库明细
			scmInvInitBillEntryBiz.deleteByInitId(entity.getInitId(), param);
		}
	}

    @Override
    public List<ScmInvInitBill2> selectNotPost(String orgUnitNo, long wareHouseId, Param param) throws AppException {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("orgUnitNo", orgUnitNo);
        map.put("wareHouseId", wareHouseId);
        return ((ScmInvInitBillDAO) dao).selectNotPost(map);
    }

	@Override
	public List<String> postBillCheck(ScmInvInitBill2 scmInvInitBill,
			Param param) throws AppException {
		//过账时检查库存组织对应的财务组织是否有维护物资的财务资料
		List<String> msgList = new ArrayList<String>();// 提示列表
		scmInvInitBill = this.selectDirect(scmInvInitBill.getInitId(), param);
		if(scmInvInitBill != null){
            if(!StringUtils.equals("A",scmInvInitBill.getStatus())) {
    			msgList.add(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvInitBill.getBillNo()}));
    			return msgList;
    		}
		    SystemState systemState = systemStateBiz.selectBySystemId(scmInvInitBill.getFinOrgUnitNo(), 170, param);
            if(systemState==null){
                msgList.add(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
                return msgList;
            }
            if (systemState.getCurrentPeriodId() != scmInvInitBill.getPeriodId()) {
                msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmInvInitBill.getBillNo()}));
                return msgList;
            }
			List<ScmInvInitBillEntry2> scmInvInitBillEntryList = scmInvInitBillEntryBiz.selectByInitId(scmInvInitBill.getInitId(), param);
			if(scmInvInitBillEntryList != null && !scmInvInitBillEntryList.isEmpty()){
				HashMap<String,Object> cacheMap = new HashMap<String,Object>();
				for(ScmInvInitBillEntry2 scmInvInitBillEntry : scmInvInitBillEntryList){
					String orgunitName ="";
					if (StringUtils.isNotBlank(scmInvInitBill.getOrgUnitNo())){
						//组织
						OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvInitBill.getOrgUnitNo());
						if(orgBaseUnit==null){
							orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvInitBill.getOrgUnitNo(), param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvInitBill.getOrgUnitNo(),orgBaseUnit);
						}
						if (orgBaseUnit != null) {
							orgunitName = orgBaseUnit.getOrgUnitName();
						}
					}
					String[] msparam = {orgunitName,scmInvInitBillEntry.getItemName()};
					if(StringUtils.isBlank(scmInvInitBill.getFinOrgUnitNo())){
						msgList.add(Message.getMessage(param.getLang(),
								"iscm.inventorymanage.otherinwarehsbill.post.error", msparam));
						continue;
					}else{
						Page page = new Page();
						page.setModelClass(ScmMaterial2.class);
						page.setShowCount(Integer.MAX_VALUE);
						page.setSqlCondition("ScmMaterial.id="+scmInvInitBillEntry.getItemId());
						List<String> arglist = new ArrayList<>();
						arglist.add("controlUnitNo="+param.getControlUnitNo());
						arglist.add("orgUnitNo="+scmInvInitBill.getFinOrgUnitNo());
						List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, arglist, "findByFinAllPage", param);
						if(scmMaterialList==null || scmMaterialList.isEmpty()){
							msgList.add(Message.getMessage(param.getLang(),
									"iscm.inventorymanage.otherinwarehsbill.post.error", msparam));
							continue;
						}
					}
				}
			}
		}
		return msgList;
	}

	@Override
	public ScmInvInitBill2 postBill(ScmInvInitBill2 scmInvInitBill, Param param) throws AppException {
		scmInvInitBill = this.selectDirect(scmInvInitBill.getInitId(), param);
		if(scmInvInitBill != null){
			if(!StringUtils.equals("A",scmInvInitBill.getStatus())) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvInitBill.getBillNo()}));
    		}
			//即时库存
			scmInvStockBiz.updateByInitBill(scmInvInitBill.getInitId(), param);
			scmInvStockBiz.addByInitBill(scmInvInitBill.getInitId(), param);
			//期间余额
			scmInvBalBiz.updateByInitBill(scmInvInitBill.getInitId(), param);
			scmInvBalBiz.addByInitBill(scmInvInitBill.getInitId(), param);
			//移动平均即时成本
			if (!(ParamType.Operation.equals(param.getParamType()) && ((OperationParam) param).getOperationId()==629223199L)) {
				scmInvInitBill.setCheckDate(CalendarUtil.getDate(param));
				scmInvInitBill.setChecker(param.getUsrCode());
				scmInvInitBill.setPostDate(CalendarUtil.getDate(param));
			}
			scmInvInitBill.setStatus("E");
			int updateRow = this.updatePostedStatus(scmInvInitBill, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvInitBill.getBillNo()}));
			}
		}
		return scmInvInitBill;
	}

	private int updatePostedStatus(ScmInvInitBill2 scmInvInitBill, Param param) {
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("initId",scmInvInitBill.getInitId());
		map.put("checker",scmInvInitBill.getChecker());
		map.put("checkDate",scmInvInitBill.getCheckDate()==null?null:FormatUtils.fmtDate(scmInvInitBill.getCheckDate()));
		map.put("status", scmInvInitBill.getStatus());
		map.put("postDate", scmInvInitBill.getPostDate()==null?null:FormatUtils.fmtDateTime(scmInvInitBill.getPostDate()));
		return ((ScmInvInitBillDAO)dao).updatePostedStatus(map);
	}

	@Override
	public List<String> cancelPostBillCheck(ScmInvInitBill2 scmInvInitBill,Param param) throws AppException {
		List<String> rtnList = new ArrayList();
		scmInvInitBill = this.selectDirect(scmInvInitBill.getInitId(), param);
		if(scmInvInitBill != null){
            if(!StringUtils.equals("E",scmInvInitBill.getStatus())) {
            	rtnList.add(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvInitBill.getBillNo()}));
    			return rtnList;
    		}
            SystemState systemState = systemStateBiz.selectBySystemId(scmInvInitBill.getFinOrgUnitNo(), 170, param);
            if(systemState==null){
            	rtnList.add(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
                return rtnList;
            }
            if (systemState.getCurrentPeriodId() != scmInvInitBill.getPeriodId()) {
            	rtnList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmInvInitBill.getBillNo()}));
                return rtnList;
            }
			ScmInvAccreditWh scmInvAccreditWh = scmInvAccreditWhBiz.selectByWareHouseId(scmInvInitBill.getWareHouseId(),scmInvInitBill.getOrgUnitNo(), param);
			if(scmInvAccreditWh!=null) {
				if(scmInvAccreditWh.isEndInit()) {
					rtnList.add(Message.getMessage(param.getLang(), "com.armitage.server.iscm.inventorymanage.inventorysetting.service.impl.ScmInvAccreditWhBizImpl.isEndInit"));
				}
			}
		}
		return rtnList;
	}

	@Override
	public ScmInvInitBill2 cancelPostBill(ScmInvInitBill2 scmInvInitBill,
			Param param) throws AppException {
		scmInvInitBill = this.selectDirect(scmInvInitBill.getInitId(), param);
		if(scmInvInitBill != null){
			if(!StringUtils.equals("E",scmInvInitBill.getStatus())) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvInitBill.getBillNo()}));
    		}
			List<ScmInvInitBillEntry2> scmInvInitBillEntryList = scmInvInitBillEntryBiz.selectCancelEffectRow(scmInvInitBill.getInitId(), param);
			//即时库存
			int updateRow = scmInvStockBiz.updateByCancelInitBill(scmInvInitBill.getInitId(), param);
			if(updateRow<scmInvInitBillEntryList.size()){
    			throw new AppException("iscm.inventorymanage.common.post.error.rowsnotequal");
    		}
			//期间余额
			scmInvBalBiz.updateByCancelInitBill(scmInvInitBill.getInitId(), param);
			//移动平均即时成本
			scmInvInitBill.setCheckDate(null);
			scmInvInitBill.setChecker("");
			scmInvInitBill.setStatus("A");
			scmInvInitBill.setPostDate(null);
			updateRow = this.updatePostedStatus(scmInvInitBill, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvInitBill.getBillNo()}));
			}
		}
		return scmInvInitBill;
	}

	@Override
	protected void beforeDelete(ScmInvInitBill2 entity, Param param)
			throws AppException {
		ScmInvInitBill2 scmInvInitBill = this.selectDirect(entity.getInitId(), param);
		if(scmInvInitBill!=null && !StringUtils.equals(scmInvInitBill.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getBillNo()}));
		}
	}
	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvInitBill2 entry = (ScmInvInitBill2) bean.getList().get(0);
		if(entry!=null){
			ScmInvInitBill2 scmInvInitBill = this.select(entry.getPK(), param);
			if(!StringUtils.equals(scmInvInitBill.getStatus(),"I")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
		}
	}

	@Override
	public ScmInvInitBill2 importExcel(ScmInvInitBillImPort scmInvInitBillImPort, Param param) throws AppException {
		if(scmInvInitBillImPort!=null) {
			List<OrgCompany2> orgCompanylist = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, param.getOrgUnitNo(), false, null, param);
			if(orgCompanylist==null || orgCompanylist.isEmpty()) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmCstInitBill.importExcel.noadmintoCst"));
			}
			SystemState systemState = systemStateBiz.selectBySystemId(orgCompanylist.get(0).getOrgUnitNo(), 170, param);
            if(systemState==null){
            	throw new AppException(Message.getMessage(param.getLang(), "com.armitage.iars.daily.util.disenable.unable"));
            }
            PeriodCalendar periodCalendar = periodCalendarBiz.selectDirect(systemState.getCurrentPeriodId(), param);
			CommonBean bean = new CommonBean();
			List<ScmInvInitBill2> scmInvInitBillList = new ArrayList();
			ScmInvInitBill2 scmInvInitBill = new ScmInvInitBill2(true);
			scmInvInitBill.setOrgUnitNo(param.getOrgUnitNo());
			scmInvInitBill.setWareHouseId(scmInvInitBillImPort.getWareHouseId());
			scmInvInitBill.setFinOrgUnitNo(orgCompanylist.get(0).getOrgUnitNo());
			scmInvInitBill.setBizDate(periodCalendar.getStartDate());
			scmInvInitBill.setCreator(param.getUsrCode());
			scmInvInitBill.setCreateDate(CalendarUtil.getDate(param));
			scmInvInitBill.setStatus("I");
			scmInvInitBill.setControlUnitNo(param.getControlUnitNo());
			scmInvInitBillList.add(scmInvInitBill);
			bean.setList(scmInvInitBillList);
			
			StringBuffer itemNos = new StringBuffer("");
			List<ScmInvInitBillEntry2> scmInvInitBillEntryList = new ArrayList<ScmInvInitBillEntry2>();
			List<ScmInvInitBillImPortDetail> scmInvInitBillImPortDetailList=scmInvInitBillImPort.getDetailList();
				
			for(ScmInvInitBillImPortDetail scmInvInitBillImPortDetail : scmInvInitBillImPortDetailList) {
				if(StringUtils.isBlank(scmInvInitBillImPortDetail.getItemNo())){
					throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemisnull");
				}
				if(StringUtils.isNotBlank(itemNos.toString()))
					itemNos.append(",");
				itemNos.append("'").append(scmInvInitBillImPortDetail.getItemNo()).append("'");
			}
				
			Page page = new Page();
			page.setModelClass(ScmMaterial2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.setSqlCondition("ScmMaterial.itemNo in ("+itemNos.toString()+")");
			ArrayList argList = new ArrayList();
	        argList.add("orgUnitNo="+param.getOrgUnitNo());
	        argList.add("controlUnitNo=" + param.getControlUnitNo());
	        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "findByInvAllPage", param);
				
			for(ScmInvInitBillImPortDetail scmInvInitBillImPortDetail : scmInvInitBillImPortDetailList) {
				ScmInvInitBillEntry2 scmInvInitBillEntry = new ScmInvInitBillEntry2(true);
				BeanUtils.copyProperties(scmInvInitBillImPortDetail,scmInvInitBillEntry);
				for(ScmMaterial2 scmMaterial:scmMaterialList){
					if(StringUtils.equals(scmMaterial.getItemNo(), scmInvInitBillImPortDetail.getItemNo())){
						scmInvInitBillEntry.setItemId(scmMaterial.getId());
						scmInvInitBillEntry.setUnit(scmMaterial.getUnitId());
						break;
					}
				}
				scmInvInitBillEntry.setPrice(scmInvInitBillEntry.getTaxPrice());
				scmInvInitBillEntry.setAmt(scmInvInitBillEntry.getTaxAmt());
				scmInvInitBillEntry.setTaxRate(BigDecimal.ZERO);
				scmInvInitBillEntryList.add(scmInvInitBillEntry);
			}
			bean.setList2(scmInvInitBillEntryList);
			bean = this.add(bean, param);
			return (ScmInvInitBill2) bean.getList().get(0);
		}
		return null;
	}

	@Override
	public ScmInvInitBill2 submit(ScmInvInitBill2 scmInvInitBill, Param param) throws AppException {
		scmInvInitBill = this.selectDirect(scmInvInitBill.getInitId(), param);
		if(scmInvInitBill!=null){
			if(!scmInvInitBill.getStatus().equals("I")){
				throw new AppException("iscm.inventorymanage.error.commit");
			}else if(scmInvInitBill.getStatus().equals("I")){
				if(param.getUsrCode()!=null ){
					scmInvInitBill.setChecker(param.getUsrCode());
					scmInvInitBill.setSubmitter(param.getUsrCode());
				}
				scmInvInitBill.setCheckDate(CalendarUtil.getDate(param));
				scmInvInitBill.setSubmitDate(CalendarUtil.getDate(param));
				scmInvInitBill.setStatus("A");
				try{
					this.updateStatus(scmInvInitBill, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.find.ordernotexists");
		}
		return scmInvInitBill;
	}

	@Override
	public ScmInvInitBill2 undoSubmit(ScmInvInitBill2 scmInvInitBill, Param param) throws AppException {
		scmInvInitBill = this.selectDirect(scmInvInitBill.getInitId(), param);
		if(scmInvInitBill!=null){
			if(!scmInvInitBill.getStatus().equals("A")){
				throw new AppException("iscm.inventorymanage.error.cancelCommit");
			}else if(scmInvInitBill.getStatus().equals("A")){
				scmInvInitBill.setChecker(null);
				scmInvInitBill.setSubmitter(null);
				scmInvInitBill.setCheckDate(null);
				scmInvInitBill.setSubmitDate(null);
				scmInvInitBill.setStatus("I");
				try{
					this.updateStatus(scmInvInitBill, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.find.ordernotexists");
		}
		return scmInvInitBill;
	}

	@Override
	public ScmInvInitBill2 updatePrtCount(ScmInvInitBill2 scmInvInitBill, Param param) throws AppException {
		if(scmInvInitBill.getInitId()>0){
			ScmInvInitBill2 bill = this.selectDirect(scmInvInitBill.getInitId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmInvInitBill;
	}

}
