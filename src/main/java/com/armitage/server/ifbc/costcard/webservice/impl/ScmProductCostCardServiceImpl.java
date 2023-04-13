
package com.armitage.server.ifbc.costcard.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardWSBean;
import com.armitage.server.ifbc.costcard.service.ScmProductCostCardBiz;
import com.armitage.server.ifbc.costcard.webservice.ScmProductCostCardService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmProductCostCardService")
public class ScmProductCostCardServiceImpl extends BaseServiceImpl<ScmProductCostCardBiz, ScmProductCostCardWSBean> implements ScmProductCostCardService {

	@Override
	public ScmProductCostCardWSBean auditCostCard(ScmProductCostCardWSBean bean) {
		try {
			ScmProductCostCard2 scmCostCard = (ScmProductCostCard2)bean.getObject();
			bean.setObject(biz.auditCostCard(scmCostCard,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
