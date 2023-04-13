package com.armitage.server.iscm.inventorymanage.countbusiness.service.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.countbusiness.dao.ScmInvCountingListMaterialDAO;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterial2;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvCountingListMaterialBiz;
import org.springframework.stereotype.Service;

@Service("scmInvCountingListMaterialBiz")
public class ScmInvCountingListMaterialBizImpl extends BaseBizImpl<ScmInvCountingListMaterial2> implements ScmInvCountingListMaterialBiz {

	@Override
	public List<ScmInvCountingListMaterial2> selectByTaskId(long taskId, Param param) throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			return ((ScmInvCountingListMaterialDAO) dao).selectByTaskId(map);
		}
		return null;
	}

	@Override
	public void deleteByTaskId(long taskId, Param param) throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			((ScmInvCountingListMaterialDAO) dao).deleteByTaskId(map);
		}
	}

}

