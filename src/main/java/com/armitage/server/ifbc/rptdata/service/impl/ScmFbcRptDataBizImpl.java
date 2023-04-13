package com.armitage.server.ifbc.rptdata.service.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.ifbc.costcard.service.ScmCookCostCardPriceBiz;
import com.armitage.server.ifbc.costcard.service.ScmCostCardPriceBiz;
import com.armitage.server.ifbc.rptdata.model.ScmFbcRptData;
import com.armitage.server.ifbc.rptdata.service.ScmCookStdCostInfoBiz;
import com.armitage.server.ifbc.rptdata.service.ScmDiskStdCostInfoBiz;
import com.armitage.server.ifbc.rptdata.service.ScmFbcRptDataBiz;
import com.armitage.server.ifbc.rptdata.service.ScmFbmSellCookDetailBiz;
import com.armitage.server.ifbc.rptdata.service.ScmFbmSellDetailBiz;
import com.armitage.server.ifbc.rptdata.service.ScmUseOrgUnitItemSumBiz;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import com.armitage.server.iscm.common.service.ScmDataCollectionStepStateBiz;
import org.springframework.stereotype.Service;

@Service("scmFbcRptDataBiz")
public class ScmFbcRptDataBizImpl extends BaseBizImpl<ScmFbcRptData> implements ScmFbcRptDataBiz {
	private static final int NIGHTAUDIT_STEP_EXPIRE_TIME = 10;
	private ScmFbmSellDetailBiz scmFbmSellDetailBiz;
	private ScmFbmSellCookDetailBiz scmFbmSellCookDetailBiz;
	private ScmUseOrgUnitItemSumBiz scmUseOrgUnitItemSumBiz;
	private ScmDataCollectionStepStateBiz scmDataCollectionStepStateBiz;
	private ScmDiskStdCostInfoBiz scmDiskStdCostInfoBiz;
	private ScmCookStdCostInfoBiz scmCookStdCostInfoBiz;
	private ScmCostCardPriceBiz scmCostCardPriceBiz;
	private ScmCookCostCardPriceBiz scmCookCostCardPriceBiz;
	private ExecutorService executors;
	
	public void setScmFbmSellDetailBiz(ScmFbmSellDetailBiz scmFbmSellDetailBiz) {
		this.scmFbmSellDetailBiz = scmFbmSellDetailBiz;
	}

	public void setScmFbmSellCookDetailBiz(ScmFbmSellCookDetailBiz scmFbmSellCookDetailBiz) {
		this.scmFbmSellCookDetailBiz = scmFbmSellCookDetailBiz;
	}

	public void setScmUseOrgUnitItemSumBiz(ScmUseOrgUnitItemSumBiz scmUseOrgUnitItemSumBiz) {
		this.scmUseOrgUnitItemSumBiz = scmUseOrgUnitItemSumBiz;
	}

	public void setScmDataCollectionStepStateBiz(ScmDataCollectionStepStateBiz scmDataCollectionStepStateBiz) {
		this.scmDataCollectionStepStateBiz = scmDataCollectionStepStateBiz;
	}

	public void setScmDiskStdCostInfoBiz(ScmDiskStdCostInfoBiz scmDiskStdCostInfoBiz) {
		this.scmDiskStdCostInfoBiz = scmDiskStdCostInfoBiz;
	}

	public void setScmCookStdCostInfoBiz(ScmCookStdCostInfoBiz scmCookStdCostInfoBiz) {
		this.scmCookStdCostInfoBiz = scmCookStdCostInfoBiz;
	}

	public void setScmCostCardPriceBiz(ScmCostCardPriceBiz scmCostCardPriceBiz) {
		this.scmCostCardPriceBiz = scmCostCardPriceBiz;
	}

	public void setScmCookCostCardPriceBiz(ScmCookCostCardPriceBiz scmCookCostCardPriceBiz) {
		this.scmCookCostCardPriceBiz = scmCookCostCardPriceBiz;
	}

	@Override
	public CommonBean calcRptData(ScmFbcRptData scmFbcRptData,ScmDataCollectionStepState2 stepState, Param param) throws AppException {
		CommonBean commonBean = new CommonBean(); 
		long begPeriodId = scmFbcRptData.getBegPeriodId();
		long endPeriodId = scmFbcRptData.getEndPeriodId();
		if(begPeriodId==0 && endPeriodId==0) {
			throw new AppException(Message.getMessage(param.getLang(),"iscm.ScmFbcRptData.calcRptData.period.isnull"));
		}
		if(begPeriodId==0)
			begPeriodId = endPeriodId;
		if(endPeriodId==0)
			endPeriodId = begPeriodId;
		this.asyncCalcRptData(commonBean, begPeriodId, endPeriodId, stepState, param);
		return commonBean;
	}

	private void asyncCalcRptData(CommonBean commonBean,final long begPeriodId,final long endPeriodId,ScmDataCollectionStepState2 stepState,final Param param)throws AppException {
		ScmDataCollectionStepState scmDataCollectionStepState = scmDataCollectionStepStateBiz.updateByAsynProcessed(stepState,ScmDataCollectionStepState.SATATE_RUN, null, param);
		final ScmDataCollectionStepState2 tempScmStepData = new ScmDataCollectionStepState2(true);
		BeanUtil.copyProperties(tempScmStepData, scmDataCollectionStepState);
		//进行夜核步骤操作
		final String invoke = stepState.getInvoke();
		if (executors == null) {
			executors = Executors.newCachedThreadPool();
		}
		executors.execute(new Runnable() {
			@Override
			public void run() {
				try {
					if (StringUtils.equalsIgnoreCase(invoke, "calcSellData")){
						//统计销售数据
						scmFbmSellDetailBiz.calcRptData(param.getOrgUnitNo(),begPeriodId,endPeriodId,param);
						scmFbmSellCookDetailBiz.calcRptData(param.getOrgUnitNo(),begPeriodId,endPeriodId,param);
						//获取标准售价
						scmCostCardPriceBiz.getSalePrice(param.getOrgUnitNo(),begPeriodId,endPeriodId,param);
						scmCookCostCardPriceBiz.getSalePrice(param.getOrgUnitNo(),begPeriodId,endPeriodId,param);
					}else if (StringUtils.equalsIgnoreCase(invoke, "calcStdCost")){
						scmDiskStdCostInfoBiz.calcRptData(param.getOrgUnitNo(),begPeriodId,endPeriodId,param);
						scmCookStdCostInfoBiz.calcRptData(param.getOrgUnitNo(),begPeriodId,endPeriodId,param);
						scmCostCardPriceBiz.calcCostPrice(param.getOrgUnitNo(),begPeriodId,endPeriodId,param);
						scmCookCostCardPriceBiz.calcCostPrice(param.getOrgUnitNo(),begPeriodId,endPeriodId,param);
					}else if (StringUtils.equalsIgnoreCase(invoke, "calcInvData")){
						//统计耗用数据
						scmUseOrgUnitItemSumBiz.calcRptData(param.getOrgUnitNo(),begPeriodId,endPeriodId,param);
					}
					//更新状态
					scmDataCollectionStepStateBiz.updateByAsynProcessed(tempScmStepData, ScmDataCollectionStepState.SATATE_SUCCESS, null, param);
				} catch (Exception e) {
					//保存错误信息
					scmDataCollectionStepStateBiz.updateByAsynProcessed(tempScmStepData, ScmDataCollectionStepState.SATATE_FAIL, e.getMessage(), param);
				}
			}
		});
		commonBean.setObject(tempScmStepData);
	}
	
}
