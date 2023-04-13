package com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.common.model.ScmAuditDetailHistory2;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrderEntry2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBillEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation2;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPriceBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurQuotationBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderEntryDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntryAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurGroup;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurGroupBiz;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmPurOrderEntryBiz")
public class ScmPurOrderEntryBizImpl extends BaseBizImpl<ScmPurOrderEntry2> implements ScmPurOrderEntryBiz {

	private ScmPurOrderBiz scmPurOrderBiz;
	private ScmPurRequireEntryBiz scmPurRequireEntryBiz;
	private ScmPurBuyerBiz scmPurBuyerBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private OrgStorageBiz orgStorageBiz;
	private OrgAdminBiz orgAdminBiz;
	private ScmPurGroupBiz scmPurGroupBiz;
	private OrgUnitBiz orgUnitBiz;
	private UsrBiz usrBiz;
	private CodeBiz codeBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private ScmPurPriceBiz scmPurPriceBiz;
    private ScmPurQuotationBiz scmPurQuotationBiz;
    private SysParamBiz sysParamBiz;
    
	public void setMyCacheDataMap(HashMap<String, Object> myCacheDataMap) {
		this.myCacheDataMap = myCacheDataMap;
	}

	protected  HashMap<String,Object> myCacheDataMap = new HashMap<String,Object>();
    
	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	public void setScmPurPriceBiz(ScmPurPriceBiz scmPurPriceBiz) {
		this.scmPurPriceBiz = scmPurPriceBiz;
	}

