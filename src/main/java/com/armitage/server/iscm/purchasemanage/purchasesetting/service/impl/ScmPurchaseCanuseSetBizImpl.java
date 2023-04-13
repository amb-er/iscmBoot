
package com.armitage.server.iscm.purchasemanage.purchasesetting.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.base.model.SimpleDataDisplay;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.ScmPurchaseType2;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmPurchaseTypeBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSet;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSetBizType2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSetMC2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSetOrg2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurchaseCanuseSetBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurchaseCanuseSetBizTypeBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurchaseCanuseSetMCBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurchaseCanuseSetOrgBiz;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmPurchaseCanuseSetBiz")
public class ScmPurchaseCanuseSetBizImpl extends BaseBizImpl<ScmPurchaseCanuseSet> implements ScmPurchaseCanuseSetBiz {

	private ScmPurchaseCanuseSetOrgBiz scmPurchaseCanuseSetOrgBiz;
	private ScmPurchaseCanuseSetMCBiz scmPurchaseCanuseSetMCBiz;
	private ScmPurchaseCanuseSetBizTypeBiz scmPurchaseCanuseSetBizTypeBiz;
	private UsrBiz usrBiz;
	private ScmPurchaseTypeBiz scmPurchaseTypeBiz;
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	private OrgPurchaseBiz orgPurchaseBiz;
	
	
	public OrgPurchaseBiz getOrgPurchaseBiz() {
		return orgPurchaseBiz;
	}

	public void setOrgPurchaseBiz(OrgPurchaseBiz orgPurchaseBiz) {
		this.orgPurchaseBiz = orgPurchaseBiz;
	}

	public void setScmPurchaseCanuseSetMCBiz(ScmPurchaseCanuseSetMCBiz scmPurchaseCanuseSetMCBiz) {
		this.scmPurchaseCanuseSetMCBiz = scmPurchaseCanuseSetMCBiz;
	}

	public void setScmPurchaseCanuseSetOrgBiz(ScmPurchaseCanuseSetOrgBiz scmPurchaseCanuseSetOrgBiz) {
		this.scmPurchaseCanuseSetOrgBiz = scmPurchaseCanuseSetOrgBiz;
	}

