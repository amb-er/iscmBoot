package com.armitage.server.quartz.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.quartz.model.ScmSystemTask;
import com.armitage.server.quartz.service.ScmSystemTaskBiz;
import com.armitage.server.quartz.util.QuartzUtil;
import org.springframework.stereotype.Service;

@Service("scmSystemTaskBiz")
public class ScmSystemTaskBizImpl extends BaseBizImpl<ScmSystemTask> implements ScmSystemTaskBiz {

	/**
	 * 无Flag查询条件
	 */
	@Override
	public List<ScmSystemTask> selectByTask(ScmSystemTask systemTask,Boolean flag, Param param)
			throws AppException {
		if(systemTask != null){
			Page page=new Page();
			page.setModelClass(ScmSystemTask.class);
			page.setShowCount(Integer.MAX_VALUE);
			if(StringUtils.isNotBlank(systemTask.getTaskType())){
				page.getParam().put(
						ScmSystemTask.FN_TASKTYPE,
						new QueryParam(ScmSystemTask.FN_TASKTYPE, QueryParam.QUERY_EQ,
								systemTask.getTaskType()));
			}
			if(StringUtils.isNotBlank(systemTask.getOrgUnitNo())){
				page.getParam().put(
						ScmSystemTask.FN_ORGUNITNO,
						new QueryParam(ScmSystemTask.FN_ORGUNITNO, QueryParam.QUERY_EQ,
								systemTask.getOrgUnitNo()));
			}
			return this.findPage(page, param);
		}
		return null;
	}
	/**
	 * 有Flag查询条件
	 */
	@Override
	public List<ScmSystemTask> selectByTask(ScmSystemTask systemTask, Param param)
			throws AppException {
		if(systemTask != null){
			Page page=new Page();
			page.setModelClass(ScmSystemTask.class);
			page.setShowCount(Integer.MAX_VALUE);
			if(StringUtils.isNotBlank(systemTask.getTaskType())){
				page.getParam().put(
						ScmSystemTask.FN_TASKTYPE,
						new QueryParam(ScmSystemTask.FN_TASKTYPE, QueryParam.QUERY_EQ,
								systemTask.getTaskType()));
			}
			if(StringUtils.isNotBlank(systemTask.getOrgUnitNo())){
				page.getParam().put(
						ScmSystemTask.FN_ORGUNITNO,
						new QueryParam(ScmSystemTask.FN_ORGUNITNO, QueryParam.QUERY_EQ,
								systemTask.getOrgUnitNo()));
			}
			page.getParam().put(ScmSystemTask.FN_FLAG,
					new QueryParam(ScmSystemTask.FN_FLAG, QueryParam.QUERY_EQ,"1"));
			return this.findPage(page, param);
		}
		return null;
	}

	@Override
	protected void afterAdd(ScmSystemTask entity, Param param) throws AppException {
		if(entity!=null) {
			QuartzUtil quartzUtil = new QuartzUtil();
			quartzUtil.addJobBySystemTask(entity);
		}
	}

	@Override
	protected void afterUpdate(ScmSystemTask oldEntity, ScmSystemTask newEntity, Param param) throws AppException {
		if(newEntity!=null) {
			QuartzUtil quartzUtil = new QuartzUtil();
			quartzUtil.updateJobBySystemTask(newEntity);
		}
	}

}
