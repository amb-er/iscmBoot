
package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmPurReturnsWSBean")
public class ScmPurReturnsWSBean extends BaseWSBean {
	
	private ScmPurReturns x;
	private ScmPurReturns2 y;
	private ScmPurReturnsEntry2 z;
	private ScmPurReceiveEntry2 a;
	private ScmPurAuditParam b;
	private ScmPurReturnsAdvQuery c;
	private CommonAuditParams d;

	public ScmPurReceiveEntry2 getA() {
		return a;
	}

	public void setA(ScmPurReceiveEntry2 a) {
		this.a = a;
	}

	public ScmPurReturns getX() {
		return x;
	}
	
	public void setX(ScmPurReturns x) {
		this.x = x;
	}

	public ScmPurReturns2 getY() {
		return y;
	}

	public void setY(ScmPurReturns2 y) {
		this.y = y;
	}

	public ScmPurReturnsEntry2 getZ() {
		return z;
	}

	public void setZ(ScmPurReturnsEntry2 z) {
		this.z = z;
	}

	public ScmPurAuditParam getB() {
		return b;
	}

	public void setB(ScmPurAuditParam b) {
		this.b = b;
	}

	public ScmPurReturnsAdvQuery getC() {
		return c;
	}

	public void setC(ScmPurReturnsAdvQuery c) {
		this.c = c;
	}

	public CommonAuditParams getD() {
		return d;
	}

	public void setD(CommonAuditParams d) {
		this.d = d;
	}

}