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
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmBrandInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMoveIssueBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueDetailAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMoveIssueBillEntryBiz;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import org.springframework.stereotype.Service;

@Service("scmInvMoveIssueBillEntryBiz")
public class ScmInvMoveIssueBillEntryBizImpl  extends BaseBizImpl<ScmInvMoveIssueBillEntry2> implements ScmInvMoveIssueBillEntryBiz{

    private PubSysBasicInfoBiz pubSysBasicInfoBiz;
    private ScmInvWareHouseBiz scmInvWareHouseBiz;
    private OrgUnitBiz orgUnitBiz;
    private OrgStorageBiz orgStorageBiz;
    private ScmMaterialGroupBiz scmMaterialGroupBiz;
	private ScmBrandInfoBiz scmBrandInfoBiz;
    
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

	public void setScmBrandInfoBiz(ScmBrandInfoBiz scmBrandInfoBiz) {
		this.scmBrandInfoBiz = scmBrandInfoBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." + ScmInvMoveIssueBill2.FN_ORGUNITNO), 
	                new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." + ScmInvMoveIssueBill2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." + ScmInvMoveIssueBill2.FN_ORGUNITNO), 
	                new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." + ScmInvMoveIssueBill2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}
	
	
	@Override
    protected void afterSelect(ScmInvMoveIssueBillEntry2 entity, Param param) throws AppException {
        if (entity != null) {
        	setConvertMap(entity, param);
        }
    }
	
	@Override
    protected void afterFindPage(List list, Page page, Param param) throws AppException {
	    if (list != null && !list.isEmpty()) {
            for (ScmInvMoveIssueBillEntry2 scmInvMoveIssueBillEntry : (List<ScmInvMoveIssueBillEntry2>)list) {
                setConvertMap(scmInvMoveIssueBillEntry, param);
            }
        }
    }

    @Override
	public List<ScmInvMoveIssueBillEntry2> selectByOtId(long otId, Param param) throws AppException {
		if(otId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("otId", otId);
			List<ScmInvMoveIssueBillEntry2> scmInvMoveIssueBillEntryList = ((ScmInvMoveIssueBillEntryDAO) dao).selectByOtId(map);
			if(scmInvMoveIssueBillEntryList!=null && !scmInvMoveIssueBillEntryList.isEmpty()){
				for(ScmInvMoveIssueBillEntry2 scmInvMoveIssueBillEntry:scmInvMoveIssueBillEntryList){
					setConvertMap(scmInvMoveIssueBillEntry, param);
				}
			}
			return scmInvMoveIssueBillEntryList;
		}
		return null;
	}

	@Override
	public void deleteByOtId(long otId, Param param) throws AppException {
		if(otId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("otId", otId);
			((ScmInvMoveIssueBillEntryDAO) dao).deleteByOtId(map);
		}
	}


    @Override
    public void deleteById(long id, Param param) throws AppException {
        if(id > 0){
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            ((ScmInvMoveIssueBillEntryDAO) dao).deleteById(map);
        }
    }


    @Override
    public List<ScmInvMoveIssueBillEntry2> selectInvQty(long otId, Param param) throws AppException {
        if(otId > 0){
            HashMap<String, Object> map = new HashMap<>();
            map.put("otId", otId);
            return ((ScmInvMoveIssueBillEntryDAO) dao).selectInvQty(map);
        }
        return null;
    }
    
