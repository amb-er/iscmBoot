
package com.armitage.server.ifbc.costcard.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetail;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetail2;

public interface ScmProductCostCardDetailDAO extends BasicDAO<ScmProductCostCardDetail> {

	List<ScmProductCostCardDetail2> selectByCardId(long id);

	void deleteByCardId(HashMap<String, Object> map);

}
