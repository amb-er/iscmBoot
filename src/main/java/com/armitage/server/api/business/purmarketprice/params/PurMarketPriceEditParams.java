package com.armitage.server.api.business.purmarketprice.params;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurMarketPriceEditParams",description="市调单修改参数对象")
public class PurMarketPriceEditParams {
	@ApiModelProperty(value="市调单号",dataType="String",example="123",required=true)
	private String piNo;

	@ApiModelProperty(value="市调日期",dataType="Date",example="2019-05-22 00:00:00",required=false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date piDate;
	
	@ApiModelProperty(value="备注",dataType="String",example="123",required=true)
	private String remarks;
	
	@ApiModelProperty(value="单据明细",dataType="String",example="123",required=true)
	private List<PurMarketPriceDetailParams> detailList;

	public String getPiNo() {
		return piNo;
	}

	public void setPiNo(String piNo) {
		this.piNo = piNo;
	}

	public Date getPiDate() {
		return piDate;
	}

	public void setPiDate(Date piDate) {
		this.piDate = piDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<PurMarketPriceDetailParams> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<PurMarketPriceDetailParams> detailList) {
		this.detailList = detailList;
	}
	
}
