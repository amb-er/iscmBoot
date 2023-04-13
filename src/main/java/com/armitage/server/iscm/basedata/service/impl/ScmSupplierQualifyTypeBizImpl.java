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
import com.armitage.server.iscm.basedata.dao.ScmSupplierQualifyTypeDAO;
import com.armitage.server.iscm.basedata.model.ScmIndustryGroup;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifyType;
import com.armitage.server.iscm.basedata.service.ScmIndustryGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierQualifyTypeBiz;
import org.springframework.stereotype.Service;

@Service("scmSupplierQualifyTypeBiz")
public class ScmSupplierQualifyTypeBizImpl extends BaseBizImpl<ScmSupplierQualifyType> implements ScmSupplierQualifyTypeBiz {
	
	private ScmIndustryGroupBiz scmIndustryGroupBiz;
	
	public void setScmIndustryGroupBiz(ScmIndustryGroupBiz scmIndustryGroupBiz) {
		this.scmIndustryGroupBiz = scmIndustryGroupBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmSupplierQualifyType.class) + "." + ScmSupplierQualifyType.FN_CONTROLUNITNO), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmSupplierQualifyType.class) + "." + ScmSupplierQualifyType.FN_CONTROLUNITNO), QueryParam.QUERY_EQ, param.getControlUnitNo()));
	}
	
	@Override
	protected void beforeUpdate(ScmSupplierQualifyType oldEntity, ScmSupplierQualifyType newEntity,	Param param) throws AppException {
		if(oldEntity.isFlag() && !newEntity.isFlag()){
			List<ScmIndustryGroup> scmIndustryGroupList = scmIndustryGroupBiz.selectByTypeId(oldEntity.getId(), param);
			if(scmIndustryGroupList != null && !scmIndustryGroupList.isEmpty()){
				throw new AppException(Message.getMessage(param.getLang(), "com.armitage.server.iscm.basedata.service.impl.ScmSupplierQualifyTypeBizImpl.error.deleteOrCancel",new String[]{scmIndustryGroupList.get(0).getClassName()}));
			}
		}
	}
	
	@Override
	protected void beforeDelete(ScmSupplierQualifyType entity, Param param) throws AppException {
		ScmSupplierQualifyType scmSupplierQualifyType = this.selectDirect(entity.getId(), param);
		if(scmSupplierQualifyType.isFlag()){
			List<ScmIndustryGroup> scmIndustryGroupList = scmIndustryGroupBiz.selectByTypeId(scmSupplierQualifyType.getId(), param);
			if(scmIndustryGroupList != null && !scmIndustryGroupList.isEmpty()){
				throw new AppException(Message.getMessage(param.getLang(), "com.armitage.server.iscm.basedata.service.impl.ScmSupplierQualifyTypeBizImpl.error.deleteOrCancel",new String[]{scmIndustryGroupList.get(0).getClassName()}));
			}
		}
	}

	@Override
	public ScmSupplierQualifyType selectByCode(String code, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("code", code);
        map.put("controlUnitNo", param.getControlUnitNo());
        return ((ScmSupplierQualifyTypeDAO)dao).selectByCode(map);
	}
	
	@Override
	public List<ScmSupplierQualifyType> selectByVendor(long id, Param createParam) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("vendorId", id);
		map.put("controlUnitNo", createParam.getControlUnitNo());
		return ((ScmSupplierQualifyTypeDAO) dao).selectByVendor(map);
	}
}

