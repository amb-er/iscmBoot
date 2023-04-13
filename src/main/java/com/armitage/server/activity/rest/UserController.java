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
import com.armitage.server.user.model.Usr2;
import com.armitage.server.user.service.UsrBiz;

@RestController
@RequestMapping("/activiti-explorer")
public class UserController {

	private UsrBiz usrBiz = (UsrBiz) AppContextUtil.getBean("usrBiz");
	private OrgBaseUnitBiz orgBaseUnitBiz = (OrgBaseUnitBiz) AppContextUtil.getBean("orgBaseUnitBiz");
	private BillConditionBiz billConditionBiz = (BillConditionBiz) AppContextUtil.getBean("billConditionBiz");
	
	@RequestMapping(value={"/model/getUserList"}, method={RequestMethod.POST})
	public Object getUserList(@RequestParam(value = "billTypeId") Long billTypeId, @RequestParam(value = "orgUnitNo") String orgUnitNo, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
		
		Param param = new Param();
		param.setOrgUnitNo(orgUnitNo);
		OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(orgUnitNo, param);
		if (orgBaseUnit != null) {
			param.setControlUnitNo(orgBaseUnit.getControlUnitNo());
		}
		
		Page page = new Page();
		page.setModelClass(Usr2.class);
		page.setPagePos(1);
		page.setShowCount(Integer.MAX_VALUE);
		List<String> arglist = new ArrayList();
		arglist.add("controlUnitNo="+orgBaseUnit.getControlUnitNo());
		List<Usr2> usrList = usrBiz.queryPage(page, arglist, "selectByCtrlPage", param);
		if (usrList != null && !usrList.isEmpty()) {
			GridData gridData = new GridData();
			gridData.setTotal(usrList.size());
			gridData.setCurrent(pageNum);
			gridData.setRowSize(pageSize);
			gridData.setTotalPages((int) (usrList.size()/pageSize+1));
			List<Usr2> usrList2 = new ArrayList<>();
//			for(int i=(pageNum-1)*pageSize;i<usrList.size()&&i<(pageNum*pageSize);i++){
//				usrList2.add(usrList.get(i));
//			}
			for(int i=0;i<usrList.size();i++){
				usrList2.add(usrList.get(i));
			}
			gridData.setRows(usrList2);
			return gridData;
		}
		return null;
    }
	
	@RequestMapping(value={"/model/getDynamicUserList"}, method={RequestMethod.POST})
	public Object getDynamicUserList(@RequestParam(value = "billTypeId") Long billTypeId, @RequestParam(value = "orgUnitNo") String orgUnitNo, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
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
				if(billConditionList.get(i).getParamType() == 3){
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
