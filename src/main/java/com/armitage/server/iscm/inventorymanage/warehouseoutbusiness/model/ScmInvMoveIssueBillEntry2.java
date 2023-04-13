package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

public class ScmInvMoveIssueBillEntry2 extends ScmInvMoveIssueBillEntry {
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_GROUPNAME = "groupName";
	public static final String FN_UNITNAME = "unitName";
	public static final String FN_CHOOSED = "choosed";
	public static final String FN_CONVRATE = "convrate";
	public static final String FN_BASEUNITNAME = "baseUnitName";
	public static final String FN_TAXRATESTR = "taxRateStr";
	public static final String FN_SOURCELINEID = "sourceLineId";
	public static final String FN_RECEIPTWAREHOUSEID = "receiptWareHouseId";
	public static final String FN_USEORGUNITNO = "useOrgUnitNo";
	public static final String FN_RECEIVEORGUNITNO = "receiveOrgUnitNo";
	public static final String FN_INVQTY ="invQty";
	public static final String FN_BIZTYPE ="bizType";
	public static final String FN_CREATOR ="creator";
	public static final String FN_WHNAME = "whName";
	
    private String otNo;
    private String bizType;
    private Date bizDate;
	private Date postDate;
	private String status;
    private String creator;
	private String itemNo;
	private String itemName;
	private String spec;
	private String groupName;
	private String unitName;
	private boolean choosed;
	private BigDecimal convrate;
	private String baseUnitName;
	private String pieUnitName;
	private String taxRateStr;
	private String className;
	private long brandId;
	private String brandName;
	
	public String getWhName() {
		return whName;
	}

	public void setWhName(String whName) {
		this.whName = whName;
	}

	private long sourceLineId;
	private long receiptWareHouseId;
    private String useOrgUnitNo;
    private String receiveOrgUnitNo;
    private String whName;
    private BigDecimal invQty;
	
	public String getOtNo() {
		return otNo;
	}

	public void setOtNo(String otNo) {
		this.otNo = otNo;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public boolean isChoosed() {
		return choosed;
	}

	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}

	public BigDecimal getConvrate() {
		return convrate;
	}

	public void setConvrate(BigDecimal convrate) {
		this.convrate = convrate;
	}

	public String getBaseUnitName() {
		return baseUnitName;
	}

	public void setBaseUnitName(String baseUnitName) {
		this.baseUnitName = baseUnitName;
	}

	public String getTaxRateStr() {
        return taxRateStr;
    }

    public void setTaxRateStr(String taxRateStr) {
        this.taxRateStr = taxRateStr;
    }

    public long getSourceLineId() {
        return sourceLineId;
    }

    public void setSourceLineId(long sourceLineId) {
        this.sourceLineId = sourceLineId;
    }

    public long getReceiptWareHouseId() {
        return receiptWareHouseId;
    }

    public void setReceiptWareHouseId(long receiptWareHouseId) {
        this.receiptWareHouseId = receiptWareHouseId;
    }

    public String getUseOrgUnitNo() {
        return useOrgUnitNo;
    }

    public void setUseOrgUnitNo(String useOrgUnitNo) {
        this.useOrgUnitNo = useOrgUnitNo;
    }

    public String getReceiveOrgUnitNo() {
        return receiveOrgUnitNo;
    }

    public void setReceiveOrgUnitNo(String receiveOrgUnitNo) {
        this.receiveOrgUnitNo = receiveOrgUnitNo;
    }

    public BigDecimal getInvQty() {
        return invQty;
    }

    public void setInvQty(BigDecimal invQty) {
        this.invQty = invQty;
    }

    public String getPieUnitName() {
		return pieUnitName;
	}

	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public long getBrandId() {
		return brandId;
	}

	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public ScmInvMoveIssueBillEntry2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			this.convrate=BigDecimal.ZERO;
			this.choosed = false;
			this.taxRateStr="0%";
			this.sourceLineId=0;
		}
	}

	public ScmInvMoveIssueBillEntry2() {
		super();
	}
}
