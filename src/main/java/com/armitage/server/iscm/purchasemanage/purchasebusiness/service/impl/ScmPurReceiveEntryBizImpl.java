package com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.ScmCostUseSet2;
import com.armitage.server.iscm.basedata.model.ScmCostUseType;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmCostUseTypeBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurReceiveEntryDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntryAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturnsEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReceiveBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReceiveEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerBiz;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.SysParamBiz;
import org.springframework.stereotype.Service;

@Service("scmPurReceiveEntryBiz")
public class ScmPurReceiveEntryBizImpl extends BaseBizImpl<ScmPurReceiveEntry2> implements ScmPurReceiveEntryBiz {
	private static Log log = LogFactory.getLog(ScmPurReceiveEntryBizImpl.class);

	private ScmPurReceiveBiz scmPurReceiveBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private ScmPurOrderEntryBiz scmPurOrderEntryBiz;
	private ScmPurBuyerBiz scmPurBuyerBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private OrgAdminBiz orgAdminBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private ScmPurOrderBiz scmPurOrderBiz;
	private ScmCostUseTypeBiz scmCostUseTypeBiz;
	private SysParamBiz sysParamBiz;
	
	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	public void setScmPurBuyerBiz(ScmPurBuyerBiz scmPurBuyerBiz) {
		this.scmPurBuyerBiz = scmPurBuyerBiz;
	}

	public void setScmPurOrderEntryBiz(ScmPurOrderEntryBiz scmPurOrderEntryBiz) {
		this.scmPurOrderEntryBiz = scmPurOrderEntryBiz;
	}

