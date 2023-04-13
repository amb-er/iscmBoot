package com.armitage.server.ifbc.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.ifbc.basedata.dao.ScmPriceUpdSetDAO;
import com.armitage.server.ifbc.basedata.model.ScmPriceUpdSet;
import com.armitage.server.ifbc.basedata.model.ScmPriceUpdSet2;
import com.armitage.server.ifbc.basedata.service.ScmPriceUpdSetBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.quartz.job.ScmBaseCostUpdateJob;
import com.armitage.server.quartz.job.ScmItemPriceUpdateJob;
import com.armitage.server.quartz.job.ScmSalePriceGetJob;
import com.armitage.server.quartz.model.ScmSystemTask;
import com.armitage.server.quartz.service.ScmSystemTaskBiz;
import com.armitage.server.quartz.util.QuartzUtil;
import com.armitage.server.system.model.OrgResource2;
import com.armitage.server.system.service.OrgResourceBiz;
import org.springframework.stereotype.Service;

@Service("scmPriceUpdSetBiz")
public class ScmPriceUpdSetBizImpl extends BaseBizImpl<ScmPriceUpdSet> implements ScmPriceUpdSetBiz {
	private ScmSystemTaskBiz scmSystemTaskBiz;
	private OrgResourceBiz orgResourceBiz;
	
	public void setScmSystemTaskBiz(ScmSystemTaskBiz scmSystemTaskBiz) {
		this.scmSystemTaskBiz = scmSystemTaskBiz;
	}

