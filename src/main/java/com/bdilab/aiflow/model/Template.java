package com.bdilab.aiflow.model;

public class Template {

    private Integer id;

    private String name;

    private Integer type;

    private Integer fkUserId;

    private Integer fkWorkflowId;

    private Integer fkExperimentId;

    private String tags;

    private Integer isDeleted;

    private String workflowAddr;

    private String paramJsonString;

    private String ggeditorObjectString;

    private String templateDesc;

    public String getParamJsonString() {
        return paramJsonString;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column template.param_json_string
     *
     * @param paramJsonString the value for template.param_json_string
     *
     * @mbg.generated Tue Sep 01 22:46:06 CST 2020
     */
    public void setParamJsonString(String paramJsonString) {
        this.paramJsonString = paramJsonString;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column template.ggeditor_object_string
     *
     * @return the value of template.ggeditor_object_string
     *
     * @mbg.generated Tue Sep 01 22:46:06 CST 2020
     */
    public String getGgeditorObjectString() {
        return ggeditorObjectString;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column template.ggeditor_object_string
     *
     * @param ggeditorObjectString the value for template.ggeditor_object_string
     *
     * @mbg.generated Tue Sep 01 22:46:06 CST 2020
     */
    public void setGgeditorObjectString(String ggeditorObjectString) {
        this.ggeditorObjectString = ggeditorObjectString;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column template.template_desc
     *
     * @return the value of template.template_desc
     *
     * @mbg.generated Tue Sep 01 22:46:06 CST 2020
     */
    public String getTemplateDesc() {
        return templateDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column template.template_desc
     *
     * @param templateDesc the value for template.template_desc
     *
     * @mbg.generated Tue Sep 01 22:46:06 CST 2020
     */
    public void setTemplateDesc(String templateDesc) {
        this.templateDesc = templateDesc;
    }
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column template.id
     *
     * @param id the value for template.id
     *
     * @mbg.generated Tue Sep 01 22:46:06 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column template.name
     *
     * @return the value of template.name
     *
     * @mbg.generated Tue Sep 01 22:46:06 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column template.name
     *
     * @param name the value for template.name
     *
     * @mbg.generated Tue Sep 01 22:46:06 CST 2020
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column template.type
     *
     * @return the value of template.type
     *
     * @mbg.generated Tue Sep 01 22:46:06 CST 2020
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column template.type
     *
     * @param type the value for template.type
     *
     * @mbg.generated Tue Sep 01 22:46:06 CST 2020
     */
    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFkWorkflowId() {
        return fkWorkflowId;
    }

    public void setFkWorkflowId(Integer fkWorkflowId) {
        this.fkWorkflowId = fkWorkflowId;
    }

    public Integer getFkUserId() {
        return fkUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column template.fk_user_id
     *
     * @param fkUserId the value for template.fk_user_id
     *
     * @mbg.generated Tue Sep 01 22:46:06 CST 2020
     */
    public void setFkUserId(Integer fkUserId) {
        this.fkUserId = fkUserId;
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column template.fk_experiment_id
     *
     * @return the value of template.fk_experiment_id
     *
     * @mbg.generated Tue Sep 01 22:46:06 CST 2020
     */
    public Integer getFkExperimentId() {
        return fkExperimentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column template.fk_experiment_id
     *
     * @param fkExperimentId the value for template.fk_experiment_id
     *
     * @mbg.generated Tue Sep 01 22:46:06 CST 2020
     */
    public void setFkExperimentId(Integer fkExperimentId) {
        this.fkExperimentId = fkExperimentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column template.tags
     *
     * @return the value of template.tags
     *
     * @mbg.generated Tue Sep 01 22:46:06 CST 2020
     */
    public String getTags() {
        return tags;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column template.tags
     *
     * @param tags the value for template.tags
     *
     * @mbg.generated Tue Sep 01 22:46:06 CST 2020
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column template.is_deleted
     *
     * @return the value of template.is_deleted
     *
     * @mbg.generated Tue Sep 01 22:46:06 CST 2020
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column template.is_deleted
     *
     * @param isDeleted the value for template.is_deleted
     *
     * @mbg.generated Tue Sep 01 22:46:06 CST 2020
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column template.workflow_addr
     *
     * @return the value of template.workflow_addr
     *
     * @mbg.generated Tue Sep 01 22:46:06 CST 2020
     */
    public String getWorkflowAddr() {
        return workflowAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column template.workflow_addr
     *
     * @param workflowAddr the value for template.workflow_addr
     *
     * @mbg.generated Tue Sep 01 22:46:06 CST 2020
     */
    public void setWorkflowAddr(String workflowAddr) {
        this.workflowAddr = workflowAddr;
    }
}