
package com.armitage.server.iscm.basedata.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import java.lang.StringBuilder;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.JSONUtils;
import com.armitage.server.iscm.basedata.dao.ScmMaterialPurchaseDAO;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialPurchase;
import com.armitage.server.iscm.basedata.model.ScmMaterialPurchase2;
import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelation2;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialPurchaseBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialUnitRelationBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service("scmMaterialPurchaseBiz")
public class ScmMaterialPurchaseBizImpl extends BaseBizImpl<ScmMaterialPurchase2> implements ScmMaterialPurchaseBiz {
	private ScmMaterialUnitRelationBiz scmMaterialUnitRelationBiz;
	private UsrBiz usrBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private ScmPurBuyerBiz scmPurBuyerBiz;
	
	public void setScmMaterialUnitRelationBiz(ScmMaterialUnitRelationBiz scmMaterialUnitRelationBiz) {
		this.scmMaterialUnitRelationBiz = scmMaterialUnitRelationBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}

	public void setScmPurBuyerBiz(ScmPurBuyerBiz scmPurBuyerBiz) {
		this.scmPurBuyerBiz = scmPurBuyerBiz;
	}

	@Override
	protected void beforeAdd(ScmMaterialPurchase2 entity, Param param)
			throws AppException {
		if(entity!=null){
			entity.setCreator(param.getUsrCode());
			entity.setCreateDate(CalendarUtil.getDate(param));
		}
	}

	@Override
	protected void beforeUpdate(ScmMaterialPurchase2 oldEntity,
			ScmMaterialPurchase2 newEntity, Param param) throws AppException {
		if(newEntity!=null){
			if(StringUtils.isBlank(newEntity.getCreator()))
				newEntity.setCreator(param.getUsrCode());
			if(newEntity.getCreateDate()==null)
				newEntity.setCreateDate(CalendarUtil.getDate(param));
			newEntity.setEditor(param.getUsrCode());
			newEntity.setCreateDate(CalendarUtil.getDate(param));
		}
	}

	@Override
	public ScmMaterialPurchase2 selectByItemIdAndOrgUnitNo(long itemId, String resOrgUnitNo, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("itemId", itemId);
		map.put("orgUnitNo", resOrgUnitNo);
		ScmMaterialPurchase2 scmMaterialPurchase = ((ScmMaterialPurchaseDAO) dao).selectByItemIdAndOrgUnitNo(map);
		if(scmMaterialPurchase!=null) {
			setConvertMap(scmMaterialPurchase,param);
		}
		return scmMaterialPurchase;
	}
	
	private void setConvertMap(ScmMaterialPurchase2 scmMaterialPurchase,Param param) {
		if(scmMaterialPurchase != null){
			if(scmMaterialPurchase.getItemId() > 0 && scmMaterialPurchase.getPurUnit() > 0){
				//采购单位
				ScmMaterialUnitRelation2 scmMaterialUnitRelation = scmMaterialUnitRelationBiz.selectByItemAndUnit(scmMaterialPurchase.getItemId(), scmMaterialPurchase.getPurUnit(), param);
				if(scmMaterialUnitRelation != null){
					scmMaterialPurchase.setConvertMap(ScmMaterialPurchase2.FN_PURUNIT, scmMaterialUnitRelation);
				}
			}
			if (StringUtils.isNotBlank(scmMaterialPurchase.getCreator())){
				//创建人
				Usr usr = usrBiz.selectByCode(scmMaterialPurchase.getCreator(), param);
				if (usr != null) {
					scmMaterialPurchase.setConvertMap(ScmMaterialPurchase2.FN_CREATOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmMaterialPurchase.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmMaterialPurchase.getEditor(), param);
				if (usr != null) {
					scmMaterialPurchase.setConvertMap(ScmMaterialPurchase2.FN_EDITOR, usr);
				}
			}
			if(scmMaterialPurchase != null && scmMaterialPurchase.getDefaultRate()!=null){
				PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmMaterialPurchase.getDefaultRate().toString(), null, param);
				if (pubSysBasicInfo != null) {
					scmMaterialPurchase.setTaxRate(pubSysBasicInfo.getFInfoNo());
					scmMaterialPurchase.setConvertMap(ScmMaterialPurchase2.FN_TAXRATE, pubSysBasicInfo);
				}
			}
			if (scmMaterialPurchase != null) {
			    if (scmMaterialPurchase.getBuyerId() > 0) {
			        // 采购员
			        ScmPurBuyer2 scmPurBuyer = scmPurBuyerBiz.selectDirect(scmMaterialPurchase.getBuyerId(), param);
			        if (scmPurBuyer != null) {
			            scmMaterialPurchase.setConvertMap(ScmMaterialPurchase2.FN_BUYERID, scmPurBuyer);
			        } 
			    }
            }
		}
	}

