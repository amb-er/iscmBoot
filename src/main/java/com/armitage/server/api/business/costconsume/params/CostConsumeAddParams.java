package com.armitage.server.api.business.costconsume.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

@ApiModel(value="CostConsumeAddParams",description="新增参数对象")
public class CostConsumeAddParams {
	@ApiModelProperty(value="耗用部门",dataType="String",example="123",required=true)
	private String useDeptNo;
	
	@ApiModelProperty(value="业务日期",dataType="Date",example="2019-05-22 00:00:00",required=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date bizDate;
	
	@ApiModelProperty(value="备注",dataType="String",example="123",required=false)
	private String remarks;
	
	@ApiModelProperty(value="请求号",dataType="String",example="调用方生成的唯一号",required=false)
	private String refNo;
	
	@ApiModelProperty(value="单据明细",dataType="List<CostConsumeDetailParams>",example="",required=true)
	private List<CostConsumeDetailParams> detailList;

	public String getUseDeptNo() {
		return useDeptNo;
	}

	public void setUseDeptNo(String useDeptNo) {
		this.useDeptNo = useDeptNo;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public List<CostConsumeDetailParams> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<CostConsumeDetailParams> detailList) {
		this.detailList = detailList;
	}

}
