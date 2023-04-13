package com.armitage.server.iscm.inventorymanage.countbusiness.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvBalOverWSBean;

public interface ScmInvBalOverService {
	
	@POST
	@Path("/balOver")
	public ScmInvBalOverWSBean balOver(ScmInvBalOverWSBean bean);
	
}

