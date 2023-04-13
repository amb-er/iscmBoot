package com.armitage.server.iscm.inventorymanage.cstbusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingListUserOrg;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingListUserOrg2;

public interface ScmInvCountingListUserOrgDAO extends BasicDAO<ScmInvCountingListUserOrg> {
	public List<ScmInvCountingListUserOrg2> selectByTaskId(HashMap<String, Object> map) throws DAOException;
	public void deleteByTaskId(HashMap<String, Object> map) throws DAOException;
}
