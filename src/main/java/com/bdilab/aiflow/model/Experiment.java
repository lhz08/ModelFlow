package com.bdilab.aiflow.model;

import io.swagger.models.auth.In;

//import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class Experiment {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column experiment.id
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column experiment.name
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column experiment.fk_workflow_id
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    private Integer fkWorkflowId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column experiment.is_deleted
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    private Integer isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column experiment.is_mark_template
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    private Integer isMarkTemplate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column experiment.create_time
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column experiment.param_json_string
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    private String paramJsonString;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column experiment.experiment_desc
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    private String experimentDesc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column experiment.id
     *
     * @return the value of experiment.id
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column experiment.id
     *
     * @param id the value for experiment.id
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column experiment.name
     *
     * @return the value of experiment.name
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column experiment.name
     *
     * @param name the value for experiment.name
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column experiment.fk_workflow_id
     *
     * @return the value of experiment.fk_workflow_id
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    public Integer getFkWorkflowId() {
        return fkWorkflowId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column experiment.fk_workflow_id
     *
     * @param fkWorkflowId the value for experiment.fk_workflow_id
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    public void setFkWorkflowId(Integer fkWorkflowId) {
        this.fkWorkflowId = fkWorkflowId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column experiment.is_deleted
     *
     * @return the value of experiment.is_deleted
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column experiment.is_deleted
     *
     * @param isDeleted the value for experiment.is_deleted
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column experiment.is_mark_template
     *
     * @return the value of experiment.is_mark_template
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    public Integer getIsMarkTemplate() {
        return isMarkTemplate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column experiment.is_mark_template
     *
     * @param isMarkTemplate the value for experiment.is_mark_template
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    public void setIsMarkTemplate(Integer isMarkTemplate) {
        this.isMarkTemplate = isMarkTemplate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column experiment.create_time
     *
     * @return the value of experiment.create_time
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column experiment.create_time
     *
     * @param createTime the value for experiment.create_time
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column experiment.param_json_string
     *
     * @return the value of experiment.param_json_string
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    public String getParamJsonString() {
        return paramJsonString;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column experiment.param_json_string
     *
     * @param paramJsonString the value for experiment.param_json_string
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    public void setParamJsonString(String paramJsonString) {
        this.paramJsonString = paramJsonString;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column experiment.experiment_desc
     *
     * @return the value of experiment.experiment_desc
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    public String getExperimentDesc() {
        return experimentDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column experiment.experiment_desc
     *
     * @param experimentDesc the value for experiment.experiment_desc
     *
     * @mbggenerated Sat Aug 29 14:37:59 CST 2020
     */
    public void setExperimentDesc(String experimentDesc) {
        this.experimentDesc = experimentDesc;
    }
}