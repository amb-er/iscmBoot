package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.OperationParam;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.Param.ParamType;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.base.model.SimpleDataDisplay;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.ListSortUtil;
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBillEntry2;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.service.ScmInvStockTransferBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvBalBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvCostBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.util.ScmWarehouseUtil;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvMoveInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMoveIssueBillDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBillAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMoveIssueBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMoveIssueBillEntryBiz;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.model.SystemState;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.service.SystemStateBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmInvMoveIssueBillBiz")
public class ScmInvMoveIssueBillBizImpl extends BaseBizImpl<ScmInvMoveIssueBill2> implements ScmInvMoveIssueBillBiz {
	
    private UsrBiz usrBiz;
    private OrgUnitBiz orgUnitBiz;
    private ScmInvMoveIssueBillEntryBiz scmInvMoveIssueBillEntryBiz;
    private PubSysBasicInfoBiz pubSysBasicInfoBiz;
    private ScmInvWareHouseBiz scmInvWareHouseBiz;
    private ScmInvStockTransferBillEntryBiz scmInvStockTransferBillEntryBiz;
    private ScmMaterialBiz scmMaterialBiz;
    private ScmInvStockBiz scmInvStockBiz;
    private ScmInvBalBiz scmInvBalBiz;
    private ScmInvCostBiz scmInvCostBiz;
    private OrgUnitRelationBiz orgUnitRelationBiz;
    private ScmInvMoveInWarehsBillBiz scmInvMoveInWarehsBillBiz;
    private PeriodCalendarBiz periodCalendarBiz;
    private SystemStateBiz systemStateBiz;
    private BillTypeBiz billTypeBiz;
    private SysParamBiz sysParamBiz;
    private OrgStorageBiz orgStorageBiz;
    
