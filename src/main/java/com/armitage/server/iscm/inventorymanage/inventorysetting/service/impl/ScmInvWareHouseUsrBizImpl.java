package com.armitage.server.iscm.inventorymanage.inventorysetting.service.impl;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.dao.ScmInvWareHouseUsrDAO;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouseUsr;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouseUsr2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseUsrBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmInvWareHouseUsrBiz")
public class ScmInvWareHouseUsrBizImpl extends BaseBizImpl<ScmInvWareHouseUsr> implements ScmInvWareHouseUsrBiz {
	private UsrBiz usrBiz;
	
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	@Override
	public List<ScmInvWareHouseUsr2> selectByWareHouseId(ScmInvWareHouse scmInvWareHouse, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("wareHouseId", scmInvWareHouse.getId());
		List<ScmInvWareHouseUsr2> scmInvWareHouseUsrList = ((ScmInvWareHouseUsrDAO)dao).selectByWareHouseId(map);
		if(scmInvWareHouseUsrList!=null && !scmInvWareHouseUsrList.isEmpty()) {
			for(ScmInvWareHouseUsr2 scmInvWareHouseUsr:scmInvWareHouseUsrList) {
				if(StringUtils.isNotBlank(scmInvWareHouseUsr.getUsrCode())) {
					Usr usr = usrBiz.selectByCode(scmInvWareHouseUsr.getUsrCode(), param);
					if(usr!=null)
						scmInvWareHouseUsr.setUsrName(usr.getName());
				}
			}
		}
		return scmInvWareHouseUsrList;
	}

	@Override
	public List<ScmInvWareHouseUsr2> selectByUsrCode(String usrCode, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("usrCode", usrCode);
		map.put("orgUnitNo", param.getOrgUnitNo());
		map.put("controlUnitNo", param.getControlUnitNo());
		return ((ScmInvWareHouseUsrDAO)dao).selectByUsrCode(map);
	}

}

