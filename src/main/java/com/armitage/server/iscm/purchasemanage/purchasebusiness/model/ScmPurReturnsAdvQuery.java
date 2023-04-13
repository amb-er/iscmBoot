package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
public class ScmPurReturnsAdvQuery extends BaseModel  {
	 
	public static final String FN_BEGBIZDATE ="begBizDate"; //单据日期起
    public static final String FN_ENDBIZDATE ="endBizDate"; //单据日期止
    public static final String FN_CREATEDATEFROM = "createDateFrom"; //创建日期起
    public static final String FN_CREATEDATETO = "createDateTo"; //创建日期止
    public static final String FN_PURORGUNITNO = "purOrgUnitNo"; //采购组织
    public static final String FN_VENDORID = "vendorId"; //供应商
    public static final String FN_CREATOR = "creator"; //创建人
	
    private Date begBizDate;
    private Date endBizDate;
    private Date createDateFrom;
    private Date createDateTo;
    private String purOrgUnitNo;
    private long vendorId;
	private String creator;

	
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
	
	public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}
	public void setPurOrgUnitNo(String purOrgUnitNo) {
		this.purOrgUnitNo = purOrgUnitNo;
	}
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public ScmPurReturnsAdvQuery(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmPurReturnsAdvQuery() {

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
