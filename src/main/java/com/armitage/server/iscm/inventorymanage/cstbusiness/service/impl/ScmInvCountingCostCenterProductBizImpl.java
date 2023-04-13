
package com.armitage.server.iscm.inventorymanage.cstbusiness.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterEntryDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterProductDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterProduct;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterProduct2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingCostCenterProductBiz;
import org.springframework.stereotype.Service;

@Service("scmInvCountingCostCenterProductBiz")
public class ScmInvCountingCostCenterProductBizImpl extends BaseBizImpl<ScmInvCountingCostCenterProduct2> implements ScmInvCountingCostCenterProductBiz {

	@Override
	public List<ScmInvCountingCostCenterProduct2> selectByTableId(long tableId, Param param) throws AppException {
		if(tableId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("tableId", tableId);
			List<ScmInvCountingCostCenterProduct2> scmInvCountingCostCenterProduct2List = ((ScmInvCountingCostCenterProductDAO)dao).selectByTableId(map);
			if (scmInvCountingCostCenterProduct2List != null && !scmInvCountingCostCenterProduct2List.isEmpty()) {
				int lineId = 1;
				for (ScmInvCountingCostCenterProduct2 scmInvCountingCostCenterProduct2 : scmInvCountingCostCenterProduct2List) {
					scmInvCountingCostCenterProduct2.setLineId(lineId);
					lineId = lineId+1;
					setConvertMap(scmInvCountingCostCenterProduct2, param);
				}
				return scmInvCountingCostCenterProduct2List;
			}
		}
		return null;
	}

	private void setConvertMap(ScmInvCountingCostCenterProduct2 scmInvCountingCostCenterProduct2, Param param) {
		
	}

	@Override
	public void deleteByTableId(long tableId, Param param) throws AppException {
		if(tableId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("tableId", tableId);
			((ScmInvCountingCostCenterProductDAO) dao).deleteByTableId(map);
		}
	}

}
