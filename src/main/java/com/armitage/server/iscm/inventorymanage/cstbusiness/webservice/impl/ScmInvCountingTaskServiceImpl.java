package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CommonBeanHelper;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTask2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTaskWSBean;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingTaskBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.webservice.ScmInvCountingTaskService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvCountingTaskService")
public class ScmInvCountingTaskServiceImpl extends BaseServiceImpl<ScmInvCountingTaskBiz, ScmInvCountingTaskWSBean> implements ScmInvCountingTaskService {

	@Override
    public ScmInvCountingTaskWSBean selectMaxIdByDate(ScmInvCountingTaskWSBean bean) {
        try {
            bean.setObject(biz.selectMaxIdByDate((String)bean.getObject(), (String)bean.getObject2(), ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

	@Override
	public ScmInvCountingTaskWSBean generateCounting(ScmInvCountingTaskWSBean bean) {
		try {
            bean.setObject(biz.generateCounting((ScmInvCountingTask2)bean.getObject(), ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}

    @Override
    public ScmInvCountingTaskWSBean selectByDate(ScmInvCountingTaskWSBean bean) {
        try {
            bean.setList(biz.selectByDate((String)bean.getObject(), (String)bean.getObject2(), ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
    }

	@Override
	public ScmInvCountingTaskWSBean selectByOrgUnitNoAndWareHouseId(ScmInvCountingTaskWSBean bean) {
		try {
            bean.setList(biz.selectByOrgUnitNoAndWareHouseId((String)bean.getObject(),(Long)bean.getObject3(),(String)bean.getObject2(), ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;

	}

	@Override
	public ScmInvCountingTaskWSBean selectByOrgUnitNoAndUseOrgUnitNo(ScmInvCountingTaskWSBean bean) {
		try {
            bean.setList(biz.selectByOrgUnitNoAndUseOrgUnitNo((String)bean.getObject(),(Long)bean.getObject3(), (String)bean.getObject2(), ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}

	@Override
	public ScmInvCountingTaskWSBean generateCosting(ScmInvCountingTaskWSBean bean) {
		try {
            bean.setObject(biz.generateCosting((ScmInvCountingTask2)bean.getObject(), ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}
	
	@Override
	public ScmInvCountingTaskWSBean countingFinish(ScmInvCountingTaskWSBean bean) {
		try {
			ScmInvCountingTask2 scmInvCountingTask = (ScmInvCountingTask2)bean.getObject();
			bean.setList(biz.countingFinish(scmInvCountingTask,ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvCountingTaskWSBean refreshAccount(ScmInvCountingTaskWSBean bean) {
		try {
            bean.setObject(biz.refreshAccount((ScmInvCountingTask2)bean.getObject(), ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}

	@Override
	public ScmInvCountingTaskWSBean queryCountInvTaskDiff(ScmInvCountingTaskWSBean bean) {
		try {
			bean.setList(biz.queryCountInvTaskDiff((ScmInvCountingTask2)bean.getObject(), ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvCountingTaskWSBean queryCountCostTaskDiff(ScmInvCountingTaskWSBean bean) {
		try {
			bean.setList(biz.queryCountCostTaskDiff((ScmInvCountingTask2)bean.getObject(), ParamHelper.createParam(bean)));
		} catch (AppException e) {
			Message.inMessage(bean, e);
		} catch (Exception e) {
			Message.inMessage(bean, e);
		}
		return bean;
	}

	@Override
	public ScmInvCountingTaskWSBean confirm(ScmInvCountingTaskWSBean bean) {
		try {
            bean.setObject(biz.confirm((ScmInvCountingTask2)bean.getObject(), ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;	}
	
	@Override
	public ScmInvCountingTaskWSBean generateCountingCheck(ScmInvCountingTaskWSBean bean) {
		try {
            bean.setObject(biz.generateCountingCheck((ScmInvCountingTask2)bean.getObject(), ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}

	@Override
	public ScmInvCountingTaskWSBean generateCostingCheck(ScmInvCountingTaskWSBean bean) {
		try {
            bean.setObject(biz.generateCostingCheck((ScmInvCountingTask2)bean.getObject(), ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}

	@Override
	public ScmInvCountingTaskWSBean splitOrgAdd(ScmInvCountingTaskWSBean bean) {
		try {
//			Thread.sleep(10*1000);
			CommonBeanHelper.toWSBean(biz.splitOrgAdd(CommonBeanHelper.fromWSBean(bean),ParamHelper.createParam(bean)),bean);
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}

	@Override
	public ScmInvCountingTaskWSBean queryByTaskNo(ScmInvCountingTaskWSBean bean) {
		try {
            bean.setObject(biz.queryByTaskNo((String)bean.getObject(), ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        return bean;
	}
	
}
