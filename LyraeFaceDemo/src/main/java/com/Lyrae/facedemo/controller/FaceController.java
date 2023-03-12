package com.Lyrae.facedemo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import com.Lyrae.facedemo.base.Result;
import com.Lyrae.facedemo.base.Results;
import com.Lyrae.facedemo.dto.FaceSearchResDto;
import com.Lyrae.facedemo.dto.FaceUserInfo;
import com.Lyrae.facedemo.dto.ProcessInfo;
import com.Lyrae.facedemo.enums.ErrorCodeEnum;
import com.Lyrae.facedemo.pojo.UserFaceInfo;
import com.Lyrae.facedemo.service.FaceEngineService;
import com.Lyrae.facedemo.service.UserFaceInfoService;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * @Author:Lyrae
 */
@Controller
@Slf4j
public class FaceController {

    @Resource
    private FaceEngineService faceEngineService;

    @Resource
    private UserFaceInfoService userFaceInfoService;

    @RequestMapping(value = "/demo",method = RequestMethod.POST)
    public ModelAndView demo(){
        return new ModelAndView("demo");
    }

    /**
     * 人脸添加
     * @param file
     * @param groupId
     * @param name
     * @return
     */
    @RequestMapping(value = "/faceAdd",method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> faceAdd(@RequestParam("file") String file , @RequestParam("groupId") Integer groupId , @RequestParam("name") String name){
        try{
            if(file == null){
                return Results.newFailedResult("file is null");
            }
            if (groupId == null){
                return Results.newFailedResult("groupId is null");
            }
            if (name == null){
                return Results.newFailedResult("name is null");
            }

            //将图片使用decodeBase64进行编码，并返回一个byte字节数组
            byte[] decode = Base64.decode(base64Process(file));
            //得到图片ARGB信息
            ImageInfo imageInfo = ImageFactory.getRGBData(decode);

            //人脸特征获取
            byte[] bytes = faceEngineService.extractFaceFeature(imageInfo);

            if (bytes == null){
                return  Results.newFailedResult(ErrorCodeEnum.NO_FACE_DETECTED);
            }

            UserFaceInfo userFaceInfo = new UserFaceInfo();
            userFaceInfo.setName(name);
            userFaceInfo.setGroupId(groupId);
            userFaceInfo.setFaceFeature(bytes);
            //使用hutool工具获得一个随机的字符串
            userFaceInfo.setFaceId(RandomUtil.randomString(10));

            //将人脸特征插入到数据库
            userFaceInfoService.insertSelective(userFaceInfo);
            log.info("faceAdd:" + name);
            return Results.newSuccessResult(ErrorCodeEnum.MOK);
        }catch (Exception e){
            log.error("【出现异常】 e = {}",e );
        }
        return Results.newFailedResult(ErrorCodeEnum.UNKNOWN);
    }

    /**
     * 人脸识别
     * @param file
     * @param groupId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/faceSearch",method = RequestMethod.POST)
    @ResponseBody
    public Result<FaceSearchResDto> faceSearch(String file,Integer groupId) throws Exception{
        if (groupId == null){
            return Results.newFailedResult("groupId is null");
        }
        byte[] decode = Base64.decode(base64Process(file));
        //读取本地照片
        BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(decode));
        //将照片信息进行处理
        ImageInfo imageInfo = ImageFactory.bufferedImage2ImageInfo(bufImage);

        //人脸特征识别
        byte[] bytes = faceEngineService.extractFaceFeature(imageInfo);
        if (bytes == null){
            return  Results.newFailedResult(ErrorCodeEnum.NO_FACE_DETECTED);
        }
        //人脸对比,获取比对结果
        List<FaceUserInfo> userFaceInfoList = faceEngineService.compareFaceFeature(bytes,groupId);

        //判断集合是否为空
        if (CollectionUtil.isNotEmpty(userFaceInfoList)){
            FaceUserInfo faceUserInfo = userFaceInfoList.get(0);
            FaceSearchResDto faceSearchResDto = new FaceSearchResDto();
            //浅拷贝,只是调用子对象的set方法，并没有将所有属性拷贝。
            BeanUtil.copyProperties(faceUserInfo,faceSearchResDto);
            List<ProcessInfo> processInfoList = faceEngineService.process(imageInfo);
            if (CollectionUtil.isNotEmpty(processInfoList)){
                //人脸检测
                List<FaceInfo> faceInfoList = faceEngineService.detectFaces(imageInfo);
                int left = faceInfoList.get(0).getRect().getLeft();
                int top = faceInfoList.get(0).getRect().getTop();
                int width = faceInfoList.get(0).getRect().getRight()-left;
                int height = faceInfoList.get(0).getRect().getBottom()-top;

                //Swing组件下的二维图形处理类 java2D
                Graphics2D graphics2D = bufImage.createGraphics();
                //红色
                graphics2D.setColor(Color.RED);
                //定义线条的特征
                BasicStroke stroke = new BasicStroke(5f);
                //设置笔画的轮廓特性（如画笔宽度、实线、虚线等）
                graphics2D.setStroke(stroke);
                //根据人脸特征绘制图像
                graphics2D.drawRect(left,top,width,height);
                //创建一个新的byte数组输出流
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                //保存java 2D生成的图像,保存为jpg格式放在输出流对象当中
                ImageIO.write(bufImage,"jpg",outputStream);
                //创建一个与此输出流大小一样的新缓冲区
                byte[] bytes1 = outputStream.toByteArray();
                //将处理好的数据传输到Dto当中
                faceSearchResDto.setImage("data:image/jpeg;base64," + Base64Utils.encodeToString(bytes1));
                faceSearchResDto.setAge(processInfoList.get(0).getAge());
                faceSearchResDto.setGender(processInfoList.get(0).getGender().equals(1)?"女":"男");
            }
            return Results.newSuccessResult(faceSearchResDto);
        }
        return Results.newFailedResult(ErrorCodeEnum.FACE_DOES_NOT_MATCH);
    }

    /**
     * 人脸检测
     * @param image
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/detectFaces",method = RequestMethod.POST)
    @ResponseBody
    public List<FaceInfo> detectFaces(String image) throws IOException{
        byte[] decode = Base64.decode(image);
        InputStream inputStream = new ByteArrayInputStream(decode);
        ImageInfo imageInfo = ImageFactory.getRGBData(inputStream);

        if(inputStream != null){
            inputStream.close();
        }
        List<FaceInfo> faceInfoList = faceEngineService.detectFaces(imageInfo);

        return faceInfoList;
    }

    /**
     * 将图片转换为base64编码
     * @param base64Str
     * @return
     */
    private String base64Process(String base64Str){
        if(!StringUtils.isEmpty(base64Str)){
            String photoBase64 = base64Str.substring(0,30).toLowerCase();
            int indexOf = photoBase64.indexOf("base64,");
            if(indexOf > 0){
                base64Str = base64Str.substring(indexOf + 7);
            }
            return  base64Str;
        }else {
            return "";
        }
    }
}
