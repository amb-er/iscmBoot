
package com.armitage.server.iscm.basedata.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
public class ScmMaterialAdd  {
	 
    public static final String FN_CLASSCODE1 ="classCode1";
    public static final String FN_CLASSNAME1 ="className1";
    public static final String FN_CLASSCODE2 ="classCode2";
    public static final String FN_CLASSNAME2 ="className2";
    public static final String FN_CLASSCODE3 ="classCode3";
    public static final String FN_CLASSNAME3 ="className3";
    public static final String FN_ITEMNO ="itemNo";
    public static final String FN_ITEMNAME ="itemName";
    public static final String FN_SPEC ="spec";
    public static final String FN_BASEUNITID ="baseUnitId";
    public static final String FN_PURUNITID ="purUnitId";
    public static final String FN_INVUNITID ="invUnitId";
    public static final String FN_BASEUNITNAME ="baseUnitName";
    public static final String FN_PURUNITNAME ="purUnitName";
    public static final String FN_INVUNITName ="invUnitName";
    public static final String FN_PURCONVRATE ="purConvrate";
    public static final String FN_INVCONVRATE ="invConvrate";
    public static final String FN_DEFAULTRATE ="defaultRate";
    public static final String FN_GROUPID = "groupId";
    public static final String FN_BRANDID = "brandId";
    
    private String classCode1;
    private String className1;
    private String classCode2;
    private String className2;
    private String classCode3;
    private String className3;
    private String itemNo;
    private String itemName;
    private String spec;
    private long baseUnitId;
    private long purUnitId;
    private long invUnitId;
    private String baseUnitName;
    private String purUnitName;
    private String invUnitName;
    private BigDecimal purConvrate;
    private BigDecimal invConvrate;
    private BigDecimal defaultRate;
    private long groupId;
    private boolean checkSuccess;
    private long brandId;
    

    public long getBrandId() {
		return brandId;
	}
	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}
	public boolean isCheckSuccess() {
		return checkSuccess;
	}
	public void setCheckSuccess(boolean checkSuccess) {
		this.checkSuccess = checkSuccess;
	}
	public String getClassCode1() {
		return classCode1;
	}
	public void setClassCode1(String classCode1) {
		this.classCode1 = classCode1;
	}
	public String getClassName1() {
		return className1;
	}
	public void setClassName1(String className1) {
		this.className1 = className1;
	}
	public String getClassCode2() {
		return classCode2;
	}
	public void setClassCode2(String classCode2) {
		this.classCode2 = classCode2;
	}
	public String getClassName2() {
		return className2;
	}
	public void setClassName2(String className2) {
		this.className2 = className2;
	}
	public String getClassCode3() {
		return classCode3;
	}
	public void setClassCode3(String classCode3) {
		this.classCode3 = classCode3;
	}
	public String getClassName3() {
		return className3;
	}
	public void setClassName3(String className3) {
		this.className3 = className3;
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
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public long getBaseUnitId() {
		return baseUnitId;
	}
	public void setBaseUnitId(long baseUnitId) {
		this.baseUnitId = baseUnitId;
	}
	public long getPurUnitId() {
		return purUnitId;
	}
	public void setPurUnitId(long purUnitId) {
		this.purUnitId = purUnitId;
	}
	public long getInvUnitId() {
		return invUnitId;
	}
	public void setInvUnitId(long invUnitId) {
		this.invUnitId = invUnitId;
	}
	public String getBaseUnitName() {
		return baseUnitName;
	}
	public void setBaseUnitName(String baseUnitName) {
		this.baseUnitName = baseUnitName;
	}
	public String getPurUnitName() {
		return purUnitName;
	}
	public void setPurUnitName(String purUnitName) {
		this.purUnitName = purUnitName;
	}
	public String getInvUnitName() {
		return invUnitName;
	}
	public void setInvUnitName(String invUnitName) {
		this.invUnitName = invUnitName;
	}
	public BigDecimal getPurConvrate() {
		return purConvrate;
	}
	public void setPurConvrate(BigDecimal purConvrate) {
		this.purConvrate = purConvrate;
	}
	public BigDecimal getInvConvrate() {
		return invConvrate;
	}
	public void setInvConvrate(BigDecimal invConvrate) {
		this.invConvrate = invConvrate;
	}
	public BigDecimal getDefaultRate() {
		return defaultRate;
	}
	public void setDefaultRate(BigDecimal defaultRate) {
		this.defaultRate = defaultRate;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	
	public ScmMaterialAdd() {

	}
	
	public ScmMaterialAdd(boolean defaultValue) {
		if(defaultValue){
			this.checkSuccess=true;
		}
	}
}
