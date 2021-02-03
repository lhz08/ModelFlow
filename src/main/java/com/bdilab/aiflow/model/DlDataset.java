package com.bdilab.aiflow.model;

import java.io.Serializable;
import java.util.Date;

public class DlDataset implements Serializable {
    //为了使用mybatis二级缓存 实现序列化接口
    private static final long serialVersionUID = 1L;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dl_dataset.id
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dl_dataset.name
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dl_dataset.type
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    private Byte type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dl_dataset.fk_user_id
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    private Integer fkUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dl_dataset.tags
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    private String tags;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dl_dataset.is_deleted
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    private Byte isDeleted;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dl_dataset.dataset_addr
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    private String datasetAddr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dl_dataset.dataset_desc
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    private String datasetDesc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dl_dataset.create_time
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dl_dataset.label_file_addr
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    private String labelFileAddr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dl_dataset.label_config
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    private String labelConfig;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dl_dataset.origin_file_type
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    private Byte originFileType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dl_dataset.is_annotation
     *是否标注
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    private Byte isAnnotation;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dl_dataset.id
     *
     * @return the value of dl_dataset.id
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dl_dataset.id
     *
     * @param id the value for dl_dataset.id
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dl_dataset.name
     *
     * @return the value of dl_dataset.name
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dl_dataset.name
     *
     * @param name the value for dl_dataset.name
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dl_dataset.type
     *
     * @return the value of dl_dataset.type
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public Byte getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dl_dataset.type
     *
     * @param type the value for dl_dataset.type
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dl_dataset.fk_user_id
     *
     * @return the value of dl_dataset.fk_user_id
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public Integer getFkUserId() {
        return fkUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dl_dataset.fk_user_id
     *
     * @param fkUserId the value for dl_dataset.fk_user_id
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public void setFkUserId(Integer fkUserId) {
        this.fkUserId = fkUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dl_dataset.tags
     *
     * @return the value of dl_dataset.tags
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public String getTags() {
        return tags;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dl_dataset.tags
     *
     * @param tags the value for dl_dataset.tags
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dl_dataset.is_deleted
     *
     * @return the value of dl_dataset.is_deleted
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public Byte getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dl_dataset.is_deleted
     *
     * @param isDeleted the value for dl_dataset.is_deleted
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dl_dataset.dataset_addr
     *
     * @return the value of dl_dataset.dataset_addr
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public String getDatasetAddr() {
        return datasetAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dl_dataset.dataset_addr
     *
     * @param datasetAddr the value for dl_dataset.dataset_addr
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public void setDatasetAddr(String datasetAddr) {
        this.datasetAddr = datasetAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dl_dataset.dataset_desc
     *
     * @return the value of dl_dataset.dataset_desc
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public String getDatasetDesc() {
        return datasetDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dl_dataset.dataset_desc
     *
     * @param datasetDesc the value for dl_dataset.dataset_desc
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public void setDatasetDesc(String datasetDesc) {
        this.datasetDesc = datasetDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dl_dataset.create_time
     *
     * @return the value of dl_dataset.create_time
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dl_dataset.create_time
     *
     * @param createTime the value for dl_dataset.create_time
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dl_dataset.label_file_addr
     *
     * @return the value of dl_dataset.label_file_addr
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public String getLabelFileAddr() {
        return labelFileAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dl_dataset.label_file_addr
     *
     * @param labelFileAddr the value for dl_dataset.label_file_addr
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public void setLabelFileAddr(String labelFileAddr) {
        this.labelFileAddr = labelFileAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dl_dataset.label_config
     *
     * @return the value of dl_dataset.label_config
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public String getLabelConfig() {
        return labelConfig;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dl_dataset.label_config
     *
     * @param labelConfig the value for dl_dataset.label_config
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public void setLabelConfig(String labelConfig) {
        this.labelConfig = labelConfig;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dl_dataset.origin_file_type
     *
     * @return the value of dl_dataset.origin_file_type
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public Byte getOriginFileType() {
        return originFileType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dl_dataset.origin_file_type
     *
     * @param originFileType the value for dl_dataset.origin_file_type
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public void setOriginFileType(Byte originFileType) {
        this.originFileType = originFileType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dl_dataset.is_annotation
     *
     * @return the value of dl_dataset.is_annotation
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public Byte getIsAnnotation() {
        return isAnnotation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dl_dataset.is_annotation
     *
     * @param isAnnotation the value for dl_dataset.is_annotation
     *
     * @mbg.generated Mon Jan 25 13:13:31 CST 2021
     */
    public void setIsAnnotation(Byte isAnnotation) {
        this.isAnnotation = isAnnotation;
    }
}