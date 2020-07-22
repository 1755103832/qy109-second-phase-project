package com.aaa.pro.service;

import cn.hutool.core.date.DateTime;
import com.aaa.pro.mapper.UploadLogMapper;
import com.aaa.pro.model.UploadLog;
import com.aaa.pro.properties.FtpProperties;
import com.aaa.pro.utils.FileNameUtils;
import com.aaa.pro.utils.FtpUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

import com.aaa.pro.utils.DateUtils;

import static com.aaa.pro.staticproperties.DateTimeFormatProperties.*;
import static com.aaa.pro.staticproperties.RedisProperties.*;

/**
 * @Author zyb
 * @Date Create in 2020/7/10 15:50
 * @Description
 **/
@Service
public class UploadService {

    @Autowired
    private FtpProperties ftpProperties;

    @Autowired
    private UploadLogMapper uploadLogMapper;

    /**
     * @Author zyb
     * @Description 文件上传
     * @Date 2020/7/10 15:53
     * @Param [file]
     * @Return java.lang.Boolean
     **/
    public Boolean upload(MultipartFile file) {
        // 1.获取文件的远程名称(为了获取后缀名)
        String oleFileName = file.getOriginalFilename();
        // 2.生成新的文件名
        String newFileName = FileNameUtils.getNewFileName();
        // 3.截取后缀名，拼接到新的文件名上
        assert oleFileName != null;
        newFileName += oleFileName.substring(oleFileName.lastIndexOf(POINT));
        // 4.获取文件的上传路径(2020/07/10)
        String filePath = DateUtils.formatDate(new Date(), DATE_FORMAT);
        // 5.调用文件上传工具类
        try {
            Boolean aBoolean = FtpUtils.upload(
                    ftpProperties.getHost(),
                    ftpProperties.getPort(),
                    ftpProperties.getUsername(),
                    ftpProperties.getPassword(),
                    ftpProperties.getBasePath(),
                    filePath,
                    newFileName,
                    file.getInputStream());
            if (aBoolean) {
                // 说明上传文件成功，需要把上传信息保存到数据库中
                UploadLog uploadLog = new UploadLog();
                String headPic = ftpProperties.getHttpPath() + "/" + filePath + "/" + newFileName;
                uploadLog.setHeadPicPath(headPic);
                uploadLog.setOriginalFileName(oleFileName);
                uploadLog.setNewFileName(newFileName);
                uploadLog.setUploadDatetime(DateTime.now().toString());
                uploadLogMapper.insert(uploadLog);
            }
            return aBoolean;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
