package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.dao.ScmsupplierbankDAO;
import com.armitage.server.iscm.basedata.model.Scmsupplierbank;
import com.armitage.server.iscm.basedata.service.ScmsupplierbankBiz;
import org.springframework.stereotype.Service;

@Service("scmsupplierbankBiz")
public class ScmsupplierbankBizImpl extends BaseBizImpl<Scmsupplierbank> implements ScmsupplierbankBiz {

	@Override
	public List<Scmsupplierbank> selectByVendorIdAndOrgUnitNo(long vendorId, String orgUnitNo, Param param) throws AppException {
		if(vendorId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("vendorId", vendorId);
			map.put("orgUnitNo", orgUnitNo);
			return ((ScmsupplierbankDAO) dao).selectByVendorIdAndOrgUnitNo(map);
		}
		return null;
	}

	@Override
	public void deleteByVendorId(long vendorId, Param param) throws AppException {
		if(vendorId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("vendorId", vendorId);
			((ScmsupplierbankDAO) dao).deleteByVendorId(map);
		}
	}
}
