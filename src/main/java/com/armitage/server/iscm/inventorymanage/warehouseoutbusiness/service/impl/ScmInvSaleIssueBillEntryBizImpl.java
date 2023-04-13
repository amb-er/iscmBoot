package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iars.basedata.model.CustType;
import com.armitage.server.iars.basedata.model.Customer;
import com.armitage.server.iars.basedata.model.Customer2;
import com.armitage.server.iars.basedata.service.CustomerBiz;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.ScmBrandInfo;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmBrandInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.inventorymanage.internaltrans.service.ScmInvSaleOrderEntryBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvSaleIssueBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueDetailAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSaleIssueBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSaleIssueBillEntryBiz;
import com.armitage.server.system.model.OrgAdmin;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service("scmInvSaleIssueBillEntryBiz")
public class ScmInvSaleIssueBillEntryBizImpl extends BaseBizImpl<ScmInvSaleIssueBillEntry2> implements ScmInvSaleIssueBillEntryBiz {
    private PubSysBasicInfoBiz pubSysBasicInfoBiz;
    private ScmInvWareHouseBiz scmInvWareHouseBiz;
    private CustomerBiz customerBiz;
    private ScmInvSaleOrderEntryBiz scmInvSaleOrderEntryBiz;
    private ScmInvSaleIssueBillBiz scmInvSaleIssueBillBiz;
    private OrgStorageBiz orgStorageBiz;
    private OrgUnitBiz orgUnitBiz;
    private OrgAdminBiz orgAdminBiz;
    private ScmMaterialBiz scmMaterialBiz;
    private ScmMaterialGroupBiz scmMaterialGroupBiz;
    private ScmBrandInfoBiz scmBrandInfoBiz;
    private final Gson gson = new Gson();
    
