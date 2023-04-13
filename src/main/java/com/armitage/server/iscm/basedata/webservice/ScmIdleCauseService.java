
package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.basedata.model.ScmIdleCauseWSBean;

public interface ScmIdleCauseService {
	
	@POST
	@Path("/findPage")
	public ScmIdleCauseWSBean findPage(ScmIdleCauseWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmIdleCauseWSBean queryPage(ScmIdleCauseWSBean bean);

	@POST
	@Path("/select")
	public ScmIdleCauseWSBean select(ScmIdleCauseWSBean bean);

	@POST
	@Path("/add")
	public ScmIdleCauseWSBean add(ScmIdleCauseWSBean bean);

	@POST
	@Path("/update")
	public ScmIdleCauseWSBean update(ScmIdleCauseWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmIdleCauseWSBean delete(ScmIdleCauseWSBean bean);
	
	@POST
	@Path("/selectByCode")
	public ScmIdleCauseWSBean selectByCode(ScmIdleCauseWSBean bean);
}
