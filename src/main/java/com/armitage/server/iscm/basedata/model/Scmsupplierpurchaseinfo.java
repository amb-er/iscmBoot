package com.armitage.server.iscm.basedata.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmsupplierpurchaseinfo")  
public class Scmsupplierpurchaseinfo extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_VENDORID ="vendorId";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_VENDORATTRIBUTE = "vendorAttribute";
    public static final String FN_BUYERID ="buyerId";
    public static final String FN_PURGROUPID ="purGroupId";
    public static final String FN_TAX ="tax";
    public static final String FN_TAXPAYERTYPE ="taxpayerType";
    public static final String FN_VATRATE ="vatRate";
    public static final String FN_CANINVOICE ="canInvoice";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_FLAG ="flag";
    public static final String FN_FREEZEORGUNITNO ="freezeOrgUnitNo";
    public static final String FN_STATUS = "status";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private long vendorId;
    private String orgUnitNo;
    private String vendorAttribute;
    private long buyerId;
    private long purGroupId;
    private boolean tax;
    private String taxpayerType;
    private BigDecimal vatRate;
    private boolean canInvoice;
    private String creator;
    private Date createDate;
    private String editor;
    private Date editDate;
    private boolean flag;
    private String freezeOrgUnitNo;
    private String status;
    private String controlUnitNo;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getVendorId() {
        return vendorId;
    }

    public void setVendorId(long val) {
        this.vendorId = val;
    }
    public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public String getVendorAttribute() {
		return vendorAttribute;
	}

	public void setVendorAttribute(String vendorAttribute) {
		this.vendorAttribute = vendorAttribute;
	}

	public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long val) {
        this.buyerId = val;
    }
    public long getPurGroupId() {
        return purGroupId;
    }

    public void setPurGroupId(long val) {
        this.purGroupId = val;
    }
    public boolean getTax() {
        return tax;
    }

    public void setTax(boolean val) {
        this.tax = val;
    }
    public String getTaxpayerType() {
        return taxpayerType;
    }

    public void setTaxpayerType(String val) {
        this.taxpayerType = val;
    }
    public BigDecimal getVatRate() {
        return vatRate;
    }

    public void setVatRate(BigDecimal val) {
        this.vatRate = val;
    }
    public boolean getCanInvoice() {
        return canInvoice;
    }

    public void setCanInvoice(boolean val) {
        this.canInvoice = val;
    }
    public String getCreator() {
        return creator;
    }

    public void setCreator(String val) {
        this.creator = val;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date val) {
        this.createDate = val;
    }
    public String getEditor() {
        return editor;
    }

    public void setEditor(String val) {
        this.editor = val;
    }
    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date val) {
        this.editDate = val;
    }
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean val) {
        this.flag = val;
    }
    public String getFreezeOrgUnitNo() {
        return freezeOrgUnitNo;
    }

    public void setFreezeOrgUnitNo(String val) {
        this.freezeOrgUnitNo = val;
    }
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public Scmsupplierpurchaseinfo(boolean defaultValue){
       if(defaultValue){
    	   this.tax=false;
    	   this.canInvoice=false;
    	   this.flag=true;
    	   this.vatRate=BigDecimal.ZERO;
       }
    }
  	public Scmsupplierpurchaseinfo() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_VENDORID,
            FN_ORGUNITNO,
            FN_VENDORATTRIBUTE,
            FN_BUYERID,
            FN_PURGROUPID,
            FN_TAX,
            FN_TAXPAYERTYPE,
            FN_VATRATE,
            FN_CANINVOICE,
            FN_CREATOR,
            FN_CREATEDATE,
            FN_EDITOR,
            FN_EDITDATE,
            FN_FLAG,
            FN_FREEZEORGUNITNO,
            FN_STATUS,
            FN_CONTROLUNITNO,
        };
    }	
    

	public Map<String, RelationModel> getForeignMap() {
        
        /*
        DEMO:
		HashMap<String, RelationModel> map = new HashMap<String, RelationModel>();
		map.put(FN_STATUS, new RelationModel(Code.class, Code.FN_CODE)
				.addFilter(Code.FN_CATEGORY, "UserStatus"));
		return map;
        */
        return null;
	}
    public List<String[]> getUniqueKeys() {
		List<String[]> list = new ArrayList<String[]>();
		//list.add(new String[] { FN_CODE, FN_CATEGORY });
		return list;
	}
	 
	public String[] getRequiredFields() {
	    /*DEMO:
		return new String[] {FN_CODE };
        */
		return new String[] {FN_ORGUNITNO,FN_TAXPAYERTYPE};
	}
 
	public Map<String, List<RelationModel>> getReferenceMap() {
		
        /*
        DEMO:
        HashMap<String, List<RelationModel>> map = new HashMap<String, List<RelationModel>>();

		List<RelationModel> list = new ArrayList<RelationModel>();
		list.add(new RelationModel(Code.class, Code.FN_CODE).addFilter(
				Code.FN_CATEGORY, "UserStatus"));

		map.put(this.FN_STATUS, list);
		return map;*/
        
        return null;
	}
    public Map<String, Integer> getDataLengthMap() {
        
		HashMap<String, Integer> map = new HashMap<String, Integer>();  
        map.put(FN_ORGUNITNO, 32);
        map.put(FN_VENDORATTRIBUTE, 12);
        map.put(FN_TAXPAYERTYPE, 32);
        map.put(FN_CREATOR, 16);
        map.put(FN_EDITOR, 16);
        map.put(FN_FLAG, 1);
        map.put(FN_FREEZEORGUNITNO, 32);
        map.put(FN_STATUS, 1);
        map.put(FN_CONTROLUNITNO, 32);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
