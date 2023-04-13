package com.armitage.server.ifbc.report.model;

import java.math.BigDecimal;

/**
 * 菜品销售结构分析
 * @author luo
 *
 */
public class ScmDishSaleStructureAnalysis {

	/**
	 * 菜品类型
	 * 1：主打菜品：毛利高销量高 
	 * 2：盈利菜品：毛利高销量一般
	 * 3：引流菜品：毛利低销量高或销量一般
	 * 4：辅助菜品：毛利高销售低
	 * 5：需调整菜品：毛利低销售低
	 */
	private String dishType;
	private String dishId;
	private String dishCode;
	private String dishName;
	private String dishSpecId;
	private String dishSpecName;
	private String deptId;
	private String deptName;
	
	private BigDecimal realSaleAmt;
	private BigDecimal saleQty;
	private BigDecimal stdAmt;
	private String profit;
	
	
	

	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
	public BigDecimal getRealSaleAmt() {
		return realSaleAmt;
	}
	public void setRealSaleAmt(BigDecimal realSaleAmt) {
		this.realSaleAmt = realSaleAmt;
	}
	public String getDishType() {
		return dishType;
	}
	public void setDishType(String dishType) {
		this.dishType = dishType;
	}
	public String getDishId() {
		return dishId;
	}
	public void setDishId(String dishId) {
		this.dishId = dishId;
	}
	public String getDishCode() {
		return dishCode;
	}
	public void setDishCode(String dishCode) {
		this.dishCode = dishCode;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public String getDishSpecId() {
		return dishSpecId;
	}
	public void setDishSpecId(String dishSpecId) {
		this.dishSpecId = dishSpecId;
	}
	public String getDishSpecName() {
		return dishSpecName;
	}
	public void setDishSpecName(String dishSpecName) {
		this.dishSpecName = dishSpecName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public BigDecimal getSaleQty() {
		return saleQty;
	}
	public void setSaleQty(BigDecimal saleQty) {
		this.saleQty = saleQty;
	}
	public BigDecimal getStdAmt() {
		return stdAmt;
	}
	public void setStdAmt(BigDecimal stdAmt) {
		this.stdAmt = stdAmt;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
