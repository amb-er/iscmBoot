package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.impl;


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

import com.armitage.server.api.business.datasync.params.InvOtherInWarehsDetailParams;
import com.armitage.server.api.business.datasync.params.InvOtherInWarehsListParams;
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
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialInventory2;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialInventoryBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvBalBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvSupplierLotBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.util.ScmWarehouseUtil;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.ScmInvOtherInWarehsBillDAO;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBillAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvOtherInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvOtherInWarehsBillEntryBiz;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.model.SystemState;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.service.SystemStateBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;

@Service("scmInvOtherInWarehsBillBiz")
public class ScmInvOtherInWarehsBillBizImpl extends BaseBizImpl<ScmInvOtherInWarehsBill2> implements ScmInvOtherInWarehsBillBiz {
	private UsrBiz usrBiz;
	private OrgUnitBiz orgUnitBiz;
	private ScmInvOtherInWarehsBillEntryBiz scmInvOtherInWarehsBillEntryBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private ScmInvStockBiz scmInvStockBiz;
	private ScmInvBalBiz scmInvBalBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private PeriodCalendarBiz periodCalendarBiz;
	private SystemStateBiz systemStateBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private ScmInvSupplierLotBiz scmInvSupplierLotBiz;
	private SysParamBiz sysParamBiz;
	private OrgStorageBiz orgStorageBiz;
    private BillTypeBiz billTypeBiz;
    private ScmMaterialInventoryBiz scmMaterialInventoryBiz;
    
	protected  HashMap<String,Long> scmMatrialMap = new HashMap<String,Long>();
	protected  HashMap<Long,Long> scmUnitMap = new HashMap<Long,Long>();
	
