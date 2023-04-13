
package com.armitage.server.iscm.purchasemanage.pricemanage.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmPurPriceEntry")  
public class ScmPurPriceEntry extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_PMID ="pmId";
    public static final String FN_LINEID ="lineId";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_PURUNIT ="purUnit";
    public static final String FN_INQUIRYPRICE1 ="inquiryPrice1";
    public static final String FN_INQUIRYPRICE2 ="inquiryPrice2";
    public static final String FN_INQUIRYPRICE3 ="inquiryPrice3";
    public static final String FN_RISEINQUIRYRATE ="riseInquiryRate";
    public static final String FN_PRICE1 ="price1";
    public static final String FN_PRICE2 ="price2";
    public static final String FN_PRICE3 ="price3";
    public static final String FN_TAXRATE1 ="taxRate1";
    public static final String FN_TAXRATE2 ="taxRate2";
    public static final String FN_TAXRATE3 ="taxRate3";
    public static final String FN_PREPRICE ="prePrice";
    public static final String FN_LASTYEARPRICE ="lastYearPrice";
    public static final String FN_PRICE ="price";
    public static final String FN_RISERATE ="riseRate";
    public static final String FN_DIFFERCOST ="differCost";
    public static final String FN_PREPURQTY = "prePurQty";
    public static final String FN_TAXRATE ="taxRate";
    public static final String FN_SELVNDRID = "selVndrId";
    public static final String FN_ROWSTATUS ="rowStatus";
    public static final String FN_CHECKER ="checker";
    public static final String FN_CHECKDATE ="checkDate";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_PRNOS = "prNos";
    
    private long id;
    private long pmId;
    private int lineId;
    private long itemId;
    private long purUnit;
    private BigDecimal inquiryPrice1;
    private BigDecimal inquiryPrice2;
    private BigDecimal inquiryPrice3;
    private BigDecimal riseInquiryRate;
    private BigDecimal price1;
    private BigDecimal price2;
    private BigDecimal price3;
    private BigDecimal taxRate1;
    private BigDecimal taxRate2;
    private BigDecimal taxRate3;
    private BigDecimal prePrice;
    private BigDecimal lastYearPrice;
    private BigDecimal price;
    private BigDecimal riseRate;
    private BigDecimal differCost;
    private BigDecimal prePurQty;
    private BigDecimal taxRate;
    private long selVndrId;
    private String rowStatus;
    private String checker;
    private Date checkDate;
    private String remarks;
    private String prNos;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getPmId() {
        return pmId;
    }

    public void setPmId(long val) {
        this.pmId = val;
    }
    public int getLineId() {
        return lineId;
    }

    public void setLineId(int val) {
        this.lineId = val;
    }
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long val) {
        this.itemId = val;
    }
    public long getPurUnit() {
        return purUnit;
    }

    public void setPurUnit(long val) {
        this.purUnit = val;
    }

    public BigDecimal getInquiryPrice1() {
		return inquiryPrice1;
	}

	public void setInquiryPrice1(BigDecimal inquiryPrice1) {
		this.inquiryPrice1 = inquiryPrice1;
	}

	public BigDecimal getInquiryPrice2() {
		return inquiryPrice2;
	}

	public void setInquiryPrice2(BigDecimal inquiryPrice2) {
		this.inquiryPrice2 = inquiryPrice2;
	}

	public BigDecimal getInquiryPrice3() {
		return inquiryPrice3;
	}

	public void setInquiryPrice3(BigDecimal inquiryPrice3) {
		this.inquiryPrice3 = inquiryPrice3;
	}

	public BigDecimal getRiseInquiryRate() {
		return riseInquiryRate;
	}

	public void setRiseInquiryRate(BigDecimal riseInquiryRate) {
		this.riseInquiryRate = riseInquiryRate;
	}

	public BigDecimal getPrice1() {
        return price1;
    }

    public void setPrice1(BigDecimal val) {
        this.price1 = val;
    }
    public BigDecimal getPrice2() {
        return price2;
    }

    public void setPrice2(BigDecimal val) {
        this.price2 = val;
    }
    public BigDecimal getPrice3() {
        return price3;
    }

    public void setPrice3(BigDecimal val) {
        this.price3 = val;
    }
    public BigDecimal getTaxRate1() {
		return taxRate1;
	}

	public void setTaxRate1(BigDecimal taxRate1) {
		this.taxRate1 = taxRate1;
	}

	public BigDecimal getTaxRate2() {
		return taxRate2;
	}

	public void setTaxRate2(BigDecimal taxRate2) {
		this.taxRate2 = taxRate2;
	}

	public BigDecimal getTaxRate3() {
		return taxRate3;
	}

	public void setTaxRate3(BigDecimal taxRate3) {
		this.taxRate3 = taxRate3;
	}

	public BigDecimal getPrePrice() {
        return prePrice;
    }

    public void setPrePrice(BigDecimal val) {
        this.prePrice = val;
    }
    public BigDecimal getLastYearPrice() {
		return lastYearPrice;
	}

	public void setLastYearPrice(BigDecimal lastYearPrice) {
		this.lastYearPrice = lastYearPrice;
	}

	public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal val) {
        this.price = val;
    }
    public BigDecimal getRiseRate() {
		return riseRate;
	}

	public void setRiseRate(BigDecimal riseRate) {
		this.riseRate = riseRate;
	}

	public BigDecimal getDifferCost() {
		return differCost;
	}

	public void setDifferCost(BigDecimal differCost) {
		this.differCost = differCost;
	}

	public BigDecimal getPrePurQty() {
		return prePurQty;
	}

	public void setPrePurQty(BigDecimal prePurQty) {
		this.prePurQty = prePurQty;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public long getSelVndrId() {
		return selVndrId;
	}

	public void setSelVndrId(long selVndrId) {
		this.selVndrId = selVndrId;
	}

	public String getRowStatus() {
        return rowStatus;
    }

    public void setRowStatus(String val) {
        this.rowStatus = val;
    }
    public String getChecker() {
        return checker;
    }

    public void setChecker(String val) {
        this.checker = val;
    }
    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date val) {
        this.checkDate = val;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String val) {
        this.remarks = val;
    }

    public String getPrNos() {
		return prNos;
	}

	public void setPrNos(String prNos) {
		this.prNos = prNos;
	}

	public ScmPurPriceEntry(boolean defaultValue){
       if(defaultValue){
    	   this.price=BigDecimal.ZERO;
    	   this.inquiryPrice1=BigDecimal.ZERO;
    	   this.inquiryPrice2=BigDecimal.ZERO;
    	   this.inquiryPrice3=BigDecimal.ZERO;
    	   this.price1=BigDecimal.ZERO;
    	   this.price2=BigDecimal.ZERO;
    	   this.price3=BigDecimal.ZERO;
    	   this.prePrice=BigDecimal.ZERO;
    	   this.lastYearPrice=BigDecimal.ZERO;
    	   this.taxRate=BigDecimal.ZERO;
    	   this.riseInquiryRate=BigDecimal.ZERO;
    	   this.riseRate=BigDecimal.ZERO;
    	   this.differCost=BigDecimal.ZERO;
    	   this.prePurQty=BigDecimal.ZERO;
    	   this.taxRate1=BigDecimal.ZERO;
    	   this.taxRate2=BigDecimal.ZERO;
    	   this.taxRate3=BigDecimal.ZERO;
       }
    }
  	public ScmPurPriceEntry() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_PMID,
            FN_LINEID,
            FN_ITEMID,
            FN_PURUNIT,
            FN_INQUIRYPRICE1,
            FN_INQUIRYPRICE2,
            FN_INQUIRYPRICE3,
            FN_PRICE1,
            FN_PRICE2,
            FN_PRICE3,
            FN_TAXRATE1,
            FN_TAXRATE2,
            FN_TAXRATE3,
            FN_PREPRICE,
            FN_PRICE,
            FN_TAXRATE,
            FN_ROWSTATUS,
            FN_CHECKER,
            FN_CHECKDATE,
            FN_REMARKS,
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
		return new String[] {FN_ITEMID,FN_PRICE};
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
        map.put(FN_ROWSTATUS, 16);
        map.put(FN_CHECKER, 16);
        map.put(FN_REMARKS, 255);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
