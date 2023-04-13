
package com.armitage.server.iscm.basedata.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.github.stuxuhai.jpinyin.PinyinException;
import org.apache.commons.lang.StringUtils;

import com.armitage.server.api.business.basedata.params.InvOrgMaterialListParams;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.cache.ModelCacheMana;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iscm.basedata.dao.ScmMaterialDAO;
import com.armitage.server.iscm.basedata.model.ScmBrandInfo;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialAdd;
import com.armitage.server.iscm.basedata.model.ScmMaterialAdvQuery;
import com.armitage.server.iscm.basedata.model.ScmMaterialCompanyInfo;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupDetail;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupDetail2;
import com.armitage.server.iscm.basedata.model.ScmMaterialInventory2;
import com.armitage.server.iscm.basedata.model.ScmMaterialPurchase2;
import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelation;
import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelation2;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.service.ScmBrandInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialCompanyInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupDetailBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialInventoryBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialPurchaseBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialUnitRelationBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import com.armitage.server.iscm.common.service.ScmDataCollectionStepStateBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmBaseAttachmentBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerBiz;
import com.armitage.server.system.model.CodingRuleEntry2;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.BillSequenceBiz;
import com.armitage.server.system.service.OrgCostCenterBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import org.springframework.stereotype.Service;

@Service("scmMaterialBiz")
public class ScmMaterialBizImpl extends BaseBizImpl<ScmMaterial2> implements ScmMaterialBiz {
	private UsrBiz usrBiz;
	private ScmMeasureUnitBiz scmMeasureUnitBiz;
	private ScmMaterialUnitRelationBiz scmMaterialUnitRelationBiz;
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	private ScmMaterialGroupDetailBiz scmMaterialGroupDetailBiz;
	private ScmMaterialPurchaseBiz scmMaterialPurchaseBiz;
	private ScmMaterialInventoryBiz scmMaterialInventoryBiz;
	private ScmMaterialCompanyInfoBiz scmMaterialCompanyInfoBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private OrgCostCenterBiz orgCostCenterBiz;
	private BillSequenceBiz billSequenceBiz;
	private ScmDataCollectionStepStateBiz scmDataCollectionStepStateBiz;
	private ScmBaseAttachmentBiz scmBaseAttachmentBiz;
	private ScmPurBuyerBiz scmPurBuyerBiz;
	private OrgStorageBiz orgStorageBiz;
	private ScmBrandInfoBiz scmBrandInfoBiz;

	public void setScmBrandInfoBiz(ScmBrandInfoBiz scmBrandInfoBiz) {
		this.scmBrandInfoBiz = scmBrandInfoBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}

