package com.armitage.server.activity.util;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;

import com.armitage.server.user.model.Role;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.model.UsrOrgRange2;

public class ActivitiUserUtil {
	public static UserEntity toActivitiUser(Usr usr){  
        UserEntity userEntity = new UserEntity();  
        userEntity.setId(usr.getCode());  
        userEntity.setFirstName(usr.getName());  
        userEntity.setPassword(usr.getPass());  
        userEntity.setEmail(usr.getEmail());  
        userEntity.setRevision(1);  
        return userEntity;  
    }  
  
    public static GroupEntity toActivitiGroup(Role role){  
        GroupEntity groupEntity = new GroupEntity();  
        groupEntity.setRevision(1);  
        groupEntity.setType("assignment");  
        groupEntity.setId(String.valueOf(role.getId()));  
        groupEntity.setName(role.getName());  
        return groupEntity;  
    } 
    
    public static GroupEntity toActivitiGroupOrgUniNo(String orgUnitNo,Role role){  
        GroupEntity groupEntity = new GroupEntity();  
        groupEntity.setRevision(1);  
        groupEntity.setType("assignment");  
        groupEntity.setId(orgUnitNo+"_"+String.valueOf(role.getId()));  
        groupEntity.setName(role.getName());  
        return groupEntity;  
    }
  
    public static List<Group> toActivitiGroups(List<UsrOrgRange2> usrOrgList,List<Role> roles){  
        List<Group> groups = new ArrayList<Group>();  
        for (Role role : roles) {  
            GroupEntity groupEntity = toActivitiGroup(role);  
            groups.add(groupEntity);
            if(usrOrgList != null && !usrOrgList.isEmpty()){
            	for (UsrOrgRange2 usrOrgRange : usrOrgList) { 
            		GroupEntity groupEntity2 = toActivitiGroupOrgUniNo(usrOrgRange.getOrgUnitNo(),role);  
                    groups.add(groupEntity2);
            	}
            }
        }  
        return groups;  
    }
}
