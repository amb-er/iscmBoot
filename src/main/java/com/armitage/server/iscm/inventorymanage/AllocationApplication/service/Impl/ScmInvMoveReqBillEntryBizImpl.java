package com.armitage.server.iscm.inventorymanage.AllocationApplication.service.Impl;

import java.math.BigDecimal;
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
import com.armitage.server.common.base.plugin.PagePlugin;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.dao.ScmInvMoveReqBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBill;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBillEntry2;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBill2;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBillEntry2;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.service.ScmInvMoveReqBillBiz;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.service.ScmInvMoveReqBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.service.ScmInvStockTransferBillBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.service.OrgUnitBiz;
import org.springframework.stereotype.Service;

@Service("scmInvMoveReqBillEntryBiz")
public class ScmInvMoveReqBillEntryBizImpl extends BaseBizImpl<ScmInvMoveReqBillEntry2> implements ScmInvMoveReqBillEntryBiz{
	
	private OrgUnitBiz orgUnitBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private ScmInvMoveReqBillBiz scmInvMoveReqBillBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private ScmInvStockTransferBillBiz scmInvStockTransferBillBiz;
	
	public void setScmInvMoveReqBillBiz(ScmInvMoveReqBillBiz scmInvMoveReqBillBiz) {
		this.scmInvMoveReqBillBiz = scmInvMoveReqBillBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}
	
	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setScmInvStockTransferBillBiz(ScmInvStockTransferBillBiz scmInvStockTransferBillBiz) {
		this.scmInvStockTransferBillBiz = scmInvStockTransferBillBiz;
	}

	@Override
	protected void afterSelect(ScmInvMoveReqBillEntry2 entity, Param param) throws AppException {
		if(entity != null){
			setConvertMap(entity, param);
		}
	}
	
	@Override
	public List<ScmInvMoveReqBillEntry2> selectByReqId(long reqId, Param param) throws AppException {
		if(reqId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("reqId", reqId);
			List<ScmInvMoveReqBillEntry2> scmInvMoveReqBillEntryList = ((ScmInvMoveReqBillEntryDAO) dao).selectByReqId(map);
			if(scmInvMoveReqBillEntryList!=null && !scmInvMoveReqBillEntryList.isEmpty()){
				for(ScmInvMoveReqBillEntry2 scmInvMoveReqBillEntry:scmInvMoveReqBillEntryList){
					setConvertMap(scmInvMoveReqBillEntry, param);
				}
			}
			return scmInvMoveReqBillEntryList;
		}
		return null;
	}

	@Override
	public void deleteByReqId(long reqId, Param param) throws AppException {
		if(reqId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("reqId", reqId);
			((ScmInvMoveReqBillEntryDAO) dao).deleteByReqId(map);
		}
	}

	@Override
	public void updateRowStatusByReqId(long reqId, String status, String checker, Date checkDate, Param param) throws AppException {
		if(reqId > 0 && StringUtils.isNotBlank(status)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("reqId", reqId);
			map.put("rowStatus", status);
			map.put("checker", checker);
			map.put("checkDate", checkDate);
			((ScmInvMoveReqBillEntryDAO) dao).updateRowStatusByReqId(map);
		}
	}

