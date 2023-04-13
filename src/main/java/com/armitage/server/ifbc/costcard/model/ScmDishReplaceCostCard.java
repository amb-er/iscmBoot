package com.armitage.server.ifbc.costcard.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
public class ScmDishReplaceCostCard extends BaseModel  {
	
	public static final String FN_CARDID ="cardId";
	public static final String FN_FBMITEMCODE ="fbmItemCode";
	public static final String FN_TYPESCOPE ="typeScope";
	public static final String FN_INCLUDECOOK ="includeCook";
	public static final String FN_ITEMID ="itemId";
	public static final String FN_ITEMNO ="itemNo";
    public static final String FN_ITEMNAME ="itemName";
    public static final String FN_CTSUNITNAME ="cstUnitName";
    public static final String FN_CTSUNIT ="cstUnit";
    public static final String FN_REPLACEITEMID ="replaceItemId";
    public static final String FN_REPLACEITEMNO ="replaceItemNo";
    public static final String FN_REPLACEITEMNAME ="replaceItemName";
    public static final String FN_REPLACECTSUNITNAME = "replaceCstUnitName";
    public static final String FN_REPLACECTSUNIT = "replaceCstUnit";
    public static final String FN_REPLACEINVUNIT = "replaceInvUnit";
    public static final String FN_REPLACECONVRATE = "replaceConvrate";
    public static final String FN_REPLACEPRICE = "replacePrice";
	
    private long cardId;
    private String fbmItemCode;
    private String typeScope;
    private boolean includeCook;
    private long itemId;
    private String itemNo;
    private String itemName;
    private String cstUnitName;
    private long cstUnit;
    private long replaceItemId;
    private String replaceItemNo;
    private String replaceItemName;
    private String replaceCstUnitName;
    private long replaceCstUnit;
    private long replaceInvUnit;
    private BigDecimal replaceConvrate;
    private BigDecimal replacePrice;
	
  	public long getCardId() {
		return cardId;
	}
	public void setCardId(long cardId) {
		this.cardId = cardId;
	}
	public String getFbmItemCode() {
		return fbmItemCode;
	}
	public void setFbmItemCode(String fbmItemCode) {
		this.fbmItemCode = fbmItemCode;
	}
	public String getTypeScope() {
		return typeScope;
	}
	public void setTypeScope(String typeScope) {
		this.typeScope = typeScope;
	}
	public boolean isIncludeCook() {
		return includeCook;
	}
	public void setIncludeCook(boolean includeCook) {
		this.includeCook = includeCook;
	}
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
	public String getCstUnitName() {
		return cstUnitName;
	}
	public void setCstUnitName(String cstUnitName) {
		this.cstUnitName = cstUnitName;
	}
	public long getCstUnit() {
		return cstUnit;
	}
	public void setCstUnit(long cstUnit) {
		this.cstUnit = cstUnit;
	}
	public long getReplaceItemId() {
		return replaceItemId;
	}
	public void setReplaceItemId(long replaceItemId) {
		this.replaceItemId = replaceItemId;
	}
	public String getReplaceItemNo() {
		return replaceItemNo;
	}
	public void setReplaceItemNo(String replaceItemNo) {
		this.replaceItemNo = replaceItemNo;
	}
	public String getReplaceItemName() {
		return replaceItemName;
	}
	public void setReplaceItemName(String replaceItemName) {
		this.replaceItemName = replaceItemName;
	}
	public String getReplaceCstUnitName() {
		return replaceCstUnitName;
	}
	public void setReplaceCstUnitName(String replaceCstUnitName) {
		this.replaceCstUnitName = replaceCstUnitName;
	}
	public long getReplaceCstUnit() {
		return replaceCstUnit;
	}
	public void setReplaceCstUnit(long replaceCstUnit) {
		this.replaceCstUnit = replaceCstUnit;
	}
	public long getReplaceInvUnit() {
		return replaceInvUnit;
	}
	public void setReplaceInvUnit(long replaceInvUnit) {
		this.replaceInvUnit = replaceInvUnit;
	}
	public BigDecimal getReplaceConvrate() {
		return replaceConvrate;
	}
	public void setReplaceConvrate(BigDecimal replaceConvrate) {
		this.replaceConvrate = replaceConvrate;
	}
	public BigDecimal getReplacePrice() {
		return replacePrice;
	}
	public void setReplacePrice(BigDecimal replacePrice) {
		this.replacePrice = replacePrice;
	}
	public ScmDishReplaceCostCard() {

	}
  	
	public ScmDishReplaceCostCard(boolean defaultValue) {
		if (defaultValue) {
			typeScope = "C";	//当前菜品类别
			includeCook=false;
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
