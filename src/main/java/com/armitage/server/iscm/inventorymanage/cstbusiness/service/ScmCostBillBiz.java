package com.armitage.server.iscm.inventorymanage.cstbusiness.service;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCostBill;

public interface ScmCostBillBiz extends BaseBiz<ScmCostBill>{

	List<ScmCostBill> selectPostAndDateByItem(String string, Date postDate, String orgUnitNo, String costOrgUnitNo,Param param) throws AppException;

}
