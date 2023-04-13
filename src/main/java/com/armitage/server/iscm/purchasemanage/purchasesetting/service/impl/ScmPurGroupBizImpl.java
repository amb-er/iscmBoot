
package com.armitage.server.iscm.purchasemanage.purchasesetting.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurGroupDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurGroup;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurGroupBiz;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmPurGroupBiz")
public class ScmPurGroupBizImpl extends BaseBizImpl<ScmPurGroup> implements ScmPurGroupBiz {

	private static Log log = LogFactory.getLog(ScmPurGroupBizImpl.class);
	private UsrBiz usrBiz;
	private OrgPurchaseBiz orgPurchaseBiz;
	
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}
	
	public void setOrgPurchaseBiz(OrgPurchaseBiz orgPurchaseBiz) {
		this.orgPurchaseBiz = orgPurchaseBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurGroup.class) + "." + ScmPurGroup.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurGroup.class) + "." + ScmPurGroup.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurGroup.class) + "." + ScmPurGroup.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurGroup.class) + "." + ScmPurGroup.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}
	
	@Override
	protected void afterSelect(ScmPurGroup entity, Param param) throws AppException {
		if (entity != null){
			HashMap<String,Object> cacheMap = new HashMap<String,Object>();
			if (StringUtils.isNotBlank(entity.getCreator())){
				//建档人
				Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+entity.getCreator());
				if(usr==null){
					usr = usrBiz.selectByCode(entity.getCreator(), param);
					cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+entity.getCreator(),usr);
				}
				if (usr != null) {
					entity.setConvertMap(ScmPurGroup.FN_CREATOR, usr);
				}
			}
			if (StringUtils.isNotBlank(entity.getEditor())){
				//修改人
				Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+entity.getEditor());
				if(usr==null){
					usr = usrBiz.selectByCode(entity.getEditor(), param);
					cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+entity.getEditor(),usr);
				}
				if (usr != null) {
					entity.setConvertMap(ScmPurGroup.FN_EDITOR, usr);
				}
			}
		}
	}

	@Override
	public List<ScmPurGroup> selectByPurGroupNo(ScmPurGroup scmPurGroup, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("purGroupNo", scmPurGroup.getPurGroupNo());
		map.put("id", scmPurGroup.getId());
		map.put("orgUnitNo", param.getOrgUnitNo());
		List<ScmPurGroup> list=((ScmPurGroupDAO) dao).selectByPurGroupNo(map);
		return list;
	}
	
}
