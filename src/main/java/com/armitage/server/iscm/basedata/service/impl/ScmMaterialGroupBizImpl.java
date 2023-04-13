
package com.armitage.server.iscm.basedata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.cache.ModelCacheMana;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iscm.basedata.dao.ScmMaterialGroupDAO;
import com.armitage.server.iscm.basedata.model.ScmCostCategory;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupAdvQuery;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupStandard;
import com.armitage.server.iscm.basedata.model.ScmPurchaseType2;
import com.armitage.server.iscm.basedata.service.ScmCostCategoryBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupDetailBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupStandardBiz;
import org.springframework.stereotype.Service;

@Service("scmMaterialGroupBiz")
public class ScmMaterialGroupBizImpl extends BaseBizImpl<ScmMaterialGroup> implements ScmMaterialGroupBiz {

	private ScmMaterialGroupDetailBiz scmMaterialGroupDetailBiz;
	private ScmMaterialGroupStandardBiz scmMaterialGroupStandardBiz;
	private ScmCostCategoryBiz scmCostCategoryBiz;
	private List<Long> longNoList = new ArrayList<Long>();
	
	public void setScmMaterialGroupDetailBiz(ScmMaterialGroupDetailBiz scmMaterialGroupDetailBiz) {
		this.scmMaterialGroupDetailBiz = scmMaterialGroupDetailBiz;
	}

	public void setScmMaterialGroupStandardBiz(ScmMaterialGroupStandardBiz scmMaterialGroupStandardBiz) {
		this.scmMaterialGroupStandardBiz = scmMaterialGroupStandardBiz;
	}

