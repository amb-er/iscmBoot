package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupStandardWSBean;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupStandardBiz;
import com.armitage.server.iscm.basedata.webservice.ScmMaterialGroupStandardService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmMaterialGroupStandardService")
public class ScmMaterialGroupStandardServiceImpl extends BaseServiceImpl<ScmMaterialGroupStandardBiz, ScmMaterialGroupStandardWSBean> implements ScmMaterialGroupStandardService {

	@Override
	public ScmMaterialGroupStandardWSBean selectSubsidiaryTypeByItem(ScmMaterialGroupStandardWSBean bean) {
		try {
			bean.setList(biz.selectSubsidiaryTypeByItem((ScmMaterial2) bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
