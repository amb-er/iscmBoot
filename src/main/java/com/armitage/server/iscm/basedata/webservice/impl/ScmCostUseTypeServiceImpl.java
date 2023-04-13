/**
 * 广州万迅电脑软件有限公司(c)
 * @author Promise
 * 2020年12月16日 下午4:53:50
 *
 */
package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmCostUseTypeWSBean;
import com.armitage.server.iscm.basedata.service.ScmCostUseTypeBiz;
import com.armitage.server.iscm.basedata.webservice.ScmCostUseTypeService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmCostUseTypeService")
public class ScmCostUseTypeServiceImpl extends BaseServiceImpl<ScmCostUseTypeBiz, ScmCostUseTypeWSBean>
		implements ScmCostUseTypeService {

	@Override
	public ScmCostUseTypeWSBean selectAll(ScmCostUseTypeWSBean bean) throws AppException{
		try {
			bean.setList(biz.selectAll(ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmCostUseTypeWSBean queryByNameOrCode(ScmCostUseTypeWSBean bean) throws AppException {
		try {
			String object = (String) bean.getObject();
			bean.setList(biz.queryByNameOrCode(object,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

}
