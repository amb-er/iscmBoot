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
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvOtherIssueBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueDetailAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvOtherIssueBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvOtherIssueBillEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderEntryBiz;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgCompanyBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import org.springframework.stereotype.Service;

@Service("scmInvOtherIssueBillEntryBiz")
public class ScmInvOtherIssueBillEntryBizImpl  extends BaseBizImpl<ScmInvOtherIssueBillEntry2> implements ScmInvOtherIssueBillEntryBiz{

    private ScmInvWareHouseBiz scmInvWareHouseBiz;
    private OrgUnitBiz orgUnitBiz;
    private PubSysBasicInfoBiz pubSysBasicInfoBiz;
    private ScmPurOrderEntryBiz scmPurOrderEntryBiz;
    private OrgCompanyBiz orgCompanyBiz;
    private ScmInvOtherIssueBillBiz scmInvOtherIssueBillBiz;
    private OrgStorageBiz orgStorageBiz;
    private ScmMaterialGroupBiz scmMaterialGroupBiz;
	private ScmBrandInfoBiz scmBrandInfoBiz;
    
	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
        this.scmInvWareHouseBiz = scmInvWareHouseBiz;
    }

    public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
        this.orgUnitBiz = orgUnitBiz;
    }

    public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}

	public void setScmPurOrderEntryBiz(ScmPurOrderEntryBiz scmPurOrderEntryBiz) {
		this.scmPurOrderEntryBiz = scmPurOrderEntryBiz;
	}

	public void setOrgCompanyBiz(OrgCompanyBiz orgCompanyBiz) {
		this.orgCompanyBiz = orgCompanyBiz;
	}
	
	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}

	public void setScmInvOtherIssueBillBiz(ScmInvOtherIssueBillBiz scmInvOtherIssueBillBiz) {
		this.scmInvOtherIssueBillBiz = scmInvOtherIssueBillBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." + ScmInvOtherIssueBill2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." + ScmInvOtherIssueBill2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." + ScmInvOtherIssueBill2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." + ScmInvOtherIssueBill2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}

    
	@Override
    protected void afterSelect(ScmInvOtherIssueBillEntry2 entity, Param param) throws AppException {
	    if (entity != null) {
            setConvertMap(entity, param);
        }
    }

    @Override
    protected void afterFindPage(List list, Page page, Param param) throws AppException {
        if (list != null && !list.isEmpty()) {
            for (ScmInvOtherIssueBillEntry2 scmInvOtherIssueBillEntry : (List<ScmInvOtherIssueBillEntry2>)list) {
                setConvertMap(scmInvOtherIssueBillEntry, param);
            }
        }
    }

    private void setConvertMap(ScmInvOtherIssueBillEntry2 scmInvOtherIssueBillEntry, Param param) {
    	if (scmInvOtherIssueBillEntry.getTaxRate() != null){
			//税率
			PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmInvOtherIssueBillEntry.getTaxRate().toString(), null, param);
			if (pubSysBasicInfo != null) {
				scmInvOtherIssueBillEntry.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
				scmInvOtherIssueBillEntry.setConvertMap(ScmInvOtherInWarehsBillEntry2.FN_TAXRATESTR, pubSysBasicInfo);
			}
		}
        if (scmInvOtherIssueBillEntry.getWareHouseId() > 0) {
            //仓库
            ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvOtherIssueBillEntry.getWareHouseId(), param);
            if (scmInvWareHouse != null) {
                scmInvOtherIssueBillEntry.setConvertMap(ScmInvOtherIssueBillEntry2.FN_WAREHOUSEID, scmInvWareHouse);
            }
        }
        if (StringUtils.isNotBlank(scmInvOtherIssueBillEntry.getOrgUnitNo())) {
            //库存组织
            OrgBaseUnit OrgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvOtherIssueBillEntry.getOrgUnitNo(), param);
            if (OrgBaseUnit != null) {
                scmInvOtherIssueBillEntry.setConvertMap(ScmInvOtherIssueBillEntry2.FN_ORGUNITNO, OrgBaseUnit);
            }
        }
        if (StringUtils.isNotBlank(scmInvOtherIssueBillEntry.getReqFinOrgUnitNo())) {
            //需求财务组织
        	OrgCompany2 orgCompany = orgCompanyBiz.selectByOrgUnitNo(scmInvOtherIssueBillEntry.getReqFinOrgUnitNo(), param);
            if (orgCompany != null) {
                scmInvOtherIssueBillEntry.setConvertMap(ScmInvOtherIssueBillEntry2.FN_REQFINORGUNITNO, orgCompany);
            }
        }
        if (StringUtils.isNotBlank(scmInvOtherIssueBillEntry.getLot())) {
            //批次
        	ScmInvStock2 scmInvStock = new ScmInvStock2(true);
			scmInvStock.setLot(scmInvOtherIssueBillEntry.getLot());
			scmInvOtherIssueBillEntry.setConvertMap(ScmInvOtherIssueBillEntry2.FN_LOT, scmInvStock);
        }
        //品牌
        if (scmInvOtherIssueBillEntry.getBrandId()>0) {
			ScmBrandInfo scmBrandInfo = scmBrandInfoBiz.selectDirect(scmInvOtherIssueBillEntry.getBrandId(), param);
			if (scmBrandInfo != null) {
				scmInvOtherIssueBillEntry.setBrandName(scmBrandInfo.getName());
			}
		}
    }

    @Override
	public List<ScmInvOtherIssueBillEntry2> selectByOtId(long otId, Param param) throws AppException {
		if(otId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("otId", otId);
			List<ScmInvOtherIssueBillEntry2> scmInvOtherIssueBillEntryList = ((ScmInvOtherIssueBillEntryDAO) dao).selectByOtId(map);
			if (scmInvOtherIssueBillEntryList != null && !scmInvOtherIssueBillEntryList.isEmpty()) {
                for(ScmInvOtherIssueBillEntry2 scmInvOtherIssueBillEntry : scmInvOtherIssueBillEntryList) {
                    setConvertMap(scmInvOtherIssueBillEntry, param);
                }
            }
			return scmInvOtherIssueBillEntryList;
		}
		return null;
	}

	@Override
	public void deleteByOtId(long otId, Param param) throws AppException {
		if(otId > 0){
			List<ScmInvOtherIssueBillEntry2> scmInvOtherIssueBillEntryList = this.selectByOtId(otId, param);
			if(scmInvOtherIssueBillEntryList!=null && !scmInvOtherIssueBillEntryList.isEmpty())
				this.delete(scmInvOtherIssueBillEntryList, param);
		}
	}

	@Override
	public List<ScmInvOtherIssueBillEntry2> selectInvQty(long otId, Param param) throws AppException {
		if(otId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("otId", otId);
			return ((ScmInvOtherIssueBillEntryDAO) dao).selectInvQty(map);
		}
		return null;
	}

	@Override
	public List<ScmInvOtherIssueBillEntry2> selectMaxLineId(long otId, Param param) throws AppException {
		if(otId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("otId", otId);
			return ((ScmInvOtherIssueBillEntryDAO) dao).selectMaxLineId(map);
		}
		return null;
	}

	@Override
	public List<ScmInvOtherIssueBillEntry2> selectOutEffectRow(long otId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("otId", otId);
		return ((ScmInvOtherIssueBillEntryDAO) dao).selectOutEffectRow(map);
	}

	@Override
	protected void afterAdd(ScmInvOtherIssueBillEntry2 entity, Param param) throws AppException {
		ScmInvOtherIssueBill scmInvOtherIssueBill = scmInvOtherIssueBillBiz.selectDirect(entity.getOtId(), param);
		if(StringUtils.equals("7", scmInvOtherIssueBill.getBizType())){
			scmPurOrderEntryBiz.writeBackByOtherIssue(null, entity, param);
		}
	}

	@Override
	protected void afterDelete(ScmInvOtherIssueBillEntry2 entity, Param param) throws AppException {
		scmPurOrderEntryBiz.writeBackByOtherIssue(entity, null, param);
	}

	@Override
	protected void afterUpdate(ScmInvOtherIssueBillEntry2 oldEntity, ScmInvOtherIssueBillEntry2 newEntity, Param param)
			throws AppException {
		scmPurOrderEntryBiz.writeBackByOtherIssue(oldEntity, newEntity, param);
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvOtherIssueDetailAdvQuery) {
				ScmInvOtherIssueDetailAdvQuery scmInvOtherIssueDetailAdvQuery = (ScmInvOtherIssueDetailAdvQuery) page.getModel();
				if(StringUtils.isNotBlank(scmInvOtherIssueDetailAdvQuery.getWareHouseId())) {
					page.getParam().put(ScmInvOtherIssueBill2.FN_WAREHOUSEID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." +ScmInvOtherIssueBill2.FN_WAREHOUSEID, QueryParam.QUERY_IN,String.valueOf(scmInvOtherIssueDetailAdvQuery.getWareHouseId())));
				}
				if(scmInvOtherIssueDetailAdvQuery.getBizDateFrom()!=null){
					if(scmInvOtherIssueDetailAdvQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmInvOtherIssueBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." +ScmInvOtherIssueBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvOtherIssueDetailAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(scmInvOtherIssueDetailAdvQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmInvOtherIssueBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." +ScmInvOtherIssueBill2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvOtherIssueDetailAdvQuery.getBizDateFrom())));
					}
				}else if(scmInvOtherIssueDetailAdvQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmInvOtherIssueBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." +ScmInvOtherIssueBill2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmInvOtherIssueDetailAdvQuery.getBizDateTo())));
				}
				if(scmInvOtherIssueDetailAdvQuery.getCreateDateFrom()!=null){
					if(scmInvOtherIssueDetailAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmInvOtherIssueBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." +ScmInvOtherIssueBill2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvOtherIssueDetailAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvOtherIssueDetailAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmInvOtherIssueBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." +ScmInvOtherIssueBill2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvOtherIssueDetailAdvQuery.getCreateDateFrom())));
					}
				}else if(scmInvOtherIssueDetailAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmInvOtherIssueBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." +ScmInvOtherIssueBill2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvOtherIssueDetailAdvQuery.getCreateDateTo(),1))));
				}
				if(scmInvOtherIssueDetailAdvQuery.getVendorId()>0) {
					page.getParam().put(ScmInvOtherIssueBill2.FN_VENDORID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "." +ScmInvOtherIssueBill2.FN_VENDORID, QueryParam.QUERY_EQ,String.valueOf(scmInvOtherIssueDetailAdvQuery.getVendorId())));
				}
				if(StringUtils.isNotBlank(scmInvOtherIssueDetailAdvQuery.getReqFinOrgUnitNo())) {
					page.getParam().put(ScmInvOtherIssueBillEntry2.FN_REQFINORGUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBillEntry2.class) + "." +ScmInvOtherIssueBillEntry2.FN_REQFINORGUNITNO, QueryParam.QUERY_EQ,scmInvOtherIssueDetailAdvQuery.getReqFinOrgUnitNo()));
				}
				if (StringUtils.isNotBlank(scmInvOtherIssueDetailAdvQuery.getBrandId())) {
					page.getParam().put(ScmMaterial2.FN_BRANDID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterial2.class) + "." +ScmMaterial2.FN_BRANDID, QueryParam.QUERY_IN,scmInvOtherIssueDetailAdvQuery.getBrandId()));
				}
				if (StringUtils.isNotBlank(scmInvOtherIssueDetailAdvQuery.getItemId())) {
					page.getParam().put(ScmInvOtherIssueBillEntry2.FN_ITEMID, new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBillEntry2.class) + "." +ScmInvOtherIssueBillEntry2.FN_ITEMID, QueryParam.QUERY_IN,scmInvOtherIssueDetailAdvQuery.getItemId()));
				}
				if(StringUtils.isNotBlank(scmInvOtherIssueDetailAdvQuery.getClassId())) {
					StringBuffer classIds = new StringBuffer("");
					String[] split = scmInvOtherIssueDetailAdvQuery.getClassId().split(",");
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
