package com.armitage.server.api.business.purmarketprice.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@ApiModel(value="PurMarketPriceDetailResult",description="定价单详情返回结果")
public class PurMarketPriceDetailResult {
	@ApiModelProperty(value="行号",dataType="int",example="1")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private int lineId;

	@ApiModelProperty(value="物资编码",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String itemNo;

	@ApiModelProperty(value="物资名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String itemName;

	@ApiModelProperty(value="规格",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String spec;
	
	@ApiModelProperty(value="计量单位",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String purUnit;
	
	@ApiModelProperty(value="计量单位名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String purUnitName;
	
	@ApiModelProperty(value="产地要求",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String placeRequire;
	
	@ApiModelProperty(value="市调价格",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal price;
	
	@ApiModelProperty(value="备注",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String remarks;

	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getPurUnit() {
		return purUnit;
	}

	public void setPurUnit(String purUnit) {
		this.purUnit = purUnit;
	}

	public String getPurUnitName() {
		return purUnitName;
	}

	public void setPurUnitName(String purUnitName) {
		this.purUnitName = purUnitName;
	}

	public String getPlaceRequire() {
		return placeRequire;
	}

	public void setPlaceRequire(String placeRequire) {
		this.placeRequire = placeRequire;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
