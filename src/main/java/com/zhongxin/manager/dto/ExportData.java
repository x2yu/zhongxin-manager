package com.zhongxin.manager.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhongxin.manager.entity.TblManager;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @Author: x2yu
 * @Date: 2020/5/28 3:43
 * @Describe：
 */

@Data
public class ExportData {
    @ExcelProperty("应用id")
    private Integer appId;

    /**
     * 应用名
     */
    @ExcelProperty("应用名称")
    private String appName;

    /**
     * 应用图片
     */
    @ExcelProperty("应用图标")
    private String appPic;

    /**
     * 应用负责人集合
     */
    @ExcelProperty("负责人集合")
    private String  managers;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("创建时间")
    private Date createdTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("更新时间")
    private Date updateTime;

}
