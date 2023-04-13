package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmPurQuotationPlanAdvQuery extends BaseModel {
	 public static final String FN_PURCHASINGQUERY = "purChasIngQuery"; 
	 public static final String FN_QTYRECORD= "qtyRecord";
	 public static final String FN_BEGINDATETO = "beginDateTo"; 
	 public static final String FN_ENDDATETO = "endDateTo"; 
	 public static final String FN_SORT = "sort";
	 
	 
      private String  purChasIngQuery;//物资类别
      private String sort;
      private String qtyRecord;//记录条数
      private Date beginDateTo;
      private Date endDateTo;
      private String  purOrgUnitNo;
      
      public ScmPurQuotationPlanAdvQuery(){}
      public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}
	public void setPurOrgUnitNo(String purOrgUnitNo) {
		this.purOrgUnitNo = purOrgUnitNo;
	}
      public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
      public String getPurChasIngQuery() {
		return purChasIngQuery;
	}
	public void setPurChasIngQuery(String purChasIngquery) {
		this.purChasIngQuery = purChasIngquery;
	}
	
	public String getQtyRecord() {
		return qtyRecord;
	}
	public void setQtyRecord(String qtyRecord) {
		this.qtyRecord = qtyRecord;
	}
	public Date getBeginDateTo() {
		return beginDateTo;
	}
	public void setBeginDateTo(Date beginDateTo) {
		this.beginDateTo = beginDateTo;
	}
	public Date getEndDateTo() {
		return endDateTo;
	}
	public void setEndDateTo(Date endDateTo) {
		this.endDateTo = endDateTo;
	}
	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getPK() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getPkKey() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String[] getRequiredFields() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String[]> getUniqueKeys() {
		// TODO Auto-generated method stub
		return null;
	}
	public ScmPurQuotationPlanAdvQuery(boolean defaultValue){
	       if(defaultValue){
	       }
	    }
}
