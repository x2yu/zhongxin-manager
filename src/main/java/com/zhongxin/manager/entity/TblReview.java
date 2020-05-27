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
 * 评价表 
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TblReview implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评价表id
     */
    @TableId(value = "review_id", type = IdType.AUTO)
    private Integer reviewId;

    /**
     * 应用id
     */
    private Integer appId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;


}
