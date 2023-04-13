package com.armitage.server.iscm.basedata.service.impl;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iscm.basedata.model.ScmBrandInfo;
import com.armitage.server.iscm.basedata.service.ScmBrandInfoBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import org.springframework.stereotype.Service;

@Service("scmBrandInfoBiz")
public class ScmBrandInfoBizImpl extends BaseBizImpl<ScmBrandInfo> implements ScmBrandInfoBiz {
		@Override
		protected void beforeFindPage(Page page, Param param) throws AppException {
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmBrandInfo.class))+"."+ScmBrandInfo.FN_CONTROLUNITNO,
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmBrandInfo.class))+"."+ScmBrandInfo.FN_CONTROLUNITNO,QueryParam.QUERY_EQ,param.getControlUnitNo()));
		}
}
