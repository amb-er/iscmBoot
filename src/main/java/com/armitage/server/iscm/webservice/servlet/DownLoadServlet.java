package com.armitage.server.iscm.webservice.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.mongodb.biz.MongoDBImageBiz;

public class DownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String IPMSDBNAME = "iscm";
	private MongoDBImageBiz mongoDBImageBiz = (MongoDBImageBiz) AppContextUtil.getBean("mongoDBImageBiz");
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String filePath = URLDecoder.decode(request.getHeader("path"),"utf-8");
			String fileName = URLDecoder.decode(request.getHeader("fileName"),"utf-8");
			String modelName = request.getHeader("modelName");
			String docType = request.getHeader("docType");
			String fullPath = request.getRealPath("/") + "upload" + filePath;
			String mongodbId = "";
			response.setContentType("application/*");
			OutputStream outputStream = response.getOutputStream();
			InputStream inputStream = null;

			String regex = "[/]\\d+[/].{10}[/]\\d+[.]\\w+";// 匹配以前的保存路径
															// /700104000/2017-03-03/20170303154751662.png
			if (Pattern.matches(regex, filePath)) {
				File f = new File(fullPath);
				if (f.exists()) {// 判断文件是否存在,保存到mongodb
					inputStream = new FileInputStream(fullPath);
//					mongodbId = MongodbUtils.saveFile(modelName, new FileInputStream(fullPath), fileName, docType);
					mongodbId = mongoDBImageBiz.save(IPMSDBNAME, modelName, new FileInputStream(fullPath), fileName, docType);

					if (StringUtils.isNotBlank(mongodbId)) {
						response.addHeader("newPath", modelName + "/"
								+ mongodbId);
					}
				}

			} else {
				inputStream = mongoDBImageBiz.select(IPMSDBNAME, modelName,
						filePath);
			}

			if (inputStream != null) {
				byte[] buffer = new byte[2048];
				int i = -1;
				while ((i = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, i);
				}
				outputStream.flush();
				response.addHeader("result", "ok");
			} else {
				response.addHeader("result", "nofile");
			}
			if (inputStream != null) {
				inputStream.close();
				inputStream = null;
			}
			if (outputStream != null) {
				outputStream.close();
				outputStream = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
