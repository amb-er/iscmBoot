package com.armitage.server.iscm.common.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iaps.daily.model.ApPaymentBill2;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBill;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLoss2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBill2;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturns2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSource2;

@XmlRootElement(name = "commonEventHistoryWSBean")
public class CommonEventHistoryWSBean extends BaseWSBean{
	private CommonEventHistory x;
	private CommonEventHistory2 y;
	private ScmInvMoveBill2 scmInvMoveBill;
	private ScmInvSaleOrder2 scmInvSaleOrder;
	private ScmInvPurInWarehsBill2 scmInvPurInWarehsBill;
	private ScmInvMaterialReqBill2 scmInvMaterialReqBill;
	private ScmInvMaterialRequestBill2 scmInvMaterialRequestBill;
	private ScmInvOtherIssueBill2 scmInvOtherIssueBill;
	private ScmPurPrice2 scmPurPrice;
	private ScmPurQuotation2 scmPurQuotation;
	private ScmPurOrder2 scmPurOrder;
	private ScmPurReceive2 scmPurReceive;
	private ScmPurRequire2 scmPurRequire;
	private ScmPurReturns2 scmPurReturns;
	private ScmCstFrmLoss2 scmCstFrmLoss;
	private ApPaymentBill2 apPaymentBill;
	private ScmSupplierQualifieInfoBill scmSupplierQualifieInfoBill;
	private ScmPurSupplierSource2 scmPurSupplierSource;
	private ScmInvSaleIssueBill2 scmInvSaleIssueBill2;
	
	public CommonEventHistory getX() {
		return x;
	}
	public void setX(CommonEventHistory x) {
		this.x = x;
	}
	public CommonEventHistory2 getY() {
		return y;
	}
	public void setY(CommonEventHistory2 y) {
		this.y = y;
	}
	public ScmInvMoveBill2 getScmInvMoveBill() {
		return scmInvMoveBill;
	}
	public void setScmInvMoveBill(ScmInvMoveBill2 scmInvMoveBill) {
		this.scmInvMoveBill = scmInvMoveBill;
	}
	public ScmInvSaleOrder2 getScmInvSaleOrder() {
		return scmInvSaleOrder;
	}
	public void setScmInvSaleOrder(ScmInvSaleOrder2 scmInvSaleOrder) {
		this.scmInvSaleOrder = scmInvSaleOrder;
	}
	public ScmInvPurInWarehsBill2 getScmInvPurInWarehsBill() {
		return scmInvPurInWarehsBill;
	}
	public void setScmInvPurInWarehsBill(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill) {
		this.scmInvPurInWarehsBill = scmInvPurInWarehsBill;
	}
	public ScmInvMaterialReqBill2 getScmInvMaterialReqBill() {
		return scmInvMaterialReqBill;
	}
	public void setScmInvMaterialReqBill(ScmInvMaterialReqBill2 scmInvMaterialReqBill) {
		this.scmInvMaterialReqBill = scmInvMaterialReqBill;
	}
	public ScmInvMaterialRequestBill2 getScmInvMaterialRequestBill() {
		return scmInvMaterialRequestBill;
	}
	public void setScmInvMaterialRequestBill(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill) {
		this.scmInvMaterialRequestBill = scmInvMaterialRequestBill;
	}
	public ScmInvOtherIssueBill2 getScmInvOtherIssueBill() {
		return scmInvOtherIssueBill;
	}
	public void setScmInvOtherIssueBill(ScmInvOtherIssueBill2 scmInvOtherIssueBill) {
		this.scmInvOtherIssueBill = scmInvOtherIssueBill;
	}
	public ScmPurPrice2 getScmPurPrice() {
		return scmPurPrice;
	}
	public void setScmPurPrice(ScmPurPrice2 scmPurPrice) {
		this.scmPurPrice = scmPurPrice;
	}
	public ScmPurQuotation2 getScmPurQuotation() {
		return scmPurQuotation;
	}
	public void setScmPurQuotation(ScmPurQuotation2 scmPurQuotation) {
		this.scmPurQuotation = scmPurQuotation;
	}
	public ScmPurOrder2 getScmPurOrder() {
		return scmPurOrder;
	}
	public void setScmPurOrder(ScmPurOrder2 scmPurOrder) {
		this.scmPurOrder = scmPurOrder;
	}
	public ScmPurReceive2 getScmPurReceive() {
		return scmPurReceive;
	}
	public void setScmPurReceive(ScmPurReceive2 scmPurReceive) {
		this.scmPurReceive = scmPurReceive;
	}
	public ScmPurRequire2 getScmPurRequire() {
		return scmPurRequire;
	}
	public void setScmPurRequire(ScmPurRequire2 scmPurRequire) {
		this.scmPurRequire = scmPurRequire;
	}
	public ScmPurReturns2 getScmPurReturns() {
		return scmPurReturns;
	}
	public void setScmPurReturns(ScmPurReturns2 scmPurReturns) {
		this.scmPurReturns = scmPurReturns;
	}
	public ScmCstFrmLoss2 getScmCstFrmLoss() {
		return scmCstFrmLoss;
	}
	public void setScmCstFrmLoss(ScmCstFrmLoss2 scmCstFrmLoss) {
		this.scmCstFrmLoss = scmCstFrmLoss;
	}
	public ApPaymentBill2 getApPaymentBill() {
		return apPaymentBill;
	}
	public void setApPaymentBill(ApPaymentBill2 apPaymentBill) {
		this.apPaymentBill = apPaymentBill;
	}
	public ScmSupplierQualifieInfoBill getScmSupplierQualifieInfoBill() {
		return scmSupplierQualifieInfoBill;
	}
	public void setScmSupplierQualifieInfoBill(ScmSupplierQualifieInfoBill scmSupplierQualifieInfoBill) {
		this.scmSupplierQualifieInfoBill = scmSupplierQualifieInfoBill;
	}
	public ScmPurSupplierSource2 getScmPurSupplierSource() {
		return scmPurSupplierSource;
	}
	public void setScmPurSupplierSource(ScmPurSupplierSource2 scmPurSupplierSource) {
		this.scmPurSupplierSource = scmPurSupplierSource;
	}
	public ScmInvSaleIssueBill2 getScmInvSaleIssueBill2() {
		return scmInvSaleIssueBill2;
	}
	public void setScmInvSaleIssueBill2(ScmInvSaleIssueBill2 scmInvSaleIssueBill2) {
		this.scmInvSaleIssueBill2 = scmInvSaleIssueBill2;
	}
	
}
