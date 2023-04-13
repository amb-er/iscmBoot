
package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmAuditMsgTempletChannel;

public interface ScmAuditMsgTempletChannelBiz extends BaseBiz<ScmAuditMsgTempletChannel> {

	public List<ScmAuditMsgTempletChannel> selectByTempetId(long templetId, Param param) throws AppException;

}
