package com.armitage.server.iscm.activityTask.audit;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

import com.alibaba.fastjson.JSONObject;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.HTTPSSecureProtocolSocketFactory;
import com.armitage.server.common.util.HttpClientUtils;
import com.armitage.server.common.util.JSONUtils;
import com.armitage.server.iscm.activityTask.audit.model.Attachment;
import com.armitage.server.iscm.activityTask.audit.model.ScmPurRequireUploadOA;
import com.armitage.server.iscm.activityTask.audit.model.ScmPurRequireUploadOAAttachmentValues;
import com.armitage.server.iscm.activityTask.audit.model.ScmPurRequireUploadOACreator;
import com.armitage.server.iscm.activityTask.audit.model.ScmPurRequireUploadOAFormValues;
import com.armitage.server.iscm.basedata.model.ScmPurchaseType;
import com.armitage.server.iscm.basedata.service.ScmPurchaseTypeBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPriceEntryBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurQuotationEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireEntryBiz;
import com.armitage.server.system.model.AppService2;
import com.armitage.server.system.model.Employee2;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.service.AppServiceBiz;
import com.armitage.server.system.service.EmployeeBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.util.AppServiceUtil;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import com.google.gson.Gson;



public class IndentityUtil {
	
	
	private static ScmPurRequireBiz scmPurRequireBiz = (ScmPurRequireBiz) AppContextUtil.getBean("scmPurRequireBiz");
	private static ScmPurRequireEntryBiz scmPurRequireEntryBiz = (ScmPurRequireEntryBiz) AppContextUtil.getBean("scmPurRequireEntryBiz");
	private static UsrBiz usrBiz = (UsrBiz) AppContextUtil.getBean("usrBiz");
	private static OrgUnitBiz orgUnitBiz = (OrgUnitBiz) AppContextUtil.getBean("orgUnitBiz");
	private static EmployeeBiz employeeBiz = (EmployeeBiz) AppContextUtil.getBean("employeeBiz");
	private static ScmPurchaseTypeBiz scmPurchaseTypeBiz = (ScmPurchaseTypeBiz) AppContextUtil.getBean("scmPurchaseTypeBiz");
	private static SysParamBiz sysParamBiz = (SysParamBiz) AppContextUtil.getBean("sysParamBiz");
	private static AppServiceBiz appServiceBiz = (AppServiceBiz) AppContextUtil.getBean("appServiceBiz");
	private static ScmPurQuotationEntryBiz scmPurQuotationEntryBiz=(ScmPurQuotationEntryBiz) AppContextUtil.getBean("scmPurQuotationEntryBiz");
	private static ScmPurPriceEntryBiz scmPurPriceEntryBiz = (ScmPurPriceEntryBiz) AppContextUtil.getBean("scmPurPriceEntryBiz");
	
	
	
	static Log log = LogFactory.getLog(IndentityUtil.class);

	private static Gson gson = JSONUtils.newGson();
	
	private static String TOKEN_URL;
	private static String START_URL;
	private static String RESTART_URL;
	private static String FTP_URL;
	private static String FTP_DIRECTORY;
	private static String TEMPLATEID;
	
	/**
	 * 获取Token
	 */
	public static String getToken(String url, String clientId, String secret) {
		String postResult = "";
		//url = url + "/api/applySecurityToken";
		Map<String, String> map = new HashMap<>();
		map.put("clientId", clientId);
		map.put("secret", secret);
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
     * 发送post请求
     * @param url  路径
     * @param token  token
     * @param jsonObject  参数(json类型)
     * @return
     */
    public static String send(String url, String token,JSONObject jsonObject){
        String body = "";
        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
 
        //装填参数
        StringEntity s = new StringEntity(jsonObject.toString(), "utf-8");
        s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                "application/json"));
        //设置参数到请求对象中
        httpPost.setEntity(s);
        String json = gson.toJson(jsonObject);
		log.info("!!!开始调用接口，地址：" + url + "，参数：" + json);

        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("x-client-token", token);
        
