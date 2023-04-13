package com.armitage.server.iscm.common.model;


public class TaskSetting2 extends TaskSetting {
    public static final String FN_TASKCODE ="taskCode";
    public static final String FN_TASKNAME ="taskName";
    public static final String FN_CHANNEL ="channel";
    public static final String FN_TASKCREATEMODE ="taskCreateMode";
    public static final String FN_CONFIRMRULECREATEMODE ="confirmRuleCreateMode";
    public static final String FN_SUPPLIERCREATEMODE ="supplierCreateMode";
    public static final String FN_DATASCOPE2 ="dataScope2";
    public static final String FN_CONTROLUNITCREATEMODE ="controlUnitCreateMode";
    public static final String FN_CONTROLUNITDATASCOPE ="controlUnitDataScope";

    private String taskCode;
	private String taskName;
    private String channel;
    private String taskCreateMode;
	private boolean purOrderFlag;	//采购订单
	private String purOrderStatus;
	private boolean invPurInWarehsFlag;	//采购入库
	private String invPurInWarehsStatus;
	private boolean confirmInfoFlag;	//确认信息
	private boolean confirmRuleFlag;	//供应商确认规则
	private String confirmRuleCreateMode;	//供应商确认明细规则
	private boolean supplerRegInfoFlag;		//商户注册信息
	private boolean purPriceFlag;	//定价单
	private String purPriceStatus;
	private boolean businessQuotationFlag;	//商户报价信息
	private String supplierCreateMode; //供应商明细任务
	private String dataScope2; //供应商推送范围
	private int lotQty2; //供应商明细任务批次数量
	private boolean industryQualifyPushFlag;	//供应商行业资质分类关系推送
	private boolean supplierStatusPushFlag;	//供应商状态与资质状态推送
	private boolean qualificationInfoPushFlag;	//供应商资质信息推送
	private boolean supplierInfoPullFlag;	//供应商主表信息拉取
	private boolean qualificationInfoPullFlag;	//供应商资质信息拉取
	private boolean controlUnitPushFlag;	//管理单元信息推送
	private String controlUnitCreateMode; //管理单元明细任务
	private String  controlUnitDataScope; //管理单元推送范围
	private int controlCycleTime;//管理单元任务循环时间
	private boolean updateFbcItemPriceFlag;	//更新物资成本价格
	private boolean getFbmSalePriceFlag; //获取餐饮标准售价
	private boolean updateFbcCostPriceFlag;	//更新标准成本
	private boolean generateCostConsumeFlag; //自动生成耗用单
	private boolean calcFbcRptDataFlag; //报表数据汇集
	
	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
    public String getTaskCreateMode() {
        return taskCreateMode;
    }

    public void setTaskCreateMode(String val) {
        this.taskCreateMode = val;
    }

	public boolean isPurOrderFlag() {
		return purOrderFlag;
	}

	public void setPurOrderFlag(boolean purOrderFlag) {
		this.purOrderFlag = purOrderFlag;
	}

	public String getPurOrderStatus() {
		return purOrderStatus;
	}

	public void setPurOrderStatus(String purOrderStatus) {
		this.purOrderStatus = purOrderStatus;
	}

	public boolean isInvPurInWarehsFlag() {
		return invPurInWarehsFlag;
	}

	public void setInvPurInWarehsFlag(boolean invPurInWarehsFlag) {
		this.invPurInWarehsFlag = invPurInWarehsFlag;
	}

	public String getInvPurInWarehsStatus() {
		return invPurInWarehsStatus;
	}

	public void setInvPurInWarehsStatus(String invPurInWarehsStatus) {
		this.invPurInWarehsStatus = invPurInWarehsStatus;
	}

	public boolean isConfirmInfoFlag() {
		return confirmInfoFlag;
	}

	public void setConfirmInfoFlag(boolean confirmInfoFlag) {
		this.confirmInfoFlag = confirmInfoFlag;
	}

	public boolean isConfirmRuleFlag() {
		return confirmRuleFlag;
	}

	public void setConfirmRuleFlag(boolean confirmRuleFlag) {
		this.confirmRuleFlag = confirmRuleFlag;
	}

	public String getConfirmRuleCreateMode() {
		return confirmRuleCreateMode;
	}

	public void setConfirmRuleCreateMode(String confirmRuleCreateMode) {
		this.confirmRuleCreateMode = confirmRuleCreateMode;
	}

	public boolean isSupplerRegInfoFlag() {
		return supplerRegInfoFlag;
	}

	public void setSupplerRegInfoFlag(boolean supplerRegInfoFlag) {
		this.supplerRegInfoFlag = supplerRegInfoFlag;
	}

	public boolean isPurPriceFlag() {
		return purPriceFlag;
	}

	public void setPurPriceFlag(boolean purPriceFlag) {
		this.purPriceFlag = purPriceFlag;
	}

	public String getPurPriceStatus() {
		return purPriceStatus;
	}

	public void setPurPriceStatus(String purPriceStatus) {
		this.purPriceStatus = purPriceStatus;
	}

	public boolean isBusinessQuotationFlag() {
		return businessQuotationFlag;
	}

	public void setBusinessQuotationFlag(boolean businessQuotationFlag) {
		this.businessQuotationFlag = businessQuotationFlag;
	}

	public String getSupplierCreateMode() {
		return supplierCreateMode;
	}

