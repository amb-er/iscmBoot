package com.armitage.server.ifbc.costcard.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
public class ScmDishReplaceCostCardDetail extends BaseModel  {
	
    private boolean choosed;
    private boolean cook;
    private long cardId;
    private String code;
    private String name;
	private BigDecimal qty;
    private boolean locked;
	
	public boolean isChoosed() {
		return choosed;
	}

	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}

	public boolean isCook() {
		return cook;
	}

	public void setCook(boolean cook) {
		this.cook = cook;
	}

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public ScmDishReplaceCostCardDetail() {

	}
  	
	public ScmDishReplaceCostCardDetail(boolean defaultValue) {
		if (defaultValue) {
			qty = BigDecimal.ZERO;
			choosed=false;
		}
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
