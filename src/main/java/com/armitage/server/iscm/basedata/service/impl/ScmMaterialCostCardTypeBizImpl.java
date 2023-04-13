
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
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.ifbc.costcard.model.ScmMaterialOrGroupAdvQuery;
import com.armitage.server.iscm.basedata.model.ScmMaterialCostCardType2;
import com.armitage.server.iscm.basedata.model.ScmMaterialCostCardTypeDetail2;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.service.ScmMaterialCostCardTypeBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialCostCardTypeDetailBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmMaterialCostCardTypeBiz")
public class ScmMaterialCostCardTypeBizImpl extends BaseBizImpl<ScmMaterialCostCardType2> implements ScmMaterialCostCardTypeBiz {
	private List<String> longNoList = new ArrayList<String>();
	private ScmMeasureUnitBiz scmMeasureUnitBiz;
	private UsrBiz usrBiz;
	private ScmMaterialCostCardTypeDetailBiz scmMaterialCostCardTypeDetailBiz;
	
	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setScmMaterialCostCardTypeDetailBiz(ScmMaterialCostCardTypeDetailBiz scmMaterialCostCardTypeDetailBiz) {
		this.scmMaterialCostCardTypeDetailBiz = scmMaterialCostCardTypeDetailBiz;
	}

	@Override
	protected void beforeAdd(ScmMaterialCostCardType2 entity, Param param) throws AppException {
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
        }
	}

	private void selectParent(Long id, Param param){
		ScmMaterialCostCardType2 parentEntity = this.select(id, param);
        if (parentEntity != null) {
            longNoList.add(parentEntity.getClassCode());
            selectParent(parentEntity.getParentId(), param);
        }
    }
	
	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page,HashMap<String, Object> map, Param param) {
		map.put(ScmMaterialCostCardType2.FN_CONTROLUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialCostCardType2.class) + "." +ScmMaterialCostCardType2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
		return map;
	}
	
	@Override
    protected void afterAdd(ScmMaterialCostCardType2 entity, Param param) throws AppException {
        if (entity != null) {
            if (entity.getParentId() == 0) {
                entity.setLongNo(entity.getClassCode()+",");
            } else if(entity.getParentId() > 0) {
                entity.setLongNo(entity.getLongNo()+entity.getClassCode()+",");
            }
            this.updateDirect(entity, param);
        }
    }
	
	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list!=null && !list.isEmpty()) {
			for(ScmMaterialCostCardType2 scmMaterialCostCardType:(List<ScmMaterialCostCardType2>)list) {
				setConverMap(scmMaterialCostCardType,param);
			}
		}
	}

	@Override
	protected void afterSelect(ScmMaterialCostCardType2 entity, Param param) throws AppException {
		if(entity!=null)
			setConverMap(entity,param);
	}

	private void setConverMap(ScmMaterialCostCardType2 scmMaterialCostCardType, Param param) throws AppException {
		if(scmMaterialCostCardType!=null) {
			if(scmMaterialCostCardType.getUnitId()>0) {
				ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmMaterialCostCardType.getUnitId(), param);
				if(scmMeasureUnit!=null) {
					scmMaterialCostCardType.setUnitName(scmMeasureUnit.getUnitName());
					scmMaterialCostCardType.setConvertMap(ScmMaterialCostCardType2.FN_UNITID, scmMeasureUnit);
				}
			}
			if(scmMaterialCostCardType.getCstUnitId()>0) {
				ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmMaterialCostCardType.getCstUnitId(), param);
				if(scmMeasureUnit!=null) {
					scmMaterialCostCardType.setCstUnitName(scmMeasureUnit.getUnitName());
					scmMaterialCostCardType.setConvertMap(ScmMaterialCostCardType2.FN_CSTUNITID, scmMeasureUnit);
				}
			}
			if (StringUtils.isNotBlank(scmMaterialCostCardType.getCreator())){
				//创建人
				Usr usr = usrBiz.selectByCode(scmMaterialCostCardType.getCreator(), param);
				if (usr != null) {
					scmMaterialCostCardType.setConvertMap(ScmMaterialCostCardType2.FN_CREATOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmMaterialCostCardType.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmMaterialCostCardType.getEditor(), param);
				if (usr != null) {
					scmMaterialCostCardType.setConvertMap(ScmMaterialCostCardType2.FN_EDITOR, usr);
				}
			}
		}
	}

	@Override
	public List<ScmMaterialCostCardType2> selectForCostCard(ScmMaterialOrGroupAdvQuery scmMaterialOrGroupAdvQuery,
			Param param) throws AppException {
		Page page = new Page();
		page.setModelClass(ScmMaterialCostCardType2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if(scmMaterialOrGroupAdvQuery!=null && StringUtils.isNotBlank(scmMaterialOrGroupAdvQuery.getMixParam())) {
			page.setSqlCondition("(ScmMaterialCostCardType.classCode like '"+scmMaterialOrGroupAdvQuery.getMixParam()+"%' or ScmMaterialCostCardType.className like '"
					+scmMaterialOrGroupAdvQuery.getMixParam()+"%')");
		}
		return this.findPage(page,param);
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmMaterialCostCardType2 scmMaterialCostCardType = (ScmMaterialCostCardType2) bean.getList().get(0);
		if(scmMaterialCostCardType != null && scmMaterialCostCardType.getId() > 0){
			List<ScmMaterialCostCardTypeDetail2> scmMaterialCostCardTypeDetailList = bean.getList2();
			if(scmMaterialCostCardTypeDetailList != null && !scmMaterialCostCardTypeDetailList.isEmpty()){
				for(ScmMaterialCostCardTypeDetail2 scmMaterialCostCardTypeDetail : scmMaterialCostCardTypeDetailList){
					scmMaterialCostCardTypeDetail.setCostCardTypeId(scmMaterialCostCardType.getId());
					scmMaterialCostCardTypeDetailBiz.add(scmMaterialCostCardTypeDetail, param);
				}
			}
		}
	}

	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmMaterialCostCardType2 scmMaterialCostCardType = (ScmMaterialCostCardType2) bean.getList().get(0);
		if(scmMaterialCostCardType != null && scmMaterialCostCardType.getId() > 0){
			List<ScmMaterialCostCardTypeDetail2> scmMaterialCostCardTypeDetailList = bean.getList2();
			if(scmMaterialCostCardTypeDetailList != null && !scmMaterialCostCardTypeDetailList.isEmpty()){
				for(ScmMaterialCostCardTypeDetail2 scmMaterialCostCardTypeDetail : scmMaterialCostCardTypeDetailList){
					scmMaterialCostCardTypeDetail.setCostCardTypeId(scmMaterialCostCardType.getId());
				}
			}
			scmMaterialCostCardTypeDetailBiz.update(scmMaterialCostCardType, scmMaterialCostCardTypeDetailList, ScmMaterialCostCardTypeDetail2.FN_COSTCARDTYPEID, param);
		}
	}

	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmMaterialCostCardType2 scmMaterialCostCardType = (ScmMaterialCostCardType2) bean.getList().get(0);
		if(scmMaterialCostCardType != null && scmMaterialCostCardType.getId() > 0){
			bean.setList2(scmMaterialCostCardTypeDetailBiz.selectByTypeId(scmMaterialCostCardType.getId(), param));
		}
	}

	@Override
	protected void afterDelete(ScmMaterialCostCardType2 entity, Param param) throws AppException {
		if(entity != null && entity.getId() > 0){
		//删除定价明细
			scmMaterialCostCardTypeDetailBiz.deleteByTypeId(entity.getId(), param);
		}
	}
	
}
