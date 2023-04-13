package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.ScmBrandInfo;
import com.armitage.server.iscm.basedata.model.ScmCostUseSet2;
import com.armitage.server.iscm.basedata.model.ScmCostUseType;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmBrandInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmCostUseTypeBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMaterialReqBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqDetailAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialReqBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialRequestBillEntryBiz;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import org.springframework.stereotype.Service;

@Service("scmInvMaterialReqBillEntryBiz")
public class ScmInvMaterialReqBillEntryBizImpl extends BaseBizImpl<ScmInvMaterialReqBillEntry2> implements ScmInvMaterialReqBillEntryBiz {
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private ScmInvMaterialRequestBillEntryBiz scmInvMaterialRequestBillEntryBiz;
	private OrgAdminBiz orgAdminBiz;
	private OrgStorageBiz orgStorageBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private ScmCostUseTypeBiz scmCostUseTypeBiz;
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	private ScmBrandInfoBiz scmBrandInfoBiz;

	public ScmCostUseTypeBiz getScmCostUseTypeBiz() {
		return scmCostUseTypeBiz;
	}

	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}

	public void setScmCostUseTypeBiz(ScmCostUseTypeBiz scmCostUseTypeBiz) {
		this.scmCostUseTypeBiz = scmCostUseTypeBiz;
	}

	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}

	public void setScmInvMaterialRequestBillEntryBiz(ScmInvMaterialRequestBillEntryBiz scmInvMaterialRequestBillEntryBiz) {
		this.scmInvMaterialRequestBillEntryBiz = scmInvMaterialRequestBillEntryBiz;
	}
	
	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}
	
	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}

	public void setScmBrandInfoBiz(ScmBrandInfoBiz scmBrandInfoBiz) {
		this.scmBrandInfoBiz = scmBrandInfoBiz;
	}

	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
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
	protected void afterFindPage(List list, Page page, Param param)	throws AppException {
		if(list != null && !list.isEmpty()){
			for(ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : (List<ScmInvMaterialReqBillEntry2>)list){
				this.setConvertMap(scmInvMaterialReqBillEntry, param);
			}
		}
	}

	@Override
	protected void afterSelect(ScmInvMaterialReqBillEntry2 entity, Param param) throws AppException {
		if(entity != null){
			setConvertMap(entity,param);
		}
	}

	@Override
	public List<ScmInvMaterialReqBillEntry2> selectByOtId(long otId, Param param) throws AppException {
		if(otId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("otId", otId);
			List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = ((ScmInvMaterialReqBillEntryDAO) dao).selectByOtId(map);
			if(scmInvMaterialReqBillEntryList!=null && !scmInvMaterialReqBillEntryList.isEmpty()){
				for(ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry:scmInvMaterialReqBillEntryList){
					setConvertMap(scmInvMaterialReqBillEntry,param);
				}
			}
			return scmInvMaterialReqBillEntryList;
		}
		return null;
	}

	@Override
	public List<ScmInvMaterialReqBillEntry2> checkStockByOutWareHouse(long otId, Param param) throws AppException {
		if(otId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("otId", otId);
			return ((ScmInvMaterialReqBillEntryDAO) dao).checkStockByOutWareHouse(map);
		}
		return null;
	}

	@Override
	public List<ScmInvMaterialReqBillEntry2> checkStockByReturnWareHouse(long otId, Param param) throws AppException {
		if(otId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("otId", otId);
			return ((ScmInvMaterialReqBillEntryDAO) dao).checkStockByReturnWareHouse(map);
		}
		return null;
	}
	
	private void setConvertMap(ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry, Param param){
		if(scmInvMaterialReqBillEntry!=null){
			if (scmInvMaterialReqBillEntry.getTaxRate() != null) {
	            //税率
	            PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmInvMaterialReqBillEntry.getTaxRate().toString(), null, param);
	            if (pubSysBasicInfo != null) {
	            	scmInvMaterialReqBillEntry.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
	            	scmInvMaterialReqBillEntry.setConvertMap(ScmInvMaterialReqBillEntry2.FN_TAXRATESTR, pubSysBasicInfo);
	            }
	        }
			if (StringUtils.isNotBlank(scmInvMaterialReqBillEntry.getLot())) {
				ScmInvStock2 scmInvStock = new ScmInvStock2(true);
				scmInvStock.setLot(scmInvMaterialReqBillEntry.getLot());
                //批次
			    scmInvMaterialReqBillEntry.setConvertMap(ScmInvSaleIssueBillEntry2.FN_LOT, scmInvStock);
            }
			if (scmInvMaterialReqBillEntry.getWareHouseId() > 0){
				//仓库
				ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvMaterialReqBillEntry.getWareHouseId(), param);
				if (scmInvWareHouse != null) {
					scmInvMaterialReqBillEntry.setConvertMap(ScmInvMaterialReqBillEntry2.FN_WAREHOUSEID, scmInvWareHouse);
				}
			}
			if (StringUtils.isNotBlank(scmInvMaterialReqBillEntry.getOrgUnitNo())){
				//库存组织
				OrgStorage2 orgStorage = orgStorageBiz.selectByOrgUnitNo(scmInvMaterialReqBillEntry.getOrgUnitNo(), param);
				if (orgStorage != null) {
					scmInvMaterialReqBillEntry.setConvertMap(ScmInvMaterialReqBillEntry2.FN_ORGUNITNO, orgStorage);
				}
			}
			if (scmInvMaterialReqBillEntry.getCostUseTypeId() > 0 ) {
				ScmCostUseType scmCostUseType = scmCostUseTypeBiz.select(scmInvMaterialReqBillEntry.getCostUseTypeId(), param);
				if (scmCostUseType != null) {
					ScmCostUseSet2 costUseSet = new ScmCostUseSet2();
					costUseSet.setCostUseTypeId(scmCostUseType.getId());
					costUseSet.setScmCostUseTypeName(scmCostUseType.getName());
					scmInvMaterialReqBillEntry.setConvertMap(ScmInvMaterialReqBillEntry2.FN_COSTUSETYPEID, costUseSet);
				}
            }else {
            	scmInvMaterialReqBillEntry.setConvertMap(ScmInvMaterialReqBillEntry2.FN_COSTUSETYPEID, new ScmCostUseSet2());
            }
			//品牌
	        if (scmInvMaterialReqBillEntry.getBrandId()>0) {
				ScmBrandInfo scmBrandInfo = scmBrandInfoBiz.selectDirect(scmInvMaterialReqBillEntry.getBrandId(), param);
				if (scmBrandInfo != null) {
					scmInvMaterialReqBillEntry.setBrandName(scmBrandInfo.getName());
				}
			}
			HashMap<String,Object> cacheMap = new HashMap<String,Object>();
			if (StringUtils.isNotBlank(scmInvMaterialReqBillEntry.getUseOrgUnitNo())){
				//领料部门
				OrgAdmin2 orgAdmin = (OrgAdmin2) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgAdmin2.class)+"_"+scmInvMaterialReqBillEntry.getUseOrgUnitNo());
				if(orgAdmin==null){
					orgAdmin = orgAdminBiz.selectByOrgUnitNo(scmInvMaterialReqBillEntry.getUseOrgUnitNo(), param);
					cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgAdmin2.class)+"_"+scmInvMaterialReqBillEntry.getUseOrgUnitNo(),orgAdmin);
				}
				if (orgAdmin != null) {
					scmInvMaterialReqBillEntry.setConvertMap(ScmInvMaterialReqBillEntry2.FN_USEORGUNITNO, orgAdmin);
					scmInvMaterialReqBillEntry.setUseOrgUnitName(orgAdmin.getOrgUnitName());
				}
			}
		}
	}

	@Override
	protected void afterAdd(ScmInvMaterialReqBillEntry2 entity, Param param) throws AppException {
		scmInvMaterialRequestBillEntryBiz.writeBackByReqOut(null, entity, param);
	}

	@Override
	protected void afterDelete(ScmInvMaterialReqBillEntry2 entity, Param param) throws AppException {
		scmInvMaterialRequestBillEntryBiz.writeBackByReqOut(entity, null, param);
	}

	@Override
	protected void afterUpdate(ScmInvMaterialReqBillEntry2 oldEntity, ScmInvMaterialReqBillEntry2 newEntity,
			Param param) throws AppException {
		scmInvMaterialRequestBillEntryBiz.writeBackByReqOut(oldEntity, newEntity, param);
	}

	@Override
	public List<ScmInvMaterialReqBillEntry2> selectOutEffectRow(long otId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("otId",otId);
		return ((ScmInvMaterialReqBillEntryDAO)dao).selectOutEffectRow(map);
	}

	@Override
	public List<ScmInvMaterialReqBillEntry2> selectReturnEffectRow(long otId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("otId",otId);
		return ((ScmInvMaterialReqBillEntryDAO)dao).selectReturnEffectRow(map);
	}

	@Override
	public List<ScmInvMaterialReqBillEntry2> selectCancelOutEffectDeptRow(long otId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("otId",otId);
		return ((ScmInvMaterialReqBillEntryDAO)dao).selectCancelOutEffectDeptRow(map);
	}

	@Override
	public List<ScmInvMaterialReqBillEntry2> selectCancelOutEffectWareHsRow(long otId, Param param)
			throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("otId",otId);
		return ((ScmInvMaterialReqBillEntryDAO)dao).selectCancelOutEffectWareHsRow(map);
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvMaterialReqDetailAdvQuery) {
				ScmInvMaterialReqDetailAdvQuery scmInvMaterialReqDetailAdvQuery = (ScmInvMaterialReqDetailAdvQuery) page.getModel();
				if(StringUtils.isNotBlank(scmInvMaterialReqDetailAdvQuery.getWareHouseId())) {
					page.getParam().put(ScmInvMaterialReqBill2.FN_WAREHOUSEID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_WAREHOUSEID, QueryParam.QUERY_IN,String.valueOf(scmInvMaterialReqDetailAdvQuery.getWareHouseId())));
				}
				if(StringUtils.isNotBlank(scmInvMaterialReqDetailAdvQuery.getUseOrgUnitNo())) {
					page.getParam().put(ScmInvMaterialReqBill2.FN_USEORGUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_USEORGUNITNO, QueryParam.QUERY_EQ,scmInvMaterialReqDetailAdvQuery.getUseOrgUnitNo()));
				}
				if(scmInvMaterialReqDetailAdvQuery.getBizDateFrom()!=null){
					if(scmInvMaterialReqDetailAdvQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmInvMaterialReqBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvMaterialReqDetailAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(scmInvMaterialReqDetailAdvQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmInvMaterialReqBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvMaterialReqDetailAdvQuery.getBizDateFrom())));
					}
				}else if(scmInvMaterialReqDetailAdvQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmInvMaterialReqBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmInvMaterialReqDetailAdvQuery.getBizDateTo())));
				}
				if(scmInvMaterialReqDetailAdvQuery.getCreateDateFrom()!=null){
					if(scmInvMaterialReqDetailAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmInvMaterialReqBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvMaterialReqDetailAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvMaterialReqDetailAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmInvMaterialReqBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvMaterialReqDetailAdvQuery.getCreateDateFrom())));
					}
				}else if(scmInvMaterialReqDetailAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmInvMaterialReqBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "." +ScmInvMaterialReqBill2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvMaterialReqDetailAdvQuery.getCreateDateTo(),1))));
				}
				if (StringUtils.isNotBlank(scmInvMaterialReqDetailAdvQuery.getBrandId())) {
					page.getParam().put(ScmMaterial2.FN_BRANDID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterial2.class) + "." +ScmMaterial2.FN_BRANDID, QueryParam.QUERY_IN,scmInvMaterialReqDetailAdvQuery.getBrandId()));
				}
				if (StringUtils.isNotBlank(scmInvMaterialReqDetailAdvQuery.getItemId())) {
					page.getParam().put(ScmInvMaterialReqBillEntry2.FN_ITEMID, new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBillEntry2.class) + "." +ScmInvMaterialReqBillEntry2.FN_ITEMID, QueryParam.QUERY_IN,scmInvMaterialReqDetailAdvQuery.getItemId()));
				}
				if(StringUtils.isNotBlank(scmInvMaterialReqDetailAdvQuery.getClassId())) {
					StringBuffer classIds = new StringBuffer("");
					String[] split = scmInvMaterialReqDetailAdvQuery.getClassId().split(",");
					for (String string : split) {
						if(StringUtils.isNotBlank(classIds.toString()))
							classIds.append(",");
						classIds.append(string);
						List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(Long.valueOf(string), param);
						if(scmMaterialGroupList!=null && !scmMaterialGroupList.isEmpty()) {
							for(ScmMaterialGroup scmMaterialGroup:scmMaterialGroupList) {
								if(StringUtils.isNotBlank(classIds.toString()))
									classIds.append(",");
								classIds.append(scmMaterialGroup.getId());
							}
						}
					}
					if(StringUtils.isNotBlank(classIds.toString()))
						page.getParam().put(ScmMaterialGroup.FN_ID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." +ScmMaterialGroup.FN_ID, QueryParam.QUERY_IN,classIds.toString()));
				}
			}
		}
	}

	@Override
	public void deleteByOtId(long otId, Param param) throws AppException {
		if(otId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("otId", otId);
			List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = ((ScmInvMaterialReqBillEntryDAO) dao).deleteByOtId(map);
		}
	}
}


