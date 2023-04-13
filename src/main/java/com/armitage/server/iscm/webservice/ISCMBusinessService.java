/**
 * 
 */
package com.armitage.server.iscm.webservice;

import javax.jws.WebService;

import com.armitage.server.common.exception.AppException;
 
/**
 * @author jiale.xu
 *
 */
@WebService

public interface ISCMBusinessService {
	
	/**
	 * 审批请购单
	 * @param jIntegratedRequest
	 * @param jParams
	 * @return
	 */
	public String doUpdateRequireStatus(String jIntegratedRequest,String jParams) throws AppException ;
}


