package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.math.BigDecimal;
import java.util.List;

public class ScmPurRequire2 extends ScmPurRequire {
	public static final String FN_CHOOSED = "choosed";
	
	public static final String FN_VENDORNAME = "vendorName";
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_PURUNIT = "purUnit";
	public static final String FN_UNITNAME = "unitName";
	public static final String FN_REQORGUNITNAME = "reqOrgUnitName";
	public static final String FN_PURORGUNITNAME = "purOrgUnitName";
	public static final String FN_QTY = "qty";
	public static final String FN_AMT = "amt";
	public static final String FN_CLASSNAME = "className";
	public static final String FN_SPEC = "spec";
	public static final String FN_FTYPE = "ftype";
	public static final String FN_TOTALAMT = "totalAmt";
	public static final String FN_FLAG = "flag";
	public static final String FN_ORGUNITNAME = "orgUnitName";
	public static final String FN_BIZTYPENAME = "bizTypeName";
	public static final String FN_PENDINGUSR = "pendingUsr";
	public static final String FN_PENDINGUSRNAME = "pendingUsrName";
	
	private boolean choosed;
	
	private String vendorName;
	private String itemNo;
	private String itemName;
	private String purUnit;
	private String className;
	private String spec;
	private String unitName;
	private String reqOrgUnitName;
	private String purOrgUnitName;
	private BigDecimal qty;
	private BigDecimal amt;
	private String ftype;
	private BigDecimal totalAmt;
	private String flag;
	private String applicantsName;
	private String receiveWareHouseName;
	private String creatorName;
	private String editorName;
	private String checkerName;
	private String statusName;	//状态名称，返回接口用
	private List<ScmPurRequireEntry2> scmPurRequireEntryList;
	
	private boolean coverTemplete;   //模板已经存在
	private String orgUnitName;
	private String bizTypeName;
	private String pendingUsr;
	private String pendingUsrName;
	private String receiveWareHouseNo;
	
	public List<ScmPurRequireEntry2> getScmPurRequireEntryList() {
		return scmPurRequireEntryList;
	}

	public void setScmPurRequireEntryList(List<ScmPurRequireEntry2> scmPurRequireEntryList) {
		this.scmPurRequireEntryList = scmPurRequireEntryList;
	}
	
	public boolean isChoosed() {
		return choosed;
	}

	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}

	
	public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
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

    public String getPurUnit() {
        return purUnit;
    }

    public void setPurUnit(String purUnit) {
        this.purUnit = purUnit;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getReqOrgUnitName() {
		return reqOrgUnitName;
	}

	public void setReqOrgUnitName(String reqOrgUnitName) {
		this.reqOrgUnitName = reqOrgUnitName;
	}

	public String getPurOrgUnitName() {
		return purOrgUnitName;
	}

	public void setPurOrgUnitName(String purOrgUnitName) {
		this.purOrgUnitName = purOrgUnitName;
	}

	public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }
    
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
    
    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }
    
    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getApplicantsName() {
		return applicantsName;
	}

	public void setApplicantsName(String applicantsName) {
		this.applicantsName = applicantsName;
	}

	public String getReceiveWareHouseName() {
		return receiveWareHouseName;
	}

	public void setReceiveWareHouseName(String receiveWareHouseName) {
		this.receiveWareHouseName = receiveWareHouseName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getEditorName() {
		return editorName;
	}

	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public boolean isCoverTemplete() {
        return coverTemplete;
    }

    public void setCoverTemplete(boolean coverTemplete) {
        this.coverTemplete = coverTemplete;
    }

    public String getOrgUnitName() {
        return orgUnitName;
    }

    public void setOrgUnitName(String orgUnitName) {
        this.orgUnitName = orgUnitName;
    }

	public String getBizTypeName() {
		return bizTypeName;
	}

	public void setBizTypeName(String bizTypeName) {
		this.bizTypeName = bizTypeName;
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

	public String getReceiveWareHouseNo() {
		return receiveWareHouseNo;
	}

	public void setReceiveWareHouseNo(String receiveWareHouseNo) {
		this.receiveWareHouseNo = receiveWareHouseNo;
	}

	public ScmPurRequire2(boolean defaultValue) {
		super(defaultValue);
	}

	public ScmPurRequire2() {
		super();
	}
}
