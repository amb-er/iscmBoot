package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
 
@XmlRootElement(name = "scmPurBillDrillResult")  
public class ScmPurBillDrillResult extends BaseModel  {
	
    public static final String FN_ITEMID ="itemId";
    public static final String FN_ITEMNO ="itemNo";
    public static final String FN_ITEMNAME ="itemName";
    public static final String FN_PRNO ="prNo";
    public static final String FN_PRDETAILID ="prDetailId";
    public static final String FN_PRIDS ="prIds";
    public static final String FN_PRSTATUS ="prStatus";
    public static final String FN_PONO ="poNo";
    public static final String FN_PODETAILID ="poDetailId";
    public static final String FN_POIDS ="poIds";
    public static final String FN_POSTATUS ="poStatus";
    public static final String FN_PVNO ="pvNo";
    public static final String FN_PVIDS ="pvIds";
    public static final String FN_PVSTATUS ="pvStatus";
    public static final String FN_PRICEBILLNO ="priceBillNo";
    public static final String FN_PRICEBILLSTATUS ="priceBillStatus";
    public static final String FN_REFPRICESTATUS ="refPriceStatus";
    
    //新增采购入库单相关字段
    public static final String FN_WRNO ="wrNo";
    public static final String FN_WRDETAILID ="wrDetailId";
    public static final String FN_WRIDS ="wrIds";
    public static final String FN_WRSTATUS ="wrStatus";
    public static final String FN_WRENTRYID_STRING="wrEntryId";
    
    //新增应付单相关字段
    public static final String FN_APINO ="apiNo";
    public static final String FN_APIETAILID ="apiDetailId";
    public static final String FN_APIIDS ="apiIds";
    public static final String FN_APISTATUS ="apiStatus";
    public static final String FN_APISOURCEDTLID_STRING="apiSourceDtlId";
    //新增付款单相关字段
    public static final String FN_APPNO ="apPNo";
    public static final String FN_APPDETAILID ="apPDetailId";
    public static final String FN_APPIDS ="apPIds";
    public static final String FN_APPSTATUS ="apPStatus";
    public static final String FN_BILLID_STRING = "billId";
    
    private long itemId;
    private String itemNo;
	private String itemName;
	private String prNo;
	private String prDetailId;
    private String prIds;
    private String prStatus;
    private String poNo;
    private String poDetailId;
    private String poIds;
    private String poStatus;
    private String pvNo;
    private String pvIds;
    private String pvStatus;
    private String priceBillNo;
    private String priceBillStatus;
    private String refPriceStatus;
    private String rowStatus;
    private String refuseReason;
    
    //新增采购入库相关属性
    private String wrNo;
	private String wrDetailId;
    private String wrIds;
    private String wrStatus;
    private String wrEntryId;
    
    //新增应付单相关属性
    private String apiNo;
	private String apiDetailId;
    private String apiIds;
    private String apiStatus;
    private String apiSourceDtlId;
    
