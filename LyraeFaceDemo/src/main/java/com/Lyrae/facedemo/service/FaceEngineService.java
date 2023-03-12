package com.Lyrae.facedemo.service;

import com.Lyrae.facedemo.dto.FaceUserInfo;
import com.Lyrae.facedemo.dto.ProcessInfo;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.toolkit.ImageInfo;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 面部引擎service
 * @Author:Lyrae
 */
public interface FaceEngineService {

    /**
     * 检测人脸
     * @param imageInfo
     * @return
     */
    List<FaceInfo> detectFaces(ImageInfo imageInfo);

    /**
     * 处理人脸
     * @param imageInfo
     * @return
     */
    List<ProcessInfo> process(ImageInfo imageInfo);

    /**
     * 提取人脸特征
     * @param imageInfo
     * @return
     * @throws InterruptedException
     */
    byte[] extractFaceFeature(ImageInfo imageInfo) throws InterruptedException;

    /**
     * 比较人脸特征
     * @param faceFeature   脸部特征
     * @param groupId   分组id
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    List<FaceUserInfo> compareFaceFeature(byte[] faceFeature, Integer groupId) throws InterruptedException, ExecutionException;
}
