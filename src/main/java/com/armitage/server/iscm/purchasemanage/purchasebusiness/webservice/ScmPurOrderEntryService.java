package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice;

import java.math.BigDecimal;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntryWSBean;


public interface ScmPurOrderEntryService {
	
	@POST
	@Path("/findPage")
	public ScmPurOrderEntryWSBean findPage(ScmPurOrderEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurOrderEntryWSBean queryPage(ScmPurOrderEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmPurOrderEntryWSBean select(ScmPurOrderEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmPurOrderEntryWSBean add(ScmPurOrderEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmPurOrderEntryWSBean update(ScmPurOrderEntryWSBean bean);
	
	@POST
	@Path("/delete")
	public ScmPurOrderEntryWSBean delete(ScmPurOrderEntryWSBean bean);
	
	@POST
	@Path("/updateStatus")
	public ScmPurOrderEntryWSBean updateStatus(ScmPurOrderEntryWSBean bean);
	
	@POST
	@Path("/getDataForLeadInto")
	public ScmPurOrderEntryWSBean getDataForLeadInto(ScmPurOrderEntryWSBean bean);
	
	@POST
	@Path("/generateTempPrice")
	public ScmPurOrderEntryWSBean generateTempPrice(ScmPurOrderEntryWSBean bean);
}

