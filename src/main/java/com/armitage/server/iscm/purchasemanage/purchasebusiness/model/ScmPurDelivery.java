package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
 
@XmlRootElement(name = "scmPurDelivery")  
public class ScmPurDelivery extends BaseModel  {
	private String reqFinOrgUnitNo;
    private String reqOrgUnitNo;
    private String reqOrgUnitName;
    private long vendorId;
    private String vendorName;
    private long itemId;
    private String itemName;
    private long purUnit;
    private String unitName;
    private BigDecimal qty;
    private String remarks;
    private Date reqDate;
    private String begReqDate;
    private String endReqDate;
    private String spec;

    public String getReqFinOrgUnitNo() {
		return reqFinOrgUnitNo;
	}
	public void setReqFinOrgUnitNo(String reqFinOrgUnitNo) {
		this.reqFinOrgUnitNo = reqFinOrgUnitNo;
	}
	public String getReqOrgUnitNo() {
		return reqOrgUnitNo;
	}
	public void setReqOrgUnitNo(String reqOrgUnitNo) {
		this.reqOrgUnitNo = reqOrgUnitNo;
	}
	public String getReqOrgUnitName() {
		return reqOrgUnitName;
	}
	public void setReqOrgUnitName(String reqOrgUnitName) {
		this.reqOrgUnitName = reqOrgUnitName;
	}
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public long getPurUnit() {
		return purUnit;
	}
	public void setPurUnit(long purUnit) {
		this.purUnit = purUnit;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getReqDate() {
		return reqDate;
	}
	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}
	public String getBegReqDate() {
		return begReqDate;
	}
	public void setBegReqDate(String begReqDate) {
		this.begReqDate = begReqDate;
	}
	public String getEndReqDate() {
		return endReqDate;
	}
	public void setEndReqDate(String endReqDate) {
		this.endReqDate = endReqDate;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public ScmPurDelivery(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmPurDelivery() {

	}
  	@Override
	public String getPkKey() {
		return null;
	}

	@Override
	public long getPK() {
		return 0;
	}

	@Override
	public String[] getRequiredFields() {
		return null;
	}

	@Override
	public String[] getFieldNames() {
		return null;
	}

	@Override
	public List<String[]> getUniqueKeys() {
		return null;
	}

}
