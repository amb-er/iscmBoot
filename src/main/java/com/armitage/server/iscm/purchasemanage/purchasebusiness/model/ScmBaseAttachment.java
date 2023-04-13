
package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmBaseAttachment")  
public class ScmBaseAttachment extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_BILLID ="billId";
    public static final String FN_DOCTYPE ="docType";
    public static final String FN_FILENAME ="fileName";
    public static final String FN_FILEPATH ="filePath";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATETIME ="createTime";
    public static final String FN_BILLTYPE ="billtype";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private long billId;
    private String docType;
    private String fileName;
    private String filePath;
    private String creator;
    private Date createTime;
    private String billtype;
    private String controlUnitNo;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }

 	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}

	public String getDocType() {
        return docType;
    }

    public void setDocType(String val) {
        this.docType = val;
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String val) {
        this.fileName = val;
    }
    
    public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getCreator() {
        return creator;
    }

    public void setCreator(String val) {
        this.creator = val;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date val) {
        this.createTime = val;
    }
    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String val) {
        this.billtype = val;
    }
    public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public ScmBaseAttachment(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmBaseAttachment() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_BILLID,
            FN_DOCTYPE,
            FN_FILENAME,
            FN_FILEPATH,
            FN_CREATOR,
            FN_CREATETIME,
            FN_BILLTYPE,
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
        map.put(FN_DOCTYPE, 16);
        map.put(FN_FILENAME, 128);
        map.put(FN_FILEPATH, 255);
        map.put(FN_CREATOR, 16);
        map.put(FN_BILLTYPE, 16);
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
