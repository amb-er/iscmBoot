
package com.armitage.server.iscm.purchasemanage.purchasesetting.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurSupplyRecOrgDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSource2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyInfo2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyRecOrg2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurSupplyRecOrgBiz;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgStorageBiz;
import org.springframework.stereotype.Service;

@Service("scmPurSupplyRecOrgBiz")
public class ScmPurSupplyRecOrgBizImpl extends BaseBizImpl<ScmPurSupplyRecOrg2> implements ScmPurSupplyRecOrgBiz {
	private OrgStorageBiz orgStorageBiz;
	
	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}

	@Override
	public void updateBySupplyInfo(ScmPurSupplyInfo2 scmPurSupplyInfo,List<ScmPurSupplyRecOrg2> scmPurSupplyRecOrgList, Param param) throws AppException {
		List<ScmPurSupplyRecOrg2> oldScmPurSupplyRecOrgList = this.selectBySupplyInfoId(scmPurSupplyInfo.getId(),param);
		if(oldScmPurSupplyRecOrgList!=null && !oldScmPurSupplyRecOrgList.isEmpty()){
			for (int i = oldScmPurSupplyRecOrgList.size() - 1; i >= 0; i--) {
				ScmPurSupplyRecOrg2 oldScmPurSupplyRecOrg = oldScmPurSupplyRecOrgList.get(i);
				if(scmPurSupplyRecOrgList!=null && !scmPurSupplyRecOrgList.isEmpty()) {
					for (int j = scmPurSupplyRecOrgList.size() - 1; j >= 0; j--) {
						ScmPurSupplyRecOrg2 scmPurSupplyRecOrg = scmPurSupplyRecOrgList.get(j);
						if(StringUtils.equals(scmPurSupplyRecOrg.getReceiveOrgUnitNo(),oldScmPurSupplyRecOrg.getReceiveOrgUnitNo())){
							oldScmPurSupplyRecOrgList.remove(i);
							scmPurSupplyRecOrgList.remove(j);
							break;
						}
					}
				}
			}
			if(!oldScmPurSupplyRecOrgList.isEmpty()){
				this.delete(oldScmPurSupplyRecOrgList, param);
			}
			if(scmPurSupplyRecOrgList!=null && !scmPurSupplyRecOrgList.isEmpty()){
				for(ScmPurSupplyRecOrg2 scmPurSupplyRecOrg:scmPurSupplyRecOrgList){
					scmPurSupplyRecOrg.setSupplyId(scmPurSupplyInfo.getId());
				}
				this.add(scmPurSupplyRecOrgList, param);
			}
		}else{
			if(scmPurSupplyRecOrgList!=null && !scmPurSupplyRecOrgList.isEmpty()){
				//全部新增
				for(ScmPurSupplyRecOrg2 scmPurSupplyRecOrg:scmPurSupplyRecOrgList){
					scmPurSupplyRecOrg.setSupplyId(scmPurSupplyInfo.getId());
				}
				this.add(scmPurSupplyRecOrgList, param);
			}
		}
	}

	@Override
	public List<ScmPurSupplyRecOrg2> selectBySupplyInfoId(long supplyId,Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("supplyId", supplyId);
		List<ScmPurSupplyRecOrg2> scmPurSupplyRecOrgList = ((ScmPurSupplyRecOrgDAO)dao).selectBySupplyInfoId(map);
		if(scmPurSupplyRecOrgList!=null && !scmPurSupplyRecOrgList.isEmpty()){
			for(ScmPurSupplyRecOrg2 scmPurSupplyRecOrg:scmPurSupplyRecOrgList){
				OrgStorage2 orgStorage = orgStorageBiz.selectByOrgUnitNo(scmPurSupplyRecOrg.getReceiveOrgUnitNo(), param);
				if(orgStorage!=null)
					scmPurSupplyRecOrg.setReceiveOrgUnitName(orgStorage.getOrgUnitName());
			}
		}
		return scmPurSupplyRecOrgList;
	}

	@Override
	public void addBySupplierSource(ScmPurSupplierSource2 scmPurSupplierSource, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("billId", scmPurSupplierSource.getId());
		((ScmPurSupplyRecOrgDAO)dao).addBySupplierSource(map);
	}
	
}