	public void setScmCostCategoryBiz(ScmCostCategoryBiz scmCostCategoryBiz) {
		this.scmCostCategoryBiz = scmCostCategoryBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_CONTROLUNITNO), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_CONTROLUNITNO), QueryParam.QUERY_EQ, param.getControlUnitNo()));
	}

	@Override
	protected void afterSelect(ScmMaterialGroup entity, Param param) throws AppException {
		if(entity!=null) {
			setConvertMap(entity,param);
		}
	}
	
	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list!=null && !list.isEmpty()) {
			for(ScmMaterialGroup scmMaterialGroup:(List<ScmMaterialGroup>)list) {
				setConvertMap(scmMaterialGroup,param);
			}
		}
	}

	@Override
	protected void afterDelete(ScmMaterialGroup entity, Param param) throws AppException {
		ScmMaterialGroup scmMaterialGroup = entity;
		//删除物料分类关系
		if(scmMaterialGroup != null && scmMaterialGroup.getId() > 0){
			scmMaterialGroupDetailBiz.deleteByItemIdOrGroupId(0, scmMaterialGroup.getId(), param);
		}
	}

    @Override
    public List<ScmMaterialGroup> findChild(long materialId , Param param) throws AppException {
        HashMap<String, Object> map = new HashMap<String, Object>();
        ScmMaterialGroup scmMaterialGroup = this.select(materialId, param);
        if (scmMaterialGroup == null) {
            return null;
        }
        map.put("controlUnitNo", scmMaterialGroup.getControlUnitNo());
        if (scmMaterialGroup.getParentId() == 0) {
            map.put("longNo", materialId+",%"); 
        } else if (scmMaterialGroup.getParentId() > 0) {
            map.put("longNo", "%," + materialId+",%");
        }
        return ((ScmMaterialGroupDAO)dao).findChild(map);
    }

	@Override
	public ScmMaterialGroup selectByClassCode(String classCode,Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("classCode", classCode);
		map.put("controlUnitNo", param.getControlUnitNo());
		return ((ScmMaterialGroupDAO)dao).selectByClassCode(map);
	}

    @Override
	public List<ScmMaterialGroup> queryMaterialClassList(ScmMaterialGroupAdvQuery scmMaterialGroupAdvQuery, int pageNum,Param param) throws AppException {
		Page page = new Page();
		page.setModelClass(ScmMaterial2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		page.setSqlCondition("(ScmMaterialGroup.standardId in (Select id from ScmMaterialGroupStandard Where standardType = '1'))");
		if(StringUtils.isNotBlank(scmMaterialGroupAdvQuery.getMixParam())){
			page.setSqlCondition(" and (ScmMaterialGroup.classCode like '%"+scmMaterialGroupAdvQuery.getMixParam()+"%' or ScmMaterialGroup.className like '%"+scmMaterialGroupAdvQuery.getMixParam()+"%')");
		}
		if(scmMaterialGroupAdvQuery.getGroupLevel() > 0){
			if(StringUtils.isNotBlank(page.getSqlCondition())) {
				page.setSqlCondition(page.getSqlCondition()+" and (LENGTH(longNo)-LENGTH(REPLACE(longNo,',','')))="+scmMaterialGroupAdvQuery.getGroupLevel());
			}else{
				page.setSqlCondition(" and (LENGTH(longNo)-LENGTH(REPLACE(longNo,',','')))="+scmMaterialGroupAdvQuery.getGroupLevel());
			}
		}
		return this.findPage(page, param);
	}

    private void selectParent(Long id, Param param){
        ScmMaterialGroup parentEntity = this.select(id, param);
        if (parentEntity != null) {
            longNoList.add(parentEntity.getId());
            selectParent(parentEntity.getParentId(), param);
        }
    }

    @Override
    protected void beforeAdd(ScmMaterialGroup entity, Param param) throws AppException {
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
                }
            }
            if(entity.getCostTypeId()>0) {
    			ScmCostCategory scmCostCategory = scmCostCategoryBiz.selectDirect(entity.getCostTypeId(), param);
    			if(scmCostCategory!=null) {
    				entity.setCostType(scmCostCategory.getType());
    			}
    		}
        }
    }
    
    @Override
	protected void beforeUpdate(ScmMaterialGroup oldEntity, ScmMaterialGroup newEntity, Param param)
			throws AppException {
    	if(newEntity!=null && newEntity.getCostTypeId()>0) {
			ScmCostCategory scmCostCategory = scmCostCategoryBiz.selectDirect(newEntity.getCostTypeId(), param);
			if(scmCostCategory!=null) {
				newEntity.setCostType(scmCostCategory.getType());
			}
		}
	}

	@Override
    protected void afterAdd(ScmMaterialGroup entity, Param param) throws AppException {
        if (entity != null) {
            if (entity.getParentId() == 0) {
                entity.setLongNo(entity.getId()+",");
            } else if(entity.getParentId() > 0) {
                entity.setLongNo(entity.getLongNo()+entity.getId()+",");
            }
            this.updateDirect(entity, param);
        }
    }
    
	private void setConvertMap(ScmMaterialGroup scmMaterialGroup,  Param param){
		if(scmMaterialGroup!=null) {
			if(scmMaterialGroup.getCostTypeId()>0) {
    			ScmCostCategory scmCostCategory = scmCostCategoryBiz.selectDirect(scmMaterialGroup.getCostTypeId(), param);
    			if(scmCostCategory!=null) {
    				scmMaterialGroup.setConvertMap(ScmMaterialGroup.FN_COSTTYPEID, scmCostCategory);
    			}
    		}
			if(scmMaterialGroup.getStandardId() > 0){
				//分类标准
				ScmMaterialGroupStandard scmMaterialGroupStandard = scmMaterialGroupStandardBiz.selectDirect(scmMaterialGroup.getStandardId(), param);
				if(scmMaterialGroupStandard != null){
					scmMaterialGroup.setConvertMap(ScmMaterialGroup.FN_STANDARDID, scmMaterialGroupStandard);
				}
			}
		}
	}

	@Override
	public ScmMaterialGroup selectByItemId(long itemId, Param param) throws AppException {
		String key = itemId+"_byItem";
		if (ModelCacheMana.keyExists(key, modelClass)) {
			Object obj = ModelCacheMana.get(key, modelClass);
			if (obj != null && obj.getClass().equals(modelClass)) {
				return (ScmMaterialGroup) obj;
			}
		}
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("itemId", itemId);
		ScmMaterialGroup scmMaterialGroup = ((ScmMaterialGroupDAO)dao).selectByItemId(map);
		if(scmMaterialGroup!=null) {
			// 放进缓存
			ModelCacheMana.set(key, scmMaterialGroup);
		}
		return scmMaterialGroup;
	}

	@Override
	public List<ScmMaterialGroup> findChild(String classCode, Param createParam) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ScmMaterialGroup scmMaterialGroup = this.selectByClassCode(classCode, createParam);
		if (scmMaterialGroup == null) {
			return null;
		}
		map.put("controlUnitNo", createParam.getControlUnitNo());
		if (scmMaterialGroup.getParentId() == 0) {
			map.put("longNo", scmMaterialGroup.getId() + ",%");
		} else if (scmMaterialGroup.getParentId() > 0) {
			map.put("longNo", "%," + scmMaterialGroup.getId() + ",%");
		}
		return ((ScmMaterialGroupDAO) dao).findChild(map);
	}

	@Override
	public List<ScmMaterialGroup2> queryDetailClassList(String finOrgUnitNo,Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("controlUnitNo",param.getControlUnitNo());
		return ((ScmMaterialGroupDAO)dao).queryDetailClassList(map);
	}

	@Override
	public void updateCostType(List<ScmMaterialGroup> scmMaterialGroupList, Param param) throws AppException {
		if(scmMaterialGroupList!=null && !scmMaterialGroupList.isEmpty()) {
			for(ScmMaterialGroup scmMaterialGroup:scmMaterialGroupList) {
				this.updateDirect(scmMaterialGroup, param);
			}
		}
	}

}
