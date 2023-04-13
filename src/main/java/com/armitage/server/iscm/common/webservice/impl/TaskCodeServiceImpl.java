package com.armitage.server.iscm.common.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.common.model.TaskCodeWSBean;
import com.armitage.server.iscm.common.service.TaskCodeBiz;
import com.armitage.server.iscm.common.webservice.TaskCodeService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/taskCodeService")
public class TaskCodeServiceImpl extends BaseServiceImpl<TaskCodeBiz, TaskCodeWSBean> implements TaskCodeService{

}
