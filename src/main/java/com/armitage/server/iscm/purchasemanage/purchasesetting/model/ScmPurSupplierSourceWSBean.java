
package com.armitage.server.iscm.purchasemanage.purchasesetting.model;

import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;

@XmlRootElement(name = "scmPurSupplierSourceWSBean")
public class ScmPurSupplierSourceWSBean extends BaseWSBean {
	
	private ScmPurSupplierSource2 x;
	private ScmPurSupplierSourceRecOrg2 y;
	private ScmPurSupplierSourceEntry2 z;
	private CommonAuditParams b;

	public ScmPurSupplierSource2 getX() {
		return x;
	}

	public void setX(ScmPurSupplierSource2 x) {
		this.x = x;
	}

	public ScmPurSupplierSourceRecOrg2 getY() {
		return y;
	}

	public void setY(ScmPurSupplierSourceRecOrg2 y) {
		this.y = y;
	}

	public ScmPurSupplierSourceEntry2 getZ() {
		return z;
	}

	public void setZ(ScmPurSupplierSourceEntry2 z) {
		this.z = z;
	}

	public CommonAuditParams getB() {
		return b;
	}

	public void setB(CommonAuditParams b) {
		this.b = b;
	}
	
}