    public BillTypeBiz getBillTypeBiz() {
		return billTypeBiz;
	}

	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
        this.usrBiz = usrBiz;
    }

    public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
        this.orgUnitBiz = orgUnitBiz;
    }

    public void setScmInvMoveIssueBillEntryBiz(ScmInvMoveIssueBillEntryBiz scmInvMoveIssueBillEntryBiz) {
        this.scmInvMoveIssueBillEntryBiz = scmInvMoveIssueBillEntryBiz;
    }

    public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
        this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
    }

    public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
        this.scmInvWareHouseBiz = scmInvWareHouseBiz;
    }

    public void setScmInvStockTransferBillEntryBiz(ScmInvStockTransferBillEntryBiz scmInvStockTransferBillEntryBiz) {
        this.scmInvStockTransferBillEntryBiz = scmInvStockTransferBillEntryBiz;
    }

    public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
        this.scmMaterialBiz = scmMaterialBiz;
    }

    public void setScmInvStockBiz(ScmInvStockBiz scmInvStockBiz) {
        this.scmInvStockBiz = scmInvStockBiz;
    }

    public void setScmInvBalBiz(ScmInvBalBiz scmInvBalBiz) {
        this.scmInvBalBiz = scmInvBalBiz;
    }

    public void setScmInvCostBiz(ScmInvCostBiz scmInvCostBiz) {
        this.scmInvCostBiz = scmInvCostBiz;
    }

    public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
        this.orgUnitRelationBiz = orgUnitRelationBiz;
    }

    public void setScmInvMoveInWarehsBillBiz(ScmInvMoveInWarehsBillBiz scmInvMoveInWarehsBillBiz) {
        this.scmInvMoveInWarehsBillBiz = scmInvMoveInWarehsBillBiz;
    }

    public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}

	public void setSystemStateBiz(SystemStateBiz systemStateBiz) {
        this.systemStateBiz = systemStateBiz;
    }

    public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." + ScmInvMoveIssueBill2.FN_ORGUNITNO), 
	                new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." + ScmInvMoveIssueBill2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." + ScmInvMoveIssueBill2.FN_ORGUNITNO), 
	                new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." + ScmInvMoveIssueBill2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
    }
    
    @Override
    protected void afterFindPage(List list, Page page, Param param) throws AppException {
        if(list != null && !list.isEmpty()){
            for(ScmInvMoveIssueBill2 scmInvMoveIssueBill : (List<ScmInvMoveIssueBill2>)list){
                setConvertMap(scmInvMoveIssueBill,param);
            }
        }
    }
    
    @Override
    protected void afterSelect(ScmInvMoveIssueBill2 entity, Param param) throws AppException {
        setConvertMap(entity,param);
        if (StringUtils.isNotBlank(entity.getBillType())){
            //来源单类型
            BillType2 billType = billTypeBiz.selectByOrgAndCode(entity.getFinOrgUnitNo(), entity.getBillType(), param);
            if (billType != null) {
                entity.setConvertMap(ScmInvSaleOrder.FN_BILLTYPE, billType);
            }
        }
    }
    
    private void setConvertMap(ScmInvMoveIssueBill2 scmInvMoveIssueBill,Param param){
        if (StringUtils.isNotBlank(scmInvMoveIssueBill.getCreator())){
            //制单人
            Usr usr = usrBiz.selectByCode(scmInvMoveIssueBill.getCreator(), param);
            if (usr != null) {
                if(scmInvMoveIssueBill.getDataDisplayMap()==null){
                    scmInvMoveIssueBill.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
                }
                scmInvMoveIssueBill.getDataDisplayMap().put(ScmInvMoveIssueBill.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
                scmInvMoveIssueBill.setConvertMap(ScmInvMoveIssueBill.FN_CREATOR, usr);
            }
        }
        if (StringUtils.isNotBlank(scmInvMoveIssueBill.getOrgUnitNo())){
            //库存组织
            OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveIssueBill.getOrgUnitNo(), param);
            if (orgBaseUnit != null) {
                scmInvMoveIssueBill.setConvertMap(ScmInvMoveIssueBill.FN_ORGUNITNO, orgBaseUnit);
            }
        }
        if(scmInvMoveIssueBill.getWareHouseId()>0) {
        	ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvMoveIssueBill.getWareHouseId(), param);
            if (scmInvWareHouse != null) {
            	scmInvMoveIssueBill.setConvertMap(ScmInvMoveIssueBillEntry2.FN_WAREHOUSEID, scmInvWareHouse);
            }
        }
        if (StringUtils.isNotBlank(scmInvMoveIssueBill.getToOrgUnitNo())){
            //移入组织
            OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveIssueBill.getToOrgUnitNo(), param);
            if (orgBaseUnit != null) {
                scmInvMoveIssueBill.setConvertMap(ScmInvMoveIssueBill.FN_TOORGUNITNO, orgBaseUnit);
            }
        }
        if (StringUtils.isNotBlank(scmInvMoveIssueBill.getCreateOrgUnitNo())){
            //制单组织
            OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveIssueBill.getCreateOrgUnitNo(), param);
            if (orgBaseUnit != null) {
                scmInvMoveIssueBill.setConvertMap(ScmInvMoveIssueBill.FN_CREATEORGUNITNO, orgBaseUnit);
            }
        }
        if (StringUtils.isNotBlank(scmInvMoveIssueBill.getEditor())){
            //修改人
            Usr usr = usrBiz.selectByCode(scmInvMoveIssueBill.getEditor(), param);
            if (usr != null) {
                scmInvMoveIssueBill.setConvertMap(ScmInvMoveIssueBill.FN_EDITOR, usr);
            }
        }
        if (StringUtils.isNotBlank(scmInvMoveIssueBill.getChecker())){
            //审核人
            Usr usr = usrBiz.selectByCode(scmInvMoveIssueBill.getChecker(), param);
            if (usr != null) {
                scmInvMoveIssueBill.setConvertMap(ScmInvMoveIssueBill.FN_CHECKER, usr);
            }
        }
    }
    
    @Override
    protected void afterSelect(CommonBean bean, Param param) throws AppException {
        ScmInvMoveIssueBill2 scmInvMoveIssueBill = (ScmInvMoveIssueBill2) bean.getList().get(0);
        HashMap<String,Object> cacheMap = new HashMap<String,Object>();
        if(scmInvMoveIssueBill != null && scmInvMoveIssueBill.getOtId() > 0){
            List<ScmInvMoveIssueBillEntry2> scmInvMoveIssueBillEntryList = scmInvMoveIssueBillEntryBiz.selectByOtId(scmInvMoveIssueBill.getOtId(), param);
            if(scmInvMoveIssueBillEntryList != null && !scmInvMoveIssueBillEntryList.isEmpty()){
                for(ScmInvMoveIssueBillEntry2 scmInvMoveIssueBillEntry : scmInvMoveIssueBillEntryList){
                    if (scmInvMoveIssueBillEntry.getTaxRate() != null) {
                        //税率
                        PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmInvMoveIssueBillEntry.getTaxRate().toString(), null, param);
                        if (pubSysBasicInfo != null) {
                            scmInvMoveIssueBillEntry.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
                            scmInvMoveIssueBillEntry.setConvertMap(ScmInvMoveIssueBillEntry2.FN_TAXRATESTR, pubSysBasicInfo);
                        }
                        if (scmInvMoveIssueBillEntry.getWareHouseId() > 0) {
                            //仓库
                            ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvMoveIssueBillEntry.getWareHouseId(), param);
                            if (scmInvWareHouse != null) {
                                scmInvMoveIssueBillEntry.setConvertMap(ScmInvMoveIssueBillEntry2.FN_WAREHOUSEID, scmInvWareHouse);
                            }
                        }
                        if (StringUtils.isNotBlank(scmInvMoveIssueBillEntry.getLot())) {
                            //批次
                            scmInvMoveIssueBillEntry.setConvertMap(ScmInvMoveIssueBillEntry2.FN_LOT, scmInvMoveIssueBillEntry);
                        }
                        if (scmInvMoveIssueBillEntry.getReceiptWareHouseId() > 0) {
                            //移入仓库
                            ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvMoveIssueBillEntry.getReceiptWareHouseId(), param);
                            if (scmInvWareHouse != null) {
                                scmInvMoveIssueBillEntry.setConvertMap(ScmInvMoveIssueBillEntry2.FN_RECEIPTWAREHOUSEID, scmInvWareHouse);
                            }
                        }
                        if (StringUtils.isNotBlank(scmInvMoveIssueBillEntry.getUseOrgUnitNo())) {
                            //移入仓库
                            OrgBaseUnit useOrgUnitNo = orgUnitBiz.selectbyOrgNo(scmInvMoveIssueBillEntry.getUseOrgUnitNo(), param);
                            if (useOrgUnitNo != null) {
                                scmInvMoveIssueBillEntry.setConvertMap(ScmInvMoveIssueBillEntry2.FN_USEORGUNITNO, useOrgUnitNo);
                            }
                        }
                    }
                }
                bean.setList2(scmInvMoveIssueBillEntryList);
            }
        }
    }
    @Override
	protected void beforeAdd(CommonBean bean, Param param) throws AppException {
    	ScmInvMoveIssueBill2 scmInvMoveIssueBill = (ScmInvMoveIssueBill2) bean.getList().get(0);
        if(scmInvMoveIssueBill != null){
        	String code = CodeAutoCalUtil.getBillCode("InvMoveIssueBill", scmInvMoveIssueBill, param);
        	scmInvMoveIssueBill.setOtNo(code);
            //获取期间
			PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(scmInvMoveIssueBill.getBizDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
			}
			scmInvMoveIssueBill.setPeriodId(periodCalendar.getPeriodId());
			scmInvMoveIssueBill.setAccountYear(periodCalendar.getAccountYear());
			scmInvMoveIssueBill.setAccountPeriod(periodCalendar.getAccountPeriod());
			List<ScmInvMoveIssueBillEntry2> scmInvMoveIssueBillEntryList = bean.getList2();
            BigDecimal amt= BigDecimal.ZERO;
            BigDecimal taxAmt= BigDecimal.ZERO;
            if(scmInvMoveIssueBillEntryList != null && !scmInvMoveIssueBillEntryList.isEmpty()){
                for(ScmInvMoveIssueBillEntry2 scmInvMoveIssueBillEntry : scmInvMoveIssueBillEntryList){
                    amt = amt.add(scmInvMoveIssueBillEntry.getAmt());
                    taxAmt = taxAmt.add(scmInvMoveIssueBillEntry.getTaxAmt());
                }
            }
            scmInvMoveIssueBill.setAmt(amt);
            scmInvMoveIssueBill.setTaxAmt(taxAmt);
        }
    }
     
	@Override
    protected void afterAdd(CommonBean bean, Param param) throws AppException {
        ScmInvMoveIssueBill2 scmInvMoveIssueBill = (ScmInvMoveIssueBill2) bean.getList().get(0);
        if(scmInvMoveIssueBill != null && scmInvMoveIssueBill.getOtId() > 0){
            //新增收货明细
            List<ScmInvMoveIssueBillEntry2> scmInvMoveIssueBillEntryList = bean.getList2();
            if(scmInvMoveIssueBillEntryList != null && !scmInvMoveIssueBillEntryList.isEmpty()){
                int lineId = 1;
                for(ScmInvMoveIssueBillEntry2 scmInvMoveIssueBillEntry : scmInvMoveIssueBillEntryList){
                    scmInvMoveIssueBillEntry.setLineId(lineId);
                    scmInvMoveIssueBillEntry.setOtId(scmInvMoveIssueBill.getOtId());
                    scmInvMoveIssueBillEntry.setWareHouseId(scmInvMoveIssueBill.getWareHouseId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvMoveIssueBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvMoveIssueBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvMoveIssueBillEntry.getItemId(), scmInvMoveIssueBillEntry.getUnit(), param);//库存单位转换系数
					scmInvMoveIssueBillEntry.setBaseQty(scmInvMoveIssueBillEntry.getQty().multiply(invConvRate));
                    scmInvMoveIssueBillEntryBiz.add(scmInvMoveIssueBillEntry, param);
                    lineId = lineId+1;
                }
            }
        }
    }
    
    @Override
    protected void afterUpdate(CommonBean bean, Param param) throws AppException {
        ScmInvMoveIssueBill2 scmInvMoveIssueBill = (ScmInvMoveIssueBill2) bean.getList().get(0);
        if(scmInvMoveIssueBill != null && scmInvMoveIssueBill.getOtId() > 0){
            //更新销售单明细
            List<ScmInvMoveIssueBillEntry2> scmInvMoveIssueBillEntryList = bean.getList2();
            if(scmInvMoveIssueBillEntryList != null && !scmInvMoveIssueBillEntryList.isEmpty()){
                int lineId = 1;
                for(ScmInvMoveIssueBillEntry2 scmInvMoveIssueBillEntry : scmInvMoveIssueBillEntryList){
                	if (StringUtils.equals("I", scmInvMoveIssueBill.getStatus())) {
                		scmInvMoveIssueBillEntry.setLineId(lineId);
                	}
                    scmInvMoveIssueBillEntry.setOtId(scmInvMoveIssueBill.getOtId());
                    scmInvMoveIssueBillEntry.setWareHouseId(scmInvMoveIssueBill.getWareHouseId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvMoveIssueBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvMoveIssueBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvMoveIssueBillEntry.getItemId(), scmInvMoveIssueBillEntry.getUnit(), param);//库存单位转换系数
					scmInvMoveIssueBillEntry.setBaseQty(scmInvMoveIssueBillEntry.getQty().multiply(invConvRate));
                    scmInvMoveIssueBillEntry.setOtId(scmInvMoveIssueBill.getOtId());
                    lineId = lineId+1;
                }
                scmInvMoveIssueBillEntryBiz.update(scmInvMoveIssueBill, scmInvMoveIssueBillEntryList, ScmInvMoveIssueBillEntry2.FN_OTID, param);
            }
        }
    }
    
    @Override
    protected void afterDelete(ScmInvMoveIssueBill2 entity, Param param) throws AppException {
        if(entity != null && entity.getOtId() > 0){
            //回写调拨单出库基本数量
            //查出出库明细明细
            List<ScmInvMoveIssueBillEntry2> scmInvMoveIssueBillEntryList = scmInvMoveIssueBillEntryBiz.selectByOtId(entity.getOtId(), param);
            if(scmInvMoveIssueBillEntryList != null && !scmInvMoveIssueBillEntryList.isEmpty()){
                for(ScmInvMoveIssueBillEntry2 scmInvMoveIssueBillEntry : scmInvMoveIssueBillEntryList){
                    //查出调拨单明细
                    ScmInvStockTransferBillEntry2 scmInvStockTransferBillEntry = 
                            scmInvStockTransferBillEntryBiz.select(scmInvMoveIssueBillEntry.getSourceBillDtlId(), param);
                    if (scmInvStockTransferBillEntry == null) {
                        throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvStockTransferBillBizImpl.generateMoveIssue.notExitTransferDetail");
                    }
                    scmInvStockTransferBillEntry.setIssueBaseQty(BigDecimal.ZERO);
                    scmInvStockTransferBillEntryBiz.update(scmInvStockTransferBillEntry, param);
                }
            }
            //删除出库明细
            scmInvMoveIssueBillEntryBiz.deleteByOtId(entity.getOtId(), param);
        }
    }

    public ScmInvMoveIssueBill2 updateStatus(ScmInvMoveIssueBill2 scmInvMoveIssueBill, Param param) throws AppException {
        if(scmInvMoveIssueBill != null){
            ScmInvMoveIssueBill2 scmInvMoveIssueBill2 = this.updateDirect(scmInvMoveIssueBill, param);
            if(scmInvMoveIssueBill2 != null){
                return scmInvMoveIssueBill2;
            }
        }
        return null;
    }

    @Override
    public ScmInvMoveIssueBill2 postBill(ScmInvMoveIssueBill2 scmInvMoveIssueBill, Param param)
            throws AppException {
    	scmInvMoveIssueBill = this.selectDirect(scmInvMoveIssueBill.getOtId(), param);
    	if(scmInvMoveIssueBill!=null) {
	        if(!StringUtils.equals("A",scmInvMoveIssueBill.getStatus())) {
	        	throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvMoveIssueBill.getOtNo()}));
			}
			// 1 查出明细拆单
			List<ScmInvMoveIssueBillEntry2> scmInvMoveIssueBillEntryList = scmInvMoveIssueBillEntryBiz.selectByOtId(scmInvMoveIssueBill.getOtId(), param);
			if (scmInvMoveIssueBillEntryList != null && !scmInvMoveIssueBillEntryList.isEmpty()) {
				scmInvMoveIssueBillEntryList = (List<ScmInvMoveIssueBillEntry2>) ListSortUtil.sort(scmInvMoveIssueBillEntryList, "lineId","Desc"); // 按行号排序
				int amtPrecision = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
				HashMap<String, List<ScmInvStock2>> qtyMap = new HashMap<String, List<ScmInvStock2>>();
				int lineId=1;
				for (ScmInvMoveIssueBillEntry2 scmInvMoveIssueBillEntry2 : scmInvMoveIssueBillEntryList) {
					// 2 有没有批次都要拆单, 查询结存(先进先出还是后进先出)
					StringBuffer info = new StringBuffer("");
					info.append(scmInvMoveIssueBillEntry2.getOrgUnitNo()).append("_")
							.append(scmInvMoveIssueBillEntry2.getWareHouseId()).append("_")
							.append(String.valueOf(scmInvMoveIssueBillEntry2.getItemId())).append("_");
					if (!qtyMap.containsKey((info.toString()))) {
						Page page = new Page();
						page.setModelClass(ScmMaterial2.class);
						page.setShowCount(Integer.MAX_VALUE);
						page.setSqlCondition("ScmMaterial.id=" + scmInvMoveIssueBillEntry2.getItemId());
						List<String> argList = new ArrayList<String>();
						argList.add("orgUnitNo=" + param.getOrgUnitNo());
						argList.add("controlUnitNo=" + param.getControlUnitNo());
						List<ScmMaterial2> materialList = scmMaterialBiz.queryPage(page, argList, "findBySingleFinAllPage",param);
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("orgUnitNo", scmInvMoveIssueBill.getOrgUnitNo());
						map.put("itemId", scmInvMoveIssueBillEntry2.getItemId());
						map.put("wareHouseId", scmInvMoveIssueBillEntry2.getWareHouseId());
						map.put("bizDate", FormatUtils.fmtDate(scmInvMoveIssueBill.getBizDate()));
						map.put("unit", scmInvMoveIssueBillEntry2.getUnit());
						// 获取costMethod来决定升序还是降序排序
						if (materialList != null && materialList.size() > 0) {
							map.put("costMethod", materialList.get(0).getCosting());
						}
						List<ScmInvStock2> stocklist = scmInvStockBiz.findByDate(map, param);
						if (stocklist == null || stocklist.size() == 0) {
							throw new AppException(
									"com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl.common.stockNotEnough",
									new String[] { materialList.get(0).getItemName() });
						}
						qtyMap.put(info.toString(), stocklist);
					}
					List<ScmInvStock2> stocklist = qtyMap.get(info.toString());
					if (stocklist != null && !stocklist.isEmpty()) {
						if (StringUtils.isNotBlank(scmInvMoveIssueBillEntry2.getLot())) {
							// 有批次则先按批次找，如不够再按先进先出拆单
							for (ScmInvStock2 scmInvStock : stocklist) {
								if (StringUtils.equals(scmInvMoveIssueBillEntry2.getLot(), scmInvStock.getLot())) {
									if (scmInvMoveIssueBillEntry2.getQty().compareTo(BigDecimal.ZERO) > 0) {
										if(setDataFromStock(scmInvStock, scmInvMoveIssueBillEntry2, amtPrecision,lineId, param))
											lineId = lineId + 1;
									} else {
										break;
									}
								}
							}
						}
						for (ScmInvStock2 scmInvStock : stocklist) {
							if (scmInvMoveIssueBillEntry2.getQty().compareTo(BigDecimal.ZERO) > 0) {
								if(setDataFromStock(scmInvStock, scmInvMoveIssueBillEntry2, amtPrecision,lineId, param))
									lineId = lineId + 1;
							} else {
								break;
							}
						}
					}
					// 删除原来的明细
					scmInvMoveIssueBillEntryBiz.deleteById(scmInvMoveIssueBillEntry2.getId(), param);
				}
			} 
            
            // 2 过账
            // 更新结存
            scmInvMoveIssueBillEntryList = scmInvMoveIssueBillEntryBiz.selectOutEffectRow(scmInvMoveIssueBill.getOtId(), param);
            int updateRow = scmInvStockBiz.updateByMoveIssuePost(scmInvMoveIssueBill.getOtId(), param);
            if(updateRow<scmInvMoveIssueBillEntryList.size()){
    			throw new AppException("iscm.inventorymanage.common.post.error.rowsnotequal");
    		}
            // 更新期间余额表
            scmInvBalBiz.updateByMoveIssuePost(scmInvMoveIssueBill.getOtId(), param);
            // 更新移动平均即时成本表
