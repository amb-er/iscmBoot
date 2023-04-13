
package com.armitage.server.iscm.purchasemanage.purchasesetting.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.cache.ModelCacheMana;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation2;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurQuotationBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurBuyerDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyerOrg2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyerWSBean;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurGroup;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerOrgBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurGroupBiz;
import com.armitage.server.system.model.Employee;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.service.EmployeeBiz;
import com.armitage.server.system.service.OrgAdminBiz;
import org.springframework.stereotype.Service;

@Service("scmPurBuyerBiz")
public class ScmPurBuyerBizImpl extends BaseBizImpl<ScmPurBuyer2> implements ScmPurBuyerBiz {

	private OrgAdminBiz orgAdminBiz;
	private ScmPurGroupBiz scmPurGroupBiz;
	private EmployeeBiz employeeBiz;
	private ScmPurRequireEntryBiz scmPurRequireEntryBiz;
	private ScmPurQuotationBiz scmPurQuotationBiz;
	private ScmPurOrderBiz scmPurOrderBiz;
	private ScmPurBuyerOrgBiz scmPurBuyerOrgBiz;
	
	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}

	public void setScmPurGroupBiz(ScmPurGroupBiz scmPurGroupBiz) {
		this.scmPurGroupBiz = scmPurGroupBiz;
	}

	public void setEmployeeBiz(EmployeeBiz employeeBiz) {
		this.employeeBiz = employeeBiz;
	}
	
	public void setScmPurRequireEntryBiz(ScmPurRequireEntryBiz scmPurRequireEntryBiz) {
		this.scmPurRequireEntryBiz = scmPurRequireEntryBiz;
	}
	
	public void setScmPurQuotationBiz(ScmPurQuotationBiz scmPurQuotationBiz) {
		this.scmPurQuotationBiz = scmPurQuotationBiz;
	}
	
	public void setScmPurOrderBiz(ScmPurOrderBiz scmPurOrderBiz) {
		this.scmPurOrderBiz = scmPurOrderBiz;
	}

	public void setScmPurBuyerOrgBiz(ScmPurBuyerOrgBiz scmPurBuyerOrgBiz) {
		this.scmPurBuyerOrgBiz = scmPurBuyerOrgBiz;
	}

	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page, HashMap<String, Object> map, Param param) {
		map.put("orgUnitNo", param.getOrgUnitNo());
		return map;
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list != null && !list.isEmpty()){
			for (int i = list.size()-1; i >= 0; i--) {
				ScmPurBuyer2 scmPurBuyer = (ScmPurBuyer2) list.get(i);
				if (!setConvertMap(scmPurBuyer, param)) {
					list.remove(scmPurBuyer);
				}
			}
		}
	}
	
	
	@Override
	protected void afterQueryPage(List list, Page page, String xmlId,Param param) throws AppException {
		if(list != null && !list.isEmpty()){
			for (int i = list.size()-1; i >= 0; i--) {
				ScmPurBuyer2 scmPurBuyer = (ScmPurBuyer2) list.get(i);
				if (!setConvertMap(scmPurBuyer, param)) {
					list.remove(scmPurBuyer);
				}
			}
		}
	}

	@Override
	protected void afterSelect(ScmPurBuyer2 entity, Param param) throws AppException {
		if(entity!=null)
			setConvertMap(entity,param);
	}

	/**
	 * 修改采购组的状态
	 */
	@Override
	public ScmPurGroup updateGroup(ScmPurGroup entity, Param param) throws AppException {
		
		return scmPurGroupBiz.update(entity, param);
	}

	@Override
	public List<ScmPurBuyer> selectByParentId(Long parentId,Param param) throws AppException {
		
		return ((ScmPurBuyerDAO) dao).selectByParentId(parentId);
	}

	@Override
	public ScmPurBuyer2 selectByCode(String buyerCode, Param param) throws AppException {
		String key = param.getOrgUnitNo()+"_"+buyerCode;
		if (ModelCacheMana.keyExists(key, modelClassById == null ? modelClass : modelClassById)) {
			Object obj = ModelCacheMana.get(key, modelClassById == null ? modelClass : modelClassById);
			if (obj != null && obj.getClass().equals(modelClass)) {
				return (ScmPurBuyer2) obj;
			}
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("buyerCode", buyerCode);
		map.put("orgUnitNo", param.getOrgUnitNo());
		ScmPurBuyer2 scmPurBuyer =  ((ScmPurBuyerDAO)dao).selectByCode(map);
		if (scmPurBuyer !=null) {
			ModelCacheMana.set(key, scmPurBuyer);
		}
		return scmPurBuyer;
	}

	@Override
	protected void beforeAdd(ScmPurBuyer2 entity, Param param) throws AppException {
		if(entity.getEmpId()>0){
			Employee employee = employeeBiz.selectDirect(entity.getEmpId(), param);
			if(employee!=null)
				entity.setBuyerCode(employee.getEmpNo());
		}
	}

	private boolean setConvertMap(ScmPurBuyer2 scmPurBuyer, Param param) throws AppException {
		if(scmPurBuyer!=null){
			//根据empId获取人员信息
			Employee employee = employeeBiz.selectDirect(scmPurBuyer.getEmpId(), param);
			if (employee != null && StringUtils.equals(employee.getStatus(), "1")) {
				scmPurBuyer.setEmpNo(employee.getEmpNo());
				scmPurBuyer.setEmpName(employee.getEmpName());
				scmPurBuyer.setOrgUnitNo(employee.getOrgUnitNo());
				if (StringUtils.isNotBlank(employee.getOrgUnitNo())){
					//行政组织
					OrgAdmin2 orgAdmin = orgAdminBiz.selectByOrgUnitNo(employee.getOrgUnitNo(), param);
					if (orgAdmin != null) {
						scmPurBuyer.setConvertMap(ScmPurBuyer2.FN_ORGUNITNO, orgAdmin);
					}
				}
				scmPurBuyer.setGender(employee.getGender());
				if(StringUtils.isBlank(scmPurBuyer.getBuyerCode())){
					scmPurBuyer.setBuyerCode(employee.getEmpNo());
					this.updateDirect(scmPurBuyer, param);
				}
				return true;
			}
		}
		return false;
	}
	
	@Override
	public List<String> deleteBuyerCheck(List<ScmPurBuyer2> scmPurBuyerList,
			Param param) throws AppException {
		List<String> msgList = new ArrayList<String>();// 提示列表
		if (scmPurBuyerList != null && !scmPurBuyerList.isEmpty()) {
			for (ScmPurBuyer2 scmPurBuyer:scmPurBuyerList) {
				long count = deleteBuyerCheckList(scmPurBuyer, param);
				if (count > 0) {
					String[] msparam = {scmPurBuyer.getBuyerName()};
					msgList.add(Message.getMessage(param.getLang(),
							"iscm.purchasemanage.purchasesetting.deleteBuyerCheck.error", msparam));
					return msgList;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * 判断采购员id有没有使用，已使用则不能删除，只能提示停用
	 * @param entity
	 * @param param
	 * @return
	 */
	private long deleteBuyerCheckList(ScmPurBuyer2 entity, Param param) {
		HashMap<String,Object> scmPurRequireEntryMap = new HashMap<String,Object>();
		long count = 0;
		
		long buyerId = entity.getId();
		scmPurRequireEntryMap.put(ScmPurRequireEntry.FN_BUYERID, buyerId);
		scmPurRequireEntryMap.put(ScmPurBuyer.FN_CONTROLUNITNO, param.getControlUnitNo());
		
		List<ScmPurRequireEntry2> scmPurBuyerList = scmPurRequireEntryBiz.selectByBuyerId(scmPurRequireEntryMap);
		if (scmPurBuyerList != null && !scmPurBuyerList.isEmpty()) {
			if (scmPurBuyerList.size() > 0) {
				count = scmPurBuyerList.size();
				return count;
			}
		}
		
		List<ScmPurQuotation2> scmPurQuotationList = scmPurQuotationBiz.findAll(scmPurRequireEntryMap, param);
		if (scmPurQuotationList != null && !scmPurQuotationList.isEmpty()) {
			if (scmPurQuotationList.size() > 0) {
				count = scmPurQuotationList.size();
				return count;
			}
		}
		
		List<ScmPurOrder2> scmPurOrderList = scmPurOrderBiz.findAll(scmPurRequireEntryMap, param);
		if (scmPurOrderList != null && !scmPurOrderList.isEmpty()) {
			if (scmPurOrderList.size() > 0) {
				count = scmPurOrderList.size();
				return count;
			}
		}
		
		return count;
	}

	@Override
	public ScmPurBuyerWSBean getGrantedOrg(ScmPurBuyer2 scmPurBuyer, Param param) throws AppException {
		if (scmPurBuyer == null)
			throw new AppException("argument.null", new String[] { "scmPurBuyer" });

		ScmPurBuyerWSBean bean = new ScmPurBuyerWSBean();
		bean.setList(scmPurBuyerOrgBiz.selectByBuyerId(scmPurBuyer.getId(), param));
		return bean;
	}

	@Override
	public void grantOrgUnit(ScmPurBuyer2 scmPurBuyer, List<ScmPurBuyerOrg2> scmPurBuyerOrgList, Param param)
			throws AppException {
		if (scmPurBuyer == null)
			throw new AppException("argument.null", new String[] { "salesman" });

		// 检查是否有更新删除salesmanOrg
		List<ScmPurBuyerOrg2> oldScmPurBuyerOrgList = scmPurBuyerOrgBiz.selectByBuyerId(scmPurBuyer.getId(), param);

		// 添加的数据。
		if (scmPurBuyerOrgList != null) {
			for (ScmPurBuyerOrg2 addScmPurBuyerOrg : scmPurBuyerOrgList) {
				if (addScmPurBuyerOrg.getId() <= 0) {
					addScmPurBuyerOrg.setBuyerId(scmPurBuyer.getId());
					scmPurBuyerOrgBiz.add(addScmPurBuyerOrg, param);
				}
			}
		}
		// 删除的数据
		if (oldScmPurBuyerOrgList != null) {
			for (ScmPurBuyerOrg2 scmPurBuyerOrg : oldScmPurBuyerOrgList) {
				boolean isExists = false;
				if (scmPurBuyerOrg != null) {
					if(scmPurBuyerOrgList != null && !scmPurBuyerOrgList.isEmpty()){
						for (ScmPurBuyerOrg2 newSalesmanOrg : scmPurBuyerOrgList) {
							if (scmPurBuyerOrg.getId() == newSalesmanOrg.getId()) {
								isExists = true;
								break;
							}
						}
					}
				}
				if (!isExists) {
					scmPurBuyerOrgBiz.delete(scmPurBuyerOrg, param);
				}
			}
		}
	}
}
