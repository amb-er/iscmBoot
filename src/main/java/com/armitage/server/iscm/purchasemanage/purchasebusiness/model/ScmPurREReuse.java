package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmPurREReuse")  
public class ScmPurREReuse extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_ENTRYBILLID ="entryBillId";
    public static final String FN_OPINION ="opinion";
    public static final String FN_OPER ="oper";
    public static final String FN_FLAG ="flag";
    public static final String FN_OPERDATE ="operDate";
    public static final String FN_ROWSTATUS ="rowStatus";
    
    private long id;
    private long entryBillId;
    private String opinion;
    private String oper;
    private boolean flag;
    private Date operDate;
    private String rowStatus;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getEntryBillId() {
        return entryBillId;
    }

    public void setEntryBillId(long val) {
        this.entryBillId = val;
    }
    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String val) {
        this.opinion = val;
    }
    public String getOper() {
        return oper;
    }

    public void setOper(String val) {
        this.oper = val;
    }
    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean val) {
        this.flag = val;
    }
    public Date getOperDate() {
        return operDate;
    }

    public void setOperDate(Date val) {
        this.operDate = val;
    }

    public String getRowStatus() {
		return rowStatus;
	}

	public void setRowStatus(String rowStatus) {
		this.rowStatus = rowStatus;
	}

	public ScmPurREReuse(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmPurREReuse() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_ENTRYBILLID,
            FN_OPINION,
            FN_OPER,
            FN_FLAG,
            FN_OPERDATE,
            FN_ROWSTATUS,
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
		return null;
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
        map.put(FN_OPINION, 765);
        map.put(FN_OPER, 48);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
