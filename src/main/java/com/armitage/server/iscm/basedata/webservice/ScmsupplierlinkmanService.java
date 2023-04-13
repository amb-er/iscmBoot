package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmsupplierlinkmanWSBean;

public interface ScmsupplierlinkmanService {
	
	@POST
	@Path("/findPage")
	public ScmsupplierlinkmanWSBean findPage(ScmsupplierlinkmanWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmsupplierlinkmanWSBean queryPage(ScmsupplierlinkmanWSBean bean);

	@POST
	@Path("/select")
	public ScmsupplierlinkmanWSBean select(ScmsupplierlinkmanWSBean bean);

	@POST
	@Path("/add")
	public ScmsupplierlinkmanWSBean add(ScmsupplierlinkmanWSBean bean);

	@POST
	@Path("/update")
	public ScmsupplierlinkmanWSBean update(ScmsupplierlinkmanWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmsupplierlinkmanWSBean delete(ScmsupplierlinkmanWSBean bean);
	
}
