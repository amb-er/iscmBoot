
package com.armitage.server.iscm.purchasemanage.pricemanage.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
public class ScmPurPriceAdd {
	 
    private String pmNo;
    private String orgUnitNo;
    private Date pmDate;
    private long groupId1;
    private long groupId2;
    private long groupId3;
    private long vendorId1;
    private long vendorId2;
    private long vendorId3;
    private long selVndrId;
    private String priceName;
    private boolean inclueTax;
    private String currencyNo;
    private BigDecimal exchangeRate;
    private String creator;
    private Date createDate;
    private String editor;
    private Date editDate;
    private String checker;
    private Date checkDate;
    private int prtcount;
    private String status;
    private String controlUnitNo;

    public String getPmNo() {
        return pmNo;
    }

    public void setPmNo(String val) {
        this.pmNo = val;
    }
    public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public Date getPmDate() {
        return pmDate;
    }

    public void setPmDate(Date val) {
        this.pmDate = val;
    }

    public long getGroupId1() {
		return groupId1;
	}

	public void setGroupId1(long groupId1) {
		this.groupId1 = groupId1;
	}

	public long getGroupId2() {
		return groupId2;
	}

	public void setGroupId2(long groupId2) {
		this.groupId2 = groupId2;
	}

	public long getGroupId3() {
		return groupId3;
	}

	public void setGroupId3(long groupId3) {
		this.groupId3 = groupId3;
	}

	public long getVendorId1() {
        return vendorId1;
    }

    public void setVendorId1(long val) {
        this.vendorId1 = val;
    }
    public long getVendorId2() {
        return vendorId2;
    }

    public void setVendorId2(long val) {
        this.vendorId2 = val;
    }
    public long getVendorId3() {
        return vendorId3;
    }

    public void setVendorId3(long val) {
        this.vendorId3 = val;
    }
    public long getSelVndrId() {
        return selVndrId;
    }

    public void setSelVndrId(long val) {
        this.selVndrId = val;
    }
    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String val) {
        this.priceName = val;
    }
    public boolean isInclueTax() {
		return inclueTax;
	}

	public void setInclueTax(boolean inclueTax) {
		this.inclueTax = inclueTax;
	}

	public String getCurrencyNo() {
        return currencyNo;
    }

    public void setCurrencyNo(String val) {
        this.currencyNo = val;
    }
    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal val) {
        this.exchangeRate = val;
    }
    
    public String getCreator() {
        return creator;
    }

    public void setCreator(String val) {
        this.creator = val;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date val) {
        this.createDate = val;
    }
    public String getEditor() {
        return editor;
    }

    public void setEditor(String val) {
        this.editor = val;
    }
    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date val) {
        this.editDate = val;
    }
    public String getChecker() {
        return checker;
    }

    public void setChecker(String val) {
        this.checker = val;
    }
    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date val) {
        this.checkDate = val;
    }
    public int getPrtcount() {
        return prtcount;
    }

    public void setPrtcount(int val) {
        this.prtcount = val;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String val) {
        this.status = val;
    }
    public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    private String pqNo;
	private String vendorCode;
	private String buyerCode;
	private Date pqDate;
	private Date begDate;
	private Date endDate;
	private String remarks;
	private List<ScmPurQuotaionDetail> detailList;
	
	public String getPqNo() {
		return pqNo;
	}
	public void setPqNo(String pqNo) {
		this.pqNo = pqNo;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getBuyerCode() {
		return buyerCode;
	}
	public void setBuyerCode(String buyerCode) {
		this.buyerCode = buyerCode;
	}
	public Date getPqDate() {
		return pqDate;
	}
	public void setPqDate(Date pqDate) {
		this.pqDate = pqDate;
	}
	public Date getBegDate() {
		return begDate;
	}
	public void setBegDate(Date begDate) {
		this.begDate = begDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<ScmPurQuotaionDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<ScmPurQuotaionDetail> detailList) {
		this.detailList = detailList;
	}


}
