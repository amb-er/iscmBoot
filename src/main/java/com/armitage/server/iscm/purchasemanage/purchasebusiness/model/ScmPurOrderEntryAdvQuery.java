package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
public class ScmPurOrderEntryAdvQuery extends BaseModel  {
	 
	public static final String FN_BEGBILLDATE ="begBillDate"; //单据日期起
    public static final String FN_ENDBILLDATE ="endBillDate"; //单据日期止
    public static final String FN_BEGPRNO ="begPoNo"; //订货单号起
    public static final String FN_ENDPRNO ="endPoNo"; //订货单号止
    public static final String FN_PURORGUNITNO ="purOrgUnitNo"; //采购组织
    public static final String FN_ORGUNITNO ="orgUnitNo"; //申购组织
    public static final String FN_BUYERID = "buyerId"; //采购员
    public static final String FN_VENDORID = "vendorId"; //供应商
    public static final String FN_ITEMID = "itemId"; //物资编码
	
    private Date begBillDate;
    private Date endBillDate;
    private String begPoNo;
    private String endPoNo;
    private String purOrgUnitNo;	//采购组织
    private String orgUnitNo;		//申购组织
    private long buyerId;
    private long vendorId;
    private long itemId;
    private String invOrgUnitNo;	//库存组织

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
	public String getBegPoNo() {
		return begPoNo;
	}
	public void setBegPoNo(String begPoNo) {
		this.begPoNo = begPoNo;
	}
	public String getEndPoNo() {
		return endPoNo;
	}
	public void setEndPoNo(String endPoNo) {
		this.endPoNo = endPoNo;
	}
	public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}
	public void setPurOrgUnitNo(String purOrgUnitNo) {
		this.purOrgUnitNo = purOrgUnitNo;
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
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public String getInvOrgUnitNo() {
		return invOrgUnitNo;
	}
	public void setInvOrgUnitNo(String invOrgUnitNo) {
		this.invOrgUnitNo = invOrgUnitNo;
	}
	public ScmPurOrderEntryAdvQuery(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmPurOrderEntryAdvQuery() {

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
