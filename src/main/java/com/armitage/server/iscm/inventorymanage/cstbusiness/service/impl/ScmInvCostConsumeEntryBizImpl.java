package com.armitage.server.iscm.inventorymanage.cstbusiness.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmCstInitBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvCostConsumeEntryDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBillEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeEntry;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCostConsumeBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCostConsumeEntryBiz;
import org.springframework.stereotype.Service;

@Service("scmInvCostConsumeEntryBiz")
public class ScmInvCostConsumeEntryBizImpl extends BaseBizImpl<ScmInvCostConsumeEntry2> implements ScmInvCostConsumeEntryBiz {

    private ScmInvCostConsumeBiz scmInvCostConsumeBiz;
    private PubSysBasicInfoBiz pubSysBasicInfoBiz;
    
    public void setScmInvCostConsumeBiz(ScmInvCostConsumeBiz scmInvCostConsumeBiz) {
        this.scmInvCostConsumeBiz = scmInvCostConsumeBiz;
    }

    public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
        this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
    }

    
    
    @Override
    protected void afterSelect(ScmInvCostConsumeEntry2 entity, Param param) throws AppException {
        if(entity != null){
            if (entity.getTaxRate() != null){
                //税率
                PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", entity.getTaxRate().toString(), null, param);
                if (pubSysBasicInfo != null) {
                    entity.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
                    entity.setConvertMap(ScmInvCostConsumeEntry2.FN_TAXRATESTR, pubSysBasicInfo);
                }
            }
        }
    }

    @Override
    public List<ScmInvCostConsumeEntry2> selectByDcId(long dcId, Param param) throws AppException {
        if(dcId > 0){
            HashMap<String, Object> map = new HashMap<>();
            map.put("dcId", dcId);
            return ((ScmInvCostConsumeEntryDAO) dao).selectByDcId(map);
        }
        return null;
    }

    @Override
    public void deleteByDcId(long dcId, Param param) throws AppException {
        if(dcId > 0){
            HashMap<String, Object> map = new HashMap<>();
            map.put("dcId", dcId);
            ((ScmInvCostConsumeEntryDAO) dao).deleteByDcId(map);
        }   
        
    }

	@Override
	public List<ScmInvCostConsumeEntry2> checkStockByReturnWareHouse(long dcId, Param param) throws AppException {
		if(dcId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("dcId", dcId);
			return ((ScmInvCostConsumeEntryDAO) dao).checkStockByReturnWareHouse(map);
		}
		return null;
	}

	@Override
	public List<ScmInvCostConsumeEntry2> selectOutEffectRow(long dcId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("dcId", dcId);
		return ((ScmInvCostConsumeEntryDAO) dao).selectOutEffectRow(map);
	}

}
