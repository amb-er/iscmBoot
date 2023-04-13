package com.armitage.server.iscm.basedata.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iscm.basedata.dao.ScmIndustryGroupDAO;
import com.armitage.server.iscm.basedata.model.ScmIndustryGroup;
import com.armitage.server.iscm.basedata.model.ScmIndustryGroupQualifyType2;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.ScmIndustryGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmIndustryGroupQualifyTypeBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.quartz.util.SupplierPlatUtil;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmIndustryGroupBiz")
public class ScmIndustryGroupBizImpl extends BaseBizImpl<ScmIndustryGroup> implements ScmIndustryGroupBiz {
	
	private List<String> longNoList = new ArrayList<String>();
	private UsrBiz usrBiz;
	private ScmIndustryGroupQualifyTypeBiz scmIndustryGroupQualifyTypeBiz;
	private ScmsupplierBiz scmsupplierBiz;
	
	public void setLongNoList(List<String> longNoList) {
		this.longNoList = longNoList;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setScmIndustryGroupQualifyTypeBiz(ScmIndustryGroupQualifyTypeBiz scmIndustryGroupQualifyTypeBiz) {
		this.scmIndustryGroupQualifyTypeBiz = scmIndustryGroupQualifyTypeBiz;
	}

	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmIndustryGroup.class) + "." + ScmIndustryGroup.FN_CONTROLUNITNO), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmIndustryGroup.class) + "." + ScmIndustryGroup.FN_CONTROLUNITNO), QueryParam.QUERY_EQ, param.getControlUnitNo()));
	}
	
	@Override
	protected void beforeAdd(ScmIndustryGroup entity, Param param) throws AppException {
		if (entity != null) {
            if (entity.getParentId() == 0) {
                entity.setLongNo("0");
            } else if(entity.getParentId() > 0) {
                StringBuilder sbLongNo = new StringBuilder("");
                selectParent(entity.getParentId(), param);
                if (longNoList != null && !longNoList.isEmpty()) {
                    for (int i = longNoList.size()-1; i >= 0; i--) {
                        sbLongNo.append(longNoList.get(i)).append(",");
                    }
                    longNoList.clear();
                    entity.setLongNo(sbLongNo.toString());
                }else{
                	entity.setLongNo("");
                	entity.setParentId(0);
                }
            }
        }
	}

	private void selectParent(Long id, Param param){
		ScmIndustryGroup parentEntity = this.select(id, param);
        if (parentEntity != null) {
            longNoList.add(String.valueOf(parentEntity.getId()));
            selectParent(parentEntity.getParentId(), param);
        }
    }
	
	@Override
    protected void afterAdd(ScmIndustryGroup entity, Param param) throws AppException {
        if (entity != null) {
            if (entity.getParentId() == 0) {
                entity.setLongNo(entity.getId()+",");
            } else if(entity.getParentId() > 0) {
                entity.setLongNo(entity.getLongNo()+entity.getId()+",");
            }
            this.updateDirect(entity, param);
        }
    }
	
	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list!=null && !list.isEmpty()) {
			for(ScmIndustryGroup scmIndustryGroup:(List<ScmIndustryGroup>)list) {
				setConverMap(scmIndustryGroup,param);
			}
		}
	}

	@Override
	protected void afterSelect(ScmIndustryGroup entity, Param param) throws AppException {
		if(entity!=null)
			setConverMap(entity,param);
	}

	private void setConverMap(ScmIndustryGroup scmIndustryGroup, Param param) throws AppException {
		if(scmIndustryGroup!=null) {
			if (StringUtils.isNotBlank(scmIndustryGroup.getCreator())){
				//创建人
				Usr usr = usrBiz.selectByCode(scmIndustryGroup.getCreator(), param);
				if (usr != null) {
					scmIndustryGroup.setConvertMap(ScmIndustryGroup.FN_CREATOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmIndustryGroup.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmIndustryGroup.getEditor(), param);
				if (usr != null) {
					scmIndustryGroup.setConvertMap(ScmIndustryGroup.FN_EDITOR, usr);
				}
			}
		}
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmIndustryGroup scmIndustryGroup = (ScmIndustryGroup) bean.getList().get(0);
		if(scmIndustryGroup != null && scmIndustryGroup.getId() > 0){
			List<ScmIndustryGroupQualifyType2> scmIndustryGroupQualifyTypeList = bean.getList2();
			if(scmIndustryGroupQualifyTypeList != null && !scmIndustryGroupQualifyTypeList.isEmpty()){
				for(ScmIndustryGroupQualifyType2 scmIndustryGroupQualifyType : scmIndustryGroupQualifyTypeList){
					scmIndustryGroupQualifyType.setClassId(scmIndustryGroup.getId());
					scmIndustryGroupQualifyTypeBiz.add(scmIndustryGroupQualifyType, param);
				}
			}
		}
	}

	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmIndustryGroup scmIndustryGroup = (ScmIndustryGroup) bean.getList().get(0);
		if(scmIndustryGroup != null && scmIndustryGroup.getId() > 0){
			List<ScmIndustryGroupQualifyType2> scmIndustryGroupQualifyTypeList = bean.getList2();
			if(scmIndustryGroupQualifyTypeList != null && !scmIndustryGroupQualifyTypeList.isEmpty()){
				for(ScmIndustryGroupQualifyType2 scmIndustryGroupQualifyType : scmIndustryGroupQualifyTypeList){
					scmIndustryGroupQualifyType.setClassId(scmIndustryGroup.getId());
				}
			}
			scmIndustryGroupQualifyTypeBiz.update(scmIndustryGroup, scmIndustryGroupQualifyTypeList, ScmIndustryGroupQualifyType2.FN_CLASSID, param);
		}
	}

	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmIndustryGroup scmIndustryGroup = (ScmIndustryGroup) bean.getList().get(0);
		if(scmIndustryGroup != null && scmIndustryGroup.getId() > 0){
			bean.setList2(scmIndustryGroupQualifyTypeBiz.selectByClassId(scmIndustryGroup.getId(), param));
		}
	}
	
	@Override
	protected void beforeUpdate(ScmIndustryGroup oldEntity, ScmIndustryGroup newEntity,	Param param) throws AppException {
		if((oldEntity.isFlag() && !newEntity.isFlag())){
			SupplierPlatUtil supplierPlatUtil = new SupplierPlatUtil();
			supplierPlatUtil.changeSyncData(oldEntity, param);
		}
	}
	
	@Override
	protected void beforeDelete(ScmIndustryGroup entity, Param param)
			throws AppException {
		List<Scmsupplier2> scmsupplierList = scmsupplierBiz.selectByIndustryGroupId(entity.getId(), param);
		if(scmsupplierList != null && !scmsupplierList.isEmpty()){
			throw new AppException(Message.getMessage(param.getLang(), "com.armitage.server.iscm.basedata.service.impl.ScmIndustryGroupBizImpl.error.vendorUsed", new String[]{scmsupplierList.get(0).getVendorName()}));
		}
		SupplierPlatUtil supplierPlatUtil = new SupplierPlatUtil();
		supplierPlatUtil.changeSyncData(entity, param);
	}

	@Override
	protected void afterDelete(ScmIndustryGroup entity, Param param) throws AppException {
		if(entity != null && entity.getId() > 0){
			//删除行业分类资质关系
			scmIndustryGroupQualifyTypeBiz.deleteByClassId(entity.getId(), param);
		}
	}

	@Override
	public List<ScmIndustryGroup> selectByTypeId(long typeId, Param param) throws AppException {
		if(typeId>0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("typeId", typeId);
	        map.put("controlUnitNo", param.getControlUnitNo());
	        return ((ScmIndustryGroupDAO)dao).selectByTypeId(map);
		}
		return null;
	}

	@Override
	public ScmIndustryGroup selectByClassCode(String classCode, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("classCode", classCode);
        map.put("controlUnitNo", param.getControlUnitNo());
        return ((ScmIndustryGroupDAO)dao).selectByClassCode(map);
	}
}

