package com.armitage.server.ifbc.costcard.webservice;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbc.costcard.model.ScmCostCardWSBean;

public interface ScmCostCardService {
	
	@POST
	@Path("/findPage")
	public ScmCostCardWSBean findPage(ScmCostCardWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmCostCardWSBean queryPage(ScmCostCardWSBean bean);

	@POST
	@Path("/select")
	public ScmCostCardWSBean select(ScmCostCardWSBean bean);

	@POST
	@Path("/add")
	public ScmCostCardWSBean add(ScmCostCardWSBean bean);

	@POST
	@Path("/update")
	public ScmCostCardWSBean update(ScmCostCardWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmCostCardWSBean delete(ScmCostCardWSBean bean);
	
	@POST
	@Path("/lock")
	public ScmCostCardWSBean lock(ScmCostCardWSBean bean);
	
	@POST
	@Path("/unlock")
	public ScmCostCardWSBean unlock(ScmCostCardWSBean bean);
	
	@POST
	@Path("/auditCostCard")
	public ScmCostCardWSBean auditCostCard(ScmCostCardWSBean bean);
//	
//	@POST
//	@Path("/getDataForReplace")
//	public ScmCostCardWSBean getDataForReplace(ScmCostCardWSBean bean);
//	
//	@POST
//	@Path("/replaceItem")
//	public ScmCostCardWSBean replaceItem(ScmCostCardWSBean bean);
//	
	@POST
	@Path("/copyCostCard")
	public ScmCostCardWSBean copyCostCard(ScmCostCardWSBean bean);
}
