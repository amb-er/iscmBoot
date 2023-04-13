package com.armitage.server.quartz.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.InterfaceParam;
import com.armitage.server.common.base.model.OperationParam;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.HttpClientUtils;
import com.armitage.server.common.util.JSONUtils;
import com.armitage.server.icrs.resource.setting.util.FileTransferUtil;
import com.armitage.server.iscm.basedata.model.ScmIndustryGroup;
import com.armitage.server.iscm.basedata.model.ScmIndustryGroupQualifyType2;
import com.armitage.server.iscm.basedata.model.ScmQualifieInfo2;
import com.armitage.server.iscm.basedata.model.ScmSupplierConfirmData2;
import com.armitage.server.iscm.basedata.model.ScmSupplierDemander;
import com.armitage.server.iscm.basedata.model.ScmSupplierEvent2;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifyType;
import com.armitage.server.iscm.basedata.model.ScmSupplierRegInvitation;
import com.armitage.server.iscm.basedata.model.ScmSupplierReplyData;
import com.armitage.server.iscm.basedata.model.ScmSupplierReplyData2;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.model.Scmsuppliercompanyinfo2;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroup2;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroupdetail;
import com.armitage.server.iscm.basedata.model.Scmsupplierlinkman;
import com.armitage.server.iscm.basedata.model.Scmsupplierpurchaseinfo2;
import com.armitage.server.iscm.basedata.service.ScmIndustryGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmQualifieInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierConfirmDataBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierDemanderBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierEventBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierQualifieInfoBillBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierQualifyTypeBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierRegInvitationBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierReplyDataBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.basedata.service.ScmsuppliergroupBiz;
import com.armitage.server.iscm.basedata.service.ScmsuppliergroupdetailBiz;
import com.armitage.server.iscm.common.model.ScmConfirmRule;
import com.armitage.server.iscm.common.model.ScmSyncData;
import com.armitage.server.iscm.common.model.ScmSyncTaskInfo2;
import com.armitage.server.iscm.common.model.TaskSettingDetail2;
import com.armitage.server.iscm.common.service.ScmConfirmRuleBiz;
import com.armitage.server.iscm.common.service.ScmSyncDataBiz;
import com.armitage.server.iscm.common.service.ScmSyncTaskInfoBiz;
import com.armitage.server.iscm.common.service.TaskSettingDetailBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPriceBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPriceEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmBaseAttachmentBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderBiz;
import com.armitage.server.mongodb.biz.MongoDBImageBiz;
import com.armitage.server.quartz.job.ScmBusinessQuotationGetJob;
import com.armitage.server.quartz.job.ScmControlUnitPushJob;
import com.armitage.server.quartz.job.ScmEspCommonTaskJob;
import com.armitage.server.quartz.job.ScmIndustryQualifyPushJob;
import com.armitage.server.quartz.job.ScmInvPurInWarehsBillPushJob;
import com.armitage.server.quartz.job.ScmPlatSupplierIdGetJob;
import com.armitage.server.quartz.job.ScmPurOrderPushJob;
import com.armitage.server.quartz.job.ScmPurPricePushJob;
import com.armitage.server.quartz.job.ScmQualificationInfoPullJob;
import com.armitage.server.quartz.job.ScmQualificationInfoPushJob;
import com.armitage.server.quartz.job.ScmSupplierConfirmDataGetJob;
import com.armitage.server.quartz.job.ScmSupplierConfirmRulePushJob;
import com.armitage.server.quartz.job.ScmSupplierStatusPushJob;
import com.armitage.server.quartz.model.AppInfo;
import com.armitage.server.quartz.model.ScmSystemTask;
import com.armitage.server.quartz.model.ScmSystemTaskExecTime;
import com.armitage.server.quartz.model.SupplierPlatIntegratedRequest;
import com.armitage.server.quartz.model.controlunit.SupplierPlatPurchase;
import com.armitage.server.quartz.model.controlunit.SupplierPlatPurchaseEntry;
import com.armitage.server.quartz.model.controlunit.SupplierPlatStorage;
import com.armitage.server.quartz.model.controlunit.SupplierPlatStorageEntry;
import com.armitage.server.quartz.model.invpurinwarehs.SupplierPlatInvPurInWarehsBill;
import com.armitage.server.quartz.model.invpurinwarehs.SupplierPlatInvPurInWarehsBillEntry;
import com.armitage.server.quartz.model.purorder.SupplierPlatPurOrder;
import com.armitage.server.quartz.model.purorder.SupplierPlatPurOrderEntry;
import com.armitage.server.quartz.model.purprice.SupplierPlatPurPrice;
import com.armitage.server.quartz.model.purprice.SupplierPlatPurPriceEntry;
import com.armitage.server.quartz.model.scmconfirmrule.SupplierPlatConfirmRule;
import com.armitage.server.quartz.model.scmsupplier.SupplierPlatIndustryGroup;
import com.armitage.server.quartz.model.scmsupplier.SupplierPlatIndustryGroupType;
import com.armitage.server.quartz.model.scmsupplier.SupplierPlatQualifieInfo;
import com.armitage.server.quartz.model.scmsupplier.SupplierPlatQualifieInfoType;
import com.armitage.server.quartz.model.scmsupplier.SupplierPlatinviteEvent;
import com.armitage.server.quartz.service.ScmSystemTaskBiz;
import com.armitage.server.quartz.service.ScmSystemTaskExecTimeBiz;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.util.AppServiceUtil;
import com.google.gson.Gson;

import sun.misc.BASE64Encoder;

/**
 * 调供应商平台接口工具
 */
public class SupplierPlatUtil {

	Log log = LogFactory.getLog(SupplierPlatUtil.class);
	private static final String ISCMDBNAME = "iscm";

	private Gson gson = JSONUtils.newGson();
	private ScmSupplierConfirmDataBiz scmSupplierConfirmDataBiz = (ScmSupplierConfirmDataBiz) AppContextUtil.getBean("scmSupplierConfirmDataBiz");
	private ScmSystemTaskBiz scmSystemTaskBiz = (ScmSystemTaskBiz) AppContextUtil.getBean("scmSystemTaskBiz");
	private ScmSupplierRegInvitationBiz scmSupplierRegInvitationBiz = (ScmSupplierRegInvitationBiz) AppContextUtil.getBean("scmSupplierRegInvitationBiz");
	private ScmSupplierDemanderBiz scmSupplierDemanderBiz = (ScmSupplierDemanderBiz) AppContextUtil.getBean("scmSupplierDemanderBiz");
	private ScmsupplierBiz scmsupplierBiz = (ScmsupplierBiz) AppContextUtil.getBean("scmsupplierBiz");
	private ScmPurOrderBiz scmPurOrderBiz = (ScmPurOrderBiz) AppContextUtil.getBean("scmPurOrderBiz");
	private ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz = (ScmInvPurInWarehsBillBiz) AppContextUtil.getBean("scmInvPurInWarehsBillBiz");
	private TaskSettingDetailBiz taskSettingDetailBiz = (TaskSettingDetailBiz) AppContextUtil.getBean("taskSettingDetailBiz");
	private ScmSyncDataBiz scmSyncDataBiz = (ScmSyncDataBiz) AppContextUtil.getBean("scmSyncDataBiz");
	private ScmSyncTaskInfoBiz scmSyncTaskInfoBiz = (ScmSyncTaskInfoBiz) AppContextUtil.getBean("scmSyncTaskInfoBiz");
	private ScmConfirmRuleBiz scmConfirmRuleBiz = (ScmConfirmRuleBiz) AppContextUtil.getBean("scmConfirmRuleBiz");
	private ScmSupplierReplyDataBiz scmSupplierReplyDataBiz = (ScmSupplierReplyDataBiz) AppContextUtil.getBean("scmSupplierReplyDataBiz");
	private ScmSystemTaskExecTimeBiz scmSystemTaskExecTimeBiz = (ScmSystemTaskExecTimeBiz) AppContextUtil.getBean("scmSystemTaskExecTimeBiz");
	private ScmPurPriceBiz scmPurPriceBiz = (ScmPurPriceBiz) AppContextUtil.getBean("scmPurPriceBiz");
	private ScmPurPriceEntryBiz scmPurPriceEntryBiz = (ScmPurPriceEntryBiz) AppContextUtil.getBean("scmPurPriceEntryBiz");
	private SysParamBiz sysParamBiz = (SysParamBiz) AppContextUtil.getBean("sysParamBiz");
	private MongoDBImageBiz mongoDBImageBiz = (MongoDBImageBiz) AppContextUtil.getBean("mongoDBImageBiz");
	private ScmBaseAttachmentBiz scmBaseAttachmentBiz = (ScmBaseAttachmentBiz) AppContextUtil.getBean("scmBaseAttachmentBiz");
	private ScmIndustryGroupBiz scmIndustryGroupBiz = (ScmIndustryGroupBiz) AppContextUtil.getBean("scmIndustryGroupBiz");
	private ScmSupplierEventBiz scmSupplierEventBiz = (ScmSupplierEventBiz) AppContextUtil.getBean("scmSupplierEventBiz");
	private ScmsuppliergroupBiz scmsuppliergroupBiz = (ScmsuppliergroupBiz) AppContextUtil.getBean("scmsuppliergroupBiz");
	private ScmsuppliergroupdetailBiz scmsuppliergroupdetailBiz = (ScmsuppliergroupdetailBiz) AppContextUtil.getBean("scmsuppliergroupdetailBiz");
	private ScmQualifieInfoBiz scmQualifieInfoBiz = (ScmQualifieInfoBiz) AppContextUtil.getBean("scmQualifieInfoBiz");
	private ScmSupplierQualifyTypeBiz scmSupplierQualifyTypeBiz = (ScmSupplierQualifyTypeBiz) AppContextUtil.getBean("scmSupplierQualifyTypeBiz");
	private OrgPurchaseBiz orgPurchaseBiz = (OrgPurchaseBiz) AppContextUtil.getBean("orgPurchaseBiz");
	private OrgStorageBiz orgStorageBiz = (OrgStorageBiz) AppContextUtil.getBean("orgStorageBiz");
	private ScmSupplierQualifieInfoBillBiz scmSupplierQualifieInfoBillBiz = (ScmSupplierQualifieInfoBillBiz) AppContextUtil.getBean("scmSupplierQualifieInfoBillBiz");
	
	public AppInfo getAPP(AppInfo appInfo){
		OperationParam param = new OperationParam();
		param.setControlUnitNo(appInfo.getControlUnitNo());
		List<Long> systemIdList = new ArrayList();
		systemIdList.add(170L);
		param.setSystemIdList(systemIdList);
		// 请求地址
		String url = AppServiceUtil.getUrl(param.getControlUnitNo(), appInfo.getAppName(), param);
		if (StringUtils.isBlank(url)) {
			log.info(appInfo.getAppName()+"应用服务未配置地址！");
			return null;
		}
		// 用户
		String usrCode = AppServiceUtil.getAppServiceParamValue(param.getControlUnitNo(),  appInfo.getAppName(), "usrCode", param);
		if (StringUtils.isBlank(usrCode)) {
			log.info(appInfo.getAppName()+"应用服务未配置用户！");
			return null;
		}
 		// 密码
		String pass = AppServiceUtil.getAppServiceParamValue(param.getControlUnitNo(),  appInfo.getAppName(), "pass", param);
		if (StringUtils.isBlank(pass)) {
			log.info(appInfo.getAppName()+"应用服务未配置用户密码！");
			return null;
		}
		if(StringUtils.equals(StringUtils.right(url, 1),"/"))
			url = StringUtils.left(url, StringUtils.length(url)-1);
		appInfo.setUrl(url);
		appInfo.setUsrCode(usrCode);
		appInfo.setPass(pass);
		return appInfo;
	}
	
