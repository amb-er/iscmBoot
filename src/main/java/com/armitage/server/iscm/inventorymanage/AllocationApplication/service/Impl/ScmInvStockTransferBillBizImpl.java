package com.armitage.server.iscm.inventorymanage.AllocationApplication.service.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.base.model.SimpleDataDisplay;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBillEntry2;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBill2;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBillEntry2;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.service.ScmInvMoveReqBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.service.ScmInvStockTransferBillBiz;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.service.ScmInvStockTransferBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMoveIssueBillBiz;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmInvStockTransferBillBiz")
public class ScmInvStockTransferBillBizImpl extends BaseBizImpl<ScmInvStockTransferBill2> implements ScmInvStockTransferBillBiz {
	
	private UsrBiz usrBiz;
	private ScmInvStockTransferBillEntryBiz scmInvStockTransferBillEntryBiz;
	private OrgUnitBiz orgUnitBiz;
	private ScmInvMoveIssueBillBiz scmInvMoveIssueBillBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private PeriodCalendarBiz periodCalendarBiz;
	private ScmInvMoveReqBillEntryBiz scmInvMoveReqBillEntryBiz;
	private BillTypeBiz billTypeBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private OrgStorageBiz orgStorageBiz;
	
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setScmInvStockTransferBillEntryBiz(
			ScmInvStockTransferBillEntryBiz scmInvStockTransferBillEntryBiz) {
		this.scmInvStockTransferBillEntryBiz = scmInvStockTransferBillEntryBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public void setScmInvMoveIssueBillBiz(
			ScmInvMoveIssueBillBiz scmInvMoveIssueBillBiz) {
		this.scmInvMoveIssueBillBiz = scmInvMoveIssueBillBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}
	
	public void setScmInvMoveReqBillEntryBiz(ScmInvMoveReqBillEntryBiz scmInvMoveReqBillEntryBiz) {
		this.scmInvMoveReqBillEntryBiz = scmInvMoveReqBillEntryBiz;
	}

	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
        this.billTypeBiz = billTypeBiz;
    }

