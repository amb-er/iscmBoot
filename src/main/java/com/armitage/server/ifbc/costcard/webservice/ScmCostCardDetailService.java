package com.armitage.server.ifbc.costcard.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.ifbc.costcard.model.ScmCostCardDetailWSBean;

public interface ScmCostCardDetailService {
	
	@POST
	@Path("/findPage")
	public ScmCostCardDetailWSBean findPage(ScmCostCardDetailWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmCostCardDetailWSBean queryPage(ScmCostCardDetailWSBean bean);

	@POST
	@Path("/select")
	public ScmCostCardDetailWSBean select(ScmCostCardDetailWSBean bean);

	@POST
	@Path("/add")
	public ScmCostCardDetailWSBean add(ScmCostCardDetailWSBean bean);

	@POST
	@Path("/update")
	public ScmCostCardDetailWSBean update(ScmCostCardDetailWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmCostCardDetailWSBean delete(ScmCostCardDetailWSBean bean);
	
}
