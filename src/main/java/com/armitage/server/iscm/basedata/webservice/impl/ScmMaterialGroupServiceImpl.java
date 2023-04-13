package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupAdvQuery;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupWSBean;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.webservice.ScmMaterialGroupService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmMaterialGroupService")
public class ScmMaterialGroupServiceImpl extends BaseServiceImpl<ScmMaterialGroupBiz, ScmMaterialGroupWSBean> implements ScmMaterialGroupService {

	@Override
	public ScmMaterialGroupWSBean queryDetailClassList(ScmMaterialGroupWSBean bean) {
		try {
			bean.setList(biz.queryDetailClassList((String)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialGroupWSBean queryMaterialClassList(ScmMaterialGroupWSBean bean) {
		try {
			bean.setList(biz.queryMaterialClassList((ScmMaterialGroupAdvQuery)bean.getObject(),-1,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialGroupWSBean updateCostType(ScmMaterialGroupWSBean bean) {
		try {
			biz.updateCostType(bean.getList(),ParamHelper.createParam(bean));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}
