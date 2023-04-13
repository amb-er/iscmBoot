package com.armitage.server.iscm.basedata.webservice.impl;

import java.util.List;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelation2;
import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelationWSBean;
import com.armitage.server.iscm.basedata.service.ScmMaterialUnitRelationBiz;
import com.armitage.server.iscm.basedata.webservice.ScmMaterialUnitRelationService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmMaterialUnitRelationService")
public class ScmMaterialUnitRelationServiceImpl extends BaseServiceImpl<ScmMaterialUnitRelationBiz, ScmMaterialUnitRelationWSBean> implements ScmMaterialUnitRelationService {

	@Override
	public ScmMaterialUnitRelationWSBean selectUnitRelation(ScmMaterialUnitRelationWSBean bean) {
		try {
			bean.setList(biz.selectUnitRelation((ScmMaterial2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialUnitRelationWSBean saveUnitRelation(ScmMaterialUnitRelationWSBean bean) {
		try {
			bean.setList(biz.saveUnitRelation((List<ScmMaterialUnitRelation2>)bean.getList(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

}

