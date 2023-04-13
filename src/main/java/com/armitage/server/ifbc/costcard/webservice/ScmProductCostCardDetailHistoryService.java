
package com.armitage.server.ifbc.costcard.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetailHistoryWSBean;

public interface ScmProductCostCardDetailHistoryService {
	
	@POST
	@Path("/findPage")
	public ScmProductCostCardDetailHistoryWSBean findPage(ScmProductCostCardDetailHistoryWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmProductCostCardDetailHistoryWSBean queryPage(ScmProductCostCardDetailHistoryWSBean bean);

	@POST
	@Path("/select")
	public ScmProductCostCardDetailHistoryWSBean select(ScmProductCostCardDetailHistoryWSBean bean);

	@POST
	@Path("/add")
	public ScmProductCostCardDetailHistoryWSBean add(ScmProductCostCardDetailHistoryWSBean bean);

	@POST
	@Path("/update")
	public ScmProductCostCardDetailHistoryWSBean update(ScmProductCostCardDetailHistoryWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmProductCostCardDetailHistoryWSBean delete(ScmProductCostCardDetailHistoryWSBean bean);
	
}
