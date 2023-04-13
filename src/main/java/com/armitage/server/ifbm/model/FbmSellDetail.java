package com.armitage.server.ifbm.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;

/**
 * 餐饮报表汇集
 * @author Fengxq
 *
 */
public class FbmSellDetail extends BaseModel {
	private long id;
    private String orgUnitNo;
    private String resOrgUnitNo;
    private Date accDate;
    private long dishId;
    private String dishCode;
    private String dishName;
    private String fbmStatCode;
    private String fbmStatName;
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

	public void setId(long id) {
		this.id = id;
	}

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public String getResOrgUnitNo() {
		return resOrgUnitNo;
	}

	public void setResOrgUnitNo(String resOrgUnitNo) {
		this.resOrgUnitNo = resOrgUnitNo;
	}

	public Date getAccDate() {
		return accDate;
	}

	public void setAccDate(Date accDate) {
		this.accDate = accDate;
	}

	public long getDishId() {
		return dishId;
	}

	public void setDishId(long dishId) {
		this.dishId = dishId;
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

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public BigDecimal getSaleQty() {
		return saleQty;
	}

	public void setSaleQty(BigDecimal saleQty) {
		this.saleQty = saleQty;
	}

	public BigDecimal getStdSalePrice() {
		return stdSalePrice;
	}

	public void setStdSalePrice(BigDecimal stdSalePrice) {
		this.stdSalePrice = stdSalePrice;
	}

	public BigDecimal getSaleAmt() {
		return saleAmt;
	}

	public void setSaleAmt(BigDecimal saleAmt) {
		this.saleAmt = saleAmt;
	}

	public BigDecimal getRealSaleAmt() {
		return realSaleAmt;
	}

	public void setRealSaleAmt(BigDecimal realSaleAmt) {
		this.realSaleAmt = realSaleAmt;
	}

	public FbmSellDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FbmSellDetail(boolean defaultValue) {
		super();
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
