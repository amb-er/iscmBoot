package com.armitage.server.iscm.common.webservice.impl;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.common.model.TaskSettingWSBean;
import com.armitage.server.iscm.common.service.TaskSettingBiz;
import com.armitage.server.iscm.common.webservice.TaskSettingService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("/taskSettingService")
public class TaskSettingServiceImpl extends BaseServiceImpl<TaskSettingBiz, TaskSettingWSBean> implements TaskSettingService{

}
