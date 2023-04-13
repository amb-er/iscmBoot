package com.armitage.server.iscm.basedata.model;

public class ScmIndustryGroupQualifyType2 extends ScmIndustryGroupQualifyType{
	public static final String FN_TYPECODE = "typeCode";			//资质种类编码
	public static final String FN_TYPENAME = "typeName";			//资质种类名称
	public static final String FN_AUDITED = "audited";
	
	private String typeCode;
	private String typeName;
	private boolean audited;

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public boolean isAudited() {
		return audited;
	}

	public void setAudited(boolean audited) {
		this.audited = audited;
	}

	public ScmIndustryGroupQualifyType2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			this.audited=false;
		}
	}
	
	public ScmIndustryGroupQualifyType2(){
		super();
	}
}