    public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvStockTransferBill2.class) + "." + ScmInvStockTransferBill2.FN_OUTORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvStockTransferBill2.class) + "." + ScmInvStockTransferBill2.FN_OUTORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvStockTransferBill2.class) + "." + ScmInvStockTransferBill2.FN_OUTORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvStockTransferBill2.class) + "." + ScmInvStockTransferBill2.FN_OUTORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}

	@Override
	protected void afterSelect(ScmInvStockTransferBill2 entity, Param param) throws AppException {
		if (entity != null){
			setConvertMap(entity,param);
		}
	}
	
	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		if(list != null && !list.isEmpty()){
			for(ScmInvStockTransferBill2 scmInvStockTransferBill2 : (List<ScmInvStockTransferBill2>)list){
				setConvertMap(scmInvStockTransferBill2,param);
			}
		}
	}
	
	private void setConvertMap(ScmInvStockTransferBill2 scmInvStockTransferBill,Param param) {
		if(scmInvStockTransferBill!=null) {
			if (StringUtils.isNotBlank(scmInvStockTransferBill.getCreator())){
				//制单人  
				Usr usr = usrBiz.selectByCode(scmInvStockTransferBill.getCreator(), param);
				if (usr != null) {
					scmInvStockTransferBill.setConvertMap(ScmInvStockTransferBill2.FN_CREATOR, usr);
					if(scmInvStockTransferBill.getDataDisplayMap()==null){
						scmInvStockTransferBill.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
					}
					scmInvStockTransferBill.getDataDisplayMap().put(ScmInvStockTransferBill2.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
				}
			}
			if (StringUtils.isNotBlank(scmInvStockTransferBill.getOutOrgUnitNo())){
				//调出库存组织
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvStockTransferBill.getOutOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmInvStockTransferBill.setConvertMap(ScmInvStockTransferBill2.FN_OUTORGUNITNO, orgBaseUnit);
				}
			}
			if (StringUtils.isNotBlank(scmInvStockTransferBill.getInOrgUnitNo())){
				//调入库存组织
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvStockTransferBill.getInOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmInvStockTransferBill.setConvertMap(ScmInvStockTransferBill2.FN_INORGUNITNO, orgBaseUnit);
				}
			}
			if (StringUtils.isNotBlank(scmInvStockTransferBill.getCreateOrgUnitNo())){
				//制单组织
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvStockTransferBill.getCreateOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmInvStockTransferBill.setConvertMap(ScmInvStockTransferBill2.FN_CREATEORGUNITNO, orgBaseUnit);
				}
			}
			if (scmInvStockTransferBill.getWareHouseId() > 0) {
	            //调出仓库
	            ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvStockTransferBill.getWareHouseId(), param);
	            if (scmInvWareHouse != null) {
	            	scmInvStockTransferBill.setConvertMap(ScmInvStockTransferBillEntry2.FN_WAREHOUSEID, scmInvWareHouse);
	            }
	        }
			if (scmInvStockTransferBill.getReceiptWarehouseId() > 0) {
	            //调入仓库
	            ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvStockTransferBill.getReceiptWarehouseId(), param);
	            if (scmInvWareHouse != null) {
	            	scmInvStockTransferBill.setConvertMap(ScmInvStockTransferBillEntry2.FN_RECEIPTWAREHOUSEID, scmInvWareHouse);
	            }
	        }
			if (StringUtils.isNotBlank(scmInvStockTransferBill.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmInvStockTransferBill.getEditor(), param);
				if (usr != null) {
					scmInvStockTransferBill.setConvertMap(ScmInvStockTransferBill2.FN_EDITOR, usr);
				}
			}
			
			if (StringUtils.isNotBlank(scmInvStockTransferBill.getChecker())){
				//审核人
				Usr usr = usrBiz.selectByCode(scmInvStockTransferBill.getChecker(), param);
				if (usr != null) {
					scmInvStockTransferBill.setConvertMap(ScmInvStockTransferBill2.FN_CHECKER, usr);
				}
			}
			if (StringUtils.isNotBlank(scmInvStockTransferBill.getBillType())){
	            //来源单类型
	            BillType2 billType = billTypeBiz.selectByOrgAndCode(scmInvStockTransferBill.getFinOrgUnitNo(), scmInvStockTransferBill.getBillType(), param);
	            if (billType != null) {
	            	scmInvStockTransferBill.setConvertMap(ScmInvSaleOrder.FN_BILLTYPE, billType);
	            }
	        }
			scmInvStockTransferBill.setTaxAmount(scmInvStockTransferBill.getTaxAmt().subtract(scmInvStockTransferBill.getAmt()));
		}
	}
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmInvStockTransferBill2 scmInvStockTransferBill2 = (ScmInvStockTransferBill2) bean.getList().get(0);
		if(scmInvStockTransferBill2 != null && scmInvStockTransferBill2.getWtId() > 0){
			HashMap<String,Object> cacheMap = new HashMap<String,Object>();
			List<ScmInvStockTransferBillEntry2> scmInvStockTransferBillEntryList = scmInvStockTransferBillEntryBiz.selectByWtId(scmInvStockTransferBill2.getWtId(), param);
			if(scmInvStockTransferBillEntryList != null && !scmInvStockTransferBillEntryList.isEmpty()){
				bean.setList2(scmInvStockTransferBillEntryList);
			}
		}
	}
	
	@Override
	protected void beforeAdd(ScmInvStockTransferBill2 entity, Param param) throws AppException {
		if(entity != null){
//			String date = StringUtils.replace(FormatUtils.fmtDate(entity.getCreateDate()), "-", "");
//			StringBuffer s = new StringBuffer("");
//			s.append("ST").append(date);
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("createDate", FormatUtils.fmtDate(FormatUtils.parseDateTime(date, "yyyyMMdd")));
//			map.put("controlUnitNo", param.getControlUnitNo());
//			ScmInvStockTransferBill2 scmInvOtherIssueBill2= ((ScmInvStockTransferBillDAO) dao).selectMaxIdByDate(map);
//			if(scmInvOtherIssueBill2 != null && StringUtils.isNotBlank(scmInvOtherIssueBill2.getWtNo())
//					&& scmInvOtherIssueBill2.getWtNo().contains(date)){
//				String str = StringUtils.substring(scmInvOtherIssueBill2.getWtNo(), (scmInvOtherIssueBill2.getWtNo().indexOf(date)+date.length()));
//				str = CodeAutoCalUtil.autoAddOne(str);
//				str = (s.append(str)).toString();
//				entity.setWtNo(str);
//			}else{
//				entity.setWtNo((s.append("001").toString()));
//			}
			String code = CodeAutoCalUtil.getBillCode("InvStockTransfer", entity, param);
			entity.setWtNo(code);
			//获取期间
			PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(entity.getBizDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
			}
			entity.setPeriodId(periodCalendar.getPeriodId());
			entity.setAccountYear(periodCalendar.getAccountYear());
			entity.setAccountPeriod(periodCalendar.getAccountPeriod());
		}
	}
	
	@Override
	protected void beforeUpdate(ScmInvStockTransferBill2 oldEntity,ScmInvStockTransferBill2 newEntity, Param param)
			throws AppException {
		//获取期间
		PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(newEntity.getBizDate(), param);
		if(periodCalendar==null){
			throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
		}
		newEntity.setPeriodId(periodCalendar.getPeriodId());
		newEntity.setAccountYear(periodCalendar.getAccountYear());
		newEntity.setAccountPeriod(periodCalendar.getAccountPeriod());
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmInvStockTransferBill2 scmInvStockTransferBill2 = (ScmInvStockTransferBill2) bean.getList().get(0);
		scmInvStockTransferBill2.setAmt(BigDecimal.ZERO);
		scmInvStockTransferBill2.setTaxAmt(BigDecimal.ZERO);
		if(scmInvStockTransferBill2 != null && scmInvStockTransferBill2.getWtId() > 0){
			//新增调拨单明细
			List<ScmInvStockTransferBillEntry2> scmInvStockTransferBillEntryList = bean.getList2();
			if(scmInvStockTransferBillEntryList != null && !scmInvStockTransferBillEntryList.isEmpty()){
				int lineId = 1;
				for(ScmInvStockTransferBillEntry2 scmInvStockTransferBillEntry : scmInvStockTransferBillEntryList){
					scmInvStockTransferBill2.setAmt(scmInvStockTransferBillEntry.getAmt().add(scmInvStockTransferBill2.getAmt()));
					scmInvStockTransferBill2.setTaxAmt(scmInvStockTransferBillEntry.getTaxAmt().add(scmInvStockTransferBill2.getTaxAmt()));
					scmInvStockTransferBillEntry.setLineId(lineId);
					scmInvStockTransferBillEntry.setWtId(scmInvStockTransferBill2.getWtId());
					scmInvStockTransferBillEntry.setWareHouseId(scmInvStockTransferBill2.getWareHouseId());
					scmInvStockTransferBillEntry.setReceiptWarehouseId(scmInvStockTransferBill2.getReceiptWarehouseId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvStockTransferBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvStockTransferBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvStockTransferBillEntry.getItemId(), scmInvStockTransferBillEntry.getUnit(), param);//库存单位转换系数
					scmInvStockTransferBillEntry.setBaseQty(scmInvStockTransferBillEntry.getQty().multiply(invConvRate));
					scmInvStockTransferBillEntryBiz.add(scmInvStockTransferBillEntry, param);
					lineId = lineId+1;
				}
				scmInvStockTransferBill2.setTaxAmount(scmInvStockTransferBill2.getTaxAmt().subtract(scmInvStockTransferBill2.getAmt()));
			}
		}
	}
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvStockTransferBill2 scmInvStockTransferBill2 = (ScmInvStockTransferBill2) bean.getList().get(0);
		if(scmInvStockTransferBill2 != null && scmInvStockTransferBill2.getWtId() > 0){
			//更新调拨单明细
			List<ScmInvStockTransferBillEntry2> scmInvStockTransferBillEntryList = bean.getList2();
			if(scmInvStockTransferBillEntryList != null && !scmInvStockTransferBillEntryList.isEmpty()){
				int lineId = 1;
				for(ScmInvStockTransferBillEntry2 scmInvStockTransferBillEntry : scmInvStockTransferBillEntryList){
					if (StringUtils.equals("I", scmInvStockTransferBill2.getStatus())) {
						scmInvStockTransferBillEntry.setLineId(lineId);
					}
					scmInvStockTransferBillEntry.setWtId(scmInvStockTransferBill2.getWtId());
					scmInvStockTransferBillEntry.setWareHouseId(scmInvStockTransferBill2.getWareHouseId());
					scmInvStockTransferBillEntry.setReceiptWarehouseId(scmInvStockTransferBill2.getReceiptWarehouseId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvStockTransferBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvStockTransferBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvStockTransferBillEntry.getItemId(), scmInvStockTransferBillEntry.getUnit(), param);//库存单位转换系数
					scmInvStockTransferBillEntry.setBaseQty(scmInvStockTransferBillEntry.getQty().multiply(invConvRate));
					lineId = lineId+1;
				}
				scmInvStockTransferBillEntryBiz.update(scmInvStockTransferBill2, scmInvStockTransferBillEntryList, ScmInvStockTransferBillEntry2.FN_WTID, param);
			}
		}
	}
	
	@Override
	protected void afterDelete(ScmInvStockTransferBill2 entity, Param param) throws AppException {
		if(entity != null && entity.getWtId() > 0){
			//回写调拨累计调拨数量
            //查出调拨单明细
            List<ScmInvStockTransferBillEntry2> scmInvStockTransferBillEntryList = scmInvStockTransferBillEntryBiz.selectByWtId(entity.getWtId(), param);
            if(scmInvStockTransferBillEntryList != null && !scmInvStockTransferBillEntryList.isEmpty()){
                for(ScmInvStockTransferBillEntry2 scmInvStockTransferBillEntry : scmInvStockTransferBillEntryList){
                    //查出调拨申请单明细
                	ScmInvMoveReqBillEntry2 scmInvMoveReqBillEntry = 
                    		scmInvMoveReqBillEntryBiz.select(scmInvStockTransferBillEntry.getSourceBillDtlId(), param);
                    if (scmInvMoveReqBillEntry != null) {
	                    scmInvMoveReqBillEntry.setMoveBaseQty(BigDecimal.ZERO);
	                    scmInvMoveReqBillEntryBiz.update(scmInvMoveReqBillEntry, param);
                    }
                }
            }
			
			//删除调拨单明细
			scmInvStockTransferBillEntryBiz.deleteByWtId(entity.getWtId(), param);
		}
	}
	
	@Override
	public ScmInvStockTransferBill2 updateStatus(ScmInvStockTransferBill2 scmInvStockTransferBill, List<ScmInvStockTransferBillEntry2> scmInvStockTransferBillEntryList, Param param) throws AppException {
		if(scmInvStockTransferBill != null){
			ScmInvStockTransferBill2 scmInvStockTransferBill2 = this.updateDirect(scmInvStockTransferBill, param);
			if(scmInvStockTransferBill2 != null && scmInvStockTransferBill2.getWtId() > 0){
				if(scmInvStockTransferBillEntryList != null && !scmInvStockTransferBillEntryList.isEmpty()){
					scmInvStockTransferBillEntryBiz.update(scmInvStockTransferBillEntryList, param);
				}
				return scmInvStockTransferBill2;
			}
		}
		return null;
	}

    @Override
    public void generateMoveIssue(ScmInvStockTransferBill2 scmInvStockTransferBill, Param param) throws AppException {
        
        List<ScmInvStockTransferBillEntry2> scmInvStockTransferBillEntryList = scmInvStockTransferBillEntryBiz.selectByWtId(scmInvStockTransferBill.getWtId(), param);
        if (scmInvStockTransferBillEntryList == null || scmInvStockTransferBillEntryList.isEmpty()) {
            throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvStockTransferBillBizImpl.generateMoveIssue.notExitTransferDetail");
        }
        CommonBean bean = new CommonBean();
        List<ScmInvMoveIssueBill2> scmInvMoveIssueBillList = new ArrayList<>();
        List<ScmInvMoveIssueBillEntry2> scmInvMoveIssueBillEntryList = new ArrayList<>();
        ScmInvMoveIssueBill2 scmInvMoveIssueBill = new ScmInvMoveIssueBill2(true);
        // 调拨出库主表
        // STO20191112001
        scmInvMoveIssueBill.setStatus("I");
        scmInvMoveIssueBill.setBizType("6");
        scmInvMoveIssueBill.setBizDate(FormatUtils.parseDate(FormatUtils.fmtDate(CalendarUtil.getDate(param))));
        scmInvMoveIssueBill.setFinOrgUnitNo(scmInvStockTransferBill.getFinOrgUnitNo());
        scmInvMoveIssueBill.setOrgUnitNo(scmInvStockTransferBill.getOutOrgUnitNo());
        scmInvMoveIssueBill.setWareHouseId(scmInvStockTransferBill.getWareHouseId());
        scmInvMoveIssueBill.setToOrgUnitNo(scmInvStockTransferBill.getInOrgUnitNo());
        // TODO 来源单类型(调拨单)
        scmInvMoveIssueBill.setBillType("InvStockTransfer");
        scmInvMoveIssueBill.setCurrencyNo(scmInvStockTransferBill.getCurrencyNo());
        scmInvMoveIssueBill.setExchangeRate(scmInvStockTransferBill.getExchangeRate());
        // TODO 主表金额, 税额
        scmInvMoveIssueBill.setAmt(BigDecimal.ZERO);
        scmInvMoveIssueBill.setTaxAmt(BigDecimal.ZERO);
        
        scmInvMoveIssueBill.setCreator(param.getUsrCode());
        scmInvMoveIssueBill.setCreateDate(CalendarUtil.getDate(param));
        scmInvMoveIssueBill.setCreateOrgUnitNo(param.getOrgUnitNo());
        scmInvMoveIssueBill.setControlUnitNo(param.getControlUnitNo());
        scmInvMoveIssueBill.setRemarks(scmInvStockTransferBill.getRemarks());
        scmInvMoveIssueBillList.add(scmInvMoveIssueBill);
        bean.setList(scmInvMoveIssueBillList);
        
        // 调拨出库明细表
        for (int i = 0; i < scmInvStockTransferBillEntryList.size(); i++) {
            ScmInvMoveIssueBillEntry2 scmInvMoveIssueBillEntry = new ScmInvMoveIssueBillEntry2(true);
            scmInvMoveIssueBillEntry.setLineId(i+1);
            scmInvMoveIssueBillEntry.setItemId(scmInvStockTransferBillEntryList.get(i).getItemId());
            scmInvMoveIssueBillEntry.setOrgUnitNo(scmInvStockTransferBill.getOutOrgUnitNo());
            scmInvMoveIssueBillEntry.setWareHouseId(scmInvStockTransferBillEntryList.get(i).getWareHouseId());
            scmInvMoveIssueBillEntry.setUnit(scmInvStockTransferBillEntryList.get(i).getUnit());
            scmInvMoveIssueBillEntry.setPieUnit(scmInvStockTransferBillEntryList.get(i).getPieUnit());
            scmInvMoveIssueBillEntry.setBaseUnit(scmInvStockTransferBillEntryList.get(i).getBaseUnit());
            scmInvMoveIssueBillEntry.setLot(scmInvStockTransferBillEntryList.get(i).getLot());
            scmInvMoveIssueBillEntry.setQty(scmInvStockTransferBillEntryList.get(i).getQty());
            scmInvMoveIssueBillEntry.setPieQty(scmInvStockTransferBillEntryList.get(i).getPieQty());
            scmInvMoveIssueBillEntry.setBaseQty(scmInvStockTransferBillEntryList.get(i).getBaseQty());
            scmInvMoveIssueBillEntry.setPrice(scmInvStockTransferBillEntryList.get(i).getPrice());
            scmInvMoveIssueBillEntry.setAmt(scmInvStockTransferBillEntryList.get(i).getAmt());
            scmInvMoveIssueBillEntry.setTaxRate(scmInvStockTransferBillEntryList.get(i).getTaxRate());
            scmInvMoveIssueBillEntry.setTaxPrice(scmInvStockTransferBillEntryList.get(i).getTaxPrice());
            scmInvMoveIssueBillEntry.setTaxAmt(scmInvStockTransferBillEntryList.get(i).getTaxAmt());
            scmInvMoveIssueBillEntry.setExpDate(scmInvStockTransferBillEntryList.get(i).getExpDate());
            scmInvMoveIssueBillEntry.setSourceBillDtlId(scmInvStockTransferBillEntryList.get(i).getId());
            scmInvMoveIssueBillEntry.setRemarks(scmInvStockTransferBillEntryList.get(i).getRemarks());
            scmInvMoveIssueBillEntryList.add(scmInvMoveIssueBillEntry);
            // 回写数据
            scmInvStockTransferBillEntryList.get(i).setIssueBaseQty(scmInvStockTransferBillEntryList.get(i).getBaseQty());
            scmInvStockTransferBillEntryBiz.update(scmInvStockTransferBillEntryList.get(i), param);
        }
        bean.setList2(scmInvMoveIssueBillEntryList);
        scmInvMoveIssueBillBiz.add(bean, param);
        
    }

	@Override
	protected void beforeDelete(ScmInvStockTransferBill2 entity, Param param)
			throws AppException {
		ScmInvStockTransferBill2 scmInvStockTransferBill = this.selectDirect(entity.getWtId(), param);
		if(scmInvStockTransferBill!=null && !StringUtils.equals(scmInvStockTransferBill.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getWtNo()}));
		}
	}
	
	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvStockTransferBill2 entry = (ScmInvStockTransferBill2) bean.getList().get(0);
		if(entry!=null){
			ScmInvStockTransferBill2 scmInvStockTransferBill = this.selectDirect(entry.getPK(), param);
			if(!StringUtils.equals(scmInvStockTransferBill.getStatus(),"I")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
		}
	}

	@Override
	public ScmInvStockTransferBill2 updatePrtCount(ScmInvStockTransferBill2 scmInvStockTransferBill, Param param)
			throws AppException {
		if(scmInvStockTransferBill.getWtId()>0){
			ScmInvStockTransferBill2 bill = this.selectDirect(scmInvStockTransferBill.getWtId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmInvStockTransferBill;
	}

}
