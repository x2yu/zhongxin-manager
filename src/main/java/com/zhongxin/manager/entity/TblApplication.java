package com.zhongxin.manager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 应用信息表 
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TblApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "app_id", type = IdType.AUTO)
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
     * 应用类别id
     */
    private Integer cateId;

    /**
     * 应用文档id
     */
    private Integer docId;

    /**
     * 开发者id
     */
    private Integer managerId;

    /**
     * 应用版本号
     */
    private String version;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;


}
