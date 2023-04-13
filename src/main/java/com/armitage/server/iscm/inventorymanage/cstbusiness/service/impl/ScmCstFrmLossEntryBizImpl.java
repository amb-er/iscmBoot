package com.armitage.server.iscm.inventorymanage.cstbusiness.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmCstFrmLossEntryDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvCostConsumeEntryDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLossEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmCstFrmLossEntryBiz;
import org.springframework.stereotype.Service;

@Service("scmCstFrmLossEntryBiz")
public class ScmCstFrmLossEntryBizImpl extends BaseBizImpl<ScmCstFrmLossEntry2> implements ScmCstFrmLossEntryBiz {

	@Override
	public List<ScmCstFrmLossEntry2> selectByBillId(long billId, Param param)
			throws AppException {
		if(billId > 0){
            HashMap<String, Object> map = new HashMap<>();
            map.put("billId", billId);
            return ((ScmCstFrmLossEntryDAO) dao).selectByBillId(map);
        }
        return null;
	}

	@Override
	public void deleteByBillId(long billId, Param param) throws AppException {
		if(billId > 0){
            HashMap<String, Object> map = new HashMap<>();
            map.put("billId", billId);
            ((ScmCstFrmLossEntryDAO) dao).deleteByBillId(map);
        }   
	}

	@Override
	public List<ScmCstFrmLossEntry2> selectOutEffectRow(long billId, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("billId", billId);
		return ((ScmCstFrmLossEntryDAO) dao).selectOutEffectRow(map);
	}

}
