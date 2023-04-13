
package com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurREReuseDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurREReuse;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurREReuseBiz;
import org.springframework.stereotype.Service;

@Service("scmPurREReuseBiz")
public class ScmPurREReuseBizImpl extends BaseBizImpl<ScmPurREReuse> implements ScmPurREReuseBiz {

	@Override
	public void cancelRefuse(String string, Param param) throws AppException {
		if (StringUtils.isNotBlank(string)) {
			HashMap<String, Object> map = new HashMap<>();
            map.put("entryBillId", string);
            ((ScmPurREReuseDAO)dao).cancelRefuse(map);
		}
	}

	@Override
	public ScmPurREReuse selectByEntryId(long prId) throws AppException {
		if (prId>0) {
			HashMap<String, Object> map = new HashMap<>();
            map.put("entryBillId", prId);
            return ((ScmPurREReuseDAO)dao).selectByEntryId(map);
		}
		return null;
	}

	@Override
	public void deleteByEntryId(long id, Param param) throws AppException {
		if (id>0) {
			HashMap<String, Object> map = new HashMap<>();
            map.put("entryBillId", id);
            ((ScmPurREReuseDAO)dao).deleteByEntryId(map);
		}
	}

}
