
package com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurReturnsEntryDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturns2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturnsEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReceiveEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReturnsBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReturnsEntryBiz;
import com.armitage.server.system.model.OrgAdmin;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.service.OrgAdminBiz;
import org.springframework.stereotype.Service;

@Service("scmPurReturnsEntryBiz")
public class ScmPurReturnsEntryBizImpl extends BaseBizImpl<ScmPurReturnsEntry2> implements ScmPurReturnsEntryBiz {
	
	private ScmPurReturnsBiz scmPurReturnsBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private ScmPurReceiveEntryBiz scmPurReceiveEntryBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private OrgAdminBiz orgAdminBiz;
	
	public void setScmPurReceiveEntryBiz(ScmPurReceiveEntryBiz scmPurReceiveEntryBiz) {
		this.scmPurReceiveEntryBiz = scmPurReceiveEntryBiz;
	}

	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}

	public void setScmPurReturnsBiz(ScmPurReturnsBiz scmPurReturnsBiz) {
		this.scmPurReturnsBiz = scmPurReturnsBiz;
	}
	
	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}

	@Override
	public List<ScmPurReturnsEntry2> selectByRtId(long rtId, Param param) throws AppException {
		if(rtId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("rtId", rtId);
			List<ScmPurReturnsEntry2> scmPurReturnsEntryList = ((ScmPurReturnsEntryDAO) dao).selectByRtId(map);
			if(scmPurReturnsEntryList!=null && !scmPurReturnsEntryList.isEmpty()){
				for(ScmPurReturnsEntry2 scmPurReturnsEntry:scmPurReturnsEntryList){
					setConvertMap(scmPurReturnsEntry, param);
				}
			}
			return scmPurReturnsEntryList;
		}
		return null;
	}

	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}

	@Override
	public void deleteByRtId(long rtId, Param param) throws AppException {
		if(rtId > 0){
			List<ScmPurReturnsEntry2> scmPurReturnsEntryList = this.selectByRtId(rtId,param);
			this.delete(scmPurReturnsEntryList, param);
		}
	}

	@Override
	public void updateRowStatusByRtId(long rtId, String status, String checker, Date checkDate, Param param)
			throws AppException {
		if(rtId > 0 && StringUtils.isNotBlank(status)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("rtId", rtId);
			map.put("rowStatus", status);
			map.put("checker", checker);
			map.put("checkDate", checkDate);
			((ScmPurReturnsEntryDAO) dao).updateRowStatusByRtId(map);
		}
	}
	
	@Override
	protected void afterSelect(ScmPurReturnsEntry2 entity, Param param) throws AppException {
		if(entity != null){
			setConvertMap (entity,param);
		}
	}
	
	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		if (list != null && !list.isEmpty()) {
			for (ScmPurReturnsEntry2 scmPurReturnsEntry:(List<ScmPurReturnsEntry2>)list) {
				setConvertMap (scmPurReturnsEntry,param);
			}
		}
	}
	
	private void setConvertMap(ScmPurReturnsEntry2 scmPurReturnsEntry,Param param){
		if (scmPurReturnsEntry != null && scmPurReturnsEntry.getTaxRate() != null){
			//税率
			PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmPurReturnsEntry.getTaxRate().toString(), null, param);
			if (pubSysBasicInfo != null) {
				scmPurReturnsEntry.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
				scmPurReturnsEntry.setConvertMap(ScmPurReturnsEntry2.FN_TAXRATESTR, pubSysBasicInfo);
			}
		}
		if(scmPurReturnsEntry.getWareHouseId()>0){
			ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmPurReturnsEntry.getWareHouseId(), param);
			if(scmInvWareHouse!=null){
				scmPurReturnsEntry.setConvertMap(ScmPurReturnsEntry2.FN_WAREHOUSEID, scmInvWareHouse);
				scmPurReturnsEntry.setWareHouseName(scmInvWareHouse.getWhName());
			}
		}
		if(StringUtils.isNotBlank(scmPurReturnsEntry.getUseOrgUnitNo())){
			OrgAdmin2 orgAdmin = orgAdminBiz.selectByOrgUnitNo(scmPurReturnsEntry.getUseOrgUnitNo(), param);
			if(orgAdmin!=null){
				scmPurReturnsEntry.setConvertMap(ScmPurReturnsEntry2.FN_USEORGUNITNO, orgAdmin);
				scmPurReturnsEntry.setUseOrgUnitName(orgAdmin.getOrgUnitName());
			}
		}
	}
	
	@Override
	protected void afterAdd(ScmPurReturnsEntry2 entity, Param param)
			throws AppException {
		scmPurReceiveEntryBiz.writeBackByPurReturn(null, entity, param);
	}

	@Override
	protected void afterUpdate(ScmPurReturnsEntry2 oldEntity,
			ScmPurReturnsEntry2 newEntity, Param param) throws AppException {
		scmPurReceiveEntryBiz.writeBackByPurReturn(oldEntity, newEntity, param);
	}

	@Override
	protected void afterDelete(ScmPurReturnsEntry2 entity, Param param) throws AppException {
		scmPurReceiveEntryBiz.writeBackByPurReturn(entity, null, param);
	}

	@Override
	public List<ScmPurReturnsEntry2> updateStatus(List<ScmPurReturnsEntry2> scmPurReturnsEntryList, Param param) throws AppException {
		if (scmPurReturnsEntryList != null && !scmPurReturnsEntryList.isEmpty()) {
			List<Long> rtIdList = new ArrayList<>();// 记录需要更新的收货申请
			List<ScmPurReturnsEntry2> scmPurReturnsEntryList2 = new ArrayList<>();
			for (ScmPurReturnsEntry2 scmPurReturnsEntry : scmPurReturnsEntryList) {
				ScmPurReturnsEntry2 scmPurReturnsEntry2 = this.updateDirect(scmPurReturnsEntry, param);
				if (scmPurReturnsEntry2 != null) {
					scmPurReturnsEntryList2.add(scmPurReturnsEntry2);
					if (scmPurReturnsEntry2.getRtId() > 0 && !rtIdList.contains(scmPurReturnsEntry2.getRtId())) {
						rtIdList.add(scmPurReturnsEntry2.getRtId());
					}
				}
			}
			for (long rtId : rtIdList) {
				ScmPurReturns2 scmPurReturns = scmPurReturnsBiz.selectDirect(rtId, param);
				if (scmPurReturns != null) {
					List<ScmPurReturnsEntry2> list = this.selectByRtId(rtId, param);
					if (list != null && !list.isEmpty()) {
						int count1 = 0;// 记录下达条数
						int count2 = 0;// 记录关闭条数
						for (ScmPurReturnsEntry2 returnsEntry : list) {
							if (StringUtils.equals("E", returnsEntry.getRowStatus())) {
								count1++;
							} else if (StringUtils.equals("C", returnsEntry.getRowStatus())) {
								count2++;
							}
						}
						if (count2 > 0) {
							// 关闭条数大于0时，等于总条数时收货单状态为关闭，否则为部分关闭
							if (count2 == list.size()) {
								scmPurReturns.setStatus("C");
							} else {
								scmPurReturns.setStatus("F");
							}
						} else if (count2 == 0 && count1 > 0) {
							// 下达条数大于0时，等于总条数时收货单状态为下达，否则为部分下达
							if (count1 == list.size()) {
								scmPurReturns.setStatus("E");
							} else {
								scmPurReturns.setStatus("S");
							}
						} else if (count2 == 0 && count1 == 0) {
							scmPurReturns.setStatus("A");
						}
						scmPurReturnsBiz.updateDirect(scmPurReturns, param);
					}
				}
			}
			if(scmPurReturnsEntryList2 != null && !scmPurReturnsEntryList2.isEmpty()){
				return scmPurReturnsEntryList2;
			}
		}
		return null;
	}

	@Override
	public void writeBackByInWarehs(ScmInvPurInWarehsBillEntry2 oldEntity,ScmInvPurInWarehsBillEntry2 newEntity, Param param)
			throws AppException {
		BigDecimal qty = BigDecimal.ZERO;
		if(newEntity!=null && newEntity.getSourceBillDtlId()>0){
			qty = qty.add(newEntity.getQty());
		}
		if(oldEntity!=null && oldEntity.getSourceBillDtlId()>0){
			qty = qty.subtract(oldEntity.getQty());
		}
		if(newEntity!=null && newEntity.getSourceBillDtlId()>0 || (oldEntity!=null && oldEntity.getSourceBillDtlId()>0)){
			ScmPurReturnsEntry2 scmPurReturnsEntry = this.selectDirect(newEntity!=null?newEntity.getSourceBillDtlId():oldEntity.getSourceBillDtlId(), param);
			scmPurReturnsEntry.setOutQty(scmPurReturnsEntry.getOutQty().add(qty));
			if(scmPurReturnsEntry.getOutQty().compareTo(scmPurReturnsEntry.getInvQty())>=0){
				scmPurReturnsEntry.setRowStatus("C");
			}else{
				scmPurReturnsEntry.setRowStatus("E");
			}
			this.updateDirect(scmPurReturnsEntry, param);
			this.updateBillStatusByEntry(scmPurReturnsEntry, param);
		}
	}
	
	private void updateBillStatusByEntry(ScmPurReturnsEntry2 scmPurReturnsEntry, Param param) throws AppException {
		if (scmPurReturnsEntry != null) {
			ScmPurReturns2 scmPurReturns = scmPurReturnsBiz.selectDirect(scmPurReturnsEntry.getRtId(), param);
			if (scmPurReturns != null) {
				List<ScmPurReturnsEntry2> list = this.selectByRtId(scmPurReturnsEntry.getRtId(), param);
				if (list != null && !list.isEmpty()) {
					int count1 = 0;// 记录下达条数
					int count2 = 0;// 记录关闭条数
					for (ScmPurReturnsEntry2 purReturnsEntry : list) {
						if (StringUtils.equals("E", purReturnsEntry.getRowStatus())) {
							count1++;
						} else if (StringUtils.equals("C", purReturnsEntry.getRowStatus())) {
							count2++;
						}
					}
					if (count2 > 0) {
						// 关闭条数大于0时，等于总条数时收货单状态为关闭，否则为部分关闭
						if (count2 == list.size()) {
							scmPurReturns.setStatus("C");
						} else {
							scmPurReturns.setStatus("F");
						}
					} else if (count2 == 0 && count1 > 0) {
						// 下达条数大于0时，等于总条数时收货单状态为下达，否则为部分下达
						if (count1 == list.size()) {
							scmPurReturns.setStatus("E");
						} else {
							scmPurReturns.setStatus("S");
						}
					} else if (count2 == 0 && count1 == 0) {
						scmPurReturns.setStatus("A");
					}
					scmPurReturnsBiz.updateDirect(scmPurReturns, param);
				}
			}
		}
	}

	@Override
	public List<ScmPurReturnsEntry2> selectByNotRt(long rtId, Param param) throws AppException {
		if(rtId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("rtId", rtId);
			map.put("notrt", "1");
			List<ScmPurReturnsEntry2> scmPurReturnsEntryList = ((ScmPurReturnsEntryDAO) dao).selectByRtId(map);
			if(scmPurReturnsEntryList!=null && !scmPurReturnsEntryList.isEmpty()){
				for(ScmPurReturnsEntry2 scmPurReturnsEntry:scmPurReturnsEntryList){
					scmPurReturnsEntry.setInvQty(scmPurReturnsEntry.getInvQty().subtract(scmPurReturnsEntry.getOutQty()));
					setConvertMap(scmPurReturnsEntry, param);
				}
			}
			return scmPurReturnsEntryList;
		}
		return null;
	}
}