	@Override
	public List<ScmInvMoveReqBillEntry2> findGrantPage(Page page, Param param) throws AppException {
		if (page.getModelClass() == null)
			throw new AppException(
					"common.base.biz.BaseBizImpl.findPage.error.page.modelclass.null");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = addFindAllPageParam(page,map, param);
		map.put(PagePlugin.PAGE, page);
		// 翻页查询前处理
		beforeFindPage(page, param);
		(page.getParam()).put(ClassUtils.getFinalModelSimpleName(ScmInvMoveReqBill.class) + "." + ScmInvMoveReqBill.FN_OUTORGUNITNO, new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveReqBill.class) + "." + ScmInvMoveReqBill.FN_OUTORGUNITNO,
				QueryParam.QUERY_EQ, param.getOrgUnitNo()));
		List<ScmInvMoveReqBillEntry2> list = ((ScmInvMoveReqBillEntryDAO)dao).findGrantPage(map);
		//资源名称加载
		if (list != null && !list.isEmpty()) {
			HashMap<String,Object> cacheMap = new HashMap<String,Object>();
			for(ScmInvMoveReqBillEntry2 scmInvMoveReqBillEntry : list){
				if (scmInvMoveReqBillEntry.getIssueWareHouseId() > 0){
					//仓库
					ScmInvWareHouse scmInvWareHouse = (ScmInvWareHouse) cacheMap.get(ClassUtils.getFinalModelSimpleName(ScmInvWareHouse.class)+"_"+scmInvMoveReqBillEntry.getIssueWareHouseId());
					if(scmInvWareHouse==null){
						scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvMoveReqBillEntry.getIssueWareHouseId(), param);
						cacheMap.put(ClassUtils.getFinalModelSimpleName(ScmInvWareHouse.class)+"_"+scmInvMoveReqBillEntry.getIssueWareHouseId(),scmInvWareHouse);
					}
					if (scmInvWareHouse != null) {
						scmInvMoveReqBillEntry.setConvertMap(ScmInvMoveReqBillEntry2.FN_ISSUEWAREHOUSEID, scmInvWareHouse);
					}
				}
				if(StringUtils.isNotBlank(scmInvMoveReqBillEntry.getReqOrgUnitNo())){
					//组织
					OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvMoveReqBillEntry.getReqOrgUnitNo());
					if(orgBaseUnit==null){
						orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveReqBillEntry.getReqOrgUnitNo(), param);
						cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvMoveReqBillEntry.getReqOrgUnitNo(),orgBaseUnit);
					}
					if (orgBaseUnit != null) {
						scmInvMoveReqBillEntry.setConvertMap(ScmInvMoveReqBillEntry2.FN_REQORGUNITNO, orgBaseUnit);
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<ScmInvMoveReqBillEntry2> updateStatus(List<ScmInvMoveReqBillEntry2> scmInvMoveReqBillEntryList,
			Param param) throws AppException {
		if (scmInvMoveReqBillEntryList != null && !scmInvMoveReqBillEntryList.isEmpty()) {
			List<Long> reqIdList = new ArrayList<>();// 记录需要更新的单据
			List<ScmInvMoveReqBillEntry2> scmInvMoveReqBillEntryList2 = new ArrayList<>();
			for (ScmInvMoveReqBillEntry2 scmInvMoveReqBillEntry : scmInvMoveReqBillEntryList) {
				ScmInvMoveReqBillEntry2 scmInvMoveReqBillEntry2 = this.updateDirect(scmInvMoveReqBillEntry, param);
				if (scmInvMoveReqBillEntry2 != null) {
					scmInvMoveReqBillEntryList2.add(scmInvMoveReqBillEntry2);
					if (scmInvMoveReqBillEntry2.getReqId() > 0 && !reqIdList.contains(scmInvMoveReqBillEntry2.getReqId())) {
						reqIdList.add(scmInvMoveReqBillEntry2.getReqId());
					}
				}
			}
			for (long reqId : reqIdList) {
				ScmInvMoveReqBill scmInvMoveReqBill = scmInvMoveReqBillBiz.selectDirect(reqId, param);
				if (scmInvMoveReqBill != null) {
					List<ScmInvMoveReqBillEntry2> list = this.selectByReqId(reqId, param);
					if (list != null && !list.isEmpty()) {
						int count1 = 0;// 记录已发放条数
						int count2 = 0;// 记录拒绝条数
						for (ScmInvMoveReqBillEntry2 scmInvMoveReqBillEntry : list) {
							if (StringUtils.equals("E", scmInvMoveReqBillEntry.getRowStatus())) {
								count1++;
							} else if (StringUtils.equals("C", scmInvMoveReqBillEntry.getRowStatus())) {
								count2++;
							}
						}
						if (count2 > 0) {
							// 拒绝条数大于0时，等于总条数时单据状态为关闭，否则为部分关闭
							if (count2 == list.size()) {
								scmInvMoveReqBill.setStatus("C");
							} else {
								scmInvMoveReqBill.setStatus("F");
							}
						} else if (count2 == 0 && count1 > 0) {
							// 已发放条数大于0时，等于总条数时单据状态为下达，否则为部分下达
							if (count1 == list.size()) {
								scmInvMoveReqBill.setStatus("E");
							} else {
								scmInvMoveReqBill.setStatus("S");
							}
						} else if (count2 == 0 && count1 == 0) {
							scmInvMoveReqBill.setStatus("A");
						}
						scmInvMoveReqBillBiz.updateDirect(scmInvMoveReqBill, param);
					}
				}
			}
			if(scmInvMoveReqBillEntryList2 != null && !scmInvMoveReqBillEntryList2.isEmpty()){
				return scmInvMoveReqBillEntryList2;
			}
		}
		return null;
	}

	@Override
	public List<ScmInvMoveReqBillEntry2> pushBill(
			List<ScmInvMoveReqBillEntry2> scmInvMoveReqBillEntryList,
			Param param) throws AppException {
		if(scmInvMoveReqBillEntryList == null || scmInvMoveReqBillEntryList.isEmpty()){
			throw new AppException("iscm.inventorymanage.AllocationApplication.service.impl.ScmInvMoveReqBillBizImpl.notExitMoveReqBillDetail");
		} else {
			List<String> list = new ArrayList<>();
			for(ScmInvMoveReqBillEntry2 scmInvMoveReqBillEntry2:scmInvMoveReqBillEntryList){
				if (!list.contains(scmInvMoveReqBillEntry2.getReqWareHouseId() + "_" + scmInvMoveReqBillEntry2.getIssueWareHouseId())) {
                    list.add(scmInvMoveReqBillEntry2.getReqWareHouseId() + "_" + scmInvMoveReqBillEntry2.getIssueWareHouseId());
					CommonBean bean = new CommonBean();
			        List<ScmInvStockTransferBill2> scmInvStockTransferBillList = new ArrayList<>();
			        List<ScmInvStockTransferBillEntry2> scmInvStockTransferBillEntryList = new ArrayList<>();
			        ScmInvStockTransferBill2 scmInvStockTransferBill = new ScmInvStockTransferBill2(true);
					scmInvStockTransferBill.setBizType("1");
					scmInvStockTransferBill.setBizDate(FormatUtils.parseDate(FormatUtils.fmtDate(CalendarUtil.getDate(param))));
					scmInvStockTransferBill.setBillType("InvMoveReqBill");
					scmInvStockTransferBill.setFinOrgUnitNo(scmInvMoveReqBillEntry2.getFinOrgUnitNo());
					scmInvStockTransferBill.setOutOrgUnitNo(scmInvMoveReqBillEntry2.getIssueOrgUnitNo());
					scmInvStockTransferBill.setWareHouseId(scmInvMoveReqBillEntry2.getIssueWareHouseId());
					scmInvStockTransferBill.setInOrgUnitNo(scmInvMoveReqBillEntry2.getReqOrgUnitNo());
					scmInvStockTransferBill.setReceiptWarehouseId(scmInvMoveReqBillEntry2.getReqWareHouseId());
					scmInvStockTransferBill.setCurrencyNo(scmInvMoveReqBillEntry2.getCurrencyNo());
					scmInvStockTransferBill.setExchangeRate(scmInvMoveReqBillEntry2.getExchangeRate());
					scmInvStockTransferBill.setCreator(param.getUsrCode());
					scmInvStockTransferBill.setCreateDate(CalendarUtil.getDate(param));
					scmInvStockTransferBill.setCreateOrgUnitNo(param.getOrgUnitNo());
					scmInvStockTransferBill.setPrtcount(0);
					scmInvStockTransferBill.setStatus("I");
					scmInvStockTransferBill.setRemarks("");
					scmInvStockTransferBill.setControlUnitNo(param.getControlUnitNo());
					scmInvStockTransferBillList.add(scmInvStockTransferBill);
					bean.setList(scmInvStockTransferBillList);
					
					for(int i = 0; i < scmInvMoveReqBillEntryList.size(); i++){
						ScmInvMoveReqBillEntry2 scmInvMoveReqBillEntry = scmInvMoveReqBillEntryList.get(i);
						if (scmInvMoveReqBillEntry.getIssueWareHouseId() == scmInvStockTransferBill.getWareHouseId()
								&& scmInvMoveReqBillEntry.getReqWareHouseId() == scmInvStockTransferBill
										.getReceiptWarehouseId()) {
							ScmInvStockTransferBillEntry2 scmInvStockTransferBillEntry = new ScmInvStockTransferBillEntry2(true);
							scmInvStockTransferBillEntry.setWtId(scmInvStockTransferBill.getWtId());
							scmInvStockTransferBillEntry.setLineId(i+1);
							scmInvStockTransferBillEntry.setItemId(scmInvMoveReqBillEntry.getItemId());
							scmInvStockTransferBillEntry.setUnit(scmInvMoveReqBillEntry.getUnit());
							scmInvStockTransferBillEntry.setPieUnit(scmInvMoveReqBillEntry.getPieUnit());
							scmInvStockTransferBillEntry.setIssueOrgUnitNo(scmInvMoveReqBillEntry.getReqOrgUnitNo());
							scmInvStockTransferBillEntry.setWareHouseId(scmInvMoveReqBillEntry.getIssueWareHouseId());
							scmInvStockTransferBillEntry.setIssueManageOrgUnitNo(param.getOrgUnitNo());
							scmInvStockTransferBillEntry.setReceiveOrgUnitNo(scmInvMoveReqBillEntry.getReqOrgUnitNo());
							scmInvStockTransferBillEntry.setReceiptWarehouseId(scmInvMoveReqBillEntry.getReqWareHouseId());
							scmInvStockTransferBillEntry.setReceiveManageOrgUnitNo(scmInvMoveReqBillEntry.getReqOrgUnitNo());
							scmInvStockTransferBillEntry.setUseOrgUnitNo(scmInvMoveReqBillEntry.getUseOrgUnitNo());
							scmInvStockTransferBillEntry.setIssueOrgUnitNo(scmInvMoveReqBillEntry.getReqOrgUnitNo());
							scmInvStockTransferBillEntry.setLot(scmInvMoveReqBillEntry.getLot());
							scmInvStockTransferBillEntry.setPieQty(BigDecimal.ZERO);
							scmInvStockTransferBillEntry.setBaseQty(scmInvMoveReqBillEntry.getBaseQty());
							scmInvStockTransferBillEntry.setQty(scmInvMoveReqBillEntry.getQty());
							scmInvStockTransferBillEntry.setPieQty(scmInvMoveReqBillEntry.getPieQty());
				            ScmMaterial2 scmMaterial = scmMaterialBiz.selectByStock(scmInvStockTransferBill.getOutOrgUnitNo(),scmInvMoveReqBillEntry.getIssueWareHouseId(),scmInvMoveReqBillEntry.getItemId(), param);
				            if(scmMaterial==null)
				                throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvStockTransferBillBizImpl.generateMoveIssue.notScmMaterial");
				            scmInvStockTransferBillEntry.setPrice(scmMaterial.getPrice());
							scmInvStockTransferBillEntry.setAmt(scmMaterial.getPrice().multiply(scmInvMoveReqBillEntry.getQty()));
							scmInvStockTransferBillEntry.setTaxPrice(scmMaterial.getTaxPrice());
							scmInvStockTransferBillEntry.setTaxAmt(scmMaterial.getTaxPrice().multiply(scmInvMoveReqBillEntry.getQty()));
							scmInvStockTransferBillEntry.setTaxRate(BigDecimal.ZERO);
							scmInvStockTransferBillEntry.setSourceBillDtlId(scmInvMoveReqBillEntry.getId());
							scmInvStockTransferBillEntry.setRemarks(scmInvMoveReqBillEntry.getRemarks());
							scmInvStockTransferBillEntryList.add(scmInvStockTransferBillEntry);
						}
					}
					bean.setList2(scmInvStockTransferBillEntryList);
					scmInvStockTransferBillBiz.add(bean, param);
				}
			}
		}
		return null;
	}
	
	private void setConvertMap(ScmInvMoveReqBillEntry2 scmInvMoveReqBillEntry, Param param){
		if (scmInvMoveReqBillEntry.getReqWareHouseId() > 0){
			//仓库
			ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvMoveReqBillEntry.getReqWareHouseId(), param);
			if (scmInvWareHouse != null) {
				scmInvMoveReqBillEntry.setConvertMap(ScmInvMoveReqBillEntry2.FN_REQWAREHOUSEID, scmInvWareHouse);
			}
		}
		if(StringUtils.isNotBlank(scmInvMoveReqBillEntry.getUseOrgUnitNo())){
			//组织
			OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveReqBillEntry.getUseOrgUnitNo(), param);
			if (orgBaseUnit != null) {
				scmInvMoveReqBillEntry.setConvertMap(ScmInvMoveReqBillEntry2.FN_USEORGUNITNO, orgBaseUnit);
			}
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list!=null) {
			super.afterFindPage(list, page, param);
		}
	}
	
}
