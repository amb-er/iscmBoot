package com.armitage.server.iscm.basedata.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmMaterialUnitRelationWSBean")
public class ScmMaterialUnitRelationWSBean extends BaseWSBean {
	
	private ScmMaterialUnitRelation x;
	private ScmMaterialUnitRelation2 y;
	private ScmMaterial2 z;
	
	public ScmMaterialUnitRelation getX() {
		return x;
	}
	
	public void setX(ScmMaterialUnitRelation x) {
		this.x = x;
	}

	public ScmMaterialUnitRelation2 getY() {
		return y;
	}

	public void setY(ScmMaterialUnitRelation2 y) {
		this.y = y;
	}

	public ScmMaterial2 getZ() {
		return z;
	}

	public void setZ(ScmMaterial2 z) {
		this.z = z;
	}

}
