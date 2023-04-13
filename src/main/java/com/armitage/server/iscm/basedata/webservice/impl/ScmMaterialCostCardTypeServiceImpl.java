package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.ifbc.costcard.model.ScmMaterialOrGroupAdvQuery;
import com.armitage.server.iscm.basedata.model.ScmMaterialCostCardTypeWSBean;
import com.armitage.server.iscm.basedata.service.ScmMaterialCostCardTypeBiz;
import com.armitage.server.iscm.basedata.webservice.ScmMaterialCostCardTypeService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmMaterialCostCardTypeService")
public class ScmMaterialCostCardTypeServiceImpl extends BaseServiceImpl<ScmMaterialCostCardTypeBiz, ScmMaterialCostCardTypeWSBean> implements ScmMaterialCostCardTypeService {

	@Override
	public ScmMaterialCostCardTypeWSBean selectForCostCard(ScmMaterialCostCardTypeWSBean bean) {
		try {
			bean.setList(biz.selectForCostCard((ScmMaterialOrGroupAdvQuery)bean.getObject(), ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

}
