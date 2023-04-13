/**
 * 广州万迅电脑软件有限公司(c)
 * @author Promise
 * 2020年12月16日 下午5:53:28
 *
 */
package com.armitage.server.iscm.basedata.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmCostUseSet2;
import com.armitage.server.iscm.basedata.model.ScmCostUseSetWSBean;
import com.armitage.server.iscm.basedata.service.ScmCostUseSetBiz;
import com.armitage.server.iscm.basedata.webservice.ScmCostUseSetService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmCostUseSetService")
public class ScmCostUseSetServiceImpl extends BaseServiceImpl<ScmCostUseSetBiz, ScmCostUseSetWSBean> implements ScmCostUseSetService{

	@Override
	public ScmCostUseSetWSBean buildMap(ScmCostUseSetWSBean bean) {
		try {
			biz.buildMap((ScmCostUseSet2)bean.getObject(),bean.getList(),ParamHelper.createParam(bean));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

}

