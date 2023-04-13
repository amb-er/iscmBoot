package com.armitage.server.iscm.inventorymanage.inventorysetting.service.impl;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseAndDeptBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.util.OrgUnitRelationType;
import org.springframework.stereotype.Service;

@Service("scmInvWareHouseAndDeptBiz")
public class ScmInvWareHouseAndDeptBizImpl extends BaseBizImpl<ScmInvWareHouse2> implements ScmInvWareHouseAndDeptBiz {
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private OrgAdminBiz orgAdminBiz;
	private OrgStorageBiz orgStorageBiz;
	
	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}

	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}
	
	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}

	@Override
	public List queryPageEx(Page page, List<String> arglist, String xmlId, Param param) throws AppException {
		List<ScmInvWareHouse2> rtnList = new ArrayList<>();
		// 用于实现查仓库及部门
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (arglist != null){
			for (String args:arglist){
				map.put(StringUtils.left(args,StringUtils.indexOf(args, "=")),StringUtils.right(args,StringUtils.length(args)-StringUtils.indexOf(args, "=") - 1));
			}
		}
		String stockType = map.get("stockType")==null?"2":(String) map.get("stockType");
		if(map.containsKey("orgUnitNo")){
			String orgUnitNo = String.valueOf(map.get("orgUnitNo"));
			map.remove("orgUnitNo");
			map.put("orgUnitNo", orgUnitNo.replace("'", ""));
		}
		if(!StringUtils.equals(stockType, "1")) {
			map.remove("stockType");
			List<ScmInvWareHouse> scmInvWareHouseList =null;
			if(map.containsKey("orgUnitNo") && map.containsKey("controlUnitNo") && map.get("orgUnitNo").equals(map.get("controlUnitNo"))) {
					Object ControlUnitNoValue = map.get("controlUnitNo");
					HashMap<String, Object> SpecialMap= new HashMap<String, Object>();
					SpecialMap.put("controlUnitNo", ControlUnitNoValue);
					scmInvWareHouseList = scmInvWareHouseBiz.findAll(SpecialMap, param);
			}
			else {
				if(map.get("orgUnitNo")!=null&&StringUtils.isNotBlank((String)map.get("orgUnitNo"))) {
					param.setOrgUnitNo((String)map.get("orgUnitNo"));
				}
				scmInvWareHouseList = scmInvWareHouseBiz.findPage(page, param);
			}
			if(scmInvWareHouseList!=null && !scmInvWareHouseList.isEmpty()) {
				for(ScmInvWareHouse scmInvWareHouse:scmInvWareHouseList) {
					ScmInvWareHouse2 rntScmInvWareHouse = new ScmInvWareHouse2();
					BeanUtil.copyProperties(rntScmInvWareHouse, scmInvWareHouse);
					rntScmInvWareHouse.setCode(String.valueOf(scmInvWareHouse.getId()));
					rntScmInvWareHouse.setType("W");//仓库
					rtnList.add(rntScmInvWareHouse);
				}
			}
			
		
		}
		if(!StringUtils.equals(stockType, "0")) {
			List<OrgAdmin2> orgAdminList2 = new ArrayList<OrgAdmin2>();
			if(map.containsKey("orgUnitNo") && map.containsKey("controlUnitNo") && map.get("orgUnitNo").equals(map.get("controlUnitNo"))) {
					orgAdminList2 = orgAdminBiz.findChild(param.getOrgUnitNo(), param);
			}else{
				List<OrgStorage2> orgStorages = orgStorageBiz.findChild(param.getOrgUnitNo(), param);
				if(orgStorages != null && !orgStorages.isEmpty()) {
					for(OrgStorage2 orgStorage : orgStorages) {
						List<OrgAdmin2> findFromOrgUnitByType = orgUnitRelationBiz.findFromOrgUnitByType(OrgUnitRelationType.ADMINTOINV, orgStorage.getOrgUnitNo(), false, null, param);
						if(findFromOrgUnitByType!=null && !findFromOrgUnitByType.isEmpty()) {
							orgAdminList2.addAll(findFromOrgUnitByType);
						}
					}
					orgAdminList2 = orgAdminList2.stream().collect(
				            Collectors.collectingAndThen(
				                    Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(OrgAdmin2::getId))), ArrayList::new)
				);
				}else {
					orgAdminList2 = orgUnitRelationBiz.findFromOrgUnitByType(OrgUnitRelationType.ADMINTOINV, param.getOrgUnitNo(), false, null, param);
				}
			}
	        if (orgAdminList2 != null && orgAdminList2.size() > 0) {
	            for (OrgAdmin2 orgAdmin : orgAdminList2) {
	            	if(StringUtils.equals("2",orgAdmin.getOrgType())) {
	            		ScmInvWareHouse2 rntScmInvWareHouse = new ScmInvWareHouse2();
	            		rntScmInvWareHouse.setWhName(orgAdmin.getOrgUnitName());
	            		rntScmInvWareHouse.setWhNo(orgAdmin.getOrgUnitNo());
	            		rntScmInvWareHouse.setCode(orgAdmin.getOrgUnitNo());
	            		rntScmInvWareHouse.setType("D");//部门
	            		rntScmInvWareHouse.setPym("");
	            		rtnList.add(rntScmInvWareHouse);
	            	}
	            }
	        }
		}
		
		Map<String, QueryParam> paramOr = page.getParamOr();
		String paramValue = "";
		if (paramOr != null && rtnList != null && !rtnList.isEmpty()) {
			if(paramOr.containsKey("ScmInvWareHouse.whName")) {
				QueryParam queryParam = paramOr.get("ScmInvWareHouse.whName");
				paramValue = queryParam.getValue().substring(1, queryParam.getValue().length()-1);
			}
			
			if(!StringUtils.isBlank(paramValue)) {
				for(int i = rtnList.size()-1 ; i >= 0 ; i--) {
					ScmInvWareHouse2 scmInvWareHouse = new ScmInvWareHouse2(true);
					scmInvWareHouse = rtnList.get(i);
					if (!(scmInvWareHouse.getWhNo().indexOf(paramValue) != -1 || scmInvWareHouse.getWhName().indexOf(paramValue) != -1 || scmInvWareHouse.getPym().indexOf(paramValue) != -1)) {
						rtnList.remove(i);
                    }
				}
			}
		}
		
        return rtnList;
	}
	
}
