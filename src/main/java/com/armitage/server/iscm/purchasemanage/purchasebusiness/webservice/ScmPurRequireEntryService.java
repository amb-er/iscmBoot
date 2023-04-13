package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice;


import java.math.BigDecimal;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntryWSBean;

public interface ScmPurRequireEntryService {
	
	@POST
	@Path("/findPage")
	public ScmPurRequireEntryWSBean findPage(ScmPurRequireEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurRequireEntryWSBean queryPage(ScmPurRequireEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmPurRequireEntryWSBean select(ScmPurRequireEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmPurRequireEntryWSBean add(ScmPurRequireEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmPurRequireEntryWSBean update(ScmPurRequireEntryWSBean bean);
	
	@POST
	@Path("/delete")
	public ScmPurRequireEntryWSBean delete(ScmPurRequireEntryWSBean bean);
	
	@POST
	@Path("/updateStatus")
	public ScmPurRequireEntryWSBean updateStatus(ScmPurRequireEntryWSBean bean);
	
	@POST
	@Path("/generateOrder")
	public ScmPurRequireEntryWSBean generateOrder(ScmPurRequireEntryWSBean bean);
	
	@POST
	@Path("/getDataForLeadInto")
	public ScmPurRequireEntryWSBean getDataForLeadInto(ScmPurRequireEntryWSBean bean);
	
	@POST
	@Path("/autoAssign")
	public ScmPurRequireEntryWSBean autoAssign(ScmPurRequireEntryWSBean bean);
	
	@POST
	@Path("/cancelRefuse")
	public ScmPurRequireEntryWSBean cancelRefuse(ScmPurRequireEntryWSBean bean);
	
	@POST
	@Path("/Refuse")
	public ScmPurRequireEntryWSBean refuse(ScmPurRequireEntryWSBean bean);
	
	@POST
	@Path("/viewPurRequestStatus")
	public ScmPurRequireEntryWSBean viewPurRequestStatus(ScmPurRequireEntryWSBean bean);
	
	@POST
	@Path("/generateTempPrice")
	public ScmPurRequireEntryWSBean generateTempPrice(ScmPurRequireEntryWSBean bean);
	
	@POST
	@Path("/selectByPurOrgUnitNo")
	public ScmPurRequireEntryWSBean selectByPurOrgUnitNo(ScmPurRequireEntryWSBean bean);
	
}

