
package com.armitage.server.ifbc.costcard.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCard;

public interface ScmProductCostCardDAO extends BasicDAO<ScmProductCostCard> {

	ScmProductCostCard selectUnique(HashMap<String, Object> map);

}