	/**
	 * 获取Token
	 */
	public String getToken(String url, String usrCode, String pass) {
		String postResult = "";
		url = url + "/isp/woUser/applySecurityToken";
		Map<String, String> map = new HashMap<>();
		map.put("userCode", usrCode);
		map.put("key", pass);
		// 调用应用服务
		String json = gson.toJson(map);
		log.info("!!!开始调用接口，地址：" + url + "，参数：" + json);
		try {
			HttpClient client = HttpClientUtils.createClient("utf-8", 100);
			postResult = HttpClientUtils.post(client, url, map);
			log.info("!!!调用接口，地址：" + url + "，返回结果：" + postResult);
			return postResult;
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
	}

	/**
	 * 获取数据
	 */
	public String getData(AppInfo appInfo, long supplierId, String path,String params) {
		String postResult = "";
		// 获取 token
		JSONObject jsonObj = JSONObject
				.parseObject(getToken(appInfo.getUrl(), appInfo.getUsrCode(), appInfo.getPass()));
		String token = jsonObj.getString("securityToken");
		// 授权
		SupplierPlatIntegratedRequest integratedRequest = new SupplierPlatIntegratedRequest();
		integratedRequest.setHostName("iSCM-PC");
		integratedRequest.setLang("zh-CN");
		integratedRequest.setProductCode("iSCM");
		integratedRequest.setOptCode(appInfo.getUsrCode());
		integratedRequest.setSupplierId(supplierId);
		integratedRequest.setSecurityToken(token);
		integratedRequest.setUserCode(appInfo.getUsrCode());
		String integrated = JSONObject.toJSONString(integratedRequest);
		// params =
		// "{\"integratedRequest\":{\"hostName\":\"Stephen-PC\",\"lang\":\"zh-CN\",\"optCode\":\"001\",\"orgUnitNo\":\"00000075\",\"securityToken\":\"8cedf155-4a04-45e7-a86e-d2dcb4e50c14\",\"userCode\":\"bei01\"},\"logicSymbol\":{\"map\":{}},\"pageNum\":{\"pageNum\":\"-1\"},\"params\":{\"accDate\":\"2020-03-01
		// 00:00:00\",\"orgUnitNo\":\"00000075\"}}";
		//是否分页
		String reqJson = "{\"integratedRequest\":" + integrated
				+ ",\"params\": " + params + "}";
		// 调用应用服务
		try {
			log.info("请求地址："+appInfo.getUrl()+path);
			//log.info("请求参数："+reqJson);
			postResult = post(appInfo.getUrl()+path, reqJson);
			return postResult;
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
	}
	
	/**
	 * 获取数据（分页）
	 */
	public String getDataPage(AppInfo appInfo,String path,String params, int pageIndex, int pageSize) {
		String postResult = "";
		// 获取 token
		JSONObject jsonObj = JSONObject
				.parseObject(getToken(appInfo.getUrl(), appInfo.getUsrCode(), appInfo.getPass()));
		String token = jsonObj.getString("securityToken");
		// 授权
		SupplierPlatIntegratedRequest integratedRequest = new SupplierPlatIntegratedRequest();
		integratedRequest.setHostName("iSCM-PC");
		integratedRequest.setLang("zh-CN");
		integratedRequest.setProductCode("iSCM");
		integratedRequest.setOptCode(appInfo.getUsrCode());
		integratedRequest.setSupplierId(0);
		integratedRequest.setSecurityToken(token);
		integratedRequest.setUserCode(appInfo.getUsrCode());
		String integrated = JSONObject.toJSONString(integratedRequest);
		// params =
		// "{\"integratedRequest\":{\"hostName\":\"Stephen-PC\",\"lang\":\"zh-CN\",\"optCode\":\"001\",\"orgUnitNo\":\"00000075\",\"securityToken\":\"8cedf155-4a04-45e7-a86e-d2dcb4e50c14\",\"userCode\":\"bei01\"},\"logicSymbol\":{\"map\":{}},\"pageNum\":{\"pageNum\":\"-1\"},\"params\":{\"accDate\":\"2020-03-01
		// 00:00:00\",\"orgUnitNo\":\"00000075\"}}";
		//是否分页
		String reqJson = "{\"integratedRequest\":" + integrated
				+ ",\"pageCount\":\""+pageSize+"\",\"pageNum\":\""+pageIndex+"\",\"params\": " + params + "}";
		// 调用应用服务
		try {
			log.info("请求地址："+appInfo.getUrl()+path);
			log.info("请求参数："+reqJson);
			postResult = post(appInfo.getUrl()+path, reqJson);
			return postResult;
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
	}

	/**
	 * 发送HttpPost请求
	 * 
	 * @param strURL
	 *            服务地址
	 * @param params
	 *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/>
	 * @return 成功:返回json字符串<br/>
	 */
	public String post(String strURL, String params) {
		BufferedReader reader = null;
		try {
			URL url = new URL(strURL);// 创建连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST"); // 设置请求方式
			connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
			connection.connect();
			// 一定要用BufferedReader 来接收响应， 使用字节来接收响应的方法是接收不到内容的
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
			out.append(params);
			out.flush();
			out.close();
			// 读取响应
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			String res = "";
			while ((line = reader.readLine()) != null) {
				res += line;
			}
			reader.close();

			return res;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error"; // 自定义错误信息
	}
	
	public void batchUpdatePlatSupplierId(AppInfo appInfo) {
		InterfaceParam param = new InterfaceParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		List<Long> systemIdList = new ArrayList<Long>();
		systemIdList.add(170L);
		param.setSystemIdList(systemIdList);
		param.setUsrCode(appInfo.getUsrCode());
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("supplierRegInfoGet");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(appInfo.getControlUnitNo(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && systemTask.getSize() > 0){
			String hostName;
			try {
				hostName = InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException e1) {
			}
			Page page=new Page();
			page.setModelClass(ScmSyncTaskInfo2.class);
			page.setShowCount(systemTask.getSize());
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKCODE, QueryParam.QUERY_EQ,"SCM_ESP"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKTYPE, QueryParam.QUERY_EQ,systemTask.getTaskType()));
			page.getParam().put(ScmSyncTaskInfo2.FN_PRODUCTCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_PRODUCTCODE, QueryParam.QUERY_EQ,"iSCM"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKSTATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKSTATUS, QueryParam.QUERY_EQ,"W"));
			page.getParam().put(ScmSyncTaskInfo2.FN_CONTROLUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmSyncTaskInfo2> scmSyncTaskInfoList = scmSyncTaskInfoBiz.findPage(page, param);
			if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
				int count = 0;
				for(int i=0;i<scmSyncTaskInfoList.size();i++){
					ScmSyncTaskInfo2 scmSyncTaskInfo = scmSyncTaskInfoList.get(i);
					if(count<systemTask.getSize()){
						count++;
						try {
							scmSyncTaskInfo.setTaskStatus("D");
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							String errorMsg = updatePlatSupplierId(appInfo,systemTask,param);
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							if(StringUtils.isBlank(errorMsg)){
								scmSyncTaskInfo.setTaskStatus("S");
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							}else{
								scmSyncTaskInfo.setTaskStatus("F");
								scmSyncTaskInfo.setStatusMessage(errorMsg);
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							}
						} catch (Exception e) {
							log.error(e);
							String errorMsg = e.getMessage();
							if(StringUtils.isNotBlank(errorMsg) && errorMsg.length()>255){
								errorMsg = StringUtils.substring(errorMsg, 0, 255-1);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncTaskInfo.setTaskStatus("F");
							scmSyncTaskInfo.setStatusMessage(errorMsg);
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfo.setEndDate(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							continue;
						}
					}
				}
			}else{
				log.info("不存在拉取供应商平台对照信息任务！");
			}
		}else{
			log.info("当前管理单元未绑定需求方！");
		}
	}
	
	public String updatePlatSupplierId(AppInfo appInfo,ScmSystemTask systemTask,Param param) {
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(param.getControlUnitNo(), param);
		ScmSystemTaskExecTime scmSystemTaskExecTime = scmSystemTaskExecTimeBiz.selectByTaskType(param.getControlUnitNo(), systemTask.getTaskType(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && StringUtils.isNotBlank(scmSupplierDemander.getUniqueNo())){
			String updateTime = (scmSystemTaskExecTime==null || StringUtils.isBlank(scmSystemTaskExecTime.getExecTime()))? FormatUtils.fmtDateTimeMS(CalendarUtil.relativeDate(new Date(), -1)) : scmSystemTaskExecTime.getExecTime();
			Date updateTimeStamp = FormatUtils.parseDateTimeMs(updateTime);
			String params = "{\"systemType\": \"iSCM\",\"unionNo\": \""+scmSupplierDemander.getUniqueNo()+"\",\"updateTimeStamp\": \""+updateTime+"\"}";
			String data = getDataPage(appInfo, "/isp/businessaccount/queryList", params, -1, (systemTask.getSize() > 0 ? systemTask.getSize() : 20));
			log.info("接口返回："+data);
			JSONObject jsonObject = JSON.parseObject(data);
			if(jsonObject != null && "0".equals(String.valueOf(jsonObject.get("errCode")))){
				String msg = "";
				JSONArray resultarray = jsonObject.getJSONArray("data");
				if(resultarray != null && !resultarray.isEmpty()){
					for (int i = 0; i < resultarray.size(); i++) {
						try {
							Date tempUpdateTimeStamp = FormatUtils.parseDateTimeMs(String.valueOf(resultarray.getJSONObject(i).get("updateTimeStamp")));
							updateTimeStamp = updateTimeStamp.compareTo(tempUpdateTimeStamp) < 0 ? tempUpdateTimeStamp : updateTimeStamp;
							String refVendorNo = "";
							if(resultarray.getJSONObject(i).get("refVendorNo") != null){
								refVendorNo = String.valueOf(resultarray.getJSONObject(i).get("refVendorNo"));
							}
							long platSupplierId = Long.parseLong(String.valueOf(resultarray.getJSONObject(i).get("id")));
							String flag = String.valueOf(resultarray.getJSONObject(i).get("flag"));
							String status = String.valueOf(resultarray.getJSONObject(i).get("status"));
							String adminCode = String.valueOf(resultarray.getJSONObject(i).get("adminCode"));
							String adminName = String.valueOf(resultarray.getJSONObject(i).get("adminName"));
							if(StringUtils.isNotBlank(refVendorNo) && "true".equalsIgnoreCase(flag) && "O".equalsIgnoreCase(status)){
								Scmsupplier2 scmsupplier = scmsupplierBiz.selectByCode(refVendorNo, param);
								if(scmsupplier != null){
									ScmSupplierRegInvitation scmSupplierRegInvitation2 = scmSupplierRegInvitationBiz.selectByVendorIdAndCtrl(scmsupplier.getId(), param.getControlUnitNo(), param);
									if(scmSupplierRegInvitation2.getPlatSupplierId() <= 0){
										scmSupplierRegInvitation2.setPlatSupplierId(platSupplierId);
									}
									scmSupplierRegInvitation2.setUesed(true);
									if(StringUtils.isNotBlank(adminCode)) {
										scmSupplierRegInvitation2.setAddAdmin(true);
										scmSupplierRegInvitation2.setAdminCode(adminCode);
										scmSupplierRegInvitation2.setAdminName(adminName);
									}
									scmSupplierRegInvitationBiz.update(scmSupplierRegInvitation2, param);
									//修改
									List<Scmsupplier2> scmsupplierTempList = new ArrayList<Scmsupplier2>();
									Scmsupplier2 tempScmsupplier = new Scmsupplier2(true);
									tempScmsupplier.setId(scmsupplier.getId());
									scmsupplierTempList.add(tempScmsupplier);
									CommonBean scmsupplierCommonBean = new CommonBean();
									scmsupplierCommonBean.setList(scmsupplierTempList);
									scmsupplierCommonBean = scmsupplierBiz.select(scmsupplierCommonBean, param);
									if (scmsupplierCommonBean != null) {
										String relationStatus = "";
										String qualificationStatus = "";
										List<Scmsupplier2> scmsupplierList = (List<Scmsupplier2>)scmsupplierCommonBean.getList();
										if(scmsupplierList != null && !scmsupplierList.isEmpty()){
											scmsupplierList.get(0).setVendorName(String.valueOf(resultarray.getJSONObject(i).get("businessName")));
											ScmIndustryGroup scmIndustryGroup = scmIndustryGroupBiz.selectByClassCode(String.valueOf(resultarray.getJSONObject(i).get("groupCode")), param);
											if(scmIndustryGroup == null){
												continue;
											}
											if(resultarray.getJSONObject(i).get("relationStatus") != null){
												relationStatus = String.valueOf(resultarray.getJSONObject(i).get("relationStatus"));
												if(!StringUtils.equals("O", relationStatus)) {
													if(StringUtils.equals(relationStatus, "W")) {
														scmsupplierList.get(0).setStatus("D");
													}else {
														scmsupplierList.get(0).setStatus(relationStatus);
													}
												}
											}
											if(resultarray.getJSONObject(i).get("authStatus") != null){
												scmsupplierList.get(0).setQualificationStatus(String.valueOf(resultarray.getJSONObject(i).get("authStatus")));
												qualificationStatus = String.valueOf(resultarray.getJSONObject(i).get("authStatus"));
											}
											scmsupplierList.get(0).setIndustryGroupId(scmIndustryGroup.getId());
											scmsupplierList.get(0).setTaxNo(String.valueOf(resultarray.getJSONObject(i).get("taxNo")));
											scmsupplierCommonBean.setList(scmsupplierList);
										}
										List<Scmsupplierpurchaseinfo2> scmsupplierpurchaseinfoList = (List<Scmsupplierpurchaseinfo2>)scmsupplierCommonBean.getList2();
										if(scmsupplierpurchaseinfoList != null && !scmsupplierpurchaseinfoList.isEmpty()){
											scmsupplierpurchaseinfoList.get(0).setTaxpayerType(String.valueOf(resultarray.getJSONObject(i).get("taxTypeCode")));
											BigDecimal taxRate = resultarray.getJSONObject(i).get("taxRate") != null ? (BigDecimal)(resultarray.getJSONObject(i).get("taxRate")) : scmsupplierpurchaseinfoList.get(0).getVatRate();
											scmsupplierpurchaseinfoList.get(0).setVatRate(taxRate);
											List<Scmsupplierlinkman> scmsupplierlinkmanList = scmsupplierpurchaseinfoList.get(0).getScmsupplierlinkmanList();
											if(scmsupplierlinkmanList != null && !scmsupplierlinkmanList.isEmpty()){
												scmsupplierlinkmanList.get(0).setContactPerson(String.valueOf(resultarray.getJSONObject(i).get("contactPerson")));
												scmsupplierlinkmanList.get(0).setTel(String.valueOf(resultarray.getJSONObject(i).get("tel")));
												scmsupplierpurchaseinfoList.get(0).setScmsupplierlinkmanList(scmsupplierlinkmanList);
											}
											scmsupplierCommonBean.setList2(scmsupplierpurchaseinfoList);
										}
										boolean sendMsg = false;
										Scmsupplier2 sendScmsupplier = scmsupplierBiz.selectDirect(scmsupplier.getId(), param);
										if(sendScmsupplier != null && (!StringUtils.equals(sendScmsupplier.getStatus(), relationStatus) ||
												!StringUtils.equals(sendScmsupplier.getQualificationStatus(), qualificationStatus))
												&& "W".equals(relationStatus) && StringUtils.contains("W,RS", qualificationStatus)){
											sendMsg = true;
										}
										scmsupplierBiz.update(scmsupplierCommonBean, param);
										if(sendMsg){
											this.sendSupplierQualityAuditMsg(sendScmsupplier, param);
										}
									}
								}
							}else{
								Scmsupplier2 myScmsupplier = scmsupplierBiz.selectByCode(refVendorNo, param);
								if(myScmsupplier==null) {
									ScmSupplierRegInvitation scmSupplierRegInvitation = scmSupplierRegInvitationBiz.selectByPlatVendorIdAndCtrl(platSupplierId,param.getControlUnitNo(),param);
									if(scmSupplierRegInvitation!=null)
										myScmsupplier = scmsupplierBiz.selectDirect(scmSupplierRegInvitation.getVendorId(), param);
								}
								if(myScmsupplier != null){
									//修改
									List<Scmsupplier2> scmsupplierTempList = new ArrayList<Scmsupplier2>();
									Scmsupplier2 tempScmsupplier = new Scmsupplier2(true);
									tempScmsupplier.setId(myScmsupplier.getId());
									scmsupplierTempList.add(tempScmsupplier);
									CommonBean scmsupplierCommonBean = new CommonBean();
									scmsupplierCommonBean.setList(scmsupplierTempList);
									scmsupplierCommonBean = scmsupplierBiz.select(scmsupplierCommonBean, param);
									if (scmsupplierCommonBean != null) {
										String relationStatus = "";
										String qualificationStatus = "";
										List<Scmsupplier2> scmsupplierList = (List<Scmsupplier2>)scmsupplierCommonBean.getList();
										if(scmsupplierList != null && !scmsupplierList.isEmpty()){
											scmsupplierList.get(0).setVendorName(String.valueOf(resultarray.getJSONObject(i).get("businessName")));
											ScmIndustryGroup scmIndustryGroup = scmIndustryGroupBiz.selectByClassCode(String.valueOf(resultarray.getJSONObject(i).get("groupCode")), param);
											if(scmIndustryGroup == null){
												continue;
											}
											if(resultarray.getJSONObject(i).get("relationStatus") != null){
												scmsupplierList.get(0).setStatus(String.valueOf(resultarray.getJSONObject(i).get("relationStatus")));
												relationStatus = String.valueOf(resultarray.getJSONObject(i).get("relationStatus"));
											}
											if(resultarray.getJSONObject(i).get("authStatus") != null){
												scmsupplierList.get(0).setQualificationStatus(String.valueOf(resultarray.getJSONObject(i).get("authStatus")));
												qualificationStatus = String.valueOf(resultarray.getJSONObject(i).get("authStatus"));
											}
											scmsupplierList.get(0).setIndustryGroupId(scmIndustryGroup.getId());
											scmsupplierList.get(0).setTaxNo(String.valueOf(resultarray.getJSONObject(i).get("taxNo")));
											scmsupplierCommonBean.setList(scmsupplierList);
										}
										List<Scmsupplierpurchaseinfo2> scmsupplierpurchaseinfoList = (List<Scmsupplierpurchaseinfo2>)scmsupplierCommonBean.getList2();
										if(scmsupplierpurchaseinfoList != null && !scmsupplierpurchaseinfoList.isEmpty()){
											scmsupplierpurchaseinfoList.get(0).setTaxpayerType(String.valueOf(resultarray.getJSONObject(i).get("taxTypeCode")));
											BigDecimal taxRate = resultarray.getJSONObject(i).get("taxRate") != null ? (BigDecimal)(resultarray.getJSONObject(i).get("taxRate")) : scmsupplierpurchaseinfoList.get(0).getVatRate();
											scmsupplierpurchaseinfoList.get(0).setVatRate(taxRate);
											List<Scmsupplierlinkman> scmsupplierlinkmanList = scmsupplierpurchaseinfoList.get(0).getScmsupplierlinkmanList();
											if(scmsupplierlinkmanList != null && !scmsupplierlinkmanList.isEmpty()){
												scmsupplierlinkmanList.get(0).setContactPerson(String.valueOf(resultarray.getJSONObject(i).get("contactPerson")));
												scmsupplierlinkmanList.get(0).setTel(String.valueOf(resultarray.getJSONObject(i).get("tel")));
												scmsupplierpurchaseinfoList.get(0).setScmsupplierlinkmanList(scmsupplierlinkmanList);
											}
											scmsupplierCommonBean.setList2(scmsupplierpurchaseinfoList);
										}
										boolean sendMsg = false;
										Scmsupplier2 sendScmsupplier = scmsupplierBiz.selectDirect(myScmsupplier.getId(), param);
										if(sendScmsupplier != null && (!StringUtils.equals(sendScmsupplier.getStatus(), relationStatus) ||
												!StringUtils.equals(sendScmsupplier.getQualificationStatus(), qualificationStatus))
												&& "W".equals(relationStatus) && StringUtils.contains("W,RS", qualificationStatus)){
											sendMsg = true;
										}
										scmsupplierBiz.update(scmsupplierCommonBean, param);
										if(sendMsg){
											this.sendSupplierQualityAuditMsg(sendScmsupplier, param);
										}
									}
								}else{
									//新增
									ScmSupplierRegInvitation tempScmSupplierRegInvitation = scmSupplierRegInvitationBiz.selectByPlatVendorIdAndCtrl(platSupplierId, param.getControlUnitNo(), param);
									if(tempScmSupplierRegInvitation != null){
										continue;
									}
									Scmsupplier2 scmsupplier = new Scmsupplier2(true);
									scmsupplier.setVendorName(String.valueOf(resultarray.getJSONObject(i).get("businessName")));
									Scmsuppliergroup2 scmsuppliergroup = scmsuppliergroupBiz.selectByClassCode(1, "OAadd", param);
									if(scmsuppliergroup == null){
										Scmsuppliergroup2 scmsuppliergroup2 = new Scmsuppliergroup2(true);
										scmsuppliergroup2.setStandardId(1);
										scmsuppliergroup2.setClassCode("OAadd");
										scmsuppliergroup2.setClassName("未分类");
										scmsuppliergroup2.setParentId(0);
										scmsuppliergroup2.setCreator(param.getUsrCode());
										scmsuppliergroup2.setCreateDate(CalendarUtil.getDate(param));
										scmsuppliergroup2.setEditor(param.getUsrCode());
										scmsuppliergroup2.setEditDate(CalendarUtil.getDate(param));
										scmsuppliergroup2.setControlUnitNo(param.getControlUnitNo());
										scmsuppliergroupBiz.add(scmsuppliergroup2, param);
										scmsupplier.setClassId(scmsuppliergroup2.getId());
									}else{
										scmsupplier.setClassId(scmsuppliergroup.getId());
									}
									ScmIndustryGroup scmIndustryGroup = scmIndustryGroupBiz.selectByClassCode(String.valueOf(resultarray.getJSONObject(i).get("groupCode")), param);
									if(scmIndustryGroup == null){
										msg = String.valueOf(resultarray.getJSONObject(i).get("groupCode"))+"行业分类在系统不存在";
										continue;
									}
									scmsupplier.setIndustryGroupId(scmIndustryGroup.getId());
									scmsupplier.setTaxNo(String.valueOf(resultarray.getJSONObject(i).get("taxNo")));
									scmsupplier.setRole("1");
									scmsupplier.setOrgUnitNo(param.getOrgUnitNo());
									scmsupplier.setCreator(param.getUsrCode());
									scmsupplier.setCreateDate(CalendarUtil.getDate(param));
									scmsupplier.setStatus("I");
									scmsupplier.setQualificationStatus("I");
									String relationStatus = "";
									String qualificationStatus = "";
									if(resultarray.getJSONObject(i).get("relationStatus") != null){
										scmsupplier.setStatus(String.valueOf(resultarray.getJSONObject(i).get("relationStatus")));
										relationStatus = String.valueOf(resultarray.getJSONObject(i).get("relationStatus"));
									}
									if(resultarray.getJSONObject(i).get("authStatus") != null){
										scmsupplier.setQualificationStatus(String.valueOf(resultarray.getJSONObject(i).get("authStatus")));
										qualificationStatus = String.valueOf(resultarray.getJSONObject(i).get("authStatus"));
									}
									scmsupplier.setManageOrgUnitNo(param.getControlUnitNo());
									scmsupplier.setControlUnitNo(param.getControlUnitNo());
									scmsupplierBiz.add(scmsupplier, param);
									//新增供应商分类关系
									if(scmsupplier.getId() > 0){
										Scmsuppliergroupdetail scmsuppliergroupdetail = new Scmsuppliergroupdetail(true);
										scmsuppliergroupdetail.setVendorId(scmsupplier.getId());
										scmsuppliergroupdetail.setClassId(scmsupplier.getClassId());
										scmsuppliergroupdetailBiz.add(scmsuppliergroupdetail, param);
									}
									//采购资料
									Scmsupplierpurchaseinfo2 scmsupplierpurchaseinfo = new Scmsupplierpurchaseinfo2(true);
									scmsupplierpurchaseinfo.setVendorId(scmsupplier.getId());
									scmsupplierpurchaseinfo.setOrgUnitNo(param.getOrgUnitNo());
									scmsupplierpurchaseinfo.setTaxpayerType(String.valueOf(resultarray.getJSONObject(i).get("taxTypeCode")));
									BigDecimal taxRate = resultarray.getJSONObject(i).get("taxRate") != null ? (BigDecimal)(resultarray.getJSONObject(i).get("taxRate")) : BigDecimal.ZERO;
									scmsupplierpurchaseinfo.setVatRate(taxRate);
									scmsupplierpurchaseinfo.setStatus("I");
									Scmsupplierlinkman scmsupplierlinkman = new Scmsupplierlinkman(true);
									scmsupplierlinkman.setVendorId(scmsupplier.getId());
									scmsupplierlinkman.setContactPerson(String.valueOf(resultarray.getJSONObject(i).get("contactPerson")));
									scmsupplierlinkman.setTel(String.valueOf(resultarray.getJSONObject(i).get("tel")));
									List<Scmsupplierlinkman> linkManList = new ArrayList<>();
									linkManList.add(scmsupplierlinkman);
									scmsupplierpurchaseinfo.setScmsupplierlinkmanList(linkManList);
									//财务资料
									Scmsuppliercompanyinfo2 scmsuppliercompanyinfo = new Scmsuppliercompanyinfo2(true);
									scmsuppliercompanyinfo.setVendorId(scmsupplier.getId());
									scmsuppliercompanyinfo.setOrgUnitNo(param.getOrgUnitNo());
									scmsuppliercompanyinfo.setSettleCycle("M");
									scmsuppliercompanyinfo.setDays(30);
									scmsuppliercompanyinfo.setCreator(param.getUsrCode());
									scmsuppliercompanyinfo.setCreateDate(CalendarUtil.getDate(param));
									scmsuppliercompanyinfo.setStatus("I");
									List<Scmsupplier2> scmsupplierList = new ArrayList<>();
									scmsupplierList.add(scmsupplier);
									List<Scmsupplierpurchaseinfo2> scmsupplierpurchaseinfoList = new ArrayList<>();
									scmsupplierpurchaseinfoList.add(scmsupplierpurchaseinfo);
									List<Scmsuppliercompanyinfo2> scmsuppliercompanyinfoList = new ArrayList<>();
									scmsuppliercompanyinfoList.add(scmsuppliercompanyinfo);
									CommonBean bean = new CommonBean();
									bean.setList(scmsupplierList);
									bean.setList2(scmsupplierpurchaseinfoList);
									bean.setList3(scmsuppliercompanyinfoList);
									scmsupplierBiz.update(bean, param);
									//绑定供应商
									scmsupplier.setUniqueNo(scmSupplierDemander.getUniqueNo());
									scmSupplierRegInvitationBiz.getOrAddByVendor(scmsupplier, param);
									ScmSupplierRegInvitation scmSupplierRegInvitation2 = scmSupplierRegInvitationBiz.selectByVendorIdAndCtrl(scmsupplier.getId(), param.getControlUnitNo(), param);
									if(scmSupplierRegInvitation2 != null){
										if(scmSupplierRegInvitation2.getPlatSupplierId() <= 0){
											scmSupplierRegInvitation2.setPlatSupplierId(platSupplierId);
										}
										scmSupplierRegInvitation2.setUesed(true);
										if(StringUtils.isNotBlank(adminCode)) {
											scmSupplierRegInvitation2.setAddAdmin(true);
											scmSupplierRegInvitation2.setAdminCode(adminCode);
											scmSupplierRegInvitation2.setAdminName(adminName);
										}
										scmSupplierRegInvitationBiz.update(scmSupplierRegInvitation2, param);
										if(StringUtils.contains("I,W,O,R", scmsupplier.getStatus()) && StringUtils.contains("I,W,O,RJ,RS", scmsupplier.getQualificationStatus())){
											String updateParams = "{\"authStatus\": \""+scmsupplier.getQualificationStatus()+"\",\"demanderId\": "+scmSupplierDemander.getDemanderId()+",\"id\": "+scmSupplierRegInvitation2.getPlatSupplierId()+",\"relationStatus\": \""+scmsupplier.getStatus()+"\",\"refVendorNo\": \""+scmsupplier.getVendorNo()+"\",\"unionNo\": \""+scmSupplierDemander.getUniqueNo()+"\",\"systemType\": \"iSCM\"}";
											String updateData = getData(appInfo, scmSupplierRegInvitation2.getPlatSupplierId(), "/isp/businessaccount/syncUpdate", updateParams);
										}
									}
									if("W".equals(relationStatus) && StringUtils.contains("W,RS", qualificationStatus)){
										this.sendSupplierQualityAuditMsg(scmsupplier, param);
									}
								}
							}
						} catch (Exception e) {
							msg = e.getMessage();
							if(StringUtils.isNotBlank(msg) && msg.length()>255){
								msg = StringUtils.substring(msg, 0, 255-1);
							}
							continue;
						}
					}
					if(scmSystemTaskExecTime==null) {
						scmSystemTaskExecTime = new ScmSystemTaskExecTime(true);
						scmSystemTaskExecTime.setOrgUnitNo(param.getControlUnitNo());
						scmSystemTaskExecTime.setTaskType(systemTask.getTaskType());
						scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTimeStamp));
						scmSystemTaskExecTime.setControlUnitNo(param.getControlUnitNo());
						scmSystemTaskExecTimeBiz.add(scmSystemTaskExecTime, param);
					}else {
						scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTimeStamp));
						scmSystemTaskExecTimeBiz.update(scmSystemTaskExecTime, param);
					}
					if(StringUtils.isNotBlank(msg)){
						return msg;
					}
				}
			}else{
				return String.valueOf(jsonObject.get("errText"));
			}
		}else{
			log.info("当前管理单元未绑定需求方！");
			return "当前管理单元未绑定需求方！";
		}
		return "";
	}
	
	public void batchPushReplyDataToSupplierPlat(AppInfo appInfo) {
		OperationParam param = new OperationParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("supplierConfirmDataGet");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(appInfo.getControlUnitNo(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && systemTask.getSize() > 0){
			Page page=new Page();
			page.setModelClass(ScmSyncTaskInfo2.class);
			page.setShowCount(systemTask.getSize());
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKCODE, QueryParam.QUERY_EQ,"SCM_ESP"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKTYPE, QueryParam.QUERY_EQ,"supplierReplyDataPush"));
			page.getParam().put(ScmSyncTaskInfo2.FN_PRODUCTCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_PRODUCTCODE, QueryParam.QUERY_EQ,"iSCM"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKSTATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKSTATUS, QueryParam.QUERY_EQ,"W"));
			page.getParam().put(ScmSyncTaskInfo2.FN_CONTROLUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmSyncTaskInfo2> scmSyncTaskInfoList = scmSyncTaskInfoBiz.findPage(page, param);
			if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
				int count = 0;
				for(int i=0;i<scmSyncTaskInfoList.size();i++){
					ScmSyncTaskInfo2 scmSyncTaskInfo = scmSyncTaskInfoList.get(i);
					ScmSyncData scmSyncData = scmSyncDataBiz.select(scmSyncTaskInfo.getSyncDataId(), param);
					if(scmSyncData != null && count<systemTask.getSize()){
						count++;
						try {
							scmSyncTaskInfo.setTaskStatus("D");
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							ScmSystemTaskExecTime scmSystemTaskExecTime = scmSystemTaskExecTimeBiz.selectByTaskType(param.getControlUnitNo(), "ReplyData", param);
							String updateTimestamp = "";
							if(scmSystemTaskExecTime!=null)
								updateTimestamp = scmSystemTaskExecTime.getExecTime();
							List<ScmSupplierReplyData2> scmSupplierReplyDataList = scmSupplierReplyDataBiz.selectPendingPushByCtrl(scmSyncData.getDataId(), param.getControlUnitNo(),updateTimestamp,param);
							String errorMsg = "";
							if(scmSupplierReplyDataList != null && !scmSupplierReplyDataList.isEmpty()){
								errorMsg = pushReplyDataToSupplierPlat(scmSupplierReplyDataList.get(0), scmSupplierDemander.getDemanderId(), param);
								updateTimestamp = FormatUtils.fmtDateTimeMS(scmSupplierReplyDataList.get(0).getUpdateTimeStamp());
								if(scmSystemTaskExecTime==null) {
									scmSystemTaskExecTime = new ScmSystemTaskExecTime(true);
									scmSystemTaskExecTime.setOrgUnitNo(param.getControlUnitNo());
									scmSystemTaskExecTime.setTaskType("ReplyData");
									scmSystemTaskExecTime.setExecTime(updateTimestamp);
									scmSystemTaskExecTime.setControlUnitNo(param.getControlUnitNo());
									scmSystemTaskExecTimeBiz.add(scmSystemTaskExecTime, param);
								}else {
									scmSystemTaskExecTime.setExecTime(updateTimestamp);
									scmSystemTaskExecTimeBiz.update(scmSystemTaskExecTime, param);
								}
							}else{
								errorMsg = "未找到原回复消息";
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncData = scmSyncDataBiz.select(scmSyncData.getId(), param);
							if(StringUtils.isBlank(errorMsg)){
								scmSyncTaskInfo.setTaskStatus("S");
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
								scmSyncData.setSynchron("Y");
								scmSyncData.setSyncTime(new Date());
								scmSyncData.setErrorMessage(null);
								scmSyncDataBiz.update(scmSyncData, param);
							}else{
								scmSyncTaskInfo.setTaskStatus("F");
								scmSyncTaskInfo.setStatusMessage(errorMsg);
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
								scmSyncData.setSynchron("F");
								scmSyncData.setErrorMessage(errorMsg);
								scmSyncDataBiz.update(scmSyncData, param);
							}
						} catch (Exception e) {
							log.error(e);
							String errorMsg = e.getMessage();
							if(StringUtils.isNotBlank(errorMsg) && errorMsg.length()>255){
								errorMsg = StringUtils.substring(errorMsg, 0, 255-1);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncData = scmSyncDataBiz.select(scmSyncData.getId(), param);
							scmSyncTaskInfo.setTaskStatus("F");
							scmSyncTaskInfo.setStatusMessage(errorMsg);
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfo.setEndDate(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							scmSyncData.setSynchron("F");
							scmSyncData.setErrorMessage(errorMsg);
							scmSyncDataBiz.update(scmSyncData, param);
							continue;
						}
					}
				}
			}else{
				log.info("不存在需要推送的回复消息！");
			}
		}else{
			log.info("当前管理单元未绑定需求方！");
		}
	}
	
	public String updateSupplierReplyData(AppInfo appInfo,ScmSystemTask systemTask,Param param) {
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(param.getControlUnitNo(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && StringUtils.isNotBlank(scmSupplierDemander.getUniqueNo())){
			ScmSupplierReplyData scmSupplierReplyData = scmSupplierReplyDataBiz.selectMaxUpdateTimeByCtrl(param.getControlUnitNo(),param);
			if(scmSupplierReplyData != null){
				String updateTimeStamp = FormatUtils.fmtDateTimeMS(scmSupplierReplyData.getUpdateTimeStamp());
				String params = "{\"demanderId\": "+scmSupplierDemander.getDemanderId()+",\"updateTimeStamp\": \""+updateTimeStamp+"\"}";
				String data = getDataPage(appInfo, "/isp/billConfirmMsg/queryListForDemander", params, -1, (systemTask.getSize() > 0 ? systemTask.getSize() : 20));
				log.info("接口返回："+data);
				JSONObject jsonObject = JSON.parseObject(data);
				if(jsonObject != null && "0".equals(String.valueOf(jsonObject.get("errCode")))){
					JSONArray resultarray = jsonObject.getJSONArray("data");
					if(resultarray != null && !resultarray.isEmpty()){
						for (int i = 0; i < resultarray.size(); i++) {
							if(resultarray.getJSONObject(i).get("refBillNo") != null 
									&& StringUtils.isNotBlank(String.valueOf(resultarray.getJSONObject(i).get("refBillNo")))
									&& !"null".equals(String.valueOf(resultarray.getJSONObject(i).get("refBillNo")))){
								long refMsgId = Long.parseLong(String.valueOf(resultarray.getJSONObject(i).get("refMsgId")));
								if(refMsgId > 0){
									Date readTime = FormatUtils.parseDateTimeMs(String.valueOf(resultarray.getJSONObject(i).get("readTime")));
									Date refUpdateTimeStamp = FormatUtils.parseDateTimeMs(String.valueOf(resultarray.getJSONObject(i).get("updateTimeStamp")));
									ScmSupplierReplyData scmSupplierReplyData2 = scmSupplierReplyDataBiz.selectByReplyDataId(refMsgId, param);
									if(scmSupplierReplyData2 != null){
										if(scmSupplierReplyData2.getUpdateTimeStamp().compareTo(refUpdateTimeStamp) < 0){
											//更新原有记录
											scmSupplierReplyData2.setReadTime(readTime);
											scmSupplierReplyDataBiz.update(scmSupplierReplyData2, param);
										}
									}
								}
							}
						}
					}
				}else{
					return String.valueOf(jsonObject.get("errText"));
				}
			}
		}else{
			log.info("当前管理单元未绑定需求方！");
			return "当前管理单元未绑定需求方！";
		}
		return "";
	}
	
	public void batchUpdateInvPurInWarehsBillConfirmStatus(AppInfo appInfo) {
		OperationParam param = new OperationParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("supplierConfirmDataGet");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(appInfo.getControlUnitNo(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && systemTask.getSize() > 0){
			Page page=new Page();
			page.setModelClass(ScmSyncTaskInfo2.class);
			page.setShowCount(systemTask.getSize());
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKCODE, QueryParam.QUERY_EQ,"SCM_ESP"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKTYPE, QueryParam.QUERY_EQ,systemTask.getTaskType()));
			page.getParam().put(ScmSyncTaskInfo2.FN_PRODUCTCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_PRODUCTCODE, QueryParam.QUERY_EQ,"iSCM"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKSTATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKSTATUS, QueryParam.QUERY_EQ,"W"));
			page.getParam().put(ScmSyncTaskInfo2.FN_CONTROLUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmSyncTaskInfo2> scmSyncTaskInfoList = scmSyncTaskInfoBiz.findPage(page, param);
			if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
				int count = 0;
				for(int i=0;i<scmSyncTaskInfoList.size();i++){
					ScmSyncTaskInfo2 scmSyncTaskInfo = scmSyncTaskInfoList.get(i);
					if(count<systemTask.getSize()){
						count++;
						try {
							scmSyncTaskInfo.setTaskStatus("D");
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							String errorMsg = updateInvPurInWarehsBillConfirmStatus(appInfo,systemTask,param);
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							if(StringUtils.isBlank(errorMsg)){
								scmSyncTaskInfo.setTaskStatus("S");
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							}else{
								scmSyncTaskInfo.setTaskStatus("F");
								scmSyncTaskInfo.setStatusMessage(errorMsg);
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							}
						} catch (Exception e) {
							log.error(e);
							String errorMsg = e.getMessage();
							if(StringUtils.isNotBlank(errorMsg) && errorMsg.length()>255){
								errorMsg = StringUtils.substring(errorMsg, 0, 255-1);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncTaskInfo.setTaskStatus("F");
							scmSyncTaskInfo.setStatusMessage(errorMsg);
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfo.setEndDate(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							continue;
						}
					}
				}
			}else{
				log.info("不存在拉取供应商平台确认信息任务！");
			}
			//暂时写在这里
			//updateSupplierReplyData(appInfo, systemTask, param);
		}else{
			log.info("当前管理单元未绑定需求方！");
		}
	}
	
	public String updateInvPurInWarehsBillConfirmStatus(AppInfo appInfo,ScmSystemTask systemTask,Param param) {
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(param.getControlUnitNo(), param);
		ScmSystemTaskExecTime scmSystemTaskExecTime = scmSystemTaskExecTimeBiz.selectByTaskType(param.getControlUnitNo(), systemTask.getTaskType(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && StringUtils.isNotBlank(scmSupplierDemander.getUniqueNo())){
			String updateTime = (scmSystemTaskExecTime==null || StringUtils.isEmpty(scmSystemTaskExecTime.getExecTime())) ? FormatUtils.fmtDateTimeMS(CalendarUtil.relativeDate(new Date(), -1)) : scmSystemTaskExecTime.getExecTime();
			Date updateTimeStamp = FormatUtils.parseDateTimeMs(updateTime);
			String params = "{\"demanderId\": "+scmSupplierDemander.getDemanderId()+",\"updateTimeStamp\": \""+updateTime+"\"}";
			String data = getDataPage(appInfo, "/isp/billConfirm/queryListForDemander", params, -1, (systemTask.getSize() > 0 ? systemTask.getSize() : 20));
			log.info("接口返回："+data);
			JSONObject jsonObject = JSON.parseObject(data);
			if(jsonObject != null && "0".equals(String.valueOf(jsonObject.get("errCode")))){
				JSONArray resultarray = jsonObject.getJSONArray("data");
				if(resultarray != null && !resultarray.isEmpty()){
					for (int i = 0; i < resultarray.size(); i++) {
						if(resultarray.getJSONObject(i).get("refBillNo") != null 
								&& StringUtils.isNotBlank(String.valueOf(resultarray.getJSONObject(i).get("refBillNo")))
								&& !"null".equals(String.valueOf(resultarray.getJSONObject(i).get("refBillNo")))){
							String billNo = String.valueOf(resultarray.getJSONObject(i).get("refBillNo"));
							String billtype = String.valueOf(resultarray.getJSONObject(i).get("billtype"));
							boolean changed = false;
							String orgUnitNo = "";
							ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = null;
							ScmPurPrice2 scmPurPrice = null;
							if(StringUtils.equals("PurOrder", billtype)){
								ScmPurOrder2 scmPurOrder = scmPurOrderBiz.selectByPoNo(billNo, param);
								if(scmPurOrder!=null) {
									orgUnitNo = scmPurOrder.getOrgUnitNo();
								}
							}else if(StringUtils.equals("InvPurInWhsBill", billtype)){
								scmInvPurInWarehsBill = scmInvPurInWarehsBillBiz.selectByWrNo(billNo, param);
								if(scmInvPurInWarehsBill!=null) {
									orgUnitNo = scmInvPurInWarehsBill.getOrgUnitNo();
								}
							}else if(StringUtils.equals("PurPrice", billtype)){
								billtype = "ScmPurPrice";
								scmPurPrice = scmPurPriceBiz.selectByPmNo(billNo, param);
								if(scmPurPrice!=null) {
									orgUnitNo = scmPurPrice.getOrgUnitNo();
								}
							}
							if(StringUtils.isNotBlank(orgUnitNo)) {
								ScmSupplierConfirmData2 scmSupplierConfirmData = JSON.parseObject(resultarray.getJSONObject(i).toJSONString(), ScmSupplierConfirmData2.class);
								scmSupplierConfirmData.setBcId((Long.parseLong(String.valueOf(resultarray.getJSONObject(i).get("id")))));
								scmSupplierConfirmData.setId(0);
								scmSupplierConfirmData.setOrgUnitNo(orgUnitNo);
								scmSupplierConfirmData.setBillNo(billNo);
								scmSupplierConfirmData.setBillType(billtype);
								scmSupplierConfirmData.setPlatSupplierId((Long.parseLong(String.valueOf(resultarray.getJSONObject(i).get("supplierId")))));
								scmSupplierConfirmData.setCreator(String.valueOf(resultarray.getJSONObject(i).get("createByName")));
								scmSupplierConfirmData.setCreateDate(FormatUtils.parseDateTime(String.valueOf(resultarray.getJSONObject(i).get("createTime"))));
								scmSupplierConfirmData.setEditor(String.valueOf(resultarray.getJSONObject(i).get("updateByName")));
								scmSupplierConfirmData.setEditDate(FormatUtils.parseDateTime(String.valueOf(resultarray.getJSONObject(i).get("updateTime"))));
								scmSupplierConfirmData.setControlUnitNo(param.getControlUnitNo());
								ScmSupplierConfirmData2 scmSupplierConfirmData2 = scmSupplierConfirmDataBiz.selectByBcId(scmSupplierConfirmData.getBcId(), param);
								if(scmSupplierConfirmData2 != null){
									if((scmSupplierConfirmData2.getEditDate()==null) || (scmSupplierConfirmData.getEditDate() != null && scmSupplierConfirmData2.getEditDate() != null
											&& scmSupplierConfirmData.getEditDate().compareTo(scmSupplierConfirmData2.getEditDate()) > 0)){
										//更新原有记录
										long id = scmSupplierConfirmData2.getId();
										BeanUtils.copyProperties(scmSupplierConfirmData, scmSupplierConfirmData2);
										scmSupplierConfirmData2.setId(id);
										scmSupplierConfirmDataBiz.update(scmSupplierConfirmData2, param);
										changed = true;
									}
								}else{
									changed = true;
									scmSupplierConfirmDataBiz.add(scmSupplierConfirmData, param);
								}
								Date tempUpdateTimeStamp = FormatUtils.parseDateTimeMs(String.valueOf(resultarray.getJSONObject(i).get("updateTimeStamp")));
								updateTimeStamp = updateTimeStamp.compareTo(tempUpdateTimeStamp) < 0 ? tempUpdateTimeStamp : updateTimeStamp;
								if(scmInvPurInWarehsBill != null && changed){
									ScmSupplierConfirmData2 tempScmSupplierConfirmData = scmSupplierConfirmDataBiz.selectByBillNoAndType(scmSupplierConfirmData.getBillNo(), billtype, param);
									if(tempScmSupplierConfirmData != null && StringUtils.isNotBlank(tempScmSupplierConfirmData.getStatus())){
										scmInvPurInWarehsBill.setConfirmStatus(tempScmSupplierConfirmData.getStatus());
									}
									scmInvPurInWarehsBill.setWrNo(scmSupplierConfirmData.getBillNo());
									scmInvPurInWarehsBillBiz.updateConfirmStatus(scmInvPurInWarehsBill, param);
								}
								if(scmPurPrice != null && changed){
									scmPurPrice.setVendorPqDate1(null);
									scmPurPrice.setVendorPqDate2(null);
									scmPurPrice.setVendorPqDate3(null);
									List<ScmPurPriceEntry2> scmPurPriceEntry2s = scmPurPriceEntryBiz.selectByPmId(scmPurPrice.getId(), param);
									StringBuffer vendorIds = new StringBuffer("0");
									if (scmPurPriceEntry2s != null && !scmPurPriceEntry2s.isEmpty()) {
										for (ScmPurPriceEntry2 scmPurPriceEntry2 : scmPurPriceEntry2s) {
											if (StringUtils.isNotBlank(vendorIds.toString())) {
												vendorIds.append(",");
											}
											vendorIds.append(scmPurPriceEntry2.getSelVndrId());
										}
									}
									//O 确认  D异议  B部分确认
									String status = "O";
									List<ScmSupplierRegInvitation> supplierPlat = scmPurPriceBiz.getEnterSupplierPlat(vendorIds.toString(), param);
									List<ScmSupplierConfirmData2> scmSupplierConfirmData2s = scmSupplierConfirmDataBiz.selectByBillNoAndPurPrice(billNo, billtype, param);
									if (scmSupplierConfirmData2s != null && !scmSupplierConfirmData2s.isEmpty()) {
										for (ScmSupplierConfirmData2 scmSupplierConfirmData22 : scmSupplierConfirmData2s) {
											if (StringUtils.equals(scmSupplierConfirmData22.getStatus(), "D")) {
												status = scmSupplierConfirmData22.getStatus();
												break;
											}
										}
										if (supplierPlat != null && ! supplierPlat.isEmpty() && !StringUtils.equals(status, "D")) {
											if (supplierPlat.size() == scmSupplierConfirmData2s.size()) {
												status="O";
											}else {
												status="B";
											}
										}
									}
									scmPurPrice.setConfirmStatus(status);
									scmPurPriceBiz.updateVendorPqData(scmPurPrice, param);
								}
							}
						}
					}
					if(scmSystemTaskExecTime==null) {
						scmSystemTaskExecTime = new ScmSystemTaskExecTime(true);
						scmSystemTaskExecTime.setOrgUnitNo(param.getControlUnitNo());
						scmSystemTaskExecTime.setTaskType(systemTask.getTaskType());
						scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTimeStamp));
						scmSystemTaskExecTime.setControlUnitNo(param.getControlUnitNo());
						scmSystemTaskExecTimeBiz.add(scmSystemTaskExecTime, param);
					}else {
						scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTimeStamp));
						scmSystemTaskExecTimeBiz.update(scmSystemTaskExecTime, param);
					}
				}
			}else{
				return String.valueOf(jsonObject.get("errText"));
			}
		}else{
			log.info("当前管理单元未绑定需求方！");
			return "当前管理单元未绑定需求方！";
		}
		return "";
	}
	
