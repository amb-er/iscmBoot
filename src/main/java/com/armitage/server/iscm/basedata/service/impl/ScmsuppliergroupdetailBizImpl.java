package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.dao.ScmsuppliergroupdetailDAO;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroupdetail;
import com.armitage.server.iscm.basedata.service.ScmsuppliergroupdetailBiz;
import org.springframework.stereotype.Service;

@Service("scmsuppliergroupdetailBiz")
public class ScmsuppliergroupdetailBizImpl extends BaseBizImpl<Scmsuppliergroupdetail> implements ScmsuppliergroupdetailBiz {

	@Override
	public void deleteByVendorIdOrGroupId(long vendorId, long groupId, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		boolean flag = false;
		if(vendorId > 0){
			flag = true;
			map.put("vendorId", vendorId);
		}
		if(groupId > 0){
			flag = true;
			map.put("classId", groupId);
		}
		if(flag){
			((ScmsuppliergroupdetailDAO) dao).deleteByVendorIdOrGroupId(map);
		}
	}
}
