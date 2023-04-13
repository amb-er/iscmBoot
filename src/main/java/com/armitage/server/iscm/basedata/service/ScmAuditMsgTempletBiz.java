
package com.armitage.server.iscm.basedata.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmAuditMsgTemplet;
import com.armitage.server.iscm.basedata.model.ScmAuditMsgTemplet2;

public interface ScmAuditMsgTempletBiz extends BaseBiz<ScmAuditMsgTemplet2> {
	public ScmAuditMsgTemplet2 selectByTempetType(String templetType,Param param) throws AppException;
}