	public void setScmPurReceiveBiz(ScmPurReceiveBiz scmPurReceiveBiz) {
		this.scmPurReceiveBiz = scmPurReceiveBiz;
	}

	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}

	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}

	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setScmPurOrderBiz(ScmPurOrderBiz scmPurOrderBiz) {
		this.scmPurOrderBiz = scmPurOrderBiz;
	}

	public void setScmCostUseTypeBiz(ScmCostUseTypeBiz scmCostUseTypeBiz) {
		this.scmCostUseTypeBiz = scmCostUseTypeBiz;
	}

	@Override
	public List<ScmPurReceiveEntry2> selectByPvId(long pvId, Param param) throws AppException {
		if(pvId > 0){
			long ss = new Date().getTime();
			HashMap<String, Object> map = new HashMap<>();
			map.put("pvId", pvId);
			List<ScmPurReceiveEntry2> scmPurReceiveEntryList = ((ScmPurReceiveEntryDAO) dao).selectByPvId(map);
			if(scmPurReceiveEntryList!=null && !scmPurReceiveEntryList.isEmpty()){
				StringBuffer itemids = new StringBuffer("");
				List<ScmMaterial2> scmMaterial2s=new ArrayList<>();
				List<ScmMaterial2> invScmMaterial2s=new ArrayList<>();
				for (ScmPurReceiveEntry2 scmPurReceiveEntry2 : scmPurReceiveEntryList) {
					if (StringUtils.isNotBlank(itemids.toString())) 
						itemids.append(",");
					itemids.append(scmPurReceiveEntry2.getItemId());
				}
				if (StringUtils.isNotEmpty(itemids.toString())) {
					scmMaterial2s = scmMaterialBiz.findByPurItemIds(param.getControlUnitNo(), scmPurReceiveEntryList.get(0).getPurOrgUnitNo(), itemids.toString(), param);
					invScmMaterial2s= scmMaterialBiz.findByInvItemIds(param.getControlUnitNo(), scmPurReceiveEntryList.get(0).getInvOrgUnitNo(), itemids.toString(), param);
				}
				for (ScmPurReceiveEntry2 scmPurReceiveEntry:scmPurReceiveEntryList) {
					setConvertMap(scmPurReceiveEntry,param);
					if (scmMaterial2s != null && !scmMaterial2s.isEmpty()) {
						for (ScmMaterial2 scmMaterial2 : scmMaterial2s) {
							if (scmMaterial2.getId() == scmPurReceiveEntry.getItemId()) {
								scmPurReceiveEntry.setReceiveTopRatio(scmMaterial2.getReceiveTopRatio());
							}
						}
					}
					if (invScmMaterial2s != null && !invScmMaterial2s.isEmpty()) {
						for (ScmMaterial2 scmMaterial2 : invScmMaterial2s) {
							if (scmMaterial2.getId() == scmPurReceiveEntry.getItemId()) {
								scmPurReceiveEntry.setPeriodValid(scmMaterial2.getPeriodValid());
								scmPurReceiveEntry.setPeriodValidDays(scmMaterial2.getPeriodValidDays());
							}
						}
					}
				}
			}
			return scmPurReceiveEntryList;
		}
		return null;
	}

	@Override
	public void deleteByPvId(long pvId, Param param) throws AppException {
		if(pvId > 0){
			List<ScmPurReceiveEntry2> scmPurReceiveEntryList= this.selectByPvId(pvId, param);
			this.delete(scmPurReceiveEntryList, param);
			scmPurOrderBiz.writeBackSended(scmPurReceiveEntryList, param);
		}
	}
	
	@Override
	protected void afterSelect(ScmPurReceiveEntry2 entity, Param param) throws AppException {
		if(entity != null){
			setConvertMap(entity,param);
		}
	}
	
	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if (list != null && !list.isEmpty()) {
			for (ScmPurReceiveEntry2 scmPurReceiveEntry:(List<ScmPurReceiveEntry2>)list) {
				setConvertMap(scmPurReceiveEntry,param);
			}
		}
	}
	
	private void setConvertMap(ScmPurReceiveEntry2 scmPurReceiveEntry,Param param){
		if (scmPurReceiveEntry != null){
			if (scmPurReceiveEntry.getTaxRate() != null){
				//税率
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmPurReceiveEntry.getTaxRate().toString(), null, param);
				if (pubSysBasicInfo != null) {
					scmPurReceiveEntry.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
					scmPurReceiveEntry.setConvertMap(ScmPurReceiveEntry2.FN_TAXRATESTR, pubSysBasicInfo);
				}
			}
			//让步接收率
			PubSysBasicInfo pubSysBasicInfo2 = pubSysBasicInfoBiz.selectByValue("scmAbnRate", scmPurReceiveEntry.getConcessiveRecRate().toString(), param);
			if (pubSysBasicInfo2 != null) {
				scmPurReceiveEntry.setConcessiveRecRateStr(pubSysBasicInfo2.getFInfoNo());
				scmPurReceiveEntry.setConvertMap(ScmPurReceiveEntry2.FN_CONCESSIVERECRATESTR, pubSysBasicInfo2);
			}
			
			if (scmPurReceiveEntry.getBuyerId() > 0){
				//采购员
				ScmPurBuyer2 scmPurBuyer = scmPurBuyerBiz.selectDirect(scmPurReceiveEntry.getBuyerId(), param);
				if (scmPurBuyer != null) {
					scmPurReceiveEntry.setConvertMap(ScmPurReceiveEntry2.FN_BUYERID, scmPurBuyer);
				}
			}
			if(scmPurReceiveEntry.getWareHouseId()>0){
				ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmPurReceiveEntry.getWareHouseId(), param);
				if(scmInvWareHouse!=null) {
					scmPurReceiveEntry.setWareHouseNo(scmInvWareHouse.getWhNo());
					scmPurReceiveEntry.setWareHouseName(scmInvWareHouse.getWhName());
					scmPurReceiveEntry.setConvertMap(ScmPurReceiveEntry2.FN_WAREHOUSEID, scmInvWareHouse);
				}
			}
			if(StringUtils.isNotBlank(scmPurReceiveEntry.getUseOrgUnitNo())){
				OrgAdmin2 orgAdmin = orgAdminBiz.selectByOrgUnitNo(scmPurReceiveEntry.getUseOrgUnitNo(), param);
				if(orgAdmin!=null) {
					scmPurReceiveEntry.setUseOrgUnitName(orgAdmin.getOrgUnitName());
					scmPurReceiveEntry.setConvertMap(ScmPurReceiveEntry2.FN_USEORGUNITNO, orgAdmin);
				}
			}
			//成本用途
			if (scmPurReceiveEntry.getCostUseTypeId()>0) {
				ScmCostUseType scmCostUseType = scmCostUseTypeBiz.select(scmPurReceiveEntry.getCostUseTypeId(), param);
				if (scmCostUseType != null) {
					ScmCostUseSet2 costUseSet = new ScmCostUseSet2();
					costUseSet.setCostUseTypeId(scmCostUseType.getId());
					costUseSet.setScmCostUseTypeName(scmCostUseType.getName());
					scmPurReceiveEntry.setConvertMap(ScmInvPurInWarehsBillEntry2.FN_COSTUSETYPEID, costUseSet);
				}
			}
			//物资编码
			if (scmPurReceiveEntry.getItemId()>0) {
				ScmMaterial2 scmMaterial2 = scmMaterialBiz.selectByItemId(scmPurReceiveEntry.getItemId(), param);
				if (scmMaterial2 != null) {
					scmPurReceiveEntry.setConvertMap(scmPurReceiveEntry.FN_ITEMNO, scmMaterial2);
				}
			}
			/*if(scmPurReceiveEntry.getItemId()>0){
				//获取超收比例
				ScmMaterial2 scmMaterial = scmMaterialBiz.findByPurItemId(param.getControlUnitNo(), scmPurReceiveEntry.getPurOrgUnitNo(), scmPurReceiveEntry.getItemId(), param);
				if(scmMaterial!=null)
					scmPurReceiveEntry.setReceiveTopRatio(scmMaterial.getReceiveTopRatio());
			}*/
			/*if (StringUtils.isNotBlank(scmPurReceiveEntry.getItemNo())) {
				Page page = new Page();
				page.setShowCount(Integer.MAX_VALUE);
				page.setModelClass(ScmMaterial2.class);
				ArrayList argList = new ArrayList();
				argList.add("purOrgUnitNo=" + scmPurReceiveEntry.getPurOrgUnitNo());
				argList.add("invOrgUnitNo="+scmPurReceiveEntry.getInvOrgUnitNo());
				argList.add("controlUnitNo="+param.getControlUnitNo());
				argList.add("itemNo="+scmPurReceiveEntry.getItemNo());
				List<ScmMaterial2> scmMaterial2s=scmMaterialBiz.queryPage(page,argList, "findBySinglePurAndInvByItemNo", param);
				if (scmMaterial2s != null && scmMaterial2s.size()>0) {
					scmPurReceiveEntry.setConvertMap(ScmMaterial2.FN_ITEMNO, scmMaterial2s.get(0));
				}
			}*/
		}
	}
