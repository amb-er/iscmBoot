package com.armitage.server.iscm.inventorymanage.countbusiness.service.impl;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.icrs.basedata.model.TaxRate;
import com.armitage.server.iscm.basedata.service.ScmIdleItemsBiz;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvBalOver;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvBalOverResult;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvBalOverResultDetail;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvBalOverBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLoss2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsume;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsume2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeEntry;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTask2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBill2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmCstFrmLossBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCostConsumeBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCostConsumeEntryBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingTaskBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvMoveBillBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvBal2;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvPeriodStock;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvBalBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvPeriodStockBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvMoveInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvOtherInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialReqBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMoveIssueBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvOtherIssueBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSaleIssueBillBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.model.SystemState;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.OrgCostCenterBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.service.SystemStateBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import org.springframework.stereotype.Service;

@Service("scmInvBalOverBiz")
public class ScmInvBalOverBizImpl extends BaseBizImpl<ScmInvBalOver> implements ScmInvBalOverBiz {
	private ScmInvPeriodStockBiz scmInvPeriodStockBiz;
	private ScmInvStockBiz scmInvStockBiz;
	private ScmInvBalBiz scmInvBalBiz;
	private SystemStateBiz systemStateBiz;
	private PeriodCalendarBiz periodCalendarBiz;
	private ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz;
	private ScmInvOtherInWarehsBillBiz scmInvOtherInWarehsBillBiz;
	private ScmInvMoveInWarehsBillBiz scmInvMoveInWarehsBillBiz;
	private ScmInvMaterialReqBillBiz scmInvMaterialReqBillBiz;
	private ScmInvMoveIssueBillBiz scmInvMoveIssueBillBiz;
	private ScmInvOtherIssueBillBiz scmInvOtherIssueBillBiz;
	private ScmInvSaleIssueBillBiz scmInvSaleIssueBillBiz;
	private ScmInvCostConsumeBiz scmInvCostConsumeBiz;
	private ScmInvMoveBillBiz scmInvMoveBillBiz;
	private BillTypeBiz billTypeBiz;
	private ScmInvCountingTaskBiz scmInvCountingTaskBiz;
	private ScmCstFrmLossBiz scmCstFrmLossBiz;
	private OrgCostCenterBiz orgCostCenterBiz;
	private ScmIdleItemsBiz scmIdleItemsBiz;
	private SysParamBiz sysParamBiz;
	
	public void setScmInvStockBiz(ScmInvStockBiz scmInvStockBiz) {
		this.scmInvStockBiz = scmInvStockBiz;
	}

	public void setScmInvPeriodStockBiz(ScmInvPeriodStockBiz scmInvPeriodStockBiz) {
		this.scmInvPeriodStockBiz = scmInvPeriodStockBiz;
	}

	public void setScmInvBalBiz(ScmInvBalBiz scmInvBalBiz) {
		this.scmInvBalBiz = scmInvBalBiz;
	}

	public void setSystemStateBiz(SystemStateBiz systemStateBiz) {
		this.systemStateBiz = systemStateBiz;
	}

