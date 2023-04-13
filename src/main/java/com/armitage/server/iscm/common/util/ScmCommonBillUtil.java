package com.armitage.server.iscm.common.util;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.iaps.daily.model.ApPaymentBill2;
import com.armitage.server.iaps.daily.service.ApPaymentBillBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBill2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvMoveBillBiz;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder2;
import com.armitage.server.iscm.inventorymanage.internaltrans.service.ScmInvSaleOrderBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialReqBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialRequestBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvOtherIssueBillBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation2;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPriceBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurQuotationBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturns2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReceiveBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReturnsBiz;

public class ScmCommonBillUtil {
	private ScmPurQuotationBiz scmPurQuotationBiz = (ScmPurQuotationBiz) AppContextUtil.getBean("scmPurQuotationBiz");
	private ScmPurPriceBiz scmPurPriceBiz = (ScmPurPriceBiz) AppContextUtil.getBean("scmPurPriceBiz");
	private ScmPurRequireBiz scmPurRequireBiz = (ScmPurRequireBiz) AppContextUtil.getBean("scmPurRequireBiz");
	private ScmPurOrderBiz scmPurOrderBiz = (ScmPurOrderBiz) AppContextUtil.getBean("scmPurOrderBiz");
	private ScmPurReceiveBiz scmPurReceiveBiz = (ScmPurReceiveBiz) AppContextUtil.getBean("scmPurReceiveBiz");
	private ScmPurReturnsBiz scmPurReturnsBiz = (ScmPurReturnsBiz) AppContextUtil.getBean("scmPurReturnsBiz");
	private ScmInvMaterialRequestBillBiz scmInvMaterialRequestBillBiz = (ScmInvMaterialRequestBillBiz) AppContextUtil.getBean("scmInvMaterialRequestBillBiz");
	private ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz = (ScmInvPurInWarehsBillBiz) AppContextUtil.getBean("scmInvPurInWarehsBillBiz");
	private ScmInvMaterialReqBillBiz scmInvMaterialReqBillBiz = (ScmInvMaterialReqBillBiz) AppContextUtil.getBean("scmInvMaterialReqBillBiz");
	private ScmInvSaleOrderBiz scmInvSaleOrderBiz = (ScmInvSaleOrderBiz) AppContextUtil.getBean("scmInvSaleOrderBiz");
	private ScmInvMoveBillBiz scmInvMoveBillBiz = (ScmInvMoveBillBiz) AppContextUtil.getBean("scmInvMoveBillBiz");
	private ScmInvOtherIssueBillBiz scmInvOtherIssueBillBiz = (ScmInvOtherIssueBillBiz) AppContextUtil.getBean("scmInvOtherIssueBillBiz");
	private ApPaymentBillBiz apPaymentBillBiz = (ApPaymentBillBiz) AppContextUtil.getBean("apPaymentBillBiz");
	
