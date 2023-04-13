package com.armitage.server.iscm.inventorymanage.inventorydata.service;


import java.util.List;

import com.armitage.server.api.business.datasync.params.InvStockListParams;
import com.armitage.server.api.business.datasync.result.DataSyncResult;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStockNo;

public interface ScmInvStockNoBiz extends BaseBiz<ScmInvStockNo> {

	DataSyncResult dataSync(InvStockListParams invStockListParam, List<ScmInvStock2> scmInvStockS,
			Param param) throws AppException;
	
}

