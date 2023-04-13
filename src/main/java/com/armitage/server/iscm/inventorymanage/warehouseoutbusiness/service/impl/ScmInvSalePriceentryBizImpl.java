package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvSalePriceentryDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePriceentry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSalePriceentryBiz;
import org.springframework.stereotype.Service;

@Service("scmInvSalePriceentryBiz")
public class ScmInvSalePriceentryBizImpl  extends BaseBizImpl<ScmInvSalePriceentry2> implements ScmInvSalePriceentryBiz{

	@Override
	public List<ScmInvSalePriceentry2> selectByPmId(long id, Param param) throws AppException {
		// TODO Auto-generated method stub
		if(id > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("id", id);
			return ((ScmInvSalePriceentryDAO) dao).selectByPmId(map);
		}
		return null;
	}

	@Override
	public void deleteByPmId(long id, Param param) throws AppException {
		// TODO Auto-generated method stub
		if(id > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("id", id);
			((ScmInvSalePriceentryDAO) dao).deleteByPmId(map);
		}
	}
	
	@Override
	public void updateRowStatusByPmId(long pmId, String status, String checker, Date checkDate, Param param) throws AppException {
		if(pmId > 0 && StringUtils.isNotBlank(status)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("pmId", pmId);
			map.put("rowStatus", status);
			map.put("checker", checker);
			map.put("checkDate", checkDate);
			((ScmInvSalePriceentryDAO) dao).updateRowStatusByPmId(map);
		}
	}
	
}
