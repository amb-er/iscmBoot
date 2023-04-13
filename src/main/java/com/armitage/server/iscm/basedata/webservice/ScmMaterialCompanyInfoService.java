package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmMaterialCompanyInfoWSBean;

public interface ScmMaterialCompanyInfoService {
	
	@POST
	@Path("/findPage")
	public ScmMaterialCompanyInfoWSBean findPage(ScmMaterialCompanyInfoWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmMaterialCompanyInfoWSBean queryPage(ScmMaterialCompanyInfoWSBean bean);

	@POST
	@Path("/select")
	public ScmMaterialCompanyInfoWSBean select(ScmMaterialCompanyInfoWSBean bean);

	@POST
	@Path("/add")
	public ScmMaterialCompanyInfoWSBean add(ScmMaterialCompanyInfoWSBean bean);

	@POST
	@Path("/update")
	public ScmMaterialCompanyInfoWSBean update(ScmMaterialCompanyInfoWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmMaterialCompanyInfoWSBean delete(ScmMaterialCompanyInfoWSBean bean);
	
}
