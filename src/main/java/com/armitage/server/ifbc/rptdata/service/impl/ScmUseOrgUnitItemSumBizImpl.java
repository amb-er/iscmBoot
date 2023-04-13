package com.armitage.server.ifbc.rptdata.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycle;
import com.armitage.server.ifbc.basedata.model.ScmProductionDeptMapping2;
import com.armitage.server.ifbc.basedata.service.ScmAccountingCycleBiz;
import com.armitage.server.ifbc.basedata.service.ScmProductionDeptMappingBiz;
import com.armitage.server.ifbc.rptdata.dao.ScmUseOrgUnitItemSumDAO;
import com.armitage.server.ifbc.rptdata.model.ScmUseOrgUnitItemSum;
import com.armitage.server.ifbc.rptdata.service.ScmUseOrgUnitItemSumBiz;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLoss;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLossEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsume;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenter;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTask;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBill;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBillEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmCstFrmLossEntryBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCostConsumeEntryBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingCostCenterEntryBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvMoveBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvBal;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvBal2;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvBalBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialReqBillEntryBiz;
import org.springframework.stereotype.Service;

@Service("scmUseOrgUnitItemSumBiz")
public class ScmUseOrgUnitItemSumBizImpl extends BaseBizImpl<ScmUseOrgUnitItemSum> implements ScmUseOrgUnitItemSumBiz {
	private ScmAccountingCycleBiz scmAccountingCycleBiz;
	private ScmProductionDeptMappingBiz scmProductionDeptMappingBiz;
	private PeriodCalendarBiz periodCalendarBiz;
	private ScmInvBalBiz scmInvBalBiz;
	private ScmInvPurInWarehsBillEntryBiz scmInvPurInWarehsBillEntryBiz;
	private ScmInvMaterialReqBillEntryBiz scmInvMaterialReqBillEntryBiz;
	private ScmInvCostConsumeEntryBiz scmInvCostConsumeEntryBiz;
	private ScmInvCountingCostCenterEntryBiz scmInvCountingCostCenterEntryBiz;
	private ScmInvMoveBillEntryBiz scmInvMoveBillEntryBiz;
	private ScmCstFrmLossEntryBiz scmCstFrmLossEntryBiz;
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	
	public void setScmAccountingCycleBiz(ScmAccountingCycleBiz scmAccountingCycleBiz) {
		this.scmAccountingCycleBiz = scmAccountingCycleBiz;
	}

	public void setScmProductionDeptMappingBiz(ScmProductionDeptMappingBiz scmProductionDeptMappingBiz) {
		this.scmProductionDeptMappingBiz = scmProductionDeptMappingBiz;
	}

	public void setPeriodCalendarBiz(PeriodCalendarBiz periodCalendarBiz) {
		this.periodCalendarBiz = periodCalendarBiz;
	}

	public void setScmInvBalBiz(ScmInvBalBiz scmInvBalBiz) {
		this.scmInvBalBiz = scmInvBalBiz;
	}
	
	public void setScmInvPurInWarehsBillEntryBiz(ScmInvPurInWarehsBillEntryBiz scmInvPurInWarehsBillEntryBiz) {
		this.scmInvPurInWarehsBillEntryBiz = scmInvPurInWarehsBillEntryBiz;
	}

	public void setScmInvMaterialReqBillEntryBiz(ScmInvMaterialReqBillEntryBiz scmInvMaterialReqBillEntryBiz) {
		this.scmInvMaterialReqBillEntryBiz = scmInvMaterialReqBillEntryBiz;
	}

	public void setScmInvCostConsumeEntryBiz(ScmInvCostConsumeEntryBiz scmInvCostConsumeEntryBiz) {
		this.scmInvCostConsumeEntryBiz = scmInvCostConsumeEntryBiz;
	}

	public void setScmInvCountingCostCenterEntryBiz(ScmInvCountingCostCenterEntryBiz scmInvCountingCostCenterEntryBiz) {
		this.scmInvCountingCostCenterEntryBiz = scmInvCountingCostCenterEntryBiz;
	}

	public void setScmInvMoveBillEntryBiz(ScmInvMoveBillEntryBiz scmInvMoveBillEntryBiz) {
		this.scmInvMoveBillEntryBiz = scmInvMoveBillEntryBiz;
	}

