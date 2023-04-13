package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
public class ScmPurRequireEntryAdvQuery extends BaseModel  {
	 
	public static final String FN_BEGBILLDATE ="begBillDate"; //单据日期起
    public static final String FN_ENDBILLDATE ="endBillDate"; //单据日期止
    public static final String FN_BEGPRNO ="begPrNo"; //请购单号起
    public static final String FN_ENDPRNO ="endPrNo"; //请购单号止
    public static final String FN_ORGUNITNO ="orgUnitNo"; //申购组织
    public static final String FN_BUYERID = "buyerId"; //采购员
    public static final String FN_VENDORCLASSID = "vendorClassId"; //供应商类别
    public static final String FN_VENDORID = "vendorId"; //供应商
    public static final String FN_MATERIALGROUPID = "materialGroupId"; //物资分类
    public static final String FN_ITEMID = "itemId"; //物资编码
    public static final String FN_REQDATEFROM = "reqDateFrom"; //需求日期起
    public static final String FN_REQDATETO = "reqDateTo"; //需求日期止
    public static final String FN_PRNO = "prNo"; //请购单号
    public static final String FN_FINORGUNITNO = "finOrgUnitNo"; //需求门店
    public static final String FN_BIZTYPE = "bizType";	//采购类型
    public static final String FN_PURORGUNITNO = "purOrgUnitNo"; //采购组织
    
    private Date begBillDate;
    private Date endBillDate;
    private String begPrNo;
    private String endPrNo;
    private String orgUnitNo;
    private long buyerId;
    private long vendorClassId;
    private long vendorId;
    private long materialGroupId;
    private long itemId;
    private Date reqDateFrom;
    private Date reqDateTo;
    private String prNo;
    private String finOrgUnitNo;
    private String bizType;
    private String purOrgUnitNo;
    
	public Date getBegBillDate() {
		return begBillDate;
	}
	public void setBegBillDate(Date begBillDate) {
		this.begBillDate = begBillDate;
	}
	public Date getEndBillDate() {
		return endBillDate;
	}
	public void setEndBillDate(Date endBillDate) {
		this.endBillDate = endBillDate;
	}
	public String getBegPrNo() {
		return begPrNo;
	}
	public void setBegPrNo(String begPrNo) {
		this.begPrNo = begPrNo;
	}
	public String getEndPrNo() {
		return endPrNo;
	}
	public void setEndPrNo(String endPrNo) {
		this.endPrNo = endPrNo;
	}
	public String getOrgUnitNo() {
		return orgUnitNo;
	}
	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}
	public long getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}
	public long getVendorClassId() {
		return vendorClassId;
	}
	public void setVendorClassId(long vendorClassId) {
		this.vendorClassId = vendorClassId;
	}
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public long getMaterialGroupId() {
		return materialGroupId;
	}
	public void setMaterialGroupId(long materialGroupId) {
		this.materialGroupId = materialGroupId;
	}
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public Date getReqDateFrom() {
        return reqDateFrom;
    }
    public void setReqDateFrom(Date reqDateFrom) {
        this.reqDateFrom = reqDateFrom;
    }
    public Date getReqDateTo() {
        return reqDateTo;
    }
    public void setReqDateTo(Date reqDateTo) {
        this.reqDateTo = reqDateTo;
    }
    public String getPrNo() {
        return prNo;
    }
    public void setPrNo(String prNo) {
        this.prNo = prNo;
    }
    public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}
	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}
	public void setPurOrgUnitNo(String purOrgUnitNo) {
		this.purOrgUnitNo = purOrgUnitNo;
	}
	public ScmPurRequireEntryAdvQuery(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmPurRequireEntryAdvQuery() {

	}
  	
  	@Override
	public String getPkKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getPK() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getRequiredFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getUniqueKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, RelationModel> getForeignMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, List<RelationModel>> getReferenceMap() {
		// TODO Auto-generated method stub
		return null;
	}

}
