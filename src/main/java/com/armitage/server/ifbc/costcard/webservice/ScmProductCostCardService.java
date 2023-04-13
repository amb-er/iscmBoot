
package com.armitage.server.ifbc.costcard.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardWSBean;

public interface ScmProductCostCardService {
	
	@POST
	@Path("/findPage")
	public ScmProductCostCardWSBean findPage(ScmProductCostCardWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmProductCostCardWSBean queryPage(ScmProductCostCardWSBean bean);

	@POST
	@Path("/select")
	public ScmProductCostCardWSBean select(ScmProductCostCardWSBean bean);

	@POST
	@Path("/add")
	public ScmProductCostCardWSBean add(ScmProductCostCardWSBean bean);

	@POST
	@Path("/update")
	public ScmProductCostCardWSBean update(ScmProductCostCardWSBean bean);
	
	@POST
	@Path("/delete")
	public ScmProductCostCardWSBean delete(ScmProductCostCardWSBean bean);
	
	@POST
	@Path("/auditCostCard")
	public ScmProductCostCardWSBean auditCostCard(ScmProductCostCardWSBean bean);
	
}
