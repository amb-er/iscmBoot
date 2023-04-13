
package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmIndustryGroupQualifyTypeWSBean;

public interface ScmIndustryGroupQualifyTypeService {
	
	@POST
	@Path("/findPage")
	public ScmIndustryGroupQualifyTypeWSBean findPage(ScmIndustryGroupQualifyTypeWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmIndustryGroupQualifyTypeWSBean queryPage(ScmIndustryGroupQualifyTypeWSBean bean);

	@POST
	@Path("/select")
	public ScmIndustryGroupQualifyTypeWSBean select(ScmIndustryGroupQualifyTypeWSBean bean);

	@POST
	@Path("/add")
	public ScmIndustryGroupQualifyTypeWSBean add(ScmIndustryGroupQualifyTypeWSBean bean);

	@POST
	@Path("/update")
	public ScmIndustryGroupQualifyTypeWSBean update(ScmIndustryGroupQualifyTypeWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmIndustryGroupQualifyTypeWSBean delete(ScmIndustryGroupQualifyTypeWSBean bean);
	
}
