package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroup;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroup2;

public interface ScmsuppliergroupDAO extends BasicDAO<Scmsuppliergroup> {

    public List<Scmsuppliergroup2> findChild(HashMap<String, Object> map) throws DAOException;
    public Scmsuppliergroup2 selectByClassCode(HashMap<String, Object> map) throws DAOException;
    public Scmsuppliergroup2 selectByVendorId(HashMap<String, Object> map) throws DAOException;
}
