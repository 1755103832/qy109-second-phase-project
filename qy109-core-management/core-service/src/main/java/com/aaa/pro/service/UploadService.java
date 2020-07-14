package com.aaa.pro.service;

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
        String newFileName = FileNameUtils.getFileName();
        // 3.截取后缀名，拼接到新的文件名上
        // newFileName = newFileName + oleFileName.substring(oleFileName.lastIndexOf(POINT));
        assert oleFileName != null;
        newFileName += oleFileName.substring(oleFileName.lastIndexOf(POINT));
        // 4.获取文件的上传路径(2020/07/10)
        String filePath = DateUtils.formatDate(new Date(), DATE_FORMAT);
        // 5.调用文件上传工具类
        try {
            return FtpUtils.upload(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername(),
                    ftpProperties.getPassword(), ftpProperties.getBasePath(), filePath, newFileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
