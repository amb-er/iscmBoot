package com.armitage.server.iscm.inventorymanage.inventorysetting.service.impl;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.cache.ModelCacheMana;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBill2;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.service.ScmInvInitBillBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.dao.ScmInvAccreditWhDAO;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvAccreditWh;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvAccreditWh2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvAccreditWhBiz;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.model.SystemState;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SystemStateBiz;
import com.armitage.server.system.util.OrgUnitRelationType;
import org.springframework.stereotype.Service;

@Service("scmInvAccreditWhBiz")
public class ScmInvAccreditWhBizImpl extends BaseBizImpl<ScmInvAccreditWh2> implements ScmInvAccreditWhBiz {

	private OrgUnitBiz orgUnitBiz;
	private SystemStateBiz systemStateBiz;
	private ScmInvInitBillBiz scmInvInitBillBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private PeriodCalendarBiz periodCalendarBiz;
	private OrgStorageBiz orgStorageBiz;
	
	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public SystemStateBiz getSystemStateBiz() {
		return systemStateBiz;
	}

	public void setSystemStateBiz(SystemStateBiz systemStateBiz) {
		this.systemStateBiz = systemStateBiz;
	}

	public ScmInvInitBillBiz getScmInvInitBillBiz() {
		return scmInvInitBillBiz;
	}

	public void setScmInvInitBillBiz(ScmInvInitBillBiz scmInvInitBillBiz) {
		this.scmInvInitBillBiz = scmInvInitBillBiz;
	}

	public OrgUnitBiz getOrgUnitBiz() {
		return orgUnitBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}

