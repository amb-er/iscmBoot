package com.armitage.server.ifbc.rptdata.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycle;
import com.armitage.server.ifbc.basedata.service.ScmAccountingCycleBiz;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPrice2;
import com.armitage.server.ifbc.costcard.service.ScmItemCostPriceBiz;
import com.armitage.server.ifbc.rptdata.dao.ScmDiskStdCostInfoDAO;
import com.armitage.server.ifbc.rptdata.model.ScmDiskStdCostInfo;
import com.armitage.server.ifbc.rptdata.service.ScmCookStdCostInfoBiz;
import com.armitage.server.ifbc.rptdata.service.ScmDiskStdCostInfoBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsume2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCostConsumeBiz;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.util.OrgUnitRelationType;
import org.springframework.stereotype.Service;

@Service("scmDiskStdCostInfoBiz")
public class ScmDiskStdCostInfoBizImpl extends BaseBizImpl<ScmDiskStdCostInfo> implements ScmDiskStdCostInfoBiz {
	private ScmAccountingCycleBiz scmAccountingCycleBiz;
	private ScmInvCostConsumeBiz scmInvCostConsumeBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private SysParamBiz sysParamBiz;
	private ScmItemCostPriceBiz scmItemCostPriceBiz;
	private ScmCookStdCostInfoBiz scmCookStdCostInfoBiz;

	public void setScmAccountingCycleBiz(ScmAccountingCycleBiz scmAccountingCycleBiz) {
		this.scmAccountingCycleBiz = scmAccountingCycleBiz;
	}

	public void setScmInvCostConsumeBiz(ScmInvCostConsumeBiz scmInvCostConsumeBiz) {
		this.scmInvCostConsumeBiz = scmInvCostConsumeBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}

	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	public void setScmItemCostPriceBiz(ScmItemCostPriceBiz scmItemCostPriceBiz) {
		this.scmItemCostPriceBiz = scmItemCostPriceBiz;
	}

	public void setScmCookStdCostInfoBiz(ScmCookStdCostInfoBiz scmCookStdCostInfoBiz) {
		this.scmCookStdCostInfoBiz = scmCookStdCostInfoBiz;
	}

	@Override
	public void calcRptData(String orgUnitNo, long begPeriodId, long endPeriodId, Param param) throws AppException {
		ScmAccountingCycle begScmAccountingCycle = scmAccountingCycleBiz.selectDirect(begPeriodId, param);
		ScmAccountingCycle endScmAccountingCycle = scmAccountingCycleBiz.selectDirect(endPeriodId, param);
		if(begScmAccountingCycle==null || endScmAccountingCycle==null) {
			throw new AppException("field.ScmAccountingCycle.error.wrongperiod");
		}
		Date begDate = begScmAccountingCycle.getStartDate();
		Date endDate = endScmAccountingCycle.getEndDate();
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo", param.getOrgUnitNo());
		map.put("begDate", FormatUtils.fmtDate(begDate));
		map.put("endDate", FormatUtils.fmtDate(endDate));
		((ScmDiskStdCostInfoDAO)dao).batchDelete(map);
		((ScmDiskStdCostInfoDAO)dao).batchAdd(map);
	}

