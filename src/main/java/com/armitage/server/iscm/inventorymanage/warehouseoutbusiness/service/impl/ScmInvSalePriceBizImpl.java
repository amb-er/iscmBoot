package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

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
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvSalePriceDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePrice;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePrice2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePriceAdd;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePriceAddDetail;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePriceAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePriceentry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSalePriceBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSalePriceentryBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.Employee;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.EmployeeBiz;
import com.armitage.server.system.service.OrgBaseUnitBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmInvSalePriceBiz")
public class ScmInvSalePriceBizImpl extends BaseBizImpl<ScmInvSalePrice> implements ScmInvSalePriceBiz{
	private UsrBiz usrBiz;
	private OrgBaseUnitBiz orgBaseUnitBiz;
	private EmployeeBiz employeeBiz;
	private ScmInvSalePriceentryBiz scmInvSalePriceentryBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private OrgStorageBiz orgStorageBiz;
	private BillTypeBiz billTypeBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private ScmBillPendingBiz scmBillPendingBiz;
	
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setOrgBaseUnitBiz(OrgBaseUnitBiz orgBaseUnitBiz) {
		this.orgBaseUnitBiz = orgBaseUnitBiz;
	}

	public void setScmInvSalePriceentryBiz(ScmInvSalePriceentryBiz scmInvSalePriceentryBiz) {
		this.scmInvSalePriceentryBiz = scmInvSalePriceentryBiz;
	}

	public void setEmployeeBiz(EmployeeBiz employeeBiz) {
		this.employeeBiz = employeeBiz;
	}
	
