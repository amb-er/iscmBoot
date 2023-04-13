
package com.armitage.server.iscm.purchasemanage.purchasesetting.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurchaseCanuseSetMCDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSetMC2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurchaseCanuseSetMCBiz;
import org.springframework.stereotype.Service;

@Service("scmPurchaseCanuseSetMCBiz")
public class ScmPurchaseCanuseSetMCBizImpl extends BaseBizImpl<ScmPurchaseCanuseSetMC2> implements ScmPurchaseCanuseSetMCBiz {
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	
	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}

	@Override
	public List<ScmPurchaseCanuseSetMC2> selectByPcsId(long pcsId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("pcsId", pcsId);
		List<ScmPurchaseCanuseSetMC2> scmPurchaseCanuseSetMCList = ((ScmPurchaseCanuseSetMCDAO) dao).selectByPcsId(map);
		if(scmPurchaseCanuseSetMCList!=null && !scmPurchaseCanuseSetMCList.isEmpty()) {
			for(ScmPurchaseCanuseSetMC2 scmPurchaseCanuseSetMC:scmPurchaseCanuseSetMCList) {
				if(scmPurchaseCanuseSetMC.getClassId()>0) {
					ScmMaterialGroup scmMaterialGroup = scmMaterialGroupBiz.selectDirect(scmPurchaseCanuseSetMC.getClassId(), param);
					if(scmMaterialGroup!=null)
						scmPurchaseCanuseSetMC.setClassName(scmMaterialGroup.getClassName());
				}
			}
		}
		return scmPurchaseCanuseSetMCList;
	}

	@Override
	public void deleteByPcsId(long pcsId, Param param) throws AppException {
		List<ScmPurchaseCanuseSetMC2> scmPurchaseCanuseSetMCList = this.selectByPcsId(pcsId, param);
		if(scmPurchaseCanuseSetMCList!=null && !scmPurchaseCanuseSetMCList.isEmpty()) {
			this.delete(scmPurchaseCanuseSetMCList, param);
		}
	}
}