	public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvAccreditWh.class) + "." + ScmInvAccreditWh.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvAccreditWh.class) + "." + ScmInvAccreditWh.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvAccreditWh.class) + "." + ScmInvAccreditWh.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvAccreditWh.class) + "." + ScmInvAccreditWh.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		HashMap<String,Object> cacheMap = new HashMap<String,Object>();
		List<ScmInvAccreditWh2> scmInvAccreditWhList = list;
		if(scmInvAccreditWhList != null && !scmInvAccreditWhList.isEmpty()){
			for(ScmInvAccreditWh2 scmInvAccreditWh : scmInvAccreditWhList){
				if(StringUtils.isNotBlank(scmInvAccreditWh.getMorgUnitNo())){
					//管理组织
					OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvAccreditWh.getMorgUnitNo());
					if(orgBaseUnit==null){
						orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvAccreditWh.getMorgUnitNo(), param);
						cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvAccreditWh.getMorgUnitNo(),orgBaseUnit);
					}
					if (orgBaseUnit != null) {
						scmInvAccreditWh.setConvertMap(ScmInvAccreditWh2.FN_MORGUNITNO, orgBaseUnit);
					}
				}
				if(scmInvAccreditWh.getPeriodId()>0){
					PeriodCalendar periodCalendar = periodCalendarBiz.selectDirect(scmInvAccreditWh.getPeriodId(), param);
					if(periodCalendar!=null){
						scmInvAccreditWh.setConvertMap(ScmInvAccreditWh2.FN_PERIODID, periodCalendar);
					}
				}
			}
		}
	}

	@Override
	public List<ScmInvAccreditWh2> selectByWareHouseIdList(String wareHouseIdList, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("wareHouseIdList", wareHouseIdList);
		return ((ScmInvAccreditWhDAO) dao).selectByWareHouseIdList(map);
	}

	/**
	 * 结束初始化
	 */
    @Override
    public Object initEnd(ScmInvAccreditWh2 scmInvAccreditWh, Param param) throws AppException {
        // 1 获取当前期间
        List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, param.getOrgUnitNo(), false, null, param);
        if(orgCompanyList==null || orgCompanyList.isEmpty()){
          throw new AppException("iscm.inventorymanage.common.invOrgUnit.notfinorg");
        }
    	
        SystemState systemState = systemStateBiz.selectBySystemId(orgCompanyList.get(0).getOrgUnitNo(), 170, param);
        if (systemState == null){
            throw new AppException("com.armitage.server.iscm.inventorymanage.inventorysetting.service.impl.ScmInvAccreditWhBizImpl.notExists");
        }else if (systemState != null  && systemState.isEndInit()) {
            throw new AppException("com.armitage.server.iscm.inventorymanage.inventorysetting.service.impl.ScmInvAccreditWhBizImpl.isEndInit"); 
        } 
        // 2 检查是否存在未过账的期初结存(ScmInvInitBill)
        List<ScmInvInitBill2> scmInvInitBillList = scmInvInitBillBiz.selectNotPost(scmInvAccreditWh.getOrgUnitNo(), scmInvAccreditWh.getWareHouseId(), param);
        if (scmInvInitBillList != null && scmInvInitBillList.size() > 0) {
            throw new AppException(
                    Message.getMessage(param.getLang(),"com.armitage.server.iscm.inventorymanage.inventorysetting.service.impl.ScmInvAccreditWhBizImpl.notPostBills"));
         // 仓库【'+ls_whname+'】还存在未过账的期初结存，请检查!
        }               
        // 3 检查是否已经结束初始化(ScmInvAccreditWH)
        if (scmInvAccreditWh.isEndInit()) {
            throw new AppException(
                    Message.getMessage(param.getLang(),"com.armitage.server.iscm.inventorymanage.inventorysetting.service.impl.ScmInvAccreditWhBizImpl.isEndInit"));
         // 仓库【'+ls_whname+'】已结束初始化，请检查!
        }
        // 4 更新信息(ScmInvAccreditWH)
        HashMap<String, Object> map = new HashMap<>();
        scmInvAccreditWh.setEndInit(true);
        scmInvAccreditWh.setStatus("Y");
        map.put("orgUnitNo", scmInvAccreditWh.getOrgUnitNo());
        map.put("wareHouseId", scmInvAccreditWh.getWareHouseId());
        map.put("endInit", scmInvAccreditWh.isEndInit());
        map.put("status", scmInvAccreditWh.getStatus());
        map.put("periodId", systemState.getCurrentPeriodId());
        ((ScmInvAccreditWhDAO) dao).updateEndInit(map);
        return true;
    }
    
    /**
     * 反初始化
     */
    @Override
    public Object reverseInit(ScmInvAccreditWh2 scmInvAccreditWh, Param param) throws AppException {
        // 1 获取当前期间
    	List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, param.getOrgUnitNo(), false, null, param);
        if(orgCompanyList==null || orgCompanyList.isEmpty()){
          throw new AppException("iscm.inventorymanage.common.invOrgUnit.notfinorg");
        }
    	
        SystemState systemState = systemStateBiz.selectBySystemId(orgCompanyList.get(0).getOrgUnitNo(), 170, param);
        if (systemState == null){
            throw new AppException("com.armitage.server.iscm.inventorymanage.inventorysetting.service.impl.ScmInvAccreditWhBizImpl.notExists");
        }else if (systemState != null  && systemState.isEndInit()) {
            throw new AppException("com.armitage.server.iscm.inventorymanage.inventorysetting.service.impl.ScmInvAccreditWhBizImpl.isEndInit"); 
        } 
        // 2 检查仓库【'+ls_whname+'】已存在业务单据，不能反结束初始化!
        HashMap<String, Object> map = new HashMap<>();
        map.put("orgUnitNo", scmInvAccreditWh.getOrgUnitNo());
        map.put("wareHouseId", scmInvAccreditWh.getWareHouseId());
        int count  = ((ScmInvAccreditWhDAO) dao).selectCount(map);
        if (count > 0) {
            throw new AppException(
                    Message.getMessage(param.getLang(),"com.armitage.server.iscm.inventorymanage.inventorysetting.service.impl.ScmInvAccreditWhBizImpl.isBusinessBills"));
         // 仓库【'+ls_whname+'】已存在业务单据，不能反结束初始化!
        } 
        // 3 更新信息(ScmInvAccreditWH)
        HashMap<String, Object> map2 = new HashMap<>();
        scmInvAccreditWh.setEndInit(false);
        scmInvAccreditWh.setStatus("N");
        map2.put("orgUnitNo", scmInvAccreditWh.getOrgUnitNo());
        map2.put("wareHouseId", scmInvAccreditWh.getWareHouseId());
        map2.put("endInit", scmInvAccreditWh.isEndInit());
        map2.put("status", scmInvAccreditWh.getStatus());
        map2.put("periodId", "0");
        ((ScmInvAccreditWhDAO) dao).updateReverseInit(map2);
        return true;
    }

	@Override
	public ScmInvAccreditWh selectByWareHouseId(long wareHouseId,String invOrgUnitNo,Param param) throws AppException {
		String key = invOrgUnitNo + "_" + wareHouseId;
		// 从缓存获取
		if (ModelCacheMana.keyExists(key, modelClass)) {
			Object obj = ModelCacheMana.get(key, modelClass);
			if (obj != null && obj.getClass().equals(modelClass)) {
				return (ScmInvAccreditWh) obj;
			}
		}
		HashMap<String, Object> map = new HashMap<>();
        map.put("wareHouseId", wareHouseId);
        map.put("orgUnitNo", invOrgUnitNo);
        ScmInvAccreditWh scmInvAccreditWh = ((ScmInvAccreditWhDAO)dao).selectByWareHouseId(map);
		// 放进缓存
		if (scmInvAccreditWh != null) {
			ModelCacheMana.set(key, scmInvAccreditWh);
		}
		return scmInvAccreditWh;
	}
	
	
}

