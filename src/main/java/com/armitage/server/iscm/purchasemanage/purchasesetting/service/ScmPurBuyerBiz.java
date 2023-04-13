
package com.armitage.server.iscm.purchasemanage.purchasesetting.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyerOrg2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyerWSBean;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurGroup;

public interface ScmPurBuyerBiz extends BaseBiz<ScmPurBuyer2> {

	public ScmPurGroup updateGroup(ScmPurGroup entity, Param param) throws AppException;
	
	public List<ScmPurBuyer> selectByParentId(Long parentId,Param param) throws AppException;
	public ScmPurBuyer2 selectByCode(String buyerCode, Param param) throws AppException;
	
	
	public List<String> deleteBuyerCheck(List<ScmPurBuyer2> scmPurBuyerList, Param param) throws AppException;

	public ScmPurBuyerWSBean getGrantedOrg(ScmPurBuyer2 scmPurBuyer, Param param) throws AppException;

	public void grantOrgUnit(ScmPurBuyer2 scmPurBuyer, List<ScmPurBuyerOrg2> scmPurBuyerOrgList, Param param) throws AppException;
	
}