	public long getBillId(String billTypeCode, String billCode, Param param){
		long billId = 0;
		HashMap<String,Object> map = new HashMap<String,Object>();
		switch (StringUtils.lowerCase(billTypeCode)) {
		case "purquotation": {
			// 报价单
			map.put(ScmPurQuotation2.FN_PQNO,new QueryParam(ScmPurQuotation2.FN_PQNO, QueryParam.QUERY_EQ,billCode));
			map.put(ScmPurQuotation2.FN_CONTROLUNITNO, new QueryParam(ScmPurQuotation2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmPurQuotation2> scmPurQuotationList =scmPurQuotationBiz.findAll(map, param);
			if(scmPurQuotationList!=null && !scmPurQuotationList.isEmpty()){
				billId = scmPurQuotationList.get(0).getId();
			}
			break;
		}
		case "scmpurprice": {
			// 定价单
			map.put(ScmPurPrice2.FN_PMNO,new QueryParam(ScmPurPrice2.FN_PMNO, QueryParam.QUERY_EQ,billCode));
			map.put(ScmPurPrice2.FN_CONTROLUNITNO, new QueryParam(ScmPurPrice2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmPurPrice2> scmPurPriceList =scmPurPriceBiz.findAll(map, param);
			if(scmPurPriceList!=null && !scmPurPriceList.isEmpty()){
				billId = scmPurPriceList.get(0).getId();
			}
			break;
		}
		case "purrequire": {
			// 请购单
			map.put(ScmPurRequire2.FN_PRNO,new QueryParam(ScmPurRequire2.FN_PRNO, QueryParam.QUERY_EQ,billCode));
			map.put(ScmPurRequire2.FN_CONTROLUNITNO, new QueryParam(ScmPurRequire2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmPurRequire2> scmPurRequireList =scmPurRequireBiz.findAll(map, param);
			if(scmPurRequireList!=null && !scmPurRequireList.isEmpty()){
				billId = scmPurRequireList.get(0).getId();
			}
			break;
		}
		case "purorder": {
			// 订货单
			map.put(ScmPurOrder2.FN_PONO,new QueryParam(ScmPurOrder2.FN_PONO, QueryParam.QUERY_EQ,billCode));
			map.put(ScmPurOrder2.FN_CONTROLUNITNO, new QueryParam(ScmPurOrder2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmPurOrder2> scmPurOrderList =scmPurOrderBiz.findAll(map, param);
			if(scmPurOrderList!=null && !scmPurOrderList.isEmpty()){
				billId = scmPurOrderList.get(0).getId();
			}
			break;
		}
		case "purreceive": {
			// 收货单
			map.put(ScmPurReceive2.FN_PVNO,new QueryParam(ScmPurReceive2.FN_PVNO, QueryParam.QUERY_EQ,billCode));
			map.put(ScmPurReceive2.FN_CONTROLUNITNO, new QueryParam(ScmPurReceive2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmPurReceive2> scmPurReceiveList =scmPurReceiveBiz.findAll(map, param);
			if(scmPurReceiveList!=null && !scmPurReceiveList.isEmpty()){
				billId = scmPurReceiveList.get(0).getId();
			}
			break;
		}
		case "purreturns": {
			// 退货申请
			map.put(ScmPurReturns2.FN_RTNO,new QueryParam(ScmPurReturns2.FN_RTNO, QueryParam.QUERY_EQ,billCode));
			map.put(ScmPurReturns2.FN_CONTROLUNITNO, new QueryParam(ScmPurReturns2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmPurReturns2> scmPurReturnsList =scmPurReturnsBiz.findAll(map, param);
			if(scmPurReturnsList!=null && !scmPurReturnsList.isEmpty()){
				billId = scmPurReturnsList.get(0).getId();
			}
			break;
		}
		case "invmatreqbill": {
			// 领料申请
			map.put(ScmInvMaterialRequestBill2.FN_REQNO,new QueryParam(ScmInvMaterialRequestBill2.FN_REQNO, QueryParam.QUERY_EQ,billCode));
			map.put(ScmInvMaterialRequestBill2.FN_CONTROLUNITNO, new QueryParam(ScmInvMaterialRequestBill2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmInvMaterialRequestBill2> scmInvMaterialRequestBillList =scmInvMaterialRequestBillBiz.findAll(map, param);
			if(scmInvMaterialRequestBillList!=null && !scmInvMaterialRequestBillList.isEmpty()){
				billId = scmInvMaterialRequestBillList.get(0).getReqId();
			}
			break;
		}
		case "invpurinwhsbill": {
			// 采购入库
			map.put(ScmInvPurInWarehsBill2.FN_WRNO,new QueryParam(ScmInvPurInWarehsBill2.FN_WRNO, QueryParam.QUERY_EQ,billCode));
			map.put(ScmInvPurInWarehsBill2.FN_CONTROLUNITNO, new QueryParam(ScmInvPurInWarehsBill2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmInvPurInWarehsBill2> scmInvPurInWarehsBillList =scmInvPurInWarehsBillBiz.findAll(map, param);
			if(scmInvPurInWarehsBillList!=null && !scmInvPurInWarehsBillList.isEmpty()){
				billId = scmInvPurInWarehsBillList.get(0).getWrId();
			}
			break;
		}
		case "invmatreqout": {
			// 领料出库
			map.put(ScmInvMaterialReqBill2.FN_OTNO,new QueryParam(ScmInvMaterialReqBill2.FN_OTNO, QueryParam.QUERY_EQ,billCode));
			map.put(ScmInvMaterialReqBill2.FN_CONTROLUNITNO, new QueryParam(ScmInvMaterialReqBill2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmInvMaterialReqBill2> scmInvMaterialReqBillList =scmInvMaterialReqBillBiz.findAll(map, param);
			if(scmInvMaterialReqBillList!=null && !scmInvMaterialReqBillList.isEmpty()){
				billId = scmInvMaterialReqBillList.get(0).getOtId();
			}
			break;
		}
		case "invsaleorder": {
			// 销售订单
			map.put(ScmInvSaleOrder2.FN_OTNO,new QueryParam(ScmInvSaleOrder2.FN_OTNO, QueryParam.QUERY_EQ,billCode));
			map.put(ScmInvSaleOrder2.FN_CONTROLUNITNO, new QueryParam(ScmInvSaleOrder2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmInvSaleOrder2> scmInvSaleOrderList =scmInvSaleOrderBiz.findAll(map, param);
			if(scmInvSaleOrderList!=null && !scmInvSaleOrderList.isEmpty()){
				billId = scmInvSaleOrderList.get(0).getOtId();
			}
			break;
		}
		case "invmovebill": {
			// 成本转移
			map.put(ScmInvMoveBill2.FN_WTNO,new QueryParam(ScmInvMoveBill2.FN_WTNO, QueryParam.QUERY_EQ,billCode));
			map.put(ScmInvMoveBill2.FN_CONTROLUNITNO, new QueryParam(ScmInvMoveBill2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmInvMoveBill2> scmInvMoveBillList =scmInvMoveBillBiz.findAll(map, param);
			if(scmInvMoveBillList!=null && !scmInvMoveBillList.isEmpty()){
				billId = scmInvMoveBillList.get(0).getWtId();
			}
			break;
		}
		case "invothissuebill": {
			// 其他出库
			map.put(ScmInvOtherIssueBill2.FN_OTNO,new QueryParam(ScmInvOtherIssueBill2.FN_OTNO, QueryParam.QUERY_EQ,billCode));
			map.put(ScmInvOtherIssueBill2.FN_CONTROLUNITNO, new QueryParam(ScmInvOtherIssueBill2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ScmInvOtherIssueBill2> scmInvOtherIssueBillList =scmInvOtherIssueBillBiz.findAll(map, param);
			if(scmInvOtherIssueBillList!=null && !scmInvOtherIssueBillList.isEmpty()){
				billId = scmInvOtherIssueBillList.get(0).getOtId();
			}
			break;
		}
		case "appaymentbill":{
			map.put(ApPaymentBill2.FN_BILLNO,new QueryParam(ApPaymentBill2.FN_BILLNO, QueryParam.QUERY_EQ,billCode));
			map.put(ApPaymentBill2.FN_CONTROLUNITNO, new QueryParam(ApPaymentBill2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
			List<ApPaymentBill2> apPaymentBillList =apPaymentBillBiz.findAll(map, param);
			if(apPaymentBillList!=null && !apPaymentBillList.isEmpty()){
				billId = apPaymentBillList.get(0).getBillId();
			}
			break;
		}
	}
		return billId;
	}
}
