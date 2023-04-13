package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.ifbc.costcard.model.ScmMaterialOrGroupAdvQuery; 

@XmlRootElement(name = "scmMaterialCostCardTypeWSBean")
public class ScmMaterialCostCardTypeWSBean extends BaseWSBean {
	
	private ScmMaterialCostCardType2 x;
	private ScmMaterialOrGroupAdvQuery y;
	private ScmMaterialCostCardTypeDetail2 z;
	
	public ScmMaterialCostCardType2 getX() {
		return x;
	}
	
	public void setX(ScmMaterialCostCardType2 x) {
		this.x = x;
	}

	public ScmMaterialOrGroupAdvQuery getY() {
		return y;
	}

	public void setY(ScmMaterialOrGroupAdvQuery y) {
		this.y = y;
	}

	public ScmMaterialCostCardTypeDetail2 getZ() {
		return z;
	}

	public void setZ(ScmMaterialCostCardTypeDetail2 z) {
		this.z = z;
	}

}