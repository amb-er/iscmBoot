package com.armitage.server.ifbc.costcard.model;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "scmItemCostPriceLogDetail2")  
public class ScmItemCostPriceLogDetail2 extends ScmItemCostPriceLogDetail  {
	 
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	
    private String itemNo;
    private String itemName;
    
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
	public ScmItemCostPriceLogDetail2() {
		super();
	}
	public ScmItemCostPriceLogDetail2(boolean defaultValue) {
		super(defaultValue);
	}

}
