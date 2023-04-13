package com.armitage.server.api.business.attachment.controller;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.armitage.server.api.business.attachment.params.AttachmentByBillTypeParams;
import com.armitage.server.api.business.attachment.params.AttachmentByBillTypeParamsApi;
import com.armitage.server.api.business.attachment.params.AttachmentParams;
import com.armitage.server.api.business.attachment.params.AttachmentParamsApi;
import com.armitage.server.api.business.attachment.result.AttachmentResult;
import com.armitage.server.api.business.attachment.result.AttachmentResultApi;
import com.armitage.server.api.common.ApiIntegratedRequest;
import com.armitage.server.api.common.ApiVersion;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.armitage.server.api.business.attachment.result.AttachmentByBillTypeResult;
import com.armitage.server.api.business.attachment.result.AttachmentByBillTypeResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.Base64;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmBaseAttachmentBiz;
import com.armitage.server.mongodb.biz.MongoDBImageBiz;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/attachment")
@Api(tags="附件接口")
public class AttachmentController {
	private static Log log = LogFactory.getLog(AttachmentController.class);
	private ScmBaseAttachmentBiz scmBaseAttachmentBiz = (ScmBaseAttachmentBiz) AppContextUtil.getBean("scmBaseAttachmentBiz");
	private MongoDBImageBiz mongoDBImageBiz = (MongoDBImageBiz) AppContextUtil.getBean("mongoDBImageBiz");

	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryAttachmentByBillType", method=RequestMethod.POST)
    @ApiOperation(value="根据单据类型查询附件", consumes="application/json",position=1)
    public AttachmentByBillTypeResultApi queryAttachmentByBillType(@RequestBody AttachmentByBillTypeParamsApi params) {
		AttachmentByBillTypeResultApi result = new AttachmentByBillTypeResultApi(); 	//显示结果集
		ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	AttachmentByBillTypeParams attachmentByBillTypeParams = params.getParams();
        	if (attachmentByBillTypeParams == null) {
        		throw new AppException("webservice.params.null");
        	}
        	List<ScmBaseAttachment> scmBaseAttachments = scmBaseAttachmentBiz.queryAttachmentByBillType(attachmentByBillTypeParams,ParamHelper.createParam(integratedRequest,"queryAttachmentByBillType"));
        	if (scmBaseAttachments != null && !scmBaseAttachments.isEmpty()) {
        		List<AttachmentByBillTypeResult> resultList = new ArrayList<>();
				for (ScmBaseAttachment scmBaseAttachment : scmBaseAttachments) {
					AttachmentByBillTypeResult scmAttachmentByBillTypeResult = new AttachmentByBillTypeResult();
					scmAttachmentByBillTypeResult.setId(scmBaseAttachment.getId());
					scmAttachmentByBillTypeResult.setName(scmBaseAttachment.getFileName());
					scmAttachmentByBillTypeResult.setType(scmBaseAttachment.getDocType());
					resultList.add(scmAttachmentByBillTypeResult);
				}
				result.setResultList(resultList);
			}
        } catch (Exception e) {
        	log.error(e);
        	result.setErrCode("-1");
        	result.setErrText("根据单据类型查询附件失败：" + Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
        }
		return result;
	}
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryAttachment", method=RequestMethod.POST)
    @ApiOperation(value="获取附件", consumes="application/json",position=1)
    public AttachmentResultApi queryAttachment(@RequestBody AttachmentParamsApi params) {
		AttachmentResultApi result = new AttachmentResultApi(); 	//显示结果集
		ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	AttachmentParams attachmentParams = params.getParams();
        	if (attachmentParams == null) {
        		throw new AppException("webservice.params.null");
        	}
        	Param createParam = ParamHelper.createParam(integratedRequest,"queryAttachmentByBillType");
        	ScmBaseAttachment scmBaseAttachment = scmBaseAttachmentBiz.select(attachmentParams.getId(),createParam);
        	if (scmBaseAttachment != null) {
        		AttachmentResult attachmentResult = new AttachmentResult();
        		attachmentResult.setType(scmBaseAttachment.getDocType());
        		attachmentResult.setName(scmBaseAttachment.getFileName());
        		InputStream in = mongoDBImageBiz.select("iscm", "ScmBaseAttachment", scmBaseAttachment.getFilePath());
				if (in != null) {
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					byte[] bytes = new byte[1024];
					byte[] buffer = null;
					int c;
					try {
						while ((c = in.read(bytes)) != -1) {
							os.write(bytes, 0, c);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					buffer = os.toByteArray();
					attachmentResult.setData(Base64.encode(buffer));
				}
				result.setResult(attachmentResult);
			}
        } catch (Exception e) {
        	log.error(e);
        	result.setErrCode("-1");
        	result.setErrText("获取附件失败：" + Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
        }
		return result;
	}
}
