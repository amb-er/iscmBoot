package com.armitage.server.iscm.inventorymanage.countbusiness.service.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.countbusiness.dao.ScmInvCountingListWarehouseDAO;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListWarehouse2;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvCountingListWarehouseBiz;
import org.springframework.stereotype.Service;

@Service("scmInvCountingListWarehouseBiz")
public class ScmInvCountingListWarehouseBizImpl extends BaseBizImpl<ScmInvCountingListWarehouse2> implements ScmInvCountingListWarehouseBiz {

	@Override
	public List<ScmInvCountingListWarehouse2> selectByTaskId(long taskId, Param param) throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			return ((ScmInvCountingListWarehouseDAO) dao).selectByTaskId(map);
		}
		return null;
	}

	@Override
	public void deleteByTaskId(long taskId, Param param) throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			((ScmInvCountingListWarehouseDAO) dao).deleteByTaskId(map);
		}
	}

}

