package com.bdilab.aiflow.service.component.impl;


import com.bdilab.aiflow.common.config.FilePathConfig;
import com.bdilab.aiflow.mapper.*;
import com.bdilab.aiflow.model.*;
import com.bdilab.aiflow.model.component.ComponentCreateInfo;
import com.bdilab.aiflow.model.component.CustomComponentInfo;
import com.bdilab.aiflow.model.component.InputStubInfo;
import com.bdilab.aiflow.model.component.OutputStubInfo;
import com.bdilab.aiflow.model.workflow.Variable;
import com.bdilab.aiflow.service.component.CustomComponentService;
import com.bdilab.aiflow.vo.ComponentInfoVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.hadoop.hbase.regionserver.RegionServerRunningException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class CustomComponentServiceImpl implements CustomComponentService {

    @Resource
    FilePathConfig filePathConfig;

    @Resource
    CustomComponentMapper customComponentMapper;

    @Resource
    ComponentInfoMapper componentInfoMapper;

    @Resource
    ComponentParameterMapper componentParamMapper;

    @Resource
    EnumValueMapper enumValueMapper;

    @Resource
    WorkflowComponentMapper workflowComponentMapper;

    @Resource
    WorkflowMapper workflowMapper;


    /**
     * 一共需要组装三张表信息:compontentInfo,componentParameter,customComponent
     * 这个接口现在只服务算法组件
     * @param userId              用户id
     * @param yamlFileName        要保存的yaml文件名称，不带.yaml
     * @param componentCreateInfo 专门用来保存组件创建信息的对象
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveComponent(Integer userId, String yamlFileName, ComponentCreateInfo componentCreateInfo) {

        ComponentInfo componentInfo = componentCreateInfo.getComponentInfo();
        List<ComponentParameter> componentParam = componentCreateInfo.getComponentParamList();

        //组装ComponentInfo对象
        componentInfo.setIsCustom((byte) 1);

        //组装、存储自定义组件yaml文件目录
        //0-算法，1-流程(流程没有yaml)，2-模型
 //       if (componentCreateInfo.getComponentType() != 1) {

            String yamlFilePath = "/home/pipelineYaml/" + yamlFileName + ".yaml";
            componentInfo.setComponentYamlAddr(yamlFilePath);
 //       }

        //组装inputStub对象
        StringBuilder inputStubBuilder = new StringBuilder();
        List<InputStubInfo> inputList = componentCreateInfo.getInputStubInfoList();
        for (InputStubInfo input : inputList) {
            inputStubBuilder.append(input.getInputName() + ":");
            inputStubBuilder.append(input.getInputType() + ",");
        }
        inputStubBuilder.deleteCharAt(inputStubBuilder.length() - 1);
        componentInfo.setInputStub("{"+inputStubBuilder.toString()+"}");

        //组装outputStub对象
        StringBuilder outputStubBuilder = new StringBuilder();
        List<OutputStubInfo> outputList = componentCreateInfo.getOutputStubInfoList();
        for (OutputStubInfo output : outputList) {
            outputStubBuilder.append(output.getOutputName() + ":");
            outputStubBuilder.append(output.getOutputType() + ",");
        }
        outputStubBuilder.deleteCharAt(outputStubBuilder.length() - 1);
        componentInfo.setOutputStub("{"+outputStubBuilder.toString()+"}");
        if(componentCreateInfo.getComponentInfo().getComponentNameChs()==null
                ||componentCreateInfo.getComponentInfo().getComponentNameChs().length()==0){
            componentInfo.setComponentNameChs(componentInfo.getName());
        }

        //先置入componentInfo
        int isInfoSuccess = componentInfoMapper.insertComponentInfo(componentInfo);

        //组装ComponentParameter对象
        if (isInfoSuccess <= 0) {

            return false;
        }
        //向表中插入所有参数
        int isParamSuccess;
        //没有参数的情况
        if(componentParam==null||componentParam.size()==0){
            isParamSuccess=1;
        }
        else {
            //对每个参数设置组件id
            for (ComponentParameter param : componentParam) {
                param.setFkComponentInfoId(componentInfo.getId());
            }
            isParamSuccess = componentParamMapper.insertComponentParam(componentParam);
        }

        CustomComponent customComponent = new CustomComponent();
        if (isParamSuccess > 0) {
            System.out.println(componentInfo);
            System.out.println(componentCreateInfo.getComponentParamList());

            //组装customComponent对象

            customComponent.setFkUserId(userId);
            customComponent.setFkComponentInfoId(componentInfo.getId());
            customComponent.setIsDeleted((byte) 0);
            customComponent.setType(componentCreateInfo.getComponentType());
            customComponent.setSourceId(userId.toString());
            customComponent.setCreateTime(new Date());
            System.out.println(customComponent);
        } else {
            return false;
        }

        int isCustomSuccess = customComponentMapper.insertCustomComponent(customComponent);
        //流程组件需要额外插入workflowComponent表，不写
//        WorkflowComponent workflowComponent = new WorkflowComponent();
        if (isCustomSuccess > 0) {
            //流程组件继续插入
//            if(componentCreateInfo.getComponentType() == 1){
//                Workflow workflow = workflowMapper.selectWorkflowById(Integer.parseInt(componentCreateInfo.getSourceId()));
//                workflowComponent.setName(componentCreateInfo.getComponentInfo().getName());
//                workflowComponent.setTag(componentCreateInfo.getComponentInfo().getTags());
//                workflowComponent.setWorkflowComponentDesc(componentCreateInfo.getComponentInfo().getComponentDesc());
//                workflowComponent.setGgeditorObjectString(workflow.getGgeditorObjectString());
//                workflowComponent.setIsDeleted((byte) 0);
//                workflowComponent.setCreateTime(customComponent.getCreateTime());
//                workflowComponent.setFkUserId(userId);
//                System.out.println(workflowComponent);
//                //流程组件插入也成功，结束
//                if(workflowComponentMapper.insert(workflowComponent)>0) { return true; }
//                else{ return false; }
//            }
            return true;
        } else {
            return false;
        }
    }


//    /**
//     * 一共需要组装三张表信息:compontentInfo,componentParameter,customComponent
//     * @param userId 用户id
//     * @param componentFile 要保存的yaml文件，流程组件没有yaml文件
//     * @param componentCreateInfo 专门用来保存组件创建信息的对象
//     * @return
//     */
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public Boolean saveComponent(Integer userId, MultipartFile componentFile, ComponentCreateInfo componentCreateInfo) {
//
//        ComponentInfo componentInfo = componentCreateInfo.getComponentInfo();
//        List<ComponentParameter> componentParam = componentCreateInfo.getComponentParamList();
//
//        //组装ComponentInfo对象
//        componentInfo.setIsCustom((byte) 1);
//
//        //组装、存储自定义组件yaml文件目录
//        //0-算法，1-流程(流程没有yaml)，2-模型
//        if (!componentFile.isEmpty() && componentCreateInfo.getComponentType()!=1) {
//            String direcrotyPath = "";
//            switch (componentCreateInfo.getComponentType()) {
//                case 0:
//                    direcrotyPath = "algorithm"; break;
//                case 2:
//                    direcrotyPath = "model"; break;
//                default:
//                    direcrotyPath = "";
//            }
//
//            String yamlFilePath = filePathConfig.getComponentYamlPath() + File.separator +
//                    direcrotyPath + File.separator + userId + "_" +
//                    componentCreateInfo.getComponentInfo().getName() + ".yaml";
//            File dest = new File(yamlFilePath);
//            try {
//                componentFile.transferTo(dest);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return false;
//            }
//            componentInfo.setComponentYamlAddr(yamlFilePath);
//        }
//
//        //组装inputStub对象
//        StringBuilder inputStubBuilder = new StringBuilder();
//        List<InputStubInfo> inputList = componentCreateInfo.getInputStubInfoList();
//        for (InputStubInfo input : inputList) {
//            inputStubBuilder.append(input.getInputName()+":");
//            inputStubBuilder.append(input.getInputType()+",");
//        }
//        inputStubBuilder.deleteCharAt(inputStubBuilder.length()-1);
//        componentInfo.setInputStub(inputStubBuilder.toString());
//
//        //组装outputStub对象
//        StringBuilder outputStubBuilder = new StringBuilder();
//        List<OutputStubInfo> outputList = componentCreateInfo.getOutputStubInfoList();
//        for (OutputStubInfo output : outputList) {
//            outputStubBuilder.append(output.getOutputName()+":");
//            outputStubBuilder.append(output.getOutputType()+",");
//        }
//        outputStubBuilder.deleteCharAt(outputStubBuilder.length()-1);
//        componentInfo.setOutputStub(outputStubBuilder.toString());
//
//        //先置入componentInfo
//        int isInfoSuccess = componentInfoMapper.insertComponentInfo(componentInfo);
//
//        //组装ComponentParameter对象
//        if (isInfoSuccess > 0) {
//            //对每个参数设置组件id
//            for (ComponentParameter param : componentParam) {
//                param.setFkComponentInfoId(componentInfo.getId());
//            }
//        } else {
//            return false;
//        }
//
//        //向表中插入所有参数
//        int isParamSuccess = componentParamMapper.insertComponentParam(componentParam);
//        if (isParamSuccess > 0) {
//            System.out.println(componentInfo);
//            System.out.println(componentCreateInfo.getComponentParamList());
//
//            //组装customComponent对象
//            CustomComponent customComponent = new CustomComponent();
//            customComponent.setFkUserId(userId);
//            customComponent.setFkComponentInfoId(componentInfo.getId());
//            customComponent.setIsDeleted((byte) 0);
//            customComponent.setType(componentCreateInfo.getComponentType());
//            customComponent.setSourceId(componentCreateInfo.getSourceId());
//            customComponent.setCreateTime(new Date());
//
//            int isCustomSuccess = customComponentMapper.insertCustomComponent(customComponent);
//            if (isCustomSuccess > 0) {
//                System.out.println(customComponent);
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
//    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteComponent(Integer componentId) {
        int isDeleteSuccess = customComponentMapper.deleteComponent(componentId);
        if (isDeleteSuccess > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteComponentPermanently(List<Integer> componentIds) {

        int componentAmount = componentIds.size();
        System.out.println(componentIds);

        List<Integer> fkComponentInfoIds = customComponentMapper.selectFkComponentInfoIds(componentIds);
        System.out.println(fkComponentInfoIds);

        //删除custom_component表数据
        int isCustomDeleteSuccess = customComponentMapper.deleteComponentPermanently(componentIds);
        System.out.println("custom:"+isCustomDeleteSuccess);

        if (isCustomDeleteSuccess > 0) {
            //删除component_parameter表数据
            int isParamDeleteSuccess = componentParamMapper.deleteComponentPermanently(fkComponentInfoIds);
            System.out.println("param:"+isParamDeleteSuccess);
            if (isParamDeleteSuccess >= 0) {

                //删除组件对应的yaml文件
                List<String> yamlFilePath = componentInfoMapper.selectComponentYamlAddr(fkComponentInfoIds);
                System.out.println("yamlFilePath:"+yamlFilePath);
                for (String path : yamlFilePath) {
                    if (path == null) {
                        continue;
                    }
                    File yamlFile = new File(path);
                    if (yamlFile.exists()) {
                        yamlFile.delete();
                        System.out.println(yamlFile);
                    } else {
                        System.out.println("组件yaml文件：" + yamlFile + " 不存在！删除失败！");
                    }
                }

                //删除component_info表数据
                int isInfoDeleteSuccess = componentInfoMapper.deleteComponentPermanently(fkComponentInfoIds);
                System.out.println("info:"+isInfoDeleteSuccess);
                if (isInfoDeleteSuccess == componentAmount) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean restoreComponent(List<Integer> componentIds,int type) {
        int isSuccess;
        if(type==1){
            isSuccess=workflowComponentMapper.restoreComponent(componentIds);
        }
        else {
            isSuccess=customComponentMapper.restoreComponent(componentIds);
        }
        return isSuccess>=1;
    }

    @Override
    public PageInfo<CustomComponentInfo> selectComponentByKeyword(String keyword, int type, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<CustomComponentInfo> customComponentList = customComponentMapper.selectCustomComponentByKeyword(keyword,type);
        PageInfo<CustomComponentInfo> pageInfo = new PageInfo<>(customComponentList);
        return pageInfo;
    }

    @Override
    public PageInfo<CustomComponentInfo> selectComponentByTag(String tag, int type, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<CustomComponentInfo> customComponentList = customComponentMapper.selectCustomComponentByTag(tag,type);
        PageInfo<CustomComponentInfo> pageInfo = new PageInfo<>(customComponentList);
        return pageInfo;
    }

    @Override
    public PageInfo<CustomComponentInfo> getCustomComponentByUserIdAndType(int userId, int pageNum, int pageSize, int type,int isDeleted) {
        PageHelper.startPage(pageNum, pageSize);
        List<CustomComponent> customComponentList = null;
        List<CustomComponentInfo> componentInfoList = new ArrayList<>();
        if(type!=1) {
            customComponentList = customComponentMapper.getCustomComponentByUserIdAndType(userId, type, isDeleted);
            for (CustomComponent customComponent : customComponentList
            ) {
                ComponentInfo componentInfo = componentInfoMapper.selectComponentInfoById(customComponent.getFkComponentInfoId());
                CustomComponentInfo customComponentInfo = new CustomComponentInfo();
                customComponentInfo.setId(customComponent.getId());
                customComponentInfo.setName(componentInfo.getName());
                customComponentInfo.setComponentDesc(componentInfo.getComponentDesc());
                customComponentInfo.setCreateTime(customComponent.getCreateTime());
                customComponentInfo.setTags(componentInfo.getTags());
                componentInfoList.add(customComponentInfo);
            }
        }
        else {
            List<WorkflowComponent> workflowComponents = workflowComponentMapper.selectWorkflowComponentByUserId(userId,isDeleted);
            for (WorkflowComponent workflowComponent:workflowComponents
                 ) {
                CustomComponentInfo customComponentInfo = new CustomComponentInfo();
                customComponentInfo.setId(workflowComponent.getId());
                customComponentInfo.setName(workflowComponent.getName());
                customComponentInfo.setComponentDesc(workflowComponent.getWorkflowComponentDesc());
                customComponentInfo.setCreateTime(workflowComponent.getCreateTime());
                customComponentInfo.setTags(workflowComponent.getTag());
                customComponentInfo.setGgeditorObjectString(workflowComponent.getGgeditorObjectString());
                componentInfoList.add(customComponentInfo);
            }
        }
        PageInfo<CustomComponentInfo> pageInfo = new PageInfo<>(componentInfoList);
        return pageInfo;
    }

    @Override
    public Map<String,List<ComponentInfoVO>> loadCustomComponentInfo(Integer userId){
        List<CustomComponent> customComponentList = customComponentMapper.loadCustomComponentByUserIdAndType(userId);
        List<ComponentInfoVO> modelComponentInfoList = new ArrayList<>();
        List<ComponentInfoVO> algorithmComponentInfoList = new ArrayList<>();
        List<ComponentInfoVO> processComponentInfoList = new ArrayList<>();
        List<WorkflowComponent> workflowComponents = workflowComponentMapper.selectWorkflowComponentByUserId(userId,0);
        Map<String,List<ComponentInfoVO>> result = new HashMap<>();
        for (CustomComponent customComponent:customComponentList
        ) {
            ComponentInfo componentInfo = componentInfoMapper.selectComponentInfoById(customComponent.getFkComponentInfoId());
            ComponentInfoVO componentInfoVO = buildComponentInfoVO(componentInfo);
            componentInfoVO.setComponentType(customComponent.getType().toString());
            if(customComponent.getType()==2)
                modelComponentInfoList.add(componentInfoVO);
            if(customComponent.getType()==0)
                algorithmComponentInfoList.add(componentInfoVO);
        }
        result.put("CustomModel Component",modelComponentInfoList);
        result.put("CustomAlgorithm Component",algorithmComponentInfoList);
        for (WorkflowComponent workflowComponent:
                workflowComponents) {
             ComponentInfoVO componentInfoVO = new ComponentInfoVO();
             componentInfoVO.setComponentType("1");
             componentInfoVO.setComponentId(workflowComponent.getId());
             componentInfoVO.setComponentName(workflowComponent.getName());
             componentInfoVO.setComponentNameChs(workflowComponent.getName());
             componentInfoVO.setGgeditorObjectString(workflowComponent.getGgeditorObjectString());
             processComponentInfoList.add(componentInfoVO);
        }
        result.put("CustomProcess Component",processComponentInfoList);
        return result;
    }

    @Override
    public Map<String,List<ComponentInfoVO>> loadPublicComponentInfo(Integer isMl){
        List<ComponentInfo> systemComponentInfos = componentInfoMapper.loadPublicComponentInfo(isMl);
        List<ComponentInfoVO> systemComponentVOs = new ArrayList<>();
        for(ComponentInfo componentInfo:systemComponentInfos){
            systemComponentVOs.add(buildComponentInfoVO(componentInfo));
        }
        Map<String,List<ComponentInfoVO>> result = new HashMap<>();
        result.put("System Component",systemComponentVOs);
        return result;
    }

    /**
     * 输入componentInfo，组装成componentInfoVO
     * @param componentInfo
     * @return
     */
    private ComponentInfoVO buildComponentInfoVO(ComponentInfo componentInfo){
        ComponentInfoVO componentInfoVO = new ComponentInfoVO();
        componentInfoVO.setComponentId(componentInfo.getId());
        componentInfoVO.setComponentName(componentInfo.getName());
        componentInfoVO.setComponentNameChs(componentInfo.getComponentNameChs());
        componentInfoVO.setComponentDesc(componentInfo.getComponentDesc());
        componentInfoVO.setIsMl(componentInfo.getIsMl());
        List<Variable> variables = new ArrayList<>();
        //根据componentInfoId检索参数
        List<ComponentParameter> componentVariablesList = componentParamMapper.selectComponentParameterByComponentId(componentInfo.getId());
        for(ComponentParameter componentParameter:componentVariablesList){
            Variable variable = new Variable();
            variable.setVariableName(componentParameter.getName());
            variable.setDefaultValue(componentParameter.getDefaultValue());
            variable.setVariableDes(componentParameter.getParameterDesc());
//            枚举型,根据enumIdString查询对应enum，将其值加入到集合里
            if(componentParameter.getParameterType().equals("1")){
                List<EnumValue> enumValueList = enumValueMapper.selectEnumValueByVariableId(componentParameter.getId());
                variable.setEnums(enumValueList);
            }
            variable.setVariableType(Integer.parseInt(componentParameter.getParameterType()));
            variables.add(variable);
        }
        componentInfoVO.setVariables(variables);
        return componentInfoVO;
    }
    @Override
    public boolean deleteWorkflowComponent(Integer workflowComponentId){
        return workflowComponentMapper.deleteWorkflowComponent(workflowComponentId)==1;
    }
    @Override
    public boolean deleteWorkflowComponentPermanently(List<Integer> componentIds){
        return workflowComponentMapper.deleteWorkflowComponentPermanently(componentIds)==2;
    }
}