//            scmInvCostBiz.updateByMoveIssuePost(scmInvMoveIssueBill.getOtId(), param);
			if (!(ParamType.Operation.equals(param.getParamType()) && ((OperationParam) param).getOperationId()==629223199L)) {
	            scmInvMoveIssueBill.setCheckDate(CalendarUtil.getDate(param));
	            scmInvMoveIssueBill.setChecker(param.getUsrCode());
	            scmInvMoveIssueBill.setPostDate(CalendarUtil.getDate(param));
			}
            scmInvMoveIssueBill.setStatus("E");
            updateRow = this.updatePostedStatus(scmInvMoveIssueBill, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvMoveIssueBill.getOtNo()}));
			}
			if (!(ParamType.Operation.equals(param.getParamType()) && ((OperationParam) param).getOperationId()==629223199L)) {
				// 3 过账生成调拨入库单, 明细为拆单后的明细
	            generateInWaresBill(scmInvMoveIssueBill, param);
			}

        }
        return scmInvMoveIssueBill;
    }

    private int updatePostedStatus(ScmInvMoveIssueBill2 scmInvMoveIssueBill, Param param) {
    	HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("otId",scmInvMoveIssueBill.getOtId());
		map.put("checker",scmInvMoveIssueBill.getChecker());
		map.put("checkDate",scmInvMoveIssueBill.getCheckDate()==null?null:FormatUtils.fmtDate(scmInvMoveIssueBill.getCheckDate()));
		map.put("status", scmInvMoveIssueBill.getStatus());
		map.put("postDate", scmInvMoveIssueBill.getPostDate()==null?null:FormatUtils.fmtDateTime(scmInvMoveIssueBill.getPostDate()));
		return ((ScmInvMoveIssueBillDAO)dao).updatePostedStatus(map);
	}

	private boolean setDataFromStock(ScmInvStock2 scmInvStock,ScmInvMoveIssueBillEntry2 scmInvMoveIssueBillEntry2,int amtPrecision,Integer lineId,Param param) {
    	boolean flag = false;
    	BigDecimal stockQty = scmInvStock.getQty();
		BigDecimal stockPieQty = scmInvStock.getPieQty();
		BigDecimal stockBaseQty = scmInvStock.getBaseQty();
		BigDecimal stockAmt = (scmInvStock.getAmt());
		BigDecimal stockTaxAmt = (scmInvStock.getTaxAmt());
		if (stockQty.compareTo(scmInvMoveIssueBillEntry2.getQty()) > 0) {
			ScmInvMoveIssueBillEntry2 scmInvMoveIssueBillEntry = new ScmInvMoveIssueBillEntry2();
			BeanUtils.copyProperties(scmInvMoveIssueBillEntry2,scmInvMoveIssueBillEntry, new String[] { "id" });
			scmInvMoveIssueBillEntry.setLot(scmInvStock.getLot());
			scmInvMoveIssueBillEntry.setStockId(scmInvStock.getId());
			scmInvMoveIssueBillEntry.setPrice(scmInvStock.getPrice());
			scmInvMoveIssueBillEntry.setTaxRate(scmInvStock.getTaxRate());
			scmInvMoveIssueBillEntry.setTaxPrice(scmInvStock.getTaxPrice());
			scmInvMoveIssueBillEntry.setBaseUnit(scmInvStock.getBaseUnit());
			scmInvMoveIssueBillEntry.setAmt(scmInvMoveIssueBillEntry.getQty().multiply(scmInvMoveIssueBillEntry.getPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
			scmInvMoveIssueBillEntry.setTaxAmt(scmInvMoveIssueBillEntry.getQty().multiply(scmInvMoveIssueBillEntry.getTaxPrice()).setScale(amtPrecision, RoundingMode.HALF_UP));
			scmInvMoveIssueBillEntry.setOrgSourceId(scmInvStock.getSourceBillId());
			scmInvMoveIssueBillEntry.setOrgSourceVendorId(scmInvStock.getVendorId());
			scmInvMoveIssueBillEntry.setLineId(lineId);
			scmInvMoveIssueBillEntryBiz.add(scmInvMoveIssueBillEntry, param);
			scmInvMoveIssueBillEntry2.setQty(BigDecimal.ZERO);
			scmInvMoveIssueBillEntry2.setPieQty(BigDecimal.ZERO);
			scmInvMoveIssueBillEntry2.setBaseQty(BigDecimal.ZERO);
    		scmInvStock.setQty(scmInvStock.getQty().subtract(scmInvMoveIssueBillEntry.getQty()));
    		scmInvStock.setPieQty(scmInvStock.getPieQty().subtract(scmInvMoveIssueBillEntry.getPieQty()));
    		scmInvStock.setBaseQty(scmInvStock.getBaseQty().subtract(scmInvMoveIssueBillEntry.getBaseQty()));
    		scmInvStock.setAmt(scmInvStock.getAmt().subtract(scmInvMoveIssueBillEntry.getAmt()));
    		scmInvStock.setTaxAmt(scmInvStock.getTaxAmt().subtract(scmInvMoveIssueBillEntry.getTaxAmt()));
    		flag = true;
		} else if (stockQty.compareTo(BigDecimal.ZERO) > 0) {
			ScmInvMoveIssueBillEntry2 scmInvMoveIssueBillEntry = new ScmInvMoveIssueBillEntry2();
			BeanUtils.copyProperties(scmInvMoveIssueBillEntry2,scmInvMoveIssueBillEntry, new String[] { "id" });
			scmInvMoveIssueBillEntry.setLot(scmInvStock.getLot());
			scmInvMoveIssueBillEntry.setStockId(scmInvStock.getId());
			scmInvMoveIssueBillEntry.setPrice(scmInvStock.getPrice());
			scmInvMoveIssueBillEntry.setQty(stockQty);
			scmInvMoveIssueBillEntry.setPieQty(stockPieQty);
			scmInvMoveIssueBillEntry.setBaseQty(stockBaseQty);
			scmInvMoveIssueBillEntry.setTaxRate(scmInvStock.getTaxRate());
			scmInvMoveIssueBillEntry.setTaxPrice(scmInvStock.getTaxPrice());
			scmInvMoveIssueBillEntry.setBaseUnit(scmInvStock.getBaseUnit());
			scmInvMoveIssueBillEntry.setAmt(stockAmt);
			scmInvMoveIssueBillEntry.setTaxAmt(stockTaxAmt);
			scmInvMoveIssueBillEntry.setOrgSourceId(scmInvStock.getSourceBillId());
			scmInvMoveIssueBillEntry.setOrgSourceVendorId(scmInvStock.getVendorId());
			scmInvMoveIssueBillEntry.setLineId(lineId);
			scmInvMoveIssueBillEntryBiz.add(scmInvMoveIssueBillEntry, param);
			scmInvMoveIssueBillEntry2.setQty((scmInvMoveIssueBillEntry2.getQty()).subtract(scmInvMoveIssueBillEntry.getQty()));
			scmInvMoveIssueBillEntry2.setBaseQty((scmInvMoveIssueBillEntry2.getBaseQty()).subtract(scmInvMoveIssueBillEntry.getBaseQty()));
			scmInvMoveIssueBillEntry2.setPieQty((scmInvMoveIssueBillEntry2.getPieQty()).subtract(scmInvMoveIssueBillEntry.getPieQty()));
			scmInvStock.setQty(BigDecimal.ZERO);
    		scmInvStock.setPieQty(BigDecimal.ZERO);
    		scmInvStock.setBaseQty(BigDecimal.ZERO);
    		scmInvStock.setAmt(BigDecimal.ZERO);
    		scmInvStock.setTaxAmt(BigDecimal.ZERO);
    		flag = true;
		}
		return flag;
    }
    private void generateInWaresBill(ScmInvMoveIssueBill2 scmInvMoveIssueBill, Param param) {
    	List<ScmInvMoveIssueBillEntry2> scmInvMoveIssueBillEntryList = scmInvMoveIssueBillEntryBiz.selectByOtId(scmInvMoveIssueBill.getOtId(), param);
        CommonBean bean = new CommonBean();
        List<ScmInvMoveInWarehsBill2> scmInvMoveInWarehsBillList = new ArrayList<>();
        List<ScmInvMoveInWarehsBillEntry2> scmInvMoveInWarehsBillEntryList = new ArrayList<>();
        ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill = new ScmInvMoveInWarehsBill2(true);
        // 调拨入库主表
        scmInvMoveInWarehsBill.setStatus("I");
        scmInvMoveInWarehsBill.setBizType("13");
        scmInvMoveInWarehsBill.setBizDate(FormatUtils.parseDate(FormatUtils.fmtDate(CalendarUtil.getDate(param))));
        // 财务组织
        List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, scmInvMoveIssueBill.getToOrgUnitNo(), false, null, param);
        if(orgCompanyList==null || orgCompanyList.isEmpty()){
          throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
        }
        scmInvMoveInWarehsBill.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
        scmInvMoveInWarehsBill.setOrgUnitNo(scmInvMoveIssueBill.getToOrgUnitNo());
        scmInvMoveInWarehsBill.setWareHouseId(scmInvMoveIssueBillEntryList.get(0).getReceiptWareHouseId());
        scmInvMoveInWarehsBill.setFromOrgUnitNo(scmInvMoveIssueBill.getOrgUnitNo());
        // TODO 来源单类型(调拨出库单)
        scmInvMoveInWarehsBill.setBillType("InvMoveIssueBill");
        scmInvMoveInWarehsBill.setCurrencyNo(scmInvMoveIssueBill.getCurrencyNo());
        scmInvMoveInWarehsBill.setExchangeRate(scmInvMoveIssueBill.getExchangeRate());
        // TODO 主表金额, 税额
        scmInvMoveInWarehsBill.setAmt(scmInvMoveIssueBill.getAmt());
        scmInvMoveInWarehsBill.setTaxAmt(scmInvMoveIssueBill.getTaxAmt());
        
        scmInvMoveInWarehsBill.setCreator(param.getUsrCode());
        scmInvMoveInWarehsBill.setCreateDate(CalendarUtil.getDate(param));
        scmInvMoveInWarehsBill.setCreateOrgUnitNo(param.getOrgUnitNo());
        scmInvMoveInWarehsBill.setControlUnitNo(param.getControlUnitNo());
        scmInvMoveInWarehsBill.setRemarks(scmInvMoveIssueBill.getRemarks());
        scmInvMoveInWarehsBillList.add(scmInvMoveInWarehsBill);
        bean.setList(scmInvMoveInWarehsBillList);
        
        // 调拨入库明细表
        for (int i = 0; i < scmInvMoveIssueBillEntryList.size(); i++) {
            ScmInvMoveInWarehsBillEntry2 scmInvMoveInWarehsBillEntry = new ScmInvMoveInWarehsBillEntry2(true);
            scmInvMoveInWarehsBillEntry.setLineId(i+1);
            scmInvMoveInWarehsBillEntry.setItemId(scmInvMoveIssueBillEntryList.get(i).getItemId());
            scmInvMoveInWarehsBillEntry.setOrgUnitNo(scmInvMoveIssueBillEntryList.get(i).getReceiveOrgUnitNo());
            scmInvMoveInWarehsBillEntry.setWareHouseId(scmInvMoveIssueBillEntryList.get(i).getReceiptWareHouseId());
            scmInvMoveInWarehsBillEntry.setUseOrgUnitNo(scmInvMoveIssueBillEntryList.get(i).getUseOrgUnitNo());
            ScmMaterial2 scmMaterial = scmMaterialBiz.findByInvItemId(param.getControlUnitNo(),scmInvMoveIssueBillEntryList.get(i).getReceiveOrgUnitNo(),scmInvMoveIssueBillEntryList.get(i).getItemId(), param);
            if(scmMaterial==null)
                throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurOrderBizImpl.generatePvOrder.error.notScmMaterial");
            if (scmMaterial.getUnitId() < 0) {
                throw new AppException("iscm.inventorymanage.warehouseoutbusiness.ScmInvMoveIssueBillBizImpl.generateInWaresBill.error.noUnit", new String[] {scmMaterial.getItemNo()});
            }
            scmInvMoveInWarehsBillEntry.setUnit(scmMaterial.getUnitId());
            scmInvMoveInWarehsBillEntry.setPieUnit(scmMaterial.getPieUnitId());
            scmInvMoveInWarehsBillEntry.setBaseUnit(scmInvMoveIssueBillEntryList.get(i).getBaseUnit());
            scmInvMoveInWarehsBillEntry.setLot(scmInvMoveIssueBillEntryList.get(i).getLot());
            scmInvMoveInWarehsBillEntry.setQty(scmInvMoveIssueBillEntryList.get(i).getQty());
            scmInvMoveInWarehsBillEntry.setPieQty(scmInvMoveIssueBillEntryList.get(i).getPieQty());
            scmInvMoveInWarehsBillEntry.setBaseQty(scmInvMoveIssueBillEntryList.get(i).getBaseQty());
            scmInvMoveInWarehsBillEntry.setPrice(scmInvMoveIssueBillEntryList.get(i).getPrice());
            scmInvMoveInWarehsBillEntry.setAmt(scmInvMoveIssueBillEntryList.get(i).getAmt());
            scmInvMoveInWarehsBillEntry.setTaxRate(scmInvMoveIssueBillEntryList.get(i).getTaxRate());
            scmInvMoveInWarehsBillEntry.setTaxPrice(scmInvMoveIssueBillEntryList.get(i).getTaxPrice());
            scmInvMoveInWarehsBillEntry.setTaxAmt(scmInvMoveIssueBillEntryList.get(i).getTaxAmt());
            scmInvMoveInWarehsBillEntry.setExpDate(scmInvMoveIssueBillEntryList.get(i).getExpDate());
            scmInvMoveInWarehsBillEntry.setSourceBillDtlId(scmInvMoveIssueBillEntryList.get(i).getSourceBillDtlId());
            scmInvMoveInWarehsBillEntry.setOutBillDtlId(scmInvMoveIssueBillEntryList.get(i).getId());
            scmInvMoveInWarehsBillEntry.setOrgSourceId(scmInvMoveIssueBillEntryList.get(i).getOrgSourceId());
            scmInvMoveInWarehsBillEntry.setOrgSourceVendorId(scmInvMoveIssueBillEntryList.get(i).getOrgSourceVendorId());
            scmInvMoveInWarehsBillEntry.setRemarks(scmInvMoveIssueBillEntryList.get(i).getRemarks());
            scmInvMoveInWarehsBillEntryList.add(scmInvMoveInWarehsBillEntry);
            // 回写数据
            scmInvMoveIssueBillEntryList.get(i).setMoveIn(true);
            scmInvMoveIssueBillEntryBiz.update(scmInvMoveIssueBillEntryList.get(i), param);
        }
        bean.setList2(scmInvMoveInWarehsBillEntryList);
        scmInvMoveInWarehsBillBiz.add(bean, param);
        
    }

    @Override
    public List<String> postBillCheck(ScmInvMoveIssueBill2 scmInvMoveIssueBill, Param param) throws AppException {
        List<String> msgList = new ArrayList<String>();// 提示列表
        scmInvMoveIssueBill = this.selectDirect(scmInvMoveIssueBill.getOtId(), param);
        if(!StringUtils.equals("A",scmInvMoveIssueBill.getStatus())) {
			msgList.add(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvMoveIssueBill.getOtNo()}));
			return msgList;
		}
        SystemState systemState = systemStateBiz.selectBySystemId(scmInvMoveIssueBill.getFinOrgUnitNo(), 170, param);
        if(systemState==null){
            msgList.add(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
            return msgList;
        }
        if (systemState.getCurrentPeriodId() != scmInvMoveIssueBill.getPeriodId()) {
            msgList.add(Message.getMessage(param.getLang(), 
                    "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmInvMoveIssueBill.getOtNo()}));
            return msgList;
        }
        
        // 检查盘点物资冻结
        // 检查冻结仓库
        List<ScmInvMoveIssueBill2> scmInvMoveIssueBillList = ((ScmInvMoveIssueBillDAO) dao).checkWareHouseFree(scmInvMoveIssueBill.getOtId());
        if (scmInvMoveIssueBillList != null && !scmInvMoveIssueBillList.isEmpty()) {
        	msgList.add(Message.getMessage(param.getLang(), 
                    "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
            return msgList;
//        	for (ScmInvMoveIssueBill2 scmInvMoveIssueBill2 : scmInvMoveIssueBillList) {
//	            // 检查冻结物资
//	            HashMap<String,Object> map = new HashMap<String,Object>();
//	            map.put("taskId", scmInvMoveIssueBill2.getTaskId());
//	            map.put("otId", scmInvMoveIssueBill.getOtId());
//	            int count = ((ScmInvMoveIssueBillDAO) dao).checkMaterialFree(map);
//	            if (count > 0) {
//	                msgList.add(Message.getMessage(param.getLang(), 
//	                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
//	                return msgList;
//	            }
//        	}
        }
        
        List<ScmInvMoveIssueBillEntry2> list = scmInvMoveIssueBillEntryBiz.selectInvQty(scmInvMoveIssueBill.getOtId(), param);
        if (list != null && list.size() > 0) {
            if (StringUtils.isNotBlank(list.get(0).getLot())) {
                msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl.count", 
                        new String[] {list.get(0).getItemNo(), list.get(0).getItemName(), list.get(0).getLot(),
                                list.get(0).getQty().toString(), list.get(0).getInvQty().toString()}));
                return msgList;
            } else {
                msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl.count2", 
                        new String[] {list.get(0).getItemNo(), list.get(0).getItemName(), 
                                list.get(0).getQty().toString(), list.get(0).getInvQty().toString()}));
                return msgList;
            }
            
        }
        return msgList;
    }

    @Override
    public ScmInvMoveIssueBill2 cancelPostBill(ScmInvMoveIssueBill2 scmInvMoveIssueBill, Param param)
            throws AppException {
    	scmInvMoveIssueBill = this.selectDirect(scmInvMoveIssueBill.getOtId(), param);
    	if(scmInvMoveIssueBill!=null) {
	        if(!StringUtils.equals("E",scmInvMoveIssueBill.getStatus())) {
	        	throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvMoveIssueBill.getOtNo()}));
			}
	        
	        // 检查盘点物资冻结
	        // 检查冻结仓库
	        List<ScmInvMoveIssueBill2> scmInvMoveIssueBillList = ((ScmInvMoveIssueBillDAO) dao).checkWareHouseFree(scmInvMoveIssueBill.getOtId());
	        if (scmInvMoveIssueBillList != null && !scmInvMoveIssueBillList.isEmpty()) {
	        	throw new AppException(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
//	        	for (ScmInvMoveIssueBill2 scmInvMoveIssueBill2 : scmInvMoveIssueBillList) {
//		            // 检查冻结物资
//		            HashMap<String,Object> map = new HashMap<String,Object>();
//		            map.put("taskId", scmInvMoveIssueBill2.getTaskId());
//		            map.put("otId", scmInvMoveIssueBill.getOtId());
//		            int count = ((ScmInvMoveIssueBillDAO) dao).checkMaterialFree(map);
//		            if (count > 0) {
//		            	throw new AppException(Message.getMessage(param.getLang(), 
//		                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
//		            }
//	        	}
	        }
	        
	        // 更新结存
	        scmInvStockBiz.updateByMoveIssueUnPost(scmInvMoveIssueBill.getOtId(), param);
	        // 更新期间余额表
	        scmInvBalBiz.updateByMoveIssueUnPost(scmInvMoveIssueBill.getOtId(), param);
	        // 更新移动平均即时成本表
//	        scmInvCostBiz.updateByMoveIssueUnPost(scmInvMoveIssueBill.getOtId(), param);
	        scmInvMoveIssueBill.setCheckDate(null);
	        scmInvMoveIssueBill.setChecker("");
	        scmInvMoveIssueBill.setStatus("A");
	        int updateRow = this.updatePostedStatus(scmInvMoveIssueBill, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvMoveIssueBill.getOtNo()}));
			}	        
	        // 删除对应调拨入库单
	        deleteMoveInBill(scmInvMoveIssueBill, param);
	        return scmInvMoveIssueBill;
    	}
    	return null;
    }

    private void deleteMoveInBill(ScmInvMoveIssueBill2 scmInvMoveIssueBill, Param param) {
    	List<ScmInvMoveInWarehsBill2> scmInvMoveInWarehsBillList = scmInvMoveInWarehsBillBiz.selectByMoveIssue(scmInvMoveIssueBill.getOtId(),param);
    	if(scmInvMoveInWarehsBillList!=null && !scmInvMoveInWarehsBillList.isEmpty()) {
    		for(ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill:scmInvMoveInWarehsBillList) {
    			if(!StringUtils.equals("I", scmInvMoveInWarehsBill.getStatus())) {
    				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvMoveIssueBill.cancelpost.error.moveInWarehsBillnotnewstatus", new String[]{scmInvMoveInWarehsBill.getWrNo()}));
    			}
    		}
    		scmInvMoveInWarehsBillBiz.delete(scmInvMoveInWarehsBillList, param);
    	}
    }
	@Override
	protected void beforeDelete(ScmInvMoveIssueBill2 entity, Param param)
			throws AppException {
		ScmInvMoveIssueBill2 scmInvMoveIssueBill = this.selectDirect(entity.getOtId(), param);
		if(scmInvMoveIssueBill!=null && !StringUtils.equals(scmInvMoveIssueBill.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getOtNo()}));
		}
	}
    
	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvMoveIssueBill2 scmInvMoveIssueBill = (ScmInvMoveIssueBill2) bean.getList().get(0);
		if(scmInvMoveIssueBill!=null){
			ScmInvMoveIssueBill2 scmInvMoveIssueBill2 = this.select(scmInvMoveIssueBill.getPK(), param);
			if(!StringUtils.equals(scmInvMoveIssueBill2.getStatus(),"I")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
			//获取期间
			PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(scmInvMoveIssueBill.getBizDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
			}
			scmInvMoveIssueBill.setPeriodId(periodCalendar.getPeriodId());
			scmInvMoveIssueBill.setAccountYear(periodCalendar.getAccountYear());
			scmInvMoveIssueBill.setAccountPeriod(periodCalendar.getAccountPeriod());
			List<ScmInvMoveIssueBillEntry2> scmInvMoveIssueBillEntryList = bean.getList2();
            BigDecimal amt= BigDecimal.ZERO;
            BigDecimal taxAmt= BigDecimal.ZERO;
            if(scmInvMoveIssueBillEntryList != null && !scmInvMoveIssueBillEntryList.isEmpty()){
                for(ScmInvMoveIssueBillEntry2 scmInvMoveIssueBillEntry : scmInvMoveIssueBillEntryList){
                    amt = amt.add(scmInvMoveIssueBillEntry.getAmt());
                    taxAmt = taxAmt.add(scmInvMoveIssueBillEntry.getTaxAmt());
                }
            }
            scmInvMoveIssueBill.setAmt(amt);
            scmInvMoveIssueBill.setTaxAmt(taxAmt);
		}
	}

	@Override
	public List<ScmInvMoveIssueBill2> checkUnPostBill(String finOrgUnitNo,long periodId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmInvMoveIssueBillDAO)dao).checkUnPostBill(map);
	}

	@Override
	public List<ScmInvMoveIssueBill2> checkPostedBill(String finOrgUnitNo,long periodId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmInvMoveIssueBillDAO)dao).checkPostedBill(map);
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvMoveIssueBillAdvQuery) {
				ScmInvMoveIssueBillAdvQuery scmInvMoveIssueBillAdvQuery = (ScmInvMoveIssueBillAdvQuery) page.getModel();
				if(scmInvMoveIssueBillAdvQuery.getWareHouseId()>0) {
					page.getParam().put(ScmInvMoveIssueBill2.FN_WAREHOUSEID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." +ScmInvMoveIssueBill2.FN_WAREHOUSEID, QueryParam.QUERY_EQ,String.valueOf(scmInvMoveIssueBillAdvQuery.getWareHouseId())));
				}
				if(scmInvMoveIssueBillAdvQuery.getBizDateFrom()!=null){
					if(scmInvMoveIssueBillAdvQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmInvMoveIssueBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." +ScmInvMoveIssueBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvMoveIssueBillAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(scmInvMoveIssueBillAdvQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmInvMoveIssueBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." +ScmInvMoveIssueBill2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvMoveIssueBillAdvQuery.getBizDateFrom())));
					}
				}else if(scmInvMoveIssueBillAdvQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmInvMoveIssueBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." +ScmInvMoveIssueBill2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmInvMoveIssueBillAdvQuery.getBizDateTo())));
				}
				if(scmInvMoveIssueBillAdvQuery.getCreateDateFrom()!=null){
					if(scmInvMoveIssueBillAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmInvMoveIssueBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." +ScmInvMoveIssueBill2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvMoveIssueBillAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvMoveIssueBillAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmInvMoveIssueBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." +ScmInvMoveIssueBill2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvMoveIssueBillAdvQuery.getCreateDateFrom())));
					}
				}else if(scmInvMoveIssueBillAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmInvMoveIssueBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." +ScmInvMoveIssueBill2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvMoveIssueBillAdvQuery.getCreateDateTo(),1))));
				}
				String filterWarehouseByUsr = ScmWarehouseUtil.filterWarehouseByUsr("", param);
				if (StringUtils.isNotBlank(filterWarehouseByUsr)) {
					page.getParam().put(ScmInvMoveIssueBill2.FN_WAREHOUSEID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveIssueBill2.class) + "." +ScmInvMoveIssueBill2.FN_WAREHOUSEID, QueryParam.QUERY_IN,filterWarehouseByUsr));
				}
			}
		}
	}

	@Override
	public List<ScmInvMoveIssueBill2> selectByMoveIn(long wrId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("wrId", wrId);
		return ((ScmInvMoveIssueBillDAO)dao).selectByMoveIn(map);
	}

	@Override
	public ScmInvMoveIssueBill2 submit(ScmInvMoveIssueBill2 scmInvMoveIssueBill, Param param) throws AppException {
		scmInvMoveIssueBill = this.selectDirect(scmInvMoveIssueBill.getOtId(), param);
		if(scmInvMoveIssueBill!=null){
			if(!scmInvMoveIssueBill.getStatus().equals("I")){
				throw new AppException("iscm.inventorymanage.error.commit");
			}else if(scmInvMoveIssueBill.getStatus().equals("I")){
				if(param.getUsrCode()!=null ){
					scmInvMoveIssueBill.setChecker(param.getUsrCode());
					scmInvMoveIssueBill.setSubmitter(param.getUsrCode());
				}
				scmInvMoveIssueBill.setCheckDate(CalendarUtil.getDate(param));
				scmInvMoveIssueBill.setSubmitDate(CalendarUtil.getDate(param));
				scmInvMoveIssueBill.setStatus("A");
				try{
					this.updateStatus(scmInvMoveIssueBill, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.find.ordernotexists");
		}
		return scmInvMoveIssueBill;
	}

	@Override
	public ScmInvMoveIssueBill2 undoSubmit(ScmInvMoveIssueBill2 scmInvMoveIssueBill, Param param) throws AppException {
		scmInvMoveIssueBill = this.selectDirect(scmInvMoveIssueBill.getOtId(), param);
		if(scmInvMoveIssueBill!=null){
			if(!scmInvMoveIssueBill.getStatus().equals("A")){
				throw new AppException("iscm.inventorymanage.error.cancelCommit");
			}else if(scmInvMoveIssueBill.getStatus().equals("A")){
				scmInvMoveIssueBill.setChecker(null);
				scmInvMoveIssueBill.setCheckDate(null);
				scmInvMoveIssueBill.setSubmitter(null);
				scmInvMoveIssueBill.setSubmitDate(null);
				scmInvMoveIssueBill.setStatus("I");
				try{
					this.updateStatus(scmInvMoveIssueBill, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.find.ordernotexists");
		}
		return scmInvMoveIssueBill;
	}
	
	@Override
	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map) throws AppException {
		return ((ScmInvMoveIssueBillDAO)dao).countUnPostBill(map);
	}

	@Override
	public ScmInvMoveIssueBill2 updatePrtCount(ScmInvMoveIssueBill2 scmInvMoveIssueBill, Param param)
			throws AppException {
		if(scmInvMoveIssueBill.getOtId()>0){
			ScmInvMoveIssueBill2 bill = this.selectDirect(scmInvMoveIssueBill.getOtId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmInvMoveIssueBill;
	}
}
