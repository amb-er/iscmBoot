package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.dao.ScmSupplierUnifiedDAO;
import com.armitage.server.iscm.basedata.model.ScmSupplierUnified;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.ScmSupplierUnifiedBiz;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgStorageBiz;
import org.springframework.stereotype.Service;

@Service("scmSupplierUnifiedBiz")
public class ScmSupplierUnifiedBizImpl extends BaseBizImpl<ScmSupplierUnified> implements ScmSupplierUnifiedBiz {
	private OrgStorageBiz orgStorageBiz;
	
	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}

	@Override
	public void saveUnified(Scmsupplier2 scmsupplier,List<ScmSupplierUnified> scmSupplierUnifiedList,Param param) throws AppException {
		List<ScmSupplierUnified> oldScmSupplierUnifiedList = this.selectByVendorId(scmsupplier.getId(), param);
		if(oldScmSupplierUnifiedList!=null && !oldScmSupplierUnifiedList.isEmpty()){
			for(int i=oldScmSupplierUnifiedList.size()-1;i>=0;i--){
				ScmSupplierUnified oldScmSupplierUnified = oldScmSupplierUnifiedList.get(0);
				if(scmSupplierUnifiedList!=null && !scmSupplierUnifiedList.isEmpty()) {
					for(int j=scmSupplierUnifiedList.size()-1;i>=0;i--){
						if(StringUtils.equals(oldScmSupplierUnified.getOrgUnitNo(), scmSupplierUnifiedList.get(j).getOrgUnitNo())){
							scmSupplierUnifiedList.remove(j);
							oldScmSupplierUnifiedList.remove(i);
							break;
						}
					}
				}
			}
			if(!oldScmSupplierUnifiedList.isEmpty()){
				this.delete(oldScmSupplierUnifiedList, param);
			}
		}
		if(scmSupplierUnifiedList!=null && !scmSupplierUnifiedList.isEmpty()){
			for(ScmSupplierUnified scmSupplierUnified:scmSupplierUnifiedList){
				scmSupplierUnified.setVendorId(scmsupplier.getId());
				this.add(scmSupplierUnified, param);
			}
		}
	}

	@Override
	public List<ScmSupplierUnified> selectByVendorId(long vendorId, Param param)	throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("vendorId", vendorId);
		List<ScmSupplierUnified> scmSupplierUnifiedList =  ((ScmSupplierUnifiedDAO)dao).selectByVendorId(map);
		if(scmSupplierUnifiedList!=null && !scmSupplierUnifiedList.isEmpty()){
			for(ScmSupplierUnified scmSupplierUnified:scmSupplierUnifiedList){
				setConvertMap(scmSupplierUnified, param);
			}
		}
		return scmSupplierUnifiedList;
	}

	private void setConvertMap(ScmSupplierUnified scmSupplierUnified,Param param){
		if(StringUtils.isNotBlank(scmSupplierUnified.getOrgUnitNo())){
			OrgStorage2 orgStorage = orgStorageBiz.selectByOrgUnitNo(scmSupplierUnified.getOrgUnitNo(), param);
			if(orgStorage!=null)
				scmSupplierUnified.setConvertMap(ScmSupplierUnified.FN_ORGUNITNO,orgStorage);
		}
	}
}
