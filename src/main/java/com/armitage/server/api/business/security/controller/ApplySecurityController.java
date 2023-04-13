package com.armitage.server.api.business.security.controller;


import javax.servlet.http.HttpServletRequest;

import com.armitage.server.api.common.ApiVersion;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.armitage.server.webservice.SecurityService;
import com.armitage.server.webservice.impl.SecurityServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api")
@Api(tags="公共安全接口")
public class ApplySecurityController {
    private SecurityService securityService = new SecurityServiceImpl();
	@ApiVersion(group="mobileApi,oaApi,iespApi,finApi,supplierPlatApi,dataSyncApi")
    @ResponseBody
    @RequestMapping(value = "/applySecurityToken", method = RequestMethod.POST)
    @ApiOperation(value = "获取必须的securityToken")
    @ApiImplicitParams({
        @ApiImplicitParam(name="userCode", paramType="query",value="授权用户代码",required = true,dataType="String"), 
        @ApiImplicitParam(name="key", paramType="query",value="用户密码",required = true,dataType="String")
    })
    public String applySecurityToken(@RequestParam String userCode, @RequestParam String key, HttpServletRequest request) {
        String securityToken =null;
        
        if (StringUtils.isNotBlank(key)) { 
            key = DigestUtils.md5Hex(key); 
            key = DigestUtils.md5Hex(userCode + key);
        }
        
        securityToken = securityService.applySecurityToken(
                userCode, key);
        System.out.println("生成的token:" + securityToken);
        return securityToken;
    }
}
