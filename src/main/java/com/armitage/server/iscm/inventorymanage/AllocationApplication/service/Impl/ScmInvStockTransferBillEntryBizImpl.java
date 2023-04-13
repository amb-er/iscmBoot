package com.armitage.server.iscm.inventorymanage.AllocationApplication.service.Impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.dao.ScmInvStockTransferBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBillEntry2;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.service.ScmInvStockTransferBillBiz;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.service.ScmInvStockTransferBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmInvStockTransferBillEntryBiz")
public class ScmInvStockTransferBillEntryBizImpl extends BaseBizImpl<ScmInvStockTransferBillEntry2> implements ScmInvStockTransferBillEntryBiz {
	private ScmInvStockTransferBillBiz scmInvStockTransferBillBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private OrgUnitBiz orgUnitBiz;
	private UsrBiz usrBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	
	public OrgUnitBiz getOrgUnitBiz() {
		return orgUnitBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public ScmInvWareHouseBiz getScmInvWareHouseBiz() {
		return scmInvWareHouseBiz;
	}

	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}

	public ScmInvStockTransferBillBiz getScmInvStockTransferBillBiz() {
		return scmInvStockTransferBillBiz;
	}

	public UsrBiz getUsrBiz() {
		return usrBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setScmInvStockTransferBillBiz(ScmInvStockTransferBillBiz scmInvStockTransferBillBiz) {
		this.scmInvStockTransferBillBiz = scmInvStockTransferBillBiz;
	}

	public void ScmInvStockTransferBillBiz(ScmInvStockTransferBillBiz scmInvStockTransferBillBiz) {
		this.scmInvStockTransferBillBiz = scmInvStockTransferBillBiz;
	}
	
	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}

	@Override
	protected void afterSelect(ScmInvStockTransferBillEntry2 entity, Param param) throws AppException {
		if(entity != null){
			setConvertMap(entity,param);		
		}
	}

	@Override
	public List<ScmInvStockTransferBillEntry2> selectByWtId(long wtId, Param param) throws AppException {
		if(wtId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("wtId", wtId);
			List<ScmInvStockTransferBillEntry2> scmInvStockTransferBillEntryList = ((ScmInvStockTransferBillEntryDAO) dao).selectByWtId(map);
			if(scmInvStockTransferBillEntryList!=null && !scmInvStockTransferBillEntryList.isEmpty()){
				for(ScmInvStockTransferBillEntry2 scmInvStockTransferBillEntry:scmInvStockTransferBillEntryList){
					setConvertMap(scmInvStockTransferBillEntry,param);
				}
			}
			return scmInvStockTransferBillEntryList;
		}
		return null;
	}

	@Override
	public void deleteByWtId(long wtId, Param param) throws AppException {
		if(wtId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("wtId", wtId);
			((ScmInvStockTransferBillEntryDAO) dao).deleteByWtId(map);
		}
	}
	
	private void setConvertMap(ScmInvStockTransferBillEntry2 scmInvStockTransferBillEntry, Param param){
		if (scmInvStockTransferBillEntry.getWareHouseId() > 0) {
            //调出仓库
            ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvStockTransferBillEntry.getWareHouseId(), param);
            if (scmInvWareHouse != null) {
            	scmInvStockTransferBillEntry.setConvertMap(ScmInvStockTransferBillEntry2.FN_WAREHOUSEID, scmInvWareHouse);
            }
        }
		if (scmInvStockTransferBillEntry.getTaxRate() != null) {
            //税率
            PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmInvStockTransferBillEntry.getTaxRate().toString(), null, param);
            if (pubSysBasicInfo != null) {
            	scmInvStockTransferBillEntry.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
            	scmInvStockTransferBillEntry.setConvertMap(scmInvStockTransferBillEntry.FN_TAXRATE, pubSysBasicInfo);
            }
        }
		if (scmInvStockTransferBillEntry.getReceiptWarehouseId() > 0) {
            //调入仓库
            ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvStockTransferBillEntry.getReceiptWarehouseId(), param);
            if (scmInvWareHouse != null) {
            	scmInvStockTransferBillEntry.setConvertMap(ScmInvStockTransferBillEntry2.FN_RECEIPTWAREHOUSEID, scmInvWareHouse);
            }
        }
		if (StringUtils.isNotBlank(scmInvStockTransferBillEntry.getUseOrgUnitNo())) {
            //调入部门
			OrgBaseUnit OrgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvStockTransferBillEntry.getUseOrgUnitNo(), param);
            if (OrgBaseUnit != null) {
            	scmInvStockTransferBillEntry.setConvertMap(ScmInvStockTransferBillEntry2.FN_USEORGUNITNO, OrgBaseUnit);
            }
        }
	}
}