	public void setScmPurQuotationBiz(ScmPurQuotationBiz scmPurQuotationBiz) {
		this.scmPurQuotationBiz = scmPurQuotationBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}

	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}

	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public void setScmPurGroupBiz(ScmPurGroupBiz scmPurGroupBiz) {
		this.scmPurGroupBiz = scmPurGroupBiz;
	}

	public void setScmPurBuyerBiz(ScmPurBuyerBiz scmPurBuyerBiz) {
		this.scmPurBuyerBiz = scmPurBuyerBiz;
	}

	public void setScmPurRequireEntryBiz(ScmPurRequireEntryBiz scmPurRequireEntryBiz) {
		this.scmPurRequireEntryBiz = scmPurRequireEntryBiz;
	}

	public void setScmPurOrderBiz(ScmPurOrderBiz scmPurOrderBiz) {
		this.scmPurOrderBiz = scmPurOrderBiz;
	}
	
	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}
	
	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}

	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}

	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		List<ScmPurOrderEntry2> scmPurOrderEntryList = bean.getList();
		if(scmPurOrderEntryList!=null && !scmPurOrderEntryList.isEmpty()){
			for(ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList){
				setConvertMap(scmPurOrderEntry,param);
			}
		}
	}

	@Override
	protected void afterSelect(ScmPurOrderEntry2 entity, Param param) throws AppException {
		if(entity != null){
			setConvertMap(entity,param);
			setRowAuditRemarks(entity,param);
		}
	}
	
	private void setRowAuditRemarks(ScmPurOrderEntry2 scmPurOrderEntry,Param param){
    	if(scmPurOrderEntry != null && StringUtils.isNotBlank(scmPurOrderEntry.getRowAuditRemarks())){
    		List<ScmAuditDetailHistory2> auditDetailHistoryList = new ArrayList<>();
			StringBuffer rowAuditRemarks = new StringBuffer("");
			String[] rowAuditRemarksList = scmPurOrderEntry.getRowAuditRemarks().split(",");
			for(String auditRemarks : rowAuditRemarksList){
				if (StringUtils.isNotBlank(rowAuditRemarks.toString())){
					rowAuditRemarks.append("；");
				}
				if(auditRemarks.indexOf("^") > 0){
					ScmAuditDetailHistory2 scmAuditDetailHistory = new ScmAuditDetailHistory2(true);
					String oper = auditRemarks.split("\\^")[0];
					String opinion = auditRemarks.split("\\^")[1];
					String operDate = auditRemarks.split("\\^")[2];
					String currrentRemarks = auditRemarks.split("\\^")[3];
					scmAuditDetailHistory.setOper(oper);
					scmAuditDetailHistory.setOperDate(FormatUtils.parseDateTime(operDate));
					scmAuditDetailHistory.setOpinion(opinion);
					scmAuditDetailHistory.setRemarks(currrentRemarks);
					Usr usr = usrBiz.selectByCode(oper, param);
					if(usr != null){
						rowAuditRemarks.append(usr.getName()).append("：");
						scmAuditDetailHistory.setOperName(usr.getName());
					}
					rowAuditRemarks.append(currrentRemarks);
					Code code = codeBiz.selectByCategoryAndCode("SCM_opinion", opinion);
					if(code!=null){
						scmAuditDetailHistory.setOpinionName(code.getName());
					}
					auditDetailHistoryList.add(scmAuditDetailHistory);
				}else{
					//rowAuditRemarks.append(auditRemarks);
				}
			}
			if(StringUtils.isNotBlank(rowAuditRemarks.toString())){
				scmPurOrderEntry.setRowAuditRemarks(rowAuditRemarks.toString());
			}
			scmPurOrderEntry.setAuditDetailHistoryList(auditDetailHistoryList);
		}
    }
	
	private void setConvertMap(ScmPurOrderEntry2 scmPurOrderEntry, Param param) throws AppException {
		if (scmPurOrderEntry != null){
			if (scmPurOrderEntry.getBuyerId() > 0){
				//采购员
				ScmPurBuyer2 scmPurBuyer = (ScmPurBuyer2) myCacheDataMap.get(ClassUtils.getFinalModelSimpleName(ScmPurBuyer2.class)+"_"+scmPurOrderEntry.getBuyerId());
				if(scmPurBuyer==null) {
					scmPurBuyer = scmPurBuyerBiz.selectDirect(scmPurOrderEntry.getBuyerId(), param);
					myCacheDataMap.put(ClassUtils.getFinalModelSimpleName(ScmPurBuyer2.class)+"_"+scmPurOrderEntry.getBuyerId(),scmPurBuyer);
				}
				if (scmPurBuyer != null) {
					scmPurOrderEntry.setBuyerName(scmPurBuyer.getBuyerName());
					scmPurOrderEntry.setConvertMap(ScmPurOrderEntry2.FN_BUYERID, scmPurBuyer);
				}
			}
			//税率
			PubSysBasicInfo pubSysBasicInfo = (PubSysBasicInfo) myCacheDataMap.get(ClassUtils.getFinalModelSimpleName(PubSysBasicInfo.class)+"_"+scmPurOrderEntry.getTaxRate().toString());
			if(pubSysBasicInfo==null) {
				pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmPurOrderEntry.getTaxRate().toString(), null, param);
				myCacheDataMap.put(ClassUtils.getFinalModelSimpleName(PubSysBasicInfo.class)+"_"+scmPurOrderEntry.getTaxRate().toString(),pubSysBasicInfo);
			}
			if (pubSysBasicInfo != null) {
				scmPurOrderEntry.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
				scmPurOrderEntry.setConvertMap(ScmPurOrderEntry2.FN_TAXRATESTR, pubSysBasicInfo);
			}
			if (StringUtils.isNotBlank(scmPurOrderEntry.getReceiveOrgUnitNo())){
				//收货组织
				OrgStorage2 orgStorage = (OrgStorage2) myCacheDataMap.get(ClassUtils.getFinalModelSimpleName(OrgStorage2.class)+"_"+scmPurOrderEntry.getReceiveOrgUnitNo());
				if(orgStorage==null) {
					orgStorage = orgStorageBiz.selectByOrgUnitNo(scmPurOrderEntry.getReceiveOrgUnitNo(), param);
					myCacheDataMap.put(ClassUtils.getFinalModelSimpleName(OrgStorage2.class)+"_"+scmPurOrderEntry.getReceiveOrgUnitNo(),orgStorage);
				}
				if (orgStorage != null) {
					scmPurOrderEntry.setReceiveOrgUnitName(orgStorage.getOrgUnitName());
					scmPurOrderEntry.setConvertMap(ScmPurOrderEntry2.FN_RECEIVEORGUNITNO, orgStorage);
				}
			}
			if (scmPurOrderEntry.getReceiveWareHouseId() > 0){
				//代收仓库
				ScmInvWareHouse scmInvWareHouse = (ScmInvWareHouse) myCacheDataMap.get(ClassUtils.getFinalModelSimpleName(ScmInvWareHouse.class)+"_"+scmPurOrderEntry.getReceiveWareHouseId());
				if(scmInvWareHouse==null) {
					scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmPurOrderEntry.getReceiveWareHouseId(), param);
					myCacheDataMap.put(ClassUtils.getFinalModelSimpleName(ScmInvWareHouse.class)+"_"+scmPurOrderEntry.getReceiveWareHouseId(),scmInvWareHouse);
				}
				if (scmInvWareHouse != null) {
					scmPurOrderEntry.setConvertMap(ScmPurOrderEntry2.FN_RECEIVEWAREHOUSEID, scmInvWareHouse);
				}
			}
			if (StringUtils.isNotBlank(scmPurOrderEntry.getReqOrgUnitNo())){
				OrgAdmin2 orgAdmin = (OrgAdmin2) myCacheDataMap.get(ClassUtils.getFinalModelSimpleName(OrgAdmin2.class)+"_"+scmPurOrderEntry.getReqOrgUnitNo());
				if(orgAdmin==null) {
					orgAdmin = orgAdminBiz.selectByOrgUnitNo(scmPurOrderEntry.getReqOrgUnitNo(), param);
					myCacheDataMap.put(ClassUtils.getFinalModelSimpleName(OrgAdmin2.class)+"_"+scmPurOrderEntry.getReqOrgUnitNo(),orgAdmin);
				}
				if(orgAdmin!=null)
					scmPurOrderEntry.setReqOrgUnitName(orgAdmin.getOrgUnitName());
					scmPurOrderEntry.setConvertMap(ScmPurOrderEntry2.FN_REQORGUNITNO, orgAdmin);
			}
			if (StringUtils.isNotBlank(scmPurOrderEntry.getReceiveFinOrgUnitNo())){
				//收货财务组织
				OrgBaseUnit orgBaseUnit = (OrgBaseUnit) myCacheDataMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmPurOrderEntry.getReceiveFinOrgUnitNo());
				if(orgBaseUnit==null) {
					orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurOrderEntry.getReceiveFinOrgUnitNo(), param);
					myCacheDataMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmPurOrderEntry.getReceiveFinOrgUnitNo(),orgBaseUnit);
				}
				if (orgBaseUnit != null) {
					scmPurOrderEntry.setReceiveFinOrgUnitName(orgBaseUnit.getOrgUnitName());
				}
			}
			if(scmPurOrderEntry.getPriceBillId() > 0 && StringUtils.isNotBlank(scmPurOrderEntry.getRefPriceStatus())){
	        	//价格来源
	        	if("1".equals(scmPurOrderEntry.getRefPriceStatus())){
	        		//报价
	        		ScmPurQuotation2 scmPurQuotation = (ScmPurQuotation2) myCacheDataMap.get(ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class)+"_"+scmPurOrderEntry.getPriceBillId());
	        		if(scmPurQuotation==null) {
	        			scmPurQuotation = scmPurQuotationBiz.selectDirect(scmPurOrderEntry.getPriceBillId(), param);
	        			myCacheDataMap.put(ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class)+"_"+scmPurOrderEntry.getPriceBillId(),scmPurQuotation);
	        		}
		            if (scmPurQuotation != null) {
		            	scmPurOrderEntry.setPriceBillNo(scmPurQuotation.getPqNo());
		            	scmPurOrderEntry.setPriceBillStatus(scmPurQuotation.getStatus());
		            }
	        	}else{
	        		//定价（包括临时定价）
	        		ScmPurPrice2 scmPurPrice = (ScmPurPrice2) myCacheDataMap.get(ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class)+"_"+scmPurOrderEntry.getPriceBillId());
	        		if(scmPurPrice==null) {
	        			scmPurPrice = scmPurPriceBiz.selectDirect(scmPurOrderEntry.getPriceBillId(), param);
	        			myCacheDataMap.put(ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class)+"_"+scmPurOrderEntry.getPriceBillId(),scmPurPrice);
	        		}
		            if (scmPurPrice != null) {
		            	scmPurOrderEntry.setPriceBillNo(scmPurPrice.getPmNo());
		            	scmPurOrderEntry.setPriceBillStatus(scmPurPrice.getStatus());
		            }
	        	}
	        }
			if(scmPurOrderEntry.getItemId()>0){
				ScmPurOrder2 scmPurOrder = (ScmPurOrder2) myCacheDataMap.get(ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class)+"_"+scmPurOrderEntry.getPoId());
				if(scmPurOrder==null) {
					scmPurOrder = scmPurOrderBiz.selectDirect(scmPurOrderEntry.getPoId(),param);
					myCacheDataMap.put(ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class)+"_"+scmPurOrderEntry.getPoId(),scmPurOrder);
				}
				/*if(scmPurOrder != null){
					//获取超收比例
					ScmMaterial2 scmMaterial = scmMaterialBiz.findByPurItemId(param.getControlUnitNo(), scmPurOrder.getOrgUnitNo(), scmPurOrderEntry.getItemId(), param);
					if(scmMaterial!=null)
						scmPurOrderEntry.setSupplyCycle(scmMaterial.getPurSupplyCycle());
				}*/
			}
		}
	}
	
	@Override
	protected void afterFindPage(List list, Page page, Param param)	throws AppException {
		if (list != null && !list.isEmpty()) {
			for (ScmPurOrderEntry2 scmPurOrderEntry:(List<ScmPurOrderEntry2>)list) {
				setConvertMap(scmPurOrderEntry,param);
			}
		}
	}
	
	@Override
	protected void afterAdd(ScmPurOrderEntry2 entity, Param param) throws AppException {
		scmPurRequireEntryBiz.writeBackByPurOrder(null, entity, param);
		scmPurOrderBiz.updateVersion(entity, param);
	}

	@Override
	protected void afterUpdate(ScmPurOrderEntry2 oldEntity,ScmPurOrderEntry2 newEntity, Param param) throws AppException {
		scmPurRequireEntryBiz.writeBackByPurOrder(oldEntity, newEntity, param);
		scmPurOrderBiz.updateVersion(newEntity, param);
	}

	@Override
	protected void afterDelete(ScmPurOrderEntry2 entity, Param param) throws AppException {
		scmPurRequireEntryBiz.writeBackByPurOrder(entity, null, param);
		scmPurOrderBiz.updateVersion(entity, param);
	}

	@Override
	public List<ScmPurOrderEntry2> selectByPoId(long poId, Param param) throws AppException {
		if(poId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("poId", poId);
			List<ScmPurOrderEntry2> scmPurOrderEntryList = ((ScmPurOrderEntryDAO) dao).selectByPoId(map);
			if(scmPurOrderEntryList!=null && !scmPurOrderEntryList.isEmpty()){
				myCacheDataMap = new HashMap<String,Object>();
				for(ScmPurOrderEntry2 scmPurOrderEntry:scmPurOrderEntryList){
					setConvertMap(scmPurOrderEntry, param);
					setRowAuditRemarks(scmPurOrderEntry,param);
				}
			}
			return scmPurOrderEntryList;
		}
		return null;
	}

	@Override
	public void deleteByPoId(long poId, Param param) throws AppException {
		if(poId > 0){
			List<ScmPurOrderEntry2> scmPurOrderEntryList = this.selectByPoId(poId, param);
			this.delete(scmPurOrderEntryList, param);
		}
	}
	
	@Override
	public void updateRowStatusByPoId(long poId, String status, String checker, Date checkDate, Param param) throws AppException {
		if(poId > 0 && StringUtils.isNotBlank(status)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("poId", poId);
			map.put("rowStatus", status);
			map.put("checker", checker);
			map.put("checkDate", checkDate);
			((ScmPurOrderEntryDAO) dao).updateRowStatusByPoId(map);
		}
	}
	
	@Override
	public List<ScmPurOrderEntry2> updateStatus(List<ScmPurOrderEntry2> scmPurOrderEntryList, Param param) throws AppException {
		if (scmPurOrderEntryList != null && !scmPurOrderEntryList.isEmpty()) {
			List<Long> poIdList = new ArrayList<>();// 记录需要更新的订货单
			List<ScmPurOrderEntry2> scmPurOrderEntryList2 = new ArrayList<>();
			for (ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList) {
				ScmPurOrderEntry2 scmPurOrderEntry2 = this.updateDirect(scmPurOrderEntry, param);
				if (scmPurOrderEntry2 != null) {
					scmPurOrderEntryList2.add(scmPurOrderEntry2);
					if (scmPurOrderEntry2.getPoId() > 0 && !poIdList.contains(scmPurOrderEntry2.getPoId())) {
						poIdList.add(scmPurOrderEntry2.getPoId());
					}
				}
			}
			for (long poId : poIdList) {
				ScmPurOrder2 scmPurOrder = scmPurOrderBiz.selectDirect(poId, param);
				if (scmPurOrder != null) {
					List<ScmPurOrderEntry2> list = this.selectByPoId(poId, param);
					if (list != null && !list.isEmpty()) {
						int count1 = 0;// 记录下达条数
						int count2 = 0;// 记录关闭条数
						for (ScmPurOrderEntry2 scmPurOrderEntry : list) {
							if (StringUtils.equals("E", scmPurOrderEntry.getRowStatus())) {
								count1++;
							} else if (StringUtils.equals("C", scmPurOrderEntry.getRowStatus())) {
								count2++;
							}
						}
						if (count2 > 0) {
							// 关闭条数大于0时，等于总条数时订货单状态为关闭，否则为部分关闭
							if (count2 == list.size()) {
								scmPurOrder.setStatus("C");
							} else {
								scmPurOrder.setStatus("F");
							}
						} else if (count2 == 0 && count1 > 0) {
							// 下达条数大于0时，等于总条数时订货单状态为下达，否则为部分下达
							if (count1 == list.size()) {
								scmPurOrder.setStatus("E");
							} else {
								scmPurOrder.setStatus("S");
							}
						} else if (count2 == 0 && count1 == 0) {
							scmPurOrder.setStatus("A");
						}
						scmPurOrderBiz.updateDirect(scmPurOrder, param);
					}
				}
			}
			if(scmPurOrderEntryList2 != null && !scmPurOrderEntryList2.isEmpty()){
				return scmPurOrderEntryList2;
			}
		}
		return null;
	}

	@Override
	public void updateBillStatusByEntry(ScmPurOrderEntry2 scmPurOrderEntry, Param param) throws AppException {
		if (scmPurOrderEntry != null ) {
			ScmPurOrder2 scmPurOrder = scmPurOrderBiz.selectDirect(scmPurOrderEntry.getPoId(), param);
			if (scmPurOrder != null) {
				List<ScmPurOrderEntry2> list = this.selectByPoId(scmPurOrderEntry.getPoId(), param);
				if (list != null && !list.isEmpty()) {
					int count1 = 0;// 记录下达条数
					int count2 = 0;// 记录关闭条数
					for (ScmPurOrderEntry2 entry : list) {
						if (StringUtils.equals("E", entry.getRowStatus())) {
							count1++;
						} else if (StringUtils.equals("C", entry.getRowStatus())) {
							count2++;
						}
					}
					if (count2 > 0) {
						// 关闭条数大于0时，等于总条数时订货单状态为关闭，否则为部分关闭
						if (count2 == list.size()) {
							scmPurOrder.setStatus("C");
						} else {
							scmPurOrder.setStatus("F");
						}
					} else if (count2 == 0 && count1 > 0) {
						// 下达条数大于0时，等于总条数时订货单状态为下达，否则为部分下达
						if (count1 == list.size()) {
							scmPurOrder.setStatus("E");
							//scmPurOrder.setSended(false);
						} else {
							scmPurOrder.setStatus("S");
						}
					} else if (count2 == 0 && count1 == 0) {
						scmPurOrder.setStatus("A");
					}
					scmPurOrderBiz.updateBillNoChangeTime(scmPurOrder, param);
				}
			}
		}
	}
	
	@Override
	public CommonBean getDataForLeadInto(ScmPurOrderEntryAdvQuery scmPurOrderEntryAdvQuery, Param param)
			throws AppException {
		CommonBean bean = new CommonBean();
		if(scmPurOrderEntryAdvQuery != null){
			//1、先查询符合条件的订货单
			Page page = new Page();
			page.setModelClass(ScmPurOrder2.class);
			page.setShowCount(Integer.MAX_VALUE);
			if(scmPurOrderEntryAdvQuery.getBegBillDate() != null && scmPurOrderEntryAdvQuery.getEndBillDate() != null){
				page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrder.class) + "." + ScmPurOrder.FN_CREATEDATE), 
						new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrder.class) + "." + ScmPurOrder.FN_CREATEDATE), QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(scmPurOrderEntryAdvQuery.getBegBillDate()),
								FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurOrderEntryAdvQuery.getEndBillDate(),1))));
			}else if(scmPurOrderEntryAdvQuery.getBegBillDate() != null){
				page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrder.class) + "." + ScmPurOrder.FN_CREATEDATE), 
						new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrder.class) + "." + ScmPurOrder.FN_CREATEDATE), QueryParam.QUERY_GE, FormatUtils.fmtDate(scmPurOrderEntryAdvQuery.getBegBillDate())));
			}else if(scmPurOrderEntryAdvQuery.getEndBillDate() != null){
				page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrder.class) + "." + ScmPurOrder.FN_CREATEDATE), 
						new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrder.class) + "." + ScmPurOrder.FN_CREATEDATE), QueryParam.QUERY_LE, FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurOrderEntryAdvQuery.getEndBillDate(),1))));
			}
			if(StringUtils.isNotBlank(scmPurOrderEntryAdvQuery.getEndPoNo())){
				if(!((scmPurOrderEntryAdvQuery.getEndPoNo()).startsWith("PO") && (scmPurOrderEntryAdvQuery.getEndPoNo()).length() >= 13)){
					return bean;
				}
				if(StringUtils.isNotBlank(scmPurOrderEntryAdvQuery.getBegPoNo()) 
						&& ((scmPurOrderEntryAdvQuery.getBegPoNo()).startsWith("PO") && (scmPurOrderEntryAdvQuery.getBegPoNo()).length() >= 13)){
					page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrder.class) + "." + ScmPurOrder.FN_PONO), 
							new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrder.class) + "." + ScmPurOrder.FN_PONO), QueryParam.QUERY_BETWEEN, scmPurOrderEntryAdvQuery.getBegPoNo(),
									scmPurOrderEntryAdvQuery.getEndPoNo()));
				}else{
					page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrder.class) + "." + ScmPurOrder.FN_PONO), 
							new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrder.class) + "." + ScmPurOrder.FN_PONO), QueryParam.QUERY_LE, scmPurOrderEntryAdvQuery.getEndPoNo()));
				}
			}else if(StringUtils.isNotBlank(scmPurOrderEntryAdvQuery.getBegPoNo()) 
					&& ((scmPurOrderEntryAdvQuery.getBegPoNo()).startsWith("PO") && (scmPurOrderEntryAdvQuery.getBegPoNo()).length() >= 13)){
				page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrder.class) + "." + ScmPurOrder.FN_PONO), 
						new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrder.class) + "." + ScmPurOrder.FN_PONO), QueryParam.QUERY_GE, scmPurOrderEntryAdvQuery.getBegPoNo()));
			}
			if(scmPurOrderEntryAdvQuery.getVendorId() > 0){
				page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrder.class) + "." + ScmPurOrder.FN_VENDORID), 
						new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrder.class) + "." + ScmPurOrder.FN_VENDORID), QueryParam.QUERY_EQ, String.valueOf(scmPurOrderEntryAdvQuery.getVendorId())));
			}
			if(StringUtils.isNotBlank(scmPurOrderEntryAdvQuery.getPurOrgUnitNo())){
				param.setOrgUnitNo(scmPurOrderEntryAdvQuery.getPurOrgUnitNo());
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_SENDED), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_SENDED),QueryParam.QUERY_EQ,String.valueOf(1)));
			List<ScmPurOrder2> scmPurOrderList = scmPurOrderBiz.findPage(page, param);
			StringBuffer poIdstr = new StringBuffer("");
			if(scmPurOrderList != null && !scmPurOrderList.isEmpty()){
				for(ScmPurOrder scmPurOrder : scmPurOrderList){
					if (StringUtils.isEmpty(poIdstr.toString())){
						poIdstr.append("'").append(scmPurOrder.getId()).append("'");
					}else{
						poIdstr.append(",").append("'").append(scmPurOrder.getId()).append("'");
					}
				}
			}
			//2、根据符合条件的订货单和原有条件查询订货明细
			if(StringUtils.isNotBlank(poIdstr.toString())){
				Page page2 = new Page();
				page2.setModelClass(ScmPurOrderEntry2.class);
				page2.setShowCount(Integer.MAX_VALUE);
				page2.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrderEntry2.class) + "." + ScmPurOrderEntry2.FN_ROWSTATUS), 
						new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrderEntry2.class) + "." + ScmPurOrderEntry2.FN_ROWSTATUS), QueryParam.QUERY_EQ, "E"));
				page2.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrderEntry2.class) + "." + ScmPurOrderEntry2.FN_POID), 
						new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrderEntry2.class) + "." + ScmPurOrderEntry2.FN_POID), QueryParam.QUERY_IN, poIdstr.toString()));
				if(scmPurOrderEntryAdvQuery.getBuyerId() > 0){
					page2.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrderEntry2.class) + "." + ScmPurOrderEntry2.FN_BUYERID), 
							new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrderEntry2.class) + "." + ScmPurOrderEntry2.FN_BUYERID), QueryParam.QUERY_EQ, String.valueOf(scmPurOrderEntryAdvQuery.getBuyerId())));
				}
				if(scmPurOrderEntryAdvQuery.getItemId() > 0){
					page2.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrderEntry2.class) + "." + ScmPurOrderEntry2.FN_ITEMID), 
							new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrderEntry2.class) + "." + ScmPurOrderEntry2.FN_ITEMID), QueryParam.QUERY_EQ, String.valueOf(scmPurOrderEntryAdvQuery.getItemId())));
				}
				if(StringUtils.isNotBlank(scmPurOrderEntryAdvQuery.getOrgUnitNo())){
					page2.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrderEntry.class) + "." + ScmPurOrderEntry.FN_REQORGUNITNO), 
							new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrderEntry.class) + "." + ScmPurOrderEntry.FN_REQORGUNITNO), QueryParam.QUERY_EQ, scmPurOrderEntryAdvQuery.getOrgUnitNo()));
				}
				if(StringUtils.isNotBlank(scmPurOrderEntryAdvQuery.getPurOrgUnitNo())){
					page2.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrder.class) + "." + ScmPurOrder.FN_ORGUNITNO), 
							new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrder.class) + "." + ScmPurOrder.FN_ORGUNITNO), QueryParam.QUERY_EQ, scmPurOrderEntryAdvQuery.getPurOrgUnitNo()));
				}
				if(StringUtils.isNotBlank(scmPurOrderEntryAdvQuery.getInvOrgUnitNo())){
					page2.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrderEntry.class) + "." + ScmPurOrderEntry.FN_RECEIVEORGUNITNO), 
							new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrderEntry.class) + "." + ScmPurOrderEntry.FN_RECEIVEORGUNITNO), QueryParam.QUERY_EQ, scmPurOrderEntryAdvQuery.getInvOrgUnitNo()));
				}
				page2.setSqlCondition("ScmPurOrderEntry.baseQty>ScmPurOrderEntry.receiveQty");
				List<ScmPurOrderEntry2> scmPurOrderEntryList = this.findPage(page2, param);
				List<Long> poIdList = new ArrayList<>();
				if(scmPurOrderEntryList != null && !scmPurOrderEntryList.isEmpty()){
					for(int i=scmPurOrderEntryList.size()-1; i>=0; i--){
						scmPurOrderEntryList.get(i).setReceiveQty(scmPurOrderEntryList.get(i).getReceiveQty().divide(scmPurOrderEntryList.get(i).getConvrate(), 2,RoundingMode.HALF_UP));
						if (scmPurOrderEntryList.get(i).getPoId() > 0 && !poIdList.contains(scmPurOrderEntryList.get(i).getPoId())) {
							poIdList.add(scmPurOrderEntryList.get(i).getPoId());
						}
					}
				}
				//3、根据查询到的订货明细过滤订货单
				if(poIdList != null && !poIdList.isEmpty()
						&& scmPurOrderList != null && !scmPurOrderList.isEmpty()){
					for(int i=scmPurOrderList.size()-1; i>=0; i--){
						boolean flag = false;
						for(int j=poIdList.size()-1; j>=0; j--){
							if(poIdList.get(j) == scmPurOrderList.get(i).getId()){
								flag = true;
								break;
							}
						}
						if(!flag){
							scmPurOrderList.remove(i);
						}
					}
					if(scmPurOrderEntryList != null && !scmPurOrderEntryList.isEmpty()
							&& scmPurOrderList != null && !scmPurOrderList.isEmpty()){
						bean.setList(scmPurOrderList);
						bean.setList2(scmPurOrderEntryList);
						return bean;
					}
				}
			}
		}
		return bean;
	}

	@Override
	public void writeBackByPurReceive(ScmPurReceiveEntry2 oldEntity,ScmPurReceiveEntry2 newEntity, Param param) throws AppException {
		BigDecimal qty = BigDecimal.ZERO;
		if(newEntity!=null && newEntity.getPoDtlId()>0){
			qty = qty.add(newEntity.getBaseQty());
		}
		if(oldEntity!=null && oldEntity.getPoDtlId()>0){
			qty = qty.subtract(oldEntity.getBaseQty());
		}
		if(newEntity!=null && newEntity.getPoDtlId()>0 || (oldEntity!=null && oldEntity.getPoDtlId()>0)){
			ScmPurOrderEntry2 scmPurOrderEntry = this.selectDirect(newEntity!=null?newEntity.getPoDtlId():oldEntity.getPoDtlId(), param);
			scmPurOrderEntry.setReceiveQty(scmPurOrderEntry.getReceiveQty().add(qty));
			if((scmPurOrderEntry.getReceiveQty().add(scmPurOrderEntry.getMovedQty().add(scmPurOrderEntry.getReturnQty()))).compareTo(scmPurOrderEntry.getBaseQty())>=0){
				scmPurOrderEntry.setRowStatus("C");
			}else{
				scmPurOrderEntry.setRowStatus("E");
			}
			this.updateDirect(scmPurOrderEntry, param);
			this.updateBillStatusByEntry(scmPurOrderEntry, param);
		}
	}

	@Override
	public List<ScmPurOrderEntry2> selectByPoIdAndSaleIssueBill(long poId,
			long otId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("poId", poId);
		map.put("otId", otId);
		return ((ScmPurOrderEntryDAO) dao).selectByPoIdAndSaleIssueBill(map);
	}

	@Override
	public void writeBackBySaleOrder(ScmInvSaleOrderEntry2 oldEntity,
			ScmInvSaleOrderEntry2 newEntity, Param param) throws AppException {
		BigDecimal qty = BigDecimal.ZERO;
		if (newEntity != null && newEntity.getSourceBillDtlId() > 0) {
			qty = qty.add(newEntity.getBaseQty());
		}
		if (oldEntity != null && oldEntity.getSourceBillDtlId() > 0) {
			qty = qty.subtract(oldEntity.getBaseQty());
		}
		if ((newEntity != null && newEntity.getSourceBillDtlId() > 0) || (oldEntity != null && oldEntity.getSourceBillDtlId() > 0)) {
			ScmPurOrderEntry2 scmPurOrderEntry = this.selectDirect(newEntity != null ? newEntity.getSourceBillDtlId(): oldEntity.getSourceBillDtlId(), param);
			scmPurOrderEntry.setMovedQty(scmPurOrderEntry.getMovedQty().add(qty));
			if((scmPurOrderEntry.getMovedQty().add(scmPurOrderEntry.getReceiveQty().add(scmPurOrderEntry.getReturnQty()))).compareTo(scmPurOrderEntry.getBaseQty()) >= 0){
				scmPurOrderEntry.setRowStatus("C");
			} else {
				scmPurOrderEntry.setRowStatus("E");
			}
			this.updateDirect(scmPurOrderEntry, param);
			this.updateBillStatusByEntry(scmPurOrderEntry, param);
		}
	}

	@Override
	public List<ScmPurOrderEntry2> selectByNotSend(long poId, Param param) throws AppException {
		if(poId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("poId", poId);
			map.put("notsend", "1");
			List<ScmPurOrderEntry2> scmPurOrderEntryList = ((ScmPurOrderEntryDAO) dao).selectByPoId(map);
			if(scmPurOrderEntryList!=null && !scmPurOrderEntryList.isEmpty()){
				for(ScmPurOrderEntry2 scmPurOrderEntry:scmPurOrderEntryList){
					BigDecimal convRate = ScmMaterialUtil.getConvRate(scmPurOrderEntry.getItemId(), scmPurOrderEntry.getPurUnit(), param);
					scmPurOrderEntry.setQty((scmPurOrderEntry.getBaseQty().subtract(scmPurOrderEntry.getReceiveQty())).divide(convRate));
					setConvertMap(scmPurOrderEntry, param);
				}
			}
			return scmPurOrderEntryList;
		}
		return null;	}

	@Override
	public void writeBackInvQty(ScmPurReceive2 scmPurReceive,int sign, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("pvId", scmPurReceive.getId());
		map.put("sign", sign);
		((ScmPurOrderEntryDAO) dao).writeBackInvQty(map);
		List<ScmPurOrderEntry2> scmPurOrderEntryList = ((ScmPurOrderEntryDAO) dao).selectByPvId(map);
		if(scmPurOrderEntryList!=null && !scmPurOrderEntryList.isEmpty())
			this.updateBillStatusByEntry(scmPurOrderEntryList.get(0), param);
	}

	@Override
	public void writeBackByOtherIssue(ScmInvOtherIssueBillEntry2 oldEntity, ScmInvOtherIssueBillEntry2 newEntity,
			Param param) throws AppException {
		BigDecimal qty = BigDecimal.ZERO;
		if (newEntity != null && newEntity.getSourceBillDtlId() > 0) {
			qty = qty.add(newEntity.getBaseQty());
		}
		if (oldEntity != null && oldEntity.getSourceBillDtlId() > 0) {
			qty = qty.subtract(oldEntity.getBaseQty());
		}
		if ((newEntity != null && newEntity.getSourceBillDtlId() > 0) || (oldEntity != null && oldEntity.getSourceBillDtlId() > 0)) {
			ScmPurOrderEntry2 scmPurOrderEntry = this.selectDirect(newEntity != null ? newEntity.getSourceBillDtlId(): oldEntity.getSourceBillDtlId(), param);
			if(scmPurOrderEntry!=null) {
				scmPurOrderEntry.setMovedQty(scmPurOrderEntry.getMovedQty().add(qty));
				if((scmPurOrderEntry.getMovedQty().add(scmPurOrderEntry.getReceiveQty().add(scmPurOrderEntry.getReturnQty()))).compareTo(scmPurOrderEntry.getBaseQty()) >= 0){
					scmPurOrderEntry.setRowStatus("C");
				} else {
					scmPurOrderEntry.setRowStatus("E");
				}
				this.updateDirect(scmPurOrderEntry, param);
				this.updateBillStatusByEntry(scmPurOrderEntry, param);
			}
		}
	}

	@Override
	public List<ScmPurOrderEntry2> selectByPoIdAndOtherIssueBill(long poId, long otId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("poId", poId);
		map.put("otId", otId);
		return ((ScmPurOrderEntryDAO) dao).selectByPoIdAndOtherIssueBill(map);
	}

	@Override
	public List<ScmPurOrderEntry2> selectStatusByIds(String ids, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("ids", ids);
		return ((ScmPurOrderEntryDAO) dao).selectStatusByIds(map);
	}

	@Override
	public void updateRowStatusByLineId(long poId, String status,
			String checker, Date checkDate, int lineId, Param param)
			throws AppException {
		if (poId > 0 && StringUtils.isNotBlank(status)) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("poId", poId);
            map.put("rowStatus", status);
            map.put("checker", checker);
            map.put("checkDate", checkDate);
            map.put("lineId", lineId);
            ((ScmPurOrderEntryDAO) dao).updateRowStatusByLineId(map);
        }
	}

	@Override
	public List<ScmPurOrderEntry2> selectByPoIdOA(long poId, Param param) throws AppException {
		if(poId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("poId", poId);
			List<ScmPurOrderEntry2> scmPurOrderEntryList = ((ScmPurOrderEntryDAO) dao).selectByPoId(map);
			if(scmPurOrderEntryList!=null && !scmPurOrderEntryList.isEmpty()){
				HashMap<String,Object> cacheMap = new HashMap<String,Object>();
				for(ScmPurOrderEntry2 scmPurOrderEntry:scmPurOrderEntryList){
					setConvertMap(scmPurOrderEntry, param);
					if (StringUtils.isNotBlank(scmPurOrderEntry.getReqStorageOrgUnitNo())){
						//需求方库存组织
						OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmPurOrderEntry.getReqStorageOrgUnitNo());
						if(orgBaseUnit==null){
							orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurOrderEntry.getReqStorageOrgUnitNo(), param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmPurOrderEntry.getReqStorageOrgUnitNo(),orgBaseUnit);
						}
						if (orgBaseUnit != null) {
							scmPurOrderEntry.setReqStorageOrgUnitName(orgBaseUnit.getOrgUnitName());
						}
					}
					if (StringUtils.isNotBlank(scmPurOrderEntry.getReqFinOrgUnitNo())){
						//需求方财务组织
						OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmPurOrderEntry.getReqFinOrgUnitNo());
						if(orgBaseUnit==null){
							orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurOrderEntry.getReqFinOrgUnitNo(), param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmPurOrderEntry.getReqFinOrgUnitNo(),orgBaseUnit);
						}
						if (orgBaseUnit != null) {
							scmPurOrderEntry.setReqFinOrgUnitName(orgBaseUnit.getOrgUnitName());
						}
					}
					if (StringUtils.isNotBlank(scmPurOrderEntry.getReceiveFinOrgUnitNo())){
						//收货财务组织
						OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmPurOrderEntry.getReceiveFinOrgUnitNo());
						if(orgBaseUnit==null){
							orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurOrderEntry.getReceiveFinOrgUnitNo(), param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmPurOrderEntry.getReceiveFinOrgUnitNo(),orgBaseUnit);
						}
						if (orgBaseUnit != null) {
							scmPurOrderEntry.setReceiveFinOrgUnitName(orgBaseUnit.getOrgUnitName());
						}
					}
					if (StringUtils.isNotBlank(scmPurOrderEntry.getMstorageOrgUnitNo())){
						//代收库存组织
						OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmPurOrderEntry.getMstorageOrgUnitNo());
						if(orgBaseUnit==null){
							orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurOrderEntry.getMstorageOrgUnitNo(), param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmPurOrderEntry.getMstorageOrgUnitNo(),orgBaseUnit);
						}
						if (orgBaseUnit != null) {
							scmPurOrderEntry.setMstorageOrgUnitName(orgBaseUnit.getOrgUnitName());
						}
					}
					if (StringUtils.isNotBlank(scmPurOrderEntry.getStorageOrgUnitNo())){
						//虚拟出库组织
						OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmPurOrderEntry.getStorageOrgUnitNo());
						if(orgBaseUnit==null){
							orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurOrderEntry.getStorageOrgUnitNo(), param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmPurOrderEntry.getStorageOrgUnitNo(),orgBaseUnit);
						}
						if (orgBaseUnit != null) {
							scmPurOrderEntry.setStorageOrgUnitName(orgBaseUnit.getOrgUnitName());
						}
					}
					if (scmPurOrderEntry.getReceiveWareHouseId() > 0){
						//代收仓库
						ScmInvWareHouse scmInvWareHouse = (ScmInvWareHouse) cacheMap.get(ClassUtils.getFinalModelSimpleName(ScmInvWareHouse.class)+"_"+scmPurOrderEntry.getReceiveWareHouseId());
						if(scmInvWareHouse==null){
							scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmPurOrderEntry.getReceiveWareHouseId(), param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(ScmInvWareHouse.class)+"_"+scmPurOrderEntry.getReceiveWareHouseId(),scmInvWareHouse);
						}
						if (scmInvWareHouse != null) {
							scmPurOrderEntry.setBalanceSupplierName(scmInvWareHouse.getWhName());
						}
					}
					if (scmPurOrderEntry.getBalanceSupplierId() > 0){
						//结算供应商
						Scmsupplier2 scmsupplier = (Scmsupplier2) cacheMap.get(ClassUtils.getFinalModelSimpleName(Scmsupplier2.class)+"_"+scmPurOrderEntry.getBalanceSupplierId());
						if(scmsupplier==null){
							scmsupplier = scmsupplierBiz.selectDirect(scmPurOrderEntry.getBalanceSupplierId(), param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(Scmsupplier2.class)+"_"+scmPurOrderEntry.getBalanceSupplierId(),scmsupplier);
						}
						if (scmsupplier != null) {
							scmPurOrderEntry.setBalanceSupplierName(scmsupplier.getVendorName());
						}
					}
					if (scmPurOrderEntry.getPurGroupId() > 0){
						//采购组
						ScmPurGroup scmPurGroup = (ScmPurGroup) cacheMap.get(ClassUtils.getFinalModelSimpleName(ScmPurGroup.class)+"_"+scmPurOrderEntry.getPurGroupId());
						if(scmPurGroup==null){
							scmPurGroup = scmPurGroupBiz.selectDirect(scmPurOrderEntry.getPurGroupId(), param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(ScmPurGroup.class)+"_"+scmPurOrderEntry.getPurGroupId(),scmPurGroup);
						}
						if (scmPurGroup != null) {
							scmPurOrderEntry.setPurGroupName(scmPurGroup.getPurGroupName());
						}
					}
					if(StringUtils.isNotBlank(scmPurOrderEntry.getRowStatus())){
						Code code = codeBiz.selectByCategoryAndCode("quotationStatus", scmPurOrderEntry.getRowStatus());
						if(code!=null)
							scmPurOrderEntry.setRowStatusName(code.getName());
					}
					if (StringUtils.isNotBlank(scmPurOrderEntry.getChecker())){
						//审核人
						Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmPurOrderEntry.getChecker());
						if(usr==null){
							usr = usrBiz.selectByCode(scmPurOrderEntry.getChecker(), param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmPurOrderEntry.getChecker(),usr);
						}
						if (usr != null) {
							scmPurOrderEntry.setCheckerName(usr.getName());
						}
					}
				}
			}
			return scmPurOrderEntryList;
		}
		return null;
	}

	@Override
	public void release(ScmPurOrder2 scmPurOrder, Param param) throws AppException {
		if(scmPurOrder!=null) {
			List<ScmPurOrderEntry2> scmPurOrderEntryList = this.selectByPoId(scmPurOrder.getId(), param);
			if(scmPurOrderEntryList!=null && !scmPurOrderEntryList.isEmpty()) {
				for(ScmPurOrderEntry2 scmPurOrderEntry:scmPurOrderEntryList) {
					if(StringUtils.equals("A", scmPurOrderEntry.getRowStatus())) {
						scmPurOrderEntry.setRowStatus("E");
						this.updateDirect(scmPurOrderEntry, param);
					}
				}
			}
		}
	}

	@Override
	public void undoRelease(ScmPurOrder2 scmPurOrder, Param param) throws AppException {
		if(scmPurOrder!=null) {
			List<ScmPurOrderEntry2> scmPurOrderEntryList = this.selectByPoId(scmPurOrder.getId(), param);
			if(scmPurOrderEntryList!=null && !scmPurOrderEntryList.isEmpty()) {
				for(ScmPurOrderEntry2 scmPurOrderEntry:scmPurOrderEntryList) {
					if(StringUtils.equals("E", scmPurOrderEntry.getRowStatus())) {
						scmPurOrderEntry.setRowStatus("A");
						this.updateDirect(scmPurOrderEntry, param);
					}
				}
			}
		}
	}
	
	@Override
	public CommonBean generateTempPrice(
			List<ScmPurOrderEntry2> scmPurOrderEntryList, Param param)
			throws AppException {
		CommonBean bean = new CommonBean();
        List<ScmPurPrice2> scmPurPriceList = new ArrayList();
        ScmPurPrice2 scmPurPrice = new ScmPurPrice2(true);
        scmPurPrice.setBizType("2");
        scmPurPrice.setPmDate(CalendarUtil.getDate(param));
        scmPurPrice.setBegDate(null);
        scmPurPrice.setEndDate(null);
        scmPurPrice.setOrgUnitNo(param.getOrgUnitNo());
        scmPurPrice.setSelVndrId(0);
        scmPurPrice.setVendorId1(0);
        scmPurPrice.setVendorId2(0);
        scmPurPrice.setVendorId3(0);
        scmPurPrice.setGroupId1(0);
        scmPurPrice.setGroupId2(0);
        scmPurPrice.setGroupId3(0);
        scmPurPrice.setCurrencyNo("RMB");
        scmPurPrice.setCreator(param.getUsrCode());
        scmPurPrice.setCreateDate(CalendarUtil.getDate(param));
        scmPurPrice.setStatus("I");
        scmPurPrice.setRemarks("");
        scmPurPrice.setControlUnitNo(param.getControlUnitNo());
        
        int count = 0;
        HashMap<String, String> itemMap = new HashMap<>();
        List<ScmPurPriceEntry2> scmPurPriceEntryList = new ArrayList<ScmPurPriceEntry2>();
        for(ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList) {
        	if (StringUtils.contains("C,E", scmPurOrderEntry.getRowStatus())) {
				throw new AppException("iscm.purchasemanage.pricemanage.ScmPurPrice.validateRowStatus3",new String[]{String.valueOf(scmPurOrderEntry.getLineId())});
			}
        	long itemId = scmPurOrderEntry.getItemId();
        	if (!itemMap.containsKey(String.valueOf(itemId))) {
        		ScmPurPriceEntry2 scmPurPriceEntry = new ScmPurPriceEntry2(true);
	            scmPurPriceEntry.setItemId(scmPurOrderEntry.getItemId());
	            scmPurPriceEntry.setItemNo(scmPurOrderEntry.getItemNo());
	            scmPurPriceEntry.setItemName(scmPurOrderEntry.getItemName());
	            scmPurPriceEntry.setSpec(scmPurOrderEntry.getSpec());
	            scmPurPriceEntry.setPurUnit(scmPurOrderEntry.getPurUnit());
	            scmPurPriceEntry.setPurUnitName(scmPurOrderEntry.getPurUnitName());
	            scmPurPriceEntry.setSelVndrId(0);
	            scmPurPriceEntry.setRowStatus("I");
	            scmPurPriceEntry.setRemarks(scmPurOrderEntry.getRemarks());
	            scmPurPriceEntryList.add(scmPurPriceEntry);
	            itemMap.put(String.valueOf(itemId), String.valueOf(count));
        	}
        }
        
        scmPurPrice.setScmPurPriceEntryList(scmPurPriceEntryList);
        scmPurPriceList.add(scmPurPrice);
        bean.setList(scmPurPriceList);
        bean.setList2(scmPurPriceEntryList);
        return bean;
	}

	@Override
	public List<ScmPurOrderEntry2> selectByPriceId(long priceId, Param param) throws AppException {
		if(priceId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("priceId", priceId);
			List<ScmPurOrderEntry2> scmPurOrderEntryList= ((ScmPurOrderEntryDAO) dao).selectByPriceId(map);
			if(scmPurOrderEntryList!=null && !scmPurOrderEntryList.isEmpty()){
				return scmPurOrderEntryList;
			}
		}
		return null;
	}

	@Override
	public void updatePurOrderEntryByPmId(ScmPurPrice2 scmPurPrice, Param param) throws AppException {
		if (scmPurPrice != null) {
            List<ScmPurOrderEntry2> scmPurOrderEntryList = this.selectByPriceId(scmPurPrice.getId(), param);
            if(scmPurOrderEntryList != null && !scmPurOrderEntryList.isEmpty()){
            	for(ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList){
            		if(!StringUtils.contains("6,7",scmPurOrderEntry.getRefPriceStatus())){
            			continue;
            		}
            		ScmMaterialPrice scmMaterialPrice = ScmMaterialUtil.getMaterialPrice(
            				scmPurOrderEntry.getPurOrgUnitNo(), scmPurOrderEntry.getVendorId(),
                            String.valueOf(scmPurOrderEntry.getItemId()), scmPurOrderEntry.getPurUnit(),
                            scmPurOrderEntry.getPurOrderDate(), 
                            scmPurOrderEntry.getReqStorageOrgUnitNo(), String.valueOf(scmPurOrderEntry.getPriceBillId()), param);
                    
                    if (scmMaterialPrice != null) {
                    	if (StringUtils.contains("6,7",scmMaterialPrice.getRefPriceStatus())) {
                    		scmPurOrderEntry.setPrice(scmMaterialPrice.getPrice());
                            scmPurOrderEntry
                                    .setAmt(scmPurOrderEntry.getQty().multiply(scmMaterialPrice.getPrice()));
                            scmPurOrderEntry.setTaxRate(scmMaterialPrice.getTaxRate());
                            scmPurOrderEntry.setTaxPrice(scmMaterialPrice.getTaxPrice());
                            scmPurOrderEntry.setTaxAmt(
                                    scmPurOrderEntry.getQty().multiply(scmMaterialPrice.getTaxPrice()));
                            scmPurOrderEntry.setPriceBillId(scmMaterialPrice.getPriceBillId());
                            scmPurOrderEntry.setRefPriceStatus(scmMaterialPrice.getRefPriceStatus());
                    	} else {
                    		//临时定价单还未下达
                            scmPurOrderEntry.setPrice(BigDecimal.ZERO);
                            scmPurOrderEntry.setAmt(BigDecimal.ZERO);
                            scmPurOrderEntry.setTaxRate(BigDecimal.ZERO);
                            scmPurOrderEntry.setTaxPrice(BigDecimal.ZERO);
                            scmPurOrderEntry.setTaxAmt(BigDecimal.ZERO);
                            scmPurOrderEntry.setPriceBillId(scmPurOrderEntry.getPriceBillId());
                            scmPurOrderEntry.setRefPriceStatus(scmPurOrderEntry.getRefPriceStatus());
                    	}
                    } else{
                    	//临时定价单还未下达
                		scmPurOrderEntry.setPrice(BigDecimal.ZERO);
                        scmPurOrderEntry.setAmt(BigDecimal.ZERO);
                        scmPurOrderEntry.setTaxRate(BigDecimal.ZERO);
                        scmPurOrderEntry.setTaxPrice(BigDecimal.ZERO);
                        scmPurOrderEntry.setTaxAmt(BigDecimal.ZERO);
                        scmPurOrderEntry.setPriceBillId(scmPurOrderEntry.getPriceBillId());
                        scmPurOrderEntry.setRefPriceStatus(scmPurOrderEntry.getRefPriceStatus());
                    }
                    this.update(scmPurOrderEntry, param);
            	}
            	ScmPurOrder2 scmPurOrder = scmPurOrderBiz.select(scmPurOrderEntryList.get(0).getPoId(),param);
            	if(scmPurOrder != null){
            		List<ScmPurOrderEntry2> scmPurOrderEntryList2 = this.selectByPoId(scmPurOrderEntryList.get(0).getPoId(), param);
                	if(scmPurOrderEntryList2 != null && !scmPurOrderEntryList2.isEmpty()){
                		int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
                		BigDecimal amt = BigDecimal.ZERO;
                		BigDecimal taxAmt = BigDecimal.ZERO;
                		for(ScmPurOrderEntry2 scmPurOrderEntry2 : scmPurOrderEntryList2){
                			amt = amt.add(scmPurOrderEntry2.getAmt()).setScale(amtPrecision, RoundingMode.HALF_UP);
                			taxAmt = taxAmt.add(scmPurOrderEntry2.getTaxAmt()).setScale(amtPrecision, RoundingMode.HALF_UP);
                		}
                		scmPurOrder.setAmt(amt);
                		scmPurOrder.setTaxAmt(taxAmt);
                		scmPurOrderBiz.updateDirect(scmPurOrder, param);
                	}
            	}
            }
        }
	}

	@Override
	public void updatePurOrderPriceBillIdByPmId(long priceId, Param param) throws AppException {
		if(priceId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("priceId", priceId);
			((ScmPurOrderEntryDAO) dao).updatePurOrderPriceBillIdByPmId(map);
		}
	}

	@Override
	public void generateAdd(ScmPurOrderEntry2 scmPurOrderEntry, Param param) throws AppException {
		if (scmPurOrderEntry != null) {
			beforeAdd(scmPurOrderEntry, param);
			setUnitNo(scmPurOrderEntry, param);
			check(scmPurOrderEntry, param);
			int generateAdd = ((ScmPurOrderEntryDAO) dao).generateAdd(scmPurOrderEntry);
			if (generateAdd==0) {
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurOrderBizImpl.error.generateAdd");
			}
			afterAdd(scmPurOrderEntry, param);
		}
	}
}

