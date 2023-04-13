package com.armitage.server.iscm.common.model;

import com.armitage.server.iscm.common.model.ScmDataCollectionStepState;

/**
 * 数据异步处理
 * @author Fengxq
 *
 */
public class ScmDataCollectionStepState2 extends ScmDataCollectionStepState {
	public static final String FN_NAME = "name";

	private String name;
	private String invoke;
	private boolean executed;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInvoke() {
		return invoke;
	}

	public void setInvoke(String invoke) {
		this.invoke = invoke;
	}

	public boolean isExecuted() {
		return executed;
	}

	public void setExecuted(boolean executed) {
		this.executed = executed;
	}

	public ScmDataCollectionStepState2() {
		super();
	}

	public ScmDataCollectionStepState2(boolean defaultValue) {
		super();
 	}

}
