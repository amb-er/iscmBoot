package com.armitage.server.activity.service;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.armitage.server.activity.util.ActivitiUserUtil;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.user.model.Role;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.model.UsrOrgRange2;
import com.armitage.server.user.service.RoleBiz;
import com.armitage.server.user.service.UsrBiz;
import com.armitage.server.user.service.UsrOrgRangeBiz;

@Component
public class CustomUserEntityManager extends UserEntityManager{
	
	private static final Log logger = LogFactory.getLog(CustomUserEntityManager.class);
 
	private UsrBiz usrBiz = (UsrBiz) AppContextUtil.getBean("usrBiz");
	private RoleBiz roleBiz = (RoleBiz) AppContextUtil.getBean("roleBiz");
	private UsrOrgRangeBiz usrOrgRangeBiz = (UsrOrgRangeBiz) AppContextUtil.getBean("usrOrgRangeBiz");
 
 
    @Override
    public UserEntity findUserById(String userCode) {
    	if(userCode == null){
        	return null;
        }
        Param param = new Param();
        Usr usr = usrBiz.selectByCode(userCode, param);
        if(usr != null){
        	return ActivitiUserUtil.toActivitiUser(usr);	
        }else if("activityStepUser".equals(userCode)){
        	UserEntity userEntity = new UserEntity();  
            userEntity.setId(userCode);  
            userEntity.setFirstName("activityStepUserName");  
            userEntity.setPassword("activityStepUserPwd");  
            userEntity.setEmail("activityStepUserEmail");  
            userEntity.setRevision(1); 
            return userEntity;
        }
        return null;
    }
 
    @Override
    public List<Group> findGroupsByUser(String userCode) {
    	if(userCode == null){
        	return null;
        }
        Param param = new Param();
        Usr usr = usrBiz.selectByCode(userCode, param);
        if(usr == null){
        	if("activityStepUser".equals(userCode)){
        		List<Group> inGs = new ArrayList<>();
            	GroupEntity groupEntity = new GroupEntity();  
                groupEntity.setRevision(1);  
                groupEntity.setType("assignment");  
                groupEntity.setId("activityStepGroupId");  
                groupEntity.setName("activityStepGroupName");  
                inGs.add(groupEntity);
                return inGs;
        	}
        	return null;
        }
        List<UsrOrgRange2> usrOrgList = usrOrgRangeBiz.selectByUsr(usr.getId(), param);
        List<Role> groupList = roleBiz.findAllByUsrId(usr.getId(), param);
        List<Group> gs = ActivitiUserUtil.toActivitiGroups(usrOrgList,groupList);
        return gs;
    }
}
