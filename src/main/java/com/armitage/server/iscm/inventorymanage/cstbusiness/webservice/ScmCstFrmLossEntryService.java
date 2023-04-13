package com.armitage.server.iscm.inventorymanage.cstbusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLossEntryWSBean;

public interface ScmCstFrmLossEntryService {
	
	@POST
	@Path("/findPage")
	public ScmCstFrmLossEntryWSBean findPage(ScmCstFrmLossEntryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmCstFrmLossEntryWSBean queryPage(ScmCstFrmLossEntryWSBean bean);

	@POST
	@Path("/select")
	public ScmCstFrmLossEntryWSBean select(ScmCstFrmLossEntryWSBean bean);

	@POST
	@Path("/add")
	public ScmCstFrmLossEntryWSBean add(ScmCstFrmLossEntryWSBean bean);

	@POST
	@Path("/update")
	public ScmCstFrmLossEntryWSBean update(ScmCstFrmLossEntryWSBean bean);
	
	@POST
	@Path("/delete")
	public ScmCstFrmLossEntryWSBean delete(ScmCstFrmLossEntryWSBean bean);
	
}
