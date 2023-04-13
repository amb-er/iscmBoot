package com.armitage.server.ifbc.costcard.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.ifbc.basedata.model.ScmPriceUpdSet;
import com.armitage.server.ifbc.basedata.model.ScmProductionDeptMapping2;
import com.armitage.server.ifbc.basedata.service.ScmPriceUpdSetBiz;
import com.armitage.server.ifbc.basedata.service.ScmProductionDeptMappingBiz;
import com.armitage.server.ifbc.costcard.dao.ScmItemCostPriceLogDetailDAO;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLog;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLogDetail;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLogDetail2;
import com.armitage.server.ifbc.costcard.service.ScmItemCostPriceLogDetailBiz;
import com.armitage.server.ifbc.rptdata.dao.ScmCookStdCostInfoDAO;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import org.springframework.stereotype.Service;

@Service("scmItemCostPriceLogDetailBiz")
public class ScmItemCostPriceLogDetailBizImpl extends BaseBizImpl<ScmItemCostPriceLogDetail2> implements ScmItemCostPriceLogDetailBiz {
	private ScmProductionDeptMappingBiz scmProductionDeptMappingBiz;
	private ScmPriceUpdSetBiz scmPriceUpdSetBiz;
	private ScmMeasureUnitBiz scmMeasureUnitBiz;
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	
	public void setScmProductionDeptMappingBiz(ScmProductionDeptMappingBiz scmProductionDeptMappingBiz) {
		this.scmProductionDeptMappingBiz = scmProductionDeptMappingBiz;
	}

