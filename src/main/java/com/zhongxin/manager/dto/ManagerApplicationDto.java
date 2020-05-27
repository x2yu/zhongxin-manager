package com.zhongxin.manager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhongxin.manager.entity.TblManager;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: x2yu
 * @Date: 2020/5/27 14:00
 * @Describe：
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ManagerApplicationDto {
    /**
     * 应用id
     */
    private Integer appId;

    /**
     * 应用名
     */
    private String appName;

    /**
     * 应用图片
     */
    private String appPic;


    /**
     * 应用负责人集合
     */
    List<TblManager> managers;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


}
