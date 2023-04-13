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
@ConditionalOnProperty(name = "mysql.ifbm.enable",  havingValue = "true")
public class IfbmSourceConfig {

	@Bean(name = "dataSourceIfbm", initMethod = "init", destroyMethod = "close")
    @Primary
	@ConfigurationProperties(prefix = "mysql.ifbm")
    public DataSource dataSourceIfbm(){
		return new AtomikosDataSourceBean();
    }
	
	@Bean(name = "sqlSessionFactoryIfbm")
    @Primary
	public SqlSessionFactory sqlSessionFactoryIfbm(@Qualifier("dataSourceIfbm") DataSource dataSource) throws Exception {
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

	@Bean(name = "sqlSessionIfbm")
    @Primary
	public SqlSessionTemplate sqlSessionIfbm(@Qualifier("sqlSessionFactoryIfbm") SqlSessionFactory sqlSessionFactory) throws Exception {
		SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
		template.setId("sqlSessionIfbm");
		return template;
	}
	
    protected Resource[] getMapperLocations() {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<String> mapperLocations = new ArrayList();
        mapperLocations.add("classpath:/com/armitage/server/ifbm/dao/mapper/**.xml");
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
				"com.armitage.server.ifbm.model",
				} );
	}

    @Bean
    @ConditionalOnProperty(name = "ifbm.uid.enabled", havingValue = "true")
    public Interceptor uidIntercepterIfbm(UidGenerator cachedUidGenerator, @Qualifier("sqlSessionFactoryIfbm") SqlSessionFactory sqlSessionFactory) {
    	UidIntercepter uidIntercepter = new UidIntercepter();
    	uidIntercepter.setUidGenerator(cachedUidGenerator);
    	sqlSessionFactory.getConfiguration().addInterceptor(uidIntercepter);
        return uidIntercepter;
    }
	
    @Bean
    public OptimisticLocker locker(@Qualifier("sqlSessionFactoryIfbm") SqlSessionFactory sqlSessionFactory) {
        OptimisticLocker locker = new OptimisticLocker();
        // 不设置versionColumn，默认为version，而spring boot方式1.2-RELEASE必须要手动指定乐观锁字段列名，1.2-RELEASE之后可以不用指定，默认为version
        Properties props = new Properties();
        props.setProperty("versionColumn", "lockVersion");
        locker.setProperties(props);
        sqlSessionFactory.getConfiguration().addInterceptor(locker);
        return locker;
    }
}