	public String pushPurOrderToSupplierPlat(AppInfo appInfo,long purOrderId, String taskSource,Param param) {
		if(purOrderId > 0){
			ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(param.getControlUnitNo(), param);
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0){
				long demanderId = scmSupplierDemander.getDemanderId();
				List<ScmPurOrder2> scmPurOrderTempList = new ArrayList<ScmPurOrder2>();
				ScmPurOrder2 tempScmPurOrder = new ScmPurOrder2(true);
				tempScmPurOrder.setId(purOrderId);
				scmPurOrderTempList.add(tempScmPurOrder);
				CommonBean scmPurOrderCommonBean = new CommonBean();
				scmPurOrderCommonBean.setList(scmPurOrderTempList);
				scmPurOrderCommonBean = scmPurOrderBiz.select(scmPurOrderCommonBean, param);
				if (scmPurOrderCommonBean != null) {
					List<ScmPurOrder2> scmPurOrderList = new ArrayList<ScmPurOrder2>();
					List<ScmPurOrderEntry2> scmPurOrderEntryList = new ArrayList<ScmPurOrderEntry2>();
					if(scmPurOrderCommonBean.getList() != null && !scmPurOrderCommonBean.getList().isEmpty()){
						scmPurOrderList = (List<ScmPurOrder2>) scmPurOrderCommonBean.getList();
					}
					if(scmPurOrderCommonBean.getList2() != null && !scmPurOrderCommonBean.getList2().isEmpty()){
						scmPurOrderEntryList = (List<ScmPurOrderEntry2>) scmPurOrderCommonBean.getList2();
					}
					if(scmPurOrderList != null && !scmPurOrderList.isEmpty()
							&& scmPurOrderEntryList != null && !scmPurOrderEntryList.isEmpty()){
						if(appInfo == null){
							appInfo = new AppInfo(AppInfo.APP_NAME,param.getControlUnitNo(),param.getControlUnitNo());
							appInfo = getAPP(appInfo);
						}
						List<SupplierPlatPurOrderEntry> supplierPlatPurOrderEntryList = new ArrayList<SupplierPlatPurOrderEntry>();
						SupplierPlatPurOrder supplierPlatPurOrder = new SupplierPlatPurOrder();
						ScmPurOrder2 scmPurOrder = scmPurOrderList.get(0);
						ScmSupplierRegInvitation scmSupplierRegInvitation = scmSupplierRegInvitationBiz.selectByVendorIdAndCtrl(scmPurOrder.getVendorId(), param.getControlUnitNo(), param);
						if(scmSupplierRegInvitation != null && scmSupplierRegInvitation.getPlatSupplierId() > 0){
							long platSupplierId = scmSupplierRegInvitation.getPlatSupplierId();
							BeanUtils.copyProperties(scmPurOrder, supplierPlatPurOrder);
							supplierPlatPurOrder.setCurrency(scmPurOrder.getCurrencyNo());
							supplierPlatPurOrder.setDemanderId(demanderId);
							supplierPlatPurOrder.setPayment(scmPurOrder.getPaymentName());
							supplierPlatPurOrder.setRefPoNo(scmPurOrder.getPoNo());
							supplierPlatPurOrder.setSettlement(scmPurOrder.getSettlementName());
							supplierPlatPurOrder.setStatus(scmPurOrder.getStatus());
							supplierPlatPurOrder.setBuyerOrgName(scmPurOrder.getPurOrgUnitName());
							supplierPlatPurOrder.setBuyerOrgUnitNo(scmPurOrder.getOrgUnitNo());
							supplierPlatPurOrder.setTaskSource(taskSource);
							supplierPlatPurOrder.setVendorId(platSupplierId);
							for(ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList){
								if("N".equalsIgnoreCase(scmPurOrderEntry.getRowStatus())){
									continue;
								}
								SupplierPlatPurOrderEntry supplierPlatPurOrderEntry = new SupplierPlatPurOrderEntry();
								BeanUtils.copyProperties(scmPurOrderEntry, supplierPlatPurOrderEntry);
								supplierPlatPurOrderEntry.setBalanceDemanderId(demanderId);
								supplierPlatPurOrderEntry.setReceiveAddress(scmPurOrderEntry.getReceiveOrgUnitName());
								supplierPlatPurOrderEntry.setReceiveNo(scmPurOrderEntry.getReceiveOrgUnitNo());
								supplierPlatPurOrderEntry.setReqUnitNo(scmPurOrderEntry.getReqOrgUnitNo());
								supplierPlatPurOrderEntry.setReqUnitName(scmPurOrderEntry.getReqOrgUnitName());
								supplierPlatPurOrderEntry.setFinUnitNo(scmPurOrderEntry.getReceiveFinOrgUnitNo());
								supplierPlatPurOrderEntry.setFinUnitName(scmPurOrderEntry.getReceiveFinOrgUnitName());
								supplierPlatPurOrderEntry.setUnitName(scmPurOrderEntry.getPurUnitName());
								//获取文件的Base64和MD5
								Map<String, String> map = fileToBase64AndMd5(scmPurOrderEntry.getAttachmentId(),param);
								if (map != null) {
									supplierPlatPurOrderEntry.setAttachData(map.get("attachData"));
									supplierPlatPurOrderEntry.setAttachMD5(map.get("attachMD5"));
								}
								supplierPlatPurOrderEntryList.add(supplierPlatPurOrderEntry);
							}
							supplierPlatPurOrder.setEntryList(supplierPlatPurOrderEntryList);
							supplierPlatPurOrder.setId(0);
							String data = getData(appInfo, platSupplierId, "/isp/purOrder/sync", JSONObject.toJSONString(supplierPlatPurOrder));
							log.info("接口返回："+data);
							JSONObject jsonObject = JSON.parseObject(data);
							if(jsonObject != null && "0".equals(String.valueOf(jsonObject.get("errCode")))){
								scmPurOrder.setPushed(true);
								scmPurOrderBiz.updatePushed(scmPurOrder, param);
							}else{
								return String.valueOf(jsonObject.get("errText"));
							}
						}else{
							log.info("当前订货单供应商未获取到供应商平台的对照！");
							return "当前订货单供应商未获取到供应商平台的对照！";
						}
					}
				}else{
					log.info("未查询到订货单！");
					return "未查询到订货单！";
				}
			}else{
				log.info("当前管理单元未绑定需求方！");
				return "当前管理单元未绑定需求方！";
			}
		}else{
			
		}
		return "";
	}
	
	public String billDeleteSync(AppInfo appInfo,long platSupplierId,long demanderId,String billNo,String billType,Param param) {
		String url = "";
		String params = "";
		if(StringUtils.equals(billType, "InvPurInWhsBill")) {
			url = "/isp/invPurInWarehs/deleteSync";
			params = "{\"demanderId\": "+demanderId+",\"refWrNo\": \""+billNo+"\"}";
		}else if(StringUtils.equals(billType, "PurOrder")){
			url = "/isp/purOrder/deleteSync";
			params = "{\"demanderId\": "+demanderId+",\"refPoNo\": \""+billNo+"\"}";
		}else if(StringUtils.equals(billType, "ScmPurPrice")){
			url = "/isp/purPrice/deleteSync";
			params = "{\"demanderId\": "+demanderId+",\"refPmNo\": \""+billNo+"\"}";
		}else if(StringUtils.equals(billType, "ScmIndustryGroup")){
			url = "/isp/qualifieGroupType/deleteSync";
			params = "{\"baId\": "+demanderId+",\"groupCode\": \""+billNo+"\"}";
		}else if(StringUtils.equals(billType, "ScmSupplierEvent")){
			url = "/isp/inviteEvent/syncDel";
			params = "{\"demanderId\": "+demanderId+",\"code\": \""+billNo+"\"}";
		}
		if(StringUtils.isNotBlank(url) && StringUtils.isNotBlank(params)){
			String data = getData(appInfo, platSupplierId, url, params);
			log.info("接口返回："+data);
			JSONObject jsonObject = JSON.parseObject(data);
			if(jsonObject != null && "0".equals(String.valueOf(jsonObject.get("errCode")))){
				return "";
			}else{
				return String.valueOf(jsonObject.get("errText"));
			}
		}
		return "";
	}
	
	public void batchPushPurOrderToSupplierPlat(AppInfo appInfo) {
		OperationParam param = new OperationParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("purOrderPush");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(appInfo.getControlUnitNo(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && systemTask.getSize() > 0){
			Page page=new Page();
			page.setModelClass(ScmSyncTaskInfo2.class);
			page.setShowCount(systemTask.getSize());
			ArrayList argList = new ArrayList();
	        argList.add("taskCode="+"SCM_ESP");
	        argList.add("taskAction=" + "Push");
	        argList.add("taskType=" + systemTask.getTaskType());
	        argList.add("productCode=" + param.getProductCode());
	        argList.add("taskOwner=" + "ESP");
	        argList.add("taskStatus=" + "W");
	        argList.add("controlUnitNo=" + param.getControlUnitNo());
	        List<ScmSyncTaskInfo2> scmSyncTaskInfoList = scmSyncTaskInfoBiz.queryPage(page, argList, "findPengdingTaskPage", param);
			if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
				int count = 0;
				for(int i=0;i<scmSyncTaskInfoList.size();i++){
					ScmSyncTaskInfo2 scmSyncTaskInfo = scmSyncTaskInfoList.get(i);
					ScmSyncData scmSyncData = scmSyncDataBiz.select(scmSyncTaskInfo.getSyncDataId(), param);
					if(scmSyncData != null && "PurOrder".equalsIgnoreCase(scmSyncData.getBillType()) && count<systemTask.getSize()){
						count++;
						try {
							scmSyncTaskInfo.setTaskStatus("D");
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							String errorMsg = "";
							if(StringUtils.equals(scmSyncData.getSyncType(), "delete")){
								errorMsg = billDeleteSync(appInfo, scmSyncData.getPlatSupplierId(), scmSupplierDemander.getDemanderId(), scmSyncData.getBillNo(), "PurOrder", param);
							}else{
								errorMsg = pushPurOrderToSupplierPlat(appInfo, scmSyncData.getDataId(), scmSyncTaskInfo.getTaskSource(), param);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncData = scmSyncDataBiz.select(scmSyncData.getId(), param);
							if(StringUtils.isBlank(errorMsg)){
								scmSyncTaskInfo.setTaskStatus("S");
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
								scmSyncData.setSynchron("Y");
								scmSyncData.setSyncTime(new Date());
								scmSyncData.setErrorMessage(null);
								scmSyncDataBiz.updateBillNoChangeTime(scmSyncData, param);
							}else{
								scmSyncTaskInfo.setTaskStatus("F");
								scmSyncTaskInfo.setStatusMessage(errorMsg);
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
								scmSyncData.setSynchron("F");
								scmSyncData.setErrorMessage(errorMsg);
								scmSyncDataBiz.updateBillNoChangeTime(scmSyncData, param);
							}
						} catch (Exception e) {
							log.error(e);
							String errorMsg = e.getMessage();
							if(StringUtils.isNotBlank(errorMsg) && errorMsg.length()>255){
								errorMsg = StringUtils.substring(errorMsg, 0, 255-1);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncData = scmSyncDataBiz.select(scmSyncData.getId(), param);
							scmSyncTaskInfo.setTaskStatus("F");
							scmSyncTaskInfo.setStatusMessage(errorMsg);
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfo.setEndDate(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							scmSyncData.setSynchron("F");
							scmSyncData.setErrorMessage(errorMsg);
							scmSyncDataBiz.updateBillNoChangeTime(scmSyncData, param);
							continue;
						}
					}else if(scmSyncData != null && "PurOrder".equalsIgnoreCase(scmSyncData.getBillType()) && "Y".equalsIgnoreCase(scmSyncData.getSynchron())){
						scmSyncTaskInfo.setTaskStatus("C");
						scmSyncTaskInfo.setUpdateTime(new Date());
						scmSyncTaskInfo.setEndDate(new Date());
						scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
					}
				}
			}else{
				log.info("不存在需要推送的采购订单！");
			}
		}else{
			log.info("当前管理单元未绑定需求方！");
		}
	}
	
	public String pushInvPurInWarehsBillToSupplierPlat(AppInfo appInfo,long purInWarehsBillId, String taskSource, Param param) {
		if(purInWarehsBillId > 0){
			ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(param.getControlUnitNo(), param);
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0){
				long demanderId = scmSupplierDemander.getDemanderId();
				List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillTempList = new ArrayList<ScmInvPurInWarehsBill2>();
				ScmInvPurInWarehsBill2 tempScmInvPurInWarehsBill = new ScmInvPurInWarehsBill2(true);
				tempScmInvPurInWarehsBill.setWrId(purInWarehsBillId);
				scmInvPurInWarehsBillTempList.add(tempScmInvPurInWarehsBill);
				CommonBean scmInvPurInWarehsBillCommonBean = new CommonBean();
				scmInvPurInWarehsBillCommonBean.setList(scmInvPurInWarehsBillTempList);
				scmInvPurInWarehsBillCommonBean = scmInvPurInWarehsBillBiz.select(scmInvPurInWarehsBillCommonBean, param);
				if (scmInvPurInWarehsBillCommonBean != null) {
					List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList = new ArrayList<ScmInvPurInWarehsBill2>();
					List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = new ArrayList<ScmInvPurInWarehsBillEntry2>();
					if(scmInvPurInWarehsBillCommonBean.getList() != null && !scmInvPurInWarehsBillCommonBean.getList().isEmpty()){
						scmInvPurInWarehsBillList = (List<ScmInvPurInWarehsBill2>) scmInvPurInWarehsBillCommonBean.getList();
					}
					if(scmInvPurInWarehsBillCommonBean.getList2() != null && !scmInvPurInWarehsBillCommonBean.getList2().isEmpty()){
						scmInvPurInWarehsBillEntryList = (List<ScmInvPurInWarehsBillEntry2>) scmInvPurInWarehsBillCommonBean.getList2();
					}
					if(scmInvPurInWarehsBillList != null && !scmInvPurInWarehsBillList.isEmpty()
							&& scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()){
						if(appInfo == null){
							appInfo = new AppInfo(AppInfo.APP_NAME,param.getControlUnitNo(),param.getControlUnitNo());
							appInfo = getAPP(appInfo);
						}
						List<SupplierPlatInvPurInWarehsBillEntry> supplierPlatInvPurInWarehsBillEntryList = new ArrayList<SupplierPlatInvPurInWarehsBillEntry>();
						SupplierPlatInvPurInWarehsBill supplierPlatInvPurInWarehsBill = new SupplierPlatInvPurInWarehsBill();
						ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = scmInvPurInWarehsBillList.get(0);
						ScmSupplierRegInvitation scmSupplierRegInvitation = scmSupplierRegInvitationBiz.selectByVendorIdAndCtrl(scmInvPurInWarehsBill.getVendorId(), param.getControlUnitNo(), param);
						if(scmSupplierRegInvitation != null && scmSupplierRegInvitation.getPlatSupplierId() > 0){
							long platSupplierId = scmSupplierRegInvitation.getPlatSupplierId();
							BeanUtils.copyProperties(scmInvPurInWarehsBill, supplierPlatInvPurInWarehsBill);
							supplierPlatInvPurInWarehsBill.setCurrency(scmInvPurInWarehsBill.getCurrencyNo());
							supplierPlatInvPurInWarehsBill.setDemanderId(demanderId);
							if(StringUtils.contains("1,3", scmInvPurInWarehsBill.getBizType())){
								supplierPlatInvPurInWarehsBill.setBizType("10");
							}else{
								supplierPlatInvPurInWarehsBill.setBizType("20");
							}
							supplierPlatInvPurInWarehsBill.setPoNo(null);
							supplierPlatInvPurInWarehsBill.setReceiveAddress(scmInvPurInWarehsBill.getOrgUnitName());
							supplierPlatInvPurInWarehsBill.setReceiveNo(scmInvPurInWarehsBill.getOrgUnitNo());
							supplierPlatInvPurInWarehsBill.setBuyerOrgUnitNo(scmInvPurInWarehsBill.getPurOrgUnitNo());
							supplierPlatInvPurInWarehsBill.setBuyerOrgName(scmInvPurInWarehsBill.getPurOrgUnitName());
							supplierPlatInvPurInWarehsBill.setRefWrNo(scmInvPurInWarehsBill.getWrNo());
							supplierPlatInvPurInWarehsBill.setStatus(scmInvPurInWarehsBill.getStatus());
							supplierPlatInvPurInWarehsBill.setTaskSource(taskSource);
							supplierPlatInvPurInWarehsBill.setVendorId(platSupplierId);
							ScmInvPurInWarehsBill2 scmInvPurInWarehsBill2 = scmInvPurInWarehsBillBiz.selectPoNoAndPvNoById(scmInvPurInWarehsBill.getWrId(), param);
							if(scmInvPurInWarehsBill2 != null){
								supplierPlatInvPurInWarehsBill.setRefPoNo(scmInvPurInWarehsBill2.getPoNo());
								supplierPlatInvPurInWarehsBill.setRefPrNo(scmInvPurInWarehsBill2.getPvNo());
							}else{
								Page page=new Page();
								page.setModelClass(ScmInvPurInWarehsBill2.class);
								page.setShowCount(Integer.MAX_VALUE);
								page.getParam().put(
										ScmInvPurInWarehsBill2.FN_WRID,
										new QueryParam(ScmInvPurInWarehsBill2.FN_WRID, QueryParam.QUERY_EQ,
												String.valueOf(scmInvPurInWarehsBill.getWrId())));
								List<ScmInvPurInWarehsBill2> scmInvPurInWarehsList2 = scmInvPurInWarehsBillBiz.findPage(page, param);
								if(scmInvPurInWarehsList2 != null && !scmInvPurInWarehsList2.isEmpty()){
									supplierPlatInvPurInWarehsBill.setRefPrNo(scmInvPurInWarehsList2.get(0).getPvNo());
								}
							}
							for(ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList){
								SupplierPlatInvPurInWarehsBillEntry supplierPlatInvPurInWarehsBillEntry = new SupplierPlatInvPurInWarehsBillEntry();
								BeanUtils.copyProperties(scmInvPurInWarehsBillEntry, supplierPlatInvPurInWarehsBillEntry);
								supplierPlatInvPurInWarehsBillEntry.setBalanceDemanderId(demanderId);
								supplierPlatInvPurInWarehsBillEntry.setWareDept(scmInvPurInWarehsBillEntry.getUseOrgUnitName());
								supplierPlatInvPurInWarehsBillEntry.setWareHouse(scmInvPurInWarehsBillEntry.getWareHouseName());
								supplierPlatInvPurInWarehsBillEntry.setWareHouseNo(scmInvPurInWarehsBillEntry.getWareHouseNo());
								supplierPlatInvPurInWarehsBillEntry.setWareDeptNo(scmInvPurInWarehsBillEntry.getUseOrgUnitNo());
								supplierPlatInvPurInWarehsBillEntry.setReqOrgUnitNo(scmInvPurInWarehsBillEntry.getRequireOrgUnitNo());
								supplierPlatInvPurInWarehsBillEntry.setReqOrgName(scmInvPurInWarehsBillEntry.getRequireOrgUnitName());
								supplierPlatInvPurInWarehsBillEntry.setFinOrgUnitNo(scmInvPurInWarehsBillEntry.getFinOrgUnitNo());
								supplierPlatInvPurInWarehsBillEntry.setFinOrgName(scmInvPurInWarehsBillEntry.getFinOrgUnitName());
								supplierPlatInvPurInWarehsBillEntryList.add(supplierPlatInvPurInWarehsBillEntry);
							}
							supplierPlatInvPurInWarehsBill.setEntryList(supplierPlatInvPurInWarehsBillEntryList);
							supplierPlatInvPurInWarehsBill.setId(0);
							String data = getData(appInfo, platSupplierId, "/isp/invPurInWarehs/sync", JSONObject.toJSONString(supplierPlatInvPurInWarehsBill));
							log.info("接口返回："+data);
							JSONObject jsonObject = JSON.parseObject(data);
							if(jsonObject != null && "0".equals(String.valueOf(jsonObject.get("errCode")))){
								scmInvPurInWarehsBill.setPushed(true);
								scmInvPurInWarehsBillBiz.updatePushed(scmInvPurInWarehsBill, param);
							}else{
								return String.valueOf(jsonObject.get("errText"));
							}
						}else{
							log.info("当前采购入库单供应商未获取到供应商平台的对照！");
							return "当前采购入库单供应商未获取到供应商平台的对照！";
						}
					}
				}else{
					log.info("未查询到采购入库单！");
					return "未查询到采购入库单！";
				}
			}else{
				log.info("当前管理单元未绑定需求方！");
				return "当前管理单元未绑定需求方！";
			}
		}
		return "";
	}
	
	public void batchPushInvPurInWarehsBillToSupplierPlat(AppInfo appInfo) {
		OperationParam param = new OperationParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("invPurInWarehsBillPush");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(appInfo.getControlUnitNo(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && systemTask.getSize() > 0){
			Page page=new Page();
			page.setModelClass(ScmSyncTaskInfo2.class);
			page.setShowCount(systemTask.getSize());
			ArrayList argList = new ArrayList();
	        argList.add("taskCode="+"SCM_ESP");
	        argList.add("taskAction=" + "Push");
	        argList.add("taskType=" + systemTask.getTaskType());
	        argList.add("productCode=" + param.getProductCode());
	        argList.add("taskOwner=" + "ESP");
	        argList.add("taskStatus=" + "W");
	        argList.add("controlUnitNo=" + param.getControlUnitNo());
	        List<ScmSyncTaskInfo2> scmSyncTaskInfoList = scmSyncTaskInfoBiz.queryPage(page, argList, "findPengdingTaskPage", param);
			if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
				int count = 0;
				for(int i=0;i<scmSyncTaskInfoList.size();i++){
					ScmSyncTaskInfo2 scmSyncTaskInfo = scmSyncTaskInfoList.get(i);
					ScmSyncData scmSyncData = scmSyncDataBiz.select(scmSyncTaskInfo.getSyncDataId(), param);
					if(scmSyncData != null && "InvPurInWhsBill".equalsIgnoreCase(scmSyncData.getBillType()) && !"Y".equalsIgnoreCase(scmSyncData.getSynchron()) && count<systemTask.getSize()){
						count++;
						try {
							String errorMsg = "";
							if(StringUtils.equals(scmSyncData.getSyncType(), "delete")){
								errorMsg = billDeleteSync(appInfo, scmSyncData.getPlatSupplierId(), scmSupplierDemander.getDemanderId(), scmSyncData.getBillNo(), "InvPurInWhsBill", param);
							}else{
								errorMsg =  pushInvPurInWarehsBillToSupplierPlat(appInfo, scmSyncData.getDataId(), scmSyncTaskInfo.getTaskSource(), param);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncData = scmSyncDataBiz.select(scmSyncData.getId(), param);
							if(StringUtils.isBlank(errorMsg)){
								scmSyncTaskInfo.setTaskStatus("S");
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
								scmSyncData.setSynchron("Y");
								scmSyncData.setSyncTime(new Date());
								scmSyncData.setErrorMessage(null);
								scmSyncDataBiz.update(scmSyncData, param);
							}else{
								scmSyncTaskInfo.setTaskStatus("F");
								scmSyncTaskInfo.setStatusMessage(errorMsg);
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
								scmSyncData.setSynchron("F");
								scmSyncData.setErrorMessage(errorMsg);
								scmSyncDataBiz.update(scmSyncData, param);
							}
						} catch (Exception e) {
							log.error(e);
							String errorMsg = e.getMessage();
							if(StringUtils.isNotBlank(errorMsg) && errorMsg.length()>255){
								errorMsg = StringUtils.substring(errorMsg, 0, 255-1);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncData = scmSyncDataBiz.select(scmSyncData.getId(), param);
							scmSyncTaskInfo.setTaskStatus("F");
							scmSyncTaskInfo.setStatusMessage(errorMsg);
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfo.setEndDate(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							scmSyncData.setSynchron("F");
							scmSyncData.setErrorMessage(errorMsg);
							scmSyncDataBiz.update(scmSyncData, param);
							continue;
						}
					}else if(scmSyncData != null && "InvPurInWhsBill".equalsIgnoreCase(scmSyncData.getBillType()) && "Y".equalsIgnoreCase(scmSyncData.getSynchron())){
						scmSyncTaskInfo.setTaskStatus("C");
						scmSyncTaskInfo.setUpdateTime(new Date());
						scmSyncTaskInfo.setEndDate(new Date());
						scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
					}
				}
			}else{
				log.info("不存在需要推送的采购入库单！");
			}
		}else{
			log.info("当前管理单元未绑定需求方！");
		}
	}
	
	public void batchPushConfirmRuleToSupplierPlat(AppInfo appInfo) {
		OperationParam param = new OperationParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("supplierConfirmRulePush");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(appInfo.getControlUnitNo(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && systemTask.getSize() > 0){
			Page page=new Page();
			page.setModelClass(ScmSyncTaskInfo2.class);
			page.setShowCount(systemTask.getSize());
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKCODE, QueryParam.QUERY_EQ,"SCM_ESP"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKTYPE, QueryParam.QUERY_EQ,systemTask.getTaskType()));
			page.getParam().put(ScmSyncTaskInfo2.FN_PRODUCTCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_PRODUCTCODE, QueryParam.QUERY_EQ,"iSCM"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKOWNER,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKOWNER, QueryParam.QUERY_EQ,"ESP"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKSTATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKSTATUS, QueryParam.QUERY_EQ,"W"));
			page.getParam().put(ScmSyncTaskInfo2.FN_CONTROLUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmSyncTaskInfo2> scmSyncTaskInfoList = scmSyncTaskInfoBiz.findPage(page, param);
			if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
				int count = 0;
				for(int i=0;i<scmSyncTaskInfoList.size();i++){
					ScmSyncTaskInfo2 scmSyncTaskInfo = scmSyncTaskInfoList.get(i);
					ScmSyncData scmSyncData = scmSyncDataBiz.select(scmSyncTaskInfo.getSyncDataId(), param);
					if(scmSyncData != null && count<systemTask.getSize()){
						count++;
						try {
							scmSyncTaskInfo.setTaskStatus("D");
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							String errorMsg = pushConfirmRuleToSupplierPlat(appInfo, scmSyncData.getDataId(), scmSyncTaskInfo.getTaskSource(), param);
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncData = scmSyncDataBiz.select(scmSyncData.getId(), param);
							if(StringUtils.isBlank(errorMsg)){
								scmSyncTaskInfo.setTaskStatus("S");
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
								scmSyncData.setSynchron("Y");
								scmSyncData.setSyncTime(new Date());
								scmSyncData.setErrorMessage(null);
								scmSyncDataBiz.update(scmSyncData, param);
							}else{
								scmSyncTaskInfo.setTaskStatus("F");
								scmSyncTaskInfo.setStatusMessage(errorMsg);
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
								scmSyncData.setSynchron("F");
								scmSyncData.setErrorMessage(errorMsg);
								scmSyncDataBiz.update(scmSyncData, param);
							}
						} catch (Exception e) {
							log.error(e);
							String errorMsg = e.getMessage();
							if(StringUtils.isNotBlank(errorMsg) && errorMsg.length()>255){
								errorMsg = StringUtils.substring(errorMsg, 0, 255-1);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncData = scmSyncDataBiz.select(scmSyncData.getId(), param);
							scmSyncTaskInfo.setTaskStatus("F");
							scmSyncTaskInfo.setStatusMessage(errorMsg);
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfo.setEndDate(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							scmSyncData.setSynchron("F");
							scmSyncData.setErrorMessage(errorMsg);
							scmSyncDataBiz.update(scmSyncData, param);
							continue;
						}
					}
				}
			}else{
				log.info("不存在需要推送的确认规则！");
			}
		}else{
			log.info("当前管理单元未绑定需求方！");
		}
	}
	
	public String pushConfirmRuleToSupplierPlat(AppInfo appInfo,long confirmRuleId, String taskSource, Param param) {
		if(confirmRuleId > 0){
			ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(param.getControlUnitNo(), param);
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0){
				ScmConfirmRule scmConfirmRule = scmConfirmRuleBiz.select(confirmRuleId, param);
				if (scmConfirmRule != null) {
					SupplierPlatConfirmRule supplierPlatConfirmRule = new SupplierPlatConfirmRule();
					BeanUtils.copyProperties(scmConfirmRule, supplierPlatConfirmRule);
					supplierPlatConfirmRule.setReceiveNo(scmConfirmRule.getOrgUnitNo());
					supplierPlatConfirmRule.setBaId(scmSupplierDemander.getDemanderId());
					supplierPlatConfirmRule.setTaskSource(taskSource);
					String data = getData(appInfo, 0, "/isp/billConfirmRule/sync", JSONObject.toJSONString(supplierPlatConfirmRule));
					log.info("接口返回："+data);
					JSONObject jsonObject = JSON.parseObject(data);
					if(jsonObject != null && "0".equals(String.valueOf(jsonObject.get("errCode")))){
						return "";
					}else{
						return String.valueOf(jsonObject.get("errText"));
					}
				}else{
					log.info("未查询到确认规则！");
					return "未查询到确认规则！";
				}
			}else{
				log.info("当前管理单元未绑定需求方！");
				return "当前管理单元未绑定需求方！";
			}
		}
		return "";
	}
	
	public void eSPComTaskScan(AppInfo appInfo) {
		OperationParam param = new OperationParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		param.setUsrCode(appInfo.getUsrCode());
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("iSCMESPComTask");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		List<TaskSettingDetail2> taskSettingDetailList = taskSettingDetailBiz.selectByCtrl(param.getControlUnitNo(), param);
		if(taskSettingDetailList != null && !taskSettingDetailList.isEmpty()){
			HashMap<String, Object> map = new HashMap<>();
			for(TaskSettingDetail2 taskSettingDetail : taskSettingDetailList){
				if(!StringUtils.equalsIgnoreCase("SCM_ESP", taskSettingDetail.getTaskCode())){
					continue;
				}
				//生成同步标识子表及同步任务子表
				generateSyncTaskDataByTaskSettingDetail(taskSettingDetail, param);
				if(!map.containsKey(taskSettingDetail.getTaskCode())){
					map.put(taskSettingDetail.getTaskCode(), taskSettingDetail.getTaskDays());
				}
			}
			//清除任务
			if(map != null && !map.isEmpty()){
				List<ScmSyncTaskInfo2> scmSyncTaskInfoList = new ArrayList<>();
				for(Map.Entry<String, Object> entry:map.entrySet()){ 
					Page page=new Page();
					page.setModelClass(ScmSyncTaskInfo2.class);
					page.setShowCount(100);
					Calendar calendar = new GregorianCalendar();
			        calendar.setTime(new Date());
			        calendar.add(Calendar.DATE, -Integer.parseInt(String.valueOf(entry.getValue())));
					ArrayList argList = new ArrayList();
			        argList.add("taskCode="+entry.getKey());
			        argList.add("controlUnitNo=" + param.getControlUnitNo());
			        argList.add("productCode=" + param.getProductCode());
			        argList.add("createTime=" + FormatUtils.fmtDate(calendar.getTime()));
			        List<ScmSyncTaskInfo2> tempScmSyncTaskInfoList = scmSyncTaskInfoBiz.queryPage(page, argList, "findDeleteTaskPage", param);
					scmSyncTaskInfoList.addAll(tempScmSyncTaskInfoList); 
			    } 
				if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
					scmSyncTaskInfoBiz.delete(scmSyncTaskInfoList, param);
				}
			}
		}
	}
	
	public void generateSyncTaskDataByTaskSettingDetail(TaskSettingDetail2 taskSettingDetail, Param param) {
		generateSyncTaskDataByTaskSettingDetail(taskSettingDetail,null,param);
	}
	
	public void generateSyncTaskDataByTaskSettingDetail(TaskSettingDetail2 taskSettingDetail,List list, Param param) {
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(param.getControlUnitNo(), param);
		Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -taskSettingDetail.getMaxDays());
		ScmSystemTaskExecTime scmSystemTaskExecTime = scmSystemTaskExecTimeBiz.selectByTaskType(param.getControlUnitNo(), taskSettingDetail.getTaskObject(), param);
		switch (taskSettingDetail.getTaskObject()) {
		case "purOrderPush":
			ScmSystemTask systemTask = new ScmSystemTask(true);
			systemTask.setTaskType("purOrderPush");
			systemTask.setOrgUnitNo(param.getOrgUnitNo());
			List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
			if(systemTaskList == null || systemTaskList.isEmpty()){
				return;
			}else{
				systemTask = systemTaskList.get(0);
			}
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && systemTask.getSize() > 0){
				List<ScmSupplierRegInvitation> scmSupplierRegInvitationList = scmSupplierRegInvitationBiz.selectBindedByCtrl(param.getControlUnitNo(), param);
				if(scmSupplierRegInvitationList != null && !scmSupplierRegInvitationList.isEmpty()){
					String taskSource="";
					Map<Long, Long> map = new HashMap<>();
					List<ScmPurOrder2> scmPurOrderList=new ArrayList();
					if(list!=null && !list.isEmpty() && list.get(0) instanceof ScmPurOrder2) {
						for(ScmPurOrder2 scmPurOrder:(List<ScmPurOrder2>)list) {
							if(scmPurOrder.isChoosed())
								scmPurOrderList.add(scmPurOrder);
						}
						taskSource="B";
					}else {
						taskSource="G";
						StringBuffer vendorIds  = new StringBuffer("");
						for(ScmSupplierRegInvitation ScmSupplierRegInvitation : scmSupplierRegInvitationList){
							map.put(ScmSupplierRegInvitation.getVendorId(), ScmSupplierRegInvitation.getPlatSupplierId());
							if (StringUtils.isNotBlank(vendorIds.toString()))
								vendorIds.append(",");
							vendorIds.append(String.valueOf(ScmSupplierRegInvitation.getVendorId()));
						}
						Page page=new Page();
						page.setModelClass(ScmPurOrder2.class);
						page.setShowCount(systemTask.getSize());
						page.getParam().put(ScmPurOrder2.FN_VENDORID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." +ScmPurOrder2.FN_VENDORID, QueryParam.QUERY_IN,vendorIds.toString()));
						page.getParam().put(ScmPurOrder2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." +ScmPurOrder2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDateTime(calendar.getTime())));
						if(scmSystemTaskExecTime!=null && StringUtils.isNotBlank(scmSystemTaskExecTime.getExecTime())) {
							page.getParam().put(ScmPurOrder2.FN_UPDATETIMESTAMP,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." +ScmPurOrder2.FN_UPDATETIMESTAMP, QueryParam.QUERY_GT,scmSystemTaskExecTime.getExecTime()));
						}
						if(StringUtils.isNotBlank(taskSettingDetail.getExtendedParam3())){
							if(StringUtils.isNotBlank(taskSettingDetail.getExtendedParam3())){
								String status[] = StringUtils.split(taskSettingDetail.getExtendedParam3(), ",");
								StringBuffer statusBuffer = new StringBuffer("");
								for(String sta:status){
									if(StringUtils.isNotBlank(statusBuffer.toString()))
										statusBuffer.append(",");
									statusBuffer.append("'").append(sta).append("'");
								}
								page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_STATUS), 
										new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_STATUS), QueryParam.QUERY_IN, statusBuffer.toString()));
							}
						}
						List<String> arglist = new ArrayList();
						arglist.add("controlUnitNo="+param.getControlUnitNo());
						scmPurOrderList = scmPurOrderBiz.queryPage(page, arglist, "selectForPushPage", param);
					}
					if(scmPurOrderList != null && !scmPurOrderList.isEmpty()){
						 Collections.sort(scmPurOrderList, new Comparator<ScmPurOrder2>() {
				                @Override
				                public int compare(ScmPurOrder2 o1, ScmPurOrder2 o2) {
				                	if (o1.getUpdateTimeStamp().compareTo(o2.getUpdateTimeStamp()) > 0) {
				                        return 1;
				                	}else if (o1.getUpdateTimeStamp().compareTo(o2.getUpdateTimeStamp()) < 0) {
				                        return -1;
				                    }
				                	return 0;
				                }

				            });
						List<ScmPurOrder2> pushScmPurOrderList = new ArrayList<>();
						pushScmPurOrderList.addAll(scmPurOrderList);
						if(pushScmPurOrderList != null && !pushScmPurOrderList.isEmpty()){
							Date updateTime = null;
					        int index = pushScmPurOrderList.size()-1;
					        updateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(pushScmPurOrderList.get(index).getUpdateTimeStamp()));
							for(int i=0;i<pushScmPurOrderList.size();i++){
								ScmPurOrder2 scmPurOrder = pushScmPurOrderList.get(i);
								long platSupplierId = 0;
								if(map != null && !map.isEmpty() && map.containsKey(scmPurOrder.getVendorId())){
									platSupplierId = map.get(scmPurOrder.getVendorId());
								}else{
									if(map == null || map.isEmpty()){
										map = new HashMap<>();
									}
									ScmSupplierRegInvitation scmSupplierRegInvitation = scmSupplierRegInvitationBiz.selectByVendorIdAndCtrl(scmPurOrder.getVendorId(), param.getControlUnitNo(), param);
									if(scmSupplierRegInvitation != null){
										platSupplierId = scmSupplierRegInvitation.getPlatSupplierId();
										map.put(scmSupplierRegInvitation.getVendorId(), scmSupplierRegInvitation.getPlatSupplierId());
									}
								}
								generateSyncTaskData(taskSettingDetail,taskSource,scmPurOrder.getId(),platSupplierId,scmPurOrder.getCreateDate(),FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(scmPurOrder.getUpdateTimeStamp())),scmPurOrder.getOrgUnitNo(),"PurOrder",scmPurOrder.getPoNo(),param);
							}
							if(StringUtils.equals(taskSource, "G")) {
								//通用任务才更新时间戳，手工任务不更新时间戳
								if(scmSystemTaskExecTime==null) {
									scmSystemTaskExecTime = new ScmSystemTaskExecTime(true);
									scmSystemTaskExecTime.setOrgUnitNo(param.getControlUnitNo());
									scmSystemTaskExecTime.setTaskType(taskSettingDetail.getTaskObject());
									scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTime));
									scmSystemTaskExecTime.setControlUnitNo(param.getControlUnitNo());
									scmSystemTaskExecTimeBiz.add(scmSystemTaskExecTime, param);
								}else {
									scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTime));
									scmSystemTaskExecTimeBiz.update(scmSystemTaskExecTime, param);
								}
							}
						}
					}
					generateSyncTaskInfoBySyncData(taskSettingDetail, "PurOrder", taskSource, param);
				}
			}
			break;
		case "invPurInWarehsBillPush":
			ScmSystemTask systemTask2 = new ScmSystemTask(true);
			systemTask2.setTaskType("invPurInWarehsBillPush");
			systemTask2.setOrgUnitNo(param.getOrgUnitNo());
			List<ScmSystemTask> systemTaskList2 = scmSystemTaskBiz.selectByTask(systemTask2, param);
			if(systemTaskList2 == null || systemTaskList2.isEmpty()){
				return;
			}else{
				systemTask2 = systemTaskList2.get(0);
			}
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && systemTask2.getSize() > 0){
				List<ScmSupplierRegInvitation> scmSupplierRegInvitationList = scmSupplierRegInvitationBiz.selectBindedByCtrl(param.getControlUnitNo(), param);
				if(scmSupplierRegInvitationList != null && !scmSupplierRegInvitationList.isEmpty()){
					String taskSource="";
					Map<Long, Long> map = new HashMap<>();
					List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList = new ArrayList();
					if(list!=null && !list.isEmpty() && list.get(0) instanceof ScmInvPurInWarehsBill2) {
						for(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill:(List<ScmInvPurInWarehsBill2>)list) {
							if(scmInvPurInWarehsBill.isChoosed())
								scmInvPurInWarehsBillList.add(scmInvPurInWarehsBill);
						}
						taskSource="B";
					}else {
						taskSource="G";
						StringBuffer vendorIds  = new StringBuffer("");
						for(ScmSupplierRegInvitation scmSupplierRegInvitation : scmSupplierRegInvitationList){
							map.put(scmSupplierRegInvitation.getVendorId(), scmSupplierRegInvitation.getPlatSupplierId());
							if (StringUtils.isNotBlank(vendorIds.toString()))
								vendorIds.append(",");
							vendorIds.append(String.valueOf(scmSupplierRegInvitation.getVendorId()));
						}
						Page page=new Page();
						page.setModelClass(ScmInvPurInWarehsBill2.class);
						page.setShowCount(systemTask2.getSize());
						page.getParam().put(ScmInvPurInWarehsBill2.FN_VENDORID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_VENDORID, QueryParam.QUERY_IN,vendorIds.toString()));
						page.getParam().put(ScmInvPurInWarehsBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDateTime(calendar.getTime())));
						if(scmSystemTaskExecTime!=null && StringUtils.isNotBlank(scmSystemTaskExecTime.getExecTime())) {
							page.getParam().put(ScmInvPurInWarehsBill2.FN_UPDATETIMESTAMP,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_UPDATETIMESTAMP, QueryParam.QUERY_GT,scmSystemTaskExecTime.getExecTime()));
						}
						if(StringUtils.isNotBlank(taskSettingDetail.getExtendedParam3())){
							if(StringUtils.isNotBlank(taskSettingDetail.getExtendedParam3())){
								String status[] = StringUtils.split(taskSettingDetail.getExtendedParam3(), ",");
								StringBuffer statusBuffer = new StringBuffer("");
								for(String sta:status){
									if(StringUtils.isNotBlank(statusBuffer.toString()))
										statusBuffer.append(",");
									statusBuffer.append("'").append(sta).append("'");
								}
								page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." + ScmInvPurInWarehsBill2.FN_STATUS), 
										new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." + ScmInvPurInWarehsBill2.FN_STATUS), QueryParam.QUERY_IN, statusBuffer.toString()));
							}
						}
						List<String> arglist = new ArrayList();
						arglist.add("controlUnitNo="+param.getControlUnitNo());
						scmInvPurInWarehsBillList = scmInvPurInWarehsBillBiz.queryPage(page, arglist, "selectForPushPage", param);
					}
					if(scmInvPurInWarehsBillList != null && !scmInvPurInWarehsBillList.isEmpty()){
						 Collections.sort(scmInvPurInWarehsBillList, new Comparator<ScmInvPurInWarehsBill2>() {
				                @Override
				                public int compare(ScmInvPurInWarehsBill2 o1, ScmInvPurInWarehsBill2 o2) {
				                	if (o1.getUpdateTimeStamp().compareTo(o2.getUpdateTimeStamp()) > 0) {
				                        return 1;
				                	}else if (o1.getUpdateTimeStamp().compareTo(o2.getUpdateTimeStamp()) < 0) {
				                        return -1;
				                    }
				                	return 0;
				                }

				            });
				        List<ScmInvPurInWarehsBill2> pushScmInvPurInWarehsBillList = new ArrayList<>();
			        	pushScmInvPurInWarehsBillList.addAll(scmInvPurInWarehsBillList);
						if(pushScmInvPurInWarehsBillList != null && !pushScmInvPurInWarehsBillList.isEmpty()){
							Date updateTime = systemTask2.getUpdateTime();
					        int index = pushScmInvPurInWarehsBillList.size()-1;
					        updateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(pushScmInvPurInWarehsBillList.get(index).getUpdateTimeStamp()));
							for(int i=0;i<pushScmInvPurInWarehsBillList.size();i++){
								ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = pushScmInvPurInWarehsBillList.get(i);
								long platSupplierId = 0;
								if(map != null && !map.isEmpty() && map.containsKey(scmInvPurInWarehsBill.getVendorId())){
									platSupplierId = map.get(scmInvPurInWarehsBill.getVendorId());
								}else{
									if(map == null || map.isEmpty()){
										map = new HashMap<>();
									}
									ScmSupplierRegInvitation scmSupplierRegInvitation = scmSupplierRegInvitationBiz.selectByVendorIdAndCtrl(scmInvPurInWarehsBill.getVendorId(), param.getControlUnitNo(), param);
									if(scmSupplierRegInvitation != null){
										platSupplierId = scmSupplierRegInvitation.getPlatSupplierId();
										map.put(scmSupplierRegInvitation.getVendorId(), scmSupplierRegInvitation.getPlatSupplierId());
									}
								}
								generateSyncTaskData(taskSettingDetail,taskSource,scmInvPurInWarehsBill.getWrId(),platSupplierId,scmInvPurInWarehsBill.getCreateDate(),FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(scmInvPurInWarehsBill.getUpdateTimeStamp())),scmInvPurInWarehsBill.getOrgUnitNo(),"InvPurInWhsBill",scmInvPurInWarehsBill.getWrNo(),param);
							}
					        if(StringUtils.equals("G", taskSource)) {
					        	//通用任务才更新时间戳，手工任务不更新时间戳
								if(scmSystemTaskExecTime==null) {
									scmSystemTaskExecTime = new ScmSystemTaskExecTime(true);
									scmSystemTaskExecTime.setOrgUnitNo(param.getControlUnitNo());
									scmSystemTaskExecTime.setTaskType(taskSettingDetail.getTaskObject());
									scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTime));
									scmSystemTaskExecTime.setControlUnitNo(param.getControlUnitNo());
									scmSystemTaskExecTimeBiz.add(scmSystemTaskExecTime, param);
								}else {
									scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTime));
									scmSystemTaskExecTimeBiz.update(scmSystemTaskExecTime, param);
								}
							}
						}
					}
					generateSyncTaskInfoBySyncData(taskSettingDetail, "InvPurInWhsBill", taskSource, param);
				}
			}
			break;
		case "supplierConfirmRulePush":
			ScmSystemTask systemTask3 = new ScmSystemTask(true);
			systemTask3.setTaskType("supplierConfirmRulePush");
			systemTask3.setOrgUnitNo(param.getOrgUnitNo());
			List<ScmSystemTask> systemTaskList3 = scmSystemTaskBiz.selectByTask(systemTask3, param);
			if(systemTaskList3 == null || systemTaskList3.isEmpty()){
				return;
			}else{
				systemTask3 = systemTaskList3.get(0);
			}
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0){
				Page page=new Page();
				page.setModelClass(ScmConfirmRule.class);
				page.setShowCount(Integer.MAX_VALUE);
				page.getParam().put(ScmConfirmRule.FN_CONTROLUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmConfirmRule.class) + "." +ScmConfirmRule.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
				page.setSqlCondition("date_format(ScmConfirmRule.updateTimeStamp,'%Y-%m-%d %T')>='"+FormatUtils.fmtDateTimeMS(systemTask3.getUpdateTime())+"'");
				if(scmSystemTaskExecTime!=null && StringUtils.isNotBlank(scmSystemTaskExecTime.getExecTime())) {
					page.getParam().put(ScmConfirmRule.FN_UPDATETIMESTAMP,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmConfirmRule.class) + "." +ScmConfirmRule.FN_UPDATETIMESTAMP, QueryParam.QUERY_GT,scmSystemTaskExecTime.getExecTime()));
				}
				List<String> arglist = new ArrayList();
				arglist.add("controlUnitNo="+param.getControlUnitNo());
				List<ScmConfirmRule> scmConfirmRuleList = scmConfirmRuleBiz.queryPage(page, arglist, "selectForPushPage", param);
				if(scmConfirmRuleList != null && !scmConfirmRuleList.isEmpty()){
					Collections.sort(scmConfirmRuleList, new Comparator<ScmConfirmRule>() {
		                @Override
		                public int compare(ScmConfirmRule o1, ScmConfirmRule o2) {
		                	if (o1.getUpdateTimeStamp().compareTo(o2.getUpdateTimeStamp()) > 0) {
		                        return 1;
		                	}else if (o1.getUpdateTimeStamp().compareTo(o2.getUpdateTimeStamp()) < 0) {
		                        return -1;
		                    }
		                	return 0;
		                }

		            });
			        Date updateTime = scmConfirmRuleList.get(scmConfirmRuleList.size()-1).getUpdateTimeStamp();
					for(int i=0;i<scmConfirmRuleList.size();i++){
						ScmConfirmRule scmConfirmRule = scmConfirmRuleList.get(i);
						generateSyncTaskData(taskSettingDetail,"G",scmConfirmRule.getId(),0,null,updateTime,scmConfirmRule.getOrgUnitNo(),scmConfirmRule.getBillType(),"",param);
					}
		        	//通用任务才更新时间戳，手工任务不更新时间戳
					if(scmSystemTaskExecTime==null) {
						scmSystemTaskExecTime = new ScmSystemTaskExecTime(true);
						scmSystemTaskExecTime.setOrgUnitNo(param.getControlUnitNo());
						scmSystemTaskExecTime.setTaskType(taskSettingDetail.getTaskObject());
						scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTime));
						scmSystemTaskExecTime.setControlUnitNo(param.getControlUnitNo());
						scmSystemTaskExecTimeBiz.add(scmSystemTaskExecTime, param);
					}else {
						scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTime));
						scmSystemTaskExecTimeBiz.update(scmSystemTaskExecTime, param);
					}
				}
				generateSyncTaskInfoBySyncData(taskSettingDetail, "", "G", param);
			}
			break;
		case "supplierRegInfoGet":
			ScmSystemTask systemTask4 = new ScmSystemTask(true);
			systemTask4.setTaskType("supplierRegInfoGet");
			systemTask4.setOrgUnitNo(param.getOrgUnitNo());
			List<ScmSystemTask> systemTaskList4 = scmSystemTaskBiz.selectByTask(systemTask4, param);
			if(systemTaskList4 == null || systemTaskList4.isEmpty()){
				return;
			}else{
				systemTask4 = systemTaskList4.get(0);
			}
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0){
				Date updateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(systemTask4.getUpdateTime()));
				generateSyncTaskInfo(taskSettingDetail,updateTime,"G",param);
			}
			break;
		case "supplierConfirmDataGet":
			ScmSystemTask systemTask5 = new ScmSystemTask(true);
			systemTask5.setTaskType("supplierConfirmDataGet");
			systemTask5.setOrgUnitNo(param.getOrgUnitNo());
			List<ScmSystemTask> systemTaskList5 = scmSystemTaskBiz.selectByTask(systemTask5, param);
			if(systemTaskList5 == null || systemTaskList5.isEmpty()){
				return;
			}else{
				systemTask5 = systemTaskList5.get(0);
			}
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0){
				Date updateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(systemTask5.getUpdateTime()));
				generateSyncTaskInfo(taskSettingDetail,updateTime,"G",param);
				List<ScmSupplierReplyData2> scmSupplierReplyDataList =  scmSupplierReplyDataBiz.selectPendingPushByCtrl(0, param.getControlUnitNo(),"",param);
				if(scmSupplierReplyDataList != null && !scmSupplierReplyDataList.isEmpty()){
					for(ScmSupplierReplyData2 scmSupplierReplyData : scmSupplierReplyDataList){
						ScmSyncData scmSyncData = new ScmSyncData(true);
						scmSyncData.setOrgUnitNo(scmSupplierReplyData.getOrgUnitNo());
						scmSyncData.setPlatSupplierId(scmSupplierReplyData.getPlatSupplierId());
						scmSyncData.setDataClass("Msg");
						scmSyncData.setDataId(scmSupplierReplyData.getId());
						scmSyncData.setBillType(scmSupplierReplyData.getBillType());
						scmSyncData.setBillNo(scmSupplierReplyData.getRefBillNo());
						scmSyncData.setCreateDate(scmSupplierReplyData.getCreateDate());
						scmSyncData.setTaskCode(taskSettingDetail.getTaskCode());
						scmSyncData.setControlUnitNo(param.getControlUnitNo());
						ScmSyncData scmSyncData2 = scmSyncDataBiz.selectByScmSyncData(scmSyncData, param);
						if(scmSyncData2 == null){
							scmSyncData.setSyncType("add");
							scmSyncData.setTaskCount(0);
							scmSyncData.setSynchron("N");
							scmSyncData.setUpdateTime(scmSupplierReplyData.getUpdateTimeStamp());
							scmSyncDataBiz.add(scmSyncData, param);
						}
					}
					taskSettingDetail.setDataClass("Msg");
					taskSettingDetail.setInteractionMode("Push");
					taskSettingDetail.setTaskObject("supplierReplyDataPush");
					generateSyncTaskInfoBySyncData(taskSettingDetail, "", "G", param);
				}
			}
			break;
		case "purPricePush":
			ScmSystemTask systemTask6 = new ScmSystemTask(true);
			systemTask6.setTaskType("purPricePush");
			systemTask6.setOrgUnitNo(param.getOrgUnitNo());
			List<ScmSystemTask> systemTaskList6 = scmSystemTaskBiz.selectByTask(systemTask6, param);
			if(systemTaskList6 == null || systemTaskList6.isEmpty()){
				return;
			}else{
				systemTask6 = systemTaskList6.get(0);
			}
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && systemTask6.getSize() > 0){
				List<ScmSupplierRegInvitation> scmSupplierRegInvitationList = scmSupplierRegInvitationBiz.selectBindedByCtrl(param.getControlUnitNo(), param);
				if(scmSupplierRegInvitationList != null && !scmSupplierRegInvitationList.isEmpty()){
					String taskSource="";
					List<ScmPurPrice2> scmPurPriceList = new ArrayList();
					if(list!=null && !list.isEmpty() && list.get(0) instanceof ScmPurPrice2) {
					}else {
						taskSource="G";
						StringBuffer vendorIds  = new StringBuffer("");
						for(ScmSupplierRegInvitation scmSupplierRegInvitation : scmSupplierRegInvitationList){
							if (StringUtils.isNotBlank(vendorIds.toString()))
								vendorIds.append(",");
							vendorIds.append(String.valueOf(scmSupplierRegInvitation.getVendorId()));
						}
						Page page=new Page();
						page.setModelClass(ScmPurPrice2.class);
						page.setShowCount(systemTask6.getSize());
						page.getParam().put(ScmPurPrice2.FN_BUSINESSAUTOQUOTATION,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class) + "." +ScmPurPrice2.FN_BUSINESSAUTOQUOTATION, QueryParam.QUERY_EQ,"1"));
						//page.getParam().put(ScmPurPrice2.FN_QUOTATIONEXPIRYDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class) + "." +ScmPurPrice2.FN_QUOTATIONEXPIRYDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(new Date())));
						page.getParam().put(ScmPurPrice2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class) + "." +ScmPurPrice2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDateTime(calendar.getTime())));
						if(scmSystemTaskExecTime!=null && StringUtils.isNotBlank(scmSystemTaskExecTime.getExecTime())) {
							page.getParam().put(ScmPurPrice2.FN_UPDATETIMESTAMP,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class) + "." +ScmPurPrice2.FN_UPDATETIMESTAMP, QueryParam.QUERY_GT,scmSystemTaskExecTime.getExecTime()));
						}
						page.setSqlCondition("(ScmPurPrice.vendorId1 in ("+vendorIds.toString()+") or ScmPurPrice.vendorId2 in ("+vendorIds.toString()+") or ScmPurPrice.vendorId3 in ("+vendorIds.toString()+"))");
						if(StringUtils.isNotBlank(taskSettingDetail.getExtendedParam3())){
							if(StringUtils.isNotBlank(taskSettingDetail.getExtendedParam3())){
								String status[] = StringUtils.split(taskSettingDetail.getExtendedParam3(), ",");
								StringBuffer statusBuffer = new StringBuffer("");
								for(String sta:status){
									if(StringUtils.isNotBlank(statusBuffer.toString()))
										statusBuffer.append(",");
									statusBuffer.append("'").append(sta).append("'");
								}
								page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class) + "." + ScmPurPrice2.FN_STATUS), 
										new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurPrice2.class) + "." + ScmPurPrice2.FN_STATUS), QueryParam.QUERY_IN, statusBuffer.toString()));
							}
						}
						List<String> arglist = new ArrayList();
						arglist.add("controlUnitNo="+param.getControlUnitNo());
						scmPurPriceList = scmPurPriceBiz.queryPage(page, arglist, "selectForPushPage", param);
					}
					if(scmPurPriceList != null && !scmPurPriceList.isEmpty()){
						 Collections.sort(scmPurPriceList, new Comparator<ScmPurPrice2>() {
				                @Override
				                public int compare(ScmPurPrice2 o1, ScmPurPrice2 o2) {
				                	if (o1.getUpdateTimeStamp().compareTo(o2.getUpdateTimeStamp()) > 0) {
				                        return 1;
				                	}else if (o1.getUpdateTimeStamp().compareTo(o2.getUpdateTimeStamp()) < 0) {
				                        return -1;
				                    }
				                	return 0;
				                }

				            });
				        List<ScmPurPrice2> pushScmPurPriceList = new ArrayList<>();
				        pushScmPurPriceList.addAll(scmPurPriceList);
						if(pushScmPurPriceList != null && !pushScmPurPriceList.isEmpty()){
							Date updateTime = systemTask6.getUpdateTime();
					        int index = pushScmPurPriceList.size()-1;
					        updateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(pushScmPurPriceList.get(index).getUpdateTimeStamp()));
							for(int i=0;i<pushScmPurPriceList.size();i++){
								ScmPurPrice2 scmPurPrice = pushScmPurPriceList.get(i);
								long platSupplierId = 0;
								generateSyncTaskData(taskSettingDetail,taskSource,scmPurPrice.getId(),platSupplierId,scmPurPrice.getCreateDate(),FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(scmPurPrice.getUpdateTimeStamp())),scmPurPrice.getOrgUnitNo(),"ScmPurPrice",scmPurPrice.getPmNo(),param);
							}
					        if(StringUtils.equals("G", taskSource)) {
					        	//通用任务才更新时间戳，手工任务不更新时间戳
								if(scmSystemTaskExecTime==null) {
									scmSystemTaskExecTime = new ScmSystemTaskExecTime(true);
									scmSystemTaskExecTime.setOrgUnitNo(param.getControlUnitNo());
									scmSystemTaskExecTime.setTaskType(taskSettingDetail.getTaskObject());
									scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTime));
									scmSystemTaskExecTime.setControlUnitNo(param.getControlUnitNo());
									scmSystemTaskExecTimeBiz.add(scmSystemTaskExecTime, param);
								}else {
									scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTime));
									scmSystemTaskExecTimeBiz.update(scmSystemTaskExecTime, param);
								}
							}
						}
					}
					generateSyncTaskInfoBySyncData(taskSettingDetail, "ScmPurPrice", taskSource, param);
				}
			}
			break;
		case "businessQuotationGet": 
			ScmSystemTask systemTask7 = new ScmSystemTask(true);
			systemTask7.setTaskType("businessQuotationGet");
			systemTask7.setOrgUnitNo(param.getOrgUnitNo());
			List<ScmSystemTask> systemTaskList7 = scmSystemTaskBiz.selectByTask(systemTask7, param);
			if(systemTaskList7 == null || systemTaskList7.isEmpty()){
				return;
			}else{
				systemTask7 = systemTaskList7.get(0);
			}
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0){
				Date updateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(systemTask7.getUpdateTime()));
				generateSyncTaskInfo(taskSettingDetail,updateTime,"G",param);
			}
			break;
		case "industryQualifyPush":
			ScmSystemTask industryQualifyPushSystemTask = new ScmSystemTask(true);
			industryQualifyPushSystemTask.setTaskType("industryQualifyPush");
			industryQualifyPushSystemTask.setOrgUnitNo(param.getOrgUnitNo());
			List<ScmSystemTask> industryQualifyPushSystemTaskList = scmSystemTaskBiz.selectByTask(industryQualifyPushSystemTask, param);
			if(industryQualifyPushSystemTaskList == null || industryQualifyPushSystemTaskList.isEmpty()){
				return;
			}else{
				industryQualifyPushSystemTask = industryQualifyPushSystemTaskList.get(0);
			}
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && industryQualifyPushSystemTask.getSize() > 0){
				String taskSource="";
				List<ScmIndustryGroup> scmIndustryGroupList = new ArrayList();
				if(list!=null && !list.isEmpty() && list.get(0) instanceof ScmIndustryGroup) {

				}else {
					taskSource="G";
					Page page=new Page();
					page.setModelClass(ScmIndustryGroup.class);
					page.setShowCount(industryQualifyPushSystemTask.getSize());
					page.getParam().put(ScmIndustryGroup.FN_FLAG,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmIndustryGroup.class) + "." +ScmIndustryGroup.FN_FLAG, QueryParam.QUERY_EQ,"1"));
					if(scmSystemTaskExecTime!=null && StringUtils.isNotBlank(scmSystemTaskExecTime.getExecTime())) {
						page.getParam().put(ScmIndustryGroup.FN_UPDATETIMESTAMP,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmIndustryGroup.class) + "." +ScmIndustryGroup.FN_UPDATETIMESTAMP, QueryParam.QUERY_GT,scmSystemTaskExecTime.getExecTime()));
					}
					List<String> arglist = new ArrayList();
					arglist.add("controlUnitNo="+param.getControlUnitNo());
					scmIndustryGroupList = scmIndustryGroupBiz.queryPage(page, arglist, "selectForPushPage", param);
				}
				if(scmIndustryGroupList != null && !scmIndustryGroupList.isEmpty()){
					 Collections.sort(scmIndustryGroupList, new Comparator<ScmIndustryGroup>() {
			                @Override
			                public int compare(ScmIndustryGroup o1, ScmIndustryGroup o2) {
			                	if (o1.getUpdateTimeStamp().compareTo(o2.getUpdateTimeStamp()) > 0) {
			                        return 1;
			                	}else if (o1.getUpdateTimeStamp().compareTo(o2.getUpdateTimeStamp()) < 0) {
			                        return -1;
			                    }
			                	return 0;
			                }

			            });
			        List<ScmIndustryGroup> pushScmIndustryGrouList = new ArrayList<>();
			        pushScmIndustryGrouList.addAll(scmIndustryGroupList);
					if(pushScmIndustryGrouList != null && !pushScmIndustryGrouList.isEmpty()){
						Date updateTime = industryQualifyPushSystemTask.getUpdateTime();
				        int index = pushScmIndustryGrouList.size()-1;
				        updateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(pushScmIndustryGrouList.get(index).getUpdateTimeStamp()));
						for(int i=0;i<pushScmIndustryGrouList.size();i++){
							ScmIndustryGroup scmIndustryGroup = pushScmIndustryGrouList.get(i);
							long platSupplierId = 0;
							generateSyncTaskData(taskSettingDetail,taskSource,scmIndustryGroup.getId(),platSupplierId,scmIndustryGroup.getCreateDate(),FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(scmIndustryGroup.getUpdateTimeStamp())),scmIndustryGroup.getControlUnitNo(),"ScmIndustryGroup",scmIndustryGroup.getClassCode(),param);
						}
				        if(StringUtils.equals("G", taskSource)) {
				        	//通用任务才更新时间戳，手工任务不更新时间戳
							if(scmSystemTaskExecTime==null) {
								scmSystemTaskExecTime = new ScmSystemTaskExecTime(true);
								scmSystemTaskExecTime.setOrgUnitNo(param.getControlUnitNo());
								scmSystemTaskExecTime.setTaskType(taskSettingDetail.getTaskObject());
								scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTime));
								scmSystemTaskExecTime.setControlUnitNo(param.getControlUnitNo());
								scmSystemTaskExecTimeBiz.add(scmSystemTaskExecTime, param);
							}else {
								scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTime));
								scmSystemTaskExecTimeBiz.update(scmSystemTaskExecTime, param);
							}
						}
					}
				}
				generateSyncTaskInfoBySyncData(taskSettingDetail, "ScmIndustryGroup", taskSource, param);
			}
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && industryQualifyPushSystemTask.getSize() > 0){
				String taskSource="";
				taskSettingDetail.setTaskObject("supplierEventPush");
				ScmSystemTaskExecTime myScmSystemTaskExecTime = scmSystemTaskExecTimeBiz.selectByTaskType(param.getControlUnitNo(), taskSettingDetail.getTaskObject(), param);
				List<ScmSupplierEvent2> scmSupplierEventList = new ArrayList();
				taskSource="G";
				Page page=new Page();
				page.setModelClass(ScmSupplierEvent2.class);
				page.setShowCount(industryQualifyPushSystemTask.getSize());
				page.getParam().put(ScmSupplierEvent2.FN_FLAG,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSupplierEvent2.class) + "." +ScmSupplierEvent2.FN_FLAG, QueryParam.QUERY_EQ,"1"));
				if(myScmSystemTaskExecTime!=null && StringUtils.isNotBlank(myScmSystemTaskExecTime.getExecTime())) {
					page.getParam().put(ScmSupplierEvent2.FN_UPDATETIMESTAMP,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSupplierEvent2.class) + "." +ScmSupplierEvent2.FN_UPDATETIMESTAMP, QueryParam.QUERY_GT,myScmSystemTaskExecTime.getExecTime()));
				}
				List<String> arglist = new ArrayList();
				arglist.add("controlUnitNo="+param.getControlUnitNo());
				scmSupplierEventList = scmSupplierEventBiz.queryPage(page, arglist, "selectForPushPage", param);
				if(scmSupplierEventList != null && !scmSupplierEventList.isEmpty()){
					 Collections.sort(scmSupplierEventList, new Comparator<ScmSupplierEvent2>() {
			                @Override
			                public int compare(ScmSupplierEvent2 o1, ScmSupplierEvent2 o2) {
			                	if (o1.getUpdateTimeStamp().compareTo(o2.getUpdateTimeStamp()) > 0) {
			                        return 1;
			                	}else if (o1.getUpdateTimeStamp().compareTo(o2.getUpdateTimeStamp()) < 0) {
			                        return -1;
			                    }
			                	return 0;
			                }

			            });
			        List<ScmSupplierEvent2> pushScmSupplierEventList = new ArrayList<>();
			        pushScmSupplierEventList.addAll(scmSupplierEventList);
					if(pushScmSupplierEventList != null && !pushScmSupplierEventList.isEmpty()){
						Date updateTime = industryQualifyPushSystemTask.getUpdateTime();
				        int index = pushScmSupplierEventList.size()-1;
				        updateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(pushScmSupplierEventList.get(index).getUpdateTimeStamp()));
						for(int i=0;i<pushScmSupplierEventList.size();i++){
							ScmSupplierEvent2 scmSupplierEvent = pushScmSupplierEventList.get(i);
							long platSupplierId = 0;
							generateSyncTaskData(taskSettingDetail,taskSource,scmSupplierEvent.getId(),platSupplierId,new Date(),FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(scmSupplierEvent.getUpdateTimeStamp())),scmSupplierEvent.getControlUnitNo(),"ScmSupplierEvent",scmSupplierEvent.getEventCode(),param);
						}
				        if(StringUtils.equals("G", taskSource)) {
				        	//通用任务才更新时间戳，手工任务不更新时间戳
							if(myScmSystemTaskExecTime==null) {
								myScmSystemTaskExecTime = new ScmSystemTaskExecTime(true);
								myScmSystemTaskExecTime.setOrgUnitNo(param.getControlUnitNo());
								myScmSystemTaskExecTime.setTaskType(taskSettingDetail.getTaskObject());
								myScmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTime));
								myScmSystemTaskExecTime.setControlUnitNo(param.getControlUnitNo());
								scmSystemTaskExecTimeBiz.add(myScmSystemTaskExecTime, param);
							}else {
								myScmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTime));
								scmSystemTaskExecTimeBiz.update(myScmSystemTaskExecTime, param);
							}
						}
					}
				}
				generateSyncTaskInfoBySyncData(taskSettingDetail, "ScmSupplierEvent", taskSource, param);
			}
			break;
		case "qualificationInfoPull": 
			ScmSystemTask qualificationInfoPullSystemTask = new ScmSystemTask(true);
			qualificationInfoPullSystemTask.setTaskType("qualificationInfoPull");
			qualificationInfoPullSystemTask.setOrgUnitNo(param.getOrgUnitNo());
			List<ScmSystemTask> qualificationInfoPullSystemTaskList = scmSystemTaskBiz.selectByTask(qualificationInfoPullSystemTask, param);
			if(qualificationInfoPullSystemTaskList == null || qualificationInfoPullSystemTaskList.isEmpty()){
				return;
			}else{
				qualificationInfoPullSystemTask = qualificationInfoPullSystemTaskList.get(0);
			}
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0){
				Date updateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(qualificationInfoPullSystemTask.getUpdateTime()));
				generateSyncTaskInfo(taskSettingDetail,updateTime,"G",param);
			}
			break;
		case "qualificationInfoPush":
			ScmSystemTask qualificationInfoPushSystemTask = new ScmSystemTask(true);
			qualificationInfoPushSystemTask.setTaskType("qualificationInfoPush");
			qualificationInfoPushSystemTask.setOrgUnitNo(param.getOrgUnitNo());
			List<ScmSystemTask> qualificationInfoPushSystemTaskList = scmSystemTaskBiz.selectByTask(qualificationInfoPushSystemTask, param);
			if(qualificationInfoPushSystemTaskList == null || qualificationInfoPushSystemTaskList.isEmpty()){
				return;
			}else{
				qualificationInfoPushSystemTask = qualificationInfoPushSystemTaskList.get(0);
			}
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && qualificationInfoPushSystemTask.getSize() > 0){
				List<ScmSupplierRegInvitation> scmSupplierRegInvitationList = scmSupplierRegInvitationBiz.selectBindedByCtrl(param.getControlUnitNo(), param);
				if(scmSupplierRegInvitationList != null && !scmSupplierRegInvitationList.isEmpty()){
					String taskSource="";
					List<ScmQualifieInfo2> scmQualifieInfoList = new ArrayList();
					if(list!=null && !list.isEmpty() && list.get(0) instanceof ScmQualifieInfo2) {

					}else {
						taskSource="G";
						StringBuffer vendorIds  = new StringBuffer("");
						for(ScmSupplierRegInvitation scmSupplierRegInvitation : scmSupplierRegInvitationList){
							if (StringUtils.isNotBlank(vendorIds.toString()))
								vendorIds.append(",");
							vendorIds.append(String.valueOf(scmSupplierRegInvitation.getVendorId()));
						}
						Page page=new Page();
						page.setModelClass(ScmQualifieInfo2.class);
						page.setShowCount(qualificationInfoPushSystemTask.getSize());
						if(scmSystemTaskExecTime!=null && StringUtils.isNotBlank(scmSystemTaskExecTime.getExecTime())) {
							page.getParam().put(ScmQualifieInfo2.FN_UPDATETIMESTAMP,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmQualifieInfo2.class) + "." +ScmQualifieInfo2.FN_UPDATETIMESTAMP, QueryParam.QUERY_GT,scmSystemTaskExecTime.getExecTime()));
						}
						page.getParam().put(ScmQualifieInfo2.FN_QUALIFICATIONSTATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmQualifieInfo2.class) + "." +ScmQualifieInfo2.FN_QUALIFICATIONSTATUS, QueryParam.QUERY_IN,"'O','RJ','N'"));
						page.getParam().put(ScmQualifieInfo2.FN_VENDORID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmQualifieInfo2.class) + "." +ScmQualifieInfo2.FN_VENDORID, QueryParam.QUERY_IN,vendorIds.toString()));
						List<String> arglist = new ArrayList();
						arglist.add("controlUnitNo="+param.getControlUnitNo());
						scmQualifieInfoList = scmQualifieInfoBiz.queryPage(page, arglist, "selectForPushPage", param);
					}
					if(scmQualifieInfoList != null && !scmQualifieInfoList.isEmpty()){
						 Collections.sort(scmQualifieInfoList, new Comparator<ScmQualifieInfo2>() {
				                @Override
				                public int compare(ScmQualifieInfo2 o1, ScmQualifieInfo2 o2) {
				                	if (o1.getUpdateTimeStamp().compareTo(o2.getUpdateTimeStamp()) > 0) {
				                        return 1;
				                	}else if (o1.getUpdateTimeStamp().compareTo(o2.getUpdateTimeStamp()) < 0) {
				                        return -1;
				                    }
				                	return 0;
				                }

				            });
				        List<ScmQualifieInfo2> pushScmQualifieInfoList = new ArrayList<>();
				        pushScmQualifieInfoList.addAll(scmQualifieInfoList);
						if(pushScmQualifieInfoList != null && !pushScmQualifieInfoList.isEmpty()){
							Map<Long, Long> map = new HashMap<>();
							Date updateTime = qualificationInfoPushSystemTask.getUpdateTime();
					        int index = pushScmQualifieInfoList.size()-1;
					        updateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(pushScmQualifieInfoList.get(index).getUpdateTimeStamp()));
							for(int i=0;i<pushScmQualifieInfoList.size();i++){
								ScmQualifieInfo2 scmQualifieInfo = pushScmQualifieInfoList.get(i);
								long platSupplierId = 0;
								if(map != null && !map.isEmpty() && map.containsKey(scmQualifieInfo.getVendorId())){
									platSupplierId = map.get(scmQualifieInfo.getVendorId());
								}else{
									if(map == null || map.isEmpty()){
										map = new HashMap<>();
									}
									ScmSupplierRegInvitation scmSupplierRegInvitation = scmSupplierRegInvitationBiz.selectByVendorIdAndCtrl(scmQualifieInfo.getVendorId(), param.getControlUnitNo(), param);
									if(scmSupplierRegInvitation != null){
										platSupplierId = scmSupplierRegInvitation.getPlatSupplierId();
										map.put(scmSupplierRegInvitation.getVendorId(), scmSupplierRegInvitation.getPlatSupplierId());
									}
								}
								generateSyncTaskData(taskSettingDetail,taskSource,scmQualifieInfo.getId(),platSupplierId,scmQualifieInfo.getCreateDate(),FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(scmQualifieInfo.getUpdateTimeStamp())),scmQualifieInfo.getControlUnitNo(),"ScmQualifieInfo",String.valueOf(scmQualifieInfo.getId()),param);
							}
					        if(StringUtils.equals("G", taskSource)) {
					        	//通用任务才更新时间戳，手工任务不更新时间戳
								if(scmSystemTaskExecTime==null) {
									scmSystemTaskExecTime = new ScmSystemTaskExecTime(true);
									scmSystemTaskExecTime.setOrgUnitNo(param.getControlUnitNo());
									scmSystemTaskExecTime.setTaskType(taskSettingDetail.getTaskObject());
									scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTime));
									scmSystemTaskExecTime.setControlUnitNo(param.getControlUnitNo());
									scmSystemTaskExecTimeBiz.add(scmSystemTaskExecTime, param);
								}else {
									scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTime));
									scmSystemTaskExecTimeBiz.update(scmSystemTaskExecTime, param);
								}
							}
						}
					}
					generateSyncTaskInfoBySyncData(taskSettingDetail, "ScmQualifieInfo", taskSource, param);
				}
			}
			break;
		case "supplierStatusPush":
			ScmSystemTask supplierStatusPushSystemTask = new ScmSystemTask(true);
			supplierStatusPushSystemTask.setTaskType("qualificationInfoPush");
			supplierStatusPushSystemTask.setOrgUnitNo(param.getOrgUnitNo());
			List<ScmSystemTask> supplierStatusPushSystemTaskList = scmSystemTaskBiz.selectByTask(supplierStatusPushSystemTask, param);
			if(supplierStatusPushSystemTaskList == null || supplierStatusPushSystemTaskList.isEmpty()){
				return;
			}else{
				supplierStatusPushSystemTask = supplierStatusPushSystemTaskList.get(0);
			}
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && supplierStatusPushSystemTask.getSize() > 0){
				List<ScmSupplierRegInvitation> scmSupplierRegInvitationList = scmSupplierRegInvitationBiz.selectBindedByCtrl(param.getControlUnitNo(), param);
				if(scmSupplierRegInvitationList != null && !scmSupplierRegInvitationList.isEmpty()){
					String taskSource="";
					List<Scmsupplier2> scmsupplierList = new ArrayList();
					if(list!=null && !list.isEmpty() && list.get(0) instanceof Scmsupplier2) {

					}else {
						taskSource="G";
						StringBuffer vendorIds  = new StringBuffer("");
						for(ScmSupplierRegInvitation scmSupplierRegInvitation : scmSupplierRegInvitationList){
							if (StringUtils.isNotBlank(vendorIds.toString()))
								vendorIds.append(",");
							vendorIds.append(String.valueOf(scmSupplierRegInvitation.getVendorId()));
						}
						Page page=new Page();
						page.setModelClass(Scmsupplier2.class);
						page.setShowCount(supplierStatusPushSystemTask.getSize());
						if(scmSystemTaskExecTime!=null && StringUtils.isNotBlank(scmSystemTaskExecTime.getExecTime())) {
							page.getParam().put(Scmsupplier2.FN_UPDATETIMESTAMP,new QueryParam(ClassUtils.getFinalModelSimpleName(Scmsupplier2.class) + "." +Scmsupplier2.FN_UPDATETIMESTAMP, QueryParam.QUERY_GT,scmSystemTaskExecTime.getExecTime()));
						}
						page.getParam().put(Scmsupplier2.FN_ID,new QueryParam(ClassUtils.getFinalModelSimpleName(Scmsupplier2.class) + "." +Scmsupplier2.FN_ID, QueryParam.QUERY_IN,vendorIds.toString()));
						page.getParam().put(Scmsupplier2.FN_STATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(Scmsupplier2.class) + "." +Scmsupplier2.FN_STATUS, QueryParam.QUERY_IN,"'N','A','R'"));
						page.getParam().put(Scmsupplier2.FN_QUALIFICATIONSTATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(Scmsupplier2.class) + "." +Scmsupplier2.FN_QUALIFICATIONSTATUS, QueryParam.QUERY_IN,"'O','RJ','N'"));
						List<String> arglist = new ArrayList();
						arglist.add("controlUnitNo="+param.getControlUnitNo());
						scmsupplierList = scmsupplierBiz.queryPage(page, arglist, "selectForPushPage", param);
					}
					if(scmsupplierList != null && !scmsupplierList.isEmpty()){
						 Collections.sort(scmsupplierList, new Comparator<Scmsupplier2>() {
				                @Override
				                public int compare(Scmsupplier2 o1, Scmsupplier2 o2) {
				                	if (o1.getUpdateTimeStamp().compareTo(o2.getUpdateTimeStamp()) > 0) {
				                        return 1;
				                	}else if (o1.getUpdateTimeStamp().compareTo(o2.getUpdateTimeStamp()) < 0) {
				                        return -1;
				                    }
				                	return 0;
				                }

				            });
				        List<Scmsupplier2> pushScmQualifieInfoList = new ArrayList<>();
				        pushScmQualifieInfoList.addAll(scmsupplierList);
						if(pushScmQualifieInfoList != null && !pushScmQualifieInfoList.isEmpty()){
							Date updateTime = supplierStatusPushSystemTask.getUpdateTime();
					        int index = pushScmQualifieInfoList.size()-1;
					        updateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(pushScmQualifieInfoList.get(index).getUpdateTimeStamp()));
							for(int i=0;i<pushScmQualifieInfoList.size();i++){
								Scmsupplier2 scmsupplier = pushScmQualifieInfoList.get(i);
								long platSupplierId = 0;
								generateSyncTaskData(taskSettingDetail,taskSource,scmsupplier.getId(),platSupplierId,scmsupplier.getCreateDate(),FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(scmsupplier.getUpdateTimeStamp())),scmsupplier.getControlUnitNo(),"ScmSupplier",scmsupplier.getVendorNo(),param);
							}
					        if(StringUtils.equals("G", taskSource)) {
					        	//通用任务才更新时间戳，手工任务不更新时间戳
								if(scmSystemTaskExecTime==null) {
									scmSystemTaskExecTime = new ScmSystemTaskExecTime(true);
									scmSystemTaskExecTime.setOrgUnitNo(param.getControlUnitNo());
									scmSystemTaskExecTime.setTaskType(taskSettingDetail.getTaskObject());
									scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTime));
									scmSystemTaskExecTime.setControlUnitNo(param.getControlUnitNo());
									scmSystemTaskExecTimeBiz.add(scmSystemTaskExecTime, param);
								}else {
									scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTime));
									scmSystemTaskExecTimeBiz.update(scmSystemTaskExecTime, param);
								}
							}
						}
					}
					generateSyncTaskInfoBySyncData(taskSettingDetail, "ScmSupplier", taskSource, param);
				}
			}
			break;
		case "controlUnitPush":
			ScmSystemTask controlUnitPushSystemTask = new ScmSystemTask(true);
			controlUnitPushSystemTask.setTaskType("controlUnitPush");
			controlUnitPushSystemTask.setOrgUnitNo(param.getOrgUnitNo());
			List<ScmSystemTask> controlUnitPushSystemTaskList = scmSystemTaskBiz.selectByTask(controlUnitPushSystemTask, param);
			if(controlUnitPushSystemTaskList == null || controlUnitPushSystemTaskList.isEmpty()){
				return;
			}else{
				controlUnitPushSystemTask = controlUnitPushSystemTaskList.get(0);
			}
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0){
				Date updateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(controlUnitPushSystemTask.getUpdateTime()));
				generateSyncTaskInfo(taskSettingDetail,updateTime,"G",param);
			}
			break;
		default:
			break;
		}
	}
	
	private void generateSyncTaskData(TaskSettingDetail2 taskSettingDetail,String taskSource,long billId,long platSupplierId,Date createDate,Date updateTimeStamp,String orgUnitNo,String billType,String billNo,Param param){
		//生成同步标识子表
		ScmSyncData scmSyncData = new ScmSyncData(true);
		scmSyncData.setOrgUnitNo(orgUnitNo);
		scmSyncData.setPlatSupplierId(platSupplierId);
		scmSyncData.setDataClass(taskSettingDetail.getDataClass());
		scmSyncData.setDataId(billId);
		scmSyncData.setBillType(billType);
		scmSyncData.setBillNo(billNo);
		scmSyncData.setCreateDate(createDate);
		scmSyncData.setTaskCode(taskSettingDetail.getTaskCode());
		scmSyncData.setControlUnitNo(param.getControlUnitNo());
		ScmSyncData scmSyncData2 = scmSyncDataBiz.selectByScmSyncData(scmSyncData, param);
		if(scmSyncData2 == null){
			scmSyncData.setSyncType("add");
			scmSyncData.setTaskCount(0);
			scmSyncData.setSynchron("N");
			scmSyncDataBiz.add(scmSyncData, param);
		}else {
			if(StringUtils.equals("G", taskSource)) {
				scmSyncData2.setSyncType("edit");
				scmSyncData2.setSynchron("N");
				if(scmSyncData2.getPlatSupplierId() != platSupplierId && platSupplierId > 0){
					scmSyncData2.setPlatSupplierId(platSupplierId);
				}
				if(!StringUtils.equalsIgnoreCase(scmSyncData2.getBillNo(), billNo)
						&& StringUtils.isNotBlank(billNo)){
					scmSyncData2.setBillNo(billNo);
				}
				scmSyncData2.setTaskCount(scmSyncData2.getTaskCount()+1);
				scmSyncDataBiz.update(scmSyncData2, param);
			}
		}
	}
	
	private void generateSyncTaskInfoBySyncData(TaskSettingDetail2 taskSettingDetail,String billType,String taskSource,Param param){
		ScmSystemTaskExecTime scmSystemTaskExecTime = scmSystemTaskExecTimeBiz.selectByTaskType(param.getControlUnitNo(), "SyncData", param);//获取同步子表生成任务时的最后时间戳
		//生成同步任务子表
		Page page=new Page();
		page.setModelClass(ScmSyncData.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmSyncData.FN_TASKCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncData.class) + "." +ScmSyncData.FN_TASKCODE, QueryParam.QUERY_EQ,taskSettingDetail.getTaskCode()));
		page.getParam().put(ScmSyncData.FN_DATACLASS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncData.class) + "." +ScmSyncData.FN_DATACLASS, QueryParam.QUERY_EQ,taskSettingDetail.getDataClass()));
		if(StringUtils.isNotBlank(billType)){
			page.getParam().put(ScmSyncData.FN_BILLTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncData.class) + "." +ScmSyncData.FN_BILLTYPE, QueryParam.QUERY_EQ,billType));
		}
		page.getParam().put(ScmSyncData.FN_SYNCHRON,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncData.class) + "." +ScmSyncData.FN_SYNCHRON, QueryParam.QUERY_EQ,"N"));
		if(scmSystemTaskExecTime!=null && StringUtils.isNotBlank(scmSystemTaskExecTime.getExecTime())) {
			page.getParam().put(ScmSyncData.FN_UPDATETIME,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncData.class) + "." +ScmSyncData.FN_UPDATETIME, QueryParam.QUERY_GT,scmSystemTaskExecTime.getExecTime()));
		}
		List<String> arglist = new ArrayList();
		arglist.add("controlUnitNo="+param.getControlUnitNo());
		List<ScmSyncData> scmSyncDataList = scmSyncDataBiz.queryPage(page, arglist, "selectForTaskPage", param);
		if(scmSyncDataList != null && !scmSyncDataList.isEmpty()){
			Date updateTime = scmSyncDataList.get(scmSyncDataList.size()-1).getUpdateTime();
			for(ScmSyncData scmSyncData : scmSyncDataList){
				ScmSyncTaskInfo2 scmSyncTaskInfo = new ScmSyncTaskInfo2(true);
				if(StringUtils.equals(scmSyncData.getBillType(), "InvPurInWhsBill")) {
					scmSyncTaskInfo.setBizCode("orgInv");
				}else if(StringUtils.equals(scmSyncData.getBillType(), "PurOrder")){
					scmSyncTaskInfo.setBizCode("orgPur");
				}else if(StringUtils.equals(scmSyncData.getBillType(), "ScmPurPrice")){
					scmSyncTaskInfo.setBizCode("orgPur");
				}
				scmSyncTaskInfo.setOrgUnitNo(scmSyncData.getOrgUnitNo());
				scmSyncTaskInfo.setTaskCode(taskSettingDetail.getTaskCode());
				scmSyncTaskInfo.setTaskAction(taskSettingDetail.getInteractionMode());
				scmSyncTaskInfo.setTaskType(taskSettingDetail.getTaskObject());
				scmSyncTaskInfo.setProductCode(taskSettingDetail.getProductCode());
				scmSyncTaskInfo.setTaskOwner(taskSettingDetail.getChannel());
				scmSyncTaskInfo.setTaskStatus("W");
				scmSyncTaskInfo.setControlUnitNo(param.getControlUnitNo());
				scmSyncTaskInfo.setSyncDataId(scmSyncData.getId());
				ScmSyncTaskInfo2 scmSyncTaskInfo2 = scmSyncTaskInfoBiz.selectByScmSyncTaskInfo(scmSyncTaskInfo, param);
				if(scmSyncTaskInfo2 != null){
					Calendar newTime = Calendar.getInstance();
		            newTime.add(Calendar.SECOND,Integer.parseInt(taskSettingDetail.getExtendedParam1()));
		            scmSyncTaskInfo2.setLogtime(newTime.getTime());
		            scmSyncTaskInfoBiz.update(scmSyncTaskInfo2, param);
				}else{
					scmSyncTaskInfo.setCreateTime(new Date());
					Calendar newTime = Calendar.getInstance();
		            newTime.add(Calendar.SECOND,Integer.parseInt(taskSettingDetail.getExtendedParam1()));
		            scmSyncTaskInfo.setLogtime(newTime.getTime());
					scmSyncTaskInfo.setTaskSource(taskSource);
					scmSyncTaskInfoBiz.add(scmSyncTaskInfo, param);
				}
				scmSyncData = scmSyncDataBiz.select(scmSyncData.getId(), param);
				scmSyncData.setLastTaskTime(new Date());
				scmSyncData.setTaskCount(scmSyncData.getTaskCount()+1);
				scmSyncDataBiz.updateBillNoChangeTime(scmSyncData, param);
			}
			if(scmSystemTaskExecTime==null) {
				scmSystemTaskExecTime = new ScmSystemTaskExecTime(true);
				scmSystemTaskExecTime.setOrgUnitNo(param.getControlUnitNo());
				scmSystemTaskExecTime.setTaskType("SyncData");
				scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTime));
				scmSystemTaskExecTime.setControlUnitNo(param.getControlUnitNo());
				scmSystemTaskExecTimeBiz.add(scmSystemTaskExecTime, param);
			}else {
				scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTime));
				scmSystemTaskExecTimeBiz.update(scmSystemTaskExecTime, param);
			}
		}
	}
	
	private void generateSyncTaskInfo(TaskSettingDetail2 taskSettingDetail,Date updateTime,String taskSource,Param param){
		//生成同步任务子表
		ScmSyncTaskInfo2 scmSyncTaskInfo = new ScmSyncTaskInfo2(true);
		scmSyncTaskInfo.setOrgUnitNo(param.getOrgUnitNo());
		scmSyncTaskInfo.setTaskCode(taskSettingDetail.getTaskCode());
		scmSyncTaskInfo.setTaskAction(taskSettingDetail.getInteractionMode());
		scmSyncTaskInfo.setTaskType(taskSettingDetail.getTaskObject());
		scmSyncTaskInfo.setProductCode(taskSettingDetail.getProductCode());
		scmSyncTaskInfo.setTaskOwner(taskSettingDetail.getChannel());
		scmSyncTaskInfo.setTaskStatus("W");
		scmSyncTaskInfo.setControlUnitNo(param.getControlUnitNo());
		scmSyncTaskInfo.setSyncDataId(0);
		ScmSyncTaskInfo2 scmSyncTaskInfo2 = scmSyncTaskInfoBiz.selectByScmSyncTaskInfo(scmSyncTaskInfo, param);
		if(scmSyncTaskInfo2 != null){
			Calendar newTime = Calendar.getInstance();
            newTime.add(Calendar.SECOND,Integer.parseInt(taskSettingDetail.getExtendedParam1()));
            scmSyncTaskInfo2.setLogtime(newTime.getTime());
            scmSyncTaskInfoBiz.update(scmSyncTaskInfo2, param);
		}else{
			scmSyncTaskInfo.setBegDate(new Date());
			scmSyncTaskInfo.setSyncDataId(0);
            scmSyncTaskInfo.setLogtime(updateTime);
			scmSyncTaskInfo.setCreateTime(new Date());
			scmSyncTaskInfo.setTaskSource(taskSource);
			scmSyncTaskInfoBiz.add(scmSyncTaskInfo, param);
		}
	}
	
	private boolean needSync(TaskSettingDetail2 taskSettingDetail,long billId,Date updateTimeStamp,String orgUnitNo,String billType,String billNo,Param param){
		ScmSyncData scmSyncData = new ScmSyncData(true);
		scmSyncData.setOrgUnitNo(orgUnitNo);
		scmSyncData.setDataClass(taskSettingDetail.getDataClass());
		scmSyncData.setDataId(billId);
		scmSyncData.setBillType(billType);
		scmSyncData.setBillNo(billNo);
		scmSyncData.setTaskCode(taskSettingDetail.getTaskCode());
		scmSyncData.setControlUnitNo(param.getControlUnitNo());
		ScmSyncData scmSyncData2 = scmSyncDataBiz.selectByScmSyncData(scmSyncData, param);
		if(scmSyncData2 != null && (FormatUtils.parseDateTime(FormatUtils.fmtDateTime(updateTimeStamp))).compareTo(scmSyncData2.getUpdateTime()) > 0){
			return true;
		}
		return false;
	}
	
	public void changeSyncData(BaseModel baseModel,Param param){
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(param.getControlUnitNo(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0){
			List<TaskSettingDetail2> taskSettingDetailList = taskSettingDetailBiz.selectByCtrl(param.getControlUnitNo(), param);
			if(taskSettingDetailList != null && !taskSettingDetailList.isEmpty()){
				for(TaskSettingDetail2 taskSettingDetail : taskSettingDetailList){
					switch (taskSettingDetail.getTaskObject()) {
					case "purOrderPush":
						if(baseModel instanceof ScmPurOrder2){
							ScmPurOrder2 scmPurOrder = (ScmPurOrder2)baseModel;
							Date updateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(scmPurOrder.getUpdateTimeStamp()));
							changeSyncType(taskSettingDetail,scmPurOrder.getId(),updateTime,scmPurOrder.getOrgUnitNo(),"PurOrder",scmPurOrder.getPoNo(),param);
						}
						break;
					case "invPurInWarehsBillPush":
						if(baseModel instanceof ScmInvPurInWarehsBill2){
							ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = (ScmInvPurInWarehsBill2)baseModel;
							Date updateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(scmInvPurInWarehsBill.getUpdateTimeStamp()));
							changeSyncType(taskSettingDetail,scmInvPurInWarehsBill.getWrId(),updateTime,scmInvPurInWarehsBill.getOrgUnitNo(),"InvPurInWhsBill",scmInvPurInWarehsBill.getWrNo(),param);
						}
						break;
					case "purPricePush":
						if(baseModel instanceof ScmPurPrice2){
							ScmPurPrice2 scmPurPrice = (ScmPurPrice2)baseModel;
							Date updateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(scmPurPrice.getUpdateTimeStamp()));
							changeSyncType(taskSettingDetail,scmPurPrice.getId(),updateTime,scmPurPrice.getOrgUnitNo(),"ScmPurPrice",scmPurPrice.getPmNo(),param);
						}
						break;
					case "industryQualifyPush":
						if(baseModel instanceof ScmIndustryGroup){
							ScmIndustryGroup scmIndustryGroup = (ScmIndustryGroup)baseModel;
							Date updateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(scmIndustryGroup.getUpdateTimeStamp()));
							changeSyncType(taskSettingDetail,scmIndustryGroup.getId(),updateTime,scmIndustryGroup.getControlUnitNo(),"ScmIndustryGroup",scmIndustryGroup.getClassCode(),param);
						}else if(baseModel instanceof ScmSupplierEvent2){
							ScmSupplierEvent2 scmSupplierEvent = (ScmSupplierEvent2)baseModel;
							Date updateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(scmSupplierEvent.getUpdateTimeStamp()));
							changeSyncType(taskSettingDetail,scmSupplierEvent.getId(),updateTime,scmSupplierEvent.getControlUnitNo(),"ScmSupplierEvent",scmSupplierEvent.getEventCode(),param);
						}
						break;
					case "qualificationInfoPush":
						if(baseModel instanceof ScmQualifieInfo2){
							ScmQualifieInfo2 scmQualifieInfo = (ScmQualifieInfo2)baseModel;
							Date updateTime = FormatUtils.parseDateTimeMs(FormatUtils.fmtDateTimeMS(scmQualifieInfo.getUpdateTimeStamp()));
							changeSyncType(taskSettingDetail,scmQualifieInfo.getId(),updateTime,scmQualifieInfo.getControlUnitNo(),"ScmQualifieInfo",String.valueOf(scmQualifieInfo.getId()),param);
						}
						break;
					default:
						break;
					}
				}
			}
		}
	}
	
	private void changeSyncType(TaskSettingDetail2 taskSettingDetail,long billId,Date updateTimeStamp,String orgUnitNo,String billType,String billNo,Param param){
		//同步标识子表修改同步类型及同步标志
		ScmSyncData scmSyncData = new ScmSyncData(true);
		scmSyncData.setOrgUnitNo(orgUnitNo);
		scmSyncData.setDataClass(taskSettingDetail.getDataClass());
		scmSyncData.setDataId(billId);
		scmSyncData.setBillType(billType);
		scmSyncData.setBillNo(billNo);
		scmSyncData.setTaskCode(taskSettingDetail.getTaskCode());
		scmSyncData.setControlUnitNo(param.getControlUnitNo());
		ScmSyncData scmSyncData2 = scmSyncDataBiz.selectByScmSyncData(scmSyncData, param);
		if(scmSyncData2 != null){
			scmSyncData2.setSyncType("delete");
			scmSyncData2.setSynchron("N");
			scmSyncData2.setBillNo(billNo);
			scmSyncDataBiz.update(scmSyncData2, param);
		}		
	}
	
	public String pushReplyDataToSupplierPlat(ScmSupplierReplyData2 scmSupplierReplyData, long demanderId, Param param) {
		if(scmSupplierReplyData != null){
			AppInfo appInfo = new AppInfo(AppInfo.APP_NAME,param.getOrgUnitNo(),param.getControlUnitNo());
			appInfo = getAPP(appInfo);
			String url = "/isp/billConfirm/statusUpdate";
			String params ="{\"billtype\": \""+scmSupplierReplyData.getBillType()
				+"\",\"confirmBy\": \""+scmSupplierReplyData.getConfirmBy()
				+"\",\"demanderId\": "+demanderId
				+",\"confirmInfo\": \""+string2Json(scmSupplierReplyData.getMsgContent())
				+"\",\"refBillNo\": \""+scmSupplierReplyData.getRefBillNo()
				+"\",\"refMsgId\": "+scmSupplierReplyData.getId()
				+",\"status\": \""+scmSupplierReplyData.getConfirmStatus()
				+"\",\"source\": \"D\",\"supplierId\": "+scmSupplierReplyData.getPlatSupplierId()+"}";
			String data = getData(appInfo, scmSupplierReplyData.getPlatSupplierId(), url, params);
			log.info("接口返回："+data);
			JSONObject jsonObject = JSON.parseObject(data);
			if(jsonObject != null && "0".equals(String.valueOf(jsonObject.get("errCode")))){
				return "";
			}else{
				return String.valueOf(jsonObject.get("errText"));
			}
		}
		return "";
	}
	
	private String string2Json(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '\"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '/':
				sb.append("\\/");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			default:
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	public void batchPushPurPriceToSupplierPlat(AppInfo appInfo) {
		OperationParam param = new OperationParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("purPricePush");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(appInfo.getControlUnitNo(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && systemTask.getSize() > 0){
			Page page=new Page();
			page.setModelClass(ScmSyncTaskInfo2.class);
			page.setShowCount(systemTask.getSize());
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKCODE, QueryParam.QUERY_EQ,"SCM_ESP"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKACTION,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKACTION, QueryParam.QUERY_EQ,"Push"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKTYPE, QueryParam.QUERY_EQ,systemTask.getTaskType()));
			page.getParam().put(ScmSyncTaskInfo2.FN_PRODUCTCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_PRODUCTCODE, QueryParam.QUERY_EQ,"iSCM"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKOWNER,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKOWNER, QueryParam.QUERY_EQ,"ESP"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKSTATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKSTATUS, QueryParam.QUERY_EQ,"W"));
			page.getParam().put(ScmSyncTaskInfo2.FN_CONTROLUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmSyncTaskInfo2> scmSyncTaskInfoList = scmSyncTaskInfoBiz.findPage(page, param);
			if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
				int count = 0;
				for(int i=0;i<scmSyncTaskInfoList.size();i++){
					ScmSyncTaskInfo2 scmSyncTaskInfo = scmSyncTaskInfoList.get(i);
					ScmSyncData scmSyncData = scmSyncDataBiz.select(scmSyncTaskInfo.getSyncDataId(), param);
					if(scmSyncData != null && "ScmPurPrice".equalsIgnoreCase(scmSyncData.getBillType()) && !"Y".equalsIgnoreCase(scmSyncData.getSynchron()) && count<systemTask.getSize()){
						count++;
						try {
							String errorMsg = "";
							if(StringUtils.equals(scmSyncData.getSyncType(), "delete")){
								errorMsg = billDeleteSync(appInfo, scmSyncData.getPlatSupplierId(), scmSupplierDemander.getDemanderId(), scmSyncData.getBillNo(), "ScmPurPrice", param);
							}else{
								errorMsg = pushPurPriceToSupplierPlat(appInfo, scmSyncData.getDataId(), scmSyncTaskInfo.getTaskSource(), param);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncData = scmSyncDataBiz.select(scmSyncData.getId(), param);
							if(StringUtils.isBlank(errorMsg)){
								scmSyncTaskInfo.setTaskStatus("S");
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
								scmSyncData.setSynchron("Y");
								scmSyncData.setSyncTime(new Date());
								scmSyncData.setErrorMessage(null);
								scmSyncDataBiz.update(scmSyncData, param);
							}else{
								scmSyncTaskInfo.setTaskStatus("F");
								scmSyncTaskInfo.setStatusMessage(errorMsg);
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
								scmSyncData.setSynchron("F");
								scmSyncData.setErrorMessage(errorMsg);
								scmSyncDataBiz.update(scmSyncData, param);
							}
						} catch (Exception e) {
							log.error(e);
							String errorMsg = e.getMessage();
							if(StringUtils.isNotBlank(errorMsg) && errorMsg.length()>255){
								errorMsg = StringUtils.substring(errorMsg, 0, 255-1);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncData = scmSyncDataBiz.select(scmSyncData.getId(), param);
							scmSyncTaskInfo.setTaskStatus("F");
							scmSyncTaskInfo.setStatusMessage(errorMsg);
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfo.setEndDate(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							scmSyncData.setSynchron("F");
							scmSyncData.setErrorMessage(errorMsg);
							scmSyncDataBiz.update(scmSyncData, param);
							continue;
						}
					}else if(scmSyncData != null && "ScmPurPrice".equalsIgnoreCase(scmSyncData.getBillType()) && "Y".equalsIgnoreCase(scmSyncData.getSynchron())){
						scmSyncTaskInfo.setTaskStatus("C");
						scmSyncTaskInfo.setUpdateTime(new Date());
						scmSyncTaskInfo.setEndDate(new Date());
						scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
					}
				}
			}else{
				log.info("不存在需要推送的定价单！");
			}
		}else{
			log.info("当前管理单元未绑定需求方！");
		}
	}
	
	public String pushPurPriceToSupplierPlat(AppInfo appInfo,long purPriceId, String taskSource,Param param) {
		if(purPriceId > 0){
			ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(param.getControlUnitNo(), param);
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0){
				long demanderId = scmSupplierDemander.getDemanderId();
				List<ScmPurPrice2> scmPurPriceTempList = new ArrayList<ScmPurPrice2>();
				ScmPurPrice2 tempScmPurPrice = new ScmPurPrice2(true);
				tempScmPurPrice.setId(purPriceId);
				scmPurPriceTempList.add(tempScmPurPrice);
				CommonBean scmPurPriceCommonBean = new CommonBean();
				scmPurPriceCommonBean.setList(scmPurPriceTempList);
				scmPurPriceCommonBean = scmPurPriceBiz.select(scmPurPriceCommonBean, param);
				if (scmPurPriceCommonBean != null) {
					List<ScmPurPrice2> scmPurPriceList = new ArrayList<ScmPurPrice2>();
					List<ScmPurPriceEntry2> scmPurPriceEntryList = new ArrayList<ScmPurPriceEntry2>();
					if(scmPurPriceCommonBean.getList() != null && !scmPurPriceCommonBean.getList().isEmpty()){
						scmPurPriceList = (List<ScmPurPrice2>) scmPurPriceCommonBean.getList();
					}
					if(scmPurPriceCommonBean.getList2() != null && !scmPurPriceCommonBean.getList2().isEmpty()){
						scmPurPriceEntryList = (List<ScmPurPriceEntry2>) scmPurPriceCommonBean.getList2();
					}
					if(scmPurPriceList != null && !scmPurPriceList.isEmpty()
							&& scmPurPriceEntryList != null && !scmPurPriceEntryList.isEmpty()){
						if(appInfo == null){
							appInfo = new AppInfo(AppInfo.APP_NAME,param.getControlUnitNo(),param.getControlUnitNo());
							appInfo = getAPP(appInfo);
						}
						List<SupplierPlatPurPriceEntry> supplierPlatPurPriceEntryList = new ArrayList<SupplierPlatPurPriceEntry>();
						SupplierPlatPurPrice supplierPlatPurPrice = new SupplierPlatPurPrice();
						ScmPurPrice2 scmPurPrice = scmPurPriceList.get(0);
						Map<Long, Long> map = new HashMap<>();
						long platSupplierId = 0;
						if(scmPurPrice.getVendorId1() > 0){
							ScmSupplierRegInvitation scmSupplierRegInvitation = scmSupplierRegInvitationBiz.selectByVendorIdAndCtrl(scmPurPrice.getVendorId1(), param.getControlUnitNo(), param);
							if(scmSupplierRegInvitation != null && scmSupplierRegInvitation.getPlatSupplierId() > 0){
								map.put(scmPurPrice.getVendorId1(), scmSupplierRegInvitation.getPlatSupplierId());
								platSupplierId = scmSupplierRegInvitation.getPlatSupplierId();
							}
						}
						if(scmPurPrice.getVendorId2() > 0){
							ScmSupplierRegInvitation scmSupplierRegInvitation = scmSupplierRegInvitationBiz.selectByVendorIdAndCtrl(scmPurPrice.getVendorId2(), param.getControlUnitNo(), param);
							if(scmSupplierRegInvitation != null && scmSupplierRegInvitation.getPlatSupplierId() > 0){
								map.put(scmPurPrice.getVendorId2(), scmSupplierRegInvitation.getPlatSupplierId());
								platSupplierId = scmSupplierRegInvitation.getPlatSupplierId();
							}
						}
						if(scmPurPrice.getVendorId3() > 0){
							ScmSupplierRegInvitation scmSupplierRegInvitation = scmSupplierRegInvitationBiz.selectByVendorIdAndCtrl(scmPurPrice.getVendorId3(), param.getControlUnitNo(), param);
							if(scmSupplierRegInvitation != null && scmSupplierRegInvitation.getPlatSupplierId() > 0){
								map.put(scmPurPrice.getVendorId3(), scmSupplierRegInvitation.getPlatSupplierId());
								platSupplierId = scmSupplierRegInvitation.getPlatSupplierId();
							}
						}
						if(map != null && !map.isEmpty()){
							BeanUtils.copyProperties(scmPurPrice, supplierPlatPurPrice);
							supplierPlatPurPrice.setAssignOrg(scmPurPrice.isIsAssignOrg());
							supplierPlatPurPrice.setBusinessSelfQuotation(scmPurPrice.isBusinessAutoQuotation());
							supplierPlatPurPrice.setBuyerOrgName(scmPurPrice.getOrgUnitName());
							supplierPlatPurPrice.setBuyerOrgUnitNo(scmPurPrice.getOrgUnitNo());
							supplierPlatPurPrice.setChecker(scmPurPrice.getCheckerName());
							supplierPlatPurPrice.setCurrency(scmPurPrice.getCurrencyNo());
							supplierPlatPurPrice.setDemanderId(demanderId);
							supplierPlatPurPrice.setFinOrgName(scmPurPrice.getFinOrgUnitName());
							String priceType =sysParamBiz.getValue(scmPurPrice.getOrgUnitNo(), "SCM_PriceType", "1", param);
							if(StringUtils.equals("1", priceType)){
								supplierPlatPurPrice.setInclueTax(true);
							}else{
								supplierPlatPurPrice.setInclueTax(false);
							}
							supplierPlatPurPrice.setPriceName(scmPurPrice.getPriceOfficer());
							supplierPlatPurPrice.setRefPmNo(scmPurPrice.getPmNo());
							supplierPlatPurPrice.setSelVndrId(((scmPurPrice.getSelVndrId()>0 && map.containsKey(scmPurPrice.getSelVndrId())) ? map.get(scmPurPrice.getSelVndrId()) : 0));
							supplierPlatPurPrice.setVendorId1(((scmPurPrice.getVendorId1()>0 && map.containsKey(scmPurPrice.getVendorId1())) ? map.get(scmPurPrice.getVendorId1()) : 0));
							supplierPlatPurPrice.setVendorId2(((scmPurPrice.getVendorId2()>0 && map.containsKey(scmPurPrice.getVendorId2())) ? map.get(scmPurPrice.getVendorId2()) : 0));
							supplierPlatPurPrice.setVendorId3(((scmPurPrice.getVendorId3()>0 && map.containsKey(scmPurPrice.getVendorId3())) ? map.get(scmPurPrice.getVendorId3()) : 0));
							for(ScmPurPriceEntry2 scmPurPriceEntry : scmPurPriceEntryList){
								if("N".equalsIgnoreCase(scmPurPriceEntry.getRowStatus())){
									continue;
								}
								SupplierPlatPurPriceEntry supplierPlatPurPriceEntry = new SupplierPlatPurPriceEntry();
								BeanUtils.copyProperties(scmPurPriceEntry, supplierPlatPurPriceEntry);
								supplierPlatPurPriceEntry.setItemCode(scmPurPriceEntry.getItemNo());
								supplierPlatPurPriceEntry.setPrNos(null);
								supplierPlatPurPriceEntry.setRefDtlId(scmPurPriceEntry.getId());
								if(StringUtils.equalsIgnoreCase("W", supplierPlatPurPrice.getConfirmStatus())){
									supplierPlatPurPriceEntry.setSelVndrId(((scmPurPriceEntry.getSelVndrId()>0 && map.containsKey(scmPurPriceEntry.getSelVndrId())) ? map.get(scmPurPriceEntry.getSelVndrId()) : 0));
									supplierPlatPurPriceEntry.setPrice(scmPurPriceEntry.getPrice());
									supplierPlatPurPriceEntry.setTaxRate(scmPurPriceEntry.getTaxRate());
								}else{
									supplierPlatPurPriceEntry.setSelVndrId(0);
									supplierPlatPurPriceEntry.setPrice(BigDecimal.ZERO);
									supplierPlatPurPriceEntry.setTaxRate(BigDecimal.ZERO);
								}
								supplierPlatPurPriceEntryList.add(supplierPlatPurPriceEntry);
							}
							supplierPlatPurPrice.setEntryList(supplierPlatPurPriceEntryList);
							String data = getData(appInfo, platSupplierId, "/isp/purPrice/sync", JSONObject.toJSONString(supplierPlatPurPrice));
							log.info("接口返回："+data);
							JSONObject jsonObject = JSON.parseObject(data);
							if(jsonObject != null && "0".equals(String.valueOf(jsonObject.get("errCode")))){

							}else{
								return String.valueOf(jsonObject.get("errText"));
							}
						}else{
							log.info("当前定价单供应商都未获取到供应商平台的对照！");
							return "当前定价单供应商都未获取到供应商平台的对照！";
						}
					}
				}else{
					log.info("未查询到定价单！");
					return "未查询到定价单！";
				}
			}else{
				log.info("当前管理单元未绑定需求方！");
				return "当前管理单元未绑定需求方！";
			}
		}else{
			
		}
		return "";
	}
	
	public void batchUpdateSupplierQuotation(AppInfo appInfo) {
		OperationParam param = new OperationParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("businessQuotationGet");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(appInfo.getControlUnitNo(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && systemTask.getSize() > 0){
			Page page=new Page();
			page.setModelClass(ScmSyncTaskInfo2.class);
			page.setShowCount(systemTask.getSize());
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKCODE, QueryParam.QUERY_EQ,"SCM_ESP"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKTYPE, QueryParam.QUERY_EQ,systemTask.getTaskType()));
			page.getParam().put(ScmSyncTaskInfo2.FN_PRODUCTCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_PRODUCTCODE, QueryParam.QUERY_EQ,"iSCM"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKOWNER,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKOWNER, QueryParam.QUERY_EQ,"ESP"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKSTATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKSTATUS, QueryParam.QUERY_EQ,"W"));
			page.getParam().put(ScmSyncTaskInfo2.FN_CONTROLUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmSyncTaskInfo2> scmSyncTaskInfoList = scmSyncTaskInfoBiz.findPage(page, param);
			if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
				int count = 0;
				for(int i=0;i<scmSyncTaskInfoList.size();i++){
					ScmSyncTaskInfo2 scmSyncTaskInfo = scmSyncTaskInfoList.get(i);
					if(count<systemTask.getSize()){
						count++;
						try {
							scmSyncTaskInfo.setTaskStatus("D");
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							String errorMsg = updateUpdateSupplierQuotation(appInfo,systemTask,param);
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							if(StringUtils.isBlank(errorMsg)){
								scmSyncTaskInfo.setTaskStatus("S");
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							}else{
								scmSyncTaskInfo.setTaskStatus("F");
								scmSyncTaskInfo.setStatusMessage(errorMsg);
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							}
						} catch (Exception e) {
							log.error(e);
							String errorMsg = e.getMessage();
							if(StringUtils.isNotBlank(errorMsg) && errorMsg.length()>255){
								errorMsg = StringUtils.substring(errorMsg, 0, 255-1);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncTaskInfo.setTaskStatus("F");
							scmSyncTaskInfo.setStatusMessage(errorMsg);
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfo.setEndDate(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							continue;
						}
					}
				}
			}else{
				log.info("不存在拉取供应商平台报价信息任务！");
			}
		}else{
			log.info("当前管理单元未绑定需求方！");
		}
	}
	
	public String updateUpdateSupplierQuotation(AppInfo appInfo,ScmSystemTask systemTask,Param param) {
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(param.getControlUnitNo(), param);
		ScmSystemTaskExecTime scmSystemTaskExecTime = scmSystemTaskExecTimeBiz.selectByTaskType(param.getControlUnitNo(), systemTask.getTaskType(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && StringUtils.isNotBlank(scmSupplierDemander.getUniqueNo())){
			String updateTime = (scmSystemTaskExecTime==null || StringUtils.isEmpty(scmSystemTaskExecTime.getExecTime())) ? FormatUtils.fmtDateTimeMS(CalendarUtil.relativeDate(new Date(), -1)) : scmSystemTaskExecTime.getExecTime();
			Date updateTimeStamp = FormatUtils.parseDateTimeMs(updateTime);
			String params = "{\"demanderId\": "+scmSupplierDemander.getDemanderId()+",\"updateTimeStamp\": \""+updateTime+"\"}";
			String data = getDataPage(appInfo, "/isp/purPrice/queryListForDemander", params, -1, (systemTask.getSize() > 0 ? systemTask.getSize() : 20));
			log.info("接口返回："+data);
			JSONObject jsonObject = JSON.parseObject(data);
			if(jsonObject != null && "0".equals(String.valueOf(jsonObject.get("errCode")))){
				JSONArray resultarray = jsonObject.getJSONArray("data");
				if(resultarray != null && !resultarray.isEmpty()){
					for (int i = 0; i < resultarray.size(); i++) {
						JSONObject billObject = resultarray.getJSONObject(i);
						if(billObject.get("refPmNo") != null 
								&& StringUtils.isNotBlank(String.valueOf(billObject.get("refPmNo")))
								&& !"null".equals(String.valueOf(billObject.get("refPmNo")))){
							String refPmNo = String.valueOf(billObject.get("refPmNo"));
							String confirmStatus = String.valueOf(billObject.get("confirmStatus"));
							long vendorId1 = Long.parseLong(String.valueOf(billObject.get("vendorId1")));
							long vendorId2 = Long.parseLong(String.valueOf(billObject.get("vendorId2")));
							long vendorId3 = Long.parseLong(String.valueOf(billObject.get("vendorId3")));
							ScmPurPrice2 scmPurPrice = scmPurPriceBiz.selectByPmNo(refPmNo, param);
							Date tempUpdateTimeStamp = FormatUtils.parseDateTimeMs(String.valueOf(billObject.get("updateTimeStamp")));
							updateTimeStamp = updateTimeStamp.compareTo(tempUpdateTimeStamp) < 0 ? tempUpdateTimeStamp : updateTimeStamp;
							if(scmPurPrice != null){
								if(StringUtils.isNotBlank(scmPurPrice.getConfirmStatus())) {
									continue;
								}
								Date vendorPqDate1 = null;
								if(resultarray.getJSONObject(i).get("vendorPqDate1") != null){
									vendorPqDate1 = FormatUtils.parseDateTime(String.valueOf(billObject.get("vendorPqDate1")));
								}
								Date vendorPqDate2 = null;
								if(resultarray.getJSONObject(i).get("vendorPqDate2") != null){
									vendorPqDate2 = FormatUtils.parseDateTime(String.valueOf(billObject.get("vendorPqDate2")));
								}
								Date vendorPqDate3 = null;
								if(resultarray.getJSONObject(i).get("vendorPqDate3") != null){
									vendorPqDate3 = FormatUtils.parseDateTime(String.valueOf(billObject.get("vendorPqDate3")));
								}
								boolean changeVendor1 = false;
								if(vendorId1 > 0 && ((scmPurPrice.getVendorPqDate1() == null && vendorPqDate1 != null)
										|| (scmPurPrice.getVendorPqDate1() != null && vendorPqDate1 != null && vendorPqDate1.compareTo(scmPurPrice.getVendorPqDate1())>0))){
									scmPurPrice.setVendorPqDate1(vendorPqDate1);
									changeVendor1 = true;
								}
								boolean changeVendor2 = false;
								if(vendorId2 > 0 && ((scmPurPrice.getVendorPqDate2() == null && vendorPqDate2 != null)
										|| (scmPurPrice.getVendorPqDate2() != null && vendorPqDate2 != null && vendorPqDate2.compareTo(scmPurPrice.getVendorPqDate2())>0))){
									scmPurPrice.setVendorPqDate2(vendorPqDate2);
									changeVendor2 = true;
								}
								boolean changeVendor3 = false;
								if(vendorId3 > 0 && ((scmPurPrice.getVendorPqDate3() == null && vendorPqDate3 != null)
										|| (scmPurPrice.getVendorPqDate3() != null && vendorPqDate3 != null && vendorPqDate3.compareTo(scmPurPrice.getVendorPqDate3())>0))){
									scmPurPrice.setVendorPqDate3(vendorPqDate3);
									changeVendor3 = true;
								}
								List<ScmPurPriceEntry2> scmPurPriceEntryList = scmPurPriceEntryBiz.selectByPmId(scmPurPrice.getId(), param);
								JSONArray entryList = billObject.getJSONArray("entryList");
								if(scmPurPriceEntryList != null && !scmPurPriceEntryList.isEmpty() && entryList != null && !entryList.isEmpty()){
									for(int j = 0; j < scmPurPriceEntryList.size(); j++){
										ScmPurPriceEntry2 scmPurPriceEntry = scmPurPriceEntryList.get(j);
										for(int k = 0; k < entryList.size(); k++){
											JSONObject billEntryObject = entryList.getJSONObject(k);
											long refDtlId = Long.parseLong(String.valueOf(billEntryObject.get("refDtlId")));
											BigDecimal price1 = billEntryObject.get("price1") != null ? (BigDecimal)(billEntryObject.get("price1")) : scmPurPriceEntry.getPrice1();
											BigDecimal price2 = billEntryObject.get("price2") != null ? (BigDecimal)(billEntryObject.get("price2")) : scmPurPriceEntry.getPrice2();
											BigDecimal price3 = billEntryObject.get("price3") != null ? (BigDecimal)(billEntryObject.get("price3")) : scmPurPriceEntry.getPrice3();
											BigDecimal taxRate1 = billEntryObject.get("taxRate1") != null ? (BigDecimal)(billEntryObject.get("taxRate1")) : scmPurPriceEntry.getTaxRate1();
											BigDecimal taxRate2 = billEntryObject.get("taxRate2") != null ? (BigDecimal)(billEntryObject.get("taxRate2")) : scmPurPriceEntry.getTaxRate2();
											BigDecimal taxRate3 = billEntryObject.get("taxRate3") != null ? (BigDecimal)(billEntryObject.get("taxRate3")) : scmPurPriceEntry.getTaxRate3();
											if(scmPurPriceEntry.getId() == refDtlId){
												if(vendorId1 > 0){
													scmPurPriceEntry.setPrice1(price1);
													scmPurPriceEntry.setTaxRate1(taxRate1);
												}
												if(vendorId2 > 0){
													scmPurPriceEntry.setPrice2(price2);
													scmPurPriceEntry.setTaxRate2(taxRate2);
												}
												if(vendorId3 > 0){
													scmPurPriceEntry.setPrice3(price3);
													scmPurPriceEntry.setTaxRate3(taxRate3);
												}
												boolean samePrice=false;
												BigDecimal minPrice = scmPurPriceEntry.getPrice1();
		                                        BigDecimal minTaxRate = scmPurPriceEntry.getTaxRate1();
		                                        long selectVendorId = scmPurPrice.getVendorId1();
		                                        if((minPrice==null || minPrice.compareTo(scmPurPriceEntry.getPrice2())>0  || minPrice.compareTo(BigDecimal.ZERO)==0)&& scmPurPriceEntry.getPrice2().compareTo(BigDecimal.ZERO)>0){
	                                                minPrice = scmPurPriceEntry.getPrice2();
	                                                minTaxRate = scmPurPriceEntry.getTaxRate2();
	                                                selectVendorId = scmPurPrice.getVendorId2();
		                                        }else if(minPrice.compareTo(scmPurPriceEntry.getPrice2())==0 && BigDecimal.ZERO.compareTo(scmPurPriceEntry.getPrice2())<0) {
		                                        	samePrice=true;
		                                        	if(scmPurPriceEntry.getTaxRate1().compareTo(scmPurPriceEntry.getTaxRate2())>0) {
		                                        		selectVendorId = scmPurPrice.getVendorId2();
		                                        		minTaxRate = scmPurPriceEntry.getTaxRate2();
		                                        	}else if(scmPurPriceEntry.getTaxRate1().compareTo(scmPurPriceEntry.getTaxRate2())==0) {
		                                        		selectVendorId = 0;
		                                        	}
		                                        }
		                                        if((minPrice==null || minPrice.compareTo(scmPurPriceEntry.getPrice3())>0 || minPrice.compareTo(BigDecimal.ZERO)==0)&& scmPurPriceEntry.getPrice3().compareTo(BigDecimal.ZERO)>0){
	                                                minPrice = scmPurPriceEntry.getPrice3();
	                                                minTaxRate = scmPurPriceEntry.getTaxRate3();
	                                                selectVendorId = scmPurPrice.getVendorId3();
		                                        }else if(minPrice.compareTo(scmPurPriceEntry.getPrice3())==0 && BigDecimal.ZERO.compareTo(scmPurPriceEntry.getPrice3())<0) {
		                                        	samePrice=true;
		                                        	if(scmPurPriceEntry.getTaxRate2().compareTo(scmPurPriceEntry.getTaxRate3())>0) {
		                                        		selectVendorId = scmPurPrice.getVendorId3();
		                                        		minTaxRate = scmPurPriceEntry.getTaxRate3();
		                                        	}else if(scmPurPriceEntry.getTaxRate2().compareTo(scmPurPriceEntry.getTaxRate3())==0) {
		                                        		selectVendorId = 0;
		                                        	}
		                                        }
		                                        if(StringUtils.equals(sysParamBiz.getValue(scmPurPrice.getOrgUnitNo(), "SCM_PurPriceRule", "1", param), "2")){
                                            		if(BigDecimal.ZERO.compareTo(minPrice)==0) {
			                                        	selectVendorId = 0;
			                                        }
	                                            	scmPurPriceEntry.setSelVndrId(selectVendorId);
		                                            scmPurPriceEntry.setPrice(minPrice);
		                                            scmPurPriceEntry.setTaxRate(minTaxRate);
		                                            if (minPrice != null) {
		                                            	BigDecimal b = (scmPurPriceEntry.getPrice().subtract(scmPurPriceEntry.getPrePrice()).multiply(scmPurPriceEntry.getPrePurQty())).setScale(2, RoundingMode.HALF_UP);
			                                            scmPurPriceEntry.setDifferCost(b);
			                                            BigDecimal a = (scmPurPriceEntry.getPrice().subtract(scmPurPriceEntry.getPrePrice()).multiply(new BigDecimal("100"))).divide(scmPurPriceEntry.getPrePrice(), 4, RoundingMode.HALF_UP);
			                                            scmPurPriceEntry.setRiseRate(a);
													}
		                                        }
												scmPurPriceEntryBiz.updateVendorQuotation(scmPurPriceEntry, param);
											}
										}
									}
								}
								if(changeVendor1 || changeVendor2 || changeVendor3){
									scmPurPriceBiz.updateVendorPqData(scmPurPrice, param);
								}
							}
						}
					}
					if(scmSystemTaskExecTime==null) {
						scmSystemTaskExecTime = new ScmSystemTaskExecTime(true);
						scmSystemTaskExecTime.setOrgUnitNo(param.getControlUnitNo());
						scmSystemTaskExecTime.setTaskType(systemTask.getTaskType());
						scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTimeStamp));
						scmSystemTaskExecTime.setControlUnitNo(param.getControlUnitNo());
						scmSystemTaskExecTimeBiz.add(scmSystemTaskExecTime, param);
					}else {
						scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTimeStamp));
						scmSystemTaskExecTimeBiz.update(scmSystemTaskExecTime, param);
					}
				}
			}else{
				return String.valueOf(jsonObject.get("errText"));
			}
		}else{
			log.info("当前管理单元未绑定需求方！");
			return "当前管理单元未绑定需求方！";
		}
		return "";
	}
	
	public void batchPushIndustryGroupToSupplierPlat(AppInfo appInfo) {
		OperationParam param = new OperationParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		param.setUsrCode(appInfo.getUsrCode());
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("industryQualifyPush");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(appInfo.getControlUnitNo(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && systemTask.getSize() > 0){
			Page page=new Page();
			page.setModelClass(ScmSyncTaskInfo2.class);
			page.setShowCount(systemTask.getSize());
			ArrayList argList = new ArrayList();
	        argList.add("taskCode="+"SCM_ESP");
	        argList.add("taskAction=" + "Push");
	        argList.add("taskType=" + systemTask.getTaskType());
	        argList.add("productCode=" + param.getProductCode());
	        argList.add("taskOwner=" + "ESP");
	        argList.add("taskStatus=" + "W");
	        argList.add("controlUnitNo=" + param.getControlUnitNo());
	        List<ScmSyncTaskInfo2> scmSyncTaskInfoList = scmSyncTaskInfoBiz.queryPage(page, argList, "findPengdingTaskPage", param);
			if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
				int count = 0;
				for(int i=0;i<scmSyncTaskInfoList.size();i++){
					ScmSyncTaskInfo2 scmSyncTaskInfo = scmSyncTaskInfoList.get(i);
					ScmSyncData scmSyncData = scmSyncDataBiz.select(scmSyncTaskInfo.getSyncDataId(), param);
					if(scmSyncData != null && "ScmIndustryGroup".equalsIgnoreCase(scmSyncData.getBillType()) && count<systemTask.getSize()){
						count++;
						try {
							scmSyncTaskInfo.setTaskStatus("D");
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							String errorMsg = "";
							if(StringUtils.equals(scmSyncData.getSyncType(), "delete")){
								errorMsg = billDeleteSync(appInfo, scmSyncData.getPlatSupplierId(), scmSupplierDemander.getDemanderId(), scmSyncData.getBillNo(), "ScmIndustryGroup", param);
							}else{
								errorMsg = pushIndustryGroupToSupplierPlat(appInfo, scmSyncData.getDataId(), scmSyncTaskInfo.getTaskSource(), param);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncData = scmSyncDataBiz.select(scmSyncData.getId(), param);
							if(StringUtils.isBlank(errorMsg)){
								scmSyncTaskInfo.setTaskStatus("S");
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
								scmSyncData.setSynchron("Y");
								scmSyncData.setSyncTime(new Date());
								scmSyncData.setErrorMessage(null);
								scmSyncDataBiz.updateBillNoChangeTime(scmSyncData, param);
							}else{
								scmSyncTaskInfo.setTaskStatus("F");
								scmSyncTaskInfo.setStatusMessage(errorMsg);
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
								scmSyncData.setSynchron("F");
								scmSyncData.setErrorMessage(errorMsg);
								scmSyncDataBiz.updateBillNoChangeTime(scmSyncData, param);
							}
						} catch (Exception e) {
							log.error(e);
							String errorMsg = e.getMessage();
							if(StringUtils.isNotBlank(errorMsg) && errorMsg.length()>255){
								errorMsg = StringUtils.substring(errorMsg, 0, 255-1);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncData = scmSyncDataBiz.select(scmSyncData.getId(), param);
							scmSyncTaskInfo.setTaskStatus("F");
							scmSyncTaskInfo.setStatusMessage(errorMsg);
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfo.setEndDate(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							scmSyncData.setSynchron("F");
							scmSyncData.setErrorMessage(errorMsg);
							scmSyncDataBiz.updateBillNoChangeTime(scmSyncData, param);
							continue;
						}
					}else if(scmSyncData != null && "ScmIndustryGroup".equalsIgnoreCase(scmSyncData.getBillType()) && "Y".equalsIgnoreCase(scmSyncData.getSynchron())){
						scmSyncTaskInfo.setTaskStatus("C");
						scmSyncTaskInfo.setUpdateTime(new Date());
						scmSyncTaskInfo.setEndDate(new Date());
						scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
					}
				}
			}else{
				log.info("不存在需要推送的行业分类！");
			}
		}else{
			log.info("当前管理单元未绑定需求方！");
		}
	}
	
	public String pushIndustryGroupToSupplierPlat(AppInfo appInfo,long industryGroupId, String taskSource,Param param) {
		if(industryGroupId > 0){
			ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(param.getControlUnitNo(), param);
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0){
				long demanderId = scmSupplierDemander.getDemanderId();
				List<ScmIndustryGroup> scmIndustryGroupTempList = new ArrayList<ScmIndustryGroup>();
				ScmIndustryGroup tempScmIndustryGroup = new ScmIndustryGroup(true);
				tempScmIndustryGroup.setId(industryGroupId);
				scmIndustryGroupTempList.add(tempScmIndustryGroup);
				CommonBean scmIndustryGroupCommonBean = new CommonBean();
				scmIndustryGroupCommonBean.setList(scmIndustryGroupTempList);
				scmIndustryGroupCommonBean = scmIndustryGroupBiz.select(scmIndustryGroupCommonBean, param);
				if (scmIndustryGroupCommonBean != null) {
					List<ScmIndustryGroup> scmIndustryGroupList = new ArrayList<ScmIndustryGroup>();
					List<ScmIndustryGroupQualifyType2> scmIndustryGroupQualifyTypeList = new ArrayList<ScmIndustryGroupQualifyType2>();
					if(scmIndustryGroupCommonBean.getList() != null && !scmIndustryGroupCommonBean.getList().isEmpty()){
						scmIndustryGroupList = (List<ScmIndustryGroup>) scmIndustryGroupCommonBean.getList();
					}
					if(scmIndustryGroupCommonBean.getList2() != null && !scmIndustryGroupCommonBean.getList2().isEmpty()){
						scmIndustryGroupQualifyTypeList = (List<ScmIndustryGroupQualifyType2>) scmIndustryGroupCommonBean.getList2();
					}
					if(scmIndustryGroupList != null && !scmIndustryGroupList.isEmpty()
							&& scmIndustryGroupQualifyTypeList != null && !scmIndustryGroupQualifyTypeList.isEmpty()){
						if(appInfo == null){
							appInfo = new AppInfo(AppInfo.APP_NAME,param.getControlUnitNo(),param.getControlUnitNo());
							appInfo = getAPP(appInfo);
						}
						List<SupplierPlatIndustryGroupType> supplierPlatIndustryGroupTypeList = new ArrayList<SupplierPlatIndustryGroupType>();
						SupplierPlatIndustryGroup supplierPlatIndustryGroup = new SupplierPlatIndustryGroup();
						ScmIndustryGroup scmIndustryGroup = scmIndustryGroupList.get(0);
						supplierPlatIndustryGroup.setBaId(demanderId);
						supplierPlatIndustryGroup.setGroupCode(scmIndustryGroup.getClassCode());
						supplierPlatIndustryGroup.setGroupCodeName(scmIndustryGroup.getClassName());
						for(ScmIndustryGroupQualifyType2 scmIndustryGroupQualifyType : scmIndustryGroupQualifyTypeList){
							SupplierPlatIndustryGroupType supplierPlatIndustryGroupType = new SupplierPlatIndustryGroupType();
							supplierPlatIndustryGroupType.setRequired(scmIndustryGroupQualifyType.isRequired());
							supplierPlatIndustryGroupType.setTypeCode(scmIndustryGroupQualifyType.getTypeCode());
							supplierPlatIndustryGroupType.setTypeCodeName(scmIndustryGroupQualifyType.getTypeName());
							supplierPlatIndustryGroupTypeList.add(supplierPlatIndustryGroupType);
						}
						supplierPlatIndustryGroup.setQualifieTypeList(supplierPlatIndustryGroupTypeList);
						String data = getData(appInfo, 0, "/isp/qualifieGroupType/sync", JSONObject.toJSONString(supplierPlatIndustryGroup));
						log.info("接口返回："+data);
						JSONObject jsonObject = JSON.parseObject(data);
						if(jsonObject != null && "0".equals(String.valueOf(jsonObject.get("errCode")))){
	
						}else{
							return String.valueOf(jsonObject.get("errText"));
						}
					}
				}else{
					log.info("未查询到行业分类！");
					return "未查询到行业分类！";
				}
			}else{
				log.info("当前管理单元未绑定需求方！");
				return "当前管理单元未绑定需求方！";
			}
		}else{
			
		}
		return "";
	}
	
	public void batchPushInviteEventToSupplierPlat(AppInfo appInfo) {
		OperationParam param = new OperationParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		param.setUsrCode(appInfo.getUsrCode());
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("industryQualifyPush");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(appInfo.getControlUnitNo(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && systemTask.getSize() > 0){
			Page page=new Page();
			page.setModelClass(ScmSyncTaskInfo2.class);
			page.setShowCount(systemTask.getSize());
			ArrayList argList = new ArrayList();
	        argList.add("taskCode="+"SCM_ESP");
	        argList.add("taskAction=" + "Push");
	        argList.add("taskType=" + "supplierEventPush");
	        argList.add("productCode=" + param.getProductCode());
	        argList.add("taskOwner=" + "ESP");
	        argList.add("taskStatus=" + "W");
	        argList.add("controlUnitNo=" + param.getControlUnitNo());
	        List<ScmSyncTaskInfo2> scmSyncTaskInfoList = scmSyncTaskInfoBiz.queryPage(page, argList, "findPengdingTaskPage", param);
			if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
				int count = 0;
				for(int i=0;i<scmSyncTaskInfoList.size();i++){
					ScmSyncTaskInfo2 scmSyncTaskInfo = scmSyncTaskInfoList.get(i);
					ScmSyncData scmSyncData = scmSyncDataBiz.select(scmSyncTaskInfo.getSyncDataId(), param);
					if(scmSyncData != null && "ScmSupplierEvent".equalsIgnoreCase(scmSyncData.getBillType()) && count<systemTask.getSize()){
						count++;
						try {
							scmSyncTaskInfo.setTaskStatus("D");
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							String errorMsg = "";
							if(StringUtils.equals(scmSyncData.getSyncType(), "delete")){
								errorMsg = billDeleteSync(appInfo, scmSyncData.getPlatSupplierId(), scmSupplierDemander.getDemanderId(), scmSyncData.getBillNo(), "ScmSupplierEvent", param);
							}else{
								errorMsg = pushInviteEventToSupplierPlat(appInfo, scmSyncData.getDataId(), scmSyncTaskInfo.getTaskSource(), param);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncData = scmSyncDataBiz.select(scmSyncData.getId(), param);
							if(StringUtils.isBlank(errorMsg)){
								scmSyncTaskInfo.setTaskStatus("S");
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
								scmSyncData.setSynchron("Y");
								scmSyncData.setSyncTime(new Date());
								scmSyncData.setErrorMessage(null);
								scmSyncDataBiz.updateBillNoChangeTime(scmSyncData, param);
							}else{
								scmSyncTaskInfo.setTaskStatus("F");
								scmSyncTaskInfo.setStatusMessage(errorMsg);
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
								scmSyncData.setSynchron("F");
								scmSyncData.setErrorMessage(errorMsg);
								scmSyncDataBiz.updateBillNoChangeTime(scmSyncData, param);
							}
						} catch (Exception e) {
							log.error(e);
							String errorMsg = e.getMessage();
							if(StringUtils.isNotBlank(errorMsg) && errorMsg.length()>255){
								errorMsg = StringUtils.substring(errorMsg, 0, 255-1);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncData = scmSyncDataBiz.select(scmSyncData.getId(), param);
							scmSyncTaskInfo.setTaskStatus("F");
							scmSyncTaskInfo.setStatusMessage(errorMsg);
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfo.setEndDate(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							scmSyncData.setSynchron("F");
							scmSyncData.setErrorMessage(errorMsg);
							scmSyncDataBiz.updateBillNoChangeTime(scmSyncData, param);
							continue;
						}
					}else if(scmSyncData != null && "ScmSupplierEvent".equalsIgnoreCase(scmSyncData.getBillType()) && "Y".equalsIgnoreCase(scmSyncData.getSynchron())){
						scmSyncTaskInfo.setTaskStatus("C");
						scmSyncTaskInfo.setUpdateTime(new Date());
						scmSyncTaskInfo.setEndDate(new Date());
						scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
					}
				}
			}else{
				log.info("不存在需要推送的邀请码设置！");
			}
		}else{
			log.info("当前管理单元未绑定需求方！");
		}
	}
	
	public String pushInviteEventToSupplierPlat(AppInfo appInfo,long inviteEventId, String taskSource,Param param) {
		if(inviteEventId > 0){
			ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(param.getControlUnitNo(), param);
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0){
				long demanderId = scmSupplierDemander.getDemanderId();
				ScmSupplierEvent2 scmSupplierEvent = scmSupplierEventBiz.select(inviteEventId, param);
				if (scmSupplierEvent != null) {
					if(appInfo == null){
						appInfo = new AppInfo(AppInfo.APP_NAME,param.getControlUnitNo(),param.getControlUnitNo());
						appInfo = getAPP(appInfo);
					}
					SupplierPlatinviteEvent supplierPlatinviteEvent = new SupplierPlatinviteEvent();
					supplierPlatinviteEvent.setBeginDate(scmSupplierEvent.getBegDate());
					supplierPlatinviteEvent.setCode(scmSupplierEvent.getEventCode());
					supplierPlatinviteEvent.setDemanderId(demanderId);
					supplierPlatinviteEvent.setDescription(scmSupplierEvent.getRemarks());
					supplierPlatinviteEvent.setEndDate(scmSupplierEvent.getEndDate());
					supplierPlatinviteEvent.setFlag(scmSupplierEvent.isFlag());
					supplierPlatinviteEvent.setName(scmSupplierEvent.getEventTypeName());
					String data = getData(appInfo, 0, "/isp/inviteEvent/sync", JSONObject.toJSONString(supplierPlatinviteEvent));
					log.info("接口返回："+data);
					JSONObject jsonObject = JSON.parseObject(data);
					if(jsonObject != null && "0".equals(String.valueOf(jsonObject.get("errCode")))){

					}else{
						return String.valueOf(jsonObject.get("errText"));
					}
				}else{
					log.info("未查询到邀请码设置！");
					return "未查询到邀请码设置！";
				}
			}else{
				log.info("当前管理单元未绑定需求方！");
				return "当前管理单元未绑定需求方！";
			}
		}else{
			
		}
		return "";
	}
	
	public void batchGetQualificationInfo(AppInfo appInfo) {
		OperationParam param = new OperationParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		param.setUsrCode(appInfo.getUsrCode());
		param.setUsrName("供应商");
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("qualificationInfoPull");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(appInfo.getControlUnitNo(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && systemTask.getSize() > 0){
			Page page=new Page();
			page.setModelClass(ScmSyncTaskInfo2.class);
			page.setShowCount(systemTask.getSize());
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKCODE, QueryParam.QUERY_EQ,"SCM_ESP"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKTYPE, QueryParam.QUERY_EQ,systemTask.getTaskType()));
			page.getParam().put(ScmSyncTaskInfo2.FN_PRODUCTCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_PRODUCTCODE, QueryParam.QUERY_EQ,"iSCM"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKOWNER,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKOWNER, QueryParam.QUERY_EQ,"ESP"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKSTATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKSTATUS, QueryParam.QUERY_EQ,"W"));
			page.getParam().put(ScmSyncTaskInfo2.FN_CONTROLUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmSyncTaskInfo2> scmSyncTaskInfoList = scmSyncTaskInfoBiz.findPage(page, param);
			if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
				int count = 0;
				for(int i=0;i<scmSyncTaskInfoList.size();i++){
					ScmSyncTaskInfo2 scmSyncTaskInfo = scmSyncTaskInfoList.get(i);
					if(count<systemTask.getSize()){
						count++;
						try {
							scmSyncTaskInfo.setTaskStatus("D");
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							String errorMsg = getQualificationInfo(appInfo,systemTask,param);
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							if(StringUtils.isBlank(errorMsg)){
								scmSyncTaskInfo.setTaskStatus("S");
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							}else{
								scmSyncTaskInfo.setTaskStatus("F");
								scmSyncTaskInfo.setStatusMessage(errorMsg);
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							}
						} catch (Exception e) {
							log.error(e);
							String errorMsg = e.getMessage();
							if(StringUtils.isNotBlank(errorMsg) && errorMsg.length()>255){
								errorMsg = StringUtils.substring(errorMsg, 0, 255-1);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncTaskInfo.setTaskStatus("F");
							scmSyncTaskInfo.setStatusMessage(errorMsg);
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfo.setEndDate(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							continue;
						}
					}
				}
			}else{
				log.info("不存在拉取资质信息任务！");
			}
		}else{
			log.info("当前管理单元未绑定需求方！");
		}
	}
	
	public String getQualificationInfo(AppInfo appInfo,ScmSystemTask systemTask,Param param) {
		param.setUsrCode(appInfo.getUsrCode());
		List<Long> systemIdList = new ArrayList();
		systemIdList.add(170L);
		param.setSystemIdList(systemIdList);
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(param.getControlUnitNo(), param);
		ScmSystemTaskExecTime scmSystemTaskExecTime = scmSystemTaskExecTimeBiz.selectByTaskType(param.getControlUnitNo(), systemTask.getTaskType(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && StringUtils.isNotBlank(scmSupplierDemander.getUniqueNo())){
			String updateTime = (scmSystemTaskExecTime==null || StringUtils.isEmpty(scmSystemTaskExecTime.getExecTime())) ? FormatUtils.fmtDateTimeMS(CalendarUtil.relativeDate(new Date(), -1)) : scmSystemTaskExecTime.getExecTime();
			Date updateTimeStamp = FormatUtils.parseDateTimeMs(updateTime);
			String params = "{\"demanderId\": "+scmSupplierDemander.getDemanderId()+",\"updateTimeStamp\": \""+updateTime+"\"}";
			String data = getDataPage(appInfo, "/isp/qualifieInfo/queryList", params, -1, Integer.MAX_VALUE);
			JSONObject jsonObject = JSON.parseObject(data);
			boolean changeTimeStamp = true;
			if(jsonObject != null && "0".equals(String.valueOf(jsonObject.get("errCode")))){
				JSONArray resultarray = jsonObject.getJSONArray("data");
				HashMap<String,JSONArray> pullMap = new HashMap<String,JSONArray> ();
				if(resultarray != null && !resultarray.isEmpty()){
					StringBuffer md5s = new StringBuffer("");
					StringBuffer qualifieTypeCodes = new StringBuffer("");
					int size = systemTask.getSize() > 0 ? systemTask.getSize() : 20;
					List<Long> platVendorIdList = new ArrayList<>();
					for (int k = 0; k < resultarray.size(); k++) {
						if(platVendorIdList.size() >= size){
							break;
						}
						JSONArray resultDetailArray = resultarray.getJSONObject(k).getJSONArray("qualifieList");
						if(resultDetailArray != null && !resultDetailArray.isEmpty()){
							JSONArray pullArray = new JSONArray();
							String badId="";
							for (int i = 0; i < resultDetailArray.size(); i++) {
								if(!platVendorIdList.contains(Long.parseLong(String.valueOf(resultDetailArray.getJSONObject(i).get("baId"))))){
									platVendorIdList.add(Long.parseLong(String.valueOf(resultDetailArray.getJSONObject(i).get("baId"))));
									badId = String.valueOf(resultDetailArray.getJSONObject(i).get("baId"));
								}
								if(StringUtils.isNotBlank(String.valueOf(resultDetailArray.getJSONObject(i).get("qualifieTypeCode")))
										&& !"null".equalsIgnoreCase(String.valueOf(resultDetailArray.getJSONObject(i).get("qualifieTypeCode")))){
									if(StringUtils.isNotBlank(qualifieTypeCodes.toString())){
										qualifieTypeCodes.append(",");
									}
									qualifieTypeCodes.append(String.valueOf(resultDetailArray.getJSONObject(i).get("qualifieTypeCode")));
								}
								if(StringUtils.isNotBlank(String.valueOf(resultDetailArray.getJSONObject(i).get("fileMD5")))
										&& !"null".equalsIgnoreCase(String.valueOf(resultDetailArray.getJSONObject(i).get("fileMD5")))){
									if(StringUtils.isNotBlank(md5s.toString())){
										md5s.append(",");
									}
									md5s.append(String.valueOf(resultDetailArray.getJSONObject(i).get("fileMD5")));
								}
								pullArray.add(resultDetailArray.getJSONObject(i));
							}
							pullMap.put(badId, pullArray);
						}
					}
					List<ScmQualifieInfo2> updateScmQualifieInfoList = new ArrayList<>();
					HashMap<Long, Object> plavVendorIdMap = new HashMap<Long, Object>();
					for (Map.Entry<String,JSONArray> entry : pullMap.entrySet()) {
						long platVendorId = Long.parseLong(String.valueOf(entry.getKey()));
						if(platVendorId == 0){
							continue;
						}
						ScmSupplierRegInvitation scmSupplierRegInvitation = null;
						if(plavVendorIdMap.containsKey(platVendorId)){
							scmSupplierRegInvitation = (ScmSupplierRegInvitation)plavVendorIdMap.get(platVendorId);
						}else{
							scmSupplierRegInvitation = scmSupplierRegInvitationBiz.selectByPlatVendorIdAndCtrl(platVendorId, param.getControlUnitNo(), param);
							if(scmSupplierRegInvitation == null){
								changeTimeStamp = false;
								continue;
							}else{
								plavVendorIdMap.put(platVendorId, scmSupplierRegInvitation);
							}
						}
						List<ScmQualifieInfo2> scmQualifieInfoList = scmQualifieInfoBiz.selectByVendorId(scmSupplierRegInvitation.getVendorId(), param);
						JSONArray pullArray = entry.getValue();
						HashMap<String,ScmQualifieInfo2> qualifieTypeMap = new HashMap<String,ScmQualifieInfo2>();
						List<ScmQualifieInfo2> scmQualifieInfoList2 = new ArrayList();
						for (int i = 0; i < pullArray.size(); i++) {
							JSONObject billObject = pullArray.getJSONObject(i);
							String fileType = String.valueOf(billObject.get("fileType"));
							if(billObject.get("fileType") == null || "null".equalsIgnoreCase(String.valueOf(billObject.get("fileType")))
									|| StringUtils.isBlank(String.valueOf(billObject.get("fileType")))){
								fileType = "jpg";
							}
							Date tempUpdateTimeStamp = FormatUtils.parseDateTimeMs(String.valueOf(billObject.get("updateTimeStamp")));
							updateTimeStamp = updateTimeStamp.compareTo(tempUpdateTimeStamp) < 0 ? tempUpdateTimeStamp : updateTimeStamp;
							if(!platVendorIdList.contains(Long.parseLong(String.valueOf(billObject.get("baId"))))){
								continue;
							}
							if(!StringUtils.contains("W,RS", String.valueOf(billObject.get("authStatus")))){
								continue;
							}
							if(scmQualifieInfoList == null || scmQualifieInfoList.isEmpty()){
								ScmQualifieInfo2 scmQualifieInfo = qualifieTypeMap.get(String.valueOf(billObject.get("qualifieTypeCode")));
								if(scmQualifieInfo==null) {
									//新增
									scmQualifieInfo = JSON.toJavaObject(billObject,ScmQualifieInfo2.class);
									scmQualifieInfo.setId(0);
									scmQualifieInfo.setVendorId(scmSupplierRegInvitation.getVendorId());
									scmQualifieInfo.setCreator(String.valueOf(billObject.get("createBy")));
									if(billObject.get("createTime") != null){
										scmQualifieInfo.setCreateDate(FormatUtils.parseDateTime(String.valueOf(billObject.get("createTime"))));
									}
									ScmSupplierQualifyType scmSupplierQualifyType = scmSupplierQualifyTypeBiz.selectByCode(String.valueOf(billObject.get("qualifieTypeCode")), param);
									if(scmSupplierQualifyType == null){
										continue;
									}
									scmQualifieInfo.setTypeId(scmSupplierQualifyType.getId());
									scmQualifieInfoBiz.add(scmQualifieInfo, param);
									qualifieTypeMap.put(String.valueOf(billObject.get("qualifieTypeCode")), scmQualifieInfo);
								}
								if(scmQualifieInfo.getId() > 0 && billObject.get("fileName") != null
									&& billObject.get("fileData") != null
									&& billObject.get("fileMD5") != null){
									generateAttachment(scmQualifieInfo.getId(), String.valueOf(billObject.get("fileName")), 
											fileType, String.valueOf(billObject.get("fileData")), String.valueOf(billObject.get("fileMD5")), "ScmQualifieInfo", param);
								}
								scmQualifieInfoList2.add(scmQualifieInfo);
							}else{
								boolean exist = false;
								ScmQualifieInfo2 existScmQualifieInfo = null;
								boolean isExistAttach = false;
								List<ScmBaseAttachment> compareList = new ArrayList<>();
								for(ScmQualifieInfo2 scmQualifieInfo : scmQualifieInfoList){
									if(StringUtils.equals(scmQualifieInfo.getQualifieTypeCode(), String.valueOf(billObject.get("qualifieTypeCode")))){
										exist = true;
										existScmQualifieInfo = scmQualifieInfo;
										HashMap<String, Object> map = new HashMap<String, Object>();
										map.put("billId", scmQualifieInfo.getId());
										map.put("billtype", "ScmQualifieInfo");
										map.put("controlUnitNo", param.getControlUnitNo());
										List<ScmBaseAttachment> scmBaseAttachmentList = scmBaseAttachmentBiz.findBybillTypeId(map, param);
										if(scmBaseAttachmentList != null && !scmBaseAttachmentList.isEmpty()){
											compareList.addAll(scmBaseAttachmentList);
										}
									}
								}
								if((compareList == null || compareList.isEmpty()) && exist && existScmQualifieInfo !=null
										&& billObject.get("fileName") != null
										&& billObject.get("fileData") != null
										&& billObject.get("fileMD5") != null){
									isExistAttach = true;
									generateAttachment(existScmQualifieInfo.getId(), String.valueOf(billObject.get("fileName")), 
											fileType, String.valueOf(billObject.get("fileData")), String.valueOf(billObject.get("fileMD5")), "ScmQualifieInfo", param);
								}
								if(exist && existScmQualifieInfo !=null && !isExistAttach && compareList != null && !compareList.isEmpty()){
									List<ScmBaseAttachment> deleteList = new ArrayList<>();
									for(int j = compareList.size() - 1; j >= 0; j--){
										ScmBaseAttachment scmBaseAttachment = compareList.get(j);
										Map<String, String> attachMap = fileToBase64AndMd5(scmBaseAttachment.getId(),param);
										if(attachMap==null) {
											deleteList.add(scmBaseAttachment);
											continue;
										}
										String attachMd5 = attachMap.get("attachMD5");
										String compareMd5 = String.valueOf(billObject.get("fileMD5"));
										if (attachMap != null && !StringUtils.contains(md5s.toString(), attachMap.get("attachMD5"))) {
											deleteList.add(scmBaseAttachment);
										}else if (attachMap != null && StringUtils.equals(compareMd5, attachMap.get("attachMD5"))){
											isExistAttach = true;
										}
									}
									if(deleteList != null && !deleteList.isEmpty()){
										scmBaseAttachmentBiz.delete(deleteList, param);
									}
								}
								if(exist && existScmQualifieInfo !=null && !isExistAttach && billObject.get("fileName") != null
										&& billObject.get("fileData") != null
										&& billObject.get("fileMD5") != null){
									generateAttachment(existScmQualifieInfo.getId(), String.valueOf(billObject.get("fileName")), 
											fileType, String.valueOf(billObject.get("fileData")), String.valueOf(billObject.get("fileMD5")), "ScmQualifieInfo", param);
								}
								if(exist && existScmQualifieInfo !=null){
									if(billObject.get("updateBy") != null){
										existScmQualifieInfo.setEditor(String.valueOf(billObject.get("updateBy")));
									}
									if(billObject.get("updateTime") != null){
										existScmQualifieInfo.setEditDate(FormatUtils.parseDateTime(String.valueOf(billObject.get("updateTime"))));
									}
									existScmQualifieInfo.setQualificationStatus("W");
									updateScmQualifieInfoList.add(existScmQualifieInfo);
									scmQualifieInfoList2.add(existScmQualifieInfo);
								}
								if(!exist){
									ScmQualifieInfo2 scmQualifieInfo = qualifieTypeMap.get(String.valueOf(billObject.get("qualifieTypeCode")));
									if(scmQualifieInfo==null) {
										//新增
										scmQualifieInfo = JSON.toJavaObject(billObject,ScmQualifieInfo2.class);
										scmQualifieInfo.setId(0);
										scmQualifieInfo.setVendorId(scmSupplierRegInvitation.getVendorId());
										scmQualifieInfo.setCreator(String.valueOf(billObject.get("createBy")));
										if(billObject.get("createTime") != null){
											scmQualifieInfo.setCreateDate(FormatUtils.parseDateTime(String.valueOf(billObject.get("createTime"))));
										}
										ScmSupplierQualifyType scmSupplierQualifyType = scmSupplierQualifyTypeBiz.selectByCode(String.valueOf(billObject.get("qualifieTypeCode")), param);
										if(scmSupplierQualifyType == null){
											continue;
										}
										scmQualifieInfo.setTypeId(scmSupplierQualifyType.getId());
										scmQualifieInfoBiz.add(scmQualifieInfo, param);
										qualifieTypeMap.put(String.valueOf(billObject.get("qualifieTypeCode")), scmQualifieInfo);
									}
									if(scmQualifieInfo.getId() > 0 && billObject.get("fileName") != null
										&& billObject.get("fileData") != null
										&& billObject.get("fileMD5") != null){
										generateAttachment(scmQualifieInfo.getId(), String.valueOf(billObject.get("fileName")), 
												fileType, String.valueOf(billObject.get("fileData")), String.valueOf(billObject.get("fileMD5")), "ScmQualifieInfo", param);
									}
									scmQualifieInfoList2.add(scmQualifieInfo);
								}
							}
						}
						if(scmQualifieInfoList != null && !scmQualifieInfoList.isEmpty()){
							for(ScmQualifieInfo2 scmQualifieInfo : scmQualifieInfoList){
								boolean exist =false;
								for (int i = 0; i < pullArray.size(); i++) {
									JSONObject billObject = pullArray.getJSONObject(i);
									if(StringUtils.equals(scmQualifieInfo.getQualifieTypeCode(), String.valueOf(billObject.get("qualifieTypeCode")))){
										exist = true;
										break;
									}
								}
								if(!exist) {
									scmQualifieInfoBiz.delete(scmQualifieInfo, param);
								}
							}
						}
						if(updateScmQualifieInfoList != null && !updateScmQualifieInfoList.isEmpty()){
							scmQualifieInfoBiz.update(updateScmQualifieInfoList, param);
						}
						scmSupplierQualifieInfoBillBiz.UpdateByQualifieInfo(scmQualifieInfoList2,param);
//						if(!changeTimeStamp){
//							return "";
//						}
						if(scmSystemTaskExecTime==null) {
							scmSystemTaskExecTime = new ScmSystemTaskExecTime(true);
							scmSystemTaskExecTime.setOrgUnitNo(param.getControlUnitNo());
							scmSystemTaskExecTime.setTaskType(systemTask.getTaskType());
							scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTimeStamp));
							scmSystemTaskExecTime.setControlUnitNo(param.getControlUnitNo());
							scmSystemTaskExecTimeBiz.add(scmSystemTaskExecTime, param);
						}else {
							scmSystemTaskExecTime.setExecTime(FormatUtils.fmtDateTimeMS(updateTimeStamp));
							scmSystemTaskExecTimeBiz.update(scmSystemTaskExecTime, param);
						}
					}
				}
			}else{
				return String.valueOf(jsonObject.get("errText"));
			}
		}else{
			log.info("当前管理单元未绑定需求方！");
			return "当前管理单元未绑定需求方！";
		}
		return "";
	}
	
	public void batchPushQualifieInfoToSupplierPlat(AppInfo appInfo) {
		OperationParam param = new OperationParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		param.setUsrCode(appInfo.getUsrCode());
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("qualificationInfoPush");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(appInfo.getControlUnitNo(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && systemTask.getSize() > 0){
			Page page=new Page();
			page.setModelClass(ScmSyncTaskInfo2.class);
			page.setShowCount(systemTask.getSize());
			ArrayList argList = new ArrayList();
	        argList.add("taskCode="+"SCM_ESP");
	        argList.add("taskAction=" + "Push");
	        argList.add("taskType=" + systemTask.getTaskType());
	        argList.add("productCode=" + param.getProductCode());
	        argList.add("taskOwner=" + "ESP");
	        argList.add("taskStatus=" + "W");
	        argList.add("controlUnitNo=" + param.getControlUnitNo());
	        List<ScmSyncTaskInfo2> scmSyncTaskInfoList = scmSyncTaskInfoBiz.queryPage(page, argList, "findPengdingTaskPage", param);
			if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
				int count = 0;
				List<Long> platSupplierIdList = new ArrayList<>();
				for(int i=0;i<scmSyncTaskInfoList.size();i++){
					ScmSyncTaskInfo2 scmSyncTaskInfo = scmSyncTaskInfoList.get(i);
					ScmSyncData scmSyncData = scmSyncDataBiz.select(scmSyncTaskInfo.getSyncDataId(), param);
					if(scmSyncData != null && "ScmQualifieInfo".equalsIgnoreCase(scmSyncData.getBillType()) && count<systemTask.getSize()){
						count++;
						try {
							scmSyncTaskInfo.setTaskStatus("D");
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							if(!platSupplierIdList.contains(scmSyncData.getPlatSupplierId())){
								platSupplierIdList.add(scmSyncData.getPlatSupplierId());
							}else if(scmSyncData != null && "ScmQualifieInfo".equalsIgnoreCase(scmSyncData.getBillType())){
								scmSyncTaskInfo.setTaskStatus("C");
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
								continue;
							}
							String errorMsg = "";
							errorMsg = pushQualifieInfoToSupplierPlat(appInfo, scmSyncData.getPlatSupplierId(), scmSyncTaskInfo.getTaskSource(), param);
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncData = scmSyncDataBiz.select(scmSyncData.getId(), param);
							if(StringUtils.isBlank(errorMsg)){
								scmSyncTaskInfo.setTaskStatus("S");
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
								scmSyncData.setSynchron("Y");
								scmSyncData.setSyncTime(new Date());
								scmSyncData.setErrorMessage(null);
								scmSyncDataBiz.updateBillNoChangeTime(scmSyncData, param);
							}else{
								scmSyncTaskInfo.setTaskStatus("F");
								scmSyncTaskInfo.setStatusMessage(errorMsg);
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
								scmSyncData.setSynchron("F");
								scmSyncData.setErrorMessage(errorMsg);
								scmSyncDataBiz.updateBillNoChangeTime(scmSyncData, param);
							}
						} catch (Exception e) {
							log.error(e);
							String errorMsg = e.getMessage();
							if(StringUtils.isNotBlank(errorMsg) && errorMsg.length()>255){
								errorMsg = StringUtils.substring(errorMsg, 0, 255-1);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncData = scmSyncDataBiz.select(scmSyncData.getId(), param);
							scmSyncTaskInfo.setTaskStatus("F");
							scmSyncTaskInfo.setStatusMessage(errorMsg);
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfo.setEndDate(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							scmSyncData.setSynchron("F");
							scmSyncData.setErrorMessage(errorMsg);
							scmSyncDataBiz.updateBillNoChangeTime(scmSyncData, param);
							continue;
						}
					}else if(scmSyncData != null && "ScmQualifieInfo".equalsIgnoreCase(scmSyncData.getBillType()) && "Y".equalsIgnoreCase(scmSyncData.getSynchron())){
						scmSyncTaskInfo.setTaskStatus("C");
						scmSyncTaskInfo.setUpdateTime(new Date());
						scmSyncTaskInfo.setEndDate(new Date());
						scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
					}
				}
			}else{
				log.info("不存在需要推送的资质明细！");
			}
		}else{
			log.info("当前管理单元未绑定需求方！");
		}
	}
	
	public String pushQualifieInfoToSupplierPlat(AppInfo appInfo,long platVendorId, String taskSource,Param param) {
		if(platVendorId > 0){
			ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(param.getControlUnitNo(), param);
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0){
				ScmSupplierRegInvitation scmSupplierRegInvitation2 = scmSupplierRegInvitationBiz.selectByPlatVendorIdAndCtrl(platVendorId, param.getControlUnitNo(), param);
				if(scmSupplierRegInvitation2 == null || scmSupplierRegInvitation2.getVendorId()<0){
					return "当前资质明细未绑定供应商";
				}
				SupplierPlatQualifieInfo supplierPlatQualifieInfo = new SupplierPlatQualifieInfo();
				supplierPlatQualifieInfo.setBaId(scmSupplierRegInvitation2.getPlatSupplierId());
				supplierPlatQualifieInfo.setDemanderId(scmSupplierDemander.getDemanderId());
				List<ScmQualifieInfo2> scmQualifieInfoList = scmQualifieInfoBiz.selectAttachByVendorId(scmSupplierRegInvitation2.getVendorId(), param);
				if(scmQualifieInfoList != null && !scmQualifieInfoList.isEmpty()){
					List<SupplierPlatQualifieInfoType> supplierPlatQualifieInfoTypeList = new ArrayList<SupplierPlatQualifieInfoType>();
					for(ScmQualifieInfo2 scmQualifieInfo2 : scmQualifieInfoList){
						SupplierPlatQualifieInfoType SupplierPlatQualifieInfoType = new SupplierPlatQualifieInfoType();
						SupplierPlatQualifieInfoType.setReviewer(scmQualifieInfo2.getChecker());
						SupplierPlatQualifieInfoType.setReviewTime(scmQualifieInfo2.getCheckDate());
						SupplierPlatQualifieInfoType.setAuthStatus(scmQualifieInfo2.getQualificationStatus());
						SupplierPlatQualifieInfoType.setQualifieTypeCode(scmQualifieInfo2.getQualifieTypeCode());
						Map<String, String> attachMap = fileToBase64AndMd5(scmQualifieInfo2.getAttachMentId(),param);
						if (attachMap != null) {
							SupplierPlatQualifieInfoType.setFileData(attachMap.get("attachData"));
							SupplierPlatQualifieInfoType.setFileMD5(attachMap.get("attachMD5"));
							SupplierPlatQualifieInfoType.setFileType(scmQualifieInfo2.getAttachMentDocType());
						}
						supplierPlatQualifieInfoTypeList.add(SupplierPlatQualifieInfoType);
					}
					supplierPlatQualifieInfo.setQualifieList(supplierPlatQualifieInfoTypeList);
				}
				String data = getData(appInfo, supplierPlatQualifieInfo.getBaId(), "/isp/qualifieInfo/sync", JSONObject.toJSONString(supplierPlatQualifieInfo));
				log.info("接口返回："+data);
				JSONObject jsonObject = JSON.parseObject(data);
				if(jsonObject != null && "0".equals(String.valueOf(jsonObject.get("errCode")))){

				}else{
					return String.valueOf(jsonObject.get("errText"));
				}
			}else{
				log.info("当前管理单元未绑定需求方！");
				return "当前管理单元未绑定需求方！";
			}
		}else{
			
		}
		return "";
	}
	
	public void batchPushSupplierStatusToSupplierPlat(AppInfo appInfo) {
		OperationParam param = new OperationParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		param.setUsrCode(appInfo.getUsrCode());
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("supplierStatusPush");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(appInfo.getControlUnitNo(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && systemTask.getSize() > 0){
			Page page=new Page();
			page.setModelClass(ScmSyncTaskInfo2.class);
			page.setShowCount(systemTask.getSize());
			ArrayList argList = new ArrayList();
	        argList.add("taskCode="+"SCM_ESP");
	        argList.add("taskAction=" + "Push");
	        argList.add("taskType=" + systemTask.getTaskType());
	        argList.add("productCode=" + param.getProductCode());
	        argList.add("taskOwner=" + "ESP");
	        argList.add("taskStatus=" + "W");
	        argList.add("controlUnitNo=" + param.getControlUnitNo());
	        List<ScmSyncTaskInfo2> scmSyncTaskInfoList = scmSyncTaskInfoBiz.queryPage(page, argList, "findPengdingTaskPage", param);
			if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
				int count = 0;
				for(int i=0;i<scmSyncTaskInfoList.size();i++){
					ScmSyncTaskInfo2 scmSyncTaskInfo = scmSyncTaskInfoList.get(i);
					ScmSyncData scmSyncData = scmSyncDataBiz.select(scmSyncTaskInfo.getSyncDataId(), param);
					if(scmSyncData != null && "ScmSupplier".equalsIgnoreCase(scmSyncData.getBillType()) && count<systemTask.getSize()){
						count++;
						try {
							scmSyncTaskInfo.setTaskStatus("D");
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							String errorMsg = "";
							errorMsg = pushSupplierStatusToSupplierPlat(appInfo, scmSyncData.getDataId(), scmSyncTaskInfo.getTaskSource(), param);
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncData = scmSyncDataBiz.select(scmSyncData.getId(), param);
							if(StringUtils.isBlank(errorMsg)){
								scmSyncTaskInfo.setTaskStatus("S");
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
								scmSyncData.setSynchron("Y");
								scmSyncData.setSyncTime(new Date());
								scmSyncData.setErrorMessage(null);
								scmSyncDataBiz.updateBillNoChangeTime(scmSyncData, param);
							}else{
								scmSyncTaskInfo.setTaskStatus("F");
								scmSyncTaskInfo.setStatusMessage(errorMsg);
								scmSyncTaskInfo.setUpdateTime(new Date());
								scmSyncTaskInfo.setEndDate(new Date());
								scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
								scmSyncData.setSynchron("F");
								scmSyncData.setErrorMessage(errorMsg);
								scmSyncDataBiz.updateBillNoChangeTime(scmSyncData, param);
							}
						} catch (Exception e) {
							log.error(e);
							String errorMsg = e.getMessage();
							if(StringUtils.isNotBlank(errorMsg) && errorMsg.length()>255){
								errorMsg = StringUtils.substring(errorMsg, 0, 255-1);
							}
							scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
							scmSyncData = scmSyncDataBiz.select(scmSyncData.getId(), param);
							scmSyncTaskInfo.setTaskStatus("F");
							scmSyncTaskInfo.setStatusMessage(errorMsg);
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfo.setEndDate(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
							scmSyncData.setSynchron("F");
							scmSyncData.setErrorMessage(errorMsg);
							scmSyncDataBiz.updateBillNoChangeTime(scmSyncData, param);
							continue;
						}
					}else if(scmSyncData != null && "ScmSupplier".equalsIgnoreCase(scmSyncData.getBillType()) && "Y".equalsIgnoreCase(scmSyncData.getSynchron())){
						scmSyncTaskInfo.setTaskStatus("C");
						scmSyncTaskInfo.setUpdateTime(new Date());
						scmSyncTaskInfo.setEndDate(new Date());
						scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
					}
				}
			}else{
				log.info("不存在需要推送的供应商状态！");
			}
		}else{
			log.info("当前管理单元未绑定需求方！");
		}
	}
	
	public String pushSupplierStatusToSupplierPlat(AppInfo appInfo,long vendorId, String taskSource,Param param) {
		if(vendorId > 0){
			ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(param.getControlUnitNo(), param);
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0){
				Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(vendorId, param);
				if(scmsupplier != null){
					ScmSupplierRegInvitation scmSupplierRegInvitation2 = scmSupplierRegInvitationBiz.selectByVendorIdAndCtrl(scmsupplier.getId(), param.getControlUnitNo(), param);
					if(scmSupplierRegInvitation2 == null || scmSupplierRegInvitation2.getPlatSupplierId()<0){
						return "当前供应商未绑定商户平台";
					}
					if(StringUtils.contains("A,R,N", scmsupplier.getStatus()) && StringUtils.contains("O,RJ,N", scmsupplier.getQualificationStatus())){
						String status="";
						if(StringUtils.equals("A", scmsupplier.getStatus())) {
							status="O";
						}else if(StringUtils.contains("R,N", scmsupplier.getStatus())) {
							status=scmsupplier.getStatus();
						} 
						String params = "{\"authStatus\": \""+scmsupplier.getQualificationStatus()+"\",\"demanderId\": "+scmSupplierDemander.getDemanderId()+",\"id\": "+scmSupplierRegInvitation2.getPlatSupplierId()+",\"relationStatus\": \""+status+"\",\"refVendorNo\": \""+scmsupplier.getVendorNo()+"\",\"unionNo\": \""+scmSupplierDemander.getUniqueNo()+"\",\"systemType\": \"iSCM\"}";
						String data = getData(appInfo, scmSupplierRegInvitation2.getPlatSupplierId(), "/isp/businessaccount/syncUpdate", params);
						log.info("接口返回："+data);
						JSONObject jsonObject = JSON.parseObject(data);
						if(jsonObject != null && "0".equals(String.valueOf(jsonObject.get("errCode")))){

						}else{
							return String.valueOf(jsonObject.get("errText"));
						}
					}else{
						return "无需推送";
					}
				}else{
					log.info("未查询到供应商！");
					return "未查询到供应商！";
				}
			}else{
				log.info("当前管理单元未绑定需求方！");
				return "当前管理单元未绑定需求方！";
			}
		}else{
			
		}
		return "";
	}
	
	private boolean checkScmSupplierInfoPull(AppInfo appInfo,Param param){
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("supplierInfoPull");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList != null && !systemTaskList.isEmpty() && systemTaskList.get(0).isFlag()){
			return true;
		}
		return false;
	}
	
	public void batchPushControlUnit(AppInfo appInfo) {
		OperationParam param = new OperationParam();
		param.setOrgUnitNo(appInfo.getOrgUnitNo());
		param.setControlUnitNo(appInfo.getControlUnitNo());
		param.setProductCode("iSCM");
		param.setUsrCode(appInfo.getUsrCode());
		ScmSystemTask systemTask = new ScmSystemTask(true);
		systemTask.setTaskType("controlUnitPush");
		systemTask.setOrgUnitNo(appInfo.getOrgUnitNo());
		List<ScmSystemTask> systemTaskList = scmSystemTaskBiz.selectByTask(systemTask, param);
		if(systemTaskList == null || systemTaskList.isEmpty()){
			return;
		}else{
			systemTask = systemTaskList.get(0);
		}
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(appInfo.getControlUnitNo(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0){
			Page page=new Page();
			page.setModelClass(ScmSyncTaskInfo2.class);
			page.setShowCount(1);
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKCODE, QueryParam.QUERY_EQ,"SCM_ESP"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKTYPE, QueryParam.QUERY_EQ,systemTask.getTaskType()));
			page.getParam().put(ScmSyncTaskInfo2.FN_PRODUCTCODE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_PRODUCTCODE, QueryParam.QUERY_EQ,"iSCM"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKOWNER,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKOWNER, QueryParam.QUERY_EQ,"ESP"));
			page.getParam().put(ScmSyncTaskInfo2.FN_TASKSTATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_TASKSTATUS, QueryParam.QUERY_EQ,"W"));
			page.getParam().put(ScmSyncTaskInfo2.FN_CONTROLUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmSyncTaskInfo2.class) + "." +ScmSyncTaskInfo2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmSyncTaskInfo2> scmSyncTaskInfoList = scmSyncTaskInfoBiz.findPage(page, param);
			if(scmSyncTaskInfoList != null && !scmSyncTaskInfoList.isEmpty()){
				for(int i=0;i<scmSyncTaskInfoList.size();i++){
					ScmSyncTaskInfo2 scmSyncTaskInfo = scmSyncTaskInfoList.get(i);
					try {
						scmSyncTaskInfo.setTaskStatus("D");
						scmSyncTaskInfo.setUpdateTime(new Date());
						scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
						String errorMsg = pushControlUnitToSupplierPlat(appInfo,systemTask,param);
						scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
						if(StringUtils.isBlank(errorMsg)){
							scmSyncTaskInfo.setTaskStatus("S");
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfo.setEndDate(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
						}else{
							scmSyncTaskInfo.setTaskStatus("F");
							scmSyncTaskInfo.setStatusMessage(errorMsg);
							scmSyncTaskInfo.setUpdateTime(new Date());
							scmSyncTaskInfo.setEndDate(new Date());
							scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
						}
					} catch (Exception e) {
						log.error(e);
						String errorMsg = e.getMessage();
						if(StringUtils.isNotBlank(errorMsg) && errorMsg.length()>255){
							errorMsg = StringUtils.substring(errorMsg, 0, 255-1);
						}
						scmSyncTaskInfo = scmSyncTaskInfoBiz.select(scmSyncTaskInfo.getId(), param);
						scmSyncTaskInfo.setTaskStatus("F");
						scmSyncTaskInfo.setStatusMessage(errorMsg);
						scmSyncTaskInfo.setUpdateTime(new Date());
						scmSyncTaskInfo.setEndDate(new Date());
						scmSyncTaskInfoBiz.update(scmSyncTaskInfo, param);
						continue;
					}
				}
			}else{
				log.info("不存在拉取资质信息任务！");
			}
		}else{
			log.info("当前管理单元未绑定需求方！");
		}
	}
	
	public String pushControlUnitToSupplierPlat(AppInfo appInfo,ScmSystemTask systemTask,Param param) {
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(param.getControlUnitNo(), param);
		if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0){
			StringBuffer msg = new StringBuffer("");
			//采购组织
			Page orgPurchasepage = new Page();
			orgPurchasepage.setModelClass(OrgPurchase2.class);
			orgPurchasepage.setShowCount(Integer.MAX_VALUE);
	    	ArrayList orgPurchaseArgList = new ArrayList();
	    	orgPurchaseArgList.add("orgUnitNo=" + param.getControlUnitNo());
	    	orgPurchaseArgList.add("controlUnitNo=" + param.getControlUnitNo());
	        List<OrgPurchase2> orgPurchaseList = orgPurchaseBiz.queryPage(orgPurchasepage, orgPurchaseArgList, "selectByOrgChildPage", param);
	        if(orgPurchaseList != null && !orgPurchaseList.isEmpty()){
				SupplierPlatPurchase supplierPlatPurchase = new SupplierPlatPurchase();
				supplierPlatPurchase.setBaId(scmSupplierDemander.getDemanderId());
				List<SupplierPlatPurchaseEntry> supplierPlatPurchaseEntryList = new ArrayList<SupplierPlatPurchaseEntry>();
				for(OrgPurchase2 orgPurchase : orgPurchaseList){
					SupplierPlatPurchaseEntry supplierPlatPurchaseEntry = new SupplierPlatPurchaseEntry();
					supplierPlatPurchaseEntry.setFlag(1);
					supplierPlatPurchaseEntry.setIsBizUnit((orgPurchase.isIsBizUnit() ? 1 : 0));
					supplierPlatPurchaseEntry.setIsSealUp((orgPurchase.isIsSealUp() ? 1 : 0));
					supplierPlatPurchaseEntry.setLeader(orgPurchase.getLeader());
					supplierPlatPurchaseEntry.setLongNo(orgPurchase.getLongNo());
					supplierPlatPurchaseEntry.setOrgUnitNo(orgPurchase.getOrgUnitNo());
					supplierPlatPurchaseEntry.setPorgUnitNo(orgPurchase.getPorgUnitNo());
					supplierPlatPurchaseEntry.setRefOrgUnitName(orgPurchase.getOrgUnitName());
					supplierPlatPurchaseEntry.setSealUpDate(orgPurchase.getSealUpDate());
					supplierPlatPurchaseEntryList.add(supplierPlatPurchaseEntry);
				}
				supplierPlatPurchase.setOrgPurchaseList(supplierPlatPurchaseEntryList);
				String data = getData(appInfo, 0, "/isp/businessOrgPurchase/sync", JSONObject.toJSONString(supplierPlatPurchase));
				log.info("接口返回："+data);
				JSONObject jsonObject = JSON.parseObject(data);
				if(jsonObject != null && "0".equals(String.valueOf(jsonObject.get("errCode")))){

				}else{
					msg.append(String.valueOf(jsonObject.get("errText")));
				}
	        }
	        //库存组织
	        Page orgStoragepage = new Page();
			orgStoragepage.setModelClass(OrgStorage2.class);
			orgStoragepage.setShowCount(Integer.MAX_VALUE);
	    	ArrayList orgStorageArgList = new ArrayList();
	    	orgStorageArgList.add("orgUnitNo=" + param.getControlUnitNo());
	    	orgStorageArgList.add("controlUnitNo=" + param.getControlUnitNo());
	        List<OrgStorage2> orgStorageList = orgStorageBiz.queryPage(orgStoragepage, orgStorageArgList, "selectByOrgChildPage", param);
	        if(orgStorageList != null && !orgStorageList.isEmpty()){
				SupplierPlatStorage supplierPlatStorage = new SupplierPlatStorage();
				supplierPlatStorage.setBaId(scmSupplierDemander.getDemanderId());
				List<SupplierPlatStorageEntry> supplierPlatStorageEntryList = new ArrayList<SupplierPlatStorageEntry>();
				for(OrgStorage2 orgStorage : orgStorageList){
					SupplierPlatStorageEntry supplierPlatStorageEntry = new SupplierPlatStorageEntry();
					supplierPlatStorageEntry.setAddress(orgStorage.getAddress());
					supplierPlatStorageEntry.setFlag(1);
					supplierPlatStorageEntry.setIsBizUnit((orgStorage.isIsBizUnit() ? 1 : 0));
					supplierPlatStorageEntry.setIsSealUp((orgStorage.isIsSealUp() ? 1 : 0));
					supplierPlatStorageEntry.setLeader(orgStorage.getLeader());
					supplierPlatStorageEntry.setLongNo(orgStorage.getLongNo());
					supplierPlatStorageEntry.setOrgUnitNo(orgStorage.getOrgUnitNo());
					supplierPlatStorageEntry.setPorgUnitNo(orgStorage.getPorgUnitNo());
					supplierPlatStorageEntry.setRefOrgUnitName(orgStorage.getOrgUnitName());
					supplierPlatStorageEntry.setSealUpDate(orgStorage.getSealUpDate());
					supplierPlatStorageEntryList.add(supplierPlatStorageEntry);
				}
				supplierPlatStorage.setOrgStorageList(supplierPlatStorageEntryList);
				String data = getData(appInfo, 0, "/isp/businessOrgStorage/sync", JSONObject.toJSONString(supplierPlatStorage));
				log.info("接口返回："+data);
				JSONObject jsonObject = JSON.parseObject(data);
				if(jsonObject != null && "0".equals(String.valueOf(jsonObject.get("errCode")))){

				}else{
					msg.append(String.valueOf(jsonObject.get("errText")));
				}
	        }
	        if(StringUtils.isNotBlank(msg.toString())){
	        	return msg.toString();
	        }
		}else{
			log.info("当前管理单元未绑定需求方！");
			return "当前管理单元未绑定需求方！";
		}
		return "";
	}
	
	public void excuteTaskThread(ScmSystemTask systemTask) {
		AppInfo appInfo = new AppInfo(AppInfo.APP_NAME,systemTask.getOrgUnitNo(),systemTask.getControlUnitNo());
		appInfo = getAPP(appInfo);
		if(systemTask != null && appInfo != null){
			if(systemTask.getTaskName().startsWith(ScmPlatSupplierIdGetJob.class.getSimpleName())){
				batchUpdatePlatSupplierId(appInfo);
			}else if(systemTask.getTaskName().startsWith(ScmSupplierConfirmDataGetJob.class.getSimpleName())){
				batchUpdateInvPurInWarehsBillConfirmStatus(appInfo);
				batchPushReplyDataToSupplierPlat(appInfo);
			}else if(systemTask.getTaskName().startsWith(ScmPurOrderPushJob.class.getSimpleName())){
				batchPushPurOrderToSupplierPlat(appInfo);
			}else if(systemTask.getTaskName().startsWith(ScmInvPurInWarehsBillPushJob.class.getSimpleName())){
				batchPushInvPurInWarehsBillToSupplierPlat(appInfo);
			}else if(systemTask.getTaskName().startsWith(ScmEspCommonTaskJob.class.getSimpleName())){
				eSPComTaskScan(appInfo);
			}else if(systemTask.getTaskName().startsWith(ScmSupplierConfirmRulePushJob.class.getSimpleName())){
				batchPushConfirmRuleToSupplierPlat(appInfo);
			}else if(systemTask.getTaskName().startsWith(ScmPurPricePushJob.class.getSimpleName())){
				batchPushPurPriceToSupplierPlat(appInfo);
			}else if(systemTask.getTaskName().startsWith(ScmBusinessQuotationGetJob.class.getSimpleName())){
				batchUpdateSupplierQuotation(appInfo);
			}else if(systemTask.getTaskName().startsWith(ScmIndustryQualifyPushJob.class.getSimpleName())){
				batchPushIndustryGroupToSupplierPlat(appInfo);
				batchPushInviteEventToSupplierPlat(appInfo);
			}else if(systemTask.getTaskName().startsWith(ScmQualificationInfoPullJob.class.getSimpleName())){
				batchGetQualificationInfo(appInfo);
			}else if(systemTask.getTaskName().startsWith(ScmQualificationInfoPushJob.class.getSimpleName())){
				batchPushQualifieInfoToSupplierPlat(appInfo);
			}else if(systemTask.getTaskName().startsWith(ScmSupplierStatusPushJob.class.getSimpleName())){
				batchPushSupplierStatusToSupplierPlat(appInfo);
			}else if(systemTask.getTaskName().startsWith(ScmControlUnitPushJob.class.getSimpleName())){
				batchPushControlUnit(appInfo);
			}
		}
	}
	
	public Map<String, String> fileToBase64AndMd5(long attachmentId,Param param) {
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = null;
		byte[] data = null;
		// 获取图片
		if (attachmentId >0) {
			ScmBaseAttachment scmBaseAttachment = scmBaseAttachmentBiz.select(attachmentId, param);
			if (scmBaseAttachment != null) {
				if (StringUtils.isNotBlank(scmBaseAttachment.getFilePath())) {
					inputStream = mongoDBImageBiz.select(ISCMDBNAME,
							ClassUtils.getFinalModelSimpleName(scmBaseAttachment), scmBaseAttachment.getFilePath());
				}
				if (inputStream != null) {
					try {
						byte[] buffer = new byte[2048];
						int i = -1;
						ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
						while ((i = inputStream.read(buffer)) != -1) {
							swapStream.write(buffer, 0, i);
						}
						data = swapStream.toByteArray();
						swapStream.close();
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (data != null) {
					String md5Hex = DigestUtils.md5Hex(data);
					BASE64Encoder encoder = new BASE64Encoder();
					String encode = encoder.encode(data);
					map.put("attachData", encode);
					map.put("attachMD5", md5Hex);
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		return map;
	}
	
	private boolean generateAttachment(long billId,String fileName,String fileType,String fileData,String fileMD5,String modelName,Param param){
		// 上传文件路径
		String sourceFileName = fileName+"."+fileType;
		fileName = fileName+"."+fileType;
		fileName = fileName.substring(fileName.lastIndexOf("/") + 1);// 获得上传文件的文件名
		Integer rdm = new Random().nextInt(10000);
		String saveName = FileTransferUtil.getInstance().getDateTimeString(true) + rdm.toString()
				+ fileName.substring(fileName.indexOf('.'));
		String mongodbSaveId = "";
		try {
			Decoder decoder = Base64.getDecoder();
			ByteArrayInputStream stringInputStream = new ByteArrayInputStream(decoder.decode(fileData.getBytes()));
			mongodbSaveId = mongoDBImageBiz.save(ISCMDBNAME, ClassUtils.getFinalModelSimpleName(ScmBaseAttachment.class), stringInputStream, saveName, fileName.substring(fileName.lastIndexOf(".")+1));
			ScmBaseAttachment scmBaseAttachment =  new ScmBaseAttachment();
            scmBaseAttachment.setBillId(billId);
            scmBaseAttachment.setBilltype(modelName);
            scmBaseAttachment.setFileName(sourceFileName);
            scmBaseAttachment.setFilePath(mongodbSaveId);
            scmBaseAttachment.setDocType(fileType);
            scmBaseAttachment.setCreator(param.getUsrCode());
            scmBaseAttachment.setCreateTime(new Date());
            scmBaseAttachmentBiz.add(scmBaseAttachment, param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		return true;
	}
	
	private void sendSupplierQualityAuditMsg(Scmsupplier2 scmsupplier,Param param){
//		Page page = new Page();
//		page.setPagePos(1);
//		page.setModelClass(ScmSupplierAuditReceiver.class);
//		page.setShowCount(Integer.MAX_VALUE);
//		List<ScmSupplierAuditReceiver> scmSupplierAuditReceiverList= scmSupplierAuditReceiverBiz.findPage(page, param);
//		Page usrPage = new Page();
//		usrPage.setModelClass(Usr2.class);
//		usrPage.setShowCount(Integer.MAX_VALUE);
//		List<String> usrArglist = new ArrayList<>();
//		usrArglist.add("operationId=809123294");
//		usrArglist.add("bindWechat=Y");
//		usrArglist.add("controlUnitNo="+param.getControlUnitNo());
//		List<Usr2> usrList = usrBiz.queryPage(usrPage, usrArglist, "selectByOperationPage", param);
//		if(scmSupplierAuditReceiverList != null && !scmSupplierAuditReceiverList.isEmpty()
//				&& usrList != null && !usrList.isEmpty()){
//			String usrCodeList="";
//			for(ScmSupplierAuditReceiver scmSupplierAuditReceiver : scmSupplierAuditReceiverList){
//				for(Usr2 usr : usrList){
//					if(StringUtils.equals(scmSupplierAuditReceiver.getCode(), usr.getCode())){
//						if(StringUtils.isNotBlank(usrCodeList)){
//							usrCodeList = usrCodeList+",";
//						}
//						usrCodeList = usrCodeList+scmSupplierAuditReceiver.getCode();
//					}
//				}
//			}
//			if(StringUtils.isNotBlank(usrCodeList)){
//				SupplierParams SupplierParams = new SupplierParams();
//				SupplierParams.setVendorNo(scmsupplier.getVendorNo());
//				AuditMsgUtil.sendSupplierAuditMsg("ScmSupplier","S",scmsupplier,SupplierParams, usrCodeList, param);
//			}
//		}
	}
}
