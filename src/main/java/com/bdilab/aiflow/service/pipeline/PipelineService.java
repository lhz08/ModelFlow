package com.bdilab.aiflow.service.pipeline;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

/**
 * @author smile
 * @data 2020/9/15 10:09
 **/
public interface PipelineService {

    public Map generatePipeline(String workflowXmlAddr, Integer userId);

    String uploadPipeline(String name, String description, File file);

    String getPipelineById(String pipelineId);

    boolean deletePipelineById(String pipelineId);
}

