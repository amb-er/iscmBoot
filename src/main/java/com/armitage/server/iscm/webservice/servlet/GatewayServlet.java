package com.armitage.server.iscm.webservice.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.armitage.server.common.util.JSONUtils;
import com.armitage.server.iscm.webservice.ISCMBusinessService;
import com.armitage.server.iscm.webservice.impl.ISCMBusinessServiceImpl;
import com.armitage.server.webservice.CommonDingdingService;
import com.armitage.server.webservice.SecurityService;
import com.armitage.server.webservice.impl.CommonDingdingServiceImpl;
import com.armitage.server.webservice.impl.SecurityServiceImpl;
import com.google.gson.Gson;

public class GatewayServlet extends HttpServlet {
	private String defaultParamXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Doc><action>接口方法</action><item><name>参数1</name><value>参数值1</value></item><item><name>参数2</name><value>参数值2</value></item><item><name>参数N</name><value>参数值N</value></item></Doc>";
	private static Log log = LogFactory.getLog(GatewayServlet.class);
	private static final String ACTION = "action";
	private static final String INTEGRATE_REQUEST = "integratedRequest";
	private static final String PARAMS = "params";
	private static final String USER_CODE = "userCode";
	private static final String KEY = "key";

	protected Gson gson = JSONUtils.newGson();
	private CommonDingdingService commonDingdingService = new CommonDingdingServiceImpl();
	private SecurityService securityService = new SecurityServiceImpl();
	private ISCMBusinessService iSCMBusinessService = new ISCMBusinessServiceImpl();
	
	private static final long serialVersionUID = 1210436705188940602L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String responseMsg = defaultParamXML;

		if (StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
			request.setCharacterEncoding("utf-8"); // 对get无效！
		}
		String xmlContent = request.getParameter("content");
		if (StringUtils.isBlank(xmlContent)) {
			xmlContent = read(request);
		}
		if (StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
			xmlContent = new String(xmlContent.getBytes("ISO-8859-1"), "UTF-8");
		}
		if(xmlContent != null){
			if(xmlContent.contains("&quot;")){
				xmlContent = xmlContent.replace("&quot;", "\"");
			}
		}

		HashMap<String, String> map = xmlToMap(xmlContent);
		String action = map.get(ACTION);

		if (StringUtils.endsWithIgnoreCase(action, "getUserInfoByDingdingAuthCode")) {// 获取钉钉免登授权码对应的用户信息
			responseMsg = commonDingdingService.getUserInfoByDingdingAuthCode(map.get(INTEGRATE_REQUEST), map.get(PARAMS));
		}else if (StringUtils.equalsIgnoreCase(action, "applySecurityToken")) { // 获取授权码
			responseMsg = securityService.applySecurityToken(
	    	    map.get(USER_CODE), map.get(KEY));
	    }else if (StringUtils.equalsIgnoreCase(action, "doUpdateRequireStatus")) { 
			responseMsg = iSCMBusinessService.doUpdateRequireStatus(map.get(INTEGRATE_REQUEST), map.get(PARAMS));
		}
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.print(responseMsg);
		response.flushBuffer();
	}

	private String read(HttpServletRequest request) throws IOException {
		ServletInputStream inputStream = request.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				inputStream, "UTF-8"));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}

	private HashMap xmlToMap(String xml) {

		HashMap map = new HashMap();

		String action = StringUtils.substringBetween(xml, "<action>",
				"</action>");
		map.put("action", action);
		while (true) {
			String item = StringUtils
					.substringBetween(xml, "<item>", "</item>");
			String name = StringUtils.substringBetween(item, "<name>",
					"</name>");
			String value = StringUtils.substringBetween(item, "<value>",
					"</value>");
			if (StringUtils.isBlank(item) || StringUtils.isBlank(name)
					|| StringUtils.isBlank(value))
				break;
			map.put(name, value);
			xml = StringUtils.substringAfter(xml, "</item>");
		}
		return map;
	}

}
