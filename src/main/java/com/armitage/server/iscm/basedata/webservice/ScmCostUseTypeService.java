/**
 * 广州万迅电脑软件有限公司(c)
 * @author Promise
 * 2020年12月16日 下午4:49:50
 *
 */
package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmCostUseSetWSBean;
import com.armitage.server.iscm.basedata.model.ScmCostUseTypeWSBean;

public interface ScmCostUseTypeService {

	@POST
	@Path("/findPage")
	public ScmCostUseTypeWSBean findPage(ScmCostUseTypeWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmCostUseTypeWSBean queryPage(ScmCostUseTypeWSBean bean);

	@POST
	@Path("/select")
	public ScmCostUseTypeWSBean select(ScmCostUseTypeWSBean bean);

	@POST
	@Path("/add")
	public ScmCostUseTypeWSBean add(ScmCostUseTypeWSBean bean);

	@POST
	@Path("/update")
	public ScmCostUseTypeWSBean update(ScmCostUseTypeWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmCostUseTypeWSBean delete(ScmCostUseTypeWSBean bean);
	
	@POST
	@Path("/selectAll")
	public ScmCostUseTypeWSBean selectAll(ScmCostUseTypeWSBean bean);
	
	@POST
	@Path("/queryByNameOrCode")
	public ScmCostUseTypeWSBean queryByNameOrCode(ScmCostUseTypeWSBean bean);
}

