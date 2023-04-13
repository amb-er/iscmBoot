package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.Scmsupplierlinkman;

public interface ScmsupplierlinkmanDAO extends BasicDAO<Scmsupplierlinkman> {
	public List<Scmsupplierlinkman> selectByVendorId(HashMap<String, Object> map) throws DAOException;
	public void deleteByVendorId(HashMap<String, Object> map) throws DAOException;
}
