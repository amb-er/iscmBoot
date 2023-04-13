package com.armitage.server.ifbc.costcard.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbc.costcard.model.ScmCookCostCardWSBean;


public interface ScmCookCostCardService {
	
	@POST
	@Path("/findPage")
	public ScmCookCostCardWSBean findPage(ScmCookCostCardWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmCookCostCardWSBean queryPage(ScmCookCostCardWSBean bean);

	@POST
	@Path("/select")
	public ScmCookCostCardWSBean select(ScmCookCostCardWSBean bean);

	@POST
	@Path("/add")
	public ScmCookCostCardWSBean add(ScmCookCostCardWSBean bean);

	@POST
	@Path("/update")
	public ScmCookCostCardWSBean update(ScmCookCostCardWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmCookCostCardWSBean delete(ScmCookCostCardWSBean bean);
	
	@POST
	@Path("/lock")
	public ScmCookCostCardWSBean lock(ScmCookCostCardWSBean bean);
	
	@POST
	@Path("/unlock")
	public ScmCookCostCardWSBean unlock(ScmCookCostCardWSBean bean);
	
	@POST
	@Path("/auditCostCard")
	public ScmCookCostCardWSBean auditCostCard(ScmCookCostCardWSBean bean);
	
	@POST
	@Path("/copyCostCard")
	public ScmCookCostCardWSBean copyCostCard(ScmCookCostCardWSBean bean);
	

}