	@Override
	public ScmMaterialPurchase2 updateByPurchase(ScmMaterialPurchase2 scmMaterialPurchase, Param param)	throws AppException {
		if(scmMaterialPurchase!=null) {
			ScmMaterialPurchase2 oldMaterialPurchase = this.selectByItemIdAndOrgUnitNo(scmMaterialPurchase.getItemId(),param.getOrgUnitNo(),param);
			ScmMaterialPurchase2 selectByItemIdAndOrgUnitNo = this.selectByItemIdAndOrgUnitNo(scmMaterialPurchase.getItemId(),param.getControlUnitNo(),param);
			boolean flag = true;
			if (selectByItemIdAndOrgUnitNo != null) {
				Gson newGson = JSONUtils.newGson();
				String json = newGson.toJson(selectByItemIdAndOrgUnitNo);
				String json1 = newGson.toJson(scmMaterialPurchase);
				if (StringUtils.equals(json, json1)) {
					flag=false;
				}
			}
			boolean exists = true;
			if(oldMaterialPurchase==null) {
				oldMaterialPurchase = new ScmMaterialPurchase2(true);
				exists = false;
			}
			long id = oldMaterialPurchase.getId();
			BeanUtil.copyProperties(oldMaterialPurchase, scmMaterialPurchase);
			oldMaterialPurchase.setId(id);
			if(exists) {
				this.update(oldMaterialPurchase, param);
			}else {
				//判断是否有修改过该资料
				if (flag) {
					oldMaterialPurchase.setOrgUnitNo(param.getOrgUnitNo());
					this.add(oldMaterialPurchase, param);
				}
			}
			return oldMaterialPurchase;
		}
		return null;
	}

	@Override
	public CommonBean batchRatioSet(List<ScmMaterial2> scmMaterial2, Param param) throws AppException {
		CommonBean resultCommonBean = new CommonBean();
		List<String> rtnList = new ArrayList<>();
		StringBuilder builder=new StringBuilder("");
		int successCount=0;
		int failCount=0;
		if (scmMaterial2 !=null && scmMaterial2.size()>0) {
			ScmMaterialPurchase scmMaterialPurchase2 = new ScmMaterialPurchase();
			scmMaterialPurchase2.setControlUnitNo(param.getControlUnitNo());
			scmMaterialPurchase2.setGuId("");
			scmMaterialPurchase2.setBuyerId(0);
			scmMaterialPurchase2.setOrgUnitNo(param.getOrgUnitNo());
			scmMaterialPurchase2.setNeedInquire("Y");
			scmMaterialPurchase2.setDefaultRate(BigDecimal.ZERO);
			scmMaterialPurchase2.setCreator(param.getUsrName());
			scmMaterialPurchase2.setCreateDate(new Date());
			scmMaterialPurchase2.setEditor(param.getUsrName());
			scmMaterialPurchase2.setEditDate(new Date());
			scmMaterialPurchase2.setFreezeOrgUnit("");
			scmMaterialPurchase2.setStatus("A");
			scmMaterialPurchase2.setReceiveBottomRatio(BigDecimal.ZERO);
			scmMaterialPurchase2.setControlUnitNo(param.getControlUnitNo());
			for (ScmMaterial2 scmMaterial : scmMaterial2) {
				ScmMaterialUnitRelation2 selectBaseUnitByItem = scmMaterialUnitRelationBiz.selectBaseUnitByItem(scmMaterial.getId(), param);
				scmMaterialPurchase2.setItemId(scmMaterial.getId());
				if (selectBaseUnitByItem!=null && selectBaseUnitByItem.getTargetUnitId()>0) {
					scmMaterialPurchase2.setPurUnit(selectBaseUnitByItem.getTargetUnitId());
				}
				scmMaterialPurchase2.setReceiveTopRatio(scmMaterial.getReceiveTopRatio());
				if (StringUtils.equals(scmMaterial.getStatus(), "A")) {
					((ScmMaterialPurchaseDAO) dao).addOrUpdateMaterialPurchase(scmMaterialPurchase2,param);
					successCount++;
				}else {
					builder.append(Message.getMessage(param.getLang(),"iscm.ScmMaterialPurchaseBizImpl.information.batchRatioSet",new String[] {scmMaterial.getItemNo() + " ",scmMaterial.getItemName() + ""}));
					builder.append("\r\n");
				}
			}
			failCount=scmMaterial2.size()-successCount;
			rtnList.add(Message.getMessage(param.getLang(),"iscm.purchasemanage.information.partSuccess",new String[] {successCount+"",failCount + ""}));
			resultCommonBean.setList(rtnList);
			resultCommonBean.setObject(builder.toString());
		}
		return resultCommonBean;
	}
}
