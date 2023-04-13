package com.armitage.server.ifbc.costcard.webservice.impl;

import java.util.List;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CommonBeanHelper;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.ifbc.costcard.model.ScmDishReplaceCostCard;
import com.armitage.server.ifbc.costcard.model.ScmDishReplaceCostCardDetail;
import com.armitage.server.ifbc.costcard.model.ScmDishReplaceCostCardWSBean;
import com.armitage.server.ifbc.costcard.service.ScmDishReplaceCostCardBiz;
import com.armitage.server.ifbc.costcard.webservice.ScmDishReplaceCostCardService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmDishReplaceCostCardService")
public class ScmDishReplaceCostCardServiceImpl extends BaseServiceImpl<ScmDishReplaceCostCardBiz, ScmDishReplaceCostCardWSBean> implements ScmDishReplaceCostCardService {

	@Override
	public ScmDishReplaceCostCardWSBean getDataForReplace(ScmDishReplaceCostCardWSBean bean) {
		try {
			CommonBeanHelper.toWSBean(biz.getDataForReplace((ScmDishReplaceCostCard)bean.getObject(),ParamHelper.createParam(bean)),bean);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmDishReplaceCostCardWSBean replaceItem(ScmDishReplaceCostCardWSBean bean) {
		try {
			biz.replaceItem((ScmDishReplaceCostCard)bean.getObject(),(List<ScmDishReplaceCostCardDetail>)bean.getList(),ParamHelper.createParam(bean));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}
