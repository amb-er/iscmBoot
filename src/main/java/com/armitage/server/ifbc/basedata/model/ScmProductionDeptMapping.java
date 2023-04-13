package com.armitage.server.ifbc.basedata.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmProductionDeptMapping")  
public class ScmProductionDeptMapping extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_PRODUCTDEPTID ="productDeptId";
    public static final String FN_DEPTNO ="deptNo";
    
    private long id;
    private long productDeptId;
    private String deptNo;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getProductDeptId() {
        return productDeptId;
    }

    public void setProductDeptId(long val) {
        this.productDeptId = val;
    }
    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String val) {
        this.deptNo = val;
    }

    public ScmProductionDeptMapping(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmProductionDeptMapping() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_PRODUCTDEPTID,
            FN_DEPTNO,
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
        map.put(FN_DEPTNO, 32);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}