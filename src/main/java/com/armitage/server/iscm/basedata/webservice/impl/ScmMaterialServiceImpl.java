
package com.armitage.server.iscm.basedata.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CommonBeanHelper;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialAdd;
import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelation2;
import com.armitage.server.iscm.basedata.model.ScmMaterialWSBean;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.webservice.ScmMaterialService;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmMaterialService")
public class ScmMaterialServiceImpl extends BaseServiceImpl<ScmMaterialBiz, ScmMaterialWSBean> implements ScmMaterialService {
	
	@Override
	public ScmMaterialWSBean selectItemUnit(ScmMaterialWSBean bean) {
		try {
			bean.setList(biz.selectItemUnit((ScmMaterial2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
	@Override
	public ScmMaterialWSBean updateStatus(ScmMaterialWSBean bean) {
		try {
			BaseModel baseModel = (BaseModel)bean.getObject();
			bean.setObject(biz.updateStatus(baseModel,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialWSBean findCountingMaterial(ScmMaterialWSBean bean) {
		try {
			String orgUnitNo = (String)bean.getObject();
			String fromItemNo = (String)bean.getObject2();
			String toItemNo = (String)bean.getObject3();
			String type = (String)bean.getObject4();
			String depts = (String)bean.getObject5();
			bean.setList(biz.findCountingMaterial(orgUnitNo,depts,fromItemNo,toItemNo,type,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialWSBean checkUse(ScmMaterialWSBean bean) {
		try {
			bean.setObject(biz.checkUse((Long)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	@Override
	public ScmMaterialWSBean checkAllUse(ScmMaterialWSBean bean) {
		try {
			bean.setObject(biz.checkAllUse((Long)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialWSBean selectByItemNo(ScmMaterialWSBean bean) {
		try {
			String controlUnitNo = (String)bean.getObject();
			String itemNo = (String)bean.getObject2();
			bean.setObject(biz.selectByItemNo(controlUnitNo,itemNo,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialWSBean findSameNameMaterial(ScmMaterialWSBean bean) {
		try {
			bean.setObject(biz.findSameNameMaterial((ScmMaterial2)bean.getObject(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialWSBean getConvrate(ScmMaterialWSBean bean) {
		try {
			ScmMaterialUnitRelation2 scmMaterialUnitRelation = (ScmMaterialUnitRelation2)bean.getObject();
			long itemId = scmMaterialUnitRelation.getItemId();
			long unitId = scmMaterialUnitRelation.getTargetUnitId();
			long baseUnitId = scmMaterialUnitRelation.getConvsUnitId();
			bean.setObject(biz.getConvrate(itemId, unitId,baseUnitId, ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialWSBean approval(ScmMaterialWSBean bean) {
		try {
			bean.setObject(biz.approval((ScmMaterial2)bean.getObject(), ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialWSBean findByFinItemId(ScmMaterialWSBean bean) {
		try {
			bean.setObject(biz.findByFinItemId((String)bean.getObject(),(String)bean.getObject2(),(Long)bean.getObject3(), ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialWSBean importScmMaterial(ScmMaterialWSBean bean) {
		try {
			List<String> msgInfoList = (List<String>)bean.getList2();
			if(msgInfoList == null || msgInfoList.isEmpty()){
				msgInfoList = new ArrayList<>();
			}
			CommonBeanHelper.toWSBean(biz.importScmMaterial((ScmDataCollectionStepState2)bean.getObject(),(List<ScmMaterialAdd>)bean.getList(),msgInfoList,ParamHelper.createParam(bean)),bean);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialWSBean batchRatioSet(ScmMaterialWSBean bean) {
		try {
			List<ScmMaterial2> scmMaterial2 = bean.getList();
			CommonBeanHelper.toWSBean(biz.batchRatioSet(scmMaterial2,ParamHelper.createParam(bean)), bean);
		} catch (AppException e) {
			Message.inMessage(bean, e);
		}catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialWSBean disable(ScmMaterialWSBean bean) {
		try {
			bean.setList(biz.disable((BaseModel) bean.getObject(),bean.getList(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmMaterialWSBean enable(ScmMaterialWSBean bean) {
		try {
			bean.setList(biz.enable((BaseModel) bean.getObject(),bean.getList(),ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}
	
}
