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
 * 应用开发者中间表 
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TblAppManager implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 中间表id
     */
    @TableId(value = "app_mana_id", type = IdType.AUTO)
    private Integer appManaId;

    /**
     * 应用id
     */
    private Integer appId;

    /**
     * 负责人id
     */
    private Integer managerId;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;


}
