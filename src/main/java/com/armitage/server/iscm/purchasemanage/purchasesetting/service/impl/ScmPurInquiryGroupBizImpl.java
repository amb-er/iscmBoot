
package com.armitage.server.iscm.purchasemanage.purchasesetting.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurInquiryGroupDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurInquiryGroup;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurInquiryGroupBiz;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmPurInquiryGroupBiz")
public class ScmPurInquiryGroupBizImpl extends BaseBizImpl<ScmPurInquiryGroup> implements ScmPurInquiryGroupBiz {
	private UsrBiz usrBiz;
	private OrgPurchaseBiz orgPurchaseBiz;
	
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}
	
	public void setOrgPurchaseBiz(OrgPurchaseBiz orgPurchaseBiz) {
		this.orgPurchaseBiz = orgPurchaseBiz;
	}

	@Override
	protected void beforeAdd(ScmPurInquiryGroup entity, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNo", param.getOrgUnitNo());
		map.put("groupNo", entity.getGroupNo());
		ScmPurInquiryGroup scmPurInquiryGroup = ((ScmPurInquiryGroupDAO)dao).selectByGroupNo(map);
		if(scmPurInquiryGroup!=null)
			throw new AppException("iscm.purchasemanage.purchasesetting.ScmPurInquiryGroup.DuplicateNo");
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		List<OrgPurchase2> orgPurchaseList = orgPurchaseBiz.findChild(param.getOrgUnitNo(), param);
		if (orgPurchaseList != null && !orgPurchaseList.isEmpty()) {
			StringBuffer orgunitList = new StringBuffer("");
			for (OrgPurchase2 orgPurchase : orgPurchaseList) {
				if(StringUtils.isNotBlank(orgunitList.toString()))
					orgunitList.append(",");
				orgunitList.append("'").append(orgPurchase.getOrgUnitNo()).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurInquiryGroup.class) + "." + ScmPurInquiryGroup.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurInquiryGroup.class) + "." + ScmPurInquiryGroup.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurInquiryGroup.class) + "." + ScmPurInquiryGroup.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurInquiryGroup.class) + "." + ScmPurInquiryGroup.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}

	@Override
	protected void afterSelect(ScmPurInquiryGroup entity, Param param) throws AppException {
		if(entity!=null){
			this.setConvertMap(entity, param);
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list!=null && !list.isEmpty()){
			for(ScmPurInquiryGroup scmPurInquiryGroup:(List<ScmPurInquiryGroup>)list){
				this.setConvertMap(scmPurInquiryGroup, param);
			}
		}
	}

	private void setConvertMap(ScmPurInquiryGroup scmPurInquiryGroup,Param param){
		if(scmPurInquiryGroup!=null){
			if(StringUtils.isNotBlank(scmPurInquiryGroup.getChargeBy())){
				Usr usr = usrBiz.selectByCode(scmPurInquiryGroup.getChargeBy(), param);
				if(usr!=null)
					scmPurInquiryGroup.setConvertMap(ScmPurInquiryGroup.FN_CHARGEBY, usr);
			}
			if(StringUtils.isNotBlank(scmPurInquiryGroup.getCreator())){
				Usr usr = usrBiz.selectByCode(scmPurInquiryGroup.getCreator(), param);
				if(usr!=null)
					scmPurInquiryGroup.setConvertMap(ScmPurInquiryGroup.FN_CREATOR, usr);
			}
			if(StringUtils.isNotBlank(scmPurInquiryGroup.getEditor())){
				Usr usr = usrBiz.selectByCode(scmPurInquiryGroup.getEditor(), param);
				if(usr!=null)
					scmPurInquiryGroup.setConvertMap(ScmPurInquiryGroup.FN_EDITOR, usr);
			}
		}
	}
}
