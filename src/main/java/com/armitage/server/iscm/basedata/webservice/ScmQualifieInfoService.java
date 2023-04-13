
package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.basedata.model.ScmQualifieInfoWSBean;

public interface ScmQualifieInfoService {
	
	@POST
	@Path("/findPage")
	public ScmQualifieInfoWSBean findPage(ScmQualifieInfoWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmQualifieInfoWSBean queryPage(ScmQualifieInfoWSBean bean);

	@POST
	@Path("/select")
	public ScmQualifieInfoWSBean select(ScmQualifieInfoWSBean bean);

	@POST
	@Path("/add")
	public ScmQualifieInfoWSBean add(ScmQualifieInfoWSBean bean);

	@POST
	@Path("/update")
	public ScmQualifieInfoWSBean update(ScmQualifieInfoWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmQualifieInfoWSBean delete(ScmQualifieInfoWSBean bean);
	
	@POST
	@Path("/auditQualify")
	public ScmQualifieInfoWSBean auditQualify(ScmQualifieInfoWSBean bean);
	
}
