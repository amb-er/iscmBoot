
package com.armitage.server.iscm.purchasemanage.purchasesetting.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurInquiryGroupWSBean;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurInquiryGroupBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.webservice.ScmPurInquiryGroupService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/ScmPurInquiryGroupService")
public class ScmPurInquiryGroupServiceImpl extends BaseServiceImpl<ScmPurInquiryGroupBiz, ScmPurInquiryGroupWSBean> implements ScmPurInquiryGroupService {


}
