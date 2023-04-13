package com.armitage.server.iscm.inventorymanage.cstbusiness.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmCstInitBillDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmCstInitBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBillEntry;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBillEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmCstInitBillBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmCstInitBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.ScmInvPurInWarehsBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvSaleIssueBillEntryDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import org.springframework.stereotype.Service;

@Service("scmCstInitBillEntryBiz")
public class ScmCstInitBillEntryBizImpl extends BaseBizImpl<ScmCstInitBillEntry2> implements ScmCstInitBillEntryBiz {

    private ScmCstInitBillBiz scmCstInitBillBiz; 
    private PubSysBasicInfoBiz pubSysBasicInfoBiz; 
    
    public void setScmCstInitBillBiz(ScmCstInitBillBiz scmCstInitBillBiz) {
        this.scmCstInitBillBiz = scmCstInitBillBiz;
    }

    public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
        this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
    }

    @Override
    protected void afterSelect(ScmCstInitBillEntry2 entity, Param param) throws AppException {
        if(entity != null){
            if (entity.getTaxRate() != null){
                //税率
                PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", entity.getTaxRate().toString(), null, param);
                if (pubSysBasicInfo != null) {
                    entity.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
                    entity.setConvertMap(ScmCstInitBillEntry2.FN_TAXRATESTR, pubSysBasicInfo);
                }
            }
        }
    }

    @Override
    public List<ScmCstInitBillEntry2> selectByInitId(long initId, Param param) throws AppException {
        if(initId > 0){
            HashMap<String, Object> map = new HashMap<>();
            map.put("initId", initId);
            List<ScmCstInitBillEntry2> scmCstInitBillEntryList = ((ScmCstInitBillEntryDAO) dao).selectByInitId(map);
            if(scmCstInitBillEntryList!=null && !scmCstInitBillEntryList.isEmpty()) {
				for(ScmCstInitBillEntry2 scmCstInitBillEntry:scmCstInitBillEntryList) {
					setConvertMap(scmCstInitBillEntry,param);
				}
			}
			return scmCstInitBillEntryList;
        }
        return null;
    }
    
    private void setConvertMap(ScmCstInitBillEntry2 scmCstInitBillEntry,Param param) throws AppException {
		if(scmCstInitBillEntry != null){
			if (scmCstInitBillEntry.getTaxRate() != null){
				//税率
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmCstInitBillEntry.getTaxRate().toString(), null, param);
				if (pubSysBasicInfo != null) {
					scmCstInitBillEntry.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
					scmCstInitBillEntry.setConvertMap(ScmCstInitBillEntry2.FN_TAXRATESTR, pubSysBasicInfo);
				}
			}
		}
	}

    @Override
    public void deleteByInitId(long initId, Param param) throws AppException {
        if(initId > 0){
            HashMap<String, Object> map = new HashMap<>();
            map.put("initId", initId);
            ((ScmCstInitBillEntryDAO) dao).deleteByInitId(map);
        }        
    }

	@Override
	public List<ScmCstInitBillEntry2> selectInvQty(long initId, Param param) throws AppException {
		if (initId > 0) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("initId", initId);
			return ((ScmCstInitBillEntryDAO) dao).selectInvQty(map);
		}
		return null;
	}

	@Override
	public List<ScmCstInitBillEntry2> selectCancelPostEffectRow(long initId, Param param) throws AppException {
		if (initId > 0) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("initId", initId);
			return ((ScmCstInitBillEntryDAO) dao).selectCancelPostEffectRow(map);
		}
		return null;
	}

}