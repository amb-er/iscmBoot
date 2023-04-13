
package com.armitage.server.iscm.purchasemanage.purchasesetting.dao;


import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurInquiryGroup;

public interface ScmPurInquiryGroupDAO extends BasicDAO<ScmPurInquiryGroup> {
	public ScmPurInquiryGroup selectByGroupNo(HashMap<String, Object> map) throws DAOException;
}
