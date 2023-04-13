package com.armitage.server.api.business.basedata.result;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierOAResult",description="返回结果集resultList")
public class SupplierOAResult {
	@ApiModelProperty(value="供应商ID",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long supplierId;
	
	@ApiModelProperty(value="使用酒店",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String syjd;

	@ApiModelProperty(value="供应商审批类型",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String gyssplx;
	
	@ApiModelProperty(value="供货类别",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String ghlb2;
	
	@ApiModelProperty(value="供应商全称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String xzgysqc;
	
	@ApiModelProperty(value="供应商联系人",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String gyslxr;
	
	@ApiModelProperty(value="供应商纳税人识别号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String gysnsrsbh;
	
	@ApiModelProperty(value="供应商收款账号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String gysskzh;
	
	@ApiModelProperty(value="供应商开户行",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String gyskhx;
	
	@ApiModelProperty(value="发票类型",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String fplx;
	
	@ApiModelProperty(value="税率",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String sl;
	
	@ApiModelProperty(value="供应商资质",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String gyszz;
	
	@ApiModelProperty(value="事情描述",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String sqms;

	public String getGyssplx() {
		return gyssplx;
	}

	public void setGyssplx(String gyssplx) {
		this.gyssplx = gyssplx;
	}

	public String getGhlb2() {
		return ghlb2;
	}

	public void setGhlb2(String ghlb2) {
		this.ghlb2 = ghlb2;
	}

	public String getGyslxr() {
		return gyslxr;
	}

	public void setGyslxr(String gyslxr) {
		this.gyslxr = gyslxr;
	}

	public String getGysnsrsbh() {
		return gysnsrsbh;
	}

	public void setGysnsrsbh(String gysnsrsbh) {
		this.gysnsrsbh = gysnsrsbh;
	}

	public String getGysskzh() {
		return gysskzh;
	}

	public void setGysskzh(String gysskzh) {
		this.gysskzh = gysskzh;
	}


	public String getGyskhx() {
		return gyskhx;
	}

	public void setGyskhx(String gyskhx) {
		this.gyskhx = gyskhx;
	}

	public String getFplx() {
		return fplx;
	}

	public void setFplx(String fplx) {
		this.fplx = fplx;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getGyszz() {
		return gyszz;
	}

	public void setGyszz(String gyszz) {
		this.gyszz = gyszz;
	}

	public String getSqms() {
		return sqms;
	}

	public void setSqms(String sqms) {
		this.sqms = sqms;
	}

	public String getXzgysqc() {
		return xzgysqc;
	}

	public void setXzgysqc(String xzgysqc) {
		this.xzgysqc = xzgysqc;
	}

	public String getSyjd() {
		return syjd;
	}

	public void setSyjd(String syjd) {
		this.syjd = syjd;
	}

	public long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}
	public SupplierOAResult(boolean defaultValue) {
		if (defaultValue) {
			this.gyssplx="0";
			this.fplx = "0";
			this.syjd = "5";
		}
	}
}
