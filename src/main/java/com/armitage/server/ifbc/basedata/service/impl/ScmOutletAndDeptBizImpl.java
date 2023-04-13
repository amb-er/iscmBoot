package com.armitage.server.ifbc.basedata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.ifbc.basedata.model.ScmProductionDept;
import com.armitage.server.ifbc.basedata.model.ScmProductionDept2;
import com.armitage.server.ifbc.basedata.service.ScmOutletAndDeptBiz;
import com.armitage.server.ifbc.basedata.service.ScmProductionDeptBiz;
import com.armitage.server.system.model.Outlet;
import com.armitage.server.system.service.OrgResourceBiz;
import com.armitage.server.system.service.OutletBiz;
import org.springframework.stereotype.Service;

@Service("scmOutletAndDeptBiz")
public class ScmOutletAndDeptBizImpl extends BaseBizImpl<ScmProductionDept2> implements ScmOutletAndDeptBiz {
	private ScmProductionDeptBiz scmProductionDeptBiz;
	private OutletBiz outletBiz;
	private OrgResourceBiz orgResourceBiz;

	
	public void setOrgResourceBiz(OrgResourceBiz orgResourceBiz) {
		this.orgResourceBiz = orgResourceBiz;
	}

	public ScmProductionDeptBiz getScmProductionDeptBiz() {
		return scmProductionDeptBiz;
	}

	public void setScmProductionDeptBiz(ScmProductionDeptBiz scmProductionDeptBiz) {
		this.scmProductionDeptBiz = scmProductionDeptBiz;
	}

	public OutletBiz getOutletBiz() {
		return outletBiz;
	}

	public void setOutletBiz(OutletBiz outletBiz) {
		this.outletBiz = outletBiz;
	}

	@Override
	public List queryPageEx(Page page, List<String> arglist, String xmlId, Param param) throws AppException {
		List<ScmProductionDept2> rtnList = new ArrayList<>();
		// 用于实现查营业点及部门
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
			List<ScmProductionDept> scmProductionDepts =null;
			if(map.containsKey("orgUnitNo") && map.containsKey("controlUnitNo") && map.get("orgUnitNo").equals(map.get("controlUnitNo"))) {
					Object ControlUnitNoValue = map.get("controlUnitNo");
					HashMap<String, Object> SpecialMap= new HashMap<String, Object>();
					SpecialMap.put("controlUnitNo", ControlUnitNoValue);
					scmProductionDepts = scmProductionDeptBiz.findAll(SpecialMap, param);
			}
			else {
				if(map.get("orgUnitNo")!=null&&StringUtils.isNotBlank((String)map.get("orgUnitNo"))) {
					param.setOrgUnitNo((String)map.get("orgUnitNo"));
				}
				if(StringUtils.isNotBlank(page.getSqlCondition())) {
					String replace = page.getSqlCondition().replace("ScmProductionDept.code", "ScmProductionDept.id");
					page.setSqlCondition(replace);
				}
				scmProductionDepts = scmProductionDeptBiz.findPage(page, param);
			}
			if(scmProductionDepts!=null && !scmProductionDepts.isEmpty()) {
				for(ScmProductionDept scmProductionDept:scmProductionDepts) {
					ScmProductionDept2 rntScmInvWareHouse = new ScmProductionDept2();
					BeanUtil.copyProperties(rntScmInvWareHouse, scmProductionDept);
					rntScmInvWareHouse.setCode(String.valueOf(scmProductionDept.getId()));
					rntScmInvWareHouse.setType("W");//出品部门
					rtnList.add(rntScmInvWareHouse);
				}
			}
		}
		if(!StringUtils.equals(stockType, "0")) {
			List<Outlet> outlets = new ArrayList<Outlet>();
			if(map.containsKey("orgUnitNo") && map.containsKey("controlUnitNo") && map.get("orgUnitNo").equals(map.get("controlUnitNo"))) {
				Object ControlUnitNoValue = map.get("controlUnitNo");
				HashMap<String, Object> SpecialMap= new HashMap<String, Object>();
				SpecialMap.put("controlUnitNo", ControlUnitNoValue);
				outlets = outletBiz.findAll(SpecialMap, param);
			}else{
				if(map.get("orgUnitNo")!=null&&StringUtils.isNotBlank((String)map.get("orgUnitNo"))) {
					param.setOrgUnitNo((String)map.get("orgUnitNo"));
				}
				if(StringUtils.isNotBlank(page.getSqlCondition())) {
					String replace = page.getSqlCondition().replace("ScmProductionDept.code", "Outlet.id");
					page.setSqlCondition(replace);
				}
				outlets = outletBiz.findPage(page, param);
			}
	        if (outlets != null && outlets.size() > 0) {
	            for (Outlet outlet : outlets) {
	            	ScmProductionDept2 rntScmInvWareHouse = new ScmProductionDept2();
            		rntScmInvWareHouse.setName(outlet.getName());
            		rntScmInvWareHouse.setOrgUnitNo(outlet.getOrgUnitno());
            		rntScmInvWareHouse.setCode(String.valueOf(outlet.getId()));
            		rntScmInvWareHouse.setType("D");//营业点
            		rtnList.add(rntScmInvWareHouse);
	            }
	        }
		}
		
        return rtnList;
	}
	
}

