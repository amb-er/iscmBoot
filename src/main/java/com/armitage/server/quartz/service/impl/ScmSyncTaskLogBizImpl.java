package com.armitage.server.quartz.service.impl;


import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.quartz.model.ScmSyncTaskLog;
import com.armitage.server.quartz.model.ScmSyncTaskLogAdvQuery;
import com.armitage.server.quartz.model.ScmSystemTask;
import com.armitage.server.quartz.service.ScmSyncTaskLogBiz;
import com.armitage.server.quartz.util.ScmTaskUtil;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgResource2;
import com.armitage.server.system.service.OrgBaseUnitBiz;
import com.armitage.server.system.service.OrgResourceBiz;
import org.springframework.stereotype.Service;

@Service("scmSyncTaskLogBiz")
public class ScmSyncTaskLogBizImpl extends BaseBizImpl<ScmSyncTaskLog> implements ScmSyncTaskLogBiz {
	
	private OrgBaseUnitBiz orgBaseUnitBiz;
	private OrgResourceBiz orgResourceBiz;
	
	public void setOrgBaseUnitBiz(OrgBaseUnitBiz orgBaseUnitBiz) {
		this.orgBaseUnitBiz = orgBaseUnitBiz;
	}

	public void setOrgResourceBiz(OrgResourceBiz orgResourceBiz) {
		this.orgResourceBiz = orgResourceBiz;
	}


	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		List<OrgResource2> orgResourceList = orgResourceBiz.findChild(param.getOrgUnitNo(), param);
		if (orgResourceList != null && !orgResourceList.isEmpty()) {
			StringBuffer orgunitList = new StringBuffer("");
			for (OrgResource2 orgResource : orgResourceList) {
				if(StringUtils.isNotBlank(orgunitList.toString()))
					orgunitList.append(",");
				orgunitList.append("'").append(orgResource.getOrgUnitNo()).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmSyncTaskLog.class) + "." + ScmSyncTaskLog.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmSyncTaskLog.class) + "." + ScmSyncTaskLog.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmSyncTaskLog.class) + "." + ScmSyncTaskLog.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmSyncTaskLog.class) + "." + ScmSyncTaskLog.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}
    

	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		if(list != null && !list.isEmpty()){
			for(ScmSyncTaskLog syncTaskLog : (List<ScmSyncTaskLog>)list){
				setConverMap(syncTaskLog,param);
			}
		}
	}
	
	private void setConverMap(ScmSyncTaskLog syncTaskLog, Param param) throws AppException {
		if(StringUtils.isNotBlank(syncTaskLog.getOrgUnitNo())){
			//资源组织
			OrgBaseUnit orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(syncTaskLog.getOrgUnitNo(), param);
			if (orgBaseUnit != null) {
				syncTaskLog.setConvertMap(ScmSyncTaskLog.FN_ORGUNITNO, orgBaseUnit);
			}
		}
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmSyncTaskLogAdvQuery) {
				ScmSyncTaskLogAdvQuery scmSyncTaskLogAdvQuery = (ScmSyncTaskLogAdvQuery) page.getModel();
				if(scmSyncTaskLogAdvQuery.getLogtimeFrom()!=null){
					if(scmSyncTaskLogAdvQuery.getLogtimeTo()!=null) {
						page.getParam().put(ScmSyncTaskLog.FN_LOGTIME,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskLog.class) + "." +ScmSyncTaskLog.FN_LOGTIME, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmSyncTaskLogAdvQuery.getLogtimeFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmSyncTaskLogAdvQuery.getLogtimeTo(),1))));
					}else {
						page.getParam().put(ScmSyncTaskLog.FN_LOGTIME,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskLog.class) + "." +ScmSyncTaskLog.FN_LOGTIME, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmSyncTaskLogAdvQuery.getLogtimeFrom())));
					}
				}else if(scmSyncTaskLogAdvQuery.getLogtimeTo()!=null) {
					page.getParam().put(ScmSyncTaskLog.FN_LOGTIME,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskLog.class) + "." +ScmSyncTaskLog.FN_LOGTIME, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmSyncTaskLogAdvQuery.getLogtimeTo())));
				}
				if(StringUtils.isNotBlank(scmSyncTaskLogAdvQuery.getOrgUnitNo())){
					page.getParam().put(ScmSyncTaskLog.FN_ORGUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskLog.class) + "." +ScmSyncTaskLog.FN_ORGUNITNO, QueryParam.QUERY_EQ,scmSyncTaskLogAdvQuery.getOrgUnitNo()));
				}
				if(StringUtils.isNotBlank(scmSyncTaskLogAdvQuery.getTaskType())){
					page.getParam().put(ScmSyncTaskLog.FN_TASKTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskLog.class) + "." +ScmSyncTaskLog.FN_TASKTYPE, QueryParam.QUERY_EQ,scmSyncTaskLogAdvQuery.getTaskType()));
				}
			}
		}
	}

	@Override
	public ScmSyncTaskLog manualUpdate(ScmSyncTaskLog syncTaskLog, Param param) throws AppException {
		if(syncTaskLog != null){
			if(StringUtils.isNotBlank(syncTaskLog.getTaskType()) && StringUtils.isNotBlank(syncTaskLog.getOrgUnitNo())){
				ScmSystemTask systemTask = new ScmSystemTask(true);
				systemTask.setTaskType(syncTaskLog.getTaskType());
    			systemTask.setOrgUnitNo(syncTaskLog.getOrgUnitNo());
    			switch (syncTaskLog.getTaskType()) {
				case "iSCMItemPriceUpdate":
					//物料价格更新
					ScmTaskUtil.updateFbcItemPrice(systemTask);
					break;
				case "iSCMBaseCostUpdate":
					ScmTaskUtil.updateFbcCostPrice(systemTask);
					break;
				case "iSCMSalePriceGet":
					ScmTaskUtil.getFbmSalePrice(systemTask);
					break;
				default:
					break;
				}
    			return syncTaskLog;
			}
		}
		return null;
	}
}

