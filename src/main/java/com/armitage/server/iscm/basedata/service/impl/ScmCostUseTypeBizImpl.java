/**
 * 广州万迅电脑软件有限公司(c)
 * @author Promise
 * 2020年12月16日 下午4:57:52
 *
 */
package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iscm.basedata.dao.ScmCostUseTypeDao;
import com.armitage.server.iscm.basedata.model.ScmCostUseSet2;
import com.armitage.server.iscm.basedata.model.ScmCostUseType;
import com.armitage.server.iscm.basedata.service.ScmCostUseSetBiz;
import com.armitage.server.iscm.basedata.service.ScmCostUseTypeBiz;
import org.springframework.stereotype.Service;

@Service("scmCostUseTypeBiz")
public class ScmCostUseTypeBizImpl extends BaseBizImpl<ScmCostUseType> implements ScmCostUseTypeBiz{
	
	private ScmCostUseSetBiz scmCostUseSetBiz;
	
	public void setScmCostUseSetBiz(ScmCostUseSetBiz scmCostUseSetBiz) {
		this.scmCostUseSetBiz = scmCostUseSetBiz;
	}

	@Override
	public List<ScmCostUseType> selectAll(Param param)  throws AppException{
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("controlUnitNo", param.getControlUnitNo());
		return ((ScmCostUseTypeDao)dao).selectAll(map);
	}
	
	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmCostUseType.class) + "." + ScmCostUseType.FN_CONTROLUNITNO), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmCostUseType.class) + "." + ScmCostUseType.FN_CONTROLUNITNO), QueryParam.QUERY_EQ, param.getControlUnitNo()));
	}
	
	@Override
	protected void beforeDelete(ScmCostUseType entity, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(ScmCostUseSet2.FN_COSTUSETYPEID, entity.getId());
		map.put(ScmCostUseSet2.FN_CONTROLUNITNO, param.getControlUnitNo());
		List<ScmCostUseSet2> scmCostUseSetList = scmCostUseSetBiz.findAll(map, param);
		if (scmCostUseSetList != null && !scmCostUseSetList.isEmpty()) {
			throw new AppException(Message.getMessage(param.getLang(),"com.armitage.server.iscm.basedata.service.impl.error.ScmCostUseTypeBizImpl.noDelete",new String[] {entity.getName()}));
		}
	}

	@Override
	public List<ScmCostUseType> queryByNameOrCode(String object, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("controlUnitNo", param.getControlUnitNo());
		map.put("queryValue", object);
		return ((ScmCostUseTypeDao)dao).queryByNameOrCode(map);
	}
}