	public void setSupplierCreateMode(String supplierCreateMode) {
		this.supplierCreateMode = supplierCreateMode;
	}

	public String getDataScope2() {
		return dataScope2;
	}

	public void setDataScope2(String dataScope2) {
		this.dataScope2 = dataScope2;
	}

	public int getLotQty2() {
		return lotQty2;
	}

	public void setLotQty2(int lotQty2) {
		this.lotQty2 = lotQty2;
	}

	public boolean isIndustryQualifyPushFlag() {
		return industryQualifyPushFlag;
	}

	public void setIndustryQualifyPushFlag(boolean industryQualifyPushFlag) {
		this.industryQualifyPushFlag = industryQualifyPushFlag;
	}

	public boolean isSupplierStatusPushFlag() {
		return supplierStatusPushFlag;
	}

	public void setSupplierStatusPushFlag(boolean supplierStatusPushFlag) {
		this.supplierStatusPushFlag = supplierStatusPushFlag;
	}

	public boolean isQualificationInfoPushFlag() {
		return qualificationInfoPushFlag;
	}

	public void setQualificationInfoPushFlag(boolean qualificationInfoPushFlag) {
		this.qualificationInfoPushFlag = qualificationInfoPushFlag;
	}

	public boolean isSupplierInfoPullFlag() {
		return supplierInfoPullFlag;
	}

	public void setSupplierInfoPullFlag(boolean supplierInfoPullFlag) {
		this.supplierInfoPullFlag = supplierInfoPullFlag;
	}

	public boolean isQualificationInfoPullFlag() {
		return qualificationInfoPullFlag;
	}

	public void setQualificationInfoPullFlag(boolean qualificationInfoPullFlag) {
		this.qualificationInfoPullFlag = qualificationInfoPullFlag;
	}

	public boolean isUpdateFbcItemPriceFlag() {
		return updateFbcItemPriceFlag;
	}

	public void setUpdateFbcItemPriceFlag(boolean updateFbcItemPriceFlag) {
		this.updateFbcItemPriceFlag = updateFbcItemPriceFlag;
	}

	public boolean isGetFbmSalePriceFlag() {
		return getFbmSalePriceFlag;
	}

	public void setGetFbmSalePriceFlag(boolean getFbmSalePriceFlag) {
		this.getFbmSalePriceFlag = getFbmSalePriceFlag;
	}

	public boolean isUpdateFbcCostPriceFlag() {
		return updateFbcCostPriceFlag;
	}

	public void setUpdateFbcCostPriceFlag(boolean updateFbcCostPriceFlag) {
		this.updateFbcCostPriceFlag = updateFbcCostPriceFlag;
	}

	public boolean isGenerateCostConsumeFlag() {
		return generateCostConsumeFlag;
	}

	public void setGenerateCostConsumeFlag(boolean generateCostConsumeFlag) {
		this.generateCostConsumeFlag = generateCostConsumeFlag;
	}

	public boolean isControlUnitPushFlag() {
		return controlUnitPushFlag;
	}

	public void setControlUnitPushFlag(boolean controlUnitPushFlag) {
		this.controlUnitPushFlag = controlUnitPushFlag;
	}

	public String getControlUnitCreateMode() {
		return controlUnitCreateMode;
	}

	public void setControlUnitCreateMode(String controlUnitCreateMode) {
		this.controlUnitCreateMode = controlUnitCreateMode;
	}

	public String getControlUnitDataScope() {
		return controlUnitDataScope;
	}

	public void setControlUnitDataScope(String controlUnitDataScope) {
		this.controlUnitDataScope = controlUnitDataScope;
	}

	public int getControlCycleTime() {
		return controlCycleTime;
	}

	public void setControlCycleTime(int controlCycleTime) {
		this.controlCycleTime = controlCycleTime;
	}

	public boolean isCalcFbcRptDataFlag() {
		return calcFbcRptDataFlag;
	}

	public void setCalcFbcRptDataFlag(boolean calcFbcRptDataFlag) {
		this.calcFbcRptDataFlag = calcFbcRptDataFlag;
	}

	public TaskSetting2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue) {
			this.purOrderFlag=true;
			this.invPurInWarehsFlag=true;
			this.confirmInfoFlag=true;
			this.confirmRuleFlag=true;
			//this.channel="ESP";
			this.taskCreateMode="1";
			this.confirmRuleCreateMode="1";
			this.supplerRegInfoFlag=true;
			this.purPriceFlag=true;
			this.businessQuotationFlag=true;
			this.supplierCreateMode="1";
			this.supplierStatusPushFlag=true;
			this.qualificationInfoPushFlag=true;
			this.supplierInfoPullFlag=true;
			this.qualificationInfoPullFlag=true;
			this.industryQualifyPushFlag=true;
			this.dataScope2="1";
			this.updateFbcItemPriceFlag=true;
			this.getFbmSalePriceFlag=true;
			this.updateFbcCostPriceFlag=true;
			this.generateCostConsumeFlag=true;
			this.controlCycleTime=600;
			this.controlUnitDataScope="1";
			this.controlUnitCreateMode="1";
			this.controlUnitPushFlag=true;
			this.calcFbcRptDataFlag=true;
			this.lotQty2=10;
		}
	}
	
	public TaskSetting2(){
		super();
	}
}
