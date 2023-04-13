package com.armitage.server.activity.service;

import javax.annotation.Resource;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomGroupEntityManagerFactory implements SessionFactory{
	
	private CustomGroupEntityManager customGroupEntityManager;
	
	public Class<?> getSessionType() {
		// 返回原始的GroupManager类型
		return GroupIdentityManager.class;
	}
 
	public Session openSession() {
		// 返回自定义的GroupManager实例
		return customGroupEntityManager;
	}
 
	@Autowired
    public void setCustomGroupEntityManager(CustomGroupEntityManager customGroupEntityManager) {
        this.customGroupEntityManager = customGroupEntityManager;
    }
}
