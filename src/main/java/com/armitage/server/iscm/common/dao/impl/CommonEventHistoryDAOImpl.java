package com.armitage.server.iscm.common.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.common.util.DAOHelper;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iscm.common.dao.CommonEventHistoryDAO;
import com.armitage.server.iscm.common.model.CommonEventHistory;
import com.armitage.server.iscm.common.model.CommonEventHistory2;

public class CommonEventHistoryDAOImpl extends BasicDAOImpl<CommonEventHistory> implements
		CommonEventHistoryDAO {
	
	protected SqlSessionTemplate sqlSession1;
	protected SqlSessionTemplate sqlSession2;
	protected SqlSessionTemplate sqlSession3;
	protected SqlSessionTemplate sqlSession4;
	protected SqlSessionTemplate sqlSession5;
	protected SqlSessionTemplate sqlSession6;
	protected SqlSessionTemplate sqlSession7;
	protected SqlSessionTemplate sqlSession8;
	protected SqlSessionTemplate sqlSession9;
	private static Log log = LogFactory.getLog(CommonEventHistoryDAOImpl.class);
	private String tableSuffix = "EventHistory";

	public CommonEventHistoryDAOImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class) pt.getActualTypeArguments()[0];
		simpleName = clazz.getSimpleName().toLowerCase();
	}

	public SqlSessionTemplate getSqlSession1() {
		return sqlSession1;
	}

	public void setSqlSession1(SqlSessionTemplate sqlSession1) {
		this.sqlSession1 = sqlSession1;
	}

	public SqlSessionTemplate getSqlSession2() {
		return sqlSession2;
	}

	public void setSqlSession2(SqlSessionTemplate sqlSession2) {
		this.sqlSession2 = sqlSession2;
	}

	public SqlSessionTemplate getSqlSession3() {
		return sqlSession3;
	}

	public void setSqlSession3(SqlSessionTemplate sqlSession3) {
		this.sqlSession3 = sqlSession3;
	}

	public SqlSessionTemplate getSqlSession4() {
		return sqlSession4;
	}

	public void setSqlSession4(SqlSessionTemplate sqlSession4) {
		this.sqlSession4 = sqlSession4;
	}

	public SqlSessionTemplate getSqlSession5() {
		return sqlSession5;
	}

	public void setSqlSession5(SqlSessionTemplate sqlSession5) {
		this.sqlSession5 = sqlSession5;
	}

	public SqlSessionTemplate getSqlSession6() {
		return sqlSession6;
	}

	public void setSqlSession6(SqlSessionTemplate sqlSession6) {
		this.sqlSession6 = sqlSession6;
	}

	public SqlSessionTemplate getSqlSession7() {
		return sqlSession7;
	}

	public void setSqlSession7(SqlSessionTemplate sqlSession7) {
		this.sqlSession7 = sqlSession7;
	}

	public SqlSessionTemplate getSqlSession8() {
		return sqlSession8;
	}

	public void setSqlSession8(SqlSessionTemplate sqlSession8) {
		this.sqlSession8 = sqlSession8;
	}

	public SqlSessionTemplate getSqlSession9() {
		return sqlSession9;
	}

	public void setSqlSession9(SqlSessionTemplate sqlSession9) {
		this.sqlSession9 = sqlSession9;
	}

	public String getTableSuffix() {
		return tableSuffix;
	}

	public void setTableSuffix(String tableSuffix) {
		this.tableSuffix = tableSuffix;
	}

	private SqlSessionTemplate getSqlSession(String table) {
		String source = DAOHelper.getSource(table);
		if (sqlSession != null && StringUtils.equals(source, sqlSession.getId())) {
			return sqlSession;
		} else if (sqlSession1 != null && StringUtils.equals(source, sqlSession1.getId())) {
			return sqlSession1;
		} else if (sqlSession2 != null && StringUtils.equals(source, sqlSession2.getId())) {
			return sqlSession2;
		} else if (sqlSession3 != null && StringUtils.equals(source, sqlSession3.getId())) {
			return sqlSession3;
		} else if (sqlSession4 != null && StringUtils.equals(source, sqlSession4.getId())) {
			return sqlSession4;
		} else if (sqlSession5 != null && StringUtils.equals(source, sqlSession5.getId())) {
			return sqlSession5;
		} else if (sqlSession6 != null && StringUtils.equals(source, sqlSession6.getId())) {
			return sqlSession6;
		} else if (sqlSession7 != null && StringUtils.equals(source, sqlSession7.getId())) {
			return sqlSession7;
		} else if (sqlSession8 != null && StringUtils.equals(source, sqlSession8.getId())) {
			return sqlSession8;
		} else if (sqlSession9 != null && StringUtils.equals(source, sqlSession9.getId())) {
			return sqlSession9;
		} 
		return null;
	}
	
	@Override
	public int saveEventHistory(String tableName,CommonEventHistory commonEventHistory)
			throws DAOException {
		SqlSessionTemplate _sqlSession = this.getSqlSession(tableName);
		if (_sqlSession == null)
			throw new DAOException("can't find SqlSessionTemplate!");
		if (commonEventHistory == null || StringUtils.isBlank(commonEventHistory.getActiveType())) {
			return 0;
		}
		
		String temp = "INSERT INTO  %s (billId,stepNo,preStepNo,activeType,opinion,handlerContent,remarks,oper,operDate,flag)	"
				+ "	VALUES ('%d','%s','%s','%s','%s','%s','%s','%s','%s','%d')";
		String sql = String.format(temp, tableName + tableSuffix,
						commonEventHistory.getBillId(),StringUtils.isBlank(commonEventHistory.getStepNo())?"":commonEventHistory.getStepNo(),
						StringUtils.isBlank(commonEventHistory.getPreStepNo())?"":commonEventHistory.getPreStepNo(), commonEventHistory.getActiveType(),
						StringUtils.isBlank(commonEventHistory.getOpinion())?"":commonEventHistory.getOpinion(),
						StringUtils.isBlank(commonEventHistory.getHandlerContent())?"":commonEventHistory.getHandlerContent(),
						StringUtils.isBlank(commonEventHistory.getRemarks())?"":commonEventHistory.getRemarks(), 
						commonEventHistory.getOper(),FormatUtils.fmtDateTime(commonEventHistory.getOperDate()),
						(commonEventHistory.isFlag()?1:0));
		return _sqlSession.insert(simpleName + ".add", sql);
	}

	@Override
	public CommonEventHistory2 loadAuditEventHistory(String tableName, String stepNo, long billId) throws DAOException {
		SqlSessionTemplate _sqlSession = this.getSqlSession(tableName);
		if (_sqlSession == null)
			throw new DAOException("can't find SqlSessionTemplate!");
		String temp = "select * from %s where activeType='A' And billId=%d and stepNo='%s'";
		String sql = String.format(temp, tableName + tableSuffix, billId,stepNo);
		return _sqlSession.selectOne(simpleName + ".loadEventHistoryBySql", sql);
	}

	@Override
	public List<CommonEventHistory2> loadAuditEventHistory(String tableName, long billId,boolean onlyAudit) throws DAOException {
		SqlSessionTemplate _sqlSession = this.getSqlSession(tableName);
		if (_sqlSession == null)
			throw new DAOException("can't find SqlSessionTemplate!");
		String temp="";
		if(onlyAudit) {
			temp = "select * from %s where flag=1 And activeType='A' And billId=%d";
		}else {
			temp = "select * from %s where billId=%d";
		}
		String sql = String.format(temp, tableName + tableSuffix, billId);
		return _sqlSession.selectList(simpleName + ".loadEventHistoryBySql", sql);
	}

	@Override
	public void updateInvalid(String tableName, long billId, String stepNo) throws DAOException {
		SqlSessionTemplate _sqlSession = this.getSqlSession(tableName);
		if (_sqlSession == null)
			throw new DAOException("can't find SqlSessionTemplate!");
		//如果未指定步骤号则全部更新为无效（驳回时）
		String temp = "";
		String sql = "";
		if(StringUtils.isNotBlank(stepNo)) {
			temp= "update %s set flag=0 where billId=%d and stepNo='%s'";
			sql = String.format(temp, tableName + tableSuffix, billId,stepNo);
		}else {
			temp= "update %s set flag=0 where billId=%d";
			sql = String.format(temp, tableName + tableSuffix, billId);
		}
		_sqlSession.update(simpleName + ".updateInvalid", sql);
	}

}
