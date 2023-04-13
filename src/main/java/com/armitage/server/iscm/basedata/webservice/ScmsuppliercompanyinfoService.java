package com.armitage.server.iscm.basedata.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.basedata.model.ScmsuppliercompanyinfoWSBean;

public interface ScmsuppliercompanyinfoService {
	
	@POST
	@Path("/findPage")
	public ScmsuppliercompanyinfoWSBean findPage(ScmsuppliercompanyinfoWSBean bean);

	@POST
	@Path("/queryPage")
	public ScmsuppliercompanyinfoWSBean queryPage(ScmsuppliercompanyinfoWSBean bean);

	@POST
	@Path("/select")
	public ScmsuppliercompanyinfoWSBean select(ScmsuppliercompanyinfoWSBean bean);

	@POST
	@Path("/add")
	public ScmsuppliercompanyinfoWSBean add(ScmsuppliercompanyinfoWSBean bean);

	@POST
	@Path("/update")
	public ScmsuppliercompanyinfoWSBean update(ScmsuppliercompanyinfoWSBean bean);
	

	@POST
	@Path("/delete")
	public ScmsuppliercompanyinfoWSBean delete(ScmsuppliercompanyinfoWSBean bean);
	
}