	public ScmMaterialBiz getScmMaterialBiz() {
		return scmMaterialBiz;
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

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvSalePrice.class) + "." + ScmInvSalePrice.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvSalePrice.class) + "." + ScmInvSalePrice.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvSalePrice.class) + "." + ScmInvSalePrice.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvSalePrice.class) + "." + ScmInvSalePrice.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}
	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		HashMap<String,Object> cacheMap = new HashMap<String,Object>();
		List<ScmInvSalePrice> scmInvSalePriceList = list;
		if(scmInvSalePriceList != null && !scmInvSalePriceList.isEmpty()){
			for(ScmInvSalePrice scmInvSalePrice : scmInvSalePriceList){
				if(StringUtils.isNotBlank(scmInvSalePrice.getOrgUnitNo())){
					//销售组织
					OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvSalePrice.getOrgUnitNo());
					if(orgBaseUnit==null){
						orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmInvSalePrice.getOrgUnitNo(), param);
						cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvSalePrice.getOrgUnitNo(),orgBaseUnit);
					}
					if (orgBaseUnit != null) {
						scmInvSalePrice.setConvertMap(ScmInvSalePrice.FN_ORGUNITNO, orgBaseUnit);
					}
				}
				if (StringUtils.isNotBlank(scmInvSalePrice.getCreator())){
					//制单人
					Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvSalePrice.getCreator());
					if(usr==null){
						usr = usrBiz.selectByCode(scmInvSalePrice.getCreator(), param);
						cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmInvSalePrice.getCreator(),usr);
					}
					if (usr != null) {
						if(scmInvSalePrice.getDataDisplayMap()==null){
							scmInvSalePrice.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
						}
						scmInvSalePrice.getDataDisplayMap().put(ScmInvSalePrice.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
					}
				}
			}
		}
	}

	@Override
	protected void beforeAdd(ScmInvSalePrice entity, Param param) throws AppException {
		if(entity != null){
			String code = CodeAutoCalUtil.getBillCode("InvSalePrice", entity, param);
			entity.setPmNo(code);
			List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, entity.getOrgUnitNo(), false, null, param);
			if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
			}
			entity.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
		}
	}
	
	@Override
	protected void afterSelect(ScmInvSalePrice entity, Param param) throws AppException {
		if(entity!= null){
			HashMap<String,Object> cacheMap = new HashMap<String,Object>();
			if (StringUtils.isNotBlank(entity.getPriceName())){
				//定价员
				Employee scmPurBuyer = employeeBiz.selectByKey(entity.getPriceName(), param);
				if (scmPurBuyer != null) {
					entity.setConvertMap(ScmInvSalePrice.FN_PRICENAME, scmPurBuyer);
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
					entity.setConvertMap(ScmInvSalePrice.FN_CREATOR, usr);
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
					entity.setConvertMap(ScmInvSalePrice.FN_EDITOR, usr);
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
					entity.setConvertMap(ScmInvSalePrice.FN_CHECKER, usr);
				}
			}
		}
	}
	
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmInvSalePrice scmInvSalePrice = (ScmInvSalePrice) bean.getList().get(0);
		HashMap<String,Object> cacheMap = new HashMap<String,Object>();
		if(scmInvSalePrice != null && scmInvSalePrice.getPmId() > 0){
			List<ScmInvSalePriceentry2> scmInvSalePriceEntryList = scmInvSalePriceentryBiz.selectByPmId(scmInvSalePrice.getPmId(), param);
			if(scmInvSalePriceEntryList != null && !scmInvSalePriceEntryList.isEmpty()){
				bean.setList2(scmInvSalePriceEntryList);
			}
		}
	}
	
	
	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmInvSalePrice scmInvPurInWarehsBill = (ScmInvSalePrice) bean.getList().get(0);
		if(scmInvPurInWarehsBill != null && scmInvPurInWarehsBill.getPmId() > 0){
			//新增定价明细
			List<ScmInvSalePriceentry2> scmInvPurInWarehsBillEntryList = bean.getList2();
			if(scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()){
                int lineId = 1;
				for(ScmInvSalePriceentry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList){
					scmInvPurInWarehsBillEntry.setPmId(scmInvPurInWarehsBill.getPmId());
					scmInvPurInWarehsBillEntry.setLineId(lineId);
					scmInvSalePriceentryBiz.add(scmInvPurInWarehsBillEntry, param);
                    lineId = lineId+1;
				}
			}
		}
	}
	
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvSalePrice scmInvPurInWarehsBill = (ScmInvSalePrice) bean.getList().get(0);
		if(scmInvPurInWarehsBill != null && scmInvPurInWarehsBill.getPmId() > 0){
			//更新定价明细
			List<ScmInvSalePriceentry2> scmInvPurInWarehsBillEntryList = bean.getList2();
			if(scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()){
                int lineId = 1;
				for(ScmInvSalePriceentry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList){
					scmInvPurInWarehsBillEntry.setPmId(scmInvPurInWarehsBill.getPmId());
					if (StringUtils.equals("I", scmInvPurInWarehsBill.getStatus())) {
						scmInvPurInWarehsBillEntry.setLineId(lineId);
					}
                    lineId = lineId+1;
				}
				scmInvSalePriceentryBiz.update(scmInvPurInWarehsBill, scmInvPurInWarehsBillEntryList, ScmInvSalePriceentry2.FN_PMID, param);
			}
		}
	}
	
	@Override
	protected void afterDelete(ScmInvSalePrice entity, Param param) throws AppException {
		if(entity != null && entity.getPmId() > 0){
			//删除定价明细
			scmInvSalePriceentryBiz.deleteByPmId(entity.getPmId(), param);
		}
	}

    @Override
    public List<ScmInvSalePrice2> findByBizDateAndItemId(HashMap<String, Object> map, Param param) throws AppException {
        return ((ScmInvSalePriceDAO) dao).findByBizDateAndItemId(map);
    }

	
	private ScmInvSalePrice updateStatus(ScmInvSalePrice scmInvSalePrice, Param param) throws AppException {
		if(scmInvSalePrice != null){
			ScmInvSalePrice scmInvSalePrice2 = this.updateDirect(scmInvSalePrice, param);
			if(scmInvSalePrice2 != null){
				scmInvSalePriceentryBiz.updateRowStatusByPmId(scmInvSalePrice2.getPmId(), scmInvSalePrice2.getStatus(), scmInvSalePrice.getChecker(), scmInvSalePrice.getCheckDate(), param);
				return scmInvSalePrice2;
			}
		}
		return null;
	}

	@Override
	protected void beforeDelete(ScmInvSalePrice entity, Param param)
			throws AppException {
		ScmInvSalePrice scmInvSalePric = this.selectDirect(entity.getPmId(), param);
		if(scmInvSalePric!=null && !StringUtils.equals(scmInvSalePric.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getPmNo()}));
		}
	}

	@Override
	public List<ScmMaterialPrice> getPrice(String invOrgUnitNo, String itemIds,Date bizDate, Param param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNo", invOrgUnitNo);
		map.put("itemIds", itemIds);
		map.put("bizDate", FormatUtils.fmtDate(bizDate));
		//兼容5.7及以上数据库获取最新价格无效（子查询排序无效）
		map.put("maxInt", Integer.MAX_VALUE);
		return ((ScmInvSalePriceDAO)dao).getPrice(map);
	}
	
	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvSalePrice entry = (ScmInvSalePrice) bean.getList().get(0);
		if(entry!=null){
			ScmInvSalePrice scmInvSalePrice = this.select(entry.getPK(), param);
			if(!StringUtils.equals(scmInvSalePrice.getStatus(),"I")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
			List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, entry.getOrgUnitNo(), false, null, param);
			if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
			}
			entry.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
		}
	}

	@Override
	public ScmInvSalePrice doAddInvSalePrice(
			ScmInvSalePriceAdd scmInvSalePriceAdd, Param param)
			throws AppException {
		ScmInvSalePrice scmInvSalePrice = new ScmInvSalePrice();
		BeanUtils.copyProperties(scmInvSalePriceAdd, scmInvSalePrice);
		this.beforeAdd(scmInvSalePrice, param);
		Page page=new Page();
		page.setModelClass(ScmInvSalePrice.class);
		page.setShowCount(Integer.MAX_VALUE);
		if(StringUtils.isNotBlank(scmInvSalePrice.getPmNo())){
			page.getParam().put(
					ScmInvSalePrice.FN_PMNO,
					new QueryParam(ScmInvSalePrice.FN_PMNO, QueryParam.QUERY_EQ,
							scmInvSalePrice.getPmNo()));
		}
		
		List<ScmInvSalePrice> scmInvSalePriceList = this.findPage(page, param);
		if(scmInvSalePriceList!=null && !scmInvSalePriceList.isEmpty()){
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.ScmInvSalePrice.error.areadyExists");
		}else{
			try {
				scmInvSalePrice.setStatus("I");
				scmInvSalePrice.setPrtcount(0);
				scmInvSalePrice.setCurrencyNo("");
				scmInvSalePrice.setCreator(param.getUsrCode());
				scmInvSalePrice.setCreateDate(CalendarUtil.getDate(param));
				scmInvSalePrice=this.add(scmInvSalePrice, param);
				
				if(scmInvSalePrice==null){
					throw new AppException("iscm.purchasemanage.error.Failadd");
				}
				
				StringBuffer itemNos = new StringBuffer("");
				List<ScmInvSalePriceentry2> scmInvSalePriceentryList = new ArrayList<ScmInvSalePriceentry2>();
				List<ScmInvSalePriceAddDetail> scmInvSalePriceAddDetailList=scmInvSalePriceAdd.getDetailList();
				
				for(ScmInvSalePriceAddDetail scmInvSalePriceAddDetail : scmInvSalePriceAddDetailList) {
					if(StringUtils.isBlank(scmInvSalePriceAddDetail.getItemNo())){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemisnull");
					}
					if(StringUtils.isNotBlank(itemNos.toString()))
						itemNos.append(",");
					itemNos.append("'").append(scmInvSalePriceAddDetail.getItemNo()).append("'");
					
					ScmInvSalePriceentry2 scmInvSalePriceentry = new ScmInvSalePriceentry2();
					BeanUtils.copyProperties(scmInvSalePriceAddDetail,scmInvSalePriceentry);
					scmInvSalePriceentryList.add(scmInvSalePriceentry);
				}
				
				page = new Page();
				page.setModelClass(ScmMaterial2.class);
				page.setShowCount(Integer.MAX_VALUE);
				page.setSqlCondition("ScmMaterial.itemNo in ("+itemNos.toString()+")");
				ArrayList argList = new ArrayList();
		        argList.add("orgUnitNo="+param.getOrgUnitNo());
		        argList.add("controlUnitNo=" + param.getControlUnitNo());
		        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "findByPurAllPage", param);
				
		        HashMap<String,Object> materialMap = new HashMap<String,Object>();
				int i=1;
				Long pmId = scmInvSalePrice.getPmId();
				
				for(ScmInvSalePriceentry2 scmInvSalePriceentry : scmInvSalePriceentryList) {
					materialMap.put(ScmMaterial2.FN_ITEMNO, scmInvSalePriceentry.getItemNo());
					materialMap.put(ScmMaterial2.FN_CONTROLUNITNO, param.getControlUnitNo());
					for(ScmMaterial2 scmMaterial:scmMaterialList){
						if(StringUtils.equals(scmMaterial.getItemNo(), scmInvSalePriceentry.getItemNo())){
							scmInvSalePriceentry.setItemId(scmMaterial.getId());
							scmInvSalePriceentry.setUnit(scmMaterial.getPurUnitId());
							break;
						}
					}
					
					if(scmInvSalePriceentry.getPreSaleTaxPrice() == null) {
						scmInvSalePriceentry.setPreSaleTaxPrice(BigDecimal.ZERO);
					}
					scmInvSalePriceentry.setPmId(pmId);
					scmInvSalePriceentry.setRowStatus("I");
					scmInvSalePriceentry.setLineId(i++);
					ScmInvSalePriceentry2 entry2 = scmInvSalePriceentryBiz.add(scmInvSalePriceentry, param);
					if(entry2==null){
						throw new AppException("iscm.purchasemanage.error.Failadd");
					}	
				}
			} catch (Exception e) {
				throw e;
			}
			
		}
		
		return scmInvSalePrice;
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvSalePriceAdvQuery) {
				ScmInvSalePriceAdvQuery scmInvSalePriceAdvQuery = (ScmInvSalePriceAdvQuery) page.getModel();
				if(StringUtils.isNotBlank(scmInvSalePriceAdvQuery.getPriceName())) {
					page.getParam().put(ScmInvSalePrice.FN_PRICENAME,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSalePrice.class) + "." +ScmInvSalePrice.FN_PRICENAME, QueryParam.QUERY_EQ,scmInvSalePriceAdvQuery.getPriceName()));
				}
				if(scmInvSalePriceAdvQuery.getBizDateFrom()!=null){
					if(scmInvSalePriceAdvQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmInvSalePrice.FN_PMDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSalePrice.class) + "." +ScmInvSalePrice.FN_PMDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvSalePriceAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(scmInvSalePriceAdvQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmInvSalePrice.FN_PMDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSalePrice.class) + "." +ScmInvSalePrice.FN_PMDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvSalePriceAdvQuery.getBizDateFrom())));
					}
				}else if(scmInvSalePriceAdvQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmInvSalePrice.FN_PMDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSalePrice.class) + "." +ScmInvSalePrice.FN_PMDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmInvSalePriceAdvQuery.getBizDateTo())));
				}
				if(scmInvSalePriceAdvQuery.getCreateDateFrom()!=null){
					if(scmInvSalePriceAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmInvSalePrice.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSalePrice.class) + "." +ScmInvSalePrice.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvSalePriceAdvQuery.getCreateDateTo()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvSalePriceAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmInvSalePrice.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSalePrice.class) + "." +ScmInvSalePrice.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvSalePriceAdvQuery.getCreateDateFrom())));
					}
				}else if(scmInvSalePriceAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmInvSalePrice.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSalePrice.class) + "." +ScmInvSalePrice.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvSalePriceAdvQuery.getCreateDateTo(),1))));
				}
			}
		}
	}

	@Override
	public ScmInvSalePrice submit(ScmInvSalePrice scmInvSalePrice, Param param) throws AppException {
		ScmInvSalePrice invBill = null;
		if(scmInvSalePrice.getPmId()>0){
			invBill = this.selectDirect(scmInvSalePrice.getPmId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmInvSalePrice.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmInvSalePrice.FN_PMNO,new QueryParam(ScmInvSalePrice.FN_PMNO, QueryParam.QUERY_EQ,scmInvSalePrice.getPmNo()));
			
			List<ScmInvSalePrice> scmInvSalePriceList =this.findPage(page, param);
			if(scmInvSalePriceList!=null && !scmInvSalePriceList.isEmpty()){
				invBill = scmInvSalePriceList.get(0);
			}
		}
		if(invBill!=null) {
			if(!invBill.getStatus().equals("I")){
				throw new AppException("iscm.purchasemanage.error.commit");
			}else if(invBill.getStatus().equals("I")){
				BillType2 billType = billTypeBiz.selectByOrgAndCode(invBill.getFinOrgUnitNo(), "InvSalePrice", param);
				boolean needAudit = false;
				if(billType!=null && billType.isNeedAudit())
					needAudit = true;
				
				if(needAudit){
					//发起流程
					List<ScmInvSalePriceentry2> scmInvSalePriceEntryList = scmInvSalePriceentryBiz.selectByPmId(scmInvSalePrice.getPmId(), param);
					
					CommonBean bean = new CommonBean();
					List<ScmInvSalePrice> scmInvSalePriceList = new ArrayList();
					scmInvSalePriceList.add(invBill);
					bean.setList(scmInvSalePriceList);
					bean.setList2(scmInvSalePriceEntryList);
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
//							purQuotationParams.setPqNo(invBill.getPmNo());
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
	public ScmInvSalePrice undoSubmit(ScmInvSalePrice scmInvSalePrice, Param param) throws AppException {
		ScmInvSalePrice invBill = null;
		if(scmInvSalePrice.getPmId()>0){
			invBill = this.selectDirect(scmInvSalePrice.getPmId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmInvSalePrice.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmInvSalePrice.FN_PMNO,new QueryParam(ScmInvSalePrice.FN_PMNO, QueryParam.QUERY_EQ,scmInvSalePrice.getPmNo()));
			
			List<ScmInvSalePrice> scmInvSalePriceList =this.findPage(page, param);
			if(scmInvSalePriceList!=null && !scmInvSalePriceList.isEmpty()){
				invBill = scmInvSalePriceList.get(0);
			}
		}
		if(invBill!=null) {
			BillType2 billType = billTypeBiz.selectByOrgAndCode(scmInvSalePrice.getFinOrgUnitNo(), "InvSalePrice", param);
			boolean needAudit = false;
			if(billType!=null && billType.isNeedAudit() && StringUtils.isNotBlank(invBill.getWorkFlowNo()))
				needAudit = true;
			
			//单据设置审批流程，但设置没有审批节点，状态为通过，可以取消通过
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ScmBillPending.FN_BILLID, new QueryParam(ScmBillPending.FN_BILLID, QueryParam.QUERY_EQ, String.valueOf(invBill.getPmId())));
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
				if(!StringUtils.equals(invBill.getStatus(),"A"))
					throw new AppException("iscm.purchasemanage.error.cancelCommit");
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
	public ScmInvSalePrice release(ScmInvSalePrice scmInvSalePrice, Param param) throws AppException {
		if(!StringUtils.equals("A",scmInvSalePrice.getStatus()))
			throw new AppException("iscm.purchasemanage.error.release");
		scmInvSalePrice.setStatus("E");
		return this.updateStatus(scmInvSalePrice, param);
	}

	@Override
	public ScmInvSalePrice undoRelease(ScmInvSalePrice scmInvSalePrice, Param param) throws AppException {
		if(!StringUtils.equals("E",scmInvSalePrice.getStatus()))
			throw new AppException("iscm.purchasemanage.error.cancelRelease");
		undoReleaseCheck(scmInvSalePrice,param);
		scmInvSalePrice.setStatus("A");
		return this.updateStatus(scmInvSalePrice, param);
	}

	private void undoReleaseCheck(ScmInvSalePrice scmInvSalePrice, Param param) throws AppException{
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pmid", scmInvSalePrice.getPmId());
		int flag = ((ScmInvSalePriceDAO) dao).undoReleaseCheck(map);
		if (flag>0) {
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.ScmInvSalePriceBizImpl.undoReleaseCheck",new String[] {scmInvSalePrice.getPmNo()});
		}
	}

	@Override
	public ScmInvSalePrice finish(ScmInvSalePrice scmInvSalePrice, Param param) throws AppException {
		ScmInvSalePrice purBill = null;
		if(scmInvSalePrice.getPmId()>0){
			purBill = this.selectDirect(scmInvSalePrice.getPmId(),param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmInvSalePrice.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmInvSalePrice.FN_PMNO,new QueryParam(ScmInvSalePrice.FN_PMNO, QueryParam.QUERY_EQ,scmInvSalePrice.getPmNo()));
			
			List<ScmInvSalePrice> scmBillList =this.findPage(page, param);
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
	public ScmInvSalePrice undoFinish(ScmInvSalePrice scmInvSalePrice, Param param) throws AppException {
		ScmInvSalePrice purBill = null;
		if(scmInvSalePrice.getPmId()>0){
			purBill = this.selectDirect(scmInvSalePrice.getPmId(),param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmInvSalePrice.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmInvSalePrice.FN_PMNO,new QueryParam(ScmInvSalePrice.FN_PMNO, QueryParam.QUERY_EQ,scmInvSalePrice.getPmNo()));
			
			List<ScmInvSalePrice> scmBillList =this.findPage(page, param);
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
	public ScmInvSalePrice updatePrtCount(ScmInvSalePrice scmInvSalePrice, Param param) throws AppException {
		if(scmInvSalePrice.getPmId()>0){
			ScmInvSalePrice bill = this.selectDirect(scmInvSalePrice.getPmId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmInvSalePrice;
	}
}

