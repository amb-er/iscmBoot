package com.armitage.server.activity.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.armitage.server.activity.model.TemplateParam;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.AESUtil;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.JSONUtils;
import com.armitage.server.external.service.client.MsgClient;
import com.armitage.server.external.service.model.DoSubmitMessageParams;
import com.armitage.server.external.service.model.DoSubmitMessageResult;
import com.armitage.server.iscm.basedata.model.ScmAuditMsgTemplet2;
import com.armitage.server.iscm.basedata.model.ScmAuditMsgTempletChannel;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBill2;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.ScmAuditMsgTempletBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.common.model.ScmMsginfo;
import com.armitage.server.iscm.common.model.ScmMsginfoAdvQuery;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLoss2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBill2;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturns2;
import com.armitage.server.system.model.AppService2;
import com.armitage.server.system.model.InternalMsgInfo2;
import com.armitage.server.system.model.InternalMsgInfoUsr;
import com.armitage.server.system.model.Report2;
import com.armitage.server.system.service.AppServiceBiz;
import com.armitage.server.system.service.InternalMsgInfoBiz;
import com.armitage.server.system.service.InternalMsgInfoUsrBiz;
import com.armitage.server.system.util.AppServiceUtil;
import com.armitage.server.webservice.model.ReportRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AuditMsgUtil {
	private static Log log = LogFactory.getLog(AuditMsgUtil.class);

	public static void sendAuditMsg(String billTypeCode,BaseModel model,Object queryObj,String usrList,Param param){
		sendAuditMsg(billTypeCode,"A",model,queryObj,usrList,param);
	}
	public static void sendAuditMsg(String billTypeCode,String activeType,BaseModel model,Object queryObj,String usrList,Param param){
		if(StringUtils.isBlank(usrList) || model==null)
			throw new AppException("com.armitage.server.activity.util.AuditMsgUtil.sendAuditMsg.error.paramerror");
		ScmAuditMsgTempletBiz scmAuditMsgTempletBiz = (ScmAuditMsgTempletBiz) AppContextUtil.getBean("scmAuditMsgTempletBiz");
		ScmAuditMsgTemplet2 scmAuditMsgTemplet = scmAuditMsgTempletBiz.selectByTempetType(activeType, param);	//获取审批消息模板
		if(scmAuditMsgTemplet==null) {
			//不存在模板使用默认值
			scmAuditMsgTemplet = new ScmAuditMsgTemplet2();
			scmAuditMsgTemplet.setTempletName("单据审批流程");
			scmAuditMsgTemplet.setTitle("单据提交审核");
			scmAuditMsgTemplet.setContent("您有单据需要审批!");
		}
		String publicNo = AppServiceUtil.getAppServiceParamValue(param.getControlUnitNo(),"WorkGroup", "publicNo", param);
		//消息落盘
		InternalMsgInfo2 internalMsgInfo = AuditMsgUtil.saveMsg(usrList, publicNo, scmAuditMsgTemplet, param);
		String templetId="";
		if(scmAuditMsgTemplet!=null && scmAuditMsgTemplet.getScmAuditMsgTempletChannelList()!=null && !scmAuditMsgTemplet.getScmAuditMsgTempletChannelList().isEmpty()) {
			for(ScmAuditMsgTempletChannel scmAuditMsgTempletChannel:scmAuditMsgTemplet.getScmAuditMsgTempletChannelList()) {
				if(StringUtils.equals("weChat",scmAuditMsgTempletChannel.getChannelCode())) {
					templetId=scmAuditMsgTempletChannel.getTemplateId();
				}
			}
		}
		//发送审批消息
		if(StringUtils.isNotBlank(templetId))
			AuditMsgUtil.sendMsg(templetId,billTypeCode,model,queryObj, internalMsgInfo, param);
	  }  
	
	public static void sendSupplierAuditMsg(String billTypeCode,String activeType,BaseModel model,Object queryObj,String usrList,Param param){
		if(StringUtils.isBlank(usrList) || model==null)
			throw new AppException("com.armitage.server.activity.util.AuditMsgUtil.sendAuditMsg.error.paramerror");
		ScmAuditMsgTempletBiz scmAuditMsgTempletBiz = (ScmAuditMsgTempletBiz) AppContextUtil.getBean("scmAuditMsgTempletBiz");
		ScmAuditMsgTemplet2 scmAuditMsgTemplet = scmAuditMsgTempletBiz.selectByTempetType(activeType, param);	//获取审批消息模板
		if(scmAuditMsgTemplet==null) {
			//不存在模板使用默认值
			scmAuditMsgTemplet = new ScmAuditMsgTemplet2();
			scmAuditMsgTemplet.setTempletName("供应商资质审核");
			scmAuditMsgTemplet.setTitle("供应商提交审核");
			scmAuditMsgTemplet.setContent("您有新供应商注册,请至电脑端审核");
		}
		String publicNo = AppServiceUtil.getAppServiceParamValue(param.getControlUnitNo(),"WorkGroup", "publicNo", param);
//		if(StringUtils.isBlank(publicNo))
//			throw new AppException("com.armitage.server.activity.util.AuditMsgUtil.sendAuditMsg.error.publicNoisnull");
		//消息落盘
		InternalMsgInfo2 internalMsgInfo = AuditMsgUtil.saveMsg(usrList, publicNo, scmAuditMsgTemplet, param);
		String templetId="";
		if(scmAuditMsgTemplet!=null && scmAuditMsgTemplet.getScmAuditMsgTempletChannelList()!=null && !scmAuditMsgTemplet.getScmAuditMsgTempletChannelList().isEmpty()) {
			for(ScmAuditMsgTempletChannel scmAuditMsgTempletChannel:scmAuditMsgTemplet.getScmAuditMsgTempletChannelList()) {
				if(StringUtils.equals("weChat",scmAuditMsgTempletChannel.getChannelCode())) {
					templetId=scmAuditMsgTempletChannel.getTemplateId();
				}
			}
		}
		//发送审批消息
		if(StringUtils.isNotBlank(templetId))
			AuditMsgUtil.sendSupplierMsg(templetId,billTypeCode,model,queryObj, internalMsgInfo, param);
	  }
  
	public static InternalMsgInfo2 saveMsg(String usrList,String publicNo,ScmAuditMsgTemplet2 scmAuditMsgTemplet,Param param){
		InternalMsgInfoBiz internalMsgInfoBiz = (InternalMsgInfoBiz) AppContextUtil.getBean("internalMsgInfoBiz");
		InternalMsgInfo2 internalMsgInfo = new InternalMsgInfo2();
		internalMsgInfo.setSender(param.getUsrCode());
		internalMsgInfo.setTitle(scmAuditMsgTemplet.getTitle());
		internalMsgInfo.setMsgContent(scmAuditMsgTemplet.getContent());
		internalMsgInfo.setMsgType(scmAuditMsgTemplet.getTempletType());
		internalMsgInfo.setMsgDate(CalendarUtil.getDate(param));
		internalMsgInfo.setPublicNo(publicNo);
		internalMsgInfo.setUsrCodes(usrList);
		internalMsgInfo.setControlUnitNo(param.getControlUnitNo());
		return internalMsgInfoBiz.addMsgInfo(internalMsgInfo, param);
	}

	public static void sendEmailMsg(BaseModel model,String type, String attachmentBASE64, Param param) {
		AppServiceBiz appServiceBiz = (AppServiceBiz) AppContextUtil.getBean("appServiceBiz");
		ScmsupplierBiz scmsupplierBiz = (ScmsupplierBiz) AppContextUtil.getBean("scmsupplierBiz");
		InternalMsgInfoUsrBiz internalMsgInfoUsrBiz = (InternalMsgInfoUsrBiz) AppContextUtil.getBean("internalMsgInfoUsrBiz");
		ScmAuditMsgTempletBiz scmAuditMsgTempletBiz = (ScmAuditMsgTempletBiz) AppContextUtil.getBean("scmAuditMsgTempletBiz");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss") .create();
		String emailAddress = "";
		String billNo ="";
		String vendorId="";
		switch (StringUtils.lowerCase(ClassUtils.getFinalModelSimpleName(model.getClass()))) {
			case "scmpurorder" :{
				ScmPurOrder2 scmPurOrder = (ScmPurOrder2)model;
				if (scmPurOrder.getVendorId()>0) {
					Scmsupplier2 scmsupplier2 = scmsupplierBiz.select(scmPurOrder.getVendorId(), param);
					if (scmsupplier2 != null) {
						emailAddress = scmsupplier2.getEmail();
						vendorId = scmsupplier2.getVendorNo();
						billNo = scmPurOrder.getPoNo();
					}
				}
				break;
			}
		}
		ScmAuditMsgTemplet2 scmAuditMsgTemplet = scmAuditMsgTempletBiz.selectByTempetType(type, param);
		if(scmAuditMsgTemplet!=null && scmAuditMsgTemplet.getScmAuditMsgTempletChannelList()!=null && !scmAuditMsgTemplet.getScmAuditMsgTempletChannelList().isEmpty()) {
			for(ScmAuditMsgTempletChannel scmAuditMsgTempletChannel:scmAuditMsgTemplet.getScmAuditMsgTempletChannelList()) {
				if(StringUtils.equals("email",scmAuditMsgTempletChannel.getChannelCode())) {
					InternalMsgInfo2 internalMsgInfo = AuditMsgUtil.saveMsg(String.valueOf(vendorId), null, scmAuditMsgTemplet, param);
					if (StringUtils.isNotBlank(emailAddress)) {
						DoSubmitMessageParams submitParam = new DoSubmitMessageParams();
						String orgUnitNo2 = AppServiceUtil.getAppServiceParamValue(param.getOrgUnitNo(),"iMsg", "orgUnitNo", param);
						if(StringUtils.isBlank(orgUnitNo2))
							orgUnitNo2 = AppServiceUtil.getAppServiceParamValue(param.getControlUnitNo(),"iMsg", "orgUnitNo", param);
						submitParam.setOrgUnitNo(orgUnitNo2);
						submitParam.setPostOrgUnitNo(param.getOrgUnitNo());
						submitParam.setSendMode("2");
						submitParam.setAddress(emailAddress);
						submitParam.setSubject(scmAuditMsgTemplet.getTitle());
						String content = convertLableHtml(gson.toJson(scmAuditMsgTemplet.getContent()));
						submitParam.setMessage(content);
						HashMap<String,String> hashMap = new HashMap<>();
						hashMap.put(billNo+".pdf", attachmentBASE64);
						submitParam.setAttachments(hashMap);
						submitParam.setSystemId(170L);
						submitParam.setGenTime(CalendarUtil.getDate(param));
						submitParam.setBizId("170_"+ClassUtils.getFinalModelSimpleName(model)+"_"+model.getPK());
						submitParam.setTemplateId("");
						submitParam.setDetailUrl("");
						String params = gson.toJson(submitParam);
						String result = MsgClient.doSubmitMessage("doSubmitMessage", params , param);
						if (StringUtils.isNotBlank(result)) {
							DoSubmitMessageResult doSubmitMessageResult = gson.fromJson(result, DoSubmitMessageResult.class);
							if (doSubmitMessageResult.getTransId()>0) {
								//发送成功
								List<InternalMsgInfoUsr> internalMsgInfoUsrList = internalMsgInfo.getInternalMsgInfoUsrList();
								if (internalMsgInfoUsrList != null) {
									InternalMsgInfoUsr internalMsgInfoUsr = internalMsgInfoUsrList.get(0);
									internalMsgInfoUsr.setSended(true);
									internalMsgInfoUsrBiz.update(internalMsgInfoUsr, param);
								}
							}
						}
					}
				}
			}
		}
	}
	
	private static String convertLableHtml(String content) {
		if (StringUtils.isNotBlank(content)) {
			StringBuilder sb = new StringBuilder(content);
			if (StringUtils.equals(String.valueOf(sb.charAt(0)), "\"") && StringUtils.equals(String.valueOf(sb.charAt(sb.length()-1)), "\"")) {
				sb.replace(0, 1, "");
				sb.replace(sb.length()-1, sb.length(), "");
				System.out.println(sb.length());
			}
			String string = sb.toString();
			string = string.replace("\\n", "<br>");
			string = string.replace("\\t", "<pre>");
			return string;
		}
		return null;
	}
	public static void sendMsg(String templetId,String billTypeCode,BaseModel model,Object queryObj,InternalMsgInfo2 internalMsgInfo,Param param){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss") .create();
		InternalMsgInfoUsrBiz internalMsgInfoUsrBiz = (InternalMsgInfoUsrBiz) AppContextUtil.getBean("internalMsgInfoUsrBiz");
		AppServiceBiz appServiceBiz = (AppServiceBiz) AppContextUtil.getBean("appServiceBiz");
		AppService2 appService = appServiceBiz.selectByCode(param.getControlUnitNo(), "WorkGroup", param);
		String url = appService.getUrl();
		if(StringUtils.isNotBlank(url)) {
			if(!StringUtils.equals("/",StringUtils.right(url, 1)))
				url = url+"/";
			String privDomainCode = appService.getAppServiceParamValueMap().get("privDomainCode");	//私有域
			String orgUnitNo = appService.getAppServiceParamValueMap().get("orgUnitNo");	//平台组织
			if(StringUtils.isNotBlank(orgUnitNo)) {
				String bizOrgUnitNo="";
				switch (StringUtils.lowerCase(ClassUtils.getFinalModelSimpleName(model.getClass()))) {
			        case "scmpurquotation":{
			            //报价单
			        	bizOrgUnitNo = ((ScmPurQuotation2)model).getOrgUnitNo();
			            break;
			        }
			        case "scmpurprice":{
			            //定价单
			        	bizOrgUnitNo = ((ScmPurPrice2)model).getOrgUnitNo();
			            break;
			        }
			        case "scmpurrequire":{
			            //请购单
			        	bizOrgUnitNo = ((ScmPurRequire2)model).getOrgUnitNo();
			        	break;
			        }
			        case "scmpurorder":{
			            //订货单
			        	bizOrgUnitNo = ((ScmPurOrder2)model).getOrgUnitNo();
			            break;
			        }
			        case "scmpurreceive":{
			            //收货单
			        	bizOrgUnitNo = ((ScmPurReceive2)model).getOrgUnitNo();
			            break;
			        }
			        case "scmpurreturns":{
			            //退货申请
			        	bizOrgUnitNo = ((ScmPurReturns2)model).getOrgUnitNo();
			            break;
			        }
			        case "scminvmaterialrequestbill":{
			            //领料申请
			        	bizOrgUnitNo = ((ScmInvMaterialRequestBill2)model).getOrgUnitNo();
			            break;
			        }
			        case "scminvpurinwarehsbill":{
			            //采购入库
			        	bizOrgUnitNo = ((ScmInvPurInWarehsBill2)model).getOrgUnitNo();
			            break;
			        }
			        case "scminvmaterialreqbill":{
			            //领料出库
			        	bizOrgUnitNo = ((ScmInvMaterialReqBill2)model).getOrgUnitNo();
			            break;
			        }
			        case "scminvsaleorder":{
			            //销售订单
			        	bizOrgUnitNo = ((ScmInvSaleOrder2)model).getOrgUnitNo();
			            break;
			        }
			        case "scminvmovebill":{
			            //成本转移
			        	bizOrgUnitNo = ((ScmInvMoveBill2)model).getFinOrgUnitNo();
			            break;
			        }
			        case "scminvotherissuebill": {
			        	bizOrgUnitNo = ((ScmInvOtherIssueBill2)model).getOrgUnitNo();
			            break;
			        }
			        case "scmcstfrmloss": {
			        	//成本中心报损单
			        	bizOrgUnitNo = ((ScmCstFrmLoss2)model).getOrgUnitNo();
			            break;
			        }
			        case "scmsupplierqualifieinfobill":{
			        	bizOrgUnitNo = ((ScmSupplierQualifieInfoBill2)model).getControlUnitNo();
			            break;
			        }
			        case "scminvsaleissuebill":{
			        	bizOrgUnitNo = ((ScmInvSaleIssueBill2)model).getOrgUnitNo();
			            break;
			        }
			    }
				String queryParam = gson.toJson(queryObj);
				url = url+"p-"+privDomainCode+"/"+orgUnitNo+"/iwechat/"+internalMsgInfo.getPublicNo()+"/getFlatFormOauth?r=ISCM-EA-"+billTypeCode+"&orgUnitNo="+bizOrgUnitNo+"&data="
					+AESUtil.encrypt(queryParam, StringUtils.left(DigestUtils.md5Hex(bizOrgUnitNo).toLowerCase(), 16));
				List<InternalMsgInfoUsr> internalMsgInfoUsrList = internalMsgInfo.getInternalMsgInfoUsrList();
				if(internalMsgInfoUsrList!=null && !internalMsgInfoUsrList.isEmpty()) {
					for(InternalMsgInfoUsr internalMsgInfoUsr:internalMsgInfoUsrList) {
						if(StringUtils.isNotBlank(internalMsgInfoUsr.getAddress())) {
							DoSubmitMessageParams submitParam = new DoSubmitMessageParams();
							String orgUnitNo2 = AppServiceUtil.getAppServiceParamValue(param.getOrgUnitNo(),"iMsg", "orgUnitNo", param);
							if(StringUtils.isBlank(orgUnitNo2))
								orgUnitNo2 = AppServiceUtil.getAppServiceParamValue(param.getControlUnitNo(),"iMsg", "orgUnitNo", param);
							submitParam.setOrgUnitNo(orgUnitNo2);
							submitParam.setPostOrgUnitNo(param.getOrgUnitNo());
							submitParam.setSendMode("3");	//微信消息
							submitParam.setAddress(internalMsgInfoUsr.getAddress());
							List<TemplateParam> templateParamList = new ArrayList();
							TemplateParam templateParam = new TemplateParam("first", internalMsgInfo.getTitle(), "#000000");
							templateParamList.add(templateParam);
							templateParam = new TemplateParam("keyword1", Message.getMessage(param.getLang(), "field."+ClassUtils.getFinalModelSimpleName(model)), "#000000");
							templateParamList.add(templateParam);
							templateParam = new TemplateParam("keyword2", param.getUsrName(), "#000000");
							templateParamList.add(templateParam);
							templateParam = new TemplateParam("keyword3", FormatUtils.fmtDateTime(CalendarUtil.getDate(param)), "#000000");
							templateParamList.add(templateParam);
							templateParam = new TemplateParam("keyword4", internalMsgInfo.getMsgContent(), "#000000");
							templateParamList.add(templateParam);
							submitParam.setMessage(gson.toJson(templateParamList));
							submitParam.setGenTime(CalendarUtil.getDate(param));
							submitParam.setSystemId(170L);
							submitParam.setBizId("170_"+ClassUtils.getFinalModelSimpleName(model)+"_"+model.getPK());
							submitParam.setTemplateId(templetId);
							submitParam.setSubject(internalMsgInfo.getTitle());
							submitParam.setDetailUrl(url+"&usr="+internalMsgInfoUsr.getReceiver());
							String params = gson.toJson(submitParam);
							String result = MsgClient.doSubmitMessage("doSubmitMessage", params , param);
							if(StringUtils.isNotBlank(result)) {
								DoSubmitMessageResult doSubmitMessageResult = gson.fromJson(result, DoSubmitMessageResult.class);
								if(doSubmitMessageResult.getTransId()>0) {
									//发送成功
									internalMsgInfoUsr.setSended(true);
									internalMsgInfoUsrBiz.updateDirect(internalMsgInfoUsr, param);
								}
							}
						}
					}
				}
			}
		}
	}
	
	public static void sendSupplierMsg(String templetId,String billTypeCode,BaseModel model,Object queryObj,InternalMsgInfo2 internalMsgInfo,Param param){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss") .create();
		InternalMsgInfoUsrBiz internalMsgInfoUsrBiz = (InternalMsgInfoUsrBiz) AppContextUtil.getBean("internalMsgInfoUsrBiz");
		AppServiceBiz appServiceBiz = (AppServiceBiz) AppContextUtil.getBean("appServiceBiz");
		AppService2 appService = appServiceBiz.selectByCode(param.getControlUnitNo(), "WorkGroup", param);
		String url = appService.getUrl();
		if(StringUtils.isNotBlank(url)) {
			if(!StringUtils.equals("/",StringUtils.right(url, 1)))
				url = url+"/";
			String privDomainCode = appService.getAppServiceParamValueMap().get("privDomainCode");	//私有域
			String orgUnitNo = appService.getAppServiceParamValueMap().get("orgUnitNo");	//平台组织
			if(StringUtils.isNotBlank(orgUnitNo)) {
				String bizOrgUnitNo="";
				bizOrgUnitNo = ((Scmsupplier2)model).getControlUnitNo();
				String queryParam = gson.toJson(queryObj);
				url = url+"p-"+privDomainCode+"/"+orgUnitNo+"/iwechat/"+internalMsgInfo.getPublicNo()+"/getFlatFormOauth?r=ISCM-EA-"+billTypeCode+"&orgUnitNo="+bizOrgUnitNo+"&data="
					+AESUtil.encrypt(queryParam, StringUtils.left(DigestUtils.md5Hex(bizOrgUnitNo).toLowerCase(), 16));
				List<InternalMsgInfoUsr> internalMsgInfoUsrList = internalMsgInfo.getInternalMsgInfoUsrList();
				if(internalMsgInfoUsrList!=null && !internalMsgInfoUsrList.isEmpty()) {
					for(InternalMsgInfoUsr internalMsgInfoUsr:internalMsgInfoUsrList) {
						if(StringUtils.isNotBlank(internalMsgInfoUsr.getAddress())) {
							DoSubmitMessageParams submitParam = new DoSubmitMessageParams();
							String orgUnitNo2 = AppServiceUtil.getAppServiceParamValue(param.getOrgUnitNo(),"iMsg", "orgUnitNo", param);
							if(StringUtils.isBlank(orgUnitNo2))
								orgUnitNo2 = AppServiceUtil.getAppServiceParamValue(param.getControlUnitNo(),"iMsg", "orgUnitNo", param);
							submitParam.setOrgUnitNo(orgUnitNo2);
							submitParam.setPostOrgUnitNo(param.getOrgUnitNo());
							submitParam.setSendMode("3");	//微信消息
							submitParam.setAddress(internalMsgInfoUsr.getAddress());
							List<TemplateParam> templateParamList = new ArrayList();
							TemplateParam templateParam = new TemplateParam("first", internalMsgInfo.getTitle(), "#000000");
							templateParamList.add(templateParam);
							templateParam = new TemplateParam("keyword1", Message.getMessage(param.getLang(), "field."+ClassUtils.getFinalModelSimpleName(model)), "#000000");
							templateParamList.add(templateParam);
							templateParam = new TemplateParam("keyword2", ((Scmsupplier2)model).getVendorName(), "#000000");
							templateParamList.add(templateParam);
							templateParam = new TemplateParam("keyword3", FormatUtils.fmtDateTime(CalendarUtil.getDate(param)), "#000000");
							templateParamList.add(templateParam);
							templateParam = new TemplateParam("keyword4", internalMsgInfo.getMsgContent(), "#000000");
							templateParamList.add(templateParam);
							submitParam.setMessage(gson.toJson(templateParamList));
							submitParam.setGenTime(CalendarUtil.getDate(param));
							submitParam.setSystemId(170L);
							submitParam.setBizId("170_"+ClassUtils.getFinalModelSimpleName(model)+"_"+model.getPK());
							submitParam.setTemplateId(templetId);
							//submitParam.setDetailUrl(url+"&usr="+internalMsgInfoUsr.getReceiver());
							String params = gson.toJson(submitParam);
							String result = MsgClient.doSubmitMessage("doSubmitMessage", params , param);
							if(StringUtils.isNotBlank(result)) {
								DoSubmitMessageResult doSubmitMessageResult = gson.fromJson(result, DoSubmitMessageResult.class);
								if(doSubmitMessageResult.getTransId()>0) {
									//发送成功
									internalMsgInfoUsr.setSended(true);
									internalMsgInfoUsrBiz.updateDirect(internalMsgInfoUsr, param);
								}
							}
						}
					}
				}
			}
		}
	}
	
	public static String getReportAttachment(Report2 report, Param param) throws AppException {
		String encoded = null;
		try {
			String token = "dfa81b84-22c1-4879-a4b9-9a61efaf3a4b";//目前token没有起作用，暂且先写死能用
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setReportCode(report.getCode());
			reportRequest.setParamValueMap(report.getParamValueMap());
			reportRequest.setHideColumns(report.getHideColumns());
			reportRequest.setQueryConditionDisplay(report.getQueryConditionDisplay());
			reportRequest.setPageCount(report.getPageCount());
			reportRequest.setPageIndex(0);
			reportRequest.setPaging(false);
			reportRequest.setAction(ReportRequest.ActionExport);
			reportRequest.setExportFormat(ReportRequest.FormatPdf);
			IntegratedRequest integratedRequest = new IntegratedRequest();
			integratedRequest.setOrgUnitNo(param.getOrgUnitNo());//zto
			integratedRequest.setUserCode(param.getUsrCode());
			integratedRequest.setSecurityToken(token);
			integratedRequest.setLang(param.getLang());

			reportRequest.setIntegratedRequest(integratedRequest);

			Map<String, String> params = new HashMap();
			params.put("userCode", param.getUsrCode());
			params.put("token", token);
			
			String json = JSONUtils.newGson().toJson(reportRequest);
			params.put("content", AESUtil.encryptByToken(json, token));

			HttpClientParams httpClientParams = new HttpClientParams();
			httpClientParams.setConnectionManagerTimeout(30 * 60);
			httpClientParams.setSoTimeout(30 * 60 * 1000);
			httpClientParams.setContentCharset("utf-8");
			HttpClient client = new HttpClient(httpClientParams);

			if(param.getSystemIdList() == null){
	 			param.setSystemIdList(new ArrayList<Long>());
	 		}
	 		if(param.getSystemIdList().isEmpty()){
	 			param.getSystemIdList().add(170L);
	 		}
	 		String SCMURL = AppServiceUtil.getUrl(param.getOrgUnitNo(), "ISCM", param);
	 		String url = "";
	 		if (StringUtils.equals("/", SCMURL.substring(SCMURL.length()-1))) {
	 			url = StringUtils.join(new String[] {SCMURL,"report"});
			}else {
				url = StringUtils.join(new String[] {SCMURL,"/report"});
			}
			PostMethod post = new PostMethod(url);
			for (Map.Entry<String, String> entry : params.entrySet()) {
				post.setParameter(entry.getKey(), entry.getValue());
			}
			int statusCode = client.executeMethod(post);

			if (statusCode != HttpStatus.SC_OK) {
				if (statusCode == HttpStatus.SC_SERVICE_UNAVAILABLE) {
					throw new AppException("system.service.impl.ReportBizImpl.error.exportExpire");
				} else {
					throw new HttpException(post.getStatusText());
				}
			}
			try (InputStream inputStream = post.getResponseBodyAsStream()) {
				byte[] bytes = IOUtils.toByteArray(inputStream);
				encoded = java.util.Base64.getEncoder().encodeToString(bytes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encoded;
	}
	
	public static List<ScmMsginfo> queryMsgList(ScmMsginfoAdvQuery scmMsginfoAdvQuery,Param param) {
		if (scmMsginfoAdvQuery != null) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss") .create();
			String orgUnitNo2 = AppServiceUtil.getAppServiceParamValue(scmMsginfoAdvQuery.getPostOrgUnitNo(),"iMsg", "orgUnitNo", param);
			if(StringUtils.isBlank(orgUnitNo2))
				orgUnitNo2 = AppServiceUtil.getAppServiceParamValue(param.getControlUnitNo(),"iMsg", "orgUnitNo", param);
			scmMsginfoAdvQuery.setOrgUnitNo(orgUnitNo2);
			scmMsginfoAdvQuery.setSystemId(170L);
			String params = gson.toJson(scmMsginfoAdvQuery);
			String result = MsgClient.doSubmitMessage("queryMsgList", params , param);
			if (StringUtils.isNotBlank(result)) {
				JSONObject jsonObject = JSON.parseObject(result);
				if(jsonObject != null && "0".equals(String.valueOf(jsonObject.get("errCode")))){
					JSONArray resultarray = jsonObject.getJSONArray("msgList");
					if(resultarray != null && !resultarray.isEmpty()){
						List<ScmMsginfo> queryMsgList = new ArrayList<>();
						for (int i = 0; i < resultarray.size(); i++) {
							JSONObject billObject = resultarray.getJSONObject(i);
							ScmMsginfo scmMsginfo = JSONObject.toJavaObject(billObject, ScmMsginfo.class);
							queryMsgList.add(scmMsginfo);
						}
						return queryMsgList;
					}
				}
			}
		}
		return null;
	}
	
//	public static void sendResultMsg(String templetId,String billTypeCode,BaseModel model,Object queryObj,InternalMsgInfo2 internalMsgInfo,Param param){
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss") .create();
//		InternalMsgInfoUsrBiz internalMsgInfoUsrBiz = (InternalMsgInfoUsrBiz) AppContextUtil.getBean("internalMsgInfoUsrBiz");
//
//				if(internalMsgInfoUsrList!=null && !internalMsgInfoUsrList.isEmpty()) {
//					for(InternalMsgInfoUsr internalMsgInfoUsr:internalMsgInfoUsrList) {
//						if(StringUtils.isNotBlank(internalMsgInfoUsr.getAddress())) {
//							DoSubmitMessageParams submitParam = new DoSubmitMessageParams();
//							submitParam.setOrgUnitNo(AppServiceUtil.getAppServiceParamValue(param.getOrgUnitNo(),"iMsg", "orgUnitNo", param));
//							submitParam.setPostOrgUnitNo(param.getOrgUnitNo());
//							submitParam.setSendMode("3");	//微信消息
//							submitParam.setAddress(internalMsgInfoUsr.getAddress());
//							List<TemplateParam> templateParamList = new ArrayList();
//							TemplateParam templateParam = new TemplateParam("first", internalMsgInfo.getTitle(), "#000000");
//							templateParamList.add(templateParam);
//							templateParam = new TemplateParam("keyword1", Message.getMessage(param.getLang(), "field."+ClassUtils.getFinalModelSimpleName(model)), "#000000");
//							templateParamList.add(templateParam);
//							templateParam = new TemplateParam("keyword2", param.getUsrName(), "#000000");
//							templateParamList.add(templateParam);
//							templateParam = new TemplateParam("keyword3", FormatUtils.fmtDateTime(CalendarUtil.getDate(param)), "#000000");
//							templateParamList.add(templateParam);
//							templateParam = new TemplateParam("keyword4", internalMsgInfo.getMsgContent(), "#000000");
//							templateParamList.add(templateParam);
//							submitParam.setMessage(gson.toJson(templateParamList));
//							submitParam.setGenTime(CalendarUtil.getDate(param));
//							submitParam.setSystemId(170L);
//							submitParam.setBizId("170_"+ClassUtils.getFinalModelSimpleName(model)+"_"+model.getPK());
//							submitParam.setTemplateId(templetId);
//							submitParam.setDetailUrl(url);
//							String params = gson.toJson(submitParam);
//							String result = MsgClient.doSubmitMessage("doSubmitMessage", params , param);
//							if(StringUtils.isNotBlank(result)) {
//								DoSubmitMessageResult doSubmitMessageResult = gson.fromJson(result, DoSubmitMessageResult.class);
//								if(doSubmitMessageResult.getTransId()>0) {
//									//发送成功
//									internalMsgInfoUsr.setSended(true);
//									internalMsgInfoUsrBiz.updateDirect(internalMsgInfoUsr, param);
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//	}
}
