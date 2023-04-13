
package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment;

public interface ScmBaseAttachmentDAO extends BasicDAO<ScmBaseAttachment> {

	ScmBaseAttachment findBybillId(HashedMap hashedMap, Param param) throws DAOException;
	List<ScmBaseAttachment> findBybillTypeId(HashMap<String, Object> hashedMap, Param param) throws DAOException;
}
