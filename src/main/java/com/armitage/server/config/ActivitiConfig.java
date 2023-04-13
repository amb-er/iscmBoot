package com.armitage.server.config;

import org.activiti.engine.*;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class ActivitiConfig {

    @Resource
    private PlatformTransactionManager platformTransactionManager;

    @Resource(name = "dataSourceActivity")
    private DataSource dataSourceActivity;

    /**
     * 流程引擎配置对象
     * @return
     */
    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration(){
        SpringProcessEngineConfiguration processEngineConfiguration=new SpringProcessEngineConfiguration();
        processEngineConfiguration.setDataSource(dataSourceActivity);
        processEngineConfiguration.setTransactionManager(platformTransactionManager);
        processEngineConfiguration.setDatabaseSchemaUpdate("true");
        processEngineConfiguration.setActivityFontName("微软雅黑");
        processEngineConfiguration.setLabelFontName("微软雅黑");
        return processEngineConfiguration;
    }

    /**
     * 流程引擎对象的bean
     * @param processEngineConfiguration
     * @return
     */
    @Bean
    public ProcessEngineFactoryBean processEngine(@Qualifier("processEngineConfiguration") SpringProcessEngineConfiguration processEngineConfiguration){
        ProcessEngineFactoryBean processEngineFactoryBean=new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration(processEngineConfiguration);
        return processEngineFactoryBean;
    }

    //以下几个为activiti的服务组件对应的bean
    @Bean
    public RepositoryService repositoryService(@Qualifier("processEngine") ProcessEngine processEngine){
        return processEngine.getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService(@Qualifier("processEngine") ProcessEngine processEngine){
        return processEngine.getRuntimeService();
    }

    @Bean
    public TaskService taskService(@Qualifier("processEngine") ProcessEngine processEngine){
        return processEngine.getTaskService();
    }

    @Bean
    public IdentityService identityService(@Qualifier("processEngine") ProcessEngine processEngine){
        return processEngine.getIdentityService();
    }

    @Bean
    public HistoryService historyService(@Qualifier("processEngine") ProcessEngine processEngine){
        return processEngine.getHistoryService();
    }

    @Bean
    public ManagementService managementService(@Qualifier("processEngine") ProcessEngine processEngine){
        return processEngine.getManagementService();
    }


}
