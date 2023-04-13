package com.armitage.server.iscm.inventorymanage.cstbusiness.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmCostBillDao;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCostBill;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmCostBillBiz;
import com.armitage.server.system.service.SysParamBiz;
import org.springframework.stereotype.Service;

@Service("scmCostBillBiz")
public class ScmCostBillBizImpl extends BaseBizImpl<ScmCostBill> implements ScmCostBillBiz {
	private SysParamBiz sysParamBiz;

	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	@Override
	public List<ScmCostBill> selectPostAndDateByItem(String string, Date postDate, String orgUnitNo,String costOrgUnitNo, Param param) throws AppException {
		String cancelIdleScope = sysParamBiz.getValue(param.getControlUnitNo(), "SCM_CancelIdleScope", "", param);
		if (StringUtils.isNotBlank(string) && StringUtils.isNotBlank(cancelIdleScope)) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("itemIds", string);
			map.put("postDate", postDate);
			map.put("orgUnitNo", orgUnitNo);
			map.put("costOrgUnitNo", costOrgUnitNo);
			map.put("controlUnitNo", param.getControlUnitNo());
			if (Arrays.asList(StringUtils.split(cancelIdleScope,",")).contains("1")) {
				map.put("CstFrmLoss", "CstFrmLoss");
			}
			if (Arrays.asList(StringUtils.split(cancelIdleScope,",")).contains("2")) {
				map.put("InvMoveBill", "InvMoveBill");
			}
			if (Arrays.asList(StringUtils.split(cancelIdleScope,",")).contains("3")) {
				map.put("InvCostConsume", "InvCostConsume");
			}
			return ((ScmCostBillDao)dao).selectPostAndDateByItem(map);
		}
		return null;
	}

}
