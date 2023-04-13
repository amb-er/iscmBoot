package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

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
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvBalBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.util.ScmWarehouseUtil;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.ScmInvMoveInWarehsBillDAO;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBill;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBillAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvMoveInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvMoveInWarehsBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBill2;
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
import com.armitage.server.system.service.SystemStateBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmInvMoveInWarehsBillBiz")
public class ScmInvMoveInWarehsBillBizImpl extends BaseBizImpl<ScmInvMoveInWarehsBill2> implements ScmInvMoveInWarehsBillBiz {
    
    private UsrBiz usrBiz;
    private OrgUnitBiz orgUnitBiz;
    private ScmInvMoveInWarehsBillEntryBiz scmInvMoveInWarehsBillEntryBiz;
    private ScmInvWareHouseBiz scmInvWareHouseBiz;
    private ScmInvMoveIssueBillEntryBiz scmInvMoveIssueBillEntryBiz;
    private OrgUnitRelationBiz orgUnitRelationBiz;
    private ScmMaterialBiz scmMaterialBiz;
    private ScmInvStockBiz scmInvStockBiz;
    private ScmInvBalBiz scmInvBalBiz;
    private PeriodCalendarBiz periodCalendarBiz;
    private SystemStateBiz systemStateBiz;
    private BillTypeBiz billTypeBiz;
    private ScmInvMoveIssueBillBiz scmInvMoveIssueBillBiz;
    private OrgStorageBiz orgStorageBiz;
    
	public void setUsrBiz(UsrBiz usrBiz) {
        this.usrBiz = usrBiz;
    }

