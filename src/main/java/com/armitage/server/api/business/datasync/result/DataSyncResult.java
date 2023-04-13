package com.armitage.server.api.business.datasync.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="DataSyncResult",description="返回结果集resultList")
public class DataSyncResult {
	
    @ApiModelProperty(value="返回单据",dataType="String")
    private String no;
    @ApiModelProperty(value="返回代码",dataType="String")
    private String errCode;
    @ApiModelProperty(value="错误说明",dataType="String")
    private String errText;

    
    
    public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
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
	public DataSyncResult() {
		this.errCode="0";
		this.errText="";
	}
}
