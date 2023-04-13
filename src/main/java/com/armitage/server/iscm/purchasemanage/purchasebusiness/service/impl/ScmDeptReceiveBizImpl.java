package com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmDeptReceiveBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReceiveBiz;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.service.OrgAdminBiz;
import org.springframework.stereotype.Service;

@Service("scmDeptReceiveBiz")
public class ScmDeptReceiveBizImpl extends BaseBizImpl<ScmPurReceive2> implements ScmDeptReceiveBiz {
	private ScmPurReceiveBiz scmPurReceiveBiz;
	private OrgAdminBiz orgAdminBiz;
	
	public void setScmPurReceiveBiz(ScmPurReceiveBiz scmPurReceiveBiz) {
		this.scmPurReceiveBiz = scmPurReceiveBiz;
	}

	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		List<OrgAdmin2> orgAdminList = orgAdminBiz.findChild(param.getOrgUnitNo(), param);
		if (orgAdminList != null && !orgAdminList.isEmpty()) {
			StringBuffer orgunitList = new StringBuffer("");
			for (OrgAdmin2 orgAdmin : orgAdminList) {
				if(StringUtils.isNotBlank(orgunitList.toString()))
					orgunitList.append(",");
				orgunitList.append("'").append(orgAdmin.getOrgUnitNo()).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." + ScmPurReceive2.FN_USEORGUNITNOS), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." + ScmPurReceive2.FN_USEORGUNITNOS), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." + ScmPurReceive2.FN_USEORGUNITNOS), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurReceive2.class) + "." + ScmPurReceive2.FN_USEORGUNITNOS), QueryParam.QUERY_EQ, "0"));
		}
	}
	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		if(list != null && !list.isEmpty()){
			for(ScmPurReceive2 scmPurReceive : (List<ScmPurReceive2>)list){
				scmPurReceiveBiz.setConvertMap(scmPurReceive,param);
				if("I,R".contains(scmPurReceive.getStatus())) {
					scmPurReceive.setPendingUsrName("");
				}
			} 
		}
	}

	@Override
	public void doAdvQuery(Page page, Param param) {
		scmPurReceiveBiz.doAdvQuery(page, param);
	}

	@Override
	public List<ScmPurReceive2> queryPurReceiveList(ScmPurReceiveAdvQuery scmPurReceiveAdvQuery, int pageNum,
			Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(ScmPurReceive2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		page.setModel(scmPurReceiveAdvQuery);	//findPage时底层会触发doAdvQuery，增加查询条件
		page.setSqlCondition("(scmpurreceive.unified=0 or Exists(Select 1 From scmpurcheck Where id=scmpurreceive.ckId And flag=1))");
		
		return this.findPage(page, param);
	}
}

