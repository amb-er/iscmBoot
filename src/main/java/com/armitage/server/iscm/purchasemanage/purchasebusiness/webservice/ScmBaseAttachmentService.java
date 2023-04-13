
package com.armitage.server.iscm.purchasemanage.purchasebusiness.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachmentWSBean;

public interface ScmBaseAttachmentService {
	
	@POST
	@Path("/findPage")
	public ScmBaseAttachmentWSBean findPage(ScmBaseAttachmentWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmBaseAttachmentWSBean queryPage(ScmBaseAttachmentWSBean bean);

	@POST
	@Path("/select")
	public ScmBaseAttachmentWSBean select(ScmBaseAttachmentWSBean bean);

	@POST
	@Path("/add")
	public ScmBaseAttachmentWSBean add(ScmBaseAttachmentWSBean bean);

	@POST
	@Path("/update")
	public ScmBaseAttachmentWSBean update(ScmBaseAttachmentWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmBaseAttachmentWSBean delete(ScmBaseAttachmentWSBean bean);
	
	@POST
	@Path("/addDataAfterUpload")
	public ScmBaseAttachmentWSBean addDataAfterUpload(ScmBaseAttachmentWSBean bean);
	
	@POST
	@Path("/findBybillId")
	public ScmBaseAttachmentWSBean findBybillId(ScmBaseAttachmentWSBean bean);
	
}