    public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
        this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
    }
    public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
        this.scmInvWareHouseBiz = scmInvWareHouseBiz;
    }
    public void setCustomerBiz(CustomerBiz customerBiz) {
        this.customerBiz = customerBiz;
    }

	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}
	public void setScmInvSaleOrderEntryBiz(
			ScmInvSaleOrderEntryBiz scmInvSaleOrderEntryBiz) {
		this.scmInvSaleOrderEntryBiz = scmInvSaleOrderEntryBiz;
	}
	public void setScmInvSaleIssueBillBiz(ScmInvSaleIssueBillBiz scmInvSaleIssueBillBiz) {
		this.scmInvSaleIssueBillBiz = scmInvSaleIssueBillBiz;
	}
	
	public void setScmBrandInfoBiz(ScmBrandInfoBiz scmBrandInfoBiz) {
		this.scmBrandInfoBiz = scmBrandInfoBiz;
	}
	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}
	
	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}
	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}
	
	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." + ScmInvSaleIssueBill2.FN_ORGUNITNO), 
	                new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." + ScmInvSaleIssueBill2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." + ScmInvSaleIssueBill2.FN_ORGUNITNO), 
	                new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." + ScmInvSaleIssueBill2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}
    
    @Override
    protected void afterSelect(ScmInvSaleIssueBillEntry2 entity, Param param) throws AppException {
        if (entity != null) {
        	setConvertMap(entity,param);
        }
    }
    
    @Override
    protected void afterFindPage(List list, Page page, Param param) throws AppException {
        if (list != null && !list.isEmpty()) {
            for (ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry:(List<ScmInvSaleIssueBillEntry2>)list) {
                setConvertMap(scmInvSaleIssueBillEntry, param);
            }
        }
    }
    @Override
    public List<ScmInvSaleIssueBillEntry2> selectByOtId(long otId, Param param) throws AppException {
        if(otId > 0){
            HashMap<String, Object> map = new HashMap<>();
            map.put("otId", otId);
            List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList = ((ScmInvSaleIssueBillEntryDAO) dao).selectByOtId(map);
            if(scmInvSaleIssueBillEntryList!=null && !scmInvSaleIssueBillEntryList.isEmpty()){
            	for(ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry:scmInvSaleIssueBillEntryList){
            		setConvertMap(scmInvSaleIssueBillEntry,param);
            	}
            }
            return scmInvSaleIssueBillEntryList;
        }
        return null;
    }
    
    private void setConvertMap(ScmInvSaleIssueBillEntry2 scmInvSaleIssueBillEntry,Param param){
    	if(scmInvSaleIssueBillEntry!=null){
	    	if (scmInvSaleIssueBillEntry.getTaxRate() != null) {
	            //税率
	            PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmInvSaleIssueBillEntry.getTaxRate().toString(), null, param);
	            if (pubSysBasicInfo != null) {
	                scmInvSaleIssueBillEntry.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
	                scmInvSaleIssueBillEntry.setConvertMap(ScmInvSaleIssueBillEntry2.FN_TAXRATESTR, pubSysBasicInfo);
	            }
	        }
	    	if (scmInvSaleIssueBillEntry.getSaleTaxRate() != null) {
                //税率
                PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmInvSaleIssueBillEntry.getSaleTaxRate().toString(), null, param);
                if (pubSysBasicInfo != null) {
                    scmInvSaleIssueBillEntry.setSaleTaxRateStr(pubSysBasicInfo.getFInfoNo());
                    scmInvSaleIssueBillEntry.setConvertMap(ScmInvSaleIssueBillEntry2.FN_SALETAXRATESTR, pubSysBasicInfo);
                }
            }
	        if (scmInvSaleIssueBillEntry.getWareHouseId() > 0) {
	            //仓库
	            ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvSaleIssueBillEntry.getWareHouseId(), param);
	            if (scmInvWareHouse != null) {
	                scmInvSaleIssueBillEntry.setConvertMap(ScmInvSaleIssueBillEntry2.FN_WAREHOUSEID, scmInvWareHouse);
	            }
	        }
	        if (scmInvSaleIssueBillEntry.getBalanceCustId() > 0) {
	            //结算客户
	            Customer2 customer = customerBiz.select(scmInvSaleIssueBillEntry.getBalanceCustId(), param);
	            if (customer != null) {
	            	CustType custType = this.getConvertData(customer, Customer2.FN_CUSTTYPE, CustType.class);
	            	if (custType != null) {
	            		scmInvSaleIssueBillEntry.setCustClass(custType.getName());
					}
	                scmInvSaleIssueBillEntry.setBalanceCustName(customer.getCustName());
	                scmInvSaleIssueBillEntry.setConvertMap(ScmInvSaleIssueBillEntry2.FN_BALANCECUSTNAME, customer);
	            }
	        }
	        if (StringUtils.isNotBlank(scmInvSaleIssueBillEntry.getLot())) {
                //批次
	        	ScmInvStock2 scmInvStock = new ScmInvStock2(true);
				scmInvStock.setLot(scmInvSaleIssueBillEntry.getLot());
                scmInvSaleIssueBillEntry.setConvertMap(ScmInvSaleIssueBillEntry2.FN_LOT, scmInvStock);
            }

	        if (StringUtils.isNotBlank(scmInvSaleIssueBillEntry.getOrgUnitNo())) {
	        	//库存组织
	        	OrgBaseUnit OrgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvSaleIssueBillEntry.getOrgUnitNo(), param);
	        	if (OrgBaseUnit != null) {
	        		scmInvSaleIssueBillEntry.setConvertMap(ScmInvSaleIssueBillEntry2.FN_ORGUNITNO, OrgBaseUnit);
	        	}
	        }
	        if (StringUtils.isNotBlank(scmInvSaleIssueBillEntry.getOutOrgUnitNo())) {
	        	//库存组织
	        	OrgAdmin orgAdmin = orgAdminBiz.selectByOrgUnitNo(scmInvSaleIssueBillEntry.getOutOrgUnitNo(), param);
	        	if (orgAdmin != null) {
	        		scmInvSaleIssueBillEntry.setConvertMap(ScmInvSaleIssueBillEntry2.FN_OUTORGUNITNO, orgAdmin);
	        	}
	        }
	        if(scmInvSaleIssueBillEntry.getItemId()>0) {
	        	ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvSaleIssueBillEntry.getItemId(), param);
	        	if(scmMaterial!=null) {
	        		scmInvSaleIssueBillEntry.setItemNo(scmMaterial.getItemNo());
	        		scmInvSaleIssueBillEntry.setItemName(scmMaterial.getItemName());
	        		scmInvSaleIssueBillEntry.setSpec(scmMaterial.getSpec());
	        	}
	        }
	        //品牌
	        if (scmInvSaleIssueBillEntry.getBrandId()>0) {
				ScmBrandInfo scmBrandInfo = scmBrandInfoBiz.selectDirect(scmInvSaleIssueBillEntry.getBrandId(), param);
				if (scmBrandInfo != null) {
					scmInvSaleIssueBillEntry.setBrandName(scmBrandInfo.getName());
				}
			}
    	}
    }
    
    public <T> T getConvertData(Object object, String keyField, Class<T> cls) {
        List<String> keyList = ((BaseModel) object).getConvertKey();
        List<String> valueList = ((BaseModel) object).getConvertValue();
        Object convertObject = null;
        if (keyList.isEmpty() || valueList.isEmpty()) {
            return null;
        }
        boolean exists = false;
        int i;
        for (i = 0; i < keyList.size(); i++) {
            if (keyField.equals(keyList.get(i))) {
                exists = true;
                break;
            }
        }
        if (exists && valueList.size() > i) {
            convertObject = valueList.get(i);
        }
        if (convertObject == null) {
            return null;
        }
        
        return (T) gson.fromJson((String) convertObject, cls);
    }
    
    @Override
    public void deleteByOtId(long otId, Param param) throws AppException {
        if(otId > 0){
        	List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList=this.selectByOtId(otId, param);
            this.delete(scmInvSaleIssueBillEntryList, param);
        }
    }
    @Override
    public List<ScmInvSaleIssueBillEntry2> selectInvQty(long otId, Param param) throws AppException{
        if(otId > 0){
            HashMap<String, Object> map = new HashMap<>();
            map.put("otId", otId);
            return ((ScmInvSaleIssueBillEntryDAO) dao).selectInvQty(map);
        }
        return null;
    }

	@Override
	protected void afterAdd(ScmInvSaleIssueBillEntry2 entity, Param param)
			throws AppException {
		ScmInvSaleIssueBill2 scmInvSaleIssueBill = scmInvSaleIssueBillBiz.selectDirect(entity.getOtId(), param);
		if(StringUtils.equals("1",scmInvSaleIssueBill.getBizType()))
			scmInvSaleOrderEntryBiz.writeBackBySaleIssue(null, entity, param);
	}
	@Override
	protected void afterDelete(ScmInvSaleIssueBillEntry2 entity, Param param)
			throws AppException {
		ScmInvSaleIssueBill2 scmInvSaleIssueBill = scmInvSaleIssueBillBiz.selectDirect(entity.getOtId(), param);
		if(StringUtils.equals("1",scmInvSaleIssueBill.getBizType()))
			scmInvSaleOrderEntryBiz.writeBackBySaleIssue(entity, null, param);
	}
	@Override
	protected void beforeAdd(ScmInvSaleIssueBillEntry2 entity, Param param)
			throws AppException {
		if (entity != null) {
			if (entity.getWareHouseId()>0 && StringUtils.isNotBlank(entity.getOutOrgUnitNo())) {
				throw new AppException("iscm.inventorymanage.ScmInvSaleIssueBillEntry.beforeValidate.outWareHouseAndOgr");
			}
		}
	}
	
	@Override
	protected void beforeUpdate(ScmInvSaleIssueBillEntry2 oldentity,ScmInvSaleIssueBillEntry2 newentity, Param param)throws AppException {
		if (newentity != null) {
			if (newentity.getWareHouseId()>0 && StringUtils.isNotBlank(newentity.getOutOrgUnitNo())) {
				throw new AppException("iscm.inventorymanage.ScmInvSaleIssueBillEntry.beforeValidate.outWareHouseAndOgr");
			}
		}
	}
	
	@Override
	protected void afterUpdate(ScmInvSaleIssueBillEntry2 oldEntity,
			ScmInvSaleIssueBillEntry2 newEntity, Param param)
			throws AppException {
		ScmInvSaleIssueBill2 scmInvSaleIssueBill = scmInvSaleIssueBillBiz.selectDirect(newEntity.getOtId(), param);
		if(StringUtils.equals("1",scmInvSaleIssueBill.getBizType()))
			scmInvSaleOrderEntryBiz.writeBackBySaleIssue(oldEntity, newEntity, param);
	}
	@Override
	public List<ScmInvSaleIssueBillEntry2> selectOutEffectRow(long otId, Param param) throws AppException {
		 HashMap<String, Object> map = new HashMap<>();
         map.put("otId", otId);
         return ((ScmInvSaleIssueBillEntryDAO) dao).selectOutEffectRow(map);
	}
	@Override
	public List<ScmInvSaleIssueBillEntry2> selectSaleIssueByPurOut(long purOutDtlId,String costMethod, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("purOutDtlId",purOutDtlId);
		map.put("costMethod",costMethod);
		return ((ScmInvSaleIssueBillEntryDAO)dao).selectSaleIssueByPurOut(map);
	}
	@Override
	public List<ScmInvSaleIssueBillEntry2> selectCancelPostEffectRow(long otId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("otId",otId);
		return ((ScmInvSaleIssueBillEntryDAO)dao).selectCancelPostEffectRow(map);
	}
    
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvSaleIssueDetailAdvQuery) {
				ScmInvSaleIssueDetailAdvQuery scmInvSaleIssueDetailAdvQuery = (ScmInvSaleIssueDetailAdvQuery) page.getModel();
				if(StringUtils.isNotBlank(scmInvSaleIssueDetailAdvQuery.getCustId())) {
					page.getParam().put(ScmInvSaleIssueBillEntry2.FN_BALANCECUSTID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "." +ScmInvSaleIssueBillEntry2.FN_BALANCECUSTID, QueryParam.QUERY_IN,scmInvSaleIssueDetailAdvQuery.getCustId()));
				}
				if(scmInvSaleIssueDetailAdvQuery.getBizDateFrom()!=null){
					if(scmInvSaleIssueDetailAdvQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmInvSaleIssueBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." +ScmInvSaleIssueBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvSaleIssueDetailAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(scmInvSaleIssueDetailAdvQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmInvSaleIssueBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." +ScmInvSaleIssueBill2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvSaleIssueDetailAdvQuery.getBizDateFrom())));
					}
				}else if(scmInvSaleIssueDetailAdvQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmInvSaleIssueBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." +ScmInvSaleIssueBill2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmInvSaleIssueDetailAdvQuery.getBizDateTo())));
				}
				if(scmInvSaleIssueDetailAdvQuery.getCreateDateFrom()!=null){
					if(scmInvSaleIssueDetailAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmInvSaleIssueBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." +ScmInvSaleIssueBill2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvSaleIssueDetailAdvQuery.getCreateDateTo()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvSaleIssueDetailAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmInvSaleIssueBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." +ScmInvSaleIssueBill2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvSaleIssueDetailAdvQuery.getCreateDateFrom())));
					}
				}else if(scmInvSaleIssueDetailAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmInvSaleIssueBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBill2.class) + "." +ScmInvSaleIssueBill2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvSaleIssueDetailAdvQuery.getCreateDateTo(),1))));
				}
				if(StringUtils.isNotBlank(scmInvSaleIssueDetailAdvQuery.getWareHouseId())) {
					page.getParam().put(ScmInvSaleIssueBillEntry2.FN_WAREHOUSEID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "." +ScmInvSaleIssueBillEntry2.FN_WAREHOUSEID, QueryParam.QUERY_IN,String.valueOf(scmInvSaleIssueDetailAdvQuery.getWareHouseId())));
				}
				if (StringUtils.isNotBlank(scmInvSaleIssueDetailAdvQuery.getBrandId())) {
					page.getParam().put(ScmMaterial2.FN_BRANDID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterial2.class) + "." +ScmMaterial2.FN_BRANDID, QueryParam.QUERY_IN,scmInvSaleIssueDetailAdvQuery.getBrandId()));
				}
				if (scmInvSaleIssueDetailAdvQuery.isLowPrice()) {
					if(StringUtils.isNotBlank(page.getSqlCondition())){
						page.setSqlCondition((page.getSqlCondition()+" and ScmInvSaleIssueBillEntry.saleTaxPrice <= ScmInvSaleIssueBillEntry.taxPrice"));
					}else{
						page.setSqlCondition("and ScmInvSaleIssueBillEntry.saleTaxPrice <= ScmInvSaleIssueBillEntry.taxPrice");
					}
				}
				if (StringUtils.isNotBlank(scmInvSaleIssueDetailAdvQuery.getItemId())) {
					page.getParam().put(ScmInvSaleIssueBillEntry2.FN_ITEMID, new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "." +ScmInvSaleIssueBillEntry2.FN_ITEMID, QueryParam.QUERY_IN,scmInvSaleIssueDetailAdvQuery.getItemId()));
				}
				if (StringUtils.isNotBlank(scmInvSaleIssueDetailAdvQuery.getCustType())) {
					Page page2 = new Page();
					page2.setShowCount(Integer.MAX_VALUE);
					page2.setModelClass(Customer2.class);
					page2.getParam().put(Customer2.FN_CUSTTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(Customer2.class) + "." +Customer2.FN_CUSTTYPE, QueryParam.QUERY_IN,scmInvSaleIssueDetailAdvQuery.getCustType()));
					List<Customer2> customerList = customerBiz.findPage(page2, param);
					if (customerList != null && !customerList.isEmpty()) {
						StringBuffer sbCustId = new StringBuffer();
						for (Customer2 customer2 : customerList) {
							if (StringUtils.isNotBlank(sbCustId.toString())) {
								sbCustId.append(",");
							}
							sbCustId.append(customer2.getId());
						}
						if (StringUtils.isNotBlank(sbCustId.toString())) {
							page.getParam().put(ScmInvSaleIssueBillEntry2.FN_BALANCECUSTID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvSaleIssueBillEntry2.class) + "." +ScmInvSaleIssueBillEntry2.FN_BALANCECUSTID, QueryParam.QUERY_IN,sbCustId.toString()));
						}
					}
				}
				if(StringUtils.isNotBlank(scmInvSaleIssueDetailAdvQuery.getClassId())) {
					StringBuffer classIds = new StringBuffer("");
					String[] split = scmInvSaleIssueDetailAdvQuery.getClassId().split(",");
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