	public void setScmMaterialUnitRelationBiz(
			ScmMaterialUnitRelationBiz scmMaterialUnitRelationBiz) {
		this.scmMaterialUnitRelationBiz = scmMaterialUnitRelationBiz;
	}

	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}

	public void setScmMaterialGroupDetailBiz(ScmMaterialGroupDetailBiz scmMaterialGroupDetailBiz) {
		this.scmMaterialGroupDetailBiz = scmMaterialGroupDetailBiz;
	}
	
	public void setScmMaterialPurchaseBiz(ScmMaterialPurchaseBiz scmMaterialPurchaseBiz) {
		this.scmMaterialPurchaseBiz = scmMaterialPurchaseBiz;
	}

	public void setScmMaterialInventoryBiz(ScmMaterialInventoryBiz scmMaterialInventoryBiz) {
		this.scmMaterialInventoryBiz = scmMaterialInventoryBiz;
	}

	public void setScmMaterialCompanyInfoBiz(ScmMaterialCompanyInfoBiz scmMaterialCompanyInfoBiz) {
		this.scmMaterialCompanyInfoBiz = scmMaterialCompanyInfoBiz;
	}

    public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}

	public void setOrgCostCenterBiz(OrgCostCenterBiz orgCostCenterBiz) {
		this.orgCostCenterBiz = orgCostCenterBiz;
	}

	public void setBillSequenceBiz(BillSequenceBiz billSequenceBiz) {
		this.billSequenceBiz = billSequenceBiz;
	}

	public void setScmDataCollectionStepStateBiz(ScmDataCollectionStepStateBiz scmDataCollectionStepStateBiz) {
		this.scmDataCollectionStepStateBiz = scmDataCollectionStepStateBiz;
	}

	public void setScmBaseAttachmentBiz(ScmBaseAttachmentBiz scmBaseAttachmentBiz) {
		this.scmBaseAttachmentBiz = scmBaseAttachmentBiz;
	}
	
	public void setScmPurBuyerBiz(ScmPurBuyerBiz scmPurBuyerBiz) {
		this.scmPurBuyerBiz = scmPurBuyerBiz;
	}

	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmMaterial2.class) + "." + ScmMaterial2.FN_CONTROLUNITNO), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmMaterial2.class) + "." + ScmMaterial2.FN_CONTROLUNITNO), QueryParam.QUERY_EQ, param.getControlUnitNo()));
	}

	@Override
	protected void afterQueryPage(List list, Page page, String xmlId,Param param) throws AppException {
		if(list!=null && !list.isEmpty()){
			HashMap<String,Object> cacheMap = new HashMap<String,Object>();
			List<ScmMaterial2> scmMaterialList = list;
			for(ScmMaterial2 scmMaterial:scmMaterialList){
				if (scmMaterial.getBaseUnitId()>0){
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmMaterial.getBaseUnitId(), param);
					if (scmMeasureUnit!=null){
						scmMaterial.setBaseUnitName(scmMeasureUnit.getUnitName());
						scmMaterial.setConvertMap(ScmMaterial2.FN_BASEUNITID, scmMeasureUnit);
					}
				}
				if (scmMaterial.getGroupId() > 0){
					//????
					ScmMaterialGroup scmMaterialGroup = (ScmMaterialGroup) cacheMap.get(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"_"+scmMaterial.getGroupId());
					if(scmMaterialGroup==null){
						scmMaterialGroup = scmMaterialGroupBiz.selectDirect(scmMaterial.getGroupId(), param);
						cacheMap.put(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"_"+scmMaterial.getGroupId(),scmMaterialGroup);
					}
					if (scmMaterialGroup != null) {
						scmMaterial.setConvertMap(ScmMaterial2.FN_GROUPID, scmMaterialGroup);
					}
				}
				if (scmMaterial.getPurUnitId()>0){
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmMaterial.getPurUnitId(), param);
					if (scmMeasureUnit!=null){
						scmMaterial.setPurUnit(scmMeasureUnit.getUnitNo());
						scmMaterial.setPurUnitName(scmMeasureUnit.getUnitName());
						scmMaterial.setDisplayUnitName(scmMeasureUnit.getUnitName());
						scmMaterial.setConvertMap(ScmMaterial2.FN_PURUNITID, scmMeasureUnit);
					}
				}
				if (scmMaterial.getUnitId()>0){
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmMaterial.getUnitId(), param);
					if (scmMeasureUnit!=null){
						scmMaterial.setUnitName(scmMeasureUnit.getUnitName());
						scmMaterial.setDisplayUnitName(scmMeasureUnit.getUnitName());
						scmMaterial.setConvertMap(ScmMaterial2.FN_UNITID, scmMeasureUnit);
					}
				}
				if (scmMaterial.getCstUnitId()>0){
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmMaterial.getCstUnitId(), param);
					if (scmMeasureUnit!=null){
						scmMaterial.setCstUnitName(scmMeasureUnit.getUnitName());
						scmMaterial.setDisplayUnitName(scmMeasureUnit.getUnitName());
						scmMaterial.setConvertMap(ScmMaterial2.FN_CSTUNITID, scmMeasureUnit);
					}
				}
				if (scmMaterial.getBaseUnitId()>0){
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmMaterial.getBaseUnitId(), param);
					if (scmMeasureUnit!=null){
						scmMaterial.setBaseUnitName(scmMeasureUnit.getUnitName());
						scmMaterial.setConvertMap(ScmMaterial2.FN_BASEUNITID, scmMeasureUnit);
					}
				}
				if (scmMaterial.getPieUnitId()>0){
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmMaterial.getPieUnitId(), param);
					if (scmMeasureUnit!=null){
						scmMaterial.setPieUnitName(scmMeasureUnit.getUnitName());
						scmMaterial.setConvertMap(ScmMaterial2.FN_PIEUNITID, scmMeasureUnit);
					}
				}
				if(scmMaterial.getWareHouseId()>0) {
					ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmMaterial.getWareHouseId(), param);
					if(scmInvWareHouse!=null)
						scmMaterial.setConvertMap(ScmMaterial2.FN_WAREHOUSEID, scmInvWareHouse);
				}
				if (scmMaterial.getBuyerId()>0) {//???
					ScmPurBuyer2 scmPurBuyer = scmPurBuyerBiz.selectDirect(scmMaterial.getBuyerId(), param);
			        if (scmPurBuyer != null) {
			        	scmMaterial.setBuyerName(scmPurBuyer.getBuyerName());
			        	scmMaterial.setConvertMap(ScmMaterialPurchase2.FN_BUYERID, scmPurBuyer);
			        } 
				}
			}
		}
	}

	@Override
	public List<ScmMaterialUnitRelation2> selectItemUnit(ScmMaterial2 scmMaterial, Param param) throws AppException {
		return scmMaterialUnitRelationBiz.selectByItemOrUnit(scmMaterial.getId(), 0, param);
	}
	
	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if (list != null && !list.isEmpty()) {
			for (ScmMaterial2 scmMaterial:(List<ScmMaterial2>)list) {
				setConvertMap(scmMaterial,param);
			}
		}
	}
	
	private void setConvertMap(ScmMaterial2 scmMaterial,Param param) {
		if(scmMaterial!=null) {
			if (scmMaterial != null && scmMaterial.getGroupId() > 0){
				//????
				ScmMaterialGroup scmMaterialGroup = scmMaterialGroupBiz.selectDirect(scmMaterial.getGroupId(), param);
				if (scmMaterialGroup != null) {
					scmMaterial.setConvertMap(ScmMaterial2.FN_GROUPID, scmMaterialGroup);
				}
			}
			if (scmMaterial.getBaseUnitId() > 0){
				//????
				ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmMaterial.getBaseUnitId(), param);
				if(scmMeasureUnit != null){
					scmMaterial.setConvertMap(ScmMaterial2.FN_BASEUNITID, scmMeasureUnit);
					scmMaterial.setBaseUnitName(scmMeasureUnit.getUnitName());
					scmMaterial.setDisplayUnitName(scmMeasureUnit.getUnitName());			
				}
			}
			if (scmMaterial.getPieUnitId() > 0){
				//????
				ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmMaterial.getPieUnitId(), param);
				//ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmMaterial.getPieUnitId(), param);
				if(scmMeasureUnit != null){
					scmMaterial.setConvertMap(ScmMaterial2.FN_PIEUNITID, scmMeasureUnit);
				}
			}
			if (StringUtils.isNotBlank(scmMaterial.getCreator())){
				//???
				Usr usr = usrBiz.selectByCode(scmMaterial.getCreator(), param);
				if (usr != null) {
					scmMaterial.setConvertMap(ScmMaterial2.FN_CREATOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmMaterial.getEditor())){
				//???
				Usr usr = usrBiz.selectByCode(scmMaterial.getEditor(), param);
				if (usr != null) {
					scmMaterial.setConvertMap(ScmMaterial2.FN_EDITOR, usr);
				}
			}
			if (scmMaterial.getCstUnitId() > 0){
				//????
				ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmMaterial.getCstUnitId(), param);
				if(scmMeasureUnit != null){
					scmMaterial.setConvertMap(ScmMaterial2.FN_CSTUNITID, scmMeasureUnit);
					scmMaterial.setCstUnitName(scmMeasureUnit.getUnitName());
				}
			}
			if (scmMaterial.getBrandId() > 0){
				//????
				ScmBrandInfo scmBrandInfo = scmBrandInfoBiz.selectDirect(scmMaterial.getBrandId(), param);
				if(scmBrandInfo != null){
					scmMaterial.setConvertMap(ScmMaterial2.FN_BRANDID, scmBrandInfo);
				}
			}
		}
	}
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmMaterial2 scmMaterial = (ScmMaterial2) bean.getList().get(0);
		if(scmMaterial != null){
			if (scmMaterial.getId() > 0){
				//????????????????
				List<ScmMaterialPurchase2> scmMaterialPurchaseList = new ArrayList();
				ScmMaterialPurchase2 scmMaterialPurchase = scmMaterialPurchaseBiz.selectByItemIdAndOrgUnitNo(scmMaterial.getId(), param.getOrgUnitNo(), param);
				if(scmMaterialPurchase == null){
					scmMaterialPurchase = scmMaterialPurchaseBiz.selectByItemIdAndOrgUnitNo(scmMaterial.getId(), param.getControlUnitNo(), param);
				}
				if(scmMaterialPurchase != null){
					scmMaterialPurchaseList.add(scmMaterialPurchase);
					bean.setList2(scmMaterialPurchaseList);
				}
				List<ScmMaterialInventory2> scmMaterialInventoryList = new ArrayList();
				ScmMaterialInventory2 scmMaterialInventory = scmMaterialInventoryBiz.selectByItemIdAndOrgUnitNo(scmMaterial.getId(), param.getOrgUnitNo(), param);
				if(scmMaterialInventory == null){
					scmMaterialInventory = scmMaterialInventoryBiz.selectByItemIdAndOrgUnitNo(scmMaterial.getId(), param.getControlUnitNo(), param);
				}
				if(scmMaterialInventory!=null) {
					scmMaterialInventoryList.add(scmMaterialInventory);
					bean.setList3(scmMaterialInventoryList);
				}
				List<ScmMaterialCompanyInfo> scmMaterialCompanyInfoList = new ArrayList();
				ScmMaterialCompanyInfo scmMaterialCompanyInfo = scmMaterialCompanyInfoBiz.selectByItemIdAndOrgUnitNo(scmMaterial.getId(), param.getOrgUnitNo(), param);
				if(scmMaterialCompanyInfo == null){
					scmMaterialCompanyInfo = scmMaterialCompanyInfoBiz.selectByItemIdAndOrgUnitNo(scmMaterial.getId(), param.getControlUnitNo(), param);
				}
				if(scmMaterialCompanyInfo!=null) {
					scmMaterialCompanyInfoList.add(scmMaterialCompanyInfo);
					bean.setList4(scmMaterialCompanyInfoList);
				}
			}
		}
	}

	@Override
	protected void afterSelect(ScmMaterial2 entity, Param param) throws AppException {
		if(entity != null){
			setConvertMap(entity,param);
			//????
			ScmMaterial2 tempScmMaterial = ((ScmMaterialDAO) dao).selectByItemId(entity.getId());
			if(tempScmMaterial != null && tempScmMaterial.getGroupId() > 0){
				ScmMaterialGroup scmMaterialGroup = scmMaterialGroupBiz.selectDirect(tempScmMaterial.getGroupId(), param); 
				if(scmMaterialGroup != null){
					entity.setConvertMap(ScmMaterial2.FN_GROUPID, scmMaterialGroup);
				}
			}
		}
	}

	@Override
	protected void beforeAdd(ScmMaterial2 entity, Param param) throws AppException {
		if(entity != null){
//			HashMap<String, Object> map = new HashMap<>();
//			map.put("controlUnitNo", param.getControlUnitNo());
//			ScmMaterial2 scmMaterial= ((ScmMaterialDAO) dao).selectMaxId(map);
//			if(scmMaterial != null){
//				String str = CodeAutoCalUtil.autoAddOne(scmMaterial.getItemNo());
//				entity.setItemNo(str);
//			}else{
//				entity.setItemNo("000001");
//			}
			String code = "";
			if(StringUtils.isNotBlank(entity.getImportItemNo())){
				code = entity.getImportItemNo();
			}else{
				code = CodeAutoCalUtil.getBillCode("ScmMaterial", entity, param);
			}
			entity.setItemNo(code);
		}
	}
	
	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmMaterial2 scmMaterial = (ScmMaterial2) bean.getList().get(0);
		if(scmMaterial != null){
			ScmMaterialPurchase2 scmMaterialPurchase;
			if(bean.getList2() != null && !bean.getList2().isEmpty()){
				//??????
				scmMaterialPurchase = (ScmMaterialPurchase2) bean.getList2().get(0);
				if(scmMaterialPurchase.getPurUnit()==0)
					scmMaterialPurchase.setPurUnit(scmMaterial.getBaseUnitId());
			}else {
				scmMaterialPurchase = new ScmMaterialPurchase2(true);
                scmMaterialPurchase.setCreator(param.getUsrCode());
                scmMaterialPurchase.setCreateDate(new Date());
                scmMaterialPurchase.setControlUnitNo(param.getControlUnitNo());
                scmMaterialPurchase.setOrgUnitNo(param.getOrgUnitNo());
                scmMaterialPurchase.setStatus("A");
                scmMaterialPurchase.setPurUnit(scmMaterial.getBaseUnitId());
			}
            scmMaterialPurchase.setItemId(scmMaterial.getId());
			scmMaterialPurchaseBiz.add(scmMaterialPurchase, param);
			ScmMaterialInventory2 scmMaterialInventory;
			if(bean.getList3() != null && !bean.getList3().isEmpty()){
				//??????
				scmMaterialInventory= (ScmMaterialInventory2) bean.getList3().get(0);
				if(scmMaterialInventory.getUnitId()==0)
					scmMaterialInventory.setUnitId(scmMaterial.getBaseUnitId());
			}else {
				scmMaterialInventory = new ScmMaterialInventory2(true);
                scmMaterialInventory.setCreator(param.getUsrCode());
                scmMaterialInventory.setControlUnitNo(param.getControlUnitNo());
                scmMaterialInventory.setOrgUnitNo(param.getOrgUnitNo());
                scmMaterialInventory.setCreateDate(new Date());
                scmMaterialInventory.setStatus("A");
                scmMaterialInventory.setUnitId(scmMaterial.getBaseUnitId());
			}
			scmMaterialInventory.setItemId(scmMaterial.getId());
			scmMaterialInventoryBiz.add(scmMaterialInventory, param);
			ScmMaterialCompanyInfo scmMaterialCompanyInfo;
			if(bean.getList4() != null && !bean.getList4().isEmpty()){
				//??????
				scmMaterialCompanyInfo = (ScmMaterialCompanyInfo) bean.getList4().get(0);
				if(scmMaterialCompanyInfo.getCstUnitId()==0)
					scmMaterialCompanyInfo.setCstUnitId(scmMaterial.getBaseUnitId());
			}else {
				scmMaterialCompanyInfo = new ScmMaterialCompanyInfo(true);
                scmMaterialCompanyInfo.setCreator(param.getUsrCode());
                scmMaterialCompanyInfo.setCreateDate(new Date());
                scmMaterialCompanyInfo.setControlUnitNo(param.getControlUnitNo());
                scmMaterialCompanyInfo.setOrgUnitNo(param.getOrgUnitNo());
                scmMaterialCompanyInfo.setStatus("A");
                scmMaterialCompanyInfo.setCosting("FIFO");
                scmMaterialCompanyInfo.setType("3");
                scmMaterialCompanyInfo.setCstUnitId(scmMaterial.getBaseUnitId());
			}
			scmMaterialCompanyInfo.setItemId(scmMaterial.getId());
			scmMaterialCompanyInfoBiz.add(scmMaterialCompanyInfo, param);
			//????????
			if(scmMaterial.getId() > 0){
				ScmMaterialGroupDetail scmMaterialGroupDetail = new ScmMaterialGroupDetail(true);
				scmMaterialGroupDetail.setItemId(((int)scmMaterial.getId()));
				scmMaterialGroupDetail.setClassId(((int)scmMaterial.getGroupId()));
				//???????????
				scmMaterialGroupDetail.setStandardId(1);
				scmMaterialGroupDetailBiz.add(scmMaterialGroupDetail, param);
			}
			//??????????
			if(bean.getList5() != null && !bean.getList5().isEmpty()){
				scmMaterialGroupDetailBiz.updateAuxiGroup(scmMaterial.getId(), bean.getList5(), param);
			}
			//??????????
			HashMap<Long, Long> unitMap = new HashMap<Long, Long>();
			if(scmMaterial.getBaseUnitId()>0 && !unitMap.containsKey(scmMaterial.getBaseUnitId())){
				ScmMaterialUnitRelation2 scmMaterialUnitRelation = new ScmMaterialUnitRelation2(true);
				scmMaterialUnitRelation.setItemId(scmMaterial.getId());
				scmMaterialUnitRelation.setGuId("");
				scmMaterialUnitRelation.setLineId(1);
				scmMaterialUnitRelation.setTargetUnitId(scmMaterial.getBaseUnitId());
				scmMaterialUnitRelation.setConvrate(BigDecimal.ONE);
				scmMaterialUnitRelation.setBaseUnit(true);
				scmMaterialUnitRelation.setQtyPrecision(4);
				scmMaterialUnitRelation.setUseConvsUnit(false);
				scmMaterialUnitRelation.setMeasureUnitType("1");
				scmMaterialUnitRelationBiz.add(scmMaterialUnitRelation, param);
				unitMap.put(scmMaterial.getBaseUnitId(), scmMaterial.getBaseUnitId());
			}
			if(scmMaterial.getPieUnitId()>0 && !unitMap.containsKey(scmMaterial.getPieUnitId())){
				ScmMaterialUnitRelation2 scmMaterialUnitRelation = new ScmMaterialUnitRelation2(true);
				scmMaterialUnitRelation.setItemId(scmMaterial.getId());
				scmMaterialUnitRelation.setGuId("");
				scmMaterialUnitRelation.setLineId(2);
				scmMaterialUnitRelation.setTargetUnitId(scmMaterial.getPieUnitId());
				scmMaterialUnitRelation.setConvrate(BigDecimal.ZERO);
				scmMaterialUnitRelation.setBaseUnit(false);
				scmMaterialUnitRelation.setQtyPrecision(0);
				scmMaterialUnitRelation.setUseConvsUnit(false);
				scmMaterialUnitRelation.setMeasureUnitType("2");
				scmMaterialUnitRelationBiz.add(scmMaterialUnitRelation, param);
				unitMap.put(scmMaterial.getPieUnitId(), scmMaterial.getPieUnitId());
			}
			if(scmMaterialPurchase.getPurUnit()>0 && !unitMap.containsKey(scmMaterialPurchase.getPurUnit())){
				ScmMaterialUnitRelation2 scmMaterialUnitRelation = new ScmMaterialUnitRelation2(true);
				scmMaterialUnitRelation.setItemId(scmMaterial.getId());
				scmMaterialUnitRelation.setGuId("");
				scmMaterialUnitRelation.setLineId(3);
				scmMaterialUnitRelation.setTargetUnitId(scmMaterialPurchase.getPurUnit());
				scmMaterialUnitRelation.setConvrate(BigDecimal.ONE);
				scmMaterialUnitRelation.setBaseUnit(false);
				scmMaterialUnitRelation.setQtyPrecision(4);
				scmMaterialUnitRelation.setUseConvsUnit(false);
				scmMaterialUnitRelation.setMeasureUnitType("1");
				scmMaterialUnitRelationBiz.add(scmMaterialUnitRelation, param);
				unitMap.put(scmMaterialPurchase.getPurUnit(), scmMaterialPurchase.getPurUnit());
			}
			if(scmMaterialInventory.getUnitId()>0 && !unitMap.containsKey(scmMaterialInventory.getUnitId())){
				ScmMaterialUnitRelation2 scmMaterialUnitRelation = new ScmMaterialUnitRelation2(true);
				scmMaterialUnitRelation.setItemId(scmMaterial.getId());
				scmMaterialUnitRelation.setGuId("");
				scmMaterialUnitRelation.setLineId(4);
				scmMaterialUnitRelation.setTargetUnitId(scmMaterialInventory.getUnitId());
				scmMaterialUnitRelation.setConvrate(BigDecimal.ONE);
				scmMaterialUnitRelation.setBaseUnit(false);
				scmMaterialUnitRelation.setQtyPrecision(4);
				scmMaterialUnitRelation.setUseConvsUnit(false);
				scmMaterialUnitRelation.setMeasureUnitType("1");
				scmMaterialUnitRelationBiz.add(scmMaterialUnitRelation, param);
				unitMap.put(scmMaterialInventory.getUnitId(), scmMaterialInventory.getUnitId());
			}
		}
	}
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param)
			throws AppException {
		ScmMaterial2 scmMaterial = (ScmMaterial2) bean.getList().get(0);
		if(scmMaterial != null){
			List<ScmMaterialGroupDetail2> scmMaterialGroupDetail2s = scmMaterialGroupDetailBiz.selectByItemId(scmMaterial.getId(), param);
			if (scmMaterialGroupDetail2s != null && !scmMaterialGroupDetail2s.isEmpty()) {
				for (ScmMaterialGroupDetail2 scmMaterialGroupDetail2 : scmMaterialGroupDetail2s) {
					if (scmMaterialGroupDetail2.getStandardId()==1) {
						scmMaterialGroupDetail2.setClassId(scmMaterial.getGroupId());
						scmMaterialGroupDetailBiz.update(scmMaterialGroupDetail2, param);
						break;
					}
				}
			}
			ScmMaterialUnitRelation2 scmMaterialUnitRelation = scmMaterialUnitRelationBiz.selectBaseUnitByItem(scmMaterial.getId(), param);
			long targetUnitId=0;
			if(scmMaterialUnitRelation != null && scmMaterial.getBaseUnitId() != scmMaterialUnitRelation.getTargetUnitId()){
				targetUnitId = scmMaterialUnitRelation.getTargetUnitId();
				//?????????
				scmMaterialUnitRelation.setTargetUnitId(scmMaterial.getBaseUnitId());
				//??????????id??????
				ScmMaterialUnitRelation2 scmMaterialUnitRelation1 = scmMaterialUnitRelationBiz.selectByItemAndUnit(scmMaterialUnitRelation.getItemId(), scmMaterialUnitRelation.getTargetUnitId(), param);
				//???????
				if(scmMaterialUnitRelation1!=null) {
					scmMaterialUnitRelationBiz.delete(scmMaterialUnitRelation, param);
				}
				scmMaterialUnitRelationBiz.update(scmMaterialUnitRelation, param);
			}
			if(bean.getList2() != null && !bean.getList2().isEmpty()){
				//??????
				ScmMaterialPurchase2 scmMaterialPurchase = (ScmMaterialPurchase2) bean.getList2().get(0);
				if(targetUnitId==scmMaterialPurchase.getPurUnit()) {
					scmMaterialPurchase.setPurUnit(scmMaterial.getBaseUnitId());
				}
				scmMaterialPurchaseBiz.updateByPurchase(scmMaterialPurchase, param);
			}
			if(bean.getList3() != null && !bean.getList3().isEmpty()){
				//??????
				ScmMaterialInventory2 scmMaterialInventory = (ScmMaterialInventory2) bean.getList3().get(0);
				if(targetUnitId==scmMaterialInventory.getUnitId()){
					scmMaterialInventory.setUnitId(scmMaterial.getBaseUnitId());
				}
				scmMaterialInventoryBiz.updateByInventory(scmMaterialInventory, param);
			}
			if(bean.getList4() != null && !bean.getList4().isEmpty()){
				//??????
				ScmMaterialCompanyInfo scmMaterialCompanyInfo = (ScmMaterialCompanyInfo) bean.getList4().get(0);
				if(targetUnitId==scmMaterialCompanyInfo.getCstUnitId()){
					scmMaterialCompanyInfo.setCstUnitId(scmMaterial.getBaseUnitId());
				}
				scmMaterialCompanyInfoBiz.updateByCompanyInfo(scmMaterialCompanyInfo, param);
			}
			//??????????
			if(bean.getList5() != null && !bean.getList5().isEmpty()){
				scmMaterialGroupDetailBiz.updateAuxiGroup(scmMaterial.getId(), bean.getList5(), param);
			}
		}
	}
	
	@Override
	protected void afterDelete(ScmMaterial2 entity, Param param) throws AppException {
		ScmMaterial2 scmMaterial = entity;
		if(scmMaterial != null){
			//????????????????
			scmMaterialPurchaseBiz.update(scmMaterial, null, ScmMaterialPurchase2.FN_ITEMID, param);
			scmMaterialInventoryBiz.update(scmMaterial, null, ScmMaterialInventory2.FN_ITEMID, param);
			scmMaterialCompanyInfoBiz.update(scmMaterial, null, ScmMaterialCompanyInfo.FN_ITEMID, param);
			//????
			scmBaseAttachmentBiz.update(scmMaterial,null,ScmBaseAttachment.FN_BILLID,param);
			//????????
			if(scmMaterial.getId() > 0){
				scmMaterialGroupDetailBiz.deleteByItemIdOrGroupId(scmMaterial.getId(), 0, param);
			}
			scmMaterialUnitRelationBiz.update(scmMaterial, null, ScmMaterialUnitRelation.FN_ITEMID, param);
		}
	}
	
	@Override
	public BaseModel updateStatus(BaseModel baseModel, Param param) throws AppException {
		if(baseModel instanceof ScmMaterial2){
			return this.updateDirect(((ScmMaterial2)baseModel), param);
		}
		if(baseModel instanceof ScmMaterialPurchase2){
			ScmMaterialPurchase2 scmMaterialPurchase;
			if(param.getOrgUnitNo().equals(param.getControlUnitNo())){
				scmMaterialPurchase = scmMaterialPurchaseBiz.updateDirect(((ScmMaterialPurchase2)baseModel), param);
			}else{
				scmMaterialPurchase = scmMaterialPurchaseBiz.updateByPurchase(((ScmMaterialPurchase2)baseModel), param);
			}
			if(scmMaterialPurchase != null){
				ScmMaterial2 scmMaterial = this.selectDirect(scmMaterialPurchase.getItemId(), param);
				ModelCacheMana.delete(scmMaterial);
			}
			return scmMaterialPurchase;
		}
		if(baseModel instanceof ScmMaterialInventory2){
			ScmMaterialInventory2 scmMaterialInventory;
			if(param.getOrgUnitNo().equals(param.getControlUnitNo())){
				scmMaterialInventory =  scmMaterialInventoryBiz.updateDirect(((ScmMaterialInventory2)baseModel), param);
			}else{
				scmMaterialInventory = scmMaterialInventoryBiz.updateByInventory(((ScmMaterialInventory2)baseModel), param);
			}
			if(scmMaterialInventory != null){
				ScmMaterial2 scmMaterial = this.selectDirect(scmMaterialInventory.getItemId(), param);
				ModelCacheMana.delete(scmMaterial);
			}
			return scmMaterialInventory;
		}
		if(baseModel instanceof ScmMaterialCompanyInfo){
			ScmMaterialCompanyInfo scmMaterialCompanyInfo;
			if(param.getOrgUnitNo().equals(param.getControlUnitNo())){
				scmMaterialCompanyInfo =  scmMaterialCompanyInfoBiz.updateDirect(((ScmMaterialCompanyInfo)baseModel), param);
			}else{
				scmMaterialCompanyInfo = scmMaterialCompanyInfoBiz.updateByCompanyInfo(((ScmMaterialCompanyInfo)baseModel), param);
			}
			if(scmMaterialCompanyInfo != null){
				ScmMaterial2 scmMaterial = this.selectDirect(scmMaterialCompanyInfo.getItemId(), param);
				ModelCacheMana.delete(scmMaterial);
			}
			return scmMaterialCompanyInfo;
		}
		return null;
	}

	@Override
	public ScmMaterial2 selectByItemId(long itemId, Param param) throws AppException {
		if(itemId > 0){
			return ((ScmMaterialDAO) dao).selectByItemId(itemId);
		}
		return null;
	}

	@Override
	public List<ScmMaterial2> selectByOrgUnitNo(String orgUnitNo, String fromItemNo, String toItemNo, Param param)
			throws AppException {
		if(StringUtils.isNotBlank(orgUnitNo)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("orgUnitNo", orgUnitNo);
			if(StringUtils.isNotBlank(fromItemNo)){
				map.put("fromItemNo", fromItemNo);
			}
			if(StringUtils.isNotBlank(toItemNo)){
				map.put("toItemNo", toItemNo);
			}
			return ((ScmMaterialDAO) dao).selectByOrgUnitNo(map);
		}
		return null;
	}

	@Override
	public List<ScmMaterial2> findCountingMaterial(String orgUnitNo,String depts, String fromItemNo,
			String toItemNo, String type, Param param) throws AppException {
		if(StringUtils.isNotBlank(orgUnitNo)){
			HashMap<String, Object> map = new HashMap<>();
			if(StringUtils.equals(type, "TC")) {
				map.put("orgUnitNo", orgUnitNo);
			}else {
				List<OrgCostCenter2> orgCostCenterList=orgCostCenterBiz.findChild(orgUnitNo,param);
				StringBuffer costOrgs = new StringBuffer("");
				if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()) {
					for(OrgCostCenter2 orgCostCenter:orgCostCenterList) {
						if(StringUtils.isNotBlank(costOrgs.toString()))
							costOrgs.append(",");
						costOrgs.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
					}
				}else {
					costOrgs.append("'0'");
				}
				map.put("orgUnitNo", costOrgs.toString());
			}
			map.put("depts", depts);
			if(StringUtils.isNotBlank(fromItemNo)){
				map.put("fromItemNo", fromItemNo);
			}
			if(StringUtils.isNotBlank(toItemNo)){
				map.put("toItemNo", toItemNo);
			}
			if(StringUtils.isNotBlank(type)){
				map.put("type", type);
			}
			return ((ScmMaterialDAO) dao).findCountingMaterial(map);
		}
		return null;
	}

	@Override
	public ScmMaterial2 selectByItemNo(String controlUnitNo, String itemNo,Param param) throws AppException {
		String key = controlUnitNo+"_"+itemNo;
		if (ModelCacheMana.keyExists(key, modelClassById == null ? modelClass : modelClassById)) {
			Object obj = ModelCacheMana.get(key, modelClassById == null ? modelClass : modelClassById);
			if (obj != null && obj.getClass().equals(modelClass)) {
				return (ScmMaterial2) obj;
			}
		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("controlUnitNo", controlUnitNo);
		map.put("itemNo", itemNo);
		ScmMaterial2 scmMaterial = ((ScmMaterialDAO) dao).selectByItemNo(map);
		if (scmMaterial != null) {
			ModelCacheMana.set(key, scmMaterial);
		}
		return scmMaterial;
	}

	@Override
	public List<ScmMaterial2> selectByCostOrgUnitNo(String orgUnitNo, String fromItemNo, String toItemNo, Param param)
			throws AppException {
		if(StringUtils.isNotBlank(orgUnitNo)){
			HashMap<String, Object> map = new HashMap<>();
			map.put("orgUnitNo", orgUnitNo);
			if(StringUtils.isNotBlank(fromItemNo)){
				map.put("fromItemNo", fromItemNo);
			}
			if(StringUtils.isNotBlank(toItemNo)){
				map.put("toItemNo", toItemNo);
			}
			return ((ScmMaterialDAO) dao).selectByCostOrgUnitNo(map);
		}
		return null;
	}

	@Override
	public boolean checkUse(long itemId, Param param) throws AppException {
		if(itemId > 0){
			int count = ((ScmMaterialDAO) dao).checkUse(itemId);
			if(count > 0){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	@Override	
	public boolean checkAllUse(long itemId, Param param) throws AppException {
		if(itemId > 0){
			int count = ((ScmMaterialDAO) dao).checkAllUse(itemId);
			if(count > 0){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	@Override
	public ScmMaterial2 findByInvItemId(String controlUnitNo, String orgUnitNo,long itemId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("controlUnitNo", controlUnitNo);
		map.put("orgUnitNo", orgUnitNo);
		map.put("itemId", itemId);
		return ((ScmMaterialDAO)dao).findByInvItemId(map);
	}

	@Override
	public ScmMaterial2 findByPurItemId(String controlUnitNo, String orgUnitNo,long itemId, Param param) throws AppException {
		String key = controlUnitNo + "_" + orgUnitNo+"_"+itemId;
		// ?????
		if (ModelCacheMana.keyExists(key, modelClass)) {
			Object obj = ModelCacheMana.get(key, modelClass);
			if (obj != null && obj.getClass().equals(modelClass)) {
				return (ScmMaterial2) obj;
			}
		}
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("controlUnitNo", controlUnitNo);
		map.put("orgUnitNo", orgUnitNo);
		map.put("itemId", itemId);
		ScmMaterial2 scmMaterial = ((ScmMaterialDAO)dao).findByPurItemId(map);
		if(scmMaterial!=null)
			ModelCacheMana.set(key, scmMaterial);
		return scmMaterial;
	}

	@Override
	public List<ScmMaterial2> queryMaterialList(ScmMaterialAdvQuery scmMaterialAdvQuery, int pageNum, Param param) throws AppException {
		Page page = new Page();
		page.setModelClass(ScmMaterial2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		page.setModel(scmMaterialAdvQuery);
		return this.findPage(page, param);
	}

	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmMaterialAdvQuery) {
				ScmMaterialAdvQuery scmMaterialAdvQuery = (ScmMaterialAdvQuery) page.getModel();
				if(StringUtils.isNotBlank(scmMaterialAdvQuery.getMixParam())){
					page.setSqlCondition("(ScmMaterial.itemNo like '%"+scmMaterialAdvQuery.getMixParam()+"%' or ScmMaterial.itemName like '%"+scmMaterialAdvQuery.getMixParam()+
							"%' or ScmMaterial.barCode like '%"+scmMaterialAdvQuery.getMixParam()+"%' or ScmMaterial.pym like '%"+scmMaterialAdvQuery.getMixParam()+"%')");
				}
			}
		}
	}

	@Override
	public ScmMaterial2 selectByStock(String orgUnitNo, long wareHouseId, long itemId, Param param)
			throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("wareHouseId", wareHouseId);
		map.put("itemId", itemId);
		map.put("costCenter", "0");
		return ((ScmMaterialDAO)dao).selectByStock(map);
	}

	@Override
	public ScmMaterial2 findSameNameMaterial(ScmMaterial2 scmMaterial, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("controlUnitNo", param.getControlUnitNo());
        map.put("itemName", scmMaterial.getItemName());
        map.put("id", scmMaterial.getId());
        map.put("spec", scmMaterial.getSpec());
        return ((ScmMaterialDAO)dao).findSameNameMaterial(map);
	}

	@Override
	public BigDecimal getConvrate(long itemId, long unitId, long baseUnitId, Param param) throws AppException {
		BigDecimal convRate1 = ScmMaterialUtil.getConvRate(itemId, baseUnitId, param);
		BigDecimal convRate2 = ScmMaterialUtil.getConvRate(itemId, unitId, param);
		return convRate2.divide(convRate1, 6, RoundingMode.HALF_UP);
	}
	
	@Override
	public List<ScmMaterial2> findByFinAllList(String controlUnitNo,
			int groupLevel, Param param) throws AppException{
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("controlUnitNo", controlUnitNo);
		map.put("orgUnitNo", param.getOrgUnitNo());
		map.put("groupLevel", groupLevel);
		return ((ScmMaterialDAO)dao).findByFinAllList(map);
	}

	@Override
	public ScmMaterial2 approval(ScmMaterial2 scmMaterial, Param param) throws AppException {
		if(scmMaterial!=null) {
			scmMaterial = this.selectDirect(scmMaterial.getId(), param);
			if(StringUtils.equals("I", scmMaterial.getStatus())) {
				scmMaterial.setStatus("A");
				this.updateDirect(scmMaterial, param);
			}
			//?????????????????????
			ScmMaterialPurchase2 scmMaterialPurchase = scmMaterialPurchaseBiz.selectByItemIdAndOrgUnitNo(scmMaterial.getId(), param.getOrgUnitNo(), param);
			if(scmMaterialPurchase != null && StringUtils.equals("I", scmMaterialPurchase.getStatus())){
				scmMaterialPurchase.setStatus("A");
				scmMaterialPurchaseBiz.updateDirect(scmMaterialPurchase, param);
			}
			ScmMaterialInventory2 scmMaterialInventory = scmMaterialInventoryBiz.selectByItemIdAndOrgUnitNo(scmMaterial.getId(), param.getOrgUnitNo(), param);
			if(scmMaterialInventory != null && StringUtils.equals("I", scmMaterialInventory.getStatus())){
				scmMaterialInventory.setStatus("A");
				scmMaterialInventoryBiz.updateDirect(scmMaterialInventory, param);
			}
			ScmMaterialCompanyInfo scmMaterialCompanyInfo = scmMaterialCompanyInfoBiz.selectByItemIdAndOrgUnitNo(scmMaterial.getId(), param.getOrgUnitNo(), param);
			if(scmMaterialCompanyInfo != null && StringUtils.equals("I", scmMaterialCompanyInfo.getStatus())){
				scmMaterialCompanyInfo.setStatus("A");
				scmMaterialCompanyInfoBiz.updateDirect(scmMaterialCompanyInfo, param);
			}
		}
		return scmMaterial;
	}

	@Override
	public ScmMaterial2 findByFinItemId(String controlUnitNo, String orgUnitNo, long itemId, Param param)
			throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("controlUnitNo", controlUnitNo);
		map.put("orgUnitNo", orgUnitNo);
		map.put("itemId", itemId);
		ScmMaterial2 scmMaterial =  ((ScmMaterialDAO)dao).findByFinItemId(map);
		if(scmMaterial!=null)
			this.setConvertMap(scmMaterial, param);
		return scmMaterial;
	}

	@Override
	public CommonBean importScmMaterial(ScmDataCollectionStepState2 stepState,final List<ScmMaterialAdd> scmMaterialAddList,final List<String> msgInfoList,
			final Param param) throws AppException {
		final CommonBean commonBean = new CommonBean(); 
		ScmDataCollectionStepState2 scmDataCollectionStepState = scmDataCollectionStepStateBiz.updateByAsynProcessed(stepState,ScmDataCollectionStepState2.SATATE_RUN, null, param);
		final ScmDataCollectionStepState2 tempScmStepData = new ScmDataCollectionStepState2();
		BeanUtil.copyProperties(tempScmStepData, scmDataCollectionStepState);
		ExecutorService executors = Executors.newCachedThreadPool();
		executors.execute(new Runnable() {
			@Override
			public void run() {
				try {
					CommonBean resultCommonBean = asynImportScmMaterial(scmMaterialAddList,param);
					int successCount = 0;
			        int failCount = (scmMaterialAddList != null && !scmMaterialAddList.isEmpty()) ? scmMaterialAddList.size() : 0;
			        if (resultCommonBean != null) {
			            List<ScmMaterial2> scmMaterialList = (List<ScmMaterial2>) resultCommonBean.getList();
			            successCount = (scmMaterialList != null && !scmMaterialList.isEmpty()) ? scmMaterialList.size() : 0;
			            failCount = (failCount - successCount) >= 0 ? (failCount - successCount) : failCount;
			            List<String> rtnList = (List<String>) resultCommonBean.getList2();
			            if (rtnList != null && !rtnList.isEmpty()) {
			                msgInfoList.addAll(rtnList);
			            }
			        }
			        String infoString = "";
			        if (failCount == 0 && scmMaterialAddList != null && scmMaterialAddList.size() > 0) {
			            infoString = Message.getMessage(param.getLang(),"iscm.purchasemanage.information.Success", new String[]{successCount + ""});
			        } else {
			            infoString = Message.getMessage(param.getLang(),"iscm.purchasemanage.information.partSuccess", new String[]{successCount + "", failCount + ""});
			        }
			        StringBuilder msgInfos = new StringBuilder("");
			        boolean flag = false;
			        if (msgInfoList != null && !msgInfoList.isEmpty()) {
			            for (String msgInfo : msgInfoList) {
			                if (StringUtils.isNotBlank(msgInfos.toString())) {
			                    msgInfos.append("\r\n");
			                }
			                if((infoString.length()+"_".length()+msgInfos.toString().length()+msgInfo.length()+"......".length()) >= 500){
			                	flag = true;
			                	break;
			                }
			                msgInfos.append(msgInfo);
			            }
			        }
			        String resultMsg = "";
			        if(flag){
			        	resultMsg = infoString+"_"+msgInfos.toString()+"......";
			        }else{
			        	resultMsg = infoString+"_"+msgInfos.toString();
			        }
					//????
					scmDataCollectionStepStateBiz.updateByAsynProcessed(tempScmStepData, ScmDataCollectionStepState.SATATE_SUCCESS,resultMsg, param);
				} catch (Exception e) {
					//??????
					scmDataCollectionStepStateBiz.updateByAsynProcessed(tempScmStepData, ScmDataCollectionStepState.SATATE_FAIL, e.getMessage(), param);
				}
			}
		});
		stepState.setState(scmDataCollectionStepState.getState());
		commonBean.setObject(stepState);
		return commonBean;
	}
	
	private CommonBean asynImportScmMaterial(List<ScmMaterialAdd> scmMaterialAddList,
			Param param) throws AppException, PinyinException {
		CommonBean resultCommonBean = new CommonBean(); 
		List<String> rtnList = new ArrayList<>();
		HashMap<String,Object> updateBillSeqMap = new HashMap<String,Object>();
		String billCode = "ScmMaterial";
		List<ScmMaterial2> scmMaterialList = new ArrayList<>();
		if(scmMaterialAddList == null || scmMaterialAddList.isEmpty()){
			return resultCommonBean;
		}
		for (int i=0;i<scmMaterialAddList.size();i++) {
			ScmMaterialAdd scmMaterialAdd = scmMaterialAddList.get(i);
			if(!scmMaterialAdd.isCheckSuccess()){
				continue;
			}
			// ??????
			String classCode1 = scmMaterialAdd.getClassCode1();
			String className1 = scmMaterialAdd.getClassName1(); 
			long groupId = 0;
			ScmMaterialGroup scmMaterialGroup1 = null;
			if (StringUtils.isNotBlank(classCode1) && StringUtils.isNotBlank(className1)) {
				scmMaterialGroup1 = scmMaterialGroupBiz.selectByClassCode(classCode1, param);
				if (scmMaterialGroup1 != null) {
					groupId = scmMaterialGroup1.getId();
				} else {
					// ??????
					scmMaterialGroup1 = new ScmMaterialGroup(true);
					scmMaterialGroup1.setClassCode(classCode1);
					scmMaterialGroup1.setClassName(className1);
					scmMaterialGroup1.setFlag(true);
					scmMaterialGroup1.setParentId(0);
					scmMaterialGroup1.setCreator(param.getUsrCode());
					scmMaterialGroup1.setCreateDate(CalendarUtil.getDate(param));
					scmMaterialGroup1.setEditor(param.getUsrCode());
					scmMaterialGroup1.setEditDate(CalendarUtil.getDate(param));
					scmMaterialGroup1.setStandardId(1);
					scmMaterialGroup1.setControlUnitNo(param.getControlUnitNo());
					scmMaterialGroup1 = scmMaterialGroupBiz.add(scmMaterialGroup1, param);
					groupId = scmMaterialGroup1.getId();
				}
			}
            
            // ??????
			String classCode2 = scmMaterialAdd.getClassCode2();
			String className2 = scmMaterialAdd.getClassName2();
			ScmMaterialGroup scmMaterialGroup2 = null;
			if (StringUtils.isNotBlank(classCode2) && StringUtils.isNotBlank(className2)) {
				scmMaterialGroup2 = scmMaterialGroupBiz.selectByClassCode(classCode2, param);
				if (scmMaterialGroup2 != null) {
					String[] groupIds = StringUtils.split(scmMaterialGroup2.getLongNo(),",");
					if(groupIds != null && groupIds.length>0){
						String compareGroupId = "";
						int count = 0;
						for(int j=groupIds.length-1;j>=0;j--){
							if(StringUtils.isNotBlank(groupIds[j])){
								count++;
							}
							if(count == 2){
								compareGroupId = groupIds[j];
								break;
							}
						}
						if(StringUtils.isBlank(compareGroupId) || (StringUtils.isNotBlank(compareGroupId) && Integer.parseInt(compareGroupId) != scmMaterialGroup1.getId())){
							rtnList.add(Message.getMessage(param.getLang(),"com.armitage.server.iscm.scmMaterialBizImpl.importScmMaterial.classCode2.parentError", new String[]{String.valueOf(i+2), scmMaterialGroup1.getClassCode()}));
							continue;
						}
					}
					groupId = scmMaterialGroup2.getId();
				} else {
					// ??????
					scmMaterialGroup2 = new ScmMaterialGroup(true);
					scmMaterialGroup2.setClassCode(classCode2);
					scmMaterialGroup2.setClassName(className2);
					scmMaterialGroup2.setFlag(true);
					scmMaterialGroup2.setParentId(scmMaterialGroup1.getId());
					scmMaterialGroup2.setCreator(param.getUsrCode());
					scmMaterialGroup2.setCreateDate(CalendarUtil.getDate(param));
					scmMaterialGroup2.setEditor(param.getUsrCode());
					scmMaterialGroup2.setEditDate(CalendarUtil.getDate(param));
					scmMaterialGroup2.setStandardId(1);
					scmMaterialGroup2.setControlUnitNo(param.getControlUnitNo());
					scmMaterialGroup2 = scmMaterialGroupBiz.add(scmMaterialGroup2, param);
					groupId = scmMaterialGroup2.getId();
				}
			}
           
            // ??????
			String classCode3 = scmMaterialAdd.getClassCode3();
			String className3 = scmMaterialAdd.getClassName3();
			ScmMaterialGroup scmMaterialGroup3 = null;
			if (StringUtils.isNotBlank(classCode3) && StringUtils.isNotBlank(className3)) {
				scmMaterialGroup3 = scmMaterialGroupBiz.selectByClassCode(classCode3, param);
				if (scmMaterialGroup3 != null) {
					String[] groupIds = StringUtils.split(scmMaterialGroup3.getLongNo(),",");
					if(groupIds != null && groupIds.length>0){
						String compareGroupId = "";
						int count = 0;
						for(int j=groupIds.length-1;j>=0;j--){
							if(StringUtils.isNotBlank(groupIds[j])){
								count++;
							}
							if(count == 2){
								compareGroupId = groupIds[j];
								break;
							}
						}
						if(StringUtils.isBlank(compareGroupId) || (StringUtils.isNotBlank(compareGroupId) && Integer.parseInt(compareGroupId) != scmMaterialGroup2.getId())){
							rtnList.add(Message.getMessage(param.getLang(),"com.armitage.server.iscm.scmMaterialBizImpl.importScmMaterial.classCode3.parentError", new String[]{String.valueOf(i+2), scmMaterialGroup2.getClassCode()}));
							continue;
						}
					}
					groupId = scmMaterialGroup3.getId();
				} else {
					// ??????
					scmMaterialGroup3 = new ScmMaterialGroup(true);
					scmMaterialGroup3.setClassCode(classCode3);
					scmMaterialGroup3.setClassName(className3);
					scmMaterialGroup3.setFlag(true);
					scmMaterialGroup3.setParentId(scmMaterialGroup2.getId());
					scmMaterialGroup3.setCreator(param.getUsrCode());
					scmMaterialGroup3.setCreateDate(CalendarUtil.getDate(param));
					scmMaterialGroup3.setEditor(param.getUsrCode());
					scmMaterialGroup3.setEditDate(CalendarUtil.getDate(param));
					scmMaterialGroup3.setStandardId(1);
					scmMaterialGroup3.setControlUnitNo(param.getControlUnitNo());
					scmMaterialGroup3 = scmMaterialGroupBiz.add(scmMaterialGroup3, param);
					groupId = scmMaterialGroup3.getId();
				}
			}
			
			List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(groupId, param);
			if (scmMaterialGroupList != null && scmMaterialGroupList.size() > 1) {
				rtnList.add(Message.getMessage(param.getLang(),"com.armitage.server.iscm.scmMaterialBizImpl.importScmMaterial.groupId", new String[]{String.valueOf(i+2), scmMaterialAdd.getItemNo(), scmMaterialAdd.getItemName()}));
				continue;
			}
           
			
            // ???
			ScmMaterial2 scmMaterial = new ScmMaterial2(true);
			scmMaterial.setItemNo(scmMaterialAdd.getItemNo());
			scmMaterial.setImportItemNo(scmMaterialAdd.getItemNo());
			scmMaterial.setItemName(scmMaterialAdd.getItemName());
			scmMaterial.setSpec(scmMaterialAdd.getSpec());
			scmMaterial.setBaseUnitId(scmMaterialAdd.getBaseUnitId());
			scmMaterial.setCreator(param.getUsrCode());
			scmMaterial.setCreateDate(new Date());
			scmMaterial.setPym((StringUtils.upperCase(PinyinHelper.getShortPinyin(scmMaterialAdd.getItemName()))));
			scmMaterial.setStatus("A");
			scmMaterial.setAdminCuNo(param.getOrgUnitNo());
			scmMaterial.setControlUnitNo(param.getControlUnitNo());
			scmMaterial.setGroupId(groupId);
			scmMaterialAdd.setGroupId(groupId);
			if(scmMaterialAdd.getBrandId() > 0) {
				scmMaterial.setBrandId(scmMaterialAdd.getBrandId());
			}
			
			CodingRuleEntry2 codingRuleEntry = CodeAutoCalUtil.getPreCode(billCode, scmMaterial, param);
			if(codingRuleEntry == null){
				continue;
			}
			String preCode = codingRuleEntry.getPreCode();
			if(StringUtils.isBlank(preCode) || "null".equals(preCode)){
				preCode = "";
			}
			String key = param.getControlUnitNo()+"-"+billCode+"_"+preCode;
			
			if(StringUtils.isNotBlank(preCode)) {
				//??????
				if(!scmMaterial.getImportItemNo().startsWith(preCode.toString())){
					rtnList.add(Message.getMessage(param.getLang(),"com.armitage.server.iscm.scmMaterialBizImpl.importScmMaterial.codeRule.itemNo", new String[]{String.valueOf(i+2), preCode.toString()}));
					continue;
				}else{
					int len = codingRuleEntry.getCodeLength();
					String realItemNo = scmMaterial.getImportItemNo().substring(preCode.toString().length());
					if(StringUtils.isBlank(realItemNo) || (StringUtils.isNotBlank(realItemNo) 
							&& (!StringUtils.isNumeric(realItemNo) || realItemNo.trim().length()>len))){
						rtnList.add(Message.getMessage(param.getLang(),"com.armitage.server.iscm.scmMaterialBizImpl.importScmMaterial.codeRule.itemNo2", new String[]{String.valueOf(i+2), preCode.toString(),String.valueOf(len)}));
						continue;
					}else{
						if(updateBillSeqMap.containsKey(key)){
							String maxValueStr = String.valueOf(updateBillSeqMap.get(key));
							if(Integer.parseInt(realItemNo) > Integer.parseInt(maxValueStr)){
								updateBillSeqMap.put(key, realItemNo);
							}
						}else{
							updateBillSeqMap.put(key, realItemNo);
						}
					}
				}
			}else {
				//????????8?????
				String realItemNo = scmMaterial.getImportItemNo();
				if(StringUtils.isBlank(realItemNo) || (StringUtils.isNotBlank(realItemNo) 
						&& (!StringUtils.isNumeric(realItemNo) || realItemNo.trim().length()>8))){
					rtnList.add(Message.getMessage(param.getLang(),"com.armitage.server.iscm.scmMaterialBizImpl.importScmMaterial.noCodeRule.itemNo3", new String[]{String.valueOf(i+2),String.valueOf(8)}));
					continue;
				}else{
					if(updateBillSeqMap.containsKey(key)){
						String maxValueStr = String.valueOf(updateBillSeqMap.get(key));
						if(Integer.parseInt(realItemNo) > Integer.parseInt(maxValueStr)){
							updateBillSeqMap.put(key, realItemNo);
						}
					}else{
						updateBillSeqMap.put(key, realItemNo);
					}
				}
			}
			
			//????
			ScmMaterial2 tempScmMaterial = this.selectByItemNo(param.getControlUnitNo(), scmMaterialAdd.getItemNo(), param);
			if(tempScmMaterial != null){
				rtnList.add(Message.getMessage(param.getLang(),"com.armitage.iscm.basedata.util.importItemNoError", new String[]{String.valueOf(i + 2), tempScmMaterial.getItemName()}));
				continue;
			}
			
			CommonBean bean = new CommonBean();
			List<ScmMaterial2> scmMaterialList2 = new ArrayList<>(); 
			scmMaterialList2.add(scmMaterial);
			bean.setList(scmMaterialList2);
			this.add(bean,  param);
			scmMaterialList.add(scmMaterial);
			
            // ???????????
			if (scmMaterialAdd.getBaseUnitId() != scmMaterialAdd.getPurUnitId() || scmMaterialAdd.getDefaultRate().compareTo(BigDecimal.ZERO) > 0) {
				ScmMaterialPurchase2 scmMaterialPurchase = scmMaterialPurchaseBiz.selectByItemIdAndOrgUnitNo(scmMaterial.getId(), param.getOrgUnitNo(), param);
				scmMaterialPurchase.setPurUnit(scmMaterialAdd.getPurUnitId());
				scmMaterialPurchase.setDefaultRate(scmMaterialAdd.getDefaultRate());
				scmMaterialPurchaseBiz.update(scmMaterialPurchase, param);
				
				//??????????
				if(scmMaterialAdd.getBaseUnitId() != scmMaterialAdd.getPurUnitId()){
					ScmMaterialUnitRelation2 scmMaterialUnitRelation = new ScmMaterialUnitRelation2(true);
					scmMaterialUnitRelation.setItemId(scmMaterial.getId());
					scmMaterialUnitRelation.setGuId("");
					scmMaterialUnitRelation.setLineId(2);
					scmMaterialUnitRelation.setTargetUnitId(scmMaterialAdd.getPurUnitId());
					scmMaterialUnitRelation.setConvrate(scmMaterialAdd.getPurConvrate());
					scmMaterialUnitRelation.setBaseUnit(false);
					scmMaterialUnitRelation.setQtyPrecision(4);
					scmMaterialUnitRelation.setUseConvsUnit(false);
					scmMaterialUnitRelation.setMeasureUnitType("1");
					scmMaterialUnitRelationBiz.add(scmMaterialUnitRelation, param);
				}
			}
			
			// ???????????
			if (scmMaterialAdd.getBaseUnitId() != scmMaterialAdd.getInvUnitId()) {
				ScmMaterialInventory2 scmMaterialInventory = scmMaterialInventoryBiz.selectByItemIdAndOrgUnitNo(scmMaterial.getId(), param.getOrgUnitNo(), param);
				scmMaterialInventory.setUnitId(scmMaterialAdd.getInvUnitId());
				scmMaterialInventoryBiz.update(scmMaterialInventory, param);
				
				//??????????
				if(scmMaterialAdd.getBaseUnitId() != scmMaterialAdd.getInvUnitId() && scmMaterialAdd.getPurUnitId() != scmMaterialAdd.getInvUnitId()){
					ScmMaterialUnitRelation2 scmMaterialUnitRelation = new ScmMaterialUnitRelation2(true);
					scmMaterialUnitRelation.setItemId(scmMaterial.getId());
					scmMaterialUnitRelation.setGuId("");
					scmMaterialUnitRelation.setLineId(3);
					scmMaterialUnitRelation.setTargetUnitId(scmMaterialAdd.getInvUnitId());
					scmMaterialUnitRelation.setConvrate(scmMaterialAdd.getInvConvrate());
					scmMaterialUnitRelation.setBaseUnit(false);
					scmMaterialUnitRelation.setQtyPrecision(4);
					scmMaterialUnitRelation.setUseConvsUnit(false);
					scmMaterialUnitRelation.setMeasureUnitType("1");
					scmMaterialUnitRelationBiz.add(scmMaterialUnitRelation, param);
				}
			}
		}
		
		if(!updateBillSeqMap.isEmpty()){
			for(String key : updateBillSeqMap.keySet()){
				String maxValueStr = String.valueOf(updateBillSeqMap.get(key));
				String preCode = key.substring(key.lastIndexOf("_")+1);
				billSequenceBiz.updateByBillCode("ScmMaterial", param.getControlUnitNo(), preCode, Integer.parseInt(maxValueStr));
		    } 
		}
		
		resultCommonBean.setList(scmMaterialList);
		resultCommonBean.setList2(rtnList);
		return resultCommonBean;
	}

	@Override
	public CommonBean batchRatioSet(List<ScmMaterial2> scmMaterial2, Param createParam) throws AppException {
		CommonBean scmMaterial2s=null;
		if (scmMaterial2 !=null && scmMaterial2.size()>0) {
			scmMaterial2s= scmMaterialPurchaseBiz.batchRatioSet(scmMaterial2,createParam);
		}
		return scmMaterial2s;
	}

	@Override
	public List<String> disable(BaseModel baseModel,List<String> orgUnitList, Param param) throws AppException {
		List<String> msgInfoList = new ArrayList();
		if (baseModel instanceof ScmMaterial2) {
			ScmMaterial2 scmMaterial = this.selectDirect(((ScmMaterial2) baseModel).getId(), param);
			scmMaterial.setStatus("S");
			scmMaterial.setFreezeOrgUnit(param.getOrgUnitNo());
			scmMaterial.setFreezeTime(CalendarUtil.getDate(param));
			this.updateDirect(scmMaterial, param);
		}
		if(baseModel instanceof ScmMaterialPurchase2){
			if(orgUnitList!=null && !orgUnitList.isEmpty()) {
				for(String orgUnitNo:orgUnitList) {
					ScmMaterialPurchase2 scmMaterialPurchase = (ScmMaterialPurchase2)baseModel;
					scmMaterialPurchase.setOrgUnitNo(orgUnitNo);
					scmMaterialPurchase.setStatus("S");
					scmMaterialPurchase.setFreezeOrgUnit(param.getOrgUnitNo());
					scmMaterialPurchase.setFreezeTime(CalendarUtil.getDate(param));
					scmMaterialPurchaseBiz.updateByPurchase(scmMaterialPurchase,param);
				}
			}else {
				ScmMaterialPurchase2 scmMaterialPurchase = (ScmMaterialPurchase2)baseModel;
				scmMaterialPurchase.setOrgUnitNo(param.getOrgUnitNo());
				scmMaterialPurchase.setStatus("S");
				scmMaterialPurchase.setFreezeOrgUnit(param.getOrgUnitNo());
				scmMaterialPurchase.setFreezeTime(CalendarUtil.getDate(param));
				scmMaterialPurchaseBiz.updateByPurchase(scmMaterialPurchase,param);
			}
		}
		if(baseModel instanceof ScmMaterialInventory2){
			if(orgUnitList!=null && !orgUnitList.isEmpty()) {
				for(String orgUnitNo:orgUnitList) {
					ScmMaterialInventory2 scmMaterialInventory = (ScmMaterialInventory2)baseModel;
					scmMaterialInventory.setOrgUnitNo(orgUnitNo);
					scmMaterialInventory.setStatus("S");
					scmMaterialInventory.setFreezeOrgUnit(param.getOrgUnitNo());
					scmMaterialInventory.setFreezeTime(CalendarUtil.getDate(param));
					scmMaterialInventoryBiz.updateByInventory(scmMaterialInventory,param);
				}
			}else {
				ScmMaterialInventory2 scmMaterialInventory = (ScmMaterialInventory2)baseModel;
				scmMaterialInventory.setOrgUnitNo(param.getOrgUnitNo());
				scmMaterialInventory.setStatus("S");
				scmMaterialInventory.setFreezeOrgUnit(param.getOrgUnitNo());
				scmMaterialInventory.setFreezeTime(CalendarUtil.getDate(param));
				scmMaterialInventoryBiz.updateByInventory(scmMaterialInventory,param);
			}
		}
		if(baseModel instanceof ScmMaterialCompanyInfo){
			if(orgUnitList!=null && !orgUnitList.isEmpty()) {
				for(String orgUnitNo:orgUnitList) {
					ScmMaterialCompanyInfo scmMaterialCompanyInfo = (ScmMaterialCompanyInfo)baseModel;
					scmMaterialCompanyInfo.setOrgUnitNo(orgUnitNo);
					scmMaterialCompanyInfo.setStatus("S");
					scmMaterialCompanyInfo.setFreezeOrgUnit(param.getOrgUnitNo());
					scmMaterialCompanyInfo.setFreezeTime(CalendarUtil.getDate(param));
					scmMaterialCompanyInfoBiz.updateByCompanyInfo(scmMaterialCompanyInfo,param);
				}
			}else {
				ScmMaterialCompanyInfo scmMaterialCompanyInfo = (ScmMaterialCompanyInfo)baseModel;
				scmMaterialCompanyInfo.setOrgUnitNo(param.getOrgUnitNo());
				scmMaterialCompanyInfo.setStatus("S");
				scmMaterialCompanyInfo.setFreezeOrgUnit(param.getOrgUnitNo());
				scmMaterialCompanyInfo.setFreezeTime(CalendarUtil.getDate(param));
				scmMaterialCompanyInfoBiz.updateByCompanyInfo(scmMaterialCompanyInfo,param);
			}
		}
		return msgInfoList;
	}

	@Override
	public List<String> enable(BaseModel baseModel,List<String> orgUnitList, Param param) throws AppException {
		List<String> msgInfoList = new ArrayList();
		if (baseModel instanceof ScmMaterial2) {
			ScmMaterial2 scmMaterial = this.selectDirect(((ScmMaterial2) baseModel).getId(), param);
			scmMaterial.setStatus("A");
			scmMaterial.setFreezeOrgUnit("");
			scmMaterial.setFreezeTime(null);
			this.updateDirect(scmMaterial, param);
		}
		if(baseModel instanceof ScmMaterialPurchase2){
			if(orgUnitList!=null && !orgUnitList.isEmpty()) {
				for(String orgUnitNo:orgUnitList) {
					ScmMaterialPurchase2 scmMaterialPurchase = (ScmMaterialPurchase2)baseModel;
					scmMaterialPurchase.setOrgUnitNo(orgUnitNo);
					scmMaterialPurchase.setStatus("A");
					scmMaterialPurchase.setFreezeOrgUnit("");
					scmMaterialPurchase.setFreezeTime(null);
					scmMaterialPurchaseBiz.updateByPurchase(scmMaterialPurchase,param);
				}
			}else {
				ScmMaterialPurchase2 scmMaterialPurchase = (ScmMaterialPurchase2)baseModel;
				scmMaterialPurchase.setOrgUnitNo(param.getOrgUnitNo());
				scmMaterialPurchase.setStatus("A");
				scmMaterialPurchase.setFreezeOrgUnit("");
				scmMaterialPurchase.setFreezeTime(null);
				scmMaterialPurchaseBiz.updateByPurchase(scmMaterialPurchase,param);
			}
		}
		if(baseModel instanceof ScmMaterialInventory2){
			if(orgUnitList!=null && !orgUnitList.isEmpty()) {
				for(String orgUnitNo:orgUnitList) {
					ScmMaterialInventory2 scmMaterialInventory = (ScmMaterialInventory2)baseModel;
					scmMaterialInventory.setOrgUnitNo(orgUnitNo);
					scmMaterialInventory.setStatus("A");
					scmMaterialInventory.setFreezeOrgUnit("");
					scmMaterialInventory.setFreezeTime(null);
					scmMaterialInventoryBiz.updateByInventory(scmMaterialInventory,param);
				}
			}else {
				ScmMaterialInventory2 scmMaterialInventory = (ScmMaterialInventory2)baseModel;
				scmMaterialInventory.setOrgUnitNo(param.getOrgUnitNo());
				scmMaterialInventory.setStatus("A");
				scmMaterialInventory.setFreezeOrgUnit("");
				scmMaterialInventory.setFreezeTime(null);
				scmMaterialInventoryBiz.updateByInventory(scmMaterialInventory,param);
			}
		}
		if(baseModel instanceof ScmMaterialCompanyInfo){
			if(orgUnitList!=null && !orgUnitList.isEmpty()) {
				for(String orgUnitNo:orgUnitList) {
					ScmMaterialCompanyInfo scmMaterialCompanyInfo = (ScmMaterialCompanyInfo)baseModel;
					scmMaterialCompanyInfo.setOrgUnitNo(orgUnitNo);
					scmMaterialCompanyInfo.setStatus("A");
					scmMaterialCompanyInfo.setFreezeOrgUnit("");
					scmMaterialCompanyInfo.setFreezeTime(null);
					scmMaterialCompanyInfoBiz.updateByCompanyInfo(scmMaterialCompanyInfo,param);
				}
			}else {
				ScmMaterialCompanyInfo scmMaterialCompanyInfo = (ScmMaterialCompanyInfo)baseModel;
				scmMaterialCompanyInfo.setOrgUnitNo(param.getOrgUnitNo());
				scmMaterialCompanyInfo.setStatus("A");
				scmMaterialCompanyInfo.setFreezeOrgUnit("");
				scmMaterialCompanyInfo.setFreezeTime(null);
				scmMaterialCompanyInfoBiz.updateByCompanyInfo(scmMaterialCompanyInfo,param);
			}
		}
		return msgInfoList;
	}

	@Override
	public List<ScmMaterial2> findByPurItemIds(String controlUnitNo, String purOrgUnitNo, String itemids,Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("controlUnitNo", controlUnitNo);
		map.put("orgUnitNo", purOrgUnitNo);
		map.put("itemIds", itemids);
		return ((ScmMaterialDAO)dao).findByPurItemIds(map);
	}
	
	@Override
	public List<ScmMaterial2> findByInvItemIds(String controlUnitNo, String purOrgUnitNo, String itemids,Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("controlUnitNo", controlUnitNo);
		map.put("orgUnitNo", purOrgUnitNo);
		map.put("itemIds", itemids);
		return ((ScmMaterialDAO)dao).findByInvItemIds(map);
	}

	@Override
	public List<ScmMaterial2> queryInvOrgMaterialList(InvOrgMaterialListParams invOrgMaterialListParams, int pageNum,
			Param createParam) throws AppException {
		List<ScmMaterialGroup> scmMaterialGroup = new ArrayList<>();
		OrgStorage2 orgStorage2 = orgStorageBiz.selectByOrgUnitNo(invOrgMaterialListParams.getInvOrgUnitNo(), createParam);
		if (orgStorage2 == null) {
			throw new AppException("iscm.scmmaterial.queryInvOrgMaterialList.error.invOrgUnitNonotexists");
		}
		if (StringUtils.isNotBlank(invOrgMaterialListParams.getClassCode())) {
			String[] split = invOrgMaterialListParams.getClassCode().split(",");
			for (String string : split) {
				List<ScmMaterialGroup> scmMaterialGroups = scmMaterialGroupBiz.findChild(string, createParam);
				if (scmMaterialGroups != null && !scmMaterialGroups.isEmpty()) {
					scmMaterialGroup.addAll(scmMaterialGroups);
				}
			}
			if (scmMaterialGroup == null || scmMaterialGroup.isEmpty()) {
				return null;
			}
		}
		Page page = new Page();
		page.setModelClass(ScmMaterial2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		List<String> arglist = new ArrayList<String>();
		arglist.add("orgUnitNo="+invOrgMaterialListParams.getInvOrgUnitNo());
		arglist.add("controlUnitNo="+orgStorage2.getControlUnitNo());
		if (StringUtils.isNotBlank(invOrgMaterialListParams.getClassCode())) {
			StringBuffer classIdBuffer = new StringBuffer();
			for (ScmMaterialGroup scmMaterialGroup2 : scmMaterialGroup) {
				if (StringUtils.isNotBlank(classIdBuffer.toString())) {
					classIdBuffer.append(",");
				}
				classIdBuffer.append(scmMaterialGroup2.getId());
			}
			arglist.add("classId="+classIdBuffer.toString());
		}
		if(StringUtils.isNotBlank(invOrgMaterialListParams.getMixParam())){
			page.setSqlCondition("(ScmMaterial.itemNo like '%"+invOrgMaterialListParams.getMixParam()+"%' or ScmMaterial.itemName like '%"+invOrgMaterialListParams.getMixParam()+
					"%' or ScmMaterial.barCode like '%"+invOrgMaterialListParams.getMixParam()+"%' or ScmMaterial.pym like '%"+invOrgMaterialListParams.getMixParam()+"%')");
		}
		return this.queryPage(page, arglist, "findBySingleInvAllPage", createParam);
	}

	@Override
	public List<ScmMaterial2> checkUnitRelation(long itemId, long targetUnitId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("itemId", itemId);
		map.put("unitid", targetUnitId);
		map.put("controlUnitNo", param.getControlUnitNo());
		return ((ScmMaterialDAO)dao).checkUnitRelation(map);
	}
	
}
