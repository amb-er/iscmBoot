
package com.armitage.server.iscm.purchasemanage.pricemanage.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPriceWSBean;

public interface ScmMaterialPriceService {
	
	@POST
	@Path("/getMaterialPrice")
	public ScmMaterialPriceWSBean getMaterialPrice(ScmMaterialPriceWSBean bean);

	@POST
	@Path("/getMaterialPriceAll")
	public ScmMaterialPriceWSBean getMaterialPriceAll(ScmMaterialPriceWSBean bean);

	@POST
	@Path("/getLastQuotationPrice")
	public ScmMaterialPriceWSBean getLastQuotationPrice(ScmMaterialPriceWSBean bean);

	@POST
	@Path("/getMaterialSalePrice")
	public ScmMaterialPriceWSBean getMaterialSalePrice(ScmMaterialPriceWSBean bean);

	@POST
	@Path("/getMaterialSupplyInfo")
	public ScmMaterialPriceWSBean getMaterialSupplyInfo(ScmMaterialPriceWSBean bean);
	
	@POST
	@Path("/getMaterialSupplyInfos")
	public ScmMaterialPriceWSBean getMaterialSupplyInfos(ScmMaterialPriceWSBean bean);
	
	@POST
	@Path("/getMaterialPriceAllList")
	public ScmMaterialPriceWSBean getMaterialPriceAllList(ScmMaterialPriceWSBean bean);
	
	@POST
	@Path("/getMaterialPreParePrice")
	public ScmMaterialPriceWSBean getMaterialPreParePrice(ScmMaterialPriceWSBean bean);
	
	@POST
	@Path("/getMaterialSupplyInfoList")
	public ScmMaterialPriceWSBean getMaterialSupplyInfoList(ScmMaterialPriceWSBean bean);
	
	@POST
	@Path("/getMaterialPriceByItemidsAndVendorIdsList")
	public ScmMaterialPriceWSBean getMaterialPriceByItemidsAndVendorIdsList(ScmMaterialPriceWSBean bean);

	@POST
	@Path("/getRecentPriceAndStock")
	public ScmMaterialPriceWSBean getRecentPriceAndStock(ScmMaterialPriceWSBean bean);
	
	@POST
	@Path("/selectCostPrice")
	public ScmMaterialPriceWSBean selectCostPrice(ScmMaterialPriceWSBean bean);
	
	@POST
	@Path("/getRecentPriceAndStockList")
	public ScmMaterialPriceWSBean getRecentPriceAndStockList(ScmMaterialPriceWSBean bean);
}