    //新增付款单相关属性
    private String apPNo;
	private String apPDetailId;
    private String apPIds;
    private String apPStatus;
    private long billId;

	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
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
	public String getPrNo() {
		return prNo;
	}
	public void setPrNo(String prNo) {
		this.prNo = prNo;
	}
	public String getPrDetailId() {
		return prDetailId;
	}
	public void setPrDetailId(String prDetailId) {
		this.prDetailId = prDetailId;
	}
	public String getPrIds() {
		return prIds;
	}
	public void setPrIds(String prIds) {
		this.prIds = prIds;
	}
	public String getPrStatus() {
		return prStatus;
	}
	public void setPrStatus(String prStatus) {
		this.prStatus = prStatus;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getPoDetailId() {
		return poDetailId;
	}
	public void setPoDetailId(String poDetailId) {
		this.poDetailId = poDetailId;
	}
	public String getPoIds() {
		return poIds;
	}
	public void setPoIds(String poIds) {
		this.poIds = poIds;
	}
	public String getPoStatus() {
		return poStatus;
	}
	public void setPoStatus(String poStatus) {
		this.poStatus = poStatus;
	}
	public String getPvNo() {
		return pvNo;
	}
	public void setPvNo(String pvNo) {
		this.pvNo = pvNo;
	}
	
	public String getPvIds() {
		return pvIds;
	}
	public void setPvIds(String pvIds) {
		this.pvIds = pvIds;
	}
	public String getPvStatus() {
		return pvStatus;
	}
	public void setPvStatus(String pvStatus) {
		this.pvStatus = pvStatus;
	}
	public String getPriceBillNo() {
		return priceBillNo;
	}
	public void setPriceBillNo(String priceBillNo) {
		this.priceBillNo = priceBillNo;
	}
	public String getPriceBillStatus() {
		return priceBillStatus;
	}
	public void setPriceBillStatus(String priceBillStatus) {
		this.priceBillStatus = priceBillStatus;
	}
	public String getRefPriceStatus() {
		return refPriceStatus;
	}
	public void setRefPriceStatus(String refPriceStatus) {
		this.refPriceStatus = refPriceStatus;
	}
	
	public String getWrNo() {
		return wrNo;
	}
	public void setWrNo(String wrNo) {
		this.wrNo = wrNo;
	}
	public String getWrDetailId() {
		return wrDetailId;
	}
	public void setWrDetailId(String wrDetailId) {
		this.wrDetailId = wrDetailId;
	}
	public String getWrIds() {
		return wrIds;
	}
	public void setWrIds(String wrIds) {
		this.wrIds = wrIds;
	}
	public String getWrStatus() {
		return wrStatus;
	}
	public void setWrStatus(String wrStatus) {
		this.wrStatus = wrStatus;
	}
	public String getApiNo() {
		return apiNo;
	}
	public void setApiNo(String apiNo) {
		this.apiNo = apiNo;
	}
	public String getApiDetailId() {
		return apiDetailId;
	}
	public void setApiDetailId(String apiDetailId) {
		this.apiDetailId = apiDetailId;
	}
	public String getApiIds() {
		return apiIds;
	}
	public void setApiIds(String apiIds) {
		this.apiIds = apiIds;
	}
	public String getApiStatus() {
		return apiStatus;
	}
	public void setApiStatus(String apiStatus) {
		this.apiStatus = apiStatus;
	}
	public String getApPNo() {
		return apPNo;
	}
	public void setApPNo(String apPNo) {
		this.apPNo = apPNo;
	}
	public String getApPDetailId() {
		return apPDetailId;
	}
	public void setApPDetailId(String apPDetailId) {
		this.apPDetailId = apPDetailId;
	}
	public String getApPIds() {
		return apPIds;
	}
	public void setApPIds(String apPIds) {
		this.apPIds = apPIds;
	}
	public String getApPStatus() {
		return apPStatus;
	}
	public void setApPStatus(String apPStatus) {
		this.apPStatus = apPStatus;
	}
	public long getBillId() {
		return billId;
	}
	public void setBillId(long billId) {
		this.billId = billId;
	}
	public String getRowStatus() {
		return rowStatus;
	}
	public void setRowStatus(String rowStatus) {
		this.rowStatus = rowStatus;
	}
	public String getRefuseReason() {
		return refuseReason;
	}
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
	
	public String getWrEntryId() {
		return wrEntryId;
	}
	public void setWrEntryId(String wrEntryId) {
		this.wrEntryId = wrEntryId;
	}
	public String getApiSourceDtlId() {
		return apiSourceDtlId;
	}
	public void setApiSourceDtlId(String apiSourceDtlId) {
		this.apiSourceDtlId = apiSourceDtlId;
	}
	public ScmPurBillDrillResult(boolean defaultValue){
       if(defaultValue){
    	   
       }
    }
  	public ScmPurBillDrillResult() {

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

}