    private void setConvertMap(ScmInvMoveIssueBillEntry2 scmInvMoveIssueBillEntry, Param param){
    	if (scmInvMoveIssueBillEntry.getTaxRate() != null) {
            //税率
            PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmInvMoveIssueBillEntry.getTaxRate().toString(), null, param);
            if (pubSysBasicInfo != null) {
            	scmInvMoveIssueBillEntry.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
            	scmInvMoveIssueBillEntry.setConvertMap(ScmInvMoveIssueBillEntry2.FN_TAXRATESTR, pubSysBasicInfo);
            }
        }
        if (scmInvMoveIssueBillEntry.getWareHouseId() > 0) {
            //仓库
            ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvMoveIssueBillEntry.getWareHouseId(), param);
            if (scmInvWareHouse != null) {
            	scmInvMoveIssueBillEntry.setConvertMap(ScmInvMoveIssueBillEntry2.FN_WAREHOUSEID, scmInvWareHouse);
            }
        }
        if (scmInvMoveIssueBillEntry.getReceiptWareHouseId() > 0) {
            //调入仓库
            ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvMoveIssueBillEntry.getReceiptWareHouseId(), param);
            if (scmInvWareHouse != null) {
            	scmInvMoveIssueBillEntry.setConvertMap(ScmInvMoveIssueBillEntry2.FN_RECEIPTWAREHOUSEID, scmInvWareHouse);
            }
        }
        if (StringUtils.isNotBlank(scmInvMoveIssueBillEntry.getUseOrgUnitNo())) {
            //调入部门
			OrgBaseUnit OrgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveIssueBillEntry.getUseOrgUnitNo(), param);
            if (OrgBaseUnit != null) {
            	scmInvMoveIssueBillEntry.setConvertMap(ScmInvMoveIssueBillEntry2.FN_USEORGUNITNO, OrgBaseUnit);
            }
        }
        if (StringUtils.isNotBlank(scmInvMoveIssueBillEntry.getOrgUnitNo())) {
            //库存组织
            OrgBaseUnit OrgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveIssueBillEntry.getOrgUnitNo(), param);
            if (OrgBaseUnit != null) {
                scmInvMoveIssueBillEntry.setConvertMap(ScmInvMoveIssueBillEntry2.FN_ORGUNITNO, OrgBaseUnit);
            }
        }
        if (StringUtils.isNotBlank(scmInvMoveIssueBillEntry.getLot())) {
            //批次
        	ScmInvStock2 scmInvStock = new ScmInvStock2(true);
			scmInvStock.setLot(scmInvMoveIssueBillEntry.getLot());
			scmInvMoveIssueBillEntry.setConvertMap(ScmInvMoveIssueBillEntry2.FN_LOT, scmInvStock);
        }
      //品牌
        if (scmInvMoveIssueBillEntry.getBrandId()>0) {
			ScmBrandInfo scmBrandInfo = scmBrandInfoBiz.selectDirect(scmInvMoveIssueBillEntry.getBrandId(), param);
			if (scmBrandInfo != null) {
				scmInvMoveIssueBillEntry.setBrandName(scmBrandInfo.getName());
			}
		}
    }

	@Override
	public List<ScmInvMoveIssueBillEntry2> selectOutEffectRow(long otId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("otId",otId);
		return ((ScmInvMoveIssueBillEntryDAO)dao).selectOutEffectRow(map);
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvMoveIssueDetailAdvQuery) {
				ScmInvMoveIssueDetailAdvQuery scmInvMoveIssueDetailAdvQuery = (ScmInvMoveIssueDetailAdvQuery) page.getModel();
				if(StringUtils.isNotBlank(scmInvMoveIssueDetailAdvQuery.getWareHouseId())) {
					page.getParam().put(ScmInvMoveIssueBill2.FN_WAREHOUSEID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." +ScmInvMoveIssueBill2.FN_WAREHOUSEID, QueryParam.QUERY_IN,String.valueOf(scmInvMoveIssueDetailAdvQuery.getWareHouseId())));
				}
				if(scmInvMoveIssueDetailAdvQuery.getBizDateFrom()!=null){
					if(scmInvMoveIssueDetailAdvQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmInvMoveIssueBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." +ScmInvMoveIssueBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvMoveIssueDetailAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(scmInvMoveIssueDetailAdvQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmInvMoveIssueBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." +ScmInvMoveIssueBill2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvMoveIssueDetailAdvQuery.getBizDateFrom())));
					}
				}else if(scmInvMoveIssueDetailAdvQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmInvMoveIssueBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." +ScmInvMoveIssueBill2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmInvMoveIssueDetailAdvQuery.getBizDateTo())));
				}
				if(scmInvMoveIssueDetailAdvQuery.getCreateDateFrom()!=null){
					if(scmInvMoveIssueDetailAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmInvMoveIssueBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." +ScmInvMoveIssueBill2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvMoveIssueDetailAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvMoveIssueDetailAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmInvMoveIssueBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." +ScmInvMoveIssueBill2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvMoveIssueDetailAdvQuery.getCreateDateFrom())));
					}
				}else if(scmInvMoveIssueDetailAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmInvMoveIssueBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." +ScmInvMoveIssueBill2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvMoveIssueDetailAdvQuery.getCreateDateTo(),1))));
				}
				if (StringUtils.isNotBlank(scmInvMoveIssueDetailAdvQuery.getBrandId())) {
					page.getParam().put(ScmMaterial2.FN_BRANDID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterial2.class) + "." +ScmMaterial2.FN_BRANDID, QueryParam.QUERY_IN,scmInvMoveIssueDetailAdvQuery.getBrandId()));
				}
				if (StringUtils.isNotBlank(scmInvMoveIssueDetailAdvQuery.getItemId())) {
					page.getParam().put(ScmInvMoveIssueBillEntry2.FN_ITEMID, new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBillEntry2.class) + "." +ScmInvMoveIssueBillEntry2.FN_ITEMID, QueryParam.QUERY_IN,scmInvMoveIssueDetailAdvQuery.getItemId()));
				}
				if(StringUtils.isNotBlank(scmInvMoveIssueDetailAdvQuery.getClassId())) {
					StringBuffer classIds = new StringBuffer("");
					String[] split = scmInvMoveIssueDetailAdvQuery.getClassId().split(",");
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