	public void setScmMaterialInventoryBiz(ScmMaterialInventoryBiz scmMaterialInventoryBiz) {
		this.scmMaterialInventoryBiz = scmMaterialInventoryBiz;
	}

	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
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

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public void setScmInvOtherInWarehsBillEntryBiz(ScmInvOtherInWarehsBillEntryBiz scmInvOtherInWarehsBillEntryBiz) {
		this.scmInvOtherInWarehsBillEntryBiz = scmInvOtherInWarehsBillEntryBiz;
	}
	public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}

	public void setSystemStateBiz(SystemStateBiz systemStateBiz) {
		this.systemStateBiz = systemStateBiz;
	}

	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}

	public void setScmInvSupplierLotBiz(ScmInvSupplierLotBiz scmInvSupplierLotBiz) {
		this.scmInvSupplierLotBiz = scmInvSupplierLotBiz;
	}

	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}
	
	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}

	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." + ScmInvOtherInWarehsBill2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." + ScmInvOtherInWarehsBill2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." + ScmInvOtherInWarehsBill2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." + ScmInvOtherInWarehsBill2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}
	
	@Override
	protected void afterSelect(ScmInvOtherInWarehsBill2 entity, Param param) throws AppException {
		if (entity != null){
			entity.setTaxInAmt(entity.getTaxAmt().subtract(entity.getAmt()));
			setConvertMap(entity,param);
		}
	}
	
	@Override
	protected void afterFindPage(List list, Page page, Param param)	throws AppException {
		if(list != null && !list.isEmpty()){
			for(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill : (List<ScmInvOtherInWarehsBill2>)list){
				scmInvOtherInWarehsBill.setTaxInAmt(scmInvOtherInWarehsBill.getTaxAmt().subtract(scmInvOtherInWarehsBill.getAmt()));
				setConvertMap(scmInvOtherInWarehsBill,param);
			}
		}
	}
	
	private void setConvertMap(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill, Param param){
		if (StringUtils.isNotBlank(scmInvOtherInWarehsBill.getCreator())){
			//制单人
			Usr usr = usrBiz.selectByCode(scmInvOtherInWarehsBill.getCreator(), param);
			if (usr != null) {
				scmInvOtherInWarehsBill.setConvertMap(ScmInvOtherInWarehsBill2.FN_CREATOR, usr);
				if(scmInvOtherInWarehsBill.getDataDisplayMap()==null){
					scmInvOtherInWarehsBill.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
				}
				scmInvOtherInWarehsBill.getDataDisplayMap().put(ScmInvOtherInWarehsBill2.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
			}
		}
		if (StringUtils.isNotBlank(scmInvOtherInWarehsBill.getEditor())){
			//修改人
			Usr usr = usrBiz.selectByCode(scmInvOtherInWarehsBill.getEditor(), param);
			if (usr != null) {
				scmInvOtherInWarehsBill.setConvertMap(ScmInvOtherInWarehsBill2.FN_EDITOR, usr);
			}
		}
		if (StringUtils.isNotBlank(scmInvOtherInWarehsBill.getChecker())){
			//审核人
			Usr usr = usrBiz.selectByCode(scmInvOtherInWarehsBill.getChecker(), param);
			if (usr != null) {
				scmInvOtherInWarehsBill.setConvertMap(ScmInvOtherInWarehsBill2.FN_CHECKER, usr);
			}
		}
		
		if (StringUtils.isNotBlank(scmInvOtherInWarehsBill.getCreateOrgUnitNo())){
			//制单组织
			OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvOtherInWarehsBill.getCreateOrgUnitNo(), param);
			if (orgBaseUnit != null) {
				scmInvOtherInWarehsBill.setConvertMap(ScmInvOtherInWarehsBill2.FN_CREATEORGUNITNO, orgBaseUnit);
			}
		}
		if (scmInvOtherInWarehsBill.getWareHouseId() > 0){
			//仓库
			ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvOtherInWarehsBill.getWareHouseId(), param);
			if (scmInvWareHouse != null) {
				scmInvOtherInWarehsBill.setConvertMap(ScmInvOtherInWarehsBill2.FN_WAREHOUSEID, scmInvWareHouse);
			}
		}
		if (scmInvOtherInWarehsBill.getVendorId()> 0){
			Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmInvOtherInWarehsBill.getVendorId(), param);
			if(scmsupplier!=null)
				scmInvOtherInWarehsBill.setConvertMap(ScmInvOtherInWarehsBill2.FN_VENDORID, scmsupplier);
		}
	}
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill = (ScmInvOtherInWarehsBill2) bean.getList().get(0);
		if(scmInvOtherInWarehsBill != null && scmInvOtherInWarehsBill.getWrId() > 0){
			HashMap<String,Object> cacheMap = new HashMap<String,Object>();
			List<ScmInvOtherInWarehsBillEntry2> scmInvOtherInWarehsBillEntryList = scmInvOtherInWarehsBillEntryBiz.selectByWrId(scmInvOtherInWarehsBill.getWrId(), param);
			if(scmInvOtherInWarehsBillEntryList != null && !scmInvOtherInWarehsBillEntryList.isEmpty()){
				for(ScmInvOtherInWarehsBillEntry2 scmInvOtherInWarehsBillEntry : scmInvOtherInWarehsBillEntryList){
					if (scmInvOtherInWarehsBillEntry.getWareHouseId() > 0){
						//仓库
						ScmInvWareHouse scmInvWareHouse = (ScmInvWareHouse) cacheMap.get(ClassUtils.getFinalModelSimpleName(ScmInvWareHouse.class)+"_"+scmInvOtherInWarehsBillEntry.getWareHouseId());
						if(scmInvWareHouse==null){
							scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvOtherInWarehsBillEntry.getWareHouseId(), param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(ScmInvWareHouse.class)+"_"+scmInvOtherInWarehsBillEntry.getWareHouseId(),scmInvWareHouse);
						}
						if (scmInvWareHouse != null) {
							scmInvOtherInWarehsBillEntry.setConvertMap(ScmInvOtherInWarehsBillEntry2.FN_WAREHOUSEID, scmInvWareHouse);
						}
					}
					if (scmInvOtherInWarehsBillEntry.getTaxRate() != null){
						//税率
						PubSysBasicInfo pubSysBasicInfo = (PubSysBasicInfo) cacheMap.get(ClassUtils.getFinalModelSimpleName(PubSysBasicInfo.class)+"_"+scmInvOtherInWarehsBillEntry.getTaxRate());
						if(pubSysBasicInfo==null){
							pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmInvOtherInWarehsBillEntry.getTaxRate().toString(), null, param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(PubSysBasicInfo.class)+"_"+scmInvOtherInWarehsBillEntry.getTaxRate(),pubSysBasicInfo);
						}
						if (pubSysBasicInfo != null) {
							scmInvOtherInWarehsBillEntry.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
							scmInvOtherInWarehsBillEntry.setConvertMap(ScmInvOtherInWarehsBillEntry2.FN_TAXRATESTR, pubSysBasicInfo);
						}
					}
				}
				bean.setList2(scmInvOtherInWarehsBillEntryList);
			}
		}
	}

	@Override
	protected void beforeAdd(ScmInvOtherInWarehsBill2 entity, Param param) throws AppException {
		if(entity != null){
			String code = CodeAutoCalUtil.getBillCode("InvOthInWhsBill", entity, param);
			entity.setWrNo(code);
//			entity.setWrNo((getBillNo(entity.getCreateDate(),param)));
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
	protected void beforeUpdate(ScmInvOtherInWarehsBill2 oldEntity,
			ScmInvOtherInWarehsBill2 newEntity, Param param)
			throws AppException {
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
		ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill = (ScmInvOtherInWarehsBill2) bean.getList().get(0);
		if(scmInvOtherInWarehsBill != null && scmInvOtherInWarehsBill.getWrId() > 0){
			//新增入库明细
			List<ScmInvOtherInWarehsBillEntry2> scmInvOtherInWarehsBillEntryList = bean.getList2();
			if(scmInvOtherInWarehsBillEntryList != null && !scmInvOtherInWarehsBillEntryList.isEmpty()){
				if(StringUtils.contains("2,3,4,5",scmInvOtherInWarehsBill.getBizType()))
					generateLot(scmInvOtherInWarehsBill,scmInvOtherInWarehsBillEntryList);
                int lineId = 1;
				for(ScmInvOtherInWarehsBillEntry2 scmInvOtherInWarehsBillEntry : scmInvOtherInWarehsBillEntryList){
					scmInvOtherInWarehsBillEntry.setLineId(lineId);
					scmInvOtherInWarehsBillEntry.setWrId(scmInvOtherInWarehsBill.getWrId());
					scmInvOtherInWarehsBillEntry.setOrgUnitNo(scmInvOtherInWarehsBill.getOrgUnitNo());
					scmInvOtherInWarehsBillEntry.setWareHouseId(scmInvOtherInWarehsBill.getWareHouseId());
					if(scmInvOtherInWarehsBill.getVendorId()>0) {
						scmInvOtherInWarehsBillEntry.setOrgSourceVendorId(scmInvOtherInWarehsBill.getVendorId());
					}
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvOtherInWarehsBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvOtherInWarehsBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvOtherInWarehsBillEntry.getItemId(), scmInvOtherInWarehsBillEntry.getUnit(), param);//库存单位转换系数
					scmInvOtherInWarehsBillEntry.setBaseQty(scmInvOtherInWarehsBillEntry.getQty().multiply(invConvRate));
					scmInvOtherInWarehsBillEntryBiz.add(scmInvOtherInWarehsBillEntry, param);
                    lineId = lineId+1;
				}
			}
		}
	}
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill = (ScmInvOtherInWarehsBill2) bean.getList().get(0);
		if(scmInvOtherInWarehsBill != null && scmInvOtherInWarehsBill.getWrId() > 0){
			//更新入库明细
			List<ScmInvOtherInWarehsBillEntry2> scmInvOtherInWarehsBillEntryList = bean.getList2();
			if(scmInvOtherInWarehsBillEntryList != null && !scmInvOtherInWarehsBillEntryList.isEmpty()){
				if(StringUtils.contains("2,3,4,5",scmInvOtherInWarehsBill.getBizType()))
					generateLot(scmInvOtherInWarehsBill,scmInvOtherInWarehsBillEntryList);
                int lineId = 1;
				for(ScmInvOtherInWarehsBillEntry2 scmInvOtherInWarehsBillEntry : scmInvOtherInWarehsBillEntryList){
					if (StringUtils.equals("I", scmInvOtherInWarehsBill.getStatus())) {
						scmInvOtherInWarehsBillEntry.setLineId(lineId);
					}
					scmInvOtherInWarehsBillEntry.setWrId(scmInvOtherInWarehsBill.getWrId());
					scmInvOtherInWarehsBillEntry.setOrgUnitNo(scmInvOtherInWarehsBill.getOrgUnitNo());
					scmInvOtherInWarehsBillEntry.setWareHouseId(scmInvOtherInWarehsBill.getWareHouseId());
					if(scmInvOtherInWarehsBill.getVendorId()>0) {
						scmInvOtherInWarehsBillEntry.setOrgSourceVendorId(scmInvOtherInWarehsBill.getVendorId());
					}
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvOtherInWarehsBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvOtherInWarehsBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvOtherInWarehsBillEntry.getItemId(), scmInvOtherInWarehsBillEntry.getUnit(), param);//库存单位转换系数
					scmInvOtherInWarehsBillEntry.setBaseQty(scmInvOtherInWarehsBillEntry.getQty().multiply(invConvRate));
					scmInvOtherInWarehsBillEntry.setWrId(scmInvOtherInWarehsBill.getWrId());
                    lineId = lineId+1;
				}
				scmInvOtherInWarehsBillEntryBiz.update(scmInvOtherInWarehsBill, scmInvOtherInWarehsBillEntryList, ScmInvOtherInWarehsBillEntry2.FN_WRID, param);
			}
		}
	}
	
	@Override
	protected void afterDelete(ScmInvOtherInWarehsBill2 entity, Param param) throws AppException {
		if(entity != null && entity.getWrId() > 0){
			//删除入库明细
			scmInvOtherInWarehsBillEntryBiz.deleteByWrId(entity.getWrId(), param);
		}
	}
	
	public ScmInvOtherInWarehsBill2 updateStatus(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill,Param param) throws AppException {
		if(scmInvOtherInWarehsBill != null){
			return this.updateDirect(scmInvOtherInWarehsBill, param);
		}
		return null;
	}

	@Override
	public List<String> postBillCheck(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill, Param param)
			throws AppException {
		//过账时检查库存组织对应的财务组织是否有维护物资的财务资料
		List<String> msgList = new ArrayList<String>();// 提示列表
		scmInvOtherInWarehsBill = this.selectDirect(scmInvOtherInWarehsBill.getWrId(), param);
		if(!StringUtils.equals("A",scmInvOtherInWarehsBill.getStatus())){
    		msgList.add(Message.getMessage(param.getLang(),"iscm.inventorymanage.post.error2",new String[] {scmInvOtherInWarehsBill.getWrNo()}));
    		return msgList;
    	}
		SystemState systemState = systemStateBiz.selectBySystemId(scmInvOtherInWarehsBill.getFinOrgUnitNo(), 170L, param);
		if(systemState==null){
			msgList.add(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
			return msgList;
		}
		if(systemState.getCurrentPeriodId()!=scmInvOtherInWarehsBill.getPeriodId()){
			msgList.add(Message.getMessage(param.getLang(),"com.armitage.server.iscm.inventorymanage.cstbusiness.service.impl.ScmInvMoveBillBizImpl.currentPeriodId.error",new String[] {scmInvOtherInWarehsBill.getWrNo()}));
			return msgList;
		}
		
		// 检查盘点物资冻结
        // 检查冻结仓库
        List<ScmInvOtherInWarehsBill2> scmInvOtherInWarehsBillList = ((ScmInvOtherInWarehsBillDAO) dao).checkWareHouseFree(scmInvOtherInWarehsBill.getWrId());
        if (scmInvOtherInWarehsBillList != null && !scmInvOtherInWarehsBillList.isEmpty()) {
        	 msgList.add(Message.getMessage(param.getLang(), 
                     "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
             return msgList;
//        	for (ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill2 : scmInvOtherInWarehsBillList) {
//	            // 检查冻结物资
//	            HashMap<String,Object> map = new HashMap<String,Object>();
//	            map.put("taskId", scmInvOtherInWarehsBill2.getTaskId());
//	            map.put("wrId", scmInvOtherInWarehsBill.getWrId());
//	            int count = ((ScmInvOtherInWarehsBillDAO) dao).checkMaterialFree(map);
//	            if (count > 0) {
//	                msgList.add(Message.getMessage(param.getLang(), 
//	                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
//	                return msgList;
//	            }
//        	}
        }
		if(scmInvOtherInWarehsBill != null){
			if(StringUtils.equals("6", scmInvOtherInWarehsBill.getBizType())) {
				//寄存退货需检查结存
				List<ScmInvOtherInWarehsBillEntry2> scmInvOtherInWarehsBillEntryList = scmInvOtherInWarehsBillEntryBiz.checkStock(scmInvOtherInWarehsBill.getWrId(), param);
				if(scmInvOtherInWarehsBillEntryList != null && !scmInvOtherInWarehsBillEntryList.isEmpty()){
					for(ScmInvOtherInWarehsBillEntry2 scmInvOtherInWarehsBillEntry : scmInvOtherInWarehsBillEntryList){
						if(StringUtils.isNotBlank(scmInvOtherInWarehsBillEntry.getLot())){
							if(scmInvOtherInWarehsBillEntry.getPieQty().compareTo(BigDecimal.ZERO)==0) {
								String[] msparam = {scmInvOtherInWarehsBillEntry.getItemNo(),scmInvOtherInWarehsBillEntry.getItemName(),
										scmInvOtherInWarehsBillEntry.getLot(),(scmInvOtherInWarehsBillEntry.getQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
										(scmInvOtherInWarehsBillEntry.getInvQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()};
								msgList.add(Message.getMessage(param.getLang(),
										"iscm.inventorymanage.warehouseoutbusiness.ScmInvMaterialReqBill.postBillCheck.error", msparam));
							}else {
								String[] msparam = {scmInvOtherInWarehsBillEntry.getItemNo(),scmInvOtherInWarehsBillEntry.getItemName(),
										scmInvOtherInWarehsBillEntry.getLot(),(scmInvOtherInWarehsBillEntry.getQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
										(scmInvOtherInWarehsBillEntry.getPieQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
										(scmInvOtherInWarehsBillEntry.getInvQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
										(scmInvOtherInWarehsBillEntry.getInvPieQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()};
								msgList.add(Message.getMessage(param.getLang(),
										"iscm.inventorymanage.warehouseoutbusiness.out.postBillCheck.pieqty.error", msparam));
							}
						}else{
							if(scmInvOtherInWarehsBillEntry.getPieQty().compareTo(BigDecimal.ZERO)==0) {
								String[] msparam = {scmInvOtherInWarehsBillEntry.getItemNo(),scmInvOtherInWarehsBillEntry.getItemName(),
										(scmInvOtherInWarehsBillEntry.getQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
										(scmInvOtherInWarehsBillEntry.getInvQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()};
								msgList.add(Message.getMessage(param.getLang(),
										"iscm.inventorymanage.warehouseoutbusiness.ScmInvMaterialReqBill.postBillCheck.error2", msparam));
							}else {
								String[] msparam = {scmInvOtherInWarehsBillEntry.getItemNo(),scmInvOtherInWarehsBillEntry.getItemName(),
										(scmInvOtherInWarehsBillEntry.getQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
										(scmInvOtherInWarehsBillEntry.getPieQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
										(scmInvOtherInWarehsBillEntry.getInvQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
										(scmInvOtherInWarehsBillEntry.getInvPieQty()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()};
								msgList.add(Message.getMessage(param.getLang(),
										"iscm.inventorymanage.warehouseoutbusiness.out.postBillCheck.pieqty.error2", msparam));
							}
						}
					}
				}
			
			}else {
				List<ScmInvOtherInWarehsBillEntry2> scmInvOtherInWarehsBillEntryList = scmInvOtherInWarehsBillEntryBiz.selectByWrId(scmInvOtherInWarehsBill.getWrId(), param);
				if(scmInvOtherInWarehsBillEntryList != null && !scmInvOtherInWarehsBillEntryList.isEmpty()){
					HashMap<String,Object> cacheMap = new HashMap<String,Object>();
					for(ScmInvOtherInWarehsBillEntry2 scmInvOtherInWarehsBillEntry : scmInvOtherInWarehsBillEntryList){
						String orgunitName ="";
						OrgCompany2 orgCompany = null;
						if (StringUtils.isNotBlank(scmInvOtherInWarehsBillEntry.getOrgUnitNo())){
							//组织
							OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvOtherInWarehsBillEntry.getOrgUnitNo());
							if(orgBaseUnit==null){
								orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvOtherInWarehsBillEntry.getOrgUnitNo(), param);
								cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvOtherInWarehsBillEntry.getOrgUnitNo(),orgBaseUnit);
							}
							if (orgBaseUnit != null) {
								orgunitName = orgBaseUnit.getOrgUnitName();
							}
							orgCompany = (OrgCompany2) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgCompany2.class)+"_"+scmInvOtherInWarehsBillEntry.getOrgUnitNo());
							if(orgCompany==null && !cacheMap.containsKey(ClassUtils.getFinalModelSimpleName(OrgCompany2.class)+"_"+scmInvOtherInWarehsBillEntry.getOrgUnitNo())){
								List<OrgCompany2> orgCompanylist = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, scmInvOtherInWarehsBillEntry.getOrgUnitNo(), false, null, param);
								if(orgCompanylist != null && !orgCompanylist.isEmpty()){
									orgCompany = orgCompanylist.get(0);
									cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgCompany2.class)+"_"+scmInvOtherInWarehsBillEntry.getOrgUnitNo(),orgCompany);
								}
							}
						}
						String[] msparam = {orgunitName,scmInvOtherInWarehsBillEntry.getItemName()};
						if(orgCompany==null){
							msgList.add(Message.getMessage(param.getLang(),
									"iscm.inventorymanage.otherinwarehsbill.post.error", msparam));
							continue;
						}else{
							Page page = new Page();
							page.setModelClass(ScmMaterial2.class);
							page.setShowCount(Integer.MAX_VALUE);
							page.setSqlCondition("ScmMaterial.id="+scmInvOtherInWarehsBillEntry.getItemId());
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
				}
			}
		}
		return msgList;
	}

	@Override
	public ScmInvOtherInWarehsBill2 postBill(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill, Param param)
			throws AppException {
		scmInvOtherInWarehsBill = this.selectDirect(scmInvOtherInWarehsBill.getWrId(), param);
        if (scmInvOtherInWarehsBill != null) {
			if(!StringUtils.equals("A",scmInvOtherInWarehsBill.getStatus())) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvOtherInWarehsBill.getWrNo()}));
    		}
			if(StringUtils.equals("6", scmInvOtherInWarehsBill.getBizType())) {
				//寄存退货
				splitBillByOutWarehouse(scmInvOtherInWarehsBill,param);//拆批次
	    		List<ScmInvOtherInWarehsBillEntry2> scmInvOtherInWarehsBillEntryList = scmInvOtherInWarehsBillEntryBiz.selectReturnEffectRow(scmInvOtherInWarehsBill.getWrId(), param);
	    		int updateRow = scmInvStockBiz.updateByDepositInReturn(scmInvOtherInWarehsBill.getWrId(), param);
	            if(updateRow<scmInvOtherInWarehsBillEntryList.size()){
	            	throw new AppException(Message.getMessage(param.getLang(),"iscm.inventorymanage.common.post.error.rowsnotequal"));
	    		}
				//期间余额
				scmInvBalBiz.updateByDepositInReturn(scmInvOtherInWarehsBill.getWrId(), param);
			}else {
				//即时库存
				scmInvStockBiz.updateByOtherInWarehsBill(scmInvOtherInWarehsBill.getWrId(), param);
				scmInvStockBiz.addByOtherInWarehsBill(scmInvOtherInWarehsBill.getWrId(), scmInvOtherInWarehsBill.getUnitedBillId(), param);
				//期间余额
				if(!StringUtils.equals("1", scmInvOtherInWarehsBill.getBizType())){
					//其他类型更新本期入库数
					scmInvBalBiz.updateByOtherInWarehsBill(scmInvOtherInWarehsBill.getWrId(), param);
					scmInvBalBiz.addByOtherInWarehsBill(scmInvOtherInWarehsBill.getWrId(), param);
				}else{
					//盘盈时更新调整数
					scmInvBalBiz.updateByOtherInWarehsBillForInvProfit(scmInvOtherInWarehsBill.getWrId(), param);
					scmInvBalBiz.addByOtherInWarehsBillForInvProfit(scmInvOtherInWarehsBill.getWrId(), param);
				}
			}
			if (!(ParamType.Operation.equals(param.getParamType()) && ((OperationParam) param).getOperationId()==629223199L)) {
				scmInvOtherInWarehsBill.setCheckDate(CalendarUtil.getDate(param));
				scmInvOtherInWarehsBill.setChecker(param.getUsrCode());
				scmInvOtherInWarehsBill.setPostDate(CalendarUtil.getDate(param));
			}
			scmInvOtherInWarehsBill.setStatus("E");
			int updateRow = this.updatePostedStatus(scmInvOtherInWarehsBill, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvOtherInWarehsBill.getWrNo()}));
			}
			if(StringUtils.equals("5", scmInvOtherInWarehsBill.getBizType())) {
				//寄存入库时记录供应商批次关系
				scmInvSupplierLotBiz.addByOtherInWarehsBill(scmInvOtherInWarehsBill.getWrId(), param);
			}
		}
		return scmInvOtherInWarehsBill;
	}

	private int updatePostedStatus(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill, Param param) {
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("wrId",scmInvOtherInWarehsBill.getWrId());
		map.put("checker",scmInvOtherInWarehsBill.getChecker());
		map.put("checkDate",scmInvOtherInWarehsBill.getCheckDate()==null?null:FormatUtils.fmtDateTime(scmInvOtherInWarehsBill.getCheckDate()));
		map.put("status", scmInvOtherInWarehsBill.getStatus());
		map.put("postDate", scmInvOtherInWarehsBill.getPostDate()==null?null:FormatUtils.fmtDateTime(scmInvOtherInWarehsBill.getPostDate()));
		return ((ScmInvOtherInWarehsBillDAO)dao).updatePostedStatus(map);
	}

	private void splitBillByOutWarehouse(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill, Param param) {
		List<ScmInvOtherInWarehsBillEntry2> scmInvOtherInWarehsBillEntryList = scmInvOtherInWarehsBillEntryBiz.selectByWrId(scmInvOtherInWarehsBill.getWrId(), param);
        String fields[]={"itemId","id"};
		String sorts[]={"ASC","ASC"};
		scmInvOtherInWarehsBillEntryList = (List<ScmInvOtherInWarehsBillEntry2>)ListSortUtil.sort(scmInvOtherInWarehsBillEntryList, fields,sorts);	//按物资排序
		int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
		HashMap<String,List<ScmInvStock2>> qtyMap = new HashMap<String,List<ScmInvStock2>>();
		int lineId = 1;
		if(scmInvOtherInWarehsBillEntryList != null && !scmInvOtherInWarehsBillEntryList.isEmpty()){
			for(int i=scmInvOtherInWarehsBillEntryList.size()-1;i>=0;i--){
				ScmInvOtherInWarehsBillEntry2 scmInvOtherInWarehsBillEntry = scmInvOtherInWarehsBillEntryList.get(i);
				StringBuffer info = new StringBuffer("");
				info.append(scmInvOtherInWarehsBillEntry.getOrgUnitNo()).append("_");
				info.append(scmInvOtherInWarehsBillEntry.getWareHouseId()).append("_");
				info.append(String.valueOf(scmInvOtherInWarehsBillEntry.getItemId()));
				if(!qtyMap.containsKey((info.toString()))){
					//查询计价方式
					Page page = new Page();
					page.setModelClass(ScmMaterial2.class);
					page.setShowCount(Integer.MAX_VALUE);
					page.setSqlCondition("ScmMaterial.id="+scmInvOtherInWarehsBillEntry.getItemId());
					ArrayList argList = new ArrayList();
			        argList.add("orgUnitNo="+scmInvOtherInWarehsBillEntry.getFinOrgUnitNo());
			        argList.add("controlUnitNo=" + param.getControlUnitNo());
                    List<ScmMaterial2> materialList = scmMaterialBiz.queryPage(page, argList, "findByFinAllPage", param);
                    //获取costMethod来决定升序还是降序排序
                    if (materialList != null && materialList.size() > 0 && StringUtils.isNotBlank(materialList.get(0).getCosting())) {
                    	HashMap<String, Object> map = new HashMap<String, Object>();
               			map.put("orgUnitNo", scmInvOtherInWarehsBillEntry.getOrgUnitNo());
                        map.put("itemId", scmInvOtherInWarehsBillEntry.getItemId());
                        map.put("wareHouseId", scmInvOtherInWarehsBillEntry.getWareHouseId());
                        map.put("bizDate", FormatUtils.fmtDate(scmInvOtherInWarehsBill.getBizDate()));
                        map.put("costMethod", materialList.get(0).getCosting());
                        map.put("unit", scmInvOtherInWarehsBillEntry.getUnit());
                        map.put("vendorId", scmInvOtherInWarehsBill.getVendorId());
                        List<ScmInvStock2> stocklist = scmInvStockBiz.findByOutAndReturnWarehouse(map, param);
                        qtyMap.put(info.toString(), stocklist);
                    }
				}
                List<ScmInvStock2> stocklist = qtyMap.get(info.toString());
				if(stocklist != null && !stocklist.isEmpty()){
					if(StringUtils.isNotBlank(scmInvOtherInWarehsBillEntry.getLot())) {
                		//有批次则先按批次找，如不够再按先进先出拆单
                		for (ScmInvStock2 scmInvStock : stocklist) {
                			if(StringUtils.equals(scmInvOtherInWarehsBillEntry.getLot(), scmInvStock.getLot())) {
		                		if(scmInvOtherInWarehsBillEntry.getQty().compareTo(BigDecimal.ZERO)>0) {
		                			if(setDataFromStock(scmInvStock,scmInvOtherInWarehsBillEntry,amtPrecision,lineId,param))
		                				lineId = lineId+1;
		                		}else {
		                			break;
		                		}
                			}
                		}
                	}
                	for (ScmInvStock2 scmInvStock : stocklist) {
                		if(scmInvOtherInWarehsBillEntry.getQty().compareTo(BigDecimal.ZERO)>0) {
                			if(setDataFromStock(scmInvStock,scmInvOtherInWarehsBillEntry,amtPrecision,lineId,param))
                				lineId = lineId+1;
                		}else {
                			break;
                		}
                	}
                    scmInvOtherInWarehsBillEntryBiz.delete(scmInvOtherInWarehsBillEntry, param);
				}
			}
		}
	}
	private boolean setDataFromStock(ScmInvStock2 scmInvStock,ScmInvOtherInWarehsBillEntry2 scmInvOtherInWarehsBillEntry,int amtPrecision,int lineId,Param param) {
		boolean flag=false;
		BigDecimal stockQty = scmInvStock.getQty();
		BigDecimal stockPieQty = scmInvStock.getPieQty();
		BigDecimal stockBaseQty = scmInvStock.getBaseQty();
		BigDecimal stockAmt = (scmInvStock.getAmt());
		BigDecimal stockTaxAmt = (scmInvStock.getTaxAmt());
		if (stockQty.compareTo(scmInvOtherInWarehsBillEntry.getQty()) > 0) {
			ScmInvOtherInWarehsBillEntry2 scmInvOtherInWarehsBillEntry2= new ScmInvOtherInWarehsBillEntry2(true);
    		scmInvOtherInWarehsBillEntry2.setWrId(scmInvOtherInWarehsBillEntry.getWrId());
    		scmInvOtherInWarehsBillEntry2.setLineId(scmInvOtherInWarehsBillEntry.getLineId()+1);
    		scmInvOtherInWarehsBillEntry2.setItemId(scmInvStock.getItemId());
    		scmInvOtherInWarehsBillEntry2.setLot(scmInvStock.getLot());
    		scmInvOtherInWarehsBillEntry2.setUnit(scmInvOtherInWarehsBillEntry.getUnit());
    		scmInvOtherInWarehsBillEntry2.setQty(scmInvOtherInWarehsBillEntry.getQty());
    		scmInvOtherInWarehsBillEntry2.setBaseUnit(scmInvStock.getBaseUnit());
    		scmInvOtherInWarehsBillEntry2.setBaseQty(scmInvOtherInWarehsBillEntry.getBaseQty());
    		scmInvOtherInWarehsBillEntry2.setPieUnit(scmInvStock.getPieUnit());
    		scmInvOtherInWarehsBillEntry2.setPieQty(scmInvOtherInWarehsBillEntry.getPieQty());
			scmInvOtherInWarehsBillEntry2.setPrice(scmInvStock.getPrice());
			scmInvOtherInWarehsBillEntry2.setTaxRate(scmInvStock.getTaxRate());
			scmInvOtherInWarehsBillEntry2.setTaxPrice(scmInvStock.getTaxPrice());
			scmInvOtherInWarehsBillEntry2.setAmt((scmInvOtherInWarehsBillEntry2.getQty().multiply(scmInvOtherInWarehsBillEntry2.getPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
			scmInvOtherInWarehsBillEntry2.setTaxAmt((scmInvOtherInWarehsBillEntry2.getQty().multiply(scmInvOtherInWarehsBillEntry2.getTaxPrice())).setScale(amtPrecision, RoundingMode.HALF_UP));
    		scmInvOtherInWarehsBillEntry2.setProductDate(scmInvOtherInWarehsBillEntry.getProductDate());
    		scmInvOtherInWarehsBillEntry2.setExpDate(scmInvStock.getExpDate());
    		scmInvOtherInWarehsBillEntry2.setOffset(false);
    		scmInvOtherInWarehsBillEntry2.setOrgUnitNo(scmInvOtherInWarehsBillEntry.getOrgUnitNo());
    		scmInvOtherInWarehsBillEntry2.setWareHouseId(scmInvOtherInWarehsBillEntry.getWareHouseId());
    		scmInvOtherInWarehsBillEntry2.setSourceBillDtlId(scmInvOtherInWarehsBillEntry.getSourceBillDtlId());
    		scmInvOtherInWarehsBillEntry2.setStockId(scmInvStock.getId());
    		scmInvOtherInWarehsBillEntry2.setRemarks(scmInvOtherInWarehsBillEntry.getRemarks());
    		scmInvOtherInWarehsBillEntry2.setFinOrgUnitNo(scmInvStock.getFinOrgUnitNo());
    		scmInvOtherInWarehsBillEntry2.setOrgSourceId(scmInvStock.getSourceBillId());
    		scmInvOtherInWarehsBillEntry2.setOrgSourceVendorId(scmInvStock.getVendorId());
    		scmInvOtherInWarehsBillEntry2.setInvDate(scmInvStock.getInvDate());
    		scmInvOtherInWarehsBillEntryBiz.add(scmInvOtherInWarehsBillEntry2, param);
    		scmInvOtherInWarehsBillEntry.setQty(BigDecimal.ZERO);
    		scmInvOtherInWarehsBillEntry.setPieQty(BigDecimal.ZERO);
    		scmInvOtherInWarehsBillEntry.setBaseQty(BigDecimal.ZERO);
    		scmInvStock.setQty(scmInvStock.getQty().subtract(scmInvOtherInWarehsBillEntry2.getQty()));
    		scmInvStock.setPieQty(scmInvStock.getPieQty().subtract(scmInvOtherInWarehsBillEntry2.getPieQty()));
    		scmInvStock.setBaseQty(scmInvStock.getBaseQty().subtract(scmInvOtherInWarehsBillEntry2.getBaseQty()));
    		scmInvStock.setAmt(scmInvStock.getAmt().subtract(scmInvOtherInWarehsBillEntry2.getAmt()));
    		scmInvStock.setTaxAmt(scmInvStock.getTaxAmt().subtract(scmInvOtherInWarehsBillEntry2.getTaxAmt()));
    		flag=true;
		} else if (stockQty.compareTo(BigDecimal.ZERO) > 0) {
			ScmInvOtherInWarehsBillEntry2 scmInvOtherInWarehsBillEntry2= new ScmInvOtherInWarehsBillEntry2(true);
    		scmInvOtherInWarehsBillEntry2.setWrId(scmInvOtherInWarehsBillEntry.getWrId());
    		scmInvOtherInWarehsBillEntry2.setLineId(scmInvOtherInWarehsBillEntry.getLineId()+1);
    		scmInvOtherInWarehsBillEntry2.setItemId(scmInvStock.getItemId());
    		scmInvOtherInWarehsBillEntry2.setLot(scmInvStock.getLot());
    		scmInvOtherInWarehsBillEntry2.setUnit(scmInvOtherInWarehsBillEntry.getUnit());
    		scmInvOtherInWarehsBillEntry2.setQty(stockQty);
    		scmInvOtherInWarehsBillEntry2.setBaseUnit(scmInvStock.getBaseUnit());
    		scmInvOtherInWarehsBillEntry2.setBaseQty(stockBaseQty);
    		scmInvOtherInWarehsBillEntry2.setPieUnit(scmInvStock.getPieUnit());
    		scmInvOtherInWarehsBillEntry2.setPieQty(stockPieQty);
			scmInvOtherInWarehsBillEntry2.setPrice(scmInvStock.getPrice());
			scmInvOtherInWarehsBillEntry2.setTaxRate(scmInvStock.getTaxRate());
			scmInvOtherInWarehsBillEntry2.setTaxPrice(scmInvStock.getTaxPrice());
			scmInvOtherInWarehsBillEntry2.setAmt(stockAmt);
			scmInvOtherInWarehsBillEntry2.setTaxAmt(stockTaxAmt);
    		scmInvOtherInWarehsBillEntry2.setProductDate(scmInvOtherInWarehsBillEntry.getProductDate());
    		scmInvOtherInWarehsBillEntry2.setExpDate(scmInvStock.getExpDate());
    		scmInvOtherInWarehsBillEntry2.setOffset(false);
    		scmInvOtherInWarehsBillEntry2.setOrgUnitNo(scmInvOtherInWarehsBillEntry.getOrgUnitNo());
    		scmInvOtherInWarehsBillEntry2.setWareHouseId(scmInvOtherInWarehsBillEntry.getWareHouseId());
    		scmInvOtherInWarehsBillEntry2.setSourceBillDtlId(scmInvOtherInWarehsBillEntry.getSourceBillDtlId());
    		scmInvOtherInWarehsBillEntry2.setStockId(scmInvStock.getId());
    		scmInvOtherInWarehsBillEntry2.setRemarks(scmInvOtherInWarehsBillEntry.getRemarks());
    		scmInvOtherInWarehsBillEntry2.setFinOrgUnitNo(scmInvStock.getFinOrgUnitNo());
    		scmInvOtherInWarehsBillEntry2.setOrgSourceId(scmInvStock.getSourceBillId());
    		scmInvOtherInWarehsBillEntry2.setOrgSourceVendorId(scmInvStock.getVendorId());
    		scmInvOtherInWarehsBillEntry2.setInvDate(scmInvStock.getInvDate());
    		scmInvOtherInWarehsBillEntryBiz.add(scmInvOtherInWarehsBillEntry2, param);
    		scmInvOtherInWarehsBillEntry.setQty(scmInvOtherInWarehsBillEntry.getQty().subtract(scmInvOtherInWarehsBillEntry2.getQty()));
    		scmInvOtherInWarehsBillEntry.setPieQty(scmInvOtherInWarehsBillEntry.getPieQty().subtract(scmInvOtherInWarehsBillEntry2.getPieQty()));
    		scmInvOtherInWarehsBillEntry.setBaseQty(scmInvOtherInWarehsBillEntry.getBaseQty().subtract(scmInvOtherInWarehsBillEntry2.getBaseQty()));
    		scmInvStock.setQty(scmInvStock.getQty().subtract(scmInvOtherInWarehsBillEntry2.getQty()));
    		scmInvStock.setPieQty(scmInvStock.getPieQty().subtract(scmInvOtherInWarehsBillEntry2.getPieQty()));
    		scmInvStock.setBaseQty(scmInvStock.getBaseQty().subtract(scmInvOtherInWarehsBillEntry2.getBaseQty()));
    		scmInvStock.setAmt(scmInvStock.getAmt().subtract(scmInvOtherInWarehsBillEntry2.getAmt()));
    		scmInvStock.setTaxAmt(scmInvStock.getTaxAmt().subtract(scmInvOtherInWarehsBillEntry2.getTaxAmt()));
    		flag=true;
		}
		return flag;
	}
	@Override
	public List<String> cancelPostBillCheck(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill, Param param)
			throws AppException {
		List<String> msgList = new ArrayList<String>();// 提示列表
		scmInvOtherInWarehsBill = this.selectDirect(scmInvOtherInWarehsBill.getWrId(), param);
		if(!StringUtils.equals("E",scmInvOtherInWarehsBill.getStatus())){
    		msgList.add(Message.getMessage(param.getLang(),"iscm.inventorymanage.cancelPost.error2",new String[] {scmInvOtherInWarehsBill.getWrNo()}));
    		return msgList;
    	}
		SystemState systemState = systemStateBiz.selectBySystemId(scmInvOtherInWarehsBill.getFinOrgUnitNo(), 170, param);
        if(systemState==null){
            msgList.add(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
            return msgList;
        }
        if (systemState.getCurrentPeriodId() != scmInvOtherInWarehsBill.getPeriodId()) {
            msgList.add(Message.getMessage(param.getLang(), 
                    "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmInvOtherInWarehsBill.getWrNo()}));
            return msgList;
        }
        
        // 检查盘点物资冻结
        // 检查冻结仓库
        List<ScmInvOtherInWarehsBill2> scmInvOtherInWarehsBillList = ((ScmInvOtherInWarehsBillDAO) dao).checkWareHouseFree(scmInvOtherInWarehsBill.getWrId());
        if (scmInvOtherInWarehsBillList != null && !scmInvOtherInWarehsBillList.isEmpty()) {
        	msgList.add(Message.getMessage(param.getLang(), 
                    "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
            return msgList;
//            for (ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill2 : scmInvOtherInWarehsBillList) {
//	            // 检查冻结物资
//	            HashMap<String,Object> map = new HashMap<String,Object>();
//	            map.put("taskId", scmInvOtherInWarehsBill2.getTaskId());
//	            map.put("wrId", scmInvOtherInWarehsBill.getWrId());
//	            int count = ((ScmInvOtherInWarehsBillDAO) dao).checkMaterialFree(map);
//	            if (count > 0) {
//	                msgList.add(Message.getMessage(param.getLang(), 
//	                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
//	                return msgList;
//	            }
//            }
        }
        
		if(!StringUtils.equals("6", scmInvOtherInWarehsBill.getBizType())) {
			List<ScmInvOtherInWarehsBillEntry2> list = scmInvOtherInWarehsBillEntryBiz.selectInvQty(scmInvOtherInWarehsBill.getWrId(), param);
	        
	        if (list != null && list.size() > 0) {
	            for (int i = 0; i < list.size(); i++) {
	                if (StringUtils.isNotBlank(list.get(i).getLot())) {
	                    msgList.add(Message.getMessage(param.getLang(), 
	                            "com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl.count", 
	                            new String[] {list.get(i).getItemNo(), list.get(i).getItemName(), list.get(i).getLot(),
	                                    list.get(i).getQty().toString(), list.get(i).getInvQty().toString()}));
	                } else {
	                    msgList.add(Message.getMessage(param.getLang(), 
	                            "com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl.count2", 
	                            new String[] {list.get(i).getItemNo(), list.get(i).getItemName(), 
	                                    list.get(i).getQty().toString(), list.get(i).getInvQty().toString()}));
	                }
	            }
	        }
		}
        return msgList;
	}

	@Override
	public ScmInvOtherInWarehsBill2 cancelPostBill(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill, Param param)
			throws AppException {
		scmInvOtherInWarehsBill = this.selectDirect(scmInvOtherInWarehsBill.getWrId(), param);
        if (scmInvOtherInWarehsBill != null) {
			if(!StringUtils.equals("E",scmInvOtherInWarehsBill.getStatus())) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvOtherInWarehsBill.getWrNo()}));
    		}
			if(StringUtils.equals("6", scmInvOtherInWarehsBill.getBizType())) {
				//寄存退货
				scmInvStockBiz.updateByCancelDepositInReturn(scmInvOtherInWarehsBill.getWrId(), param);
				//期间余额
				scmInvBalBiz.updateByCancelDepositInReturn(scmInvOtherInWarehsBill.getWrId(), param);
			}else {
	    		List<ScmInvOtherInWarehsBillEntry2> scmInvOtherInWarehsBillEntryList = scmInvOtherInWarehsBillEntryBiz.selectOutEffectRow(scmInvOtherInWarehsBill.getWrId(), param);
				//即时库存
				int updateRow = scmInvStockBiz.updateByCancelOtherInWarehsBill(scmInvOtherInWarehsBill.getWrId(), param);
				if(updateRow<scmInvOtherInWarehsBillEntryList.size()){
	    			throw new AppException("iscm.inventorymanage.common.post.error.rowsnotequal");
	    		}
				//期间余额
				if(!StringUtils.equals("1", scmInvOtherInWarehsBill.getBizType())){
					scmInvBalBiz.updateByCancelOtherInWarehsBill(scmInvOtherInWarehsBill.getWrId(), param);
				}else{
					//盘盈
					scmInvBalBiz.updateByCancelOtherInWarehsBillForInvProfit(scmInvOtherInWarehsBill.getWrId(), param);
				}
			}
			scmInvOtherInWarehsBill.setCheckDate(null);
			scmInvOtherInWarehsBill.setChecker("");
			scmInvOtherInWarehsBill.setStatus("A");
			scmInvOtherInWarehsBill.setPostDate(null);
			int updateRow = this.updatePostedStatus(scmInvOtherInWarehsBill, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvOtherInWarehsBill.getWrNo()}));
			}
			if(StringUtils.equals("5", scmInvOtherInWarehsBill.getBizType())) {
				//寄存入库时记录供应商批次关系
				scmInvSupplierLotBiz.delByOtherInWarehsBill(scmInvOtherInWarehsBill.getWrId(), param);
			}
		}
		return scmInvOtherInWarehsBill;
	}
	
	private void generateLot(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill,List<ScmInvOtherInWarehsBillEntry2> scmInvOtherInWarehsBillEntryList){
		if(!scmInvOtherInWarehsBill.isOffset() && !StringUtils.equals("1", scmInvOtherInWarehsBill.getBizType())){
            for(int i= 0;i<scmInvOtherInWarehsBillEntryList.size();i++){
            	scmInvOtherInWarehsBillEntryList.get(i).setLot("");
            }
            String addLot = CodeAutoCalUtil.autoAddOne("00");
            for(int i= 0;i<scmInvOtherInWarehsBillEntryList.size();i++){
                if(StringUtils.isBlank(scmInvOtherInWarehsBillEntryList.get(i).getLot())){
                    scmInvOtherInWarehsBillEntryList.get(i).setLot((scmInvOtherInWarehsBill.getWrNo())+"-"+addLot);
                    long itemId = scmInvOtherInWarehsBillEntryList.get(i).getItemId();
                    String outLot = addLot;
                    for(int j= i+1;j<scmInvOtherInWarehsBillEntryList.size();j++){
                    	if(StringUtils.isBlank(scmInvOtherInWarehsBillEntryList.get(j).getLot())){
                    		if(itemId == scmInvOtherInWarehsBillEntryList.get(j).getItemId()){
                    			outLot = CodeAutoCalUtil.autoAddOne(outLot);
                                scmInvOtherInWarehsBillEntryList.get(j).setLot((scmInvOtherInWarehsBill.getWrNo())+"-"+addLot);
                            }
                    	}
                    }
                }
            }
        }
	}

	@Override
	public String getBillNo(Date date, Param param) throws AppException {
		String dateStr = StringUtils.replace(FormatUtils.fmtDate(date), "-", "");
		StringBuffer s = new StringBuffer("");
		s.append("IO").append(dateStr);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("createDate", dateStr);
		map.put("controlUnitNo", param.getControlUnitNo());
		ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill= ((ScmInvOtherInWarehsBillDAO) dao).selectMaxIdByDate(map);
		if(scmInvOtherInWarehsBill != null && StringUtils.isNotBlank(scmInvOtherInWarehsBill.getWrNo())
				&& scmInvOtherInWarehsBill.getWrNo().contains(dateStr)){
			String str = StringUtils.substring(scmInvOtherInWarehsBill.getWrNo(), (scmInvOtherInWarehsBill.getWrNo().indexOf(dateStr)+dateStr.length()));
			str = CodeAutoCalUtil.autoAddOne(str);
			str = (s.append(str)).toString();
			return str;
		}else{
			return (s.append("0001").toString());
		}
	}

	@Override
	protected void beforeDelete(ScmInvOtherInWarehsBill2 entity, Param param)
			throws AppException {
		ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill = this.selectDirect(entity.getWrId(), param);
		if(scmInvOtherInWarehsBill!=null && !StringUtils.equals(scmInvOtherInWarehsBill.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getWrNo()}));
		}
	}
	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvOtherInWarehsBill2 entry = (ScmInvOtherInWarehsBill2) bean.getList().get(0);
		if(entry!=null){
			ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill = this.select(entry.getPK(), param);
			if(!StringUtils.equals(scmInvOtherInWarehsBill.getStatus(),"I")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
		}
	}

	@Override
	public List<ScmInvOtherInWarehsBill2> checkUnPostBill(String finOrgUnitNo,long periodId, Param param) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmInvOtherInWarehsBillDAO)dao).checkUnPostBill(map);
	}

	@Override
	public List<ScmInvOtherInWarehsBill2> checkPostedBill(String finOrgUnitNo,long periodId, Param param) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmInvOtherInWarehsBillDAO)dao).checkPostedBill(map);
	}

	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvOtherInWarehsBillAdvQuery) {
				ScmInvOtherInWarehsBillAdvQuery scmInvOtherInWarehsBillAdvQuery = (ScmInvOtherInWarehsBillAdvQuery) page.getModel();
				if(scmInvOtherInWarehsBillAdvQuery.getWareHouseId()>0) {
					page.getParam().put(ScmInvOtherInWarehsBill2.FN_WAREHOUSEID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." +ScmInvOtherInWarehsBill2.FN_WAREHOUSEID, QueryParam.QUERY_EQ,String.valueOf(scmInvOtherInWarehsBillAdvQuery.getWareHouseId())));
				}
				if(scmInvOtherInWarehsBillAdvQuery.getBizDateFrom()!=null){
					if(scmInvOtherInWarehsBillAdvQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmInvOtherInWarehsBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." +ScmInvOtherInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvOtherInWarehsBillAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(scmInvOtherInWarehsBillAdvQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmInvOtherInWarehsBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." +ScmInvOtherInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvOtherInWarehsBillAdvQuery.getBizDateFrom())));
					}
				}else if(scmInvOtherInWarehsBillAdvQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmInvOtherInWarehsBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." +ScmInvOtherInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmInvOtherInWarehsBillAdvQuery.getBizDateTo())));
				}
				if(scmInvOtherInWarehsBillAdvQuery.getCreateDateFrom()!=null){
					if(scmInvOtherInWarehsBillAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmInvOtherInWarehsBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." +ScmInvOtherInWarehsBill2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvOtherInWarehsBillAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvOtherInWarehsBillAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmInvOtherInWarehsBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." +ScmInvOtherInWarehsBill2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvOtherInWarehsBillAdvQuery.getCreateDateFrom())));
					}
				}else if(scmInvOtherInWarehsBillAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmInvOtherInWarehsBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." +ScmInvOtherInWarehsBill2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvOtherInWarehsBillAdvQuery.getCreateDateTo(),1))));
				}
				if(scmInvOtherInWarehsBillAdvQuery.getVendorId()>0) {
					page.getParam().put(ScmInvOtherInWarehsBill2.FN_VENDORID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." +ScmInvOtherInWarehsBill2.FN_VENDORID, QueryParam.QUERY_EQ,String.valueOf(scmInvOtherInWarehsBillAdvQuery.getVendorId())));
				}
				String filterWarehouseByUsr = ScmWarehouseUtil.filterWarehouseByUsr("", param);
				if (StringUtils.isNotBlank(filterWarehouseByUsr)) {
					page.getParam().put(ScmInvOtherInWarehsBill2.FN_WAREHOUSEID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." +ScmInvOtherInWarehsBill2.FN_WAREHOUSEID, QueryParam.QUERY_IN,filterWarehouseByUsr));
				}
			}
		}
	}

	@Override
	public ScmInvOtherInWarehsBill2 submit(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill, Param param)
			throws AppException {
		scmInvOtherInWarehsBill = this.selectDirect(scmInvOtherInWarehsBill.getWrId(), param);
		if(scmInvOtherInWarehsBill!=null){
			if(!scmInvOtherInWarehsBill.getStatus().equals("I")){
				throw new AppException("iscm.inventorymanage.error.commit");
			}else if(scmInvOtherInWarehsBill.getStatus().equals("I")){
				if(param.getUsrCode()!=null ){
					scmInvOtherInWarehsBill.setChecker(param.getUsrCode());
					scmInvOtherInWarehsBill.setSubmitter(param.getUsrCode());
				}
				scmInvOtherInWarehsBill.setCheckDate(CalendarUtil.getDate(param));
				scmInvOtherInWarehsBill.setSubmitDate(CalendarUtil.getDate(param));
				scmInvOtherInWarehsBill.setStatus("A");
				try{
					this.updateStatus(scmInvOtherInWarehsBill, param);
				}catch(AppException e){
					throw e;
				}
				SystemState systemState = systemStateBiz.selectBySystemId(scmInvOtherInWarehsBill.getFinOrgUnitNo(), 170, param);
				if(systemState==null){
					throw new AppException("com.armitage.iars.daily.util.disenable.unable");
				}
				if (systemState.getCurrentPeriodId() == scmInvOtherInWarehsBill.getPeriodId()) {
					if(StringUtils.equals("A", scmInvOtherInWarehsBill.getStatus())) {
						//通过或部分通过时检查是否自动过帐
						BillType2 billType = billTypeBiz.selectByOrgAndCode(scmInvOtherInWarehsBill.getFinOrgUnitNo(), "InvMoveInWhsBill", param);
						if(billType!=null && billType.isAutoRelease()) {
							List<String> msgList = this.postBillCheck(scmInvOtherInWarehsBill, param);
							if (msgList != null && !msgList.isEmpty()) {
								StringBuilder detailInfo = new StringBuilder("");
		                        detailInfo.append(Message.getMessage(param.getLang(),"iscm.inventorymanage.otherinwarehsbill.post.errorTitle"));
	
								for (String str : msgList) {
		                            detailInfo.append(str).append("\n");
		                        }
								
								throw new AppException(detailInfo.toString(), new String[]{scmInvOtherInWarehsBill.getWrNo()});
							}
							
							this.postBill(scmInvOtherInWarehsBill, param);
						}
					}
				}
			}
		}else{
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.find.ordernotexists");
		}
		return scmInvOtherInWarehsBill;
	}

	@Override
	public ScmInvOtherInWarehsBill2 undoSubmit(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill, Param param)
			throws AppException {
		scmInvOtherInWarehsBill = this.selectDirect(scmInvOtherInWarehsBill.getWrId(), param);
		if(scmInvOtherInWarehsBill!=null){
			if(!scmInvOtherInWarehsBill.getStatus().equals("A")){
				throw new AppException("iscm.inventorymanage.error.cancelCommit");
			}else if(scmInvOtherInWarehsBill.getStatus().equals("A")){
				scmInvOtherInWarehsBill.setChecker(null);
				scmInvOtherInWarehsBill.setSubmitter(null);
				scmInvOtherInWarehsBill.setCheckDate(null);
				scmInvOtherInWarehsBill.setSubmitDate(null);
				scmInvOtherInWarehsBill.setStatus("I");
				try{
					this.updateStatus(scmInvOtherInWarehsBill, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.find.ordernotexists");
		}
		return scmInvOtherInWarehsBill;
	}
	
	@Override
	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map) {
		return ((ScmInvOtherInWarehsBillDAO)dao).countUnPostBill(map);
	}

	@Override
	public ScmInvOtherInWarehsBill2 updatePrtCount(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill, Param param)
			throws AppException {
		if(scmInvOtherInWarehsBill.getWrId()>0){
			ScmInvOtherInWarehsBill2 bill = this.selectDirect(scmInvOtherInWarehsBill.getWrId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmInvOtherInWarehsBill;
	}

	@Override
	public DataSyncResult dataSync(InvOtherInWarehsListParams invOtherInWarehsListParam,List<ScmInvOtherInWarehsBill2> scmInvOtherInWarehsBill2s, Param param) throws AppException {
		DataSyncResult dataSyncResult = new DataSyncResult();
		if(StringUtils.isBlank(invOtherInWarehsListParam.getWrNo())&& StringUtils.equals(invOtherInWarehsListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billNo.notExist");
		}
		if(StringUtils.isBlank(invOtherInWarehsListParam.getPostStatus())&& StringUtils.equals(invOtherInWarehsListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billStatus.notExist");
		}
		if(invOtherInWarehsListParam.getBizDate() == null&& StringUtils.equals(invOtherInWarehsListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billBizDate.notExist");
		}
		if((invOtherInWarehsListParam.getDetailList() == null || invOtherInWarehsListParam.getDetailList().isEmpty())&& StringUtils.equals(invOtherInWarehsListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billEntry.notExist");
		}
		if(!StringUtils.isNotBlank(invOtherInWarehsListParam.getWhNo())&& StringUtils.equals(invOtherInWarehsListParam.getDelete(), "N")) {
			throw new AppException("iscm.api.datePut.billWhNo.notExist");
		}
		//获取仓库					
		ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectByWhNo(param.getOrgUnitNo(), invOtherInWarehsListParam.getWhNo(), param);
		if(scmInvWareHouse == null&& StringUtils.equals(invOtherInWarehsListParam.getDelete(), "N"))
			throw new AppException("iscm.api.datePut.billWhNo.notExist");
		for(ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill:scmInvOtherInWarehsBill2s) {
			if(StringUtils.equals(invOtherInWarehsListParam.getWrNo(), scmInvOtherInWarehsBill.getWrNo())) {
				//删除				
				if(StringUtils.equals(invOtherInWarehsListParam.getDelete(),"Y")) {
					scmInvOtherInWarehsBillEntryBiz.deleteByWrId(scmInvOtherInWarehsBill.getWrId(), param);
					((ScmInvOtherInWarehsBillDAO) dao).delete(scmInvOtherInWarehsBill.getPK()+"");
					dataSyncResult.setNo(invOtherInWarehsListParam.getWrNo());
					dataSyncResult.setErrCode("0");
					return dataSyncResult;
				}
				ArrayList<ScmInvOtherInWarehsBillEntry2> scmInvOtherInWarehsBillEntryAdd = new ArrayList<ScmInvOtherInWarehsBillEntry2>();
				for(InvOtherInWarehsDetailParams invOtherInWarehsDetailParam :invOtherInWarehsListParam.getDetailList()) {
					ScmInvOtherInWarehsBillEntry2 scmInvOtherInWarehsBillEntry = new ScmInvOtherInWarehsBillEntry2(true);
					BeanUtils.copyProperties(invOtherInWarehsDetailParam, scmInvOtherInWarehsBillEntry);
					if(!scmMatrialMap.containsKey(invOtherInWarehsDetailParam.getItemNo())) {
						ScmMaterial2 selectByItemNo = scmMaterialBiz.selectByItemNo(param.getControlUnitNo(), invOtherInWarehsDetailParam.getItemNo(), param);
						if(selectByItemNo == null)
							throw new AppException("iscm.api.datePut.billItemNo.notExist");
						scmMatrialMap.put(selectByItemNo.getItemNo(), selectByItemNo.getId());
					}
					scmInvOtherInWarehsBillEntry.setItemId(scmMatrialMap.get(invOtherInWarehsDetailParam.getItemNo()));
					if(!scmUnitMap.containsKey(scmInvOtherInWarehsBillEntry.getItemId())) {
						ScmMaterialInventory2 selectByItemIdAndOrgUnitNo = scmMaterialInventoryBiz.selectByItemId(scmInvOtherInWarehsBillEntry.getItemId(), param.getOrgUnitNo(), param.getControlUnitNo(), param);
						if(selectByItemIdAndOrgUnitNo == null)
							throw new AppException("iscm.api.datePut.billUnitNo.notExist");
						scmUnitMap.put(selectByItemIdAndOrgUnitNo.getItemId(), selectByItemIdAndOrgUnitNo.getUnitId());
					}
					scmInvOtherInWarehsBillEntry.setUnit(scmUnitMap.get(scmInvOtherInWarehsBillEntry.getItemId()));
					scmInvOtherInWarehsBillEntry.setOrgUnitNo(param.getOrgUnitNo());;
					scmInvOtherInWarehsBillEntry.setWareHouseId(scmInvWareHouse.getId());
					scmInvOtherInWarehsBillEntry.setWrId(scmInvOtherInWarehsBill.getWrId());
					scmInvOtherInWarehsBillEntry.setWrNo(scmInvOtherInWarehsBill.getWrNo());
					scmInvOtherInWarehsBillEntry.setBaseQty(invOtherInWarehsDetailParam.getQty());
					scmInvOtherInWarehsBillEntry.setInvDate(scmInvOtherInWarehsBill.getBizDate());
					scmInvOtherInWarehsBillEntryAdd.add(scmInvOtherInWarehsBillEntry);
				}
				//2、	过账 》过账 ok
				if(StringUtils.equals(invOtherInWarehsListParam.getPostStatus(), "1")&& "E,C".contains(scmInvOtherInWarehsBill.getStatus())) {
					throw new AppException("iscm.api.business.datasync.controller.posted");
				//3、	非过账 》过账
				}else if(!"E,C".contains(scmInvOtherInWarehsBill.getStatus())&& StringUtils.equals(invOtherInWarehsListParam.getPostStatus(), "1")) {
					//更新主子表
					refreshData(invOtherInWarehsListParam,scmInvOtherInWarehsBill,param,scmInvWareHouse);
					scmInvOtherInWarehsBill.setStatus("E");
					update(scmInvOtherInWarehsBill, param);
					scmInvOtherInWarehsBillEntryBiz.deleteByWrId(scmInvOtherInWarehsBill.getWrId(), param);
					scmInvOtherInWarehsBillEntryBiz.add(scmInvOtherInWarehsBillEntryAdd, param);
					//期间余额
					scmInvBalBiz.updateByOtherInWarehsBillForDataSnyc(scmInvOtherInWarehsBill.getWrId(), param);
					scmInvBalBiz.addByOtherInWarehsBillForInvProfit(scmInvOtherInWarehsBill.getWrId(), param);
					//返回结果					
					dataSyncResult.setNo(invOtherInWarehsListParam.getWrNo());
					dataSyncResult.setErrCode("0");
					return dataSyncResult;
				//4、	过账 》非过账	ok				
				}else if("E,C".contains(scmInvOtherInWarehsBill.getStatus()) && StringUtils.equals(invOtherInWarehsListParam.getPostStatus(), "0")){
					throw new AppException("iscm.api.business.datasync.controller.cancelPost");
				//5、	非过账 》非过账				
				}else{
					refreshData(invOtherInWarehsListParam,scmInvOtherInWarehsBill,param,scmInvWareHouse);
					update(scmInvOtherInWarehsBill, param);
					scmInvOtherInWarehsBillEntryBiz.deleteByWrId(scmInvOtherInWarehsBill.getWrId(), param);
					scmInvOtherInWarehsBillEntryBiz.add(scmInvOtherInWarehsBillEntryAdd, param);
					dataSyncResult.setNo(invOtherInWarehsListParam.getWrNo());
					dataSyncResult.setErrCode("0");
					return dataSyncResult;
				}
			}
		}
		if(StringUtils.equals(invOtherInWarehsListParam.getDelete(),"Y")) {
			dataSyncResult.setNo(invOtherInWarehsListParam.getWrNo());
			dataSyncResult.setErrCode("0");
			return dataSyncResult;
		}
		//1、	新增 √
		ArrayList<ScmInvOtherInWarehsBill2> scmInvOtherInWarehsBillAdd = new ArrayList<ScmInvOtherInWarehsBill2>();
		ArrayList<ScmInvOtherInWarehsBillEntry2> scmInvOtherInWarehsEntryAdd2 = new ArrayList<ScmInvOtherInWarehsBillEntry2>();
		ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill = new ScmInvOtherInWarehsBill2(true);
		BeanUtils.copyProperties(invOtherInWarehsListParam, scmInvOtherInWarehsBill);
		beforeAdd(scmInvOtherInWarehsBill, param);
		scmInvOtherInWarehsBill.setStatus("I");
		if(StringUtils.equals(invOtherInWarehsListParam.getPostStatus(),"1")) 
			scmInvOtherInWarehsBill.setStatus("E");
		scmInvOtherInWarehsBill.setCreator("");
		scmInvOtherInWarehsBill.setBizType("1");
		scmInvOtherInWarehsBill.setCreateDate(invOtherInWarehsListParam.getBizDate());
		scmInvOtherInWarehsBill.setWrNo(invOtherInWarehsListParam.getWrNo());
		scmInvOtherInWarehsBill.setWareHouseId(scmInvWareHouse.getId());
		scmInvOtherInWarehsBill.setOrgUnitNo(param.getOrgUnitNo());
		scmInvOtherInWarehsBill.setControlUnitNo(param.getControlUnitNo());
		List<OrgCompany2> orgCompanylist2 = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, scmInvOtherInWarehsBill.getOrgUnitNo(), false, null, param);
		if(orgCompanylist2 != null && !orgCompanylist2.isEmpty()){
			scmInvOtherInWarehsBill.setFinOrgUnitNo(orgCompanylist2.get(0).getOrgUnitNo());
		}else {
			throw new AppException("iscm.api.datePut.billInvOrgUnitNo.notToFin");
		}
		((ScmInvOtherInWarehsBillDAO) dao).add(scmInvOtherInWarehsBill);
		for(InvOtherInWarehsDetailParams invOtherInWarehsDetailParam :invOtherInWarehsListParam.getDetailList()) {
			ScmInvOtherInWarehsBillEntry2 scmInvOtherInWarehsBillEntry = new ScmInvOtherInWarehsBillEntry2(true);
			BeanUtils.copyProperties(invOtherInWarehsDetailParam, scmInvOtherInWarehsBillEntry);
			if(!scmMatrialMap.containsKey(invOtherInWarehsDetailParam.getItemNo())) {
				ScmMaterial2 selectByItemNo = scmMaterialBiz.selectByItemNo(param.getControlUnitNo(), invOtherInWarehsDetailParam.getItemNo(), param);
				if(selectByItemNo == null)
					throw new AppException("iscm.api.datePut.billItemNo.notExist");
				scmMatrialMap.put(selectByItemNo.getItemNo(), selectByItemNo.getId());
			}
			scmInvOtherInWarehsBillEntry.setItemId(scmMatrialMap.get(invOtherInWarehsDetailParam.getItemNo()));
			if(!scmUnitMap.containsKey(scmInvOtherInWarehsBillEntry.getItemId())) {
				ScmMaterialInventory2 selectByItemIdAndOrgUnitNo = scmMaterialInventoryBiz.selectByItemId(scmInvOtherInWarehsBillEntry.getItemId(), param.getOrgUnitNo(), param.getControlUnitNo(), param);
				if(selectByItemIdAndOrgUnitNo == null)
					throw new AppException("iscm.api.datePut.billUnitNo.notExist");
				scmUnitMap.put(selectByItemIdAndOrgUnitNo.getItemId(), selectByItemIdAndOrgUnitNo.getUnitId());
			}
			scmInvOtherInWarehsBillEntry.setStatus("I");
			if(StringUtils.equals(invOtherInWarehsListParam.getPostStatus(),"1")) 
				scmInvOtherInWarehsBillEntry.setStatus("E");
			scmInvOtherInWarehsBillEntry.setUnit(scmUnitMap.get(scmInvOtherInWarehsBillEntry.getItemId()));
			scmInvOtherInWarehsBillEntry.setWrNo(scmInvOtherInWarehsBill.getWrNo());
			scmInvOtherInWarehsBillEntry.setBaseQty(invOtherInWarehsDetailParam.getQty());
			scmInvOtherInWarehsBillEntry.setInvDate(scmInvOtherInWarehsBill.getBizDate());
			scmInvOtherInWarehsBillEntry.setWrId(scmInvOtherInWarehsBill.getWrId());
			scmInvOtherInWarehsBillEntry.setWareHouseId(scmInvWareHouse.getId());
			scmInvOtherInWarehsEntryAdd2.add(scmInvOtherInWarehsBillEntry);
		}
		scmInvOtherInWarehsBillEntryBiz.add(scmInvOtherInWarehsEntryAdd2, param);
		if(StringUtils.equals(invOtherInWarehsListParam.getPostStatus(),"1")) {
			//盘盈时期间余额
			scmInvBalBiz.updateByOtherInWarehsBillForDataSnyc(scmInvOtherInWarehsBill.getWrId(), param);
			scmInvBalBiz.addByOtherInWarehsBillForInvDataSync(scmInvOtherInWarehsBill.getWrId(), param);
		}
		dataSyncResult.setNo(invOtherInWarehsListParam.getWrNo());
		dataSyncResult.setErrCode("0");
		return dataSyncResult;
	}

	private void refreshData(InvOtherInWarehsListParams invOtherInWarehsListParam,ScmInvOtherInWarehsBill2 scmInvOtherInWarehsBill,Param param,ScmInvWareHouse scmInvWareHouse) throws AppException{
		BeanUtils.copyProperties(invOtherInWarehsListParam, scmInvOtherInWarehsBill);
		List<OrgCompany2> orgCompanylist2 = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, scmInvOtherInWarehsBill.getOrgUnitNo(), false, null, param);
		if(orgCompanylist2 != null && !orgCompanylist2.isEmpty()){
			scmInvOtherInWarehsBill.setFinOrgUnitNo(orgCompanylist2.get(0).getOrgUnitNo());
		}else {
			throw new AppException("iscm.api.datePut.billInvOrgUnitNo.notToFin");
		}
		scmInvOtherInWarehsBill.setBizType("1");
		scmInvOtherInWarehsBill.setWareHouseId(scmInvWareHouse.getId());
		scmInvOtherInWarehsBill.setOrgUnitNo(param.getOrgUnitNo());
		scmInvOtherInWarehsBill.setCreateOrgUnitNo(param.getOrgUnitNo());
		scmInvOtherInWarehsBill.setControlUnitNo(param.getControlUnitNo());
	}
}

