package com.armitage.server.activity.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.armitage.server.activity.common.model.BillCondition;
import com.armitage.server.activity.common.service.BillConditionBiz;
import com.armitage.server.activity.model.GridData;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.service.OrgBaseUnitBiz;
import com.armitage.server.user.model.Role;
import com.armitage.server.user.service.RoleBiz;

@RestController
@RequestMapping("/activiti-explorer")
public class GroupController {
	
	private RoleBiz roleBiz = (RoleBiz) AppContextUtil.getBean("roleBiz");
	private OrgBaseUnitBiz orgBaseUnitBiz = (OrgBaseUnitBiz) AppContextUtil.getBean("orgBaseUnitBiz");
	private BillConditionBiz billConditionBiz = (BillConditionBiz) AppContextUtil.getBean("billConditionBiz");
	
	@RequestMapping(value={"/model/getGroupList"}, method={RequestMethod.POST})
	public Object getGroupList(@RequestParam(value = "billTypeId") Long billTypeId, @RequestParam(value = "orgUnitNo") String orgUnitNo, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
		
		Param param = new Param();
		param.setOrgUnitNo(orgUnitNo);
		OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(orgUnitNo, param);
		if (orgBaseUnit != null) {
			param.setControlUnitNo(orgBaseUnit.getControlUnitNo());
		}
		
		Page page = new Page();
		page.setModelClass(Role.class);
		page.setPagePos(1);
		page.setShowCount(Integer.MAX_VALUE);
		List<Role> roleList = roleBiz.findPage(page, param);
		if (roleList != null && !roleList.isEmpty()) {
			GridData gridData = new GridData();
			gridData.setTotal(roleList.size());
			gridData.setCurrent(pageNum);
			gridData.setRowSize(pageSize);
			gridData.setTotalPages((int) (roleList.size()/pageSize+1));
			List<Role> roleList2 = new ArrayList<>();
//			for(int i=(pageNum-1)*pageSize;i<roleList.size()&&i<(pageNum*pageSize);i++){
//				roleList2.add(roleList.get(i));
//			}
			for(int i=0;i<roleList.size();i++){
				roleList2.add(roleList.get(i));
			}
			gridData.setRows(roleList2);
			return gridData;
		}
		return null;
    }
	
	@RequestMapping(value={"/model/getFilterOrgUnitNoList"}, method={RequestMethod.POST})
	public Object getFilterOrgUnitNoList(@RequestParam(value = "billTypeId") Long billTypeId, @RequestParam(value = "orgUnitNo") String orgUnitNo, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
		Param param = new Param();
		param.setOrgUnitNo(orgUnitNo);
		OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(orgUnitNo, param);
		if (orgBaseUnit != null) {
			param.setControlUnitNo(orgBaseUnit.getControlUnitNo());
		}
		
		List<BillCondition> billConditionList = billConditionBiz.selectCondition(billTypeId.longValue(), param);
		if (billConditionList != null && !billConditionList.isEmpty()) {
			List<BillCondition> billConditionList2 = new ArrayList<>();
			for(int i=0;i<billConditionList.size();i++){
				if(billConditionList.get(i).getParamType() == 2){
					billConditionList2.add(billConditionList.get(i));
				}
			}
			GridData gridData = new GridData();
			gridData.setTotal(billConditionList2.size());
			gridData.setCurrent(pageNum);
			gridData.setRowSize(pageSize);
			gridData.setTotalPages((int) (billConditionList2.size()/pageSize+1));
			gridData.setRows(billConditionList2);
			return gridData;
		}
		return null;
    }
}
