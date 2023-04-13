
package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.JSONUtils;
import com.armitage.server.iscm.basedata.dao.ScmMaterialCompanyInfoDAO;
import com.armitage.server.iscm.basedata.model.ScmMaterialCompanyInfo;
import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelation2;
import com.armitage.server.iscm.basedata.service.ScmMaterialCompanyInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialUnitRelationBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service("scmMaterialCompanyInfoBiz")
public class ScmMaterialCompanyInfoBizImpl extends BaseBizImpl<ScmMaterialCompanyInfo> implements ScmMaterialCompanyInfoBiz {
	private ScmMaterialUnitRelationBiz scmMaterialUnitRelationBiz;
	private UsrBiz usrBiz;
	
	public void setScmMaterialUnitRelationBiz(ScmMaterialUnitRelationBiz scmMaterialUnitRelationBiz) {
		this.scmMaterialUnitRelationBiz = scmMaterialUnitRelationBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	@Override
	protected void beforeAdd(ScmMaterialCompanyInfo entity, Param param)
			throws AppException {
		if(entity!=null){
			entity.setCreator(param.getUsrCode());
			entity.setCreateDate(CalendarUtil.getDate(param));
		}
	}

	@Override
	protected void beforeUpdate(ScmMaterialCompanyInfo oldEntity,
			ScmMaterialCompanyInfo newEntity, Param param) throws AppException {
		if(newEntity!=null){
			if(StringUtils.isBlank(newEntity.getCreator()))
				newEntity.setCreator(param.getUsrCode());
			if(newEntity.getCreateDate()==null)
				newEntity.setCreateDate(CalendarUtil.getDate(param));
			newEntity.setEditor(param.getUsrCode());
			newEntity.setCreateDate(CalendarUtil.getDate(param));
		}
	}

	@Override
	public ScmMaterialCompanyInfo selectByItemIdAndOrgUnitNo(long itemId, String resOrgUnitNo, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("itemId", itemId);
		map.put("orgUnitNo", resOrgUnitNo);
		ScmMaterialCompanyInfo scmMaterialCompanyInfo = ((ScmMaterialCompanyInfoDAO) dao).selectByItemIdAndOrgUnitNo(map);
		if(scmMaterialCompanyInfo!=null) {
			setConvertMap(scmMaterialCompanyInfo,param);
		}
		return scmMaterialCompanyInfo;
	}
	
	private void setConvertMap(ScmMaterialCompanyInfo scmMaterialCompanyInfo,  Param param){
		if(scmMaterialCompanyInfo != null){
			if (StringUtils.isNotBlank(scmMaterialCompanyInfo.getCreator())){
				//创建人
				Usr usr = usrBiz.selectByCode(scmMaterialCompanyInfo.getCreator(), param);
				if (usr != null) {
					scmMaterialCompanyInfo.setConvertMap(ScmMaterialCompanyInfo.FN_CREATOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmMaterialCompanyInfo.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmMaterialCompanyInfo.getEditor(), param);
				if (usr != null) {
					scmMaterialCompanyInfo.setConvertMap(ScmMaterialCompanyInfo.FN_EDITOR, usr);
				}
			}
		}
	}

	@Override
	public ScmMaterialCompanyInfo updateByCompanyInfo(ScmMaterialCompanyInfo scmMaterialCompanyInfo, Param param)
			throws AppException {
		if(scmMaterialCompanyInfo!=null) {
			ScmMaterialCompanyInfo oldScmMaterialCompanyInfo = this.selectByItemIdAndOrgUnitNo(scmMaterialCompanyInfo.getItemId(),param.getOrgUnitNo(),param);
			ScmMaterialCompanyInfo seleScmMaterialCompanyInfo = this.selectByItemIdAndOrgUnitNo(scmMaterialCompanyInfo.getItemId(),param.getControlUnitNo(),param);
			boolean flag = true;
			if (seleScmMaterialCompanyInfo != null) {
				Gson newGson = JSONUtils.newGson();
				String json = newGson.toJson(seleScmMaterialCompanyInfo);
				String json1 = newGson.toJson(scmMaterialCompanyInfo);
				if (StringUtils.equals(json, json1)) {
					flag=false;
				}
			}
			boolean exists = true;
			if(oldScmMaterialCompanyInfo==null) {
				oldScmMaterialCompanyInfo = new ScmMaterialCompanyInfo(true);
				exists = false;
			}
			long id = oldScmMaterialCompanyInfo.getId();
			BeanUtil.copyProperties(oldScmMaterialCompanyInfo, scmMaterialCompanyInfo);
			oldScmMaterialCompanyInfo.setId(id);
			if(exists) {
				this.update(oldScmMaterialCompanyInfo, param);
			}else {
				if (flag) {
					oldScmMaterialCompanyInfo.setOrgUnitNo(param.getOrgUnitNo());
					this.add(oldScmMaterialCompanyInfo, param);
				}
			}
			return oldScmMaterialCompanyInfo;
		}
		return null;
	}
}
