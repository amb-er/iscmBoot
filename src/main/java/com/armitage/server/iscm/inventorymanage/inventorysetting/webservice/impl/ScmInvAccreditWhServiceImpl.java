package com.armitage.server.iscm.inventorymanage.inventorysetting.webservice.impl;

import javax.ws.rs.Path;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvAccreditWh2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvAccreditWhWSBean;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvAccreditWhBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.webservice.ScmInvAccreditWhService;
import org.springframework.stereotype.Controller;

@Controller
@Path("/scmInvAccreditWhService")
public class ScmInvAccreditWhServiceImpl extends BaseServiceImpl<ScmInvAccreditWhBiz, ScmInvAccreditWhWSBean> implements ScmInvAccreditWhService {

    /**
     * 结束初始化
     */
    @Override
    public ScmInvAccreditWhWSBean initEnd(ScmInvAccreditWhWSBean bean) {
        try {
            ScmInvAccreditWh2 scmInvAccreditWh = (ScmInvAccreditWh2)bean.getObject();
            bean.setObject(biz.initEnd(scmInvAccreditWh, ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        
        return bean;
    }

    /**
     * 反初始化
     */
    @Override
    public ScmInvAccreditWhWSBean reverseInit(ScmInvAccreditWhWSBean bean) {
        try {
            ScmInvAccreditWh2 scmInvAccreditWh = (ScmInvAccreditWh2)bean.getObject();
            bean.setObject(biz.reverseInit(scmInvAccreditWh, ParamHelper.createParam(bean)));
        } catch (AppException e) {
            Message.inMessage(bean, e);
        } catch (Exception e) {
            Message.inMessage(bean, e);
        }
        
        return bean;
    }
    
}

