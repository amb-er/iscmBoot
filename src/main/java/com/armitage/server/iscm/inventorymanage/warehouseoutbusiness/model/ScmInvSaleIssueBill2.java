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
 

public class ScmInvSaleIssueBill2 extends ScmInvSaleIssueBill {
	public static final String FN_AMT = "Amt";
	public static final String FN_NETAMT = "netAmt";
	public static final String FN_NETSALEAMT = "netSaleAmt";
	public static final String FN_ORGAMT = "orgAmt";
	public static final String FN_TAXAMT = "taxAmt";
	public static final String FN_SALETAXAMT = "saleTaxAmt";
	public static final String FN_TASKID = "taskId";
    public static final String FN_FREEITEM = "freeItem";
    public static final String FN_PENDINGUSR = "pendingUsr";
	public static final String FN_PENDINGUSRNAME = "pendingUsrName";
	
	private BigDecimal amt;
	private BigDecimal netAmt;
	private BigDecimal netSaleAmt;
	private BigDecimal orgAmt;
	private boolean	delBySaleOrder;
	private BigDecimal taxAmt;
	private BigDecimal saleTaxAmt;
	private long taskId;
    private boolean freeItem;
    private String bizTypeName;
    private String orgUnitName;
    private String creatorName;
    private String statusName;
    private String pendingUsr;
	private String pendingUsrName;
    private List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList;
	
	public BigDecimal getNetAmt() {
		return netAmt;
	}

	public void setNetAmt(BigDecimal netAmt) {
		this.netAmt = netAmt;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getNetSaleAmt() {
        return netSaleAmt;
    }

    public void setNetSaleAmt(BigDecimal netSaleAmt) {
        this.netSaleAmt = netSaleAmt;
    }

    public BigDecimal getOrgAmt() {
        return orgAmt;
    }

    public void setOrgAmt(BigDecimal orgAmt) {
        this.orgAmt = orgAmt;
    }

    public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getSaleTaxAmt() {
		return saleTaxAmt;
	}

	public void setSaleTaxAmt(BigDecimal saleTaxAmt) {
		this.saleTaxAmt = saleTaxAmt;
	}

	public boolean isDelBySaleOrder() {
		return delBySaleOrder;
	}

	public void setDelBySaleOrder(boolean delBySaleOrder) {
		this.delBySaleOrder = delBySaleOrder;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public boolean isFreeItem() {
		return freeItem;
	}

	public void setFreeItem(boolean freeItem) {
		this.freeItem = freeItem;
	}

	public String getBizTypeName() {
		return bizTypeName;
	}

	public void setBizTypeName(String bizTypeName) {
		this.bizTypeName = bizTypeName;
	}

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public List<ScmInvSaleIssueBillEntry2> getScmInvSaleIssueBillEntryList() {
		return scmInvSaleIssueBillEntryList;
	}

	public void setScmInvSaleIssueBillEntryList(List<ScmInvSaleIssueBillEntry2> scmInvSaleIssueBillEntryList) {
		this.scmInvSaleIssueBillEntryList = scmInvSaleIssueBillEntryList;
	}

	public String getPendingUsr() {
		return pendingUsr;
	}

	public void setPendingUsr(String pendingUsr) {
		this.pendingUsr = pendingUsr;
	}

	public String getPendingUsrName() {
		return pendingUsrName;
	}

	public void setPendingUsrName(String pendingUsrName) {
		this.pendingUsrName = pendingUsrName;
	}

	public ScmInvSaleIssueBill2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			this.netAmt = BigDecimal.ZERO;
			this.amt = BigDecimal.ZERO;
			this.taxAmt = BigDecimal.ZERO;
			this.saleTaxAmt = BigDecimal.ZERO;
		}
	}

	public ScmInvSaleIssueBill2() {
		super();
	}
}
