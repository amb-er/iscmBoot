package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
 
@XmlRootElement(name = "scmPurAuditParam")  
public class ScmPurAuditParam extends BaseModel  {
	 
    public static final String FN_OPINION ="opinion";
    public static final String FN_REMARKS ="remarks";
    
    private String opinion;
    private String remarks;

    
    public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public ScmPurAuditParam(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmPurAuditParam() {

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
