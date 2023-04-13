
package com.armitage.server.iscm.purchasemanage.purchasesetting.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmPurchaseType;
import com.armitage.server.iscm.basedata.service.ScmPurchaseTypeBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurchaseCanuseSetBizTypeDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSetBizType2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurchaseCanuseSetBizTypeBiz;
import org.springframework.stereotype.Service;


@Service("scmPurchaseCanuseSetBizTypeBiz")
public class ScmPurchaseCanuseSetBizTypeBizImpl extends BaseBizImpl<ScmPurchaseCanuseSetBizType2> implements ScmPurchaseCanuseSetBizTypeBiz {
	private ScmPurchaseTypeBiz scmPurchaseTypeBiz;
	
	public void setScmPurchaseTypeBiz(ScmPurchaseTypeBiz scmPurchaseTypeBiz) {
		this.scmPurchaseTypeBiz = scmPurchaseTypeBiz;
	}

	@Override
	public List<ScmPurchaseCanuseSetBizType2> selectByPcsId(long pcsId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("pcsId",pcsId);
		List<ScmPurchaseCanuseSetBizType2> scmPurchaseCanuseSetBizTypeList = ((ScmPurchaseCanuseSetBizTypeDAO)dao).selectByPcsId(map);
		if(scmPurchaseCanuseSetBizTypeList!=null && !scmPurchaseCanuseSetBizTypeList.isEmpty()) {
			for(ScmPurchaseCanuseSetBizType2 scmPurchaseCanuseSetBizType:scmPurchaseCanuseSetBizTypeList) {
				if(scmPurchaseCanuseSetBizType.getPurTypeId()>0) {
					ScmPurchaseType scmPurchaseType = scmPurchaseTypeBiz.selectDirect(scmPurchaseCanuseSetBizType.getPurTypeId(), param);
					if(scmPurchaseType!=null)
						scmPurchaseCanuseSetBizType.setName(scmPurchaseType.getName());
				}
			}
		}
		return scmPurchaseCanuseSetBizTypeList;
	}

	@Override
	public void deleteByPcsId(long pcsId, Param param) throws AppException {
		List<ScmPurchaseCanuseSetBizType2> scmPurchaseCanuseSetBizTypeList = this.selectByPcsId(pcsId, param);
		if(scmPurchaseCanuseSetBizTypeList!=null && !scmPurchaseCanuseSetBizTypeList.isEmpty()) {
			this.delete(scmPurchaseCanuseSetBizTypeList, param);
		}
	}

}
