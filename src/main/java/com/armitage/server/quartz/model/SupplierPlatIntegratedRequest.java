package com.armitage.server.quartz.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="integratedRequest",description="请求头")
public class SupplierPlatIntegratedRequest {
    
    @ApiModelProperty(value="终端类型",dataType="String",example="",required=false)
	private String terminal;

    @ApiModelProperty(value="产品代码",dataType="String",example="iSCM",required=false)
	private String productCode;
    
    @ApiModelProperty(value="用户代码",dataType="String",example="bei01",required=true)
    private String userCode;
    
    @ApiModelProperty(value="token",dataType="String",example="5585c636-6b09-48f4-967e-254e0dc99c56",required=true)
    private String securityToken;
    
    @ApiModelProperty(value="供应商Id",dataType="Long",example="23",required=true)
	private long supplierId;
    
    @ApiModelProperty(value="操作用户代码（业务系统的用户代码）",dataType="String",example="001",required=false)
    private String optCode;
    
    @ApiModelProperty(value="客户端计算机名",dataType="String",example="Stephen-PC",required=false)
    private String hostName;
    
    @ApiModelProperty(value="返回提示消息中英文",dataType="String",example="zh-CN",required=false)
    private String lang;        
    
    public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getUserCode() {
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getSecurityToken() {
        return securityToken;
    }
    public void setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }
    public String getOptCode() {
        return optCode;
    }
    public void setOptCode(String optCode) {
        this.optCode = optCode;
    }
    public String getHostName() {
        return hostName;
    }
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
    public long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}
	public String getLang() {
        return lang;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }

}
