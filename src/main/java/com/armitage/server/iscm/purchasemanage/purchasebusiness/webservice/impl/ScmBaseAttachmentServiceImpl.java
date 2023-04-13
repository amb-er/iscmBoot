
package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.impl;

import java.util.Date;

import org.apache.commons.collections.map.HashedMap;

import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachmentWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmBaseAttachmentBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice.ScmBaseAttachmentService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

/**
 * @author cp020020006
 */
@Controller
@Path("/scmBaseAttachmentService")
public class ScmBaseAttachmentServiceImpl extends BaseServiceImpl<ScmBaseAttachmentBiz, ScmBaseAttachmentWSBean> implements ScmBaseAttachmentService {
	@Override
	public ScmBaseAttachmentWSBean addDataAfterUpload(ScmBaseAttachmentWSBean bean) {
		try {
			Param param = ParamHelper.createParam(bean);
			ScmBaseAttachment object = (ScmBaseAttachment) bean.getObject();
			object.setCreateTime(new Date());
			object.setCreator(param.getUsrCode());
			object.setControlUnitNo(param.getControlUnitNo());
			bean.setObject(biz.add(object, param));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmBaseAttachmentWSBean findBybillId(ScmBaseAttachmentWSBean bean) {
		try {
			Param param = ParamHelper.createParam(bean);
			long billId = (long) bean.getObject();
			String controlUnitNo = param.getControlUnitNo();
			HashedMap hashedMap = new HashedMap();
			hashedMap.put("controlUnitNo", controlUnitNo);
			hashedMap.put("billId", billId);
			hashedMap.put("billType", bean.getObject2());
			bean.setObject(biz.findBybillId(hashedMap,param));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
}
