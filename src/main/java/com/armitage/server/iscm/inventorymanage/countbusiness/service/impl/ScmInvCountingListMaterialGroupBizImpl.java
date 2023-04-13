
package com.armitage.server.iscm.inventorymanage.countbusiness.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.countbusiness.dao.ScmInvCountingListMaterialGroupDAO;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterialGroup;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterialGroup2;
import com.armitage.server.iscm.inventorymanage.countbusiness.service.ScmInvCountingListMaterialGroupBiz;
import org.springframework.stereotype.Service;

@Service("scmInvCountingListMaterialGroupBiz")
public class ScmInvCountingListMaterialGroupBizImpl extends BaseBizImpl<ScmInvCountingListMaterialGroup2> implements ScmInvCountingListMaterialGroupBiz {

	@Override
	public void deleteByTaskId(long taskId, Param param) throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			((ScmInvCountingListMaterialGroupDAO) dao).deleteByTaskId(map);
		}		
	}

	@Override
	public List<ScmInvCountingListMaterialGroup2> selectByTaskId(long taskId, Param param) throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			return ((ScmInvCountingListMaterialGroupDAO) dao).selectByTaskId(map);
		}
		return null;	
	}

}
