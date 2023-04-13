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
@ConditionalOnProperty(name = "mysql.activity.enable",  havingValue = "true")
public class ActivitySourceConfig {

	@Bean(name = "dataSourceActivity", initMethod = "init", destroyMethod = "close")
    @Primary
	@ConfigurationProperties(prefix = "mysql.activity")
    public DataSource dataSourceActivity(){
		return new AtomikosDataSourceBean();
    }
	
	@Bean(name = "sqlSessionFactoryActivity")
    @Primary
	public SqlSessionFactory sqlSessionFactoryActivity(@Qualifier("dataSourceActivity") DataSource dataSource) throws Exception {
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

	@Bean(name = "sqlSessionActivity")
    @Primary
	public SqlSessionTemplate sqlSessionActivity(@Qualifier("sqlSessionFactoryActivity") SqlSessionFactory sqlSessionFactory) throws Exception {
		SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
		template.setId("sqlSessionActivity");
		return template;
	}
	
    protected Resource[] getMapperLocations() {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<String> mapperLocations = new ArrayList();

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
				} );
	}

    @Bean
    @ConditionalOnProperty(name = "activity.uid.enabled", havingValue = "true")
    public Interceptor uidIntercepterActivity(UidGenerator cachedUidGenerator, @Qualifier("sqlSessionFactoryActivity") SqlSessionFactory sqlSessionFactory) {
    	UidIntercepter uidIntercepter = new UidIntercepter();
    	uidIntercepter.setUidGenerator(cachedUidGenerator);
    	sqlSessionFactory.getConfiguration().addInterceptor(uidIntercepter);
        return uidIntercepter;
    }
	
    @Bean
    public OptimisticLocker lockerActivity(@Qualifier("sqlSessionFactoryActivity") SqlSessionFactory sqlSessionFactory) {
        OptimisticLocker lockerActivity = new OptimisticLocker();
        // 不设置versionColumn，默认为version，而spring boot方式1.2-RELEASE必须要手动指定乐观锁字段列名，1.2-RELEASE之后可以不用指定，默认为version
        Properties props = new Properties();
        props.setProperty("versionColumn", "lockVersion");
        lockerActivity.setProperties(props);
        sqlSessionFactory.getConfiguration().addInterceptor(lockerActivity);
        return lockerActivity;
    }
}