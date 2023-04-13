
package com.armitage.server.iscm.inventorymanage.inventorysetting.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouseAndDeptWSBean;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseAndDeptBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.webservice.ScmInvWareHouseAndDeptService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmInvWareHouseAndDeptService")
public class ScmInvWareHouseAndDeptServiceImpl extends BaseServiceImpl<ScmInvWareHouseAndDeptBiz, ScmInvWareHouseAndDeptWSBean> implements ScmInvWareHouseAndDeptService {
	

}