    public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
        this.orgUnitBiz = orgUnitBiz;
    }

    public void setScmInvMoveInWarehsBillEntryBiz(ScmInvMoveInWarehsBillEntryBiz scmInvMoveInWarehsBillEntryBiz) {
        this.scmInvMoveInWarehsBillEntryBiz = scmInvMoveInWarehsBillEntryBiz;
    }

    public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
        this.scmInvWareHouseBiz = scmInvWareHouseBiz;
    }

    public void setScmInvMoveIssueBillEntryBiz(ScmInvMoveIssueBillEntryBiz scmInvMoveIssueBillEntryBiz) {
        this.scmInvMoveIssueBillEntryBiz = scmInvMoveIssueBillEntryBiz;
    }

    public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
        this.orgUnitRelationBiz = orgUnitRelationBiz;
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

    public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}

	public void setSystemStateBiz(SystemStateBiz systemStateBiz) {
        this.systemStateBiz = systemStateBiz;
    }

    public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
        this.billTypeBiz = billTypeBiz;
    }

    public void setScmInvMoveIssueBillBiz(ScmInvMoveIssueBillBiz scmInvMoveIssueBillBiz) {
		this.scmInvMoveIssueBillBiz = scmInvMoveIssueBillBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." + ScmInvMoveInWarehsBill2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." + ScmInvMoveInWarehsBill2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." + ScmInvMoveInWarehsBill2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." + ScmInvMoveInWarehsBill2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}

	@Override
    protected void afterFindPage(List list, Page page, Param param) throws AppException {
        if(list != null && !list.isEmpty()){
            for(ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill : (List<ScmInvMoveInWarehsBill2>)list){
                setConvertMap(scmInvMoveInWarehsBill,param);
            }
        }
    }
	
	@Override
    protected void afterSelect(ScmInvMoveInWarehsBill2 entity, Param param) throws AppException {
        setConvertMap(entity,param);
    }
	
	private void setConvertMap(ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill,Param param){
        if (StringUtils.isNotBlank(scmInvMoveInWarehsBill.getCreator())){
            //制单人
            Usr usr = usrBiz.selectByCode(scmInvMoveInWarehsBill.getCreator(), param);
            if (usr != null) {
                if(scmInvMoveInWarehsBill.getDataDisplayMap()==null){
                    scmInvMoveInWarehsBill.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
                }
                scmInvMoveInWarehsBill.getDataDisplayMap().put(ScmInvMoveInWarehsBill.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
                scmInvMoveInWarehsBill.setConvertMap(ScmInvMoveInWarehsBill.FN_CREATOR, usr);
            }
        }
        if (StringUtils.isNotBlank(scmInvMoveInWarehsBill.getOrgUnitNo())){
            //调入组织
            OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveInWarehsBill.getOrgUnitNo(), param);
            if (orgBaseUnit != null) {
                scmInvMoveInWarehsBill.setConvertMap(ScmInvMoveInWarehsBill.FN_ORGUNITNO, orgBaseUnit);
            }
        }
        if(scmInvMoveInWarehsBill.getWareHouseId()>0) {
        	ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvMoveInWarehsBill.getWareHouseId(), param);
            if (scmInvWareHouse != null) {
            	scmInvMoveInWarehsBill.setConvertMap(ScmInvMoveIssueBillEntry2.FN_WAREHOUSEID, scmInvWareHouse);
            }
        }
        if (StringUtils.isNotBlank(scmInvMoveInWarehsBill.getFromOrgUnitNo())){
            //调出组织
            OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveInWarehsBill.getFromOrgUnitNo(), param);
            if (orgBaseUnit != null) {
                scmInvMoveInWarehsBill.setConvertMap(ScmInvMoveInWarehsBill.FN_FROMORGUNITNO, orgBaseUnit);
            }
        }
        if (StringUtils.isNotBlank(scmInvMoveInWarehsBill.getCreateOrgUnitNo())){
            //制单组织
            OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveInWarehsBill.getCreateOrgUnitNo(), param);
            if (orgBaseUnit != null) {
                scmInvMoveInWarehsBill.setConvertMap(ScmInvMoveInWarehsBill.FN_CREATEORGUNITNO, orgBaseUnit);
            }
        }
        if (StringUtils.isNotBlank(scmInvMoveInWarehsBill.getEditor())){
            //修改人
            Usr usr = usrBiz.selectByCode(scmInvMoveInWarehsBill.getEditor(), param);
            if (usr != null) {
                scmInvMoveInWarehsBill.setConvertMap(ScmInvMoveInWarehsBill.FN_EDITOR, usr);
            }
        }
        if (StringUtils.isNotBlank(scmInvMoveInWarehsBill.getChecker())){
            //审核人
            Usr usr = usrBiz.selectByCode(scmInvMoveInWarehsBill.getChecker(), param);
            if (usr != null) {
                scmInvMoveInWarehsBill.setConvertMap(ScmInvMoveInWarehsBill.FN_CHECKER, usr);
            }
        }
        if (StringUtils.isNotBlank(scmInvMoveInWarehsBill.getBillType())){
            //来源单类型
            BillType2 billType = billTypeBiz.selectByOrgAndCode(scmInvMoveInWarehsBill.getFinOrgUnitNo(), scmInvMoveInWarehsBill.getBillType(), param);
            if (billType != null) {
                scmInvMoveInWarehsBill.setConvertMap(ScmInvSaleOrder.FN_BILLTYPE, billType);
            }
        }
    }
	
	@Override
    protected void afterSelect(CommonBean bean, Param param) throws AppException {
        ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill = (ScmInvMoveInWarehsBill2) bean.getList().get(0);
        if(scmInvMoveInWarehsBill != null && scmInvMoveInWarehsBill.getWrId() > 0){
        	bean.setList2(scmInvMoveInWarehsBillEntryBiz.selectByWrId(scmInvMoveInWarehsBill.getWrId(), param));
        }
    }
	
	@Override
	protected void beforeAdd(CommonBean bean, Param param) throws AppException {
        ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill = (ScmInvMoveInWarehsBill2) bean.getList().get(0);
        if(scmInvMoveInWarehsBill != null){
//            String date = StringUtils.replace(FormatUtils.fmtDate(scmInvMoveInWarehsBill.getCreateDate()), "-", "");
//            StringBuffer s = new StringBuffer("");
//            s.append("STI").append(date);
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("createDate", FormatUtils.fmtDate(FormatUtils.parseDateTime(date, "yyyyMMdd")));
//			map.put("controlUnitNo", param.getControlUnitNo());
//            ScmInvMoveInWarehsBill scmInvMoveInWarehsBill2 = ((ScmInvMoveInWarehsBillDAO) dao).selectMaxIdByDate(map);
//            if(scmInvMoveInWarehsBill2 != null && StringUtils.isNotBlank(scmInvMoveInWarehsBill2.getWrNo())
//                    && scmInvMoveInWarehsBill2.getWrNo().contains(date)){
//                String str = StringUtils.substring(scmInvMoveInWarehsBill2.getWrNo(), (scmInvMoveInWarehsBill2.getWrNo().indexOf(date)+date.length()));
//                str = CodeAutoCalUtil.autoAddOne(str);
//                str = (s.append(str)).toString();
//                scmInvMoveInWarehsBill.setWrNo(str);
//            }else{
//            	scmInvMoveInWarehsBill.setWrNo((s.append("001").toString()));
//            }
        	String code = CodeAutoCalUtil.getBillCode("InvMoveInWhsBill", scmInvMoveInWarehsBill, param);
        	scmInvMoveInWarehsBill.setWrNo(code);
            //获取期间
			PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(scmInvMoveInWarehsBill.getBizDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
			}
			scmInvMoveInWarehsBill.setPeriodId(periodCalendar.getPeriodId());
			scmInvMoveInWarehsBill.setAccountYear(periodCalendar.getAccountYear());
			scmInvMoveInWarehsBill.setAccountPeriod(periodCalendar.getAccountPeriod());
			//新增收货明细
            List<ScmInvMoveInWarehsBillEntry2> scmInvMoveInWarehsBillEntryList = bean.getList2();
            BigDecimal amt= BigDecimal.ZERO;
            BigDecimal taxAmt= BigDecimal.ZERO;
            if(scmInvMoveInWarehsBillEntryList != null && !scmInvMoveInWarehsBillEntryList.isEmpty()){
                for(ScmInvMoveInWarehsBillEntry2 scmInvMoveInWarehsBillEntry : scmInvMoveInWarehsBillEntryList){
                    amt = amt.add(scmInvMoveInWarehsBillEntry.getAmt());
                    taxAmt = taxAmt.add(scmInvMoveInWarehsBillEntry.getTaxAmt());
                }
            }
            scmInvMoveInWarehsBill.setAmt(amt);
            scmInvMoveInWarehsBill.setTaxAmt(taxAmt);
        }
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
        ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill = (ScmInvMoveInWarehsBill2) bean.getList().get(0);
        if(scmInvMoveInWarehsBill != null){
			//新增收货明细
            List<ScmInvMoveInWarehsBillEntry2> scmInvMoveInWarehsBillEntryList = bean.getList2();
            if(scmInvMoveInWarehsBillEntryList != null && !scmInvMoveInWarehsBillEntryList.isEmpty()){
                int lineId = 1;
                for(ScmInvMoveInWarehsBillEntry2 scmInvMoveInWarehsBillEntry : scmInvMoveInWarehsBillEntryList){
                    scmInvMoveInWarehsBillEntry.setLineId(lineId);
                    scmInvMoveInWarehsBillEntry.setWrId(scmInvMoveInWarehsBill.getWrId());
                    scmInvMoveInWarehsBillEntry.setWareHouseId(scmInvMoveInWarehsBill.getWareHouseId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvMoveInWarehsBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvMoveInWarehsBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvMoveInWarehsBillEntry.getItemId(), scmInvMoveInWarehsBillEntry.getUnit(), param);//库存单位转换系数
					scmInvMoveInWarehsBillEntry.setBaseQty(scmInvMoveInWarehsBillEntry.getQty().multiply(invConvRate));
                    scmInvMoveInWarehsBillEntryBiz.add(scmInvMoveInWarehsBillEntry, param);
                    lineId = lineId+1;
                }
            }
        }
	}

	
	@Override
    protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
        ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill = (ScmInvMoveInWarehsBill2) bean.getList().get(0);
        if(scmInvMoveInWarehsBill != null){
        	ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill2 = this.select(scmInvMoveInWarehsBill.getPK(), param);
    		if(!StringUtils.equals(scmInvMoveInWarehsBill2.getStatus(),"I")){
    			throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
    		}
            //更新销售单明细
            List<ScmInvMoveInWarehsBillEntry2> scmInvMoveInWarehsBillEntryList = bean.getList2();
            BigDecimal amt= BigDecimal.ZERO;
            BigDecimal taxAmt= BigDecimal.ZERO;
            if(scmInvMoveInWarehsBillEntryList != null && !scmInvMoveInWarehsBillEntryList.isEmpty()){
                for(ScmInvMoveInWarehsBillEntry2 scmInvMoveInWarehsBillEntry : scmInvMoveInWarehsBillEntryList){
                    amt = amt.add(scmInvMoveInWarehsBillEntry.getAmt());
                    taxAmt = taxAmt.add(scmInvMoveInWarehsBillEntry.getTaxAmt());
                }
            }
            scmInvMoveInWarehsBill.setAmt(amt);
            scmInvMoveInWarehsBill.setTaxAmt(taxAmt);
            //获取期间
    		PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(scmInvMoveInWarehsBill.getBizDate(), param);
    		if(periodCalendar==null){
    			throw new AppException("com.armitage.server.iars.daily.selectByBizdate.error");
    		}
    		scmInvMoveInWarehsBill.setPeriodId(periodCalendar.getPeriodId());
    		scmInvMoveInWarehsBill.setAccountYear(periodCalendar.getAccountYear());
    		scmInvMoveInWarehsBill.setAccountPeriod(periodCalendar.getAccountPeriod());
        }
    }
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param)
			throws AppException {
        ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill = (ScmInvMoveInWarehsBill2) bean.getList().get(0);
        if(scmInvMoveInWarehsBill != null && scmInvMoveInWarehsBill.getWrId() > 0){
        	//更新销售单明细
            List<ScmInvMoveInWarehsBillEntry2> scmInvMoveInWarehsBillEntryList = bean.getList2();
            if(scmInvMoveInWarehsBillEntryList != null && !scmInvMoveInWarehsBillEntryList.isEmpty()){
                int lineId = 1;
                for(ScmInvMoveInWarehsBillEntry2 scmInvMoveInWarehsBillEntry : scmInvMoveInWarehsBillEntryList){
                	if (StringUtils.equals("I", scmInvMoveInWarehsBill.getStatus())) {
                		scmInvMoveInWarehsBillEntry.setLineId(lineId);
                	}
                    scmInvMoveInWarehsBillEntry.setWrId(scmInvMoveInWarehsBill.getWrId());
                    scmInvMoveInWarehsBillEntry.setWareHouseId(scmInvMoveInWarehsBill.getWareHouseId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmInvMoveInWarehsBillEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmInvMoveInWarehsBillEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvMoveInWarehsBillEntry.getItemId(), scmInvMoveInWarehsBillEntry.getUnit(), param);//库存单位转换系数
					scmInvMoveInWarehsBillEntry.setBaseQty(scmInvMoveInWarehsBillEntry.getQty().multiply(invConvRate));
                    scmInvMoveInWarehsBillEntry.setWrId(scmInvMoveInWarehsBill.getWrId());
                    lineId = lineId+1;
                }
                scmInvMoveInWarehsBillEntryBiz.update(scmInvMoveInWarehsBill, scmInvMoveInWarehsBillEntryList, ScmInvMoveInWarehsBillEntry2.FN_WRID, param);
            }
        }
	}
	@Override
    protected void afterDelete(ScmInvMoveInWarehsBill2 entity, Param param) throws AppException {
        if(entity != null && entity.getWrId() > 0){
            //回写调拨出库的入库状态
            //查出入库明细
            List<ScmInvMoveInWarehsBillEntry2> scmInvMoveInWarehsBillEntryList = scmInvMoveInWarehsBillEntryBiz.selectByWrId(entity.getWrId(), param);
            if (scmInvMoveInWarehsBillEntryList == null || scmInvMoveInWarehsBillEntryList.isEmpty()) {
                throw new AppException("iscm.inventorymanage.warehouseinbusiness.service.impl.ScmInvMoveInWarehsBillBizImpl.notExistWRDetails");
            }
            for(ScmInvMoveInWarehsBillEntry2 scmInvMoveInWarehsBillEntry : scmInvMoveInWarehsBillEntryList){
                //查出调拨出库单明细
                ScmInvMoveIssueBillEntry2 scmInvMoveIssueBillEntry = 
                        scmInvMoveIssueBillEntryBiz.select(scmInvMoveInWarehsBillEntry.getOutBillDtlId(), param);
                if (scmInvMoveIssueBillEntry != null) {
	                scmInvMoveIssueBillEntry.setMoveIn(false);
	                scmInvMoveIssueBillEntryBiz.update(scmInvMoveIssueBillEntry, param);
                }
            }
            //删除出库明细
            scmInvMoveInWarehsBillEntryBiz.deleteByWrId(entity.getWrId(), param);
        }
    }
	
    public ScmInvMoveInWarehsBill2 updateStatus(ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill, Param param) throws AppException {
        if(scmInvMoveInWarehsBill != null){
            ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill2 = this.updateDirect(scmInvMoveInWarehsBill, param);
            if(scmInvMoveInWarehsBill2 != null){
                return scmInvMoveInWarehsBill2;
            }
        }
        return null;
    }

    @Override
    public ScmInvMoveInWarehsBill2 postBill(ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill, Param param) throws AppException {
    	scmInvMoveInWarehsBill = this.selectDirect(scmInvMoveInWarehsBill.getWrId(), param);
        if (scmInvMoveInWarehsBill != null) {
			if(!StringUtils.equals("A",scmInvMoveInWarehsBill.getStatus())) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvMoveInWarehsBill.getWrNo()}));
    		}
            //更新结存，插入结存表
            scmInvStockBiz.updateByMoveInWarehsBill(scmInvMoveInWarehsBill.getWrId(), param);
            scmInvStockBiz.addByMoveInWarehsBill(scmInvMoveInWarehsBill.getWrId(), param);
            // 4更新调出期间余额表，插入期间余额表
            scmInvBalBiz.updateByMoveInWarehsBill(scmInvMoveInWarehsBill.getWrId(), param);
            scmInvBalBiz.addByMoveInWarehsBill(scmInvMoveInWarehsBill.getWrId(), param);
			if (!(ParamType.Operation.equals(param.getParamType()) && ((OperationParam) param).getOperationId()==629223199L)) {
				scmInvMoveInWarehsBill.setCheckDate(CalendarUtil.getDate(param));
				scmInvMoveInWarehsBill.setChecker(param.getUsrCode());
	            scmInvMoveInWarehsBill.setPostDate(CalendarUtil.getDate(param));
			}
            scmInvMoveInWarehsBill.setStatus("E");
			int updateRow = this.updatePostedStatus(scmInvMoveInWarehsBill, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.post.error2", new String[] {scmInvMoveInWarehsBill.getWrNo()}));
			}
        }
        return null;
    }

    private int updatePostedStatus(ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill, Param param) {
    	HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("wrId",scmInvMoveInWarehsBill.getWrId());
		map.put("checker",scmInvMoveInWarehsBill.getChecker());
		map.put("checkDate",scmInvMoveInWarehsBill.getCheckDate()==null?null:FormatUtils.fmtDate(scmInvMoveInWarehsBill.getCheckDate()));
		map.put("status", scmInvMoveInWarehsBill.getStatus());
		map.put("postDate", scmInvMoveInWarehsBill.getPostDate()==null?null:FormatUtils.fmtDateTime(scmInvMoveInWarehsBill.getPostDate()));
		return ((ScmInvMoveInWarehsBillDAO)dao).updatePostedStatus(map);
	}

	@Override
    public ScmInvMoveInWarehsBill2 cancelPostBill(ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill, Param param) throws AppException {
    	scmInvMoveInWarehsBill = this.selectDirect(scmInvMoveInWarehsBill.getWrId(), param);
        if (scmInvMoveInWarehsBill != null) {
			if(!StringUtils.equals("E",scmInvMoveInWarehsBill.getStatus())) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvMoveInWarehsBill.getWrNo()}));
    		}
    		List<ScmInvMoveInWarehsBillEntry2> scmInvMoveInWarehsBillEntryList = scmInvMoveInWarehsBillEntryBiz.selectOutEffectRow(scmInvMoveInWarehsBill.getWrId(), param);
			//即时库存
			int updateRow = scmInvStockBiz.updateByCancelMoveInWarehsBill(scmInvMoveInWarehsBill.getWrId(), param);
			if(updateRow<scmInvMoveInWarehsBillEntryList.size()){
    			throw new AppException("iscm.inventorymanage.common.post.error.rowsnotequal");
    		}
			//
			//期间余额
			scmInvBalBiz.updateByCancelMoveInWarehsBill(scmInvMoveInWarehsBill.getWrId(), param);
			scmInvMoveInWarehsBill.setCheckDate(null);
			scmInvMoveInWarehsBill.setChecker("");
			scmInvMoveInWarehsBill.setStatus("A");
			scmInvMoveInWarehsBill.setPostDate(null);
			updateRow = this.updatePostedStatus(scmInvMoveInWarehsBill, param);
			if(updateRow!=1) {
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.cancelPost.error2", new String[] {scmInvMoveInWarehsBill.getWrNo()}));
			}
		}
		return scmInvMoveInWarehsBill;
    }

    @Override
    public List<String> postBillCheck(ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill, Param param) throws AppException {
        List<String> msgList = new ArrayList<String>();// 提示列表
        scmInvMoveInWarehsBill = this.selectDirect(scmInvMoveInWarehsBill.getWrId(), param);
        if(scmInvMoveInWarehsBill != null){
        	if(!StringUtils.equals("A",scmInvMoveInWarehsBill.getStatus())){
        		msgList.add(Message.getMessage(param.getLang(),"iscm.inventorymanage.post.error2",new String[] {scmInvMoveInWarehsBill.getWrNo()}));
        		return msgList;
        	}
            SystemState systemState = systemStateBiz.selectBySystemId(scmInvMoveInWarehsBill.getFinOrgUnitNo(), 170, param);
            if(systemState==null){
                msgList.add(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
                return msgList;
            }
            if (systemState.getCurrentPeriodId() != scmInvMoveInWarehsBill.getPeriodId()) {
                msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmInvMoveInWarehsBill.getWrNo()}));
                return msgList;
            }
            // 检查盘点物资冻结
            // 检查冻结仓库
            List<ScmInvMoveInWarehsBill2> scmInvMoveInWarehsBillList = ((ScmInvMoveInWarehsBillDAO) dao).checkWareHouseFree(scmInvMoveInWarehsBill.getWrId());
            if (scmInvMoveInWarehsBillList != null && !scmInvMoveInWarehsBillList.isEmpty()) {
            	msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
                return msgList;
//            	for (ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill2 : scmInvMoveInWarehsBillList) {
//	                // 检查冻结物资
//	                HashMap<String,Object> map = new HashMap<String,Object>();
//	                map.put("taskId", scmInvMoveInWarehsBill2.getTaskId());
//	                map.put("wrId", scmInvMoveInWarehsBill.getWrId());
//	                int count = ((ScmInvMoveInWarehsBillDAO) dao).checkMaterialFree(map);
//	                if (count > 0) {
//	                    msgList.add(Message.getMessage(param.getLang(), 
//	                            "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
//	                    return msgList;
//	                }
//            	}
            }
            List<ScmInvMoveInWarehsBillEntry2> scmInvMoveInWarehsBillEntryList = scmInvMoveInWarehsBillEntryBiz.selectByWrId(scmInvMoveInWarehsBill.getWrId(), param);
            if(scmInvMoveInWarehsBillEntryList != null && !scmInvMoveInWarehsBillEntryList.isEmpty()){
                HashMap<String,Object> cacheMap = new HashMap<String,Object>();
                for(ScmInvMoveInWarehsBillEntry2 scmInvMoveInWarehsBillEntry : scmInvMoveInWarehsBillEntryList){
                    String orgunitName ="";
                    OrgCompany2 orgCompany = null;
                    if (StringUtils.isNotBlank(scmInvMoveInWarehsBillEntry.getOrgUnitNo())){
                        //组织
                        OrgBaseUnit orgBaseUnit = (OrgBaseUnit) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvMoveInWarehsBillEntry.getOrgUnitNo());
                        if(orgBaseUnit==null){
                            orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvMoveInWarehsBillEntry.getOrgUnitNo(), param);
                            cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit.class)+"_"+scmInvMoveInWarehsBillEntry.getOrgUnitNo(),orgBaseUnit);
                        }
                        if (orgBaseUnit != null) {
                            orgunitName = orgBaseUnit.getOrgUnitName();
                        }
                        orgCompany = (OrgCompany2) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgCompany2.class)+"_"+scmInvMoveInWarehsBillEntry.getOrgUnitNo());
                        if(orgCompany==null && !cacheMap.containsKey(ClassUtils.getFinalModelSimpleName(OrgCompany2.class)+"_"+scmInvMoveInWarehsBillEntry.getOrgUnitNo())){
                            List<OrgCompany2> orgCompanylist = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.INVTOFIN, scmInvMoveInWarehsBillEntry.getOrgUnitNo(), false, null, param);
                            if(orgCompanylist != null && !orgCompanylist.isEmpty()){
                                orgCompany = orgCompanylist.get(0);
                                cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgCompany2.class)+"_"+scmInvMoveInWarehsBillEntry.getOrgUnitNo(),orgCompany);
                            }
                        }
                    }
                    String[] msparam = {orgunitName,scmInvMoveInWarehsBillEntry.getItemName()};
                    if(orgCompany==null){
                        msgList.add(Message.getMessage(param.getLang(),
                                "iscm.inventorymanage.otherinwarehsbill.post.error", msparam));
                        continue;
                    }else{
                    	Page page = new Page();
						page.setModelClass(ScmMaterial2.class);
						page.setShowCount(Integer.MAX_VALUE);
						page.setSqlCondition("ScmMaterial.id="+scmInvMoveInWarehsBillEntry.getItemId());
						List<String> arglist = new ArrayList<>();
						arglist.add("controlUnitNo="+param.getControlUnitNo());
						arglist.add("orgUnitNo="+orgCompany.getOrgUnitNo());
						List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, arglist, "findByFinAllPage", param);
						if(scmMaterialList==null || scmMaterialList.isEmpty()){
                            msgList.add(Message.getMessage(param.getLang(),
                                    "iscm.inventorymanage.otherinwarehsbill.post.error", msparam));
                            continue;
                        }
                    }
                }
            }
        }
        return msgList;
    }

	@Override
	public List<String> cancelPostBillCheck(ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill, Param param) throws AppException {
		List<String> msgList = new ArrayList<String>();// 提示列表
		scmInvMoveInWarehsBill = this.selectDirect(scmInvMoveInWarehsBill.getWrId(), param);
        if(scmInvMoveInWarehsBill != null){
        	if(!StringUtils.equals("E",scmInvMoveInWarehsBill.getStatus())){
        		msgList.add(Message.getMessage(param.getLang(),"iscm.inventorymanage.cancelPost.error2",new String[] {scmInvMoveInWarehsBill.getWrNo()}));
        		return msgList;
        	}
        	SystemState systemState = systemStateBiz.selectBySystemId(scmInvMoveInWarehsBill.getFinOrgUnitNo(), 170, param);
            if(systemState==null){
                msgList.add(Message.getMessage(param.getLang(),"com.armitage.iars.daily.util.disenable.unable"));
                return msgList;
            }
            if (systemState.getCurrentPeriodId() != scmInvMoveInWarehsBill.getPeriodId()) {
                msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.notCurrent", new String[] {scmInvMoveInWarehsBill.getWrNo()}));
                return msgList;
            }
            
            // 检查盘点物资冻结
            // 检查冻结仓库
            List<ScmInvMoveInWarehsBill2> scmInvMoveInWarehsBillList = ((ScmInvMoveInWarehsBillDAO) dao).checkWareHouseFree(scmInvMoveInWarehsBill.getWrId());
            if (scmInvMoveInWarehsBillList != null && !scmInvMoveInWarehsBillList.isEmpty()) {
            	msgList.add(Message.getMessage(param.getLang(), 
                        "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
                return msgList;
//            	for (ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill2 : scmInvMoveInWarehsBillList) {
//	                // 检查冻结物资
//	                HashMap<String,Object> map = new HashMap<String,Object>();
//	                map.put("taskId", scmInvMoveInWarehsBill2.getTaskId());
//	                map.put("wrId", scmInvMoveInWarehsBill.getWrId());
//	                int count = ((ScmInvMoveInWarehsBillDAO) dao).checkMaterialFree(map);
//	                if (count > 0) {
//	                    msgList.add(Message.getMessage(param.getLang(), 
//	                            "com.armitage.server.iscm.postBill.peroidId.error.countingTask.countingTable"));
//	                    return msgList;
//	                }
//            	}
            }
        	
        	List<ScmInvMoveInWarehsBillEntry2> list = scmInvMoveInWarehsBillEntryBiz.selectInvQty(scmInvMoveInWarehsBill.getWrId(), param);
        
	        if (list != null && list.size() > 0) {
	            for (int i = 0; i < list.size(); i++) {
	                if (StringUtils.isNotBlank(list.get(i).getLot())) {
	                    msgList.add(Message.getMessage(param.getLang(), 
	                            "com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl.count", 
	                            new String[] {list.get(i).getItemNo(), list.get(i).getItemName(), list.get(i).getLot(),
	                                    list.get(i).getQty().toString(), list.get(i).getInvQty().toString()}));
	                } else {
	                    msgList.add(Message.getMessage(param.getLang(), 
	                            "com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl.count2", 
	                            new String[] {list.get(i).getItemNo(), list.get(i).getItemName(), 
	                                    list.get(i).getQty().toString(), list.get(i).getInvQty().toString()}));
	                }
	            }
	        }
        }
        return msgList;
	}

	@Override
	protected void beforeDelete(ScmInvMoveInWarehsBill2 entity, Param param)
			throws AppException {
		ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill = this.selectDirect(entity.getWrId(), param);
		if(scmInvMoveInWarehsBill!=null && !StringUtils.equals(scmInvMoveInWarehsBill.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getWrNo()}));
		}
		List<ScmInvMoveIssueBill2> scmInvMoveIssueBillList = scmInvMoveIssueBillBiz.selectByMoveIn(entity.getWrId(),param);
		if(scmInvMoveIssueBillList!=null && !scmInvMoveIssueBillList.isEmpty()) {
			StringBuffer otNos = new StringBuffer("");
			for(ScmInvMoveIssueBill2 scmInvMoveIssueBill:scmInvMoveIssueBillList) {
				if(StringUtils.equals("E",scmInvMoveIssueBill.getStatus())){
					if(StringUtils.isNotBlank(otNos.toString()))
						otNos.append(",");
					otNos.append(scmInvMoveIssueBill.getOtNo());
				}
			}
			if(StringUtils.isNotBlank(otNos.toString()))
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.scmInvMoveInWarehsBill.delete.buildByMoveIssue", new String[]{entity.getWrNo(),otNos.toString()}));

		}
	}

	@Override
	public List<ScmInvMoveInWarehsBill2> checkUnPostBill(String finOrgUnitNo,long periodId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmInvMoveInWarehsBillDAO)dao).checkUnPostBill(map);
	}

	@Override
	public List<ScmInvMoveInWarehsBill2> checkPostedBill(String finOrgUnitNo,long periodId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("finOrgUnitNo", finOrgUnitNo);
		map.put("periodId", periodId);
		return ((ScmInvMoveInWarehsBillDAO)dao).checkPostedBill(map);
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvMoveInWarehsBillAdvQuery) {
				ScmInvMoveInWarehsBillAdvQuery scmInvMoveInWarehsBillAdvQuery = (ScmInvMoveInWarehsBillAdvQuery) page.getModel();
				if(scmInvMoveInWarehsBillAdvQuery.getWareHouseId()>0) {
					page.getParam().put(ScmInvMoveInWarehsBill2.FN_WAREHOUSEID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." +ScmInvMoveInWarehsBill2.FN_WAREHOUSEID, QueryParam.QUERY_EQ,String.valueOf(scmInvMoveInWarehsBillAdvQuery.getWareHouseId())));
				}
				if(scmInvMoveInWarehsBillAdvQuery.getBizDateFrom()!=null){
					if(scmInvMoveInWarehsBillAdvQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmInvMoveInWarehsBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." +ScmInvMoveInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvMoveInWarehsBillAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(scmInvMoveInWarehsBillAdvQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmInvMoveInWarehsBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." +ScmInvMoveInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvMoveInWarehsBillAdvQuery.getBizDateFrom())));
					}
				}else if(scmInvMoveInWarehsBillAdvQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmInvMoveInWarehsBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." +ScmInvMoveInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmInvMoveInWarehsBillAdvQuery.getBizDateTo())));
				}
				if(scmInvMoveInWarehsBillAdvQuery.getCreateDateFrom()!=null){
					if(scmInvMoveInWarehsBillAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmInvMoveInWarehsBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." +ScmInvMoveInWarehsBill2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvMoveInWarehsBillAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvMoveInWarehsBillAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmInvMoveInWarehsBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." +ScmInvMoveInWarehsBill2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvMoveInWarehsBillAdvQuery.getCreateDateFrom())));
					}
				}else if(scmInvMoveInWarehsBillAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmInvMoveInWarehsBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." +ScmInvMoveInWarehsBill2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvMoveInWarehsBillAdvQuery.getCreateDateTo(),1))));
				}
				String filterWarehouseByUsr = ScmWarehouseUtil.filterWarehouseByUsr("", param);
				if (StringUtils.isNotBlank(filterWarehouseByUsr)) {
					page.getParam().put(ScmInvMoveInWarehsBill2.FN_WAREHOUSEID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMoveInWarehsBill2.class) + "." +ScmInvMoveInWarehsBill2.FN_WAREHOUSEID, QueryParam.QUERY_IN,filterWarehouseByUsr));
				}
			}
		}
	}

	@Override
	public List<ScmInvMoveInWarehsBill2> selectByMoveIssue(long otId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("otId", otId);
		return ((ScmInvMoveInWarehsBillDAO)dao).selectByMoveIssue(map);
	}

	@Override
	public ScmInvMoveInWarehsBill2 submit(ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill, Param param)
			throws AppException {
		scmInvMoveInWarehsBill = this.selectDirect(scmInvMoveInWarehsBill.getWrId(), param);
		if(scmInvMoveInWarehsBill!=null){
			if(!scmInvMoveInWarehsBill.getStatus().equals("I")){
				throw new AppException("iscm.inventorymanage.error.commit");
			}else if(scmInvMoveInWarehsBill.getStatus().equals("I")){
				if(param.getUsrCode()!=null ){
					scmInvMoveInWarehsBill.setChecker(param.getUsrCode());
					scmInvMoveInWarehsBill.setSubmitter(param.getUsrCode());
				}
				scmInvMoveInWarehsBill.setCheckDate(CalendarUtil.getDate(param));
				scmInvMoveInWarehsBill.setSubmitDate(CalendarUtil.getDate(param));
				scmInvMoveInWarehsBill.setStatus("A");
				try{
					this.updateStatus(scmInvMoveInWarehsBill, param);
				}catch(AppException e){
					throw e;
				}
				SystemState systemState = systemStateBiz.selectBySystemId(scmInvMoveInWarehsBill.getFinOrgUnitNo(), 170, param);
				if(systemState==null){
					throw new AppException("com.armitage.iars.daily.util.disenable.unable");
				}
				if (systemState.getCurrentPeriodId() == scmInvMoveInWarehsBill.getPeriodId()) {
					if(StringUtils.equals("A", scmInvMoveInWarehsBill.getStatus())) {
						//通过或部分通过时检查是否自动过帐
						BillType2 billType = billTypeBiz.selectByOrgAndCode(scmInvMoveInWarehsBill.getFinOrgUnitNo(), "InvMoveInWhsBill", param);
						if(billType!=null && billType.isAutoRelease()) {
							List<String> msgList = this.postBillCheck(scmInvMoveInWarehsBill, param);
							if (msgList != null && !msgList.isEmpty()) {
								StringBuilder detailInfo = new StringBuilder("");
		                        detailInfo.append(Message.getMessage(param.getLang(),"iscm.inventorymanage.moveinwarehsbill.post.errorTitle"));
	
								for (String str : msgList) {
		                            detailInfo.append(str).append("\n");
		                        }
								
								throw new AppException(detailInfo.toString(), new String[]{scmInvMoveInWarehsBill.getWrNo()});
							}
							this.postBill(scmInvMoveInWarehsBill, param);
						}
					}
				}
			}
		}else{
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.find.ordernotexists");
		}
		return scmInvMoveInWarehsBill;
	}

	@Override
	public ScmInvMoveInWarehsBill2 undoSubmit(ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill, Param param)
			throws AppException {
		scmInvMoveInWarehsBill = this.selectDirect(scmInvMoveInWarehsBill.getWrId(), param);
		if(scmInvMoveInWarehsBill!=null){
			if(!scmInvMoveInWarehsBill.getStatus().equals("A")){
				throw new AppException("iscm.inventorymanage.error.cancelCommit");
			}else if(scmInvMoveInWarehsBill.getStatus().equals("A")){
				scmInvMoveInWarehsBill.setChecker(null);
				scmInvMoveInWarehsBill.setSubmitter(null);
				scmInvMoveInWarehsBill.setCheckDate(null);
				scmInvMoveInWarehsBill.setSubmitDate(null);
				scmInvMoveInWarehsBill.setStatus("I");
				try{
					this.updateStatus(scmInvMoveInWarehsBill, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.find.ordernotexists");
		}
		return scmInvMoveInWarehsBill;
	}
	
	@Override
	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map) throws AppException{
		return ((ScmInvMoveInWarehsBillDAO)dao).countUnPostBill(map);
	}

	@Override
	public List<Map<String, Object>> countCostUnPostBill(HashMap<String, Object> map) throws AppException{
		return ((ScmInvMoveInWarehsBillDAO)dao).countCostUnPostBill(map);
	}

	@Override
	public ScmInvMoveInWarehsBill2 updatePrtCount(ScmInvMoveInWarehsBill2 scmInvMoveInWarehsBill, Param param)
			throws AppException {
		if(scmInvMoveInWarehsBill.getWrId()>0){
			ScmInvMoveInWarehsBill2 bill = this.selectDirect(scmInvMoveInWarehsBill.getWrId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmInvMoveInWarehsBill;
	}
}