	public void setScmPurchaseCanuseSetBizTypeBiz(ScmPurchaseCanuseSetBizTypeBiz scmPurchaseCanuseSetBizTypeBiz) {
		this.scmPurchaseCanuseSetBizTypeBiz = scmPurchaseCanuseSetBizTypeBiz;
	}
	
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setScmPurchaseTypeBiz(ScmPurchaseTypeBiz scmPurchaseTypeBiz) {
		this.scmPurchaseTypeBiz = scmPurchaseTypeBiz;
	}

	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		if(bean!=null) {
			ScmPurchaseCanuseSet scmPurchaseCanuseSet = null;
			if(bean.getList()!=null && !bean.getList().isEmpty())
				scmPurchaseCanuseSet = (ScmPurchaseCanuseSet)(bean.getList().get(0));
			if(scmPurchaseCanuseSet!=null) {
				List<ScmPurchaseCanuseSetOrg2> scmPurchaseCanuseSetOrgList  = bean.getList2();
				List<ScmPurchaseCanuseSetMC2> scmPurchaseCanuseSetMCList = bean.getList3();
				List<ScmPurchaseCanuseSetBizType2> scmPurchaseCanuseSetBizTypeList = bean.getList4();
				if(StringUtils.equals(scmPurchaseCanuseSet.getDeptScope(), "A")) {
					scmPurchaseCanuseSetOrgList = new ArrayList();
				}
				scmPurchaseCanuseSetOrgBiz.update(scmPurchaseCanuseSet, scmPurchaseCanuseSetOrgList, ScmPurchaseCanuseSetOrg2.FN_PCSID, param);
				if(StringUtils.equals(scmPurchaseCanuseSet.getItemScope(),"A")) {
					scmPurchaseCanuseSetMCList = new ArrayList();
				}
				scmPurchaseCanuseSetMCBiz.update(scmPurchaseCanuseSet,scmPurchaseCanuseSetMCList,ScmPurchaseCanuseSetMC2.FN_PCSID, param);
				if(StringUtils.equals(scmPurchaseCanuseSet.getBizTypeScope(),"A")) {
					scmPurchaseCanuseSetBizTypeList = new ArrayList();
				}
				scmPurchaseCanuseSetBizTypeBiz.update(scmPurchaseCanuseSet,scmPurchaseCanuseSetBizTypeList,ScmPurchaseCanuseSetBizType2.FN_PCSID,param);
			}
		}
	}
	
	
	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmPurchaseCanuseSet scmPurchaseCanuseSet = (ScmPurchaseCanuseSet) bean.getList().get(0);
		if (scmPurchaseCanuseSet !=null) {
			scmPurchaseCanuseSet.setEditor(param.getUsrCode());
			scmPurchaseCanuseSet.setEditDate(new Date());
		}
	}
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		afterAdd(bean, param);
	}
	
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmPurchaseCanuseSet scmPurchaseCanuseSet = (ScmPurchaseCanuseSet)(bean.getList().get(0));

		if(StringUtils.equals(scmPurchaseCanuseSet.getDeptScope(), "S")) {
			bean.setList2(scmPurchaseCanuseSetOrgBiz.selectByPcsId(scmPurchaseCanuseSet.getId(), param));
		}
		
		if(StringUtils.equals(scmPurchaseCanuseSet.getItemScope(), "S")) {
			bean.setList3(scmPurchaseCanuseSetMCBiz.selectByPcsId(scmPurchaseCanuseSet.getId(), param));
		}
		
		if(StringUtils.equals(scmPurchaseCanuseSet.getBizTypeScope(), "S")) {
			bean.setList4(scmPurchaseCanuseSetBizTypeBiz.selectByPcsId(scmPurchaseCanuseSet.getId(), param));
		}
		setConvertMap(scmPurchaseCanuseSet, param);
	}
	
	private void setConvertMap(ScmPurchaseCanuseSet scmPurchaseCanuseSet,Param param){
		if (StringUtils.isNotBlank(scmPurchaseCanuseSet.getEditor())) {
			//修改人
			Usr usr = usrBiz.selectByCode(scmPurchaseCanuseSet.getEditor(), param);
			if (usr != null) {
				if (scmPurchaseCanuseSet.getDataDisplayMap() == null) {
					scmPurchaseCanuseSet.setDataDisplayMap(new HashMap<String, SimpleDataDisplay>());
				}
				scmPurchaseCanuseSet.getDataDisplayMap().put(ScmPurRequire2.FN_EDITOR,SimpleDataDisplayUtil.convert(usr));
				scmPurchaseCanuseSet.setConvertMap(ScmPurRequire2.FN_EDITOR, usr);
			}
		}
		if (StringUtils.isNotBlank(scmPurchaseCanuseSet.getCreator())) {
			//创建人
			Usr usr = usrBiz.selectByCode(scmPurchaseCanuseSet.getCreator(), param);
			if (usr != null) {
				if(scmPurchaseCanuseSet.getDataDisplayMap()==null){
					scmPurchaseCanuseSet.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
				}
				scmPurchaseCanuseSet.getDataDisplayMap().put(ScmPurRequire2.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
				scmPurchaseCanuseSet.setConvertMap(ScmPurRequire2.FN_CREATOR, usr);
			}
		}
        if (StringUtils.isNotBlank(scmPurchaseCanuseSet.getOrgUnitNo())){
            //制单组织
            OrgPurchase2 selectByOrgUnitNo = orgPurchaseBiz.selectByOrgUnitNo(scmPurchaseCanuseSet.getOrgUnitNo(), param);
            if (selectByOrgUnitNo != null) {
            	scmPurchaseCanuseSet.setConvertMap(ScmPurchaseCanuseSet.FN_ORGUNITNO, selectByOrgUnitNo);
            }
        }
	}
	
	@Override
	protected void afterFindPage(List list, Page page, Param param) {
		List<ScmPurchaseCanuseSet> scmPurchaseCanuseSetList = list;
		for (ScmPurchaseCanuseSet scmPurchaseCanuseSet : scmPurchaseCanuseSetList) {
			setConvertMap(scmPurchaseCanuseSet, param);
		}
	}

	protected void beforeFindPage(Page page, Param param)throws AppException {
		List<OrgPurchase2> orgPurchase2s = orgPurchaseBiz.findChild(param.getOrgUnitNo(), param);
		StringBuffer orgs = new StringBuffer();
		if(orgPurchase2s!= null && !orgPurchase2s.isEmpty()) {
			for(OrgPurchase2 orgPurchase:orgPurchase2s) {
				if(!StringUtils.isEmpty(orgs.toString()))
					orgs.append(",");
				orgs.append(orgPurchase.getOrgUnitNo());
			}
			page.getParam().put(ScmPurchaseCanuseSet.FN_ORGUNITNO, new QueryParam(ScmPurchaseCanuseSet.FN_ORGUNITNO,QueryParam.QUERY_IN,orgs.toString()));
		}
		page.getParam().put(ScmPurchaseCanuseSet.FN_CONTROLUNITNO, new QueryParam(ScmPurchaseCanuseSet.FN_CONTROLUNITNO,QueryParam.QUERY_EQ,param.getControlUnitNo()));
	}

	@Override
	public String checkDate(ScmPurRequire2 scmPurRequire2, List<ScmPurRequireEntry2> scmPurRequireEntryList,Param param) throws AppException {
		StringBuffer msg = new StringBuffer("");
		if (scmPurRequire2 != null) {
			if(StringUtils.isBlank(scmPurRequire2.getBizType())) {
				//未选择采购类型
				msg.append(Message.getMessage(param.getLang(), "iscm.service.ScmPurRequire.error.notpurtype"));
				return msg.toString();
			}
			ScmPurchaseType2 scmPurchaseType = scmPurchaseTypeBiz.selectByCodeAncCtrl(scmPurRequire2.getBizType(), param);
			if(scmPurchaseType==null) {
				//采购类型错误
				msg.append(Message.getMessage(param.getLang(), "iscm.service.ScmPurRequire.error.wrongpurtype"));
				return msg.toString();
			}
			Page page = new Page();
			page.setModelClass(ScmPurchaseCanuseSet.class);
			page.setShowCount(Integer.MAX_VALUE);
			ArrayList<String> arrayList = new ArrayList<>();
			arrayList.add("orgUnitNo=" + param.getOrgUnitNo());
			arrayList.add("controlUnitNo=" + param.getControlUnitNo());
			arrayList.add("purOrgUnitNo=" + scmPurRequire2.getPurOrgUnitNo());
			arrayList.add("bizTypeId=" + scmPurchaseType.getId());
			List<ScmPurchaseCanuseSet> scmPurchaseCanuseSetList = this.queryPage(page, arrayList, "checkDate",param);
			if(scmPurchaseCanuseSetList==null || scmPurchaseCanuseSetList.isEmpty()) {
				//申购组织未设置可申购的条件
				msg.append(Message.getMessage(param.getLang(), "iscm.service.ScmPurRequire.checkDate.error.nosetting"));
				return msg.toString();
			}
			boolean allItem=false;
			int currDay = CalendarUtil.getDate(param).getDate();
			String currTime = FormatUtils.fmtDateTime(CalendarUtil.getDate(param), "HH:mm");
			StringBuffer ids = new StringBuffer("");
			for(ScmPurchaseCanuseSet scmPurchaseCanuseSet:scmPurchaseCanuseSetList) {
				boolean canOrder = false;
				if(StringUtils.equals("A", scmPurchaseCanuseSet.getItemScope())) {
					allItem = true;
				}
				if(StringUtils.equals("S",scmPurchaseCanuseSet.getDateScope())) {
					if(StringUtils.isBlank(scmPurchaseCanuseSet.getDateSlot())) {
						canOrder = false;
						msg.append(Message.getMessage(param.getLang(), "iscm.service.ScmPurRequire.checkDate.error.nodateSlot"));
						continue;
					}else {
						canOrder = true;
						msg = new StringBuffer("");
						String dateList[] = StringUtils.split(scmPurchaseCanuseSet.getDateSlot(), ",");
						boolean exists=false;
						for(String day:dateList) {
							if(StringUtils.contains(day, "-")) {
								if(currDay>=Integer.valueOf(StringUtils.substringBefore(day,"-")) &&
									currDay<=Integer.valueOf(StringUtils.substringAfter(day,"-"))) {
									exists = true;
									break;
								}
							}else {
								if(currDay==Integer.valueOf(day)) {
									exists = true;
									break;
								}
							}
						}
						if(!exists) {
							canOrder = false;
							msg.append(Message.getMessage(param.getLang(), "iscm.service.ScmPurRequire.checkDate.error.nomeetdaterange",new String[]{scmPurchaseCanuseSet.getDateSlot()}));
							continue;
						}else {
							msg = new StringBuffer("");
							canOrder = true;
						}
					}
				}
				if(StringUtils.equals("S",scmPurchaseCanuseSet.getTimeScope())) {
					if(StringUtils.isBlank(scmPurchaseCanuseSet.getTimeSlot())) {
						canOrder = false;
						msg.append(Message.getMessage(param.getLang(), "iscm.service.ScmPurRequire.checkDate.error.notimeSlot"));
						continue;
					}else {
						canOrder = true;
						msg = new StringBuffer("");
						String timeList[] = StringUtils.split(scmPurchaseCanuseSet.getTimeSlot(), ",");
						boolean exists=false;
						for(String time:timeList) {
							if(StringUtils.contains(time, "-")) {
								if(currTime.compareTo(StringUtils.substringBefore(time,"-"))>=0 &&
										currTime.compareTo(StringUtils.substringAfter(time,"-"))<=0) {
									exists = true;
									break;
								}
							}else {
								if(currTime.contains(time)) {
									exists = true;
									break;
								}
							}
						}
						if(!exists) {
							canOrder = false;
							msg.append(Message.getMessage(param.getLang(), "iscm.service.ScmPurRequire.checkDate.error.nomeettimerange",new String[]{scmPurchaseCanuseSet.getTimeSlot()}));
							continue;
						}else {
							msg = new StringBuffer("");
							canOrder = true;
						}
					}
				}
				if(canOrder) {
					if(StringUtils.isNotBlank(ids.toString()))
						ids.append(",");
					ids.append(scmPurchaseCanuseSet.getId());
				}
			}
			if(StringUtils.isEmpty(ids.toString())) {
				//申购组织不在可申购时段内
				return msg.toString();
			}
			if(!allItem) {
				//指定物资类别则获取所有物资类别
				page = new Page();
				page.setModelClass(ScmPurchaseCanuseSetMC2.class);
				page.setShowCount(Integer.MAX_VALUE);
				arrayList = new ArrayList<>();
				arrayList.add("pcsIds=" + ids.toString());
				List<ScmPurchaseCanuseSetMC2> scmPurchaseCanuseSetMCList = scmPurchaseCanuseSetMCBiz.queryPage(page,arrayList,"selectByPcsIds", param);
				if(scmPurchaseCanuseSetMCList==null || scmPurchaseCanuseSetMCList.isEmpty()) {
					msg.append(Message.getMessage(param.getLang(), "iscm.service.ScmPurRequire.checkDate.error.nomaterialclass"));
					return msg.toString();
				}
				for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
					ScmMaterialGroup scmMaterialGroup = scmMaterialGroupBiz.selectByItemId(scmPurRequireEntry.getItemId(), param);
					if(scmMaterialGroup!=null) {
						boolean exists=false;
						for(ScmPurchaseCanuseSetMC2 scmPurchaseCanuseSetMC:scmPurchaseCanuseSetMCList) {
							if(StringUtils.contains(","+scmMaterialGroup.getLongNo(),","+scmPurchaseCanuseSetMC.getLongNo())) {
								exists=true;
								break;
							}
						}
						if(!exists) {
							if(StringUtils.isNotBlank(msg.toString()))
								msg.append("\r\n");
							msg.append(Message.getMessage(param.getLang(), "iscm.service.ScmPurRequire.checkDate.error.materialnotmet",new String[] {scmPurRequireEntry.getItemName()}));
						}
					}
				}
			}
		}
		return msg.toString();
	}
	
	public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
		if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime()) {
			return true;
		}
		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		begin.setTime(startTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void afterDelete(ScmPurchaseCanuseSet entity, Param param) throws AppException {
		if (entity != null && entity.getId()>0) {
			scmPurchaseCanuseSetOrgBiz.deleteByPcsId(entity.getId(), param);
			scmPurchaseCanuseSetMCBiz.deleteByPcsId(entity.getId(), param);
			scmPurchaseCanuseSetBizTypeBiz.deleteByPcsId(entity.getId(), param);
		}
	}
}
