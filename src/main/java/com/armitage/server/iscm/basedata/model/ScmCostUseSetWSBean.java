/**
 * 广州万迅电脑软件有限公司(c)
 * @author Promise
 * 2020年12月16日 下午5:43:47
 *
 */
package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "scmCostUseSetWSBean")
public class ScmCostUseSetWSBean extends BaseWSBean {

	private ScmCostUseSet x;
	private ScmCostUseSet2 y;
	private ScmCostUseSetAdvQuery zAdvQuery;
	private ScmMaterialGroup2 z;
	
	public ScmCostUseSetAdvQuery getzAdvQuery() {
		return zAdvQuery;
	}

	public void setzAdvQuery(ScmCostUseSetAdvQuery zAdvQuery) {
		this.zAdvQuery = zAdvQuery;
	}

	public ScmCostUseSet2 getY() {
		return y;
	}

	public void setY(ScmCostUseSet2 y) {
		this.y = y;
	}

	public ScmCostUseSet getX() {
		return x;
	}

	public void setX(ScmCostUseSet x) {
		this.x = x;
	}

	public ScmMaterialGroup2 getZ() {
		return z;
	}

	public void setZ(ScmMaterialGroup2 z) {
		this.z = z;
	}

}
