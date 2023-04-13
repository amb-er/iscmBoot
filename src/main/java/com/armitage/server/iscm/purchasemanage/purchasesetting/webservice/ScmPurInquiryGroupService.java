
package com.armitage.server.iscm.purchasemanage.purchasesetting.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurInquiryGroupWSBean;
@Path("/scmPurInquiryGroup/")
public interface ScmPurInquiryGroupService {
	
	@POST
	@Path("/findPage")
	public ScmPurInquiryGroupWSBean findPage(ScmPurInquiryGroupWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurInquiryGroupWSBean queryPage(ScmPurInquiryGroupWSBean bean);

	@POST
	@Path("/select")
	public ScmPurInquiryGroupWSBean select(ScmPurInquiryGroupWSBean bean);

	@POST
	@Path("/add")
	public ScmPurInquiryGroupWSBean add(ScmPurInquiryGroupWSBean bean);

	@POST
	@Path("/update")
	public ScmPurInquiryGroupWSBean update(ScmPurInquiryGroupWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmPurInquiryGroupWSBean delete(ScmPurInquiryGroupWSBean bean);
	
}
