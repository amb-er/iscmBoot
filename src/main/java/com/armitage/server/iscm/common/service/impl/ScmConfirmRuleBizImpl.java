package com.armitage.server.iscm.common.service.impl;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.common.dao.ScmConfirmRuleDAO;
import com.armitage.server.iscm.common.model.ScmConfirmRule;
import com.armitage.server.iscm.common.service.ScmConfirmRuleBiz;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.service.BillTypeBiz;
import org.springframework.stereotype.Service;

@Service("scmConfirmRuleBiz")
public class ScmConfirmRuleBizImpl extends BaseBizImpl<ScmConfirmRule> implements ScmConfirmRuleBiz {
	private BillTypeBiz billTypeBiz;
	
	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}

	@Override
	public ScmConfirmRule selectByBillType(String billType, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("billType", billType);
		map.put("orgUnitNo", param.getOrgUnitNo());
		return ((ScmConfirmRuleDAO)dao).selectByBillType(map);
	}

	@Override
	protected void afterSelect(ScmConfirmRule entity, Param param) throws AppException {
		if(entity!=null) {
			this.setConvertMap(entity, param);
		}
	}
	
	private void setConvertMap(ScmConfirmRule scmConfirmRule,Param param){
		if(scmConfirmRule!=null) {
			if(StringUtils.isNotBlank(scmConfirmRule.getBillType())) {
				BillType2 billType = billTypeBiz.selectByBillCode(scmConfirmRule.getBillType(), param);
				if(billType!=null) {
					scmConfirmRule.setConvertMap(ScmConfirmRule.FN_BILLTYPE, billType);
				}
			}
		}
	}
}
