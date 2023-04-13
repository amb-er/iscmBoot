
package com.armitage.server.iscm.basedata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.iscm.basedata.dao.ScmCostCategoryDAO;
import com.armitage.server.iscm.basedata.model.ScmCostCategory;
import com.armitage.server.iscm.basedata.service.ScmCostCategoryBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmCostCategoryBiz")
public class ScmCostCategoryBizImpl extends BaseBizImpl<ScmCostCategory> implements ScmCostCategoryBiz {
	private List<String> longNoList = new ArrayList<String>();
	private UsrBiz usrBiz;

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	@Override
	protected void beforeAdd(ScmCostCategory entity, Param param) throws AppException {
		if (entity != null) {
			if(StringUtils.isNotBlank(entity.getClassCode())) {
				ScmCostCategory scmCostCategory = this.selectByCode(entity.getClassCode(),entity.getId(),param);
				if(scmCostCategory!=null) {
					throw new AppException(Message.getMessage(param.getLang(),"iscm.common.input.coderepet"));
				}
			}
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

	@Override
	protected void beforeUpdate(ScmCostCategory oldEntity, ScmCostCategory newEntity, Param param) throws AppException {
		if(StringUtils.isNotBlank(newEntity.getClassCode())) {
			ScmCostCategory scmCostCategory = this.selectByCode(newEntity.getClassCode(),newEntity.getId(),param);
			if(scmCostCategory!=null) {
				throw new AppException(Message.getMessage(param.getLang(),"iscm.common.input.coderepet"));
			}
		}
	}

	private void selectParent(Long id, Param param){
		ScmCostCategory parentEntity = this.select(id, param);
        if (parentEntity != null) {
            longNoList.add(parentEntity.getClassCode());
            selectParent(parentEntity.getParentId(), param);
        }
    }
	
	@Override
	protected void afterAdd(ScmCostCategory entity, Param param) throws AppException {
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
	protected void afterSelect(ScmCostCategory entity, Param param) throws AppException {
		if(entity!=null)
			setConverMap(entity,param);
	}

	private void setConverMap(ScmCostCategory scmCostCategory, Param param) throws AppException {
		if(scmCostCategory!=null) {
			if (StringUtils.isNotBlank(scmCostCategory.getCreator())){
				//创建人
				Usr usr = usrBiz.selectByCode(scmCostCategory.getCreator(), param);
				if (usr != null) {
					scmCostCategory.setConvertMap(ScmCostCategory.FN_CREATOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmCostCategory.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmCostCategory.getEditor(), param);
				if (usr != null) {
					scmCostCategory.setConvertMap(ScmCostCategory.FN_EDITOR, usr);
				}
			}
		}
	}

	@Override
	public ScmCostCategory selectByCode(String code,long excludeId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("classCode", code);
		map.put("controlUnitNo", param.getControlUnitNo());
		map.put("id", excludeId);
		return ((ScmCostCategoryDAO)dao).selectByCode(map);
	}
}
