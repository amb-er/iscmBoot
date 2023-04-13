/**
 * 广州万迅电脑软件有限公司(c)
 * @author Promise
 * 2020年12月16日 下午5:57:15
 *
 */
package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iscm.basedata.dao.ScmCostUseSetDao;
import com.armitage.server.iscm.basedata.model.ScmCostUseSet2;
import com.armitage.server.iscm.basedata.model.ScmCostUseSetAdvQuery;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup2;
import com.armitage.server.iscm.basedata.service.ScmCostUseSetBiz;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.service.OrgCompanyBiz;
import com.armitage.server.system.service.OrgCostCenterBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.github.houbb.heaven.util.secrect.Md5Util;
import org.springframework.stereotype.Service;

@Service("scmCostUseSetBiz")
public class ScmCostUseSetBizImpl extends BaseBizImpl<ScmCostUseSet2> implements ScmCostUseSetBiz {
	
	private OrgUnitBiz orgUnitBiz;
	private OrgCostCenterBiz orgCostCenterBiz;
	private SysParamBiz sysParamBiz;
	private OrgCompanyBiz orgCompanyBiz;
	
	public SysParamBiz getSysParamBiz() {
		return sysParamBiz;
	}

	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}
	
	public void setOrgCostCenterBiz(OrgCostCenterBiz orgCostCenterBiz) {
        this.orgCostCenterBiz = orgCostCenterBiz;
    }
	
	public void setOrgCompanyBiz(OrgCompanyBiz orgCompanyBiz) {
		this.orgCompanyBiz = orgCompanyBiz;
	}

	@Override
	protected void beforeAdd(ScmCostUseSet2 scmCostUseSet, Param param) throws AppException {
		if(scmCostUseSet!=null) {
			OrgCostCenter2 orgCostCenter = orgCostCenterBiz.selectByOrgUnitNo(scmCostUseSet.getCostOrgUnitNo(), param);
			String accType =null;// orgCostCenter.getAccType();
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(scmCostUseSet.getCostOrgUnitNo()).append("_");
			stringBuffer.append(scmCostUseSet.getClassId());
			if(accType.equals("F")) {
				String checkMD5 = Md5Util.md5(stringBuffer.toString());
				ScmCostUseSet2 selectByRowMD = ((ScmCostUseSetDao) dao).selectByRowMD(checkMD5);
				if (selectByRowMD != null) {
					this.delete(selectByRowMD, param);
				}
				stringBuffer.append("_").append(scmCostUseSet.getCostUseTypeId());
			}
			String md5 = Md5Util.md5(stringBuffer.toString());
			stringBuffer.append("_").append(scmCostUseSet.getCostUseTypeId());
			String checkMD5 = Md5Util.md5(stringBuffer.toString());
			ScmCostUseSet2 selectByRowMD = ((ScmCostUseSetDao) dao).selectByRowMD(checkMD5.toString());
			if (selectByRowMD != null) {
				this.delete(selectByRowMD, param);
			}
			scmCostUseSet.setRowMD(md5);
		}
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		List<OrgCompany2> orgCompanyList = orgCompanyBiz.findChild(param.getOrgUnitNo(), param);
		if (orgCompanyList != null && !orgCompanyList.isEmpty()) {
			StringBuffer orgunitList = new StringBuffer("");
			for (OrgCompany2 orgCompany : orgCompanyList) {
				if(StringUtils.isNotBlank(orgunitList.toString()))
					orgunitList.append(",");
				orgunitList.append("'").append(orgCompany.getOrgUnitNo()).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmCostUseSet2.class) + "." + ScmCostUseSet2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmCostUseSet2.class) + "." + ScmCostUseSet2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmCostUseSet2.class) + "." + ScmCostUseSet2.FN_ORGUNITNO), 
	   				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmCostUseSet2.class) + "." + ScmCostUseSet2.FN_ORGUNITNO), QueryParam.QUERY_EQ, param.getOrgUnitNo()));
		}
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel()!=null) {
			if (page.getModel() instanceof ScmCostUseSetAdvQuery) {
				ScmCostUseSetAdvQuery setAdvQuery = (ScmCostUseSetAdvQuery) page.getModel();
				if (setAdvQuery.getClassId()!=0) {
					page.getParam().put(ScmCostUseSet2.FN_CLASSID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCostUseSet2.class) + "." +ScmCostUseSet2.FN_CLASSID, QueryParam.QUERY_EQ,String.valueOf(setAdvQuery.getClassId())));
				}
				if (setAdvQuery.getScmCostUseTypeId()!=0) {
					page.getParam().put(ScmCostUseSet2.FN_COSTUSETYPEID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCostUseSet2.class) + "." +ScmCostUseSet2.FN_COSTUSETYPEID, QueryParam.QUERY_EQ,String.valueOf(setAdvQuery.getScmCostUseTypeId())));
				}
				if (StringUtils.isNotBlank(setAdvQuery.getCostOrgUnitNo())) {
					page.getParam().put(ScmCostUseSet2.FN_COSTORGUNITNO, new QueryParam(ClassUtils.getFinalModelSimpleName(ScmCostUseSet2.class) + "." +ScmCostUseSet2.FN_COSTORGUNITNO, QueryParam.QUERY_EQ,setAdvQuery.getCostOrgUnitNo()));
				}
			}
		}
	}

	@Override
	public void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list != null && !list.isEmpty()){
			for (ScmCostUseSet2 scmCostUseSet : (List<ScmCostUseSet2>)list) {
				this.setConvertMap(scmCostUseSet, param);
			}
		}
	}
	
	private void setConvertMap(ScmCostUseSet2 scmCostUseSet, Param param) throws AppException {
		if (scmCostUseSet != null){
			if (StringUtils.isNotBlank(scmCostUseSet.getCostOrgUnitNo())){
				//采购组织
				OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmCostUseSet.getCostOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmCostUseSet.setOrgUnitName(orgBaseUnit.getOrgUnitName());
					scmCostUseSet.setConvertMap(ScmCostUseSet2.FN_COSTORGUNITNO, orgBaseUnit);
				}
			}
		}
	}

	@Override
	public void buildMap(ScmCostUseSet2 scmCostUseSet, List<ScmMaterialGroup2> scmMaterialGroupList, Param param) throws AppException {
		if(scmMaterialGroupList!=null && !scmMaterialGroupList.isEmpty()) {
			for(ScmMaterialGroup2 scmMaterialGroup:scmMaterialGroupList) {
				if(scmMaterialGroup.isCheckAll()) {
					if(StringUtils.isNotBlank(scmCostUseSet.getScmCostUseTypeIds())) {
						String useTypeIdList[] = StringUtils.split(scmCostUseSet.getScmCostUseTypeIds(), ",");
						for(String useTypeId:useTypeIdList) {
							ScmCostUseSet2 addScmCostUseSet = new ScmCostUseSet2(true);
							addScmCostUseSet.setOrgUnitNo(param.getOrgUnitNo());
							addScmCostUseSet.setCostUseTypeId(Long.valueOf(useTypeId));
							addScmCostUseSet.setClassId(scmMaterialGroup.getId());
							addScmCostUseSet.setCostOrgUnitNo(scmCostUseSet.getCostOrgUnitNo());
							addScmCostUseSet.setCreator(param.getUsrCode());
							addScmCostUseSet.setCreateDate(CalendarUtil.getDate(param));
							addScmCostUseSet.setEditor(param.getUsrCode());
							addScmCostUseSet.setEditDate(CalendarUtil.getDate(param));
							addScmCostUseSet.setControlUnitNo(param.getControlUnitNo());
							this.add(addScmCostUseSet, param);
						}
					}
				}
			}
		}
		
	}

	@Override
	public List<ScmCostUseSet2> getScmCostUseSetByItemId(String itemIds, String costOrgUnitNo, Param param) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("costOrgUnitNo", costOrgUnitNo);
		map.put("controlUnitNo", param.getControlUnitNo());
		map.put("itemIds", itemIds);
		return ((ScmCostUseSetDao) dao).getScmCostUseSetByItemId(map);
	}
}
