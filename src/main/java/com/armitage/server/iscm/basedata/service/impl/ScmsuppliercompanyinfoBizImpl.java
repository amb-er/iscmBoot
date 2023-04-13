package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.iscm.basedata.dao.ScmsuppliercompanyinfoDAO;
import com.armitage.server.iscm.basedata.model.ScmMaterialCompanyInfo;
import com.armitage.server.iscm.basedata.model.Scmsupplierbank;
import com.armitage.server.iscm.basedata.model.Scmsuppliercompanyinfo2;
import com.armitage.server.iscm.basedata.service.ScmsupplierbankBiz;
import com.armitage.server.iscm.basedata.service.ScmsuppliercompanyinfoBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmsuppliercompanyinfoBiz")
public class ScmsuppliercompanyinfoBizImpl extends BaseBizImpl<Scmsuppliercompanyinfo2> implements ScmsuppliercompanyinfoBiz {

	private ScmsupplierbankBiz scmsupplierbankBiz;
	private UsrBiz usrBiz;
	
	public void setScmsupplierbankBiz(ScmsupplierbankBiz scmsupplierbankBiz) {
		this.scmsupplierbankBiz = scmsupplierbankBiz;
	}
                                     
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	@Override
	public Scmsuppliercompanyinfo2 selectByVendorIdAndOrgUnitNo(long vendorId, String resOrgUnitNo, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("vendorId", vendorId);
		map.put("orgUnitNo", resOrgUnitNo);
		Scmsuppliercompanyinfo2 scmsuppliercompanyinfo = ((ScmsuppliercompanyinfoDAO) dao).selectByVendorIdAndOrgUnitNo(map);
		if(scmsuppliercompanyinfo != null && scmsuppliercompanyinfo.getVendorId() > 0){
			setConvertMap(scmsuppliercompanyinfo,param);
			//加载银行信息
			List<Scmsupplierbank> list = scmsupplierbankBiz.selectByVendorIdAndOrgUnitNo(scmsuppliercompanyinfo.getVendorId(), scmsuppliercompanyinfo.getOrgUnitNo(), param);
			if(list != null && !list.isEmpty()){
				scmsuppliercompanyinfo.setScmsupplierbankList(list);
			}
		}
		return scmsuppliercompanyinfo;
	}
	
	@Override
	protected void afterAdd(Scmsuppliercompanyinfo2 entity, Param param) throws AppException {
		if(entity != null 
				&& entity.getScmsupplierbankList() != null
				&& !entity.getScmsupplierbankList().isEmpty()){
			//新增银行信息
			for(Scmsupplierbank scmsupplierbank : entity.getScmsupplierbankList()){
				scmsupplierbank.setOrgUnitNo(entity.getOrgUnitNo());
			}
			scmsupplierbankBiz.add(entity.getScmsupplierbankList(), param);
		}
	}
	
	@Override
	protected void afterUpdate(Scmsuppliercompanyinfo2 oldEntity,Scmsuppliercompanyinfo2 entity, Param param) throws AppException {
		if(entity != null 
				&& entity.getScmsupplierbankList() != null
				&& !entity.getScmsupplierbankList().isEmpty()){
			//更新银行信息
			List<Scmsupplierbank> scmsupplierbankList = entity.getScmsupplierbankList();
			for(Scmsupplierbank scmsupplierbank : scmsupplierbankList){
				if(scmsupplierbank.getId() > 0){
					scmsupplierbankBiz.update(scmsupplierbank, param);
				}else{
					scmsupplierbank.setOrgUnitNo(entity.getOrgUnitNo());
					scmsupplierbankBiz.add(scmsupplierbank, param);
				}
			}
		}
	}
	
	@Override
	protected void afterDelete(Scmsuppliercompanyinfo2 entity, Param param) throws AppException {
		if(entity != null && entity.getVendorId() > 0){
			//删除银行信息
			scmsupplierbankBiz.deleteByVendorId(entity.getVendorId(), param);
		}
	}
	
	private void setConvertMap(Scmsuppliercompanyinfo2 scmsuppliercompanyinfo, Param param){
		if(scmsuppliercompanyinfo != null){
			if (StringUtils.isNotBlank(scmsuppliercompanyinfo.getCreator())){
				//创建人
				Usr usr = usrBiz.selectByCode(scmsuppliercompanyinfo.getCreator(), param);
				if (usr != null) {
					scmsuppliercompanyinfo.setConvertMap(Scmsuppliercompanyinfo2.FN_CREATOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmsuppliercompanyinfo.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmsuppliercompanyinfo.getEditor(), param);
				if (usr != null) {
					scmsuppliercompanyinfo.setConvertMap(Scmsuppliercompanyinfo2.FN_EDITOR, usr);
				}
			}
		}
	}

	@Override
	public Scmsuppliercompanyinfo2 updateByCompanyInfo(Scmsuppliercompanyinfo2 scmsuppliercompanyinfo, Param param)
			throws AppException {
		if(scmsuppliercompanyinfo!=null) {
			Scmsuppliercompanyinfo2 oldScmsuppliercompanyinfo = this.selectByVendorIdAndOrgUnitNo(scmsuppliercompanyinfo.getVendorId(),scmsuppliercompanyinfo.getOrgUnitNo(),param);
			boolean exists = true;
			if(oldScmsuppliercompanyinfo==null) {
				oldScmsuppliercompanyinfo = new Scmsuppliercompanyinfo2(true);
				exists = false;
			}
			long id = oldScmsuppliercompanyinfo.getId();
			BeanUtil.copyProperties(oldScmsuppliercompanyinfo, scmsuppliercompanyinfo);
			oldScmsuppliercompanyinfo.setId(id);
			if(exists) {
				this.update(oldScmsuppliercompanyinfo, param);
			}else {
				this.add(oldScmsuppliercompanyinfo, param);
			}
			return oldScmsuppliercompanyinfo;
		}
		return null;
	}
}