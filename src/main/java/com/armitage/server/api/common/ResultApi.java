package com.armitage.server.api.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="业务请求",description="返回请求结果")
public class ResultApi {
    @ApiModelProperty(value="返回代码",dataType="String")
    private String errCode;
    @ApiModelProperty(value="错误说明",dataType="String")
    private String errText;

    public String getErrCode() {
        return errCode;
    }
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
    public String getErrText() {
        return errText;
    }
    public void setErrText(String errText) {
        this.errText = errText;
    }
	public ResultApi() {
		this.errCode="0";
		this.errText="";
	}

}
