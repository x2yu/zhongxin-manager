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
 * 应用文档表 
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TblDoc implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文档表id
     */
    @TableId(value = "doc_id", type = IdType.AUTO)
    private Integer docId;

    /**
     * 应用id
     */
    private Integer appId;

    /**
     * 文档内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;


}
