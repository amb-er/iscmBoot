package com.armitage.server.ifbc.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.basedata.dao.ScmResOrgUnitMapDAO;
import com.armitage.server.ifbc.basedata.model.ScmResOrgUnitMap;
import com.armitage.server.ifbc.basedata.service.ScmResOrgUnitMapBiz;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgResource2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgResourceBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import org.springframework.stereotype.Service;

@Service("scmResOrgUnitMapBiz")
public class ScmResOrgUnitMapBizImpl extends BaseBizImpl<ScmResOrgUnitMap> implements ScmResOrgUnitMapBiz {
	private OrgResourceBiz orgResourceBiz;
	private OrgUnitBiz orgUnitBiz;
	private OrgStorageBiz orgStorageBiz;	
	
	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}

	public void setOrgResourceBiz(OrgResourceBiz orgResourceBiz) {
		this.orgResourceBiz = orgResourceBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page, HashMap<String, Object> map, Param param) {
		map.put("controlUnitNo", param.getControlUnitNo());
		return map;
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list!=null && !list.isEmpty()) {
			for(ScmResOrgUnitMap ScmResOrgUnitMap:(List<ScmResOrgUnitMap>)list) {
				this.setConvertMap(ScmResOrgUnitMap, param);
			}
		}
	}

	private void setConvertMap(ScmResOrgUnitMap ScmResOrgUnitMap,Param param) {
		if(ScmResOrgUnitMap!=null) {
			if(StringUtils.isNotBlank(ScmResOrgUnitMap.getResOrgUnitNo())) {
				OrgResource2 orgResource = orgResourceBiz.selectByOrgUnitNo(ScmResOrgUnitMap.getResOrgUnitNo(), param);
				if(orgResource!=null) {
					ScmResOrgUnitMap.setConvertMap(ScmResOrgUnitMap.FN_RESORGUNITNO, orgResource);
				}
			}
			if(StringUtils.isNotBlank(ScmResOrgUnitMap.getInvOrgUnitNo())) {
				OrgStorage2 selectByOrgUnitNo = orgStorageBiz.selectByOrgUnitNo(ScmResOrgUnitMap.getResOrgUnitNo(), param);
				if(selectByOrgUnitNo!=null) {
					ScmResOrgUnitMap.setConvertMap(ScmResOrgUnitMap.FN_INVORGUNITNO, selectByOrgUnitNo);
				}
			}
		}
	}

	@Override
	public ScmResOrgUnitMap selectByResOrgUnit(String resOrgUnitNo, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("controlUnitNo", param.getControlUnitNo());
		map.put("resOrgUnitNo", resOrgUnitNo);
		return ((ScmResOrgUnitMapDAO)dao).selectByResOrgUnit(map);
	}
	
	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		QueryParam queryParam = page.getParamOr().get("ScmResOrgUnitMap.resOrgUnitNo");
		if (queryParam != null) {
			String value = queryParam.getValue();
			Page page2 = new Page();
			page2.setModelClass(OrgBaseUnit.class);
			page2.setShowCount(Integer.MAX_VALUE);
			page2.setSqlCondition("OrgBaseUnit.orgUnitNo like '%"+value+"%' or OrgBaseUnit.orgUnitName like '%"+value+"%'");
			List<OrgBaseUnit> orgBaseUnits = orgUnitBiz.findPage(page2, param);
			StringBuffer stringBuffer = new StringBuffer();
			if (orgBaseUnits != null && !orgBaseUnits.isEmpty()) {
				for (OrgBaseUnit orgBaseUnit : orgBaseUnits) {
					if (StringUtils.isNotBlank(stringBuffer.toString())) {
						stringBuffer.append(",");
					}
					stringBuffer.append("'").append(orgBaseUnit.getOrgUnitNo()).append("'");
				}
			}
			if (StringUtils.isEmpty(stringBuffer.toString())) {
				stringBuffer.append("0");
			}
			page.setParamOr(new HashMap<>());
			page.getParam().put(ScmResOrgUnitMap.FN_RESORGUNITNO, new QueryParam(ScmResOrgUnitMap.FN_RESORGUNITNO, QueryParam.QUERY_IN, stringBuffer.toString()));
		}
	}
}