	public void setScmPriceUpdSetBiz(ScmPriceUpdSetBiz scmPriceUpdSetBiz) {
		this.scmPriceUpdSetBiz = scmPriceUpdSetBiz;
	}

	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}

	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}

	@Override
	public void batchAddItemPrice(String orgUnitNo, long logId, Param param) throws AppException{
		List<String> useOrgList = new ArrayList();
		List<ScmProductionDeptMapping2> scmProductionDeptMappingList = scmProductionDeptMappingBiz.selectByOrgUnit(orgUnitNo, param);
		if(scmProductionDeptMappingList!=null && !scmProductionDeptMappingList.isEmpty()) {
			for(ScmProductionDeptMapping2 scmProductionDeptMappings:scmProductionDeptMappingList) {
				useOrgList.add(scmProductionDeptMappings.getDeptNo());
			}
		}else {
			useOrgList.add("0");
		}
		ScmPriceUpdSet scmPriceUpdSet = scmPriceUpdSetBiz.selectByOrgUnit(param.getOrgUnitNo());
		int days=60;
		if(scmPriceUpdSet!=null) {
			days = scmPriceUpdSet.getDays();
		}
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo",orgUnitNo);
		map.put("controlUnitNo", param.getControlUnitNo());
		map.put("accDate",FormatUtils.fmtDate(new Date()));
		map.put("logId",logId);
		map.put("days",days);
		map.put("useOrgList",useOrgList);
		((ScmItemCostPriceLogDetailDAO)dao).batchAddItemPrice(map);
	}

	@Override
	public List<ScmItemCostPriceLogDetail2> getItemPrice(ScmItemCostPriceLog scmItemCostPriceLog, Param param)
			throws AppException {
		List<String> useOrgList = new ArrayList();
		List<ScmProductionDeptMapping2> scmProductionDeptMappingList = scmProductionDeptMappingBiz.selectByOrgUnit(param.getOrgUnitNo(), param);
		if(scmProductionDeptMappingList!=null && !scmProductionDeptMappingList.isEmpty()) {
			for(ScmProductionDeptMapping2 scmProductionDeptMappings:scmProductionDeptMappingList) {
				useOrgList.add(scmProductionDeptMappings.getDeptNo());
			}
		}else {
			useOrgList.add("0");
		}
		ScmPriceUpdSet scmPriceUpdSet = scmPriceUpdSetBiz.selectByOrgUnit(param.getOrgUnitNo());
		int days=60;
		String getPriceType ="1";
		if(scmPriceUpdSet!=null) {
			days = scmPriceUpdSet.getDays();
			getPriceType = scmPriceUpdSet.getUpdPriceType();
		}
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo",param.getOrgUnitNo());
		map.put("controlUnitNo", param.getControlUnitNo());
		map.put("begDate",FormatUtils.fmtDate(scmItemCostPriceLog.getBeginDate()));
		map.put("endDate",FormatUtils.fmtDate(scmItemCostPriceLog.getEndDate()));
		map.put("days",days);
		map.put("useOrgList",useOrgList);
		if(scmItemCostPriceLog.getClassId()>0) {
			List<ScmMaterialGroup> findChild = scmMaterialGroupBiz.findChild(scmItemCostPriceLog.getClassId(), param);
			StringBuffer stringBuffer = new StringBuffer();
			if (findChild != null && !findChild.isEmpty()) {
				for (ScmMaterialGroup scmMaterialGroup : findChild) {
					if (StringUtils.isNotBlank(stringBuffer.toString())) {
						stringBuffer.append(",");
					}
					stringBuffer.append(scmMaterialGroup.getId());
				}
			}else {
				stringBuffer.append(scmItemCostPriceLog.getClassId());
			}
			map.put("classId",stringBuffer.toString());
		}
		if(scmItemCostPriceLog.getItemId()>0) {
			map.put("itemId",scmItemCostPriceLog.getItemId());
		}
		map.put("maxInt", Integer.MAX_VALUE);
		List<ScmItemCostPriceLogDetail2> returnList = new ArrayList<>();
		List<ScmItemCostPriceLogDetail2> scmItemCostPriceLogDetailList = ((ScmItemCostPriceLogDetailDAO)dao).getItemPrice(map);
		List<ScmItemCostPriceLogDetail2> scmInvItemCostPriceLogDetailList = ((ScmItemCostPriceLogDetailDAO)dao).getItemPriceByInvStock(map);
		/**
		 * 1最近入库价
		 * 2结存平均价
		 * 3最近入库价,结存平均价
		 * 4结存平均价,最近入库价
		 */
		switch (getPriceType) {
			case "1":{
				returnList = scmItemCostPriceLogDetailList;
				break;
			}
			case "2":{
				returnList = scmInvItemCostPriceLogDetailList;
				break;
			}
			case "3":{
				if (scmItemCostPriceLogDetailList!= null && !scmItemCostPriceLogDetailList.isEmpty()) {
					if (scmInvItemCostPriceLogDetailList!= null && !scmInvItemCostPriceLogDetailList.isEmpty()) {
						for (ScmItemCostPriceLogDetail2 scmItemCostPriceLogDetail2 : scmItemCostPriceLogDetailList) {
							if (StringUtils.isEmpty(scmItemCostPriceLogDetail2.getPriceSourceBillType())) {
								for (ScmItemCostPriceLogDetail2 scmItemCostPriceLogDetail22 : scmInvItemCostPriceLogDetailList) {
									if (scmItemCostPriceLogDetail22.getItemId()==scmItemCostPriceLogDetail2.getItemId() && scmItemCostPriceLogDetail22.getInvUnitId()==scmItemCostPriceLogDetail2.getInvUnitId()) {
										BeanUtil.copyProperties(scmItemCostPriceLogDetail22,scmItemCostPriceLogDetail2);
										break;
									}
								}
							}
						}
						returnList = scmItemCostPriceLogDetailList;
					}else {
						returnList = scmItemCostPriceLogDetailList;
					}
				}else {
					returnList = scmInvItemCostPriceLogDetailList;
				}
				break;
			}
			case "4":{
				if (scmInvItemCostPriceLogDetailList != null && !scmInvItemCostPriceLogDetailList.isEmpty()) {
					if (scmItemCostPriceLogDetailList != null && !scmItemCostPriceLogDetailList.isEmpty()) {
						for (ScmItemCostPriceLogDetail2 scmItemCostPriceLogDetail2 : scmInvItemCostPriceLogDetailList) {
							if (StringUtils.isEmpty(scmItemCostPriceLogDetail2.getPriceSourceBillType())) {
								for (ScmItemCostPriceLogDetail2 scmItemCostPriceLogDetail22 : scmItemCostPriceLogDetailList) {
									if (scmItemCostPriceLogDetail22.getItemId() == scmItemCostPriceLogDetail2.getItemId() && scmItemCostPriceLogDetail22.getInvUnitId() == scmItemCostPriceLogDetail2.getInvUnitId()) {
										BeanUtil.copyProperties(scmItemCostPriceLogDetail2, scmItemCostPriceLogDetail22);
										break;
									}
								}
							}
						}
						returnList = scmInvItemCostPriceLogDetailList;
					}else {
						returnList = scmInvItemCostPriceLogDetailList;
					}
				}else {
					returnList = scmItemCostPriceLogDetailList;
				}
				break;
			}
		}
		if(returnList!=null) {
			for(ScmItemCostPriceLogDetail2 scmItemCostPriceLogDetail:returnList) {
				setConvertMap(scmItemCostPriceLogDetail,param);
			}
		}
		return returnList;
	}

	private void setConvertMap(ScmItemCostPriceLogDetail2 scmItemCostPriceLogDetail,Param param) {
		if(scmItemCostPriceLogDetail!=null) {
			if(scmItemCostPriceLogDetail.getInvUnitId()>0) {
				ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmItemCostPriceLogDetail.getInvUnitId(), param);
				if(scmMeasureUnit!=null) {
					scmItemCostPriceLogDetail.setConvertMap(ScmItemCostPriceLogDetail2.FN_INVUNITID, scmMeasureUnit);
				}
			}
			if(scmItemCostPriceLogDetail.getCstUnitId()>0) {
				ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmItemCostPriceLogDetail.getCstUnitId(), param);
				if(scmMeasureUnit!=null) {
					scmItemCostPriceLogDetail.setConvertMap(ScmItemCostPriceLogDetail2.FN_CSTUNITID, scmMeasureUnit);
				}
			}
		}
	}

	@Override
	public void batchAdd(List<ScmItemCostPriceLogDetail2> scmItemCostPriceLogDetailList, Param param)
			throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("scmItemCostPriceLogDetailList",scmItemCostPriceLogDetailList);
		((ScmItemCostPriceLogDetailDAO)dao).batchAdd(map);
	}
}
