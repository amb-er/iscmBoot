package com.armitage.server.iscm.basedata.service;


import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.iscm.basedata.model.ScmSupplierEvent2;

public interface ScmSupplierEventBiz extends BaseBiz<ScmSupplierEvent2> {
	public String generateEventQRcode(ScmSupplierEvent2 scmSupplierEvent,Param param);
}

