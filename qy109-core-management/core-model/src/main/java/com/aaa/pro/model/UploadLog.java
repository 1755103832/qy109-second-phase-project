package com.aaa.pro.model;

import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author zyb
 * @Date Create in 2020/7/22 20:49
 * @Description
 **/
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "t_upload_log")
public class UploadLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "head_pic_path")
    private String headPicPath;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "new_file_name")
    private String newFileName;

    @Column(name = "upload_datetime")
    private String uploadDatetime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return head_pic_path
     */
    public String getHeadPicPath() {
        return headPicPath;
    }

    /**
     * @param headPicPath
     */
    public void setHeadPicPath(String headPicPath) {
        this.headPicPath = headPicPath == null ? null : headPicPath.trim();
    }

    /**
     * @return original_file_name
     */
    public String getOriginalFileName() {
        return originalFileName;
    }

    /**
     * @param originalFileName
     */
    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName == null ? null : originalFileName.trim();
    }

    /**
     * @return new_file_name
     */
    public String getNewFileName() {
        return newFileName;
    }

    /**
     * @param newFileName
     */
    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName == null ? null : newFileName.trim();
    }

    /**
     * @return upload_datetime
     */
    public String getUploadDatetime() {
        return uploadDatetime;
    }

    /**
     * @param uploadDatetime
     */
    public void setUploadDatetime(String uploadDatetime) {
        this.uploadDatetime = uploadDatetime == null ? null : uploadDatetime.trim();
    }
}