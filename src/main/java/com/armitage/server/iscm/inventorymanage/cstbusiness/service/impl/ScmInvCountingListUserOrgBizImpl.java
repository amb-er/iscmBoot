package com.armitage.server.iscm.inventorymanage.cstbusiness.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvCountingListUserOrgDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingListUserOrg2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingListUserOrgBiz;
import org.springframework.stereotype.Service;

@Service("scmInvCountingListUserOrgBiz")
public class ScmInvCountingListUserOrgBizImpl extends BaseBizImpl<ScmInvCountingListUserOrg2> implements ScmInvCountingListUserOrgBiz {

	@Override
	public List<ScmInvCountingListUserOrg2> selectByTaskId(long taskId, Param param) throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			return ((ScmInvCountingListUserOrgDAO) dao).selectByTaskId(map);
		}
		return null;
	}

	@Override
	public void deleteByTaskId(long taskId, Param param) throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			((ScmInvCountingListUserOrgDAO) dao).deleteByTaskId(map);
		}
	}

}
