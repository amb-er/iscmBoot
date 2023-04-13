package com.armitage.server.ifbc.rptdata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CommonBeanHelper;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.ifbc.rptdata.model.ScmFbcRptData;
import com.armitage.server.ifbc.rptdata.model.ScmFbcRptDataWSBean;
import com.armitage.server.ifbc.rptdata.service.ScmFbcRptDataBiz;
import com.armitage.server.ifbc.rptdata.webservice.ScmFbcRptDataService;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmFbcRptDataService")
public class ScmFbcRptDataServiceImpl extends BaseServiceImpl<ScmFbcRptDataBiz, ScmFbcRptDataWSBean> implements ScmFbcRptDataService {

	@Override
	public ScmFbcRptDataWSBean calcRptData(ScmFbcRptDataWSBean bean) {
		try {
			CommonBeanHelper.toWSBean(biz.calcRptData((ScmFbcRptData)bean.getObject(),(ScmDataCollectionStepState2)bean.getObject2(), ParamHelper.createParam(bean)), bean);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}