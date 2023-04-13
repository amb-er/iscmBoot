
package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmQualifieInfo2;
import com.armitage.server.iscm.basedata.model.ScmQualifieInfoWSBean;
import com.armitage.server.iscm.basedata.service.ScmQualifieInfoBiz;
import com.armitage.server.iscm.basedata.webservice.ScmQualifieInfoService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmQualifieInfoService")
public class ScmQualifieInfoServiceImpl extends BaseServiceImpl<ScmQualifieInfoBiz, ScmQualifieInfoWSBean> implements ScmQualifieInfoService {

	@Override
	public ScmQualifieInfoWSBean auditQualify(ScmQualifieInfoWSBean bean) {
		try {
			biz.auditQualify((ScmQualifieInfo2)bean.getObject(),(String) bean.getObject2(),ParamHelper.createParam(bean));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
