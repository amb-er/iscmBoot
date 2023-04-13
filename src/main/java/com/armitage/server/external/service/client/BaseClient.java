package com.armitage.server.external.service.client;

import java.io.IOException;
import java.net.ConnectException;
import java.util.HashMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.model.CommonResponse;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.HttpClientUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseClient {
 	private static Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss") .create();

 	/**
	 * 生成post需要的xml文件
	 * @param map
	 * @param action
	 * 服务端执行的方法
	 * @return
	 */
	public static String toXML(HashMap map, String action) {
		StringBuffer buf = new StringBuffer();
		buf.append("<?xml version=\"1.0\"?><doc>");
		buf.append("<action>").append(action).append("</action>");
		String[] keys = (String[]) map.keySet().toArray(new String[0]);
		for (String key : keys) {
			buf.append("<item><name>").append(key).append("</name>")
					.append("<value>").append(map.get(key))
					.append("</value></item>");
		}
		if (!buf.toString().contains("pageNum")) {
			buf.append("<item><name>").append("pageNum").append("</name>")
			.append("<value>").append(-1)
			.append("</value></item>");
		}

		buf.append("</doc>");
		String xml = buf.toString();
		return xml;
	}
	
	
	/**
	 * 接口调用Key生成方法
	 * 
	 * @param userCode
	 * @param md5Pass
	 * @param str
	 * @return key=MD5(用户名+MD5(密码明文)。
	 */
	public static String generateKey(String userCode, String md5Pass) {
		return DigestUtils.md5Hex(userCode + md5Pass);
	}
	
	/**
	 * 
	 * 根据usercode和密码生成securityToken
	 * @return
	 */
	public static String getApplySecurityToken(String URL, String usercode,String password) {
		String pswd = generateKey(usercode,password);
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("userCode", usercode);
		map.put("key",pswd);

		String result="";
		String securityToken="";
		HttpClient client = HttpClientUtils.createClient("utf-8", 100);
		try {
			result = HttpClientUtils.post(client, URL, "content",
					toXML(map, "applySecurityToken"));			

			System.out.println(result);
			
			/* securityToken = result.split(",")[0].split(":")[1]; 
			securityToken = securityToken.replaceAll("\"", "");
			System.out.println(securityToken); */

			securityToken = StringUtils.substringBetween(result,"\"securityToken\":\"", "\",\"errCode");
			System.out.println(securityToken);
				
		}catch (IOException e) {
			e.printStackTrace();
//			throw new AppException(e);
			//调用接口失败,注意检查接口参数!
			throw new AppException("BaseClient.Failed.to.call.interface");

		}catch (IllegalArgumentException e) {
			if(e.getMessage().equals("host parameter is null")){
				throw new AppException("BaseClient.Failed.to.call.interface");
			}else{
				throw new AppException(e.getMessage());
			}
			
		}
		
		return securityToken;
	}
	
	/**
	 * 根据usercode和密码生成securityToken,调crm
	 * @param URL
	 * @param usercode
	 * @param password
	 * @param timeout
	 * @return
	 */
	public static String getApplySecurityToken(String URL, String usercode,String password,int timeout) {
		String pswd = generateKey(usercode,password);
		
		HashMap map = new HashMap();
		map.put("userCode", usercode);
		map.put("key",pswd);

		String result="";
		String securityToken="";
		HttpClient client = HttpClientUtils.createClient("utf-8", timeout);
//		client.setConnectionTimeout(timeout);
		try {
			result = HttpClientUtils.post(client, URL, "content",
					toXML(map, "applySecurityToken"));			
			
			System.out.println(result);

			securityToken = StringUtils.substringBetween(result,"\"securityToken\":\"", "\",\"errCode");
			if (securityToken == null) {
				CommonResponse response = gson.fromJson(result, CommonResponse.class);
				throw new AppException(response.getErrText());
			}
			System.out.println(securityToken);
				
		}catch(ConnectTimeoutException e){
			e.printStackTrace();
			throw new AppException(Message.getMessage("icrs.external.service.client.CrmClient.securityToken.connectError"));
			
		}catch(ConnectException e){
			e.printStackTrace();
			throw new AppException(Message.getMessage("icrs.external.service.client.CrmClient.securityToken.connectError"));
			
		}catch (IOException e) {
			e.printStackTrace();
			throw new AppException(e);

		}catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new AppException(Message.getMessage("icrs.external.service.client.BaseClient.getApplySecurityToken"));
			 
		}
		
		return securityToken;
	}
}