	public void setOrgResourceBiz(OrgResourceBiz orgResourceBiz) {
		this.orgResourceBiz = orgResourceBiz;
	}

	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page, HashMap<String, Object> map, Param param) {
		List<OrgResource2> orgResource2s = orgResourceBiz.findChild(param.getOrgUnitNo(), param);
		if (orgResource2s != null && !orgResource2s.isEmpty()) {
			StringBuffer orgunitList = new StringBuffer("");
			for (OrgResource2 orgResource2 : orgResource2s) {
				if (StringUtils.isNotBlank(orgunitList)) {
					orgunitList.append(",");
				}
				orgunitList.append("'").append(orgResource2.getOrgUnitNo()).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPriceUpdSet.class) + "." +ScmPriceUpdSet.FN_ORGUNITNO),
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPriceUpdSet.class) + "." +ScmPriceUpdSet.FN_ORGUNITNO),QueryParam.QUERY_IN,orgunitList.toString()));
		}else {
			map.put(ScmPriceUpdSet.FN_ORGUNITNO, param.getControlUnitNo());
		}
		map.put(ScmPriceUpdSet.FN_CONTROLUNITNO, param.getControlUnitNo());
		return map;
	}

	@Override
	protected void afterAdd(ScmPriceUpdSet entity, Param param) throws AppException {
		//updateSystemTask(entity,param);
	}
	
	@Override
	protected void afterUpdate(ScmPriceUpdSet oldEntity, ScmPriceUpdSet newEntity, Param param) throws AppException {
		//updateSystemTask(newEntity,param);
		//if(!newEntity.isFlag()){
			//deleteSystemTask(newEntity,param);
		//}
	}
	
	@Override
	protected void afterDelete(ScmPriceUpdSet entity, Param param) throws AppException {
		//deleteSystemTask(entity,param);
	}
	
	@Override
	protected void beforeAdd(ScmPriceUpdSet entity, Param param) throws AppException {
		ScmPriceUpdSet scmPriceUpdSet = this.selectByOrgUnit(param.getOrgUnitNo());
		if(scmPriceUpdSet!=null) {
			throw new AppException(Message.getMessage(param.getLang(),"iscm.ScmPriceUpdSet.error.scheduledrepet"));
		}
		checkTime(scmPriceUpdSet, param);
	}

	@Override
	protected void beforeUpdate(ScmPriceUpdSet oldEntity, ScmPriceUpdSet newEntity, Param param) throws AppException {
		ScmPriceUpdSet scmPriceUpdSet = this.selectByOrgUnit(param.getOrgUnitNo());
		if(scmPriceUpdSet!=null && scmPriceUpdSet.getId()!=newEntity.getId()) {
			throw new AppException(Message.getMessage(param.getLang(),"iscm.ScmPriceUpdSet.error.scheduledrepet"));
		}
		checkTime(newEntity, param);
	}
	
	private void checkTime(ScmPriceUpdSet scmPriceUpdSet, Param param){
		if(scmPriceUpdSet != null && scmPriceUpdSet.isFlag()){
			if(StringUtils.isNotBlank(scmPriceUpdSet.getItemPriceUpd())){
				try {
					String itemPriceUpdateTime = (scmPriceUpdSet.getItemPriceUpd()).replace("：", ":");
					if((itemPriceUpdateTime.split(":")[0]).length() != 2 || (itemPriceUpdateTime.split(":")[1]).length() != 2){
						throw new AppException("com.armitage.server.ifbc.basedata.service.impl.ScmPriceUpdSetBizImpl.itemPriceUpdError");
					}
					String hour = String.valueOf((Integer.parseInt(itemPriceUpdateTime.split(":")[0])));
					String minute = String.valueOf((Integer.parseInt(itemPriceUpdateTime.split(":")[1])));
				} catch (Exception e) {
					throw new AppException("com.armitage.server.ifbc.basedata.service.impl.ScmPriceUpdSetBizImpl.itemPriceUpdError");
				}
			}
			if(StringUtils.isNotBlank(scmPriceUpdSet.getBaseCostUpd())){
				try {
					String baseCostUpdateTime = (scmPriceUpdSet.getBaseCostUpd()).replace("：", ":");
					if((baseCostUpdateTime.split(":")[0]).length() != 2 || (baseCostUpdateTime.split(":")[1]).length() != 2){
						throw new AppException("com.armitage.server.ifbc.basedata.service.impl.ScmPriceUpdSetBizImpl.baseCostUpdError");
					}
					String hour = String.valueOf((Integer.parseInt(baseCostUpdateTime.split(":")[0])));
					String minute = String.valueOf((Integer.parseInt(baseCostUpdateTime.split(":")[1])));
				} catch (Exception e) {
					throw new AppException("com.armitage.server.ifbc.basedata.service.impl.ScmPriceUpdSetBizImpl.baseCostUpdError");
				}
			}
			if(StringUtils.isNotBlank(scmPriceUpdSet.getPriceGetTime())){
				try {
					String priceGetTime = (scmPriceUpdSet.getPriceGetTime()).replace("：", ":");
					if((priceGetTime.split(":")[0]).length() != 2 || (priceGetTime.split(":")[1]).length() != 2){
						throw new AppException("com.armitage.server.ifbc.basedata.service.impl.ScmSalePriceGetSetBizImpl.salePriceGetError");
					}
					String hour = String.valueOf((Integer.parseInt(priceGetTime.split(":")[0])));
					String minute = String.valueOf((Integer.parseInt(priceGetTime.split(":")[1])));
				} catch (Exception e) {
					throw new AppException("com.armitage.server.ifbc.basedata.service.impl.ScmSalePriceGetSetBizImpl.salePriceGetError");
				}
			}
			if(StringUtils.isNotBlank(scmPriceUpdSet.getGenerateCostConsumeTime())){
				try {
					String generateCostConsumeTime = (scmPriceUpdSet.getGenerateCostConsumeTime()).replace("：", ":");
					if((generateCostConsumeTime.split(":")[0]).length() != 2 || (generateCostConsumeTime.split(":")[1]).length() != 2){
						throw new AppException("com.armitage.server.ifbc.basedata.service.impl.ScmPriceUpdSetBizImpl.generateCostConsumeTimeError");
					}
					String hour = String.valueOf((Integer.parseInt(generateCostConsumeTime.split(":")[0])));
					String minute = String.valueOf((Integer.parseInt(generateCostConsumeTime.split(":")[1])));
				} catch (Exception e) {
					throw new AppException("com.armitage.server.ifbc.basedata.service.impl.ScmPriceUpdSetBizImpl.generateCostConsumeTimeError");
				}
			}
			if(StringUtils.isNotBlank(scmPriceUpdSet.getCalcFbcRptDataTime())){
				try {
					String calcFbcRptDataTime = (scmPriceUpdSet.getCalcFbcRptDataTime()).replace("：", ":");
					if((calcFbcRptDataTime.split(":")[0]).length() != 2 || (calcFbcRptDataTime.split(":")[1]).length() != 2){
						throw new AppException("com.armitage.server.ifbc.basedata.service.impl.ScmPriceUpdSetBizImpl.calcFbcRptDataTimeError");
					}
					String hour = String.valueOf((Integer.parseInt(calcFbcRptDataTime.split(":")[0])));
					String minute = String.valueOf((Integer.parseInt(calcFbcRptDataTime.split(":")[1])));
				} catch (Exception e) {
					throw new AppException("com.armitage.server.ifbc.basedata.service.impl.ScmPriceUpdSetBizImpl.calcFbcRptDataTimeError");
				}
			}
		}
	}

	private void updateSystemTask(ScmPriceUpdSet scmPriceUpdSet, Param param){
		if(scmPriceUpdSet != null && scmPriceUpdSet.isFlag()){
			QuartzUtil quartzUtil = new QuartzUtil();
			if(StringUtils.isNotBlank(scmPriceUpdSet.getItemPriceUpd())){
				String hour="";
				String minute="";
				try {
					String itemPriceUpdateTime = (scmPriceUpdSet.getItemPriceUpd()).replace("：", ":");
					hour = String.valueOf((Integer.parseInt(itemPriceUpdateTime.split(":")[0])));
					minute = String.valueOf((Integer.parseInt(itemPriceUpdateTime.split(":")[1])));
				} catch (Exception e) {
					throw new AppException("com.armitage.server.ifbc.basedata.service.impl.ScmPriceUpdSetBizImpl.itemPriceUpdError");
				}
				ScmSystemTask systemTask = new ScmSystemTask(true);
				systemTask.setTaskType("iSCMItemPriceUpdate");
				systemTask.setOrgUnitNo(scmPriceUpdSet.getOrgUnitNo());
				List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
				if(systemTaskList != null && !systemTaskList.isEmpty()){
					//更新物料价格更新定时任务
					systemTask = systemTaskList.get(0);
					systemTask.setCronExpression("0 "+minute+" "+hour+" * * ?");
					scmSystemTaskBiz.update(systemTask, param);
					quartzUtil.updateJobBySystemTask(systemTask);
				}else{
					//新增物料价格更新定时任务
					systemTask.setTaskName(ScmItemPriceUpdateJob.class.getSimpleName());
					systemTask.setTaskGroup("iSCM");
					systemTask.setCronExpression("0 "+minute+" "+hour+" * * ?");
					systemTask.setTaskClass(ScmItemPriceUpdateJob.class.getName());
					systemTask.setControlUnitNo(param.getControlUnitNo());
					scmSystemTaskBiz.add(systemTask, param);
					quartzUtil.addJobBySystemTask(systemTask);
				}
			}
			if(StringUtils.isNotBlank(scmPriceUpdSet.getBaseCostUpd())){
				String hour="";
				String minute="";
				try {
					String baseCostUpdateTime = (scmPriceUpdSet.getBaseCostUpd()).replace("：", ":");
					hour = String.valueOf((Integer.parseInt(baseCostUpdateTime.split(":")[0])));
					minute = String.valueOf((Integer.parseInt(baseCostUpdateTime.split(":")[1])));
				} catch (Exception e) {
					throw new AppException("com.armitage.server.ifbc.basedata.service.impl.ScmPriceUpdSetBizImpl.baseCostUpdError");
				}
				ScmSystemTask systemTask = new ScmSystemTask(true);
				systemTask.setTaskType("iSCMBaseCostUpdate");
				systemTask.setOrgUnitNo(scmPriceUpdSet.getOrgUnitNo());
				List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
				if(systemTaskList != null && !systemTaskList.isEmpty()){
					//更新标准成本更新定时任务
					systemTask = systemTaskList.get(0);
					systemTask.setCronExpression("0 "+minute+" "+hour+" * * ?");
					scmSystemTaskBiz.update(systemTask, param);
					quartzUtil.updateJobBySystemTask(systemTask);
				}else{
					//新增标准成本更新定时任务
					systemTask.setTaskName(ScmBaseCostUpdateJob.class.getSimpleName());
					systemTask.setTaskGroup("iSCM");
					systemTask.setCronExpression("0 "+minute+" "+hour+" * * ?");
					systemTask.setTaskClass(ScmBaseCostUpdateJob.class.getName());
					systemTask.setControlUnitNo(param.getControlUnitNo());
					scmSystemTaskBiz.add(systemTask, param);
					quartzUtil.addJobBySystemTask(systemTask);
				}
			}
			if(StringUtils.isNotBlank(scmPriceUpdSet.getPriceGetTime())){
				String hour="";
				String minute="";
				try {
					String priceGetTime = (scmPriceUpdSet.getPriceGetTime()).replace("：", ":");
					hour = String.valueOf((Integer.parseInt(priceGetTime.split(":")[0])));
					minute = String.valueOf((Integer.parseInt(priceGetTime.split(":")[1])));
				} catch (Exception e) {
					throw new AppException("com.armitage.server.ifbc.basedata.service.impl.ScmSalePriceGetSetBizImpl.salePriceGetError");
				}
				ScmSystemTask systemTask = new ScmSystemTask(true);
				systemTask.setTaskType("iSCMSalePriceGet");
				systemTask.setOrgUnitNo(scmPriceUpdSet.getOrgUnitNo());
				List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
				if(systemTaskList != null && !systemTaskList.isEmpty()){
					//更新售价获取定时任务
					systemTask = systemTaskList.get(0);
					systemTask.setCronExpression("0 "+minute+" "+hour+" * * ?");
					scmSystemTaskBiz.update(systemTask, param);
					quartzUtil.updateJobBySystemTask(systemTask);
				}else{
					//新增售价获取定时任务
					systemTask.setTaskName(ScmSalePriceGetJob.class.getSimpleName());
					systemTask.setTaskGroup("iSCM");
					systemTask.setCronExpression("0 "+minute+" "+hour+" * * ?");
					systemTask.setTaskClass(ScmSalePriceGetJob.class.getName());
					systemTask.setControlUnitNo(param.getControlUnitNo());
					scmSystemTaskBiz.add(systemTask, param);
					quartzUtil.addJobBySystemTask(systemTask);
				}
			}
		}
	}
	
	private void deleteSystemTask(ScmPriceUpdSet scmPriceUpdSet, Param param){
		if(scmPriceUpdSet != null){
			QuartzUtil quartzUtil = new QuartzUtil();
			if(StringUtils.isNotBlank(scmPriceUpdSet.getItemPriceUpd())){
				//删除物料价格更新定时任务
				ScmSystemTask systemTask = new ScmSystemTask(true);
				systemTask.setTaskType("iSCMItemPriceUpdate");
				systemTask.setOrgUnitNo(scmPriceUpdSet.getOrgUnitNo());
				List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
				if(systemTaskList != null && !systemTaskList.isEmpty()){
					scmSystemTaskBiz.delete(systemTaskList.get(0), param);
					quartzUtil.removeJobBySystemTask(systemTask);
				}
			}
			if(StringUtils.isNotBlank(scmPriceUpdSet.getBaseCostUpd())){
				//删除标准成本更新定时任务
				ScmSystemTask systemTask = new ScmSystemTask(true);
				systemTask.setTaskType("iSCMBaseCostUpdate");
				systemTask.setOrgUnitNo(scmPriceUpdSet.getOrgUnitNo());
				List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
				if(systemTaskList != null && !systemTaskList.isEmpty()){
					scmSystemTaskBiz.delete(systemTaskList.get(0), param);
					quartzUtil.removeJobBySystemTask(systemTask);
				}
			}
		}
	}
	
	@Override
	public ScmPriceUpdSet selectByOrgUnit(String orgUnitNo) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNo", orgUnitNo);
		List<ScmPriceUpdSet> scmPriceUpdSetList = ((ScmPriceUpdSetDAO) dao).findRepeat(map);
		return (scmPriceUpdSetList == null || scmPriceUpdSetList.isEmpty()) ? null
				: scmPriceUpdSetList.get(0);
	}

	@Override
	public List<ScmPriceUpdSet2> selectByCtrl(String controlUnitNo, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("controlUnitNo", controlUnitNo);
		return ((ScmPriceUpdSetDAO) dao).selectByCtrl(map);
	}
	
	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		List<ScmPriceUpdSet> scmPriceUpdSets = list;
		if (list != null && ! list.isEmpty()) {
			for (ScmPriceUpdSet entity : scmPriceUpdSets) {
				setConvertMap(entity,param);
			}
		}
	}

	private void setConvertMap(ScmPriceUpdSet entity, Param param) {
		if (StringUtils.isNotBlank(entity.getOrgUnitNo())) {
			OrgResource2 orgResource2 = orgResourceBiz.selectByOrgUnitNo(entity.getOrgUnitNo(), param);
			if (orgResource2 != null) {
				entity.setConvertMap(ScmPriceUpdSet.FN_ORGUNITNO, orgResource2);
			}
		}
	}
}
