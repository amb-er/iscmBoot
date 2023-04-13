package com.armitage.server.iscm.common.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.common.dao.TaskSettingDetailDAO;
import com.armitage.server.iscm.common.model.TaskSetting2;
import com.armitage.server.iscm.common.model.TaskSettingDetail;
import com.armitage.server.iscm.common.model.TaskSettingDetail2;
import com.armitage.server.iscm.common.service.TaskSettingDetailBiz;
import com.armitage.server.quartz.model.ScmSystemTask;
import com.armitage.server.quartz.service.ScmSystemTaskBiz;
import com.armitage.server.quartz.util.QuartzUtil;
import org.springframework.stereotype.Service;

@Service("taskSettingDetailBiz")
public class TaskSettingDetailBizImpl extends BaseBizImpl<TaskSettingDetail> implements TaskSettingDetailBiz {
	private ScmSystemTaskBiz scmSystemTaskBiz;

	public void setScmSystemTaskBiz(ScmSystemTaskBiz scmSystemTaskBiz) {
		this.scmSystemTaskBiz = scmSystemTaskBiz;
	}

	@Override
	public void updateByTaskSetting(TaskSetting2 taskSetting, Param param) throws AppException {
		List<TaskSettingDetail> taskSettingDetailList = this.selectBySetId(taskSetting.getId(), param);
		if(taskSettingDetailList==null) {
			taskSettingDetailList = new ArrayList();
		}
		boolean existsPurOrderFlag=false;
		boolean existsInvPurInWarehsFlag=false;
		boolean existsConfirmInfoFlag=false;
		boolean existsRegInfoFlag=false;
		boolean existsConfirmRuleFlag=false;
		boolean existsPurPriceFlag=false;
		boolean existsBusinessQuotationFlag=false;
		boolean existsSupplierStatusPushFlag=false;
		boolean existsQualificationInfoPushFlag=false;
		boolean existsSupplierInfoPullFlag=false;
		boolean existsQualificationInfoPullFlag=false;
		boolean existsIndustryQualifyPushFlag=false;
		boolean existsUpdateFbcItemPriceFlag=false;
		boolean existsGetFbmSalePriceFlag=false;
		boolean existsUpdateFbcCostPriceFlag=false;
		boolean existsGenerateCostConsumeFlag=false;
		boolean existsCalcFbcRptDataFlag=false;
		boolean existsControlUnitPushFlag=false;
		for(TaskSettingDetail taskSettingDetail:taskSettingDetailList) {
			if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"purOrderPush")){
				existsPurOrderFlag = true;
				taskSettingDetail.setFlag(taskSetting.isPurOrderFlag());
				taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
				taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
				taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));	//每次单据数量
				taskSettingDetail.setExtendedParam3(taskSetting.getPurOrderStatus());	//订货单状态
			}
			if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"invPurInWarehsBillPush")){
				existsInvPurInWarehsFlag = true;
				taskSettingDetail.setFlag(taskSetting.isInvPurInWarehsFlag());
				taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
				taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
				taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));
				taskSettingDetail.setExtendedParam3(taskSetting.getInvPurInWarehsStatus());	//入库单状态
			}
			if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"supplierConfirmDataGet")){
				existsConfirmInfoFlag = true;
				taskSettingDetail.setFlag(taskSetting.isConfirmInfoFlag());
				taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
				taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
				taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));
			}
			if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"supplierRegInfoGet")){
				existsRegInfoFlag = true;
				taskSettingDetail.setFlag(taskSetting.isSupplerRegInfoFlag());
				taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
				taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
				taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));
			}
			if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"supplierConfirmRulePush")){
				existsConfirmRuleFlag = true;
				taskSettingDetail.setFlag(taskSetting.isConfirmRuleFlag());
				taskSettingDetail.setTaskCreateMode(taskSetting.getConfirmRuleCreateMode());
				taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
				taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));
			}
			if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"purPricePush")){
				existsPurPriceFlag = true;
				taskSettingDetail.setFlag(taskSetting.isPurPriceFlag());
				taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
				taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
				taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));	//每次单据数量
				taskSettingDetail.setExtendedParam3(taskSetting.getPurPriceStatus());	//定价单状态
			}
			if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"businessQuotationGet")){
				existsBusinessQuotationFlag = true;
				taskSettingDetail.setFlag(taskSetting.isBusinessQuotationFlag());
				taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
				taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
				taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));
			}
			if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"supplierStatusPush")){
				existsSupplierStatusPushFlag = true;
				taskSettingDetail.setFlag(taskSetting.isSupplierStatusPushFlag());
				taskSettingDetail.setTaskCreateMode(taskSetting.getSupplierCreateMode());
				taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
				taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty2()==0?10:taskSetting.getLotQty2()));
			}
			if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"qualificationInfoPush")){
				existsQualificationInfoPushFlag = true;
				taskSettingDetail.setFlag(taskSetting.isQualificationInfoPushFlag());
				taskSettingDetail.setTaskCreateMode(taskSetting.getSupplierCreateMode());
				taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
				taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty2()==0?10:taskSetting.getLotQty2()));
			}
			if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"supplierInfoPull")){
				existsSupplierInfoPullFlag = true;
				taskSettingDetail.setFlag(taskSetting.isSupplierInfoPullFlag());
				taskSettingDetail.setTaskCreateMode(taskSetting.getSupplierCreateMode());
				taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
				taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty2()==0?10:taskSetting.getLotQty2()));
			}
			if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"qualificationInfoPull")){
				existsQualificationInfoPullFlag = true;
				taskSettingDetail.setFlag(taskSetting.isQualificationInfoPullFlag());
				taskSettingDetail.setTaskCreateMode(taskSetting.getSupplierCreateMode());
				taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
				taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty2()==0?10:taskSetting.getLotQty2()));
			}
			if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"industryQualifyPush")){
				existsIndustryQualifyPushFlag = true;
				taskSettingDetail.setFlag(taskSetting.isIndustryQualifyPushFlag());
				taskSettingDetail.setTaskCreateMode(taskSetting.getSupplierCreateMode());
				taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
				taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty2()==0?10:taskSetting.getLotQty2()));
			}
			if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"updateFbcItemPrice")){
				existsUpdateFbcItemPriceFlag = true;
				taskSettingDetail.setFlag(taskSetting.isUpdateFbcItemPriceFlag());
				taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
				taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
				taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty2()==0?10:taskSetting.getLotQty2()));
			}
			if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"getFbmSalePrice")){
				existsGetFbmSalePriceFlag = true;
				taskSettingDetail.setFlag(taskSetting.isGetFbmSalePriceFlag());
				taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
				taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
				taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty2()==0?10:taskSetting.getLotQty2()));
			}
			if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"updateFbcCostPrice")){
				existsUpdateFbcCostPriceFlag = true;
				taskSettingDetail.setFlag(taskSetting.isUpdateFbcCostPriceFlag());
				taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
				taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
				taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));
			}
			if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"generateCostConsume")){
				existsGenerateCostConsumeFlag = true;
				taskSettingDetail.setFlag(taskSetting.isGenerateCostConsumeFlag());
				taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
				taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
				taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));
			}
			if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"calcFbcRptData")){
				existsCalcFbcRptDataFlag = true;
				taskSettingDetail.setFlag(taskSetting.isCalcFbcRptDataFlag());
				taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
				taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
				taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));
			}
			if(StringUtils.equalsIgnoreCase(taskSettingDetail.getTaskObject(),"controlUnitPush")){
				existsControlUnitPushFlag = true;
				taskSettingDetail.setFlag(taskSetting.isControlUnitPushFlag());
				taskSettingDetail.setTaskCreateMode(taskSetting.getControlUnitCreateMode());
				taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getControlCycleTime()));
			}
		}
		if(!existsPurOrderFlag && StringUtils.equalsIgnoreCase("SCM_ESP", taskSetting.getTaskCode())) {
			TaskSettingDetail taskSettingDetail = new TaskSettingDetail(true);
			taskSettingDetail.setSetId(taskSetting.getId());
			taskSettingDetail.setDataClass("Bill");
			taskSettingDetail.setInteractionMode("Push");
			taskSettingDetail.setTaskObject("purOrderPush");
			taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
			taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
			taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));
			taskSettingDetail.setExtendedParam3(taskSetting.getPurOrderStatus());	//订货单状态
			taskSettingDetail.setFlag(taskSetting.isPurOrderFlag());
			taskSettingDetailList.add(taskSettingDetail);
		}
		if(!existsInvPurInWarehsFlag && StringUtils.equalsIgnoreCase("SCM_ESP", taskSetting.getTaskCode())) {
			TaskSettingDetail taskSettingDetail = new TaskSettingDetail(true);
			taskSettingDetail.setSetId(taskSetting.getId());
			taskSettingDetail.setDataClass("Bill");
			taskSettingDetail.setInteractionMode("Push");
			taskSettingDetail.setTaskObject("invPurInWarehsBillPush");
			taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
			taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
			taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));
			taskSettingDetail.setExtendedParam3(taskSetting.getInvPurInWarehsStatus());	//入库单状态
			taskSettingDetail.setFlag(taskSetting.isInvPurInWarehsFlag());
			taskSettingDetailList.add(taskSettingDetail);
		}
		if(!existsConfirmInfoFlag && StringUtils.equalsIgnoreCase("SCM_ESP", taskSetting.getTaskCode())) {
			TaskSettingDetail taskSettingDetail = new TaskSettingDetail(true);
			taskSettingDetail.setSetId(taskSetting.getId());
			taskSettingDetail.setDataClass("Bill");
			taskSettingDetail.setInteractionMode("Pull");
			taskSettingDetail.setTaskObject("supplierConfirmDataGet");
			taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
			taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
			taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));
			taskSettingDetail.setFlag(taskSetting.isConfirmInfoFlag());
			taskSettingDetailList.add(taskSettingDetail);
		}
		if(!existsRegInfoFlag && StringUtils.equalsIgnoreCase("SCM_ESP", taskSetting.getTaskCode())) {
			TaskSettingDetail taskSettingDetail = new TaskSettingDetail(true);
			taskSettingDetail.setSetId(taskSetting.getId());
			taskSettingDetail.setDataClass("Bill");
			taskSettingDetail.setInteractionMode("Pull");
			taskSettingDetail.setTaskObject("supplierRegInfoGet");
			taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
			taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
			taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));
			taskSettingDetail.setFlag(taskSetting.isSupplerRegInfoFlag());
			taskSettingDetailList.add(taskSettingDetail);
		}
		if(!existsConfirmRuleFlag && StringUtils.equalsIgnoreCase("SCM_ESP", taskSetting.getTaskCode())) {
			TaskSettingDetail taskSettingDetail = new TaskSettingDetail(true);
			taskSettingDetail.setSetId(taskSetting.getId());
			taskSettingDetail.setDataClass("Rule");
			taskSettingDetail.setInteractionMode("Push");
			taskSettingDetail.setTaskObject("supplierConfirmRulePush");
			taskSettingDetail.setTaskCreateMode(taskSetting.getConfirmRuleCreateMode());
			taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
			taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));
			taskSettingDetail.setFlag(taskSetting.isConfirmRuleFlag());
			taskSettingDetailList.add(taskSettingDetail);
		}
		if(!existsPurPriceFlag && StringUtils.equalsIgnoreCase("SCM_ESP", taskSetting.getTaskCode())) {
			TaskSettingDetail taskSettingDetail = new TaskSettingDetail(true);
			taskSettingDetail.setSetId(taskSetting.getId());
			taskSettingDetail.setDataClass("Bill");
			taskSettingDetail.setInteractionMode("Push");
			taskSettingDetail.setTaskObject("purPricePush");
			taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
			taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
			taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));
			taskSettingDetail.setExtendedParam3(taskSetting.getPurPriceStatus());	//定价单状态
			taskSettingDetail.setFlag(taskSetting.isPurPriceFlag());
			taskSettingDetailList.add(taskSettingDetail);
		}
		if(!existsBusinessQuotationFlag && StringUtils.equalsIgnoreCase("SCM_ESP", taskSetting.getTaskCode())) {
			TaskSettingDetail taskSettingDetail = new TaskSettingDetail(true);
			taskSettingDetail.setSetId(taskSetting.getId());
			taskSettingDetail.setDataClass("Bill");
			taskSettingDetail.setInteractionMode("Pull");
			taskSettingDetail.setTaskObject("businessQuotationGet");
			taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
			taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
			taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));
			taskSettingDetail.setFlag(taskSetting.isBusinessQuotationFlag());
			taskSettingDetailList.add(taskSettingDetail);
		}
		if(!existsSupplierStatusPushFlag && StringUtils.equalsIgnoreCase("SCM_ESP", taskSetting.getTaskCode())) {
			TaskSettingDetail taskSettingDetail = new TaskSettingDetail(true);
			taskSettingDetail.setSetId(taskSetting.getId());
			taskSettingDetail.setDataClass("Info");
			taskSettingDetail.setInteractionMode("Push");
			taskSettingDetail.setTaskObject("supplierStatusPush");
			taskSettingDetail.setTaskCreateMode(taskSetting.getSupplierCreateMode());
			taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
			taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty2()==0?10:taskSetting.getLotQty2()));
			taskSettingDetail.setFlag(taskSetting.isSupplierStatusPushFlag());
			taskSettingDetailList.add(taskSettingDetail);
		}
		if(!existsQualificationInfoPushFlag && StringUtils.equalsIgnoreCase("SCM_ESP", taskSetting.getTaskCode())) {
			TaskSettingDetail taskSettingDetail = new TaskSettingDetail(true);
			taskSettingDetail.setSetId(taskSetting.getId());
			taskSettingDetail.setDataClass("Info");
			taskSettingDetail.setInteractionMode("Push");
			taskSettingDetail.setTaskObject("qualificationInfoPush");
			taskSettingDetail.setTaskCreateMode(taskSetting.getSupplierCreateMode());
			taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
			taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty2()==0?10:taskSetting.getLotQty2()));
			taskSettingDetail.setFlag(taskSetting.isQualificationInfoPushFlag());
			taskSettingDetailList.add(taskSettingDetail);
		}
		if(!existsSupplierInfoPullFlag && StringUtils.equalsIgnoreCase("SCM_ESP", taskSetting.getTaskCode())) {
			TaskSettingDetail taskSettingDetail = new TaskSettingDetail(true);
			taskSettingDetail.setSetId(taskSetting.getId());
			taskSettingDetail.setDataClass("Info");
			taskSettingDetail.setInteractionMode("Pull");
			taskSettingDetail.setTaskObject("supplierInfoPull");
			taskSettingDetail.setTaskCreateMode(taskSetting.getSupplierCreateMode());
			taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
			taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty2()==0?10:taskSetting.getLotQty2()));
			taskSettingDetail.setFlag(taskSetting.isSupplierInfoPullFlag());
			taskSettingDetailList.add(taskSettingDetail);
		}
		if(!existsQualificationInfoPullFlag && StringUtils.equalsIgnoreCase("SCM_ESP", taskSetting.getTaskCode())) {
			TaskSettingDetail taskSettingDetail = new TaskSettingDetail(true);
			taskSettingDetail.setSetId(taskSetting.getId());
			taskSettingDetail.setDataClass("Info");
			taskSettingDetail.setInteractionMode("Pull");
			taskSettingDetail.setTaskObject("qualificationInfoPull");
			taskSettingDetail.setTaskCreateMode(taskSetting.getSupplierCreateMode());
			taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
			taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty2()==0?10:taskSetting.getLotQty2()));
			taskSettingDetail.setFlag(taskSetting.isQualificationInfoPullFlag());
			taskSettingDetailList.add(taskSettingDetail);
		}
		if(!existsIndustryQualifyPushFlag && StringUtils.equalsIgnoreCase("SCM_ESP", taskSetting.getTaskCode())) {
			TaskSettingDetail taskSettingDetail = new TaskSettingDetail(true);
			taskSettingDetail.setSetId(taskSetting.getId());
			taskSettingDetail.setDataClass("Info");
			taskSettingDetail.setInteractionMode("Push");
			taskSettingDetail.setTaskObject("industryQualifyPush");
			taskSettingDetail.setTaskCreateMode(taskSetting.getSupplierCreateMode());
			taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
			taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty2()==0?10:taskSetting.getLotQty2()));
			taskSettingDetail.setFlag(taskSetting.isIndustryQualifyPushFlag());
			taskSettingDetailList.add(taskSettingDetail);
		}
		if(!existsUpdateFbcItemPriceFlag && StringUtils.equalsIgnoreCase("SCM_FBC", taskSetting.getTaskCode())) {
			TaskSettingDetail taskSettingDetail = new TaskSettingDetail(true);
			taskSettingDetail.setSetId(taskSetting.getId());
			taskSettingDetail.setDataClass("FbmCost");
			taskSettingDetail.setInteractionMode("Generate");
			taskSettingDetail.setTaskObject("updateFbcItemPrice");
			taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
			taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
			taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));
			taskSettingDetail.setFlag(taskSetting.isUpdateFbcItemPriceFlag());
			taskSettingDetailList.add(taskSettingDetail);
		}
		if(!existsGetFbmSalePriceFlag && StringUtils.equalsIgnoreCase("SCM_FBC", taskSetting.getTaskCode())) {
			TaskSettingDetail taskSettingDetail = new TaskSettingDetail(true);
			taskSettingDetail.setSetId(taskSetting.getId());
			taskSettingDetail.setDataClass("FbmCost");
			taskSettingDetail.setInteractionMode("Generate");
			taskSettingDetail.setTaskObject("getFbmSalePrice");
			taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
			taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
			taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));
			taskSettingDetail.setFlag(taskSetting.isGetFbmSalePriceFlag());
			taskSettingDetailList.add(taskSettingDetail);
		}
		if(!existsUpdateFbcCostPriceFlag && StringUtils.equalsIgnoreCase("SCM_FBC", taskSetting.getTaskCode())) {
			TaskSettingDetail taskSettingDetail = new TaskSettingDetail(true);
			taskSettingDetail.setSetId(taskSetting.getId());
			taskSettingDetail.setDataClass("FbmCost");
			taskSettingDetail.setInteractionMode("Generate");
			taskSettingDetail.setTaskObject("updateFbcCostPrice");
			taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
			taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
			taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));
			taskSettingDetail.setFlag(taskSetting.isUpdateFbcCostPriceFlag());
			taskSettingDetailList.add(taskSettingDetail);
		}
		if(!existsGenerateCostConsumeFlag && StringUtils.equalsIgnoreCase("SCM_FBC", taskSetting.getTaskCode())) {
			TaskSettingDetail taskSettingDetail = new TaskSettingDetail(true);
			taskSettingDetail.setSetId(taskSetting.getId());
			taskSettingDetail.setDataClass("FbmCost");
			taskSettingDetail.setInteractionMode("Generate");
			taskSettingDetail.setTaskObject("generateCostConsume");
			taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
			taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
			taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));
			taskSettingDetail.setFlag(taskSetting.isGenerateCostConsumeFlag());
			taskSettingDetailList.add(taskSettingDetail);
		}
		if(!existsCalcFbcRptDataFlag && StringUtils.equalsIgnoreCase("SCM_FBC", taskSetting.getTaskCode())) {
			TaskSettingDetail taskSettingDetail = new TaskSettingDetail(true);
			taskSettingDetail.setSetId(taskSetting.getId());
			taskSettingDetail.setDataClass("FbmCost");
			taskSettingDetail.setInteractionMode("Generate");
			taskSettingDetail.setTaskObject("calcFbcRptData");
			taskSettingDetail.setTaskCreateMode(taskSetting.getTaskCreateMode());
			taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getCycleTime()));
			taskSettingDetail.setExtendedParam2(String.valueOf(taskSetting.getLotQty()==0?10:taskSetting.getLotQty()));
			taskSettingDetail.setFlag(taskSetting.isCalcFbcRptDataFlag());
			taskSettingDetailList.add(taskSettingDetail);
		}
		if(!existsControlUnitPushFlag && StringUtils.equalsIgnoreCase("SCM_ESP", taskSetting.getTaskCode())) {
			TaskSettingDetail taskSettingDetail = new TaskSettingDetail(true);
			taskSettingDetail.setSetId(taskSetting.getId());
			taskSettingDetail.setDataClass("Info");
			taskSettingDetail.setInteractionMode("Push");
			taskSettingDetail.setTaskObject("controlUnitPush");
			taskSettingDetail.setTaskCreateMode(taskSetting.getControlUnitCreateMode());
			taskSettingDetail.setExtendedParam1(String.valueOf(taskSetting.getControlCycleTime()));
			taskSettingDetail.setFlag(taskSetting.isControlUnitPushFlag());
			taskSettingDetailList.add(taskSettingDetail);
		}
		this.update(taskSetting, taskSettingDetailList, TaskSettingDetail.FN_SETID, param);
	}

	@Override
	public List<TaskSettingDetail> selectBySetId(long setId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("setId", setId);
		return ((TaskSettingDetailDAO)dao).selectBySetId(map);
	}

	@Override
	protected void afterAdd(TaskSettingDetail entity, Param param) throws AppException {
		if(entity!=null) {
			updateSystemTask(entity,param);
		}
	}

	@Override
	protected void afterUpdate(TaskSettingDetail oldEntity, TaskSettingDetail newEntity, Param param)
			throws AppException {
		if(newEntity!=null) {
			updateSystemTask(newEntity,param);
		}
	}
	
	@Override
	protected void afterDelete(TaskSettingDetail entity, Param param) throws AppException {
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType(entity.getTaskObject());
		systemTask.setOrgUnitNo(param.getControlUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask,true, param);
		if(systemTaskList != null && !systemTaskList.isEmpty()){
			//更新定时任务
			systemTask = systemTaskList.get(0);
			systemTask.setFlag(false);
			systemTask.setUpdateTime(new Date());
			QuartzUtil quartzUtil = new QuartzUtil();
			quartzUtil.removeJobBySystemTask(systemTask);
			scmSystemTaskBiz.update(systemTask, param);
		}
	}
	
	private void updateSystemTask(TaskSettingDetail taskSettingDetail, Param param){
		int hour=0,minute=0,second=0;
		if(StringUtils.isNotBlank(taskSettingDetail.getExtendedParam1()) && StringUtils.isNumeric(taskSettingDetail.getExtendedParam1()) && Integer.valueOf(taskSettingDetail.getExtendedParam1())>0) {
			hour = (int)Integer.valueOf(taskSettingDetail.getExtendedParam1())/3600;
			minute = (int)(Integer.valueOf(taskSettingDetail.getExtendedParam1())-hour*3600)/60;
			second = Integer.valueOf(taskSettingDetail.getExtendedParam1())-hour*3600-minute*60;
		}
		String cronExpression="";
		if(second>0) {
			cronExpression = ""+second;
		}else {
			cronExpression = "0";
		}
		if(minute>0) {
			cronExpression = cronExpression+" 0/"+minute;
		}else {
			cronExpression = cronExpression+" *";
		}
		if(hour>0) {
			cronExpression = cronExpression+" 0/"+hour+" * * ?";
		}else {
			cronExpression = cronExpression+" * * * ?";
		}
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType(taskSettingDetail.getTaskObject());
		systemTask.setOrgUnitNo(param.getControlUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask,true, param);
		if(systemTaskList != null && !systemTaskList.isEmpty()){
			//更新物料价格更新定时任务
			systemTask = systemTaskList.get(0);
			systemTask.setFlag(taskSettingDetail.isFlag());
			if(StringUtils.isNotBlank(taskSettingDetail.getExtendedParam2()) && StringUtils.isNumeric(taskSettingDetail.getExtendedParam2()) && Integer.valueOf(taskSettingDetail.getExtendedParam2())>0) {
				systemTask.setSize(Integer.valueOf(taskSettingDetail.getExtendedParam2()));
			}
			if(taskSettingDetail.isFlag()) {
				systemTask.setCronExpression(cronExpression);
			}
			systemTask.setUpdateTime(new Date());
			QuartzUtil quartzUtil = new QuartzUtil();
			quartzUtil.removeJobBySystemTask(systemTask);
			scmSystemTaskBiz.update(systemTask, param);
		}else{
			//新增物料价格更新定时任务
			systemTask.setTaskGroup("iSCM");
			systemTask.setCronExpression(cronExpression);
			String taskclass="",taskname="";
			switch (StringUtils.lowerCase(taskSettingDetail.getTaskObject())) {
		        case "purorderpush":{
		        	taskname = "ScmPurOrderPushJob";
		        	taskclass = "com.armitage.server.quartz.job.ScmPurOrderPushJob";
		        	break;
		        }
		        case "invpurinwarehsbillpush":{
		        	taskname = "ScmInvPurInWarehsBillPushJob";
		        	taskclass = "com.armitage.server.quartz.job.ScmInvPurInWarehsBillPushJob";
		        	break;
		        }
		        case "supplierconfirmdataget":{
		        	taskname = "ScmSupplierConfirmDataGetJob";
		        	taskclass = "com.armitage.server.quartz.job.ScmSupplierConfirmDataGetJob";
		        	break;
		        }
		        case "supplierreginfoget":{
		        	taskname = "ScmPlatSupplierIdGetJob";
		        	taskclass = "com.armitage.server.quartz.job.ScmPlatSupplierIdGetJob";
		        	break;
		        }
		        case "supplierconfirmrulepush":{
		        	taskname = "ScmSupplierConfirmRulePushJob";
		        	taskclass = "com.armitage.server.quartz.job.ScmSupplierConfirmRulePushJob";
		        	break;
		        }
		        case "purpricepush":{
		        	taskname = "ScmPurPricePushJob";
		        	taskclass = "com.armitage.server.quartz.job.ScmPurPricePushJob";
		        	break;
		        }
		        case "businessquotationget":{
		        	taskname = "ScmBusinessQuotationGetJob";
		        	taskclass = "com.armitage.server.quartz.job.ScmBusinessQuotationGetJob";
		        	break;
		        }
		        case "supplierstatuspush":{
		        	taskname = "ScmSupplierStatusPushJob";
		        	taskclass = "com.armitage.server.quartz.job.ScmSupplierStatusPushJob";
		        	break;
		        }
		        case "qualificationinfopush":{
		        	taskname = "ScmQualificationInfoPushJob";
		        	taskclass = "com.armitage.server.quartz.job.ScmQualificationInfoPushJob";
		        	break;
		        }
		        case "supplierinfopull":{
		        	taskname = "ScmSupplierInfoPullJob";
		        	taskclass = "com.armitage.server.quartz.job.ScmSupplierInfoPullJob";
		        	break;
		        }
		        case "qualificationinfopull":{
		        	taskname = "ScmQualificationInfoPullJob";
		        	taskclass = "com.armitage.server.quartz.job.ScmQualificationInfoPullJob";
		        	break;
		        }
		        case "industryqualifypush":{
		        	taskname = "ScmIndustryQualifyPushJob";
		        	taskclass = "com.armitage.server.quartz.job.ScmIndustryQualifyPushJob";
		        	break;
		        }
		        case "updatefbcitemprice":{
		        	taskname = "ScmItemPriceUpdateJob";
		        	taskclass = "com.armitage.server.quartz.job.ScmItemPriceUpdateJob";
		        	break;
		        }
		        case "getfbmsaleprice":{
		        	taskname = "ScmSalePriceGetJob";
		        	taskclass = "com.armitage.server.quartz.job.ScmSalePriceGetJob";
		        	break;
		        }
		        case "updatefbccostprice":{
		        	taskname = "ScmBaseCostUpdateJob";
		        	taskclass = "com.armitage.server.quartz.job.ScmBaseCostUpdateJob";
		        	break;
		        }
		        case "generatecostconsume":{
		        	taskname = "ScmGenerateCostConsumeJob";
		        	taskclass = "com.armitage.server.quartz.job.ScmGenerateCostConsumeJob";
		        	break;
		        }
		        case "controlunitpush":{
		        	taskname = "ScmControlUnitPushJob";
		        	taskclass = "com.armitage.server.quartz.job.ScmControlUnitPushJob";
		        	break;
		        }
		        case "calcfbcrptdata":{
		        	taskname = "ScmCalcFbcRptDataJob";
		        	taskclass = "com.armitage.server.quartz.job.ScmCalcFbcRptDataJob";
		        	break;
		        }
			}
			systemTask.setTaskClass(taskclass);
			systemTask.setTaskName(taskname);
			if(StringUtils.isNotBlank(taskSettingDetail.getExtendedParam2()) && StringUtils.isNumeric(taskSettingDetail.getExtendedParam2()) && Integer.valueOf(taskSettingDetail.getExtendedParam2())>0) {
				systemTask.setSize(Integer.valueOf(taskSettingDetail.getExtendedParam2()));
			}
			systemTask.setFlag(taskSettingDetail.isFlag());
			systemTask.setUpdateTime(new Date());
			systemTask.setControlUnitNo(param.getControlUnitNo());
			scmSystemTaskBiz.add(systemTask, param);
		}
	}

	@Override
	public List<TaskSettingDetail2> selectByCtrl(String controlUnitNo, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("controlUnitNo", controlUnitNo);
		return ((TaskSettingDetailDAO) dao).selectByCtrl(map);
	}

	@Override
	public TaskSettingDetail2 selectByTaskObject(String taskObject, String controlUnitNo, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("taskObject", taskObject);
		map.put("controlUnitNo", controlUnitNo);
		return ((TaskSettingDetailDAO) dao).selectByTaskObject(map);
	}
}
