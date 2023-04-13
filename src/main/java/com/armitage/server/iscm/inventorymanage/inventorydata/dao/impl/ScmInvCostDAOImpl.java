package com.armitage.server.iscm.inventorymanage.inventorydata.dao.impl;


import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAO;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvCost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvCostDAO")
public class ScmInvCostDAOImpl extends BasicDAOImpl<ScmInvCost> implements ScmInvCostDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}
	
	@Override
	public void addByOtherInWarehsBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addByOtherInWarehsBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.addByOtherInWarehsBill", e);
		}
	}

	@Override
	public void updateByOtherInWarehsBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByOtherInWarehsBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByOtherInWarehsBill", e);
		}
	}
	
	@Override
	public void addByPurInWarehsBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addByPurInWarehsBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.addByPurInWarehsBill", e);
		}
	}

	@Override
	public void updateByPurInWarehsBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByPurInWarehsBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByPurInWarehsBill", e);
		}
	}

	@Override
	public void updateByPurInWarehsBillOut(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByPurInWarehsBillOut",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByPurInWarehsBillOut", e);
		}
	}

	@Override
	public void updateByCancelPurInWarehsBillOut(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByCancelPurInWarehsBillOut",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByCancelPurInWarehsBillOut", e);
		}
	}

	@Override
	public void updateByCancelPurInWarehsBill(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByCancelPurInWarehsBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByCancelPurInWarehsBill", e);
		}
	}

    @Override
    public void updateByMoveBillOutSub(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.update(simpleName + ".updateByMoveBillOutSub",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByMoveBillOutSub", e);
        }
        
    }

    @Override
    public void addByMoveBillIn(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.insert(simpleName + ".addByMoveBillIn",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.addByMoveBillIn", e);
        }
        
    }

    @Override
    public void updateByMoveBillInPlus(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.update(simpleName + ".updateByMoveBillInPlus",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByMoveBillInPlus", e);
        }
        
    }

    @Override
    public void addByMoveBillOut(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.insert(simpleName + ".addByMoveBillOut",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.addByMoveBillOut", e);
        }
        
    }

    @Override
    public void updateByMoveBillOutPlus(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.update(simpleName + ".updateByMoveBillOutPlus",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByMoveBillOutPlus", e);
        }
        
    }

    @Override
    public void updateByMoveBillInSub(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.update(simpleName + ".updateByMoveBillInSub",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByMoveBillInSub", e);
        }
        
    }

    @Override
    public void updateBySaleIssuePostSub(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.update(simpleName + ".updateBySaleIssuePostSub",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateBySaleIssuePostSub", e);
        }
    }
    
    @Override
    public void updateBySaleIssueUnPost(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.update(simpleName + ".updateBySaleIssueUnPost",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateBySaleIssueUnPost", e);
        }
    }

    @Override
    public void addBySaleIssuePost(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.insert(simpleName + ".addBySaleIssuePost",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.addBySaleIssuePost", e);
        }
    }

    @Override
    public void updateBySaleIssuePostPlus(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.update(simpleName + ".updateBySaleIssuePostPlus",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateBySaleIssuePostPlus", e);
        }
    }

	@Override
	public void updateByOtherIssueOutSub(HashMap<String, Object> map) throws AppException {
		 try {
			 sqlSession.update(simpleName + ".updateByOtherIssueOutSub",map);
		 } catch (Exception e) {
			 throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByOtherIssueOutSub", e);
		 }
	}

	@Override
	public void updateByOtherIssueInSub(HashMap<String, Object> map) throws AppException {
		 try {
			 sqlSession.update(simpleName + ".updateByOtherIssueInSub",map);
		 } catch (Exception e) {
			 throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByOtherIssueInSub", e);
		 }
	}

	@Override
	public void addByMaterialReqBillOutOrgunitNo(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.insert(simpleName + ".addByMaterialReqBillOutOrgunitNo",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.addByMaterialReqBillOutOrgunitNo", e);
        }
	}

	@Override
	public void updateByMaterialReqBillOut(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.update(simpleName + ".updateByMaterialReqBillOut",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByMaterialReqBillOut", e);
        }
	}

	@Override
	public void updateByMaterialReqBillOutOrgunitNo(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.update(simpleName + ".updateByMaterialReqBillOutOrgunitNo",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByMaterialReqBillOutOrgunitNo", e);
        }
	}

	@Override
	public void updateByMaterialReqBillReturn(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.update(simpleName + ".updateByMaterialReqBillReturn",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByMaterialReqBillReturn", e);
        }
	}

	@Override
	public void updateByMaterialReqBillReturnOrgunitNo(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.update(simpleName + ".updateByMaterialReqBillReturnOrgunitNo",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByMaterialReqBillReturnOrgunitNo", e);
        }
	}

	@Override
	public void updateByCancelOtherInWarehsBill(HashMap<String, Object> map)
			throws DAOException {
		try {
            sqlSession.update(simpleName + ".updateByCancelOtherInWarehsBill",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByCancelOtherInWarehsBill", e);
        }
	}

	@Override
	public void updateByMoveInWarehsBill(HashMap<String, Object> map)
			throws DAOException {
		try {
            sqlSession.update(simpleName + ".updateByMoveInWarehsBill",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByMoveInWarehsBill", e);
        }
	}

	@Override
	public void addByMoveInWarehsBill(HashMap<String, Object> map)
			throws DAOException {
		try {
            sqlSession.insert(simpleName + ".addByMoveInWarehsBill",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.addByMoveInWarehsBill", e);
        }
	}

	@Override
	public void updateByCancelMoveInWarehsBill(HashMap<String, Object> map)
			throws DAOException {
		try {
            sqlSession.update(simpleName + ".updateByCancelMoveInWarehsBill",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByCancelMoveInWarehsBill", e);
        }
	}

	@Override
	public void updateByMoveIssueUnPost(HashMap<String, Object> map)
			throws DAOException {
		try {
            sqlSession.update(simpleName + ".updateByMoveIssueUnPost",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByMoveIssueUnPost", e);
        }
	}

	@Override
	public void updateByMoveIssuePost(HashMap<String, Object> map)
			throws DAOException {
		try {
            sqlSession.update(simpleName + ".updateByMoveIssuePost",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByMoveIssuePost", e);
        }
	}

	@Override
	public void updateByCostCenter(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.update(simpleName + ".updateByCostCenter",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByCostCenter", e);
        }
	}

	@Override
	public void addByCostCenter(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.update(simpleName + ".addByCostCenter",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.addByCostCenter", e);
        }
	}

	@Override
	public void updateByDepositInReturn(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.update(simpleName + ".updateByDepositInReturn",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByDepositInReturn", e);
        }
	}

	@Override
	public void updateByCancelDepositInReturn(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.update(simpleName + ".updateByCancelDepositInReturn",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvCostDAOImpl.error.updateByCancelDepositInReturn", e);
        }
	}

}

