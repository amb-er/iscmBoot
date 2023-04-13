
package com.armitage.server.iscm.inventorymanage.inventorysetting.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRule;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRuleWSBean;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvSupplyRuleBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.webservice.ScmInvSupplyRuleService;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireWSBean;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvSupplyRuleService")
public class ScmInvSupplyRuleServiceImpl extends BaseServiceImpl<ScmInvSupplyRuleBiz, ScmInvSupplyRuleWSBean> implements ScmInvSupplyRuleService {

	@Override
	public ScmInvSupplyRuleWSBean generatePlan(ScmInvSupplyRuleWSBean bean) {
	    try {
	    	ScmInvSupplyRule scmInvSupplyRule = (ScmInvSupplyRule)bean.getObject();
	        bean = biz.generatePlan(scmInvSupplyRule,ParamHelper.createParam(bean));
	    } catch (AppException e) {
	        Message.inMessage(bean, e);
	    } catch (Exception e) {
	        Message.inMessage(bean, e);
	    }
	    return bean; 
	}
	
}
