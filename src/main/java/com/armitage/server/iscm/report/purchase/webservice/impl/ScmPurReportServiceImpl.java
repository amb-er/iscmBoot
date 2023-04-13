package com.armitage.server.iscm.report.purchase.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;

import com.alibaba.fastjson.JSON;
import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurDelivery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.report.purchase.model.OrderTransTotalAPI;
import com.armitage.server.iscm.report.purchase.model.ScmPurHistoryPrice;
import com.armitage.server.iscm.report.purchase.model.ScmPurOrderTransInfo;
import com.armitage.server.iscm.report.purchase.model.ScmPurOrderTransTotal;
import com.armitage.server.iscm.report.purchase.model.ScmPurReturnInfo;
import com.armitage.server.iscm.report.purchase.model.ScmPurSupplierAppraiseDetails;
import com.armitage.server.iscm.report.purchase.service.ScmPurReportBiz;
import com.armitage.server.iscm.report.purchase.webservice.ScmPurReportService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;

@Controller
@Path("/scmPurReportService")
public class ScmPurReportServiceImpl extends BaseServiceImpl<ScmPurReportBiz, BaseWSBean> implements ScmPurReportService {
	
	//供应商综合情况表
	@Override
	public Object selectSupplierConsolidation(HttpServletRequest request){
		List<ScmPurOrder2> list=new ArrayList<>();
		try {
			list=biz.selectSupplierConsolidation(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String data = JSON.toJSONString(list);
		return data;
	}
	
	//物资采购排行榜
	@Override
	public Object selectMaterialProcurement(HttpServletRequest request){
		List<ScmPurOrder2> list=new ArrayList<>();
		try {
			list=biz.selectMaterialProcurement(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String data = JSON.toJSONString(list);
		return data;
	}
	
	//供应商交易汇总表
	@Override
	public Object selectSupTransSummary(HttpServletRequest request){
		List<OrderTransTotalAPI> list=new ArrayList<>();
		try {
			list=biz.selectSupTransSummary(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String data = JSON.toJSONString(list);
		return data;
	}
	
	//物资交易明细表（按供应商*库存组织）
	@Override
	public Object selectMaterialTransDetails(HttpServletRequest request){
		List<ScmPurOrder2> list=new ArrayList<>();
		try {
			list=biz.selectMaterialTransDetails(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String data = JSON.toJSONString(list);
		return data;
	}
		
	//供应商交易物资汇总
	@Override
	public Object selectSupTransItemSummary(HttpServletRequest request){
		List<ScmPurOrderTransInfo> list=new ArrayList<>();
		try {
			list=biz.selectSupTransItemSummary(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String data = JSON.toJSONString(list);
		return data;
	}

	@Override
	public Object selectPODueOrNot(HttpServletRequest request) {
		List<ScmPurOrder2> list = new ArrayList<>();
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        try {
            list = biz.selectPODueOrNot(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
       String data = gson.toJson(list);
        return data;
	}
	
	@Override
    public Object selectOrderDeliverySummary(HttpServletRequest httpServletRequest) {
        List<ScmPurRequire2> list = new ArrayList<>();
        try {
            list = biz.selectOrderDeliverySummary(httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
		String data = JSON.toJSONString(list);
		return data;
    }

	@Override
    public Object selectDeptApplySummary(HttpServletRequest httpServletRequest) {
        List<ScmPurRequire2> list = new ArrayList<>();
        try {
            list = biz.selectDeptApplySummary(httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
		String data = JSON.toJSONString(list);
		return data;
    }

	@Override
    public Object selectPurPriceInfo(HttpServletRequest httpServletRequest) {
        List<ScmPurPrice2> list = new ArrayList<>();
        Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        try {
            list = biz.selectPurPriceInfo(httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
		String data = gson.toJson(list);
		return data;
    }
    
	@Override
    public Object selectPurPriceInfoCheck(HttpServletRequest httpServletRequest) {
        List<ScmPurPrice2> list = new ArrayList<>();
        Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        try {
            list = biz.selectPurPriceInfoCheck(httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	String data = gson.toJson(list);
		return data;
    }

	@Override
	public Object selectPurHistoryPrice(HttpServletRequest httpServletRequest) {
		List<ScmPurHistoryPrice> list = new ArrayList<>();
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		try {
			list = biz.selectPurHistoryPrice(httpServletRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String data = gson.toJson(list);
		return data;
	}	
	
	@Override
	public Object selectSupplierDetails(HttpServletRequest request){
		List<ScmPurOrderTransInfo> list=new ArrayList<>();
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		try {
			list=biz.selectSupplierDetails(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String data = gson.toJson(list);
		return data;
	}
	
	@Override
	public Object selectSupplierSummary(HttpServletRequest request){
		List<ScmPurOrderTransTotal> list=new ArrayList<>();
		try {
			list=biz.selectSupplierSummary(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String data = JSON.toJSONString(list);
		return data;
	}

	@Override
	public Object selectSupplierOrderSummary(HttpServletRequest httpServletRequest) {
		List<ScmPurRequireEntry2> list = new ArrayList<>();
		try {
			list = biz.selectSupplierOrderSummary(httpServletRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String data = JSON.toJSONString(list);
		return data;
	}	
	@Override
	public Object selectPurchaseReturn(HttpServletRequest request){
		List<ScmPurReturnInfo> list=new ArrayList<ScmPurReturnInfo>();
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		try {
			list=biz.selectPurchaseReturn(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String data = gson.toJson(list);
		return data;
	}

	@Override
	public Object selectScmPurSupplierAppraiseDetails(HttpServletRequest request) {
		List<ScmPurSupplierAppraiseDetails> list=new ArrayList<ScmPurSupplierAppraiseDetails>();
		try {
			list=biz.selectScmPurSupplierAppraiseDetails(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String data = JSON.toJSONString(list);
		return data;
	}

	@Override
	public Object selectScmPurDelivery(HttpServletRequest request) {
		List<ScmPurDelivery> list = new ArrayList<ScmPurDelivery>();
		try {
			list=biz.selectScmPurDelivery(request);
		} catch (Exception e) {
			e.printStackTrace();
	}
		String data = JSON.toJSONString(list);
		return data;
	}

}

