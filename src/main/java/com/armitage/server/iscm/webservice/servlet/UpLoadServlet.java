package com.armitage.server.iscm.webservice.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.icrs.resource.setting.util.FileTransferUtil;
import com.armitage.server.mongodb.biz.MongoDBImageBiz;

public class UpLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String IPMSDBNAME = "iscm";
	private MongoDBImageBiz mongoDBImageBiz = (MongoDBImageBiz) AppContextUtil.getBean("mongoDBImageBiz");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8");  
		upload(request, response);
	}

	public void upload(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		List<String> folder = new ArrayList<String>();
		folder.add("upload");
		folder.add("temp");
		String tempDir = FileTransferUtil.getInstance().getDir(request, folder);
		if (StringUtils.isNotBlank(tempDir)) {
			tempDir = tempDir.substring(0, tempDir.lastIndexOf("/"));
			tempDir = tempDir.substring(0, tempDir.lastIndexOf("/") + 1);
		}
		// 检测是不是存在上传文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String fileDir="";
		String saveName="";
		String modelName = "";
		String mongodbSaveId = "";
		PrintWriter out = null;  
		if (isMultipart) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 指定在内存中缓存数据大小,单位为byte,这里设为1Mb
			factory.setSizeThreshold(1024 * 1024);
			// 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
			factory.setRepository(new File(tempDir));
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 指定单个上传文件的最大尺寸,单位:字节，这里设为50Mb
			upload.setFileSizeMax(50 * 1024 * 1024);
			// 指定一次上传多个文件的总尺寸,单位:字节，这里设为50Mb
			upload.setSizeMax(50 * 1024 * 1024);
			upload.setHeaderEncoding("UTF-8");
			List<FileItem> items = null;
			try {
				// 解析request请求
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			if (items != null) {
				String moduleId = null;
				
				// 解析表单项目
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					// 如果是普通表单属性
					if (item.isFormField()) {
						if(StringUtils.equalsIgnoreCase("moduleId",item.getFieldName())){
							moduleId = item.getString();
						}else if(StringUtils.equalsIgnoreCase("modelName", item.getFieldName())){
							modelName = item.getString();
						}
					}
				}
				iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					// 如果是普通表单属性
					if (!item.isFormField()) {
						// 属性名
						String fileName =item.getName();
						// 上传文件路径
						fileName = fileName.substring(fileName.lastIndexOf("/") + 1);// 获得上传文件的文件名
						folder.clear();
						folder.add("upload");
						folder.add(moduleId);
						fileDir = FileTransferUtil.getInstance().getDir(request, folder);
						Integer rdm = new Random().nextInt(10000);
						saveName = FileTransferUtil.getInstance().getDateTimeString(true) + rdm.toString()
								+ fileName.substring(fileName.indexOf('.'));
						if (StringUtils.isBlank(fileDir) || StringUtils.isBlank(fileName)) {
							try {  
						        out = response.getWriter();  
						        out.append("failed");
						    } catch (IOException e) {  
						        e.printStackTrace();  
						    } finally {  
						        if (out != null) {  
						            out.close();  
						        }  
						    }  
							return;
						}
						try {
							mongodbSaveId = mongoDBImageBiz.save(IPMSDBNAME, modelName, item.getInputStream(), saveName, fileName.substring(fileName.lastIndexOf(".")+1));
						} catch (Exception e) {
							e.printStackTrace();
							throw new AppException(e.getMessage());
						}
					}
				}
			}
		}
		
	    try {  
	        out = response.getWriter();  
	        out.append(mongodbSaveId);
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (out != null) {  
	            out.close();  
	        }  
	    }  
	}
	
}
