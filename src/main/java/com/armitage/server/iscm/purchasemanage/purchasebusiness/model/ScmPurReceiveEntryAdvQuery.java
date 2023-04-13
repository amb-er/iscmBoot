package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
public class ScmPurReceiveEntryAdvQuery extends BaseModel  {
	 
	public static final String FN_BEGBILLDATE ="begBillDate"; //单据日期起
    public static final String FN_ENDBILLDATE ="endBillDate"; //单据日期止
    public static final String FN_BEGPRNO ="begPvNo"; //收货单号起
    public static final String FN_ENDPRNO ="endPvNo"; //收货单号止
    public static final String FN_VENDORID = "vendorId"; //供应商
    public static final String FN_ITEMID = "itemId"; //物资编码
	
    private Date begBillDate;
    private Date endBillDate;
    private String begPvNo;
    private String endPvNo;
    private long vendorId;
    private long itemId;

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
	public String getBegPvNo() {
		return begPvNo;
	}
	public void setBegPvNo(String begPvNo) {
		this.begPvNo = begPvNo;
	}
	public String getEndPvNo() {
		return endPvNo;
	}
	public void setEndPvNo(String endPvNo) {
		this.endPvNo = endPvNo;
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
	public ScmPurReceiveEntryAdvQuery(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmPurReceiveEntryAdvQuery() {

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
