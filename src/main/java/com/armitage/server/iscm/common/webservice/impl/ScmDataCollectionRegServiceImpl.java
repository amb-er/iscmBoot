package com.armitage.server.iscm.common.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CommonBeanHelper;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.common.model.ScmDataCollectionReg2;
import com.armitage.server.iscm.common.model.ScmDataCollectionRegWSBean;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import com.armitage.server.iscm.common.service.ScmDataCollectionRegBiz;
import com.armitage.server.iscm.common.webservice.ScmDataCollectionRegService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmDataCollectionRegService")
public class ScmDataCollectionRegServiceImpl extends BaseServiceImpl<ScmDataCollectionRegBiz, ScmDataCollectionRegWSBean> implements ScmDataCollectionRegService{

	@Override
	public ScmDataCollectionRegWSBean findAsynStep(ScmDataCollectionRegWSBean bean) {
		try {
			CommonBeanHelper.toWSBean(biz.findAsynStep((ScmDataCollectionReg2)bean.getObject(), ParamHelper.createParam(bean)), bean);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmDataCollectionRegWSBean selectByStepIdAndOrgUnitNo(ScmDataCollectionRegWSBean bean) {
		try {
			bean.setObject(biz.selectByStepIdAndOrgUnitNo((ScmDataCollectionStepState2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

}
