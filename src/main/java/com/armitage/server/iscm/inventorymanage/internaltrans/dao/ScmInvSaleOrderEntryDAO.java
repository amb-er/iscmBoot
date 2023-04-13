package com.armitage.server.iscm.inventorymanage.internaltrans.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrderEntry;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrderEntry2;

public interface ScmInvSaleOrderEntryDAO extends BasicDAO<ScmInvSaleOrderEntry> {
    public List<ScmInvSaleOrderEntry2> selectByOtId(HashMap<String, Object> map) throws DAOException;
}