	public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}

	public void setScmInvPurInWarehsBillBiz(
			ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz) {
		this.scmInvPurInWarehsBillBiz = scmInvPurInWarehsBillBiz;
	}

	public void setScmInvOtherInWarehsBillBiz(
			ScmInvOtherInWarehsBillBiz scmInvOtherInWarehsBillBiz) {
		this.scmInvOtherInWarehsBillBiz = scmInvOtherInWarehsBillBiz;
	}

	public void setScmInvMoveInWarehsBillBiz(
			ScmInvMoveInWarehsBillBiz scmInvMoveInWarehsBillBiz) {
		this.scmInvMoveInWarehsBillBiz = scmInvMoveInWarehsBillBiz;
	}

	public void setScmInvMaterialReqBillBiz(
			ScmInvMaterialReqBillBiz scmInvMaterialReqBillBiz) {
		this.scmInvMaterialReqBillBiz = scmInvMaterialReqBillBiz;
	}

	public void setScmInvMoveIssueBillBiz(
			ScmInvMoveIssueBillBiz scmInvMoveIssueBillBiz) {
		this.scmInvMoveIssueBillBiz = scmInvMoveIssueBillBiz;
	}

	public void setScmInvOtherIssueBillBiz(
			ScmInvOtherIssueBillBiz scmInvOtherIssueBillBiz) {
		this.scmInvOtherIssueBillBiz = scmInvOtherIssueBillBiz;
	}

	public void setScmInvSaleIssueBillBiz(
			ScmInvSaleIssueBillBiz scmInvSaleIssueBillBiz) {
		this.scmInvSaleIssueBillBiz = scmInvSaleIssueBillBiz;
	}

	public void setScmInvCostConsumeBiz(ScmInvCostConsumeBiz scmInvCostConsumeBiz) {
		this.scmInvCostConsumeBiz = scmInvCostConsumeBiz;
	}

	public void setScmInvMoveBillBiz(ScmInvMoveBillBiz scmInvMoveBillBiz) {
		this.scmInvMoveBillBiz = scmInvMoveBillBiz;
	}

	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
        this.billTypeBiz = billTypeBiz;
    }

    public void setScmInvCountingTaskBiz(ScmInvCountingTaskBiz scmInvCountingTaskBiz) {
		this.scmInvCountingTaskBiz = scmInvCountingTaskBiz;
	}
    
    public void setScmCstFrmLossBiz(ScmCstFrmLossBiz scmCstFrmLossBiz) {
		this.scmCstFrmLossBiz = scmCstFrmLossBiz;
	}
    
    public void setOrgCostCenterBiz(OrgCostCenterBiz orgCostCenterBiz) {
		this.orgCostCenterBiz = orgCostCenterBiz;
	}

	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	public void setScmIdleItemsBiz(ScmIdleItemsBiz scmIdleItemsBiz) {
		this.scmIdleItemsBiz = scmIdleItemsBiz;
	}

	@Override
	public ScmInvBalOverResult balOver(ScmInvBalOver scmInvBalOver, Param param) throws AppException {
		ScmInvBalOverResult scmInvBalOverResult = new ScmInvBalOverResult(true);
		scmInvBalOverResult.setType(scmInvBalOver.getType());
		boolean sucess = false;
		if(StringUtils.equals("1", scmInvBalOver.getType())){
			//结转
			//1、检查当前期间的单据是否都过账
			List<ScmInvBalOverResultDetail> detailList = checkUnPostBill(scmInvBalOver.getOrgUnitNo(),scmInvBalOver.getPeriodId(),param);
			if(detailList!=null && !detailList.isEmpty()){
				scmInvBalOverResult.setDetailList(detailList);
			}else{
				//清除期间数据
				scmInvPeriodStockBiz.turnbackStock(scmInvBalOver.getOrgUnitNo(),scmInvBalOver.getPeriodId(),false,param);
				//生成成本中心耗用单
				generateInvCostConsume(scmInvBalOver,param);
				//2、结存写入期间结存表，并清除零数量记录
				turnoffStock(scmInvBalOver.getOrgUnitNo(),scmInvBalOver.getPeriodId(), param);
				//3、计算余额表期末余额，并生成下一期间期初
				turnoffBal(scmInvBalOver.getOrgUnitNo(),scmInvBalOver.getPeriodId(),param);
				//4、结转会计期间
				turnoffPeriod(scmInvBalOver.getOrgUnitNo(),scmInvBalOver.getPeriodId(),param);
				sucess = true;
			}
		}else{
			//反结转
			//1、检查当前期间的是否存在过账单据，存在则不允许结转
			List<ScmInvBalOverResultDetail> detailList = checkPostedBill(scmInvBalOver.getOrgUnitNo(),scmInvBalOver.getPeriodId(),param);
			if(detailList!=null && !detailList.isEmpty()){
				scmInvBalOverResult.setDetailList(detailList);
			}else{
				//删除耗用单
				deleteInvCostConsume(scmInvBalOver,param);
				//2、从期间结存表写回零数量记录
				turnbackStock(scmInvBalOver.getOrgUnitNo(),scmInvBalOver.getPeriodId(),param);
				//3、当前期间期初清零
				turnbackBal(scmInvBalOver.getOrgUnitNo(),scmInvBalOver.getPeriodId(),param);
				//4、反结转会计期
				turnbackPeriod(scmInvBalOver.getOrgUnitNo(),scmInvBalOver.getPeriodId(),param);
				sucess = true;
			}
		}
		if(sucess)
			scmInvBalOverResult.setMsgInfo(Message.getMessage(param.getLang(), "iscm.server.ScmInvBalOverBizImpl.turnoff.result.sucess"));
		return scmInvBalOverResult;
	}
	
	private void deleteInvCostConsume(ScmInvBalOver scmInvBalOver, Param param) {
		if (scmInvBalOver != null) {

			PeriodCalendar periodCalendar = periodCalendarBiz.selectPrevPeriod(scmInvBalOver.getPeriodId(), param);
			if (periodCalendar !=null) {
				List<ScmInvCostConsume2> scmInvCostConsumes = scmInvCostConsumeBiz.selectGenerateBill(periodCalendar.getPeriodId(),scmInvBalOver.getOrgUnitNo(), param);
				if (scmInvCostConsumes !=null && scmInvCostConsumes.size()>0) {
					scmInvCostConsumeBiz.delete(scmInvCostConsumes, param);
				}
			}
		}
	}
		

	private void generateInvCostConsume(ScmInvBalOver scmInvBalOver, Param param) {
		//1、查询财务组织下以领代耗的成本中心
		Page page = new Page();
		page.setModelClass(OrgCostCenter2.class);
		page.setShowCount(Integer.MAX_VALUE);
		List<String> argList = new ArrayList<String>();
		argList.add("type=from");
        argList.add("relationType=" + OrgUnitRelationType.COSTTOFIN);
        argList.add("toOrgUnitNo=" + scmInvBalOver.getOrgUnitNo());
		List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.queryPage(page, argList, "queryPageEx", param);
		StringBuffer costOrgUnitNos = new StringBuffer("");
		if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()) {
			for(OrgCostCenter2 orgCostCenter:orgCostCenterList) {
				if(StringUtils.equals("1",orgCostCenter.getCostCenterType())) {
					if(StringUtils.isNotBlank(costOrgUnitNos.toString()))
						costOrgUnitNos.append(",");
					costOrgUnitNos.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
				}
			}
		}
		if (StringUtils.isEmpty(costOrgUnitNos.toString())) {
			return;
		}
		//2、查询已领代耗的结存数据
		
		if (StringUtils.isNotEmpty(costOrgUnitNos.toString())) {
			List<ScmInvStock2> scmInvPeriodStocks = scmInvStockBiz.selectByOrgUnitNos(scmInvBalOver.getOrgUnitNo(),costOrgUnitNos.toString(),param);
			if (scmInvPeriodStocks != null && scmInvPeriodStocks.size()>0) {
				Map<String, List<ScmInvStock2>> collect = scmInvPeriodStocks.stream().collect(Collectors.groupingBy(ScmInvStock2 -> ScmInvStock2.getOrgUnitNo()+'-'+ScmInvStock2.getCostOrgUnitNo()));
				for (Entry<String, List<ScmInvStock2>> m : collect.entrySet()) {
			        List<ScmInvStock2> list = collect.get(m.getKey());
			        //创建耗用单主表对象
					CommonBean bean = new CommonBean();
					List<ScmInvCostConsume2> scmInvCostConsumes = new ArrayList<>();
					List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntry2s = new ArrayList<>();
					PeriodCalendar periodCalendar = periodCalendarBiz.selectDirect(scmInvBalOver.getPeriodId(), param);
					ScmInvCostConsume2 scmInvCostConsume = new ScmInvCostConsume2(true);
					scmInvCostConsume.setBizDate(periodCalendar.getEndDate());
					scmInvCostConsume.setFinOrgUnitNo(list.get(0).getFinOrgUnitNo());
					scmInvCostConsume.setOrgUnitNo(list.get(0).getCostOrgUnitNo());
					scmInvCostConsume.setUseOrgUnitNo(list.get(0).getOrgUnitNo());
					scmInvCostConsume.setOffset(false);
					scmInvCostConsume.setCurrencyNo("");
					scmInvCostConsume.setExchangeRate(BigDecimal.ONE);
					scmInvCostConsume.setCreator(param.getUsrCode());
					scmInvCostConsume.setCreateDate(new Date());
					scmInvCostConsume.setStatus("E");
					scmInvCostConsume.setGenerate(true);
					scmInvCostConsume.setPrtcount(0);
					scmInvCostConsume.setControlUnitNo(param.getControlUnitNo());
					scmInvCostConsume.setDcNo(CodeAutoCalUtil.getBillCode("InvCostConsume", scmInvCostConsume, param));
					scmInvCostConsumes.add(scmInvCostConsume);
					bean.setList(scmInvCostConsumes);
					for (ScmInvStock2 scmInvBal22 : list) {
						//生成明细
						ScmInvCostConsumeEntry2 scmInvCostConsumeEntry = new ScmInvCostConsumeEntry2();
						scmInvCostConsumeEntry.setItemId(scmInvBal22.getItemId());
						scmInvCostConsumeEntry.setBaseUnit(scmInvBal22.getBaseUnit());
						scmInvCostConsumeEntry.setUnit(scmInvBal22.getUnit());
						scmInvCostConsumeEntry.setPieUnit(scmInvBal22.getPieUnit());
						scmInvCostConsumeEntry.setLot(scmInvBal22.getLot());
						scmInvCostConsumeEntry.setQty(scmInvBal22.getQty());
						scmInvCostConsumeEntry.setPrice(scmInvBal22.getPrice());
						scmInvCostConsumeEntry.setTaxPrice(scmInvBal22.getTaxPrice());
						scmInvCostConsumeEntry.setTaxRate(scmInvBal22.getTaxRate());
						scmInvCostConsumeEntry.setPieQty(scmInvBal22.getPieQty());
						scmInvCostConsumeEntry.setAmt(scmInvBal22.getAmt());
						scmInvCostConsumeEntry.setTaxAmt(scmInvBal22.getTaxAmt());
						scmInvCostConsumeEntry.setOffset(false);
						scmInvCostConsumeEntry.setSourceBillDtlId(0);
						scmInvCostConsumeEntry.setStockId(scmInvBal22.getId());
						scmInvCostConsumeEntry.setInvDate(scmInvBal22.getInvDate());
						scmInvCostConsumeEntry.setOrgSourceId(0);
						scmInvCostConsumeEntry.setOrgSourceVendorId(0);
						scmInvCostConsumeEntry2s.add(scmInvCostConsumeEntry);
					}
					bean.setList2(scmInvCostConsumeEntry2s);
					scmInvCostConsumeBiz.add(bean, param);
			    }
			}
		}
	}
	
	private static <T> Predicate<? super BaseModel> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply((T) t), Boolean.TRUE) == null;
    }

	private List<ScmInvBalOverResultDetail> checkUnPostBill(String orgUnitNo, long periodId, Param param) {
		List<ScmInvBalOverResultDetail> detailList = new ArrayList();
		//根据财务组织检查单据
		//采购入库
		List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList = scmInvPurInWarehsBillBiz.checkUnPostBill(orgUnitNo,periodId,param);
		if(scmInvPurInWarehsBillList!=null && !scmInvPurInWarehsBillList.isEmpty()){
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
			scmInvBalOverResultDetail.setBillType("InvPurInWhsBill");
			BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "InvPurInWhsBill", param);
			if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(scmInvPurInWarehsBillList.size())}));
			List<Long> bilList = new ArrayList<>();
			for(ScmInvPurInWarehsBill2 bill:scmInvPurInWarehsBillList){
				bilList.add(bill.getWrId());
			}
			scmInvBalOverResultDetail.setList(bilList);
			detailList.add(scmInvBalOverResultDetail);
		}
		//其他入库
		List<ScmInvOtherInWarehsBill2> scmInvOtherInWarehsBillList = scmInvOtherInWarehsBillBiz.checkUnPostBill(orgUnitNo,periodId,param);
		if(scmInvOtherInWarehsBillList!=null && !scmInvOtherInWarehsBillList.isEmpty()){
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
			scmInvBalOverResultDetail.setBillType("InvOthInWhsBill");
			BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "InvOthInWhsBill", param);
			if (billType != null) {
			    scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(scmInvOtherInWarehsBillList.size())}));
			List<Long> bilList = new ArrayList<>();
			for(ScmInvOtherInWarehsBill2 bill:scmInvOtherInWarehsBillList){
				bilList.add(bill.getWrId());
			}
			scmInvBalOverResultDetail.setList(bilList);
			detailList.add(scmInvBalOverResultDetail);
		}
		//调拨入库
		List<ScmInvMoveInWarehsBill2> scmInvMoveInWarehsBillList = scmInvMoveInWarehsBillBiz.checkUnPostBill(orgUnitNo,periodId,param);
		if(scmInvMoveInWarehsBillList!=null && !scmInvMoveInWarehsBillList.isEmpty()){
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
			scmInvBalOverResultDetail.setBillType("InvMoveInWhsBill");
			BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "InvMoveInWhsBill", param);
            if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(scmInvMoveInWarehsBillList.size())}));
			List<Long> bilList = new ArrayList<>();
			for(ScmInvMoveInWarehsBill2 bill:scmInvMoveInWarehsBillList){
				bilList.add(bill.getWrId());
			}
			scmInvBalOverResultDetail.setList(bilList);
			detailList.add(scmInvBalOverResultDetail);
		}
		//领料出库
		List<ScmInvMaterialReqBill2> scmInvMaterialReqBillList = scmInvMaterialReqBillBiz.checkUnPostBill(orgUnitNo,periodId,param);
		if(scmInvMaterialReqBillList!=null && !scmInvMaterialReqBillList.isEmpty()){
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
			scmInvBalOverResultDetail.setBillType("InvMatReqout");
			BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "InvMatReqout", param);
            if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(scmInvMaterialReqBillList.size())}));
			List<Long> bilList = new ArrayList<>();
			for(ScmInvMaterialReqBill2 bill:scmInvMaterialReqBillList){
				bilList.add(bill.getOtId());
			}
			scmInvBalOverResultDetail.setList(bilList);
			detailList.add(scmInvBalOverResultDetail);
		}
		//调拨出库
		List<ScmInvMoveIssueBill2> scmInvMoveIssueBillList = scmInvMoveIssueBillBiz.checkUnPostBill(orgUnitNo,periodId,param);
		if(scmInvMoveIssueBillList!=null && !scmInvMoveIssueBillList.isEmpty()){
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
			scmInvBalOverResultDetail.setBillType("InvMoveIssueBill");
			BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "InvMoveIssueBill", param);
            if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(scmInvMoveIssueBillList.size())}));
			List<Long> bilList = new ArrayList<>();
			for(ScmInvMoveIssueBill2 bill:scmInvMoveIssueBillList){
				bilList.add(bill.getOtId());
			}
			scmInvBalOverResultDetail.setList(bilList);
			detailList.add(scmInvBalOverResultDetail);
		}
		//其他出库
		List<ScmInvOtherIssueBill2> scmInvOtherIssueBillList = scmInvOtherIssueBillBiz.checkUnPostBill(orgUnitNo,periodId,param);
		if(scmInvOtherIssueBillList!=null && !scmInvOtherIssueBillList.isEmpty()){
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
			scmInvBalOverResultDetail.setBillType("InvOthIssueBill");
			BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "InvOthIssueBill", param);
            if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(scmInvOtherIssueBillList.size())}));
			List<Long> bilList = new ArrayList<>();
			for(ScmInvOtherIssueBill2 bill:scmInvOtherIssueBillList){
				bilList.add(bill.getOtId());
			}
			scmInvBalOverResultDetail.setList(bilList);
			detailList.add(scmInvBalOverResultDetail);
		}
		//销售出库
		List<ScmInvSaleIssueBill2> scmInvSaleIssueBillList = scmInvSaleIssueBillBiz.checkUnPostBill(orgUnitNo,periodId,param);
		if(scmInvSaleIssueBillList!=null && !scmInvSaleIssueBillList.isEmpty()){
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
			scmInvBalOverResultDetail.setBillType("InvSaleIssueBill");
			BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "InvSaleIssueBill", param);
            if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(scmInvSaleIssueBillList.size())}));
			List<Long> bilList = new ArrayList<>();
			for(ScmInvSaleIssueBill2 bill:scmInvSaleIssueBillList){
				bilList.add(bill.getOtId());
			}
			scmInvBalOverResultDetail.setList(bilList);
			detailList.add(scmInvBalOverResultDetail);
		}
		//耗用
		List<ScmInvCostConsume2> scmInvCostConsumeList = scmInvCostConsumeBiz.checkUnPostBill(orgUnitNo,periodId,param);
		if(scmInvCostConsumeList!=null && !scmInvCostConsumeList.isEmpty()){
		    BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "InvCostConsume", param);
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
            scmInvBalOverResultDetail.setBillType("InvCostConsume");
            if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(scmInvCostConsumeList.size())}));
			List<Long> bilList = new ArrayList<>();
			for(ScmInvCostConsume2 bill:scmInvCostConsumeList){
				bilList.add(bill.getDcId());
			}
			scmInvBalOverResultDetail.setList(bilList);
			detailList.add(scmInvBalOverResultDetail);
		}
		//成本转移
		List<ScmInvMoveBill2> scmInvMoveBillList = scmInvMoveBillBiz.checkUnPostBill(orgUnitNo,periodId,param);
		if(scmInvMoveBillList!=null && !scmInvMoveBillList.isEmpty()){
		    BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "InvMoveBill", param);
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
            scmInvBalOverResultDetail.setBillType("InvMoveBill");
            if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(scmInvMoveBillList.size())}));
			List<Long> bilList = new ArrayList<>();
			for(ScmInvMoveBill2 bill:scmInvMoveBillList){
				bilList.add(bill.getWtId());
			}
			scmInvBalOverResultDetail.setList(bilList);
			detailList.add(scmInvBalOverResultDetail);
		}
		//盘点盘存
		List<ScmInvCountingTask2> scmInvCountingTaskList = scmInvCountingTaskBiz.checkUnPostBill(orgUnitNo, periodId, param);
		if(scmInvCountingTaskList!=null && !scmInvCountingTaskList.isEmpty()){
		    BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "CountTable", param);
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
            scmInvBalOverResultDetail.setBillType("CountTable");
            if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			List<Long> bilList = new ArrayList<>();
			for(ScmInvCountingTask2 bill:scmInvCountingTaskList){
				if(!bill.isCostCenter())
					bilList.add(bill.getTaskId());
			}
			if(!bilList.isEmpty()) {
				scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(bilList.size())}));
				scmInvBalOverResultDetail.setList(bilList);
				detailList.add(scmInvBalOverResultDetail);
			}
		}
		if(scmInvCountingTaskList!=null && !scmInvCountingTaskList.isEmpty()){
		    BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "CountCostCenter", param);
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
            scmInvBalOverResultDetail.setBillType("CountCostCenter");
            if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			List<Long> bilList = new ArrayList<>();
			for(ScmInvCountingTask2 bill:scmInvCountingTaskList){
				if(bill.isCostCenter())
					bilList.add(bill.getTaskId());
			}
			if(!bilList.isEmpty()) {
				scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(bilList.size())}));
				scmInvBalOverResultDetail.setList(bilList);
				detailList.add(scmInvBalOverResultDetail);
			}
		}
		//成本中心报损
		List<ScmCstFrmLoss2> scmCstFrmLossList = scmCstFrmLossBiz.checkUnPostBill(orgUnitNo,periodId,param);
		if(scmCstFrmLossList!=null && !scmCstFrmLossList.isEmpty()){
		    BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "CstFrmLoss", param);
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
            scmInvBalOverResultDetail.setBillType("CstFrmLoss");
            if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(scmCstFrmLossList.size())}));
			List<Long> bilList = new ArrayList<>();
			for(ScmCstFrmLoss2 bill:scmCstFrmLossList){
				bilList.add(bill.getId());
			}
			scmInvBalOverResultDetail.setList(bilList);
			detailList.add(scmInvBalOverResultDetail);
		}
		return detailList;
	}


	private void turnoffStock(String orgUnitNo, long periodId, Param param) {
		//清除闲置物资结存数量为0的
		scmIdleItemsBiz.deleteZeroQty(orgUnitNo,param);
		scmInvPeriodStockBiz.turnoffStock(orgUnitNo,periodId,false,param);
		scmInvStockBiz.deleteZeroQty(orgUnitNo,false,param);
	}

	private void turnoffBal(String orgUnitNo, long periodId, Param param) {
		//清除以领代耗的部门结存
		scmInvBalBiz.delDeplete(orgUnitNo,periodId,param);
		//计算期末结存
		scmInvBalBiz.calcEndBal(orgUnitNo,periodId,param);
		//生成下一期间初始结存
		PeriodCalendar periodCalendar = periodCalendarBiz.selectNextPeriod(periodId, param);
		if(periodCalendar!=null){
			scmInvBalBiz.buildNextPeriodStartBal(orgUnitNo,periodId,periodCalendar.getPeriodId(),periodCalendar.getAccountYear(),periodCalendar.getAccountPeriod(),param);
		}
	}

	private void turnoffPeriod(String orgUnitNo, long periodId, Param param) {
		PeriodCalendar periodCalendar = periodCalendarBiz.selectNextPeriod(periodId, param);
		if(periodCalendar!=null){
			SystemState systemState = new SystemState();
			systemState.setOrgUnitNo(orgUnitNo);
			systemState.setSystemid(170L);
			systemState = systemStateBiz.selectBySystemId(systemState, param);
			if(systemState!=null){
				systemState.setCurrentPeriodId(periodCalendar.getPeriodId());
				systemStateBiz.updateDirect(systemState, param);
			}
		}
	}

	private List<ScmInvBalOverResultDetail> checkPostedBill(String orgUnitNo, long periodId, Param param) {
		List<ScmInvBalOverResultDetail> detailList = new ArrayList();
		//根据财务组织检查单据
		List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList = scmInvPurInWarehsBillBiz.checkPostedBill(orgUnitNo,periodId,param);
		if(scmInvPurInWarehsBillList!=null && !scmInvPurInWarehsBillList.isEmpty()){
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
			scmInvBalOverResultDetail.setBillType("InvPurInWhsBill");
            BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "InvPurInWhsBill", param);
            if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(scmInvPurInWarehsBillList.size())}));
			List<Long> bilList = new ArrayList<>();
			for(ScmInvPurInWarehsBill2 bill:scmInvPurInWarehsBillList){
				bilList.add(bill.getWrId());
			}
			scmInvBalOverResultDetail.setList(bilList);
			detailList.add(scmInvBalOverResultDetail);
		}
		List<ScmInvOtherInWarehsBill2> scmInvOtherInWarehsBillList = scmInvOtherInWarehsBillBiz.checkPostedBill(orgUnitNo,periodId,param);
		if(scmInvOtherInWarehsBillList!=null && !scmInvOtherInWarehsBillList.isEmpty()){
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
			scmInvBalOverResultDetail.setBillType("InvOthInWhsBill");
            BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "InvOthInWhsBill", param);
            if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(scmInvOtherInWarehsBillList.size())}));
			List<Long> bilList = new ArrayList<>();
			for(ScmInvOtherInWarehsBill2 bill:scmInvOtherInWarehsBillList){
				bilList.add(bill.getWrId());
			}
			scmInvBalOverResultDetail.setList(bilList);
			detailList.add(scmInvBalOverResultDetail);
		}
		List<ScmInvMoveInWarehsBill2> scmInvMoveInWarehsBillList = scmInvMoveInWarehsBillBiz.checkPostedBill(orgUnitNo,periodId,param);
		if(scmInvMoveInWarehsBillList!=null && !scmInvMoveInWarehsBillList.isEmpty()){
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
			scmInvBalOverResultDetail.setBillType("InvMoveInWhsBill");
            BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "InvMoveInWhsBill", param);
            if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(scmInvMoveInWarehsBillList.size())}));
			List<Long> bilList = new ArrayList<>();
			for(ScmInvMoveInWarehsBill2 bill:scmInvMoveInWarehsBillList){
				bilList.add(bill.getWrId());
			}
			scmInvBalOverResultDetail.setList(bilList);
			detailList.add(scmInvBalOverResultDetail);
		}
		List<ScmInvMaterialReqBill2> scmInvMaterialReqBillList = scmInvMaterialReqBillBiz.checkPostedBill(orgUnitNo,periodId,param);
		if(scmInvMaterialReqBillList!=null && !scmInvMaterialReqBillList.isEmpty()){
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
			scmInvBalOverResultDetail.setBillType("InvMatReqout");
            BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "InvMatReqout", param);
            if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(scmInvMaterialReqBillList.size())}));
			List<Long> bilList = new ArrayList<>();
			for(ScmInvMaterialReqBill2 bill:scmInvMaterialReqBillList){
				bilList.add(bill.getOtId());
			}
			scmInvBalOverResultDetail.setList(bilList);
			detailList.add(scmInvBalOverResultDetail);
		}
		List<ScmInvMoveIssueBill2> scmInvMoveIssueBillList = scmInvMoveIssueBillBiz.checkPostedBill(orgUnitNo,periodId,param);
		if(scmInvMoveIssueBillList!=null && !scmInvMoveIssueBillList.isEmpty()){
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
			scmInvBalOverResultDetail.setBillType("InvMoveIssueBill");
            BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "InvMoveIssueBill", param);
            if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(scmInvMoveIssueBillList.size())}));
			List<Long> bilList = new ArrayList<>();
			for(ScmInvMoveIssueBill2 bill:scmInvMoveIssueBillList){
				bilList.add(bill.getOtId());
			}
			scmInvBalOverResultDetail.setList(bilList);
			detailList.add(scmInvBalOverResultDetail);
		}
		List<ScmInvOtherIssueBill2> scmInvOtherIssueBillList = scmInvOtherIssueBillBiz.checkPostedBill(orgUnitNo,periodId,param);
		if(scmInvOtherIssueBillList!=null && !scmInvOtherIssueBillList.isEmpty()){
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
			scmInvBalOverResultDetail.setBillType("InvOthIssueBill");
            BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "InvOthIssueBill", param);
            if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(scmInvOtherIssueBillList.size())}));
			List<Long> bilList = new ArrayList<>();
			for(ScmInvOtherIssueBill2 bill:scmInvOtherIssueBillList){
				bilList.add(bill.getOtId());
			}
			scmInvBalOverResultDetail.setList(bilList);
			detailList.add(scmInvBalOverResultDetail);
		}
		List<ScmInvSaleIssueBill2> scmInvSaleIssueBillList = scmInvSaleIssueBillBiz.checkPostedBill(orgUnitNo,periodId,param);
		if(scmInvSaleIssueBillList!=null && !scmInvSaleIssueBillList.isEmpty()){
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
			scmInvBalOverResultDetail.setBillType("InvSaleIssueBill");
            BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "InvSaleIssueBill", param);
            if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(scmInvSaleIssueBillList.size())}));
			List<Long> bilList = new ArrayList<>();
			for(ScmInvSaleIssueBill2 bill:scmInvSaleIssueBillList){
				bilList.add(bill.getOtId());
			}
			scmInvBalOverResultDetail.setList(bilList);
			detailList.add(scmInvBalOverResultDetail);
		}
		List<ScmInvCostConsume2> scmInvCostConsumeList = scmInvCostConsumeBiz.checkPostedBill(orgUnitNo,periodId,param);
		if(scmInvCostConsumeList!=null && !scmInvCostConsumeList.isEmpty()){
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
			scmInvBalOverResultDetail.setBillType("InvCostConsume");
            BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "InvCostConsume", param);
            if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(scmInvCostConsumeList.size())}));
			List<Long> bilList = new ArrayList<>();
			for(ScmInvCostConsume2 bill:scmInvCostConsumeList){
				bilList.add(bill.getDcId());
			}
			scmInvBalOverResultDetail.setList(bilList);
			detailList.add(scmInvBalOverResultDetail);
		}
		List<ScmInvMoveBill2> scmInvMoveBillList = scmInvMoveBillBiz.checkPostedBill(orgUnitNo,periodId,param);
		if(scmInvMoveBillList!=null && !scmInvMoveBillList.isEmpty()){
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
			scmInvBalOverResultDetail.setBillType("InvMoveBill");
            BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "InvMoveBill", param);
            if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(scmInvMoveBillList.size())}));
			List<Long> bilList = new ArrayList<>();
			for(ScmInvMoveBill2 bill:scmInvMoveBillList){
				bilList.add(bill.getWtId());
			}
			scmInvBalOverResultDetail.setList(bilList);
			detailList.add(scmInvBalOverResultDetail);
		}
		List<ScmCstFrmLoss2> scmCstFrmLossList = scmCstFrmLossBiz.checkPostedBill(orgUnitNo,periodId,param);
		if(scmCstFrmLossList!=null && !scmCstFrmLossList.isEmpty()){
			ScmInvBalOverResultDetail scmInvBalOverResultDetail = new ScmInvBalOverResultDetail();
			scmInvBalOverResultDetail.setBillType("CstFrmLoss");
            BillType2 billType = billTypeBiz.selectByOrgAndCode(orgUnitNo, "CstFrmLoss", param);
            if (billType != null) {
                scmInvBalOverResultDetail.setConvertMap(ScmInvBalOverResultDetail.FN_BILLTYPE, billType);
            }
			scmInvBalOverResultDetail.setMsgInfo(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvBalOver.bill.total", new String[]{String.valueOf(scmCstFrmLossList.size())}));
			List<Long> bilList = new ArrayList<>();
			for(ScmCstFrmLoss2 bill:scmCstFrmLossList){
				bilList.add(bill.getId());
			}
			scmInvBalOverResultDetail.setList(bilList);
			detailList.add(scmInvBalOverResultDetail);
		}
		return detailList;
	}

	private void turnbackStock(String orgUnitNo, long periodId, Param param) {
		//从期间结存写回零数量记录
		PeriodCalendar periodCalendar = periodCalendarBiz.selectPrevPeriod(periodId, param);
		if(periodCalendar!=null){
			scmInvStockBiz.writeBackZeroQty(orgUnitNo,false,periodCalendar.getPeriodId(),param);
		}
		//清除期间数据
		scmInvPeriodStockBiz.turnbackStock(orgUnitNo,periodId,false,param);
		//清除上一期间数据
		if(periodCalendar!=null){
			scmInvPeriodStockBiz.turnbackStock(orgUnitNo,periodCalendar.getPeriodId(),false,param);
		}
	}

	private void turnbackBal(String orgUnitNo, long periodId, Param param) {
		scmInvBalBiz.clearStartBal(orgUnitNo,periodId,param);
	}

	private void turnbackPeriod(String orgUnitNo, long periodId, Param param) {
		PeriodCalendar periodCalendar = periodCalendarBiz.selectPrevPeriod(periodId, param);
		if(periodCalendar!=null){
			SystemState systemState = new SystemState();
			systemState.setOrgUnitNo(orgUnitNo);
			systemState.setSystemid(170L);
			systemState = systemStateBiz.selectBySystemId(systemState, param);
			if(systemState!=null){
				systemState.setCurrentPeriodId(periodCalendar.getPeriodId());
				systemStateBiz.updateDirect(systemState, param);
			}
		}
	}

}

