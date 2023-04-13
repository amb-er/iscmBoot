
package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnitWSBean;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.basedata.webservice.ScmMeasureUnitService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmMeasureUnitService")
public class ScmMeasureUnitServiceImpl extends BaseServiceImpl<ScmMeasureUnitBiz, ScmMeasureUnitWSBean> implements ScmMeasureUnitService {
	
	@Override
	public ScmMeasureUnitWSBean updateStatus(ScmMeasureUnitWSBean bean) {
		try {
			ScmMeasureUnit scmMeasureUnit = (ScmMeasureUnit)bean.getObject();
			bean.setObject(biz.updateDirect(scmMeasureUnit,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmMeasureUnitWSBean checkUnitUse(ScmMeasureUnitWSBean bean) {
		try {
			ScmMeasureUnit scmMeasureUnit = (ScmMeasureUnit)bean.getObject();
			bean.setObject(biz.checkUnitUse(scmMeasureUnit,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}
