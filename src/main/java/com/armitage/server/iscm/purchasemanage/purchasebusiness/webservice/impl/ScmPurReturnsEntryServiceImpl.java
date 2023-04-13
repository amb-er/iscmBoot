
package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.impl;

import java.util.List;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntryWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturnsEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturnsEntryWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReturnsEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.ScmPurReturnsEntryService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmPurReturnsEntryService")
public class ScmPurReturnsEntryServiceImpl extends BaseServiceImpl<ScmPurReturnsEntryBiz, ScmPurReturnsEntryWSBean> implements ScmPurReturnsEntryService {

	@Override
	public ScmPurReturnsEntryWSBean updateStatus(ScmPurReturnsEntryWSBean bean) {
		try {
			List<ScmPurReturnsEntry2> scmPurReturnsEntryList = (List<ScmPurReturnsEntry2>)bean.getList();
			bean.setList(biz.updateStatus(scmPurReturnsEntryList,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