	@Override
	public void generateCostConsume(Map<String,String> useOrgUnitNoMap,String resOrgUnitNos, Date begDate, Date endDate, Param param) throws AppException {
		checkPrerequisites(resOrgUnitNos,begDate,endDate, param);
		List<ScmDiskStdCostInfo> scmDiskStdCostInfoList = this.selectByResOrgUnitNos(resOrgUnitNos, begDate, endDate, param);
		if(scmDiskStdCostInfoList != null && !scmDiskStdCostInfoList.isEmpty()){
			Map<String, List<ScmDiskStdCostInfo>> collect = scmDiskStdCostInfoList.stream().collect(Collectors.groupingBy(ScmDiskStdCostInfo -> ScmDiskStdCostInfo.getDeptCode()));
			StringBuffer useOrgUnitNos = new StringBuffer("");
			for (Entry<String, List<ScmDiskStdCostInfo>> m : collect.entrySet()) {
				if(!useOrgUnitNoMap.containsKey(m.getKey())){
					continue;
				}
				if(StringUtils.isNotBlank(useOrgUnitNos.toString())){
					useOrgUnitNos.append(",");
				}
				useOrgUnitNos.append("'").append(useOrgUnitNoMap.get(m.getKey())).append("'");
			}
			if(StringUtils.isNotBlank(useOrgUnitNos.toString())){
				List<ScmInvCostConsume2> scmInvCostConsumeList = scmInvCostConsumeBiz.selectGenerateBillBySourceType(useOrgUnitNos.toString(), "fbcCostConsume", begDate, param);
				if(scmInvCostConsumeList != null && !scmInvCostConsumeList.isEmpty()){
					List<ScmInvCostConsume2> changeScmInvCostConsumeList = new ArrayList<>();
					for(ScmInvCostConsume2 scmInvCostConsume : scmInvCostConsumeList){
						if(StringUtils.equalsIgnoreCase("E", scmInvCostConsume.getStatus())){
							List<String> msgList = scmInvCostConsumeBiz.cancelPostBillCheck(scmInvCostConsume, param);
    						if (msgList != null && !msgList.isEmpty()) {
    							StringBuilder detailInfo = new StringBuilder("");
    	                        detailInfo.append(Message.getMessage(param.getLang(),"iscm.inventorymanage.common.cancelpost.failed"));

    							for (String str : msgList) {
    	                            detailInfo.append(str).append("\n");
    	                        }
    							
    							throw new AppException(detailInfo.toString());
    						}
    						scmInvCostConsume = scmInvCostConsumeBiz.cancelPostBill(scmInvCostConsume, param);
						}
						scmInvCostConsume.setStatus("I");
						changeScmInvCostConsumeList.add(scmInvCostConsume);
					}
					if(changeScmInvCostConsumeList != null && !changeScmInvCostConsumeList.isEmpty()){
						scmInvCostConsumeBiz.update(changeScmInvCostConsumeList, param);
						scmInvCostConsumeBiz.delete(changeScmInvCostConsumeList, param);

					}
				}
			}
			for (Entry<String, List<ScmDiskStdCostInfo>> m : collect.entrySet()) {
				List<ScmDiskStdCostInfo> list = collect.get(m.getKey());
		        //创建耗用单主表对象
				CommonBean bean = new CommonBean();
				List<ScmInvCostConsume2> scmInvCostConsumes = new ArrayList<>();
				List<ScmInvCostConsumeEntry2> scmInvCostConsumeEntry2s = new ArrayList<>();
				ScmInvCostConsume2 scmInvCostConsume = new ScmInvCostConsume2(true);
				scmInvCostConsume.setBizDate(FormatUtils.parseDate(FormatUtils.fmtDate(begDate)));
				if(!useOrgUnitNoMap.containsKey(list.get(0).getDeptCode())){
					continue;
				}
				String useOrgUnitNo = useOrgUnitNoMap.get(list.get(0).getDeptCode());
				List<OrgCostCenter2> outOrgCostCenterList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOCOST, useOrgUnitNo, false, null, param);
		        if (outOrgCostCenterList == null || outOrgCostCenterList.isEmpty()) {
		        	throw new AppException(Message.getMessage(param.getLang(),"com.armitage.server.ifbc.rptdata.service.impl.ScmDiskStdCostInfoBizImpl.noOutCst",new String[] {useOrgUnitNo}));
		        }
		        String generateCostConsumeForFbc = sysParamBiz.getValue(outOrgCostCenterList.get(0).getOrgUnitNo(), "SCM_GenerateCostConsumeForFbc", "N", param);
		        if(!"Y".equalsIgnoreCase(generateCostConsumeForFbc)){
		        	throw new AppException(Message.getMessage(param.getLang(),"com.armitage.server.ifbc.rptdata.service.impl.ScmDiskStdCostInfoBizImpl.generateCostConsumeNotOpen",new String[] {outOrgCostCenterList.get(0).getOrgUnitNo()}));
		        }
				List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOFIN, useOrgUnitNo, false, null, param);
				if(orgCompanyList==null || orgCompanyList.isEmpty()){
					throw new AppException(Message.getMessage(param.getLang(),"com.armitage.server.ifbc.rptdata.service.impl.ScmDiskStdCostInfoBizImpl.reqOrgUnit.notfinorg",new String[] {useOrgUnitNo}));
				}
				scmInvCostConsume.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
				scmInvCostConsume.setOrgUnitNo(outOrgCostCenterList.get(0).getOrgUnitNo());
				scmInvCostConsume.setUseOrgUnitNo(useOrgUnitNo);
				scmInvCostConsume.setOffset(false);
				scmInvCostConsume.setCurrencyNo("");
				scmInvCostConsume.setExchangeRate(BigDecimal.ONE);
				scmInvCostConsume.setCreator(param.getUsrCode());
				scmInvCostConsume.setCreateDate(CalendarUtil.getDate(param));
				scmInvCostConsume.setStatus("I");
				scmInvCostConsume.setGenerate(false);
				scmInvCostConsume.setPrtcount(0);
				scmInvCostConsume.setSourceType("fbcCostConsume");
				scmInvCostConsume.setControlUnitNo(param.getControlUnitNo());
				scmInvCostConsumes.add(scmInvCostConsume);
				bean.setList(scmInvCostConsumes);
				for (ScmDiskStdCostInfo scmDiskStdCostInfo : list) {
					//生成明细
					ScmInvCostConsumeEntry2 scmInvCostConsumeEntry = new ScmInvCostConsumeEntry2();
					scmInvCostConsumeEntry.setItemId(scmDiskStdCostInfo.getItemId());
					scmInvCostConsumeEntry.setUnit(scmDiskStdCostInfo.getUnitId());
					scmInvCostConsumeEntry.setPieUnit(0);
					scmInvCostConsumeEntry.setQty(scmDiskStdCostInfo.getStdQty());
					scmInvCostConsumeEntry.setPrice(scmDiskStdCostInfo.getPrice());
					scmInvCostConsumeEntry.setTaxPrice(scmDiskStdCostInfo.getPrice());
					scmInvCostConsumeEntry.setTaxRate(BigDecimal.ZERO);
					scmInvCostConsumeEntry.setPieQty(BigDecimal.ZERO);
					scmInvCostConsumeEntry.setAmt(scmDiskStdCostInfo.getStdAmt());
					scmInvCostConsumeEntry.setTaxAmt(scmDiskStdCostInfo.getStdAmt());
					scmInvCostConsumeEntry.setOffset(false);
					scmInvCostConsumeEntry.setSourceBillDtlId(0);
					scmInvCostConsumeEntry.setOrgSourceId(0);
					scmInvCostConsumeEntry.setOrgSourceVendorId(0);
					scmInvCostConsumeEntry2s.add(scmInvCostConsumeEntry);
				}
				bean.setList2(scmInvCostConsumeEntry2s);
				scmInvCostConsumeBiz.add(bean, param);
		    }
		}
	}

	private void checkPrerequisites(String resOrgUnitNos, Date begDate, Date endDate, Param param) {
		//1、检查是否当天获取过价格（查询ScmItemCostPrice表获取）
		List<ScmItemCostPrice2> scmItemCostPrice2s = scmItemCostPriceBiz.selectItemCost(resOrgUnitNos,FormatUtils.fmtDate(begDate),param);
		//2、已经获取过，跳过该方法
		if (scmItemCostPrice2s != null && !scmItemCostPrice2s.isEmpty()) {
			return;
		}
		//3、获取物料价格
		int updateByPriceUpdSet = scmItemCostPriceBiz.updateByPriceUpdSet(resOrgUnitNos,param);
		//3、ScmDiskStdCostInfo表数据重新汇总
		this.calcRptDataByTask(resOrgUnitNos,begDate,endDate,param);
		//4、ScmCookStdCostInfo表数据重新汇集
		scmCookStdCostInfoBiz.calcRptDataByTask(resOrgUnitNos, begDate, endDate, param);
	}

	@Override
	public List<ScmDiskStdCostInfo> selectByResOrgUnitNos(String resOrgUnitNos, Date begDate, Date endDate, Param param)
			throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("resOrgUnitNos", resOrgUnitNos);
		map.put("begDate", FormatUtils.fmtDate(begDate));
		map.put("endDate", FormatUtils.fmtDate(endDate));
		return ((ScmDiskStdCostInfoDAO)dao).selectByResOrgUnitNos(map);
	}

	@Override
	public void calcRptDataByTask(String orgUnitNo, Date begDate, Date endDate, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo", param.getOrgUnitNo());
		map.put("begDate", FormatUtils.fmtDate(begDate));
		map.put("endDate", FormatUtils.fmtDate(endDate));
		((ScmDiskStdCostInfoDAO)dao).batchDelete(map);
		((ScmDiskStdCostInfoDAO)dao).batchAdd(map);
	}

}
