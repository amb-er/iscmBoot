
package com.armitage.server.iscm.basedata.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmIdleCauseWSBean")
public class ScmIdleCauseWSBean extends BaseWSBean {
	
	private ScmIdleCause x;
	
	public ScmIdleCause getX() {
		return x;
	}
	
	public void setX(ScmIdleCause x) {
		this.x = x;
	}

}