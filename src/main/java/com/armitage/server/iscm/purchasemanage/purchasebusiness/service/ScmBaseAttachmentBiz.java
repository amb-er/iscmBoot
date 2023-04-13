
package com.armitage.server.iscm.purchasemanage.purchasebusiness.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;

import com.armitage.server.api.business.attachment.params.AttachmentByBillTypeParams;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment;

public interface ScmBaseAttachmentBiz extends BaseBiz<ScmBaseAttachment> {

	public ScmBaseAttachment findBybillId(HashedMap hashedMap, Param param) throws AppException;
	public List<ScmBaseAttachment> findBybillTypeId(HashMap<String, Object> hashedMap, Param param) throws AppException;
	public List<ScmBaseAttachment> queryAttachmentByBillType(AttachmentByBillTypeParams attachmentByBillTypeParams,Param createParam) throws AppException;

}