//	@Override
//	protected void afterAdd(ScmPurReceiveEntry2 entity, Param param)
//			throws AppException {
//		scmPurOrderEntryBiz.writeBackByPurReceive(null, entity, param);
//	}

	@Override
	protected void afterUpdate(ScmPurReceiveEntry2 oldEntity,
			ScmPurReceiveEntry2 newEntity, Param param) throws AppException {
		scmPurOrderEntryBiz.writeBackByPurReceive(oldEntity, newEntity, param);
	}

	@Override
	protected void afterDelete(ScmPurReceiveEntry2 entity, Param param) throws AppException {
		scmPurOrderEntryBiz.writeBackByPurReceive(entity, null, param);
	}
	
	@Override
	public void updateRowStatusByPvId(long pvId, String status, String checker, Date checkDate, Param param) throws AppException {
		if(pvId > 0 && StringUtils.isNotBlank(status)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("pvId", pvId);
			map.put("rowStatus", status);
			map.put("checker", checker);
			map.put("checkDate", checkDate);
			((ScmPurReceiveEntryDAO) dao).updateRowStatusByPvId(map);
		}
	}
	
	@Override
	public List<ScmPurReceiveEntry2> updateStatus(List<ScmPurReceiveEntry2> scmPurReceiveEntryList, Param param) throws AppException {
		if (scmPurReceiveEntryList != null && !scmPurReceiveEntryList.isEmpty()) {
			List<Long> pvIdList = new ArrayList<>();// 记录需要更新的收货单
			List<ScmPurReceiveEntry2> scmPurReceiveEntryList2 = new ArrayList<>();
			for (ScmPurReceiveEntry2 scmPurReceiveEntry : scmPurReceiveEntryList) {
				ScmPurReceiveEntry2 scmPurReceiveEntry2 = this.updateDirect(scmPurReceiveEntry, param);
				if (scmPurReceiveEntry2 != null) {
					scmPurReceiveEntryList2.add(scmPurReceiveEntry2);
					if (scmPurReceiveEntry2.getPvId() > 0 && !pvIdList.contains(scmPurReceiveEntry2.getPvId())) {
						pvIdList.add(scmPurReceiveEntry2.getPvId());
					}
				}
			}
			for (long pvId : pvIdList) {
				ScmPurReceive2 scmPurReceive = scmPurReceiveBiz.selectDirect(pvId, param);
				if (scmPurReceive != null) {
					List<ScmPurReceiveEntry2> list = this.selectByPvId(pvId, param);
					if (list != null && !list.isEmpty()) {
						int count1 = 0;// 记录下达条数
						int count2 = 0;// 记录关闭条数
						for (ScmPurReceiveEntry2 receiveEntry : list) {
							if (StringUtils.equals("E", receiveEntry.getRowStatus())) {
								count1++;
							} else if (StringUtils.equals("C", receiveEntry.getRowStatus())) {
								count2++;
							}
						}
						if (count2 > 0) {
							// 关闭条数大于0时，等于总条数时收货单状态为关闭，否则为部分关闭
							if (count2 == list.size()) {
								scmPurReceive.setStatus("C");
							} else {
								scmPurReceive.setStatus("F");
							}
						} else if (count2 == 0 && count1 > 0) {
							// 下达条数大于0时，等于总条数时收货单状态为下达，否则为部分下达
							if (count1 == list.size()) {
								scmPurReceive.setStatus("E");
							} else {
								scmPurReceive.setStatus("S");
							}
						} else if (count2 == 0 && count1 == 0) {
							scmPurReceive.setStatus("A");
						}
						scmPurReceiveBiz.updateDirect(scmPurReceive, param);
					}
				}
			}
			if(scmPurReceiveEntryList2 != null && !scmPurReceiveEntryList2.isEmpty()){
				return scmPurReceiveEntryList2;
			}
		}
		return null;
	}
	
	@Override
	public void updateBillStatusByEntry(ScmPurReceiveEntry2 scmPurReceiveEntry, Param param) throws AppException {
		if (scmPurReceiveEntry != null) {
			ScmPurReceive2 scmPurReceive = scmPurReceiveBiz.selectDirect(scmPurReceiveEntry.getPvId(), param);
			if (scmPurReceive != null) {
				List<ScmPurReceiveEntry2> list = this.selectByNotWr(scmPurReceiveEntry.getPvId(), param);
				if (list != null && !list.isEmpty()) {
					int count1 = 0;// 记录下达条数
					int count2 = 0;// 记录关闭条数
					for (ScmPurReceiveEntry2 receiveEntry : list) {
						if (StringUtils.equals("E", receiveEntry.getRowStatus())) {
							count1++;
						} else if (StringUtils.equals("C", receiveEntry.getRowStatus())) {
							count2++;
						}
					}
					if (count2 > 0) {
						// 关闭条数大于0时，等于总条数时收货单状态为关闭，否则为部分关闭
						if (count2 == list.size()) {
							scmPurReceive.setStatus("C");
						} else {
							scmPurReceive.setStatus("F");
						}
					} else if (count2 == 0 && count1 > 0) {
						// 下达条数大于0时，等于总条数时收货单状态为下达，否则为部分下达
						if (count1 == list.size()) {
							scmPurReceive.setStatus("E");
						} else {
							scmPurReceive.setStatus("S");
						}
					} else if (count2 == 0 && count1 == 0) {
						scmPurReceive.setStatus("A");
					}
					scmPurReceiveBiz.updateDirect(scmPurReceive, param);
				}else {
					scmPurReceive.setStatus("C");
					scmPurReceiveBiz.updateDirect(scmPurReceive, param);
				}
			}
		}
	}
	
	@Override
	public CommonBean getDataForLeadInto(ScmPurReceiveEntryAdvQuery scmPurReceiveEntryAdvQuery, Param param)
			throws AppException {
		CommonBean bean = new CommonBean();
		if(scmPurReceiveEntryAdvQuery != null){
			//1、先查询符合条件的收货单
			Page page = new Page();
			page.setModelClass(ScmPurReceive.class);
			page.setShowCount(Integer.MAX_VALUE);
			if(scmPurReceiveEntryAdvQuery.getBegBillDate() != null && scmPurReceiveEntryAdvQuery.getEndBillDate() != null){
				page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurReceive.class) + "." + ScmPurReceive.FN_RECEIVEDATE), 
						new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurReceive.class) + "." + ScmPurReceive.FN_RECEIVEDATE), QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(scmPurReceiveEntryAdvQuery.getBegBillDate()),
								FormatUtils.fmtDate(scmPurReceiveEntryAdvQuery.getEndBillDate())));
			}else if(scmPurReceiveEntryAdvQuery.getBegBillDate() != null){
				page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurReceive.class) + "." + ScmPurReceive.FN_RECEIVEDATE), 
						new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurReceive.class) + "." + ScmPurReceive.FN_RECEIVEDATE), QueryParam.QUERY_GE, FormatUtils.fmtDate(scmPurReceiveEntryAdvQuery.getBegBillDate())));
			}else if(scmPurReceiveEntryAdvQuery.getEndBillDate() != null){
				page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurReceive.class) + "." + ScmPurReceive.FN_RECEIVEDATE), 
						new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurReceive.class) + "." + ScmPurReceive.FN_RECEIVEDATE), QueryParam.QUERY_LE, FormatUtils.fmtDate(scmPurReceiveEntryAdvQuery.getEndBillDate())));
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurReceive.class) + "." + ScmPurReceive.FN_STATUS), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurReceive.class) + "." + ScmPurReceive.FN_STATUS), QueryParam.QUERY_IN, "'C','F'"));
			if(StringUtils.isNotBlank(scmPurReceiveEntryAdvQuery.getEndPvNo())){
				if(StringUtils.isNotBlank(scmPurReceiveEntryAdvQuery.getBegPvNo())){
					page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurReceive.class) + "." + ScmPurReceive.FN_PVNO), 
							new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurReceive.class) + "." + ScmPurReceive.FN_PVNO), QueryParam.QUERY_BETWEEN, scmPurReceiveEntryAdvQuery.getBegPvNo(),
									scmPurReceiveEntryAdvQuery.getEndPvNo()));
				}else{
					page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurReceive.class) + "." + ScmPurReceive.FN_PVNO), 
							new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurReceive.class) + "." + ScmPurReceive.FN_PVNO), QueryParam.QUERY_LE, scmPurReceiveEntryAdvQuery.getEndPvNo()));
				}
			}else if(StringUtils.isNotBlank(scmPurReceiveEntryAdvQuery.getBegPvNo())){
				page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurReceive.class) + "." + ScmPurReceive.FN_PVNO), 
						new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurReceive.class) + "." + ScmPurReceive.FN_PVNO), QueryParam.QUERY_GE, scmPurReceiveEntryAdvQuery.getBegPvNo()));
			}
			if(scmPurReceiveEntryAdvQuery.getVendorId() > 0){
				page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurReceive.class) + "." + ScmPurReceive.FN_VENDORID), 
						new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurReceive.class) + "." + ScmPurReceive.FN_VENDORID), QueryParam.QUERY_EQ, String.valueOf(scmPurReceiveEntryAdvQuery.getVendorId())));
			}
			List<ScmPurReceive> resultList = scmPurReceiveBiz.findPage(page, param);
			List<ScmPurReceive2> scmPurReceiveList = new ArrayList<>();
			StringBuffer pvIdstr = new StringBuffer("");
			if(resultList != null && !resultList.isEmpty()){
				for(ScmPurReceive scmPurReceive : resultList){
					ScmPurReceive2 scmPurReceive2 = new ScmPurReceive2(true);
					BeanUtil.copyProperties(scmPurReceive2, scmPurReceive);
					scmPurReceiveList.add(scmPurReceive2);
					if (StringUtils.isEmpty(pvIdstr.toString())){
						pvIdstr.append("'").append(scmPurReceive.getId()).append("'");
					}else{
						pvIdstr.append(",").append("'").append(scmPurReceive.getId()).append("'");
					}
				}
			}
			//2、根据符合条件的收货单和原有条件查询收货明细
			if(StringUtils.isNotBlank(pvIdstr.toString())){
				Page page2 = new Page();
				page2.setModelClass(ScmPurReceiveEntry2.class);
				page2.setShowCount(Integer.MAX_VALUE);
				page2.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurReceiveEntry2.class) + "." + ScmPurReceiveEntry2.FN_ROWSTATUS), 
						new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurReceiveEntry2.class) + "." + ScmPurReceiveEntry2.FN_ROWSTATUS), QueryParam.QUERY_IN, "'C','E'"));
				page2.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurReceiveEntry2.class) + "." + ScmPurReceiveEntry2.FN_PVID), 
						new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurReceiveEntry2.class) + "." + ScmPurReceiveEntry2.FN_PVID), QueryParam.QUERY_IN, pvIdstr.toString()));
				if(scmPurReceiveEntryAdvQuery.getItemId() > 0){
					page2.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurReceiveEntry2.class) + "." + ScmPurReceiveEntry2.FN_ITEMID), 
							new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurReceiveEntry2.class) + "." + ScmPurReceiveEntry2.FN_ITEMID), QueryParam.QUERY_EQ, String.valueOf(scmPurReceiveEntryAdvQuery.getItemId())));
				}
				page2.setSqlCondition("ScmPurReceiveEntry.returnQty<ScmPurReceiveEntry.invQty");
				List<ScmPurReceiveEntry2> scmPurReceiveEntryList = this.findPage(page2, param);
				List<Long> pvIdList = new ArrayList<>();
				if(scmPurReceiveEntryList != null && !scmPurReceiveEntryList.isEmpty()){
					for(int i=scmPurReceiveEntryList.size()-1; i>=0; i--){
						if (scmPurReceiveEntryList.get(i).getPvId() > 0 && !pvIdList.contains(scmPurReceiveEntryList.get(i).getPvId())) {
							pvIdList.add(scmPurReceiveEntryList.get(i).getPvId());
						}
					}
				}
				//3、根据查询到的收货明细过滤收货单
				if(pvIdList != null && !pvIdList.isEmpty()
						&& scmPurReceiveList != null && !scmPurReceiveList.isEmpty()){
					for(int i=scmPurReceiveList.size()-1; i>=0; i--){
						boolean flag = false;
						for(int j=pvIdList.size()-1; j>=0; j--){
							if(pvIdList.get(j) == scmPurReceiveList.get(i).getId()){
								flag = true;
								break;
							}
						}
						if(!flag){
							scmPurReceiveList.remove(i);
						}
					}
					if(scmPurReceiveEntryList != null && !scmPurReceiveEntryList.isEmpty()
							&& scmPurReceiveList != null && !scmPurReceiveList.isEmpty()){
						bean.setList(scmPurReceiveList);
						bean.setList2(scmPurReceiveEntryList);
						return bean;
					}
				}
			}
		}
		return bean;
	}

	@Override
	public void writeBackByPurReturn(ScmPurReturnsEntry2 oldEntity,ScmPurReturnsEntry2 newEntity, Param param) throws AppException {
		BigDecimal qty = BigDecimal.ZERO;
		if(newEntity!=null && newEntity.getSourceDtlId()>0){
			qty = qty.add(newEntity.getInvQty());
		}
		if(oldEntity!=null && oldEntity.getSourceDtlId()>0){
			qty = qty.subtract(oldEntity.getInvQty());
		}
		if(newEntity!=null && newEntity.getSourceDtlId()>0 || (oldEntity!=null && oldEntity.getSourceDtlId()>0)){
			ScmPurReceiveEntry2 scmPurReceiveEntry = this.selectDirect(newEntity!=null?newEntity.getSourceDtlId():oldEntity.getSourceDtlId(), param);
			scmPurReceiveEntry.setReturnQty(scmPurReceiveEntry.getReturnQty().add(qty));
			if((scmPurReceiveEntry.getReturnQty().add(scmPurReceiveEntry.getAddInQty())).compareTo(scmPurReceiveEntry.getInvQty())>=0){
				scmPurReceiveEntry.setRowStatus("C");
			}else{
				scmPurReceiveEntry.setRowStatus("E");
			}
			this.updateDirect(scmPurReceiveEntry, param);
//			this.updateBillStatusByEntry(scmPurReceiveEntry, param);
		}
	}

	@Override
	public List<ScmPurReceiveEntry2> selectByCkId(long ckId, Param param) throws AppException {
		if(ckId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("ckId", ckId);
			List<ScmPurReceiveEntry2> scmPurReceiveEntryList = ((ScmPurReceiveEntryDAO) dao).selectByCkId(map);
			if(scmPurReceiveEntryList!=null && !scmPurReceiveEntryList.isEmpty()){
				for(ScmPurReceiveEntry2 scmPurReceiveEntry :scmPurReceiveEntryList){
					setConvertMap(scmPurReceiveEntry, param);
				}
			}
			return scmPurReceiveEntryList;
		}
		return null;
	}

	@Override
	public void writeBackByInWarehs(ScmInvPurInWarehsBillEntry2 oldEntity,
			ScmInvPurInWarehsBillEntry2 newEntity, Param param)
			throws AppException {
		BigDecimal qty = BigDecimal.ZERO;
		if(newEntity!=null && newEntity.getSourceBillDtlId()>0){
			qty = qty.add(newEntity.getQty());
		}
		if(oldEntity!=null && oldEntity.getSourceBillDtlId()>0){
			qty = qty.subtract(oldEntity.getQty());
		}
		if(newEntity!=null && newEntity.getSourceBillDtlId()>0 || (oldEntity!=null && oldEntity.getSourceBillDtlId()>0)){
			ScmPurReceiveEntry2 scmPurReceiveEntry = this.selectDirect(newEntity!=null?newEntity.getSourceBillDtlId():oldEntity.getSourceBillDtlId(), param);
			scmPurReceiveEntry.setAddInQty(scmPurReceiveEntry.getAddInQty().add(qty));
			if((scmPurReceiveEntry.getAddInQty().add(scmPurReceiveEntry.getReturnQty())).compareTo(scmPurReceiveEntry.getInvQty())>=0){
				scmPurReceiveEntry.setRowStatus("C");
			}else{
				scmPurReceiveEntry.setRowStatus("E");
			}
			this.updateDirect(scmPurReceiveEntry, param);
			this.updateBillStatusByEntry(scmPurReceiveEntry, param);
		}
	}

	@Override
	public List<ScmPurReceiveEntry2> selectByNotWr(long pvId, Param param) throws AppException {
		if(pvId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("pvId", pvId);
			map.put("controlUnitNo", param.getControlUnitNo());
			String rebateType = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_RebateType", "2", param);//折让方式
			map.put("notwr", rebateType);
			List<ScmPurReceiveEntry2> scmPurReceiveEntryList = ((ScmPurReceiveEntryDAO) dao).selectByPvId(map);
			if(scmPurReceiveEntryList!=null && !scmPurReceiveEntryList.isEmpty()){
				for (ScmPurReceiveEntry2 scmPurReceiveEntry:scmPurReceiveEntryList) {
					scmPurReceiveEntry.setInvQty(scmPurReceiveEntry.getInvQty().subtract(scmPurReceiveEntry.getAddInQty()));
					setConvertMap(scmPurReceiveEntry,param);
				}
			}
			return scmPurReceiveEntryList;
		}
		return null;
	}

	@Override
	public void clearInvQty(String ids, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("ids", ids);
		((ScmPurReceiveEntryDAO)dao).clearInvQty(map);
	}

	}

