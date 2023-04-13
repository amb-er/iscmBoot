package com.armitage.server.config;

import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.base.plugin.PagePlugin;
import com.armitage.server.common.uid.intercepter.UidIntercepter;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.github.dreamroute.locker.interceptor.OptimisticLocker;
import com.xfvape.uid.UidGenerator;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Configuration
@ConditionalOnProperty(name = "mysql.iscm.enable",  havingValue = "true")
public class IscmDataSourceConfig {

	@Bean(name = "dataSourceIscm", initMethod = "init", destroyMethod = "close")
	@Primary
	@ConfigurationProperties(prefix = "mysql.iscm")
    public DataSource dataSourceIscm(){
		System.out.println("数据库连接");
		return new AtomikosDataSourceBean();
    }
	
	@Bean(name = "sqlSessionFactoryIscm")
	@Primary
	public SqlSessionFactory sqlSessionFactoryIscm(@Qualifier("dataSourceIscm") DataSource dataSource) throws Exception {
        //配置myabtisSqlSession
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		// 分页插件
		PagePlugin pagePlugin = new PagePlugin();
		// 添加插件
		bean.setPlugins(new Interceptor[] { pagePlugin });
		// 映射文件
		bean.setMapperLocations(getMapperLocations());
		// 实体类
		bean.setTypeAliasesPackage(getTypeAliasesPackage());
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        // 配置打印sql语句
        mybatisConfiguration.setLogImpl(StdOutImpl.class);
        bean.setConfiguration(mybatisConfiguration);
		return bean.getObject();
	}

	@Bean(name = "sqlSessionIscm")
	@Primary
	public SqlSessionTemplate sqlSessionIscm(@Qualifier("sqlSessionFactoryIscm") SqlSessionFactory sqlSessionFactory) throws Exception {
		SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
		template.setId("sqlSessionIscm");
		return template;
	}
	
    protected Resource[] getMapperLocations() {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<String> mapperLocations = new ArrayList();
        mapperLocations.add("classpath:/com/armitage/server/iscm/basedata/dao/mapper/**.xml");
        mapperLocations.add("classpath:/com/armitage/server/iscm/purchasemanage/purchasesetting/dao/mapper/**.xml");
        mapperLocations.add("classpath:/com/armitage/server/iscm/purchasemanage/purchasebusiness/dao/mapper/**.xml");
        mapperLocations.add("classpath:/com/armitage/server/iscm/purchasemanage/pricemanage/dao/mapper/**.xml");
        mapperLocations.add("classpath:/com/armitage/server/iscm/inventorymanage/inventorysetting/dao/mapper/**.xml");
        mapperLocations.add("classpath:/com/armitage/server/iscm/inventorymanage/warehouseinbusiness/dao/mapper/**.xml");
        mapperLocations.add("classpath:/com/armitage/server/iscm/inventorymanage/warehouseoutbusiness/dao/mapper/**.xml");
        mapperLocations.add("classpath:/com/armitage/server/iscm/inventorymanage/cstbusiness/dao/mapper/**.xml");
        mapperLocations.add("classpath:/com/armitage/server/iscm/inventorymanage/AllocationApplication/dao/mapper/**.xml");
        mapperLocations.add("classpath:/com/armitage/server/iscm/inventorymanage/inventorydata/dao/mapper/**.xml");
        mapperLocations.add("classpath:/com/armitage/server/iscm/inventorymanage/inventoryinitialization/dao/mapper/**.xml");
        mapperLocations.add("classpath:/com/armitage/server/iscm/report/purchase/dao/mapper/**.xml");
        mapperLocations.add("classpath:/com/armitage/server/iscm/report/inventory/dao/mapper/**.xml");
        mapperLocations.add("classpath:/com/armitage/server/iscm/inventorymanage/internaltrans/dao/mapper/**.xml");
        mapperLocations.add("classpath:/com/armitage/server/iscm/report/costcenter/dao/mapper/**.xml");
        mapperLocations.add("classpath:/com/armitage/server/iscm/report/common/dao/mapper/**.xml");
        mapperLocations.add("classpath:/Com/armitage/server/iscm/common/dao/mapper/**.xml");
        mapperLocations.add("classpath:/com/armitage/server/ifbc/costcard/dao/mapper/**.xml");
        mapperLocations.add("classpath:/com/armitage/server/ifbc/rptdata/dao/mapper/**.xml");
        mapperLocations.add("classpath:/com/armitage/server/quartz/dao/mapper/**.xml");


        List<Resource> resources = new ArrayList();
        if (!mapperLocations.isEmpty()) {
            for (String mapperLocation : mapperLocations) {
                try {
                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (IOException e) {
                	e.printStackTrace();
                }
            }
        }

        return resources.toArray(new Resource[resources.size()]);
    }
    
    protected String getTypeAliasesPackage() {
		return StringUtils.arrayToCommaDelimitedString(new String[] {
				"com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model",
                "com.armitage.server.iscm.inventorymanage.AllocationApplication.model",
                "com.armitage.server.iscm.basedata.model",
                "com.armitage.server.iscm.purchasemanage.purchasesetting.model",
                "com.armitage.server.iscm.purchasemanage.pricemanage.model",
                "com.armitage.server.iscm.purchasemanage.purchasebusiness.model",
                "com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model",
                "com.armitage.server.iscm.inventorymanage.inventorysetting.model",
                "com.armitage.server.iscm.inventorymanage.cstbusiness.model",
                "com.armitage.server.iscm.inventorymanage.inventorydata.model",
                "com.armitage.server.iscm.inventorymanage.inventoryinitialization.model",
                "com.armitage.server.iscm.inventorymanage.internaltrans.model",
                "com.armitage.server.iscm.report.purchase.model",
                "com.armitage.server.iscm.report.inventory.model",
                "com.armitage.server.iscm.report.costcenter.model",
                "com.armitage.server.iscm.common.model",
                "com.armitage.server.ifbc.costcard.model",
                "com.armitage.server.ifbc.rptdata.model",
                "com.armitage.server.quartz.model"
				} );
	}

    @Bean
    @ConditionalOnProperty(name = "iscm.uid.enabled", havingValue = "true")
    public Interceptor uidIntercepterIscm(UidGenerator cachedUidGenerator, @Qualifier("sqlSessionFactoryIscm") SqlSessionFactory sqlSessionFactory) {
    	UidIntercepter uidIntercepter = new UidIntercepter();
    	uidIntercepter.setUidGenerator(cachedUidGenerator);
    	sqlSessionFactory.getConfiguration().addInterceptor(uidIntercepter);
        return uidIntercepter;
    }
	
    @Bean
    public OptimisticLocker lockerIscm(@Qualifier("sqlSessionFactoryIscm") SqlSessionFactory sqlSessionFactory) {
        OptimisticLocker lockerIscm = new OptimisticLocker();
        // 不设置versionColumn，默认为version，而spring boot方式1.2-RELEASE必须要手动指定乐观锁字段列名，1.2-RELEASE之后可以不用指定，默认为version
        Properties props = new Properties();
        props.setProperty("versionColumn", "lockVersion");
        lockerIscm.setProperties(props);
        sqlSessionFactory.getConfiguration().addInterceptor(lockerIscm);
        return lockerIscm;
    }
}