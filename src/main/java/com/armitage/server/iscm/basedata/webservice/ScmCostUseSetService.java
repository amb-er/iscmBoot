/**
 * 广州万迅电脑软件有限公司(c)
 * @author Promise
 * 2020年12月16日 下午5:43:13
 *
 */
package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmCostUseSetWSBean;

public interface ScmCostUseSetService {

	@POST
	@Path("/findPage")
	public ScmCostUseSetWSBean findPage(ScmCostUseSetWSBean bean);


	@POST
	@Path("/queryPage")
	public ScmCostUseSetWSBean queryPage(ScmCostUseSetWSBean bean);

	@POST
	@Path("/select")
	public ScmCostUseSetWSBean select(ScmCostUseSetWSBean bean);

	@POST
	@Path("/add")
	public ScmCostUseSetWSBean add(ScmCostUseSetWSBean bean);

	@POST
	@Path("/update")
	public ScmCostUseSetWSBean update(ScmCostUseSetWSBean bean);

	@POST
	@Path("/delete")
	public ScmCostUseSetWSBean delete(ScmCostUseSetWSBean bean);

	@POST
	@Path("/buildMap")
	public ScmCostUseSetWSBean buildMap(ScmCostUseSetWSBean bean);
}
