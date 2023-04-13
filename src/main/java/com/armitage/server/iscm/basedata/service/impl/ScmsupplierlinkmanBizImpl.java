package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.dao.ScmsupplierlinkmanDAO;
import com.armitage.server.iscm.basedata.model.Scmsupplierlinkman;
import com.armitage.server.iscm.basedata.service.ScmsupplierlinkmanBiz;
import org.springframework.stereotype.Service;

@Service("scmsupplierlinkmanBiz")
public class ScmsupplierlinkmanBizImpl extends BaseBizImpl<Scmsupplierlinkman> implements ScmsupplierlinkmanBiz {

	@Override
	public List<Scmsupplierlinkman> selectByVendorId(long vendorId, Param param) throws AppException {
		if(vendorId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("vendorId", vendorId);
			return ((ScmsupplierlinkmanDAO) dao).selectByVendorId(map);
		}
		return null;
	}

	@Override
	public void deleteByVendorId(long vendorId, Param param) throws AppException {
		if(vendorId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("vendorId", vendorId);
			((ScmsupplierlinkmanDAO) dao).deleteByVendorId(map);
		}
	}

}
