package com.armitage.server.iscm.purchasemanage.purchasesetting.model;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
 
@XmlRootElement(name = "scmPurSupplyInfoAdvQuery")  
public class ScmPurSupplyInfoAdvQuery extends BaseModel  {
	 
    public static final String FN_VENDORID ="vendorId";
    public static final String FN_ITEMID ="itemId";
    
    private long vendorId;
    private long itemId;

    public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public static String getFnVendorid() {
		return FN_VENDORID;
	}

	public static String getFnItemid() {
		return FN_ITEMID;
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
