package com.bdilab.aiflow.mapper;

import com.bdilab.aiflow.model.WorkflowComponent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkflowComponentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow_component
     *
     * @mbg.generated Tue Dec 08 11:06:52 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow_component
     *
     * @mbg.generated Tue Dec 08 11:06:52 CST 2020
     */
    int insert(WorkflowComponent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow_component
     *
     * @mbg.generated Tue Dec 08 11:06:52 CST 2020
     */
    int insertSelective(WorkflowComponent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow_component
     *
     * @mbg.generated Tue Dec 08 11:06:52 CST 2020
     */
    WorkflowComponent selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow_component
     *
     * @mbg.generated Tue Dec 08 11:06:52 CST 2020
     */
    int updateByPrimaryKeySelective(WorkflowComponent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow_component
     *
     * @mbg.generated Tue Dec 08 11:06:52 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(WorkflowComponent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow_component
     *
     * @mbg.generated Tue Dec 08 11:06:52 CST 2020
     */
    int updateByPrimaryKey(WorkflowComponent record);

    List<WorkflowComponent> selectWorkflowComponentByUserId(@Param("userId") Integer userId,@Param("isDeleted") Integer isDeleted);

    int deleteWorkflowComponent(Integer workflowComponentId);

    int deleteWorkflowComponentPermanently(List<Integer> componentIds);

    int restoreComponent(List<Integer> componentIds);
}