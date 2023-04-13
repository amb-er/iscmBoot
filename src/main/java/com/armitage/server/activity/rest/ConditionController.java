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
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.service.OrgBaseUnitBiz;

@RestController
@RequestMapping("/activiti-explorer")
public class ConditionController {
	
	private BillConditionBiz billConditionBiz = (BillConditionBiz) AppContextUtil.getBean("billConditionBiz");
	private OrgBaseUnitBiz orgBaseUnitBiz = (OrgBaseUnitBiz) AppContextUtil.getBean("orgBaseUnitBiz");
	
	@RequestMapping(value={"/model/getConditionList"}, method={RequestMethod.POST})
	public Object getConditionList(@RequestParam(value = "billTypeId") Long billTypeId, @RequestParam(value = "orgUnitNo") String orgUnitNo, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
		
		Param param = new Param();
		param.setOrgUnitNo(orgUnitNo);
		OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(orgUnitNo, param);
		if (orgBaseUnit != null) {
			param.setControlUnitNo(orgBaseUnit.getControlUnitNo());
		}
		
		List<BillCondition> billConditionList = billConditionBiz.selectCondition(billTypeId.longValue(), param);
		if (billConditionList != null && !billConditionList.isEmpty()) {
			for(int i=billConditionList.size()-1;i>=0;i--){
				if(billConditionList.get(i).getParamType() != 1){
					billConditionList.remove(i);
				}
			}
			if (billConditionList != null && !billConditionList.isEmpty()) {
				GridData gridData = new GridData();
				gridData.setTotal(billConditionList.size());
				gridData.setCurrent(pageNum);
				gridData.setRowSize(pageSize);
				gridData.setTotalPages((int) (billConditionList.size()/pageSize+1));
				List<BillCondition> billConditionList2 = new ArrayList<>();
				for(int i=(pageNum-1)*pageSize;i<billConditionList.size()&&i<(pageNum*pageSize);i++){
					billConditionList2.add(billConditionList.get(i));
				}
				gridData.setRows(billConditionList2);
				return gridData;
			}
		}
		return null;
    }
	
	@RequestMapping(value={"/model/getConditionListNoPage"}, method={RequestMethod.POST})
	public Object getConditionListNoPage(@RequestParam(value = "billTypeId") Long billTypeId, @RequestParam(value = "orgUnitNo") String orgUnitNo) {
		
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
				if(billConditionList.get(i).getParamType() == 1){
					billConditionList2.add(billConditionList.get(i));
				}
			}
			GridData gridData = new GridData();
			gridData.setTotal(billConditionList2.size());
			gridData.setRows(billConditionList2);
			return gridData;
		}
		return null;
    }
}
