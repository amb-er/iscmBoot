/**
 * 广州万迅电脑软件有限公司(c)
 * @author Promise
 * 2020年12月16日 下午4:51:39
 *
 */
package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "scmCostUseSetWSBean")
public class ScmCostUseTypeWSBean extends BaseWSBean{
	
	private ScmCostUseType x;

	public ScmCostUseType getX() {
		return x;
	}

	public void setX(ScmCostUseType x) {
		this.x = x;
	}
	
	

}