        try {
        	//执行请求操作，并拿到结果
            CloseableHttpResponse response = client.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == HttpStatus.SC_OK){
            	//获取结果实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    //按指定编码转换结果实体为String类型
                	body = EntityUtils.toString(entity, "utf-8");
                }
                EntityUtils.consume(entity);
            }
            //释放链接
            response.close();
            return body;
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
    }
	
	public static String post(HttpClient client, String url,String token,
			Map<String, String> params) throws IOException {
		PostMethod post = new PostMethod(url);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			post.setParameter(entry.getKey(), entry.getValue());

		}
		
		String json = gson.toJson(params);
		log.info("!!!开始调用接口，地址：" + url + "，参数：" + json);
		
		post.setRequestHeader("x-client-token",token);
		
		if (url.startsWith("https")) {
			Protocol https = new Protocol("https",new HTTPSSecureProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", https);
		}
		
		int statusCode = client.executeMethod(post);
		if (statusCode != HttpStatus.SC_OK) {
			throw new HttpException(post.getStatusText());
		}

		InputStream inputStream = post.getResponseBodyAsStream();
//		byte[] bytes = new byte[READ_BATCH_SIZE];
//		int bytesRead = 0;
//		StringBuffer stringBuffer = new StringBuffer();
//		while ((bytesRead = inputStream.read(bytes, 0, READ_BATCH_SIZE)) > 0) {
//			stringBuffer.append(new String(bytes, 0, bytesRead, post.getResponseCharSet()));
//		}
//
//		return stringBuffer.toString();

		return inputStreamToString(inputStream, post.getResponseCharSet());
	}
	
	private static String inputStreamToString(InputStream in, String charset) throws IOException {
		StringBuffer out = new StringBuffer();
		BufferedReader input = new BufferedReader(new InputStreamReader(in, charset));
		String s;
		while ((s = input.readLine()) != null) {
			out.append(s);
		}
		return out.toString();
	}
	
	/**
	 * 表单格式发送post请求
	 * @param url
	 * @param token
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String post2(String url, String token, Map map) throws UnsupportedEncodingException {
		String postResult = "";
		//url = url + "/api/applySecurityToken";
		/*Map<String, String> map = new HashMap<>();
		map.put("loginName", "admin");*/
		//PostMethod post = new PostMethod(url);
		//StringRequestEntity entity = new StringRequestEntity("x-client-token", token, "UTF-8");
		//StringRequestEntity entity = new StringRequestEntity("loginName", "admin", "UTF-8");
		//post.setRequestHeader("x-client-token",token);
	    //post.setRequestEntity(entity);
		// 调用应用服务
		String json = gson.toJson(map);
		//log.info("!!!开始调用接口，地址：" + url + "，参数：" + json);
		try {
			HttpClient client = HttpClientUtils.createClient("utf-8", 100);
			client.getHttpConnectionManager().getParams().setSoTimeout(1000*60);//读取超时60秒
			postResult = post(client, url,token, map);
			log.info("!!!调用接口，地址：" + url + "，返回结果：" + postResult);
			return postResult;
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
	}

	public static Map<String, Object> createExcel(ScmPurRequire2 purRequire,List<ScmPurRequireEntry2> list,String userName,Param param) throws FileNotFoundException, IOException{
		Map<String, Object> map=new HashMap<>();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("请购单");
        // 设置默认宽度
        sheet.setDefaultColumnWidth(22);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
       // sheet.addMergedRegion(new CellRangeAddress(1,1,1,2));
        sheet.addMergedRegion(new CellRangeAddress(4,4,0,9));
        sheet.setDisplayGridlines(false);

        //设置单元格风格，居中对齐.
        HSSFCellStyle titleStyle = hssfWorkbook.createCellStyle();

        HSSFCellStyle paramStyle = hssfWorkbook.createCellStyle();		

        HSSFCellStyle csLeft = hssfWorkbook.createCellStyle();
        HSSFCellStyle csCenter = hssfWorkbook.createCellStyle();
        HSSFCellStyle csRight = hssfWorkbook.createCellStyle();
        
        HSSFCellStyle csRight1 = hssfWorkbook.createCellStyle();
        HSSFCellStyle csLeft1 = hssfWorkbook.createCellStyle();

        csLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        csCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        csRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        csLeft1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        csRight1.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

        csLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
        csCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
        csRight.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
        csLeft1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
        csRight1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直

        csLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        csCenter.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        csRight.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_NONE); //下边框
        csRight1.setBorderBottom(HSSFCellStyle.BORDER_NONE); //下边框
        csLeft1.setBorderBottom(HSSFCellStyle.BORDER_NONE); //下边框

        csLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        csCenter.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        csRight.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_NONE);//左边框
        csLeft1.setBorderLeft(HSSFCellStyle.BORDER_NONE);//左边框
        csRight1.setBorderLeft(HSSFCellStyle.BORDER_NONE);//左边框

        csLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        csCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        csRight.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        titleStyle.setBorderTop(HSSFCellStyle.BORDER_NONE);//上边框
        csLeft1.setBorderTop(HSSFCellStyle.BORDER_NONE);//上边框
        csRight1.setBorderTop(HSSFCellStyle.BORDER_NONE);//上边框


        csLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        csCenter.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        csRight.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        titleStyle.setBorderRight(HSSFCellStyle.BORDER_NONE);//右边框
        csLeft1.setBorderRight(HSSFCellStyle.BORDER_NONE);//右边框
        csRight1.setBorderRight(HSSFCellStyle.BORDER_NONE);//右边框


        //设置字体:
        HSSFFont font = hssfWorkbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 9);//设置字体大小
       // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示

        HSSFFont titleFont = hssfWorkbook.createFont();
        titleFont.setFontName("宋体");
        titleFont.setFontHeightInPoints((short) 24);//设置字体大小
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        csLeft.setFont(font);//要用到的字体格式
        csCenter.setFont(font);//要用到的字体格式
        csRight.setFont(font);//要用到的字体格式
        csLeft1.setFont(font);//要用到的字体格式
        csRight1.setFont(font);//要用到的字体格式
        titleStyle.setFont(titleFont);//要用到的字体格式


        HSSFRow rowheader = sheet.createRow(0);
        HSSFCell cellheader = rowheader.createCell(0);
         cellheader.setCellValue("请购单");
        cellheader.setCellStyle(titleStyle);

        HSSFRow paramRow1=sheet.createRow(1);
        HSSFCell paramCell1=paramRow1.createCell(0);
        paramCell1.setCellValue("请购单号：");
        paramCell1.setCellStyle(csRight1);
        HSSFCell paramCell2=paramRow1.createCell(1);
        paramCell2.setCellValue(purRequire.getPrNo());
        paramCell2.setCellStyle(csLeft1);
        HSSFCell paramCell6=paramRow1.createCell(7);
        paramCell6.setCellValue("申请日期：");
        paramCell6.setCellStyle(csRight1);
        HSSFCell paramCell7=paramRow1.createCell(8);
        paramCell7.setCellValue(simpleDateFormat.format(purRequire.getApplyDate()));
        paramCell7.setCellStyle(csLeft1);

        HSSFRow paramRow2=sheet.createRow(2);
        HSSFCell row2Cell1=paramRow2.createCell(0);
        row2Cell1.setCellValue("采购类型：");
        row2Cell1.setCellStyle(csRight1);
        HSSFCell row2Cell2=paramRow2.createCell(1);
        row2Cell2.setCellValue(purRequire.getBizTypeName());
        row2Cell2.setCellStyle(csLeft1);
        HSSFCell row2Cell6=paramRow2.createCell(7);
        row2Cell6.setCellValue("需求日期：");
        row2Cell6.setCellStyle(csRight1);
        HSSFCell row2Cell7=paramRow2.createCell(8);
        row2Cell7.setCellValue(simpleDateFormat.format(purRequire.getReqDate()));
        row2Cell7.setCellStyle(csLeft1);


        HSSFRow paramRow3=sheet.createRow(3);
        HSSFCell row3Cell1=paramRow3.createCell(0);
        row3Cell1.setCellValue("申请组织：");
        row3Cell1.setCellStyle(csRight1);
        HSSFCell row3Cell2=paramRow3.createCell(1);
        row3Cell2.setCellValue(purRequire.getOrgUnitName());
        row3Cell2.setCellStyle(csLeft1);
        
        HSSFCell row3Cell6=paramRow3.createCell(7);
        row3Cell6.setCellValue("备注：");
        row3Cell6.setCellStyle(csRight1);
        HSSFCell row3Cell7=paramRow3.createCell(8);
        row3Cell7.setCellValue(purRequire.getRemarks());
        row3Cell7.setCellStyle(csLeft1);

        HSSFRow paramRow4=sheet.createRow(4);
        paramRow4.setHeightInPoints(20);


        HSSFRow titleRow = sheet.createRow(5);
        titleRow.setHeightInPoints(30);
        String[] header={"物资编码","物资名称","规格","采购单位","申请数量","含税单价","含税金额","税率","供应商","备注"};
        for(int i=0;i<header.length;i++){
             HSSFCell cell2=titleRow.createCell(i);
            cell2.setCellStyle(csCenter);
            cell2.setCellValue(header[i]);
        }
        int qtyPrec = Integer.valueOf(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_QtyPrecision", "2", param));
        int pricePrec = Integer.valueOf(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PricePrecision", "2", param));
        int amtPrec = Integer.valueOf(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
        for (int j=0;j<list.size();j++) {
            ScmPurRequireEntry2 scmPurRequireEntry=list.get(j);
            HSSFRow row = sheet.createRow(j + 6);
            row.setHeightInPoints(25);
            HSSFCell cell0=row.createCell(0);
            cell0.setCellValue(scmPurRequireEntry.getItemNo());
            cell0.setCellStyle(csLeft);


            HSSFCell cell1=row.createCell(1);
            cell1.setCellValue(scmPurRequireEntry.getItemName());
            cell1.setCellStyle(csLeft);

            HSSFCell cell2=row.createCell(2);
            cell2.setCellValue(scmPurRequireEntry.getSpec());
            cell2.setCellStyle(csLeft);

            HSSFCell cell3=row.createCell(3);
            cell3.setCellValue(scmPurRequireEntry.getPurUnitName());
            cell3.setCellStyle(csCenter);
            
            HSSFCell cell4=row.createCell(4);
            DecimalFormat decimalFormat = new DecimalFormat("##0."+StringUtils.repeat("0", qtyPrec));
            cell4.setCellValue(decimalFormat.format(scmPurRequireEntry.getQty()).toString());
            cell4.setCellStyle(csRight);

            HSSFCell cell5=row.createCell(5);
            DecimalFormat decimalFormat1 = new DecimalFormat("##0."+StringUtils.repeat("0", pricePrec));
            cell5.setCellValue(decimalFormat1.format(scmPurRequireEntry.getPrice()).toString());
            cell5.setCellStyle(csRight);

            HSSFCell cell6=row.createCell(6);
            DecimalFormat decimalFormat2 = new DecimalFormat("##0."+StringUtils.repeat("0", amtPrec));
            cell6.setCellValue(decimalFormat2.format(scmPurRequireEntry.getAmt()).toString());
            cell6.setCellStyle(csRight);

            HSSFCell cell7=row.createCell(7);
            if (scmPurRequireEntry.getPriceBillId()>0) {
				if (StringUtils.equals(scmPurRequireEntry.getRefPriceStatus(), "1")) {
					//报价
					ScmPurQuotationEntry2 scmPurQuotationEntry2 = scmPurQuotationEntryBiz.selectTaxRateByPqId(scmPurRequireEntry.getPriceBillId(),scmPurRequireEntry.getItemId(), param);
					if (scmPurQuotationEntry2 !=null && StringUtils.isNotEmpty(scmPurQuotationEntry2.getTaxRateStr())) {
						cell7.setCellValue(scmPurQuotationEntry2.getTaxRateStr());
					}else {
						cell7.setCellValue("0%");
					}
				}else {
					//定价
					ScmPurPriceEntry2 scmPurPriceEntry2 = scmPurPriceEntryBiz.selectTaxRateByPmId(scmPurRequireEntry.getPriceBillId(),scmPurRequireEntry.getItemId(), param);
					if (scmPurPriceEntry2 !=null && StringUtils.isNotEmpty(scmPurPriceEntry2.getTaxRateStr())) {
						cell7.setCellValue(scmPurPriceEntry2.getTaxRateStr());
					}else {
						cell7.setCellValue("0%");
					}
				}
			}else {
				cell7.setCellValue("0%");
			}
            cell7.setCellStyle(csRight);
            
            HSSFCell cell8=row.createCell(8);
            cell8.setCellValue(scmPurRequireEntry.getVendorName());
            cell8.setCellStyle(csLeft);
            
            HSSFCell cell9=row.createCell(9);
            cell9.setCellValue(scmPurRequireEntry.getRemarks());
            cell9.setCellStyle(csLeft);
        }
        
        //合计金额
        BigDecimal countAmt=new BigDecimal(0);
        for(ScmPurRequireEntry scmPurRequireEntry:list){
        	BigDecimal bigDecimal=new BigDecimal(scmPurRequireEntry.getAmt().toString());
        	countAmt=countAmt.add(bigDecimal);
        }
        HSSFRow countRow = sheet.createRow(list.size()+6);
        for(int i=0;i<header.length;i++){
        	HSSFCell cells=countRow.createCell(i);
        	cells.setCellStyle(csCenter);
        }
        countRow.setHeightInPoints(30);
        HSSFCell countCell0=countRow.createCell(0);
        countCell0.setCellValue("合计：");
        countCell0.setCellStyle(csCenter);
        HSSFCell countCell6=countRow.createCell(6);
        DecimalFormat decimalFormat3 = new DecimalFormat("##0."+StringUtils.repeat("0", amtPrec));
        countCell6.setCellValue(decimalFormat3.format(countAmt).toString());
        countCell6.setCellStyle(csRight);
        
        HSSFRow lastRow = sheet.createRow(list.size()+8);
        lastRow.setHeightInPoints(20);
        HSSFCell lastCell1=lastRow.createCell(1);
        lastCell1.setCellValue("制单人：");
        lastCell1.setCellStyle(csRight1);
        HSSFCell lastCell2=lastRow.createCell(2);
        lastCell2.setCellValue(userName);
        lastCell2.setCellStyle(csLeft1);
        
        HSSFCell lastCell6=lastRow.createCell(6);
        lastCell6.setCellValue("制单日期：");
        lastCell6.setCellStyle(csRight1);
        HSSFCell lastCell7=lastRow.createCell(7);
        Date currentDate=(Date) new Date();
        lastCell7.setCellValue(simpleDateFormat.format(currentDate));
        lastCell7.setCellStyle(csLeft1);
        
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        hssfWorkbook.write(byteArrayOutputStream);
        map.put("status", "true");
        map.put("size", byteArrayOutputStream.size());
        //java.io.File file=new java.io.File("F:\\test666.xls");
        //hssfWorkbook.write(new FileOutputStream(file));
        //ftp附件上传
        byte[] data = byteArrayOutputStream.toByteArray();
		try (ByteArrayInputStream inputStream = new ByteArrayInputStream(data)) {
			FTPUtils ftpUtils = FTPUtils.getInstance();
			String FTP_USRNAME = AppServiceUtil.getAppServiceParamValue(param.getControlUnitNo(),"LLOAService", "ftpUsrname", param);
			if(StringUtils.isBlank(FTP_USRNAME)){
				log.info("蓝凌OA服务ftp用户未配置好！");
				return null;
			}
			String FTP_PASSWORD = AppServiceUtil.getAppServiceParamValue(param.getControlUnitNo(),"LLOAService", "ftpPassword", param);
			if(StringUtils.isBlank(FTP_PASSWORD)){
				log.info("蓝凌OA服务ftp用户密码未配置好！");
				return null;
			}
			ftpUtils.setUsername(FTP_USRNAME);
			ftpUtils.setPassword(FTP_PASSWORD);
			log.info("地址："+FTP_URL+"/"+FTP_DIRECTORY+",文件流大小："+inputStream.available());
			ftpUtils.storeFile(FTP_URL, FTP_DIRECTORY, purRequire.getPrNo() + ".xls", inputStream);
			map.put("name", purRequire.getPrNo() + ".xls");
			map.put("url", "/Hotel/" + purRequire.getPrNo() + ".xls");
			log.info("生成Excel并上传ftp成功！");
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		}
		return map;
	}
	
	
	public static Map<String, Object> processStart(String token,Map<String, Object> startProInterfaceParam) throws UnsupportedEncodingException, InterruptedException{
		 Thread.sleep(3000);
		String proceInfo=post2(START_URL, token, startProInterfaceParam);
		log.info("蓝凌OA流程启动结果："+proceInfo);
		 Map<String, Object> proceInfoMap = gson.fromJson(proceInfo, Map.class);
		 if(proceInfoMap.get("status")==null || proceInfoMap.get("data")==null){
			 processStart(token,startProInterfaceParam);
		 }
		return proceInfoMap;	 
	}
	
	public static  String processReStart(String token,Map<String, Object> restartParamMap) throws UnsupportedEncodingException, InterruptedException{
		 Thread.sleep(3000);
		String proceInfo=post2(RESTART_URL, token, restartParamMap);
		log.info("蓝凌OA流程重启结果："+proceInfo);
		 Map<String, Object>proceInfoMap = gson.fromJson(proceInfo, Map.class);
		 if(proceInfoMap.get("status")==null || proceInfoMap.get("data")==null){
			 processReStart(token,restartParamMap);
		 }
		return proceInfo;	 
	}
	

	
	public static String identity(long requireId,String usrCode,String processInstanceId,Param param) throws FileNotFoundException, IOException, InterruptedException{
		AppService2 appService = appServiceBiz.selectByCode(param.getControlUnitNo(), "LLOAService", param);
		String url = appService.getUrl();
		if(StringUtils.isBlank(url)){
			log.info("蓝凌OA服务未配置好！");
			return "";
		}
		if(StringUtils.equals(StringUtils.right(url, 1),"/"))
			url = StringUtils.left(url, StringUtils.length(url)-1);
		TOKEN_URL = url+"/api/auth/client/getAccessToken";
		START_URL = url+"/api/oa/start";
		RESTART_URL = url+"/api/oa/restart";
		FTP_URL = AppServiceUtil.getAppServiceParamValue(param.getControlUnitNo(),"LLOAService", "ftpUrl", param);
		if(StringUtils.isBlank(FTP_URL)){
			log.info("蓝凌OA服务ftp地址未配置好！");
			return "";
		}
		FTP_DIRECTORY = AppServiceUtil.getAppServiceParamValue(param.getControlUnitNo(),"LLOAService", "ftpDirectory", param);
		if(StringUtils.isBlank(FTP_DIRECTORY)){
			log.info("蓝凌OA服务ftp上传目录未配置好！");
			return "";
		}
		TEMPLATEID = AppServiceUtil.getAppServiceParamValue(param.getControlUnitNo(),"LLOAService", "templateId", param);
		if(StringUtils.isBlank(TEMPLATEID)){
			log.info("蓝凌OA服务审批模板号未配置好！");
			return "";
		}
		Gson gson = new Gson();
		ScmPurRequire2 require = null;
		Employee2 employee = null;
		String loginName = "";
		String proceInfo = "";
		Map<String, Object> formValuesMap = new HashMap<String, Object>();
		Map<String, Object> createrMap = new HashMap<String, Object>();
		Map<String, Object> attachmentValuesMap = new HashMap<String, Object>();
		Map<String, Object> attachmentEntryValuesMap = new HashMap<String, Object>();
		Map<String, Object> startProInterfaceParam = new HashMap<String, Object>();
		List<Map> list = new ArrayList<Map>();
		List<ScmPurRequireEntry2> scmPurRequireList = new ArrayList<>();
		if (requireId > 0) {
			require = scmPurRequireBiz.selectDirect(requireId, param);
			scmPurRequireList = scmPurRequireEntryBiz.selectByPrId2(requireId, param);
		}
		if (require == null || (scmPurRequireList == null || scmPurRequireList.isEmpty())) {
			log.info("需上传的请购单或需审批的请购单明细未查询到，请检查！");
			return "";
		}
		if (require != null) {
			ScmPurchaseType scmPurchaseType = scmPurchaseTypeBiz.selectDirect(Long.parseLong(require.getBizType()),
					param);
			OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(require.getOrgUnitNo(), param);
			require.setBizTypeName(scmPurchaseType.getName());
			require.setOrgUnitName(orgBaseUnit.getOrgUnitName());
		}
		if(StringUtils.isBlank(require.getSubscribeReason()) || StringUtils.isBlank(require.getPurRequireTheme())){
			log.info("请购单的申购理由或申购主题为空！");
			return "";
		}
		Map map = scmPurRequireBiz.selectPurRequireTotalAmt(requireId, param);
		Usr usr = usrBiz.selectByCode(usrCode, param);
		if (usr != null) {
			employee = (Employee2) employeeBiz.selectDirect(usr.getEmpid(), param);
		}
		// OA系统登录账号
		if (employee != null) {
			loginName = employee.getEmpNo();
		}else{
			log.info("获取OA系统登录账号失败！");
			return "";
		}
		// 获取token
		String tokenInfo = getToken(TOKEN_URL, "gz-oa", "d739c72297b95f4a80bcdedae82aff38");
		if(StringUtils.isBlank(tokenInfo)){
			log.info("获取token失败！");
			return "";
		}
		Map tokenMap = gson.fromJson(tokenInfo, Map.class);
		String token = (String) tokenMap.get("token");
		ScmPurRequireUploadOA scmPurRequireUploadOA = new ScmPurRequireUploadOA();
		ScmPurRequireUploadOAFormValues ScmPurRequireUploadOAFormValues = new ScmPurRequireUploadOAFormValues();
		// 表单数据
		ScmPurRequireUploadOAFormValues.setPhone(usr.getCellphone());
		ScmPurRequireUploadOAFormValues.setType(Integer.parseInt(require.getBizType()));
		ScmPurRequireUploadOAFormValues.setOrderType(("00000102".equals(require.getFinOrgUnitNo()) ? 2 : 1));
		ScmPurRequireUploadOAFormValues.setDate(FormatUtils.fmtDate(require.getReqDate()));
		ScmPurRequireUploadOAFormValues.setTotalPrice(String.valueOf(map.get("totalAmt")));
		ScmPurRequireUploadOAFormValues.setReason(string2Json(require.getSubscribeReason()));
		scmPurRequireUploadOA.setFormValues(ScmPurRequireUploadOAFormValues);
		/*formValuesMap.put("phone", usr.getCellphone());
		formValuesMap.put("type", require.getBizType());
		formValuesMap.put("orderType", ("00001448".equals(require.getFinOrgUnitNo()) ? 2 : 1));
		formValuesMap.put("date", FormatUtils.fmtDate(require.getReqDate()));
		formValuesMap.put("totalPrice", map.get("totalAmt"));
		formValuesMap.put("reason", require.getRemarks());*/
		// 流程起草人
		ScmPurRequireUploadOACreator docCreator = new ScmPurRequireUploadOACreator();
		docCreator.setLoginName(loginName);
		scmPurRequireUploadOA.setDocCreator(docCreator);
		//createrMap.put("LoginName", loginName);
		// 附件生成并上传ftp
		Map<String, Object> result = createExcel(require, scmPurRequireList, usr.getName(), param);
		if(result == null){
			return "";
		}
		Attachment attachmentValue = new Attachment();
		attachmentValue.setName(string2Json(require.getPurRequireTheme()) + ".xls");
		attachmentValue.setSize(String.valueOf(result.get("size")));
		attachmentValue.setUrl(String.valueOf(result.get("url")));
		//attachmentEntryValuesMap.put("name", result.get("name"));
		//attachmentEntryValuesMap.put("size", result.get("size"));
		//attachmentEntryValuesMap.put("url", result.get("url"));
		List<Attachment> attachmentValueList = new ArrayList<Attachment>();
		attachmentValueList.add(attachmentValue);
		ScmPurRequireUploadOAAttachmentValues scmPurRequireUploadOAAttachmentValues = new ScmPurRequireUploadOAAttachmentValues();
		scmPurRequireUploadOAAttachmentValues.setAttachment(attachmentValueList);
		scmPurRequireUploadOA.setAttachmentValues(scmPurRequireUploadOAAttachmentValues);
		//list.add(attachmentEntryValuesMap);
		//attachmentValuesMap.put("attachment", list);
		scmPurRequireUploadOA.setFdTemplateId(TEMPLATEID);
		scmPurRequireUploadOA.setDocSubject(string2Json(require.getPurRequireTheme()));
		scmPurRequireUploadOA.setFdSource("audit");
		scmPurRequireUploadOA.setDocStatus("20");
		//startProInterfaceParam.put("fdTemplateId", "16a23b3e3fcb9bc9d905c0a4690b2446");
		//startProInterfaceParam.put("docSubject", "请购单");
		//startProInterfaceParam.put("formValues", gson.toJson(formValuesMap));
		//startProInterfaceParam.put("fdSource", "audit");
		//startProInterfaceParam.put("docCreator", gson.toJson(createrMap));
		//startProInterfaceParam.put("docStatus", "20");
		//startProInterfaceParam.put("attachmentValues", gson.toJson(attachmentValuesMap));
		//startProInterfaceParam.put("flowParam",gson.toJson(""));
		scmPurRequireUploadOA.setFlowParam("");
		scmPurRequireUploadOA.setEncrypt("N");
		//startProInterfaceParam.put("encrypt", "Y");
		if (StringUtils.isBlank(require.getOtherAuditNo())) {
			scmPurRequireUploadOA.setId(null);
			JSONObject jsonObject = (JSONObject) JSONObject.toJSON(scmPurRequireUploadOA);
			//proceInfo = post2(START_URL, token, startProInterfaceParam);
			proceInfo = send(START_URL, token, jsonObject);
			Map proceInfoMap = gson.fromJson(proceInfo, Map.class);
			log.info("蓝凌OA流程启动结果：" + proceInfo);
			if (proceInfoMap.get("status") != null && proceInfoMap.get("data") != null 
					&& (String.valueOf(proceInfoMap.get("status"))).startsWith("200")) {
				String proceId = (String) ((Map) proceInfoMap.get("data")).get("id");
				if(StringUtils.isNotBlank(proceId)){
					String outAuditType = "3";
					require.setOutAuditType(outAuditType);
					require.setOtherAuditNo(proceId);
					//ActivityUtil.deleteJob(processInstanceId);
					scmPurRequireBiz.updateOutAudit(require, param);
				}
			}
		} else {
			Map<String, Object> restartParamMap = new HashMap<>();
			scmPurRequireUploadOA.setId(require.getOtherAuditNo());
			scmPurRequireUploadOA.setFdTemplateId(null);
			scmPurRequireUploadOA.setDocCreator(null);
			scmPurRequireUploadOA.setDocStatus(null);
			//restartParamMap.put("id", require.getOtherAuditNo());
			//restartParamMap.put("docSubject", "请购单");
			//restartParamMap.put("formValues", gson.toJson(formValuesMap));
			//restartParamMap.put("fdSource", "audit");
			//restartParamMap.put("attachmentValues",gson.toJson(attachmentValuesMap));
			//restartParamMap.put("flowParam", gson.toJson(""));
			//restartParamMap.put("encrypt", "Y");
			JSONObject jsonObject = (JSONObject) JSONObject.toJSON(scmPurRequireUploadOA);
			//proceInfo = post2(RESTART_URL, token, restartParamMap);
			proceInfo = send(RESTART_URL, token, jsonObject);
			log.info("蓝凌OA流程重启结果：" + proceInfo);
			Map proceInfoMap = gson.fromJson(proceInfo, Map.class);
			if (proceInfoMap.get("status") != null && (String.valueOf(proceInfoMap.get("status"))).startsWith("200")) {
				String outAuditType = "7";
				require.setOutAuditType(outAuditType);
				//ActivityUtil.deleteJob(processInstanceId);
				scmPurRequireBiz.updateOutAudit(require, param);
			}
		}
		return proceInfo;
	}
	
	private static String string2Json(String s) {
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
}
