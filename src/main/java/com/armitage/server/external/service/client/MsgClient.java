package com.armitage.server.external.service.client;

import java.io.IOException;
import java.net.ConnectException;
import java.util.HashMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.HttpClientUtils;
import com.armitage.server.system.util.AppServiceUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MsgClient extends BaseClient {
	private static Log log = LogFactory.getLog(MsgClient.class);
	private static final int DEFAULT_TIME_OUT = 8000;
	private static String URL = "http://192.168.3.159:8580/iMsgService/gateway";

	private static Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss") .create();
	private static String user = "lcsc";
	private static String password = DigestUtils.md5Hex("xcopy");
	private static String orgNo = null;
	
	public static void getConParam (Param param) {
		URL = AppServiceUtil.getUrl(param.getOrgUnitNo(), "iMsg", param);
		user = AppServiceUtil.getAppServiceParamValue(param.getOrgUnitNo(), "iMsg", "usrCode", param);
		password = DigestUtils.md5Hex(AppServiceUtil.getAppServiceParamValue(param.getOrgUnitNo(), "iMsg", "pass", param));
		orgNo = DigestUtils.md5Hex(AppServiceUtil.getAppServiceParamValue(param.getOrgUnitNo(), "iMsg", "orgUnitNo", param));
	}
	
	/**
	 * 获取应用服务地址和参数
	 * @param param
	 */
	public static void getConParams (Param param) {
		URL = AppServiceUtil.getUrl(param.getOrgUnitNo(), "iMsg", param);
		user = AppServiceUtil.getAppServiceParamValue(param.getOrgUnitNo(), "iMsg", "usrCode", param);
		if(StringUtils.isBlank(user))
			user = AppServiceUtil.getAppServiceParamValue(param.getControlUnitNo(), "iMsg", "usrCode", param);
		password = AppServiceUtil.getAppServiceParamValue(param.getOrgUnitNo(), "iMsg", "pass", param);
		if(StringUtils.isBlank(password))
			password = AppServiceUtil.getAppServiceParamValue(param.getControlUnitNo(), "iMsg", "pass", param);
	}
	
	/**
	 * 获取Token
	 * @param url
	 * @param user
	 * @param pwd
	 * @param timeOut
	 * @return
	 */
	private static String getIntegratedRequest(String url,String user,String pwd,int timeOut) {
		String securityToken = "";
		String key = DigestUtils.md5Hex(user+ DigestUtils.md5Hex(pwd));
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("userCode", user);
		map.put("key", key);

		HttpClient client = HttpClientUtils.createClient("utf-8", timeOut);
		try {
			securityToken = HttpClientUtils.post(client, url, "content",
					toXML(map, "applySecurityToken"));
			securityToken = StringUtils.substringBetween(securityToken,
					"\"securityToken\":\"", "\",\"errCode\"");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return securityToken;
	}
	
	/**
	 * 调用接口返回结果集，不带分页
	 * @param actionName
	 * @param params
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public static String getResult(String actionName, String params, Param param) throws AppException {
		String result = "";
		String securityToken =null;
		IntegratedRequest request = new IntegratedRequest();
		HashMap<String,Object> map = new HashMap<String,Object>();

		getConParam(param);
		if(StringUtils.isNotBlank(URL) && StringUtils.isNotBlank(user) && StringUtils.isNotBlank(password)) {
			securityToken = getIntegratedRequest(URL, user, password, DEFAULT_TIME_OUT);
			if(StringUtils.isBlank(securityToken)){
				throw new AppException("iscm.server.MsgClient.gettoken.error");
			}
			request.setOrgUnitNo(orgNo);
			request.setUserCode(user);
			request.setSecurityToken(securityToken);
			request.setIncludeDataDisplayMap(true);
	
			map.put("integratedRequest", gson.toJson(request));
			map.put("params", params);
	
			HttpClient client = HttpClientUtils.createClient("utf-8", 100);
			try {
				log.info("输入：----->" + toXML(map, actionName));
	
				result = HttpClientUtils.post(client, URL, "content",
						toXML(map, actionName));
	
				log.info("返回------>" + result);
			}catch(ConnectException e){
				e.printStackTrace();		
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	/**
	 * 调用接口返回结果集
	 * @param actionName
	 * @param params
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public static String doSubmitMessage(String actionName, String params, Param param) throws AppException {
		String result = "";
		String securityToken =null;
		IntegratedRequest request = new IntegratedRequest();
		HashMap<String,Object> map = new HashMap<String,Object>();

		getConParams(param);
		if(StringUtils.isNotBlank(URL) && StringUtils.isNotBlank(user) && StringUtils.isNotBlank(password)) {
			securityToken = getIntegratedRequest(URL, user, password, DEFAULT_TIME_OUT);
			if(StringUtils.isBlank(securityToken)){
				throw new AppException("iscm.server.MsgClient.gettoken.error");
			}
			request.setOrgUnitNo(param.getOrgUnitNo());
			request.setUserCode(user);
			request.setSecurityToken(securityToken);
			request.setIncludeDataDisplayMap(true);
	
			map.put("integratedRequest", gson.toJson(request));
			map.put("params", params);
	
			HttpClient client = HttpClientUtils.createClient("utf-8", 100);
			try {
				log.info("输入：----->" + toXML(map, actionName));
	
				result = HttpClientUtils.post(client, URL, "content",
						toXML(map, actionName));
	
				log.info("返回------>" + result);
			}catch(ConnectException e){
				e.printStackTrace();		
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
