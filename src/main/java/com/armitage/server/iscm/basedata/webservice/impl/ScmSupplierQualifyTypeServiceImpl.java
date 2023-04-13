package com.armitage.server.iscm.basedata.webservice.impl;


import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifyTypeWSBean;
import com.armitage.server.iscm.basedata.service.ScmSupplierQualifyTypeBiz;
import com.armitage.server.iscm.basedata.webservice.ScmSupplierQualifyTypeService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/scmSupplierQualifyTypeService")
public class ScmSupplierQualifyTypeServiceImpl extends BaseServiceImpl<ScmSupplierQualifyTypeBiz, ScmSupplierQualifyTypeWSBean> implements ScmSupplierQualifyTypeService {
	
}

