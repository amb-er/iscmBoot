package com.armitage.server.iscm.inventorymanage.inventorydata.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAO;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvBal;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvBal2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvBalsAO")
public class ScmInvBalDAOImpl extends BasicDAOImpl<ScmInvBal> implements ScmInvBalDAO {

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
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByOtherInWarehsBill", e);
		}
	}

	@Override
	public void updateByOtherInWarehsBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByOtherInWarehsBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByOtherInWarehsBill", e);
		}
	}
	
	@Override
	public void addByPurInWarehsBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addByPurInWarehsBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByPurInWarehsBill", e);
		}
	}

	@Override
	public void updateByPurInWarehsBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByPurInWarehsBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByPurInWarehsBill", e);
		}
	}

    @Override
    public void updateByMoveBillOutSub(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.update(simpleName + ".updateByMoveBillOutSub",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByMoveBillOutSub", e);
        }
        
    }

    @Override
    public void updateByMoveBillInPlus(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.update(simpleName + ".updateByMoveBillInPlus",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByMoveBillInPlus", e);
        }
        
    }

    @Override
    public void addByMoveBillIn(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.insert(simpleName + ".addByMoveBillIn",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByMoveBillIn", e);
        }
        
    }
    
    @Override
    public void addByPostMoveBillForOut(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.insert(simpleName + ".addByPostMoveBillForOut",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByPostMoveBillForOut", e);
        }
        
    }

    @Override
    public void updateByMoveBillInSub(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.update(simpleName + ".updateByMoveBillInSub",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByMoveBillInSub", e);
        }
        
    }

    @Override
    public void updateByMoveBillOutPlus(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.update(simpleName + ".updateByMoveBillOutPlus",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByMoveBillOutPlus", e);
        }
        
    }

    @Override
    public void addByMoveBillOut(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.insert(simpleName + ".addByMoveBillOut",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByMoveBillOut", e);
        }
        
    }
    
    @Override
    public void addByCancelMoveBillForIn(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.insert(simpleName + ".addByCancelMoveBillForIn",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByCancelMoveBillForIn", e);
        }
        
    }

    @Override
    public void updateBySaleIssuePostPlus(HashMap<String, Object> map) {
        try {
            sqlSession.update(simpleName + ".updateBySaleIssuePostPlus",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateBySaleIssuePostPlus", e);
        }
        
    }

    @Override
    public void updateBySaleIssueUnPost(HashMap<String, Object> map) {
        try {
            sqlSession.update(simpleName + ".updateBySaleIssueUnPost",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateBySaleIssueUnPost", e);
        }
    }

    @Override
    public void addBySaleIssuePost(HashMap<String, Object> map) {
        try {
            sqlSession.insert(simpleName + ".addBySaleIssuePost",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addBySaleIssuePost", e);
        }
    }

    @Override
    public void updateBySaleIssuePostSub(HashMap<String, Object> map) {
        try {
            sqlSession.update(simpleName + ".updateBySaleIssuePostSub",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateBySaleIssuePostSub", e);
        }
    }

	@Override
	public void updateByOtherIssueOutSub(HashMap<String, Object> map) throws AppException {
		try {
			sqlSession.insert(simpleName + ".updateByOtherIssueOutSub",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByOtherIssueOutSub", e);
		}
	}

	@Override
	public void updateByOtherIssueInSub(HashMap<String, Object> map) throws AppException {
		try {
			sqlSession.insert(simpleName + ".updateByOtherIssueInSub",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByOtherIssueInSub", e);
		}
	}
	
	@Override
	public void addByOtherIssueInForCancelDataSnyc(HashMap<String, Object> map) throws AppException {
		try {
			sqlSession.insert(simpleName + ".addByOtherIssueInForCancelDataSnyc",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByOtherIssueInForCancelDataSnyc", e);
		}
	}
	
	@Override
	public void addByOtherIssueOutForDataSnyc(HashMap<String, Object> map) throws AppException {
		try {
			sqlSession.insert(simpleName + ".addByOtherIssueOutForDataSnyc",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByOtherIssueOutForDataSnyc", e);
		}
	}

	@Override
	public void addByMaterialReqBillOutOrgunitNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addByMaterialReqBillOutOrgunitNo",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByMaterialReqBillOutOrgunitNo", e);
		}
	}
	
	@Override
	public void addByMaterialReqBillOut(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addByMaterialReqBillOut",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByMaterialReqBillOut", e);
		}
	}
	
	@Override
	public void addByMaterialReqBillIn(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addByMaterialReqBillIn",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByMaterialReqBillIn", e);
		}
	}
	
	@Override
	public void addByMaterialReqBillInOrgunitNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addByMaterialReqBillInOrgunitNo",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByMaterialReqBillInOrgunitNo", e);
		}
	}

	@Override
	public void addByMaterialReqBillReturn(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addByMaterialReqBillReturn",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByMaterialReqBillReturn", e);
		}
	}

	@Override
	public int updateByMaterialReqBillOut(HashMap<String, Object> map) throws DAOException {
		try {
            return sqlSession.update(simpleName + ".updateByMaterialReqBillOut",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByMaterialReqBillOut", e);
        }
	}

	@Override
	public void updateByMaterialReqBillOutOrgunitNo(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.update(simpleName + ".updateByMaterialReqBillOutOrgunitNo",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByMaterialReqBillOutOrgunitNo", e);
        }
	}
	
	@Override
	public void updateByMaterialReqBillIn(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.update(simpleName + ".updateByMaterialReqBillIn",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByMaterialReqBillIn", e);
        }
	}
	
	@Override
	public int updateByMaterialReqBillInOrgunitNo(HashMap<String, Object> map) throws DAOException {
		try {
            return sqlSession.update(simpleName + ".updateByMaterialReqBillInOrgunitNo",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByMaterialReqBillInOrgunitNo", e);
        }
	}

	@Override
	public void updateByMaterialReqBillReturn(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.update(simpleName + ".updateByMaterialReqBillReturn",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByMaterialReqBillReturn", e);
        }
	}

	@Override
	public void updateByMaterialReqBillReturnOrgunitNo(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.update(simpleName + ".updateByMaterialReqBillReturnOrgunitNo",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByMaterialReqBillReturnOrgunitNo", e);
        }
	}

	@Override
	public void updateByMaterialReqBillOrgunitNo(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.update(simpleName + ".updateByMaterialReqBillOrgunitNo",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByMaterialReqBillOrgunitNo", e);
        }
	}

	@Override
	public void updateByMaterialReqBill(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.update(simpleName + ".updateByMaterialReqBill",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByMaterialReqBill", e);
        }
	}

	@Override
	public void updateByCancelOtherInWarehsBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByCancelOtherInWarehsBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByCancelOtherInWarehsBill", e);
		}
	}

	@Override
	public void updateByInitBill(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByInitBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByInitBill", e);
		}
	}

	@Override
	public void addByInitBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addByInitBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByInitBill", e);
		}
	}
	
	@Override
	public void updateByCancelInitBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByCancelInitBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByCancelInitBill", e);
		}
	}
	
	@Override
	public void updateByCancelPurInWarehsBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByCancelPurInWarehsBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByCancelPurInWarehsBill", e);
		}
	}

    @Override
    public void updateByMoveIssuePost(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.update(simpleName + ".updateByMoveIssuePost",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByMoveIssuePost", e);
        }
    }

    @Override
    public void updateByMoveIssueUnPost(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.update(simpleName + ".updateByMoveIssueUnPost",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByMoveIssueUnPost", e);
        }
    }
	
	@Override
	public void addByPurInWarehsBillOut(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addByPurInWarehsBillOut",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByPurInWarehsBillOut", e);
		}
	}

	@Override
	public void updateByPurInWarehsBillOut(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByPurInWarehsBillOut",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByPurInWarehsBillOut", e);
		}
	}
	
	@Override
	public void updateByCancelPurInWarehsBillOut(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByCancelPurInWarehsBillOut",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByCancelPurInWarehsBillOut", e);
		}
	}

    @Override
    public void updateByMoveInWarehsBill(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.update(simpleName + ".updateByMoveInWarehsBill",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByMoveInWarehsBill", e);
        }
    }

    @Override
    public void addByMoveInWarehsBill(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.update(simpleName + ".addByMoveInWarehsBill",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByMoveInWarehsBill", e);
        }
    }

	@Override
	public void updateByCstInitBill(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByCstInitBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByCstInitBill", e);
		}
	}

	@Override
	public void addByCstInitBill(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addByCstInitBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByCstInitBill", e);
		}
	}

	@Override
	public void updateByCancelCstInitBill(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByCancelCstInitBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByCancelCstInitBill", e);
		}
	}

	@Override
	public void addByCostCenter(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addByCostCenter",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByCostCenter", e);
		}
	}

	@Override
	public void updateByCostCenter(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByCostCenter",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByCostCenter", e);
		}
	}

	@Override
	public void addByOtherInWarehsBillForInvProfit(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addByOtherInWarehsBillForInvProfit",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByOtherInWarehsBillForInvProfit", e);
		}
	}
	
	@Override
	public void addByOtherInWarehsBillForInvDataSync(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addByOtherInWarehsBillForInvDataSync",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByOtherInWarehsBillForInvDataSync", e);
		}
	}
	
	@Override
	public void addByOtherInWarehsBillForCancel(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addByOtherInWarehsBillForCancel",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByOtherInWarehsBillForCancel", e);
		}
	}

	@Override
	public void updateByOtherInWarehsBillForInvProfit(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByOtherInWarehsBillForInvProfit",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByOtherInWarehsBillForInvProfit", e);
		}
	}

	@Override
	public void updateByOtherInWarehsBillForDataSnyc(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByOtherInWarehsBillForDataSnyc",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByOtherInWarehsBillForDataSnyc", e);
		}
	}
	
	@Override
	public void updateByCancelOtherInWarehsBillForInvProfit(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByCancelOtherInWarehsBillForInvProfit",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByCancelOtherInWarehsBillForInvProfit", e);
		}
	}
	
	@Override
	public void updateByCancelOtherInWarehsBillForDataSnyc(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByCancelOtherInWarehsBillForDataSnyc",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByCancelOtherInWarehsBillForDataSnyc", e);
		}
	}

	@Override
	public void updateByCostConsume(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByCostConsume",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByCostConsume", e);
		}
	}

	@Override
	public void updateByCancelCostConsume(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByCancelCostConsume",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByCancelCostConsume", e);
		}
	}
	
	@Override
	public void addByCostConsumPost(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".addByCostConsumPost",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByCostConsumPost", e);
		}
	}
	
	@Override
	public void addByCostConsumCancelPost(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".addByCostConsumCancelPost",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.addByCostConsumCancelPost", e);
		}
	}

	@Override
	public void updateByCancelMoveInWarehsBill(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByCancelMoveInWarehsBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByCancelMoveInWarehsBill", e);
		}
	}

	@Override
	public void calcEndBal(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".calcEndBal",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.calcEndBal", e);
		}
	}

	@Override
	public void buildNextPeriodStartBal(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.insert(simpleName + ".buildNextPeriodStartBal",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.buildNextPeriodStartBal", e);
		}
	}

	@Override
	public void clearStartBal(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".clearStartBal",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.clearStartBal", e);
		}
	}

	@Override
	public void delDeplete(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.delete(simpleName + ".delDeplete", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.delDeplete", e);
		}
	}

	@Override
	public void updateByDepositInReturn(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByDepositInReturn",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByDepositInReturn", e);
		}
	}

	@Override
	public void updateByCancelDepositInReturn(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByCancelDepositInReturn",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByCancelDepositInReturn", e);
		}
	}
	
	@Override
	public void updateByCstFrmLoss(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByCstFrmLoss",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByCstFrmLoss", e);
		}
	}

	@Override
	public void updateByCancelCstFrmLoss(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByCancelCstFrmLoss",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvBalDAOImpl.error.updateByCancelCstFrmLoss", e);
		}
	}
}

