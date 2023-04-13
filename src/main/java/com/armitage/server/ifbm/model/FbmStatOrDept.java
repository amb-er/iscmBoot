package com.armitage.server.ifbm.model;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "fbmStatOrDept")  
public class FbmStatOrDept extends BaseModel  {
    public static final String FN_CODE ="code";
    public static final String FN_NAME ="name";

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String val) {
        this.code = val;
    }
    public String getName() {
        return name;
    }

    public void setName(String val) {
        this.name = val;
    }

    public FbmStatOrDept(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public FbmStatOrDept() {

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
