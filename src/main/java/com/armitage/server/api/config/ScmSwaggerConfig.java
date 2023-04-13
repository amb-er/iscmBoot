/* 
* 2015-11-21 
* MySwaggerConfig.java 
* author:Zack Chan
*/ 
package com.armitage.server.api.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.armitage.server.api.common.ApiVersion;
import com.google.common.base.Predicate;

@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enable",  havingValue = "true")
public class ScmSwaggerConfig
{

	/**
	 * 定义API组，
	 */
	@Bean
	public Docket mobileApi() {
		
		return new Docket(DocumentationType.SWAGGER_2).groupName("移动API")
				.genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false).forCodeGeneration(true)
				.select().apis(new Predicate<RequestHandler>() {
					@Override
					public boolean apply(RequestHandler input) {
						ApiVersion apiVersion = input.getHandlerMethod()
								.getMethodAnnotation(ApiVersion.class);
						if (apiVersion != null) {
							List<String> groupList = Arrays.asList(apiVersion.group());
							if (groupList != null && !groupList.isEmpty()) {
								for (String group : groupList) {
									if (group.contains("mobileApi"))
										return true;
								}
							}
						}
						return false;
					}
				}).paths(PathSelectors.any()).build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
				.apiInfo(apiInfo("移动端API接口","提供给供应链移动端的API接口文档","1.0"));
	}
	
	@Bean
	public Docket iespApi() {
		
		return new Docket(DocumentationType.SWAGGER_2).groupName("工单接口API")
				.genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false).forCodeGeneration(true)
				.select().apis(new Predicate<RequestHandler>() {
					@Override
					public boolean apply(RequestHandler input) {
						ApiVersion apiVersion = input.getHandlerMethod()
								.getMethodAnnotation(ApiVersion.class);
						if (apiVersion != null) {
							List<String> groupList = Arrays.asList(apiVersion.group());
							if (groupList != null && !groupList.isEmpty()) {
								for (String group : groupList) {
									if (group.contains("iespApi"))
										return true;
								}
							}
						}
						return false;
					}
				}).paths(PathSelectors.any()).build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
				.apiInfo(apiInfo("iESP API接口","提供给iESP的API接口文档","1.0"));
	}
	
	@Bean
	public Docket oaApi() {
		
		return new Docket(DocumentationType.SWAGGER_2).groupName("康帝OA API")
				.genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false).forCodeGeneration(true)
				.select().apis(new Predicate<RequestHandler>() {
					@Override
					public boolean apply(RequestHandler input) {
						ApiVersion apiVersion = input.getHandlerMethod()
								.getMethodAnnotation(ApiVersion.class);
						if (apiVersion != null) {
							List<String> groupList = Arrays.asList(apiVersion.group());
							if (groupList != null && !groupList.isEmpty()) {
								for (String group : groupList) {
									if (group.contains("oaApi"))
										return true;
								}
							}
						}
						return false;
					}
				}).paths(PathSelectors.any()).build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
				.apiInfo(apiInfo("OA API接口","提供给OA系统的API接口文档","1.0"));
	}
	
	@Bean
	public Docket finApi() {
		
		return new Docket(DocumentationType.SWAGGER_2).groupName("财务接口API")
				.genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false).forCodeGeneration(true)
				.select().apis(new Predicate<RequestHandler>() {
					@Override
					public boolean apply(RequestHandler input) {
						ApiVersion apiVersion = input.getHandlerMethod()
								.getMethodAnnotation(ApiVersion.class);
						if (apiVersion != null) {
							List<String> groupList = Arrays.asList(apiVersion.group());
							if (groupList != null && !groupList.isEmpty()) {
								for (String group : groupList) {
									if (group.contains("finApi"))
										return true;
								}
							}
						}
						return false;
					}
				}).paths(PathSelectors.any()).build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
				.apiInfo(apiInfo("Fin API接口","提供给财务系统的API接口文档","1.0"));
	}
	
	@Bean
	public Docket supplierPlatApi() {
		
		return new Docket(DocumentationType.SWAGGER_2).groupName("供应商平台API")
				.genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false).forCodeGeneration(true)
				.select().apis(new Predicate<RequestHandler>() {
					@Override
					public boolean apply(RequestHandler input) {
						ApiVersion apiVersion = input.getHandlerMethod()
								.getMethodAnnotation(ApiVersion.class);
						if (apiVersion != null) {
							List<String> groupList = Arrays.asList(apiVersion.group());
							if (groupList != null && !groupList.isEmpty()) {
								for (String group : groupList) {
									if (group.contains("supplierPlatApi"))
										return true;
								}
							}
						}
						return false;
					}
				}).paths(PathSelectors.any()).build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
				.apiInfo(apiInfo("供应商平台API","提供给供应商平台的API接口文档","1.0"));
	}
	
	@Bean
	public Docket dataSyncApi() {
		
		return new Docket(DocumentationType.SWAGGER_2).groupName("数据同步接口API")
				.genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false).forCodeGeneration(true)
				.select().apis(new Predicate<RequestHandler>() {
					@Override
					public boolean apply(RequestHandler input) {
						ApiVersion apiVersion = input.getHandlerMethod()
								.getMethodAnnotation(ApiVersion.class);
						if (apiVersion != null) {
							List<String> groupList = Arrays.asList(apiVersion.group());
							if (groupList != null && !groupList.isEmpty()) {
								for (String group : groupList) {
									if (group.contains("dataSyncApi"))
										return true;
								}
							}
						}
						return false;
					}
				}).paths(PathSelectors.any()).build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
				.apiInfo(apiInfo("dataSync API接口","提供给外部系统数据对接的API接口文档","1.0"));
	}
	private ApiInfo apiInfo(String title, String description, String version){
		return new ApiInfoBuilder()
				.title(title)
				// 大标题
				.description(description)
				// 详细描述
				.version(version)
				// 版本
				.contact("冯小青")
				// 作者
				.license("The Armitage License, Version 1.0")
				.build();
	}
	
	private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList= new ArrayList<ApiKey>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeyList;
    }
    
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts=new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build());
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences=new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }
}
