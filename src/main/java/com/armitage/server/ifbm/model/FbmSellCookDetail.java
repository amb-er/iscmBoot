package com.armitage.server.ifbm.model;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmFbmSellCookDetail")  
public class FbmSellCookDetail extends BaseModel  {
    
    private long id;
    private String orgUnitNo;
    private String resOrgUnitNo;
    private Date accDate;
    private long dishId;
    private String dishCode;
    private String dishName;
    private String fbmStatCode;
    private String fbmStatName;
    private long cookId;
    private String cookCode;
    private String cookName;
	private long dishSpecId;
    private String specCode;
    private String specName;
    private String deptCode;
    private String deptName;
    private BigDecimal saleQty;
    private BigDecimal stdSalePrice;
    private BigDecimal saleAmt;
    private BigDecimal realSaleAmt;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public String getResOrgUnitNo() {
        return resOrgUnitNo;
    }

    public void setResOrgUnitNo(String val) {
        this.resOrgUnitNo = val;
    }
    public Date getAccDate() {
        return accDate;
    }

    public void setAccDate(Date val) {
        this.accDate = val;
    }
    public long getDishId() {
        return dishId;
    }

    public void setDishId(long val) {
        this.dishId = val;
    }
    public String getDishCode() {
		return dishCode;
	}

	public void setDishCode(String dishCode) {
		this.dishCode = dishCode;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getFbmStatCode() {
		return fbmStatCode;
	}

	public void setFbmStatCode(String fbmStatCode) {
		this.fbmStatCode = fbmStatCode;
	}

	public String getFbmStatName() {
		return fbmStatName;
	}

	public void setFbmStatName(String fbmStatName) {
		this.fbmStatName = fbmStatName;
	}

	public long getCookId() {
        return cookId;
    }

    public void setCookId(long val) {
        this.cookId = val;
    }
    public String getCookCode() {
		return cookCode;
	}

	public void setCookCode(String cookCode) {
		this.cookCode = cookCode;
	}

	public String getCookName() {
		return cookName;
	}

	public void setCookName(String cookName) {
		this.cookName = cookName;
	}

	public long getDishSpecId() {
		return dishSpecId;
	}

	public void setDishSpecId(long dishSpecId) {
		this.dishSpecId = dishSpecId;
	}

	public String getSpecCode() {
		return specCode;
	}

	public void setSpecCode(String specCode) {
		this.specCode = specCode;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String val) {
        this.deptCode = val;
    }
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String val) {
        this.deptName = val;
    }
    public BigDecimal getSaleQty() {
        return saleQty;
    }

    public void setSaleQty(BigDecimal val) {
        this.saleQty = val;
    }
    public BigDecimal getStdSalePrice() {
        return stdSalePrice;
    }

    public void setStdSalePrice(BigDecimal val) {
        this.stdSalePrice = val;
    }
    public BigDecimal getSaleAmt() {
        return saleAmt;
    }

    public void setSaleAmt(BigDecimal val) {
        this.saleAmt = val;
    }

    public BigDecimal getRealSaleAmt() {
		return realSaleAmt;
	}

	public void setRealSaleAmt(BigDecimal realSaleAmt) {
		this.realSaleAmt = realSaleAmt;
	}

	public FbmSellCookDetail(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public FbmSellCookDetail() {

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
