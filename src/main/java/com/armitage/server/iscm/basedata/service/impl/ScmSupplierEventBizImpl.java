package com.armitage.server.iscm.basedata.service.impl;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iscm.basedata.model.ScmSupplierDemander;
import com.armitage.server.iscm.basedata.model.ScmSupplierEvent2;
import com.armitage.server.iscm.basedata.service.ScmSupplierDemanderBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierEventBiz;
import com.armitage.server.mongodb.biz.MongoDBImageBiz;
import com.armitage.server.quartz.util.SupplierPlatUtil;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Service;

@Service("scmSupplierEventBiz")
public class ScmSupplierEventBizImpl extends BaseBizImpl<ScmSupplierEvent2> implements ScmSupplierEventBiz {
	
	private CodeBiz codeBiz;
	private ScmSupplierDemanderBiz scmSupplierDemanderBiz;
	private SysParamBiz sysParamBiz;
	private MongoDBImageBiz mongoDBImageBiz = (MongoDBImageBiz) AppContextUtil.getBean("mongoDBImageBiz");
	private static final String ISCMDBNAME = "iscm";
	private static final String IMAGENAME = "QRCode";
	private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;
	
	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}

	public void setScmSupplierDemanderBiz(ScmSupplierDemanderBiz scmSupplierDemanderBiz) {
		this.scmSupplierDemanderBiz = scmSupplierDemanderBiz;
	}

	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmSupplierEvent2.class) + "." + ScmSupplierEvent2.FN_CONTROLUNITNO), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmSupplierEvent2.class) + "." + ScmSupplierEvent2.FN_CONTROLUNITNO), QueryParam.QUERY_EQ, param.getControlUnitNo()));
	}

	@Override
	public String generateEventQRcode(ScmSupplierEvent2 scmSupplierEvent, Param param) {
		if(scmSupplierEvent == null){
			return "";
		}
		String data="";
		String mongoDBImageId="";
		LinkedHashMap<String, Object> imageInfoMap = new LinkedHashMap<>();
		//ScmSupplierEvent scmSupplierEvent = (ScmSupplierEvent)bean.getObject();
		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByCtrl(param.getControlUnitNo(), param);
		if(scmSupplierDemander == null) {
			throw new AppException("iscm.basedata.controller.ScmSupplierEventQRCodeShowController.supplierNoDemander");
		}
		if(StringUtils.isBlank(scmSupplierEvent.getWxUrl())) {
			throw new AppException("iscm.basedata.controller.ScmSupplierEventQRCodeShowController.supplierNoWxUrl");
		}
		if(!scmSupplierEvent.isFlag()) {
			throw new AppException("iscm.basedata.controller.ScmSupplierEventQRCodeShowController.supplierTimeOut");
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			imageInfoMap.put("demanderId", scmSupplierDemander.getDemanderId());
			imageInfoMap.put("eventCode", scmSupplierEvent.getEventCode());
			imageInfoMap.put("beginDate", sdf.format(scmSupplierEvent.getBegDate()));
			imageInfoMap.put("endDate", sdf.format(scmSupplierEvent.getEndDate()));
			data = JSON.toJSONString(imageInfoMap);
			data = Base64.getEncoder().encodeToString(data.getBytes());
			String url = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_MerchantPlatForm", "", param)+"/iwechat/getFlatFormOauth?r="+scmSupplierEvent.getWxUrl()+"&data="+data;
			String uuid = UUID.randomUUID().toString();
			//生成二进制
			Map<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 200, 200, hints);
			//转为bufferImage
			BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
	        for (int x = 0; x < 200; x++) {
	            for (int y = 0; y < 200; y++) {
	                image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
	            }
	        }
	        //转为输入流
	        ByteArrayOutputStream bs = new ByteArrayOutputStream();
	        ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
	        ImageIO.write(image, "jpg", imOut);
	        InputStream in = new ByteArrayInputStream(bs.toByteArray());

			if(!StringUtils.isBlank(scmSupplierEvent.getFilePath())) {
				mongoDBImageBiz.delete(ISCMDBNAME, IMAGENAME, scmSupplierEvent.getFilePath());
			}
			mongoDBImageId=mongoDBImageBiz.save(ISCMDBNAME, IMAGENAME,in,uuid,"jpg");
			in.close();
			scmSupplierEvent.setFilePath(mongoDBImageId);
			this.update(scmSupplierEvent, param);
			return mongoDBImageId;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	@Override
	protected void afterSelect(ScmSupplierEvent2 entity, Param param) throws AppException {
		if (entity != null){
			setConvertMap(entity,param);
		}
	}

    private void setConvertMap(ScmSupplierEvent2 scmSupplierEvent,Param param){
		if (scmSupplierEvent != null){
			if(StringUtils.isNotBlank(scmSupplierEvent.getEventType())){
				Code code = codeBiz.selectByCategoryAndCode("ScmSupplierEvent", scmSupplierEvent.getEventType());
				if(code!=null)
					scmSupplierEvent.setEventTypeName(code.getName());
			}
		}
	}
    
    @Override
	protected void beforeUpdate(ScmSupplierEvent2 oldEntity, ScmSupplierEvent2 newEntity,	Param param) throws AppException {
		if((oldEntity.isFlag() && !newEntity.isFlag())){
			SupplierPlatUtil supplierPlatUtil = new SupplierPlatUtil();
			supplierPlatUtil.changeSyncData(oldEntity, param);
		}
	}
	
	@Override
	protected void beforeDelete(ScmSupplierEvent2 entity, Param param)
			throws AppException {
		SupplierPlatUtil supplierPlatUtil = new SupplierPlatUtil();
		supplierPlatUtil.changeSyncData(entity, param);
	}
}

