package com.armitage.server.iscm.inventorymanage.cstbusiness.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.ScmCostUseSet2;
import com.armitage.server.iscm.basedata.model.ScmCostUseType;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmCostUseTypeBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvMoveBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBillEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvMoveBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillEntry2;
import org.springframework.stereotype.Service;

@Service("scmInvMoveBillEntryBiz")
public class ScmInvMoveBillEntryBizImpl extends BaseBizImpl<ScmInvMoveBillEntry2> implements ScmInvMoveBillEntryBiz {

    private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private ScmCostUseTypeBiz scmCostUseTypeBiz;

	public ScmCostUseTypeBiz getScmCostUseTypeBiz() {
		return scmCostUseTypeBiz;
	}

	public void setScmCostUseTypeBiz(ScmCostUseTypeBiz scmCostUseTypeBiz) {
		this.scmCostUseTypeBiz = scmCostUseTypeBiz;
	}
    
    public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
        this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
    }

    
    
    @Override
    protected void afterSelect(ScmInvMoveBillEntry2 entity, Param param) throws AppException {
        if(entity != null){
            if (entity.getTaxRate() != null){
                //税率
                PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", entity.getTaxRate().toString(), null, param);
                if (pubSysBasicInfo != null) {
                    entity.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
                    entity.setConvertMap(ScmInvCostConsumeEntry2.FN_TAXRATESTR, pubSysBasicInfo);
                }
            }
            if (StringUtils.isNotBlank(entity.getLot())) {
                //批次
                entity.setConvertMap(ScmInvMoveBillEntry2.FN_LOT, entity);
            }
        }
    }



    @Override
    public List<ScmInvMoveBillEntry2> selectByWtId(long wtId, Param param) throws AppException {
        if(wtId > 0){
            HashMap<String, Object> map = new HashMap<>();
            map.put("wtId", wtId);
            List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList = ((ScmInvMoveBillEntryDAO) dao).selectByWtId(map);
            if (scmInvMoveBillEntryList != null && !scmInvMoveBillEntryList.isEmpty()) {
            	for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveBillEntryList) {
            		setConvertMap(scmInvMoveBillEntry, param);
            	}
            }
            return scmInvMoveBillEntryList;
        }
        return null;
    }

    @Override
    public void deleteByWtId(long wtId, Param param) throws AppException {
        if(wtId > 0){
        	List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList = this.selectByWtId(wtId, param);
            if(scmInvMoveBillEntryList!=null && !scmInvMoveBillEntryList.isEmpty())
            	this.delete(scmInvMoveBillEntryList, param);
        }  
    }



	@Override
	public List<ScmInvMoveBillEntry2> selectOutEffectRow(long wtId, Param param) throws AppException {
		 HashMap<String, Object> map = new HashMap<>();
         map.put("wtId", wtId);
         return ((ScmInvMoveBillEntryDAO) dao).selectOutEffectRow(map);
	}



	@Override
	public List<ScmInvMoveBillEntry2> selectCancelEffectRow(long wtId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
        map.put("wtId", wtId);
        return ((ScmInvMoveBillEntryDAO) dao).selectCancelEffectRow(map);
	}
	
	private void setConvertMap(ScmInvMoveBillEntry2 entity, Param param) throws AppException {
		if (entity.getCostUseTypeOutId() > 0 ) {
			ScmCostUseType scmCostUseType = scmCostUseTypeBiz.select(entity.getCostUseTypeOutId(), param);
			if (scmCostUseType != null) {
				ScmCostUseSet2 costUseSet = new ScmCostUseSet2();
				costUseSet.setCostUseTypeId(scmCostUseType.getId());
				costUseSet.setScmCostUseTypeName(scmCostUseType.getName());
				entity.setConvertMap(ScmInvMoveBillEntry2.FN_COSTUSETYPEOUTID, costUseSet);
			}
        }else {
        	entity.setConvertMap(ScmInvMoveBillEntry2.FN_COSTUSETYPEOUTID, new ScmCostUseSet2());
        }
		if (entity.getCostUseTypeInId() > 0 ) {
			ScmCostUseType scmCostUseType = scmCostUseTypeBiz.select(entity.getCostUseTypeInId(), param);
			if (scmCostUseType != null) {
				ScmCostUseSet2 costUseSet = new ScmCostUseSet2();
				costUseSet.setCostUseTypeId(scmCostUseType.getId());
				costUseSet.setScmCostUseTypeName(scmCostUseType.getName());
				entity.setConvertMap(ScmInvMoveBillEntry2.FN_COSTUSETYPEINID, costUseSet);
			}
        }else {
        	entity.setConvertMap(ScmInvMoveBillEntry2.FN_COSTUSETYPEINID, new ScmCostUseSet2());
        }
		if(entity != null && entity.getWtId() > 0){
			if (entity.getTaxRate() != null){
				//税率
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", entity.getTaxRate().toString(), null, param);
				if (pubSysBasicInfo != null) {
					entity.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
					entity.setConvertMap(ScmInvMoveBillEntry2.FN_TAXRATESTR, pubSysBasicInfo);
				}
			}
		}
	}
    
}
