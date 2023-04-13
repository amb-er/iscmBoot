
package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.dao.ScmMaterialCostCardTypeDetailDAO;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialCostCardTypeDetail2;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialCostCardTypeDetailBiz;
import org.springframework.stereotype.Service;

@Service("scmMaterialCostCardTypeDetailBiz")
public class ScmMaterialCostCardTypeDetailBizImpl extends BaseBizImpl<ScmMaterialCostCardTypeDetail2> implements ScmMaterialCostCardTypeDetailBiz {
	private ScmMaterialBiz scmMaterialBiz;
	
	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	@Override
	public void deleteByTypeId(long typeId, Param param) throws AppException {
		if(typeId > 0){
			List<ScmMaterialCostCardTypeDetail2> scmMaterialCostCardTypeDetailList = this.selectByTypeId(typeId,param);
			this.delete(scmMaterialCostCardTypeDetailList, param);
		}
	}

	@Override
	public List<ScmMaterialCostCardTypeDetail2> selectByTypeId(long typeId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("costCardTypeId", typeId);
		List<ScmMaterialCostCardTypeDetail2> scmMaterialCostCardTypeDetailList = ((ScmMaterialCostCardTypeDetailDAO)dao).selectByTypeId(map);
		if(scmMaterialCostCardTypeDetailList!=null && !scmMaterialCostCardTypeDetailList.isEmpty()) {
			for(ScmMaterialCostCardTypeDetail2 scmMaterialCostCardTypeDetail:scmMaterialCostCardTypeDetailList) {
				if(scmMaterialCostCardTypeDetail.getItemId()>0) {
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmMaterialCostCardTypeDetail.getItemId(), param);
					if(scmMaterial!=null) {
						scmMaterialCostCardTypeDetail.setItemNo(scmMaterial.getItemNo());
						scmMaterialCostCardTypeDetail.setItemName(scmMaterial.getItemName());
					}
				}
			}
		}
		return scmMaterialCostCardTypeDetailList;
	}

}