	public void setScmCstFrmLossEntryBiz(ScmCstFrmLossEntryBiz scmCstFrmLossEntryBiz) {
		this.scmCstFrmLossEntryBiz = scmCstFrmLossEntryBiz;
	}

	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}

	@Override
	public void calcRptData(String orgUnitNo, long begPeriodId, long endPeriodId, Param param) throws AppException {
		List<ScmAccountingCycle> scmAccountingCycleList = scmAccountingCycleBiz.selectByBegAndEndId(orgUnitNo, begPeriodId, endPeriodId, param);
		if(scmAccountingCycleList==null || scmAccountingCycleList.isEmpty()) {
			throw new AppException("field.ScmAccountingCycle.error.wrongperiod");
		}
		List<ScmProductionDeptMapping2> scmProductionDeptMappingList = scmProductionDeptMappingBiz.selectByOrgUnit(orgUnitNo, param);
		if(scmProductionDeptMappingList==null || scmProductionDeptMappingList.isEmpty()) {
			throw new AppException("field.ScmProductionDeptMapping.error.nodata");
		}
		for(ScmAccountingCycle scmAccountingCycle:scmAccountingCycleList) {
			for(ScmProductionDeptMapping2 scmProductionDeptMapping:scmProductionDeptMappingList) {
				this.delRptData(scmProductionDeptMapping.getDeptNo(),scmAccountingCycle.getId(), param);
				List<ScmUseOrgUnitItemSum> scmUseOrgUnitItemSumList = this.calcDeptPeriodData(scmAccountingCycle, scmProductionDeptMapping.getDeptNo(), param);
				if(scmUseOrgUnitItemSumList!=null && !scmUseOrgUnitItemSumList.isEmpty())
					this.batchAdd(scmUseOrgUnitItemSumList, param);
			}
		}
	}

	private List<ScmUseOrgUnitItemSum> calcDeptPeriodData(ScmAccountingCycle scmAccountingCycle,String orgUnitNo,Param param) throws AppException {
		List<ScmUseOrgUnitItemSum> scmUseOrgUnitItemSumList = new ArrayList<ScmUseOrgUnitItemSum>();
		Date beginDate = scmAccountingCycle.getStartDate();
		Date endDate = scmAccountingCycle.getEndDate();
		// 获取期间期初数
		PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(beginDate, param);
		Page page = new Page();
		page.setModelClass(ScmInvBal.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTCENTER,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_COSTCENTER,QueryParam.QUERY_EQ, "1"));
		page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_ORGUNITNO,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_ORGUNITNO,QueryParam.QUERY_EQ, orgUnitNo));
		page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_PERIODID,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvBal.class) + "." + ScmInvBal.FN_PERIODID,
						QueryParam.QUERY_EQ, String.valueOf(scmAccountingCycle.getPeriodId())));
		List<String> arglist = new ArrayList<>();
		arglist.add("controlUnitNo=" + param.getControlUnitNo());
		List<ScmInvBal2> scmInvBalList = scmInvBalBiz.queryPage(page, arglist, "findItemPage", param);
		if (scmInvBalList != null && !scmInvBalList.isEmpty()) {
			for (ScmInvBal2 scmInvBal : scmInvBalList) {
				boolean exists = false;
				if (!scmUseOrgUnitItemSumList.isEmpty()) {
					for (ScmUseOrgUnitItemSum existScmUseOrgUnitItemSum : scmUseOrgUnitItemSumList) {
						if (existScmUseOrgUnitItemSum.getItemId() == scmInvBal.getItemId() && StringUtils.equals(
								existScmUseOrgUnitItemSum.getOrgUnitNo(), scmInvBal.getOrgUnitNo())) {
							existScmUseOrgUnitItemSum.setStartQty(existScmUseOrgUnitItemSum.getStartQty().add(scmInvBal.getStartQty()));
							existScmUseOrgUnitItemSum.setStartAmt(existScmUseOrgUnitItemSum.getStartAmt().add(scmInvBal.getStartTaxAmt()));
							exists = true;
						}
					}
				}
				if (!exists) {
					ScmUseOrgUnitItemSum scmUseOrgUnitItemSum = new ScmUseOrgUnitItemSum(true);
					scmUseOrgUnitItemSum.setCkeckPeriodId(scmAccountingCycle.getId());
					scmUseOrgUnitItemSum.setPeriodId(scmInvBal.getPeriodId());
					scmUseOrgUnitItemSum.setItemId(scmInvBal.getItemId());
					scmUseOrgUnitItemSum.setUnitId(scmInvBal.getUnit());
					scmUseOrgUnitItemSum.setOrgUnitNo(scmInvBal.getOrgUnitNo());
					scmUseOrgUnitItemSum.setStartQty(scmInvBal.getStartQty());
					scmUseOrgUnitItemSum.setStartAmt(scmInvBal.getStartTaxAmt());
					scmUseOrgUnitItemSumList.add(scmUseOrgUnitItemSum);
				}
			}
		}
		// 获取入库直拨数据
		page = new Page();
		page.setModelClass(ScmInvPurInWarehsBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
						+ ScmInvPurInWarehsBillEntry2.FN_USEORGUNITNO,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "."
								+ ScmInvPurInWarehsBillEntry2.FN_USEORGUNITNO,
						QueryParam.QUERY_EQ, orgUnitNo));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
						+ ScmInvPurInWarehsBill2.FN_BIZDATE, new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
										+ ScmInvPurInWarehsBill2.FN_BIZDATE,
								QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), FormatUtils.fmtDate(endDate)));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
						+ ScmInvPurInWarehsBill2.FN_STATUS,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "."
						+ ScmInvPurInWarehsBill2.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		arglist = new ArrayList<>();
		arglist.add("controlUnitNo=" + param.getControlUnitNo());
		List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = scmInvPurInWarehsBillEntryBiz.queryPage(page,
				arglist, "findAllPage", param);
		if (scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()) {
			for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList) {
				BigDecimal sideFlag = BigDecimal.ONE;
				if (StringUtils.contains("6,8", scmInvPurInWarehsBillEntry.getBizType())) {
					sideFlag = new BigDecimal("-1");
				}
				if (scmInvPurInWarehsBillEntry.getBizDate().compareTo(beginDate) < 0) {
					// 比查询日期小的统计到期初数据中，并从List中删除
					boolean exists = false;
					if (!scmUseOrgUnitItemSumList.isEmpty()) {
						for (ScmUseOrgUnitItemSum existScmUseOrgUnitItemSum : scmUseOrgUnitItemSumList) {
							if (existScmUseOrgUnitItemSum.getItemId() == scmInvPurInWarehsBillEntry.getItemId()
									&& StringUtils.equals(existScmUseOrgUnitItemSum.getOrgUnitNo(),
											scmInvPurInWarehsBillEntry.getUseOrgUnitNo())) {
								existScmUseOrgUnitItemSum.setStartQty(existScmUseOrgUnitItemSum.getStartQty()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty())));
								existScmUseOrgUnitItemSum.setStartAmt(existScmUseOrgUnitItemSum.getStartAmt()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt())));
								exists = true;
							}
						}
					}
					if (!exists) {
						ScmUseOrgUnitItemSum scmUseOrgUnitItemSum = new ScmUseOrgUnitItemSum(true);
						scmUseOrgUnitItemSum.setCkeckPeriodId(scmAccountingCycle.getId());
						scmUseOrgUnitItemSum.setPeriodId(scmInvPurInWarehsBillEntry.getPeriodId());
						scmUseOrgUnitItemSum.setItemId(scmInvPurInWarehsBillEntry.getItemId());
						scmUseOrgUnitItemSum.setUnitId(scmInvPurInWarehsBillEntry.getUnit());
						scmUseOrgUnitItemSum.setOrgUnitNo(scmInvPurInWarehsBillEntry.getUseOrgUnitNo());
						scmUseOrgUnitItemSum.setStartQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
						scmUseOrgUnitItemSum.setStartAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
						scmUseOrgUnitItemSumList.add(scmUseOrgUnitItemSum);
					}
				} else {
					boolean exists = false;
					if (!scmUseOrgUnitItemSumList.isEmpty()) {
						for (ScmUseOrgUnitItemSum existScmUseOrgUnitItemSum : scmUseOrgUnitItemSumList) {
							if (existScmUseOrgUnitItemSum.getItemId() == scmInvPurInWarehsBillEntry.getItemId()
									&& StringUtils.equals(existScmUseOrgUnitItemSum.getOrgUnitNo(),
											scmInvPurInWarehsBillEntry.getUseOrgUnitNo())) {
								existScmUseOrgUnitItemSum.setDiQty(existScmUseOrgUnitItemSum.getDiQty()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty())));
								existScmUseOrgUnitItemSum.setDiAmt(existScmUseOrgUnitItemSum.getDiAmt()
										.add(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt())));
								exists = true;
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmUseOrgUnitItemSum scmUseOrgUnitItemSum = new ScmUseOrgUnitItemSum(true);
						scmUseOrgUnitItemSum.setCkeckPeriodId(scmAccountingCycle.getId());
						scmUseOrgUnitItemSum.setPeriodId(scmInvPurInWarehsBillEntry.getPeriodId());
						scmUseOrgUnitItemSum.setItemId(scmInvPurInWarehsBillEntry.getItemId());
						scmUseOrgUnitItemSum.setUnitId(scmInvPurInWarehsBillEntry.getUnit());
						scmUseOrgUnitItemSum.setOrgUnitNo(scmInvPurInWarehsBillEntry.getUseOrgUnitNo());
						scmUseOrgUnitItemSum.setDiQty(sideFlag.multiply(scmInvPurInWarehsBillEntry.getQty()));
						scmUseOrgUnitItemSum.setDiAmt(sideFlag.multiply(scmInvPurInWarehsBillEntry.getTaxAmt()));
						scmUseOrgUnitItemSumList.add(scmUseOrgUnitItemSum);
					}
				}
			}
  		}
		// 获取领料出库数据
		page = new Page();
		page.setModelClass(ScmInvPurInWarehsBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
						+ ScmInvMaterialReqBill2.FN_USEORGUNITNO,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
								+ ScmInvMaterialReqBill.FN_USEORGUNITNO,
						QueryParam.QUERY_EQ, orgUnitNo));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
						+ ScmInvMaterialReqBill2.FN_BIZDATE, new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
										+ ScmInvMaterialReqBill2.FN_BIZDATE,
								QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), FormatUtils.fmtDate(endDate)));
			page.getParam()
					.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
							+ ScmInvMaterialReqBill2.FN_STATUS,
					new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
							+ ScmInvMaterialReqBill2.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		
		List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryBiz.queryPage(page,arglist, "findAllPage", param);
		if (scmInvMaterialReqBillEntryList != null && !scmInvMaterialReqBillEntryList.isEmpty()) {
			for (ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryList) {
				BigDecimal sideFlag = BigDecimal.ONE;
				if (StringUtils.contains("2", scmInvMaterialReqBillEntry.getBizType())) {
					sideFlag = new BigDecimal("-1");
				}
				if (scmInvMaterialReqBillEntry.getBizDate().compareTo(beginDate) < 0) {
					// 比查询日期小的统计到期初数据中，并从List中删除
					boolean exists = false;
					if (!scmUseOrgUnitItemSumList.isEmpty()) {
						for (ScmUseOrgUnitItemSum existScmUseOrgUnitItemSum : scmUseOrgUnitItemSumList) {
							if (existScmUseOrgUnitItemSum.getItemId() == scmInvMaterialReqBillEntry.getItemId()
									&& StringUtils.equals(existScmUseOrgUnitItemSum.getOrgUnitNo(),
											scmInvMaterialReqBillEntry.getUseOrgUnitNo())) {
								existScmUseOrgUnitItemSum.setStartQty(existScmUseOrgUnitItemSum.getStartQty().add(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
								existScmUseOrgUnitItemSum.setStartAmt(existScmUseOrgUnitItemSum.getStartAmt().add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
								exists = true;
							}
						}
					}
					if (!exists) {
						ScmUseOrgUnitItemSum scmUseOrgUnitItemSum = new ScmUseOrgUnitItemSum(true);
						scmUseOrgUnitItemSum.setCkeckPeriodId(scmAccountingCycle.getId());
						scmUseOrgUnitItemSum.setPeriodId(scmInvMaterialReqBillEntry.getPeriodId());
						scmUseOrgUnitItemSum.setItemId(scmInvMaterialReqBillEntry.getItemId());
						scmUseOrgUnitItemSum.setUnitId(scmInvMaterialReqBillEntry.getUnit());
						scmUseOrgUnitItemSum.setOrgUnitNo(scmInvMaterialReqBillEntry.getUseOrgUnitNo());
						scmUseOrgUnitItemSum.setStartQty(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
						scmUseOrgUnitItemSum.setStartAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
						scmUseOrgUnitItemSumList.add(scmUseOrgUnitItemSum);
					}
				} else {
					boolean exists = false;
					if (!scmUseOrgUnitItemSumList.isEmpty()) {
						for (ScmUseOrgUnitItemSum existScmUseOrgUnitItemSum : scmUseOrgUnitItemSumList) {
							if (existScmUseOrgUnitItemSum.getItemId() == scmInvMaterialReqBillEntry.getItemId()
									&& StringUtils.equals(existScmUseOrgUnitItemSum.getOrgUnitNo(),
											scmInvMaterialReqBillEntry.getUseOrgUnitNo())) {
								existScmUseOrgUnitItemSum.setWiQty(existScmUseOrgUnitItemSum.getWiQty().add(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty())));
								existScmUseOrgUnitItemSum.setWiAmt(existScmUseOrgUnitItemSum.getWiAmt().add(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt())));
								exists = true;
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmUseOrgUnitItemSum scmUseOrgUnitItemSum = new ScmUseOrgUnitItemSum(true);
						scmUseOrgUnitItemSum.setCkeckPeriodId(scmAccountingCycle.getId());
						scmUseOrgUnitItemSum.setPeriodId(scmInvMaterialReqBillEntry.getPeriodId());
						scmUseOrgUnitItemSum.setItemId(scmInvMaterialReqBillEntry.getItemId());
						scmUseOrgUnitItemSum.setUnitId(scmInvMaterialReqBillEntry.getUnit());
						scmUseOrgUnitItemSum.setOrgUnitNo(scmInvMaterialReqBillEntry.getUseOrgUnitNo());
						scmUseOrgUnitItemSum.setWiQty(sideFlag.multiply(scmInvMaterialReqBillEntry.getQty()));
						scmUseOrgUnitItemSum.setWiAmt(sideFlag.multiply(scmInvMaterialReqBillEntry.getTaxAmt()));
						scmUseOrgUnitItemSumList.add(scmUseOrgUnitItemSum);
					}
				}
			}
		}
		// 获取耗用数据
		page = new Page();
		page.setModelClass(ScmInvCostConsumeEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "." + ScmInvCostConsume.FN_USEORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
										+ ScmInvCostConsume.FN_USEORGUNITNO,
								QueryParam.QUERY_EQ, orgUnitNo));
		page.getParam().put(
				ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "." + ScmInvCostConsume.FN_BIZDATE,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
						+ ScmInvCostConsume.FN_BIZDATE, QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), FormatUtils.fmtDate(endDate)));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
						+ ScmInvCostConsume.FN_STATUS,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume.class) + "."
						+ ScmInvCostConsume.FN_STATUS, QueryParam.QUERY_EQ, "E"));
		List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntryList = scmInvCostConsumeEntryBiz.queryPage(page, arglist,"findAllPage", param);
		if (scmInvCostConsumeEntryList != null && !scmInvCostConsumeEntryList.isEmpty()) {
			for (ScmInvCostConsumeEntry2 scmInvCostConsumeEntry : scmInvCostConsumeEntryList) {
				if (scmInvCostConsumeEntry.getBizDate().compareTo(beginDate) < 0) {
					// 比查询日期小的统计到期初数据中，并从List中删除
					boolean exists = false;
					if (!scmUseOrgUnitItemSumList.isEmpty()) {
						for (ScmUseOrgUnitItemSum existScmUseOrgUnitItemSum : scmUseOrgUnitItemSumList) {
							if (existScmUseOrgUnitItemSum.getItemId() == scmInvCostConsumeEntry.getItemId()
									&& StringUtils.equals(existScmUseOrgUnitItemSum.getOrgUnitNo(),
											scmInvCostConsumeEntry.getUseOrgUnitNo())) {
								existScmUseOrgUnitItemSum.setStartQty(existScmUseOrgUnitItemSum.getStartQty().subtract(scmInvCostConsumeEntry.getQty()));
								existScmUseOrgUnitItemSum.setStartAmt(existScmUseOrgUnitItemSum.getStartAmt().subtract(scmInvCostConsumeEntry.getTaxAmt()));
								exists = true;
							}
						}
					}
					if (!exists) {
						ScmUseOrgUnitItemSum scmUseOrgUnitItemSum = new ScmUseOrgUnitItemSum(true);
						scmUseOrgUnitItemSum.setCkeckPeriodId(scmAccountingCycle.getId());
						scmUseOrgUnitItemSum.setPeriodId(scmInvCostConsumeEntry.getPeriodId());
						scmUseOrgUnitItemSum.setItemId(scmInvCostConsumeEntry.getItemId());
						scmUseOrgUnitItemSum.setUnitId(scmInvCostConsumeEntry.getUnit());
						scmUseOrgUnitItemSum.setOrgUnitNo(scmInvCostConsumeEntry.getUseOrgUnitNo());
						scmUseOrgUnitItemSum.setStartQty(BigDecimal.ZERO.subtract(scmInvCostConsumeEntry.getQty()));
						scmUseOrgUnitItemSum.setStartAmt(BigDecimal.ZERO.subtract(scmInvCostConsumeEntry.getTaxAmt()));
						scmUseOrgUnitItemSumList.add(scmUseOrgUnitItemSum);
					}
				} else {
					boolean exists = false;
					if (!scmUseOrgUnitItemSumList.isEmpty()) {
						for (ScmUseOrgUnitItemSum existScmUseOrgUnitItemSum : scmUseOrgUnitItemSumList) {
							if (existScmUseOrgUnitItemSum.getItemId() == scmInvCostConsumeEntry.getItemId()
									&& StringUtils.equals(existScmUseOrgUnitItemSum.getOrgUnitNo(),
											scmInvCostConsumeEntry.getUseOrgUnitNo())) {
								existScmUseOrgUnitItemSum.setDcQty(existScmUseOrgUnitItemSum.getDcQty().add(scmInvCostConsumeEntry.getQty()));
								existScmUseOrgUnitItemSum.setDcAmt(existScmUseOrgUnitItemSum.getDcAmt().add(scmInvCostConsumeEntry.getTaxAmt()));
								exists = true;
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmUseOrgUnitItemSum scmUseOrgUnitItemSum = new ScmUseOrgUnitItemSum(true);
						scmUseOrgUnitItemSum.setCkeckPeriodId(scmAccountingCycle.getId());
						scmUseOrgUnitItemSum.setPeriodId(scmInvCostConsumeEntry.getPeriodId());
						scmUseOrgUnitItemSum.setItemId(scmInvCostConsumeEntry.getItemId());
						scmUseOrgUnitItemSum.setUnitId(scmInvCostConsumeEntry.getUnit());
						scmUseOrgUnitItemSum.setOrgUnitNo(scmInvCostConsumeEntry.getUseOrgUnitNo());
						scmUseOrgUnitItemSum.setDcQty(scmInvCostConsumeEntry.getQty());
						scmUseOrgUnitItemSum.setDcAmt(scmInvCostConsumeEntry.getTaxAmt());
						scmUseOrgUnitItemSumList.add(scmUseOrgUnitItemSum);
					}
				}
			}
		}
		// 盘存差异
		page = new Page();
		page.setModelClass(ScmInvCountingCostCenterEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter.class) + "."
						+ ScmInvCountingCostCenter.FN_USEORGUNITNO,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter.class) + "."
								+ ScmInvCountingCostCenter.FN_USEORGUNITNO,
						QueryParam.QUERY_EQ, orgUnitNo));
		page.getParam().put(
				ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "." + ScmInvCountingTask.FN_BIZDATE,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
						+ ScmInvCountingTask.FN_BIZDATE, QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), FormatUtils.fmtDate(endDate)));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class) + "."
						+ ScmInvCountingCostCenterEntry2.FN_DIFFERQTY,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenterEntry2.class) + "."
						+ ScmInvCountingCostCenterEntry2.FN_DIFFERQTY, QueryParam.QUERY_NE, "0"));
		page.getParam()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
						+ ScmInvCountingTask.FN_STATUS,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask.class) + "."
						+ ScmInvCountingTask.FN_STATUS, QueryParam.QUERY_EQ, "O"));
		List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList = scmInvCountingCostCenterEntryBiz.queryPage(page, arglist, "findAllPage", param);
		long dataEndTimeInventory = System.currentTimeMillis();
		if (scmInvCountingCostCenterEntryList != null && !scmInvCountingCostCenterEntryList.isEmpty()) {
			for (ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry : scmInvCountingCostCenterEntryList) {
				if (scmInvCountingCostCenterEntry.getBizDate().compareTo(beginDate) < 0) {
					// 比查询日期小的统计到期初数据中，并从List中删除
					boolean exists = false;
					if (!scmUseOrgUnitItemSumList.isEmpty()) {
						for (ScmUseOrgUnitItemSum existScmUseOrgUnitItemSum : scmUseOrgUnitItemSumList) {
							if (existScmUseOrgUnitItemSum.getItemId() == scmInvCountingCostCenterEntry.getItemId()
									&& StringUtils.equals(existScmUseOrgUnitItemSum.getOrgUnitNo(),
											scmInvCountingCostCenterEntry.getUseOrgUnitNo())) {
								existScmUseOrgUnitItemSum.setStartQty(existScmUseOrgUnitItemSum.getStartQty().add(scmInvCountingCostCenterEntry.getDifferQty()));
								existScmUseOrgUnitItemSum.setStartAmt(existScmUseOrgUnitItemSum.getStartAmt().add(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
								exists = true;
							}
						}
					}
					if (!exists) {
						ScmUseOrgUnitItemSum scmUseOrgUnitItemSum = new ScmUseOrgUnitItemSum(true);
						scmUseOrgUnitItemSum.setCkeckPeriodId(scmAccountingCycle.getId());
						scmUseOrgUnitItemSum.setPeriodId(scmInvCountingCostCenterEntry.getPeriodId());
						scmUseOrgUnitItemSum.setItemId(scmInvCountingCostCenterEntry.getItemId());
						scmUseOrgUnitItemSum.setUnitId(scmInvCountingCostCenterEntry.getUnit());
						scmUseOrgUnitItemSum.setOrgUnitNo(scmInvCountingCostCenterEntry.getUseOrgUnitNo());
						scmUseOrgUnitItemSum.setStartQty(scmInvCountingCostCenterEntry.getDifferQty());
						scmUseOrgUnitItemSum.setStartAmt(scmInvCountingCostCenterEntry.getDifferTaxAmt());
						scmUseOrgUnitItemSumList.add(scmUseOrgUnitItemSum);
					}
				} else {
					boolean exists = false;
					if (!scmUseOrgUnitItemSumList.isEmpty()) {
						for (ScmUseOrgUnitItemSum existScmUseOrgUnitItemSum : scmUseOrgUnitItemSumList) {
							if (existScmUseOrgUnitItemSum.getItemId() == scmInvCountingCostCenterEntry.getItemId()
									&& StringUtils.equals(existScmUseOrgUnitItemSum.getOrgUnitNo(),
											scmInvCountingCostCenterEntry.getUseOrgUnitNo())) {
								existScmUseOrgUnitItemSum.setDsQty(existScmUseOrgUnitItemSum.getDsQty().subtract(scmInvCountingCostCenterEntry.getDifferQty()));
								existScmUseOrgUnitItemSum.setDsAmt(existScmUseOrgUnitItemSum.getDsAmt().subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
								exists = true;
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmUseOrgUnitItemSum scmUseOrgUnitItemSum = new ScmUseOrgUnitItemSum(true);
						scmUseOrgUnitItemSum.setCkeckPeriodId(scmAccountingCycle.getId());
						scmUseOrgUnitItemSum.setPeriodId(scmInvCountingCostCenterEntry.getPeriodId());
						scmUseOrgUnitItemSum.setItemId(scmInvCountingCostCenterEntry.getItemId());
						scmUseOrgUnitItemSum.setUnitId(scmInvCountingCostCenterEntry.getUnit());
						scmUseOrgUnitItemSum.setOrgUnitNo(scmInvCountingCostCenterEntry.getUseOrgUnitNo());
						scmUseOrgUnitItemSum.setDsQty(BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry.getDifferQty()));
						scmUseOrgUnitItemSum.setDsAmt(BigDecimal.ZERO.subtract(scmInvCountingCostCenterEntry.getDifferTaxAmt()));
						scmUseOrgUnitItemSumList.add(scmUseOrgUnitItemSum);
					}
				}
			}
		}
		// 获取成本转移数据
		page = new Page();
		page.setModelClass(ScmInvMoveBillEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParamOr()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_OUTORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "."
										+ ScmInvMoveBill.FN_OUTORGUNITNO,
								QueryParam.QUERY_EQ, orgUnitNo));
		page.getParamOr()
				.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_INORGUNITNO,
						new QueryParam(
								ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "."
										+ ScmInvMoveBill.FN_INORGUNITNO,
								QueryParam.QUERY_EQ, orgUnitNo));
		page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_BIZDATE,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_BIZDATE,
						QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), FormatUtils.fmtDate(endDate)));
		page.getParam().put(
				ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_STATUS,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmInvMoveBill.class) + "." + ScmInvMoveBill.FN_STATUS,
						QueryParam.QUERY_EQ, "E"));
		List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList = scmInvMoveBillEntryBiz.queryPage(page, arglist,"findAllPage", param);
		if (scmInvMoveBillEntryList != null && !scmInvMoveBillEntryList.isEmpty()) {
			for (ScmInvMoveBillEntry2 scmInvMoveBillEntry : scmInvMoveBillEntryList) {
				if (scmInvMoveBillEntry.getBizDate().compareTo(beginDate) < 0) {
					// 比查询日期小的统计到期初数据中
					if (StringUtils.contains(orgUnitNo, scmInvMoveBillEntry.getOutOrgUnitNo())) {
						// 调出
						boolean exists = false;
						if (!scmUseOrgUnitItemSumList.isEmpty()) {
							for (ScmUseOrgUnitItemSum existScmUseOrgUnitItemSum : scmUseOrgUnitItemSumList) {
								if (existScmUseOrgUnitItemSum.getItemId() == scmInvMoveBillEntry.getItemId()
										&& StringUtils.equals(existScmUseOrgUnitItemSum.getOrgUnitNo(),
												scmInvMoveBillEntry.getOutOrgUnitNo())) {
									existScmUseOrgUnitItemSum.setStartQty(existScmUseOrgUnitItemSum.getStartQty().subtract(scmInvMoveBillEntry.getQty()));
									existScmUseOrgUnitItemSum.setStartAmt(existScmUseOrgUnitItemSum.getStartAmt().subtract(scmInvMoveBillEntry.getTaxAmt()));
									exists = true;
								}
							}
						}
						if (!exists) {
							ScmUseOrgUnitItemSum scmUseOrgUnitItemSum = new ScmUseOrgUnitItemSum(true);
							scmUseOrgUnitItemSum.setCkeckPeriodId(scmAccountingCycle.getId());
							scmUseOrgUnitItemSum.setPeriodId(scmInvMoveBillEntry.getPeriodId());
							scmUseOrgUnitItemSum.setItemId(scmInvMoveBillEntry.getItemId());
							scmUseOrgUnitItemSum.setUnitId(scmInvMoveBillEntry.getUnit());
							scmUseOrgUnitItemSum.setOrgUnitNo(orgUnitNo);
							scmUseOrgUnitItemSum.setStartQty(BigDecimal.ZERO.subtract(scmInvMoveBillEntry.getQty()));
							scmUseOrgUnitItemSum.setStartAmt(BigDecimal.ZERO.subtract(scmInvMoveBillEntry.getTaxAmt()));
							scmUseOrgUnitItemSumList.add(scmUseOrgUnitItemSum);
						}
					}
					if (StringUtils.contains(orgUnitNo, scmInvMoveBillEntry.getInOrgUnitNo())) {
						// 调入
						boolean exists = false;
						if (!scmUseOrgUnitItemSumList.isEmpty()) {
							for (ScmUseOrgUnitItemSum existScmUseOrgUnitItemSum : scmUseOrgUnitItemSumList) {
								if (existScmUseOrgUnitItemSum.getItemId() == scmInvMoveBillEntry.getItemId()
										&& StringUtils.equals(existScmUseOrgUnitItemSum.getOrgUnitNo(),
												scmInvMoveBillEntry.getInOrgUnitNo())) {
									existScmUseOrgUnitItemSum.setStartQty(existScmUseOrgUnitItemSum.getStartQty().add(scmInvMoveBillEntry.getQty()));
									existScmUseOrgUnitItemSum.setStartAmt(existScmUseOrgUnitItemSum.getStartAmt().add(scmInvMoveBillEntry.getTaxAmt()));
									exists = true;
								}
							}
						}
						if (!exists) {
							ScmUseOrgUnitItemSum scmUseOrgUnitItemSum = new ScmUseOrgUnitItemSum(true);
							scmUseOrgUnitItemSum.setCkeckPeriodId(scmAccountingCycle.getId());
							scmUseOrgUnitItemSum.setPeriodId(scmInvMoveBillEntry.getPeriodId());
							scmUseOrgUnitItemSum.setItemId(scmInvMoveBillEntry.getItemId());
							scmUseOrgUnitItemSum.setUnitId(scmInvMoveBillEntry.getUnit());
							scmUseOrgUnitItemSum.setOrgUnitNo(orgUnitNo);
							scmUseOrgUnitItemSum.setStartQty(scmInvMoveBillEntry.getQty());
							scmUseOrgUnitItemSum.setStartAmt(scmInvMoveBillEntry.getTaxAmt());
							scmUseOrgUnitItemSumList.add(scmUseOrgUnitItemSum);
						}
					}
				} else {
					// 放入明细记录结果集中
					if (StringUtils.contains(orgUnitNo, scmInvMoveBillEntry.getOutOrgUnitNo())) {
						boolean exists = false;
						if (!scmUseOrgUnitItemSumList.isEmpty()) {
							for (ScmUseOrgUnitItemSum existScmUseOrgUnitItemSum : scmUseOrgUnitItemSumList) {
								if (existScmUseOrgUnitItemSum.getItemId() == scmInvMoveBillEntry.getItemId()
										&& StringUtils.equals(existScmUseOrgUnitItemSum.getOrgUnitNo(),
												scmInvMoveBillEntry.getOutOrgUnitNo())) {
									existScmUseOrgUnitItemSum.setMoQty(existScmUseOrgUnitItemSum.getMoQty().add(scmInvMoveBillEntry.getQty()));
									existScmUseOrgUnitItemSum.setMoAmt(existScmUseOrgUnitItemSum.getMoAmt().add(scmInvMoveBillEntry.getTaxAmt()));
									exists = true;
								}
							}
						}
						if (!exists) {
							ScmUseOrgUnitItemSum scmUseOrgUnitItemSum = new ScmUseOrgUnitItemSum(true);
							scmUseOrgUnitItemSum.setCkeckPeriodId(scmAccountingCycle.getId());
							scmUseOrgUnitItemSum.setPeriodId(scmInvMoveBillEntry.getPeriodId());
							scmUseOrgUnitItemSum.setItemId(scmInvMoveBillEntry.getItemId());
							scmUseOrgUnitItemSum.setUnitId(scmInvMoveBillEntry.getUnit());
							scmUseOrgUnitItemSum.setOrgUnitNo(scmInvMoveBillEntry.getOutOrgUnitNo());
							scmUseOrgUnitItemSum.setMoQty(scmInvMoveBillEntry.getQty());
							scmUseOrgUnitItemSum.setMoAmt(scmInvMoveBillEntry.getTaxAmt());
							scmUseOrgUnitItemSumList.add(scmUseOrgUnitItemSum);
						}
					}
					if (StringUtils.contains(orgUnitNo, scmInvMoveBillEntry.getInCstOrgUnitNo())) {
						boolean exists = false;
						if (!scmUseOrgUnitItemSumList.isEmpty()) {
							for (ScmUseOrgUnitItemSum existScmUseOrgUnitItemSum : scmUseOrgUnitItemSumList) {
								if (existScmUseOrgUnitItemSum.getItemId() == scmInvMoveBillEntry.getItemId()
										&& StringUtils.equals(existScmUseOrgUnitItemSum.getOrgUnitNo(),
												scmInvMoveBillEntry.getInOrgUnitNo())) {
									existScmUseOrgUnitItemSum.setMiQty(existScmUseOrgUnitItemSum.getMiQty().add(scmInvMoveBillEntry.getQty()));
									existScmUseOrgUnitItemSum.setMiAmt(existScmUseOrgUnitItemSum.getMiAmt().add(scmInvMoveBillEntry.getTaxAmt()));
									exists = true;
								}
							}
						}
						if (!exists) {
							ScmUseOrgUnitItemSum scmUseOrgUnitItemSum = new ScmUseOrgUnitItemSum(true);
							scmUseOrgUnitItemSum.setCkeckPeriodId(scmAccountingCycle.getId());
							scmUseOrgUnitItemSum.setPeriodId(scmInvMoveBillEntry.getPeriodId());
							scmUseOrgUnitItemSum.setItemId(scmInvMoveBillEntry.getItemId());
							scmUseOrgUnitItemSum.setUnitId(scmInvMoveBillEntry.getUnit());
							scmUseOrgUnitItemSum.setOrgUnitNo(scmInvMoveBillEntry.getInOrgUnitNo());
							scmUseOrgUnitItemSum.setMiQty(scmInvMoveBillEntry.getQty());
							scmUseOrgUnitItemSum.setMiAmt(scmInvMoveBillEntry.getTaxAmt());
							scmUseOrgUnitItemSumList.add(scmUseOrgUnitItemSum);
						}
					}
				}
			}
		}
		// 获取报损单数据
		page = new Page();
		page.setModelClass(ScmCstFrmLossEntry2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_ORGUNITNO,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_ORGUNITNO,
						QueryParam.QUERY_EQ, orgUnitNo));
		page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_BIZDATE,
				new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_BIZDATE,
						QueryParam.QUERY_BETWEEN, FormatUtils.fmtDate(periodCalendar.getStartDate()), FormatUtils.fmtDate(endDate)));
		page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_STATUS,
				new QueryParam(
						ClassUtils.getFinalModelSimpleName(ScmCstFrmLoss.class) + "." + ScmCstFrmLoss.FN_STATUS,
						QueryParam.QUERY_EQ, "E"));
		List<ScmCstFrmLossEntry2> scmCstFrmLossEntryList = scmCstFrmLossEntryBiz.queryPage(page, arglist, "findAllPage",param);
		if (scmCstFrmLossEntryList != null && !scmCstFrmLossEntryList.isEmpty()) {
			for (ScmCstFrmLossEntry2 scmCstFrmLossEntry : scmCstFrmLossEntryList) {
				if (scmCstFrmLossEntry.getBizDate().compareTo(beginDate) < 0) {
					// 比查询日期小的统计到期初数据中，并从List中删除
					boolean exists = false;
					if (!scmUseOrgUnitItemSumList.isEmpty()) {
						for (ScmUseOrgUnitItemSum existScmUseOrgUnitItemSum : scmUseOrgUnitItemSumList) {
							if (existScmUseOrgUnitItemSum.getItemId() == scmCstFrmLossEntry.getItemId()
									&& StringUtils.equals(existScmUseOrgUnitItemSum.getOrgUnitNo(),
											scmCstFrmLossEntry.getUseOrgUnitNo())) {
								existScmUseOrgUnitItemSum.setStartQty(existScmUseOrgUnitItemSum.getStartQty().subtract(scmCstFrmLossEntry.getQty()));
								existScmUseOrgUnitItemSum.setStartAmt(existScmUseOrgUnitItemSum.getStartAmt().subtract(scmCstFrmLossEntry.getTaxAmt()));
								exists = true;
							}
						}
					}
					if (!exists) {
						ScmUseOrgUnitItemSum scmUseOrgUnitItemSum = new ScmUseOrgUnitItemSum(true);
						scmUseOrgUnitItemSum.setCkeckPeriodId(scmAccountingCycle.getId());
						scmUseOrgUnitItemSum.setPeriodId(scmCstFrmLossEntry.getPeriodId());
						scmUseOrgUnitItemSum.setUnitId(scmCstFrmLossEntry.getUnit());
						scmUseOrgUnitItemSum.setOrgUnitNo(scmCstFrmLossEntry.getUseOrgUnitNo());
						scmUseOrgUnitItemSum.setStartQty(BigDecimal.ZERO.subtract(scmCstFrmLossEntry.getQty()));
						scmUseOrgUnitItemSum.setStartAmt(BigDecimal.ZERO.subtract(scmCstFrmLossEntry.getTaxAmt()));
						scmUseOrgUnitItemSumList.add(scmUseOrgUnitItemSum);
					}
				} else {
					boolean exists = false;
					if (!scmUseOrgUnitItemSumList.isEmpty()) {
						for (ScmUseOrgUnitItemSum existScmUseOrgUnitItemSum : scmUseOrgUnitItemSumList) {
							if (existScmUseOrgUnitItemSum.getItemId() == scmCstFrmLossEntry.getItemId()
									&& StringUtils.equals(existScmUseOrgUnitItemSum.getOrgUnitNo(),
											scmCstFrmLossEntry.getUseOrgUnitNo())) {
								existScmUseOrgUnitItemSum.setIlQty(existScmUseOrgUnitItemSum.getIlQty().add(scmCstFrmLossEntry.getQty()));
								existScmUseOrgUnitItemSum.setIlAmt(existScmUseOrgUnitItemSum.getIlAmt().add(scmCstFrmLossEntry.getTaxAmt()));
								exists = true;
							}
						}
					}
					if (!exists) {
						// 放入明细记录结果集中
						ScmUseOrgUnitItemSum scmUseOrgUnitItemSum = new ScmUseOrgUnitItemSum(true);
						scmUseOrgUnitItemSum.setCkeckPeriodId(scmAccountingCycle.getId());
						scmUseOrgUnitItemSum.setPeriodId(scmCstFrmLossEntry.getPeriodId());
						scmUseOrgUnitItemSum.setItemId(scmCstFrmLossEntry.getItemId());
						scmUseOrgUnitItemSum.setUnitId(scmCstFrmLossEntry.getUnit());
						scmUseOrgUnitItemSum.setOrgUnitNo(scmCstFrmLossEntry.getUseOrgUnitNo());
						scmUseOrgUnitItemSum.setIlQty(scmCstFrmLossEntry.getQty());
						scmUseOrgUnitItemSum.setIlAmt(scmCstFrmLossEntry.getTaxAmt());
						scmUseOrgUnitItemSumList.add(scmUseOrgUnitItemSum);
					}
				}
			}
		}
		
		if (!scmUseOrgUnitItemSumList.isEmpty()) {
			HashMap<Long,Object> cacheMap = new HashMap<Long,Object>();
			for (ScmUseOrgUnitItemSum scmUseOrgUnitItemSum : scmUseOrgUnitItemSumList) {
				if (scmUseOrgUnitItemSum.getItemId() > 0) {
					ScmMaterialGroup scmMaterialGroup = null;
					if(cacheMap.containsKey(scmUseOrgUnitItemSum.getItemId())) {
						scmMaterialGroup = (ScmMaterialGroup) cacheMap.get(scmUseOrgUnitItemSum.getItemId());
					}else {
						scmMaterialGroup = scmMaterialGroupBiz.selectByItemId(scmUseOrgUnitItemSum.getItemId(), param);
						cacheMap.put(scmUseOrgUnitItemSum.getItemId(), scmMaterialGroup);
					}
					if (scmMaterialGroup!=null) {
						scmUseOrgUnitItemSum.setCostType(StringUtils.isBlank(scmMaterialGroup.getCostType())?"2":scmMaterialGroup.getCostType());
					}else {
						scmUseOrgUnitItemSum.setCostType("2");
					}
				}
			}
		}
		return scmUseOrgUnitItemSumList;
	}

	@Override
	public void batchAdd(List<ScmUseOrgUnitItemSum> scmUseOrgUnitItemSumList, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("scmUseOrgUnitItemSumList", scmUseOrgUnitItemSumList);
		((ScmUseOrgUnitItemSumDAO)dao).batchAdd(map);
	}

	@Override
	public void delRptData(String orgUnitNo, long periodId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("periodId", periodId);
		((ScmUseOrgUnitItemSumDAO)dao).delRptData(map);
	}

}
