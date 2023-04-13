package com.armitage.server.iscm.inventorymanage.inventorydata.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAO;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmFinDeptConsume;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvStockDAO")
public class ScmInvStockDAOImpl extends BasicDAOImpl<ScmInvStock> implements ScmInvStockDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public int addByOtherInWarehsBill(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.insert(simpleName + ".addByOtherInWarehsBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.addByOtherInWarehsBill", e);
		}
	}

	@Override
	public int updateByOtherInWarehsBill(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByOtherInWarehsBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByOtherInWarehsBill", e);
		}
	}
	
	@Override
	public int addByPurInWarehsBill(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.insert(simpleName + ".addByPurInWarehsBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.addByPurInWarehsBill", e);
		}
	}

	@Override
	public int updateByPurInWarehsBill(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByPurInWarehsBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByPurInWarehsBill", e);
		}
	}

    @Override
    public int addByMoveBillIn(HashMap<String, Object> map) throws DAOException {
        try {
        	return sqlSession.insert(simpleName + ".addByMoveBillIn",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.addByMoveBillIn", e);
        }
        
    }

    @Override
    public int updateByMoveBillOutSub(HashMap<String, Object> map) throws DAOException {
        try {
        	return sqlSession.update(simpleName + ".updateByMoveBillOutSub",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByMoveBillOutSub", e);
        }
        
    }

    @Override
    public int updateByMoveBillInPlus(HashMap<String, Object> map) throws DAOException {
        try {
        	return sqlSession.update(simpleName + ".updateByMoveBillInPlus",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByMoveBillInPlus", e);
        }
        
    }

    @Override
    public int addByMoveBillOut(HashMap<String, Object> map) throws DAOException {
        try {
        	return sqlSession.insert(simpleName + ".addByMoveBillOut",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.addByMoveBillOut", e);
        }
    }

    @Override
    public int updateByMoveBillOutPlus(HashMap<String, Object> map) throws DAOException {
        try {
        	return sqlSession.update(simpleName + ".updateByMoveBillOutPlus",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByMoveBillOutPlus", e);
        }
    }

    @Override
    public int updateByMoveBillInSub(HashMap<String, Object> map) throws DAOException {
        try {
        	return sqlSession.update(simpleName + ".updateByMoveBillInSub",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByMoveBillInSub", e);
        }
    }

    @Override
    public int updateBySaleIssuePostSub(HashMap<String, Object> map) throws DAOException {
        try {
        	return sqlSession.update(simpleName + ".updateBySaleIssuePostSub",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateBySaleIssuePostSub", e);
        }
    }

    @Override
    public int updateBySaleIssueUnPost(HashMap<String, Object> map) throws DAOException {
        try {
        	return sqlSession.update(simpleName + ".updateBySaleIssueUnPost",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateBySaleIssueUnPost", e);
        }
    }

    @Override
    public int updateBySaleIssueNotOffset(HashMap<String, Object> map) throws DAOException {
        try {
        	return sqlSession.update(simpleName + ".updateBySaleIssueNotOffset",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateBySaleIssueNotOffset", e);
        }
    }

    @Override
    public int addBySaleIssuePost(HashMap<String, Object> map) throws DAOException {
        try {
        	return sqlSession.insert(simpleName + ".addBySaleIssuePost",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.addBySaleIssuePost", e);
        }
    }

	@Override
	public int updateByOtherIssueOutSub(HashMap<String, Object> map) throws AppException {
		try {
			return sqlSession.update(simpleName + ".updateByOtherIssueOutSub",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByOtherIssueOutSub", e);
        }
	}

	@Override
	public int updateByOtherIssueInSub(HashMap<String, Object> map) throws AppException {
		try {
			return sqlSession.update(simpleName + ".updateByOtherIssueInSub",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByOtherIssueInSub", e);
        }
	}

    @Override
    public List<ScmInvStock2> findByDate(HashMap<String, Object> map) {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".findByDate",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.findByDate", e);
        }
    }

	@Override
	public List<ScmInvStock2> findByOutWarehouse(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".findByOutWarehouse",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.findByOutWarehouse", e);
        }
	}

	@Override
	public List<ScmInvStock2> findByReturnWarehouse(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".findByReturnWarehouse",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.findByReturnWarehouse", e);
        }
	}

	@Override
	public int addByMaterialReqBillOutOrgunitNo(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.insert(simpleName + ".addByMaterialReqBillOutOrgunitNo",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.addByMaterialReqBillOutOrgunitNo", e);
        }
	}
	
	@Override
	public int updateByMaterialReqBill(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.insert(simpleName + ".updateByMaterialReqBill",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByMaterialReqBill", e);
        }
	}
	
	@Override
	public int updateByMaterialReqBillOrgunitNo(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.insert(simpleName + ".updateByMaterialReqBillOrgunitNo",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByMaterialReqBillOrgunitNo", e);
        }
	}

	@Override
	public int addByMaterialReqBillReturn(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.insert(simpleName + ".addByMaterialReqBillReturn",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.addByMaterialReqBillReturn", e);
        }
	}

	@Override
	public int updateByMaterialReqBillOut(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByMaterialReqBillOut",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByMaterialReqBillOut", e);
        }
	}
	
	@Override
	public int updateByMaterialReqBillIn(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByMaterialReqBillIn",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByMaterialReqBillIn", e);
        }
	}
	
	@Override
	public int updateByMaterialReqBillInOrgunitNo(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByMaterialReqBillInOrgunitNo",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByMaterialReqBillInOrgunitNo", e);
        }
	}

	@Override
	public int updateByMaterialReqBillOutOrgunitNo(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByMaterialReqBillOutOrgunitNo",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByMaterialReqBillOutOrgunitNo", e);
        }
	}

	@Override
	public int updateByMaterialReqBillReturn(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByMaterialReqBillReturn",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByMaterialReqBillReturn", e);
        }
	}

	@Override
	public int updateByMaterialReqBillReturnOrgunitNo(HashMap<String, Object> map) throws DAOException {
		try {
            return sqlSession.update(simpleName + ".updateByMaterialReqBillReturnOrgunitNo",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByMaterialReqBillReturnOrgunitNo", e);
        }
	}

	@Override
	public int updateByCancelOtherInWarehsBill(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByCancelOtherInWarehsBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByCancelOtherInWarehsBill", e);
		}
	}

    @Override
    public List<ScmInvStock2> selectPriceByStock(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectPriceByStock",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.selectPriceByStock", e);
        }
    }
    
    @Override
	public int updateByInitBill(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByInitBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByInitBill", e);
		}
	}
    
    @Override
	public int updateByCancelPurInWarehsBill(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByCancelPurInWarehsBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByCancelPurInWarehsBill", e);
		}
	}

	@Override
	public List<ScmInvStock2> findByOutAndReturnWarehouse(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".findByOutAndReturnWarehouse",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.findByOutAndReturnWarehouse", e);
        }
	}
	
	@Override
	public int updateByPurInWarehsBillOut(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByPurInWarehsBillOut",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByPurInWarehsBillOut", e);
		}
	}
	
	@Override
	public int updateByCancelPurInWarehsBillOut(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByCancelPurInWarehsBillOut",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByCancelPurInWarehsBillOut", e);
		}
	}

    @Override
	public int addByInitBill(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.insert(simpleName + ".addByInitBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.addByInitBill", e);
		}
	}

	@Override
	public int updateByCancelInitBill(HashMap<String, Object> map)
			throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByCancelInitBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByCancelInitBill", e);
		}
	}

    @Override
    public int updateByMoveIssuePost(HashMap<String, Object> map) throws DAOException {
        try {
        	return sqlSession.update(simpleName + ".updateByMoveIssuePost",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByMoveIssuePost", e);
        }
    }
	@Override
	public int checkCostCenter(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".checkCostCenter", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.checkCostCenter", e);
		}
	}

	@Override
	public int updateByCstInitBill(HashMap<String, Object> map)
			throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByCstInitBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByCstInitBill", e);
		}
	}

	@Override
	public int addByCstInitBill(HashMap<String, Object> map)
			throws DAOException {
		try {
			return sqlSession.insert(simpleName + ".addByCstInitBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.addByCstInitBill", e);
		}
	}

	@Override
	public int updateByCancelCstInitBill(HashMap<String, Object> map)
			throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByCancelCstInitBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByCancelCstInitBill", e);
		}
	}

	@Override
	public int checkCostCenter2(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".checkCostCenter2", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.checkCostCenter2", e);
		}
	}

	@Override
	public int addByCostCenter(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.insert(simpleName + ".addByCostCenter",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.addByCostCenter", e);
		}
	}

	@Override
	public int updateByCostCenter(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByCostCenter",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByCostCenter", e);
        }
	}

    @Override
    public int updateByMoveIssueUnPost(HashMap<String, Object> map) throws DAOException {
        try {
        	return sqlSession.update(simpleName + ".updateByMoveIssueUnPost",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByMoveIssueUnPost", e);
        }
    }

    @Override
    public int updateByMoveInWarehsBill(HashMap<String, Object> map) throws DAOException {
        try {
            return sqlSession.update(simpleName + ".updateByMoveInWarehsBill",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByMoveInWarehsBill", e);
        }
    }

    @Override
    public int addByMoveInWarehsBill(HashMap<String, Object> map) throws DAOException {
        try {
            return sqlSession.insert(simpleName + ".addByMoveInWarehsBill",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.addByMoveInWarehsBill", e);
        }
    }

	@Override
	public int updateByCostConsume(HashMap<String, Object> map)
			throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByCostConsume",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByCostConsume", e);
		}
	}

	@Override
	public int updateByCancelCostConsume(HashMap<String, Object> map)
			throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByCancelCostConsume",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByCancelCostConsume", e);
		}
	}

	@Override
	public int updateByCancelMoveInWarehsBill(HashMap<String, Object> map)
			throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByCancelMoveInWarehsBill",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByCancelMoveInWarehsBill", e);
		}
	}

	@Override
	public int deleteZeroQty(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.delete(simpleName + ".deleteZeroQty", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.deleteZeroQty", e);
		}
	}

	@Override
	public int writeBackZeroQty(HashMap<String, Object> map)
			throws DAOException {
		try {
			return sqlSession.insert(simpleName + ".writeBackZeroQty", map);

		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.writeBackZeroQty", e);
		}
	}

	@Override
	public List<ScmInvStock2> findByWareHouse(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".findByWareHouse",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.findByWareHouse", e);
        }
	}

	@Override
	public List<ScmInvStock2> findByUseOrgUnitNo(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".findByUseOrgUnitNo",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.findByUseOrgUnitNo", e);
        }
	}

	@Override
	public List<ScmInvStock2> selectWareHsForSale(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectWareHsForSale",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.selectWareHsForSale", e);
        }
	}

	@Override
	public int updateByDepositInReturn(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByDepositInReturn",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByDepositInReturn", e);
		}
	}

	@Override
	public int updateByCancelDepositInReturn(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByCancelDepositInReturn",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByCancelDepositInReturn", e);
		}
	}

	@Override
	public List<ScmInvStock2> findByUseOrgCounting(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".findByUseOrgCounting",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.findByUseOrgCounting", e);
        }
	}
	
	@Override
	public int updateByCstFrmLoss(HashMap<String, Object> map)
			throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByCstFrmLoss",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByCstFrmLoss", e);
		}
	}

	@Override
	public int updateByCancelCstFrmLoss(HashMap<String, Object> map)
			throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateByCancelCstFrmLoss",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.updateByCancelCstFrmLoss", e);
		}
	}

	@Override
	public List<ScmFinDeptConsume> selectFinDeptConsume(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectFinDeptConsume",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.selectFinDeptConsume", e);
        }
	}

	@Override
	public List<ScmFinDeptConsume> selectReqFinDeptConsume(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectReqFinDeptConsume",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.selectReqFinDeptConsume", e);
        }
	}

	@Override
	public List<ScmInvStock2> selectByOrgUnitNos(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByOrgUnitNos",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.selectByOrgUnitNos", e);
        }
	}

	@Override
	public ScmInvStock2 selectIdleItemStock(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectIdleItemStock", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.selectIdleItemStock", e);
		}
	}

	@Override
	public ScmInvStock2 getStockQty(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".getStockQty", map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.getStockQty", e);
		}
	}

	@Override
	public ScmInvStock2 selectCostPrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectCostPrice", map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.selectCostPrice", e);
		}
	}

	@Override
	public ScmInvStock2 getStockQtyByReqOrg(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".getStockQtyByReqOrg", map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.getStockQtyByReqOrg", e);
		}
	}

	@Override
	public List<ScmInvStock2> getStockQtyList(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".getStockQtyList",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.getStockQtyList", e);
        }
	}

	@Override
	public List<ScmInvStock2> selectOrgForSale(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectOrgForSale",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.selectOrgForSale", e);
        }
	}

	@Override
	public List<ScmInvStock2> selectCostPriceByItems(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectCostPriceByItems",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockDAOImpl.error.selectCostPriceByItems", e);
        }
	}
	
	
}

