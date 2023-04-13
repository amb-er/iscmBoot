package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.impl;


import java.util.ArrayList;
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
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.ScmInvOtherInWarehsBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBill;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsDetailAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvOtherInWarehsBillEntryBiz;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgStorageBiz;
import org.springframework.stereotype.Service;

@Service("scmInvOtherInWarehsBillEntryBiz")
public class ScmInvOtherInWarehsBillEntryBizImpl extends BaseBizImpl<ScmInvOtherInWarehsBillEntry2> implements ScmInvOtherInWarehsBillEntryBiz {
	private OrgStorageBiz orgStorageBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	
	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}

	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}

	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}
	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
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
	protected void afterSelect(ScmInvOtherInWarehsBillEntry2 entity, Param param) throws AppException {
		setConvertMap(entity,param);
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		if(list!=null && !list.isEmpty()){
    		for(ScmInvOtherInWarehsBillEntry2 scmInvOtherInWarehsBillEntry:(List<ScmInvOtherInWarehsBillEntry2>)list){
				setConvertMap(scmInvOtherInWarehsBillEntry,param);
			}
    	}
	}

	@Override
	public List<ScmInvOtherInWarehsBillEntry2> selectByWrId(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("wrId", wrId);
			List<ScmInvOtherInWarehsBillEntry2> scmInvOtherInWarehsBillEntry2s = ((ScmInvOtherInWarehsBillEntryDAO) dao).selectByWrId(map);
			if (scmInvOtherInWarehsBillEntry2s != null && ! scmInvOtherInWarehsBillEntry2s.isEmpty()) {
				StringBuffer itemids = new StringBuffer("");
				List<ScmMaterial2> invScmMaterial2s=new ArrayList<>();
				for (ScmInvOtherInWarehsBillEntry2 scmInvOtherInWarehsBillEntry2 : scmInvOtherInWarehsBillEntry2s) {
					if (StringUtils.isNotBlank(itemids.toString())) {
						itemids.append(",");
					}
					itemids.append(scmInvOtherInWarehsBillEntry2.getItemId());
				}
				if (StringUtils.isNotBlank(itemids.toString())) {
					invScmMaterial2s = scmMaterialBiz.findByInvItemIds(param.getControlUnitNo(), param.getOrgUnitNo(), itemids.toString(), param);
				}
				for (ScmInvOtherInWarehsBillEntry2 scmInvOtherInWarehsBillEntry2 : scmInvOtherInWarehsBillEntry2s) {
					if (invScmMaterial2s != null && ! invScmMaterial2s.isEmpty()) {
						for (ScmMaterial2 scmMaterial2 : invScmMaterial2s) {
							if (scmMaterial2.getId()==scmInvOtherInWarehsBillEntry2.getItemId()) {
								scmInvOtherInWarehsBillEntry2.setPeriodValid(scmMaterial2.getPeriodValid());
								scmInvOtherInWarehsBillEntry2.setPeriodValidDays(scmMaterial2.getPeriodValidDays());
							}
						}
					}
				}
			}
			return scmInvOtherInWarehsBillEntry2s;
		}
		return null;
	}

	@Override
	public void deleteByWrId(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("wrId", wrId);
			((ScmInvOtherInWarehsBillEntryDAO) dao).deleteByWrId(map);
		}
	}
	
	private void setConvertMap(ScmInvOtherInWarehsBillEntry2 scmInvOtherInWarehsBillEntry, Param param){
		if(scmInvOtherInWarehsBillEntry != null){
			if (scmInvOtherInWarehsBillEntry.getTaxRate() != null){
				//税率
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmInvOtherInWarehsBillEntry.getTaxRate().toString(), null, param);
				if (pubSysBasicInfo != null) {
					scmInvOtherInWarehsBillEntry.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
					scmInvOtherInWarehsBillEntry.setConvertMap(ScmInvOtherInWarehsBillEntry2.FN_TAXRATESTR, pubSysBasicInfo);
				}
			}
			if (scmInvOtherInWarehsBillEntry.getWareHouseId() > 0){
				//仓库
				ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvOtherInWarehsBillEntry.getWareHouseId(), param);
				if (scmInvWareHouse != null) {
					scmInvOtherInWarehsBillEntry.setConvertMap(ScmInvOtherInWarehsBillEntry2.FN_WAREHOUSEID, scmInvWareHouse);
				}
			}
			if (StringUtils.isNotBlank(scmInvOtherInWarehsBillEntry.getOrgUnitNo())){
				//库存组织
				OrgStorage2 orgStorage = orgStorageBiz.selectByOrgUnitNo(scmInvOtherInWarehsBillEntry.getOrgUnitNo(), param);
				if (orgStorage != null) {
					scmInvOtherInWarehsBillEntry.setConvertMap(ScmInvOtherInWarehsBillEntry2.FN_ORGUNITNO, orgStorage);
				}
			}
			if (StringUtils.isNotBlank(scmInvOtherInWarehsBillEntry.getLot())){
				ScmInvStock2 scmInvStock = new ScmInvStock2(true);
				scmInvStock.setLot(scmInvOtherInWarehsBillEntry.getLot());
				scmInvOtherInWarehsBillEntry.setConvertMap(ScmInvOtherInWarehsBillEntry2.FN_LOT, scmInvStock);
			}
		}
	}

	@Override
	public List<ScmInvOtherInWarehsBillEntry2> selectInvQty(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("wrId", wrId);
			return ((ScmInvOtherInWarehsBillEntryDAO) dao).selectInvQty(map);
		}
		return null;
	}

	@Override
	public List<ScmInvOtherInWarehsBillEntry2> selectOutEffectRow(long wrId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("wrId", wrId);
		return ((ScmInvOtherInWarehsBillEntryDAO) dao).selectOutEffectRow(map);
	}

	@Override
	public List<ScmInvOtherInWarehsBillEntry2> checkStock(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("wrId", wrId);
			return ((ScmInvOtherInWarehsBillEntryDAO) dao).checkStock(map);
		}
		return null;
	}

	@Override
	public List<ScmInvOtherInWarehsBillEntry2> selectReturnEffectRow(long wrId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("wrId", wrId);
		return ((ScmInvOtherInWarehsBillEntryDAO) dao).selectReturnEffectRow(map);
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvOtherInWarehsDetailAdvQuery) {
				ScmInvOtherInWarehsDetailAdvQuery scmInvOtherInWarehsDetailAdvQuery = (ScmInvOtherInWarehsDetailAdvQuery) page.getModel();
				if(scmInvOtherInWarehsDetailAdvQuery.getWareHouseId()>0) {
					page.getParam().put(ScmInvOtherInWarehsBill2.FN_WAREHOUSEID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." +ScmInvOtherInWarehsBill2.FN_WAREHOUSEID, QueryParam.QUERY_EQ,String.valueOf(scmInvOtherInWarehsDetailAdvQuery.getWareHouseId())));
				}
				if(scmInvOtherInWarehsDetailAdvQuery.getBizDateFrom()!=null){
					if(scmInvOtherInWarehsDetailAdvQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmInvOtherInWarehsBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." +ScmInvOtherInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvOtherInWarehsDetailAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(scmInvOtherInWarehsDetailAdvQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmInvOtherInWarehsBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." +ScmInvOtherInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvOtherInWarehsDetailAdvQuery.getBizDateFrom())));
					}
				}else if(scmInvOtherInWarehsDetailAdvQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmInvOtherInWarehsBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." +ScmInvOtherInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmInvOtherInWarehsDetailAdvQuery.getBizDateTo())));
				}
				if(scmInvOtherInWarehsDetailAdvQuery.getCreateDateFrom()!=null){
					if(scmInvOtherInWarehsDetailAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmInvOtherInWarehsBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." +ScmInvOtherInWarehsBill2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvOtherInWarehsDetailAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvOtherInWarehsDetailAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmInvOtherInWarehsBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." +ScmInvOtherInWarehsBill2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvOtherInWarehsDetailAdvQuery.getCreateDateFrom())));
					}
				}else if(scmInvOtherInWarehsDetailAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmInvOtherInWarehsBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." +ScmInvOtherInWarehsBill2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvOtherInWarehsDetailAdvQuery.getCreateDateTo(),1))));
				}
				if(scmInvOtherInWarehsDetailAdvQuery.getVendorId()>0) {
					page.getParam().put(ScmInvOtherInWarehsBill2.FN_VENDORID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "." +ScmInvOtherInWarehsBill2.FN_VENDORID, QueryParam.QUERY_EQ,String.valueOf(scmInvOtherInWarehsDetailAdvQuery.getVendorId())));
				}
				if (StringUtils.isNotBlank(scmInvOtherInWarehsDetailAdvQuery.getItemId())) {
					page.getParam().put(ScmInvOtherInWarehsBillEntry2.FN_ITEMID, new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBillEntry2.class) + "." +ScmInvOtherInWarehsBillEntry2.FN_ITEMID, QueryParam.QUERY_IN,scmInvOtherInWarehsDetailAdvQuery.getItemId()));
				}
				if(StringUtils.isNotBlank(scmInvOtherInWarehsDetailAdvQuery.getClassId())) {
					StringBuffer classIds = new StringBuffer("");
					String[] split = scmInvOtherInWarehsDetailAdvQuery.getClassId().split(",");
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
}

