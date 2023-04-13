
package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturnsWSBean;

public interface ScmPurReturnsService {
	
	@POST
	@Path("/findPage")
	public ScmPurReturnsWSBean findPage(ScmPurReturnsWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmPurReturnsWSBean queryPage(ScmPurReturnsWSBean bean);

	@POST
	@Path("/select")
	public ScmPurReturnsWSBean select(ScmPurReturnsWSBean bean);

	@POST
	@Path("/add")
	public ScmPurReturnsWSBean add(ScmPurReturnsWSBean bean);

	@POST
	@Path("/update")
	public ScmPurReturnsWSBean update(ScmPurReturnsWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmPurReturnsWSBean delete(ScmPurReturnsWSBean bean);
	
	@POST
	@Path("/submit")
	public ScmPurReturnsWSBean submit(ScmPurReturnsWSBean bean);

	@POST
	@Path("/undoSubmit")
	public ScmPurReturnsWSBean undoSubmit(ScmPurReturnsWSBean bean);

	@POST
	@Path("/release")
	public ScmPurReturnsWSBean release(ScmPurReturnsWSBean bean);

	@POST
	@Path("/undoRelease")
	public ScmPurReturnsWSBean undoRelease(ScmPurReturnsWSBean bean);

	@POST
	@Path("/audit")
	public ScmPurReturnsWSBean audit(ScmPurReturnsWSBean bean);

	@POST
	@Path("/antiAudit")
	public ScmPurReturnsWSBean antiAudit(ScmPurReturnsWSBean bean);

	@POST
	@Path("/doAudit")
	public ScmPurReturnsWSBean doAudit(ScmPurReturnsWSBean bean);

	@POST
	@Path("/undoAudit")
	public ScmPurReturnsWSBean undoAudit(ScmPurReturnsWSBean bean);

	@POST
	@Path("/updatePrtCount")
	public ScmPurReturnsWSBean updatePrtCount(ScmPurReturnsWSBean bean);
}
