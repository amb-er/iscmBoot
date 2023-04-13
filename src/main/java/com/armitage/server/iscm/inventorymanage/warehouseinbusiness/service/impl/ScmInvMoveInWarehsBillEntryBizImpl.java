package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.impl;


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
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.ScmInvMoveInWarehsBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBillAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBillEntryAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvMoveInWarehsBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBillEntry2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import org.springframework.stereotype.Service;

@Service("scmInvMoveInWarehsBillEntryBiz")
public class ScmInvMoveInWarehsBillEntryBizImpl extends BaseBizImpl<ScmInvMoveInWarehsBillEntry2> implements ScmInvMoveInWarehsBillEntryBiz {
    
    private PubSysBasicInfoBiz pubSysBasicInfoBiz;
    private ScmInvWareHouseBiz scmInvWareHouseBiz;
    private OrgUnitBiz orgUnitBiz;
    private OrgStorageBiz orgStorageBiz;
    private ScmMaterialGroupBiz scmMaterialGroupBiz;
    
	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
        this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
    }

    public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
        this.scmInvWareHouseBiz = scmInvWareHouseBiz;
    }
    
    public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
        this.orgUnitBiz = orgUnitBiz;
    }
    
    public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
        this.orgStorageBiz = orgStorageBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." + ScmInvMoveInWarehsBill2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." + ScmInvMoveInWarehsBill2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." + ScmInvMoveInWarehsBill2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." + ScmInvMoveInWarehsBill2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}

	
	@Override
    protected void afterSelect(ScmInvMoveInWarehsBillEntry2 entity, Param param) throws AppException {
        if (entity != null) {
        	setConvertMap(entity, param);
        }
    }
	
    @Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
    	if(list!=null && !list.isEmpty()){
    		for(ScmInvMoveInWarehsBillEntry2 scmInvMoveInWarehsBillEntry:(List<ScmInvMoveInWarehsBillEntry2>)list){
				setConvertMap(scmInvMoveInWarehsBillEntry,param);
			}
    	}
	}

	@Override
    public void deleteByWrId(long wrId, Param param) throws AppException {
        if(wrId > 0){
            HashMap<String, Object> map = new HashMap<>();
            map.put("wrId", wrId);
            ((ScmInvMoveInWarehsBillEntryDAO) dao).deleteByWrId(map);
        }
    }


    @Override
    public List<ScmInvMoveInWarehsBillEntry2> selectByWrId(long wrId, Param param) throws AppException {
        if(wrId > 0){
            HashMap<String, Object> map = new HashMap<>();
            map.put("wrId", wrId);
            List<ScmInvMoveInWarehsBillEntry2> scmInvMoveInWarehsBillEntryList = ((ScmInvMoveInWarehsBillEntryDAO) dao).selectByWrId(map);
            if(scmInvMoveInWarehsBillEntryList!=null && !scmInvMoveInWarehsBillEntryList.isEmpty()){
				for(ScmInvMoveInWarehsBillEntry2 scmInvMoveInWarehsBillEntry:scmInvMoveInWarehsBillEntryList){
					setConvertMap(scmInvMoveInWarehsBillEntry, param);
				}
			}
            return scmInvMoveInWarehsBillEntryList;
        }
        return null;
    }
    
    private void setConvertMap(ScmInvMoveInWarehsBillEntry2 scmInvMoveInWarehsBillEntry, Param param){
    	if (scmInvMoveInWarehsBillEntry.getTaxRate() != null) {
            //税率
            PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmInvMoveInWarehsBillEntry.getTaxRate().toString(), null, param);
            if (pubSysBasicInfo != null) {
            	scmInvMoveInWarehsBillEntry.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
            	scmInvMoveInWarehsBillEntry.setConvertMap(ScmInvMoveInWarehsBillEntry2.FN_TAXRATESTR, pubSysBasicInfo);
            }
        }
        if (scmInvMoveInWarehsBillEntry.getWareHouseId() > 0) {
            //仓库
            ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvMoveInWarehsBillEntry.getWareHouseId(), param);
            if (scmInvWareHouse != null) {
            	scmInvMoveInWarehsBillEntry.setConvertMap(ScmInvMoveInWarehsBillEntry2.FN_WAREHOUSEID, scmInvWareHouse);
            }
        }
        if (StringUtils.isNotBlank(scmInvMoveInWarehsBillEntry.getUseOrgUnitNo())) {
            //调入部门
			OrgBaseUnit OrgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveInWarehsBillEntry.getUseOrgUnitNo(), param);
            if (OrgBaseUnit != null) {
            	scmInvMoveInWarehsBillEntry.setConvertMap(ScmInvMoveInWarehsBillEntry2.FN_USEORGUNITNO, OrgBaseUnit);
            }
        }
        if (StringUtils.isNotBlank(scmInvMoveInWarehsBillEntry.getOrgUnitNo())) {
            //库存组织
            OrgBaseUnit OrgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveInWarehsBillEntry.getOrgUnitNo(), param);
            if (OrgBaseUnit != null) {
            	scmInvMoveInWarehsBillEntry.setConvertMap(ScmInvMoveInWarehsBillEntry2.FN_ORGUNITNO, OrgBaseUnit);
            }
        }
    }

	@Override
	public List<ScmInvMoveInWarehsBillEntry2> selectInvQty(long wrId, Param param) throws AppException {
		if(wrId > 0){
            HashMap<String, Object> map = new HashMap<>();
            map.put("wrId", wrId);
            return ((ScmInvMoveInWarehsBillEntryDAO) dao).selectInvQty(map);
        }
        return null;
	}

	@Override
	public List<ScmInvMoveInWarehsBillEntry2> selectOutEffectRow(long wrId, Param param) throws AppException {
		 HashMap<String, Object> map = new HashMap<>();
         map.put("wrId", wrId);
         return ((ScmInvMoveInWarehsBillEntryDAO) dao).selectOutEffectRow(map);
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvMoveInWarehsBillEntryAdvQuery) {
				ScmInvMoveInWarehsBillEntryAdvQuery scmInvMoveInWarehsBillEntryAdvQuery = (ScmInvMoveInWarehsBillEntryAdvQuery) page.getModel();
				if(scmInvMoveInWarehsBillEntryAdvQuery.getWareHouseId()>0) {
					page.getParam().put(ScmInvMoveInWarehsBill2.FN_WAREHOUSEID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." +ScmInvMoveInWarehsBill2.FN_WAREHOUSEID, QueryParam.QUERY_EQ,String.valueOf(scmInvMoveInWarehsBillEntryAdvQuery.getWareHouseId())));
				}
				if(scmInvMoveInWarehsBillEntryAdvQuery.getBizDateFrom()!=null){
					if(scmInvMoveInWarehsBillEntryAdvQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmInvMoveInWarehsBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." +ScmInvMoveInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvMoveInWarehsBillEntryAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(scmInvMoveInWarehsBillEntryAdvQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmInvMoveInWarehsBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." +ScmInvMoveInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvMoveInWarehsBillEntryAdvQuery.getBizDateFrom())));
					}
				}else if(scmInvMoveInWarehsBillEntryAdvQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmInvMoveInWarehsBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." +ScmInvMoveInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmInvMoveInWarehsBillEntryAdvQuery.getBizDateTo())));
				}
				if(scmInvMoveInWarehsBillEntryAdvQuery.getCreateDateFrom()!=null){
					if(scmInvMoveInWarehsBillEntryAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmInvMoveInWarehsBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." +ScmInvMoveInWarehsBill2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvMoveInWarehsBillEntryAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvMoveInWarehsBillEntryAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmInvMoveInWarehsBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." +ScmInvMoveInWarehsBill2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvMoveInWarehsBillEntryAdvQuery.getCreateDateFrom())));
					}
				}else if(scmInvMoveInWarehsBillEntryAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmInvMoveInWarehsBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." +ScmInvMoveInWarehsBill2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvMoveInWarehsBillEntryAdvQuery.getCreateDateTo(),1))));
				}
				if (StringUtils.isNotBlank(scmInvMoveInWarehsBillEntryAdvQuery.getItemId())) {
					page.getParam().put(ScmInvMoveInWarehsBillEntry2.FN_ITEMID, new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBillEntry2.class) + "." +ScmInvMoveInWarehsBillEntry2.FN_ITEMID, QueryParam.QUERY_IN,scmInvMoveInWarehsBillEntryAdvQuery.getItemId()));
				}
				if(StringUtils.isNotBlank(scmInvMoveInWarehsBillEntryAdvQuery.getClassId())) {
					StringBuffer classIds = new StringBuffer("");
					String[] split = scmInvMoveInWarehsBillEntryAdvQuery.getClassId().split(",");
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

