/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.armitage.server.activity.rest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.armitage.server.common.base.model.OperationParam;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.Param.ParamType;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.BillTypeSetting;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.BillTypeSettingBiz;
import com.armitage.server.system.service.OrgBaseUnitBiz;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author Tijs Rademakers
 */
@RestController
@RequestMapping("/activiti-explorer")
public class ModelSaveRestResource implements ModelDataJsonConstants {
  
  protected static final Logger LOGGER = LoggerFactory.getLogger(ModelSaveRestResource.class);

  @Autowired
  private RepositoryService repositoryService;
  
  @Autowired
  private ObjectMapper objectMapper;
  
  private OrgBaseUnitBiz orgBaseUnitBiz = (OrgBaseUnitBiz) AppContextUtil.getBean("orgBaseUnitBiz");
  private BillTypeBiz billTypeBiz = (BillTypeBiz) AppContextUtil.getBean("billTypeBiz");
  
  @RequestMapping(value="/model/{modelId}/save", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.OK)
  public void saveModel(@PathVariable String modelId, @RequestBody MultiValueMap<String, String> values) throws ActivitiException,AppException{
    try {
      
      Model model = repositoryService.getModel(modelId);
      
      ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
      
      modelJson.put(MODEL_NAME, values.getFirst("name"));
      modelJson.put(MODEL_DESCRIPTION, values.getFirst("description"));
      model.setMetaInfo(modelJson.toString());
      model.setName(values.getFirst("name"));
      
      repositoryService.saveModel(model);
      
      repositoryService.addModelEditorSource(model.getId(), values.getFirst("json_xml").getBytes("utf-8"));
      
      InputStream svgStream = new ByteArrayInputStream(values.getFirst("svg_xml").getBytes("utf-8"));
      TranscoderInput input = new TranscoderInput(svgStream);
      
      PNGTranscoder transcoder = new PNGTranscoder();
      // Setup output
      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      TranscoderOutput output = new TranscoderOutput(outStream);
      
      // Do the transformation
      transcoder.transcode(input, output);
      final byte[] result = outStream.toByteArray();
      repositoryService.addModelEditorSourceExtra(model.getId(), result);
      outStream.close();
      
      //自定义内容
      String customParams = values.getFirst("customParams");
      String params[] = StringUtils.split(customParams, "&");
      String usrCode=StringUtils.substringAfter(params[1], "=");
      String orgUnitNo=StringUtils.substringAfter(params[2], "=");
      String billTypeId=StringUtils.substringAfter(params[3], "=");
      String operation=StringUtils.substringAfter(params[4], "=");
      
      OperationParam param = new OperationParam();
      param.setUsrCode(usrCode);
      param.setOrgUnitNo(orgUnitNo);
      OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(orgUnitNo, param);
      if (orgBaseUnit != null) {
    	  param.setControlUnitNo(orgBaseUnit.getControlUnitNo());
      }
      
      BillType2 billType = billTypeBiz.selectByOrgAndBillTypeId(orgUnitNo, Long.parseLong(billTypeId), param);
      if(billType != null){
    	  if(StringUtils.isNotBlank(billType.getWorkFlowNo()) && !modelId.equals(billType.getWorkFlowNo())){
    		  throw new AppException("activity.util.ActivityUtil.bill.processDiff.error");
    	  }
    	  billType.setWorkFlowNo(modelId);
    	  billTypeBiz.update(billType, param);
      }
    } catch (Exception e) {
      LOGGER.error("Error saving model", e);
      throw new ActivitiException("Error saving model", e);
    }
  }
}
