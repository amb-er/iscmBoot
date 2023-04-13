
package com.armitage.server.ifbc.costcard.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetailWSBean;

public interface ScmProductCostCardDetailService {
	
	@POST
	@Path("/findPage")
	public ScmProductCostCardDetailWSBean findPage(ScmProductCostCardDetailWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmProductCostCardDetailWSBean queryPage(ScmProductCostCardDetailWSBean bean);

	@POST
	@Path("/select")
	public ScmProductCostCardDetailWSBean select(ScmProductCostCardDetailWSBean bean);

	@POST
	@Path("/add")
	public ScmProductCostCardDetailWSBean add(ScmProductCostCardDetailWSBean bean);

	@POST
	@Path("/update")
	public ScmProductCostCardDetailWSBean update(ScmProductCostCardDetailWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmProductCostCardDetailWSBean delete(ScmProductCostCardDetailWSBean bean);
	
}
