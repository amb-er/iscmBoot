package com.armitage.server.iscm.inventorymanage.internaltrans.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
public class ScmInvSaleOrderAdvQuery extends BaseModel  {
	 
	public static final String FN_BEGBIZDATE ="begBizDate"; //单据日期起
    public static final String FN_ENDBIZDATE ="endBizDate"; //单据日期止
    public static final String FN_CREATEDATEFROM = "createDateFrom"; //创建日期起
    public static final String FN_CREATEDATETO = "createDateTo"; //创建日期止
    public static final String FN_CUSTID = "custId"; //客户
	
    private Date begBizDate;
    private Date endBizDate;
    private Date createDateFrom;
    private Date createDateTo;
    private long custId;
	
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
	
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public ScmInvSaleOrderAdvQuery(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmInvSaleOrderAdvQuery() {

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
