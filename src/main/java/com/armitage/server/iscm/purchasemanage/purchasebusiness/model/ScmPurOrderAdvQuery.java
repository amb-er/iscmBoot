package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
public class ScmPurOrderAdvQuery extends BaseModel  {
	 
	public static final String FN_BEGBIZDATE ="begBizDate"; //单据日期起
    public static final String FN_ENDBIZDATE ="endBizDate"; //单据日期止
    public static final String FN_CREATEDATEFROM = "createDateFrom"; //创建日期起
    public static final String FN_CREATEDATETO = "createDateTo"; //创建日期止
    public static final String FN_VENDORCLASSID = "vendorClassId"; //供应商类别
    public static final String FN_VENDORID = "vendorId"; //供应商
    public static final String FN_CREATOR = "creator"; //创建人
    public static final String FN_RELEASEDATEFROM = "releaseDateFrom"; //下达日期起
    public static final String FN_RELEASEDATETO = "releaseDateTo"; //下达日期止
	
    private Date begBizDate;
    private Date endBizDate;
    private Date createDateFrom;
    private Date createDateTo;
    private long vendorClassId;
    private long vendorId;
    private long itemId;
	private String creator;
	private String poNo;
	private String buyerId;
	private Date orderDateFrom;
    private Date orderDateTo;
    private String status;
    private String vendorCode;
    private boolean fromInterface;
	private String orgUnitNo;
	private Date releaseDateFrom;
	private Date releaseDateTo;
	private String dateType;
	
	public Date getBegBizDate() {
		return begBizDate;
	}
	public void setBegBizDate(Date begBizDate) {
		this.begBizDate = begBizDate;
	}
	public Date getEndBizDate() {
		return endBizDate;
	}
	public void setEndBizDate(Date endBizDate) {
		this.endBizDate = endBizDate;
	}
	public Date getCreateDateFrom() {
		return createDateFrom;
	}
	public void setCreateDateFrom(Date createDateFrom) {
		this.createDateFrom = createDateFrom;
	}
	public Date getCreateDateTo() {
		return createDateTo;
	}
	public void setCreateDateTo(Date createDateTo) {
		this.createDateTo = createDateTo;
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
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public Date getOrderDateFrom() {
		return orderDateFrom;
	}
	public void setOrderDateFrom(Date orderDateFrom) {
		this.orderDateFrom = orderDateFrom;
	}
	public Date getOrderDateTo() {
		return orderDateTo;
	}
	public void setOrderDateTo(Date orderDateTo) {
		this.orderDateTo = orderDateTo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public boolean isFromInterface() {
		return fromInterface;
	}
	public void setFromInterface(boolean fromInterface) {
		this.fromInterface = fromInterface;
	}
	
	public String getOrgUnitNo() {
		return orgUnitNo;
	}
	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}
	public Date getReleaseDateFrom() {
		return releaseDateFrom;
	}
	public void setReleaseDateFrom(Date releaseDateFrom) {
		this.releaseDateFrom = releaseDateFrom;
	}
	public Date getReleaseDateTo() {
		return releaseDateTo;
	}
	public void setReleaseDateTo(Date releaseDateTo) {
		this.releaseDateTo = releaseDateTo;
	}
	
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public ScmPurOrderAdvQuery(boolean defaultValue){
       if(defaultValue){
    	   dateType="1";
       }
    }
  	public ScmPurOrderAdvQuery() {

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
